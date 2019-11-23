/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.CoDetallesTarifa;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.InPrestacionesPorServiciosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class InPrestacionesPorServiciosJpaController implements Serializable {

    public InPrestacionesPorServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InPrestacionesPorServicios inPrestacionesPorServicios) throws PreexistingEntityException, Exception {
        if (inPrestacionesPorServicios.getInPrestacionesPorServiciosPK() == null) {
            inPrestacionesPorServicios.setInPrestacionesPorServiciosPK(new InPrestacionesPorServiciosPK());
        }
        if (inPrestacionesPorServicios.getCoDetallesTarifaList() == null) {
            inPrestacionesPorServicios.setCoDetallesTarifaList(new ArrayList<CoDetallesTarifa>());
        }
        inPrestacionesPorServicios.getInPrestacionesPorServiciosPK().setIdPrestacion(inPrestacionesPorServicios.getPrPrestaciones().getIdPrestacion());
        inPrestacionesPorServicios.getInPrestacionesPorServiciosPK().setIdUnidadServicio(inPrestacionesPorServicios.getVeUnidadServicio().getIdUnidadServicio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeUnidadServicio veUnidadServicio = inPrestacionesPorServicios.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio = em.getReference(veUnidadServicio.getClass(), veUnidadServicio.getIdUnidadServicio());
                inPrestacionesPorServicios.setVeUnidadServicio(veUnidadServicio);
            }
            PrPrestaciones prPrestaciones = inPrestacionesPorServicios.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones = em.getReference(prPrestaciones.getClass(), prPrestaciones.getIdPrestacion());
                inPrestacionesPorServicios.setPrPrestaciones(prPrestaciones);
            }
            List<CoDetallesTarifa> attachedCoDetallesTarifaList = new ArrayList<CoDetallesTarifa>();
            for (CoDetallesTarifa coDetallesTarifaListCoDetallesTarifaToAttach : inPrestacionesPorServicios.getCoDetallesTarifaList()) {
                coDetallesTarifaListCoDetallesTarifaToAttach = em.getReference(coDetallesTarifaListCoDetallesTarifaToAttach.getClass(), coDetallesTarifaListCoDetallesTarifaToAttach.getCoDetallesTarifaPK());
                attachedCoDetallesTarifaList.add(coDetallesTarifaListCoDetallesTarifaToAttach);
            }
            inPrestacionesPorServicios.setCoDetallesTarifaList(attachedCoDetallesTarifaList);
            em.persist(inPrestacionesPorServicios);
            if (veUnidadServicio != null) {
                veUnidadServicio.getInPrestacionesPorServiciosList().add(inPrestacionesPorServicios);
                veUnidadServicio = em.merge(veUnidadServicio);
            }
            if (prPrestaciones != null) {
                prPrestaciones.getInPrestacionesPorServiciosList().add(inPrestacionesPorServicios);
                prPrestaciones = em.merge(prPrestaciones);
            }
            for (CoDetallesTarifa coDetallesTarifaListCoDetallesTarifa : inPrestacionesPorServicios.getCoDetallesTarifaList()) {
                InPrestacionesPorServicios oldInPrestacionesPorServiciosOfCoDetallesTarifaListCoDetallesTarifa = coDetallesTarifaListCoDetallesTarifa.getInPrestacionesPorServicios();
                coDetallesTarifaListCoDetallesTarifa.setInPrestacionesPorServicios(inPrestacionesPorServicios);
                coDetallesTarifaListCoDetallesTarifa = em.merge(coDetallesTarifaListCoDetallesTarifa);
                if (oldInPrestacionesPorServiciosOfCoDetallesTarifaListCoDetallesTarifa != null) {
                    oldInPrestacionesPorServiciosOfCoDetallesTarifaListCoDetallesTarifa.getCoDetallesTarifaList().remove(coDetallesTarifaListCoDetallesTarifa);
                    oldInPrestacionesPorServiciosOfCoDetallesTarifaListCoDetallesTarifa = em.merge(oldInPrestacionesPorServiciosOfCoDetallesTarifaListCoDetallesTarifa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInPrestacionesPorServicios(inPrestacionesPorServicios.getInPrestacionesPorServiciosPK()) != null) {
                throw new PreexistingEntityException("InPrestacionesPorServicios " + inPrestacionesPorServicios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InPrestacionesPorServicios inPrestacionesPorServicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        inPrestacionesPorServicios.getInPrestacionesPorServiciosPK().setIdPrestacion(inPrestacionesPorServicios.getPrPrestaciones().getIdPrestacion());
        inPrestacionesPorServicios.getInPrestacionesPorServiciosPK().setIdUnidadServicio(inPrestacionesPorServicios.getVeUnidadServicio().getIdUnidadServicio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InPrestacionesPorServicios persistentInPrestacionesPorServicios = em.find(InPrestacionesPorServicios.class, inPrestacionesPorServicios.getInPrestacionesPorServiciosPK());
            VeUnidadServicio veUnidadServicioOld = persistentInPrestacionesPorServicios.getVeUnidadServicio();
            VeUnidadServicio veUnidadServicioNew = inPrestacionesPorServicios.getVeUnidadServicio();
            PrPrestaciones prPrestacionesOld = persistentInPrestacionesPorServicios.getPrPrestaciones();
            PrPrestaciones prPrestacionesNew = inPrestacionesPorServicios.getPrPrestaciones();
            List<CoDetallesTarifa> coDetallesTarifaListOld = persistentInPrestacionesPorServicios.getCoDetallesTarifaList();
            List<CoDetallesTarifa> coDetallesTarifaListNew = inPrestacionesPorServicios.getCoDetallesTarifaList();
            List<String> illegalOrphanMessages = null;
            for (CoDetallesTarifa coDetallesTarifaListOldCoDetallesTarifa : coDetallesTarifaListOld) {
                if (!coDetallesTarifaListNew.contains(coDetallesTarifaListOldCoDetallesTarifa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetallesTarifa " + coDetallesTarifaListOldCoDetallesTarifa + " since its inPrestacionesPorServicios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (veUnidadServicioNew != null) {
                veUnidadServicioNew = em.getReference(veUnidadServicioNew.getClass(), veUnidadServicioNew.getIdUnidadServicio());
                inPrestacionesPorServicios.setVeUnidadServicio(veUnidadServicioNew);
            }
            if (prPrestacionesNew != null) {
                prPrestacionesNew = em.getReference(prPrestacionesNew.getClass(), prPrestacionesNew.getIdPrestacion());
                inPrestacionesPorServicios.setPrPrestaciones(prPrestacionesNew);
            }
            List<CoDetallesTarifa> attachedCoDetallesTarifaListNew = new ArrayList<CoDetallesTarifa>();
            for (CoDetallesTarifa coDetallesTarifaListNewCoDetallesTarifaToAttach : coDetallesTarifaListNew) {
                coDetallesTarifaListNewCoDetallesTarifaToAttach = em.getReference(coDetallesTarifaListNewCoDetallesTarifaToAttach.getClass(), coDetallesTarifaListNewCoDetallesTarifaToAttach.getCoDetallesTarifaPK());
                attachedCoDetallesTarifaListNew.add(coDetallesTarifaListNewCoDetallesTarifaToAttach);
            }
            coDetallesTarifaListNew = attachedCoDetallesTarifaListNew;
            inPrestacionesPorServicios.setCoDetallesTarifaList(coDetallesTarifaListNew);
            inPrestacionesPorServicios = em.merge(inPrestacionesPorServicios);
            if (veUnidadServicioOld != null && !veUnidadServicioOld.equals(veUnidadServicioNew)) {
                veUnidadServicioOld.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServicios);
                veUnidadServicioOld = em.merge(veUnidadServicioOld);
            }
            if (veUnidadServicioNew != null && !veUnidadServicioNew.equals(veUnidadServicioOld)) {
                veUnidadServicioNew.getInPrestacionesPorServiciosList().add(inPrestacionesPorServicios);
                veUnidadServicioNew = em.merge(veUnidadServicioNew);
            }
            if (prPrestacionesOld != null && !prPrestacionesOld.equals(prPrestacionesNew)) {
                prPrestacionesOld.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServicios);
                prPrestacionesOld = em.merge(prPrestacionesOld);
            }
            if (prPrestacionesNew != null && !prPrestacionesNew.equals(prPrestacionesOld)) {
                prPrestacionesNew.getInPrestacionesPorServiciosList().add(inPrestacionesPorServicios);
                prPrestacionesNew = em.merge(prPrestacionesNew);
            }
            for (CoDetallesTarifa coDetallesTarifaListNewCoDetallesTarifa : coDetallesTarifaListNew) {
                if (!coDetallesTarifaListOld.contains(coDetallesTarifaListNewCoDetallesTarifa)) {
                    InPrestacionesPorServicios oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa = coDetallesTarifaListNewCoDetallesTarifa.getInPrestacionesPorServicios();
                    coDetallesTarifaListNewCoDetallesTarifa.setInPrestacionesPorServicios(inPrestacionesPorServicios);
                    coDetallesTarifaListNewCoDetallesTarifa = em.merge(coDetallesTarifaListNewCoDetallesTarifa);
                    if (oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa != null && !oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa.equals(inPrestacionesPorServicios)) {
                        oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa.getCoDetallesTarifaList().remove(coDetallesTarifaListNewCoDetallesTarifa);
                        oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa = em.merge(oldInPrestacionesPorServiciosOfCoDetallesTarifaListNewCoDetallesTarifa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InPrestacionesPorServiciosPK id = inPrestacionesPorServicios.getInPrestacionesPorServiciosPK();
                if (findInPrestacionesPorServicios(id) == null) {
                    throw new NonexistentEntityException("The inPrestacionesPorServicios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InPrestacionesPorServiciosPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InPrestacionesPorServicios inPrestacionesPorServicios;
            try {
                inPrestacionesPorServicios = em.getReference(InPrestacionesPorServicios.class, id);
                inPrestacionesPorServicios.getInPrestacionesPorServiciosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inPrestacionesPorServicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CoDetallesTarifa> coDetallesTarifaListOrphanCheck = inPrestacionesPorServicios.getCoDetallesTarifaList();
            for (CoDetallesTarifa coDetallesTarifaListOrphanCheckCoDetallesTarifa : coDetallesTarifaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InPrestacionesPorServicios (" + inPrestacionesPorServicios + ") cannot be destroyed since the CoDetallesTarifa " + coDetallesTarifaListOrphanCheckCoDetallesTarifa + " in its coDetallesTarifaList field has a non-nullable inPrestacionesPorServicios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            VeUnidadServicio veUnidadServicio = inPrestacionesPorServicios.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServicios);
                veUnidadServicio = em.merge(veUnidadServicio);
            }
            PrPrestaciones prPrestaciones = inPrestacionesPorServicios.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServicios);
                prPrestaciones = em.merge(prPrestaciones);
            }
            em.remove(inPrestacionesPorServicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InPrestacionesPorServicios> findInPrestacionesPorServiciosEntities() {
        return findInPrestacionesPorServiciosEntities(true, -1, -1);
    }

    public List<InPrestacionesPorServicios> findInPrestacionesPorServiciosEntities(int maxResults, int firstResult) {
        return findInPrestacionesPorServiciosEntities(false, maxResults, firstResult);
    }

    private List<InPrestacionesPorServicios> findInPrestacionesPorServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InPrestacionesPorServicios.class));
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

    public InPrestacionesPorServicios findInPrestacionesPorServicios(InPrestacionesPorServiciosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InPrestacionesPorServicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getInPrestacionesPorServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InPrestacionesPorServicios> rt = cq.from(InPrestacionesPorServicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
