<h1> V1. Only functional tests

Content:
*   [Rules](#rules)
*   [Findings](#findings)
*   [Conclusions](#conclusions)

<h2>Rules</h2>
*   write the dumbest end to end test and see it fail (red)
*   write the dumbest code that makes the test pass (green)
*   refactor like you really meant it

<h2>Findings</h2>

It took over 40 tests to complete the kata. 
The increments where really really tiny. It felt like slowly uncovering an algorithm to compute the roman number decimal value. The refactoring stage was really crucial. Most of the time was spent on exposing and refactoring duplication.
In the end an new algorithm emerged. I was totally surprised by that.

The algorithm found can be described like this:
* locate the biggest symbol in a roman numeral
* the value of that numberal is the the value of that symbol minus the value of the roman numeral on the left plus the value of the roman numeral on the right.
* an empty roman numeral has the value of 0

*Example: XLIV*
* The biggest symbol is L and thus XLII can be splitted in (L) - (X) + (IV)
* For IV we do the same. Biggest simbol is V an IV ca be splitted in (V) - (I)

<h2>Conclusions</h2>

**Benefits:**
* This approach can be used to discover an algorithm
* The amount of production code produced is minimal
* As a top-down approach, it keeps you focused on solving the problem
* You have acceptance tests to prove that the software works

**Downsides:**
* It took a long time to go through the whole process.
* The code produced is not that simple to understand as it uses recursion.
* The sheer number of resulted test represents a form of duplication and this make test fragile
* The overall result (code + tests) are difficult to change and maintain
