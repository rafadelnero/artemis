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
package org.jnosql.artemis.document;

import org.jnosql.diana.api.document.DocumentCollectionManager;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * The default implementation of {@link DocumentTemplate}
 */
@SuppressWarnings("unchecked")
class DefaultDocumentTemplate extends AbstractDocumentTemplate {


    private DocumentEntityConverter converter;

    private Instance<DocumentCollectionManager> manager;

    private DocumentWorkflow workflow;

    private DocumentEventPersistManager persistManager;


    @Inject
    DefaultDocumentTemplate(DocumentEntityConverter converter, Instance<DocumentCollectionManager> manager,
                            DocumentWorkflow workflow, DocumentEventPersistManager persistManager) {
        this.converter = converter;
        this.manager = manager;
        this.workflow = workflow;
        this.persistManager = persistManager;
    }

    DefaultDocumentTemplate() {
    }

    @Override
    protected DocumentEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected DocumentCollectionManager getManager() {
        return manager.get();
    }

    @Override
    protected DocumentWorkflow getWorkflow() {
        return workflow;
    }

    @Override
    protected DocumentEventPersistManager getPersistManager() {
        return persistManager;
    }
}
