/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.InTipoDocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class InKardexJpaController implements Serializable {

    public InKardexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InKardex inKardex) throws PreexistingEntityException, Exception {
        if (inKardex.getInKardexPK() == null) {
            inKardex.setInKardexPK(new InKardexPK());
        }
        inKardex.getInKardexPK().setIdTipoDocumento(inKardex.getInTipoDocumento().getIdTipoDocumento());
        inKardex.getInKardexPK().setIdEmpresa(inKardex.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inKardex.getInKardexPK().setIdSucursal(inKardex.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = inKardex.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                inKardex.setSeSucursal(seSucursal);
            }
            InTipoDocumento inTipoDocumento = inKardex.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento = em.getReference(inTipoDocumento.getClass(), inTipoDocumento.getIdTipoDocumento());
                inKardex.setInTipoDocumento(inTipoDocumento);
            }
            em.persist(inKardex);
            if (seSucursal != null) {
                seSucursal.getInKardexList().add(inKardex);
                seSucursal = em.merge(seSucursal);
            }
            if (inTipoDocumento != null) {
                inTipoDocumento.getInKardexList().add(inKardex);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInKardex(inKardex.getInKardexPK()) != null) {
                throw new PreexistingEntityException("InKardex " + inKardex + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InKardex inKardex) throws NonexistentEntityException, Exception {
        inKardex.getInKardexPK().setIdTipoDocumento(inKardex.getInTipoDocumento().getIdTipoDocumento());
        inKardex.getInKardexPK().setIdEmpresa(inKardex.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        inKardex.getInKardexPK().setIdSucursal(inKardex.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InKardex persistentInKardex = em.find(InKardex.class, inKardex.getInKardexPK());
            SeSucursal seSucursalOld = persistentInKardex.getSeSucursal();
            SeSucursal seSucursalNew = inKardex.getSeSucursal();
            InTipoDocumento inTipoDocumentoOld = persistentInKardex.getInTipoDocumento();
            InTipoDocumento inTipoDocumentoNew = inKardex.getInTipoDocumento();
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                inKardex.setSeSucursal(seSucursalNew);
            }
            if (inTipoDocumentoNew != null) {
                inTipoDocumentoNew = em.getReference(inTipoDocumentoNew.getClass(), inTipoDocumentoNew.getIdTipoDocumento());
                inKardex.setInTipoDocumento(inTipoDocumentoNew);
            }
            inKardex = em.merge(inKardex);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getInKardexList().remove(inKardex);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getInKardexList().add(inKardex);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (inTipoDocumentoOld != null && !inTipoDocumentoOld.equals(inTipoDocumentoNew)) {
                inTipoDocumentoOld.getInKardexList().remove(inKardex);
                inTipoDocumentoOld = em.merge(inTipoDocumentoOld);
            }
            if (inTipoDocumentoNew != null && !inTipoDocumentoNew.equals(inTipoDocumentoOld)) {
                inTipoDocumentoNew.getInKardexList().add(inKardex);
                inTipoDocumentoNew = em.merge(inTipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InKardexPK id = inKardex.getInKardexPK();
                if (findInKardex(id) == null) {
                    throw new NonexistentEntityException("The inKardex with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InKardexPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InKardex inKardex;
            try {
                inKardex = em.getReference(InKardex.class, id);
                inKardex.getInKardexPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inKardex with id " + id + " no longer exists.", enfe);
            }
            SeSucursal seSucursal = inKardex.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getInKardexList().remove(inKardex);
                seSucursal = em.merge(seSucursal);
            }
            InTipoDocumento inTipoDocumento = inKardex.getInTipoDocumento();
            if (inTipoDocumento != null) {
                inTipoDocumento.getInKardexList().remove(inKardex);
                inTipoDocumento = em.merge(inTipoDocumento);
            }
            em.remove(inKardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InKardex> findInKardexEntities() {
        return findInKardexEntities(true, -1, -1);
    }

    public List<InKardex> findInKardexEntities(int maxResults, int firstResult) {
        return findInKardexEntities(false, maxResults, firstResult);
    }

    private List<InKardex> findInKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InKardex.class));
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

    public InKardex findInKardex(InKardexPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InKardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getInKardexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InKardex> rt = cq.from(InKardex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
