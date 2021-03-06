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
package org.jnosql.artemis.column;


import org.jnosql.diana.api.column.ColumnDeleteQuery;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnQuery;

/**
 * This interface represent the manager of events. When an entity be either saved or updated an event will be fired. This order gonna be:
 * 1) firePreEntity
 * 2) firePreColumnEntity
 * 3) firePreColumn
 * 4) firePostColumn
 * 5) firePostEntity
 * 6) firePostColumnEntity
 *
 * @see ColumnWorkflow
 */
public interface ColumnEventPersistManager {

    /**
     * Fire an event after the conversion of the entity to communication API model.
     *
     * @param entity the entity
     */
    void firePreColumn(ColumnEntity entity);

    /**
     * Fire an event after the response from communication layer
     *
     * @param entity the entity
     */
    void firePostColumn(ColumnEntity entity);

    /**
     * Fire an event once the method is called
     *
     * @param entity the entity
     * @param <T>    the entity type
     */
    <T> void firePreEntity(T entity);

    /**
     * Fire an event after convert the {@link org.jnosql.diana.api.column.ColumnEntity},
     * from database response, to Entity.
     *
     * @param entity the entity
     * @param <T>    the entity kind
     */
    <T> void firePostEntity(T entity);


    /**
     * Fire an event once the method is called after firePreEntity
     *
     * @param entity the entity
     * @param <T>    the entity type
     */
    <T> void firePreColumnEntity(T entity);

    /**
     * Fire an event after firePostEntity
     *
     * @param entity the entity
     * @param <T>    the entity kind
     */
    <T> void firePostColumnEntity(T entity);

    /**
     * Fire an event before the query is executed
     *
     * @param query the query
     */
    void firePreQuery(ColumnQuery query);

    /**
     * Fire an event before the delete query is executed
     *
     * @param query the query
     */
    void firePreDeleteQuery(ColumnDeleteQuery query);
}
