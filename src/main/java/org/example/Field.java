package org.example;

public interface Field {

    Long id();

    void accept(FieldVisitor visitor, Object value);

}
