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
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeTipoPersona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class SeTipoPersonaJpaController implements Serializable {

    public SeTipoPersonaJpaController() {
       this.emf = Persistence.createEntityManagerFactory("asofarPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeTipoPersona seTipoPersona) {
        if (seTipoPersona.getSePersonasList() == null) {
            seTipoPersona.setSePersonasList(new ArrayList<SePersonas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SePersonas> attachedSePersonasList = new ArrayList<SePersonas>();
            for (SePersonas sePersonasListSePersonasToAttach : seTipoPersona.getSePersonasList()) {
                sePersonasListSePersonasToAttach = em.getReference(sePersonasListSePersonasToAttach.getClass(), sePersonasListSePersonasToAttach.getSePersonasPK());
                attachedSePersonasList.add(sePersonasListSePersonasToAttach);
            }
            seTipoPersona.setSePersonasList(attachedSePersonasList);
            em.persist(seTipoPersona);
            for (SePersonas sePersonasListSePersonas : seTipoPersona.getSePersonasList()) {
                SeTipoPersona oldSeTipoPersonaOfSePersonasListSePersonas = sePersonasListSePersonas.getSeTipoPersona();
                sePersonasListSePersonas.setSeTipoPersona(seTipoPersona);
                sePersonasListSePersonas = em.merge(sePersonasListSePersonas);
                if (oldSeTipoPersonaOfSePersonasListSePersonas != null) {
                    oldSeTipoPersonaOfSePersonasListSePersonas.getSePersonasList().remove(sePersonasListSePersonas);
                    oldSeTipoPersonaOfSePersonasListSePersonas = em.merge(oldSeTipoPersonaOfSePersonasListSePersonas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeTipoPersona seTipoPersona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeTipoPersona persistentSeTipoPersona = em.find(SeTipoPersona.class, seTipoPersona.getIdTipoPersona());
            List<SePersonas> sePersonasListOld = persistentSeTipoPersona.getSePersonasList();
            List<SePersonas> sePersonasListNew = seTipoPersona.getSePersonasList();
            List<String> illegalOrphanMessages = null;
            for (SePersonas sePersonasListOldSePersonas : sePersonasListOld) {
                if (!sePersonasListNew.contains(sePersonasListOldSePersonas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SePersonas " + sePersonasListOldSePersonas + " since its seTipoPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SePersonas> attachedSePersonasListNew = new ArrayList<SePersonas>();
            for (SePersonas sePersonasListNewSePersonasToAttach : sePersonasListNew) {
                sePersonasListNewSePersonasToAttach = em.getReference(sePersonasListNewSePersonasToAttach.getClass(), sePersonasListNewSePersonasToAttach.getSePersonasPK());
                attachedSePersonasListNew.add(sePersonasListNewSePersonasToAttach);
            }
            sePersonasListNew = attachedSePersonasListNew;
            seTipoPersona.setSePersonasList(sePersonasListNew);
            seTipoPersona = em.merge(seTipoPersona);
            for (SePersonas sePersonasListNewSePersonas : sePersonasListNew) {
                if (!sePersonasListOld.contains(sePersonasListNewSePersonas)) {
                    SeTipoPersona oldSeTipoPersonaOfSePersonasListNewSePersonas = sePersonasListNewSePersonas.getSeTipoPersona();
                    sePersonasListNewSePersonas.setSeTipoPersona(seTipoPersona);
                    sePersonasListNewSePersonas = em.merge(sePersonasListNewSePersonas);
                    if (oldSeTipoPersonaOfSePersonasListNewSePersonas != null && !oldSeTipoPersonaOfSePersonasListNewSePersonas.equals(seTipoPersona)) {
                        oldSeTipoPersonaOfSePersonasListNewSePersonas.getSePersonasList().remove(sePersonasListNewSePersonas);
                        oldSeTipoPersonaOfSePersonasListNewSePersonas = em.merge(oldSeTipoPersonaOfSePersonasListNewSePersonas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seTipoPersona.getIdTipoPersona();
                if (findSeTipoPersona(id) == null) {
                    throw new NonexistentEntityException("The seTipoPersona with id " + id + " no longer exists.");
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
            SeTipoPersona seTipoPersona;
            try {
                seTipoPersona = em.getReference(SeTipoPersona.class, id);
                seTipoPersona.getIdTipoPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seTipoPersona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SePersonas> sePersonasListOrphanCheck = seTipoPersona.getSePersonasList();
            for (SePersonas sePersonasListOrphanCheckSePersonas : sePersonasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeTipoPersona (" + seTipoPersona + ") cannot be destroyed since the SePersonas " + sePersonasListOrphanCheckSePersonas + " in its sePersonasList field has a non-nullable seTipoPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(seTipoPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeTipoPersona> findSeTipoPersonaEntities() {
        return findSeTipoPersonaEntities(true, -1, -1);
    }

    public List<SeTipoPersona> findSeTipoPersonaEntities(int maxResults, int firstResult) {
        return findSeTipoPersonaEntities(false, maxResults, firstResult);
    }

    private List<SeTipoPersona> findSeTipoPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeTipoPersona.class));
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

    public SeTipoPersona findSeTipoPersona(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeTipoPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeTipoPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeTipoPersona> rt = cq.from(SeTipoPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
