public class QToken {

    QTokenType qTokenType;
    TripleComponent.TripleComponentType elemType;
    String value;


    public QToken(QTokenType qTokenType, TripleComponent.TripleComponentType elemType) {
        this.qTokenType = qTokenType;
        this.elemType = elemType;
    }

    public QToken(String value) {
        this.qTokenType = QTokenType.WHERE_MATCH;
        this.value = value;
    }

    public QTokenType getqTokenType() {
        return qTokenType;
    }

    public TripleComponent.TripleComponentType getElemType() {
        return elemType;
    }

    public String getValue() {
        return value;
    }

    public void setQTokenType(QTokenType qTokenType) {
        this.qTokenType = qTokenType;
    }

    public void setElemType(TripleComponent.TripleComponentType elemType) {
        this.elemType = elemType;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String valueString = "";
        if (this.value!=null) valueString = this.value.toString();
        String elemTypeStr = (this.elemType != null) ? this.elemType + " " : "";
        return String.format("[%s] %s%s", this.qTokenType, elemTypeStr, valueString).trim();
    }
}