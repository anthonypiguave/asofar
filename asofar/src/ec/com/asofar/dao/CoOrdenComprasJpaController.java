/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenComprasPK;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InMovimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
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
        if (coOrdenCompras.getInMovimientosList() == null) {
            coOrdenCompras.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursal);
            }
            SePersonas idProveedor = coOrdenCompras.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdPersona());
                coOrdenCompras.setIdProveedor(idProveedor);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraList = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach : coOrdenCompras.getCoDetalleOrdenCompraList()) {
                coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getIdDetalleOrdenCompra());
                attachedCoDetalleOrdenCompraList.add(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach);
            }
            coOrdenCompras.setCoDetalleOrdenCompraList(attachedCoDetalleOrdenCompraList);
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : coOrdenCompras.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            coOrdenCompras.setInMovimientosList(attachedInMovimientosList);
            em.persist(coOrdenCompras);
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            if (idProveedor != null) {
                idProveedor.getCoOrdenComprasList().add(coOrdenCompras);
                idProveedor = em.merge(idProveedor);
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
            for (InMovimientos inMovimientosListInMovimientos : coOrdenCompras.getInMovimientosList()) {
                CoOrdenCompras oldCoOrdenComprasOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getCoOrdenCompras();
                inMovimientosListInMovimientos.setCoOrdenCompras(coOrdenCompras);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldCoOrdenComprasOfInMovimientosListInMovimientos != null) {
                    oldCoOrdenComprasOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldCoOrdenComprasOfInMovimientosListInMovimientos = em.merge(oldCoOrdenComprasOfInMovimientosListInMovimientos);
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

    public void edit(CoOrdenCompras coOrdenCompras) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras persistentCoOrdenCompras = em.find(CoOrdenCompras.class, coOrdenCompras.getCoOrdenComprasPK());
            SeSucursal seSucursalOld = persistentCoOrdenCompras.getSeSucursal();
            SeSucursal seSucursalNew = coOrdenCompras.getSeSucursal();
            SePersonas idProveedorOld = persistentCoOrdenCompras.getIdProveedor();
            SePersonas idProveedorNew = coOrdenCompras.getIdProveedor();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListOld = persistentCoOrdenCompras.getCoDetalleOrdenCompraList();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListNew = coOrdenCompras.getCoDetalleOrdenCompraList();
            List<InMovimientos> inMovimientosListOld = persistentCoOrdenCompras.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = coOrdenCompras.getInMovimientosList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its coOrdenCompras field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursalNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdPersona());
                coOrdenCompras.setIdProveedor(idProveedorNew);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraListNew = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach : coDetalleOrdenCompraListNew) {
                coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getIdDetalleOrdenCompra());
                attachedCoDetalleOrdenCompraListNew.add(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach);
            }
            coDetalleOrdenCompraListNew = attachedCoDetalleOrdenCompraListNew;
            coOrdenCompras.setCoDetalleOrdenCompraList(coDetalleOrdenCompraListNew);
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            coOrdenCompras.setInMovimientosList(inMovimientosListNew);
            coOrdenCompras = em.merge(coOrdenCompras);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getCoOrdenComprasList().remove(coOrdenCompras);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getCoOrdenComprasList().add(coOrdenCompras);
                idProveedorNew = em.merge(idProveedorNew);
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
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    CoOrdenCompras oldCoOrdenComprasOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getCoOrdenCompras();
                    inMovimientosListNewInMovimientos.setCoOrdenCompras(coOrdenCompras);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldCoOrdenComprasOfInMovimientosListNewInMovimientos != null && !oldCoOrdenComprasOfInMovimientosListNewInMovimientos.equals(coOrdenCompras)) {
                        oldCoOrdenComprasOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldCoOrdenComprasOfInMovimientosListNewInMovimientos = em.merge(oldCoOrdenComprasOfInMovimientosListNewInMovimientos);
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

    public void destroy(CoOrdenComprasPK id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = coOrdenCompras.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoOrdenCompras (" + coOrdenCompras + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable coOrdenCompras field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            SePersonas idProveedor = coOrdenCompras.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getCoOrdenComprasList().remove(coOrdenCompras);
                idProveedor = em.merge(idProveedor);
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
