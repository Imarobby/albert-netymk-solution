--member x xs = any (x==) xs
--subset xs ys = all (\x -> member x xs) ys
--
--data Set a = Set [a]
--
--instance Eq a => Eq (Set a) where
--   Set xs == Set ys = subset xs ys && subset ys xs


class Dual a where
    dual :: a -> a

instance Dual Bool where
    dual = not
    
--instance (Num a) => Dual a where
instance Dual Int where
    dual = negate

instance (Dual a, Dual b) => Dual (a -> b) where
    dual f = dual . f . dual

instance Dual a => Dual [a] where
    dual = reverse . map dual
main = do
    print "dummy"
    --print $ member 1 $ reverse [1..10]
    --print $ member 0 $ reverse [1..10]
    --print $ dual head ([1..10] :: [Int])
    --print $ dual head [1..10]
    print $ dual tail [True, False, False]
    --print $ dual (++) [3,4] [1,2]
    print $ dual max (3 :: Int) (4 :: Int)
