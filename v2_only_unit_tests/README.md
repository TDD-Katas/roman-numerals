<h1> V2. Only unit tests

Content:
*   [Rules](#rules)
*   [Findings](#findings)
*   [Conclusions](#conclusions)

<h2>Rules</h2>
*   pick the smallest behaviour from the domain space
*   write the test that describes it (red)
*   write the minimum amount of code to make the test pass (green)
*   continue to the next behaviour
*   refactor any duplication between tests

<h2>Findings</h2>

<p>
I found that focusing on small units of behaviour makes it easy to start with.
In just a couple of tests the core domain behaviours are nailed down.
Using this approach focused on behaviours you get the domain objects emerging from the very begining.
</p>
<p>
 The tricky bit has after all the behaviours where written as unit tests. Looking at the code, I had really nice units of functionality but they where not interacting with eachother. 
</p>
<p>
 In order to produce a working software I ended up adding tests in order to glue all the pieces together. This felt a bit unnatural and the tests where syntetic, not representing domain concepts.
</p>

<h2>Conclusions</h2>

**Benefits:**
* It is easy to get up to speed
* Most of the resulting unit tests are focused on behaviour and are easy to maintain
* The emerged code feels more like object oriented

**Downsides:**
* As a bottom-up approach, you can miss scope by over-engineering the units
* You need to add syntetic test to glue the units together and you end up introducing fake domain concepts
* You have no functional/acceptance tests to prove that the software works
