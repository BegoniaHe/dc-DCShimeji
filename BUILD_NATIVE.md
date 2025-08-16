# Shimeji-ee 原生构建指南

## 概述

此项目现在支持使用 Java 的 `jpackage` 工具创建原生 Windows 可执行文件。这样可以为用户提供更便捷的安装和使用体验。

## 前提条件

- Java 14 或更高版本（推荐 Java 21+）
- Apache Ant
- WiX Toolset 3.x（用于创建 MSI 安装程序）

## 构建选项

### 1. 传统 JAR 构建
```batch
ant jar
```
这会创建传统的 JAR 文件 `target/Shimeji-ee.jar`。

### 2. 原生 MSI 安装程序
```batch
ant jpackage
```
这会创建一个 Windows MSI 安装程序 `target/Shimeji-ee-1.0.9.msi`，包含：

- 原生启动器（不需要单独安装 Java）
- 所有必需的 Java 运行时组件
- 自动配置的 JVM 参数
- Windows 开始菜单集成

### 3. 便携版本
```batch
ant jpackage-portable
```
这会创建一个便携版本 `target/Shimeji-ee_1.0.9_Portable.zip`，包含：

- 完整的应用程序目录结构
- 内嵌的 Java 运行时
- 所有配置文件和资源
- 无需安装，解压即用

### 4. 一键构建所有版本
```batch
build_native.bat
```
这个批处理文件会依次创建 JAR、MSI 安装程序和便携版本。

## 构建目标详解

### `ant clean`
清理构建目录

### `ant compile`
编译 Java 源代码

### `ant jar`
创建可执行 JAR 文件

### `ant run`
运行应用程序（带正确的 JVM 参数）

### `ant jpackage`
创建 MSI 安装程序

### `ant jpackage-portable`
创建便携版本

### `ant zip`
创建传统的发布包（Calm/Professional/Mischievous 版本）

## 文件说明

构建完成后，`target` 目录将包含：

- `Shimeji-ee.jar` - 传统 JAR 文件
- `Shimeji-ee-1.0.9.msi` - Windows MSI 安装程序
- `Shimeji-ee_1.0.9_Portable.zip` - 便携版本

## JVM 参数

所有版本都预配置了必要的 JVM 参数：

- `--enable-native-access=ALL-UNNAMED`
- `--add-opens=java.base/java.lang=ALL-UNNAMED`
- `--add-opens=java.desktop/sun.awt=ALL-UNNAMED`
- `--add-opens=java.desktop/java.awt=ALL-UNNAMED`

这些参数确保应用程序在较新的 Java 版本上正常运行。

## 安装 WiX Toolset

如果遇到 WiX 相关错误，请安装 WiX Toolset：

1. 访问 <https://wixtoolset.org/>
2. 下载并安装 WiX Toolset 3.x
3. 确保安装路径添加到系统 PATH 环境变量

## 故障排除

### "找不到 jpackage 命令"
确保使用 Java 14 或更高版本。

### "WiX 相关错误"
安装 WiX Toolset 3.x 并将其添加到 PATH。

### "编译警告"
`finalize()` 方法的废弃警告是正常的，不影响构建。

## 使用建议

- **普通用户**：使用 MSI 安装程序，提供最佳的 Windows 集成体验
- **便携使用**：使用便携版本，适合在不同计算机间移动使用
- **开发者**：使用 JAR 文件，方便调试和开发

## 版本信息

当前版本：1.0.9

支持的 Java 版本：21+（编译）/ 14+（运行时内嵌）

支持的操作系统：Windows 10/11
