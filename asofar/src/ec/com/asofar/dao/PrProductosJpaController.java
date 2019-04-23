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
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.PrPrestaciones;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.PrProductoBodega;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrProductosPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class PrProductosJpaController implements Serializable {

    public PrProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrProductos prProductos) throws PreexistingEntityException, Exception {
        if (prProductos.getPrProductosPK() == null) {
            prProductos.setPrProductosPK(new PrProductosPK());
        }
        if (prProductos.getPrPrestacionesList() == null) {
            prProductos.setPrPrestacionesList(new ArrayList<PrPrestaciones>());
        }
        if (prProductos.getPrProductoBodegaList() == null) {
            prProductos.setPrProductoBodegaList(new ArrayList<PrProductoBodega>());
        }
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdTipoMedidas(prProductos.getPrTipoMedidas().getIdTipoMedidas());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdTipoPresentacion(prProductos.getPrTipoPresentacion().getIdTipoPresentacion());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa seEmpresa = prProductos.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa = em.getReference(seEmpresa.getClass(), seEmpresa.getIdEmpresa());
                prProductos.setSeEmpresa(seEmpresa);
            }
            PrMedidas prMedidas = prProductos.getPrMedidas();
            if (prMedidas != null) {
                prMedidas = em.getReference(prMedidas.getClass(), prMedidas.getPrMedidasPK());
                prProductos.setPrMedidas(prMedidas);
            }
            PrTipoMedidas prTipoMedidas = prProductos.getPrTipoMedidas();
            if (prTipoMedidas != null) {
                prTipoMedidas = em.getReference(prTipoMedidas.getClass(), prTipoMedidas.getIdTipoMedidas());
                prProductos.setPrTipoMedidas(prTipoMedidas);
            }
            PrTipoPresentacion prTipoPresentacion = prProductos.getPrTipoPresentacion();
            if (prTipoPresentacion != null) {
                prTipoPresentacion = em.getReference(prTipoPresentacion.getClass(), prTipoPresentacion.getIdTipoPresentacion());
                prProductos.setPrTipoPresentacion(prTipoPresentacion);
            }
            List<PrPrestaciones> attachedPrPrestacionesList = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListPrPrestacionesToAttach : prProductos.getPrPrestacionesList()) {
                prPrestacionesListPrPrestacionesToAttach = em.getReference(prPrestacionesListPrPrestacionesToAttach.getClass(), prPrestacionesListPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesList.add(prPrestacionesListPrPrestacionesToAttach);
            }
            prProductos.setPrPrestacionesList(attachedPrPrestacionesList);
            List<PrProductoBodega> attachedPrProductoBodegaList = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListPrProductoBodegaToAttach : prProductos.getPrProductoBodegaList()) {
                prProductoBodegaListPrProductoBodegaToAttach = em.getReference(prProductoBodegaListPrProductoBodegaToAttach.getClass(), prProductoBodegaListPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaList.add(prProductoBodegaListPrProductoBodegaToAttach);
            }
            prProductos.setPrProductoBodegaList(attachedPrProductoBodegaList);
            em.persist(prProductos);
            if (seEmpresa != null) {
                seEmpresa.getPrProductosList().add(prProductos);
                seEmpresa = em.merge(seEmpresa);
            }
            if (prMedidas != null) {
                prMedidas.getPrProductosList().add(prProductos);
                prMedidas = em.merge(prMedidas);
            }
            if (prTipoMedidas != null) {
                prTipoMedidas.getPrProductosList().add(prProductos);
                prTipoMedidas = em.merge(prTipoMedidas);
            }
            if (prTipoPresentacion != null) {
                prTipoPresentacion.getPrProductosList().add(prProductos);
                prTipoPresentacion = em.merge(prTipoPresentacion);
            }
            for (PrPrestaciones prPrestacionesListPrPrestaciones : prProductos.getPrPrestacionesList()) {
                PrProductos oldPrProductosOfPrPrestacionesListPrPrestaciones = prPrestacionesListPrPrestaciones.getPrProductos();
                prPrestacionesListPrPrestaciones.setPrProductos(prProductos);
                prPrestacionesListPrPrestaciones = em.merge(prPrestacionesListPrPrestaciones);
                if (oldPrProductosOfPrPrestacionesListPrPrestaciones != null) {
                    oldPrProductosOfPrPrestacionesListPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListPrPrestaciones);
                    oldPrProductosOfPrPrestacionesListPrPrestaciones = em.merge(oldPrProductosOfPrPrestacionesListPrPrestaciones);
                }
            }
            for (PrProductoBodega prProductoBodegaListPrProductoBodega : prProductos.getPrProductoBodegaList()) {
                PrProductos oldPrProductosOfPrProductoBodegaListPrProductoBodega = prProductoBodegaListPrProductoBodega.getPrProductos();
                prProductoBodegaListPrProductoBodega.setPrProductos(prProductos);
                prProductoBodegaListPrProductoBodega = em.merge(prProductoBodegaListPrProductoBodega);
                if (oldPrProductosOfPrProductoBodegaListPrProductoBodega != null) {
                    oldPrProductosOfPrProductoBodegaListPrProductoBodega.getPrProductoBodegaList().remove(prProductoBodegaListPrProductoBodega);
                    oldPrProductosOfPrProductoBodegaListPrProductoBodega = em.merge(oldPrProductosOfPrProductoBodegaListPrProductoBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrProductos(prProductos.getPrProductosPK()) != null) {
                throw new PreexistingEntityException("PrProductos " + prProductos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrProductos prProductos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdTipoMedidas(prProductos.getPrTipoMedidas().getIdTipoMedidas());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdTipoPresentacion(prProductos.getPrTipoPresentacion().getIdTipoPresentacion());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductos persistentPrProductos = em.find(PrProductos.class, prProductos.getPrProductosPK());
            SeEmpresa seEmpresaOld = persistentPrProductos.getSeEmpresa();
            SeEmpresa seEmpresaNew = prProductos.getSeEmpresa();
            PrMedidas prMedidasOld = persistentPrProductos.getPrMedidas();
            PrMedidas prMedidasNew = prProductos.getPrMedidas();
            PrTipoMedidas prTipoMedidasOld = persistentPrProductos.getPrTipoMedidas();
            PrTipoMedidas prTipoMedidasNew = prProductos.getPrTipoMedidas();
            PrTipoPresentacion prTipoPresentacionOld = persistentPrProductos.getPrTipoPresentacion();
            PrTipoPresentacion prTipoPresentacionNew = prProductos.getPrTipoPresentacion();
            List<PrPrestaciones> prPrestacionesListOld = persistentPrProductos.getPrPrestacionesList();
            List<PrPrestaciones> prPrestacionesListNew = prProductos.getPrPrestacionesList();
            List<PrProductoBodega> prProductoBodegaListOld = persistentPrProductos.getPrProductoBodegaList();
            List<PrProductoBodega> prProductoBodegaListNew = prProductos.getPrProductoBodegaList();
            List<String> illegalOrphanMessages = null;
            for (PrPrestaciones prPrestacionesListOldPrPrestaciones : prPrestacionesListOld) {
                if (!prPrestacionesListNew.contains(prPrestacionesListOldPrPrestaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrPrestaciones " + prPrestacionesListOldPrPrestaciones + " since its prProductos field is not nullable.");
                }
            }
            for (PrProductoBodega prProductoBodegaListOldPrProductoBodega : prProductoBodegaListOld) {
                if (!prProductoBodegaListNew.contains(prProductoBodegaListOldPrProductoBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrProductoBodega " + prProductoBodegaListOldPrProductoBodega + " since its prProductos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seEmpresaNew != null) {
                seEmpresaNew = em.getReference(seEmpresaNew.getClass(), seEmpresaNew.getIdEmpresa());
                prProductos.setSeEmpresa(seEmpresaNew);
            }
            if (prMedidasNew != null) {
                prMedidasNew = em.getReference(prMedidasNew.getClass(), prMedidasNew.getPrMedidasPK());
                prProductos.setPrMedidas(prMedidasNew);
            }
            if (prTipoMedidasNew != null) {
                prTipoMedidasNew = em.getReference(prTipoMedidasNew.getClass(), prTipoMedidasNew.getIdTipoMedidas());
                prProductos.setPrTipoMedidas(prTipoMedidasNew);
            }
            if (prTipoPresentacionNew != null) {
                prTipoPresentacionNew = em.getReference(prTipoPresentacionNew.getClass(), prTipoPresentacionNew.getIdTipoPresentacion());
                prProductos.setPrTipoPresentacion(prTipoPresentacionNew);
            }
            List<PrPrestaciones> attachedPrPrestacionesListNew = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListNewPrPrestacionesToAttach : prPrestacionesListNew) {
                prPrestacionesListNewPrPrestacionesToAttach = em.getReference(prPrestacionesListNewPrPrestacionesToAttach.getClass(), prPrestacionesListNewPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesListNew.add(prPrestacionesListNewPrPrestacionesToAttach);
            }
            prPrestacionesListNew = attachedPrPrestacionesListNew;
            prProductos.setPrPrestacionesList(prPrestacionesListNew);
            List<PrProductoBodega> attachedPrProductoBodegaListNew = new ArrayList<PrProductoBodega>();
            for (PrProductoBodega prProductoBodegaListNewPrProductoBodegaToAttach : prProductoBodegaListNew) {
                prProductoBodegaListNewPrProductoBodegaToAttach = em.getReference(prProductoBodegaListNewPrProductoBodegaToAttach.getClass(), prProductoBodegaListNewPrProductoBodegaToAttach.getPrProductoBodegaPK());
                attachedPrProductoBodegaListNew.add(prProductoBodegaListNewPrProductoBodegaToAttach);
            }
            prProductoBodegaListNew = attachedPrProductoBodegaListNew;
            prProductos.setPrProductoBodegaList(prProductoBodegaListNew);
            prProductos = em.merge(prProductos);
            if (seEmpresaOld != null && !seEmpresaOld.equals(seEmpresaNew)) {
                seEmpresaOld.getPrProductosList().remove(prProductos);
                seEmpresaOld = em.merge(seEmpresaOld);
            }
            if (seEmpresaNew != null && !seEmpresaNew.equals(seEmpresaOld)) {
                seEmpresaNew.getPrProductosList().add(prProductos);
                seEmpresaNew = em.merge(seEmpresaNew);
            }
            if (prMedidasOld != null && !prMedidasOld.equals(prMedidasNew)) {
                prMedidasOld.getPrProductosList().remove(prProductos);
                prMedidasOld = em.merge(prMedidasOld);
            }
            if (prMedidasNew != null && !prMedidasNew.equals(prMedidasOld)) {
                prMedidasNew.getPrProductosList().add(prProductos);
                prMedidasNew = em.merge(prMedidasNew);
            }
            if (prTipoMedidasOld != null && !prTipoMedidasOld.equals(prTipoMedidasNew)) {
                prTipoMedidasOld.getPrProductosList().remove(prProductos);
                prTipoMedidasOld = em.merge(prTipoMedidasOld);
            }
            if (prTipoMedidasNew != null && !prTipoMedidasNew.equals(prTipoMedidasOld)) {
                prTipoMedidasNew.getPrProductosList().add(prProductos);
                prTipoMedidasNew = em.merge(prTipoMedidasNew);
            }
            if (prTipoPresentacionOld != null && !prTipoPresentacionOld.equals(prTipoPresentacionNew)) {
                prTipoPresentacionOld.getPrProductosList().remove(prProductos);
                prTipoPresentacionOld = em.merge(prTipoPresentacionOld);
            }
            if (prTipoPresentacionNew != null && !prTipoPresentacionNew.equals(prTipoPresentacionOld)) {
                prTipoPresentacionNew.getPrProductosList().add(prProductos);
                prTipoPresentacionNew = em.merge(prTipoPresentacionNew);
            }
            for (PrPrestaciones prPrestacionesListNewPrPrestaciones : prPrestacionesListNew) {
                if (!prPrestacionesListOld.contains(prPrestacionesListNewPrPrestaciones)) {
                    PrProductos oldPrProductosOfPrPrestacionesListNewPrPrestaciones = prPrestacionesListNewPrPrestaciones.getPrProductos();
                    prPrestacionesListNewPrPrestaciones.setPrProductos(prProductos);
                    prPrestacionesListNewPrPrestaciones = em.merge(prPrestacionesListNewPrPrestaciones);
                    if (oldPrProductosOfPrPrestacionesListNewPrPrestaciones != null && !oldPrProductosOfPrPrestacionesListNewPrPrestaciones.equals(prProductos)) {
                        oldPrProductosOfPrPrestacionesListNewPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListNewPrPrestaciones);
                        oldPrProductosOfPrPrestacionesListNewPrPrestaciones = em.merge(oldPrProductosOfPrPrestacionesListNewPrPrestaciones);
                    }
                }
            }
            for (PrProductoBodega prProductoBodegaListNewPrProductoBodega : prProductoBodegaListNew) {
                if (!prProductoBodegaListOld.contains(prProductoBodegaListNewPrProductoBodega)) {
                    PrProductos oldPrProductosOfPrProductoBodegaListNewPrProductoBodega = prProductoBodegaListNewPrProductoBodega.getPrProductos();
                    prProductoBodegaListNewPrProductoBodega.setPrProductos(prProductos);
                    prProductoBodegaListNewPrProductoBodega = em.merge(prProductoBodegaListNewPrProductoBodega);
                    if (oldPrProductosOfPrProductoBodegaListNewPrProductoBodega != null && !oldPrProductosOfPrProductoBodegaListNewPrProductoBodega.equals(prProductos)) {
                        oldPrProductosOfPrProductoBodegaListNewPrProductoBodega.getPrProductoBodegaList().remove(prProductoBodegaListNewPrProductoBodega);
                        oldPrProductosOfPrProductoBodegaListNewPrProductoBodega = em.merge(oldPrProductosOfPrProductoBodegaListNewPrProductoBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrProductosPK id = prProductos.getPrProductosPK();
                if (findPrProductos(id) == null) {
                    throw new NonexistentEntityException("The prProductos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrProductosPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductos prProductos;
            try {
                prProductos = em.getReference(PrProductos.class, id);
                prProductos.getPrProductosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prProductos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrPrestaciones> prPrestacionesListOrphanCheck = prProductos.getPrPrestacionesList();
            for (PrPrestaciones prPrestacionesListOrphanCheckPrPrestaciones : prPrestacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrProductos (" + prProductos + ") cannot be destroyed since the PrPrestaciones " + prPrestacionesListOrphanCheckPrPrestaciones + " in its prPrestacionesList field has a non-nullable prProductos field.");
            }
            List<PrProductoBodega> prProductoBodegaListOrphanCheck = prProductos.getPrProductoBodegaList();
            for (PrProductoBodega prProductoBodegaListOrphanCheckPrProductoBodega : prProductoBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PrProductos (" + prProductos + ") cannot be destroyed since the PrProductoBodega " + prProductoBodegaListOrphanCheckPrProductoBodega + " in its prProductoBodegaList field has a non-nullable prProductos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa seEmpresa = prProductos.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa.getPrProductosList().remove(prProductos);
                seEmpresa = em.merge(seEmpresa);
            }
            PrMedidas prMedidas = prProductos.getPrMedidas();
            if (prMedidas != null) {
                prMedidas.getPrProductosList().remove(prProductos);
                prMedidas = em.merge(prMedidas);
            }
            PrTipoMedidas prTipoMedidas = prProductos.getPrTipoMedidas();
            if (prTipoMedidas != null) {
                prTipoMedidas.getPrProductosList().remove(prProductos);
                prTipoMedidas = em.merge(prTipoMedidas);
            }
            PrTipoPresentacion prTipoPresentacion = prProductos.getPrTipoPresentacion();
            if (prTipoPresentacion != null) {
                prTipoPresentacion.getPrProductosList().remove(prProductos);
                prTipoPresentacion = em.merge(prTipoPresentacion);
            }
            em.remove(prProductos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrProductos> findPrProductosEntities() {
        return findPrProductosEntities(true, -1, -1);
    }

    public List<PrProductos> findPrProductosEntities(int maxResults, int firstResult) {
        return findPrProductosEntities(false, maxResults, firstResult);
    }

    private List<PrProductos> findPrProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrProductos.class));
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

    public PrProductos findPrProductos(PrProductosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrProductos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrProductos> rt = cq.from(PrProductos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}