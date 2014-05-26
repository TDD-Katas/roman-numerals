<h3>Rules</h3>
*   write the dumbest end to end test and see it fail (red)
*   write the dumbest code that makes the test pass (green)
*   refactor like you really meant it

<h3>Findings</h3>

It took over 40 tests to complete the kata. 
The increments where really really tiny. It felt like discovering an algorithm to compute the roman number decimal value. 
I was totally surprised by the result. I did had not expected a recursive algorithm to emerge.

The algorithm can be described like this:
* find the biggest symbol in a roman numeral
* the value of that numberal is the the value of that symbol minus the value of the roman numeral on the left plus the value of the roman numeral on the right.
* an empty roman numeral has the value of 0

*Example: XLII*
* The biggest symbol is L and will be splitted in (X)(L)(II)
* XLII = (L) - (X) + (II) = (L) - (X) + (I) + (I) = 50 - 10 + 1 + 1 = 42

<h3>Conclusions</h3>

**Benefits:**
* This approach can be used to discover an algorithm
* The amount of production code produced is minimal

**Downsides:**
* It took a long time to go through the whole process.
* The code produced is not that simple to understand as it uses recursion.
* The sheer number of resulted test represents a form of duplication
* The overall result (code + tests) are difficult to change
