def filterFile(old, new):
	f1 = open(old, 'r');
	f2 = open(new, 'w');
	while True:
		line = f1.readline();
		if line == '':
			break;
		if line[0] == '#':
			continue;	# skip this line
		f2.write(line);
	f1.close();
	f2.close();
return;
