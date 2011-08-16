#!/usr/bin/perl
# convert temperature in Celsius to Fahrenheit, and vice versa
use strict;
use warnings;

print "Enter a temperature (e.g., 32F, 100C):\n";
my $input = <STDIN>;
chomp($input);

if($input =~ m/^([-+]?[0-9]+(\.[0-9]*)?)\s*([CF])$/i) {
	# if we get in here, we had a match. $1 is the number, and $2 is "C" or "F"
	# print "number 1 is $1";
	# print "number 3 is $3";
	my $number = $1;
	my $unit = $3;
	my $celsius;
	my $fahrenheit;
	if($unit =~ m/c/i) {
		$celsius = $number;
		$fahrenheit = ($celsius * 9 / 5) + 32;
	} else {
		$fahrenheit = $number;
		$celsius = ($fahrenheit -32) * 5 / 9;
	}
	$fahrenheit = ($celsius * 9 / 5 ) + 32;
	printf("%.2fC is %.2f F\n", $celsius, $fahrenheit);
} else {
	print "Expecting a number.\n";
}
