/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.CoDetItemsCotizacionPK;
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
 * @author jorge
 */
public class CoDetItemsCotizacionJpaController implements Serializable {

    public CoDetItemsCotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetItemsCotizacion coDetItemsCotizacion) throws PreexistingEntityException, Exception {
        if (coDetItemsCotizacion.getCoDetItemsCotizacionPK() == null) {
            coDetItemsCotizacion.setCoDetItemsCotizacionPK(new CoDetItemsCotizacionPK());
        }
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdEmpresa(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdCotizacion(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdSucursal(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoItemsCotizacion coItemsCotizacion = coDetItemsCotizacion.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion = em.getReference(coItemsCotizacion.getClass(), coItemsCotizacion.getCoItemsCotizacionPK());
                coDetItemsCotizacion.setCoItemsCotizacion(coItemsCotizacion);
            }
            em.persist(coDetItemsCotizacion);
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoDetItemsCotizacionList().add(coDetItemsCotizacion);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetItemsCotizacion(coDetItemsCotizacion.getCoDetItemsCotizacionPK()) != null) {
                throw new PreexistingEntityException("CoDetItemsCotizacion " + coDetItemsCotizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetItemsCotizacion coDetItemsCotizacion) throws NonexistentEntityException, Exception {
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdEmpresa(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdCotizacion(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        coDetItemsCotizacion.getCoDetItemsCotizacionPK().setIdSucursal(coDetItemsCotizacion.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetItemsCotizacion persistentCoDetItemsCotizacion = em.find(CoDetItemsCotizacion.class, coDetItemsCotizacion.getCoDetItemsCotizacionPK());
            CoItemsCotizacion coItemsCotizacionOld = persistentCoDetItemsCotizacion.getCoItemsCotizacion();
            CoItemsCotizacion coItemsCotizacionNew = coDetItemsCotizacion.getCoItemsCotizacion();
            if (coItemsCotizacionNew != null) {
                coItemsCotizacionNew = em.getReference(coItemsCotizacionNew.getClass(), coItemsCotizacionNew.getCoItemsCotizacionPK());
                coDetItemsCotizacion.setCoItemsCotizacion(coItemsCotizacionNew);
            }
            coDetItemsCotizacion = em.merge(coDetItemsCotizacion);
            if (coItemsCotizacionOld != null && !coItemsCotizacionOld.equals(coItemsCotizacionNew)) {
                coItemsCotizacionOld.getCoDetItemsCotizacionList().remove(coDetItemsCotizacion);
                coItemsCotizacionOld = em.merge(coItemsCotizacionOld);
            }
            if (coItemsCotizacionNew != null && !coItemsCotizacionNew.equals(coItemsCotizacionOld)) {
                coItemsCotizacionNew.getCoDetItemsCotizacionList().add(coDetItemsCotizacion);
                coItemsCotizacionNew = em.merge(coItemsCotizacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetItemsCotizacionPK id = coDetItemsCotizacion.getCoDetItemsCotizacionPK();
                if (findCoDetItemsCotizacion(id) == null) {
                    throw new NonexistentEntityException("The coDetItemsCotizacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetItemsCotizacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetItemsCotizacion coDetItemsCotizacion;
            try {
                coDetItemsCotizacion = em.getReference(CoDetItemsCotizacion.class, id);
                coDetItemsCotizacion.getCoDetItemsCotizacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetItemsCotizacion with id " + id + " no longer exists.", enfe);
            }
            CoItemsCotizacion coItemsCotizacion = coDetItemsCotizacion.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoDetItemsCotizacionList().remove(coDetItemsCotizacion);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            em.remove(coDetItemsCotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetItemsCotizacion> findCoDetItemsCotizacionEntities() {
        return findCoDetItemsCotizacionEntities(true, -1, -1);
    }

    public List<CoDetItemsCotizacion> findCoDetItemsCotizacionEntities(int maxResults, int firstResult) {
        return findCoDetItemsCotizacionEntities(false, maxResults, firstResult);
    }

    private List<CoDetItemsCotizacion> findCoDetItemsCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetItemsCotizacion.class));
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

    public CoDetItemsCotizacion findCoDetItemsCotizacion(CoDetItemsCotizacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetItemsCotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetItemsCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetItemsCotizacion> rt = cq.from(CoDetItemsCotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
