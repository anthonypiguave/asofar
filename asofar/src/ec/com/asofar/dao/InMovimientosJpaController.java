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
import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InMovimientosPK;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class InMovimientosJpaController implements Serializable {

    public InMovimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InMovimientos inMovimientos) throws PreexistingEntityException, Exception {
        if (inMovimientos.getInMovimientosPK() == null) {
            inMovimientos.setInMovimientosPK(new InMovimientosPK());
        }
        inMovimientos.getInMovimientosPK().setIdTipoDocumento(inMovimientos.getInTipoDocumento().getIdTipoDocumento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InEstadosMovimiento idEstado = inMovimientos.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstadoMovimiento());
                inMovimientos.setIdEstado(idEstado);
            }
            InMotivos idMotivo = inMovimientos.getIdMotivo();
            if (idMotivo != null) {
                idMotivo = em.getReference(idMotivo.getClass(), idMotivo.getIdMotivo());
                inMovimientos.setIdMotivo(idMotivo);
            }
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento = em.getReference(inTipoDocumento.getClass(), inTipoDocumento.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumento);
            }
            InTipoMovimiento idTipoMovimiento = inMovimientos.getIdTipoMovimiento();
            if (idTipoMovimiento != null) {
                idTipoMovimiento = em.getReference(idTipoMovimiento.getClass(), idTipoMovimiento.getIdTipoMovimiento());
                inMovimientos.setIdTipoMovimiento(idTipoMovimiento);
            }
            em.persist(inMovimientos);
            if (idEstado != null) {
                idEstado.getInMovimientosList().add(inMovimientos);
                idEstado = em.merge(idEstado);
            }
            if (idMotivo != null) {
                idMotivo.getInMovimientosList().add(inMovimientos);
                idMotivo = em.merge(idMotivo);
            }
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().add(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            if (idTipoMovimiento != null) {
                idTipoMovimiento.getInMovimientosList().add(inMovimientos);
                idTipoMovimiento = em.merge(idTipoMovimiento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInMovimientos(inMovimientos.getInMovimientosPK()) != null) {
                throw new PreexistingEntityException("InMovimientos " + inMovimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InMovimientos inMovimientos) throws NonexistentEntityException, Exception {
        inMovimientos.getInMovimientosPK().setIdTipoDocumento(inMovimientos.getInTipoDocumento().getIdTipoDocumento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos persistentInMovimientos = em.find(InMovimientos.class, inMovimientos.getInMovimientosPK());
            InEstadosMovimiento idEstadoOld = persistentInMovimientos.getIdEstado();
            InEstadosMovimiento idEstadoNew = inMovimientos.getIdEstado();
            InMotivos idMotivoOld = persistentInMovimientos.getIdMotivo();
            InMotivos idMotivoNew = inMovimientos.getIdMotivo();
            InTipoDocumento inTipoDocumentoOld = persistentInMovimientos.getInTipoDocumento();
            InTipoDocumento inTipoDocumentoNew = inMovimientos.getInTipoDocumento();
            InTipoMovimiento idTipoMovimientoOld = persistentInMovimientos.getIdTipoMovimiento();
            InTipoMovimiento idTipoMovimientoNew = inMovimientos.getIdTipoMovimiento();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstadoMovimiento());
                inMovimientos.setIdEstado(idEstadoNew);
            }
            if (idMotivoNew != null) {
                idMotivoNew = em.getReference(idMotivoNew.getClass(), idMotivoNew.getIdMotivo());
                inMovimientos.setIdMotivo(idMotivoNew);
            }
            if (inTipoDocumentoNew != null) {
                inTipoDocumentoNew = em.getReference(inTipoDocumentoNew.getClass(), inTipoDocumentoNew.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumentoNew);
            }
            if (idTipoMovimientoNew != null) {
                idTipoMovimientoNew = em.getReference(idTipoMovimientoNew.getClass(), idTipoMovimientoNew.getIdTipoMovimiento());
                inMovimientos.setIdTipoMovimiento(idTipoMovimientoNew);
            }
            inMovimientos = em.merge(inMovimientos);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getInMovimientosList().remove(inMovimientos);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getInMovimientosList().add(inMovimientos);
                idEstadoNew = em.merge(idEstadoNew);
            }
            if (idMotivoOld != null && !idMotivoOld.equals(idMotivoNew)) {
                idMotivoOld.getInMovimientosList().remove(inMovimientos);
                idMotivoOld = em.merge(idMotivoOld);
            }
            if (idMotivoNew != null && !idMotivoNew.equals(idMotivoOld)) {
                idMotivoNew.getInMovimientosList().add(inMovimientos);
                idMotivoNew = em.merge(idMotivoNew);
            }
            if (inTipoDocumentoOld != null && !inTipoDocumentoOld.equals(inTipoDocumentoNew)) {
                inTipoDocumentoOld.getInMovimientosList().remove(inMovimientos);
                inTipoDocumentoOld = em.merge(inTipoDocumentoOld);
            }
            if (inTipoDocumentoNew != null && !inTipoDocumentoNew.equals(inTipoDocumentoOld)) {
                inTipoDocumentoNew.getInMovimientosList().add(inMovimientos);
                inTipoDocumentoNew = em.merge(inTipoDocumentoNew);
            }
            if (idTipoMovimientoOld != null && !idTipoMovimientoOld.equals(idTipoMovimientoNew)) {
                idTipoMovimientoOld.getInMovimientosList().remove(inMovimientos);
                idTipoMovimientoOld = em.merge(idTipoMovimientoOld);
            }
            if (idTipoMovimientoNew != null && !idTipoMovimientoNew.equals(idTipoMovimientoOld)) {
                idTipoMovimientoNew.getInMovimientosList().add(inMovimientos);
                idTipoMovimientoNew = em.merge(idTipoMovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InMovimientosPK id = inMovimientos.getInMovimientosPK();
                if (findInMovimientos(id) == null) {
                    throw new NonexistentEntityException("The inMovimientos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InMovimientosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos inMovimientos;
            try {
                inMovimientos = em.getReference(InMovimientos.class, id);
                inMovimientos.getInMovimientosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inMovimientos with id " + id + " no longer exists.", enfe);
            }
            InEstadosMovimiento idEstado = inMovimientos.getIdEstado();
            if (idEstado != null) {
                idEstado.getInMovimientosList().remove(inMovimientos);
                idEstado = em.merge(idEstado);
            }
            InMotivos idMotivo = inMovimientos.getIdMotivo();
            if (idMotivo != null) {
                idMotivo.getInMovimientosList().remove(inMovimientos);
                idMotivo = em.merge(idMotivo);
            }
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().remove(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            InTipoMovimiento idTipoMovimiento = inMovimientos.getIdTipoMovimiento();
            if (idTipoMovimiento != null) {
                idTipoMovimiento.getInMovimientosList().remove(inMovimientos);
                idTipoMovimiento = em.merge(idTipoMovimiento);
            }
            em.remove(inMovimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InMovimientos> findInMovimientosEntities() {
        return findInMovimientosEntities(true, -1, -1);
    }

    public List<InMovimientos> findInMovimientosEntities(int maxResults, int firstResult) {
        return findInMovimientosEntities(false, maxResults, firstResult);
    }

    private List<InMovimientos> findInMovimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InMovimientos.class));
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

    public InMovimientos findInMovimientos(InMovimientosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InMovimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getInMovimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InMovimientos> rt = cq.from(InMovimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
