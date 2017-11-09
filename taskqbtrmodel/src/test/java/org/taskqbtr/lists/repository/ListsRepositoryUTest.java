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
