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
import ec.com.asofar.dto.Historial;
import ec.com.asofar.dto.Invitados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class InvitadosJpaController implements Serializable {

    public InvitadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Invitados invitados) {
        if (invitados.getHistorialList() == null) {
            invitados.setHistorialList(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historial> attachedHistorialList = new ArrayList<Historial>();
            for (Historial historialListHistorialToAttach : invitados.getHistorialList()) {
                historialListHistorialToAttach = em.getReference(historialListHistorialToAttach.getClass(), historialListHistorialToAttach.getIdhistorial());
                attachedHistorialList.add(historialListHistorialToAttach);
            }
            invitados.setHistorialList(attachedHistorialList);
            em.persist(invitados);
            for (Historial historialListHistorial : invitados.getHistorialList()) {
                Invitados oldIdinvitadoOfHistorialListHistorial = historialListHistorial.getIdinvitado();
                historialListHistorial.setIdinvitado(invitados);
                historialListHistorial = em.merge(historialListHistorial);
                if (oldIdinvitadoOfHistorialListHistorial != null) {
                    oldIdinvitadoOfHistorialListHistorial.getHistorialList().remove(historialListHistorial);
                    oldIdinvitadoOfHistorialListHistorial = em.merge(oldIdinvitadoOfHistorialListHistorial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Invitados invitados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invitados persistentInvitados = em.find(Invitados.class, invitados.getIdinvitado());
            List<Historial> historialListOld = persistentInvitados.getHistorialList();
            List<Historial> historialListNew = invitados.getHistorialList();
            List<String> illegalOrphanMessages = null;
            for (Historial historialListOldHistorial : historialListOld) {
                if (!historialListNew.contains(historialListOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialListOldHistorial + " since its idinvitado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historial> attachedHistorialListNew = new ArrayList<Historial>();
            for (Historial historialListNewHistorialToAttach : historialListNew) {
                historialListNewHistorialToAttach = em.getReference(historialListNewHistorialToAttach.getClass(), historialListNewHistorialToAttach.getIdhistorial());
                attachedHistorialListNew.add(historialListNewHistorialToAttach);
            }
            historialListNew = attachedHistorialListNew;
            invitados.setHistorialList(historialListNew);
            invitados = em.merge(invitados);
            for (Historial historialListNewHistorial : historialListNew) {
                if (!historialListOld.contains(historialListNewHistorial)) {
                    Invitados oldIdinvitadoOfHistorialListNewHistorial = historialListNewHistorial.getIdinvitado();
                    historialListNewHistorial.setIdinvitado(invitados);
                    historialListNewHistorial = em.merge(historialListNewHistorial);
                    if (oldIdinvitadoOfHistorialListNewHistorial != null && !oldIdinvitadoOfHistorialListNewHistorial.equals(invitados)) {
                        oldIdinvitadoOfHistorialListNewHistorial.getHistorialList().remove(historialListNewHistorial);
                        oldIdinvitadoOfHistorialListNewHistorial = em.merge(oldIdinvitadoOfHistorialListNewHistorial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invitados.getIdinvitado();
                if (findInvitados(id) == null) {
                    throw new NonexistentEntityException("The invitados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Invitados invitados;
            try {
                invitados = em.getReference(Invitados.class, id);
                invitados.getIdinvitado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invitados with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historial> historialListOrphanCheck = invitados.getHistorialList();
            for (Historial historialListOrphanCheckHistorial : historialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invitados (" + invitados + ") cannot be destroyed since the Historial " + historialListOrphanCheckHistorial + " in its historialList field has a non-nullable idinvitado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(invitados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Invitados> findInvitadosEntities() {
        return findInvitadosEntities(true, -1, -1);
    }

    public List<Invitados> findInvitadosEntities(int maxResults, int firstResult) {
        return findInvitadosEntities(false, maxResults, firstResult);
    }

    private List<Invitados> findInvitadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invitados.class));
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

    public Invitados findInvitados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Invitados.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvitadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invitados> rt = cq.from(Invitados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
