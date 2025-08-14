package com.group_finity.mascot.script;

import com.group_finity.mascot.Main;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

// Rhino direct imports
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

// Java imports
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.group_finity.mascot.exception.VariableException;

/**
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */

public class Script extends Variable {

	// 使用 Rhino 原生 API，避免 JSR-223 依赖问题
	private static final boolean USE_RHINO_DIRECT = true;
	private static final ScriptEngine jsrEngine = initJSREngine();
	
	/**
	 * 初始化 JSR-223 引擎（作为备选方案）
	 */
	private static ScriptEngine initJSREngine() {
		if (USE_RHINO_DIRECT) {
			return null; // 优先使用 Rhino 直接 API
		}
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("rhino");
		if (engine != null) {
			return engine;
		}
		
		engine = manager.getEngineByName("nashorn");
		if (engine != null) {
			return engine;
		}
		
		engine = manager.getEngineByName("javascript");
		if (engine != null) {
			return engine;
		}
		
		return null; // 没有找到 JSR-223 引擎，将使用 Rhino 直接 API
	}
        
	private final String source;
	
	private final boolean clearAtInitFrame;
	
	private final CompiledScript compiled;
	private final org.mozilla.javascript.Script rhinoScript;
	
	private Object value;
	
	// 用于检测递归调用的线程本地变量
	private static final ThreadLocal<Set<String>> executingScripts = new ThreadLocal<Set<String>>() {
		@Override
		protected Set<String> initialValue() {
			return new HashSet<String>();
		}
	};
	
	public Script(final String source, final boolean clearAtInitFrame)  throws VariableException {
            
		this.source = source;
		this.clearAtInitFrame = clearAtInitFrame;
		
		try {
			if (USE_RHINO_DIRECT) {
				// 使用 Rhino 直接 API 编译脚本
				Context context = Context.enter();
				try {
					this.rhinoScript = context.compileString(this.source, "<script>", 1, null);
					this.compiled = null;
				} finally {
					Context.exit();
				}
			} else if (jsrEngine != null) {
				// 使用 JSR-223 引擎
				this.compiled = ((Compilable) jsrEngine).compile(this.source);
				this.rhinoScript = null;
			} else {
				throw new VariableException("No JavaScript engine available. Please ensure Rhino JAR is in classpath.");
			}
		} catch (final Exception e) {
			throw new VariableException( Main.getInstance( ).getLanguageBundle( ).getString( "ScriptCompilationErrorMessage" ) + ": "+this.source, e);
		}
	}

	@Override
	public String toString() {
		return this.isClearAtInitFrame() ? "#{"+this.getSource()+"}" : "${"+this.getSource()+"}";
	}
	
	@Override
	public void init() {
		setValue(null);
	}
	
	@Override
	public void initFrame() {
		if ( this.isClearAtInitFrame() ) {
			setValue(null);
		}
	}
	
	@Override
	public synchronized Object get(final VariableMap variables)  throws VariableException {
			
		if ( getValue()!=null ) {
			return getValue();
		}

		// 递归检测：防止同一个脚本在同一线程中被重复执行
		String scriptId = this.source + "@" + System.identityHashCode(this);
		Set<String> currentlyExecuting = executingScripts.get();
		
		if (currentlyExecuting.contains(scriptId)) {
			// 检测到递归调用，返回null避免无限递归
			return null;
		}
		
		currentlyExecuting.add(scriptId);
		try {
			if (USE_RHINO_DIRECT && rhinoScript != null) {
				// 使用 Rhino 直接 API 执行脚本
				Context context = Context.enter();
				try {
					context.setOptimizationLevel(-1); // 禁用优化，提高稳定性
					Scriptable scope = context.initStandardObjects();
					
					// 绑定变量到脚本作用域，采用保守策略避免递归
					if (variables != null) {
						Map<String, Variable> rawMap = variables.getRawMap();
						for (Map.Entry<String, Variable> entry : rawMap.entrySet()) {
							try {
								String key = entry.getKey();
								Variable variable = entry.getValue();
								
								// 更保守的变量绑定策略
								if (variable instanceof Script) {
									// 对于Script类型变量，只绑定已经有值的
									Script scriptVar = (Script) variable;
									Object existingValue = scriptVar.getValue();
									if (existingValue != null) {
										Object jsValue = Context.javaToJS(existingValue, scope);
										scope.put(key, scope, jsValue);
									}
								} else {
									// 对于非Script类型变量，尝试获取值
									try {
										Object value = variable.get(variables);
										if (value != null) {
											Object jsValue = Context.javaToJS(value, scope);
											scope.put(key, scope, jsValue);
										}
									} catch (StackOverflowError e) {
										// 如果遇到栈溢出，跳过这个变量
										continue;
									}
								}
							} catch (Exception e) {
								// 忽略单个变量绑定失败，继续处理其他变量
								// 静默处理，避免输出过多信息
							}
						}
					}
					
					Object result = rhinoScript.exec(context, scope);
					Object javaResult = Context.jsToJava(result, Object.class);
					setValue(javaResult);
					return javaResult;
				} finally {
					Context.exit();
				}
			} else if (compiled != null) {
				// 使用 JSR-223 引擎
				Object result = compiled.eval(variables);
				setValue(result);
				return result;
			} else {
				throw new VariableException("No script engine available");
			}
		} catch (final StackOverflowError e) {
			// 处理栈溢出错误
			throw new VariableException("Stack overflow detected in script execution: " + this.source, e);
		} catch (final OutOfMemoryError e) {
			// 处理内存溢出错误
			throw new VariableException("Out of memory error in script execution: " + this.source, e);
		} catch (final Exception e) {
			throw new VariableException( Main.getInstance( ).getLanguageBundle( ).getString( "ScriptEvaluationErrorMessage" ) + ": "+this.source, e);
		} finally {
			// 清理递归检测标记
			currentlyExecuting.remove(scriptId);
			if (currentlyExecuting.isEmpty()) {
				executingScripts.remove(); // 清理ThreadLocal，避免内存泄漏
			}
		}
	}

	private void setValue(final Object value) {
		this.value = value;
	}

	private Object getValue() {
		return this.value;
	}
	
	private boolean isClearAtInitFrame() {
		return this.clearAtInitFrame;
	}
	
	private String getSource() {
		return this.source;
	}
	
	/**
	 * 清理ThreadLocal资源，防止内存泄漏
	 * 应该在应用程序关闭时调用
	 */
	public static void cleanup() {
		executingScripts.remove();
	}
	
	/**
	 * 获取当前正在执行的脚本数量（用于调试）
	 */
	public static int getCurrentExecutingScriptCount() {
		Set<String> current = executingScripts.get();
		return current != null ? current.size() : 0;
	}
}
