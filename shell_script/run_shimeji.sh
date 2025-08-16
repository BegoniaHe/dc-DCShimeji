#!/bin/bash

# Shimeji-ee JRE 21 便携启动脚本 (Unix/Linux/macOS)

cd "$(dirname "$0")"

echo "========================================"
echo "  Shimeji-ee - JRE 21 便携版本"
echo "========================================"
echo

# 检查是否存在绑定的 JRE 21
if [ -f "jre/bin/java" ]; then
    echo "检测到绑定的 JRE 21，使用绑定版本..."
    JAVA_CMD="./jre/bin/java"
else
    echo "错误: 未找到绑定的 JRE 21"
    echo "此便携版本仅支持内置的 JRE 21，请确保 jre 文件夹存在"
    read -p "按 Enter 键退出..."
    exit 1
fi

if [ ! -f "target/Shimeji-ee.jar" ]; then
    echo "错误: 未找到 Shimeji-ee.jar 文件！"
    echo "请确保 target 文件夹存在"
    read -p "按 Enter 键退出..."
    exit 1
fi

echo "使用内置 JRE 21 启动 Shimeji-ee..."
echo
echo "启动 Shimeji-ee..."

$JAVA_CMD -Dfile.encoding=UTF-8 \
     -Xms64m \
     -Xmx512m \
     -cp "target/Shimeji-ee.jar:lib/*" \
     com.group_finity.mascot.Main &

JAVA_PID=$!
echo
echo "Shimeji-ee 已成功启动！(使用内置 JRE 21, PID: $JAVA_PID)"
echo "现在您可以安全地关闭此终端。"
echo "要停止 Shimeji-ee，请右键点击桌面精灵或使用以下命令："
echo "kill $JAVA_PID"
echo
echo "提示: 此便携版本使用内置的 JRE 21，无需额外安装 Java"
echo
