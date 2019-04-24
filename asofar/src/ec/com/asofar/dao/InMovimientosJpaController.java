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
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InMovimientosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
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
        if (inMovimientos.getInDetalleMovimientoList() == null) {
            inMovimientos.setInDetalleMovimientoList(new ArrayList<InDetalleMovimiento>());
        }
        inMovimientos.getInMovimientosPK().setIdTipoDocumento(inMovimientos.getInTipoDocumento().getIdTipoDocumento());
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getInBodega().getInBodegaPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras coOrdenCompras = inMovimientos.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras = em.getReference(coOrdenCompras.getClass(), coOrdenCompras.getCoOrdenComprasPK());
                inMovimientos.setCoOrdenCompras(coOrdenCompras);
            }
            InBodega inBodega = inMovimientos.getInBodega();
            if (inBodega != null) {
                inBodega = em.getReference(inBodega.getClass(), inBodega.getInBodegaPK());
                inMovimientos.setInBodega(inBodega);
            }
            SePersonas idProveedor = inMovimientos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdPersona());
                inMovimientos.setIdProveedor(idProveedor);
            }
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento = em.getReference(inTipoDocumento.getClass(), inTipoDocumento.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumento);
            }
            SeUsuarios idUsuario = inMovimientos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                inMovimientos.setIdUsuario(idUsuario);
            }
            List<InDetalleMovimiento> attachedInDetalleMovimientoList = new ArrayList<InDetalleMovimiento>();
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimientoToAttach : inMovimientos.getInDetalleMovimientoList()) {
                inDetalleMovimientoListInDetalleMovimientoToAttach = em.getReference(inDetalleMovimientoListInDetalleMovimientoToAttach.getClass(), inDetalleMovimientoListInDetalleMovimientoToAttach.getIdDetalleMovimiento());
                attachedInDetalleMovimientoList.add(inDetalleMovimientoListInDetalleMovimientoToAttach);
            }
            inMovimientos.setInDetalleMovimientoList(attachedInDetalleMovimientoList);
            em.persist(inMovimientos);
            if (coOrdenCompras != null) {
                coOrdenCompras.getInMovimientosList().add(inMovimientos);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            if (inBodega != null) {
                inBodega.getInMovimientosList().add(inMovimientos);
                inBodega = em.merge(inBodega);
            }
            if (idProveedor != null) {
                idProveedor.getInMovimientosList().add(inMovimientos);
                idProveedor = em.merge(idProveedor);
            }
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().add(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            if (idUsuario != null) {
                idUsuario.getInMovimientosList().add(inMovimientos);
                idUsuario = em.merge(idUsuario);
            }
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimiento : inMovimientos.getInDetalleMovimientoList()) {
                InMovimientos oldInMovimientosOfInDetalleMovimientoListInDetalleMovimiento = inDetalleMovimientoListInDetalleMovimiento.getInMovimientos();
                inDetalleMovimientoListInDetalleMovimiento.setInMovimientos(inMovimientos);
                inDetalleMovimientoListInDetalleMovimiento = em.merge(inDetalleMovimientoListInDetalleMovimiento);
                if (oldInMovimientosOfInDetalleMovimientoListInDetalleMovimiento != null) {
                    oldInMovimientosOfInDetalleMovimientoListInDetalleMovimiento.getInDetalleMovimientoList().remove(inDetalleMovimientoListInDetalleMovimiento);
                    oldInMovimientosOfInDetalleMovimientoListInDetalleMovimiento = em.merge(oldInMovimientosOfInDetalleMovimientoListInDetalleMovimiento);
                }
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
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getInBodega().getInBodegaPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos persistentInMovimientos = em.find(InMovimientos.class, inMovimientos.getInMovimientosPK());
            CoOrdenCompras coOrdenComprasOld = persistentInMovimientos.getCoOrdenCompras();
            CoOrdenCompras coOrdenComprasNew = inMovimientos.getCoOrdenCompras();
            InBodega inBodegaOld = persistentInMovimientos.getInBodega();
            InBodega inBodegaNew = inMovimientos.getInBodega();
            SePersonas idProveedorOld = persistentInMovimientos.getIdProveedor();
            SePersonas idProveedorNew = inMovimientos.getIdProveedor();
            InTipoDocumento inTipoDocumentoOld = persistentInMovimientos.getInTipoDocumento();
            InTipoDocumento inTipoDocumentoNew = inMovimientos.getInTipoDocumento();
            SeUsuarios idUsuarioOld = persistentInMovimientos.getIdUsuario();
            SeUsuarios idUsuarioNew = inMovimientos.getIdUsuario();
            List<InDetalleMovimiento> inDetalleMovimientoListOld = persistentInMovimientos.getInDetalleMovimientoList();
            List<InDetalleMovimiento> inDetalleMovimientoListNew = inMovimientos.getInDetalleMovimientoList();
            if (coOrdenComprasNew != null) {
                coOrdenComprasNew = em.getReference(coOrdenComprasNew.getClass(), coOrdenComprasNew.getCoOrdenComprasPK());
                inMovimientos.setCoOrdenCompras(coOrdenComprasNew);
            }
            if (inBodegaNew != null) {
                inBodegaNew = em.getReference(inBodegaNew.getClass(), inBodegaNew.getInBodegaPK());
                inMovimientos.setInBodega(inBodegaNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdPersona());
                inMovimientos.setIdProveedor(idProveedorNew);
            }
            if (inTipoDocumentoNew != null) {
                inTipoDocumentoNew = em.getReference(inTipoDocumentoNew.getClass(), inTipoDocumentoNew.getIdTipoDocumento());
                inMovimientos.setInTipoDocumento(inTipoDocumentoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                inMovimientos.setIdUsuario(idUsuarioNew);
            }
            List<InDetalleMovimiento> attachedInDetalleMovimientoListNew = new ArrayList<InDetalleMovimiento>();
            for (InDetalleMovimiento inDetalleMovimientoListNewInDetalleMovimientoToAttach : inDetalleMovimientoListNew) {
                inDetalleMovimientoListNewInDetalleMovimientoToAttach = em.getReference(inDetalleMovimientoListNewInDetalleMovimientoToAttach.getClass(), inDetalleMovimientoListNewInDetalleMovimientoToAttach.getIdDetalleMovimiento());
                attachedInDetalleMovimientoListNew.add(inDetalleMovimientoListNewInDetalleMovimientoToAttach);
            }
            inDetalleMovimientoListNew = attachedInDetalleMovimientoListNew;
            inMovimientos.setInDetalleMovimientoList(inDetalleMovimientoListNew);
            inMovimientos = em.merge(inMovimientos);
            if (coOrdenComprasOld != null && !coOrdenComprasOld.equals(coOrdenComprasNew)) {
                coOrdenComprasOld.getInMovimientosList().remove(inMovimientos);
                coOrdenComprasOld = em.merge(coOrdenComprasOld);
            }
            if (coOrdenComprasNew != null && !coOrdenComprasNew.equals(coOrdenComprasOld)) {
                coOrdenComprasNew.getInMovimientosList().add(inMovimientos);
                coOrdenComprasNew = em.merge(coOrdenComprasNew);
            }
            if (inBodegaOld != null && !inBodegaOld.equals(inBodegaNew)) {
                inBodegaOld.getInMovimientosList().remove(inMovimientos);
                inBodegaOld = em.merge(inBodegaOld);
            }
            if (inBodegaNew != null && !inBodegaNew.equals(inBodegaOld)) {
                inBodegaNew.getInMovimientosList().add(inMovimientos);
                inBodegaNew = em.merge(inBodegaNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getInMovimientosList().remove(inMovimientos);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getInMovimientosList().add(inMovimientos);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (inTipoDocumentoOld != null && !inTipoDocumentoOld.equals(inTipoDocumentoNew)) {
                inTipoDocumentoOld.getInMovimientosList().remove(inMovimientos);
                inTipoDocumentoOld = em.merge(inTipoDocumentoOld);
            }
            if (inTipoDocumentoNew != null && !inTipoDocumentoNew.equals(inTipoDocumentoOld)) {
                inTipoDocumentoNew.getInMovimientosList().add(inMovimientos);
                inTipoDocumentoNew = em.merge(inTipoDocumentoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getInMovimientosList().remove(inMovimientos);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getInMovimientosList().add(inMovimientos);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (InDetalleMovimiento inDetalleMovimientoListOldInDetalleMovimiento : inDetalleMovimientoListOld) {
                if (!inDetalleMovimientoListNew.contains(inDetalleMovimientoListOldInDetalleMovimiento)) {
                    inDetalleMovimientoListOldInDetalleMovimiento.setInMovimientos(null);
                    inDetalleMovimientoListOldInDetalleMovimiento = em.merge(inDetalleMovimientoListOldInDetalleMovimiento);
                }
            }
            for (InDetalleMovimiento inDetalleMovimientoListNewInDetalleMovimiento : inDetalleMovimientoListNew) {
                if (!inDetalleMovimientoListOld.contains(inDetalleMovimientoListNewInDetalleMovimiento)) {
                    InMovimientos oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento = inDetalleMovimientoListNewInDetalleMovimiento.getInMovimientos();
                    inDetalleMovimientoListNewInDetalleMovimiento.setInMovimientos(inMovimientos);
                    inDetalleMovimientoListNewInDetalleMovimiento = em.merge(inDetalleMovimientoListNewInDetalleMovimiento);
                    if (oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento != null && !oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento.equals(inMovimientos)) {
                        oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento.getInDetalleMovimientoList().remove(inDetalleMovimientoListNewInDetalleMovimiento);
                        oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento = em.merge(oldInMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento);
                    }
                }
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
            CoOrdenCompras coOrdenCompras = inMovimientos.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras.getInMovimientosList().remove(inMovimientos);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            InBodega inBodega = inMovimientos.getInBodega();
            if (inBodega != null) {
                inBodega.getInMovimientosList().remove(inMovimientos);
                inBodega = em.merge(inBodega);
            }
            SePersonas idProveedor = inMovimientos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getInMovimientosList().remove(inMovimientos);
                idProveedor = em.merge(idProveedor);
            }
            InTipoDocumento inTipoDocumento = inMovimientos.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento.getInMovimientosList().remove(inMovimientos);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            SeUsuarios idUsuario = inMovimientos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getInMovimientosList().remove(inMovimientos);
                idUsuario = em.merge(idUsuario);
            }
            List<InDetalleMovimiento> inDetalleMovimientoList = inMovimientos.getInDetalleMovimientoList();
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimiento : inDetalleMovimientoList) {
                inDetalleMovimientoListInDetalleMovimiento.setInMovimientos(null);
                inDetalleMovimientoListInDetalleMovimiento = em.merge(inDetalleMovimientoListInDetalleMovimiento);
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
