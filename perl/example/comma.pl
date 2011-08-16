#!/usr/bin/perl
use strict;
use warnings;

my $pop = "The US population is 135323253.";
# find the position where there one digit on the left, and there are 3n digits on the right
$pop =~ s/(?<=\d)(?=(\d\d\d)+(?!\d))/,/g;
print "$pop\n";
