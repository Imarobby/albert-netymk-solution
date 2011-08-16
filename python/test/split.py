#!/usr/bin/python

import sys,os;

mega = 1000*1024;
chunksize=int(1.4*mega);

def split(fromfile, todir, chunksize=chunksize):
	if not os.path.exists(todir):
		os.mkdir(todir);
	else:
		if os.listdir(todir):
			if raw_input('Are you sure you want to overwrite this directory?') == 'y' :
				for fname in os.listdir(todir):
					os.remove(os.path.join(todir, fname));
			else:
				return ;

	partnum = 0;
	input = open(fromfile, 'rb');
	while 1:
		chunk = input.read(chunksize);
		if not chunk: break;
		partnum = partnum+1;
		filename = os.path.join(todir, ('part%04d' % partnum));
		fileobj = open(filename, 'wb');
		fileobj.write(chunk);
		fileobj.close();
	input.close();
	assert partnum <= 9999;
	return partnum;

if __name__ == "__main__":
	if len(sys.argv) == 3:
		try:
			split(sys.argv[1], sys.argv[2]);
		except:
			print sys.exc_info();
		else:
			print 'finished';
	elif len(sys.argv) == 4:
		try:
			split(sys.argv[1], sys.argv[2], sys.argv[3]);
		except:
			print sys.exc_info();
		else:
			print 'finished';
	else:
		print 'argument is not right';
