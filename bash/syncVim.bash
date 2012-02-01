#!/bin/bash -e
# Synchronize the ~/.vim directory with ~/workspace/.vim
#
SOURCE="$HOME/.vim"
DESTINATION="$HOME/workspace/.vim"

if [ ! -e "$SOURCE" ]; then
	mkdir "$SOURCE"
fi
if [ ! -e "$DESTINATION" ]; then
	mkdir "$DESTINATION"
fi

function synchronizer {
		local SOURCE="$1"
		local DESTINATION="$2"
		echo "source is $SOURCE"
		echo "destination is $DESTINATION"
		# All the directory we might need to create
		target=`find "$SOURCE" -type d | sed 's!.*\.vim/\?\(.*\)!\1!'`
		
		for item in $target
		do
			if [ ! -e "$DESTINATION/$item" ]
			then
				mkdir "$DESTINATION/$item"
			fi
		done
		
		# All the files we might need to create
		target=`find "$SOURCE" -type f | sed 's!.*\.vim/\(.*\)!\1!'`
		
		for item in $target
		do
			if [ ! -e "$DESTINATION/$item" ]
			then
				ln "$SOURCE/$item" "$DESTINATION/$item"
			fi
		done
}
synchronizer "$SOURCE" "$DESTINATION"
synchronizer "$DESTINATION" "$SOURCE"
