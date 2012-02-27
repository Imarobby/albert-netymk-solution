sqr x = convAbs $ iterate improve 1
    where improve r = (r + x/r) / 2
          convAbs (x1:x2:_) | abs (x1-x2) < 1e-10 = x2
          convAbs (_:xs)                          = convAbs xs

data PD a = P a a deriving (Eq, Ord, Show)
instance Num a => Num (PD a) where
        P x x' + P y y' = P (x+y) (x'+y')
        P x x' - P y y' = P (x-y) (x'-y')
        P x x' * P y y' = P (x*y) (x*y' + y'*x)
        fromInteger i = P (fromInteger i) 0
        abs (P x x') = P (abs x) (signum x * x')
        signum (P x x') = P (signum x) 0
instance Fractional a => Fractional (PD a) where
        P x x' / P y y' = P (x/y) ((x'*y - x*y')/(y*y))
        fromRational r = P (fromRational r) 0
