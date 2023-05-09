package org.example;

public class DoubleField extends AbstractField {

    private final int precision;

    public DoubleField(Long id, int precision) {
        super(id);
        this.precision = precision;
    }

    public int precision() {
        return precision;
    }

    @Override
    public void accept(FieldVisitor visitor, Object value) {
        visitor.visit(this, (Double) value);
    }

}
