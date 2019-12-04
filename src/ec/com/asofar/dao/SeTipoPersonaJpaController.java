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
import ec.com.asofar.dto.SePersonas;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.SeTipoPersona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class SeTipoPersonaJpaController implements Serializable {

    public SeTipoPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeTipoPersona seTipoPersona) {
        if (seTipoPersona.getSePersonasList() == null) {
            seTipoPersona.setSePersonasList(new ArrayList<SePersonas>());
        }
        if (seTipoPersona.getCoProveedoresList() == null) {
            seTipoPersona.setCoProveedoresList(new ArrayList<CoProveedores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SePersonas> attachedSePersonasList = new ArrayList<SePersonas>();
            for (SePersonas sePersonasListSePersonasToAttach : seTipoPersona.getSePersonasList()) {
                sePersonasListSePersonasToAttach = em.getReference(sePersonasListSePersonasToAttach.getClass(), sePersonasListSePersonasToAttach.getIdPersona());
                attachedSePersonasList.add(sePersonasListSePersonasToAttach);
            }
            seTipoPersona.setSePersonasList(attachedSePersonasList);
            List<CoProveedores> attachedCoProveedoresList = new ArrayList<CoProveedores>();
            for (CoProveedores coProveedoresListCoProveedoresToAttach : seTipoPersona.getCoProveedoresList()) {
                coProveedoresListCoProveedoresToAttach = em.getReference(coProveedoresListCoProveedoresToAttach.getClass(), coProveedoresListCoProveedoresToAttach.getIdProveedor());
                attachedCoProveedoresList.add(coProveedoresListCoProveedoresToAttach);
            }
            seTipoPersona.setCoProveedoresList(attachedCoProveedoresList);
            em.persist(seTipoPersona);
            for (SePersonas sePersonasListSePersonas : seTipoPersona.getSePersonasList()) {
                SeTipoPersona oldIdTipoPersonaOfSePersonasListSePersonas = sePersonasListSePersonas.getIdTipoPersona();
                sePersonasListSePersonas.setIdTipoPersona(seTipoPersona);
                sePersonasListSePersonas = em.merge(sePersonasListSePersonas);
                if (oldIdTipoPersonaOfSePersonasListSePersonas != null) {
                    oldIdTipoPersonaOfSePersonasListSePersonas.getSePersonasList().remove(sePersonasListSePersonas);
                    oldIdTipoPersonaOfSePersonasListSePersonas = em.merge(oldIdTipoPersonaOfSePersonasListSePersonas);
                }
            }
            for (CoProveedores coProveedoresListCoProveedores : seTipoPersona.getCoProveedoresList()) {
                SeTipoPersona oldTipoPersonaOfCoProveedoresListCoProveedores = coProveedoresListCoProveedores.getTipoPersona();
                coProveedoresListCoProveedores.setTipoPersona(seTipoPersona);
                coProveedoresListCoProveedores = em.merge(coProveedoresListCoProveedores);
                if (oldTipoPersonaOfCoProveedoresListCoProveedores != null) {
                    oldTipoPersonaOfCoProveedoresListCoProveedores.getCoProveedoresList().remove(coProveedoresListCoProveedores);
                    oldTipoPersonaOfCoProveedoresListCoProveedores = em.merge(oldTipoPersonaOfCoProveedoresListCoProveedores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeTipoPersona seTipoPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeTipoPersona persistentSeTipoPersona = em.find(SeTipoPersona.class, seTipoPersona.getIdTipoPersona());
            List<SePersonas> sePersonasListOld = persistentSeTipoPersona.getSePersonasList();
            List<SePersonas> sePersonasListNew = seTipoPersona.getSePersonasList();
            List<CoProveedores> coProveedoresListOld = persistentSeTipoPersona.getCoProveedoresList();
            List<CoProveedores> coProveedoresListNew = seTipoPersona.getCoProveedoresList();
            List<SePersonas> attachedSePersonasListNew = new ArrayList<SePersonas>();
            for (SePersonas sePersonasListNewSePersonasToAttach : sePersonasListNew) {
                sePersonasListNewSePersonasToAttach = em.getReference(sePersonasListNewSePersonasToAttach.getClass(), sePersonasListNewSePersonasToAttach.getIdPersona());
                attachedSePersonasListNew.add(sePersonasListNewSePersonasToAttach);
            }
            sePersonasListNew = attachedSePersonasListNew;
            seTipoPersona.setSePersonasList(sePersonasListNew);
            List<CoProveedores> attachedCoProveedoresListNew = new ArrayList<CoProveedores>();
            for (CoProveedores coProveedoresListNewCoProveedoresToAttach : coProveedoresListNew) {
                coProveedoresListNewCoProveedoresToAttach = em.getReference(coProveedoresListNewCoProveedoresToAttach.getClass(), coProveedoresListNewCoProveedoresToAttach.getIdProveedor());
                attachedCoProveedoresListNew.add(coProveedoresListNewCoProveedoresToAttach);
            }
            coProveedoresListNew = attachedCoProveedoresListNew;
            seTipoPersona.setCoProveedoresList(coProveedoresListNew);
            seTipoPersona = em.merge(seTipoPersona);
            for (SePersonas sePersonasListOldSePersonas : sePersonasListOld) {
                if (!sePersonasListNew.contains(sePersonasListOldSePersonas)) {
                    sePersonasListOldSePersonas.setIdTipoPersona(null);
                    sePersonasListOldSePersonas = em.merge(sePersonasListOldSePersonas);
                }
            }
            for (SePersonas sePersonasListNewSePersonas : sePersonasListNew) {
                if (!sePersonasListOld.contains(sePersonasListNewSePersonas)) {
                    SeTipoPersona oldIdTipoPersonaOfSePersonasListNewSePersonas = sePersonasListNewSePersonas.getIdTipoPersona();
                    sePersonasListNewSePersonas.setIdTipoPersona(seTipoPersona);
                    sePersonasListNewSePersonas = em.merge(sePersonasListNewSePersonas);
                    if (oldIdTipoPersonaOfSePersonasListNewSePersonas != null && !oldIdTipoPersonaOfSePersonasListNewSePersonas.equals(seTipoPersona)) {
                        oldIdTipoPersonaOfSePersonasListNewSePersonas.getSePersonasList().remove(sePersonasListNewSePersonas);
                        oldIdTipoPersonaOfSePersonasListNewSePersonas = em.merge(oldIdTipoPersonaOfSePersonasListNewSePersonas);
                    }
                }
            }
            for (CoProveedores coProveedoresListOldCoProveedores : coProveedoresListOld) {
                if (!coProveedoresListNew.contains(coProveedoresListOldCoProveedores)) {
                    coProveedoresListOldCoProveedores.setTipoPersona(null);
                    coProveedoresListOldCoProveedores = em.merge(coProveedoresListOldCoProveedores);
                }
            }
            for (CoProveedores coProveedoresListNewCoProveedores : coProveedoresListNew) {
                if (!coProveedoresListOld.contains(coProveedoresListNewCoProveedores)) {
                    SeTipoPersona oldTipoPersonaOfCoProveedoresListNewCoProveedores = coProveedoresListNewCoProveedores.getTipoPersona();
                    coProveedoresListNewCoProveedores.setTipoPersona(seTipoPersona);
                    coProveedoresListNewCoProveedores = em.merge(coProveedoresListNewCoProveedores);
                    if (oldTipoPersonaOfCoProveedoresListNewCoProveedores != null && !oldTipoPersonaOfCoProveedoresListNewCoProveedores.equals(seTipoPersona)) {
                        oldTipoPersonaOfCoProveedoresListNewCoProveedores.getCoProveedoresList().remove(coProveedoresListNewCoProveedores);
                        oldTipoPersonaOfCoProveedoresListNewCoProveedores = em.merge(oldTipoPersonaOfCoProveedoresListNewCoProveedores);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            List<SePersonas> sePersonasList = seTipoPersona.getSePersonasList();
            for (SePersonas sePersonasListSePersonas : sePersonasList) {
                sePersonasListSePersonas.setIdTipoPersona(null);
                sePersonasListSePersonas = em.merge(sePersonasListSePersonas);
            }
            List<CoProveedores> coProveedoresList = seTipoPersona.getCoProveedoresList();
            for (CoProveedores coProveedoresListCoProveedores : coProveedoresList) {
                coProveedoresListCoProveedores.setTipoPersona(null);
                coProveedoresListCoProveedores = em.merge(coProveedoresListCoProveedores);
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
