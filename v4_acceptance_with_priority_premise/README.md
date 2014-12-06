# V4. Acceptance tests and then unit tests

Content:
*   [Rules](#rules)
*   [Findings](#findings)
*   [Conclusions](#conclusions)

## Rules
*   Choose an acceptance that will impose a higher priority transformation to the code.
*   Refactor any duplication by applying lower level transformation

See Uncle Bob's article "The Priority Transformation Premise": 
http://blog.8thlight.com/uncle-bob/2013/05/27/TheTransformationPriorityPremise.html

## Findings

I have started with the test "value of an empty roman numeral is 0". This forced the transformation:  **(nil -> constant)**

The next higher transformation I could force was: **(unconditional -> if)** by adding the test for the value of I.

I then refactored the multiple IFs into a Map.  The transformation could be called: **(constants -> map)**

I have introduced the test for adding two numbers to force the transformation **(unconditional -> if)**

I have used the test for adding three numbers to force the transformation: **(if -> for)**

The last test, the one for subtracting two numbers forced a bigger change on the code base. 
This suggest that I should have introduced the test earlier. Probably at the same time I have introduced the test for adding the two numbers.
 
## Conclusions

**Benefits:**
* The resulting code is simple and clean
* The tests are focused on behaviours that push the design
* The evolution of the code seams natural

**Notes:**
* You need to pay great attention to choosing your tests.
* All the refactorings should be made by paying attention to the priority of the transformations
* As the code become more abstract you should note that some test will duplicate the same behaviour. You should remember to remove duplicated tests.
