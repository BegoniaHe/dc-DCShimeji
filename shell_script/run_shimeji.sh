#!/bin/bash
cd "$(dirname "$0")"
echo "Starting Shimeji-ee..."

if [ ! -f "jre/bin/java" ]; then
    echo "Error: JRE not found! Please make sure the jre folder exists."
    read -p "Press Enter to exit..."
    exit 1
fi

if [ ! -f "target/Shimeji-ee.jar" ]; then
    echo "Error: Shimeji-ee.jar not found! Please make sure the target folder exists."
    read -p "Press Enter to exit..."
    exit 1
fi

"./jre/bin/java" -cp "target/Shimeji-ee.jar:lib/*" com.group_finity.mascot.Main

if [ $? -ne 0 ]; then
    echo ""
    echo "Shimeji-ee exited with an error."
    read -p "Press Enter to exit..."
fi
