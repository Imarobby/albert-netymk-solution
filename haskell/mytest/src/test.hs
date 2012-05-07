import SimpleJSON
import Prettify (pretty, compact)
import PrettyJSON

main = do
    --putStrLn $ compact $ renderJValue $ JObject [ ("name", JString "albert"), ("age", JNumber 22), ("registered", JBool True) ]
    putStrLn $ pretty 30 $ renderJValue $ JObject [ ("f", JNumber 1), ("q", JBool True)]
