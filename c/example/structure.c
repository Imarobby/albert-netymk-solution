/*
 * Program: structure.c
 * Purpose: to illustrate the use of struct in c
 */
#include <stdio.h>
#include <stdlib.h>

#define MAX_NAME_LENGTH 100

struct person {
    char name[MAX_NAME_LENGTH];
    int year_of_birth;
    float height;		/* in meters */
};

struct children {
    struct person data;	/* The person */
    struct person *father_ptr;	/* Pointer to the persons father */
    struct person *mother_ptr;	/* Pointer to the persons mother */
};

// function declaration
void print_person(struct person *person_ptr);


int main(void) {
    struct person father = { "Steve", 1934, 1.88 },
				  mother = { "Judith", 1937, 1.65};

    struct children son = { "Mike", 1960, 1.92, NULL, NULL };

    int size1 = 0, size2 = 0;

    size1 = sizeof(struct person);
    size2 = sizeof(char) * MAX_NAME_LENGTH + sizeof(int) + sizeof(float);

    /* These should be the same */
    printf("%d is the size of struct person, so is %d\n", size1, size2);

    /* Print Father. Note, here .-notation is used to access the parts of the struct */
    printf("%s, %d, %2.2f\n", father.name, father.year_of_birth,
	   father.height);

    /* Print mother, this time with a function who take a pointer to struct, ie we send a adress */
    print_person(&mother);

    /* Assign the son's father and mother */
    son.father_ptr = &father;
    son.mother_ptr = &mother;

    /* print sons personal data */
    print_person(&son.data);
    /* Here we use -> notation for the part of the struct that is a pointer to the father */
    printf("The son, %s, has a father called %s\n", son.data.name,
	   son.father_ptr->name);

	return 0;
}

void print_person(struct person *person_ptr) {
    printf("%s, %d, %2.2f\n", person_ptr->name,
	   person_ptr->year_of_birth, person_ptr->height);
}
