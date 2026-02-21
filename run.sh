#!/bin/bash

# Run script with Lombok support

BIN_DIR="bin"
LIB_DIR="lib"
MAIN_CLASS="com.huerta.fleetmanagementsystem.app.FleetManagementApplication"

echo "Running FleetManagementSystem..."

java -cp "$BIN_DIR:$LIB_DIR/lombok.jar" "$MAIN_CLASS"
