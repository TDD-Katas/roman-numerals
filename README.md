 RomanToDecimal
================

Content:
*   [Problem description](#problem-description)
*   [TDD approaches](#tdd-approaches)

<h2>Problem description</h2>

The Kata says you should write a function to convert from Roman Numerals to decimal numerals.
In order to keep the kata light, we will not check for valid Roman Numeral.

Roman numerals, the numeral system of ancient Rome, uses combinations of letters from the Latin alphabet to signify values. 
They are based on seven symbols:

| Symbol  | Value    |
| ------- | -------- |
| I       |    1     |
| V       |    5     |
| X       |   10     |
| L       |   50     |
| C       |  100     |
| D       |  500     |
| M       | 1000     |

Numbers are formed by combining symbols together and adding the values. 
Generally, symbols are placed in order of value, 
starting with the largest values. 
When smaller values precede larger values, 
the smaller values are subtracted from the larger values, 
and the result is added to the total. 

Example:

| Roman Number  | Computation                                    | Value      | Comment                         |
| ------------- | ---------------------------------------------- | ---------- | ------------------------------- |
| MMVI          |  1000 + 1000 + 5 + 1                           | 2006       | only addition                   |
| MCMXLIV       |   1000 + (1000 - 100) + (50 - 10) + (5 - 1)    | 1944       | addition and substraction       |

<h2>TDD Approaches</h2>

The intent of this kata was to experiment with various forms of doing TDD. 

<h3> 1. Only functional tests </h3>

**The rules** where:
*   write the dumbest end to end test and see it fail (red)
*   write the dumbest code that makes the test pass (green)
*   refactor like you really meant it

**Findings**

It took over 40 tests to complete the kata. 
The increments where really really tiny. It felt like discovering an algorithm to compute the roman number decimal value. 
I was totaly surprised by the result. A recursive algorithm emerged for computing the value of a roman number.

The algorithm can be described like this:
* find the biggest symbol in a roman numeral
* the value of that numberal is the the value of that symbol minus the value of the roman numeral on the left plus the value of the roman numeral on the right.
* an empty roman numeral has the value of 0

*Example: XLII*
* The biggest symbol is L and will be splitted in (X)(L)(II)
* XLII = (L) - (X) + (II) = (L) - (X) + (I) + (I) = 50 - 10 + 1 + 1 = 42

**Downsides**

It took a long time to go throught the whole process

