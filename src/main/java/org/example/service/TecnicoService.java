package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.domain.Tecnico;
import org.example.domain.Time;
import org.example.factory.ConnectionFactory;

import java.util.List;

public class TecnicoService {

    private final EntityManager em;

    public TecnicoService() {
        this.em = new ConnectionFactory().getEntityManager();
    }

    public void save(Tecnico tecnico) {
        try {
            em.getTransaction().begin();
            em.persist(tecnico);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Tecnico update(Tecnico tecnico) {
        try {
            em.getTransaction().begin();
            Tecnico updated = em.merge(tecnico);
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
            Tecnico tecnico = em.find(Tecnico.class, id);
            if (tecnico != null) {
                em.remove(tecnico);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Tecnico findById(Long id) {
        return em.find(Tecnico.class, id);
    }

    public List<Tecnico> findAll() {
        TypedQuery<Tecnico> query = em.createQuery("SELECT t FROM Tecnico t", Tecnico.class);
        return query.getResultList();
    }

    public Time findTimeByTecnicoNome(String nomeTecnico) {
        TypedQuery<Time> query = em.createQuery(
                "SELECT t FROM Time  t JOIN t.tecnico tec " +
                        "LEFT JOIN FETCH t.jogadores " +
                        "WHERE tec.nome = :nomeTecnico", Time.class
        );
        query.setParameter("nomeTecnico", nomeTecnico);
        return query.getSingleResult();
    }
}
