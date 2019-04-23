/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoOrdenCompras;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class CoDetalleOrdenCompraJpaController implements Serializable {

    public CoDetalleOrdenCompraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("asofarPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetalleOrdenCompra coDetalleOrdenCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras idOrdenCompra = coDetalleOrdenCompra.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra = em.getReference(idOrdenCompra.getClass(), idOrdenCompra.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setIdOrdenCompra(idOrdenCompra);
            }
            em.persist(coDetalleOrdenCompra);
            if (idOrdenCompra != null) {
                idOrdenCompra.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetalleOrdenCompra coDetalleOrdenCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenCompra persistentCoDetalleOrdenCompra = em.find(CoDetalleOrdenCompra.class, coDetalleOrdenCompra.getIdDetalleOrdenCompra());
            CoOrdenCompras idOrdenCompraOld = persistentCoDetalleOrdenCompra.getIdOrdenCompra();
            CoOrdenCompras idOrdenCompraNew = coDetalleOrdenCompra.getIdOrdenCompra();
            if (idOrdenCompraNew != null) {
                idOrdenCompraNew = em.getReference(idOrdenCompraNew.getClass(), idOrdenCompraNew.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setIdOrdenCompra(idOrdenCompraNew);
            }
            coDetalleOrdenCompra = em.merge(coDetalleOrdenCompra);
            if (idOrdenCompraOld != null && !idOrdenCompraOld.equals(idOrdenCompraNew)) {
                idOrdenCompraOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idOrdenCompraOld = em.merge(idOrdenCompraOld);
            }
            if (idOrdenCompraNew != null && !idOrdenCompraNew.equals(idOrdenCompraOld)) {
                idOrdenCompraNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idOrdenCompraNew = em.merge(idOrdenCompraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = coDetalleOrdenCompra.getIdDetalleOrdenCompra();
                if (findCoDetalleOrdenCompra(id) == null) {
                    throw new NonexistentEntityException("The coDetalleOrdenCompra with id " + id + " no longer exists.");
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
            CoDetalleOrdenCompra coDetalleOrdenCompra;
            try {
                coDetalleOrdenCompra = em.getReference(CoDetalleOrdenCompra.class, id);
                coDetalleOrdenCompra.getIdDetalleOrdenCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetalleOrdenCompra with id " + id + " no longer exists.", enfe);
            }
            CoOrdenCompras idOrdenCompra = coDetalleOrdenCompra.getIdOrdenCompra();
            if (idOrdenCompra != null) {
                idOrdenCompra.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idOrdenCompra = em.merge(idOrdenCompra);
            }
            em.remove(coDetalleOrdenCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities() {
        return findCoDetalleOrdenCompraEntities(true, -1, -1);
    }

    public List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities(int maxResults, int firstResult) {
        return findCoDetalleOrdenCompraEntities(false, maxResults, firstResult);
    }

    private List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetalleOrdenCompra.class));
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

    public CoDetalleOrdenCompra findCoDetalleOrdenCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetalleOrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetalleOrdenCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetalleOrdenCompra> rt = cq.from(CoDetalleOrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
