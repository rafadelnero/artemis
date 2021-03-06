/*
 * Copyright 2017 Otavio Santana and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jnosql.artemis.reflection;

import org.jnosql.artemis.AttributeConverter;
import org.jnosql.diana.api.TypeSupplier;

import java.lang.reflect.Field;

class FieldRepresentationBuilder {

    private FieldType type;

    private Field field;

    private String name;

    private String entityName;

    private TypeSupplier<?> typeSupplier;

    private Class<? extends AttributeConverter> converter;

    public FieldRepresentationBuilder withType(FieldType type) {
        this.type = type;
        return this;
    }

    public FieldRepresentationBuilder withField(Field field) {
        this.field = field;
        return this;
    }

    public FieldRepresentationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FieldRepresentationBuilder withTypeSupplier(TypeSupplier<?> typeSupplier) {
        this.typeSupplier = typeSupplier;
        return this;
    }

    public FieldRepresentationBuilder withEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public FieldRepresentationBuilder withConverter(Class<? extends AttributeConverter> converter) {
        this.converter = converter;
        return this;
    }

    public DefaultFieldRepresentation buildDefault() {
        return new DefaultFieldRepresentation(type, field, name, converter);
    }

    public GenericFieldRepresentation buildGeneric() {
        return new GenericFieldRepresentation(type, field, name, typeSupplier, converter);
    }

    public EmbeddedFieldRepresentation buildEmedded() {
        return new EmbeddedFieldRepresentation(type, field, name, entityName);
    }

}
