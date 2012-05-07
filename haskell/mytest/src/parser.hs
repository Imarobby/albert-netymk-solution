type Parser a = String -> [(a, String)]

item :: Parser Char
item = \input -> case input of
                      [] -> []
                      (x:xs) -> [(x,xs)]
failure :: Parser a
failure = \input -> []

return :: a -> Parser a
return v = \input -> [(v, input)]

(+++) :: Parser a -> Parser a -> Parser a
p +++ q = \input -> case p input of
                         [] -> q input
                         _ -> p input

main = do 
    print $ item "hello"
    print $ (failure +++ item) "hello"
    print $ (Main.return 'd' +++ item) "hello"
