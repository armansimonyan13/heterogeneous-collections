package org.example;

import java.util.Date;

public interface FieldVisitor {

    default void visit(DoubleField field, Double value) { }

    default void visit(StringField field, String value) { }

    default void visit(DateField field, Date date) { }

}
