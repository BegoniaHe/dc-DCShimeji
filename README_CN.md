# Shimeji-ee: Shimeji 英文增强版

[![License](https://img.shields.io/badge/License-BSD%202--Clause-orange.svg)](https://opensource.org/licenses/BSD-2-Clause)
[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Platform](https://img.shields.io/badge/Platform-Windows-lightgrey.svg)](https://www.microsoft.com/windows/)

**🌐 语言选择 / Language:** [English](README.md) | [中文](README_CN.md)

Shimeji-ee 是一个可以在 Windows 桌面上自由游走玩耍的桌面宠物。这个宠物具有高度可配置性；其行为通过 XML 定义，动画/图像可以（费时费力地）自定义。

Shimeji 最初由 Group Finity 的 Yuki Yamada 创建（[http://www.group-finity.com/Shimeji/](http://www.group-finity.com/Shimeji/)）。这个 Shimeji 项目的分支不仅将程序/源码翻译为英文，还由 Kilkakon 和社区其他成员为 Shimeji 添加了额外的增强功能。

![Shimeji Banner](img/banner.bmp)

## 📋 目录

- [🌐 主页](#-主页)
- [📋 系统要求](#-系统要求)
- [🚀 快速开始](#-快速开始)
- [⚙️ 基础配置](#️-基础配置)
- [🔧 高级配置](#-高级配置)
- [📁 项目结构](#-项目结构)
- [🎨 创建自定义图像集](#-创建自定义图像集)
- [🔄 配置文件](#-配置文件)
- [🛠️ 构建说明](#️-构建说明)
- [❌ 如何退出](#-如何退出)
- [🗑️ 如何卸载](#️-如何卸载)
- [📚 源代码](#-源代码)
- [📦 依赖库](#-依赖库)
- [🐛 故障排除](#-故障排除)
- [📝 更新日志](#-更新日志)
- [📄 许可证](#-许可证)
- [🤝 贡献指南](#-贡献指南)

## 🌐 主页

**官方主页：** [http://kilkakon.com/shimeji](http://kilkakon.com/shimeji)

## 📋 系统要求

- **操作系统：** Windows Vista 或更高版本
- **Java 运行环境：** Java 8 或更高版本
- **内存：** 至少 512MB RAM（推荐更多，用于多个图像集）

## 🚀 快速开始

### 运行 Shimeji-ee

1. **双击** `Shimeji-ee.jar` 文件
2. **右键点击** 系统托盘图标查看通用选项
3. **右键点击** Shimeji 角色查看角色特定选项

### 快速教程

📺 **视频教程：** [如何运行 Shimeji](https://www.youtube.com/watch?v=S7fPCGh5xxo)

📺 **常见问题视频：** [常见问题及解决方案](https://www.youtube.com/watch?v=A1y9C1Vbn6Q)

💬 **Discord 社区：** [https://discord.gg/dcJGAn3](https://discord.gg/dcJGAn3)

## ⚙️ 基础配置

### 添加多个 Shimeji 类型

要拥有多个 Shimeji 类型，您需要多个图像集。每个图像集应放置在 `img/` 目录下的单独文件夹中。

#### 示例：添加蝙蝠侠 Shimeji

1. **创建文件夹：** `img/Batman/`
2. **添加图像：** 创建蝙蝠侠版本的 `shime1.png` 到 `shime46.png`
   - 文件名必须与原始 `img/Shimeji/` 文件完全匹配
   - 参考 `img/Shimeji/` 了解正确的角色位置
3. **启动 Shimeji-ee：** Shimeji 和蝙蝠侠都会出现
4. **右键点击蝙蝠侠** 查看蝙蝠侠特定选项

### 内存管理

⚠️ **重要提示：** 当加载多个图像集时，Shimeji-ee 可能会使用系统可用内存的 60%。

- **隐藏未使用的集合：** 将图像集移动到 `img/unused/` 文件夹
- **使用图像集选择器：** 在运行时选择特定的图像集
- **监控内存使用：** 特别是有很多图像集时

### 图像集选择器

图像集选择器工具允许您：

- 在启动时选择要加载的图像集
- 预览可用的图像集（每个集合需要 `shime1.png`）
- 在 `conf/settings.properties` 中保存偏好设置

## 🔧 高级配置

### 配置文件位置

所有配置文件都位于 `conf/` 文件夹中：

| 文件 | 用途 |
|------|------|
| `settings.properties` | 活动 Shimeji 和窗口交互设置 |
| `actions.xml` | 定义可用的 Shimeji 动作 |
| `behaviors.xml` | 定义执行动作的时机 |
| `logging.properties` | 日志配置 |
| `language.properties` | 本地化设置 |

### 每个角色的配置

每个 Shimeji 类型都可以通过以下方式进行自定义配置：

1. **图像集：** 位于 `img/[NAME]/`
2. **动作文件：**
   - `img/[NAME]/conf/actions.xml`（最高优先级）
   - `conf/[NAME]/actions.xml`（中等优先级）
   - `conf/actions.xml`（默认回退）
3. **行为文件：** 与动作文件类似的优先级系统

### 必需的动作和行为

#### 必需的动作（actions.xml）

- `ChaseMouse`
- `Fall`
- `Dragged`
- `Thrown`

#### 必需的行为（behaviors.xml）

- `ChaseMouse`
- `Fall`
- `Dragged`
- `Thrown`

### 自定义提示

- **禁用行为：** 在 behaviors.xml 中将频率设置为 `0`
- **限制 Shimeji 数量：** 在配置文件中编辑设置
- **自定义托盘图标：** 替换 `img/icon.png`

## 📁 项目结构

```text
dc-DCShimeji/
├── 📄 Shimeji-ee.jar          # 主可执行文件
├── 📄 Shimeji-ee.exe          # Windows 可执行文件
├── 📄 build.xml               # Ant 构建文件
├── 📄 MANIFEST.MF             # JAR 清单文件
├── 📁 conf/                   # 配置文件
│   ├── 📄 actions.xml         # 动作定义
│   ├── 📄 behaviors.xml       # 行为定义
│   ├── 📄 settings.properties # 应用程序设置
│   └── 📄 language_*.properties # 本地化文件
├── 📁 img/                    # 图像资源
│   ├── 📁 Shimeji/           # 默认角色图像
│   ├── 📁 KuroShimeji/       # 替代角色
│   └── 📁 unused/            # 禁用的图像集
├── 📁 lib/                    # 外部库
├── 📁 src/                    # 源代码
│   ├── 📁 com/               # 主要源代码包
│   ├── 📁 hqx/               # 图像缩放算法
│   └── ...
├── 📁 src_generic/           # 平台无关代码
├── 📁 src_win/               # Windows 特定代码
├── 📁 src_mac/               # macOS 特定代码
├── 📁 src_virtual/           # 虚拟机特定代码
└── 📁 target/                # 构建输出
    └── 📄 Shimeji-ee.jar     # 编译后的 JAR
```

## 🎨 创建自定义图像集

### 图像要求

- **格式：** 带透明度的 PNG
- **数量：** 46 个图像（shime1.png - shime46.png）
- **命名：** 必须与默认集合完全匹配
- **尺寸：** 建议所有图像保持一致的尺寸

### 图像集结构

```text
img/您的角色/
├── 📄 shime1.png    # 站立/空闲姿势（预览必需）
├── 📄 shime2.png    # 行走帧 1
├── 📄 shime3.png    # 行走帧 2
├── ...
└── 📄 shime46.png   # 最终动画帧
```

### 动画指南

参考默认的 `img/Shimeji/` 文件夹了解：

- 正确的角色定位
- 动画序列
- 精灵尺寸
- 透明度处理

## 🔄 配置文件

### settings.properties

```properties
# 活动图像集（逗号分隔）
ActiveShimeji=Shimeji

# Shimeji 可以交互的窗口
InteractiveWindows=Chat/Notepad/Friends/Windows Live Messenger

# UI 缩放
MenuDPI=96
```

### actions.xml 结构

```xml
<ActionList>
    <Action name="ActionName">
        <ImageList>image1.png,image2.png</ImageList>
        <Duration>1000</Duration>
    </Action>
</ActionList>
```

### behaviors.xml 结构

```xml
<BehaviorList>
    <Behavior name="BehaviorName" frequency="100">
        <Condition>condition</Condition>
        <NextBehavior>NextBehaviorName</NextBehavior>
    </Behavior>
</BehaviorList>
```

## 🛠️ 构建说明

### 前提条件

- Java 开发工具包（JDK）8 或更高版本
- Apache Ant（用于构建）

### 从源码构建

1. **克隆仓库**
2. **导航到项目目录**
3. **运行构建脚本：**

   ```batch
   # Windows
   build_and_run.bat
   
   # Unix/Linux/macOS
   ./build_and_run.sh
   ```

4. **或直接使用 Ant：**

   ```bash
   ant
   ```

### 构建输出

- 编译后的 JAR：`target/Shimeji-ee.jar`
- 所有依赖项都包含在 JAR 中

## ❌ 如何退出

**右键点击** 托盘图标并选择 **"Dismiss All"**

## 🗑️ 如何卸载

只需 **删除解压后的文件夹** - 不会修改注册表或系统文件。

## 📚 源代码

### 许可证

Shimeji-ee 源代码在 **新 BSD 许可证** 下可用。

### 使用权限

- ✅ 可在个人和商业项目中免费使用
- ✅ 允许修改和重新分发
- ✅ 鼓励源代码学习和研究
- ⚠️ 必须保留版权声明
- ⚠️ 必须遵循包含库的 zlib/libpng 许可证

## 📦 依赖库

| 库 | 用途 | 许可证 |
|----|----|-------|
| `jna.jar` | 原生系统集成 | LGPL |
| `examples.jar` | JNA 示例 | LGPL |
| `AbsoluteLayout.jar` | NetBeans 布局管理器 | 各种 |
| `nimrodlf.jar` | 外观和感觉 | 各种 |

## 🐛 故障排除

### 常见问题

#### Shimeji 图标出现但没有角色显示

1. **检查 Java 版本：** 确保安装了 Java 8+
2. **验证图像集：** 在 `img/` 中只有有效的图像集文件夹
3. **检查日志：** 查看 `ShimejiLogX.log` 中的错误
4. **内存问题：** 将额外的图像集移动到 `img/unused/`
5. **命令行测试：**

   ```batch
   "C:\Program Files (x86)\Java\jre8\bin\java" -jar Shimeji-ee.jar
   ```

#### 启动缓慢

- **原因：** 加载了太多图像集
- **解决方案：** 使用图像集选择器或将未使用的集合移动到 `img/unused/`

#### 内存不足错误

- **编辑内存分配：** 修改 `Shimeji-ee.bat`
- **更改：** `-Xmx1000m` 为更高的值（例如 `-Xmx2000m`）

#### 性能问题

- **在设置中减少活动 Shimeji 数量**
- **禁用资源密集型行为**
- **关闭其他内存密集型应用程序**

### 获取帮助

- 📺 [教程视频](https://www.youtube.com/watch?v=S7fPCGh5xxo)
- 📺 [常见问题视频](https://www.youtube.com/watch?v=A1y9C1Vbn6Q)
- 💬 [Discord 社区](https://discord.gg/dcJGAn3)
- 🐛 在主页上[报告问题](http://kilkakon.com/shimeji)

## 📝 更新日志

### 版本 1.0.3

- 显著减少了内存使用量

### 版本 1.0.2

- 添加了用于运行时选择的图像集选择器
- 添加了弹出错误消息以获得更好的用户反馈
- 更新了默认日志选项

### 版本 1.0.1

- 配置文件现在可以位于 `img/[NAME]/conf/` 下
- 程序不再因无效图像集而崩溃

### 版本 1.0.0

- Shimeji-ee 的初始版本

## 📄 许可证

```text
版权所有 (c) Shimeji-ee Group
保留所有权利。

在满足以下条件的情况下，允许以源代码和二进制形式重新分发和使用，
无论是否经过修改：

1. 源代码的重新分发必须保留上述版权声明、
   此条件列表和以下免责声明。

2. 二进制形式的重新分发必须在文档和/或
   随分发提供的其他材料中复制上述版权声明、
   此条件列表和以下免责声明。

本软件由版权所有者和贡献者"按原样"提供，
不承担任何明示或暗示的保证。
```

**作者：Kilkakon** - [kilkakon.com](http://kilkakon.com)

如果您在自己的项目中使用此作品，欢迎注明 Kilkakon 和最初参与此项目的人员。如果能提供到 kilkakon.com 的链接就更好了。

## 🤝 贡献指南

### 如何贡献

1. **Fork** 仓库
2. **创建** 功能分支
3. **进行** 更改
4. **彻底测试**
5. **提交** pull request

### 贡献领域

- 🎨 新角色图像集
- 🌍 翻译和本地化
- 🐛 错误修复和改进
- 📚 文档增强
- ✨ 新功能和行为

### 社区

- **Discord：** [https://discord.gg/dcJGAn3](https://discord.gg/dcJGAn3)
- **主页：** [http://kilkakon.com/shimeji](http://kilkakon.com/shimeji)

---

## 🎉 享受您的桌面伙伴
