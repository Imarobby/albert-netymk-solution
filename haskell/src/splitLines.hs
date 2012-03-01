splitLines [] = []
splitLines cs = 
    pre :
    case suf of
	('\r' : '\n' : rest) -> splitLines rest
	('\r' : rest)	     -> splitLines rest
	('\n' : rest)	     -> splitLines rest
	[]		     -> [] -- end of string only
    where
	(pre, suf) = break isLineTerminator cs
isLineTerminator c = c =='\r' || c == '\n'
