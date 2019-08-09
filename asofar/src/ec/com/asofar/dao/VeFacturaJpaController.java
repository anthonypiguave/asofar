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
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeFacturaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin
 */
public class VeFacturaJpaController implements Serializable {

    public VeFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeFactura veFactura) throws PreexistingEntityException, Exception {
        if (veFactura.getVeFacturaPK() == null) {
            veFactura.setVeFacturaPK(new VeFacturaPK());
        }
        if (veFactura.getVeFacturaDetalleList() == null) {
            veFactura.setVeFacturaDetalleList(new ArrayList<VeFacturaDetalle>());
        }
        veFactura.getVeFacturaPK().setIdSucursal(veFactura.getSeSucursal().getSeSucursalPK().getIdSucursal());
        veFactura.getVeFacturaPK().setIdEmpresa(veFactura.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = veFactura.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                veFactura.setSeSucursal(seSucursal);
            }
            List<VeFacturaDetalle> attachedVeFacturaDetalleList = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalleToAttach : veFactura.getVeFacturaDetalleList()) {
                veFacturaDetalleListVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleList.add(veFacturaDetalleListVeFacturaDetalleToAttach);
            }
            veFactura.setVeFacturaDetalleList(attachedVeFacturaDetalleList);
            em.persist(veFactura);
            if (seSucursal != null) {
                seSucursal.getVeFacturaList().add(veFactura);
                seSucursal = em.merge(seSucursal);
            }
            for (VeFacturaDetalle veFacturaDetalleListVeFacturaDetalle : veFactura.getVeFacturaDetalleList()) {
                VeFactura oldVeFacturaOfVeFacturaDetalleListVeFacturaDetalle = veFacturaDetalleListVeFacturaDetalle.getVeFactura();
                veFacturaDetalleListVeFacturaDetalle.setVeFactura(veFactura);
                veFacturaDetalleListVeFacturaDetalle = em.merge(veFacturaDetalleListVeFacturaDetalle);
                if (oldVeFacturaOfVeFacturaDetalleListVeFacturaDetalle != null) {
                    oldVeFacturaOfVeFacturaDetalleListVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListVeFacturaDetalle);
                    oldVeFacturaOfVeFacturaDetalleListVeFacturaDetalle = em.merge(oldVeFacturaOfVeFacturaDetalleListVeFacturaDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVeFactura(veFactura.getVeFacturaPK()) != null) {
                throw new PreexistingEntityException("VeFactura " + veFactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeFactura veFactura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        veFactura.getVeFacturaPK().setIdSucursal(veFactura.getSeSucursal().getSeSucursalPK().getIdSucursal());
        veFactura.getVeFacturaPK().setIdEmpresa(veFactura.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFactura persistentVeFactura = em.find(VeFactura.class, veFactura.getVeFacturaPK());
            SeSucursal seSucursalOld = persistentVeFactura.getSeSucursal();
            SeSucursal seSucursalNew = veFactura.getSeSucursal();
            List<VeFacturaDetalle> veFacturaDetalleListOld = persistentVeFactura.getVeFacturaDetalleList();
            List<VeFacturaDetalle> veFacturaDetalleListNew = veFactura.getVeFacturaDetalleList();
            List<String> illegalOrphanMessages = null;
            for (VeFacturaDetalle veFacturaDetalleListOldVeFacturaDetalle : veFacturaDetalleListOld) {
                if (!veFacturaDetalleListNew.contains(veFacturaDetalleListOldVeFacturaDetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFacturaDetalle " + veFacturaDetalleListOldVeFacturaDetalle + " since its veFactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                veFactura.setSeSucursal(seSucursalNew);
            }
            List<VeFacturaDetalle> attachedVeFacturaDetalleListNew = new ArrayList<VeFacturaDetalle>();
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalleToAttach : veFacturaDetalleListNew) {
                veFacturaDetalleListNewVeFacturaDetalleToAttach = em.getReference(veFacturaDetalleListNewVeFacturaDetalleToAttach.getClass(), veFacturaDetalleListNewVeFacturaDetalleToAttach.getVeFacturaDetallePK());
                attachedVeFacturaDetalleListNew.add(veFacturaDetalleListNewVeFacturaDetalleToAttach);
            }
            veFacturaDetalleListNew = attachedVeFacturaDetalleListNew;
            veFactura.setVeFacturaDetalleList(veFacturaDetalleListNew);
            veFactura = em.merge(veFactura);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getVeFacturaList().remove(veFactura);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getVeFacturaList().add(veFactura);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (VeFacturaDetalle veFacturaDetalleListNewVeFacturaDetalle : veFacturaDetalleListNew) {
                if (!veFacturaDetalleListOld.contains(veFacturaDetalleListNewVeFacturaDetalle)) {
                    VeFactura oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle = veFacturaDetalleListNewVeFacturaDetalle.getVeFactura();
                    veFacturaDetalleListNewVeFacturaDetalle.setVeFactura(veFactura);
                    veFacturaDetalleListNewVeFacturaDetalle = em.merge(veFacturaDetalleListNewVeFacturaDetalle);
                    if (oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle != null && !oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle.equals(veFactura)) {
                        oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle.getVeFacturaDetalleList().remove(veFacturaDetalleListNewVeFacturaDetalle);
                        oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle = em.merge(oldVeFacturaOfVeFacturaDetalleListNewVeFacturaDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VeFacturaPK id = veFactura.getVeFacturaPK();
                if (findVeFactura(id) == null) {
                    throw new NonexistentEntityException("The veFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VeFacturaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFactura veFactura;
            try {
                veFactura = em.getReference(VeFactura.class, id);
                veFactura.getVeFacturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veFactura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VeFacturaDetalle> veFacturaDetalleListOrphanCheck = veFactura.getVeFacturaDetalleList();
            for (VeFacturaDetalle veFacturaDetalleListOrphanCheckVeFacturaDetalle : veFacturaDetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VeFactura (" + veFactura + ") cannot be destroyed since the VeFacturaDetalle " + veFacturaDetalleListOrphanCheckVeFacturaDetalle + " in its veFacturaDetalleList field has a non-nullable veFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeSucursal seSucursal = veFactura.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getVeFacturaList().remove(veFactura);
                seSucursal = em.merge(seSucursal);
            }
            em.remove(veFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeFactura> findVeFacturaEntities() {
        return findVeFacturaEntities(true, -1, -1);
    }

    public List<VeFactura> findVeFacturaEntities(int maxResults, int firstResult) {
        return findVeFacturaEntities(false, maxResults, firstResult);
    }

    private List<VeFactura> findVeFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeFactura.class));
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

    public VeFactura findVeFactura(VeFacturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeFactura> rt = cq.from(VeFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
