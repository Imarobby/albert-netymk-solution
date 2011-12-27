#!/usr/bin/python

"""

"""

import os.path
import sys
import subprocess
import tempfile

def combine(sourceFileName, headerFileName):
	prefix = '/home/albertnet/workspace/perl/example/';

	oldDeclaration = tempfile.TemporaryFile()
	newDeclaration = tempfile.TemporaryFile()
	subprocess.call(prefix + 'extractHeader.pl -e "' + headerFileName +'"', stdout=oldDeclaration, shell=True)
	subprocess.call(prefix + 'extractHeader.pl "' + sourceFileName +'"', stdout=newDeclaration, shell=True)
	oldDeclaration.seek(0)
	newDeclaration.seek(0)

	oldDeclarationLines = oldDeclaration.readlines()
	newDeclarationLines = newDeclaration.readlines()
	if oldDeclarationLines != newDeclarationLines:
		print headerFileName, ' is updated.'
		header = tempfile.TemporaryFile()
		subprocess.call(prefix + 'extractHeader.pl "' + headerFileName +'"', stdout=header, shell=True)
		newHeader = open(headerFileName, 'w')

		header.seek(0)
		headerLines = header.readlines() 
		for line in headerLines[:-1] + newDeclarationLines:
			newHeader.write(line)

		newHeader.write(headerLines[-1])
		newHeader.close()
		header.close()
	newDeclaration.close()
	oldDeclaration.close()

if len(sys.argv) != 3:
	print "arguments not right.\n"
	sys.exit(0);

targetDirectory = sys.argv[1]
if targetDirectory[-1] != '/':
	targetDirectory += '/';

src = sys.argv[2]
src = targetDirectory + src

itemname = os.path.basename(src)[:-2]
header = targetDirectory + 'include/' + itemname + '.h'
if not os.path.isfile(header):
	# create new header
	f = open(header, 'w')
	f.write('#ifndef _' + itemname + '_h' + "\n")
	f.write('#define _' + itemname + '_h' + "\n")
	f.write('#endif')
	f.close()
combine(src, header);
