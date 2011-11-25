#include "TinyTimber.h"
#include <avr/io.h>
/* 

This file is just for you to check that you can compile a program
together with tinytimber. In your programs you will have a .h and a .c
file per reactive object and then the application .c file.

*/

// this part will typicaly be in other files, the .h for a reactive object
typedef struct{
  Object super;
} Is;


#define initIs() {initObject()}
#define CONFLCD {LCDCRB = 0xb7; LCDFRR = 0x10; LCDCCR = 0x0f; LCDCRA = 0x80;}
int showAll(Is *self, int nothing);
// end of what you would have in the reactive object .h

// this parts will typicaly be in other files, the .c for a reactive object
int showAll(Is *self, int nothing){
  LCDCRA  = 0x80;
  LCDCRB  = 0xb7;

  LCDDR0  = 0x11;
  LCDDR5  = 0x88;
  LCDDR15 = 0x33;

  LCDDR1  = 0x11;
  LCDDR6  = 0x88;
  LCDDR16 = 0x33;

  LCDDR2  = 0x11;
  LCDDR7  = 0x88;
  LCDDR17 = 0x33;   
}
// end of what you would have in the reactive object .c



//  This is the application:
Is is = initIs();
STARTUP(CONFLCD;ASYNC(&is,showAll,0););
