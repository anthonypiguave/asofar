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
import ec.com.asofar.dto.CoCotizacionesPorPorveedor;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedorPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
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
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacion(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacion());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacionesPorPorveedor());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdEmpresa(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdEmpresa());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdSucursal(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor = coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor = em.getReference(coCotizacionesPorPorveedor.getClass(), coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK());
                coDetalleCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor);
            }
            em.persist(coDetalleCotizacionPorProveedor);
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
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
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacion(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacion());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacionesPorPorveedor());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdEmpresa(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdEmpresa());
        coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK().setIdSucursal(coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleCotizacionPorProveedor persistentCoDetalleCotizacionPorProveedor = em.find(CoDetalleCotizacionPorProveedor.class, coDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorPK());
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedorOld = persistentCoDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedorNew = coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedorNew != null) {
                coCotizacionesPorPorveedorNew = em.getReference(coCotizacionesPorPorveedorNew.getClass(), coCotizacionesPorPorveedorNew.getCoCotizacionesPorPorveedorPK());
                coDetalleCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedorNew);
            }
            coDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedor);
            if (coCotizacionesPorPorveedorOld != null && !coCotizacionesPorPorveedorOld.equals(coCotizacionesPorPorveedorNew)) {
                coCotizacionesPorPorveedorOld.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                coCotizacionesPorPorveedorOld = em.merge(coCotizacionesPorPorveedorOld);
            }
            if (coCotizacionesPorPorveedorNew != null && !coCotizacionesPorPorveedorNew.equals(coCotizacionesPorPorveedorOld)) {
                coCotizacionesPorPorveedorNew.getCoDetalleCotizacionPorProveedorList().add(coDetalleCotizacionPorProveedor);
                coCotizacionesPorPorveedorNew = em.merge(coCotizacionesPorPorveedorNew);
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
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor = coDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedor);
                coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
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
