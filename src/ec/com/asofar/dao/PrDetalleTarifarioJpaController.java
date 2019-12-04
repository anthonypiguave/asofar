/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrDetalleTarifario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrTarifario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class PrDetalleTarifarioJpaController implements Serializable {

    public PrDetalleTarifarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrDetalleTarifario prDetalleTarifario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTarifario prTarifario = prDetalleTarifario.getPrTarifario();
            if (prTarifario != null) {
                prTarifario = em.getReference(prTarifario.getClass(), prTarifario.getPrTarifarioPK());
                prDetalleTarifario.setPrTarifario(prTarifario);
            }
            em.persist(prDetalleTarifario);
            if (prTarifario != null) {
                prTarifario.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prTarifario = em.merge(prTarifario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrDetalleTarifario prDetalleTarifario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrDetalleTarifario persistentPrDetalleTarifario = em.find(PrDetalleTarifario.class, prDetalleTarifario.getIdDetalleTarifario());
            PrTarifario prTarifarioOld = persistentPrDetalleTarifario.getPrTarifario();
            PrTarifario prTarifarioNew = prDetalleTarifario.getPrTarifario();
            if (prTarifarioNew != null) {
                prTarifarioNew = em.getReference(prTarifarioNew.getClass(), prTarifarioNew.getPrTarifarioPK());
                prDetalleTarifario.setPrTarifario(prTarifarioNew);
            }
            prDetalleTarifario = em.merge(prDetalleTarifario);
            if (prTarifarioOld != null && !prTarifarioOld.equals(prTarifarioNew)) {
                prTarifarioOld.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prTarifarioOld = em.merge(prTarifarioOld);
            }
            if (prTarifarioNew != null && !prTarifarioNew.equals(prTarifarioOld)) {
                prTarifarioNew.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prTarifarioNew = em.merge(prTarifarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prDetalleTarifario.getIdDetalleTarifario();
                if (findPrDetalleTarifario(id) == null) {
                    throw new NonexistentEntityException("The prDetalleTarifario with id " + id + " no longer exists.");
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
            PrDetalleTarifario prDetalleTarifario;
            try {
                prDetalleTarifario = em.getReference(PrDetalleTarifario.class, id);
                prDetalleTarifario.getIdDetalleTarifario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prDetalleTarifario with id " + id + " no longer exists.", enfe);
            }
            PrTarifario prTarifario = prDetalleTarifario.getPrTarifario();
            if (prTarifario != null) {
                prTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prTarifario = em.merge(prTarifario);
            }
            em.remove(prDetalleTarifario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrDetalleTarifario> findPrDetalleTarifarioEntities() {
        return findPrDetalleTarifarioEntities(true, -1, -1);
    }

    public List<PrDetalleTarifario> findPrDetalleTarifarioEntities(int maxResults, int firstResult) {
        return findPrDetalleTarifarioEntities(false, maxResults, firstResult);
    }

    private List<PrDetalleTarifario> findPrDetalleTarifarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrDetalleTarifario.class));
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

    public PrDetalleTarifario findPrDetalleTarifario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrDetalleTarifario.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrDetalleTarifarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrDetalleTarifario> rt = cq.from(PrDetalleTarifario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
