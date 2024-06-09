public class TerminalNode extends Node {
    public final String outcome;
    public TerminalNode(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public int maximumDecisions() {
        return 0;
    }

    @Override
    public String classify(Spider spider) {
        // TODO - Your Code Starts Here (Q2a)
        // TODO - Note: you may implement this function and/or classify in the subclasses
        return outcome;
        // TODO - Your Code END Here
    }

    public String getOutcome(){
        System.out.println("Terminal");
        return outcome;
    }

    @Override
    public String toStringHelp(int level) {
        // TODO - Your Code Starts Here (Q2b)
        // You may create helper functions in any of the Node classes
        // You may wish to use the method Spider.propertyToString
        return outcome+"\n"; // TODO replace this line
        // TODO - Your Code END Here
    }

}
