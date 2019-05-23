/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoCotizacionesPorProveedorPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoItemsCotizacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class CoCotizacionesPorProveedorJpaController implements Serializable {

    public CoCotizacionesPorProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoCotizacionesPorProveedor coCotizacionesPorProveedor) throws PreexistingEntityException, Exception {
        if (coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK() == null) {
            coCotizacionesPorProveedor.setCoCotizacionesPorProveedorPK(new CoCotizacionesPorProveedorPK());
        }
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdEmpresa(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdCotizacion(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdSucursal(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorProveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion = em.getReference(coItemsCotizacion.getClass(), coItemsCotizacion.getCoItemsCotizacionPK());
                coCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacion);
            }
            em.persist(coCotizacionesPorProveedor);
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoCotizacionesPorProveedor(coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK()) != null) {
                throw new PreexistingEntityException("CoCotizacionesPorProveedor " + coCotizacionesPorProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoCotizacionesPorProveedor coCotizacionesPorProveedor) throws NonexistentEntityException, Exception {
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdEmpresa(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdCotizacion(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdSucursal(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor persistentCoCotizacionesPorProveedor = em.find(CoCotizacionesPorProveedor.class, coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK());
            CoItemsCotizacion coItemsCotizacionOld = persistentCoCotizacionesPorProveedor.getCoItemsCotizacion();
            CoItemsCotizacion coItemsCotizacionNew = coCotizacionesPorProveedor.getCoItemsCotizacion();
            if (coItemsCotizacionNew != null) {
                coItemsCotizacionNew = em.getReference(coItemsCotizacionNew.getClass(), coItemsCotizacionNew.getCoItemsCotizacionPK());
                coCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacionNew);
            }
            coCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedor);
            if (coItemsCotizacionOld != null && !coItemsCotizacionOld.equals(coItemsCotizacionNew)) {
                coItemsCotizacionOld.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                coItemsCotizacionOld = em.merge(coItemsCotizacionOld);
            }
            if (coItemsCotizacionNew != null && !coItemsCotizacionNew.equals(coItemsCotizacionOld)) {
                coItemsCotizacionNew.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                coItemsCotizacionNew = em.merge(coItemsCotizacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoCotizacionesPorProveedorPK id = coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK();
                if (findCoCotizacionesPorProveedor(id) == null) {
                    throw new NonexistentEntityException("The coCotizacionesPorProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoCotizacionesPorProveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor coCotizacionesPorProveedor;
            try {
                coCotizacionesPorProveedor = em.getReference(CoCotizacionesPorProveedor.class, id);
                coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coCotizacionesPorProveedor with id " + id + " no longer exists.", enfe);
            }
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorProveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            em.remove(coCotizacionesPorProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities() {
        return findCoCotizacionesPorProveedorEntities(true, -1, -1);
    }

    public List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities(int maxResults, int firstResult) {
        return findCoCotizacionesPorProveedorEntities(false, maxResults, firstResult);
    }

    private List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoCotizacionesPorProveedor.class));
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

    public CoCotizacionesPorProveedor findCoCotizacionesPorProveedor(CoCotizacionesPorProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoCotizacionesPorProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoCotizacionesPorProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoCotizacionesPorProveedor> rt = cq.from(CoCotizacionesPorProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
