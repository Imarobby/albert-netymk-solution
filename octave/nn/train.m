function [weights, rmse] = train(iteration, update)
	load('data.mat', 'data');
	n = size(data, 2)-1;
	m = size(data,1);
	eta = 0.1;
	try 
		load('weights.mat', 'weights')
	catch
		weights = rand(n+1, 10);
	end
	rmse = 0;
	% [m x 32*32+1] The last digit is y
	for i = 0:9
		tmp = find(data(:,end) == i);
		index(i+1, 1:size(tmp,1)) = tmp;
	end

	%x = grouped;
	for i=1:iteration
		rmse = 0;
		for j=0:9
			totalNumber = sum(index(j+1,:) > 0);
			half = floor(totalNumber/2);
			y = zeros(10, half);
			y(j+1, :) = 1;
			x = zeros(n+1, half);
			for k = 1:half
				x(:,k) = [1 data(index(j+1, k), 1:end-1)]';
			end
			z = weights' * x;
			% [ 10 x half ]
			h = 1./(1+exp(-z));
			for k=1:n+1
				mySum = (h - y) * x(k, :)';
				step = -eta*mySum;
				weights(k, :) = weights(k, :) + step';
			end
			rmse = rmse + sqrt(sum(sum((h-y).^2))/size(y, 2));
		end
		rmse
	end
	if update
		save('weights.mat', 'weights');
	end
end
