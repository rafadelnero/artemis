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
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.api.column.ColumnQuery;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * The template method to {@link ColumnTemplate}
 */
public abstract class AbstractColumnTemplate implements ColumnTemplate {


    protected abstract ColumnEntityConverter getConverter();

    protected abstract ColumnFamilyManager getManager();

    protected abstract ColumnWorkflow getFlow();

    protected abstract ColumnEventPersistManager getEventManager();


    @Override
    public <T> T save(T entity) throws NullPointerException {
        requireNonNull(entity, "entity is required");
        UnaryOperator<ColumnEntity> save = e -> getManager().save(e);
        return getFlow().flow(entity, save);
    }


    @Override
    public <T> T save(T entity, Duration ttl) {
        requireNonNull(entity, "entity is required");
        requireNonNull(ttl, "ttl is required");
        UnaryOperator<ColumnEntity> save = e -> getManager().save(e, ttl);
        return getFlow().flow(entity, save);
    }


    @Override
    public <T> T update(T entity) {
        requireNonNull(entity, "entity is required");
        UnaryOperator<ColumnEntity> save = e -> getManager().update(e);
        return getFlow().flow(entity, save);
    }


    @Override
    public void delete(ColumnDeleteQuery query) {
        requireNonNull(query, "query is required");
        getEventManager().firePreDeleteQuery(query);
        getManager().delete(query);
    }


    @Override
    public <T> List<T> find(ColumnQuery query) throws NullPointerException {
        requireNonNull(query, "query is required");
        getEventManager().firePreQuery(query);
        List<ColumnEntity> entities = getManager().find(query);
        Function<ColumnEntity, T> function = e -> getConverter().toEntity(e);
        return entities.stream().map(function).collect(Collectors.toList());
    }
}
