/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.CoProveedores;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InMovimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class CoProveedoresJpaController implements Serializable {

    public CoProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoProveedores coProveedores) {
        if (coProveedores.getPrProductosList() == null) {
            coProveedores.setPrProductosList(new ArrayList<PrProductos>());
        }
        if (coProveedores.getInMovimientosList() == null) {
            coProveedores.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeTipoPersona tipoPersona = coProveedores.getTipoPersona();
            if (tipoPersona != null) {
                tipoPersona = em.getReference(tipoPersona.getClass(), tipoPersona.getIdTipoPersona());
                coProveedores.setTipoPersona(tipoPersona);
            }
            SePais idPais = coProveedores.getIdPais();
            if (idPais != null) {
                idPais = em.getReference(idPais.getClass(), idPais.getIdPais());
                coProveedores.setIdPais(idPais);
            }
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : coProveedores.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            coProveedores.setPrProductosList(attachedPrProductosList);
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : coProveedores.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            coProveedores.setInMovimientosList(attachedInMovimientosList);
            em.persist(coProveedores);
            if (tipoPersona != null) {
                tipoPersona.getCoProveedoresList().add(coProveedores);
                tipoPersona = em.merge(tipoPersona);
            }
            if (idPais != null) {
                idPais.getCoProveedoresList().add(coProveedores);
                idPais = em.merge(idPais);
            }
            for (PrProductos prProductosListPrProductos : coProveedores.getPrProductosList()) {
                CoProveedores oldIdProveedorOfPrProductosListPrProductos = prProductosListPrProductos.getIdProveedor();
                prProductosListPrProductos.setIdProveedor(coProveedores);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldIdProveedorOfPrProductosListPrProductos != null) {
                    oldIdProveedorOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldIdProveedorOfPrProductosListPrProductos = em.merge(oldIdProveedorOfPrProductosListPrProductos);
                }
            }
            for (InMovimientos inMovimientosListInMovimientos : coProveedores.getInMovimientosList()) {
                CoProveedores oldIdProveedorOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getIdProveedor();
                inMovimientosListInMovimientos.setIdProveedor(coProveedores);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldIdProveedorOfInMovimientosListInMovimientos != null) {
                    oldIdProveedorOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldIdProveedorOfInMovimientosListInMovimientos = em.merge(oldIdProveedorOfInMovimientosListInMovimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoProveedores coProveedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoProveedores persistentCoProveedores = em.find(CoProveedores.class, coProveedores.getIdProveedor());
            SeTipoPersona tipoPersonaOld = persistentCoProveedores.getTipoPersona();
            SeTipoPersona tipoPersonaNew = coProveedores.getTipoPersona();
            SePais idPaisOld = persistentCoProveedores.getIdPais();
            SePais idPaisNew = coProveedores.getIdPais();
            List<PrProductos> prProductosListOld = persistentCoProveedores.getPrProductosList();
            List<PrProductos> prProductosListNew = coProveedores.getPrProductosList();
            List<InMovimientos> inMovimientosListOld = persistentCoProveedores.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = coProveedores.getInMovimientosList();
            if (tipoPersonaNew != null) {
                tipoPersonaNew = em.getReference(tipoPersonaNew.getClass(), tipoPersonaNew.getIdTipoPersona());
                coProveedores.setTipoPersona(tipoPersonaNew);
            }
            if (idPaisNew != null) {
                idPaisNew = em.getReference(idPaisNew.getClass(), idPaisNew.getIdPais());
                coProveedores.setIdPais(idPaisNew);
            }
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            coProveedores.setPrProductosList(prProductosListNew);
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            coProveedores.setInMovimientosList(inMovimientosListNew);
            coProveedores = em.merge(coProveedores);
            if (tipoPersonaOld != null && !tipoPersonaOld.equals(tipoPersonaNew)) {
                tipoPersonaOld.getCoProveedoresList().remove(coProveedores);
                tipoPersonaOld = em.merge(tipoPersonaOld);
            }
            if (tipoPersonaNew != null && !tipoPersonaNew.equals(tipoPersonaOld)) {
                tipoPersonaNew.getCoProveedoresList().add(coProveedores);
                tipoPersonaNew = em.merge(tipoPersonaNew);
            }
            if (idPaisOld != null && !idPaisOld.equals(idPaisNew)) {
                idPaisOld.getCoProveedoresList().remove(coProveedores);
                idPaisOld = em.merge(idPaisOld);
            }
            if (idPaisNew != null && !idPaisNew.equals(idPaisOld)) {
                idPaisNew.getCoProveedoresList().add(coProveedores);
                idPaisNew = em.merge(idPaisNew);
            }
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    prProductosListOldPrProductos.setIdProveedor(null);
                    prProductosListOldPrProductos = em.merge(prProductosListOldPrProductos);
                }
            }
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    CoProveedores oldIdProveedorOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getIdProveedor();
                    prProductosListNewPrProductos.setIdProveedor(coProveedores);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldIdProveedorOfPrProductosListNewPrProductos != null && !oldIdProveedorOfPrProductosListNewPrProductos.equals(coProveedores)) {
                        oldIdProveedorOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldIdProveedorOfPrProductosListNewPrProductos = em.merge(oldIdProveedorOfPrProductosListNewPrProductos);
                    }
                }
            }
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    inMovimientosListOldInMovimientos.setIdProveedor(null);
                    inMovimientosListOldInMovimientos = em.merge(inMovimientosListOldInMovimientos);
                }
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    CoProveedores oldIdProveedorOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getIdProveedor();
                    inMovimientosListNewInMovimientos.setIdProveedor(coProveedores);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldIdProveedorOfInMovimientosListNewInMovimientos != null && !oldIdProveedorOfInMovimientosListNewInMovimientos.equals(coProveedores)) {
                        oldIdProveedorOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldIdProveedorOfInMovimientosListNewInMovimientos = em.merge(oldIdProveedorOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = coProveedores.getIdProveedor();
                if (findCoProveedores(id) == null) {
                    throw new NonexistentEntityException("The coProveedores with id " + id + " no longer exists.");
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
            CoProveedores coProveedores;
            try {
                coProveedores = em.getReference(CoProveedores.class, id);
                coProveedores.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coProveedores with id " + id + " no longer exists.", enfe);
            }
            SeTipoPersona tipoPersona = coProveedores.getTipoPersona();
            if (tipoPersona != null) {
                tipoPersona.getCoProveedoresList().remove(coProveedores);
                tipoPersona = em.merge(tipoPersona);
            }
            SePais idPais = coProveedores.getIdPais();
            if (idPais != null) {
                idPais.getCoProveedoresList().remove(coProveedores);
                idPais = em.merge(idPais);
            }
            List<PrProductos> prProductosList = coProveedores.getPrProductosList();
            for (PrProductos prProductosListPrProductos : prProductosList) {
                prProductosListPrProductos.setIdProveedor(null);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
            }
            List<InMovimientos> inMovimientosList = coProveedores.getInMovimientosList();
            for (InMovimientos inMovimientosListInMovimientos : inMovimientosList) {
                inMovimientosListInMovimientos.setIdProveedor(null);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
            }
            em.remove(coProveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoProveedores> findCoProveedoresEntities() {
        return findCoProveedoresEntities(true, -1, -1);
    }

    public List<CoProveedores> findCoProveedoresEntities(int maxResults, int firstResult) {
        return findCoProveedoresEntities(false, maxResults, firstResult);
    }

    private List<CoProveedores> findCoProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoProveedores.class));
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

    public CoProveedores findCoProveedores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoProveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoProveedores> rt = cq.from(CoProveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
