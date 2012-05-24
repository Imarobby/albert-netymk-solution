import Data.Number.Symbolic
{-
import Test.HUnit

testPlus = TestList [
        TestCase $ assertEqual 
            "two cons" "6" (show $ (con 3) + (con 3)),
        TestCase $ assertEqual 
            "int, con" "4" (show $ 1 + (con 3)),
        TestCase $ assertEqual 
            "con, int" "5" (show $ (con 2) + 3),
        TestCase $ assertEqual 
            "con 0, int 0" "0" (show $ (con 0) + 0),
        TestCase $ assertEqual 
            "int 0, int 0" "0" (show $ 0 + 0),
        TestCase $ assertEqual 
            "int, con" "1" (show $ (con 1) + 0)
        --TestCase $ assertEqual 
        --    "var, con" "x" (show $ (var "x") + 0)
        --TestCase $ assertEqual 
        --    "con, var" "x" (show $ 0 + (var "x"))
        ]
        -}
main = do
    --runTestTT testPlus
    print $ (var "x") + 0
    print $ 0 + (var "x")
