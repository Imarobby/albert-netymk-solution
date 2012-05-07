tinySecond (_:x:_) = Just x
tinySecond _ = Nothing


main = do
    print $ tinySecond [1..10]
    print $ tinySecond [1]
