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
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenComprasPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class CoOrdenComprasJpaController implements Serializable {

    public CoOrdenComprasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoOrdenCompras coOrdenCompras) throws PreexistingEntityException, Exception {
        if (coOrdenCompras.getCoOrdenComprasPK() == null) {
            coOrdenCompras.setCoOrdenComprasPK(new CoOrdenComprasPK());
        }
        if (coOrdenCompras.getCoDetalleOrdenCompraList() == null) {
            coOrdenCompras.setCoDetalleOrdenCompraList(new ArrayList<CoDetalleOrdenCompra>());
        }
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursal);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraList = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach : coOrdenCompras.getCoDetalleOrdenCompraList()) {
                coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach.getCoDetalleOrdenCompraPK());
                attachedCoDetalleOrdenCompraList.add(coDetalleOrdenCompraListCoDetalleOrdenCompraToAttach);
            }
            coOrdenCompras.setCoDetalleOrdenCompraList(attachedCoDetalleOrdenCompraList);
            em.persist(coOrdenCompras);
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListCoDetalleOrdenCompra : coOrdenCompras.getCoDetalleOrdenCompraList()) {
                CoOrdenCompras oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra = coDetalleOrdenCompraListCoDetalleOrdenCompra.getCoOrdenCompras();
                coDetalleOrdenCompraListCoDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
                coDetalleOrdenCompraListCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListCoDetalleOrdenCompra);
                if (oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra != null) {
                    oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompraListCoDetalleOrdenCompra);
                    oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra = em.merge(oldCoOrdenComprasOfCoDetalleOrdenCompraListCoDetalleOrdenCompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoOrdenCompras(coOrdenCompras.getCoOrdenComprasPK()) != null) {
                throw new PreexistingEntityException("CoOrdenCompras " + coOrdenCompras + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoOrdenCompras coOrdenCompras) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coOrdenCompras.getCoOrdenComprasPK().setIdEmpresa(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        coOrdenCompras.getCoOrdenComprasPK().setIdSucursal(coOrdenCompras.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras persistentCoOrdenCompras = em.find(CoOrdenCompras.class, coOrdenCompras.getCoOrdenComprasPK());
            SeSucursal seSucursalOld = persistentCoOrdenCompras.getSeSucursal();
            SeSucursal seSucursalNew = coOrdenCompras.getSeSucursal();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListOld = persistentCoOrdenCompras.getCoDetalleOrdenCompraList();
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListNew = coOrdenCompras.getCoDetalleOrdenCompraList();
            List<String> illegalOrphanMessages = null;
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListOldCoDetalleOrdenCompra : coDetalleOrdenCompraListOld) {
                if (!coDetalleOrdenCompraListNew.contains(coDetalleOrdenCompraListOldCoDetalleOrdenCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetalleOrdenCompra " + coDetalleOrdenCompraListOldCoDetalleOrdenCompra + " since its coOrdenCompras field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                coOrdenCompras.setSeSucursal(seSucursalNew);
            }
            List<CoDetalleOrdenCompra> attachedCoDetalleOrdenCompraListNew = new ArrayList<CoDetalleOrdenCompra>();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach : coDetalleOrdenCompraListNew) {
                coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach = em.getReference(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getClass(), coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach.getCoDetalleOrdenCompraPK());
                attachedCoDetalleOrdenCompraListNew.add(coDetalleOrdenCompraListNewCoDetalleOrdenCompraToAttach);
            }
            coDetalleOrdenCompraListNew = attachedCoDetalleOrdenCompraListNew;
            coOrdenCompras.setCoDetalleOrdenCompraList(coDetalleOrdenCompraListNew);
            coOrdenCompras = em.merge(coOrdenCompras);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getCoOrdenComprasList().add(coOrdenCompras);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListNewCoDetalleOrdenCompra : coDetalleOrdenCompraListNew) {
                if (!coDetalleOrdenCompraListOld.contains(coDetalleOrdenCompraListNewCoDetalleOrdenCompra)) {
                    CoOrdenCompras oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra = coDetalleOrdenCompraListNewCoDetalleOrdenCompra.getCoOrdenCompras();
                    coDetalleOrdenCompraListNewCoDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
                    coDetalleOrdenCompraListNewCoDetalleOrdenCompra = em.merge(coDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                    if (oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra != null && !oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra.equals(coOrdenCompras)) {
                        oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                        oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra = em.merge(oldCoOrdenComprasOfCoDetalleOrdenCompraListNewCoDetalleOrdenCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoOrdenComprasPK id = coOrdenCompras.getCoOrdenComprasPK();
                if (findCoOrdenCompras(id) == null) {
                    throw new NonexistentEntityException("The coOrdenCompras with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoOrdenComprasPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras coOrdenCompras;
            try {
                coOrdenCompras = em.getReference(CoOrdenCompras.class, id);
                coOrdenCompras.getCoOrdenComprasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coOrdenCompras with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CoDetalleOrdenCompra> coDetalleOrdenCompraListOrphanCheck = coOrdenCompras.getCoDetalleOrdenCompraList();
            for (CoDetalleOrdenCompra coDetalleOrdenCompraListOrphanCheckCoDetalleOrdenCompra : coDetalleOrdenCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoOrdenCompras (" + coOrdenCompras + ") cannot be destroyed since the CoDetalleOrdenCompra " + coDetalleOrdenCompraListOrphanCheckCoDetalleOrdenCompra + " in its coDetalleOrdenCompraList field has a non-nullable coOrdenCompras field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeSucursal seSucursal = coOrdenCompras.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getCoOrdenComprasList().remove(coOrdenCompras);
                seSucursal = em.merge(seSucursal);
            }
            em.remove(coOrdenCompras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoOrdenCompras> findCoOrdenComprasEntities() {
        return findCoOrdenComprasEntities(true, -1, -1);
    }

    public List<CoOrdenCompras> findCoOrdenComprasEntities(int maxResults, int firstResult) {
        return findCoOrdenComprasEntities(false, maxResults, firstResult);
    }

    private List<CoOrdenCompras> findCoOrdenComprasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoOrdenCompras.class));
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

    public CoOrdenCompras findCoOrdenCompras(CoOrdenComprasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoOrdenCompras.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoOrdenComprasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoOrdenCompras> rt = cq.from(CoOrdenCompras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
