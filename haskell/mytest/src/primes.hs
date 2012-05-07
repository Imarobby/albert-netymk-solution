primes [] = []
primes (x:xs) = x : primes [a | a <- xs, not (a `mod` x == 0)]

main = do
    print $ primes [2..100]
