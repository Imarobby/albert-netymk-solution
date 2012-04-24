exports = this

# Each
# Natively supported by coffeescript
result = ''
f = (i) -> result += "Item: #{i} "

#Map
# Natively supported by coffeescript
result = (item*2 for item in [1..5])

# Filter
# Natively supported by coffeescript
result = (item for item in [1..10] when item % 2 == 1)

#Foldl
foldl = (list, seed, f) ->
	copyList = list.slice()
	while copyList.length
		seed = f seed, copyList.shift()
	seed
list = [1..3]
result = foldl list, 10, (a,b) -> a*b

#Foldr
foldr = (list, seed, f) ->
	copyList = list.slice()
	while copyList.length
		seed = f copyList.pop(), seed
	seed
list = [1..3]

#Sum
list = [1..10]
sum = (list) ->
		result = 0
		((i) -> result += i) item for item in list
		result

#Join
join = (list, sep) ->
	first = list.shift()
	foldl list, first, (seed, item) -> seed + sep + item
join = (list, sep) ->
	last = list.pop()
	foldr list, last, (item, seed) -> item + sep + seed
result = join [1..5], ':'

#Any
any = (list, f) ->
	foldl list, false, (seed, item) -> seed || f item

#All
all = (list, f) ->
	foldl list, true, (seed, item) -> seed && f item

#Zip
zip = (xs, ys) ->
	len = Math.min [xs.length, ys.length]...
	result = []
	for i in [0..len-1]
		result.push [xs[i], ys[i]]
	result

#Compose
compose = () ->
	methods = (method for key,method of arguments)
	(i) -> foldr methods, i, (f, i) -> f(i)
result = compose( ((i) -> i+3), ((i) -> i*2), sum ) [1..5]

#Curry
curry = (f, arg) ->
	(i) -> f(arg, i)
exportsMethods =
	'foldl'  	:	foldl
	'foldr'  	:	foldr
	'sum'    	:	sum
	'join'   	:	join
	'any'    	:	any
	'all'    	:	all
	'zip'    	:	zip
	'compose'	:	compose
	'curry'		:	curry

exports.M = exportsMethods
