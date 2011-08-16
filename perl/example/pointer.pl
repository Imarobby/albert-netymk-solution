#!/usr/bin/perl
# the pointer type should be "type *varible/function"
# bugs: can not distinguish it from multiplication
use strict;
use warnings;

# set the record separate
$/ = "\n";

while(<>) {
	# pointer type
	# type *variable OR type *function
	s/(?<=[a-zA-Z])\s*\*\s*(?=\w)/ \*/g;
	# (type *)variable
	s/(?<=[a-zA-Z])\s*\*\s*(?=\))/ \*/g;
	print;
}
