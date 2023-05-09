package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class RecordType {

    private final Long id;

    private final String name;

    private final List<Field> fields;

    private final Map<Long, Field> fieldsByIds;

    public RecordType(Long id, String name, List<Field> fields) {
        this.id = id;
        this.name = name;
        this.fields = fields;

        this.fieldsByIds = fields.stream().collect(Collectors.toMap(Field::id, field -> field));
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Field> fields() {
        return fields;
    }

    public Map<Long, Field> fieldsByIds() {
        return fieldsByIds;
    }

}
