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
import ec.com.asofar.dto.CoOrdenCompras;
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
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class InMovimientosJpaController implements Serializable {

    public InMovimientosJpaController() {
       this.emf = Persistence.createEntityManagerFactory("asofarPU");
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
        inMovimientos.getInMovimientosPK().setIdOrdenCompra(inMovimientos.getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra());
        inMovimientos.getInMovimientosPK().setIdBodega(inMovimientos.getInBodega().getInBodegaPK().getIdBodega());
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getInBodega().getInBodegaPK().getIdEmpresa());
        inMovimientos.getInMovimientosPK().setIdUsuario(inMovimientos.getSeUsuarios().getSeUsuariosPK().getIdUsuario());
        inMovimientos.getInMovimientosPK().setIdProveedor(inMovimientos.getSePersonas().getSePersonasPK().getIdPersona());
        inMovimientos.getInMovimientosPK().setIdSucursal(inMovimientos.getInBodega().getInBodegaPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega inBodega = inMovimientos.getInBodega();
            if (inBodega != null) {
                inBodega = em.getReference(inBodega.getClass(), inBodega.getInBodegaPK());
                inMovimientos.setInBodega(inBodega);
            }
            CoOrdenCompras coOrdenCompras = inMovimientos.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras = em.getReference(coOrdenCompras.getClass(), coOrdenCompras.getCoOrdenComprasPK());
                inMovimientos.setCoOrdenCompras(coOrdenCompras);
            }
            SePersonas sePersonas = inMovimientos.getSePersonas();
            if (sePersonas != null) {
                sePersonas = em.getReference(sePersonas.getClass(), sePersonas.getSePersonasPK());
                inMovimientos.setSePersonas(sePersonas);
            }
            InTipoDocumento idNumeroDocumento = inMovimientos.getIdNumeroDocumento();
            if (idNumeroDocumento != null) {
                idNumeroDocumento = em.getReference(idNumeroDocumento.getClass(), idNumeroDocumento.getIdTipoDocumento());
                inMovimientos.setIdNumeroDocumento(idNumeroDocumento);
            }
            SeUsuarios seUsuarios = inMovimientos.getSeUsuarios();
            if (seUsuarios != null) {
                seUsuarios = em.getReference(seUsuarios.getClass(), seUsuarios.getSeUsuariosPK());
                inMovimientos.setSeUsuarios(seUsuarios);
            }
            List<InDetalleMovimiento> attachedInDetalleMovimientoList = new ArrayList<InDetalleMovimiento>();
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimientoToAttach : inMovimientos.getInDetalleMovimientoList()) {
                inDetalleMovimientoListInDetalleMovimientoToAttach = em.getReference(inDetalleMovimientoListInDetalleMovimientoToAttach.getClass(), inDetalleMovimientoListInDetalleMovimientoToAttach.getIdDetalleMovimiento());
                attachedInDetalleMovimientoList.add(inDetalleMovimientoListInDetalleMovimientoToAttach);
            }
            inMovimientos.setInDetalleMovimientoList(attachedInDetalleMovimientoList);
            em.persist(inMovimientos);
            if (inBodega != null) {
                inBodega.getInMovimientosList().add(inMovimientos);
                inBodega = em.merge(inBodega);
            }
            if (coOrdenCompras != null) {
                coOrdenCompras.getInMovimientosList().add(inMovimientos);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            if (sePersonas != null) {
                sePersonas.getInMovimientosList().add(inMovimientos);
                sePersonas = em.merge(sePersonas);
            }
            if (idNumeroDocumento != null) {
                idNumeroDocumento.getInMovimientosList().add(inMovimientos);
                idNumeroDocumento = em.merge(idNumeroDocumento);
            }
            if (seUsuarios != null) {
                seUsuarios.getInMovimientosList().add(inMovimientos);
                seUsuarios = em.merge(seUsuarios);
            }
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimiento : inMovimientos.getInDetalleMovimientoList()) {
                InMovimientos oldIdMovimientosOfInDetalleMovimientoListInDetalleMovimiento = inDetalleMovimientoListInDetalleMovimiento.getIdMovimientos();
                inDetalleMovimientoListInDetalleMovimiento.setIdMovimientos(inMovimientos);
                inDetalleMovimientoListInDetalleMovimiento = em.merge(inDetalleMovimientoListInDetalleMovimiento);
                if (oldIdMovimientosOfInDetalleMovimientoListInDetalleMovimiento != null) {
                    oldIdMovimientosOfInDetalleMovimientoListInDetalleMovimiento.getInDetalleMovimientoList().remove(inDetalleMovimientoListInDetalleMovimiento);
                    oldIdMovimientosOfInDetalleMovimientoListInDetalleMovimiento = em.merge(oldIdMovimientosOfInDetalleMovimientoListInDetalleMovimiento);
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
        inMovimientos.getInMovimientosPK().setIdOrdenCompra(inMovimientos.getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra());
        inMovimientos.getInMovimientosPK().setIdBodega(inMovimientos.getInBodega().getInBodegaPK().getIdBodega());
        inMovimientos.getInMovimientosPK().setIdEmpresa(inMovimientos.getInBodega().getInBodegaPK().getIdEmpresa());
        inMovimientos.getInMovimientosPK().setIdUsuario(inMovimientos.getSeUsuarios().getSeUsuariosPK().getIdUsuario());
        inMovimientos.getInMovimientosPK().setIdProveedor(inMovimientos.getSePersonas().getSePersonasPK().getIdPersona());
        inMovimientos.getInMovimientosPK().setIdSucursal(inMovimientos.getInBodega().getInBodegaPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMovimientos persistentInMovimientos = em.find(InMovimientos.class, inMovimientos.getInMovimientosPK());
            InBodega inBodegaOld = persistentInMovimientos.getInBodega();
            InBodega inBodegaNew = inMovimientos.getInBodega();
            CoOrdenCompras coOrdenComprasOld = persistentInMovimientos.getCoOrdenCompras();
            CoOrdenCompras coOrdenComprasNew = inMovimientos.getCoOrdenCompras();
            SePersonas sePersonasOld = persistentInMovimientos.getSePersonas();
            SePersonas sePersonasNew = inMovimientos.getSePersonas();
            InTipoDocumento idNumeroDocumentoOld = persistentInMovimientos.getIdNumeroDocumento();
            InTipoDocumento idNumeroDocumentoNew = inMovimientos.getIdNumeroDocumento();
            SeUsuarios seUsuariosOld = persistentInMovimientos.getSeUsuarios();
            SeUsuarios seUsuariosNew = inMovimientos.getSeUsuarios();
            List<InDetalleMovimiento> inDetalleMovimientoListOld = persistentInMovimientos.getInDetalleMovimientoList();
            List<InDetalleMovimiento> inDetalleMovimientoListNew = inMovimientos.getInDetalleMovimientoList();
            if (inBodegaNew != null) {
                inBodegaNew = em.getReference(inBodegaNew.getClass(), inBodegaNew.getInBodegaPK());
                inMovimientos.setInBodega(inBodegaNew);
            }
            if (coOrdenComprasNew != null) {
                coOrdenComprasNew = em.getReference(coOrdenComprasNew.getClass(), coOrdenComprasNew.getCoOrdenComprasPK());
                inMovimientos.setCoOrdenCompras(coOrdenComprasNew);
            }
            if (sePersonasNew != null) {
                sePersonasNew = em.getReference(sePersonasNew.getClass(), sePersonasNew.getSePersonasPK());
                inMovimientos.setSePersonas(sePersonasNew);
            }
            if (idNumeroDocumentoNew != null) {
                idNumeroDocumentoNew = em.getReference(idNumeroDocumentoNew.getClass(), idNumeroDocumentoNew.getIdTipoDocumento());
                inMovimientos.setIdNumeroDocumento(idNumeroDocumentoNew);
            }
            if (seUsuariosNew != null) {
                seUsuariosNew = em.getReference(seUsuariosNew.getClass(), seUsuariosNew.getSeUsuariosPK());
                inMovimientos.setSeUsuarios(seUsuariosNew);
            }
            List<InDetalleMovimiento> attachedInDetalleMovimientoListNew = new ArrayList<InDetalleMovimiento>();
            for (InDetalleMovimiento inDetalleMovimientoListNewInDetalleMovimientoToAttach : inDetalleMovimientoListNew) {
                inDetalleMovimientoListNewInDetalleMovimientoToAttach = em.getReference(inDetalleMovimientoListNewInDetalleMovimientoToAttach.getClass(), inDetalleMovimientoListNewInDetalleMovimientoToAttach.getIdDetalleMovimiento());
                attachedInDetalleMovimientoListNew.add(inDetalleMovimientoListNewInDetalleMovimientoToAttach);
            }
            inDetalleMovimientoListNew = attachedInDetalleMovimientoListNew;
            inMovimientos.setInDetalleMovimientoList(inDetalleMovimientoListNew);
            inMovimientos = em.merge(inMovimientos);
            if (inBodegaOld != null && !inBodegaOld.equals(inBodegaNew)) {
                inBodegaOld.getInMovimientosList().remove(inMovimientos);
                inBodegaOld = em.merge(inBodegaOld);
            }
            if (inBodegaNew != null && !inBodegaNew.equals(inBodegaOld)) {
                inBodegaNew.getInMovimientosList().add(inMovimientos);
                inBodegaNew = em.merge(inBodegaNew);
            }
            if (coOrdenComprasOld != null && !coOrdenComprasOld.equals(coOrdenComprasNew)) {
                coOrdenComprasOld.getInMovimientosList().remove(inMovimientos);
                coOrdenComprasOld = em.merge(coOrdenComprasOld);
            }
            if (coOrdenComprasNew != null && !coOrdenComprasNew.equals(coOrdenComprasOld)) {
                coOrdenComprasNew.getInMovimientosList().add(inMovimientos);
                coOrdenComprasNew = em.merge(coOrdenComprasNew);
            }
            if (sePersonasOld != null && !sePersonasOld.equals(sePersonasNew)) {
                sePersonasOld.getInMovimientosList().remove(inMovimientos);
                sePersonasOld = em.merge(sePersonasOld);
            }
            if (sePersonasNew != null && !sePersonasNew.equals(sePersonasOld)) {
                sePersonasNew.getInMovimientosList().add(inMovimientos);
                sePersonasNew = em.merge(sePersonasNew);
            }
            if (idNumeroDocumentoOld != null && !idNumeroDocumentoOld.equals(idNumeroDocumentoNew)) {
                idNumeroDocumentoOld.getInMovimientosList().remove(inMovimientos);
                idNumeroDocumentoOld = em.merge(idNumeroDocumentoOld);
            }
            if (idNumeroDocumentoNew != null && !idNumeroDocumentoNew.equals(idNumeroDocumentoOld)) {
                idNumeroDocumentoNew.getInMovimientosList().add(inMovimientos);
                idNumeroDocumentoNew = em.merge(idNumeroDocumentoNew);
            }
            if (seUsuariosOld != null && !seUsuariosOld.equals(seUsuariosNew)) {
                seUsuariosOld.getInMovimientosList().remove(inMovimientos);
                seUsuariosOld = em.merge(seUsuariosOld);
            }
            if (seUsuariosNew != null && !seUsuariosNew.equals(seUsuariosOld)) {
                seUsuariosNew.getInMovimientosList().add(inMovimientos);
                seUsuariosNew = em.merge(seUsuariosNew);
            }
            for (InDetalleMovimiento inDetalleMovimientoListOldInDetalleMovimiento : inDetalleMovimientoListOld) {
                if (!inDetalleMovimientoListNew.contains(inDetalleMovimientoListOldInDetalleMovimiento)) {
                    inDetalleMovimientoListOldInDetalleMovimiento.setIdMovimientos(null);
                    inDetalleMovimientoListOldInDetalleMovimiento = em.merge(inDetalleMovimientoListOldInDetalleMovimiento);
                }
            }
            for (InDetalleMovimiento inDetalleMovimientoListNewInDetalleMovimiento : inDetalleMovimientoListNew) {
                if (!inDetalleMovimientoListOld.contains(inDetalleMovimientoListNewInDetalleMovimiento)) {
                    InMovimientos oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento = inDetalleMovimientoListNewInDetalleMovimiento.getIdMovimientos();
                    inDetalleMovimientoListNewInDetalleMovimiento.setIdMovimientos(inMovimientos);
                    inDetalleMovimientoListNewInDetalleMovimiento = em.merge(inDetalleMovimientoListNewInDetalleMovimiento);
                    if (oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento != null && !oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento.equals(inMovimientos)) {
                        oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento.getInDetalleMovimientoList().remove(inDetalleMovimientoListNewInDetalleMovimiento);
                        oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento = em.merge(oldIdMovimientosOfInDetalleMovimientoListNewInDetalleMovimiento);
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
            InBodega inBodega = inMovimientos.getInBodega();
            if (inBodega != null) {
                inBodega.getInMovimientosList().remove(inMovimientos);
                inBodega = em.merge(inBodega);
            }
            CoOrdenCompras coOrdenCompras = inMovimientos.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras.getInMovimientosList().remove(inMovimientos);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            SePersonas sePersonas = inMovimientos.getSePersonas();
            if (sePersonas != null) {
                sePersonas.getInMovimientosList().remove(inMovimientos);
                sePersonas = em.merge(sePersonas);
            }
            InTipoDocumento idNumeroDocumento = inMovimientos.getIdNumeroDocumento();
            if (idNumeroDocumento != null) {
                idNumeroDocumento.getInMovimientosList().remove(inMovimientos);
                idNumeroDocumento = em.merge(idNumeroDocumento);
            }
            SeUsuarios seUsuarios = inMovimientos.getSeUsuarios();
            if (seUsuarios != null) {
                seUsuarios.getInMovimientosList().remove(inMovimientos);
                seUsuarios = em.merge(seUsuarios);
            }
            List<InDetalleMovimiento> inDetalleMovimientoList = inMovimientos.getInDetalleMovimientoList();
            for (InDetalleMovimiento inDetalleMovimientoListInDetalleMovimiento : inDetalleMovimientoList) {
                inDetalleMovimientoListInDetalleMovimiento.setIdMovimientos(null);
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
