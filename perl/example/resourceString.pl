#!/usr/bin/perl
use strict;
use warnings;

my %set = ();
my $key;
while(<>) {
	chomp $_;
	if (/.*GetString\("[^"]*"\).*/) {
		s/.*GetString\("([^"]*)"\).*/$1/;
		$set{$_} = 1 unless $set{$_}
	}
}
foreach $key (sort keys %set) {
	print "<li>", $key, "</li>\n";
}
