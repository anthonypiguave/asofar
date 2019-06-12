/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoOrdenPedidoPK;
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
public class CoOrdenPedidoJpaController implements Serializable {

    public CoOrdenPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoOrdenPedido coOrdenPedido) throws PreexistingEntityException, Exception {
        if (coOrdenPedido.getCoOrdenPedidoPK() == null) {
            coOrdenPedido.setCoOrdenPedidoPK(new CoOrdenPedidoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(coOrdenPedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoOrdenPedido(coOrdenPedido.getCoOrdenPedidoPK()) != null) {
                throw new PreexistingEntityException("CoOrdenPedido " + coOrdenPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoOrdenPedido coOrdenPedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            coOrdenPedido = em.merge(coOrdenPedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoOrdenPedidoPK id = coOrdenPedido.getCoOrdenPedidoPK();
                if (findCoOrdenPedido(id) == null) {
                    throw new NonexistentEntityException("The coOrdenPedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoOrdenPedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenPedido coOrdenPedido;
            try {
                coOrdenPedido = em.getReference(CoOrdenPedido.class, id);
                coOrdenPedido.getCoOrdenPedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coOrdenPedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(coOrdenPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoOrdenPedido> findCoOrdenPedidoEntities() {
        return findCoOrdenPedidoEntities(true, -1, -1);
    }

    public List<CoOrdenPedido> findCoOrdenPedidoEntities(int maxResults, int firstResult) {
        return findCoOrdenPedidoEntities(false, maxResults, firstResult);
    }

    private List<CoOrdenPedido> findCoOrdenPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoOrdenPedido.class));
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

    public CoOrdenPedido findCoOrdenPedido(CoOrdenPedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoOrdenPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoOrdenPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoOrdenPedido> rt = cq.from(CoOrdenPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
