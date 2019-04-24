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
import ec.com.asofar.dto.InMovimientos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inBodega.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inBodega.setInMovimientosList(attachedInMovimientosList);
            em.persist(inBodega);
            for (InMovimientos inMovimientosListInMovimientos : inBodega.getInMovimientosList()) {
                InBodega oldInBodegaOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getInBodega();
                inMovimientosListInMovimientos.setInBodega(inBodega);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldInBodegaOfInMovimientosListInMovimientos != null) {
                    oldInBodegaOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldInBodegaOfInMovimientosListInMovimientos = em.merge(oldInBodegaOfInMovimientosListInMovimientos);
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InBodega persistentInBodega = em.find(InBodega.class, inBodega.getInBodegaPK());
            List<InMovimientos> inMovimientosListOld = persistentInBodega.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inBodega.getInMovimientosList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its inBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inBodega.setInMovimientosList(inMovimientosListNew);
            inBodega = em.merge(inBodega);
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
