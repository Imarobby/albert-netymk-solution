#ifndef _melody_h
#define _melody_h

#include "TinyTimber.h"
#include "sound.h"

typedef struct {
	Object super;
	Sound *s;
} Melody;

#define initMelody(s) { initObject(), s }

void playDiana(Melody *self, int index);
void playDianaNonStop(Melody *self, int index);
void playDianaRecommended(Melody *self, int index);
void playDianaHacked(Melody *self, int index);
#endif
