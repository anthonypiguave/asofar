/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InDetalleMovimientoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InMovimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class InDetalleMovimientoJpaController implements Serializable {

    public InDetalleMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InDetalleMovimiento inDetalleMovimiento) throws PreexistingEntityException, Exception {
        if (inDetalleMovimiento.getInDetalleMovimientoPK() == null) {
            inDetalleMovimiento.setInDetalleMovimientoPK(new InDetalleMovimientoPK());
        }
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdTipoDocumento(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdTipoDocumento());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdTipoMovimiento(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdTipoMovimiento());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdMovimientos(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdMovimientos());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdMotivo(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdMotivo());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdSucursal(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdSucursal());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdEmpresa(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos inMovimientos = inDetalleMovimiento.getInMovimientos();
            if (inMovimientos != null) {
                inMovimientos = em.getReference(inMovimientos.getClass(), inMovimientos.getInMovimientosPK());
                inDetalleMovimiento.setInMovimientos(inMovimientos);
            }
            em.persist(inDetalleMovimiento);
            if (inMovimientos != null) {
                inMovimientos.getInDetalleMovimientoList().add(inDetalleMovimiento);
                inMovimientos = em.merge(inMovimientos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInDetalleMovimiento(inDetalleMovimiento.getInDetalleMovimientoPK()) != null) {
                throw new PreexistingEntityException("InDetalleMovimiento " + inDetalleMovimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InDetalleMovimiento inDetalleMovimiento) throws NonexistentEntityException, Exception {
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdTipoDocumento(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdTipoDocumento());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdTipoMovimiento(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdTipoMovimiento());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdMovimientos(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdMovimientos());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdMotivo(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdMotivo());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdSucursal(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdSucursal());
        inDetalleMovimiento.getInDetalleMovimientoPK().setIdEmpresa(inDetalleMovimiento.getInMovimientos().getInMovimientosPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InDetalleMovimiento persistentInDetalleMovimiento = em.find(InDetalleMovimiento.class, inDetalleMovimiento.getInDetalleMovimientoPK());
            InMovimientos inMovimientosOld = persistentInDetalleMovimiento.getInMovimientos();
            InMovimientos inMovimientosNew = inDetalleMovimiento.getInMovimientos();
            if (inMovimientosNew != null) {
                inMovimientosNew = em.getReference(inMovimientosNew.getClass(), inMovimientosNew.getInMovimientosPK());
                inDetalleMovimiento.setInMovimientos(inMovimientosNew);
            }
            inDetalleMovimiento = em.merge(inDetalleMovimiento);
            if (inMovimientosOld != null && !inMovimientosOld.equals(inMovimientosNew)) {
                inMovimientosOld.getInDetalleMovimientoList().remove(inDetalleMovimiento);
                inMovimientosOld = em.merge(inMovimientosOld);
            }
            if (inMovimientosNew != null && !inMovimientosNew.equals(inMovimientosOld)) {
                inMovimientosNew.getInDetalleMovimientoList().add(inDetalleMovimiento);
                inMovimientosNew = em.merge(inMovimientosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InDetalleMovimientoPK id = inDetalleMovimiento.getInDetalleMovimientoPK();
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

    public void destroy(InDetalleMovimientoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InDetalleMovimiento inDetalleMovimiento;
            try {
                inDetalleMovimiento = em.getReference(InDetalleMovimiento.class, id);
                inDetalleMovimiento.getInDetalleMovimientoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inDetalleMovimiento with id " + id + " no longer exists.", enfe);
            }
            InMovimientos inMovimientos = inDetalleMovimiento.getInMovimientos();
            if (inMovimientos != null) {
                inMovimientos.getInDetalleMovimientoList().remove(inDetalleMovimiento);
                inMovimientos = em.merge(inMovimientos);
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

    public InDetalleMovimiento findInDetalleMovimiento(InDetalleMovimientoPK id) {
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
