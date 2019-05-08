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
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeFacturaDetallePK;
import ec.com.asofar.dto.VeUnidadServicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
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
        veFacturaDetalle.getVeFacturaDetallePK().setIdFactura(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdFactura());
        veFacturaDetalle.getVeFacturaDetallePK().setIdPrestaciones(veFacturaDetalle.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        veFacturaDetalle.getVeFacturaDetallePK().setIdEmpresa(veFacturaDetalle.getPrPrestaciones().getPrPrestacionesPK().getIdEmpresa());
        veFacturaDetalle.getVeFacturaDetallePK().setIdSucursal(veFacturaDetalle.getSeSucursal().getSeSucursalPK().getIdSucursal());
        veFacturaDetalle.getVeFacturaDetallePK().setIdUnidadServicio(veFacturaDetalle.getVeUnidadServicio().getIdUnidadServicio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = veFacturaDetalle.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                veFacturaDetalle.setSeSucursal(seSucursal);
            }
            VeFactura veFactura = veFacturaDetalle.getVeFactura();
            if (veFactura != null) {
                veFactura = em.getReference(veFactura.getClass(), veFactura.getVeFacturaPK());
                veFacturaDetalle.setVeFactura(veFactura);
            }
            PrPrestaciones prPrestaciones = veFacturaDetalle.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones = em.getReference(prPrestaciones.getClass(), prPrestaciones.getPrPrestacionesPK());
                veFacturaDetalle.setPrPrestaciones(prPrestaciones);
            }
            VeUnidadServicio veUnidadServicio = veFacturaDetalle.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio = em.getReference(veUnidadServicio.getClass(), veUnidadServicio.getIdUnidadServicio());
                veFacturaDetalle.setVeUnidadServicio(veUnidadServicio);
            }
            em.persist(veFacturaDetalle);
            if (seSucursal != null) {
                seSucursal.getVeFacturaDetalleList().add(veFacturaDetalle);
                seSucursal = em.merge(seSucursal);
            }
            if (veFactura != null) {
                veFactura.getVeFacturaDetalleList().add(veFacturaDetalle);
                veFactura = em.merge(veFactura);
            }
            if (prPrestaciones != null) {
                prPrestaciones.getVeFacturaDetalleList().add(veFacturaDetalle);
                prPrestaciones = em.merge(prPrestaciones);
            }
            if (veUnidadServicio != null) {
                veUnidadServicio.getVeFacturaDetalleList().add(veFacturaDetalle);
                veUnidadServicio = em.merge(veUnidadServicio);
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
        veFacturaDetalle.getVeFacturaDetallePK().setIdFactura(veFacturaDetalle.getVeFactura().getVeFacturaPK().getIdFactura());
        veFacturaDetalle.getVeFacturaDetallePK().setIdPrestaciones(veFacturaDetalle.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        veFacturaDetalle.getVeFacturaDetallePK().setIdEmpresa(veFacturaDetalle.getPrPrestaciones().getPrPrestacionesPK().getIdEmpresa());
        veFacturaDetalle.getVeFacturaDetallePK().setIdSucursal(veFacturaDetalle.getSeSucursal().getSeSucursalPK().getIdSucursal());
        veFacturaDetalle.getVeFacturaDetallePK().setIdUnidadServicio(veFacturaDetalle.getVeUnidadServicio().getIdUnidadServicio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VeFacturaDetalle persistentVeFacturaDetalle = em.find(VeFacturaDetalle.class, veFacturaDetalle.getVeFacturaDetallePK());
            SeSucursal seSucursalOld = persistentVeFacturaDetalle.getSeSucursal();
            SeSucursal seSucursalNew = veFacturaDetalle.getSeSucursal();
            VeFactura veFacturaOld = persistentVeFacturaDetalle.getVeFactura();
            VeFactura veFacturaNew = veFacturaDetalle.getVeFactura();
            PrPrestaciones prPrestacionesOld = persistentVeFacturaDetalle.getPrPrestaciones();
            PrPrestaciones prPrestacionesNew = veFacturaDetalle.getPrPrestaciones();
            VeUnidadServicio veUnidadServicioOld = persistentVeFacturaDetalle.getVeUnidadServicio();
            VeUnidadServicio veUnidadServicioNew = veFacturaDetalle.getVeUnidadServicio();
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                veFacturaDetalle.setSeSucursal(seSucursalNew);
            }
            if (veFacturaNew != null) {
                veFacturaNew = em.getReference(veFacturaNew.getClass(), veFacturaNew.getVeFacturaPK());
                veFacturaDetalle.setVeFactura(veFacturaNew);
            }
            if (prPrestacionesNew != null) {
                prPrestacionesNew = em.getReference(prPrestacionesNew.getClass(), prPrestacionesNew.getPrPrestacionesPK());
                veFacturaDetalle.setPrPrestaciones(prPrestacionesNew);
            }
            if (veUnidadServicioNew != null) {
                veUnidadServicioNew = em.getReference(veUnidadServicioNew.getClass(), veUnidadServicioNew.getIdUnidadServicio());
                veFacturaDetalle.setVeUnidadServicio(veUnidadServicioNew);
            }
            veFacturaDetalle = em.merge(veFacturaDetalle);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getVeFacturaDetalleList().remove(veFacturaDetalle);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getVeFacturaDetalleList().add(veFacturaDetalle);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (veFacturaOld != null && !veFacturaOld.equals(veFacturaNew)) {
                veFacturaOld.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veFacturaOld = em.merge(veFacturaOld);
            }
            if (veFacturaNew != null && !veFacturaNew.equals(veFacturaOld)) {
                veFacturaNew.getVeFacturaDetalleList().add(veFacturaDetalle);
                veFacturaNew = em.merge(veFacturaNew);
            }
            if (prPrestacionesOld != null && !prPrestacionesOld.equals(prPrestacionesNew)) {
                prPrestacionesOld.getVeFacturaDetalleList().remove(veFacturaDetalle);
                prPrestacionesOld = em.merge(prPrestacionesOld);
            }
            if (prPrestacionesNew != null && !prPrestacionesNew.equals(prPrestacionesOld)) {
                prPrestacionesNew.getVeFacturaDetalleList().add(veFacturaDetalle);
                prPrestacionesNew = em.merge(prPrestacionesNew);
            }
            if (veUnidadServicioOld != null && !veUnidadServicioOld.equals(veUnidadServicioNew)) {
                veUnidadServicioOld.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veUnidadServicioOld = em.merge(veUnidadServicioOld);
            }
            if (veUnidadServicioNew != null && !veUnidadServicioNew.equals(veUnidadServicioOld)) {
                veUnidadServicioNew.getVeFacturaDetalleList().add(veFacturaDetalle);
                veUnidadServicioNew = em.merge(veUnidadServicioNew);
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
            SeSucursal seSucursal = veFacturaDetalle.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getVeFacturaDetalleList().remove(veFacturaDetalle);
                seSucursal = em.merge(seSucursal);
            }
            VeFactura veFactura = veFacturaDetalle.getVeFactura();
            if (veFactura != null) {
                veFactura.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veFactura = em.merge(veFactura);
            }
            PrPrestaciones prPrestaciones = veFacturaDetalle.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones.getVeFacturaDetalleList().remove(veFacturaDetalle);
                prPrestaciones = em.merge(prPrestaciones);
            }
            VeUnidadServicio veUnidadServicio = veFacturaDetalle.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio.getVeFacturaDetalleList().remove(veFacturaDetalle);
                veUnidadServicio = em.merge(veUnidadServicio);
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
