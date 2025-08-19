package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.domain.Campeonato;
import org.example.domain.Time;
import org.example.factory.ConnectionFactory;

import java.util.List;

public class TimeService {

    private final EntityManager em;

    public TimeService() {
        this.em = new ConnectionFactory().getEntityManager();
    }

    public void save(Time time) {
        try {
            em.getTransaction().begin();
            em.persist(time);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Time update(Time time) {
        try {
            em.getTransaction().begin();
            Time updated = em.merge(time);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            Time time = em.find(Time.class, id);
            if (time != null) {
                em.remove(time);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Time findById(Long id) {
        return em.find(Time.class, id);
    }

    public List<Time> findAll() {
        TypedQuery<Time> query = em.createQuery("SELECT t FROM Time t", Time.class);
        return query.getResultList();
    }


    public Time findByNome(String nome) {
        TypedQuery<Time> query = em.createNamedQuery("Time.findByNome", Time.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    public Time findByJogadorNome(String nomeJogador) {
        TypedQuery<Time> query = em.createNamedQuery("Time.findByJogadorNome", Time.class);
        query.setParameter("nomeJogador", nomeJogador);
        return query.getSingleResult();
    }

    public List<Campeonato> findCampeonatos(Long timeId) {
        TypedQuery<Campeonato> query = em.createNamedQuery("Time.findCampeonatos", Campeonato.class);
        query.setParameter("timeId", timeId);
        return query.getResultList();
    }
}
