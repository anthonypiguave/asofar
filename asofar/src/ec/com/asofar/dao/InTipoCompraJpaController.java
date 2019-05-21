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
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.InTipoCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
 */
public class InTipoCompraJpaController implements Serializable {

    public InTipoCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoCompra inTipoCompra) {
        if (inTipoCompra.getCoItemsCotizacionList() == null) {
            inTipoCompra.setCoItemsCotizacionList(new ArrayList<CoItemsCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CoItemsCotizacion> attachedCoItemsCotizacionList = new ArrayList<CoItemsCotizacion>();
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacionToAttach : inTipoCompra.getCoItemsCotizacionList()) {
                coItemsCotizacionListCoItemsCotizacionToAttach = em.getReference(coItemsCotizacionListCoItemsCotizacionToAttach.getClass(), coItemsCotizacionListCoItemsCotizacionToAttach.getCoItemsCotizacionPK());
                attachedCoItemsCotizacionList.add(coItemsCotizacionListCoItemsCotizacionToAttach);
            }
            inTipoCompra.setCoItemsCotizacionList(attachedCoItemsCotizacionList);
            em.persist(inTipoCompra);
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacion : inTipoCompra.getCoItemsCotizacionList()) {
                InTipoCompra oldIdTipoCompraOfCoItemsCotizacionListCoItemsCotizacion = coItemsCotizacionListCoItemsCotizacion.getIdTipoCompra();
                coItemsCotizacionListCoItemsCotizacion.setIdTipoCompra(inTipoCompra);
                coItemsCotizacionListCoItemsCotizacion = em.merge(coItemsCotizacionListCoItemsCotizacion);
                if (oldIdTipoCompraOfCoItemsCotizacionListCoItemsCotizacion != null) {
                    oldIdTipoCompraOfCoItemsCotizacionListCoItemsCotizacion.getCoItemsCotizacionList().remove(coItemsCotizacionListCoItemsCotizacion);
                    oldIdTipoCompraOfCoItemsCotizacionListCoItemsCotizacion = em.merge(oldIdTipoCompraOfCoItemsCotizacionListCoItemsCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoCompra inTipoCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoCompra persistentInTipoCompra = em.find(InTipoCompra.class, inTipoCompra.getIdInTipoCompra());
            List<CoItemsCotizacion> coItemsCotizacionListOld = persistentInTipoCompra.getCoItemsCotizacionList();
            List<CoItemsCotizacion> coItemsCotizacionListNew = inTipoCompra.getCoItemsCotizacionList();
            List<CoItemsCotizacion> attachedCoItemsCotizacionListNew = new ArrayList<CoItemsCotizacion>();
            for (CoItemsCotizacion coItemsCotizacionListNewCoItemsCotizacionToAttach : coItemsCotizacionListNew) {
                coItemsCotizacionListNewCoItemsCotizacionToAttach = em.getReference(coItemsCotizacionListNewCoItemsCotizacionToAttach.getClass(), coItemsCotizacionListNewCoItemsCotizacionToAttach.getCoItemsCotizacionPK());
                attachedCoItemsCotizacionListNew.add(coItemsCotizacionListNewCoItemsCotizacionToAttach);
            }
            coItemsCotizacionListNew = attachedCoItemsCotizacionListNew;
            inTipoCompra.setCoItemsCotizacionList(coItemsCotizacionListNew);
            inTipoCompra = em.merge(inTipoCompra);
            for (CoItemsCotizacion coItemsCotizacionListOldCoItemsCotizacion : coItemsCotizacionListOld) {
                if (!coItemsCotizacionListNew.contains(coItemsCotizacionListOldCoItemsCotizacion)) {
                    coItemsCotizacionListOldCoItemsCotizacion.setIdTipoCompra(null);
                    coItemsCotizacionListOldCoItemsCotizacion = em.merge(coItemsCotizacionListOldCoItemsCotizacion);
                }
            }
            for (CoItemsCotizacion coItemsCotizacionListNewCoItemsCotizacion : coItemsCotizacionListNew) {
                if (!coItemsCotizacionListOld.contains(coItemsCotizacionListNewCoItemsCotizacion)) {
                    InTipoCompra oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion = coItemsCotizacionListNewCoItemsCotizacion.getIdTipoCompra();
                    coItemsCotizacionListNewCoItemsCotizacion.setIdTipoCompra(inTipoCompra);
                    coItemsCotizacionListNewCoItemsCotizacion = em.merge(coItemsCotizacionListNewCoItemsCotizacion);
                    if (oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion != null && !oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion.equals(inTipoCompra)) {
                        oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion.getCoItemsCotizacionList().remove(coItemsCotizacionListNewCoItemsCotizacion);
                        oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion = em.merge(oldIdTipoCompraOfCoItemsCotizacionListNewCoItemsCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoCompra.getIdInTipoCompra();
                if (findInTipoCompra(id) == null) {
                    throw new NonexistentEntityException("The inTipoCompra with id " + id + " no longer exists.");
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
            InTipoCompra inTipoCompra;
            try {
                inTipoCompra = em.getReference(InTipoCompra.class, id);
                inTipoCompra.getIdInTipoCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoCompra with id " + id + " no longer exists.", enfe);
            }
            List<CoItemsCotizacion> coItemsCotizacionList = inTipoCompra.getCoItemsCotizacionList();
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacion : coItemsCotizacionList) {
                coItemsCotizacionListCoItemsCotizacion.setIdTipoCompra(null);
                coItemsCotizacionListCoItemsCotizacion = em.merge(coItemsCotizacionListCoItemsCotizacion);
            }
            em.remove(inTipoCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoCompra> findInTipoCompraEntities() {
        return findInTipoCompraEntities(true, -1, -1);
    }

    public List<InTipoCompra> findInTipoCompraEntities(int maxResults, int firstResult) {
        return findInTipoCompraEntities(false, maxResults, firstResult);
    }

    private List<InTipoCompra> findInTipoCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoCompra.class));
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

    public InTipoCompra findInTipoCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoCompra> rt = cq.from(InTipoCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
