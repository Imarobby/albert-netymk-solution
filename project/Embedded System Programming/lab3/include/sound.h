#ifndef _sound_h
#define _sound_h

#include "TinyTimber.h"
#include "piezo.h"

typedef struct {
	Object super;
	Piezo *p;
	int f;
	int status;
} Sound;

#define initSound(p, f) { initObject(), p, f, 1}

void changeFrequency(Sound *self, int freq);
void play(Sound *self, int nothing);
void setStatus(Sound *self, int status);
#endif
