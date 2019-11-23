/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoOrdenPedidoPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
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
        if (coOrdenPedido.getCoDetalleOrdenPedidoList() == null) {
            coOrdenPedido.setCoDetalleOrdenPedidoList(new ArrayList<CoDetalleOrdenPedido>());
        }
        coOrdenPedido.getCoOrdenPedidoPK().setIdSucursal(coOrdenPedido.getSeSucursal().getSeSucursalPK().getIdSucursal());
        coOrdenPedido.getCoOrdenPedidoPK().setIdEmpresa(coOrdenPedido.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = coOrdenPedido.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                coOrdenPedido.setSeSucursal(seSucursal);
            }
            List<CoDetalleOrdenPedido> attachedCoDetalleOrdenPedidoList = new ArrayList<CoDetalleOrdenPedido>();
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListCoDetalleOrdenPedidoToAttach : coOrdenPedido.getCoDetalleOrdenPedidoList()) {
                coDetalleOrdenPedidoListCoDetalleOrdenPedidoToAttach = em.getReference(coDetalleOrdenPedidoListCoDetalleOrdenPedidoToAttach.getClass(), coDetalleOrdenPedidoListCoDetalleOrdenPedidoToAttach.getCoDetalleOrdenPedidoPK());
                attachedCoDetalleOrdenPedidoList.add(coDetalleOrdenPedidoListCoDetalleOrdenPedidoToAttach);
            }
            coOrdenPedido.setCoDetalleOrdenPedidoList(attachedCoDetalleOrdenPedidoList);
            em.persist(coOrdenPedido);
            if (seSucursal != null) {
                seSucursal.getCoOrdenPedidoList().add(coOrdenPedido);
                seSucursal = em.merge(seSucursal);
            }
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListCoDetalleOrdenPedido : coOrdenPedido.getCoDetalleOrdenPedidoList()) {
                CoOrdenPedido oldCoOrdenPedidoOfCoDetalleOrdenPedidoListCoDetalleOrdenPedido = coDetalleOrdenPedidoListCoDetalleOrdenPedido.getCoOrdenPedido();
                coDetalleOrdenPedidoListCoDetalleOrdenPedido.setCoOrdenPedido(coOrdenPedido);
                coDetalleOrdenPedidoListCoDetalleOrdenPedido = em.merge(coDetalleOrdenPedidoListCoDetalleOrdenPedido);
                if (oldCoOrdenPedidoOfCoDetalleOrdenPedidoListCoDetalleOrdenPedido != null) {
                    oldCoOrdenPedidoOfCoDetalleOrdenPedidoListCoDetalleOrdenPedido.getCoDetalleOrdenPedidoList().remove(coDetalleOrdenPedidoListCoDetalleOrdenPedido);
                    oldCoOrdenPedidoOfCoDetalleOrdenPedidoListCoDetalleOrdenPedido = em.merge(oldCoOrdenPedidoOfCoDetalleOrdenPedidoListCoDetalleOrdenPedido);
                }
            }
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

    public void edit(CoOrdenPedido coOrdenPedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coOrdenPedido.getCoOrdenPedidoPK().setIdSucursal(coOrdenPedido.getSeSucursal().getSeSucursalPK().getIdSucursal());
        coOrdenPedido.getCoOrdenPedidoPK().setIdEmpresa(coOrdenPedido.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenPedido persistentCoOrdenPedido = em.find(CoOrdenPedido.class, coOrdenPedido.getCoOrdenPedidoPK());
            SeSucursal seSucursalOld = persistentCoOrdenPedido.getSeSucursal();
            SeSucursal seSucursalNew = coOrdenPedido.getSeSucursal();
            List<CoDetalleOrdenPedido> coDetalleOrdenPedidoListOld = persistentCoOrdenPedido.getCoDetalleOrdenPedidoList();
            List<CoDetalleOrdenPedido> coDetalleOrdenPedidoListNew = coOrdenPedido.getCoDetalleOrdenPedidoList();
            List<String> illegalOrphanMessages = null;
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListOldCoDetalleOrdenPedido : coDetalleOrdenPedidoListOld) {
                if (!coDetalleOrdenPedidoListNew.contains(coDetalleOrdenPedidoListOldCoDetalleOrdenPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetalleOrdenPedido " + coDetalleOrdenPedidoListOldCoDetalleOrdenPedido + " since its coOrdenPedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                coOrdenPedido.setSeSucursal(seSucursalNew);
            }
            List<CoDetalleOrdenPedido> attachedCoDetalleOrdenPedidoListNew = new ArrayList<CoDetalleOrdenPedido>();
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListNewCoDetalleOrdenPedidoToAttach : coDetalleOrdenPedidoListNew) {
                coDetalleOrdenPedidoListNewCoDetalleOrdenPedidoToAttach = em.getReference(coDetalleOrdenPedidoListNewCoDetalleOrdenPedidoToAttach.getClass(), coDetalleOrdenPedidoListNewCoDetalleOrdenPedidoToAttach.getCoDetalleOrdenPedidoPK());
                attachedCoDetalleOrdenPedidoListNew.add(coDetalleOrdenPedidoListNewCoDetalleOrdenPedidoToAttach);
            }
            coDetalleOrdenPedidoListNew = attachedCoDetalleOrdenPedidoListNew;
            coOrdenPedido.setCoDetalleOrdenPedidoList(coDetalleOrdenPedidoListNew);
            coOrdenPedido = em.merge(coOrdenPedido);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getCoOrdenPedidoList().remove(coOrdenPedido);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getCoOrdenPedidoList().add(coOrdenPedido);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListNewCoDetalleOrdenPedido : coDetalleOrdenPedidoListNew) {
                if (!coDetalleOrdenPedidoListOld.contains(coDetalleOrdenPedidoListNewCoDetalleOrdenPedido)) {
                    CoOrdenPedido oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido = coDetalleOrdenPedidoListNewCoDetalleOrdenPedido.getCoOrdenPedido();
                    coDetalleOrdenPedidoListNewCoDetalleOrdenPedido.setCoOrdenPedido(coOrdenPedido);
                    coDetalleOrdenPedidoListNewCoDetalleOrdenPedido = em.merge(coDetalleOrdenPedidoListNewCoDetalleOrdenPedido);
                    if (oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido != null && !oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido.equals(coOrdenPedido)) {
                        oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido.getCoDetalleOrdenPedidoList().remove(coDetalleOrdenPedidoListNewCoDetalleOrdenPedido);
                        oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido = em.merge(oldCoOrdenPedidoOfCoDetalleOrdenPedidoListNewCoDetalleOrdenPedido);
                    }
                }
            }
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

    public void destroy(CoOrdenPedidoPK id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<CoDetalleOrdenPedido> coDetalleOrdenPedidoListOrphanCheck = coOrdenPedido.getCoDetalleOrdenPedidoList();
            for (CoDetalleOrdenPedido coDetalleOrdenPedidoListOrphanCheckCoDetalleOrdenPedido : coDetalleOrdenPedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoOrdenPedido (" + coOrdenPedido + ") cannot be destroyed since the CoDetalleOrdenPedido " + coDetalleOrdenPedidoListOrphanCheckCoDetalleOrdenPedido + " in its coDetalleOrdenPedidoList field has a non-nullable coOrdenPedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeSucursal seSucursal = coOrdenPedido.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getCoOrdenPedidoList().remove(coOrdenPedido);
                seSucursal = em.merge(seSucursal);
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
