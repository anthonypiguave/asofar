/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrFabricante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class PrFabricanteJpaController implements Serializable {

    public PrFabricanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrFabricante prFabricante) {
        if (prFabricante.getPrProductosList() == null) {
            prFabricante.setPrProductosList(new ArrayList<PrProductos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : prFabricante.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            prFabricante.setPrProductosList(attachedPrProductosList);
            em.persist(prFabricante);
            for (PrProductos prProductosListPrProductos : prFabricante.getPrProductosList()) {
                PrFabricante oldCodFabricanteOfPrProductosListPrProductos = prProductosListPrProductos.getCodFabricante();
                prProductosListPrProductos.setCodFabricante(prFabricante);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldCodFabricanteOfPrProductosListPrProductos != null) {
                    oldCodFabricanteOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldCodFabricanteOfPrProductosListPrProductos = em.merge(oldCodFabricanteOfPrProductosListPrProductos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrFabricante prFabricante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrFabricante persistentPrFabricante = em.find(PrFabricante.class, prFabricante.getIdFabricante());
            List<PrProductos> prProductosListOld = persistentPrFabricante.getPrProductosList();
            List<PrProductos> prProductosListNew = prFabricante.getPrProductosList();
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            prFabricante.setPrProductosList(prProductosListNew);
            prFabricante = em.merge(prFabricante);
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    prProductosListOldPrProductos.setCodFabricante(null);
                    prProductosListOldPrProductos = em.merge(prProductosListOldPrProductos);
                }
            }
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    PrFabricante oldCodFabricanteOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getCodFabricante();
                    prProductosListNewPrProductos.setCodFabricante(prFabricante);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldCodFabricanteOfPrProductosListNewPrProductos != null && !oldCodFabricanteOfPrProductosListNewPrProductos.equals(prFabricante)) {
                        oldCodFabricanteOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldCodFabricanteOfPrProductosListNewPrProductos = em.merge(oldCodFabricanteOfPrProductosListNewPrProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prFabricante.getIdFabricante();
                if (findPrFabricante(id) == null) {
                    throw new NonexistentEntityException("The prFabricante with id " + id + " no longer exists.");
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
            PrFabricante prFabricante;
            try {
                prFabricante = em.getReference(PrFabricante.class, id);
                prFabricante.getIdFabricante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prFabricante with id " + id + " no longer exists.", enfe);
            }
            List<PrProductos> prProductosList = prFabricante.getPrProductosList();
            for (PrProductos prProductosListPrProductos : prProductosList) {
                prProductosListPrProductos.setCodFabricante(null);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
            }
            em.remove(prFabricante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrFabricante> findPrFabricanteEntities() {
        return findPrFabricanteEntities(true, -1, -1);
    }

    public List<PrFabricante> findPrFabricanteEntities(int maxResults, int firstResult) {
        return findPrFabricanteEntities(false, maxResults, firstResult);
    }

    private List<PrFabricante> findPrFabricanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrFabricante.class));
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

    public PrFabricante findPrFabricante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrFabricante.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrFabricanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrFabricante> rt = cq.from(PrFabricante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
