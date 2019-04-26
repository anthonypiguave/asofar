/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrTipoMedidas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeEmpresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class PrTipoMedidasJpaController implements Serializable {

    public PrTipoMedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrTipoMedidas prTipoMedidas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = prTipoMedidas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                prTipoMedidas.setIdEmpresa(idEmpresa);
            }
            em.persist(prTipoMedidas);
            if (idEmpresa != null) {
                idEmpresa.getPrTipoMedidasList().add(prTipoMedidas);
                idEmpresa = em.merge(idEmpresa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrTipoMedidas prTipoMedidas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTipoMedidas persistentPrTipoMedidas = em.find(PrTipoMedidas.class, prTipoMedidas.getIdTipoMedidas());
            SeEmpresa idEmpresaOld = persistentPrTipoMedidas.getIdEmpresa();
            SeEmpresa idEmpresaNew = prTipoMedidas.getIdEmpresa();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                prTipoMedidas.setIdEmpresa(idEmpresaNew);
            }
            prTipoMedidas = em.merge(prTipoMedidas);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPrTipoMedidasList().remove(prTipoMedidas);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPrTipoMedidasList().add(prTipoMedidas);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prTipoMedidas.getIdTipoMedidas();
                if (findPrTipoMedidas(id) == null) {
                    throw new NonexistentEntityException("The prTipoMedidas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTipoMedidas prTipoMedidas;
            try {
                prTipoMedidas = em.getReference(PrTipoMedidas.class, id);
                prTipoMedidas.getIdTipoMedidas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prTipoMedidas with id " + id + " no longer exists.", enfe);
            }
            SeEmpresa idEmpresa = prTipoMedidas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPrTipoMedidasList().remove(prTipoMedidas);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(prTipoMedidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrTipoMedidas> findPrTipoMedidasEntities() {
        return findPrTipoMedidasEntities(true, -1, -1);
    }

    public List<PrTipoMedidas> findPrTipoMedidasEntities(int maxResults, int firstResult) {
        return findPrTipoMedidasEntities(false, maxResults, firstResult);
    }

    private List<PrTipoMedidas> findPrTipoMedidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrTipoMedidas.class));
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

    public PrTipoMedidas findPrTipoMedidas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrTipoMedidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrTipoMedidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrTipoMedidas> rt = cq.from(PrTipoMedidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
