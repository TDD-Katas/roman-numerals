TDD Katas
=========

In order to discover TDD I have done a series of simple Katas.

Here they are:
*   [RomanToDecimal](#romantodecimal)
*   [BowlingScore](#bowlingscore)

<h2>RomanToDecimal</h2>

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

<h2>BowlingScore</h2>

Create a program, which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game. Here are some things that the program will not do:
* We will not check for valid rolls.
* We will not check for correct number of rolls and frames.
* We will not provide scores for intermediate frames.

You can find more info on how score is calculated here: http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring

Some test ideas:
* Gutter game scores zero - When you roll all misses, you get a total score of zero.
* All ones scores 20 - When you knock down one pin with each ball, your total score is 20.
* A spare in the first frame, followed by three pins, followed by all misses scores 16.
* A strike in the first frame, followed by three and then four pins, followed by all misses, scores 24.
* A perfect game (12 strikes) scores 300.
