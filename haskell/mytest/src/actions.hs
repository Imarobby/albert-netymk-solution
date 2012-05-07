str2action input = putStrLn ("Data: " ++ input)

main = do
    str2action "Start of the program"
    mapM_ (str2action . show) [1..10]
    str2action "Done!"
