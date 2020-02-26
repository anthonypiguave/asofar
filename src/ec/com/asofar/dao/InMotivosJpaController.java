/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InMotivos;
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
 * @author usuario
 */
public class InMotivosJpaController implements Serializable {

    public InMotivosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InMotivos inMotivos) {
        if (inMotivos.getInMovimientosList() == null) {
            inMotivos.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inMotivos.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inMotivos.setInMovimientosList(attachedInMovimientosList);
            em.persist(inMotivos);
            for (InMovimientos inMovimientosListInMovimientos : inMotivos.getInMovimientosList()) {
                InMotivos oldInMotivosOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getInMotivos();
                inMovimientosListInMovimientos.setInMotivos(inMotivos);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldInMotivosOfInMovimientosListInMovimientos != null) {
                    oldInMotivosOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldInMotivosOfInMovimientosListInMovimientos = em.merge(oldInMotivosOfInMovimientosListInMovimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InMotivos inMotivos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMotivos persistentInMotivos = em.find(InMotivos.class, inMotivos.getIdMotivo());
            List<InMovimientos> inMovimientosListOld = persistentInMotivos.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inMotivos.getInMovimientosList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its inMotivos field is not nullable.");
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
            inMotivos.setInMovimientosList(inMovimientosListNew);
            inMotivos = em.merge(inMotivos);
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InMotivos oldInMotivosOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getInMotivos();
                    inMovimientosListNewInMovimientos.setInMotivos(inMotivos);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldInMotivosOfInMovimientosListNewInMovimientos != null && !oldInMotivosOfInMovimientosListNewInMovimientos.equals(inMotivos)) {
                        oldInMotivosOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldInMotivosOfInMovimientosListNewInMovimientos = em.merge(oldInMotivosOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inMotivos.getIdMotivo();
                if (findInMotivos(id) == null) {
                    throw new NonexistentEntityException("The inMotivos with id " + id + " no longer exists.");
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
            InMotivos inMotivos;
            try {
                inMotivos = em.getReference(InMotivos.class, id);
                inMotivos.getIdMotivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inMotivos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = inMotivos.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InMotivos (" + inMotivos + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable inMotivos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(inMotivos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InMotivos> findInMotivosEntities() {
        return findInMotivosEntities(true, -1, -1);
    }

    public List<InMotivos> findInMotivosEntities(int maxResults, int firstResult) {
        return findInMotivosEntities(false, maxResults, firstResult);
    }

    private List<InMotivos> findInMotivosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InMotivos.class));
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

    public InMotivos findInMotivos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InMotivos.class, id);
        } finally {
            em.close();
        }
    }

    public int getInMotivosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InMotivos> rt = cq.from(InMotivos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
