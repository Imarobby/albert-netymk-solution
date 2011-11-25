#include "tinytimber.h"

typedef struct {
	Object super;
	LCD lcd;
} PrimeCalculator;

#define initPrimeCalculator(lcd) { initObject(), lcd }

int primes(PrimeCalculator *self, int x);
