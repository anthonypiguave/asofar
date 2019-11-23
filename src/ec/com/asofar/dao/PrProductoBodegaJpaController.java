/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.PrProductoBodega;
import ec.com.asofar.dto.PrProductoBodegaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class PrProductoBodegaJpaController implements Serializable {

    public PrProductoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrProductoBodega prProductoBodega) throws PreexistingEntityException, Exception {
        if (prProductoBodega.getPrProductoBodegaPK() == null) {
            prProductoBodega.setPrProductoBodegaPK(new PrProductoBodegaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega inBodega = prProductoBodega.getInBodega();
            if (inBodega != null) {
                inBodega = em.getReference(inBodega.getClass(), inBodega.getInBodegaPK());
                prProductoBodega.setInBodega(inBodega);
            }
            em.persist(prProductoBodega);
            if (inBodega != null) {
                inBodega.getPrProductoBodegaList().add(prProductoBodega);
                inBodega = em.merge(inBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrProductoBodega(prProductoBodega.getPrProductoBodegaPK()) != null) {
                throw new PreexistingEntityException("PrProductoBodega " + prProductoBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrProductoBodega prProductoBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductoBodega persistentPrProductoBodega = em.find(PrProductoBodega.class, prProductoBodega.getPrProductoBodegaPK());
            InBodega inBodegaOld = persistentPrProductoBodega.getInBodega();
            InBodega inBodegaNew = prProductoBodega.getInBodega();
            if (inBodegaNew != null) {
                inBodegaNew = em.getReference(inBodegaNew.getClass(), inBodegaNew.getInBodegaPK());
                prProductoBodega.setInBodega(inBodegaNew);
            }
            prProductoBodega = em.merge(prProductoBodega);
            if (inBodegaOld != null && !inBodegaOld.equals(inBodegaNew)) {
                inBodegaOld.getPrProductoBodegaList().remove(prProductoBodega);
                inBodegaOld = em.merge(inBodegaOld);
            }
            if (inBodegaNew != null && !inBodegaNew.equals(inBodegaOld)) {
                inBodegaNew.getPrProductoBodegaList().add(prProductoBodega);
                inBodegaNew = em.merge(inBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrProductoBodegaPK id = prProductoBodega.getPrProductoBodegaPK();
                if (findPrProductoBodega(id) == null) {
                    throw new NonexistentEntityException("The prProductoBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrProductoBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductoBodega prProductoBodega;
            try {
                prProductoBodega = em.getReference(PrProductoBodega.class, id);
                prProductoBodega.getPrProductoBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prProductoBodega with id " + id + " no longer exists.", enfe);
            }
            InBodega inBodega = prProductoBodega.getInBodega();
            if (inBodega != null) {
                inBodega.getPrProductoBodegaList().remove(prProductoBodega);
                inBodega = em.merge(inBodega);
            }
            em.remove(prProductoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrProductoBodega> findPrProductoBodegaEntities() {
        return findPrProductoBodegaEntities(true, -1, -1);
    }

    public List<PrProductoBodega> findPrProductoBodegaEntities(int maxResults, int firstResult) {
        return findPrProductoBodegaEntities(false, maxResults, firstResult);
    }

    private List<PrProductoBodega> findPrProductoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrProductoBodega.class));
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

    public PrProductoBodega findPrProductoBodega(PrProductoBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrProductoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrProductoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrProductoBodega> rt = cq.from(PrProductoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
