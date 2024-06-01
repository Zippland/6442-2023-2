import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QTokenizerTest {

    /**
     *  Helper Method, used to get all tokens from the buffer string.
     */
    public static List<QToken> getAllTokens(String stringBuffer) {
        QueryTokenizer qt = new QueryTokenizer(stringBuffer);

        QToken selectToken = qt.getSelectTokenIfValid();
        if (selectToken==null) return null;

        boolean whereValid = qt.whereIsValid();
        if (!whereValid) return null;
        List<QToken> tokens = new ArrayList<>(List.of(selectToken));
        while (qt.hasNext()) {
            QToken token = qt.next();
            tokens.add(token);
            if (token==null) return null;
        }
        return tokens;
    }


    @Test(timeout = 500)
    public void tokenizeT0selectAll() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject ?predicate ?object
                }
                """;

        List<QToken> tokens = getAllTokens(q);
        assertEquals("[[SELECT] <SUBJECT>, [WHERE_ANY] <SUBJECT>, [WHERE_ANY] <PREDICATE>, [WHERE_ANY] <OBJECT>]", tokens.toString());
    }


    @Test(timeout = 500)
    public void tokenizeT0selectMatchS() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  I ?predicate ?object
                }
                """;

        List<QToken> tokens = getAllTokens(q);
        assertEquals("[[SELECT] <SUBJECT>, [WHERE_MATCH] I, [WHERE_ANY] <PREDICATE>, [WHERE_ANY] <OBJECT>]", tokens.toString());
    }

    @Test(timeout = 500)
    public void tokenizeT0selectMatchP() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject am ?object
                }
                """;

        List<QToken> tokens = getAllTokens(q);
        assertEquals("[[SELECT] <SUBJECT>, [WHERE_ANY] <SUBJECT>, [WHERE_MATCH] am, [WHERE_ANY] <OBJECT>]", tokens.toString());
    }

    @Test
    public void tokenizeT1DoubleSubject() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject ?subject
                }
                """;

        List<QToken> tokens = getAllTokens(q);
        assertEquals("[[SELECT] <SUBJECT>, [WHERE_ANY] <SUBJECT>, [WHERE_ANY] <SUBJECT>]", tokens.toString());
    }

    /**
     * Note - this query is invalid, but the tokenizer should still return the correct tokens;
     * since the tokenizer should not consider the query's validity,
     */
    @Test(timeout = 500)
    public void tokenizeInvalidOatS() {
        String q =
                """
                SELECT triple
                WHERE {
                  ?object I am
                }
                """;

        List<QToken> tokens = getAllTokens(q);
        assertEquals("[[SELECT] <TRIPLE>, [WHERE_ANY] <OBJECT>, [WHERE_MATCH] I, [WHERE_MATCH] am]", tokens.toString());
    }

}
