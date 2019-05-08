/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrDetalleTarifarioPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.VeUnidadServicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class PrDetalleTarifarioJpaController implements Serializable {

    public PrDetalleTarifarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrDetalleTarifario prDetalleTarifario) throws PreexistingEntityException, Exception {
        if (prDetalleTarifario.getPrDetalleTarifarioPK() == null) {
            prDetalleTarifario.setPrDetalleTarifarioPK(new PrDetalleTarifarioPK());
        }
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdUnidadServicio(prDetalleTarifario.getVeUnidadServicio().getIdUnidadServicio());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdEmpresa(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdEmpresa());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdPrestacion(prDetalleTarifario.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdTarifario(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdTarifario());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdSurcusal(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdSurcusal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrPrestaciones prPrestaciones = prDetalleTarifario.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones = em.getReference(prPrestaciones.getClass(), prPrestaciones.getPrPrestacionesPK());
                prDetalleTarifario.setPrPrestaciones(prPrestaciones);
            }
            PrTarifario prTarifario = prDetalleTarifario.getPrTarifario();
            if (prTarifario != null) {
                prTarifario = em.getReference(prTarifario.getClass(), prTarifario.getPrTarifarioPK());
                prDetalleTarifario.setPrTarifario(prTarifario);
            }
            VeUnidadServicio veUnidadServicio = prDetalleTarifario.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio = em.getReference(veUnidadServicio.getClass(), veUnidadServicio.getIdUnidadServicio());
                prDetalleTarifario.setVeUnidadServicio(veUnidadServicio);
            }
            em.persist(prDetalleTarifario);
            if (prPrestaciones != null) {
                prPrestaciones.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prPrestaciones = em.merge(prPrestaciones);
            }
            if (prTarifario != null) {
                prTarifario.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prTarifario = em.merge(prTarifario);
            }
            if (veUnidadServicio != null) {
                veUnidadServicio.getPrDetalleTarifarioList().add(prDetalleTarifario);
                veUnidadServicio = em.merge(veUnidadServicio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrDetalleTarifario(prDetalleTarifario.getPrDetalleTarifarioPK()) != null) {
                throw new PreexistingEntityException("PrDetalleTarifario " + prDetalleTarifario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrDetalleTarifario prDetalleTarifario) throws NonexistentEntityException, Exception {
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdUnidadServicio(prDetalleTarifario.getVeUnidadServicio().getIdUnidadServicio());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdEmpresa(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdEmpresa());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdPrestacion(prDetalleTarifario.getPrPrestaciones().getPrPrestacionesPK().getIdPrestacion());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdTarifario(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdTarifario());
        prDetalleTarifario.getPrDetalleTarifarioPK().setIdSurcusal(prDetalleTarifario.getPrTarifario().getPrTarifarioPK().getIdSurcusal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrDetalleTarifario persistentPrDetalleTarifario = em.find(PrDetalleTarifario.class, prDetalleTarifario.getPrDetalleTarifarioPK());
            PrPrestaciones prPrestacionesOld = persistentPrDetalleTarifario.getPrPrestaciones();
            PrPrestaciones prPrestacionesNew = prDetalleTarifario.getPrPrestaciones();
            PrTarifario prTarifarioOld = persistentPrDetalleTarifario.getPrTarifario();
            PrTarifario prTarifarioNew = prDetalleTarifario.getPrTarifario();
            VeUnidadServicio veUnidadServicioOld = persistentPrDetalleTarifario.getVeUnidadServicio();
            VeUnidadServicio veUnidadServicioNew = prDetalleTarifario.getVeUnidadServicio();
            if (prPrestacionesNew != null) {
                prPrestacionesNew = em.getReference(prPrestacionesNew.getClass(), prPrestacionesNew.getPrPrestacionesPK());
                prDetalleTarifario.setPrPrestaciones(prPrestacionesNew);
            }
            if (prTarifarioNew != null) {
                prTarifarioNew = em.getReference(prTarifarioNew.getClass(), prTarifarioNew.getPrTarifarioPK());
                prDetalleTarifario.setPrTarifario(prTarifarioNew);
            }
            if (veUnidadServicioNew != null) {
                veUnidadServicioNew = em.getReference(veUnidadServicioNew.getClass(), veUnidadServicioNew.getIdUnidadServicio());
                prDetalleTarifario.setVeUnidadServicio(veUnidadServicioNew);
            }
            prDetalleTarifario = em.merge(prDetalleTarifario);
            if (prPrestacionesOld != null && !prPrestacionesOld.equals(prPrestacionesNew)) {
                prPrestacionesOld.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prPrestacionesOld = em.merge(prPrestacionesOld);
            }
            if (prPrestacionesNew != null && !prPrestacionesNew.equals(prPrestacionesOld)) {
                prPrestacionesNew.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prPrestacionesNew = em.merge(prPrestacionesNew);
            }
            if (prTarifarioOld != null && !prTarifarioOld.equals(prTarifarioNew)) {
                prTarifarioOld.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prTarifarioOld = em.merge(prTarifarioOld);
            }
            if (prTarifarioNew != null && !prTarifarioNew.equals(prTarifarioOld)) {
                prTarifarioNew.getPrDetalleTarifarioList().add(prDetalleTarifario);
                prTarifarioNew = em.merge(prTarifarioNew);
            }
            if (veUnidadServicioOld != null && !veUnidadServicioOld.equals(veUnidadServicioNew)) {
                veUnidadServicioOld.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                veUnidadServicioOld = em.merge(veUnidadServicioOld);
            }
            if (veUnidadServicioNew != null && !veUnidadServicioNew.equals(veUnidadServicioOld)) {
                veUnidadServicioNew.getPrDetalleTarifarioList().add(prDetalleTarifario);
                veUnidadServicioNew = em.merge(veUnidadServicioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrDetalleTarifarioPK id = prDetalleTarifario.getPrDetalleTarifarioPK();
                if (findPrDetalleTarifario(id) == null) {
                    throw new NonexistentEntityException("The prDetalleTarifario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrDetalleTarifarioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrDetalleTarifario prDetalleTarifario;
            try {
                prDetalleTarifario = em.getReference(PrDetalleTarifario.class, id);
                prDetalleTarifario.getPrDetalleTarifarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prDetalleTarifario with id " + id + " no longer exists.", enfe);
            }
            PrPrestaciones prPrestaciones = prDetalleTarifario.getPrPrestaciones();
            if (prPrestaciones != null) {
                prPrestaciones.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prPrestaciones = em.merge(prPrestaciones);
            }
            PrTarifario prTarifario = prDetalleTarifario.getPrTarifario();
            if (prTarifario != null) {
                prTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                prTarifario = em.merge(prTarifario);
            }
            VeUnidadServicio veUnidadServicio = prDetalleTarifario.getVeUnidadServicio();
            if (veUnidadServicio != null) {
                veUnidadServicio.getPrDetalleTarifarioList().remove(prDetalleTarifario);
                veUnidadServicio = em.merge(veUnidadServicio);
            }
            em.remove(prDetalleTarifario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrDetalleTarifario> findPrDetalleTarifarioEntities() {
        return findPrDetalleTarifarioEntities(true, -1, -1);
    }

    public List<PrDetalleTarifario> findPrDetalleTarifarioEntities(int maxResults, int firstResult) {
        return findPrDetalleTarifarioEntities(false, maxResults, firstResult);
    }

    private List<PrDetalleTarifario> findPrDetalleTarifarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrDetalleTarifario.class));
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

    public PrDetalleTarifario findPrDetalleTarifario(PrDetalleTarifarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrDetalleTarifario.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrDetalleTarifarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrDetalleTarifario> rt = cq.from(PrDetalleTarifario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
