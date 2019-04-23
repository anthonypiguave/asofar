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
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrTipoMedidas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class PrTipoMedidasJpaController implements Serializable {

    public PrTipoMedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrTipoMedidas prTipoMedidas) throws PreexistingEntityException, Exception {
        if (prTipoMedidas.getPrProductosList() == null) {
            prTipoMedidas.setPrProductosList(new ArrayList<PrProductos>());
        }
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
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : prTipoMedidas.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            prTipoMedidas.setPrProductosList(attachedPrProductosList);
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
            for (PrProductos prProductosListPrProductos : prTipoMedidas.getPrProductosList()) {
                PrTipoMedidas oldPrTipoMedidasOfPrProductosListPrProductos = prProductosListPrProductos.getPrTipoMedidas();
                prProductosListPrProductos.setPrTipoMedidas(prTipoMedidas);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldPrTipoMedidasOfPrProductosListPrProductos != null) {
                    oldPrTipoMedidasOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldPrTipoMedidasOfPrProductosListPrProductos = em.merge(oldPrTipoMedidasOfPrProductosListPrProductos);
                }
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
        } catch (Exception ex) {
            if (findPrTipoMedidas(prTipoMedidas.getIdTipoMedidas()) != null) {
                throw new PreexistingEntityException("PrTipoMedidas " + prTipoMedidas + " already exists.", ex);
            }
            throw ex;
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
            List<PrProductos> prProductosListOld = persistentPrTipoMedidas.getPrProductosList();
            List<PrProductos> prProductosListNew = prTipoMedidas.getPrProductosList();
            List<PrMedidas> prMedidasListOld = persistentPrTipoMedidas.getPrMedidasList();
            List<PrMedidas> prMedidasListNew = prTipoMedidas.getPrMedidasList();
            List<String> illegalOrphanMessages = null;
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrProductos " + prProductosListOldPrProductos + " since its prTipoMedidas field is not nullable.");
                }
            }
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
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            prTipoMedidas.setPrProductosList(prProductosListNew);
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
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    PrTipoMedidas oldPrTipoMedidasOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getPrTipoMedidas();
                    prProductosListNewPrProductos.setPrTipoMedidas(prTipoMedidas);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldPrTipoMedidasOfPrProductosListNewPrProductos != null && !oldPrTipoMedidasOfPrProductosListNewPrProductos.equals(prTipoMedidas)) {
                        oldPrTipoMedidasOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldPrTipoMedidasOfPrProductosListNewPrProductos = em.merge(oldPrTipoMedidasOfPrProductosListNewPrProductos);
                    }
                }
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
            List<PrProductos> prProductosListOrphanCheck = prTipoMedidas.getPrProductosList();
            for (PrProductos prProductosListOrphanCheckPrProductos : prProductosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrTipoMedidas (" + prTipoMedidas + ") cannot be destroyed since the PrProductos " + prProductosListOrphanCheckPrProductos + " in its prProductosList field has a non-nullable prTipoMedidas field.");
            }
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
