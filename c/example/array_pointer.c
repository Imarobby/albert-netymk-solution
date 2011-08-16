/************************************************
 * Purpose: to illustrate the relation between array and pointer
 * Conclusion: pointer is the fundamental way to access variables when
 *		we use index to access the elements inside one array, this is
 * 		converted to pointer arithmetic. One array is always converted to
 * 		be one pointer, pointing to the first element, which could be one
 * 		array as well.
 * ************************************************/ 
#include <stdio.h>
int main() {
	// function declaration
	void f0(void);
	void f1(void);
	void f2(void);
	printf("Begin of f0...\n");
	f0();
	printf("Begin of f1...\n");
	f1();
	printf("Begin of f2...\n");
	f2();

	return 0;
}
void f0(void)
{
	/*
	 * int array
	 */

	// I am asking for a block of memory
	int j;
	// asking for one pointer
	int *j_ptr;
	// connect the relation between the pointer and the real address
	// in the memory
	j_ptr = &j;
	// this assigns value to the variable j through the pointer
	*j_ptr = 1;

	printf("The address of j is %p.\n", j_ptr);
	printf("The value of j is %d.\n", *j_ptr);

	/*
	 * char array
	 */

	// we are following the same procedure as the previous example,
	// even though the format is quite different
	// firstly, one block of memory is created for the string
	// secondly, the pointer is pointed to the memory allocated
	char *i_ptr = "Hello";
	printf("The address of i is %p.\n", i_ptr);
	printf("The content of i is %s.\n", i_ptr);
}
void f1(void)
{
	int i,j;
	// one pointer, pointing to a row of integer
	int (*pointer1)[2];

	int a[2][2];

	// a is converted to one pointer pointing to the first row
	pointer1 = a;

	for(i=0; i<2; ++i) {
		for(j=0; j<2; ++j) {
			a[i][j] = 0;
			printf("The address of %d %d element: %p\n",
					i, j, &a[i][j]);
		}
	}
	for(i=0; i<2; ++i) {
		for(j=0; j<2; ++j) {
			printf("The value of %d %d element: %d\n",
					i, j, pointer1[i][j]);
		}
	}
	printf("pointer1+1 is pointing to the second row: %p\n",
			pointer1+1);
}
void f2(void)
{
	int i,j;
	int *pointer1, **pointer2;
	int a[2][2];

	// pointer1 is pointing to that element(integer)
	pointer1 = &a[0][0];
	// pointer2 is pointing to pointer1
	pointer2 = &pointer1;

	for(i=0; i<2; ++i) {
		for(j=0; j<2; ++j) {
			a[i][j] = i*2+1+j;
			printf("The address of %d %d element: %p\n",
					i, j, &a[i][j]);
		}
	}
	for(j=0; j<2; ++j) {
		printf("%d\n", pointer2[0][j]);
	}
	for(i=2; i<4; ++i) {
		printf("%d\n", pointer1[i]);
	}
}
