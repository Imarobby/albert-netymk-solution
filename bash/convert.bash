#!/bin/bash
# convert from .avi to .mp3
# $1 is the target path
dir=$1
for file in "$dir"/*.avi
do 
	#newfile=`echo "$file" | sed 's/F.R.I.E.N.D.S./friends/'`
	newfile=$file
	#mv "$file" "$newfile" 2>/dev/null
	if [ -e "`echo "$file" | sed 's/avi/mp3/'`" ];then
		echo
	else
		ffmpeg -vn -i "$newfile" "`echo "$newfile" | sed 's/avi/mp3/'`" 2>/dev/null
		echo "$newfile" | sed 's/avi/mp3/'
	fi
done 

