import qualified Data.ByteString.Lazy.Char8 as L

readPrice str = case L.readInt str of
                     Nothing              -> Nothing
                     Just (dollars, rest) -> 
                        case L.readInt (L.tail rest) of
                             Nothing            -> Nothing
                             Just (cents, more) ->
                                 Just (dollars * 100 + cents)

closing = readPrice . (!!4) . L.split ','

highestClose = maximum . (Nothing:) . map closing . L.lines

main = do
    print "dummy"
    let path = "prices.txt"
    contents <- L.readFile path
    print (highestClose contents)
