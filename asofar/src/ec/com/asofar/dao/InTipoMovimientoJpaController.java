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
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InTipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class InTipoMovimientoJpaController implements Serializable {

    public InTipoMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoMovimiento inTipoMovimiento) {
        if (inTipoMovimiento.getInKardexList() == null) {
            inTipoMovimiento.setInKardexList(new ArrayList<InKardex>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InKardex> attachedInKardexList = new ArrayList<InKardex>();
            for (InKardex inKardexListInKardexToAttach : inTipoMovimiento.getInKardexList()) {
                inKardexListInKardexToAttach = em.getReference(inKardexListInKardexToAttach.getClass(), inKardexListInKardexToAttach.getInKardexPK());
                attachedInKardexList.add(inKardexListInKardexToAttach);
            }
            inTipoMovimiento.setInKardexList(attachedInKardexList);
            em.persist(inTipoMovimiento);
            for (InKardex inKardexListInKardex : inTipoMovimiento.getInKardexList()) {
                InTipoMovimiento oldInTipoMovimientoOfInKardexListInKardex = inKardexListInKardex.getInTipoMovimiento();
                inKardexListInKardex.setInTipoMovimiento(inTipoMovimiento);
                inKardexListInKardex = em.merge(inKardexListInKardex);
                if (oldInTipoMovimientoOfInKardexListInKardex != null) {
                    oldInTipoMovimientoOfInKardexListInKardex.getInKardexList().remove(inKardexListInKardex);
                    oldInTipoMovimientoOfInKardexListInKardex = em.merge(oldInTipoMovimientoOfInKardexListInKardex);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoMovimiento inTipoMovimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoMovimiento persistentInTipoMovimiento = em.find(InTipoMovimiento.class, inTipoMovimiento.getIdTipoMovimiento());
            List<InKardex> inKardexListOld = persistentInTipoMovimiento.getInKardexList();
            List<InKardex> inKardexListNew = inTipoMovimiento.getInKardexList();
            List<String> illegalOrphanMessages = null;
            for (InKardex inKardexListOldInKardex : inKardexListOld) {
                if (!inKardexListNew.contains(inKardexListOldInKardex)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InKardex " + inKardexListOldInKardex + " since its inTipoMovimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InKardex> attachedInKardexListNew = new ArrayList<InKardex>();
            for (InKardex inKardexListNewInKardexToAttach : inKardexListNew) {
                inKardexListNewInKardexToAttach = em.getReference(inKardexListNewInKardexToAttach.getClass(), inKardexListNewInKardexToAttach.getInKardexPK());
                attachedInKardexListNew.add(inKardexListNewInKardexToAttach);
            }
            inKardexListNew = attachedInKardexListNew;
            inTipoMovimiento.setInKardexList(inKardexListNew);
            inTipoMovimiento = em.merge(inTipoMovimiento);
            for (InKardex inKardexListNewInKardex : inKardexListNew) {
                if (!inKardexListOld.contains(inKardexListNewInKardex)) {
                    InTipoMovimiento oldInTipoMovimientoOfInKardexListNewInKardex = inKardexListNewInKardex.getInTipoMovimiento();
                    inKardexListNewInKardex.setInTipoMovimiento(inTipoMovimiento);
                    inKardexListNewInKardex = em.merge(inKardexListNewInKardex);
                    if (oldInTipoMovimientoOfInKardexListNewInKardex != null && !oldInTipoMovimientoOfInKardexListNewInKardex.equals(inTipoMovimiento)) {
                        oldInTipoMovimientoOfInKardexListNewInKardex.getInKardexList().remove(inKardexListNewInKardex);
                        oldInTipoMovimientoOfInKardexListNewInKardex = em.merge(oldInTipoMovimientoOfInKardexListNewInKardex);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoMovimiento.getIdTipoMovimiento();
                if (findInTipoMovimiento(id) == null) {
                    throw new NonexistentEntityException("The inTipoMovimiento with id " + id + " no longer exists.");
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
            InTipoMovimiento inTipoMovimiento;
            try {
                inTipoMovimiento = em.getReference(InTipoMovimiento.class, id);
                inTipoMovimiento.getIdTipoMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoMovimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InKardex> inKardexListOrphanCheck = inTipoMovimiento.getInKardexList();
            for (InKardex inKardexListOrphanCheckInKardex : inKardexListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InTipoMovimiento (" + inTipoMovimiento + ") cannot be destroyed since the InKardex " + inKardexListOrphanCheckInKardex + " in its inKardexList field has a non-nullable inTipoMovimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(inTipoMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoMovimiento> findInTipoMovimientoEntities() {
        return findInTipoMovimientoEntities(true, -1, -1);
    }

    public List<InTipoMovimiento> findInTipoMovimientoEntities(int maxResults, int firstResult) {
        return findInTipoMovimientoEntities(false, maxResults, firstResult);
    }

    private List<InTipoMovimiento> findInTipoMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoMovimiento.class));
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

    public InTipoMovimiento findInTipoMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoMovimiento> rt = cq.from(InTipoMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
