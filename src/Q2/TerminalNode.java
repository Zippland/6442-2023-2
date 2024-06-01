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

        return null; // TODO replace this line
        // TODO - Your Code END Here
    }

    public String getOutcome(){
        return outcome;
    }



}
