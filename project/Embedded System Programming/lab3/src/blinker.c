#include "blinker.h"

int startBlinking(Blinker *self, int nothing)
{
	if(self->period > 0) {
		blink();
		AFTER(SEC(self->period), self, blink, nothing);
	}
}

int stopBlinking(Blinker *self, int nothing)
{
	self->period = 0;
}

int setPeriod(Blinker *self, int period)
{
	self->period = period;
}

static void blink(Blinker *self, int nothing)
{
	*(self->segment) = ! *(self->segment);
}
