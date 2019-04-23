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
import ec.com.asofar.dto.PrDetalleTarifario;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeUnidadServicio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class VeUnidadServicioJpaController implements Serializable {

    public VeUnidadServicioJpaController() {
       this.emf = Persistence.createEntityManagerFactory("asofarPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeUnidadServicio veUnidadServicio) {
        if (veUnidadServicio.getPrDetalleTarifarioList() == null) {
            veUnidadServicio.setPrDetalleTarifarioList(new ArrayList<PrDetalleTarifario>());
        }
        if (veUnidadServicio.getVeFacturaDetalleList() == null) {
            veUnidadServicio.setVeFacturaDetalleList(new ArrayList<VeFacturaDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrDetalleTarifario> attachedPrDetalleTarifarioList = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifarioToAttach : veUnidadServicio.getPrDetalleTarifarioList()) {
                prDetalleTarifarioListPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioList.add(prDetalleTarifarioListPrDetalleTarifarioToAttach);
            }
            veUnidadServicio.setPrDetalleTarifarioList(attachedPrDetalleTarifarioList);
            List<VeFacturaDetalle> attachedVeFacturaDetalleList = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalleToAttach : veUnidadServicio.getVeFacturaDetalleList()) {
                veFacturaDetalleListVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleList.add(veFacturaDetalleListVeFacturaDetalleToAttach);
            }
            veUnidadServicio.setVeFacturaDetalleList(attachedVeFacturaDetalleList);
            em.persist(veUnidadServicio);
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifario : veUnidadServicio.getPrDetalleTarifarioList()) {
                VeUnidadServicio oldVeUnidadServicioOfPrDetalleTarifarioListPrDetalleTarifario = prDetalleTarifarioListPrDetalleTarifario.getVeUnidadServicio();
                prDetalleTarifarioListPrDetalleTarifario.setVeUnidadServicio(veUnidadServicio);
                prDetalleTarifarioListPrDetalleTarifario = em.merge(prDetalleTarifarioListPrDetalleTarifario);
                if (oldVeUnidadServicioOfPrDetalleTarifarioListPrDetalleTarifario != null) {
                    oldVeUnidadServicioOfPrDetalleTarifarioListPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListPrDetalleTarifario);
                    oldVeUnidadServicioOfPrDetalleTarifarioListPrDetalleTarifario = em.merge(oldVeUnidadServicioOfPrDetalleTarifarioListPrDetalleTarifario);
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalle : veUnidadServicio.getVeFacturaDetalleList()) {
                VeUnidadServicio oldVeUnidadServicioOfVeFacturaDetalleListVeFacturaDetalle = veFacturaDetalleListVeFacturaDetalle.getVeUnidadServicio();
                veFacturaDetalleListVeFacturaDetalle.setVeUnidadServicio(veUnidadServicio);
                veFacturaDetalleListVeFacturaDetalle = em.merge(veFacturaDetalleListVeFacturaDetalle);
                if (oldVeUnidadServicioOfVeFacturaDetalleListVeFacturaDetalle != null) {
                    oldVeUnidadServicioOfVeFacturaDetalleListVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListVeFacturaDetalle);
                    oldVeUnidadServicioOfVeFacturaDetalleListVeFacturaDetalle = em.merge(oldVeUnidadServicioOfVeFacturaDetalleListVeFacturaDetalle);
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
            List<PrDetalleTarifario> prDetalleTarifarioListOld = persistentVeUnidadServicio.getPrDetalleTarifarioList();
            List<PrDetalleTarifario> prDetalleTarifarioListNew = veUnidadServicio.getPrDetalleTarifarioList();
            List<VeFacturaDetalle> veFacturaDetalleListOld = persistentVeUnidadServicio.getVeFacturaDetalleList();
            List<VeFacturaDetalle> veFacturaDetalleListNew = veUnidadServicio.getVeFacturaDetalleList();
            List<String> illegalOrphanMessages = null;
            for (PrDetalleTarifario prDetalleTarifarioListOldPrDetalleTarifario : prDetalleTarifarioListOld) {
                if (!prDetalleTarifarioListNew.contains(prDetalleTarifarioListOldPrDetalleTarifario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrDetalleTarifario " + prDetalleTarifarioListOldPrDetalleTarifario + " since its veUnidadServicio field is not nullable.");
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListOldVeFacturaDetalle : veFacturaDetalleListOld) {
                if (!veFacturaDetalleListNew.contains(veFacturaDetalleListOldVeFacturaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFacturaDetalle " + veFacturaDetalleListOldVeFacturaDetalle + " since its veUnidadServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PrDetalleTarifario> attachedPrDetalleTarifarioListNew = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifarioToAttach : prDetalleTarifarioListNew) {
                prDetalleTarifarioListNewPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioListNew.add(prDetalleTarifarioListNewPrDetalleTarifarioToAttach);
            }
            prDetalleTarifarioListNew = attachedPrDetalleTarifarioListNew;
            veUnidadServicio.setPrDetalleTarifarioList(prDetalleTarifarioListNew);
            List<VeFacturaDetalle> attachedVeFacturaDetalleListNew = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalleToAttach : veFacturaDetalleListNew) {
                veFacturaDetalleListNewVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListNewVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListNewVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleListNew.add(veFacturaDetalleListNewVeFacturaDetalleToAttach);
            }
            veFacturaDetalleListNew = attachedVeFacturaDetalleListNew;
            veUnidadServicio.setVeFacturaDetalleList(veFacturaDetalleListNew);
            veUnidadServicio = em.merge(veUnidadServicio);
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifario : prDetalleTarifarioListNew) {
                if (!prDetalleTarifarioListOld.contains(prDetalleTarifarioListNewPrDetalleTarifario)) {
                    VeUnidadServicio oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario = prDetalleTarifarioListNewPrDetalleTarifario.getVeUnidadServicio();
                    prDetalleTarifarioListNewPrDetalleTarifario.setVeUnidadServicio(veUnidadServicio);
                    prDetalleTarifarioListNewPrDetalleTarifario = em.merge(prDetalleTarifarioListNewPrDetalleTarifario);
                    if (oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario != null && !oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario.equals(veUnidadServicio)) {
                        oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListNewPrDetalleTarifario);
                        oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario = em.merge(oldVeUnidadServicioOfPrDetalleTarifarioListNewPrDetalleTarifario);
                    }
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalle : veFacturaDetalleListNew) {
                if (!veFacturaDetalleListOld.contains(veFacturaDetalleListNewVeFacturaDetalle)) {
                    VeUnidadServicio oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle = veFacturaDetalleListNewVeFacturaDetalle.getVeUnidadServicio();
                    veFacturaDetalleListNewVeFacturaDetalle.setVeUnidadServicio(veUnidadServicio);
                    veFacturaDetalleListNewVeFacturaDetalle = em.merge(veFacturaDetalleListNewVeFacturaDetalle);
                    if (oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle != null && !oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle.equals(veUnidadServicio)) {
                        oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListNewVeFacturaDetalle);
                        oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle = em.merge(oldVeUnidadServicioOfVeFacturaDetalleListNewVeFacturaDetalle);
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
            List<PrDetalleTarifario> prDetalleTarifarioListOrphanCheck = veUnidadServicio.getPrDetalleTarifarioList();
            for (PrDetalleTarifario prDetalleTarifarioListOrphanCheckPrDetalleTarifario : prDetalleTarifarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VeUnidadServicio (" + veUnidadServicio + ") cannot be destroyed since the PrDetalleTarifario " + prDetalleTarifarioListOrphanCheckPrDetalleTarifario + " in its prDetalleTarifarioList field has a non-nullable veUnidadServicio field.");
            }
            List<VeFacturaDetalle> veFacturaDetalleListOrphanCheck = veUnidadServicio.getVeFacturaDetalleList();
            for (VeFacturaDetalle veFacturaDetalleListOrphanCheckVeFacturaDetalle : veFacturaDetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VeUnidadServicio (" + veUnidadServicio + ") cannot be destroyed since the VeFacturaDetalle " + veFacturaDetalleListOrphanCheckVeFacturaDetalle + " in its veFacturaDetalleList field has a non-nullable veUnidadServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
