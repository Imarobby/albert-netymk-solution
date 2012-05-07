class Arithmetic t where
    add :: t -> t -> t

instance Arithmetic `+` where
    add f g = f 
main = do
    print "dummy"
