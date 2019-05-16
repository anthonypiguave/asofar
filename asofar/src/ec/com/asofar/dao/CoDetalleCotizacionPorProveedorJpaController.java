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
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedorPK;
import ec.com.asofar.dto.PrProductos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class CoDetalleCotizacionPorProveedorJpaController implements Serializable {

    public CoDetalleCotizacionPorProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedor) throws PreexistingEntityException, Exception {
        if (coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK() == null) {
            coDetalleCotizacionPorProveedor.setCoDetalleCotizacionPorProveedorPK(new CoDetalleCotizacionPorProveedorPK());
        }
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacion(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdCotizacion());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdSucursal(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdSucursal());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdEmpresa(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdEmpresa());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor coCotizacionesPorProveedor = coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
            if (coCotizacionesPorProveedor != null) {
                coCotizacionesPorProveedor = em.getReference(coCotizacionesPorProveedor.getClass(), coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK());
                coDetalleCotizacionPorProveedor.setCoCotizacionesPorProveedor(coCotizacionesPorProveedor);
            }
            PrProductos idProducto = coDetalleCotizacionPorProveedor.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getPrProductosPK());
                coDetalleCotizacionPorProveedor.setIdProducto(idProducto);
            }
            em.persist(coDetalleCotizacionPorProveedor);
            if (coCotizacionesPorProveedor != null) {
                coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                coCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedor);
            }
            if (idProducto != null) {
                idProducto.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                idProducto = em.merge(idProducto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetalleCotizacionPorProveedor(coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK()) != null) {
                throw new PreexistingEntityException("CoDetalleCotizacionPorProveedor " + coDetalleCotizacionPorProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedor) throws NonexistentEntityException, Exception {
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacion(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdCotizacion());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdSucursal(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdSucursal());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdEmpresa(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdEmpresa());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor().getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleCotizacionPorProveedor persistentCoDetalleCotizacionPorProveedor = em.find(CoDetalleCotizacionPorProveedor.class, coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK());
            CoCotizacionesPorProveedor coCotizacionesPorProveedorOld = persistentCoDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
            CoCotizacionesPorProveedor coCotizacionesPorProveedorNew = coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
            PrProductos idProductoOld = persistentCoDetalleCotizacionPorProveedor.getIdProducto();
            PrProductos idProductoNew = coDetalleCotizacionPorProveedor.getIdProducto();
            if (coCotizacionesPorProveedorNew != null) {
                coCotizacionesPorProveedorNew = em.getReference(coCotizacionesPorProveedorNew.getClass(), coCotizacionesPorProveedorNew.getCoCotizacionesPorProveedorPK());
                coDetalleCotizacionPorProveedor.setCoCotizacionesPorProveedor(coCotizacionesPorProveedorNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getPrProductosPK());
                coDetalleCotizacionPorProveedor.setIdProducto(idProductoNew);
            }
            coDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedor);
            if (coCotizacionesPorProveedorOld != null && !coCotizacionesPorProveedorOld.equals(coCotizacionesPorProveedorNew)) {
                coCotizacionesPorProveedorOld.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                coCotizacionesPorProveedorOld = em.merge(coCotizacionesPorProveedorOld);
            }
            if (coCotizacionesPorProveedorNew != null && !coCotizacionesPorProveedorNew.equals(coCotizacionesPorProveedorOld)) {
                coCotizacionesPorProveedorNew.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                coCotizacionesPorProveedorNew = em.merge(coCotizacionesPorProveedorNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                idProductoNew = em.merge(idProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetalleCotizacionPorProveedorPK id = coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK();
                if (findCoDetalleCotizacionPorProveedor(id) == null) {
                    throw new NonexistentEntityException("The coDetalleCotizacionPorProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetalleCotizacionPorProveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedor;
            try {
                coDetalleCotizacionPorProveedor = em.getReference(CoDetalleCotizacionPorProveedor.class, id);
                coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetalleCotizacionPorProveedor with id " + id + " no longer exists.", enfe);
            }
            CoCotizacionesPorProveedor coCotizacionesPorProveedor = coDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
            if (coCotizacionesPorProveedor != null) {
                coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                coCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedor);
            }
            PrProductos idProducto = coDetalleCotizacionPorProveedor.getIdProducto();
            if (idProducto != null) {
                idProducto.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                idProducto = em.merge(idProducto);
            }
            em.remove(coDetalleCotizacionPorProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetalleCotizacionPorProveedor> findCoDetalleCotizacionPorProveedorEntities() {
        return findCoDetalleCotizacionPorProveedorEntities(true, -1, -1);
    }

    public List<CoDetalleCotizacionPorProveedor> findCoDetalleCotizacionPorProveedorEntities(int maxResults, int firstResult) {
        return findCoDetalleCotizacionPorProveedorEntities(false, maxResults, firstResult);
    }

    private List<CoDetalleCotizacionPorProveedor> findCoDetalleCotizacionPorProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetalleCotizacionPorProveedor.class));
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

    public CoDetalleCotizacionPorProveedor findCoDetalleCotizacionPorProveedor(CoDetalleCotizacionPorProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetalleCotizacionPorProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetalleCotizacionPorProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetalleCotizacionPorProveedor> rt = cq.from(CoDetalleCotizacionPorProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
