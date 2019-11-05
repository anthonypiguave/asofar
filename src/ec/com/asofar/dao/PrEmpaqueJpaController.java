/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrEmpaque;
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
 * @author jorge
 */
public class PrEmpaqueJpaController implements Serializable {

    public PrEmpaqueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrEmpaque prEmpaque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prEmpaque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrEmpaque prEmpaque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prEmpaque = em.merge(prEmpaque);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prEmpaque.getId();
                if (findPrEmpaque(id) == null) {
                    throw new NonexistentEntityException("The prEmpaque with id " + id + " no longer exists.");
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
            PrEmpaque prEmpaque;
            try {
                prEmpaque = em.getReference(PrEmpaque.class, id);
                prEmpaque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prEmpaque with id " + id + " no longer exists.", enfe);
            }
            em.remove(prEmpaque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrEmpaque> findPrEmpaqueEntities() {
        return findPrEmpaqueEntities(true, -1, -1);
    }

    public List<PrEmpaque> findPrEmpaqueEntities(int maxResults, int firstResult) {
        return findPrEmpaqueEntities(false, maxResults, firstResult);
    }

    private List<PrEmpaque> findPrEmpaqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrEmpaque.class));
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

    public PrEmpaque findPrEmpaque(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrEmpaque.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrEmpaqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrEmpaque> rt = cq.from(PrEmpaque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
