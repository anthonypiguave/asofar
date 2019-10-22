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
import ec.com.asofar.dto.Administrador;
import ec.com.asofar.dto.Historial;
import ec.com.asofar.dto.Invitados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class HistorialJpaController implements Serializable {

    public HistorialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historial historial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador idadministrador = historial.getIdadministrador();
            if (idadministrador != null) {
                idadministrador = em.getReference(idadministrador.getClass(), idadministrador.getIdadministrador());
                historial.setIdadministrador(idadministrador);
            }
            Invitados idinvitado = historial.getIdinvitado();
            if (idinvitado != null) {
                idinvitado = em.getReference(idinvitado.getClass(), idinvitado.getIdinvitado());
                historial.setIdinvitado(idinvitado);
            }
            em.persist(historial);
            if (idadministrador != null) {
                idadministrador.getHistorialList().add(historial);
                idadministrador = em.merge(idadministrador);
            }
            if (idinvitado != null) {
                idinvitado.getHistorialList().add(historial);
                idinvitado = em.merge(idinvitado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historial historial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historial persistentHistorial = em.find(Historial.class, historial.getIdhistorial());
            Administrador idadministradorOld = persistentHistorial.getIdadministrador();
            Administrador idadministradorNew = historial.getIdadministrador();
            Invitados idinvitadoOld = persistentHistorial.getIdinvitado();
            Invitados idinvitadoNew = historial.getIdinvitado();
            if (idadministradorNew != null) {
                idadministradorNew = em.getReference(idadministradorNew.getClass(), idadministradorNew.getIdadministrador());
                historial.setIdadministrador(idadministradorNew);
            }
            if (idinvitadoNew != null) {
                idinvitadoNew = em.getReference(idinvitadoNew.getClass(), idinvitadoNew.getIdinvitado());
                historial.setIdinvitado(idinvitadoNew);
            }
            historial = em.merge(historial);
            if (idadministradorOld != null && !idadministradorOld.equals(idadministradorNew)) {
                idadministradorOld.getHistorialList().remove(historial);
                idadministradorOld = em.merge(idadministradorOld);
            }
            if (idadministradorNew != null && !idadministradorNew.equals(idadministradorOld)) {
                idadministradorNew.getHistorialList().add(historial);
                idadministradorNew = em.merge(idadministradorNew);
            }
            if (idinvitadoOld != null && !idinvitadoOld.equals(idinvitadoNew)) {
                idinvitadoOld.getHistorialList().remove(historial);
                idinvitadoOld = em.merge(idinvitadoOld);
            }
            if (idinvitadoNew != null && !idinvitadoNew.equals(idinvitadoOld)) {
                idinvitadoNew.getHistorialList().add(historial);
                idinvitadoNew = em.merge(idinvitadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historial.getIdhistorial();
                if (findHistorial(id) == null) {
                    throw new NonexistentEntityException("The historial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historial historial;
            try {
                historial = em.getReference(Historial.class, id);
                historial.getIdhistorial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historial with id " + id + " no longer exists.", enfe);
            }
            Administrador idadministrador = historial.getIdadministrador();
            if (idadministrador != null) {
                idadministrador.getHistorialList().remove(historial);
                idadministrador = em.merge(idadministrador);
            }
            Invitados idinvitado = historial.getIdinvitado();
            if (idinvitado != null) {
                idinvitado.getHistorialList().remove(historial);
                idinvitado = em.merge(idinvitado);
            }
            em.remove(historial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historial> findHistorialEntities() {
        return findHistorialEntities(true, -1, -1);
    }

    public List<Historial> findHistorialEntities(int maxResults, int firstResult) {
        return findHistorialEntities(false, maxResults, firstResult);
    }

    private List<Historial> findHistorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historial.class));
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

    public Historial findHistorial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historial.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historial> rt = cq.from(Historial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
