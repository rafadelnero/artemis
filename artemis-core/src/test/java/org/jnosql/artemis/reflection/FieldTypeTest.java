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

import org.jnosql.artemis.model.Actor;
import org.jnosql.artemis.model.Director;
import org.jnosql.artemis.model.Movie;
import org.jnosql.artemis.model.Person;
import org.jnosql.artemis.model.Worker;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;


public class FieldTypeTest {


    @Test
    public void shouldReturnList() throws NoSuchFieldException {
        Field field = Person.class.getDeclaredField("phones");
        Assert.assertEquals(FieldType.COLLECTION, FieldType.of(field));
    }

    @Test
    public void shouldReturnSet() throws NoSuchFieldException {
        Field field = Movie.class.getDeclaredField("actors");
        Assert.assertEquals(FieldType.COLLECTION, FieldType.of(field));
    }

    @Test
    public void shouldReturnMap() throws NoSuchFieldException {
        Field field = Actor.class.getDeclaredField("movieCharacter");
        Assert.assertEquals(FieldType.MAP, FieldType.of(field));
    }

    @Test
    public void shouldReturnDefault() throws NoSuchFieldException {
        Field field = Person.class.getDeclaredField("name");
        Assert.assertEquals(FieldType.DEFAULT, FieldType.of(field));
    }


    @Test
    public void shouldReturnEmbeddedAsEntity() throws NoSuchFieldException{
        Field field = Director.class.getDeclaredField("movie");
        Assert.assertEquals(FieldType.EMBEDDED, FieldType.of(field));
    }


    @Test
    public void shouldReturnEmbedded() throws NoSuchFieldException{
        Field field = Worker.class.getDeclaredField("job");
        Assert.assertEquals(FieldType.EMBEDDED, FieldType.of(field));
    }

}