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
import ec.com.asofar.dto.DetalleIngreso;
import ec.com.asofar.dto.Ingreso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class IngresoJpaController implements Serializable {

    public IngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingreso ingreso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleIngreso iddetalleIngreso = ingreso.getIddetalleIngreso();
            if (iddetalleIngreso != null) {
                iddetalleIngreso = em.getReference(iddetalleIngreso.getClass(), iddetalleIngreso.getIddetalleIngreso());
                ingreso.setIddetalleIngreso(iddetalleIngreso);
            }
            em.persist(ingreso);
            if (iddetalleIngreso != null) {
                iddetalleIngreso.getIngresoList().add(ingreso);
                iddetalleIngreso = em.merge(iddetalleIngreso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingreso ingreso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingreso persistentIngreso = em.find(Ingreso.class, ingreso.getIdingreso());
            DetalleIngreso iddetalleIngresoOld = persistentIngreso.getIddetalleIngreso();
            DetalleIngreso iddetalleIngresoNew = ingreso.getIddetalleIngreso();
            if (iddetalleIngresoNew != null) {
                iddetalleIngresoNew = em.getReference(iddetalleIngresoNew.getClass(), iddetalleIngresoNew.getIddetalleIngreso());
                ingreso.setIddetalleIngreso(iddetalleIngresoNew);
            }
            ingreso = em.merge(ingreso);
            if (iddetalleIngresoOld != null && !iddetalleIngresoOld.equals(iddetalleIngresoNew)) {
                iddetalleIngresoOld.getIngresoList().remove(ingreso);
                iddetalleIngresoOld = em.merge(iddetalleIngresoOld);
            }
            if (iddetalleIngresoNew != null && !iddetalleIngresoNew.equals(iddetalleIngresoOld)) {
                iddetalleIngresoNew.getIngresoList().add(ingreso);
                iddetalleIngresoNew = em.merge(iddetalleIngresoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingreso.getIdingreso();
                if (findIngreso(id) == null) {
                    throw new NonexistentEntityException("The ingreso with id " + id + " no longer exists.");
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
            Ingreso ingreso;
            try {
                ingreso = em.getReference(Ingreso.class, id);
                ingreso.getIdingreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingreso with id " + id + " no longer exists.", enfe);
            }
            DetalleIngreso iddetalleIngreso = ingreso.getIddetalleIngreso();
            if (iddetalleIngreso != null) {
                iddetalleIngreso.getIngresoList().remove(ingreso);
                iddetalleIngreso = em.merge(iddetalleIngreso);
            }
            em.remove(ingreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingreso> findIngresoEntities() {
        return findIngresoEntities(true, -1, -1);
    }

    public List<Ingreso> findIngresoEntities(int maxResults, int firstResult) {
        return findIngresoEntities(false, maxResults, firstResult);
    }

    private List<Ingreso> findIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingreso.class));
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

    public Ingreso findIngreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingreso> rt = cq.from(Ingreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
