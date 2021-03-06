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
package org.jnosql.artemis;

import org.junit.Test;

import static org.jnosql.artemis.DatabaseType.COLUMN;
import static org.jnosql.artemis.DatabaseType.DOCUMENT;
import static org.jnosql.artemis.DatabaseType.KEY_VALUE;
import static org.junit.Assert.assertEquals;


public class DatabaseQualifierTest {

    @Test
    public void shouldReturnDefaultColumn() {
        DatabaseQualifier qualifier = DatabaseQualifier.ofColumn();
        assertEquals("", qualifier.provider());
        assertEquals(COLUMN, qualifier.value());
    }

    @Test
    public void shouldReturnColumnProvider() {
        String provider = "provider";
        DatabaseQualifier qualifier = DatabaseQualifier.ofColumn(provider);
        assertEquals(provider, qualifier.provider());
        assertEquals(COLUMN, qualifier.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenColumnNull() {
        DatabaseQualifier.ofColumn(null);
    }

    @Test
    public void shouldReturnDefaultDocument() {
        DatabaseQualifier qualifier = DatabaseQualifier.ofDocument();
        assertEquals("", qualifier.provider());
        assertEquals(DOCUMENT, qualifier.value());
    }

    @Test
    public void shouldReturnDocumentProvider() {
        String provider = "provider";
        DatabaseQualifier qualifier = DatabaseQualifier.ofDocument(provider);
        assertEquals(provider, qualifier.provider());
        assertEquals(DOCUMENT, qualifier.value());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenDocumentNull() {
        DatabaseQualifier.ofDocument(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnErrorWhenKeyValueNull() {
        DatabaseQualifier.ofKeyValue(null);
    }

    @Test
    public void shouldReturnKeyValueProvider() {
        String provider = "provider";
        DatabaseQualifier qualifier = DatabaseQualifier.ofKeyValue(provider);
        assertEquals(provider, qualifier.provider());
        assertEquals(KEY_VALUE, qualifier.value());
    }

    @Test
    public void shouldReturnDefaultKeyValue() {
        DatabaseQualifier qualifier = DatabaseQualifier.ofKeyValue();
        assertEquals("", qualifier.provider());
        assertEquals(KEY_VALUE, qualifier.value());
    }
}