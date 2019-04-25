/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class InKardexJpaController implements Serializable {

    public InKardexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InKardex inKardex) throws PreexistingEntityException, Exception {
        if (inKardex.getInKardexPK() == null) {
            inKardex.setInKardexPK(new InKardexPK());
        }
        inKardex.getInKardexPK().setIdTipoDocumento(inKardex.getInTipoDocumento().getIdTipoDocumento());
        inKardex.getInKardexPK().setIdEmpresa(inKardex.getPrPrestaciones().getPrPrestacionesPK().getIdEmpresa());
        inKardex.getInKardexPK().setIdTipoMovimiento(inKardex.getInTipoMovimiento().getIdTipoMovimiento());
        inKardex.getInKardexPK().setIdSucursal(inKardex.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inKardex.getInKardexPK().setIdPrestaciones(inKardex.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = inKardex.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                inKardex.setSeSucursal(seSucursal);
            }
            PrPrestaciones prPrestaciones = inKardex.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones = em.getReference(prPrestaciones.getClass(), prPrestaciones.getPrPrestacionesPK());
                inKardex.setPrPrestaciones(prPrestaciones);
            }
            InTipoDocumento inTipoDocumento = inKardex.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento = em.getReference(inTipoDocumento.getClass(), inTipoDocumento.getIdTipoDocumento());
                inKardex.setInTipoDocumento(inTipoDocumento);
            }
            InTipoMovimiento inTipoMovimiento = inKardex.getInTipoMovimiento();
            if (inTipoMovimiento != null) {
                inTipoMovimiento = em.getReference(inTipoMovimiento.getClass(), inTipoMovimiento.getIdTipoMovimiento());
                inKardex.setInTipoMovimiento(inTipoMovimiento);
            }
            em.persist(inKardex);
            if (seSucursal != null) {
                seSucursal.getInKardexList().add(inKardex);
                seSucursal = em.merge(seSucursal);
            }
            if (prPrestaciones != null) {
                prPrestaciones.getInKardexList().add(inKardex);
                prPrestaciones = em.merge(prPrestaciones);
            }
            if (inTipoDocumento != null) {
                inTipoDocumento.getInKardexList().add(inKardex);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            if (inTipoMovimiento != null) {
                inTipoMovimiento.getInKardexList().add(inKardex);
                inTipoMovimiento = em.merge(inTipoMovimiento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInKardex(inKardex.getInKardexPK()) != null) {
                throw new PreexistingEntityException("InKardex " + inKardex + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InKardex inKardex) throws NonexistentEntityException, Exception {
        inKardex.getInKardexPK().setIdTipoDocumento(inKardex.getInTipoDocumento().getIdTipoDocumento());
        inKardex.getInKardexPK().setIdEmpresa(inKardex.getPrPrestaciones().getPrPrestacionesPK().getIdEmpresa());
        inKardex.getInKardexPK().setIdTipoMovimiento(inKardex.getInTipoMovimiento().getIdTipoMovimiento());
        inKardex.getInKardexPK().setIdSucursal(inKardex.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inKardex.getInKardexPK().setIdPrestaciones(inKardex.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InKardex persistentInKardex = em.find(InKardex.class, inKardex.getInKardexPK());
            SeSucursal seSucursalOld = persistentInKardex.getSeSucursal();
            SeSucursal seSucursalNew = inKardex.getSeSucursal();
            PrPrestaciones prPrestacionesOld = persistentInKardex.getPrPrestaciones();
            PrPrestaciones prPrestacionesNew = inKardex.getPrPrestaciones();
            InTipoDocumento inTipoDocumentoOld = persistentInKardex.getInTipoDocumento();
            InTipoDocumento inTipoDocumentoNew = inKardex.getInTipoDocumento();
            InTipoMovimiento inTipoMovimientoOld = persistentInKardex.getInTipoMovimiento();
            InTipoMovimiento inTipoMovimientoNew = inKardex.getInTipoMovimiento();
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                inKardex.setSeSucursal(seSucursalNew);
            }
            if (prPrestacionesNew != null) {
                prPrestacionesNew = em.getReference(prPrestacionesNew.getClass(), prPrestacionesNew.getPrPrestacionesPK());
                inKardex.setPrPrestaciones(prPrestacionesNew);
            }
            if (inTipoDocumentoNew != null) {
                inTipoDocumentoNew = em.getReference(inTipoDocumentoNew.getClass(), inTipoDocumentoNew.getIdTipoDocumento());
                inKardex.setInTipoDocumento(inTipoDocumentoNew);
            }
            if (inTipoMovimientoNew != null) {
                inTipoMovimientoNew = em.getReference(inTipoMovimientoNew.getClass(), inTipoMovimientoNew.getIdTipoMovimiento());
                inKardex.setInTipoMovimiento(inTipoMovimientoNew);
            }
            inKardex = em.merge(inKardex);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getInKardexList().remove(inKardex);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getInKardexList().add(inKardex);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (prPrestacionesOld != null && !prPrestacionesOld.equals(prPrestacionesNew)) {
                prPrestacionesOld.getInKardexList().remove(inKardex);
                prPrestacionesOld = em.merge(prPrestacionesOld);
            }
            if (prPrestacionesNew != null && !prPrestacionesNew.equals(prPrestacionesOld)) {
                prPrestacionesNew.getInKardexList().add(inKardex);
                prPrestacionesNew = em.merge(prPrestacionesNew);
            }
            if (inTipoDocumentoOld != null && !inTipoDocumentoOld.equals(inTipoDocumentoNew)) {
                inTipoDocumentoOld.getInKardexList().remove(inKardex);
                inTipoDocumentoOld = em.merge(inTipoDocumentoOld);
            }
            if (inTipoDocumentoNew != null && !inTipoDocumentoNew.equals(inTipoDocumentoOld)) {
                inTipoDocumentoNew.getInKardexList().add(inKardex);
                inTipoDocumentoNew = em.merge(inTipoDocumentoNew);
            }
            if (inTipoMovimientoOld != null && !inTipoMovimientoOld.equals(inTipoMovimientoNew)) {
                inTipoMovimientoOld.getInKardexList().remove(inKardex);
                inTipoMovimientoOld = em.merge(inTipoMovimientoOld);
            }
            if (inTipoMovimientoNew != null && !inTipoMovimientoNew.equals(inTipoMovimientoOld)) {
                inTipoMovimientoNew.getInKardexList().add(inKardex);
                inTipoMovimientoNew = em.merge(inTipoMovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InKardexPK id = inKardex.getInKardexPK();
                if (findInKardex(id) == null) {
                    throw new NonexistentEntityException("The inKardex with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InKardexPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InKardex inKardex;
            try {
                inKardex = em.getReference(InKardex.class, id);
                inKardex.getInKardexPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inKardex with id " + id + " no longer exists.", enfe);
            }
            SeSucursal seSucursal = inKardex.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getInKardexList().remove(inKardex);
                seSucursal = em.merge(seSucursal);
            }
            PrPrestaciones prPrestaciones = inKardex.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones.getInKardexList().remove(inKardex);
                prPrestaciones = em.merge(prPrestaciones);
            }
            InTipoDocumento inTipoDocumento = inKardex.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento.getInKardexList().remove(inKardex);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            InTipoMovimiento inTipoMovimiento = inKardex.getInTipoMovimiento();
            if (inTipoMovimiento != null) {
                inTipoMovimiento.getInKardexList().remove(inKardex);
                inTipoMovimiento = em.merge(inTipoMovimiento);
            }
            em.remove(inKardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InKardex> findInKardexEntities() {
        return findInKardexEntities(true, -1, -1);
    }

    public List<InKardex> findInKardexEntities(int maxResults, int firstResult) {
        return findInKardexEntities(false, maxResults, firstResult);
    }

    private List<InKardex> findInKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InKardex.class));
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

    public InKardex findInKardex(InKardexPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InKardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getInKardexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InKardex> rt = cq.from(InKardex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
