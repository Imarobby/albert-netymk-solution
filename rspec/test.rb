require 'rubygems'
require 'rspec'

describe 'simple math' do
	it 'should calculate the sume of two numbers' do
		(1+2).should == 3
	end
end
