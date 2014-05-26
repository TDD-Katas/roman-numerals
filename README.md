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

Discussions about the approaches use to perform the individual katas can be found by navigating to their directory.
