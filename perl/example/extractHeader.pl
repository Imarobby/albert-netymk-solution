#!/usr/bin/perl
# to extract the public method declaration from .c file
# to exclude method declaration from .h file in order to rebuilt it
use warnings;
use strict;
use Switch;

my $flag = 0;
my $extract = 1;
switch( $ARGV ) {
	case 1 { 
		switch( $ARGV[0] ) {
			case /.*\.c/ { $extract = 1; }
			case /.*\.h/ { $extract = 0; }
			print "I can only handle '.c' or '.h' files\n";
			exit;
		} 
	}
	case 2 { $extract = 1; }
	print "I need one or two argument.\n";
	exit;
}

if($extract) {
	LINE: while(<>) {
		s///;
		# it is possible that the function declaration extends 
		# two lines
		if($flag != 1) {
			if(/^\w[^=]*\([^=]*\)?$/) {
				if(/^(static)/) {
					# omit the private function declaration
					next LINE;
				}
				if(/^\w[^;]*\);?$/) {
					if(/.*;$/) {
						s/;$//;
					}
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
} else {
	LINE: while(<>) {
		s///;
		if($flag != 1) {
			if(/^\w[^=]*\([^=]*\)?$/) {
				if(/^\w[^;]*\);?$/) {
					next LINE;
				} elsif (! /.*main.*/){
					next LINE;
					# the following line is useful
					$flag = 1;
				}
			}
		} else {
			# we are in the second line of the declaration
			$flag = 0;
			next LINE;
		}
		print;
	}
}
