-- Illustrate the usage of foldr
myAll p ns = foldr (\n b -> b && p n) True ns

myLength = foldr (\_ x -> x+1) 0

main = do 
    print $ myAll even $ filter even [1..5]
    print $ myLength [1..10]
