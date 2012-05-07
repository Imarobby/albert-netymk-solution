#!/usr/bin/perl
use strict;
use warnings;

my %originalResource = ();
my %newResource = ();
# Original resource file
open my $first, '<', $ARGV[0];
# Possible new resource
open my $second, '<', $ARGV[1];

# collect all the existing data
while(<$first>) {
	if(/\s*<data name="([^"]*)".*$/) {
		$originalResource{$1} = 1 unless $originalResource{$1}
	}
}
close $first;

# only add the new ones
while(<$second>) {
	if(/(\w+)\s*=\s*(\w.+)$/) {
		unless ($originalResource{$1}) {
			print '  <data name="', $1, '" xml:space="preserve">', "\n";
			print '    <value>', $2, '</value>', "\n";
			print '    <comment>Added for MapQuestion</comment>', "\n";
			print '  </data>', "\n";
			#$newResource{$1} = 1;
		}
	}
}
close $second;
#foreach my $key (sort keys %newResource) {
#	print $key, "\n";
#}
