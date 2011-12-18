# Print the frequency of each word
{
	for (i = 1; i <= NF; i++)
		freq[$i]++;
}

END{
	for (word in freq)
		printf "%-20s\t\t%d\n", word, freq[word]
}
