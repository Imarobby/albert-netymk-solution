/************************************************
 * Purpose: to illustrate the relation between array and pointer
 * Conclusion: pointer is the fundamental way to access variables when
 *		we use index to access the elements inside one array, this is
 *		converted to pointer arithmetic.
 *		However, array and point are two different data structure;
 *		sometimes one array can "decay" to one pointer. "Pointer
 *		arithmetic and array indexing are equivalent in C, pointers and
 *		arrays are different."
 * ************************************************/
#include <stdio.h>
void first_exception()
{
    int array[5];
    printf("sizeof of array is %d\n", sizeof(array));
}
void second_exception()
{
    int array[5];
    printf("Address of this array is %p\n", &array);
    printf("Address of the first element is %p\n", &array[0]);
}
void third_exception()
{
    char a[] = "hello";
}

// The type pointer-to-type-T is not the same as array-of-type-T;
// completely different
// Declaration of one array.
extern char array_of_type_T[];
// Declaration of one pointer.
extern char *pointer_to_type_T;

// Since arrays decay into pointers while being passed as formal
// parameters, one pointer is actually passed.
// This reflects what's really passed.
void f_with_pointer_to_type_T(char *a);
// As a convenience, we can't also write it as if one array is passed.
// However, this declaration is identical to the above. Only in this, are
// the two formats the same.
void f_with_array_of_type_T(char a[]);

int main() {
    char a[] = "hello";
    // With the expression a[3], the compiler emits code to start at
    // the location "a", move three past it, and fetch the character
    // there.
    char *p = "world";
    // With the expression p[3], the compiler emits code to start at
    // the location "p", fetch the pointer value there, add three to
    // the pointer, and finally fetch the character pointed to.
    //
    // A reference to an object of type array-of-T which appears in an
    // expression decays(with three exceptions) into a pointer to its
    // first element; the type of the resultant pointer is pointer-to-T
    // The exceptions are when the array is the operand of a sizeof or
    // & operator or is a string literal initializer for a character
    // array.
    first_exception();
    second_exception();
    third_exception();
    return 0;
}
