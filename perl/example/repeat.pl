#!/usr/bin/perl
use strict;
use warnings;

# set the input record separator
$/ = ".\n";
# the default argument for <> is @ARGV, which is the arguments for this script
while(<>) {
	next unless s{
		\b				# start of word
		([a-z]+)		# the first word
		(\s+)			# at least one whitespace, for it is one obvious spell mistake
		(\1\b)
	}
	{\e[7m$1\e[m$2\e[7m$3\e[m}igx;

	s/^([^\e]+\n)+//mg;
	# $ARGV constains the name of the current file
	s/^/$ARGV: /m;
	
	print;
}
