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
package org.jnosql.artemis.key.spi;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.UserRepository;
import org.jnosql.artemis.WeldJUnit4Runner;
import org.jnosql.artemis.key.KeyValueTemplate;
import org.jnosql.artemis.model.Person;
import org.jnosql.artemis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WeldJUnit4Runner.class)
public class BucketManagerProducerExtensionTest {

    @Inject
    private KeyValueTemplate repository;

    @Inject
    @Database(value = DatabaseType.KEY_VALUE, provider = "keyvalueMock")
    private KeyValueTemplate repositoryMock;

    @Inject
    private UserRepository userRepository;

    @Inject
    @Database(value = DatabaseType.KEY_VALUE)
    private UserRepository userRepositoryDefault;

    @Inject
    @Database(value = DatabaseType.KEY_VALUE, provider = "keyvalueMock")
    private UserRepository userRepositoryMock;

    @Test
    public void shouldPut() {
        Person person = repository.get("key", Person.class).get();

        Person personMock = repositoryMock.get("key", Person.class).get();

        assertEquals("Default", person.getName());
        assertEquals("keyvalueMock", personMock.getName());

    }


    @Test
    public void shouldGet() {
        User user = userRepository.get("user").get();
        User userDefault = userRepositoryDefault.get("user").get();
        User userMock = userRepositoryMock.get("user").get();
        assertEquals("Default", user.getName());
        assertEquals("Default", userDefault.getName());
        assertEquals("keyvalueMock", userMock.getName());
    }

}