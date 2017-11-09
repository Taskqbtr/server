/*
 * MIT License
 *
 * Copyright (c) 2017 Taskqbtr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.taskqbtr.lists.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.taskqbtr.lists.model.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.taskqbtr.lists.common.ListsForTestsRepository.inbox;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ListsRepositoryUTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private ListRepository listRepository;

    @Before
    public void initTestCase() {
        emf = Persistence.createEntityManagerFactory("listsPU");
        em = emf.createEntityManager();

        listRepository = new ListRepository();
        listRepository.em = em;
    }

    @After
    public void closeEntityManager() {
        em.close();
        emf.close();
    }

    @Test
    public void addListAndFindIt() {
        Long listAddedId = null;
        try {
            em.getTransaction().begin();

            listAddedId = listRepository.addList(inbox()).getId();

            assertThat(listAddedId, is(notNullValue()));
            em.getTransaction().commit();
            em.clear();
        } catch (final Exception e) {
            fail("This Exceptions should not have been thrown!");
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        final List list = listRepository.findById(listAddedId);
        assertThat(list, is(notNullValue()));
        assertThat(list.getName(), is(equalTo(inbox().getName())));
    }
}
