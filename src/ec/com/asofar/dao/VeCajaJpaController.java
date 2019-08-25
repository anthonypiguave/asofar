/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.VeCaja;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.VeDetalleCaja;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin
 */
public class VeCajaJpaController implements Serializable {

    public VeCajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeCaja veCaja) {
        if (veCaja.getVeDetalleCajaList() == null) {
            veCaja.setVeDetalleCajaList(new ArrayList<VeDetalleCaja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<VeDetalleCaja> attachedVeDetalleCajaList = new ArrayList<VeDetalleCaja>();
            for (VeDetalleCaja veDetalleCajaListVeDetalleCajaToAttach : veCaja.getVeDetalleCajaList()) {
                veDetalleCajaListVeDetalleCajaToAttach = em.getReference(veDetalleCajaListVeDetalleCajaToAttach.getClass(), veDetalleCajaListVeDetalleCajaToAttach.getVeDetalleCajaPK());
                attachedVeDetalleCajaList.add(veDetalleCajaListVeDetalleCajaToAttach);
            }
            veCaja.setVeDetalleCajaList(attachedVeDetalleCajaList);
            em.persist(veCaja);
            for (VeDetalleCaja veDetalleCajaListVeDetalleCaja : veCaja.getVeDetalleCajaList()) {
                VeCaja oldVeCajaOfVeDetalleCajaListVeDetalleCaja = veDetalleCajaListVeDetalleCaja.getVeCaja();
                veDetalleCajaListVeDetalleCaja.setVeCaja(veCaja);
                veDetalleCajaListVeDetalleCaja = em.merge(veDetalleCajaListVeDetalleCaja);
                if (oldVeCajaOfVeDetalleCajaListVeDetalleCaja != null) {
                    oldVeCajaOfVeDetalleCajaListVeDetalleCaja.getVeDetalleCajaList().remove(veDetalleCajaListVeDetalleCaja);
                    oldVeCajaOfVeDetalleCajaListVeDetalleCaja = em.merge(oldVeCajaOfVeDetalleCajaListVeDetalleCaja);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeCaja veCaja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeCaja persistentVeCaja = em.find(VeCaja.class, veCaja.getIdCaja());
            List<VeDetalleCaja> veDetalleCajaListOld = persistentVeCaja.getVeDetalleCajaList();
            List<VeDetalleCaja> veDetalleCajaListNew = veCaja.getVeDetalleCajaList();
            List<String> illegalOrphanMessages = null;
            for (VeDetalleCaja veDetalleCajaListOldVeDetalleCaja : veDetalleCajaListOld) {
                if (!veDetalleCajaListNew.contains(veDetalleCajaListOldVeDetalleCaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeDetalleCaja " + veDetalleCajaListOldVeDetalleCaja + " since its veCaja field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<VeDetalleCaja> attachedVeDetalleCajaListNew = new ArrayList<VeDetalleCaja>();
            for (VeDetalleCaja veDetalleCajaListNewVeDetalleCajaToAttach : veDetalleCajaListNew) {
                veDetalleCajaListNewVeDetalleCajaToAttach = em.getReference(veDetalleCajaListNewVeDetalleCajaToAttach.getClass(), veDetalleCajaListNewVeDetalleCajaToAttach.getVeDetalleCajaPK());
                attachedVeDetalleCajaListNew.add(veDetalleCajaListNewVeDetalleCajaToAttach);
            }
            veDetalleCajaListNew = attachedVeDetalleCajaListNew;
            veCaja.setVeDetalleCajaList(veDetalleCajaListNew);
            veCaja = em.merge(veCaja);
            for (VeDetalleCaja veDetalleCajaListNewVeDetalleCaja : veDetalleCajaListNew) {
                if (!veDetalleCajaListOld.contains(veDetalleCajaListNewVeDetalleCaja)) {
                    VeCaja oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja = veDetalleCajaListNewVeDetalleCaja.getVeCaja();
                    veDetalleCajaListNewVeDetalleCaja.setVeCaja(veCaja);
                    veDetalleCajaListNewVeDetalleCaja = em.merge(veDetalleCajaListNewVeDetalleCaja);
                    if (oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja != null && !oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja.equals(veCaja)) {
                        oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja.getVeDetalleCajaList().remove(veDetalleCajaListNewVeDetalleCaja);
                        oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja = em.merge(oldVeCajaOfVeDetalleCajaListNewVeDetalleCaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = veCaja.getIdCaja();
                if (findVeCaja(id) == null) {
                    throw new NonexistentEntityException("The veCaja with id " + id + " no longer exists.");
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
            VeCaja veCaja;
            try {
                veCaja = em.getReference(VeCaja.class, id);
                veCaja.getIdCaja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veCaja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VeDetalleCaja> veDetalleCajaListOrphanCheck = veCaja.getVeDetalleCajaList();
            for (VeDetalleCaja veDetalleCajaListOrphanCheckVeDetalleCaja : veDetalleCajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VeCaja (" + veCaja + ") cannot be destroyed since the VeDetalleCaja " + veDetalleCajaListOrphanCheckVeDetalleCaja + " in its veDetalleCajaList field has a non-nullable veCaja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(veCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeCaja> findVeCajaEntities() {
        return findVeCajaEntities(true, -1, -1);
    }

    public List<VeCaja> findVeCajaEntities(int maxResults, int firstResult) {
        return findVeCajaEntities(false, maxResults, firstResult);
    }

    private List<VeCaja> findVeCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeCaja.class));
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

    public VeCaja findVeCaja(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeCaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeCaja> rt = cq.from(VeCaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
