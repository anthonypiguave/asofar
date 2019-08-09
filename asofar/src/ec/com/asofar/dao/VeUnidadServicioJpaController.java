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
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.VeUnidadServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin
 */
public class VeUnidadServicioJpaController implements Serializable {

    public VeUnidadServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeUnidadServicio veUnidadServicio) {
        if (veUnidadServicio.getInPrestacionesPorServiciosList() == null) {
            veUnidadServicio.setInPrestacionesPorServiciosList(new ArrayList<InPrestacionesPorServicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = veUnidadServicio.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                veUnidadServicio.setIdEmpresa(idEmpresa);
            }
            List<InPrestacionesPorServicios> attachedInPrestacionesPorServiciosList = new ArrayList<InPrestacionesPorServicios>();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach : veUnidadServicio.getInPrestacionesPorServiciosList()) {
                inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach = em.getReference(inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach.getClass(), inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach.getInPrestacionesPorServiciosPK());
                attachedInPrestacionesPorServiciosList.add(inPrestacionesPorServiciosListInPrestacionesPorServiciosToAttach);
            }
            veUnidadServicio.setInPrestacionesPorServiciosList(attachedInPrestacionesPorServiciosList);
            em.persist(veUnidadServicio);
            if (idEmpresa != null) {
                idEmpresa.getVeUnidadServicioList().add(veUnidadServicio);
                idEmpresa = em.merge(idEmpresa);
            }
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListInPrestacionesPorServicios : veUnidadServicio.getInPrestacionesPorServiciosList()) {
                VeUnidadServicio oldVeUnidadServicioOfInPrestacionesPorServiciosListInPrestacionesPorServicios = inPrestacionesPorServiciosListInPrestacionesPorServicios.getVeUnidadServicio();
                inPrestacionesPorServiciosListInPrestacionesPorServicios.setVeUnidadServicio(veUnidadServicio);
                inPrestacionesPorServiciosListInPrestacionesPorServicios = em.merge(inPrestacionesPorServiciosListInPrestacionesPorServicios);
                if (oldVeUnidadServicioOfInPrestacionesPorServiciosListInPrestacionesPorServicios != null) {
                    oldVeUnidadServicioOfInPrestacionesPorServiciosListInPrestacionesPorServicios.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServiciosListInPrestacionesPorServicios);
                    oldVeUnidadServicioOfInPrestacionesPorServiciosListInPrestacionesPorServicios = em.merge(oldVeUnidadServicioOfInPrestacionesPorServiciosListInPrestacionesPorServicios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeUnidadServicio veUnidadServicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeUnidadServicio persistentVeUnidadServicio = em.find(VeUnidadServicio.class, veUnidadServicio.getIdUnidadServicio());
            SeEmpresa idEmpresaOld = persistentVeUnidadServicio.getIdEmpresa();
            SeEmpresa idEmpresaNew = veUnidadServicio.getIdEmpresa();
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListOld = persistentVeUnidadServicio.getInPrestacionesPorServiciosList();
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListNew = veUnidadServicio.getInPrestacionesPorServiciosList();
            List<String> illegalOrphanMessages = null;
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListOldInPrestacionesPorServicios : inPrestacionesPorServiciosListOld) {
                if (!inPrestacionesPorServiciosListNew.contains(inPrestacionesPorServiciosListOldInPrestacionesPorServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InPrestacionesPorServicios " + inPrestacionesPorServiciosListOldInPrestacionesPorServicios + " since its veUnidadServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                veUnidadServicio.setIdEmpresa(idEmpresaNew);
            }
            List<InPrestacionesPorServicios> attachedInPrestacionesPorServiciosListNew = new ArrayList<InPrestacionesPorServicios>();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach : inPrestacionesPorServiciosListNew) {
                inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach = em.getReference(inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach.getClass(), inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach.getInPrestacionesPorServiciosPK());
                attachedInPrestacionesPorServiciosListNew.add(inPrestacionesPorServiciosListNewInPrestacionesPorServiciosToAttach);
            }
            inPrestacionesPorServiciosListNew = attachedInPrestacionesPorServiciosListNew;
            veUnidadServicio.setInPrestacionesPorServiciosList(inPrestacionesPorServiciosListNew);
            veUnidadServicio = em.merge(veUnidadServicio);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getVeUnidadServicioList().remove(veUnidadServicio);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getVeUnidadServicioList().add(veUnidadServicio);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListNewInPrestacionesPorServicios : inPrestacionesPorServiciosListNew) {
                if (!inPrestacionesPorServiciosListOld.contains(inPrestacionesPorServiciosListNewInPrestacionesPorServicios)) {
                    VeUnidadServicio oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios = inPrestacionesPorServiciosListNewInPrestacionesPorServicios.getVeUnidadServicio();
                    inPrestacionesPorServiciosListNewInPrestacionesPorServicios.setVeUnidadServicio(veUnidadServicio);
                    inPrestacionesPorServiciosListNewInPrestacionesPorServicios = em.merge(inPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                    if (oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios != null && !oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios.equals(veUnidadServicio)) {
                        oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios.getInPrestacionesPorServiciosList().remove(inPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                        oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios = em.merge(oldVeUnidadServicioOfInPrestacionesPorServiciosListNewInPrestacionesPorServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = veUnidadServicio.getIdUnidadServicio();
                if (findVeUnidadServicio(id) == null) {
                    throw new NonexistentEntityException("The veUnidadServicio with id " + id + " no longer exists.");
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
            VeUnidadServicio veUnidadServicio;
            try {
                veUnidadServicio = em.getReference(VeUnidadServicio.class, id);
                veUnidadServicio.getIdUnidadServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veUnidadServicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InPrestacionesPorServicios> inPrestacionesPorServiciosListOrphanCheck = veUnidadServicio.getInPrestacionesPorServiciosList();
            for (InPrestacionesPorServicios inPrestacionesPorServiciosListOrphanCheckInPrestacionesPorServicios : inPrestacionesPorServiciosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VeUnidadServicio (" + veUnidadServicio + ") cannot be destroyed since the InPrestacionesPorServicios " + inPrestacionesPorServiciosListOrphanCheckInPrestacionesPorServicios + " in its inPrestacionesPorServiciosList field has a non-nullable veUnidadServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa idEmpresa = veUnidadServicio.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getVeUnidadServicioList().remove(veUnidadServicio);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(veUnidadServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeUnidadServicio> findVeUnidadServicioEntities() {
        return findVeUnidadServicioEntities(true, -1, -1);
    }

    public List<VeUnidadServicio> findVeUnidadServicioEntities(int maxResults, int firstResult) {
        return findVeUnidadServicioEntities(false, maxResults, firstResult);
    }

    private List<VeUnidadServicio> findVeUnidadServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeUnidadServicio.class));
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

    public VeUnidadServicio findVeUnidadServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeUnidadServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeUnidadServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeUnidadServicio> rt = cq.from(VeUnidadServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
