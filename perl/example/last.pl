#!/usr/bin/perl
use strict;
use warnings;

my @last5;
while(<>) {
	# add this line to the end
	push @last5, $_;
	shift @last5 if @last5 > 5;
}
print "last five lines:\n", @last5;
