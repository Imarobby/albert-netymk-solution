/************************************************
 * Purpose: split the input "Last/First" into First, Last
 * ********************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
 * Parameter: one string and one character
 *
 * Return: Find the first occurrence in the given string and return
 * the following part of this string, including this character.
 */

char* my_strchr(char *string_ptr, char character) {
	// char* current = string;
	while (*string_ptr != character) {
		if ( *string_ptr == '\0' )
			return NULL;

		++string_ptr;
	}
	return string_ptr;
}

int main() {
	char *first, *last;
	char line[40];

	fgets(line, 40, stdin);
	line[strlen(line) - 1] = '\0'; // get rid of the trailing newline

	last = line; // line begins with the "Last"
	first = my_strchr(line, '/');
	if ( first == NULL ) {
		printf("Error: Can not find '/' in the given string.");
		exit(8);
	}
	*first = '\0';
	++first; // move the pointer to the next position, which is the beginning of First

	printf("First: %s\nLast: %s.\n", first, last);
	return 0;
}
