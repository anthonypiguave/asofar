/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InBodegaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.PrProductoBodega;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class InBodegaJpaController implements Serializable {

    public InBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InBodega inBodega) throws PreexistingEntityException, Exception {
        if (inBodega.getInBodegaPK() == null) {
            inBodega.setInBodegaPK(new InBodegaPK());
        }
        if (inBodega.getPrProductoBodegaList() == null) {
            inBodega.setPrProductoBodegaList(new ArrayList<PrProductoBodega>());
        }
        inBodega.getInBodegaPK().setIdSucursal(inBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inBodega.getInBodegaPK().setIdEmpresa(inBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inBodega.getInBodegaPK().setIdTipoBodega(inBodega.getInTipoBodega().getIdTipoBodega());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = inBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                inBodega.setSeSucursal(seSucursal);
            }
            InTipoBodega inTipoBodega = inBodega.getInTipoBodega();
            if (inTipoBodega != null) {
                inTipoBodega = em.getReference(inTipoBodega.getClass(), inTipoBodega.getIdTipoBodega());
                inBodega.setInTipoBodega(inTipoBodega);
            }
            List<PrProductoBodega> attachedPrProductoBodegaList = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListPrProductoBodegaToAttach : inBodega.getPrProductoBodegaList()) {
                prProductoBodegaListPrProductoBodegaToAttach = em.getReference(prProductoBodegaListPrProductoBodegaToAttach.getClass(), prProductoBodegaListPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaList.add(prProductoBodegaListPrProductoBodegaToAttach);
            }
            inBodega.setPrProductoBodegaList(attachedPrProductoBodegaList);
            em.persist(inBodega);
            if (seSucursal != null) {
                seSucursal.getInBodegaList().add(inBodega);
                seSucursal = em.merge(seSucursal);
            }
            if (inTipoBodega != null) {
                inTipoBodega.getInBodegaList().add(inBodega);
                inTipoBodega = em.merge(inTipoBodega);
            }
            for (PrProductoBodega prProductoBodegaListPrProductoBodega : inBodega.getPrProductoBodegaList()) {
                InBodega oldInBodegaOfPrProductoBodegaListPrProductoBodega = prProductoBodegaListPrProductoBodega.getInBodega();
                prProductoBodegaListPrProductoBodega.setInBodega(inBodega);
                prProductoBodegaListPrProductoBodega = em.merge(prProductoBodegaListPrProductoBodega);
                if (oldInBodegaOfPrProductoBodegaListPrProductoBodega != null) {
                    oldInBodegaOfPrProductoBodegaListPrProductoBodega.getPrProductoBodegaList().remove(prProductoBodegaListPrProductoBodega);
                    oldInBodegaOfPrProductoBodegaListPrProductoBodega = em.merge(oldInBodegaOfPrProductoBodegaListPrProductoBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInBodega(inBodega.getInBodegaPK()) != null) {
                throw new PreexistingEntityException("InBodega " + inBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InBodega inBodega) throws NonexistentEntityException, Exception {
        inBodega.getInBodegaPK().setIdSucursal(inBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        inBodega.getInBodegaPK().setIdEmpresa(inBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inBodega.getInBodegaPK().setIdTipoBodega(inBodega.getInTipoBodega().getIdTipoBodega());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega persistentInBodega = em.find(InBodega.class, inBodega.getInBodegaPK());
            SeSucursal seSucursalOld = persistentInBodega.getSeSucursal();
            SeSucursal seSucursalNew = inBodega.getSeSucursal();
            InTipoBodega inTipoBodegaOld = persistentInBodega.getInTipoBodega();
            InTipoBodega inTipoBodegaNew = inBodega.getInTipoBodega();
            List<PrProductoBodega> prProductoBodegaListOld = persistentInBodega.getPrProductoBodegaList();
            List<PrProductoBodega> prProductoBodegaListNew = inBodega.getPrProductoBodegaList();
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                inBodega.setSeSucursal(seSucursalNew);
            }
            if (inTipoBodegaNew != null) {
                inTipoBodegaNew = em.getReference(inTipoBodegaNew.getClass(), inTipoBodegaNew.getIdTipoBodega());
                inBodega.setInTipoBodega(inTipoBodegaNew);
            }
            List<PrProductoBodega> attachedPrProductoBodegaListNew = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListNewPrProductoBodegaToAttach : prProductoBodegaListNew) {
                prProductoBodegaListNewPrProductoBodegaToAttach = em.getReference(prProductoBodegaListNewPrProductoBodegaToAttach.getClass(), prProductoBodegaListNewPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaListNew.add(prProductoBodegaListNewPrProductoBodegaToAttach);
            }
            prProductoBodegaListNew = attachedPrProductoBodegaListNew;
            inBodega.setPrProductoBodegaList(prProductoBodegaListNew);
            inBodega = em.merge(inBodega);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getInBodegaList().remove(inBodega);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getInBodegaList().add(inBodega);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (inTipoBodegaOld != null && !inTipoBodegaOld.equals(inTipoBodegaNew)) {
                inTipoBodegaOld.getInBodegaList().remove(inBodega);
                inTipoBodegaOld = em.merge(inTipoBodegaOld);
            }
            if (inTipoBodegaNew != null && !inTipoBodegaNew.equals(inTipoBodegaOld)) {
                inTipoBodegaNew.getInBodegaList().add(inBodega);
                inTipoBodegaNew = em.merge(inTipoBodegaNew);
            }
            for (PrProductoBodega prProductoBodegaListOldPrProductoBodega : prProductoBodegaListOld) {
                if (!prProductoBodegaListNew.contains(prProductoBodegaListOldPrProductoBodega)) {
                    prProductoBodegaListOldPrProductoBodega.setInBodega(null);
                    prProductoBodegaListOldPrProductoBodega = em.merge(prProductoBodegaListOldPrProductoBodega);
                }
            }
            for (PrProductoBodega prProductoBodegaListNewPrProductoBodega : prProductoBodegaListNew) {
                if (!prProductoBodegaListOld.contains(prProductoBodegaListNewPrProductoBodega)) {
                    InBodega oldInBodegaOfPrProductoBodegaListNewPrProductoBodega = prProductoBodegaListNewPrProductoBodega.getInBodega();
                    prProductoBodegaListNewPrProductoBodega.setInBodega(inBodega);
                    prProductoBodegaListNewPrProductoBodega = em.merge(prProductoBodegaListNewPrProductoBodega);
                    if (oldInBodegaOfPrProductoBodegaListNewPrProductoBodega != null && !oldInBodegaOfPrProductoBodegaListNewPrProductoBodega.equals(inBodega)) {
                        oldInBodegaOfPrProductoBodegaListNewPrProductoBodega.getPrProductoBodegaList().remove(prProductoBodegaListNewPrProductoBodega);
                        oldInBodegaOfPrProductoBodegaListNewPrProductoBodega = em.merge(oldInBodegaOfPrProductoBodegaListNewPrProductoBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InBodegaPK id = inBodega.getInBodegaPK();
                if (findInBodega(id) == null) {
                    throw new NonexistentEntityException("The inBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega inBodega;
            try {
                inBodega = em.getReference(InBodega.class, id);
                inBodega.getInBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inBodega with id " + id + " no longer exists.", enfe);
            }
            SeSucursal seSucursal = inBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getInBodegaList().remove(inBodega);
                seSucursal = em.merge(seSucursal);
            }
            InTipoBodega inTipoBodega = inBodega.getInTipoBodega();
            if (inTipoBodega != null) {
                inTipoBodega.getInBodegaList().remove(inBodega);
                inTipoBodega = em.merge(inTipoBodega);
            }
            List<PrProductoBodega> prProductoBodegaList = inBodega.getPrProductoBodegaList();
            for (PrProductoBodega prProductoBodegaListPrProductoBodega : prProductoBodegaList) {
                prProductoBodegaListPrProductoBodega.setInBodega(null);
                prProductoBodegaListPrProductoBodega = em.merge(prProductoBodegaListPrProductoBodega);
            }
            em.remove(inBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InBodega> findInBodegaEntities() {
        return findInBodegaEntities(true, -1, -1);
    }

    public List<InBodega> findInBodegaEntities(int maxResults, int firstResult) {
        return findInBodegaEntities(false, maxResults, firstResult);
    }

    private List<InBodega> findInBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InBodega.class));
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

    public InBodega findInBodega(InBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getInBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InBodega> rt = cq.from(InBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
