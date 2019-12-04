/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class CoCotizacionesPorProveedorJpaController implements Serializable {

    public CoCotizacionesPorProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoCotizacionesPorProveedor coCotizacionesPorProveedor) {
        if (coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList() == null) {
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(new ArrayList<CoDetalleCotizacionPorProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorList = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach : coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList()) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getIdCotizacionesPorPorveedor());
                attachedCoDetalleCotizacionPorProveedorList.add(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach);
            }
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(attachedCoDetalleCotizacionPorProveedorList);
            em.persist(coCotizacionesPorProveedor);
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList()) {
                CoCotizacionesPorProveedor oldIdCotizacionOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getIdCotizacion();
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setIdCotizacion(coCotizacionesPorProveedor);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                if (oldIdCotizacionOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor != null) {
                    oldIdCotizacionOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                    oldIdCotizacionOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(oldIdCotizacionOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoCotizacionesPorProveedor coCotizacionesPorProveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor persistentCoCotizacionesPorProveedor = em.find(CoCotizacionesPorProveedor.class, coCotizacionesPorProveedor.getIdCotizacionesPorPorveedor());
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOld = persistentCoCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListNew = coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorListNew = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach : coDetalleCotizacionPorProveedorListNew) {
                coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getIdCotizacionesPorPorveedor());
                attachedCoDetalleCotizacionPorProveedorListNew.add(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach);
            }
            coDetalleCotizacionPorProveedorListNew = attachedCoDetalleCotizacionPorProveedorListNew;
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(coDetalleCotizacionPorProveedorListNew);
            coCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedor);
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOld) {
                if (!coDetalleCotizacionPorProveedorListNew.contains(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor)) {
                    coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor.setIdCotizacion(null);
                    coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor);
                }
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListNew) {
                if (!coDetalleCotizacionPorProveedorListOld.contains(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor)) {
                    CoCotizacionesPorProveedor oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getIdCotizacion();
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.setIdCotizacion(coCotizacionesPorProveedor);
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    if (oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor != null && !oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.equals(coCotizacionesPorProveedor)) {
                        oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                        oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(oldIdCotizacionOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = coCotizacionesPorProveedor.getIdCotizacionesPorPorveedor();
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

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor coCotizacionesPorProveedor;
            try {
                coCotizacionesPorProveedor = em.getReference(CoCotizacionesPorProveedor.class, id);
                coCotizacionesPorProveedor.getIdCotizacionesPorPorveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coCotizacionesPorProveedor with id " + id + " no longer exists.", enfe);
            }
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorList = coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorList) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setIdCotizacion(null);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
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

    public CoCotizacionesPorProveedor findCoCotizacionesPorProveedor(Long id) {
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
