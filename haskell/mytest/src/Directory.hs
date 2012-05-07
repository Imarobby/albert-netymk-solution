module Main (main) where

import Control.Monad
import Data.List
import System.Directory
import System.Environment

main = do
    args <- getArgs
    mapM_ tlist (if null args then ["."] else args)

tlist :: [Char] -> IO ()
tlist path =
    visit (if "/" `isPrefixOf` path then "" else ".") "" "" "" path

visit ::  [Char] -> [Char] -> [Char] -> [Char] -> [Char] -> IO ()
visit path leading arm tie node = do
    putStrLn (leading ++ arm ++ tie ++ node)
    visitChildren (path ++ "/" ++ node) (leading ++ extension)
  where
    extension = case arm of 
        -- First level
        ""  -> ""
        -- Rest levels
        -- This is the last node.
        "`" -> "    "
        -- There are other nodes; need to be connected with "|"
        _   -> "|   "

visitChildren ::  [Char] -> [Char] -> IO ()
visitChildren path leading =
    whenM (doesDirectoryExist path) $ do
        contents <- getDirectoryContents path
            `catch` (\e -> return [show e])
        let visibles = sort . filter (`notElem` [".", ".."]) $ contents
        mapM_ (visit path leading "|" "-- ") 
                $ take (length visibles -1) visibles
        when (length visibles > 0) 
                $ visit path leading "`" "-- " $ last visibles

-- mtest will "return" bool; flip changes the order of arguments of when so
-- that the second argument is bool now.
whenM mtest ma = mtest >>= flip when ma
