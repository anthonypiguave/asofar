/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

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
 * @author admin1
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
                InMotivos oldIdMotivoOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getIdMotivo();
                inMovimientosListInMovimientos.setIdMotivo(inMotivos);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldIdMotivoOfInMovimientosListInMovimientos != null) {
                    oldIdMotivoOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldIdMotivoOfInMovimientosListInMovimientos = em.merge(oldIdMotivoOfInMovimientosListInMovimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InMotivos inMotivos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InMotivos persistentInMotivos = em.find(InMotivos.class, inMotivos.getIdMotivo());
            List<InMovimientos> inMovimientosListOld = persistentInMotivos.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inMotivos.getInMovimientosList();
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inMotivos.setInMovimientosList(inMovimientosListNew);
            inMotivos = em.merge(inMotivos);
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    inMovimientosListOldInMovimientos.setIdMotivo(null);
                    inMovimientosListOldInMovimientos = em.merge(inMovimientosListOldInMovimientos);
                }
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InMotivos oldIdMotivoOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getIdMotivo();
                    inMovimientosListNewInMovimientos.setIdMotivo(inMotivos);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldIdMotivoOfInMovimientosListNewInMovimientos != null && !oldIdMotivoOfInMovimientosListNewInMovimientos.equals(inMotivos)) {
                        oldIdMotivoOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldIdMotivoOfInMovimientosListNewInMovimientos = em.merge(oldIdMotivoOfInMovimientosListNewInMovimientos);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            List<InMovimientos> inMovimientosList = inMotivos.getInMovimientosList();
            for (InMovimientos inMovimientosListInMovimientos : inMovimientosList) {
                inMovimientosListInMovimientos.setIdMotivo(null);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
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
