#!/usr/bin/perl
# to construct the header part for one c file
# this only contains the function declaration
use warnings;
use strict;

my $flag = 0;
LINE: while(<>) {
	s///;
	# it is possible that the function declaration extends two lines
	if($flag != 1) {
		if(/^\w[^;=]*\)?$/) {
			if(/^(static)/) {
				# omit the private function declaration
				next LINE;
			}
			if(/^\w[^;]*\)$/) {
				s/$/;/;
				print;
			} elsif (! /.*main.*/){
				print;
				# the following line is useful
				$flag = 1;
			}
		}
	} else {
		# we are in the second line of the declaration
		$flag = 0;
		s/$/;/;
		print;
	}
}

