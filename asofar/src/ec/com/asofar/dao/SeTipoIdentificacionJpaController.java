/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.SeTipoIdentificacion;
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
public class SeTipoIdentificacionJpaController implements Serializable {

    public SeTipoIdentificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeTipoIdentificacion seTipoIdentificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(seTipoIdentificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeTipoIdentificacion seTipoIdentificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seTipoIdentificacion = em.merge(seTipoIdentificacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seTipoIdentificacion.getIdTipoIdentificacion();
                if (findSeTipoIdentificacion(id) == null) {
                    throw new NonexistentEntityException("The seTipoIdentificacion with id " + id + " no longer exists.");
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
            SeTipoIdentificacion seTipoIdentificacion;
            try {
                seTipoIdentificacion = em.getReference(SeTipoIdentificacion.class, id);
                seTipoIdentificacion.getIdTipoIdentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seTipoIdentificacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(seTipoIdentificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeTipoIdentificacion> findSeTipoIdentificacionEntities() {
        return findSeTipoIdentificacionEntities(true, -1, -1);
    }

    public List<SeTipoIdentificacion> findSeTipoIdentificacionEntities(int maxResults, int firstResult) {
        return findSeTipoIdentificacionEntities(false, maxResults, firstResult);
    }

    private List<SeTipoIdentificacion> findSeTipoIdentificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeTipoIdentificacion.class));
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

    public SeTipoIdentificacion findSeTipoIdentificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeTipoIdentificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeTipoIdentificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeTipoIdentificacion> rt = cq.from(SeTipoIdentificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
