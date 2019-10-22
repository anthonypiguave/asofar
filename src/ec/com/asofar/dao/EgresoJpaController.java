/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.DetalleEgreso;
import ec.com.asofar.dto.Egreso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class EgresoJpaController implements Serializable {

    public EgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Egreso egreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleEgreso iddetalleEgreso = egreso.getIddetalleEgreso();
            if (iddetalleEgreso != null) {
                iddetalleEgreso = em.getReference(iddetalleEgreso.getClass(), iddetalleEgreso.getIddetalleEgreso());
                egreso.setIddetalleEgreso(iddetalleEgreso);
            }
            em.persist(egreso);
            if (iddetalleEgreso != null) {
                iddetalleEgreso.getEgresoList().add(egreso);
                iddetalleEgreso = em.merge(iddetalleEgreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Egreso egreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Egreso persistentEgreso = em.find(Egreso.class, egreso.getIdegreso());
            DetalleEgreso iddetalleEgresoOld = persistentEgreso.getIddetalleEgreso();
            DetalleEgreso iddetalleEgresoNew = egreso.getIddetalleEgreso();
            if (iddetalleEgresoNew != null) {
                iddetalleEgresoNew = em.getReference(iddetalleEgresoNew.getClass(), iddetalleEgresoNew.getIddetalleEgreso());
                egreso.setIddetalleEgreso(iddetalleEgresoNew);
            }
            egreso = em.merge(egreso);
            if (iddetalleEgresoOld != null && !iddetalleEgresoOld.equals(iddetalleEgresoNew)) {
                iddetalleEgresoOld.getEgresoList().remove(egreso);
                iddetalleEgresoOld = em.merge(iddetalleEgresoOld);
            }
            if (iddetalleEgresoNew != null && !iddetalleEgresoNew.equals(iddetalleEgresoOld)) {
                iddetalleEgresoNew.getEgresoList().add(egreso);
                iddetalleEgresoNew = em.merge(iddetalleEgresoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = egreso.getIdegreso();
                if (findEgreso(id) == null) {
                    throw new NonexistentEntityException("The egreso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Egreso egreso;
            try {
                egreso = em.getReference(Egreso.class, id);
                egreso.getIdegreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The egreso with id " + id + " no longer exists.", enfe);
            }
            DetalleEgreso iddetalleEgreso = egreso.getIddetalleEgreso();
            if (iddetalleEgreso != null) {
                iddetalleEgreso.getEgresoList().remove(egreso);
                iddetalleEgreso = em.merge(iddetalleEgreso);
            }
            em.remove(egreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Egreso> findEgresoEntities() {
        return findEgresoEntities(true, -1, -1);
    }

    public List<Egreso> findEgresoEntities(int maxResults, int firstResult) {
        return findEgresoEntities(false, maxResults, firstResult);
    }

    private List<Egreso> findEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Egreso.class));
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

    public Egreso findEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Egreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Egreso> rt = cq.from(Egreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
