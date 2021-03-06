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


import org.jnosql.diana.api.column.ColumnFamilyManager;

import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import java.util.Objects;

/**
 * The default implementation of {@link ColumnTemplateProducer}
 */
class DefaultColumnTemplateProducer implements ColumnTemplateProducer {


    @Inject
    private ColumnEntityConverter converter;

    @Inject
    private ColumnWorkflow columnWorkflow;

    @Inject
    private ColumnEventPersistManager eventManager;


    @Override
    public ColumnTemplate get(ColumnFamilyManager columnFamilyManager) throws NullPointerException {
        Objects.requireNonNull(columnFamilyManager, "columnFamilyManager is required");
        return new ProducerColumnTemplate(converter, columnWorkflow, columnFamilyManager, eventManager);
    }


    @Vetoed
    static class ProducerColumnTemplate extends AbstractColumnTemplate {

        private ColumnEntityConverter converter;

        private ColumnWorkflow columnWorkflow;

        private ColumnFamilyManager columnFamilyManager;

        private ColumnEventPersistManager eventManager;

        ProducerColumnTemplate(ColumnEntityConverter converter, ColumnWorkflow columnWorkflow,
                               ColumnFamilyManager columnFamilyManager, ColumnEventPersistManager eventManager) {
            this.converter = converter;
            this.columnWorkflow = columnWorkflow;
            this.columnFamilyManager = columnFamilyManager;
            this.eventManager = eventManager;
        }

        ProducerColumnTemplate() {
        }

        @Override
        protected ColumnEntityConverter getConverter() {
            return converter;
        }

        @Override
        protected ColumnFamilyManager getManager() {
            return columnFamilyManager;
        }

        @Override
        protected ColumnWorkflow getFlow() {
            return columnWorkflow;
        }

        @Override
        protected ColumnEventPersistManager getEventManager() {
            return eventManager;
        }
    }
}
