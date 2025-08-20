package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.domain.Campeonato;
import org.example.domain.Time;
import org.example.factory.ConnectionFactory;

import java.util.List;

public class CampeonatoService {

    private final EntityManager em;

    public CampeonatoService() {
        this.em = new ConnectionFactory().getEntityManager();
    }

    public void save(Campeonato campeonato) {
        try {
            em.getTransaction().begin();
            em.persist(campeonato);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Campeonato update(Campeonato campeonato) {
        try {
            em.getTransaction().begin();
            Campeonato updated = em.merge(campeonato);
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
            Campeonato campeonato = em.find(Campeonato.class, id);
            if (campeonato != null) {
                em.remove(campeonato);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Campeonato findById(Long id) {
        return em.find(Campeonato.class, id);
    }

    public List<Campeonato> findAll() {
        TypedQuery<Campeonato> query = em.createQuery("SELECT c FROM Campeonato c", Campeonato.class);
        return query.getResultList();
    }

    public List<Time> findTimesByNome(String nomeCampeonato) {
        TypedQuery<Time> query = em.createQuery(
                "SELECT t FROM Campeonato c JOIN c.times t WHERE c.nome = :nomeCampeonato", Time.class
        );
        query.setParameter("nomeCampeonato", nomeCampeonato);
        return query.getResultList();
    }
}
