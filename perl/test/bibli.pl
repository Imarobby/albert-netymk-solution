#!/usr/bin/perl
# this is used to extract a few tables out of the whole database
# this only works with dbml in visual studio
use warnings;
use strict;

# containing all tables of interest
my %dict = (
	"d10" => "1",
	"d11" => "1",
	"d12" => "1",
	"d13" => "1",
	"d14" => "1",
	"d15" => "1",
	"d16" => "1",
	"d17" => "1",
	"d18" => "1",
	"d19" => "1",
	"d2" => "1",
	"d20" => "1",
	"d21" => "1",
	"d24" => "1",
	"d27" => "1",
	"d28" => "1",
	"d29" => "1",
	"d3" => "1",
	"d30" => "1",
	"d31" => "1",
	"d32" => "1",
	"d33" => "1",
	"d4" => "1",
	"d5" => "1",
	"d5" => "1",
	"d5" => "1",
	"d5" => "1",
	"d6" => "1",
	"d6" => "1",
	"d6" => "1",
	"d6" => "1",
	"d6" => "1",
	"d7" => "1",
	"d7" => "1",
	"d8" => "1",
	"d9" => "1",
	"f19" => "1",
	"f26" => "1",
	"fermi" => "1",
	"gutinengdaililun" => "1",
	"hydrogen_storage" => "1",
	"key1" => "1",
	"key2" => "1",
	"key-2.10" => "1",
	"key-2.10" => "1",
	"key-2.11" => "1",
	"key-2.11" => "1",
	"key-2.12" => "1",
	"key-2.13" => "1",
	"key-2.14" => "1",
	"key-2.15" => "1",
	"key3" => "1",
	"key-3.0" => "1",
	"key-3.1" => "1",
	"key-3.-1" => "1",
	"key7" => "1",
	"ncyanshi" => "1",
	"paw" => "1",
	"pw110" => "1",
	"pw111" => "1",
	"pw112" => "1",
	"pw113" => "1",
	"pw114" => "1",
	"pw115" => "1",
	"pw116" => "1",
	"pw125" => "1",
	"pw126" => "1",
	"pw138" => "1",
	"pw139" => "1",
	"pw140" => "1",
	"pw141" => "1",
	"pw142" => "1",
	"pw143" => "1",
	"pw144" => "1",
	"pw145" => "1",
	"pw146" => "1",
	"pw147" => "1",
	"pw148" => "1",
	"pw149" => "1",
	"pw154" => "1",
	"pw26" => "1",
	"pw54" => "1",
	"pw56" => "1",
	"pw60" => "1",
	"pw61" => "1",
	"pw62" => "1",
	"pw63" => "1",
	"pw64" => "1",
	"pw78" => "1",
	"Thomas" => "1",
	"tuidao" => "1",
	"ultrasoft" => "1",
	"yanshiyinru" => "1",
	"zahuafanhan1" => "1",
	"zahuafanhan2" => "1",
	"zahuafanhan3" => "1"
);

# by default, we will print current line
my $flag = 1;
my $currentLine;

LINE: while(<>) {
	chomp($_);
	if(/^\s*\\bibitem/) {
		$currentLine = $_;
		$_ =~ s/.*bibitem{([^"]+)}\s*/$1/;

		if($dict{$_}) {
			# we need this block
			print $currentLine, "\n";
			$flag = 1;
		} else {
			$flag = 0;
		}
	} elsif( $flag == 1) {
		print $_, "\n";
	}
}
