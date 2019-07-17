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
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InMovimientosPK;
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
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inMovimientos.getInMovimientosPK().setIdSucursal(inMovimientos.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inMovimientos.getInMovimientosPK().setIdUsuario(inMovimientos.getSePersonas().getIdPersona());
        inMovimientos.getInMovimientosPK().setIdEstado(inMovimientos.getInEstadosMovimiento().getIdEstadoMovimiento());
        inMovimientos.getInMovimientosPK().setIdMotivo(inMovimientos.getInMotivos().getIdMotivo());
        inMovimientos.getInMovimientosPK().setIdTipoDocumento(inMovimientos.getInTipoDocumento().getIdTipoDocumento());
        inMovimientos.getInMovimientosPK().setIdTipoMovimiento(inMovimientos.getInTipoMovimiento().getIdTipoMovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento = em.getReference(inTipoDocumento.getClass(), inTipoDocumento.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumento);
            }
            CoProveedores idProveedor = inMovimientos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                inMovimientos.setIdProveedor(idProveedor);
            }
            InTipoMovimiento inTipoMovimiento = inMovimientos.getInTipoMovimiento();
            if (inTipoMovimiento != null) {
                inTipoMovimiento = em.getReference(inTipoMovimiento.getClass(), inTipoMovimiento.getIdTipoMovimiento());
                inMovimientos.setInTipoMovimiento(inTipoMovimiento);
            }
            InMotivos inMotivos = inMovimientos.getInMotivos();
            if (inMotivos != null) {
                inMotivos = em.getReference(inMotivos.getClass(), inMotivos.getIdMotivo());
                inMovimientos.setInMotivos(inMotivos);
            }
            SeSucursal seSucursal = inMovimientos.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                inMovimientos.setSeSucursal(seSucursal);
            }
            SePersonas sePersonas = inMovimientos.getSePersonas();
            if (sePersonas != null) {
                sePersonas = em.getReference(sePersonas.getClass(), sePersonas.getIdPersona());
                inMovimientos.setSePersonas(sePersonas);
            }
            InEstadosMovimiento inEstadosMovimiento = inMovimientos.getInEstadosMovimiento();
            if (inEstadosMovimiento != null) {
                inEstadosMovimiento = em.getReference(inEstadosMovimiento.getClass(), inEstadosMovimiento.getIdEstadoMovimiento());
                inMovimientos.setInEstadosMovimiento(inEstadosMovimiento);
            }
            em.persist(inMovimientos);
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().add(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            if (idProveedor != null) {
                idProveedor.getInMovimientosList().add(inMovimientos);
                idProveedor = em.merge(idProveedor);
            }
            if (inTipoMovimiento != null) {
                inTipoMovimiento.getInMovimientosList().add(inMovimientos);
                inTipoMovimiento = em.merge(inTipoMovimiento);
            }
            if (inMotivos != null) {
                inMotivos.getInMovimientosList().add(inMovimientos);
                inMotivos = em.merge(inMotivos);
            }
            if (seSucursal != null) {
                seSucursal.getInMovimientosList().add(inMovimientos);
                seSucursal = em.merge(seSucursal);
            }
            if (sePersonas != null) {
                sePersonas.getInMovimientosList().add(inMovimientos);
                sePersonas = em.merge(sePersonas);
            }
            if (inEstadosMovimiento != null) {
                inEstadosMovimiento.getInMovimientosList().add(inMovimientos);
                inEstadosMovimiento = em.merge(inEstadosMovimiento);
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
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inMovimientos.getInMovimientosPK().setIdSucursal(inMovimientos.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inMovimientos.getInMovimientosPK().setIdUsuario(inMovimientos.getSePersonas().getIdPersona());
        inMovimientos.getInMovimientosPK().setIdEstado(inMovimientos.getInEstadosMovimiento().getIdEstadoMovimiento());
        inMovimientos.getInMovimientosPK().setIdMotivo(inMovimientos.getInMotivos().getIdMotivo());
        inMovimientos.getInMovimientosPK().setIdTipoDocumento(inMovimientos.getInTipoDocumento().getIdTipoDocumento());
        inMovimientos.getInMovimientosPK().setIdTipoMovimiento(inMovimientos.getInTipoMovimiento().getIdTipoMovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos persistentInMovimientos = em.find(InMovimientos.class, inMovimientos.getInMovimientosPK());
            InTipoDocumento inTipoDocumentoOld = persistentInMovimientos.getInTipoDocumento();
            InTipoDocumento inTipoDocumentoNew = inMovimientos.getInTipoDocumento();
            CoProveedores idProveedorOld = persistentInMovimientos.getIdProveedor();
            CoProveedores idProveedorNew = inMovimientos.getIdProveedor();
            InTipoMovimiento inTipoMovimientoOld = persistentInMovimientos.getInTipoMovimiento();
            InTipoMovimiento inTipoMovimientoNew = inMovimientos.getInTipoMovimiento();
            InMotivos inMotivosOld = persistentInMovimientos.getInMotivos();
            InMotivos inMotivosNew = inMovimientos.getInMotivos();
            SeSucursal seSucursalOld = persistentInMovimientos.getSeSucursal();
            SeSucursal seSucursalNew = inMovimientos.getSeSucursal();
            SePersonas sePersonasOld = persistentInMovimientos.getSePersonas();
            SePersonas sePersonasNew = inMovimientos.getSePersonas();
            InEstadosMovimiento inEstadosMovimientoOld = persistentInMovimientos.getInEstadosMovimiento();
            InEstadosMovimiento inEstadosMovimientoNew = inMovimientos.getInEstadosMovimiento();
            if (inTipoDocumentoNew != null) {
                inTipoDocumentoNew = em.getReference(inTipoDocumentoNew.getClass(), inTipoDocumentoNew.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumentoNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                inMovimientos.setIdProveedor(idProveedorNew);
            }
            if (inTipoMovimientoNew != null) {
                inTipoMovimientoNew = em.getReference(inTipoMovimientoNew.getClass(), inTipoMovimientoNew.getIdTipoMovimiento());
                inMovimientos.setInTipoMovimiento(inTipoMovimientoNew);
            }
            if (inMotivosNew != null) {
                inMotivosNew = em.getReference(inMotivosNew.getClass(), inMotivosNew.getIdMotivo());
                inMovimientos.setInMotivos(inMotivosNew);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                inMovimientos.setSeSucursal(seSucursalNew);
            }
            if (sePersonasNew != null) {
                sePersonasNew = em.getReference(sePersonasNew.getClass(), sePersonasNew.getIdPersona());
                inMovimientos.setSePersonas(sePersonasNew);
            }
            if (inEstadosMovimientoNew != null) {
                inEstadosMovimientoNew = em.getReference(inEstadosMovimientoNew.getClass(), inEstadosMovimientoNew.getIdEstadoMovimiento());
                inMovimientos.setInEstadosMovimiento(inEstadosMovimientoNew);
            }
            inMovimientos = em.merge(inMovimientos);
            if (inTipoDocumentoOld != null && !inTipoDocumentoOld.equals(inTipoDocumentoNew)) {
                inTipoDocumentoOld.getInMovimientosList().remove(inMovimientos);
                inTipoDocumentoOld = em.merge(inTipoDocumentoOld);
            }
            if (inTipoDocumentoNew != null && !inTipoDocumentoNew.equals(inTipoDocumentoOld)) {
                inTipoDocumentoNew.getInMovimientosList().add(inMovimientos);
                inTipoDocumentoNew = em.merge(inTipoDocumentoNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getInMovimientosList().remove(inMovimientos);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getInMovimientosList().add(inMovimientos);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (inTipoMovimientoOld != null && !inTipoMovimientoOld.equals(inTipoMovimientoNew)) {
                inTipoMovimientoOld.getInMovimientosList().remove(inMovimientos);
                inTipoMovimientoOld = em.merge(inTipoMovimientoOld);
            }
            if (inTipoMovimientoNew != null && !inTipoMovimientoNew.equals(inTipoMovimientoOld)) {
                inTipoMovimientoNew.getInMovimientosList().add(inMovimientos);
                inTipoMovimientoNew = em.merge(inTipoMovimientoNew);
            }
            if (inMotivosOld != null && !inMotivosOld.equals(inMotivosNew)) {
                inMotivosOld.getInMovimientosList().remove(inMovimientos);
                inMotivosOld = em.merge(inMotivosOld);
            }
            if (inMotivosNew != null && !inMotivosNew.equals(inMotivosOld)) {
                inMotivosNew.getInMovimientosList().add(inMovimientos);
                inMotivosNew = em.merge(inMotivosNew);
            }
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getInMovimientosList().remove(inMovimientos);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getInMovimientosList().add(inMovimientos);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (sePersonasOld != null && !sePersonasOld.equals(sePersonasNew)) {
                sePersonasOld.getInMovimientosList().remove(inMovimientos);
                sePersonasOld = em.merge(sePersonasOld);
            }
            if (sePersonasNew != null && !sePersonasNew.equals(sePersonasOld)) {
                sePersonasNew.getInMovimientosList().add(inMovimientos);
                sePersonasNew = em.merge(sePersonasNew);
            }
            if (inEstadosMovimientoOld != null && !inEstadosMovimientoOld.equals(inEstadosMovimientoNew)) {
                inEstadosMovimientoOld.getInMovimientosList().remove(inMovimientos);
                inEstadosMovimientoOld = em.merge(inEstadosMovimientoOld);
            }
            if (inEstadosMovimientoNew != null && !inEstadosMovimientoNew.equals(inEstadosMovimientoOld)) {
                inEstadosMovimientoNew.getInMovimientosList().add(inMovimientos);
                inEstadosMovimientoNew = em.merge(inEstadosMovimientoNew);
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
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().remove(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            CoProveedores idProveedor = inMovimientos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getInMovimientosList().remove(inMovimientos);
                idProveedor = em.merge(idProveedor);
            }
            InTipoMovimiento inTipoMovimiento = inMovimientos.getInTipoMovimiento();
            if (inTipoMovimiento != null) {
                inTipoMovimiento.getInMovimientosList().remove(inMovimientos);
                inTipoMovimiento = em.merge(inTipoMovimiento);
            }
            InMotivos inMotivos = inMovimientos.getInMotivos();
            if (inMotivos != null) {
                inMotivos.getInMovimientosList().remove(inMovimientos);
                inMotivos = em.merge(inMotivos);
            }
            SeSucursal seSucursal = inMovimientos.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getInMovimientosList().remove(inMovimientos);
                seSucursal = em.merge(seSucursal);
            }
            SePersonas sePersonas = inMovimientos.getSePersonas();
            if (sePersonas != null) {
                sePersonas.getInMovimientosList().remove(inMovimientos);
                sePersonas = em.merge(sePersonas);
            }
            InEstadosMovimiento inEstadosMovimiento = inMovimientos.getInEstadosMovimiento();
            if (inEstadosMovimiento != null) {
                inEstadosMovimiento.getInMovimientosList().remove(inMovimientos);
                inEstadosMovimiento = em.merge(inEstadosMovimiento);
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
