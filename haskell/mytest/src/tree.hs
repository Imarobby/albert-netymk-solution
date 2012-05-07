data BinTree a = Leaf a
               | BinTree a :^: BinTree a

data LabTree l a = Tip a
                 | LFork l (LabTree l a) (LabTree l a)

data STree a = Empty
             | Split a (STree a) (STree a)

data RoseTree a = Node a [RoseTree a]

type Name = String
data Term = Var Name
          | Ap Term Term
          | Lam Name Term

class Tree t where
    subtrees :: t -> [t]

instance Tree (BinTree a) where
    subtrees (Leaf n) = []
    subtrees (l :^: r) = [l, r]

instance Tree (LabTree l a) where
    subtrees (Tip x) = []
    subtrees (LFork x l r) = [l, r]

instance Tree (STree a) where
    subtrees Empty = []
    subtrees (Split x l r) = [l, r]

instance Tree (RoseTree a) where
    subtrees (Node x gts) = gts

instance Tree Term where
    subtrees (Var _) = []
    subtrees (Ap f x) = [f, x]
    subtrees (Lam v b) = [b]

depth :: Tree t => t -> Int
depth = (1+) . foldl max 0 . map depth . subtrees

size :: Tree t => t -> Int
size = (1+) . sum . map size . subtrees

paths :: Tree t => t -> [[t]]
paths t | null br = [[t]]
        | otherwise = [ t:p | b <- br, p <- paths b]
          where br = subtrees t

dfs :: Tree t => t -> [t]
dfs t = t : concat (map dfs (subtrees t))

bfs :: Tree t => t -> [t]
bfs = concat . lev
        where lev t = [t] : foldr cat [] (map lev (subtrees t))
              cat = combine (++)

combine :: (a->a->a) -> ([a]->[a]->[a])
combine f (x:xs) (y:ys) = f x y : combine f xs ys
combine f [] ys = ys
combine f xs [] = xs

class Tree t => DrawTree t where
    drawTree :: t -> String
    labTree :: t -> String

    -- unlines will add "\n" to each item in this list.
    drawTree = unlines . drawTree' labTree

instance Show a => DrawTree (BinTree a) where
    labTree (Leaf n) = show n
    labTree (_ :^: _) = "@"

instance (Show l, Show a) => DrawTree (LabTree l a) where
    labTree (Tip x) = show x
    labTree (LFork x _ _) = show x

instance Show a => DrawTree (STree a) where
    labTree Empty = "*"
    labTree (Split x _ _) = show x

instance Show a => DrawTree (RoseTree a) where
    labTree (Node x _) = show x
    
instance DrawTree Term where
    labTree (Var v) = v
    labTree (Ap _ _) = "@"
    labTree (Lam v _) = "\\"++ v

drawTree' :: Tree t => (t->String) -> t -> [String]
drawTree' f t = visit f "" "" "" t

visit ::  Tree t => (t -> String) -> 
        [Char] -> [Char] -> [Char] -> t -> [String]
visit f leading arm tie node =
    [leading ++ arm ++ tie ++ (f node) ]
    ++ (concat $ map (visit f (leading ++ extension) "|" "-- ") firstPart)
    ++ (concat $ map (visit f (leading ++ extension) "`" "-- ") lastOne)
        where 
            extension = case arm of 
                           "" -> ""
                           "`" -> "    "
                           _ -> "|   "
            children = [x | x <- subtrees node]
            firstPart = take (length children - 1) children
            lastOne | length children > 0 = [last children]
                    | otherwise = []

main = do
    --let b = Leaf 1
    --let c = Leaf 4
    --let a = b :^: c
    --print $ depth a
    --print $ size a
    --putStrLn $ drawTree ((Leaf 1 :^: Leaf 2) :^: (Leaf 3 :^: Leaf 4))
    putStr $ drawTree ((Leaf 1 :^: (Leaf 2 :^: Leaf 3)) :^: Leaf 4)
