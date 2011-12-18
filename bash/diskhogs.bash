#!/bin/bash
TEMP=`mktemp -t tmp.XXXXXX`
du -s /home/* 2>/dev/null | grep -v lost | sed 's/\/home\///' | sort -g -r > $TEMP  
TOTAL=`du -s /home 2>/dev/null | gawk '{print $1}'` 
cat $TEMP | gawk -v n="$TOTAL" '
BEGIN {
	print "Total Disk Usage by Users";
	print "User\tSpace\tPercent"
}
{
	printf "%s\t%d\t%6.2f%\n", $2, $1, ($1/n)*100
}
END {
	print "----------------------"
	printf "Total\t%d\n", n
}'
rm -f $TEMP 
df -h | sed -n '/\home/p;/^Filesystem/p' | gawk '
{
	print $3, $4, $5
}'
