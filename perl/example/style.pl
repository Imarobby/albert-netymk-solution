#!/usr/bin/perl
use warnings;
use strict;

# set the input record separate to empty lines
$/ = "";

while(<>) {
	# the whole block is like a very long string, including several logical new lines
	s/\n\s+(?={)/ /mg;
	print;
}

