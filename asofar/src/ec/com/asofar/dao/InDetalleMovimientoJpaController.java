/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InDetalleMovimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InTipoMovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class InDetalleMovimientoJpaController implements Serializable {

    public InDetalleMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InDetalleMovimiento inDetalleMovimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoMovimiento idTipoMovimiento = inDetalleMovimiento.getIdTipoMovimiento();
            if (idTipoMovimiento != null) {
                idTipoMovimiento = em.getReference(idTipoMovimiento.getClass(), idTipoMovimiento.getIdTipoMovimiento());
                inDetalleMovimiento.setIdTipoMovimiento(idTipoMovimiento);
            }
            em.persist(inDetalleMovimiento);
            if (idTipoMovimiento != null) {
                idTipoMovimiento.getInDetalleMovimientoList().add(inDetalleMovimiento);
                idTipoMovimiento = em.merge(idTipoMovimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InDetalleMovimiento inDetalleMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InDetalleMovimiento persistentInDetalleMovimiento = em.find(InDetalleMovimiento.class, inDetalleMovimiento.getIdDetalleMovimiento());
            InTipoMovimiento idTipoMovimientoOld = persistentInDetalleMovimiento.getIdTipoMovimiento();
            InTipoMovimiento idTipoMovimientoNew = inDetalleMovimiento.getIdTipoMovimiento();
            if (idTipoMovimientoNew != null) {
                idTipoMovimientoNew = em.getReference(idTipoMovimientoNew.getClass(), idTipoMovimientoNew.getIdTipoMovimiento());
                inDetalleMovimiento.setIdTipoMovimiento(idTipoMovimientoNew);
            }
            inDetalleMovimiento = em.merge(inDetalleMovimiento);
            if (idTipoMovimientoOld != null && !idTipoMovimientoOld.equals(idTipoMovimientoNew)) {
                idTipoMovimientoOld.getInDetalleMovimientoList().remove(inDetalleMovimiento);
                idTipoMovimientoOld = em.merge(idTipoMovimientoOld);
            }
            if (idTipoMovimientoNew != null && !idTipoMovimientoNew.equals(idTipoMovimientoOld)) {
                idTipoMovimientoNew.getInDetalleMovimientoList().add(inDetalleMovimiento);
                idTipoMovimientoNew = em.merge(idTipoMovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inDetalleMovimiento.getIdDetalleMovimiento();
                if (findInDetalleMovimiento(id) == null) {
                    throw new NonexistentEntityException("The inDetalleMovimiento with id " + id + " no longer exists.");
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
            InDetalleMovimiento inDetalleMovimiento;
            try {
                inDetalleMovimiento = em.getReference(InDetalleMovimiento.class, id);
                inDetalleMovimiento.getIdDetalleMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inDetalleMovimiento with id " + id + " no longer exists.", enfe);
            }
            InTipoMovimiento idTipoMovimiento = inDetalleMovimiento.getIdTipoMovimiento();
            if (idTipoMovimiento != null) {
                idTipoMovimiento.getInDetalleMovimientoList().remove(inDetalleMovimiento);
                idTipoMovimiento = em.merge(idTipoMovimiento);
            }
            em.remove(inDetalleMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InDetalleMovimiento> findInDetalleMovimientoEntities() {
        return findInDetalleMovimientoEntities(true, -1, -1);
    }

    public List<InDetalleMovimiento> findInDetalleMovimientoEntities(int maxResults, int firstResult) {
        return findInDetalleMovimientoEntities(false, maxResults, firstResult);
    }

    private List<InDetalleMovimiento> findInDetalleMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InDetalleMovimiento.class));
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

    public InDetalleMovimiento findInDetalleMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InDetalleMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInDetalleMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InDetalleMovimiento> rt = cq.from(InDetalleMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
