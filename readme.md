# Shimeji-ee: Shimeji English Enhanced

[![License](https://img.shields.io/badge/License-BSD%202--Clause-orange.svg)](https://opensource.org/licenses/BSD-2-Clause)
[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Platform](https://img.shields.io/badge/Platform-Windows-lightgrey.svg)](https://www.microsoft.com/windows/)

**🌐 Language / 语言选择:** [English](README.md) | [中文](README_CN.md)

Shimeji-ee is a Windows desktop mascot that freely wanders and plays around the screen. The mascot is very configurable; its actions are defined through XML and its animations/images can be (painstakingly) customized.

Shimeji was originally created by Yuki Yamada of Group Finity ([http://www.group-finity.com/Shimeji/](http://www.group-finity.com/Shimeji/)). This branch of the original Shimeji project not only translates the program/source to English, but adds additional enhancements to Shimeji by Kilkakon and other members of the community.

![Shimeji Banner](img/banner.bmp)

## 📋 Table of Contents

- [🌐 Homepage](#-homepage)
- [📋 Requirements](#-requirements)
- [🚀 Quick Start](#-quick-start)
- [⚙️ Basic Configuration](#️-basic-configuration)
- [🔧 Advanced Configuration](#-advanced-configuration)
- [📁 Project Structure](#-project-structure)
- [🎨 Creating Custom Image Sets](#-creating-custom-image-sets)
- [🔄 Configuration Files](#-configuration-files)
- [🛠️ Build Instructions](#️-build-instructions)
- [❌ How to Quit](#-how-to-quit)
- [🗑️ How to Uninstall](#️-how-to-uninstall)
- [📚 Source Code](#-source-code)
- [📦 Libraries](#-libraries)
- [🐛 Troubleshooting](#-troubleshooting)
- [📝 Changelog](#-changelog)
- [📄 License](#-license)
- [🤝 Contributing](#-contributing)

## 🌐 Homepage

**Official Homepage:** [http://kilkakon.com/shimeji](http://kilkakon.com/shimeji)

## 📋 Requirements

- **Operating System:** Windows
- **Java Runtime:** Java 8 or later
- **Memory:** At least 512MB RAM (more recommended for multiple image sets)

## 🚀 Quick Start

### Running Shimeji-ee

1. **Double-click** the `Shimeji-ee.jar` file
2. **Right-click** the tray icon for general options
3. **Right-click** a Shimeji for character-specific options

### Quick Tutorial

📺 **Video Tutorial:** [How to get Shimeji running](https://www.youtube.com/watch?v=S7fPCGh5xxo)

📺 **FAQ Video:** [Common problems and solutions](https://www.youtube.com/watch?v=A1y9C1Vbn6Q)

💬 **Discord Community:** [https://discord.gg/dcJGAn3](https://discord.gg/dcJGAn3)

## ⚙️ Basic Configuration

### Adding Multiple Shimeji Types

To have multiple Shimeji types, you need multiple image sets. Each image set should be placed in a separate folder under the `img/` directory.

#### Example: Adding a Batman Shimeji

1. **Create folder:** `img/Batman/`
2. **Add images:** Create Batman versions of `shime1.png` through `shime46.png`
   - Filenames must match exactly with the original `img/Shimeji/` files
   - Refer to `img/Shimeji/` for proper character positions
3. **Start Shimeji-ee:** Both Shimeji and Batman will appear
4. **Right-click Batman** for Batman-specific options

### Memory Management

⚠️ **Important:** Shimeji-ee can use up to 60% of your system's free memory when multiple image sets are loaded.

- **Hide unused sets:** Move image sets to `img/unused/` folder
- **Use Image Set Chooser:** Select specific image sets at runtime
- **Monitor memory usage:** Especially with many image sets

### Image Set Chooser

The Image Set Chooser tool allows you to:

- Select which image sets to load at startup
- Preview available image sets (requires `shime1.png` in each set)
- Save preferences in `conf/settings.properties`

## 🔧 Advanced Configuration

### Configuration File Locations

All configuration files are located in the `conf/` folder:

| File | Purpose |
|------|---------|
| `settings.properties` | Active Shimeji and window interaction settings |
| `actions.xml` | Defines available Shimeji actions |
| `behaviors.xml` | Defines when actions are performed |
| `logging.properties` | Logging configuration |
| `language.properties` | Localization settings |

### Per-Character Configuration

Each Shimeji type can have custom configuration through:

1. **Image set:** Located in `img/[NAME]/`
2. **Actions file:**
   - `img/[NAME]/conf/actions.xml` (highest priority)
   - `conf/[NAME]/actions.xml` (medium priority)
   - `conf/actions.xml` (default fallback)
3. **Behaviors file:** Similar priority system as actions

### Required Actions and Behaviors

#### Required Actions (actions.xml)

- `ChaseMouse`
- `Fall`
- `Dragged`
- `Thrown`

#### Required Behaviors (behaviors.xml)

- `ChaseMouse`
- `Fall`
- `Dragged`
- `Thrown`

### Customization Tips

- **Disable behaviors:** Set frequency to `0` in behaviors.xml
- **Limit Shimeji count:** Edit settings in configuration files
- **Custom tray icon:** Replace `img/icon.png`

## 📁 Project Structure

```text
dc-DCShimeji/
├── 📄 Shimeji-ee.jar          # Main executable
├── 📄 Shimeji-ee.exe          # Windows executable
├── 📄 build.xml               # Ant build file
├── 📄 MANIFEST.MF             # JAR manifest
├── 📁 conf/                   # Configuration files
│   ├── 📄 actions.xml         # Action definitions
│   ├── 📄 behaviors.xml       # Behavior definitions
│   ├── 📄 settings.properties # Application settings
│   └── 📄 language_*.properties # Localization files
├── 📁 img/                    # Image assets
│   ├── 📁 Shimeji/           # Default character images
│   ├── 📁 KuroShimeji/       # Alternative character
│   └── 📁 unused/            # Disabled image sets
├── 📁 lib/                    # External libraries
├── 📁 src/                    # Source code
│   ├── 📁 com/               # Main source packages
│   ├── 📁 hqx/               # Image scaling algorithms
│   └── ...
├── 📁 src_generic/           # Platform-independent code
├── 📁 src_win/               # Windows-specific code
├── 📁 src_mac/               # macOS-specific code
├── 📁 src_virtual/           # Virtual machine specific code
└── 📁 target/                # Build output
    └── 📄 Shimeji-ee.jar     # Compiled JAR
```

## 🎨 Creating Custom Image Sets

### Image Requirements

- **Format:** PNG with transparency
- **Count:** 46 images (shime1.png - shime46.png)
- **Naming:** Must match exactly with default set
- **Size:** Recommended consistent dimensions across all images

### Image Set Structure

```text
img/YourCharacter/
├── 📄 shime1.png    # Standing/idle pose (required for preview)
├── 📄 shime2.png    # Walking frame 1
├── 📄 shime3.png    # Walking frame 2
├── ...
└── 📄 shime46.png   # Final animation frame
```

### Animation Guidelines

Refer to the default `img/Shimeji/` folder for:

- Proper character positioning
- Animation sequences
- Sprite dimensions
- Transparency handling

## 🔄 Configuration Files

### settings.properties

```properties
# Active image sets (comma-separated)
ActiveShimeji=Shimeji

# Windows that Shimeji can interact with
InteractiveWindows=Chat/Notepad/Friends/Windows Live Messenger

# UI scaling
MenuDPI=96
```

### actions.xml Structure

```xml
<ActionList>
    <Action name="ActionName">
        <ImageList>image1.png,image2.png</ImageList>
        <Duration>1000</Duration>
    </Action>
</ActionList>
```

### behaviors.xml Structure

```xml
<BehaviorList>
    <Behavior name="BehaviorName" frequency="100">
        <Condition>condition</Condition>
        <NextBehavior>NextBehaviorName</NextBehavior>
    </Behavior>
</BehaviorList>
```

## 🛠️ Build Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Ant (for building)

### Building from Source

1. **Clone the repository**
2. **Navigate to project directory**
3. **Run build script:**

   ```batch
   # Windows
   build_and_run.bat
   
   # Unix/Linux/macOS
   ./build_and_run.sh
   ```

4. **Or use Ant directly:**

   ```bash
   ant
   ```

### Build Output

- Compiled JAR: `target/Shimeji-ee.jar`
- All dependencies included in the JAR

## ❌ How to Quit

**Right-click** the tray icon and select **"Dismiss All"**

## 🗑️ How to Uninstall

Simply **delete the unzipped folder** - no registry entries or system files are modified.

## 📚 Source Code

### License

The Shimeji-ee source code is available under the **New BSD License**.

### Usage Rights

- ✅ Free to use in personal and commercial projects
- ✅ Modification and redistribution allowed
- ✅ Source code study and learning encouraged
- ⚠️ Must retain copyright notices
- ⚠️ Must follow zlib/libpng licenses for included libraries

## 📦 Libraries

| Library | Purpose | License |
|---------|---------|---------|
| `jna.jar` | Native system integration | LGPL |
| `examples.jar` | JNA examples | LGPL |
| `AbsoluteLayout.jar` | NetBeans layout manager | Various |
| `nimrodlf.jar` | Look and Feel | Various |

## 🐛 Troubleshooting

### Common Issues

#### Shimeji icon appears but no characters show

1. **Check Java version:** Ensure Java 8+ is installed
2. **Verify image sets:** Only have valid image set folders in `img/`
3. **Check logs:** Review `ShimejiLogX.log` for errors
4. **Memory issues:** Move extra image sets to `img/unused/`
5. **Command line test:**

   ```batch
   "C:\Program Files (x86)\Java\jre8\bin\java" -jar Shimeji-ee.jar
   ```

#### Slow startup

- **Cause:** Too many image sets loading
- **Solution:** Use Image Set Chooser or move unused sets to `img/unused/`

#### Out of memory errors

- **Edit memory allocation:** Modify `Shimeji-ee.bat`
- **Change:** `-Xmx1000m` to a higher value (e.g., `-Xmx2000m`)

#### Performance issues

- **Reduce active Shimeji count** in settings
- **Disable resource-intensive behaviors**
- **Close other memory-intensive applications**

### Getting Help

- 📺 [Tutorial Video](https://www.youtube.com/watch?v=S7fPCGh5xxo)
- 📺 [FAQ Video](https://www.youtube.com/watch?v=A1y9C1Vbn6Q)
- 💬 [Discord Community](https://discord.gg/dcJGAn3)
- 🐛 [Report Issues](http://kilkakon.com/shimeji) on the homepage

## 📝 Changelog

### Version 1.0.21.3

- Added ComplexJump and ComplexMove with `Characteristics` attribute support (Breed, Scan)
- Added TargetX and TargetY variables for Scanning actions
- Removed target variable from Scanning actions due to technical issues
- Dismissed shimeji no longer broadcast affordances
- Information screen improvements: theme colors, proper centering, image centering
- Fixed information screen X button behavior
- Fixed conditions being ignored on Move ActionReferences
- General code cleanup for Breed and Move actions
- Updated Chinese translation

### Version 1.0.21.2

- Added BornCount parameter for spawning multiple shimeji at once
- Fixed low velocity scaling issues at 0.5 scale

### Version 1.0.21.1 (Hotfix)

- Theme Editor now displays with proper DPI scaling
- Font size editor and reset button work with effective scaled font size
- Fixed Toggleable flag functionality

### Version 1.0.21

- Added opacity slider in settings for semi-transparent shimeji
- Added Theme tab in settings for visual customization
- Added background image support for Window Mode
- Added behavior toggle functionality via right-click menu
- Added new configuration file: `info.xml`
- Added custom name and icon support in shimeji chooser
- Added splash screen functionality with credits and links
- Exposed additional variables for conditions (Jump, Fall, ScanMove, ScanJump)
- Added ScanInteract action type for long-range interactions
- Added TargetLook attribute for ScanMove and ScanJump
- Added Toggleable flag for behaviors
- Added affordance broadcasting capability for most actions
- Deprecated Broadcast actions (still functional)
- Added automatic turning animation support
- Deprecated MoveWithTurn action

### Version 1.0.20

- Window Mode for running Shimeji in a window (ideal for streaming)
- Added ScanJump and BroadcastJump actions
- Boss mode: double middle-click tray icon to quickly hide shimeji
- XML constant support for advanced configurations
- Hotspot support without behavior set for click-hold animations
- New Settings screen with filtering and scaling options
- Reworked tray icon menu with Shimeji Chooser and Settings buttons
- Improved interactive window detection with Windows 11 support
- Better error messages for poses without image anchors
- Fixed rare freezing issue with simultaneous sound playback
- Fixed delayed grab/hotspot behavior for paused shimeji
- Updated translations: Arabic, Catalan, French, Spanish, Polish

### Version 1.0.19

- Hotspots: XML-defined interactive areas
- Pause and resume functionality for shimeji animations
- Fixed compatibility with Japanese locale computers
- Improved drag behavior and landing animations
- New translations: Arabic, Traditional Chinese
- Updated multiple language translations
- General code cleanup

### Version 1.0.18

- BreedJump and BreedMove actions for cloning while moving
- Exposed velocity during Fall for dynamic animations
- Transient flag for breeding actions (temporary vs permanent clones)
- Added Transients to Allowed Behaviours menu
- Fixed Dragged OffsetX/Y swing animation issues
- Fixed interactive windows on Windows 7
- Restored non-fatal error message for missing icon.png
- Updated French translation

### Version 1.0.17

- Shimeji chooser can appear on each startup
- Improved chooser behavior for selected shimeji
- Case-insensitive unused folder detection
- Fixed invisible window glitch on Windows 10
- Better multi-screen behavior when "Move Between Screens" is disabled
- Fixed "Restore Windows" functionality
- Added "Dismiss All Others" to right-click menu
- Added OffsetX/OffsetY properties to Dragged action
- Critical security fixes

### Version 1.0.16

- "Move Between Screens" toggle for multi-monitor setups
- "Transformation" behavior toggle
- HQX scaling filter for high-quality upscaling
- Generic sound folder for shared audio files
- Count variable exposed to XML conditions
- Muting action for sound control
- Fixed Windows lockscreen and monitor plug-in freezing issues

### Version 1.0.15

- Japanese Shimeji-e backwards compatibility

### Version 1.0.14

- Affordances system implementation

### Version 1.0.13

- Draggable property
- SelfDestruct action
- Scaling bug fixes
- Clarified missing imageRight error messages

### Version 1.0.12

- Transform action
- Shimeji scaling support
- Menu DPI awareness
- Improved memory usage
- Added 3 more languages

### Version 1.0.11

- New menu system
- Nimrod theming integration
- Added 5 more languages

### Version 1.0.10

- Internationalization support
- Updated build configuration
- Improved error handling for failed shimeji loads

### Version 1.0.9

- Sound and Volume attributes for pose tags
- Fixed Turn action
- Improved error message dialogs

### Version 1.0.8

- Asymmetry support with ImageRight attribute
- New MoveWithTurn and Turn actions
- Fixed duplicate options in Add Interactive Windows dialog

### Version 1.0.7

- 64-bit support
- BornMascot attribute for Breed action
- Allowed Behaviour menu

### Version 1.0.6

- Added image premultiplying for translucent image support
- Improved behavior list formatting

### Version 1.0.5

- Replaced windows.txt with settings.properties
- New form design and menu system
- Hidden form on startup

### Version 1.0.3

- Decreased memory usage significantly

### Version 1.0.2

- Added Image Set Chooser for runtime selection
- Added pop-up error messages for better user feedback
- Updated default logging options

### Version 1.0.1

- Configuration files can now be under `img/[NAME]/conf/`
- Program no longer crashes with invalid image sets

### Version 1.0.0

- Initial release of Shimeji-ee

## 📄 License

```text
Copyright (c) Shimeji-ee Group
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, 
   this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES ARE DISCLAIMED.
```

**By Kilkakon** - [kilkakon.com](http://kilkakon.com)

You are welcome to use this work in your own projects if you credit Kilkakon and the original people who worked on this. A link to kilkakon.com would also be nice.

## 🤝 Contributing

### How to Contribute

1. **Fork** the repository
2. **Create** a feature branch
3. **Make** your changes
4. **Test** thoroughly
5. **Submit** a pull request

### Areas for Contribution

- 🎨 New character image sets
- 🌍 Translations and localization
- 🐛 Bug fixes and improvements
- 📚 Documentation enhancements
- ✨ New features and behaviors

### Community

- **Discord:** [https://discord.gg/dcJGAn3](https://discord.gg/dcJGAn3)
- **Homepage:** [http://kilkakon.com/shimeji](http://kilkakon.com/shimeji)

---

## 🎉 Enjoy your desktop companions
