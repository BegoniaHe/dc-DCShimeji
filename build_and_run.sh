#!/bin/bash

echo "Building Shimeji-ee..."
echo

# Clean and build the project
ant clean jar

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo
echo "Build successful! Starting Shimeji-ee..."
echo

# Run the application (Unix-style classpath with colon separator)
java -cp "target/Shimeji-ee.jar:lib/*" com.group_finity.mascot.Main

if [ $? -ne 0 ]; then
    echo "Application failed to start properly."
    exit 1
fi
