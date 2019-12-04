/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InEstadosMovimiento;
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
 * @author admini
 */
public class InEstadosMovimientoJpaController implements Serializable {

    public InEstadosMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InEstadosMovimiento inEstadosMovimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inEstadosMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InEstadosMovimiento inEstadosMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inEstadosMovimiento = em.merge(inEstadosMovimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inEstadosMovimiento.getIdEstadoMovimiento();
                if (findInEstadosMovimiento(id) == null) {
                    throw new NonexistentEntityException("The inEstadosMovimiento with id " + id + " no longer exists.");
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
            InEstadosMovimiento inEstadosMovimiento;
            try {
                inEstadosMovimiento = em.getReference(InEstadosMovimiento.class, id);
                inEstadosMovimiento.getIdEstadoMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inEstadosMovimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(inEstadosMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InEstadosMovimiento> findInEstadosMovimientoEntities() {
        return findInEstadosMovimientoEntities(true, -1, -1);
    }

    public List<InEstadosMovimiento> findInEstadosMovimientoEntities(int maxResults, int firstResult) {
        return findInEstadosMovimientoEntities(false, maxResults, firstResult);
    }

    private List<InEstadosMovimiento> findInEstadosMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InEstadosMovimiento.class));
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

    public InEstadosMovimiento findInEstadosMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InEstadosMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInEstadosMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InEstadosMovimiento> rt = cq.from(InEstadosMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
