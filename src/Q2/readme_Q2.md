## Q2 - Object-oriented Decisions

Spiders are a concern for Australian hikers and tourists alike. It is often important to be able to determine exactly which kind of spider you encounter, because this affects what kind of behaviour or medical treatment may be necessary. This piece of software is designed to solve that problem.

This problem considers binary decision trees operating on `Spider`s. Each decision step has **exactly two** branches, based on whether a spider has a particular property (listed within the `enum` `Spider.Property`) or not. These branches may lead to another decision, or to a terminal node, which reports the name of the species the spider has been classified as.

<h2> Tasks </h2>

<h3> Part a) Classify Spiders </h3>

Implement the function `classify` in `Node` and its subclasses. The methods take as input a `Spider` and, by following the decision tree until a terminal node is reached, is able to classify the spider.
<br><br>

<h3> Part b) Stringify the Decision Node </h3>

Implement the function `DecisionNode.toString`, which outputs a well-formatted `String` representation of a given decision tree, with the following template format:

```
Is black?
| Yes: Has coloured area?
| | Yes: Red-back
| | No: Huntsman
| No: Has coloured area?
| | Yes: Peacock
| | No: Daddy long-legs
```

Every aspect of your output must match the above specification, including the indenting and punctuation. You may wish to reference `Spider.propertyToString` and `TerminalNode.outcome` to generate parts of the output.

Since this method is implemented in the class `DecisionNode`, you can assume that the tree contains at least one decision. Of course, the terminal nodes must also be included in your output.
<br><br>

<h3> Part c) Classifier Factory </h3>

The class `ClassifierFactory` uses the factory design pattern to build decision trees 
that are able to distinguish between a given set of spiders. Implement this with `ClassifierFactory.createClassifier`.

Users must be able to distinguish between spiders quickly and efficiently. 
Therefore, for this part, you will be marked not only on the correctness of the resulting decision tree, 
but also on its height. That is, you should construct the correct classifier that minimises `maximumDecisions`. 
Partial marks will be given for less efficient classifiers.

You may assume that the input to `createClassifier` is a valid non-empty set, 
in which every spider has a different `speciesName`.
<br><br>

- You may find test cases in `ClassificationTests.java` to help with your code development. The marking test cases for this question will be highly similar to that of the ones provided, in terms of the aspects of assessment .


***

#### You are expected to update the following files and push them to GitLab:
* `Node.java`
* `DecisionNode.java`
* `TerminalNode.java`
* `ClassifierFactory.java`
