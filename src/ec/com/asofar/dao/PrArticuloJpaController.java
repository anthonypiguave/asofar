/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrArticuloPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrMedidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class PrArticuloJpaController implements Serializable {

    public PrArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrArticulo prArticulo) throws PreexistingEntityException, Exception {
        if (prArticulo.getPrArticuloPK() == null) {
            prArticulo.setPrArticuloPK(new PrArticuloPK());
        }
        if (prArticulo.getPrMedidasList() == null) {
            prArticulo.setPrMedidasList(new ArrayList<PrMedidas>());
        }
        prArticulo.getPrArticuloPK().setIdGrupo(prArticulo.getPrSubgrupos().getPrSubgruposPK().getIdGrupo());
        prArticulo.getPrArticuloPK().setIdSubgrupo(prArticulo.getPrSubgrupos().getPrSubgruposPK().getIdSubgrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrSubgrupos prSubgrupos = prArticulo.getPrSubgrupos();
            if (prSubgrupos != null) {
                prSubgrupos = em.getReference(prSubgrupos.getClass(), prSubgrupos.getPrSubgruposPK());
                prArticulo.setPrSubgrupos(prSubgrupos);
            }
            List<PrMedidas> attachedPrMedidasList = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListPrMedidasToAttach : prArticulo.getPrMedidasList()) {
                prMedidasListPrMedidasToAttach = em.getReference(prMedidasListPrMedidasToAttach.getClass(), prMedidasListPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasList.add(prMedidasListPrMedidasToAttach);
            }
            prArticulo.setPrMedidasList(attachedPrMedidasList);
            em.persist(prArticulo);
            if (prSubgrupos != null) {
                prSubgrupos.getPrArticuloList().add(prArticulo);
                prSubgrupos = em.merge(prSubgrupos);
            }
            for (PrMedidas prMedidasListPrMedidas : prArticulo.getPrMedidasList()) {
                PrArticulo oldPrArticuloOfPrMedidasListPrMedidas = prMedidasListPrMedidas.getPrArticulo();
                prMedidasListPrMedidas.setPrArticulo(prArticulo);
                prMedidasListPrMedidas = em.merge(prMedidasListPrMedidas);
                if (oldPrArticuloOfPrMedidasListPrMedidas != null) {
                    oldPrArticuloOfPrMedidasListPrMedidas.getPrMedidasList().remove(prMedidasListPrMedidas);
                    oldPrArticuloOfPrMedidasListPrMedidas = em.merge(oldPrArticuloOfPrMedidasListPrMedidas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrArticulo(prArticulo.getPrArticuloPK()) != null) {
                throw new PreexistingEntityException("PrArticulo " + prArticulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrArticulo prArticulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prArticulo.getPrArticuloPK().setIdGrupo(prArticulo.getPrSubgrupos().getPrSubgruposPK().getIdGrupo());
        prArticulo.getPrArticuloPK().setIdSubgrupo(prArticulo.getPrSubgrupos().getPrSubgruposPK().getIdSubgrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrArticulo persistentPrArticulo = em.find(PrArticulo.class, prArticulo.getPrArticuloPK());
            PrSubgrupos prSubgruposOld = persistentPrArticulo.getPrSubgrupos();
            PrSubgrupos prSubgruposNew = prArticulo.getPrSubgrupos();
            List<PrMedidas> prMedidasListOld = persistentPrArticulo.getPrMedidasList();
            List<PrMedidas> prMedidasListNew = prArticulo.getPrMedidasList();
            List<String> illegalOrphanMessages = null;
            for (PrMedidas prMedidasListOldPrMedidas : prMedidasListOld) {
                if (!prMedidasListNew.contains(prMedidasListOldPrMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrMedidas " + prMedidasListOldPrMedidas + " since its prArticulo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prSubgruposNew != null) {
                prSubgruposNew = em.getReference(prSubgruposNew.getClass(), prSubgruposNew.getPrSubgruposPK());
                prArticulo.setPrSubgrupos(prSubgruposNew);
            }
            List<PrMedidas> attachedPrMedidasListNew = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListNewPrMedidasToAttach : prMedidasListNew) {
                prMedidasListNewPrMedidasToAttach = em.getReference(prMedidasListNewPrMedidasToAttach.getClass(), prMedidasListNewPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasListNew.add(prMedidasListNewPrMedidasToAttach);
            }
            prMedidasListNew = attachedPrMedidasListNew;
            prArticulo.setPrMedidasList(prMedidasListNew);
            prArticulo = em.merge(prArticulo);
            if (prSubgruposOld != null && !prSubgruposOld.equals(prSubgruposNew)) {
                prSubgruposOld.getPrArticuloList().remove(prArticulo);
                prSubgruposOld = em.merge(prSubgruposOld);
            }
            if (prSubgruposNew != null && !prSubgruposNew.equals(prSubgruposOld)) {
                prSubgruposNew.getPrArticuloList().add(prArticulo);
                prSubgruposNew = em.merge(prSubgruposNew);
            }
            for (PrMedidas prMedidasListNewPrMedidas : prMedidasListNew) {
                if (!prMedidasListOld.contains(prMedidasListNewPrMedidas)) {
                    PrArticulo oldPrArticuloOfPrMedidasListNewPrMedidas = prMedidasListNewPrMedidas.getPrArticulo();
                    prMedidasListNewPrMedidas.setPrArticulo(prArticulo);
                    prMedidasListNewPrMedidas = em.merge(prMedidasListNewPrMedidas);
                    if (oldPrArticuloOfPrMedidasListNewPrMedidas != null && !oldPrArticuloOfPrMedidasListNewPrMedidas.equals(prArticulo)) {
                        oldPrArticuloOfPrMedidasListNewPrMedidas.getPrMedidasList().remove(prMedidasListNewPrMedidas);
                        oldPrArticuloOfPrMedidasListNewPrMedidas = em.merge(oldPrArticuloOfPrMedidasListNewPrMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrArticuloPK id = prArticulo.getPrArticuloPK();
                if (findPrArticulo(id) == null) {
                    throw new NonexistentEntityException("The prArticulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrArticuloPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrArticulo prArticulo;
            try {
                prArticulo = em.getReference(PrArticulo.class, id);
                prArticulo.getPrArticuloPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prArticulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrMedidas> prMedidasListOrphanCheck = prArticulo.getPrMedidasList();
            for (PrMedidas prMedidasListOrphanCheckPrMedidas : prMedidasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrArticulo (" + prArticulo + ") cannot be destroyed since the PrMedidas " + prMedidasListOrphanCheckPrMedidas + " in its prMedidasList field has a non-nullable prArticulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PrSubgrupos prSubgrupos = prArticulo.getPrSubgrupos();
            if (prSubgrupos != null) {
                prSubgrupos.getPrArticuloList().remove(prArticulo);
                prSubgrupos = em.merge(prSubgrupos);
            }
            em.remove(prArticulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrArticulo> findPrArticuloEntities() {
        return findPrArticuloEntities(true, -1, -1);
    }

    public List<PrArticulo> findPrArticuloEntities(int maxResults, int firstResult) {
        return findPrArticuloEntities(false, maxResults, firstResult);
    }

    private List<PrArticulo> findPrArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrArticulo.class));
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

    public PrArticulo findPrArticulo(PrArticuloPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrArticulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrArticulo> rt = cq.from(PrArticulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
