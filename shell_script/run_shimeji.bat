@echo off
chcp 65001 > nul
cd /d "%~dp0"
title Shimeji-ee (JRE 21 便携版本)
echo ========================================
echo   Shimeji-ee - JRE 21 便携版本
echo ========================================
echo.

REM 检查是否存在绑定的 JRE 21
if exist "jre\bin\java.exe" (
    echo 检测到绑定的 JRE 21，使用绑定版本...
    set JAVA_CMD=jre\bin\java.exe
) else (
    echo 错误: 未找到绑定的 JRE 21
    echo 此便携版本仅支持内置的 JRE 21，请确保 jre 文件夹存在
    pause
    exit /b 1
)

if not exist "target\Shimeji-ee.jar" (
    echo 错误: 未找到 Shimeji-ee.jar 文件！
    echo 请确保 target 文件夹存在
    pause
    exit /b 1
)

echo 使用内置 JRE 21 启动 Shimeji-ee...
echo.
echo 启动 Shimeji-ee...
start "" "%JAVA_CMD%" -Dfile.encoding=UTF-8 ^
     -Xms64m ^
     -Xmx512m ^
     -cp "target\Shimeji-ee.jar;lib\*" ^
     com.group_finity.mascot.Main

echo.
echo Shimeji-ee 已成功启动！(使用内置 JRE 21)
echo 现在您可以安全地关闭此窗口。
echo 要停止 Shimeji-ee，请右键点击桌面精灵或使用任务管理器。
echo.
echo 提示: 此便携版本使用内置的 JRE 21，无需额外安装 Java
echo.
pause
