/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InTipoDepartamento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author admin1
 */
public class InTipoDepartamentoJpaController implements Serializable {

    public InTipoDepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoDepartamento inTipoDepartamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inTipoDepartamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoDepartamento inTipoDepartamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inTipoDepartamento = em.merge(inTipoDepartamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoDepartamento.getIdTipoDepartamento();
                if (findInTipoDepartamento(id) == null) {
                    throw new NonexistentEntityException("The inTipoDepartamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoDepartamento inTipoDepartamento;
            try {
                inTipoDepartamento = em.getReference(InTipoDepartamento.class, id);
                inTipoDepartamento.getIdTipoDepartamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoDepartamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(inTipoDepartamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoDepartamento> findInTipoDepartamentoEntities() {
        return findInTipoDepartamentoEntities(true, -1, -1);
    }

    public List<InTipoDepartamento> findInTipoDepartamentoEntities(int maxResults, int firstResult) {
        return findInTipoDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<InTipoDepartamento> findInTipoDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoDepartamento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public InTipoDepartamento findInTipoDepartamento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoDepartamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoDepartamento> rt = cq.from(InTipoDepartamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
