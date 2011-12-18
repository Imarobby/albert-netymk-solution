#!/bin/bash -e
if [ $# -eq 1 ]; then
	ifconfig wlan0 down;
	macchanger wlan0 -m $1;
	ifconfig wlan0 up;
else
	echo "At least one argument"
fi
