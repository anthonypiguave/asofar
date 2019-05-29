/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoCotizacionesPorPorveedor;
import ec.com.asofar.dto.CoCotizacionesPorPorveedorPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author admin1
 */
public class CoCotizacionesPorPorveedorJpaController implements Serializable {

    public CoCotizacionesPorPorveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoCotizacionesPorPorveedor coCotizacionesPorPorveedor) throws PreexistingEntityException, Exception {
        if (coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK() == null) {
            coCotizacionesPorPorveedor.setCoCotizacionesPorPorveedorPK(new CoCotizacionesPorPorveedorPK());
        }
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdSucursal(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdCotizacion(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdEmpresa(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(coCotizacionesPorPorveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK()) != null) {
                throw new PreexistingEntityException("CoCotizacionesPorPorveedor " + coCotizacionesPorPorveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoCotizacionesPorPorveedor coCotizacionesPorPorveedor) throws NonexistentEntityException, Exception {
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdSucursal(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdCotizacion(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdEmpresa(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoCotizacionesPorPorveedorPK id = coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK();
                if (findCoCotizacionesPorPorveedor(id) == null) {
                    throw new NonexistentEntityException("The coCotizacionesPorPorveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoCotizacionesPorPorveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor;
            try {
                coCotizacionesPorPorveedor = em.getReference(CoCotizacionesPorPorveedor.class, id);
                coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coCotizacionesPorPorveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(coCotizacionesPorPorveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities() {
        return findCoCotizacionesPorPorveedorEntities(true, -1, -1);
    }

    public List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities(int maxResults, int firstResult) {
        return findCoCotizacionesPorPorveedorEntities(false, maxResults, firstResult);
    }

    private List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoCotizacionesPorPorveedor.class));
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

    public CoCotizacionesPorPorveedor findCoCotizacionesPorPorveedor(CoCotizacionesPorPorveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoCotizacionesPorPorveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoCotizacionesPorPorveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoCotizacionesPorPorveedor> rt = cq.from(CoCotizacionesPorPorveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
