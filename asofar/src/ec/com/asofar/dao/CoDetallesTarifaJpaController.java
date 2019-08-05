/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoDetallesTarifa;
import ec.com.asofar.dto.CoDetallesTarifaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoDetallesTarifaJpaController implements Serializable {

    public CoDetallesTarifaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetallesTarifa coDetallesTarifa) throws PreexistingEntityException, Exception {
        if (coDetallesTarifa.getCoDetallesTarifaPK() == null) {
            coDetallesTarifa.setCoDetallesTarifaPK(new CoDetallesTarifaPK());
        }
        coDetallesTarifa.getCoDetallesTarifaPK().setIdSucursal(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdSurcusal());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdUnidadServicio(coDetallesTarifa.getInPrestacionesPorServicios().getInPrestacionesPorServiciosPK().getIdUnidadServicio());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdEmpresa(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdEmpresa());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdPrestacion(coDetallesTarifa.getInPrestacionesPorServicios().getInPrestacionesPorServiciosPK().getIdPrestacion());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdTarifa(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdTarifario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTarifario prTarifario = coDetallesTarifa.getPrTarifario();
            if (prTarifario != null) {
                prTarifario = em.getReference(prTarifario.getClass(), prTarifario.getPrTarifarioPK());
                coDetallesTarifa.setPrTarifario(prTarifario);
            }
            InPrestacionesPorServicios inPrestacionesPorServicios = coDetallesTarifa.getInPrestacionesPorServicios();
            if (inPrestacionesPorServicios != null) {
                inPrestacionesPorServicios = em.getReference(inPrestacionesPorServicios.getClass(), inPrestacionesPorServicios.getInPrestacionesPorServiciosPK());
                coDetallesTarifa.setInPrestacionesPorServicios(inPrestacionesPorServicios);
            }
            em.persist(coDetallesTarifa);
            if (prTarifario != null) {
                prTarifario.getCoDetallesTarifaList().add(coDetallesTarifa);
                prTarifario = em.merge(prTarifario);
            }
            if (inPrestacionesPorServicios != null) {
                inPrestacionesPorServicios.getCoDetallesTarifaList().add(coDetallesTarifa);
                inPrestacionesPorServicios = em.merge(inPrestacionesPorServicios);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetallesTarifa(coDetallesTarifa.getCoDetallesTarifaPK()) != null) {
                throw new PreexistingEntityException("CoDetallesTarifa " + coDetallesTarifa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetallesTarifa coDetallesTarifa) throws NonexistentEntityException, Exception {
        coDetallesTarifa.getCoDetallesTarifaPK().setIdSucursal(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdSurcusal());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdUnidadServicio(coDetallesTarifa.getInPrestacionesPorServicios().getInPrestacionesPorServiciosPK().getIdUnidadServicio());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdEmpresa(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdEmpresa());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdPrestacion(coDetallesTarifa.getInPrestacionesPorServicios().getInPrestacionesPorServiciosPK().getIdPrestacion());
        coDetallesTarifa.getCoDetallesTarifaPK().setIdTarifa(coDetallesTarifa.getPrTarifario().getPrTarifarioPK().getIdTarifario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetallesTarifa persistentCoDetallesTarifa = em.find(CoDetallesTarifa.class, coDetallesTarifa.getCoDetallesTarifaPK());
            PrTarifario prTarifarioOld = persistentCoDetallesTarifa.getPrTarifario();
            PrTarifario prTarifarioNew = coDetallesTarifa.getPrTarifario();
            InPrestacionesPorServicios inPrestacionesPorServiciosOld = persistentCoDetallesTarifa.getInPrestacionesPorServicios();
            InPrestacionesPorServicios inPrestacionesPorServiciosNew = coDetallesTarifa.getInPrestacionesPorServicios();
            if (prTarifarioNew != null) {
                prTarifarioNew = em.getReference(prTarifarioNew.getClass(), prTarifarioNew.getPrTarifarioPK());
                coDetallesTarifa.setPrTarifario(prTarifarioNew);
            }
            if (inPrestacionesPorServiciosNew != null) {
                inPrestacionesPorServiciosNew = em.getReference(inPrestacionesPorServiciosNew.getClass(), inPrestacionesPorServiciosNew.getInPrestacionesPorServiciosPK());
                coDetallesTarifa.setInPrestacionesPorServicios(inPrestacionesPorServiciosNew);
            }
            coDetallesTarifa = em.merge(coDetallesTarifa);
            if (prTarifarioOld != null && !prTarifarioOld.equals(prTarifarioNew)) {
                prTarifarioOld.getCoDetallesTarifaList().remove(coDetallesTarifa);
                prTarifarioOld = em.merge(prTarifarioOld);
            }
            if (prTarifarioNew != null && !prTarifarioNew.equals(prTarifarioOld)) {
                prTarifarioNew.getCoDetallesTarifaList().add(coDetallesTarifa);
                prTarifarioNew = em.merge(prTarifarioNew);
            }
            if (inPrestacionesPorServiciosOld != null && !inPrestacionesPorServiciosOld.equals(inPrestacionesPorServiciosNew)) {
                inPrestacionesPorServiciosOld.getCoDetallesTarifaList().remove(coDetallesTarifa);
                inPrestacionesPorServiciosOld = em.merge(inPrestacionesPorServiciosOld);
            }
            if (inPrestacionesPorServiciosNew != null && !inPrestacionesPorServiciosNew.equals(inPrestacionesPorServiciosOld)) {
                inPrestacionesPorServiciosNew.getCoDetallesTarifaList().add(coDetallesTarifa);
                inPrestacionesPorServiciosNew = em.merge(inPrestacionesPorServiciosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetallesTarifaPK id = coDetallesTarifa.getCoDetallesTarifaPK();
                if (findCoDetallesTarifa(id) == null) {
                    throw new NonexistentEntityException("The coDetallesTarifa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetallesTarifaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetallesTarifa coDetallesTarifa;
            try {
                coDetallesTarifa = em.getReference(CoDetallesTarifa.class, id);
                coDetallesTarifa.getCoDetallesTarifaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetallesTarifa with id " + id + " no longer exists.", enfe);
            }
            PrTarifario prTarifario = coDetallesTarifa.getPrTarifario();
            if (prTarifario != null) {
                prTarifario.getCoDetallesTarifaList().remove(coDetallesTarifa);
                prTarifario = em.merge(prTarifario);
            }
            InPrestacionesPorServicios inPrestacionesPorServicios = coDetallesTarifa.getInPrestacionesPorServicios();
            if (inPrestacionesPorServicios != null) {
                inPrestacionesPorServicios.getCoDetallesTarifaList().remove(coDetallesTarifa);
                inPrestacionesPorServicios = em.merge(inPrestacionesPorServicios);
            }
            em.remove(coDetallesTarifa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetallesTarifa> findCoDetallesTarifaEntities() {
        return findCoDetallesTarifaEntities(true, -1, -1);
    }

    public List<CoDetallesTarifa> findCoDetallesTarifaEntities(int maxResults, int firstResult) {
        return findCoDetallesTarifaEntities(false, maxResults, firstResult);
    }

    private List<CoDetallesTarifa> findCoDetallesTarifaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetallesTarifa.class));
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

    public CoDetallesTarifa findCoDetallesTarifa(CoDetallesTarifaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetallesTarifa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetallesTarifaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetallesTarifa> rt = cq.from(CoDetallesTarifa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
