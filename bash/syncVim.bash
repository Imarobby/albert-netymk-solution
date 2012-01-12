#!/bin/bash -e
# Synchronize the ~/.vim directory with ~/workspace/.vim
#
DESTINATION="$HOME/workspace"

# All the directory we might need to create
target=`find ~/.vim -type d | sed 's!.*\(.vim.*\)!\1!'`

for item in $target
do
	if [ ! -e "$DESTINATION/$item" ]
	then
		mkdir $target 2>/dev/null
	fi
done

# All the files we might need to create
target=`find ~/.vim -type f | sed 's!.*\/\(.vim.*\)!\1!'`

for item in $target
do
	if [ ! -e "$DESTINATION/$item" ]
	then
		ln "$HOME/$item" "$DESTINATION/$item"
	fi
done
