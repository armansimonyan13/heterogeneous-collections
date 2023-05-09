package org.example;

public abstract class AbstractField implements Field {

    private final Long id;

    public AbstractField(final Long id) {
        this.id = id;
    }

    @Override
    public final Long id() {
        return id;
    }

}
