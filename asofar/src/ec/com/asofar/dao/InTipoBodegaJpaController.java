/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InTipoBodega;
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
 * @author ADMIN
 */
public class InTipoBodegaJpaController implements Serializable {

    public InTipoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoBodega inTipoBodega) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inTipoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoBodega inTipoBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inTipoBodega = em.merge(inTipoBodega);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoBodega.getIdTipoBodega();
                if (findInTipoBodega(id) == null) {
                    throw new NonexistentEntityException("The inTipoBodega with id " + id + " no longer exists.");
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
            InTipoBodega inTipoBodega;
            try {
                inTipoBodega = em.getReference(InTipoBodega.class, id);
                inTipoBodega.getIdTipoBodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoBodega with id " + id + " no longer exists.", enfe);
            }
            em.remove(inTipoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoBodega> findInTipoBodegaEntities() {
        return findInTipoBodegaEntities(true, -1, -1);
    }

    public List<InTipoBodega> findInTipoBodegaEntities(int maxResults, int firstResult) {
        return findInTipoBodegaEntities(false, maxResults, firstResult);
    }

    private List<InTipoBodega> findInTipoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoBodega.class));
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

    public InTipoBodega findInTipoBodega(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoBodega> rt = cq.from(InTipoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
