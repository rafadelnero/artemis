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

import org.jnosql.artemis.WeldJUnit4Runner;
import org.jnosql.diana.api.column.ColumnFamilyManagerAsync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(WeldJUnit4Runner.class)
public class DefaultColumnTemplateAsyncProducerTest {


    @Inject
    private ColumnTemplateAsyncProducer producer;


    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenColumnFamilyManagerNull() {
        producer.get(null);
    }

    @Test
    public void shouldReturn() {
        ColumnFamilyManagerAsync manager = Mockito.mock(ColumnFamilyManagerAsync.class);
        ColumnTemplateAsync columnRepository = producer.get(manager);
        assertNotNull(columnRepository);
    }

}