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
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenComprasPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoOrdenComprasJpaController implements Serializable {

    public CoOrdenComprasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoOrdenCompras coOrdenCompras) throws PreexistingEntityException, Exception {
        if (coOrdenCompras.getCoOrdenComprasPK() == null) {
            coOrdenCompras.setCoOrdenComprasPK(new CoOrdenComprasPK());
        }
        if (coOrdenCompras.getCoDetalleOrdenCompraList() == null) {
            coOrdenCompras.setCoDetalleOrdenCompraList(new ArrayList<CoDetalleOrdenCompra>());
        }
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoProveedores idProveedor = coOrdenCompras.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                coOrdenCompras.setIdProveedor(idProveedor);
            }
            InTipoDocumento idTipoDocumento = coOrdenCompras.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento = em.getReference(idTipoDocumento.getClass(), idTipoDocumento.getIdTipoDocumento());
                coOrdenCompras.setIdTipoDocumento(idTipoDocumento);
            }
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursal);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraList = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach : coOrdenCompras.getCoDetalleOrdenCompraList()) {
                coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getIdDetalleOrdenCompra());
                attachedCoDetalleOrdenCompraList.add(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach);
            }
            coOrdenCompras.setCoDetalleOrdenCompraList(attachedCoDetalleOrdenCompraList);
            em.persist(coOrdenCompras);
            if (idProveedor != null) {
                idProveedor.getCoOrdenComprasList().add(coOrdenCompras);
                idProveedor = em.merge(idProveedor);
            }
            if (idTipoDocumento != null) {
                idTipoDocumento.getCoOrdenComprasList().add(coOrdenCompras);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompra : coOrdenCompras.getCoDetalleOrdenCompraList()) {
                CoOrdenCompras oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra = coDetalleOrdenCompraListCoDetalleOrdenCompra.getCoOrdenCompras();
                coDetalleOrdenCompraListCoDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
                coDetalleOrdenCompraListCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListCoDetalleOrdenCompra);
                if (oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra != null) {
                    oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompraListCoDetalleOrdenCompra);
                    oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra = em.merge(oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoOrdenCompras(coOrdenCompras.getCoOrdenComprasPK()) != null) {
                throw new PreexistingEntityException("CoOrdenCompras " + coOrdenCompras + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoOrdenCompras coOrdenCompras) throws NonexistentEntityException, Exception {
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras persistentCoOrdenCompras = em.find(CoOrdenCompras.class, coOrdenCompras.getCoOrdenComprasPK());
            CoProveedores idProveedorOld = persistentCoOrdenCompras.getIdProveedor();
            CoProveedores idProveedorNew = coOrdenCompras.getIdProveedor();
            InTipoDocumento idTipoDocumentoOld = persistentCoOrdenCompras.getIdTipoDocumento();
            InTipoDocumento idTipoDocumentoNew = coOrdenCompras.getIdTipoDocumento();
            SeSucursal seSucursalOld = persistentCoOrdenCompras.getSeSucursal();
            SeSucursal seSucursalNew = coOrdenCompras.getSeSucursal();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListOld = persistentCoOrdenCompras.getCoDetalleOrdenCompraList();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListNew = coOrdenCompras.getCoDetalleOrdenCompraList();
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                coOrdenCompras.setIdProveedor(idProveedorNew);
            }
            if (idTipoDocumentoNew != null) {
                idTipoDocumentoNew = em.getReference(idTipoDocumentoNew.getClass(), idTipoDocumentoNew.getIdTipoDocumento());
                coOrdenCompras.setIdTipoDocumento(idTipoDocumentoNew);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursalNew);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraListNew = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach : coDetalleOrdenCompraListNew) {
                coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getIdDetalleOrdenCompra());
                attachedCoDetalleOrdenCompraListNew.add(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach);
            }
            coDetalleOrdenCompraListNew = attachedCoDetalleOrdenCompraListNew;
            coOrdenCompras.setCoDetalleOrdenCompraList(coDetalleOrdenCompraListNew);
            coOrdenCompras = em.merge(coOrdenCompras);
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getCoOrdenComprasList().remove(coOrdenCompras);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getCoOrdenComprasList().add(coOrdenCompras);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (idTipoDocumentoOld != null && !idTipoDocumentoOld.equals(idTipoDocumentoNew)) {
                idTipoDocumentoOld.getCoOrdenComprasList().remove(coOrdenCompras);
                idTipoDocumentoOld = em.merge(idTipoDocumentoOld);
            }
            if (idTipoDocumentoNew != null && !idTipoDocumentoNew.equals(idTipoDocumentoOld)) {
                idTipoDocumentoNew.getCoOrdenComprasList().add(coOrdenCompras);
                idTipoDocumentoNew = em.merge(idTipoDocumentoNew);
            }
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListOldCoDetalleOrdenCompra : coDetalleOrdenCompraListOld) {
                if (!coDetalleOrdenCompraListNew.contains(coDetalleOrdenCompraListOldCoDetalleOrdenCompra)) {
                    coDetalleOrdenCompraListOldCoDetalleOrdenCompra.setCoOrdenCompras(null);
                    coDetalleOrdenCompraListOldCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListOldCoDetalleOrdenCompra);
                }
            }
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListNewCoDetalleOrdenCompra : coDetalleOrdenCompraListNew) {
                if (!coDetalleOrdenCompraListOld.contains(coDetalleOrdenCompraListNewCoDetalleOrdenCompra)) {
                    CoOrdenCompras oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra = coDetalleOrdenCompraListNewCoDetalleOrdenCompra.getCoOrdenCompras();
                    coDetalleOrdenCompraListNewCoDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
                    coDetalleOrdenCompraListNewCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                    if (oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra != null && !oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra.equals(coOrdenCompras)) {
                        oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                        oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra = em.merge(oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoOrdenComprasPK id = coOrdenCompras.getCoOrdenComprasPK();
                if (findCoOrdenCompras(id) == null) {
                    throw new NonexistentEntityException("The coOrdenCompras with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoOrdenComprasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras coOrdenCompras;
            try {
                coOrdenCompras = em.getReference(CoOrdenCompras.class, id);
                coOrdenCompras.getCoOrdenComprasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coOrdenCompras with id " + id + " no longer exists.", enfe);
            }
            CoProveedores idProveedor = coOrdenCompras.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getCoOrdenComprasList().remove(coOrdenCompras);
                idProveedor = em.merge(idProveedor);
            }
            InTipoDocumento idTipoDocumento = coOrdenCompras.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento.getCoOrdenComprasList().remove(coOrdenCompras);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraList = coOrdenCompras.getCoDetalleOrdenCompraList();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompra : coDetalleOrdenCompraList) {
                coDetalleOrdenCompraListCoDetalleOrdenCompra.setCoOrdenCompras(null);
                coDetalleOrdenCompraListCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListCoDetalleOrdenCompra);
            }
            em.remove(coOrdenCompras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoOrdenCompras> findCoOrdenComprasEntities() {
        return findCoOrdenComprasEntities(true, -1, -1);
    }

    public List<CoOrdenCompras> findCoOrdenComprasEntities(int maxResults, int firstResult) {
        return findCoOrdenComprasEntities(false, maxResults, firstResult);
    }

    private List<CoOrdenCompras> findCoOrdenComprasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoOrdenCompras.class));
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

    public CoOrdenCompras findCoOrdenCompras(CoOrdenComprasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoOrdenCompras.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoOrdenComprasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoOrdenCompras> rt = cq.from(CoOrdenCompras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
