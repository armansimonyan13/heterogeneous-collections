package org.example;

import java.util.Date;

public class DateField extends AbstractField {

    private final String format;

    public DateField(Long id, String format) {
        super(id);
        this.format = format;
    }

    public String format() {
        return format;
    }

    @Override
    public void accept(FieldVisitor visitor, Object value) {
        visitor.visit(this, (Date) value);
    }

}
