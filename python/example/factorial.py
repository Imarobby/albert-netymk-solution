def factorial(n):
	if not isinstance(n, int):
		print "Only integer.";
		return -1;
	elif n < 0:
		print "Only positive number.";
		return -1;
	else:
		return do_factorial(n);

def do_factorial(n):
	if n == 0:
		return 1;
	else:
		return n*do_factorial(n-1);

n = input("Input sth:\n");
print factorial(n);
