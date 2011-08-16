#-----------------------------------------------#
#	Makefile for linux systems		#
#-----------------------------------------------#
CC=clang
CFLAGS=-g -Wall

search.out: search.o fileRead.o
	${CC} ${CFLAGS} $^ -o search.out

search.o: search.c

fileRead.o: fileRead.c

clean:
	rm -f search.out
