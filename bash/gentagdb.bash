#!/bin/bash
#gentagdb database build script
cscope -b
ctags -L cscope.files
