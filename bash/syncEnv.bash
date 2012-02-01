#!/bin/bash -e
# Synchronize the ~/workspace/Env/ directory with ~
#
SOURCE="$HOME/workspace/env"
DESTINATION="$HOME"

# only the hidden files
target=`find "$SOURCE" -name '.*' -type f | sed 's!.*/\([^/]*\)!\1!'`

for item in $target
do
	if [ ! -e "$DESTINATION/$item" ]
	then
		ln "$SOURCE/$item" "$DESTINATION/$item"
	fi
done
