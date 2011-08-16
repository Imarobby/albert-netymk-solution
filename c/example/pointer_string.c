/************************************************
 * Program: string_pointer.c
 * Purpose: To illustrate the subtle difference between one pointer
 * pointing to character and one character array.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void char_pointer();
void char_pointer_wrong();
void char_array();

int main() {
	char_pointer();
	// char_pointer_wrong();
	char_array();
	return 0;
}

void char_pointer()
{
	char *string = "Hello";
	int length = strlen(string);

	char *tmplate = malloc(length);
	strcpy(tmplate, string);
	printf("The string is %s.\n", tmplate);

	tmplate[0] = 'h';
	printf("The string is %s.\n", tmplate);
}

void char_pointer_wrong()
{
	char *tmplate = "Hello";
	printf("The string is %s.\n", tmplate);

	// this will result in one segment fault, for "Hello" is stored in
	// data segment
	tmplate[0] = 'h';
	printf("The string is %s.\n", tmplate);
}

void char_array()
{
	// "Hello" is stored on the stack.
	char tmplate[] = "Hello";
	printf("The string is %s.\n", tmplate);

	tmplate[0] = 'h';
	printf("The string is %s.\n", tmplate);
}
