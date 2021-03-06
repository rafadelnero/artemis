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
package org.jnosql.artemis.column.query;


import org.jnosql.artemis.Repository;
import org.jnosql.artemis.column.ColumnTemplate;
import org.jnosql.artemis.reflection.ClassRepresentation;
import org.jnosql.artemis.reflection.ClassRepresentations;

import java.lang.reflect.ParameterizedType;


/**
 * Proxy handle to generate {@link Repository}
 *
 * @param <T> the type
 */
class ColumnRepositoryProxy<T> extends AbstractColumnRepositoryProxy {

    private final Class<T> typeClass;

    private final ColumnTemplate template;

    private final ColumnRepository repository;

    private final ClassRepresentation classRepresentation;

    private final ColumnQueryParser queryParser;

    private final ColumnQueryDeleteParser deleteParser;


    ColumnRepositoryProxy(ColumnTemplate template, ClassRepresentations classRepresentations, Class<?> repositoryType) {
        this.template = template;
        this.repository = new ColumnRepository(template);
        this.typeClass = Class.class.cast(ParameterizedType.class.cast(repositoryType.getGenericInterfaces()[0])
                .getActualTypeArguments()[0]);
        this.classRepresentation = classRepresentations.get(typeClass);
        this.queryParser = new ColumnQueryParser();
        this.deleteParser = new ColumnQueryDeleteParser();
    }

    @Override
    protected Repository getRepository() {
        return repository;
    }

    @Override
    protected ClassRepresentation getClassRepresentation() {
        return classRepresentation;
    }

    @Override
    protected ColumnQueryParser getQueryParser() {
        return queryParser;
    }

    @Override
    protected ColumnQueryDeleteParser getDeleteParser() {
        return deleteParser;
    }

    @Override
    protected ColumnTemplate getTemplate() {
        return template;
    }


    class ColumnRepository extends AbstractColumnRepository implements Repository {

        private final ColumnTemplate template;

        ColumnRepository(ColumnTemplate template) {
            this.template = template;
        }

        @Override
        protected ColumnTemplate getTemplate() {
            return template;
        }
    }
}
