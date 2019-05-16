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
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrMedidasPK;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class PrMedidasJpaController implements Serializable {

    public PrMedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrMedidas prMedidas) throws PreexistingEntityException, Exception {
        if (prMedidas.getPrMedidasPK() == null) {
            prMedidas.setPrMedidasPK(new PrMedidasPK());
        }
        if (prMedidas.getPrProductosList() == null) {
            prMedidas.setPrProductosList(new ArrayList<PrProductos>());
        }
        prMedidas.getPrMedidasPK().setIdSubgrupo(prMedidas.getPrArticulo().getPrArticuloPK().getIdSubgrupo());
        prMedidas.getPrMedidasPK().setIdTipoPresentacion(prMedidas.getPrTipoPresentacion().getIdTipoPresentacion());
        prMedidas.getPrMedidasPK().setIdGrupo(prMedidas.getPrArticulo().getPrArticuloPK().getIdGrupo());
        prMedidas.getPrMedidasPK().setIdTipoMedidas(prMedidas.getPrTipoMedidas().getIdTipoMedidas());
        prMedidas.getPrMedidasPK().setIdArticulo(prMedidas.getPrArticulo().getPrArticuloPK().getIdArticulo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrArticulo prArticulo = prMedidas.getPrArticulo();
            if (prArticulo != null) {
                prArticulo = em.getReference(prArticulo.getClass(), prArticulo.getPrArticuloPK());
                prMedidas.setPrArticulo(prArticulo);
            }
            PrTipoMedidas prTipoMedidas = prMedidas.getPrTipoMedidas();
            if (prTipoMedidas != null) {
                prTipoMedidas = em.getReference(prTipoMedidas.getClass(), prTipoMedidas.getIdTipoMedidas());
                prMedidas.setPrTipoMedidas(prTipoMedidas);
            }
            PrTipoPresentacion prTipoPresentacion = prMedidas.getPrTipoPresentacion();
            if (prTipoPresentacion != null) {
                prTipoPresentacion = em.getReference(prTipoPresentacion.getClass(), prTipoPresentacion.getIdTipoPresentacion());
                prMedidas.setPrTipoPresentacion(prTipoPresentacion);
            }
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : prMedidas.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            prMedidas.setPrProductosList(attachedPrProductosList);
            em.persist(prMedidas);
            if (prArticulo != null) {
                prArticulo.getPrMedidasList().add(prMedidas);
                prArticulo = em.merge(prArticulo);
            }
            if (prTipoMedidas != null) {
                prTipoMedidas.getPrMedidasList().add(prMedidas);
                prTipoMedidas = em.merge(prTipoMedidas);
            }
            if (prTipoPresentacion != null) {
                prTipoPresentacion.getPrMedidasList().add(prMedidas);
                prTipoPresentacion = em.merge(prTipoPresentacion);
            }
            for (PrProductos prProductosListPrProductos : prMedidas.getPrProductosList()) {
                PrMedidas oldPrMedidasOfPrProductosListPrProductos = prProductosListPrProductos.getPrMedidas();
                prProductosListPrProductos.setPrMedidas(prMedidas);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldPrMedidasOfPrProductosListPrProductos != null) {
                    oldPrMedidasOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldPrMedidasOfPrProductosListPrProductos = em.merge(oldPrMedidasOfPrProductosListPrProductos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrMedidas(prMedidas.getPrMedidasPK()) != null) {
                throw new PreexistingEntityException("PrMedidas " + prMedidas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrMedidas prMedidas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prMedidas.getPrMedidasPK().setIdSubgrupo(prMedidas.getPrArticulo().getPrArticuloPK().getIdSubgrupo());
        prMedidas.getPrMedidasPK().setIdTipoPresentacion(prMedidas.getPrTipoPresentacion().getIdTipoPresentacion());
        prMedidas.getPrMedidasPK().setIdGrupo(prMedidas.getPrArticulo().getPrArticuloPK().getIdGrupo());
        prMedidas.getPrMedidasPK().setIdTipoMedidas(prMedidas.getPrTipoMedidas().getIdTipoMedidas());
        prMedidas.getPrMedidasPK().setIdArticulo(prMedidas.getPrArticulo().getPrArticuloPK().getIdArticulo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrMedidas persistentPrMedidas = em.find(PrMedidas.class, prMedidas.getPrMedidasPK());
            PrArticulo prArticuloOld = persistentPrMedidas.getPrArticulo();
            PrArticulo prArticuloNew = prMedidas.getPrArticulo();
            PrTipoMedidas prTipoMedidasOld = persistentPrMedidas.getPrTipoMedidas();
            PrTipoMedidas prTipoMedidasNew = prMedidas.getPrTipoMedidas();
            PrTipoPresentacion prTipoPresentacionOld = persistentPrMedidas.getPrTipoPresentacion();
            PrTipoPresentacion prTipoPresentacionNew = prMedidas.getPrTipoPresentacion();
            List<PrProductos> prProductosListOld = persistentPrMedidas.getPrProductosList();
            List<PrProductos> prProductosListNew = prMedidas.getPrProductosList();
            List<String> illegalOrphanMessages = null;
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrProductos " + prProductosListOldPrProductos + " since its prMedidas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prArticuloNew != null) {
                prArticuloNew = em.getReference(prArticuloNew.getClass(), prArticuloNew.getPrArticuloPK());
                prMedidas.setPrArticulo(prArticuloNew);
            }
            if (prTipoMedidasNew != null) {
                prTipoMedidasNew = em.getReference(prTipoMedidasNew.getClass(), prTipoMedidasNew.getIdTipoMedidas());
                prMedidas.setPrTipoMedidas(prTipoMedidasNew);
            }
            if (prTipoPresentacionNew != null) {
                prTipoPresentacionNew = em.getReference(prTipoPresentacionNew.getClass(), prTipoPresentacionNew.getIdTipoPresentacion());
                prMedidas.setPrTipoPresentacion(prTipoPresentacionNew);
            }
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            prMedidas.setPrProductosList(prProductosListNew);
            prMedidas = em.merge(prMedidas);
            if (prArticuloOld != null && !prArticuloOld.equals(prArticuloNew)) {
                prArticuloOld.getPrMedidasList().remove(prMedidas);
                prArticuloOld = em.merge(prArticuloOld);
            }
            if (prArticuloNew != null && !prArticuloNew.equals(prArticuloOld)) {
                prArticuloNew.getPrMedidasList().add(prMedidas);
                prArticuloNew = em.merge(prArticuloNew);
            }
            if (prTipoMedidasOld != null && !prTipoMedidasOld.equals(prTipoMedidasNew)) {
                prTipoMedidasOld.getPrMedidasList().remove(prMedidas);
                prTipoMedidasOld = em.merge(prTipoMedidasOld);
            }
            if (prTipoMedidasNew != null && !prTipoMedidasNew.equals(prTipoMedidasOld)) {
                prTipoMedidasNew.getPrMedidasList().add(prMedidas);
                prTipoMedidasNew = em.merge(prTipoMedidasNew);
            }
            if (prTipoPresentacionOld != null && !prTipoPresentacionOld.equals(prTipoPresentacionNew)) {
                prTipoPresentacionOld.getPrMedidasList().remove(prMedidas);
                prTipoPresentacionOld = em.merge(prTipoPresentacionOld);
            }
            if (prTipoPresentacionNew != null && !prTipoPresentacionNew.equals(prTipoPresentacionOld)) {
                prTipoPresentacionNew.getPrMedidasList().add(prMedidas);
                prTipoPresentacionNew = em.merge(prTipoPresentacionNew);
            }
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    PrMedidas oldPrMedidasOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getPrMedidas();
                    prProductosListNewPrProductos.setPrMedidas(prMedidas);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldPrMedidasOfPrProductosListNewPrProductos != null && !oldPrMedidasOfPrProductosListNewPrProductos.equals(prMedidas)) {
                        oldPrMedidasOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldPrMedidasOfPrProductosListNewPrProductos = em.merge(oldPrMedidasOfPrProductosListNewPrProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrMedidasPK id = prMedidas.getPrMedidasPK();
                if (findPrMedidas(id) == null) {
                    throw new NonexistentEntityException("The prMedidas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrMedidasPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrMedidas prMedidas;
            try {
                prMedidas = em.getReference(PrMedidas.class, id);
                prMedidas.getPrMedidasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prMedidas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrProductos> prProductosListOrphanCheck = prMedidas.getPrProductosList();
            for (PrProductos prProductosListOrphanCheckPrProductos : prProductosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrMedidas (" + prMedidas + ") cannot be destroyed since the PrProductos " + prProductosListOrphanCheckPrProductos + " in its prProductosList field has a non-nullable prMedidas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PrArticulo prArticulo = prMedidas.getPrArticulo();
            if (prArticulo != null) {
                prArticulo.getPrMedidasList().remove(prMedidas);
                prArticulo = em.merge(prArticulo);
            }
            PrTipoMedidas prTipoMedidas = prMedidas.getPrTipoMedidas();
            if (prTipoMedidas != null) {
                prTipoMedidas.getPrMedidasList().remove(prMedidas);
                prTipoMedidas = em.merge(prTipoMedidas);
            }
            PrTipoPresentacion prTipoPresentacion = prMedidas.getPrTipoPresentacion();
            if (prTipoPresentacion != null) {
                prTipoPresentacion.getPrMedidasList().remove(prMedidas);
                prTipoPresentacion = em.merge(prTipoPresentacion);
            }
            em.remove(prMedidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrMedidas> findPrMedidasEntities() {
        return findPrMedidasEntities(true, -1, -1);
    }

    public List<PrMedidas> findPrMedidasEntities(int maxResults, int firstResult) {
        return findPrMedidasEntities(false, maxResults, firstResult);
    }

    private List<PrMedidas> findPrMedidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrMedidas.class));
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

    public PrMedidas findPrMedidas(PrMedidasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrMedidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrMedidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrMedidas> rt = cq.from(PrMedidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
