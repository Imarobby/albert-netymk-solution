#include "tinytimber.h"

typedef struct {
	Object super;
	int segment;
	int period;
} Blinker;

int startBlinking(Blinker *self, int nothing);
int stopBlinking(Blinker *self, int nothing);
int setPeriod(Blinker *self, int period);
