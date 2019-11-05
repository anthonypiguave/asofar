/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.PrProductoBodega;
import ec.com.asofar.dto.PrProductoBodegaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
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
        prProductoBodega.getPrProductoBodegaPK().setIdSucursal(prProductoBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        prProductoBodega.getPrProductoBodegaPK().setIdEmpresa(prProductoBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = prProductoBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                prProductoBodega.setSeSucursal(seSucursal);
            }
            em.persist(prProductoBodega);
            if (seSucursal != null) {
                seSucursal.getPrProductoBodegaList().add(prProductoBodega);
                seSucursal = em.merge(seSucursal);
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
        prProductoBodega.getPrProductoBodegaPK().setIdSucursal(prProductoBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        prProductoBodega.getPrProductoBodegaPK().setIdEmpresa(prProductoBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductoBodega persistentPrProductoBodega = em.find(PrProductoBodega.class, prProductoBodega.getPrProductoBodegaPK());
            SeSucursal seSucursalOld = persistentPrProductoBodega.getSeSucursal();
            SeSucursal seSucursalNew = prProductoBodega.getSeSucursal();
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                prProductoBodega.setSeSucursal(seSucursalNew);
            }
            prProductoBodega = em.merge(prProductoBodega);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getPrProductoBodegaList().remove(prProductoBodega);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getPrProductoBodegaList().add(prProductoBodega);
                seSucursalNew = em.merge(seSucursalNew);
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
            SeSucursal seSucursal = prProductoBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getPrProductoBodegaList().remove(prProductoBodega);
                seSucursal = em.merge(seSucursal);
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
