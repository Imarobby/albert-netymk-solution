myGetLine ::  IO [Char]
myGetLine = do x <- getChar
               case x of
                  '\n' -> return []
                  otherwise -> do xs <- myGetLine
                                  return (x:xs)

myPutStr :: String -> IO ()
myPutStr = foldr f (return ())
        where f x p = putChar x >>= \_ -> p

-- >>= works like a chain, pipe. That's why I have to create the lambda
-- boilerplate. Kind of drawback of pure functional programming language
-- dealing with side effect.
myPutStrLn :: String -> IO ()
myPutStrLn x = myPutStr x >>= \_ -> putChar '\n'

guess word = do
    myPutStr ">"
    xs <- myGetLine
    if xs == word then
         myPutStrLn "You got it!"
     else
        do myPutStrLn $ diff word xs
           guess word

diff xs ys = [ if elem x ys then x else '-' | x <- xs ]

sGetLine :: IO String
sGetLine = do
    x <- getChar
    case x of
         '\n' -> do putChar x
                    return []
         otherwise -> do putChar '*'
                         xs <- sGetLine
                         return (x:xs)

hangman = do
    myPutStrLn "Think of a word: "
    word <- sGetLine
    myPutStrLn "Try to guess it: "
    guess word
main = do result <- myGetLine
          myPutStrLn result
          hangman








