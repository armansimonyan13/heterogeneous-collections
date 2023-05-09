package org.example;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    @Test
    void test() {
        // arrange
        final Long doubleField0Id = 100L;
        final Long doubleField1Id = 101L;
        final Long stringField0Id = 200L;
        Record record = new Record(
                1L,
                "record_name",
                new RecordType(
                        300L,
                        "record_type_name",
                        List.of(
                                new DoubleField(doubleField0Id, 2),
                                new DoubleField(doubleField1Id, 3),
                                new StringField(stringField0Id, List.of())
                        )
                )
        );
        record.putDouble(doubleField0Id, 3.123456);
        record.putDouble(doubleField1Id, 5.123456);
        record.putString(stringField0Id, "hello world");
        // assert
        assertEquals("record_name", record.name());
        assertEquals(record.getDouble(doubleField0Id), 3.123456);
        assertEquals(record.getDouble(doubleField1Id), 5.123456);
        assertEquals(record.getString(stringField0Id), "hello world");

        record.accept(new FieldVisitor() {
            @Override
            public void visit(DoubleField field, Double value) {
                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setMaximumFractionDigits(field.precision());
                System.out.println(decimalFormat.format(value));
            }
        });
    }

}
