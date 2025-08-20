package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Status;
import org.example.domain.Jogador;
import org.example.domain.Time;
import org.example.factory.ConnectionFactory;

import java.util.List;

public class JogadorService {

    private final EntityManager em;

    public JogadorService() {
        this.em = new ConnectionFactory().getEntityManager();
    }

    public void save(Jogador jogador) {
        try {
            em.getTransaction().begin();
            em.persist(jogador);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Jogador update(Jogador jogador) {
        try {
            em.getTransaction().begin();
            Jogador updated = em.merge(jogador);
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
            Jogador jogador = em.find(Jogador.class, id);
            if (jogador != null) {
                em.remove(jogador);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Jogador findById(Long id) {
        return em.find(Jogador.class, id);
    }

    public List<Jogador> findAll() {
        TypedQuery<Jogador> query = em.createQuery("select j from Jogador j", Jogador.class);
        return query.getResultList();
    }

    public Jogador findByNome(String nome) {
        TypedQuery<Jogador> query = em.createQuery(
                "SELECT j FROM Jogador j WHERE j.nome = :nome", Jogador.class
        );
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    public List<Jogador> findByStatus(Status status) {
        TypedQuery<Jogador> query = em.createQuery(
                "SELECT j FROM Jogador j WHERE j.status = :status", Jogador.class
        );
        query.setParameter("status", status);
        return query.getResultList();
    }

    public  List<Jogador> findByTime(String nomeTime) {
        TypedQuery<Jogador> query = em.createQuery(
                "SELECT j FROM Jogador j WHERE j.time = :nomeTime", Jogador.class
        );
        query.setParameter("nomeTime", nomeTime);
        return query.getResultList();
    }
}
