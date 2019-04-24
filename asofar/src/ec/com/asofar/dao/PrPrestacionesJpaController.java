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
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrDetalleTarifario;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrPrestacionesPK;
import ec.com.asofar.dto.VeFacturaDetalle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class PrPrestacionesJpaController implements Serializable {

    public PrPrestacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrPrestaciones prPrestaciones) throws PreexistingEntityException, Exception {
        if (prPrestaciones.getPrPrestacionesPK() == null) {
            prPrestaciones.setPrPrestacionesPK(new PrPrestacionesPK());
        }
        if (prPrestaciones.getPrDetalleTarifarioList() == null) {
            prPrestaciones.setPrDetalleTarifarioList(new ArrayList<PrDetalleTarifario>());
        }
        if (prPrestaciones.getInKardexList() == null) {
            prPrestaciones.setInKardexList(new ArrayList<InKardex>());
        }
        if (prPrestaciones.getVeFacturaDetalleList() == null) {
            prPrestaciones.setVeFacturaDetalleList(new ArrayList<VeFacturaDetalle>());
        }
        prPrestaciones.getPrPrestacionesPK().setIdEmpresa(prPrestaciones.getSeEmpresa().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa seEmpresa = prPrestaciones.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa = em.getReference(seEmpresa.getClass(), seEmpresa.getIdEmpresa());
                prPrestaciones.setSeEmpresa(seEmpresa);
            }
            List<PrDetalleTarifario> attachedPrDetalleTarifarioList = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifarioToAttach : prPrestaciones.getPrDetalleTarifarioList()) {
                prDetalleTarifarioListPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioList.add(prDetalleTarifarioListPrDetalleTarifarioToAttach);
            }
            prPrestaciones.setPrDetalleTarifarioList(attachedPrDetalleTarifarioList);
            List<InKardex> attachedInKardexList = new ArrayList<InKardex>();
            for (InKardex inKardexListInKardexToAttach : prPrestaciones.getInKardexList()) {
                inKardexListInKardexToAttach = em.getReference(inKardexListInKardexToAttach.getClass(), inKardexListInKardexToAttach.getInKardexPK());
                attachedInKardexList.add(inKardexListInKardexToAttach);
            }
            prPrestaciones.setInKardexList(attachedInKardexList);
            List<VeFacturaDetalle> attachedVeFacturaDetalleList = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalleToAttach : prPrestaciones.getVeFacturaDetalleList()) {
                veFacturaDetalleListVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleList.add(veFacturaDetalleListVeFacturaDetalleToAttach);
            }
            prPrestaciones.setVeFacturaDetalleList(attachedVeFacturaDetalleList);
            em.persist(prPrestaciones);
            if (seEmpresa != null) {
                seEmpresa.getPrPrestacionesList().add(prPrestaciones);
                seEmpresa = em.merge(seEmpresa);
            }
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifario : prPrestaciones.getPrDetalleTarifarioList()) {
                PrPrestaciones oldPrPrestacionesOfPrDetalleTarifarioListPrDetalleTarifario = prDetalleTarifarioListPrDetalleTarifario.getPrPrestaciones();
                prDetalleTarifarioListPrDetalleTarifario.setPrPrestaciones(prPrestaciones);
                prDetalleTarifarioListPrDetalleTarifario = em.merge(prDetalleTarifarioListPrDetalleTarifario);
                if (oldPrPrestacionesOfPrDetalleTarifarioListPrDetalleTarifario != null) {
                    oldPrPrestacionesOfPrDetalleTarifarioListPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListPrDetalleTarifario);
                    oldPrPrestacionesOfPrDetalleTarifarioListPrDetalleTarifario = em.merge(oldPrPrestacionesOfPrDetalleTarifarioListPrDetalleTarifario);
                }
            }
            for (InKardex inKardexListInKardex : prPrestaciones.getInKardexList()) {
                PrPrestaciones oldPrPrestacionesOfInKardexListInKardex = inKardexListInKardex.getPrPrestaciones();
                inKardexListInKardex.setPrPrestaciones(prPrestaciones);
                inKardexListInKardex = em.merge(inKardexListInKardex);
                if (oldPrPrestacionesOfInKardexListInKardex != null) {
                    oldPrPrestacionesOfInKardexListInKardex.getInKardexList().remove(inKardexListInKardex);
                    oldPrPrestacionesOfInKardexListInKardex = em.merge(oldPrPrestacionesOfInKardexListInKardex);
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalle : prPrestaciones.getVeFacturaDetalleList()) {
                PrPrestaciones oldPrPrestacionesOfVeFacturaDetalleListVeFacturaDetalle = veFacturaDetalleListVeFacturaDetalle.getPrPrestaciones();
                veFacturaDetalleListVeFacturaDetalle.setPrPrestaciones(prPrestaciones);
                veFacturaDetalleListVeFacturaDetalle = em.merge(veFacturaDetalleListVeFacturaDetalle);
                if (oldPrPrestacionesOfVeFacturaDetalleListVeFacturaDetalle != null) {
                    oldPrPrestacionesOfVeFacturaDetalleListVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListVeFacturaDetalle);
                    oldPrPrestacionesOfVeFacturaDetalleListVeFacturaDetalle = em.merge(oldPrPrestacionesOfVeFacturaDetalleListVeFacturaDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrPrestaciones(prPrestaciones.getPrPrestacionesPK()) != null) {
                throw new PreexistingEntityException("PrPrestaciones " + prPrestaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrPrestaciones prPrestaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prPrestaciones.getPrPrestacionesPK().setIdEmpresa(prPrestaciones.getSeEmpresa().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrPrestaciones persistentPrPrestaciones = em.find(PrPrestaciones.class, prPrestaciones.getPrPrestacionesPK());
            SeEmpresa seEmpresaOld = persistentPrPrestaciones.getSeEmpresa();
            SeEmpresa seEmpresaNew = prPrestaciones.getSeEmpresa();
            List<PrDetalleTarifario> prDetalleTarifarioListOld = persistentPrPrestaciones.getPrDetalleTarifarioList();
            List<PrDetalleTarifario> prDetalleTarifarioListNew = prPrestaciones.getPrDetalleTarifarioList();
            List<InKardex> inKardexListOld = persistentPrPrestaciones.getInKardexList();
            List<InKardex> inKardexListNew = prPrestaciones.getInKardexList();
            List<VeFacturaDetalle> veFacturaDetalleListOld = persistentPrPrestaciones.getVeFacturaDetalleList();
            List<VeFacturaDetalle> veFacturaDetalleListNew = prPrestaciones.getVeFacturaDetalleList();
            List<String> illegalOrphanMessages = null;
            for (PrDetalleTarifario prDetalleTarifarioListOldPrDetalleTarifario : prDetalleTarifarioListOld) {
                if (!prDetalleTarifarioListNew.contains(prDetalleTarifarioListOldPrDetalleTarifario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrDetalleTarifario " + prDetalleTarifarioListOldPrDetalleTarifario + " since its prPrestaciones field is not nullable.");
                }
            }
            for (InKardex inKardexListOldInKardex : inKardexListOld) {
                if (!inKardexListNew.contains(inKardexListOldInKardex)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InKardex " + inKardexListOldInKardex + " since its prPrestaciones field is not nullable.");
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListOldVeFacturaDetalle : veFacturaDetalleListOld) {
                if (!veFacturaDetalleListNew.contains(veFacturaDetalleListOldVeFacturaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFacturaDetalle " + veFacturaDetalleListOldVeFacturaDetalle + " since its prPrestaciones field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seEmpresaNew != null) {
                seEmpresaNew = em.getReference(seEmpresaNew.getClass(), seEmpresaNew.getIdEmpresa());
                prPrestaciones.setSeEmpresa(seEmpresaNew);
            }
            List<PrDetalleTarifario> attachedPrDetalleTarifarioListNew = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifarioToAttach : prDetalleTarifarioListNew) {
                prDetalleTarifarioListNewPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioListNew.add(prDetalleTarifarioListNewPrDetalleTarifarioToAttach);
            }
            prDetalleTarifarioListNew = attachedPrDetalleTarifarioListNew;
            prPrestaciones.setPrDetalleTarifarioList(prDetalleTarifarioListNew);
            List<InKardex> attachedInKardexListNew = new ArrayList<InKardex>();
            for (InKardex inKardexListNewInKardexToAttach : inKardexListNew) {
                inKardexListNewInKardexToAttach = em.getReference(inKardexListNewInKardexToAttach.getClass(), inKardexListNewInKardexToAttach.getInKardexPK());
                attachedInKardexListNew.add(inKardexListNewInKardexToAttach);
            }
            inKardexListNew = attachedInKardexListNew;
            prPrestaciones.setInKardexList(inKardexListNew);
            List<VeFacturaDetalle> attachedVeFacturaDetalleListNew = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalleToAttach : veFacturaDetalleListNew) {
                veFacturaDetalleListNewVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListNewVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListNewVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleListNew.add(veFacturaDetalleListNewVeFacturaDetalleToAttach);
            }
            veFacturaDetalleListNew = attachedVeFacturaDetalleListNew;
            prPrestaciones.setVeFacturaDetalleList(veFacturaDetalleListNew);
            prPrestaciones = em.merge(prPrestaciones);
            if (seEmpresaOld != null && !seEmpresaOld.equals(seEmpresaNew)) {
                seEmpresaOld.getPrPrestacionesList().remove(prPrestaciones);
                seEmpresaOld = em.merge(seEmpresaOld);
            }
            if (seEmpresaNew != null && !seEmpresaNew.equals(seEmpresaOld)) {
                seEmpresaNew.getPrPrestacionesList().add(prPrestaciones);
                seEmpresaNew = em.merge(seEmpresaNew);
            }
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifario : prDetalleTarifarioListNew) {
                if (!prDetalleTarifarioListOld.contains(prDetalleTarifarioListNewPrDetalleTarifario)) {
                    PrPrestaciones oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario = prDetalleTarifarioListNewPrDetalleTarifario.getPrPrestaciones();
                    prDetalleTarifarioListNewPrDetalleTarifario.setPrPrestaciones(prPrestaciones);
                    prDetalleTarifarioListNewPrDetalleTarifario = em.merge(prDetalleTarifarioListNewPrDetalleTarifario);
                    if (oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario != null && !oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario.equals(prPrestaciones)) {
                        oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListNewPrDetalleTarifario);
                        oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario = em.merge(oldPrPrestacionesOfPrDetalleTarifarioListNewPrDetalleTarifario);
                    }
                }
            }
            for (InKardex inKardexListNewInKardex : inKardexListNew) {
                if (!inKardexListOld.contains(inKardexListNewInKardex)) {
                    PrPrestaciones oldPrPrestacionesOfInKardexListNewInKardex = inKardexListNewInKardex.getPrPrestaciones();
                    inKardexListNewInKardex.setPrPrestaciones(prPrestaciones);
                    inKardexListNewInKardex = em.merge(inKardexListNewInKardex);
                    if (oldPrPrestacionesOfInKardexListNewInKardex != null && !oldPrPrestacionesOfInKardexListNewInKardex.equals(prPrestaciones)) {
                        oldPrPrestacionesOfInKardexListNewInKardex.getInKardexList().remove(inKardexListNewInKardex);
                        oldPrPrestacionesOfInKardexListNewInKardex = em.merge(oldPrPrestacionesOfInKardexListNewInKardex);
                    }
                }
            }
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalle : veFacturaDetalleListNew) {
                if (!veFacturaDetalleListOld.contains(veFacturaDetalleListNewVeFacturaDetalle)) {
                    PrPrestaciones oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle = veFacturaDetalleListNewVeFacturaDetalle.getPrPrestaciones();
                    veFacturaDetalleListNewVeFacturaDetalle.setPrPrestaciones(prPrestaciones);
                    veFacturaDetalleListNewVeFacturaDetalle = em.merge(veFacturaDetalleListNewVeFacturaDetalle);
                    if (oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle != null && !oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle.equals(prPrestaciones)) {
                        oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListNewVeFacturaDetalle);
                        oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle = em.merge(oldPrPrestacionesOfVeFacturaDetalleListNewVeFacturaDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrPrestacionesPK id = prPrestaciones.getPrPrestacionesPK();
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

    public void destroy(PrPrestacionesPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrPrestaciones prPrestaciones;
            try {
                prPrestaciones = em.getReference(PrPrestaciones.class, id);
                prPrestaciones.getPrPrestacionesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prPrestaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrDetalleTarifario> prDetalleTarifarioListOrphanCheck = prPrestaciones.getPrDetalleTarifarioList();
            for (PrDetalleTarifario prDetalleTarifarioListOrphanCheckPrDetalleTarifario : prDetalleTarifarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrPrestaciones (" + prPrestaciones + ") cannot be destroyed since the PrDetalleTarifario " + prDetalleTarifarioListOrphanCheckPrDetalleTarifario + " in its prDetalleTarifarioList field has a non-nullable prPrestaciones field.");
            }
            List<InKardex> inKardexListOrphanCheck = prPrestaciones.getInKardexList();
            for (InKardex inKardexListOrphanCheckInKardex : inKardexListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrPrestaciones (" + prPrestaciones + ") cannot be destroyed since the InKardex " + inKardexListOrphanCheckInKardex + " in its inKardexList field has a non-nullable prPrestaciones field.");
            }
            List<VeFacturaDetalle> veFacturaDetalleListOrphanCheck = prPrestaciones.getVeFacturaDetalleList();
            for (VeFacturaDetalle veFacturaDetalleListOrphanCheckVeFacturaDetalle : veFacturaDetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrPrestaciones (" + prPrestaciones + ") cannot be destroyed since the VeFacturaDetalle " + veFacturaDetalleListOrphanCheckVeFacturaDetalle + " in its veFacturaDetalleList field has a non-nullable prPrestaciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa seEmpresa = prPrestaciones.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa.getPrPrestacionesList().remove(prPrestaciones);
                seEmpresa = em.merge(seEmpresa);
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

    public PrPrestaciones findPrPrestaciones(PrPrestacionesPK id) {
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
