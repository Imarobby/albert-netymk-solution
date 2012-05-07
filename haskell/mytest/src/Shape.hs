module Shape (Shape(Rectangle, Ellipse, RtTriangle, Polygon),
                Radius, Side, Vertex,
                square, circle, distBetween, area
                ) where
-- The list of names above are those entities that are intended to be
-- visible or exported, from the module.
-- 1. Data types that are listed with their constructors in parentheses.
-- 2. Type synonyms.
-- 3. Ordinary values (in this case the functions).
data Shape      = Rectangle Side Side
                | Ellipse Radius Radius
                | RtTriangle Side Side
                | Polygon [Vertex]
        deriving Show

-- type synonyms
type Side       = Float
type Vertex     = (Float, Float)
type Radius     = Float

square s = Rectangle s s
circle r = Ellipse r r

area (Rectangle s1 s2)  = s1*s2
area (RtTriangle s1 s2) = s1*s2/2
area (Ellipse r1 r2)    = pi*r1*r2
area (Polygon (v1:vs)) = polygonArea vs
        where polygonArea (v2: v3: vs') = triArea v1 v2 v3 + polyArea (v3: vs')
              polyArea _                = 0

triArea v1 v2 v3 = sqrt (s*(s-a)*(s-b)*(s-c))
                where a = distBetween v1 v2
                      b = distBetween v2 v3
                      c = distBetween v3 v1
                      s = 0.5*(a+b+c)

distBetween (x1, y1) (x2, y2)
        = sqrt ((x1-x2)^2 + (y1-y2)^2)
