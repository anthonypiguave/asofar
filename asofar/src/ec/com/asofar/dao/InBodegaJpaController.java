/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InBodegaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.InMovimientos;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.PrProductoBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
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
        if (inBodega.getInMovimientosList() == null) {
            inBodega.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (inBodega.getInKardexList() == null) {
            inBodega.setInKardexList(new ArrayList<InKardex>());
        }
        if (inBodega.getPrProductoBodegaList() == null) {
            inBodega.setPrProductoBodegaList(new ArrayList<PrProductoBodega>());
        }
        inBodega.getInBodegaPK().setIdEmpresa(inBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inBodega.getInBodegaPK().setIdTipoBodega(inBodega.getInTipoBodega().getIdTipoBodega());
        inBodega.getInBodegaPK().setIdSucursal(inBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoBodega inTipoBodega = inBodega.getInTipoBodega();
            if (inTipoBodega != null) {
                inTipoBodega = em.getReference(inTipoBodega.getClass(), inTipoBodega.getIdTipoBodega());
                inBodega.setInTipoBodega(inTipoBodega);
            }
            SeSucursal seSucursal = inBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                inBodega.setSeSucursal(seSucursal);
            }
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inBodega.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inBodega.setInMovimientosList(attachedInMovimientosList);
            List<InKardex> attachedInKardexList = new ArrayList<InKardex>();
            for (InKardex inKardexListInKardexToAttach : inBodega.getInKardexList()) {
                inKardexListInKardexToAttach = em.getReference(inKardexListInKardexToAttach.getClass(), inKardexListInKardexToAttach.getInKardexPK());
                attachedInKardexList.add(inKardexListInKardexToAttach);
            }
            inBodega.setInKardexList(attachedInKardexList);
            List<PrProductoBodega> attachedPrProductoBodegaList = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListPrProductoBodegaToAttach : inBodega.getPrProductoBodegaList()) {
                prProductoBodegaListPrProductoBodegaToAttach = em.getReference(prProductoBodegaListPrProductoBodegaToAttach.getClass(), prProductoBodegaListPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaList.add(prProductoBodegaListPrProductoBodegaToAttach);
            }
            inBodega.setPrProductoBodegaList(attachedPrProductoBodegaList);
            em.persist(inBodega);
            if (inTipoBodega != null) {
                inTipoBodega.getInBodegaList().add(inBodega);
                inTipoBodega = em.merge(inTipoBodega);
            }
            if (seSucursal != null) {
                seSucursal.getInBodegaList().add(inBodega);
                seSucursal = em.merge(seSucursal);
            }
            for (InMovimientos inMovimientosListInMovimientos : inBodega.getInMovimientosList()) {
                InBodega oldInBodegaOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getInBodega();
                inMovimientosListInMovimientos.setInBodega(inBodega);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldInBodegaOfInMovimientosListInMovimientos != null) {
                    oldInBodegaOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldInBodegaOfInMovimientosListInMovimientos = em.merge(oldInBodegaOfInMovimientosListInMovimientos);
                }
            }
            for (InKardex inKardexListInKardex : inBodega.getInKardexList()) {
                InBodega oldInBodegaOfInKardexListInKardex = inKardexListInKardex.getInBodega();
                inKardexListInKardex.setInBodega(inBodega);
                inKardexListInKardex = em.merge(inKardexListInKardex);
                if (oldInBodegaOfInKardexListInKardex != null) {
                    oldInBodegaOfInKardexListInKardex.getInKardexList().remove(inKardexListInKardex);
                    oldInBodegaOfInKardexListInKardex = em.merge(oldInBodegaOfInKardexListInKardex);
                }
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

    public void edit(InBodega inBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        inBodega.getInBodegaPK().setIdEmpresa(inBodega.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inBodega.getInBodegaPK().setIdTipoBodega(inBodega.getInTipoBodega().getIdTipoBodega());
        inBodega.getInBodegaPK().setIdSucursal(inBodega.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega persistentInBodega = em.find(InBodega.class, inBodega.getInBodegaPK());
            InTipoBodega inTipoBodegaOld = persistentInBodega.getInTipoBodega();
            InTipoBodega inTipoBodegaNew = inBodega.getInTipoBodega();
            SeSucursal seSucursalOld = persistentInBodega.getSeSucursal();
            SeSucursal seSucursalNew = inBodega.getSeSucursal();
            List<InMovimientos> inMovimientosListOld = persistentInBodega.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inBodega.getInMovimientosList();
            List<InKardex> inKardexListOld = persistentInBodega.getInKardexList();
            List<InKardex> inKardexListNew = inBodega.getInKardexList();
            List<PrProductoBodega> prProductoBodegaListOld = persistentInBodega.getPrProductoBodegaList();
            List<PrProductoBodega> prProductoBodegaListNew = inBodega.getPrProductoBodegaList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its inBodega field is not nullable.");
                }
            }
            for (InKardex inKardexListOldInKardex : inKardexListOld) {
                if (!inKardexListNew.contains(inKardexListOldInKardex)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InKardex " + inKardexListOldInKardex + " since its inBodega field is not nullable.");
                }
            }
            for (PrProductoBodega prProductoBodegaListOldPrProductoBodega : prProductoBodegaListOld) {
                if (!prProductoBodegaListNew.contains(prProductoBodegaListOldPrProductoBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrProductoBodega " + prProductoBodegaListOldPrProductoBodega + " since its inBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (inTipoBodegaNew != null) {
                inTipoBodegaNew = em.getReference(inTipoBodegaNew.getClass(), inTipoBodegaNew.getIdTipoBodega());
                inBodega.setInTipoBodega(inTipoBodegaNew);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                inBodega.setSeSucursal(seSucursalNew);
            }
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inBodega.setInMovimientosList(inMovimientosListNew);
            List<InKardex> attachedInKardexListNew = new ArrayList<InKardex>();
            for (InKardex inKardexListNewInKardexToAttach : inKardexListNew) {
                inKardexListNewInKardexToAttach = em.getReference(inKardexListNewInKardexToAttach.getClass(), inKardexListNewInKardexToAttach.getInKardexPK());
                attachedInKardexListNew.add(inKardexListNewInKardexToAttach);
            }
            inKardexListNew = attachedInKardexListNew;
            inBodega.setInKardexList(inKardexListNew);
            List<PrProductoBodega> attachedPrProductoBodegaListNew = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListNewPrProductoBodegaToAttach : prProductoBodegaListNew) {
                prProductoBodegaListNewPrProductoBodegaToAttach = em.getReference(prProductoBodegaListNewPrProductoBodegaToAttach.getClass(), prProductoBodegaListNewPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaListNew.add(prProductoBodegaListNewPrProductoBodegaToAttach);
            }
            prProductoBodegaListNew = attachedPrProductoBodegaListNew;
            inBodega.setPrProductoBodegaList(prProductoBodegaListNew);
            inBodega = em.merge(inBodega);
            if (inTipoBodegaOld != null && !inTipoBodegaOld.equals(inTipoBodegaNew)) {
                inTipoBodegaOld.getInBodegaList().remove(inBodega);
                inTipoBodegaOld = em.merge(inTipoBodegaOld);
            }
            if (inTipoBodegaNew != null && !inTipoBodegaNew.equals(inTipoBodegaOld)) {
                inTipoBodegaNew.getInBodegaList().add(inBodega);
                inTipoBodegaNew = em.merge(inTipoBodegaNew);
            }
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getInBodegaList().remove(inBodega);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getInBodegaList().add(inBodega);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InBodega oldInBodegaOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getInBodega();
                    inMovimientosListNewInMovimientos.setInBodega(inBodega);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldInBodegaOfInMovimientosListNewInMovimientos != null && !oldInBodegaOfInMovimientosListNewInMovimientos.equals(inBodega)) {
                        oldInBodegaOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldInBodegaOfInMovimientosListNewInMovimientos = em.merge(oldInBodegaOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            for (InKardex inKardexListNewInKardex : inKardexListNew) {
                if (!inKardexListOld.contains(inKardexListNewInKardex)) {
                    InBodega oldInBodegaOfInKardexListNewInKardex = inKardexListNewInKardex.getInBodega();
                    inKardexListNewInKardex.setInBodega(inBodega);
                    inKardexListNewInKardex = em.merge(inKardexListNewInKardex);
                    if (oldInBodegaOfInKardexListNewInKardex != null && !oldInBodegaOfInKardexListNewInKardex.equals(inBodega)) {
                        oldInBodegaOfInKardexListNewInKardex.getInKardexList().remove(inKardexListNewInKardex);
                        oldInBodegaOfInKardexListNewInKardex = em.merge(oldInBodegaOfInKardexListNewInKardex);
                    }
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

    public void destroy(InBodegaPK id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = inBodega.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InBodega (" + inBodega + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable inBodega field.");
            }
            List<InKardex> inKardexListOrphanCheck = inBodega.getInKardexList();
            for (InKardex inKardexListOrphanCheckInKardex : inKardexListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InBodega (" + inBodega + ") cannot be destroyed since the InKardex " + inKardexListOrphanCheckInKardex + " in its inKardexList field has a non-nullable inBodega field.");
            }
            List<PrProductoBodega> prProductoBodegaListOrphanCheck = inBodega.getPrProductoBodegaList();
            for (PrProductoBodega prProductoBodegaListOrphanCheckPrProductoBodega : prProductoBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InBodega (" + inBodega + ") cannot be destroyed since the PrProductoBodega " + prProductoBodegaListOrphanCheckPrProductoBodega + " in its prProductoBodegaList field has a non-nullable inBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InTipoBodega inTipoBodega = inBodega.getInTipoBodega();
            if (inTipoBodega != null) {
                inTipoBodega.getInBodegaList().remove(inBodega);
                inTipoBodega = em.merge(inTipoBodega);
            }
            SeSucursal seSucursal = inBodega.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getInBodegaList().remove(inBodega);
                seSucursal = em.merge(seSucursal);
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
