#!/usr/bin/perl
# number the content of one file
use strict;
use warnings;

my $lineNumber = 1;
my $fileName;
while(<>) {
	# get rid of the prefix; the absolute path name
	$ARGV =~ s!(.*)/!!;
	print $ARGV, " ", $lineNumber;
	print ": $_";
	$lineNumber++;
}
