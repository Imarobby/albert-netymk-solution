function validate()
	%data = dlmread('digits.dat');
	load('data.mat', 'data');
	%save('data.mat', 'data')
	%size(data)
	%exit()
	n = size(data, 2)-1;
	m = size(data,1);
	eta = 0.1;
	load('weights.mat', 'weights')
	rmse = 0;
	% [m x 32*32+1] The last digit is y
	for i = 0:9
		tmp = find(data(:,end) == i);
		index(i+1, 1:size(tmp,1)) = tmp;
	end

	right = 0;
	totalCase = 0;
	for j=0:9
		totalNumber = sum(index(j+1,:) > 0);
		half = floor(totalNumber/2);
		y = zeros(10, half);
		y(j+1, :) = 1;
		x = zeros(n+1, half);
		for k = 1:half
			x(:,k) = [1 data(index(j+1, half+k), 1:end-1)]';
		end
		z = weights' * x;
		% [ 10 x half ]
		h = 1./(1+exp(-z));
		totalCase = totalCase + half;
		[key value] = max(h);
		right = right + sum(value == (j+1));
	end
	hitRating = right/totalCase
end
