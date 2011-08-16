#!/usr/bin/perl
use strict;
use warnings;

my $n = 0;
while(<>) {
	if(/.*sourcing.*/) {
		if (/^finished sourcing.*/) {
			$n -= 1;
			print "\t" x $n, $_;
		} else {
			print "\t" x $n, $_;
			$n += 1;
		}
	}
}
