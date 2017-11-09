package org.taskqbtr.lists.repository;

import org.taskqbtr.lists.model.List;

import javax.persistence.EntityManager;

public class ListRepository {

    EntityManager em;

    public List addList(final List list) {
        em.persist(list);
        return list;
    }

    public List findById(final long id) {
        return em.find(List.class, id);
    }
}
