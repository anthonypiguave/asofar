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
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrTipoPrestacion;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrPrestaciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class PrPrestacionesJpaController implements Serializable {

    public PrPrestacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrPrestaciones prPrestaciones) {
        if (prPrestaciones.getInPrestacionesPorServiciosList() == null) {
            prPrestaciones.setInPrestacionesPorServiciosList(new ArrayList<InPrestacionesPorServicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = prPrestaciones.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                prPrestaciones.setIdEmpresa(idEmpresa);
            }
            PrTipoPrestacion idTipoPrestacion = prPrestaciones.getIdTipoPrestacion();
            if (idTipoPrestacion != null) {
                idTipoPrestacion = em.getReference(idTipoPrestacion.getClass(), idTipoPrestacion.getIdTipoPrestacion());
                prPrestaciones.setIdTipoPrestacion(idTipoPrestacion);
            }
            List<InPrestacionesPorServicios> attachedInPrestacionesPorServiciosList = new ArrayList<InPrestacionesPorServicios>();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach : prPrestaciones.getInPrestacionesPorServiciosList()) {
                inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach = em.getReference(inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach.getClass(), inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach.getInPrestacionesPorServiciosPK());
                attachedInPrestacionesPorServiciosList.add(inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach);
            }
            prPrestaciones.setInPrestacionesPorServiciosList(attachedInPrestacionesPorServiciosList);
            em.persist(prPrestaciones);
            if (idEmpresa != null) {
                idEmpresa.getPrPrestacionesList().add(prPrestaciones);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idTipoPrestacion != null) {
                idTipoPrestacion.getPrPrestacionesList().add(prPrestaciones);
                idTipoPrestacion = em.merge(idTipoPrestacion);
            }
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListInPrestacionesPorServicios : prPrestaciones.getInPrestacionesPorServiciosList()) {
                PrPrestaciones oldPrPrestacionesOfInPrestacionesPorServiciosListInPrestacionesPorServicios = inPrestacionesPorServiciosListInPrestacionesPorServicios.getPrPrestaciones();
                inPrestacionesPorServiciosListInPrestacionesPorServicios.setPrPrestaciones(prPrestaciones);
                inPrestacionesPorServiciosListInPrestacionesPorServicios = em.merge(inPrestacionesPorServiciosListInPrestacionesPorServicios);
                if (oldPrPrestacionesOfInPrestacionesPorServiciosListInPrestacionesPorServicios != null) {
                    oldPrPrestacionesOfInPrestacionesPorServiciosListInPrestacionesPorServicios.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServiciosListInPrestacionesPorServicios);
                    oldPrPrestacionesOfInPrestacionesPorServiciosListInPrestacionesPorServicios = em.merge(oldPrPrestacionesOfInPrestacionesPorServiciosListInPrestacionesPorServicios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrPrestaciones prPrestaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrPrestaciones persistentPrPrestaciones = em.find(PrPrestaciones.class, prPrestaciones.getIdPrestacion());
            SeEmpresa idEmpresaOld = persistentPrPrestaciones.getIdEmpresa();
            SeEmpresa idEmpresaNew = prPrestaciones.getIdEmpresa();
            PrTipoPrestacion idTipoPrestacionOld = persistentPrPrestaciones.getIdTipoPrestacion();
            PrTipoPrestacion idTipoPrestacionNew = prPrestaciones.getIdTipoPrestacion();
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListOld = persistentPrPrestaciones.getInPrestacionesPorServiciosList();
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListNew = prPrestaciones.getInPrestacionesPorServiciosList();
            List<String> illegalOrphanMessages = null;
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListOldInPrestacionesPorServicios : inPrestacionesPorServiciosListOld) {
                if (!inPrestacionesPorServiciosListNew.contains(inPrestacionesPorServiciosListOldInPrestacionesPorServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InPrestacionesPorServicios " + inPrestacionesPorServiciosListOldInPrestacionesPorServicios + " since its prPrestaciones field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                prPrestaciones.setIdEmpresa(idEmpresaNew);
            }
            if (idTipoPrestacionNew != null) {
                idTipoPrestacionNew = em.getReference(idTipoPrestacionNew.getClass(), idTipoPrestacionNew.getIdTipoPrestacion());
                prPrestaciones.setIdTipoPrestacion(idTipoPrestacionNew);
            }
            List<InPrestacionesPorServicios> attachedInPrestacionesPorServiciosListNew = new ArrayList<InPrestacionesPorServicios>();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach : inPrestacionesPorServiciosListNew) {
                inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach = em.getReference(inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach.getClass(), inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach.getInPrestacionesPorServiciosPK());
                attachedInPrestacionesPorServiciosListNew.add(inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach);
            }
            inPrestacionesPorServiciosListNew = attachedInPrestacionesPorServiciosListNew;
            prPrestaciones.setInPrestacionesPorServiciosList(inPrestacionesPorServiciosListNew);
            prPrestaciones = em.merge(prPrestaciones);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPrPrestacionesList().remove(prPrestaciones);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPrPrestacionesList().add(prPrestaciones);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idTipoPrestacionOld != null && !idTipoPrestacionOld.equals(idTipoPrestacionNew)) {
                idTipoPrestacionOld.getPrPrestacionesList().remove(prPrestaciones);
                idTipoPrestacionOld = em.merge(idTipoPrestacionOld);
            }
            if (idTipoPrestacionNew != null && !idTipoPrestacionNew.equals(idTipoPrestacionOld)) {
                idTipoPrestacionNew.getPrPrestacionesList().add(prPrestaciones);
                idTipoPrestacionNew = em.merge(idTipoPrestacionNew);
            }
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListNewInPrestacionesPorServicios : inPrestacionesPorServiciosListNew) {
                if (!inPrestacionesPorServiciosListOld.contains(inPrestacionesPorServiciosListNewInPrestacionesPorServicios)) {
                    PrPrestaciones oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios = inPrestacionesPorServiciosListNewInPrestacionesPorServicios.getPrPrestaciones();
                    inPrestacionesPorServiciosListNewInPrestacionesPorServicios.setPrPrestaciones(prPrestaciones);
                    inPrestacionesPorServiciosListNewInPrestacionesPorServicios = em.merge(inPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                    if (oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios != null && !oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios.equals(prPrestaciones)) {
                        oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                        oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios = em.merge(oldPrPrestacionesOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prPrestaciones.getIdPrestacion();
                if (findPrPrestaciones(id) == null) {
                    throw new NonexistentEntityException("The prPrestaciones with id " + id + " no longer exists.");
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
            PrPrestaciones prPrestaciones;
            try {
                prPrestaciones = em.getReference(PrPrestaciones.class, id);
                prPrestaciones.getIdPrestacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prPrestaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListOrphanCheck = prPrestaciones.getInPrestacionesPorServiciosList();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListOrphanCheckInPrestacionesPorServicios : inPrestacionesPorServiciosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrPrestaciones (" + prPrestaciones + ") cannot be destroyed since the InPrestacionesPorServicios " + inPrestacionesPorServiciosListOrphanCheckInPrestacionesPorServicios + " in its inPrestacionesPorServiciosList field has a non-nullable prPrestaciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa idEmpresa = prPrestaciones.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPrPrestacionesList().remove(prPrestaciones);
                idEmpresa = em.merge(idEmpresa);
            }
            PrTipoPrestacion idTipoPrestacion = prPrestaciones.getIdTipoPrestacion();
            if (idTipoPrestacion != null) {
                idTipoPrestacion.getPrPrestacionesList().remove(prPrestaciones);
                idTipoPrestacion = em.merge(idTipoPrestacion);
            }
            em.remove(prPrestaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrPrestaciones> findPrPrestacionesEntities() {
        return findPrPrestacionesEntities(true, -1, -1);
    }

    public List<PrPrestaciones> findPrPrestacionesEntities(int maxResults, int firstResult) {
        return findPrPrestacionesEntities(false, maxResults, firstResult);
    }

    private List<PrPrestaciones> findPrPrestacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrPrestaciones.class));
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

    public PrPrestaciones findPrPrestaciones(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrPrestaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrPrestacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrPrestaciones> rt = cq.from(PrPrestaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
