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
import ec.com.asofar.dto.CoDetCotizacionPorProveedor;
import ec.com.asofar.dto.CoDetCotizacionPorProveedorPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoDetCotizacionPorProveedorJpaController implements Serializable {

    public CoDetCotizacionPorProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetCotizacionPorProveedor coDetCotizacionPorProveedor) throws PreexistingEntityException, Exception {
        if (coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK() == null) {
            coDetCotizacionPorProveedor.setCoDetCotizacionPorProveedorPK(new CoDetCotizacionPorProveedorPK());
        }
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdCotizacion(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacion());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacionesPorPorveedor());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdEmpresa(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdEmpresa());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdSucursal(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor = coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor = em.getReference(coCotizacionesPorPorveedor.getClass(), coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK());
                coDetCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor);
            }
            em.persist(coDetCotizacionPorProveedor);
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor.getCoDetCotizacionPorProveedorList().add(coDetCotizacionPorProveedor);
                coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetCotizacionPorProveedor(coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK()) != null) {
                throw new PreexistingEntityException("CoDetCotizacionPorProveedor " + coDetCotizacionPorProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetCotizacionPorProveedor coDetCotizacionPorProveedor) throws NonexistentEntityException, Exception {
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdCotizacion(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacion());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdCotizacionesPorPorveedor(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdCotizacionesPorPorveedor());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdEmpresa(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdEmpresa());
        coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK().setIdSucursal(coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor().getCoCotizacionesPorPorveedorPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetCotizacionPorProveedor persistentCoDetCotizacionPorProveedor = em.find(CoDetCotizacionPorProveedor.class, coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK());
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedorOld = persistentCoDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedorNew = coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedorNew != null) {
                coCotizacionesPorPorveedorNew = em.getReference(coCotizacionesPorPorveedorNew.getClass(), coCotizacionesPorPorveedorNew.getCoCotizacionesPorPorveedorPK());
                coDetCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedorNew);
            }
            coDetCotizacionPorProveedor = em.merge(coDetCotizacionPorProveedor);
            if (coCotizacionesPorPorveedorOld != null && !coCotizacionesPorPorveedorOld.equals(coCotizacionesPorPorveedorNew)) {
                coCotizacionesPorPorveedorOld.getCoDetCotizacionPorProveedorList().remove(coDetCotizacionPorProveedor);
                coCotizacionesPorPorveedorOld = em.merge(coCotizacionesPorPorveedorOld);
            }
            if (coCotizacionesPorPorveedorNew != null && !coCotizacionesPorPorveedorNew.equals(coCotizacionesPorPorveedorOld)) {
                coCotizacionesPorPorveedorNew.getCoDetCotizacionPorProveedorList().add(coDetCotizacionPorProveedor);
                coCotizacionesPorPorveedorNew = em.merge(coCotizacionesPorPorveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetCotizacionPorProveedorPK id = coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK();
                if (findCoDetCotizacionPorProveedor(id) == null) {
                    throw new NonexistentEntityException("The coDetCotizacionPorProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetCotizacionPorProveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetCotizacionPorProveedor coDetCotizacionPorProveedor;
            try {
                coDetCotizacionPorProveedor = em.getReference(CoDetCotizacionPorProveedor.class, id);
                coDetCotizacionPorProveedor.getCoDetCotizacionPorProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetCotizacionPorProveedor with id " + id + " no longer exists.", enfe);
            }
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor = coDetCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
            if (coCotizacionesPorPorveedor != null) {
                coCotizacionesPorPorveedor.getCoDetCotizacionPorProveedorList().remove(coDetCotizacionPorProveedor);
                coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
            }
            em.remove(coDetCotizacionPorProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetCotizacionPorProveedor> findCoDetCotizacionPorProveedorEntities() {
        return findCoDetCotizacionPorProveedorEntities(true, -1, -1);
    }

    public List<CoDetCotizacionPorProveedor> findCoDetCotizacionPorProveedorEntities(int maxResults, int firstResult) {
        return findCoDetCotizacionPorProveedorEntities(false, maxResults, firstResult);
    }

    private List<CoDetCotizacionPorProveedor> findCoDetCotizacionPorProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetCotizacionPorProveedor.class));
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

    public CoDetCotizacionPorProveedor findCoDetCotizacionPorProveedor(CoDetCotizacionPorProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetCotizacionPorProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetCotizacionPorProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetCotizacionPorProveedor> rt = cq.from(CoDetCotizacionPorProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
