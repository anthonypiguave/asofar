/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InBodegaPK;
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
public class InBodegaJpaController implements Serializable {

    public InBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InBodega inBodega) throws PreexistingEntityException, Exception {
        if (inBodega.getInBodegaPK() == null) {
            inBodega.setInBodegaPK(new InBodegaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inBodega);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInBodega(inBodega.getInBodegaPK()) != null) {
                throw new PreexistingEntityException("InBodega " + inBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InBodega inBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inBodega = em.merge(inBodega);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InBodegaPK id = inBodega.getInBodegaPK();
                if (findInBodega(id) == null) {
                    throw new NonexistentEntityException("The inBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega inBodega;
            try {
                inBodega = em.getReference(InBodega.class, id);
                inBodega.getInBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inBodega with id " + id + " no longer exists.", enfe);
            }
            em.remove(inBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InBodega> findInBodegaEntities() {
        return findInBodegaEntities(true, -1, -1);
    }

    public List<InBodega> findInBodegaEntities(int maxResults, int firstResult) {
        return findInBodegaEntities(false, maxResults, firstResult);
    }

    private List<InBodega> findInBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InBodega.class));
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

    public InBodega findInBodega(InBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getInBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InBodega> rt = cq.from(InBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
