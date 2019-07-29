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
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeDetalleCajaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin
 */
public class VeDetalleCajaJpaController implements Serializable {

    public VeDetalleCajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeDetalleCaja veDetalleCaja) throws PreexistingEntityException, Exception {
        if (veDetalleCaja.getVeDetalleCajaPK() == null) {
            veDetalleCaja.setVeDetalleCajaPK(new VeDetalleCajaPK());
        }
        veDetalleCaja.getVeDetalleCajaPK().setIdCaja(veDetalleCaja.getVeCaja().getIdCaja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeCaja veCaja = veDetalleCaja.getVeCaja();
            if (veCaja != null) {
                veCaja = em.getReference(veCaja.getClass(), veCaja.getIdCaja());
                veDetalleCaja.setVeCaja(veCaja);
            }
            em.persist(veDetalleCaja);
            if (veCaja != null) {
                veCaja.getVeDetalleCajaList().add(veDetalleCaja);
                veCaja = em.merge(veCaja);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVeDetalleCaja(veDetalleCaja.getVeDetalleCajaPK()) != null) {
                throw new PreexistingEntityException("VeDetalleCaja " + veDetalleCaja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeDetalleCaja veDetalleCaja) throws NonexistentEntityException, Exception {
        veDetalleCaja.getVeDetalleCajaPK().setIdCaja(veDetalleCaja.getVeCaja().getIdCaja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeDetalleCaja persistentVeDetalleCaja = em.find(VeDetalleCaja.class, veDetalleCaja.getVeDetalleCajaPK());
            VeCaja veCajaOld = persistentVeDetalleCaja.getVeCaja();
            VeCaja veCajaNew = veDetalleCaja.getVeCaja();
            if (veCajaNew != null) {
                veCajaNew = em.getReference(veCajaNew.getClass(), veCajaNew.getIdCaja());
                veDetalleCaja.setVeCaja(veCajaNew);
            }
            veDetalleCaja = em.merge(veDetalleCaja);
            if (veCajaOld != null && !veCajaOld.equals(veCajaNew)) {
                veCajaOld.getVeDetalleCajaList().remove(veDetalleCaja);
                veCajaOld = em.merge(veCajaOld);
            }
            if (veCajaNew != null && !veCajaNew.equals(veCajaOld)) {
                veCajaNew.getVeDetalleCajaList().add(veDetalleCaja);
                veCajaNew = em.merge(veCajaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VeDetalleCajaPK id = veDetalleCaja.getVeDetalleCajaPK();
                if (findVeDetalleCaja(id) == null) {
                    throw new NonexistentEntityException("The veDetalleCaja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VeDetalleCajaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeDetalleCaja veDetalleCaja;
            try {
                veDetalleCaja = em.getReference(VeDetalleCaja.class, id);
                veDetalleCaja.getVeDetalleCajaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veDetalleCaja with id " + id + " no longer exists.", enfe);
            }
            VeCaja veCaja = veDetalleCaja.getVeCaja();
            if (veCaja != null) {
                veCaja.getVeDetalleCajaList().remove(veDetalleCaja);
                veCaja = em.merge(veCaja);
            }
            em.remove(veDetalleCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeDetalleCaja> findVeDetalleCajaEntities() {
        return findVeDetalleCajaEntities(true, -1, -1);
    }

    public List<VeDetalleCaja> findVeDetalleCajaEntities(int maxResults, int firstResult) {
        return findVeDetalleCajaEntities(false, maxResults, firstResult);
    }

    private List<VeDetalleCaja> findVeDetalleCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeDetalleCaja.class));
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

    public VeDetalleCaja findVeDetalleCaja(VeDetalleCajaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeDetalleCaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeDetalleCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeDetalleCaja> rt = cq.from(VeDetalleCaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
