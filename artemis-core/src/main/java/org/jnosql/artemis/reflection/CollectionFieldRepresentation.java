package org.jnosql.artemis.reflection;


import java.lang.reflect.Field;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CollectionFieldRepresentation extends AbstractFieldRepresentation {

    private final Class valueClass;

    CollectionFieldRepresentation(FieldType type, Field field, String name, Class valueClass) {
        super(type, field, name);
        this.valueClass = valueClass;
    }

    public Class getValueClass() {
        return valueClass;
    }

    @Override
    public <T extends FieldRepresentation> T cast(FieldType type) throws IllegalStateException {
        if (FieldType.COLLECTION.equals(type)) {
            return (T) this;
        }
        throw new IllegalStateException("The CollectionFieldRepresentation just can convert to type Collection");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionFieldRepresentation that = (CollectionFieldRepresentation) o;
        return type == that.type &&
                Objects.equals(field, that.field) &&
                Objects.equals(valueClass, that.valueClass) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, field, name, valueClass);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("field", field)
                .append("name", name)
                .append("valueClass", valueClass)
                .toString();
    }
}
