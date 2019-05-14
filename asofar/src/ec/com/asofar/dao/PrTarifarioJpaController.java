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
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTarifarioPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class PrTarifarioJpaController implements Serializable {

    public PrTarifarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrTarifario prTarifario) throws PreexistingEntityException, Exception {
        if (prTarifario.getPrTarifarioPK() == null) {
            prTarifario.setPrTarifarioPK(new PrTarifarioPK());
        }
        if (prTarifario.getPrDetalleTarifarioList() == null) {
            prTarifario.setPrDetalleTarifarioList(new ArrayList<PrDetalleTarifario>());
        }
        prTarifario.getPrTarifarioPK().setIdEmpresa(prTarifario.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        prTarifario.getPrTarifarioPK().setIdSurcusal(prTarifario.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal = prTarifario.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                prTarifario.setSeSucursal(seSucursal);
            }
            List<PrDetalleTarifario> attachedPrDetalleTarifarioList = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifarioToAttach : prTarifario.getPrDetalleTarifarioList()) {
                prDetalleTarifarioListPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioList.add(prDetalleTarifarioListPrDetalleTarifarioToAttach);
            }
            prTarifario.setPrDetalleTarifarioList(attachedPrDetalleTarifarioList);
            em.persist(prTarifario);
            if (seSucursal != null) {
                seSucursal.getPrTarifarioList().add(prTarifario);
                seSucursal = em.merge(seSucursal);
            }
            for (PrDetalleTarifario prDetalleTarifarioListPrDetalleTarifario : prTarifario.getPrDetalleTarifarioList()) {
                PrTarifario oldPrTarifarioOfPrDetalleTarifarioListPrDetalleTarifario = prDetalleTarifarioListPrDetalleTarifario.getPrTarifario();
                prDetalleTarifarioListPrDetalleTarifario.setPrTarifario(prTarifario);
                prDetalleTarifarioListPrDetalleTarifario = em.merge(prDetalleTarifarioListPrDetalleTarifario);
                if (oldPrTarifarioOfPrDetalleTarifarioListPrDetalleTarifario != null) {
                    oldPrTarifarioOfPrDetalleTarifarioListPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListPrDetalleTarifario);
                    oldPrTarifarioOfPrDetalleTarifarioListPrDetalleTarifario = em.merge(oldPrTarifarioOfPrDetalleTarifarioListPrDetalleTarifario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrTarifario(prTarifario.getPrTarifarioPK()) != null) {
                throw new PreexistingEntityException("PrTarifario " + prTarifario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrTarifario prTarifario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prTarifario.getPrTarifarioPK().setIdEmpresa(prTarifario.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        prTarifario.getPrTarifarioPK().setIdSurcusal(prTarifario.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTarifario persistentPrTarifario = em.find(PrTarifario.class, prTarifario.getPrTarifarioPK());
            SeSucursal seSucursalOld = persistentPrTarifario.getSeSucursal();
            SeSucursal seSucursalNew = prTarifario.getSeSucursal();
            List<PrDetalleTarifario> prDetalleTarifarioListOld = persistentPrTarifario.getPrDetalleTarifarioList();
            List<PrDetalleTarifario> prDetalleTarifarioListNew = prTarifario.getPrDetalleTarifarioList();
            List<String> illegalOrphanMessages = null;
            for (PrDetalleTarifario prDetalleTarifarioListOldPrDetalleTarifario : prDetalleTarifarioListOld) {
                if (!prDetalleTarifarioListNew.contains(prDetalleTarifarioListOldPrDetalleTarifario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrDetalleTarifario " + prDetalleTarifarioListOldPrDetalleTarifario + " since its prTarifario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                prTarifario.setSeSucursal(seSucursalNew);
            }
            List<PrDetalleTarifario> attachedPrDetalleTarifarioListNew = new ArrayList<PrDetalleTarifario>();
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifarioToAttach : prDetalleTarifarioListNew) {
                prDetalleTarifarioListNewPrDetalleTarifarioToAttach = em.getReference(prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getClass(), prDetalleTarifarioListNewPrDetalleTarifarioToAttach.getPrDetalleTarifarioPK());
                attachedPrDetalleTarifarioListNew.add(prDetalleTarifarioListNewPrDetalleTarifarioToAttach);
            }
            prDetalleTarifarioListNew = attachedPrDetalleTarifarioListNew;
            prTarifario.setPrDetalleTarifarioList(prDetalleTarifarioListNew);
            prTarifario = em.merge(prTarifario);
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getPrTarifarioList().remove(prTarifario);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getPrTarifarioList().add(prTarifario);
                seSucursalNew = em.merge(seSucursalNew);
            }
            for (PrDetalleTarifario prDetalleTarifarioListNewPrDetalleTarifario : prDetalleTarifarioListNew) {
                if (!prDetalleTarifarioListOld.contains(prDetalleTarifarioListNewPrDetalleTarifario)) {
                    PrTarifario oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario = prDetalleTarifarioListNewPrDetalleTarifario.getPrTarifario();
                    prDetalleTarifarioListNewPrDetalleTarifario.setPrTarifario(prTarifario);
                    prDetalleTarifarioListNewPrDetalleTarifario = em.merge(prDetalleTarifarioListNewPrDetalleTarifario);
                    if (oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario != null && !oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario.equals(prTarifario)) {
                        oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario.getPrDetalleTarifarioList().remove(prDetalleTarifarioListNewPrDetalleTarifario);
                        oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario = em.merge(oldPrTarifarioOfPrDetalleTarifarioListNewPrDetalleTarifario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrTarifarioPK id = prTarifario.getPrTarifarioPK();
                if (findPrTarifario(id) == null) {
                    throw new NonexistentEntityException("The prTarifario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrTarifarioPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrTarifario prTarifario;
            try {
                prTarifario = em.getReference(PrTarifario.class, id);
                prTarifario.getPrTarifarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prTarifario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrDetalleTarifario> prDetalleTarifarioListOrphanCheck = prTarifario.getPrDetalleTarifarioList();
            for (PrDetalleTarifario prDetalleTarifarioListOrphanCheckPrDetalleTarifario : prDetalleTarifarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrTarifario (" + prTarifario + ") cannot be destroyed since the PrDetalleTarifario " + prDetalleTarifarioListOrphanCheckPrDetalleTarifario + " in its prDetalleTarifarioList field has a non-nullable prTarifario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeSucursal seSucursal = prTarifario.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getPrTarifarioList().remove(prTarifario);
                seSucursal = em.merge(seSucursal);
            }
            em.remove(prTarifario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrTarifario> findPrTarifarioEntities() {
        return findPrTarifarioEntities(true, -1, -1);
    }

    public List<PrTarifario> findPrTarifarioEntities(int maxResults, int firstResult) {
        return findPrTarifarioEntities(false, maxResults, firstResult);
    }

    private List<PrTarifario> findPrTarifarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrTarifario.class));
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

    public PrTarifario findPrTarifario(PrTarifarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrTarifario.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrTarifarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrTarifario> rt = cq.from(PrTarifario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
