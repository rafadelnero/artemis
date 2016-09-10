/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jnosql.artemis.document;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.jnosql.artemis.EntityPostPersit;
import org.jnosql.artemis.EntityPrePersist;
import org.jnosql.diana.api.document.DocumentCollectionEntity;

/**
 * The default implementation of {@link DocumentPersistManager}
 */
@ApplicationScoped
class DefaultDocumentPersistManager implements DocumentPersistManager {

    @Inject
    private Event<DocumentEntityPrePersist> documentEntityPrePersistEvent;

    @Inject
    private Event<DocumentEntityPostPersist> documentEntityPostPersistEvent;

    @Inject
    private Event<EntityPrePersist> entityPrePersistEvent;

    @Inject
    private Event<EntityPostPersit> entityPostPersitEvent;

    @Override
    public void firePreDocument(DocumentCollectionEntity entity) {
        documentEntityPrePersistEvent.fire(DocumentEntityPrePersist.of(entity));
    }

    @Override
    public void firePostDocument(DocumentCollectionEntity entity) {
        documentEntityPostPersistEvent.fire(DocumentEntityPostPersist.of(entity));
    }

    @Override
    public <T> void firePreEntity(T entity) {
        entityPrePersistEvent.fire(EntityPrePersist.of(entity));
    }

    @Override
    public <T> void firePostEntity(T entity) {
        entityPostPersitEvent.fire(EntityPostPersit.of(entity));
    }
}
