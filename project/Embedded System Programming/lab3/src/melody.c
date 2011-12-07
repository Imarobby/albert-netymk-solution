#include "melody.h"

#define a 440
#define b 494
#define c 262
#define d 294
#define e 330
#define f 349
#define g 392
#define h 311

#define x 500
#define y 250
#define z 1250
#define SS 2000
#define t 1500

static int freq[50] = {e, e, e, e, e, e, e, f, f, f,
	f, g, f, d, e, e, e, e, e, e,
	e, f, f, f, f, g, f, d, g, g,
	g, g, a, a, a, a, a, a, a, h, 
	h, h, c, c, a, a, g, h, d, c};

static int duration[50] = {x, x, x, x, x, y, z, x, x, x, 
	x, x, y, z, x, x, x, x, x, y,
	z, x, x, x, x, x, y, z, x, x, 
	x, x, x, y, z, x, x, x, x, x, 
	y, z, SS, SS, t, x, t, x, x, t};


void playDiana(Melody *self, int index)
{
	if(index == 50) {
		index = 0;
	}
	SYNC(self->s, setStatus, 1);
	SYNC(self->s, changeFrequency, freq[index]);
	// SYNC(self->s, changeFrequency, 400);
	// SYNC(self->s, play, NOTHING);
	ASYNC(self->s, play, NOTHING);
	AFTER(RESOLUTION(duration[index]), self->s, setStatus, 0);
	// AFTER(RESOLUTION(31250), self->s, setStatus, 0);
	AFTER(MSEC(50), self, playDiana, index+1);
	//AFTER(MSEC(10), self, playDiana, 0);
}
