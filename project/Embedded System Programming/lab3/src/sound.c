#include "sound.h"

void changeFrequency(Sound *self, int freq)
{
	self->f = freq;
}

void play(Sound *self, int nothing)
{
	if(self->status) {
		AFTER(RESOLUTION(0), self->p, turnOn, 0);
		AFTER(RESOLUTION(31250/(self->f)), self->p, turnOff, 0);
		// WITHIN(RESOLUTION(0), RESOLUTION(31250/(self->f)), self->p, turnOn, 0);
		// WITHIN(RESOLUTION(31250/(self->f)), RESOLUTION(2*31250/(self->f)), self->p, turnOff, 0);
		AFTER(RESOLUTION(2 * 31250 /(self->f)), self, play, 0);
	}
}

void setStatus(Sound *self, int status)
{
	self->status = status;
}
