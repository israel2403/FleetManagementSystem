#!/bin/bash

# Compilation script with Lombok support

# Directories
SRC_DIR="src"
BIN_DIR="bin"
LIB_DIR="lib"

# Create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

echo "Compiling Java project with Lombok..."

# Find all .java files and compile them with Lombok
find "$SRC_DIR" -name "*.java" > sources.txt

javac -cp "$LIB_DIR/lombok.jar" \
      -d "$BIN_DIR" \
      @sources.txt

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    rm sources.txt
else
    echo "✗ Compilation failed!"
    rm sources.txt
    exit 1
fi
