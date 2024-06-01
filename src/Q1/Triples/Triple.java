import java.util.Objects;

public class Triple {
    TripleComponent subject;
    TripleComponent predicate;
    TripleComponent object;
    public Triple(TripleComponent subject, TripleComponent predicate, TripleComponent object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public TripleComponent getSubject() {
        return subject;
    }

    public TripleComponent getPredicate() {
        return predicate;
    }

    public TripleComponent getObject() {
        return object;
    }

    public String toString() {
        return String.format("%s %s %s", this.subject.getValue(), this.predicate.getValue(), this.object.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple triple = (Triple) o;
        return subject.equals(triple.subject) &&
                predicate.equals(triple.predicate) &&
                object.equals(triple.object);
    }


    @Override
    public int hashCode() {
        return Objects.hash(subject, predicate, object);
    }
}
