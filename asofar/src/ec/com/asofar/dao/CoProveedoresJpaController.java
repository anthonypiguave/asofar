/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoProveedores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
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
        if (coProveedores.getCoOrdenComprasList() == null) {
            coProveedores.setCoOrdenComprasList(new ArrayList<CoOrdenCompras>());
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
            List<CoOrdenCompras> attachedCoOrdenComprasList = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListCoOrdenComprasToAttach : coProveedores.getCoOrdenComprasList()) {
                coOrdenComprasListCoOrdenComprasToAttach = em.getReference(coOrdenComprasListCoOrdenComprasToAttach.getClass(), coOrdenComprasListCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasList.add(coOrdenComprasListCoOrdenComprasToAttach);
            }
            coProveedores.setCoOrdenComprasList(attachedCoOrdenComprasList);
            em.persist(coProveedores);
            if (tipoPersona != null) {
                tipoPersona.getCoProveedoresList().add(coProveedores);
                tipoPersona = em.merge(tipoPersona);
            }
            if (idPais != null) {
                idPais.getCoProveedoresList().add(coProveedores);
                idPais = em.merge(idPais);
            }
            for (CoOrdenCompras coOrdenComprasListCoOrdenCompras : coProveedores.getCoOrdenComprasList()) {
                CoProveedores oldIdProveedorOfCoOrdenComprasListCoOrdenCompras = coOrdenComprasListCoOrdenCompras.getIdProveedor();
                coOrdenComprasListCoOrdenCompras.setIdProveedor(coProveedores);
                coOrdenComprasListCoOrdenCompras = em.merge(coOrdenComprasListCoOrdenCompras);
                if (oldIdProveedorOfCoOrdenComprasListCoOrdenCompras != null) {
                    oldIdProveedorOfCoOrdenComprasListCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListCoOrdenCompras);
                    oldIdProveedorOfCoOrdenComprasListCoOrdenCompras = em.merge(oldIdProveedorOfCoOrdenComprasListCoOrdenCompras);
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
            List<CoOrdenCompras> coOrdenComprasListOld = persistentCoProveedores.getCoOrdenComprasList();
            List<CoOrdenCompras> coOrdenComprasListNew = coProveedores.getCoOrdenComprasList();
            if (tipoPersonaNew != null) {
                tipoPersonaNew = em.getReference(tipoPersonaNew.getClass(), tipoPersonaNew.getIdTipoPersona());
                coProveedores.setTipoPersona(tipoPersonaNew);
            }
            if (idPaisNew != null) {
                idPaisNew = em.getReference(idPaisNew.getClass(), idPaisNew.getIdPais());
                coProveedores.setIdPais(idPaisNew);
            }
            List<CoOrdenCompras> attachedCoOrdenComprasListNew = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenComprasToAttach : coOrdenComprasListNew) {
                coOrdenComprasListNewCoOrdenComprasToAttach = em.getReference(coOrdenComprasListNewCoOrdenComprasToAttach.getClass(), coOrdenComprasListNewCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasListNew.add(coOrdenComprasListNewCoOrdenComprasToAttach);
            }
            coOrdenComprasListNew = attachedCoOrdenComprasListNew;
            coProveedores.setCoOrdenComprasList(coOrdenComprasListNew);
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
            for (CoOrdenCompras coOrdenComprasListOldCoOrdenCompras : coOrdenComprasListOld) {
                if (!coOrdenComprasListNew.contains(coOrdenComprasListOldCoOrdenCompras)) {
                    coOrdenComprasListOldCoOrdenCompras.setIdProveedor(null);
                    coOrdenComprasListOldCoOrdenCompras = em.merge(coOrdenComprasListOldCoOrdenCompras);
                }
            }
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenCompras : coOrdenComprasListNew) {
                if (!coOrdenComprasListOld.contains(coOrdenComprasListNewCoOrdenCompras)) {
                    CoProveedores oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras = coOrdenComprasListNewCoOrdenCompras.getIdProveedor();
                    coOrdenComprasListNewCoOrdenCompras.setIdProveedor(coProveedores);
                    coOrdenComprasListNewCoOrdenCompras = em.merge(coOrdenComprasListNewCoOrdenCompras);
                    if (oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras != null && !oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras.equals(coProveedores)) {
                        oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListNewCoOrdenCompras);
                        oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras = em.merge(oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras);
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
            List<CoOrdenCompras> coOrdenComprasList = coProveedores.getCoOrdenComprasList();
            for (CoOrdenCompras coOrdenComprasListCoOrdenCompras : coOrdenComprasList) {
                coOrdenComprasListCoOrdenCompras.setIdProveedor(null);
                coOrdenComprasListCoOrdenCompras = em.merge(coOrdenComprasListCoOrdenCompras);
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
