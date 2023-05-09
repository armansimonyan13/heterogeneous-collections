# Heterogeneous Collections

## Intention
- use object-oriented approaches.
- make codebase cleaner

## Key refactoring points

### Have different field types defined as Java Classes

- NumberField
- CurrencyField
- DateField
- etc...

### Do not expose 'raw' data of the Record (the internals of the class)

This will make the Record the owner of the 'data' and single point where the data is
validated. We will get rid of multiple static helper methods.

```java
class Demo {
    public static void demo(String[] args) {
        // initialization
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
        // usage
        // put
        record.putDouble(doubleField0Id, 3.123456);
        record.putDouble(doubleField1Id, 5.123456);
        record.putString(stringField0Id, "hello world");
        // get
        record.getDouble(doubleField0Id);
        record.getDouble(doubleField1Id);
        record.getString(stringField0Id);
    }
}
```

### We will adopt visitor pattern to work with Record's data

As we can't get rid of class casts while working with Record's data because of having
heterogeneous map of type Map<String, Object>, we can adopt visitor pattern. This will
help to get rid of class casts spread in entire codebase and be centralized in dedicated
Field classes. The processing of Record's data will go through FieldVisitor's 
overloaded methods.

```java
public class DoubleField extends AbstractField {
    @Override
    public void accept(FieldVisitor visitor, Object value) {
        visitor.visit(this, (Double) value);
    }
}

class Demo {
    public static void demo(String[] args) {
        // Record record = ...;
                
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
```

## Example

See test `RecordTest`;
