package org.example;

public class StringField extends AbstractField {

    public StringField(Long id) {
        super(id);
    }

    @Override
    public void accept(FieldVisitor visitor, Object value) {
        visitor.visit(this, (String) value);
    }

}
