import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


public class TripleReaderTest {

    @Test(timeout = 500)
    public void readTriples0Size() {
        String filePath1 = "src/Q1/assets/triples0.xml";
        Set<Triple> triples = TripleReader.read(filePath1);

        assertNotNull(triples);
        assertEquals(triples.size(), 1);
    }

    @Test(timeout = 500)
    public void readTriples0Content() {
        String filePath1 = "src/Q1/assets/triples0.xml";
        Set<Triple> triples = TripleReader.read(filePath1);
        assertNotNull(triples);
        assertEquals(triples.size(), 1);

        assertEquals(new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "I"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "am"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "the_exam")), triples.iterator().next());
    }

    @Test(timeout = 500)
    public void readTriples1Size() {
        String filePath1 = "src/Q1/assets/triples1.xml";
        Set<Triple> triples = TripleReader.read(filePath1);

        assertNotNull(triples);
        assertEquals(triples.size(), 10);
    }


    @Test(timeout = 500)
    public void readTriples1Content() {
        String filePath1 = "src/Q1/assets/triples1.xml";
        Set<Triple> triples = TripleReader.read(filePath1);
        assertNotNull(triples);

        /*
         *  The content of the Triples read will be tested in this method,
         *  but the test is not provided to you.
         *  Feel free to create your own test for this.
         */

        // An example triple that should be included
        var exampleTriple = new Triple(new TripleComponent(TripleComponent.TripleComponentType.SUBJECT, "http://samoyed.au/Penguin"), new TripleComponent(TripleComponent.TripleComponentType.PREDICATE, "is_the"), new TripleComponent(TripleComponent.TripleComponentType.OBJECT, "cutest_animal_in_the_world"));


    }

}
