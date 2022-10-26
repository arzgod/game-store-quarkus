package com.darkstudios.samples.services;

import com.darkstudios.samples.entities.Games;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

public class GameService {
    @Inject
    EntityManager em;

    public List<Games> getAll() {
        List<Games> games = em.createNamedQuery("Games.findAll", Games.class)
                .getResultList();
        return games != null ? games : new ArrayList<>();
    }
    public Games findById(Long id) {
        return em.find(Games.class, id);
    }
    @Transactional
    public void update(Games games) {
        em.merge(games);
    }
    @Transactional
    public void create(Games games) {
        em.persist(games);
    }
    @Transactional
    public void delete(Games games) {
        if (!em.contains(games)) {
            games = em.merge(games);
        }
        em.remove(games);
    }
}
