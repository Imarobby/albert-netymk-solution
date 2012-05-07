data BookInfo = Book SerialNumber Name Authors
                deriving (Show)

type SerialNumber = Int
type Name = String
type Authors = [String]
