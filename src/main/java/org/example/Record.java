package org.example;

import java.util.*;

public class Record {

    private final Long id;

    private final String name;

    private final RecordType recordType;

    private final Map<Long, Object> data;

    public Record(
            Long id,
            String name,
            RecordType recordType
    ) {
        this.id = id;
        this.name = name;
        this.recordType = recordType;
        this.data = new HashMap<>();
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public RecordType recordType() {
        return recordType;
    }

    public Map<Long, Object> data() {
        return data;
    }

    public void putDouble(Long fieldId, Double value) {
        if (!recordType.fieldsByIds().containsKey(fieldId)) {
            throw new IllegalArgumentException("Field does not exist with id: " + fieldId);
        }
        Field field = recordType.fieldsByIds().get(fieldId);
        if (!(field instanceof DoubleField)) {
            throw new IllegalArgumentException("Field is not double, expected: " + field.getClass());
        }
        data.put(fieldId, value);
    }

    public Double getDouble(Long fieldId) {
        return (Double) data.get(fieldId);
    }

    public void putString(Long fieldId, String value) {
        if (!recordType.fieldsByIds().containsKey(fieldId)) {
            throw new IllegalArgumentException("Field does not exist with id: " + fieldId);
        }
        Field field = recordType.fieldsByIds().get(fieldId);
        if (!(field instanceof StringField)) {
            throw new IllegalArgumentException("Field is not string, expected: " + field.getClass());
        }
        data.put(fieldId, value);
    }

    public String getString(Long fieldId) {
        return (String) data.get(fieldId);
    }

    public void accept(FieldVisitor visitor) {
        recordType.fields().forEach(field -> field.accept(visitor, data.get(field.id())));
    }

}
