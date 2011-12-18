#!/bin/bash
#
# Purpose: Print the frequency of each word in $1

# The first tr translates all the upper case character in $1 to lower case.
# The second tr delete all the characters in $1 except 'a-z\012\040'.
# 012 is line feed, and 040 is space
# Sort the previous result by its second field.
tr A-Z a-z < $1 | tr -cd 'a-z\012\040' | awk -f frequency.awk | sort -k 2 -nr
