module.paths.push '/usr/local/lib/node_modules'
chai = require 'chai'
expect = chai.expect
should = chai.should
M = require('../src/Functional').M

describe 'Fold', ->
	beforeEach ->
		@list = [1..3]
		@result = M.foldl @list, 10, (a,b) -> a*b
	describe 'Foldl', ->
		it 'Foldl in haskell', ->
			expect(@result).to.equal 60
		it 'doesnt change the input list', ->
			expect(@list.toString()).to.equal [1..3].toString()
	describe 'Foldr', ->
		it 'gives the correct result', ->
			expect(@result).to.equal 60
		it 'doesnt change the input list', ->
			expect(@list.toString()).to.equal [1..3].toString()
describe 'Sum', ->
	it 'sums all the elements in the list', ->
		expect(M.sum [1..10]).to.equal 55
describe 'Join', ->
	it 'joins all the elements in the list', ->
		result = M.join [1..5], ':'
		expect(result).to.equal '1:2:3:4:5'
describe 'Any', ->
	it 'could find the right element', ->
		result = M.any [1, 4, 5], (i) -> i % 2 == 0
		expect(result).to.equal true
	it 'could eliminate all the wrong elements', ->
		result = M.any [1, 3, 5], (i) -> i % 2 == 0
		expect(result).to.equal false
describe 'All', ->
	it 'could find the right element', ->
		result = M.all [1, 3, 5], (i) -> i % 2 == 1
		expect(result).to.equal true
	it 'could eliminate all the wrong elements', ->
		result = M.all [1, 3, 6], (i) -> i % 2 == 1
		expect(result).to.equal false
describe 'Zip', ->
	it 'works when the two lists are of the same size', ->
		result = M.zip [1..3], [1..3]
		expect(result.toString()).to.equal [[1,1], [2,2], [3,3]].toString()
	it 'works when the first one is longer', ->
		result = M.zip [1..4], [1..3]
		expect(result.toString()).to.equal [[1,1], [2,2], [3,3]].toString()
	it 'works when the second one is longer', ->
		result = M.zip [1..3], [1..4]
		expect(result.toString()).to.equal [[1,1], [2,2], [3,3]].toString()
describe 'Compose', ->
	it '', ->
		result = M.compose( ((i) -> i+3), ((i) -> i*2), M.sum ) [1..5]
		expect(result).to.equal 33
describe 'Curry', ->
	it '', ->
		result = M.curry(((a,b) -> a+b), 10)
		result = result 10
		expect(result).to.equal 20
