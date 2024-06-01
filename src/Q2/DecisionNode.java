public class DecisionNode extends Node {
    private final Spider.Property testsFor;
    private final Node yesOutcome, noOutcome;
    public DecisionNode(Spider.Property testsFor, Node yesOutcome, Node noOutcome) {
        this.testsFor = testsFor;
        this.yesOutcome = yesOutcome;
        this.noOutcome = noOutcome;
    }

    @Override
    public int maximumDecisions() {
        return Math.max(yesOutcome.maximumDecisions(), noOutcome.maximumDecisions()) + 1;
    }

    @Override
    public String classify(Spider spider) {
        // TODO - Your Code Starts Here (Q2a)
        // TODO - Note: you may implement this function and/or classify in the superclass

        return null; // TODO replace this line
        // TODO - Your Code END Here
    }


    @Override
    public String toString() {
        // TODO - Your Code Starts Here (Q2b)
        // You may create helper functions in any of the Node classes
        // You may wish to use the method Spider.propertyToString

        return null; // TODO replace this line
        // TODO - Your Code END Here
    }




}