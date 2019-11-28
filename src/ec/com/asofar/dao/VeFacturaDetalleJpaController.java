/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeFacturaDetallePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class VeFacturaDetalleJpaController implements Serializable {

    public VeFacturaDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VeFacturaDetalle veFacturaDetalle) throws PreexistingEntityException, Exception {
        if (veFacturaDetalle.getVeFacturaDetallePK() == null) {
            veFacturaDetalle.setVeFacturaDetallePK(new VeFacturaDetallePK());
        }
        veFacturaDetalle.getVeFacturaDetallePK().setIdSucursal(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdSucursal());
        veFacturaDetalle.getVeFacturaDetallePK().setIdEmpresa(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdEmpresa());
        veFacturaDetalle.getVeFacturaDetallePK().setIdFactura(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdFactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFactura veFactura = veFacturaDetalle.getVeFactura();
            if (veFactura != null) {
                veFactura = em.getReference(veFactura.getClass(), veFactura.getVeFacturaPK());
                veFacturaDetalle.setVeFactura(veFactura);
            }
            em.persist(veFacturaDetalle);
            if (veFactura != null) {
                veFactura.getVeFacturaDetalleList().add(veFacturaDetalle);
                veFactura = em.merge(veFactura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVeFacturaDetalle(veFacturaDetalle.getVeFacturaDetallePK()) != null) {
                throw new PreexistingEntityException("VeFacturaDetalle " + veFacturaDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VeFacturaDetalle veFacturaDetalle) throws NonexistentEntityException, Exception {
        veFacturaDetalle.getVeFacturaDetallePK().setIdSucursal(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdSucursal());
        veFacturaDetalle.getVeFacturaDetallePK().setIdEmpresa(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdEmpresa());
        veFacturaDetalle.getVeFacturaDetallePK().setIdFactura(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdFactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFacturaDetalle persistentVeFacturaDetalle = em.find(VeFacturaDetalle.class, veFacturaDetalle.getVeFacturaDetallePK());
            VeFactura veFacturaOld = persistentVeFacturaDetalle.getVeFactura();
            VeFactura veFacturaNew = veFacturaDetalle.getVeFactura();
            if (veFacturaNew != null) {
                veFacturaNew = em.getReference(veFacturaNew.getClass(), veFacturaNew.getVeFacturaPK());
                veFacturaDetalle.setVeFactura(veFacturaNew);
            }
            veFacturaDetalle = em.merge(veFacturaDetalle);
            if (veFacturaOld != null && !veFacturaOld.equals(veFacturaNew)) {
                veFacturaOld.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veFacturaOld = em.merge(veFacturaOld);
            }
            if (veFacturaNew != null && !veFacturaNew.equals(veFacturaOld)) {
                veFacturaNew.getVeFacturaDetalleList().add(veFacturaDetalle);
                veFacturaNew = em.merge(veFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VeFacturaDetallePK id = veFacturaDetalle.getVeFacturaDetallePK();
                if (findVeFacturaDetalle(id) == null) {
                    throw new NonexistentEntityException("The veFacturaDetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VeFacturaDetallePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFacturaDetalle veFacturaDetalle;
            try {
                veFacturaDetalle = em.getReference(VeFacturaDetalle.class, id);
                veFacturaDetalle.getVeFacturaDetallePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veFacturaDetalle with id " + id + " no longer exists.", enfe);
            }
            VeFactura veFactura = veFacturaDetalle.getVeFactura();
            if (veFactura != null) {
                veFactura.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veFactura = em.merge(veFactura);
            }
            em.remove(veFacturaDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VeFacturaDetalle> findVeFacturaDetalleEntities() {
        return findVeFacturaDetalleEntities(true, -1, -1);
    }

    public List<VeFacturaDetalle> findVeFacturaDetalleEntities(int maxResults, int firstResult) {
        return findVeFacturaDetalleEntities(false, maxResults, firstResult);
    }

    private List<VeFacturaDetalle> findVeFacturaDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VeFacturaDetalle.class));
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

    public VeFacturaDetalle findVeFacturaDetalle(VeFacturaDetallePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VeFacturaDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeFacturaDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VeFacturaDetalle> rt = cq.from(VeFacturaDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
