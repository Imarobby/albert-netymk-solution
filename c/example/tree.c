/************************************************
 * Purpose: Display all words appeared in one file in alphabetical
 * order
 * Known issue: words are defined as letters delimited by spaces
 * ********************************************/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

struct tree_node {
	struct tree_node *left;
	struct tree_node *right;
	char *word;
};

typedef struct tree_node node; // define type node

node *root = NULL;

int main(int argc, char *argv[]) {
	void scan(char *);
	void print_tree(node *);

	if ( argc != 2 ) {
		fprintf(stderr, "Error: not enough arguments.\n");
		exit(8);
	}

	scan(argv[1]); // read data from the input file
	print_tree(root);

	return 0;
}

void scan( char *name ) {
	// function declaration
	void insert(node **, char *);

	char ch; // the current character
	char word[50]; // the current word
	FILE *file; // input file

	int i;

	file = fopen(name, "r");
	if (file == NULL) {
		fprintf(stderr, "Error");
		exit(8);
	}	  

	while (1) {
		ch = fgetc(file);
		
		if (ch == EOF)
			break; // EOF
		else if (!isalpha(ch)) 
			continue;

		// if we are here, ch is one letter
		word[0] = ch;
		for ( i=1; i<sizeof(word)-1; ++i) {
			ch = fgetc(file);
			if (!isalpha(ch))
				break;
			// if we are here, ch is OK
			word[i] = ch;
		}

		word[i] = '\0'; // construct one string

		// we want function insert do some modification to root
		// therefore, we give the address of root to it
		insert(&root, word); // put this word into the tree
	}

	// clean up
	fclose(file);
}

/*
 * Param:
 * 			n		current node
 * 			word	word to insert
 */
void insert(node **n, char *word) {
	// function declaration
	void memory_error(void);
	char* save_string(char *);

	int result; // the result of comparison

	// if the current node is null, we need to create one
	if (*n == NULL) {
		*n = malloc(sizeof(node));
		if (*n == NULL)
			memory_error();

		// Initialization
		(**n).left = NULL;
		(**n).right = NULL;
		(**n).word = save_string(word);
		return ;
	}

	// check the two sub-node to this node to see which way shall we
	// go
	result = strcmp((**n).word, word);

	if (result == 0)
		return; // this word has been in this tree, do nothing

	if (result < 0)
		insert( &(**n).right, word);
	else
		insert( &(**n).left, word);

}

/*
 * pointer to the allocated memory with string copied in it
 */
char *save_string(char *string) {
	// function declaration
	void memory_error(void);

	char * result = malloc(strlen(string)+(size_t)1);

	if (result == NULL)
		memory_error();

	strcpy(result, string);
	return result;
}

/*
 * begin from the top element in this tree
 */
void print_tree(node *top) {
	if (top == NULL)
		return;

	print_tree((*top).left);
	printf("%s\n", (*top).word);
	print_tree((*top).right);
}

void memory_error(void) {
	fprintf(stderr, "Error");
	exit(8);
}
