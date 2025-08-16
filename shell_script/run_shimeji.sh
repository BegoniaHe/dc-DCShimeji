#!/bin/bash

# Shimeji-ee JRE 21 Portable Startup Script (Unix/Linux/macOS)

cd "$(dirname "$0")"

echo "========================================"
echo "  Shimeji-ee - JRE 21 Portable Version"
echo "========================================"
echo

# Check for bundled JRE 21
if [ -f "jre/bin/java" ]; then
    echo "Found bundled JRE 21, using bundled version..."
    JAVA_CMD="./jre/bin/java"
else
    echo "Error: Bundled JRE 21 not found"
    echo "This portable version only supports bundled JRE 21, please ensure jre folder exists"
    read -p "Press Enter to exit..."
    exit 1
fi

if [ ! -f "target/Shimeji-ee.jar" ]; then
    echo "Error: Shimeji-ee.jar file not found!"
    echo "Please ensure target folder exists"
    read -p "Press Enter to exit..."
    exit 1
fi

echo "Starting Shimeji-ee with bundled JRE 21..."
echo
echo "Launching Shimeji-ee..."

$JAVA_CMD -Dfile.encoding=UTF-8 \
     -Xms64m \
     -Xmx512m \
     -cp "target/Shimeji-ee.jar:lib/*" \
     com.group_finity.mascot.Main &

JAVA_PID=$!
echo
echo "Shimeji-ee has been successfully started! (using bundled JRE 21, PID: $JAVA_PID)"
echo "You can now safely close this terminal."
echo "To stop Shimeji-ee, right-click on the desktop mascot or use the following command:"
echo "kill $JAVA_PID"
echo
echo "Note: This portable version uses bundled JRE 21, no additional Java installation required"
echo
