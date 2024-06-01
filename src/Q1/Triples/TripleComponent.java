import java.util.Objects;

public class TripleComponent {

    public enum TripleComponentType {
        SUBJECT, OBJECT, PREDICATE, TRIPLE;

        public String toString() {
            return "<"+name()+">";
        }
    }

    TripleComponentType tripleComponentType;
    String value;

    public TripleComponent(TripleComponentType tripleComponentType, String value) {
        this.tripleComponentType = tripleComponentType;
        this.value = value;
    }

    public TripleComponent(String value) {
        this.value = value;
    }

    public void setTripleComponentType(TripleComponentType tripleComponentType) {
        this.tripleComponentType = tripleComponentType;
    }


    @Override
    public String toString() {
        String entityTypeString = "[UNK]";
        if (this.tripleComponentType !=null) {
            entityTypeString = String.format("[%s]", this.tripleComponentType);
        }
        return String.format("%s %s", entityTypeString, this.value);
    }


    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TripleComponent o = (TripleComponent) other;
        return this.toString().equals(o.toString());
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.tripleComponentType, this.value);
    }

}
