/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrTipoMedidas;
import java.util.ArrayList;
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
        if (prTipoMedidas.getPrMedidasList() == null) {
            prTipoMedidas.setPrMedidasList(new ArrayList<PrMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa idEmpresa = prTipoMedidas.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                prTipoMedidas.setIdEmpresa(idEmpresa);
            }
            List<PrMedidas> attachedPrMedidasList = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListPrMedidasToAttach : prTipoMedidas.getPrMedidasList()) {
                prMedidasListPrMedidasToAttach = em.getReference(prMedidasListPrMedidasToAttach.getClass(), prMedidasListPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasList.add(prMedidasListPrMedidasToAttach);
            }
            prTipoMedidas.setPrMedidasList(attachedPrMedidasList);
            em.persist(prTipoMedidas);
            if (idEmpresa != null) {
                idEmpresa.getPrTipoMedidasList().add(prTipoMedidas);
                idEmpresa = em.merge(idEmpresa);
            }
            for (PrMedidas prMedidasListPrMedidas : prTipoMedidas.getPrMedidasList()) {
                PrTipoMedidas oldPrTipoMedidasOfPrMedidasListPrMedidas = prMedidasListPrMedidas.getPrTipoMedidas();
                prMedidasListPrMedidas.setPrTipoMedidas(prTipoMedidas);
                prMedidasListPrMedidas = em.merge(prMedidasListPrMedidas);
                if (oldPrTipoMedidasOfPrMedidasListPrMedidas != null) {
                    oldPrTipoMedidasOfPrMedidasListPrMedidas.getPrMedidasList().remove(prMedidasListPrMedidas);
                    oldPrTipoMedidasOfPrMedidasListPrMedidas = em.merge(oldPrTipoMedidasOfPrMedidasListPrMedidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrTipoMedidas prTipoMedidas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTipoMedidas persistentPrTipoMedidas = em.find(PrTipoMedidas.class, prTipoMedidas.getIdTipoMedidas());
            SeEmpresa idEmpresaOld = persistentPrTipoMedidas.getIdEmpresa();
            SeEmpresa idEmpresaNew = prTipoMedidas.getIdEmpresa();
            List<PrMedidas> prMedidasListOld = persistentPrTipoMedidas.getPrMedidasList();
            List<PrMedidas> prMedidasListNew = prTipoMedidas.getPrMedidasList();
            List<String> illegalOrphanMessages = null;
            for (PrMedidas prMedidasListOldPrMedidas : prMedidasListOld) {
                if (!prMedidasListNew.contains(prMedidasListOldPrMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrMedidas " + prMedidasListOldPrMedidas + " since its prTipoMedidas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                prTipoMedidas.setIdEmpresa(idEmpresaNew);
            }
            List<PrMedidas> attachedPrMedidasListNew = new ArrayList<PrMedidas>();
            for (PrMedidas prMedidasListNewPrMedidasToAttach : prMedidasListNew) {
                prMedidasListNewPrMedidasToAttach = em.getReference(prMedidasListNewPrMedidasToAttach.getClass(), prMedidasListNewPrMedidasToAttach.getPrMedidasPK());
                attachedPrMedidasListNew.add(prMedidasListNewPrMedidasToAttach);
            }
            prMedidasListNew = attachedPrMedidasListNew;
            prTipoMedidas.setPrMedidasList(prMedidasListNew);
            prTipoMedidas = em.merge(prTipoMedidas);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPrTipoMedidasList().remove(prTipoMedidas);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPrTipoMedidasList().add(prTipoMedidas);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            for (PrMedidas prMedidasListNewPrMedidas : prMedidasListNew) {
                if (!prMedidasListOld.contains(prMedidasListNewPrMedidas)) {
                    PrTipoMedidas oldPrTipoMedidasOfPrMedidasListNewPrMedidas = prMedidasListNewPrMedidas.getPrTipoMedidas();
                    prMedidasListNewPrMedidas.setPrTipoMedidas(prTipoMedidas);
                    prMedidasListNewPrMedidas = em.merge(prMedidasListNewPrMedidas);
                    if (oldPrTipoMedidasOfPrMedidasListNewPrMedidas != null && !oldPrTipoMedidasOfPrMedidasListNewPrMedidas.equals(prTipoMedidas)) {
                        oldPrTipoMedidasOfPrMedidasListNewPrMedidas.getPrMedidasList().remove(prMedidasListNewPrMedidas);
                        oldPrTipoMedidasOfPrMedidasListNewPrMedidas = em.merge(oldPrTipoMedidasOfPrMedidasListNewPrMedidas);
                    }
                }
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<PrMedidas> prMedidasListOrphanCheck = prTipoMedidas.getPrMedidasList();
            for (PrMedidas prMedidasListOrphanCheckPrMedidas : prMedidasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrTipoMedidas (" + prTipoMedidas + ") cannot be destroyed since the PrMedidas " + prMedidasListOrphanCheckPrMedidas + " in its prMedidasList field has a non-nullable prTipoMedidas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
