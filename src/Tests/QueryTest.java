import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class QueryTest {
    static Set<Triple> triples0 = new HashSet<>(List.of(
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "am"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "the_exam"))
    ));

    static Set<Triple> triples1 = new HashSet<>(List.of(
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://example.org/I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/am"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "alright")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "CECCStudent"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "ex:hasProperty"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "cool")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://example.org/I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/am"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "fine")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://example.org/You"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/are"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "100")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "Mary"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/had_a"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "little_lamp")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "AM"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "alright")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://example.org/I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/love"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "exams")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://example.org/I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "http://xmlns.com/foaf/0.1/am"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "good")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "AM"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "fine")),
            new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://samoyed.au/Penguin"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "is_the"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "cutest_animal_in_the_world"))
    ));


    /**
     * Example 1 - This query is valid. It searches for all triples
     * and return a set of subjects
     */
    @Test(timeout = 500)
    public void parseT0selectAll() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject ?predicate ?object
                }
                """;
        List<QToken> tokens = QTokenizerTest.getAllTokens(q);
        var entitiesFiltered = QueryParser.parse(tokens, triples0);
        assertEquals(1, entitiesFiltered.size());
        assertEquals("[[<SUBJECT>] I]", entitiesFiltered.toString());
    }

    /**
     * Example 2 - This query is valid. It searches for all triples
     * and return a set of subjects which has "I" in the subject
     *
     * Note that it could match a Subject with a valueType of `ValueType.String` (and value.content of "I"); or
     * a Subject with a valueType of `ValueType.URI` (and value.content in the form of "xxx/.../xxx/I")
     */
    @Test(timeout = 500)
    public void parseT0selectMatchS() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  I ?predicate ?object
                }
                """;

        List<QToken> tokens = QTokenizerTest.getAllTokens(q);
        var entitiesFiltered = QueryParser.parse(tokens, triples0);
        assertEquals(1, entitiesFiltered.size());
        assertEquals("[[<SUBJECT>] I]", entitiesFiltered.toString());
    }

    /**
     * Example 3 - This is invalid since it does not comply with the CFG.
     */
    @Test(timeout = 500)
    public void parseInvalidMultiplePredicates() {
        String q =
                """
                SELECT triple
                WHERE {
                  ?subject ex:hasProperty1 ?object1
                           ex:hasProperty2 ?object2
                }
                """;

        List<QToken> tokens = QTokenizerTest.getAllTokens(q);
        var entitiesFiltered = QueryParser.parse(tokens, triples1);
        assertNull(entitiesFiltered);
    }

    @Test(timeout = 500)
    public void parseT0selectMatchP() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject am ?object
                }
                """;

        List<QToken> tokens = QTokenizerTest.getAllTokens(q);
        var entitiesFiltered = QueryParser.parse(tokens, triples0);
        assertEquals(1, entitiesFiltered.size());
        assertEquals("[[<SUBJECT>] I]", entitiesFiltered.toString());
    }

    @Test(timeout = 500)
    public void parseT1selectAll() {
        String q =
                """
                SELECT ?subject
                WHERE {
                  ?subject ?predicate ?object
                }
                """;

        List<QToken> tokens = QTokenizerTest.getAllTokens(q);
        var entitiesFiltered = QueryParser.parse(tokens, triples1);
        assertEquals(6, entitiesFiltered.size());
        assertStrsEqual("[[<SUBJECT>] CECCStudent, [<SUBJECT>] I, [<SUBJECT>] Mary, [<SUBJECT>] http://example.org/I, [<SUBJECT>] http://example.org/You, [<SUBJECT>] http://samoyed.au/Penguin]", entitiesFiltered);
    }


    Comparator<Object> objectComparator = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            String s1 = o1.toString();
            String s2 = o2.toString();
            return s1.compareTo(s2);
        }
    };

    public void assertStrsEqual(String expected, Set<Object> actualSet) {
        List<Object> actualList = new ArrayList<>(actualSet);
        actualList.sort(objectComparator);
        assertEquals(expected, actualList.toString());
    }
}
