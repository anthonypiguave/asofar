/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.SeCiudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SePais;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
 */
public class SeCiudadJpaController implements Serializable {

    public SeCiudadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeCiudad seCiudad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeProvincia idProvincia = seCiudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                seCiudad.setIdProvincia(idProvincia);
            }
            SePais idPais = seCiudad.getIdPais();
            if (idPais != null) {
                idPais = em.getReference(idPais.getClass(), idPais.getIdPais());
                seCiudad.setIdPais(idPais);
            }
            em.persist(seCiudad);
            if (idProvincia != null) {
                idProvincia.getSeCiudadList().add(seCiudad);
                idProvincia = em.merge(idProvincia);
            }
            if (idPais != null) {
                idPais.getSeCiudadList().add(seCiudad);
                idPais = em.merge(idPais);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeCiudad seCiudad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeCiudad persistentSeCiudad = em.find(SeCiudad.class, seCiudad.getIdCiudad());
            SeProvincia idProvinciaOld = persistentSeCiudad.getIdProvincia();
            SeProvincia idProvinciaNew = seCiudad.getIdProvincia();
            SePais idPaisOld = persistentSeCiudad.getIdPais();
            SePais idPaisNew = seCiudad.getIdPais();
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                seCiudad.setIdProvincia(idProvinciaNew);
            }
            if (idPaisNew != null) {
                idPaisNew = em.getReference(idPaisNew.getClass(), idPaisNew.getIdPais());
                seCiudad.setIdPais(idPaisNew);
            }
            seCiudad = em.merge(seCiudad);
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getSeCiudadList().remove(seCiudad);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getSeCiudadList().add(seCiudad);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            if (idPaisOld != null && !idPaisOld.equals(idPaisNew)) {
                idPaisOld.getSeCiudadList().remove(seCiudad);
                idPaisOld = em.merge(idPaisOld);
            }
            if (idPaisNew != null && !idPaisNew.equals(idPaisOld)) {
                idPaisNew.getSeCiudadList().add(seCiudad);
                idPaisNew = em.merge(idPaisNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seCiudad.getIdCiudad();
                if (findSeCiudad(id) == null) {
                    throw new NonexistentEntityException("The seCiudad with id " + id + " no longer exists.");
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
            SeCiudad seCiudad;
            try {
                seCiudad = em.getReference(SeCiudad.class, id);
                seCiudad.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seCiudad with id " + id + " no longer exists.", enfe);
            }
            SeProvincia idProvincia = seCiudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getSeCiudadList().remove(seCiudad);
                idProvincia = em.merge(idProvincia);
            }
            SePais idPais = seCiudad.getIdPais();
            if (idPais != null) {
                idPais.getSeCiudadList().remove(seCiudad);
                idPais = em.merge(idPais);
            }
            em.remove(seCiudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeCiudad> findSeCiudadEntities() {
        return findSeCiudadEntities(true, -1, -1);
    }

    public List<SeCiudad> findSeCiudadEntities(int maxResults, int firstResult) {
        return findSeCiudadEntities(false, maxResults, firstResult);
    }

    private List<SeCiudad> findSeCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeCiudad.class));
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

    public SeCiudad findSeCiudad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeCiudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeCiudad> rt = cq.from(SeCiudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
