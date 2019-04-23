/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrGrupos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrSubgrupos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class PrGruposJpaController implements Serializable {

    public PrGruposJpaController() {
        this.emf = Persistence.createEntityManagerFactory("asofarPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrGrupos prGrupos) {
        if (prGrupos.getPrSubgruposList() == null) {
            prGrupos.setPrSubgruposList(new ArrayList<PrSubgrupos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = prGrupos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                prGrupos.setIdEmpresa(idEmpresa);
            }
            List<PrSubgrupos> attachedPrSubgruposList = new ArrayList<PrSubgrupos>();
            for (PrSubgrupos prSubgruposListPrSubgruposToAttach : prGrupos.getPrSubgruposList()) {
                prSubgruposListPrSubgruposToAttach = em.getReference(prSubgruposListPrSubgruposToAttach.getClass(), prSubgruposListPrSubgruposToAttach.getPrSubgruposPK());
                attachedPrSubgruposList.add(prSubgruposListPrSubgruposToAttach);
            }
            prGrupos.setPrSubgruposList(attachedPrSubgruposList);
            em.persist(prGrupos);
            if (idEmpresa != null) {
                idEmpresa.getPrGruposList().add(prGrupos);
                idEmpresa = em.merge(idEmpresa);
            }
            for (PrSubgrupos prSubgruposListPrSubgrupos : prGrupos.getPrSubgruposList()) {
                PrGrupos oldPrGruposOfPrSubgruposListPrSubgrupos = prSubgruposListPrSubgrupos.getPrGrupos();
                prSubgruposListPrSubgrupos.setPrGrupos(prGrupos);
                prSubgruposListPrSubgrupos = em.merge(prSubgruposListPrSubgrupos);
                if (oldPrGruposOfPrSubgruposListPrSubgrupos != null) {
                    oldPrGruposOfPrSubgruposListPrSubgrupos.getPrSubgruposList().remove(prSubgruposListPrSubgrupos);
                    oldPrGruposOfPrSubgruposListPrSubgrupos = em.merge(oldPrGruposOfPrSubgruposListPrSubgrupos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrGrupos prGrupos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrGrupos persistentPrGrupos = em.find(PrGrupos.class, prGrupos.getIdGrupo());
            SeEmpresa idEmpresaOld = persistentPrGrupos.getIdEmpresa();
            SeEmpresa idEmpresaNew = prGrupos.getIdEmpresa();
            List<PrSubgrupos> prSubgruposListOld = persistentPrGrupos.getPrSubgruposList();
            List<PrSubgrupos> prSubgruposListNew = prGrupos.getPrSubgruposList();
            List<String> illegalOrphanMessages = null;
            for (PrSubgrupos prSubgruposListOldPrSubgrupos : prSubgruposListOld) {
                if (!prSubgruposListNew.contains(prSubgruposListOldPrSubgrupos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrSubgrupos " + prSubgruposListOldPrSubgrupos + " since its prGrupos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                prGrupos.setIdEmpresa(idEmpresaNew);
            }
            List<PrSubgrupos> attachedPrSubgruposListNew = new ArrayList<PrSubgrupos>();
            for (PrSubgrupos prSubgruposListNewPrSubgruposToAttach : prSubgruposListNew) {
                prSubgruposListNewPrSubgruposToAttach = em.getReference(prSubgruposListNewPrSubgruposToAttach.getClass(), prSubgruposListNewPrSubgruposToAttach.getPrSubgruposPK());
                attachedPrSubgruposListNew.add(prSubgruposListNewPrSubgruposToAttach);
            }
            prSubgruposListNew = attachedPrSubgruposListNew;
            prGrupos.setPrSubgruposList(prSubgruposListNew);
            prGrupos = em.merge(prGrupos);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPrGruposList().remove(prGrupos);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPrGruposList().add(prGrupos);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (PrSubgrupos prSubgruposListNewPrSubgrupos : prSubgruposListNew) {
                if (!prSubgruposListOld.contains(prSubgruposListNewPrSubgrupos)) {
                    PrGrupos oldPrGruposOfPrSubgruposListNewPrSubgrupos = prSubgruposListNewPrSubgrupos.getPrGrupos();
                    prSubgruposListNewPrSubgrupos.setPrGrupos(prGrupos);
                    prSubgruposListNewPrSubgrupos = em.merge(prSubgruposListNewPrSubgrupos);
                    if (oldPrGruposOfPrSubgruposListNewPrSubgrupos != null && !oldPrGruposOfPrSubgruposListNewPrSubgrupos.equals(prGrupos)) {
                        oldPrGruposOfPrSubgruposListNewPrSubgrupos.getPrSubgruposList().remove(prSubgruposListNewPrSubgrupos);
                        oldPrGruposOfPrSubgruposListNewPrSubgrupos = em.merge(oldPrGruposOfPrSubgruposListNewPrSubgrupos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prGrupos.getIdGrupo();
                if (findPrGrupos(id) == null) {
                    throw new NonexistentEntityException("The prGrupos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrGrupos prGrupos;
            try {
                prGrupos = em.getReference(PrGrupos.class, id);
                prGrupos.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prGrupos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrSubgrupos> prSubgruposListOrphanCheck = prGrupos.getPrSubgruposList();
            for (PrSubgrupos prSubgruposListOrphanCheckPrSubgrupos : prSubgruposListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrGrupos (" + prGrupos + ") cannot be destroyed since the PrSubgrupos " + prSubgruposListOrphanCheckPrSubgrupos + " in its prSubgruposList field has a non-nullable prGrupos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa idEmpresa = prGrupos.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPrGruposList().remove(prGrupos);
                idEmpresa = em.merge(idEmpresa);
            }
            em.remove(prGrupos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrGrupos> findPrGruposEntities() {
        return findPrGruposEntities(true, -1, -1);
    }

    public List<PrGrupos> findPrGruposEntities(int maxResults, int firstResult) {
        return findPrGruposEntities(false, maxResults, firstResult);
    }

    private List<PrGrupos> findPrGruposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrGrupos.class));
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

    public PrGrupos findPrGrupos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrGrupos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrGruposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrGrupos> rt = cq.from(PrGrupos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
