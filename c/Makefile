TARGET = target
MAKEFILE = /home/albertnet/workspace/c/Makefile

SRCDIR=src
TESESRCDIR=test
OUTPUTDIR=obj
DENDIR = $(OUTPUTDIR)/dep
SOURCES = $(notdir $(wildcard $(SRCDIR)/*))
OBJECTS = $(addprefix $(OUTPUTDIR)/,$(subst .c,.o,$(SOURCES)))
TESTSOURCES = $(notdir $(wildcard $(TESESRCDIR)/*))
TESTOBJECTS = $(addprefix $(OUTPUTDIR)/,$(subst .c,.o,$(TESTSOURCES)))

CFLAGS += -std=c99
CPPFLAGS += -Iinclude
LDLIBS = -lcspec -lm

CC=clang
SORT=sort
AWK=awk
CAT=cat

vpath %.c src
vpath %.c test
vpath %.h include
vpath %.o obj
vpath %.d obj/dep


all: compile

link: $(OUTPUTDIR)/$(TARGET).out
	ln -s "$<" "$(TARGET).out"
	
test: $(OBJECTS) $(TESTOBJECTS)
	$(LINK.o) $^ $(LOADLIBES) $(LDLIBS) -o $@.out
	./$@.out

compile: $(OBJECTS)

$(OUTPUTDIR)/$(TARGET).out : $(OBJECTS)
	$(LINK.o) $^ $(LOADLIBES) $(LDLIBS) -o $@

$(OUTPUTDIR)/%.o : %.c
	$(COMPILE.c) $(OUTPUT_OPTION) $<

$(DENDIR)/%.d : %.c
	$(CC) -M $(CPPFLAGS) $< > $@.$$$$; \
	sed 's!\($*\)\.o[ :]*!$(OUTPUTDIR)/\1.o $@ : !g' < $@.$$$$ > $@; \
	$(RM) -f $@.$$$$

ifneq "$(strip $(MAKECMDGOALS))" "help"
ifneq "$(strip $(MAKECMDGOALS))" "clean"
-include $(shell mkdir $(DENDIR) 2>/dev/null) $(addprefix $(DENDIR)/,$(subst .c,.d,$(SOURCES)))
endif
endif

.PHONY: clean
clean:
	rm -rf $(OUTPUTDIR)/*
	rm -f "$(TARGET).hex"

.PHONY: help
help:
	@$(CAT) "$(MAKEFILE)" | $(AWK) '/^[^.%][-A-Za-z0-9_]*:/ { print substr($$1, 1, length($$1)-1) }' | $(SORT)
