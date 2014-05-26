<h1> V3. Acceptance tests and then unit tests

Content:
*   [Rules](#rules)
*   [Findings](#findings)
*   [Conclusions](#conclusions)

<h2>Rules</h2>
*   Write on acceptance test an see it fail
*   Step by step, write unit tests to describe the behaviour needed for the acceptance test to pass
*   Once the acceptance test passes, move to the next one

<h2>Findings</h2>

<p>
 I have started with the description of the problem and turned it into executable acceptance test using Concordion. This was interesting because as you played with the domain level specification you gain a better understanding of the behaviours needed to make the software work.
</p>
<p>
 Having one acceptance test to pass keeps you on scope as your coding efforts focus on passing that one test. Also, the html output produced looks rather nice.
</p>
<p>
 For each of the acceptance test I then started writing unit tests to describe how the input should move to the output. This felt quite natural as you only write the unit test for the smallest processing in order to push the input forward.
<p/>
<p>
 Looking back at the process, I now see that having the freedom to describe the how you process the input to produce the output, you can over-engineer by adding more processing steps or over complicating them.
 You unit tests describe exactly the solution so now the code is couple with the tests.
<p/>

<h2>Conclusions</h2>

**Benefits:**
* You get nice, readable acceptance tests
* As a top-down approach, it keeps you focused on solving the problem

**Downsides:**
* Takes a long time to write the acceptance tests with Concordion
* For small pieces of behaviour you get duplication between acceptance and unit test
* There is the danger to over-engineer the solution by adding unit tests to describe a complex processing
* Although simple, the produced code if quite difficult to understand because it focuses more on processing input than on domain concepts
