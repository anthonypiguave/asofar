/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InTipoBodega;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class InTipoBodegaJpaController implements Serializable {

    public InTipoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoBodega inTipoBodega) {
        if (inTipoBodega.getInBodegaList() == null) {
            inTipoBodega.setInBodegaList(new ArrayList<InBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InBodega> attachedInBodegaList = new ArrayList<InBodega>();
            for (InBodega inBodegaListInBodegaToAttach : inTipoBodega.getInBodegaList()) {
                inBodegaListInBodegaToAttach = em.getReference(inBodegaListInBodegaToAttach.getClass(), inBodegaListInBodegaToAttach.getInBodegaPK());
                attachedInBodegaList.add(inBodegaListInBodegaToAttach);
            }
            inTipoBodega.setInBodegaList(attachedInBodegaList);
            em.persist(inTipoBodega);
            for (InBodega inBodegaListInBodega : inTipoBodega.getInBodegaList()) {
                InTipoBodega oldInTipoBodegaOfInBodegaListInBodega = inBodegaListInBodega.getInTipoBodega();
                inBodegaListInBodega.setInTipoBodega(inTipoBodega);
                inBodegaListInBodega = em.merge(inBodegaListInBodega);
                if (oldInTipoBodegaOfInBodegaListInBodega != null) {
                    oldInTipoBodegaOfInBodegaListInBodega.getInBodegaList().remove(inBodegaListInBodega);
                    oldInTipoBodegaOfInBodegaListInBodega = em.merge(oldInTipoBodegaOfInBodegaListInBodega);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoBodega inTipoBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoBodega persistentInTipoBodega = em.find(InTipoBodega.class, inTipoBodega.getIdTipoBodega());
            List<InBodega> inBodegaListOld = persistentInTipoBodega.getInBodegaList();
            List<InBodega> inBodegaListNew = inTipoBodega.getInBodegaList();
            List<String> illegalOrphanMessages = null;
            for (InBodega inBodegaListOldInBodega : inBodegaListOld) {
                if (!inBodegaListNew.contains(inBodegaListOldInBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InBodega " + inBodegaListOldInBodega + " since its inTipoBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InBodega> attachedInBodegaListNew = new ArrayList<InBodega>();
            for (InBodega inBodegaListNewInBodegaToAttach : inBodegaListNew) {
                inBodegaListNewInBodegaToAttach = em.getReference(inBodegaListNewInBodegaToAttach.getClass(), inBodegaListNewInBodegaToAttach.getInBodegaPK());
                attachedInBodegaListNew.add(inBodegaListNewInBodegaToAttach);
            }
            inBodegaListNew = attachedInBodegaListNew;
            inTipoBodega.setInBodegaList(inBodegaListNew);
            inTipoBodega = em.merge(inTipoBodega);
            for (InBodega inBodegaListNewInBodega : inBodegaListNew) {
                if (!inBodegaListOld.contains(inBodegaListNewInBodega)) {
                    InTipoBodega oldInTipoBodegaOfInBodegaListNewInBodega = inBodegaListNewInBodega.getInTipoBodega();
                    inBodegaListNewInBodega.setInTipoBodega(inTipoBodega);
                    inBodegaListNewInBodega = em.merge(inBodegaListNewInBodega);
                    if (oldInTipoBodegaOfInBodegaListNewInBodega != null && !oldInTipoBodegaOfInBodegaListNewInBodega.equals(inTipoBodega)) {
                        oldInTipoBodegaOfInBodegaListNewInBodega.getInBodegaList().remove(inBodegaListNewInBodega);
                        oldInTipoBodegaOfInBodegaListNewInBodega = em.merge(oldInTipoBodegaOfInBodegaListNewInBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoBodega.getIdTipoBodega();
                if (findInTipoBodega(id) == null) {
                    throw new NonexistentEntityException("The inTipoBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoBodega inTipoBodega;
            try {
                inTipoBodega = em.getReference(InTipoBodega.class, id);
                inTipoBodega.getIdTipoBodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InBodega> inBodegaListOrphanCheck = inTipoBodega.getInBodegaList();
            for (InBodega inBodegaListOrphanCheckInBodega : inBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InTipoBodega (" + inTipoBodega + ") cannot be destroyed since the InBodega " + inBodegaListOrphanCheckInBodega + " in its inBodegaList field has a non-nullable inTipoBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(inTipoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoBodega> findInTipoBodegaEntities() {
        return findInTipoBodegaEntities(true, -1, -1);
    }

    public List<InTipoBodega> findInTipoBodegaEntities(int maxResults, int firstResult) {
        return findInTipoBodegaEntities(false, maxResults, firstResult);
    }

    private List<InTipoBodega> findInTipoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoBodega.class));
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

    public InTipoBodega findInTipoBodega(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoBodega> rt = cq.from(InTipoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
