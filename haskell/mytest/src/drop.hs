--myDrop n xs@(x:xs') | n <= 0 = xs
--			    | otherwise = myDrop (n-1) xs'
myDrop n (x:xs') | n <= 0 = x:xs'
                 | otherwise = myDrop (n-1) xs'
myDrop n [] = []
