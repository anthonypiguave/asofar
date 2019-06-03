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
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrTipoPrestacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class PrTipoPrestacionJpaController implements Serializable {

    public PrTipoPrestacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrTipoPrestacion prTipoPrestacion) {
        if (prTipoPrestacion.getPrPrestacionesList() == null) {
            prTipoPrestacion.setPrPrestacionesList(new ArrayList<PrPrestaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrPrestaciones> attachedPrPrestacionesList = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListPrPrestacionesToAttach : prTipoPrestacion.getPrPrestacionesList()) {
                prPrestacionesListPrPrestacionesToAttach = em.getReference(prPrestacionesListPrPrestacionesToAttach.getClass(), prPrestacionesListPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesList.add(prPrestacionesListPrPrestacionesToAttach);
            }
            prTipoPrestacion.setPrPrestacionesList(attachedPrPrestacionesList);
            em.persist(prTipoPrestacion);
            for (PrPrestaciones prPrestacionesListPrPrestaciones : prTipoPrestacion.getPrPrestacionesList()) {
                PrTipoPrestacion oldTipoPrestacionOfPrPrestacionesListPrPrestaciones = prPrestacionesListPrPrestaciones.getTipoPrestacion();
                prPrestacionesListPrPrestaciones.setTipoPrestacion(prTipoPrestacion);
                prPrestacionesListPrPrestaciones = em.merge(prPrestacionesListPrPrestaciones);
                if (oldTipoPrestacionOfPrPrestacionesListPrPrestaciones != null) {
                    oldTipoPrestacionOfPrPrestacionesListPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListPrPrestaciones);
                    oldTipoPrestacionOfPrPrestacionesListPrPrestaciones = em.merge(oldTipoPrestacionOfPrPrestacionesListPrPrestaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrTipoPrestacion prTipoPrestacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTipoPrestacion persistentPrTipoPrestacion = em.find(PrTipoPrestacion.class, prTipoPrestacion.getIdTipoPrestacion());
            List<PrPrestaciones> prPrestacionesListOld = persistentPrTipoPrestacion.getPrPrestacionesList();
            List<PrPrestaciones> prPrestacionesListNew = prTipoPrestacion.getPrPrestacionesList();
            List<PrPrestaciones> attachedPrPrestacionesListNew = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListNewPrPrestacionesToAttach : prPrestacionesListNew) {
                prPrestacionesListNewPrPrestacionesToAttach = em.getReference(prPrestacionesListNewPrPrestacionesToAttach.getClass(), prPrestacionesListNewPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesListNew.add(prPrestacionesListNewPrPrestacionesToAttach);
            }
            prPrestacionesListNew = attachedPrPrestacionesListNew;
            prTipoPrestacion.setPrPrestacionesList(prPrestacionesListNew);
            prTipoPrestacion = em.merge(prTipoPrestacion);
            for (PrPrestaciones prPrestacionesListOldPrPrestaciones : prPrestacionesListOld) {
                if (!prPrestacionesListNew.contains(prPrestacionesListOldPrPrestaciones)) {
                    prPrestacionesListOldPrPrestaciones.setTipoPrestacion(null);
                    prPrestacionesListOldPrPrestaciones = em.merge(prPrestacionesListOldPrPrestaciones);
                }
            }
            for (PrPrestaciones prPrestacionesListNewPrPrestaciones : prPrestacionesListNew) {
                if (!prPrestacionesListOld.contains(prPrestacionesListNewPrPrestaciones)) {
                    PrTipoPrestacion oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones = prPrestacionesListNewPrPrestaciones.getTipoPrestacion();
                    prPrestacionesListNewPrPrestaciones.setTipoPrestacion(prTipoPrestacion);
                    prPrestacionesListNewPrPrestaciones = em.merge(prPrestacionesListNewPrPrestaciones);
                    if (oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones != null && !oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones.equals(prTipoPrestacion)) {
                        oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListNewPrPrestaciones);
                        oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones = em.merge(oldTipoPrestacionOfPrPrestacionesListNewPrPrestaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prTipoPrestacion.getIdTipoPrestacion();
                if (findPrTipoPrestacion(id) == null) {
                    throw new NonexistentEntityException("The prTipoPrestacion with id " + id + " no longer exists.");
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
            PrTipoPrestacion prTipoPrestacion;
            try {
                prTipoPrestacion = em.getReference(PrTipoPrestacion.class, id);
                prTipoPrestacion.getIdTipoPrestacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prTipoPrestacion with id " + id + " no longer exists.", enfe);
            }
            List<PrPrestaciones> prPrestacionesList = prTipoPrestacion.getPrPrestacionesList();
            for (PrPrestaciones prPrestacionesListPrPrestaciones : prPrestacionesList) {
                prPrestacionesListPrPrestaciones.setTipoPrestacion(null);
                prPrestacionesListPrPrestaciones = em.merge(prPrestacionesListPrPrestaciones);
            }
            em.remove(prTipoPrestacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrTipoPrestacion> findPrTipoPrestacionEntities() {
        return findPrTipoPrestacionEntities(true, -1, -1);
    }

    public List<PrTipoPrestacion> findPrTipoPrestacionEntities(int maxResults, int firstResult) {
        return findPrTipoPrestacionEntities(false, maxResults, firstResult);
    }

    private List<PrTipoPrestacion> findPrTipoPrestacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrTipoPrestacion.class));
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

    public PrTipoPrestacion findPrTipoPrestacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrTipoPrestacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrTipoPrestacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrTipoPrestacion> rt = cq.from(PrTipoPrestacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
