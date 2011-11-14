
#vpath %.c src
#vpath %.h include
#
#SDIR=src
#IDIR=include
#ODIR=obj

SDIR=.
IDIR=../include
ODIR=../obj

CC=gcc
CFLAGS=-I$(IDIR)

all: $(ODIR)/test1.o

#test.out: $(ODIR)/%.o

#$(ODIR)/%.o: $(SDIR)/%.c, $(IDIR)/%.h

obj/test1.o : $(SDIR)/tinythreads.c $(SDIR)/test1.c $(IDIR)/tinythreads.h
	$(CC) -c $(CFLAGS) $^

.PHONY: clean

clean:
	rm -rf $(ODIR)/*.o


