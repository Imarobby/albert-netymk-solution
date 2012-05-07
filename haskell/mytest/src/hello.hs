import Control.Exception (handle, bracket)
import Control.Monad (forM)
import System.Directory (doesDirectoryExist, getDirectoryContents)
import System.FilePath ((</>))
import System.IO (openFile, IOMode(..), hClose, hFileSize)

getRecursiveContents :: FilePath -> IO [FilePath]
getRecursiveContents topdir = do
    names <- getDirectoryContents topdir
    let properNames = filter (`notElem` [".", ".."]) names
    paths <- forM properNames $ \name -> do
       let path = topdir </> name
       isDirectory <- doesDirectoryExist path
       if isDirectory
        then getRecursiveContents path
        else return [path]
    return (concat paths)

saferFileSize :: FilePath -> IO (Maybe Integer)
saferFileSize path = handle (\name -> return Nothing) $ do
   h <- openFile path ReadMode
   size <- hFileSize h
   hClose h
   return (Just size)

main = do
    result <- getRecursiveContents "."
    putStrLn $ unlines result
