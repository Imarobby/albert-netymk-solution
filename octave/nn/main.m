for i=1:10:100
	%[w, e] = train(i, false);
end
	%[w, e] = train(10, true);
	validate();
