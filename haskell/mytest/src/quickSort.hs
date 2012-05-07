quickSort [] = []
quickSort (x:xs) = quickSort ys ++ [x] ++ quickSort zs
        where
        -- choose either of them
            --ys = [a | a <- xs, a <= x]
            --zs = [b | b <- xs, b > x]
            ys = filter (<=x) xs
            zs = filter (>x) xs

main = 
    print $ quickSort [1, 2.3, 5, 4.4]

