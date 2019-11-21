/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoDetalleOrdenPedidoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoOrdenPedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class CoDetalleOrdenPedidoJpaController implements Serializable {

    public CoDetalleOrdenPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetalleOrdenPedido coDetalleOrdenPedido) throws PreexistingEntityException, Exception {
        if (coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK() == null) {
            coDetalleOrdenPedido.setCoDetalleOrdenPedidoPK(new CoDetalleOrdenPedidoPK());
        }
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdSurcusal(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdSucursal());
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdEmpresa(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdEmpresa());
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdOrdenPedido(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdOrdenPedido());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenPedido coOrdenPedido = coDetalleOrdenPedido.getCoOrdenPedido();
            if (coOrdenPedido != null) {
                coOrdenPedido = em.getReference(coOrdenPedido.getClass(), coOrdenPedido.getCoOrdenPedidoPK());
                coDetalleOrdenPedido.setCoOrdenPedido(coOrdenPedido);
            }
            em.persist(coDetalleOrdenPedido);
            if (coOrdenPedido != null) {
                coOrdenPedido.getCoDetalleOrdenPedidoList().add(coDetalleOrdenPedido);
                coOrdenPedido = em.merge(coOrdenPedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetalleOrdenPedido(coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK()) != null) {
                throw new PreexistingEntityException("CoDetalleOrdenPedido " + coDetalleOrdenPedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetalleOrdenPedido coDetalleOrdenPedido) throws NonexistentEntityException, Exception {
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdSurcusal(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdSucursal());
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdEmpresa(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdEmpresa());
        coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK().setIdOrdenPedido(coDetalleOrdenPedido.getCoOrdenPedido().getCoOrdenPedidoPK().getIdOrdenPedido());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenPedido persistentCoDetalleOrdenPedido = em.find(CoDetalleOrdenPedido.class, coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK());
            CoOrdenPedido coOrdenPedidoOld = persistentCoDetalleOrdenPedido.getCoOrdenPedido();
            CoOrdenPedido coOrdenPedidoNew = coDetalleOrdenPedido.getCoOrdenPedido();
            if (coOrdenPedidoNew != null) {
                coOrdenPedidoNew = em.getReference(coOrdenPedidoNew.getClass(), coOrdenPedidoNew.getCoOrdenPedidoPK());
                coDetalleOrdenPedido.setCoOrdenPedido(coOrdenPedidoNew);
            }
            coDetalleOrdenPedido = em.merge(coDetalleOrdenPedido);
            if (coOrdenPedidoOld != null && !coOrdenPedidoOld.equals(coOrdenPedidoNew)) {
                coOrdenPedidoOld.getCoDetalleOrdenPedidoList().remove(coDetalleOrdenPedido);
                coOrdenPedidoOld = em.merge(coOrdenPedidoOld);
            }
            if (coOrdenPedidoNew != null && !coOrdenPedidoNew.equals(coOrdenPedidoOld)) {
                coOrdenPedidoNew.getCoDetalleOrdenPedidoList().add(coDetalleOrdenPedido);
                coOrdenPedidoNew = em.merge(coOrdenPedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetalleOrdenPedidoPK id = coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK();
                if (findCoDetalleOrdenPedido(id) == null) {
                    throw new NonexistentEntityException("The coDetalleOrdenPedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetalleOrdenPedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenPedido coDetalleOrdenPedido;
            try {
                coDetalleOrdenPedido = em.getReference(CoDetalleOrdenPedido.class, id);
                coDetalleOrdenPedido.getCoDetalleOrdenPedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetalleOrdenPedido with id " + id + " no longer exists.", enfe);
            }
            CoOrdenPedido coOrdenPedido = coDetalleOrdenPedido.getCoOrdenPedido();
            if (coOrdenPedido != null) {
                coOrdenPedido.getCoDetalleOrdenPedidoList().remove(coDetalleOrdenPedido);
                coOrdenPedido = em.merge(coOrdenPedido);
            }
            em.remove(coDetalleOrdenPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetalleOrdenPedido> findCoDetalleOrdenPedidoEntities() {
        return findCoDetalleOrdenPedidoEntities(true, -1, -1);
    }

    public List<CoDetalleOrdenPedido> findCoDetalleOrdenPedidoEntities(int maxResults, int firstResult) {
        return findCoDetalleOrdenPedidoEntities(false, maxResults, firstResult);
    }

    private List<CoDetalleOrdenPedido> findCoDetalleOrdenPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetalleOrdenPedido.class));
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

    public CoDetalleOrdenPedido findCoDetalleOrdenPedido(CoDetalleOrdenPedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetalleOrdenPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetalleOrdenPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetalleOrdenPedido> rt = cq.from(CoDetalleOrdenPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
