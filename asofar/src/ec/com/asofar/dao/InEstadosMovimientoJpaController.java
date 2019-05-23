/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.InEstadosMovimiento;
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
public class InEstadosMovimientoJpaController implements Serializable {

    public InEstadosMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InEstadosMovimiento inEstadosMovimiento) {
        if (inEstadosMovimiento.getInMovimientosList() == null) {
            inEstadosMovimiento.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inEstadosMovimiento.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inEstadosMovimiento.setInMovimientosList(attachedInMovimientosList);
            em.persist(inEstadosMovimiento);
            for (InMovimientos inMovimientosListInMovimientos : inEstadosMovimiento.getInMovimientosList()) {
                InEstadosMovimiento oldIdEstadoOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getIdEstado();
                inMovimientosListInMovimientos.setIdEstado(inEstadosMovimiento);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldIdEstadoOfInMovimientosListInMovimientos != null) {
                    oldIdEstadoOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldIdEstadoOfInMovimientosListInMovimientos = em.merge(oldIdEstadoOfInMovimientosListInMovimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InEstadosMovimiento inEstadosMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InEstadosMovimiento persistentInEstadosMovimiento = em.find(InEstadosMovimiento.class, inEstadosMovimiento.getIdEstadoMovimiento());
            List<InMovimientos> inMovimientosListOld = persistentInEstadosMovimiento.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inEstadosMovimiento.getInMovimientosList();
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inEstadosMovimiento.setInMovimientosList(inMovimientosListNew);
            inEstadosMovimiento = em.merge(inEstadosMovimiento);
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    inMovimientosListOldInMovimientos.setIdEstado(null);
                    inMovimientosListOldInMovimientos = em.merge(inMovimientosListOldInMovimientos);
                }
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InEstadosMovimiento oldIdEstadoOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getIdEstado();
                    inMovimientosListNewInMovimientos.setIdEstado(inEstadosMovimiento);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldIdEstadoOfInMovimientosListNewInMovimientos != null && !oldIdEstadoOfInMovimientosListNewInMovimientos.equals(inEstadosMovimiento)) {
                        oldIdEstadoOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldIdEstadoOfInMovimientosListNewInMovimientos = em.merge(oldIdEstadoOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inEstadosMovimiento.getIdEstadoMovimiento();
                if (findInEstadosMovimiento(id) == null) {
                    throw new NonexistentEntityException("The inEstadosMovimiento with id " + id + " no longer exists.");
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
            InEstadosMovimiento inEstadosMovimiento;
            try {
                inEstadosMovimiento = em.getReference(InEstadosMovimiento.class, id);
                inEstadosMovimiento.getIdEstadoMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inEstadosMovimiento with id " + id + " no longer exists.", enfe);
            }
            List<InMovimientos> inMovimientosList = inEstadosMovimiento.getInMovimientosList();
            for (InMovimientos inMovimientosListInMovimientos : inMovimientosList) {
                inMovimientosListInMovimientos.setIdEstado(null);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
            }
            em.remove(inEstadosMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InEstadosMovimiento> findInEstadosMovimientoEntities() {
        return findInEstadosMovimientoEntities(true, -1, -1);
    }

    public List<InEstadosMovimiento> findInEstadosMovimientoEntities(int maxResults, int firstResult) {
        return findInEstadosMovimientoEntities(false, maxResults, firstResult);
    }

    private List<InEstadosMovimiento> findInEstadosMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InEstadosMovimiento.class));
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

    public InEstadosMovimiento findInEstadosMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InEstadosMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInEstadosMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InEstadosMovimiento> rt = cq.from(InEstadosMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
