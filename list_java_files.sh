#!/bin/bash

# Script to create a txt file with all .java files and their contents

OUTPUT_FILE="java_files_list.txt"

echo "Finding all .java files in FleetManagementSystem..."

# Clear the output file
> "$OUTPUT_FILE"

# Find all .java files, sort them, and process each one
find . -name "*.java" -type f | sort | while read -r file; do
    echo "$file" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo "******************************************************" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
done

echo "Done! List of .java files with contents saved to $OUTPUT_FILE"
echo "Total files found: $(find . -name "*.java" -type f | wc -l)"
