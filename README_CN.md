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
- [🤝 Affordances 交互系统教程](#-affordances-交互系统教程)
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

- **操作系统：** Windows
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

## 🤝 Affordances 交互系统教程

Affordances 系统允许不同的 Shimeji 角色之间进行交互动画。这个功能让您的桌面宠物能够彼此寻找、相遇并执行互动动画。

### 📖 交互原理

交互动作包含以下三个组件：

1. **广播（Broadcast）：** 一个 Shimeji 广播一个 affordance
2. **扫描移动（ScanMove）：** 其他 Shimeji 检测到这个 affordance 并向它移动
3. **交互（Interact）：** 当 Shimeji 相遇时，它们进行交互。如果交互成功完成，它们会切换到指定的新行为

### 🔧 步骤 1：创建广播动作（Broadcast）

首先，我们需要创建一个能够广播 affordance 的动作。

#### 广播动作类型

有三种广播类型：

- `Broadcast` - 对应 `Animate`
- `BroadcastStay` - 对应 `Stay`
- `BroadcastMove` - 对应 `Move`

#### 示例：创建坐着广播动作

**复制现有的坐着动作：**

```xml
<Action Name="Sit" Type="Stay" BorderType="Floor">
    <Animation>
        <Pose Image="/shime11.png" ImageAnchor="64,128" Velocity="0,0" Duration="250" />
    </Animation>
</Action>
```

**修改为广播动作：**

```xml
<Action Name="SitAffordance" Type="Embedded" Class="com.group_finity.mascot.action.Broadcast" Affordance="Cuddle" BorderType="Floor">
    <Animation>
        <Pose Image="/shime11.png" ImageAnchor="64,128" Velocity="0,0" Duration="250" />
    </Animation>
</Action>
```

**重要参数说明：**

- `Affordance="Cuddle"` - 这是 affordance 的标识符，所有相关动作必须使用相同的值

**创建动作序列：**

```xml
<Action Name="BroadcastAffordance" Type="Sequence">
    <ActionReference Name="SitAffordance" />
</Action>
```

**添加到 behaviors.xml：**

```xml
<Behavior Name="BroadcastAffordance" Frequency="1200" />
```

⚠️ **建议使用高频率，因为需要其他 Shimeji 注意到它。**

### 🔧 步骤 2：创建扫描移动动作（ScanMove）

现在创建能够寻找并移动到广播 Shimeji 的动作。

**复制奔跑动作：**

```xml
<Action Name="Run" Type="Move" BorderType="Floor">
    <Animation>
        <Pose Image="/shime1.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime2.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime1.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime3.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
    </Animation>
</Action>
```

**修改为 ScanMove 动作：**

```xml
<Action Name="RunAffordance" Type="Embedded" Class="com.group_finity.mascot.action.ScanMove" 
        Affordance="Cuddle" Behavior="IHugYou" TargetBehavior="IAmHugged" BorderType="Floor">
    <Animation>
        <Pose Image="/shime1.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime2.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime1.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime3.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
    </Animation>
</Action>
```

**关键参数说明：**

- `Affordance="Cuddle"` - 必须与广播动作中的值完全匹配
- `Behavior="IHugYou"` - 扫描 Shimeji 到达目标后执行的行为
- `TargetBehavior="IAmHugged"` - 广播 Shimeji 执行的行为

**创建包含延迟的序列：**

```xml
<Action Name="Hunt" Type="Sequence">
    <!-- 需要小延迟防止无限循环 -->
    <ActionReference Name="Stand" Duration="${20+Math.random()*20}" />
    <ActionReference Name="RunAffordance" />
</Action>
```

⚠️ **重要：** 在 ScanMove 之前必须有一个 Stand 动作，否则 ScanMove 将不工作！

**添加到 behaviors.xml：**

```xml
<Behavior Name="Hunt" Frequency="1000" />
```

### 🔧 步骤 3：创建交互动作（Interact）

当两个 Shimeji 相遇时，需要执行交互动画。

#### 方法一：一个显示，一个隐藏

**显示交互动画的 Shimeji：**

```xml
<Action Name="HugAction" Type="Embedded" Class="com.group_finity.mascot.action.Interact" BorderType="Floor">
    <Animation>
        <Pose Image="/hug1.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
        <Pose Image="/hug2.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
        <Pose Image="/hug3.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
        <Pose Image="/hug4.png" ImageAnchor="64,128" Velocity="0,0" Duration="50" />
        <Pose Image="/hug3.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
        <Pose Image="/hug2.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
        <Pose Image="/hug1.png" ImageAnchor="64,128" Velocity="0,0" Duration="8" />
    </Animation>
</Action>
```

**对应的序列：**

```xml
<Action Name="IHugYou" Type="Sequence">
    <ActionReference Name="HugAction" />
</Action>
```

**隐藏的 Shimeji（使用强制行为切换）：**

```xml
<Action Name="HuggedAction" Type="Embedded" Class="com.group_finity.mascot.action.Interact" 
        BorderType="Floor" Behavior="SitAndSpinHead">
    <Animation>
        <!-- 无图像，持续时间与另一个动作匹配 -->
        <Pose Velocity="0,0" Duration="98" />
    </Animation>
</Action>
```

**对应的序列：**

```xml
<Action Name="IAmHugged" Type="Sequence">
    <ActionReference Name="HuggedAction" />
</Action>
```

#### 在 behaviors.xml 中设置交互行为

```xml
<!-- 这些行为必须列出，但频率设为 0 且隐藏 -->
<Behavior Name="IHugYou" Frequency="0" Hidden="true" />
<Behavior Name="IAmHugged" Frequency="0" Hidden="true" />
```

⚠️ **重要：** 如果这些行为未在 behaviors.xml 中列出，Shimeji 交互将不起作用！

### 🎨 交互动画设计建议

#### 方法二：分别显示动画

您也可以让两个 Shimeji 都显示各自的动画帧：

```xml
<!-- Shimeji A 的交互动画 -->
<Action Name="HugLeftSide" Type="Embedded" Class="com.group_finity.mascot.action.Interact" BorderType="Floor">
    <Animation>
        <Pose Image="/hug_left1.png" ImageAnchor="32,128" Velocity="0,0" Duration="20" />
        <Pose Image="/hug_left2.png" ImageAnchor="32,128" Velocity="0,0" Duration="20" />
    </Animation>
</Action>

<!-- Shimeji B 的交互动画 -->
<Action Name="HugRightSide" Type="Embedded" Class="com.group_finity.mascot.action.Interact" BorderType="Floor">
    <Animation>
        <Pose Image="/hug_right1.png" ImageAnchor="32,128" Velocity="0,0" Duration="20" />
        <Pose Image="/hug_right2.png" ImageAnchor="32,128" Velocity="0,0" Duration="20" />
    </Animation>
</Action>
```

### 🔄 高级交互功能

#### 强制行为切换

使用 `Behavior` 属性可以在交互成功完成后强制执行特定行为：

```xml
<Action Name="InteractAction" Type="Embedded" Class="com.group_finity.mascot.action.Interact" 
        BorderType="Floor" Behavior="AfterInteractionBehavior">
    <!-- 动画内容 -->
</Action>
```

#### 条件交互

您可以在交互中使用条件来创建更复杂的行为：

```xml
<ActionReference Name="HugAction">
    <Condition>${Math.random() > 0.5}</Condition>
</ActionReference>
```

### 📝 完整示例配置

#### actions.xml 示例

```xml
<!-- 广播动作 -->
<Action Name="SitAffordance" Type="Embedded" Class="com.group_finity.mascot.action.Broadcast" 
        Affordance="Cuddle" BorderType="Floor">
    <Animation>
        <Pose Image="/shime11.png" ImageAnchor="64,128" Velocity="0,0" Duration="250" />
    </Animation>
</Action>

<Action Name="BroadcastAffordance" Type="Sequence">
    <ActionReference Name="SitAffordance" />
</Action>

<!-- 扫描移动动作 -->
<Action Name="RunAffordance" Type="Embedded" Class="com.group_finity.mascot.action.ScanMove" 
        Affordance="Cuddle" Behavior="IHugYou" TargetBehavior="IAmHugged" BorderType="Floor">
    <Animation>
        <Pose Image="/shime1.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
        <Pose Image="/shime2.png" ImageAnchor="64,128" Velocity="-4,0" Duration="2" />
    </Animation>
</Action>

<Action Name="Hunt" Type="Sequence">
    <ActionReference Name="Stand" Duration="${20+Math.random()*20}" />
    <ActionReference Name="RunAffordance" />
</Action>

<!-- 交互动作 -->
<Action Name="HugAction" Type="Embedded" Class="com.group_finity.mascot.action.Interact" BorderType="Floor">
    <Animation>
        <Pose Image="/hug1.png" ImageAnchor="64,128" Velocity="0,0" Duration="50" />
        <Pose Image="/hug2.png" ImageAnchor="64,128" Velocity="0,0" Duration="50" />
    </Animation>
</Action>

<Action Name="IHugYou" Type="Sequence">
    <ActionReference Name="HugAction" />
</Action>

<Action Name="HuggedAction" Type="Embedded" Class="com.group_finity.mascot.action.Interact" 
        BorderType="Floor" Behavior="SitAndSpinHead">
    <Animation>
        <Pose Velocity="0,0" Duration="100" />
    </Animation>
</Action>

<Action Name="IAmHugged" Type="Sequence">
    <ActionReference Name="HuggedAction" />
</Action>
```

#### behaviors.xml 示例

```xml
<!-- 正常行为 -->
<Condition type="mascot.environment.ComplexEnvironment.OnFloor">
    <Behavior Name="BroadcastAffordance" Frequency="1200" />
    <Behavior Name="Hunt" Frequency="1000" />
    <!-- 其他正常行为... -->
</Condition>

<!-- 交互行为（必须包含但隐藏） -->
<Behavior Name="IHugYou" Frequency="0" Hidden="true" />
<Behavior Name="IAmHugged" Frequency="0" Hidden="true" />
```

### 🐛 常见问题排除

#### ScanMove 不工作

- **检查：** ScanMove 前是否有 Stand 动作
- **检查：** Affordance 名称是否完全匹配
- **检查：** 目标行为是否在 behaviors.xml 中定义

#### 交互立即结束

- **检查：** 两个交互动作的持续时间是否匹配
- **检查：** 交互行为是否在 behaviors.xml 中正确设置

#### Shimeji 找不到彼此

- **增加：** 广播和扫描行为的频率
- **检查：** 两个 Shimeji 是否在相同的环境条件下（如都在地板上）

### 💡 创意应用

#### 多种交互类型

您可以创建多种不同的交互：

```xml
<!-- 拥抱交互 -->
<Action Name="HugBroadcast" ... Affordance="Hug" ... />

<!-- 玩耍交互 -->
<Action Name="PlayBroadcast" ... Affordance="Play" ... />

<!-- 睡觉交互 -->
<Action Name="SleepBroadcast" ... Affordance="Sleep" ... />
```

#### 连锁交互

创建交互后继续其他行为：

```xml
<Action Name="AfterHug" Type="Sequence">
    <ActionReference Name="Stand" Duration="50" />
    <ActionReference Name="Walk" />
</Action>
```

通过这个详细的教程，您现在可以创建复杂的 Shimeji 交互系统，让您的桌面宠物变得更加生动有趣！

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

### 版本 1.0.21.3

- 添加了 ComplexJump 和 ComplexMove，支持 `Characteristics` 属性（Breed, Scan）
- 为扫描动作添加了 TargetX 和 TargetY 变量
- 由于技术问题移除了扫描动作的 target 变量
- 被取消的 shimeji 不再广播 affordances
- 信息屏幕改进：主题颜色、正确居中、图像居中
- 修复了信息屏幕 X 按钮行为
- 修复了 Move ActionReferences 中条件被忽略的问题
- 对 Breed 和 Move 动作进行了一般性代码清理
- 更新了中文翻译

### 版本 1.0.21.2

- 添加了 BornCount 参数，允许一次生成多个 shimeji
- 修复了 0.5 缩放时低速度缩放问题

### 版本 1.0.21.1（热修复）

- 主题编辑器现在以适当的 DPI 缩放显示
- 字体大小编辑器和重置按钮可使用缩放后的有效字体大小
- 修复了 Toggleable 标志功能

### 版本 1.0.21

- 在设置中添加了不透明度滑块，支持半透明 shimeji
- 在设置中添加了主题选项卡，用于视觉自定义
- 为窗口模式添加了背景图像支持
- 通过右键菜单添加了行为切换功能
- 添加了新的配置文件：`info.xml`
- 在 shimeji 选择器中添加了自定义名称和图标支持
- 添加了带有制作人员名单和链接的启动画面功能
- 为条件暴露了额外变量（Jump、Fall、ScanMove、ScanJump）
- 添加了 ScanInteract 动作类型，用于远程交互
- 为 ScanMove 和 ScanJump 添加了 TargetLook 属性
- 为行为添加了 Toggleable 标志
- 为大多数动作添加了 affordance 广播功能
- 废弃了 Broadcast 动作（仍然可用）
- 添加了自动转身动画支持
- 废弃了 MoveWithTurn 动作

### 版本 1.0.20

- 窗口模式：在窗口中运行 Shimeji（适合直播）
- 添加了 ScanJump 和 BroadcastJump 动作
- Boss 模式：双击中键托盘图标快速隐藏 shimeji
- XML 常量支持，用于高级配置
- 支持无行为设置的热点，用于点击保持式动画
- 新的设置屏幕，包含过滤和缩放选项
- 重新设计的托盘图标菜单，包含 Shimeji 选择器和设置按钮
- 改进的交互窗口检测，支持 Windows 11
- 为没有图像锚点的姿势提供更好的错误消息
- 修复了同时播放声音时的罕见冻结问题
- 修复了暂停 shimeji 的延迟抓取/热点行为
- 更新翻译：阿拉伯语、加泰罗尼亚语、法语、西班牙语、波兰语

### 版本 1.0.19

- 热点：XML 定义的交互区域
- Shimeji 动画的暂停和恢复功能
- 修复了与日语区域设置计算机的兼容性
- 改进了拖拽行为和着陆动画
- 新翻译：阿拉伯语、繁体中文
- 更新了多种语言翻译
- 一般性代码清理

### 版本 1.0.18

- BreedJump 和 BreedMove 动作，用于在移动时克隆
- 在 Fall 期间暴露速度，用于动态动画
- 繁殖动作的 Transient 标志（临时与永久克隆）
- 在允许行为菜单中添加了 Transients
- 修复了阻止摆动动画播放的 Dragged OffsetX/Y 问题
- 修复了 Windows 7 上交互窗口的问题
- 恢复了缺少 icon.png 的非致命错误消息
- 更新了法语翻译

### 版本 1.0.17

- Shimeji 选择器可以在每次启动时出现
- 改进了所选 shimeji 的选择器行为
- 不区分大小写的未使用文件夹检测
- 修复了 Windows 10 上的隐形窗口故障
- 禁用"在屏幕间移动"时更好的多屏幕行为
- 修复了"恢复窗口"功能
- 在右键菜单中添加了"取消所有其他"
- 为 Dragged 动作添加了 OffsetX/OffsetY 属性
- 关键安全修复

### 版本 1.0.16

- 多显示器设置的"在屏幕间移动"切换
- "变换"行为切换
- HQX 缩放滤镜，用于高质量放大
- 通用声音文件夹，用于共享音频文件
- Count 变量暴露给 XML 条件
- 用于声音控制的静音动作
- 修复了 Windows 锁屏和显示器插入时的冻结问题

### 版本 1.0.15

- 日语 Shimeji-e 向后兼容性

### 版本 1.0.14

- Affordances 系统实现

### 版本 1.0.13

- Draggable 属性
- SelfDestruct 动作
- 缩放错误修复
- 澄清了缺少 imageRight 的错误消息

### 版本 1.0.12

- Transform 动作
- Shimeji 缩放支持
- 菜单 DPI 感知
- 改进的内存使用
- 添加了 3 种语言

### 版本 1.0.11

- 新菜单系统
- Nimrod 主题集成
- 添加了 5 种语言

### 版本 1.0.10

- 国际化支持
- 更新了构建配置
- 改进了失败 shimeji 加载的错误处理

### 版本 1.0.9

- 姿势标签的声音和音量属性
- 修复了 Turn 动作
- 改进了错误消息对话框

### 版本 1.0.8

- 使用 ImageRight 属性的不对称支持
- 新的 MoveWithTurn 和 Turn 动作
- 修复了添加交互窗口对话框中的重复选项

### 版本 1.0.7

- 64 位支持
- Breed 动作的 BornMascot 属性
- 允许行为菜单

### 版本 1.0.6

- 添加了图像预乘，支持半透明图像
- 改进了行为列表格式

### 版本 1.0.5

- 用 settings.properties 替换了 windows.txt
- 新的表单设计和菜单系统
- 启动时隐藏表单

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
