lend amount balance = if amount > reserve * 0.5
                         then Nothing
                         else Just newbalance
        where reserve = 100
              newbalance = balance - amount

lendGuards amount balance
        | amount <= 0            = Nothing
        | amount > reserve * 0.5 = Nothing
        | otherwise              = Just newbalance
    where reserve = 100
          newbalance = balance - amount

main = do
    print $ lend 10 100
    print $ lend 60 100
    print $ lendGuards 10 100
    print $ lendGuards 60 100
