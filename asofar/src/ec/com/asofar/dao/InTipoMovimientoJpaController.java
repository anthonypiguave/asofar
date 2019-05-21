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
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InTipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
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
        if (inTipoMovimiento.getInMovimientosList() == null) {
            inTipoMovimiento.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inTipoMovimiento.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inTipoMovimiento.setInMovimientosList(attachedInMovimientosList);
            em.persist(inTipoMovimiento);
            for (InMovimientos inMovimientosListInMovimientos : inTipoMovimiento.getInMovimientosList()) {
                InTipoMovimiento oldIdTipoMovimientoOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getIdTipoMovimiento();
                inMovimientosListInMovimientos.setIdTipoMovimiento(inTipoMovimiento);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldIdTipoMovimientoOfInMovimientosListInMovimientos != null) {
                    oldIdTipoMovimientoOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldIdTipoMovimientoOfInMovimientosListInMovimientos = em.merge(oldIdTipoMovimientoOfInMovimientosListInMovimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoMovimiento inTipoMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoMovimiento persistentInTipoMovimiento = em.find(InTipoMovimiento.class, inTipoMovimiento.getIdTipoMovimiento());
            List<InMovimientos> inMovimientosListOld = persistentInTipoMovimiento.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inTipoMovimiento.getInMovimientosList();
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inTipoMovimiento.setInMovimientosList(inMovimientosListNew);
            inTipoMovimiento = em.merge(inTipoMovimiento);
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    inMovimientosListOldInMovimientos.setIdTipoMovimiento(null);
                    inMovimientosListOldInMovimientos = em.merge(inMovimientosListOldInMovimientos);
                }
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InTipoMovimiento oldIdTipoMovimientoOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getIdTipoMovimiento();
                    inMovimientosListNewInMovimientos.setIdTipoMovimiento(inTipoMovimiento);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldIdTipoMovimientoOfInMovimientosListNewInMovimientos != null && !oldIdTipoMovimientoOfInMovimientosListNewInMovimientos.equals(inTipoMovimiento)) {
                        oldIdTipoMovimientoOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldIdTipoMovimientoOfInMovimientosListNewInMovimientos = em.merge(oldIdTipoMovimientoOfInMovimientosListNewInMovimientos);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            List<InMovimientos> inMovimientosList = inTipoMovimiento.getInMovimientosList();
            for (InMovimientos inMovimientosListInMovimientos : inMovimientosList) {
                inMovimientosListInMovimientos.setIdTipoMovimiento(null);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
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
