#!/bin/bash -e

page=300;
if [ $# -ge 1 ]; then
	fullname=$(echo "$1" | sed 's/.pdf//');
	filename=$(echo "$fullname" | sed 's!.*/!!');
	pages=$(pdfinfo "$1" | grep 'Pages' | sed 's/[^0-9]//g');
	if [ $# -ge 2 ]; then
		page="$2";
	fi
	echo "$pages";
	if [ $pages -gt 0 ]; then
		i=1;
		while [ $pages -gt $page ]; do
			pdftk "$1" cat $((1+$i*$page-$page))-$(($i*$page)) output "$filename-part$i.pdf";
			pages=$(($pages-$page));
			i=$(($i+1));
		done
		pdftk "$1" cat $((1+$i*$page-$page))-end output "$filename-part$i.pdf";
	fi
fi
