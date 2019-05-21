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
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
 */
public class PrTipoPresentacionJpaController implements Serializable {

    public PrTipoPresentacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrTipoPresentacion prTipoPresentacion) {
        if (prTipoPresentacion.getPrMedidasList() == null) {
            prTipoPresentacion.setPrMedidasList(new ArrayList<PrMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrMedidas> attachedPrMedidasList = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListPrMedidasToAttach : prTipoPresentacion.getPrMedidasList()) {
                prMedidasListPrMedidasToAttach = em.getReference(prMedidasListPrMedidasToAttach.getClass(), prMedidasListPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasList.add(prMedidasListPrMedidasToAttach);
            }
            prTipoPresentacion.setPrMedidasList(attachedPrMedidasList);
            em.persist(prTipoPresentacion);
            for (PrMedidas prMedidasListPrMedidas : prTipoPresentacion.getPrMedidasList()) {
                PrTipoPresentacion oldPrTipoPresentacionOfPrMedidasListPrMedidas = prMedidasListPrMedidas.getPrTipoPresentacion();
                prMedidasListPrMedidas.setPrTipoPresentacion(prTipoPresentacion);
                prMedidasListPrMedidas = em.merge(prMedidasListPrMedidas);
                if (oldPrTipoPresentacionOfPrMedidasListPrMedidas != null) {
                    oldPrTipoPresentacionOfPrMedidasListPrMedidas.getPrMedidasList().remove(prMedidasListPrMedidas);
                    oldPrTipoPresentacionOfPrMedidasListPrMedidas = em.merge(oldPrTipoPresentacionOfPrMedidasListPrMedidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrTipoPresentacion prTipoPresentacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTipoPresentacion persistentPrTipoPresentacion = em.find(PrTipoPresentacion.class, prTipoPresentacion.getIdTipoPresentacion());
            List<PrMedidas> prMedidasListOld = persistentPrTipoPresentacion.getPrMedidasList();
            List<PrMedidas> prMedidasListNew = prTipoPresentacion.getPrMedidasList();
            List<String> illegalOrphanMessages = null;
            for (PrMedidas prMedidasListOldPrMedidas : prMedidasListOld) {
                if (!prMedidasListNew.contains(prMedidasListOldPrMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrMedidas " + prMedidasListOldPrMedidas + " since its prTipoPresentacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PrMedidas> attachedPrMedidasListNew = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListNewPrMedidasToAttach : prMedidasListNew) {
                prMedidasListNewPrMedidasToAttach = em.getReference(prMedidasListNewPrMedidasToAttach.getClass(), prMedidasListNewPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasListNew.add(prMedidasListNewPrMedidasToAttach);
            }
            prMedidasListNew = attachedPrMedidasListNew;
            prTipoPresentacion.setPrMedidasList(prMedidasListNew);
            prTipoPresentacion = em.merge(prTipoPresentacion);
            for (PrMedidas prMedidasListNewPrMedidas : prMedidasListNew) {
                if (!prMedidasListOld.contains(prMedidasListNewPrMedidas)) {
                    PrTipoPresentacion oldPrTipoPresentacionOfPrMedidasListNewPrMedidas = prMedidasListNewPrMedidas.getPrTipoPresentacion();
                    prMedidasListNewPrMedidas.setPrTipoPresentacion(prTipoPresentacion);
                    prMedidasListNewPrMedidas = em.merge(prMedidasListNewPrMedidas);
                    if (oldPrTipoPresentacionOfPrMedidasListNewPrMedidas != null && !oldPrTipoPresentacionOfPrMedidasListNewPrMedidas.equals(prTipoPresentacion)) {
                        oldPrTipoPresentacionOfPrMedidasListNewPrMedidas.getPrMedidasList().remove(prMedidasListNewPrMedidas);
                        oldPrTipoPresentacionOfPrMedidasListNewPrMedidas = em.merge(oldPrTipoPresentacionOfPrMedidasListNewPrMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prTipoPresentacion.getIdTipoPresentacion();
                if (findPrTipoPresentacion(id) == null) {
                    throw new NonexistentEntityException("The prTipoPresentacion with id " + id + " no longer exists.");
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
            PrTipoPresentacion prTipoPresentacion;
            try {
                prTipoPresentacion = em.getReference(PrTipoPresentacion.class, id);
                prTipoPresentacion.getIdTipoPresentacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prTipoPresentacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrMedidas> prMedidasListOrphanCheck = prTipoPresentacion.getPrMedidasList();
            for (PrMedidas prMedidasListOrphanCheckPrMedidas : prMedidasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrTipoPresentacion (" + prTipoPresentacion + ") cannot be destroyed since the PrMedidas " + prMedidasListOrphanCheckPrMedidas + " in its prMedidasList field has a non-nullable prTipoPresentacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(prTipoPresentacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrTipoPresentacion> findPrTipoPresentacionEntities() {
        return findPrTipoPresentacionEntities(true, -1, -1);
    }

    public List<PrTipoPresentacion> findPrTipoPresentacionEntities(int maxResults, int firstResult) {
        return findPrTipoPresentacionEntities(false, maxResults, firstResult);
    }

    private List<PrTipoPresentacion> findPrTipoPresentacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrTipoPresentacion.class));
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

    public PrTipoPresentacion findPrTipoPresentacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrTipoPresentacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrTipoPresentacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrTipoPresentacion> rt = cq.from(PrTipoPresentacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
