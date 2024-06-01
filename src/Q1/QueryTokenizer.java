public class QueryTokenizer {
    String buffer;

    public QueryTokenizer(String buffer) {
        this.buffer = buffer;
    }

    public boolean hasNext() {
        return this.buffer.trim().length() > 0;
    }


    /**
     *  Question 1(b). Returns the SELECT Token if a valid SELECT statement was found, or null otherwise.
     *  <p>
     *  You may also update the buffer to get prepared for the subsequent tokenization tasks.
     *      The updates do not need to be precise as long as it does not wrongly remove information
     *      (i.e., you could choose to not update the buffer here too)
     */
    public QToken getSelectTokenIfValid() {
        // TODO - Your Code Starts Here (Q1b)

        return null; // TODO replace this line
        // TODO - Your Code END Here
    }


    /**
     *  Question 1(c). Determine whether the WHERE statement for search is valid.
     *  <p>
     *  You may also update the buffer to get prepared for the subsequent tokenization tasks.
     *      The updates do not need to be precise as long as it does not wrongly remove information
     *      (i.e., you could choose to not update the buffer here too)
     */
    public boolean whereIsValid() {
        // TODO - Your Code Starts Here (Q1c)


        return false; // TODO replace this line
        // TODO - Your Code END Here
    }


    /**
     *  Question 1(d). Return the next (WHERE_*) QToken for search.
     */
    public QToken next() {

        return null; // TODO replace this line
        // TODO - Your Code END Here
    }





}
