#!/usr/bin/perl
# this is used to extract a few tables out of the whole database
# this only works with dbml in visual studio
use warnings;
use strict;

# containing all tables of interest
my %dict = (
	Question => "1",
	QuestionSetting => "2",
	QuestionSCOSetting => "3",
	QuestionRight => "4",
	Test => "5",
	TestSetting => "6",
	TestQuestion => "7",
	TestQuestionSetting => "8",
	TestAnswerSet => "9",
	TestAnswerBasic => "9",
	TestAnswerCombi => "9",
	TestAnswerImage => "9",
	TestAnswerMatrix => "9",
	TestAnswerSetSCOQuestion => "9",
	TestAnswerSetQuestion => "10",
	scormTest => "11",
	AdminUser => "12",
	PublicUser => "12"
);


my $flag = 1;
my $previousLine;
my $currentLine;
LINE: while(<>) {
	if(/^\s*<Table/) {
		$previousLine = $_;
		next LINE;
	} elsif(/^\s*<Type/) {
		$currentLine = $_;
		$_ =~ s/.*Name="([^"]+)".+/$1/;
		chomp($_);

		if($dict{$_}) {
			# we need this block
			print $previousLine;
			print $currentLine;
			$flag = 1;
		} else {
			$flag = 0;
		}
	} elsif(/^\s*<\/Table/) {
		if($flag == 1) {
			print;
		} 
		# start over again
		$flag = 1;
	} elsif($flag == 1) {
		# ordinary lines within one table element, just print
		print;
	}
}
