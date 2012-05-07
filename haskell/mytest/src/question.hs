class Dual a where
    dual :: a -> a

instance Dual Bool where
    dual = not
    
-- What if I want to say for each type belonging to Num, it belongs to Dual as
-- well?
--instance (Num a) => Dual a where
instance Dual Int where
    dual = negate

instance (Dual a, Dual b) => Dual (a -> b) where
    dual f = dual . f . dual

instance Dual a => Dual [a] where
    dual = reverse . map dual
main = do
    --print $ dual head [1..10]
    print $ dual head ([1..10] :: [Int])
