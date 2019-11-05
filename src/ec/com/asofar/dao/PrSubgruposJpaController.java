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
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrSubgruposPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class PrSubgruposJpaController implements Serializable {

    public PrSubgruposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrSubgrupos prSubgrupos) throws PreexistingEntityException, Exception {
        if (prSubgrupos.getPrSubgruposPK() == null) {
            prSubgrupos.setPrSubgruposPK(new PrSubgruposPK());
        }
        if (prSubgrupos.getPrArticuloList() == null) {
            prSubgrupos.setPrArticuloList(new ArrayList<PrArticulo>());
        }
        prSubgrupos.getPrSubgruposPK().setIdGrupo(prSubgrupos.getPrGrupos().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = prSubgrupos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                prSubgrupos.setIdEmpresa(idEmpresa);
            }
            PrGrupos prGrupos = prSubgrupos.getPrGrupos();
            if (prGrupos != null) {
                prGrupos = em.getReference(prGrupos.getClass(), prGrupos.getIdGrupo());
                prSubgrupos.setPrGrupos(prGrupos);
            }
            List<PrArticulo> attachedPrArticuloList = new ArrayList<PrArticulo>();
            for (PrArticulo prArticuloListPrArticuloToAttach : prSubgrupos.getPrArticuloList()) {
                prArticuloListPrArticuloToAttach = em.getReference(prArticuloListPrArticuloToAttach.getClass(), prArticuloListPrArticuloToAttach.getPrArticuloPK());
                attachedPrArticuloList.add(prArticuloListPrArticuloToAttach);
            }
            prSubgrupos.setPrArticuloList(attachedPrArticuloList);
            em.persist(prSubgrupos);
            if (idEmpresa != null) {
                idEmpresa.getPrSubgruposList().add(prSubgrupos);
                idEmpresa = em.merge(idEmpresa);
            }
            if (prGrupos != null) {
                prGrupos.getPrSubgruposList().add(prSubgrupos);
                prGrupos = em.merge(prGrupos);
            }
            for (PrArticulo prArticuloListPrArticulo : prSubgrupos.getPrArticuloList()) {
                PrSubgrupos oldPrSubgruposOfPrArticuloListPrArticulo = prArticuloListPrArticulo.getPrSubgrupos();
                prArticuloListPrArticulo.setPrSubgrupos(prSubgrupos);
                prArticuloListPrArticulo = em.merge(prArticuloListPrArticulo);
                if (oldPrSubgruposOfPrArticuloListPrArticulo != null) {
                    oldPrSubgruposOfPrArticuloListPrArticulo.getPrArticuloList().remove(prArticuloListPrArticulo);
                    oldPrSubgruposOfPrArticuloListPrArticulo = em.merge(oldPrSubgruposOfPrArticuloListPrArticulo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrSubgrupos(prSubgrupos.getPrSubgruposPK()) != null) {
                throw new PreexistingEntityException("PrSubgrupos " + prSubgrupos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrSubgrupos prSubgrupos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prSubgrupos.getPrSubgruposPK().setIdGrupo(prSubgrupos.getPrGrupos().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrSubgrupos persistentPrSubgrupos = em.find(PrSubgrupos.class, prSubgrupos.getPrSubgruposPK());
            SeEmpresa idEmpresaOld = persistentPrSubgrupos.getIdEmpresa();
            SeEmpresa idEmpresaNew = prSubgrupos.getIdEmpresa();
            PrGrupos prGruposOld = persistentPrSubgrupos.getPrGrupos();
            PrGrupos prGruposNew = prSubgrupos.getPrGrupos();
            List<PrArticulo> prArticuloListOld = persistentPrSubgrupos.getPrArticuloList();
            List<PrArticulo> prArticuloListNew = prSubgrupos.getPrArticuloList();
            List<String> illegalOrphanMessages = null;
            for (PrArticulo prArticuloListOldPrArticulo : prArticuloListOld) {
                if (!prArticuloListNew.contains(prArticuloListOldPrArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrArticulo " + prArticuloListOldPrArticulo + " since its prSubgrupos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                prSubgrupos.setIdEmpresa(idEmpresaNew);
            }
            if (prGruposNew != null) {
                prGruposNew = em.getReference(prGruposNew.getClass(), prGruposNew.getIdGrupo());
                prSubgrupos.setPrGrupos(prGruposNew);
            }
            List<PrArticulo> attachedPrArticuloListNew = new ArrayList<PrArticulo>();
            for (PrArticulo prArticuloListNewPrArticuloToAttach : prArticuloListNew) {
                prArticuloListNewPrArticuloToAttach = em.getReference(prArticuloListNewPrArticuloToAttach.getClass(), prArticuloListNewPrArticuloToAttach.getPrArticuloPK());
                attachedPrArticuloListNew.add(prArticuloListNewPrArticuloToAttach);
            }
            prArticuloListNew = attachedPrArticuloListNew;
            prSubgrupos.setPrArticuloList(prArticuloListNew);
            prSubgrupos = em.merge(prSubgrupos);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPrSubgruposList().remove(prSubgrupos);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPrSubgruposList().add(prSubgrupos);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (prGruposOld != null && !prGruposOld.equals(prGruposNew)) {
                prGruposOld.getPrSubgruposList().remove(prSubgrupos);
                prGruposOld = em.merge(prGruposOld);
            }
            if (prGruposNew != null && !prGruposNew.equals(prGruposOld)) {
                prGruposNew.getPrSubgruposList().add(prSubgrupos);
                prGruposNew = em.merge(prGruposNew);
            }
            for (PrArticulo prArticuloListNewPrArticulo : prArticuloListNew) {
                if (!prArticuloListOld.contains(prArticuloListNewPrArticulo)) {
                    PrSubgrupos oldPrSubgruposOfPrArticuloListNewPrArticulo = prArticuloListNewPrArticulo.getPrSubgrupos();
                    prArticuloListNewPrArticulo.setPrSubgrupos(prSubgrupos);
                    prArticuloListNewPrArticulo = em.merge(prArticuloListNewPrArticulo);
                    if (oldPrSubgruposOfPrArticuloListNewPrArticulo != null && !oldPrSubgruposOfPrArticuloListNewPrArticulo.equals(prSubgrupos)) {
                        oldPrSubgruposOfPrArticuloListNewPrArticulo.getPrArticuloList().remove(prArticuloListNewPrArticulo);
                        oldPrSubgruposOfPrArticuloListNewPrArticulo = em.merge(oldPrSubgruposOfPrArticuloListNewPrArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrSubgruposPK id = prSubgrupos.getPrSubgruposPK();
                if (findPrSubgrupos(id) == null) {
                    throw new NonexistentEntityException("The prSubgrupos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrSubgruposPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrSubgrupos prSubgrupos;
            try {
                prSubgrupos = em.getReference(PrSubgrupos.class, id);
                prSubgrupos.getPrSubgruposPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prSubgrupos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrArticulo> prArticuloListOrphanCheck = prSubgrupos.getPrArticuloList();
            for (PrArticulo prArticuloListOrphanCheckPrArticulo : prArticuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrSubgrupos (" + prSubgrupos + ") cannot be destroyed since the PrArticulo " + prArticuloListOrphanCheckPrArticulo + " in its prArticuloList field has a non-nullable prSubgrupos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa idEmpresa = prSubgrupos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPrSubgruposList().remove(prSubgrupos);
                idEmpresa = em.merge(idEmpresa);
            }
            PrGrupos prGrupos = prSubgrupos.getPrGrupos();
            if (prGrupos != null) {
                prGrupos.getPrSubgruposList().remove(prSubgrupos);
                prGrupos = em.merge(prGrupos);
            }
            em.remove(prSubgrupos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrSubgrupos> findPrSubgruposEntities() {
        return findPrSubgruposEntities(true, -1, -1);
    }

    public List<PrSubgrupos> findPrSubgruposEntities(int maxResults, int firstResult) {
        return findPrSubgruposEntities(false, maxResults, firstResult);
    }

    private List<PrSubgrupos> findPrSubgruposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrSubgrupos.class));
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

    public PrSubgrupos findPrSubgrupos(PrSubgruposPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrSubgrupos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrSubgruposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrSubgrupos> rt = cq.from(PrSubgrupos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
