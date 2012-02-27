--Pay close attention to the signature of these two functions.
--The second argument, the callback function, has different signature.
--
--foldl :: (a->b->a)->a->[b]->a
--foldl step ero (x:xs) = foldl step (step ero x) xs
--foldl _	   ero []     = ero
--
--foldr :: (a->b->b)->b->[a]->b
--foldr step ero (x:xs) = step x (foldr step ero xs)
--foldr _	   ero []     = ero
--
--
myFoldl f z xs = foldr step id xs z
		--where step x g a = g (f a x)
		where step x g a = g (f a x)

--myFilter p xs = foldl step [] xs
--    where step ys x | p x	= reverse (x : reverse ys)
--					| otherwise = ys

--myFilter p xs = foldr step [] xs
--    where step x ys | p x	= x : ys
--		    | otherwise = ys
--
