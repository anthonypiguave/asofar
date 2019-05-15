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
import ec.com.asofar.dto.CoCotizacionesPorPorveedor;
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
        if (coProveedores.getCoCotizacionesPorPorveedorList() == null) {
            coProveedores.setCoCotizacionesPorPorveedorList(new ArrayList<CoCotizacionesPorPorveedor>());
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
            List<CoCotizacionesPorPorveedor> attachedCoCotizacionesPorPorveedorList = new ArrayList<CoCotizacionesPorPorveedor>();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach : coProveedores.getCoCotizacionesPorPorveedorList()) {
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach = em.getReference(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach.getClass(), coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach.getCoCotizacionesPorPorveedorPK());
                attachedCoCotizacionesPorPorveedorList.add(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach);
            }
            coProveedores.setCoCotizacionesPorPorveedorList(attachedCoCotizacionesPorPorveedorList);
            em.persist(coProveedores);
            if (tipoPersona != null) {
                tipoPersona.getCoProveedoresList().add(coProveedores);
                tipoPersona = em.merge(tipoPersona);
            }
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor : coProveedores.getCoCotizacionesPorPorveedorList()) {
                CoProveedores oldIdProveedorOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.getIdProveedor();
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.setIdProveedor(coProveedores);
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
                if (oldIdProveedorOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor != null) {
                    oldIdProveedorOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
                    oldIdProveedorOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = em.merge(oldIdProveedorOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
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
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorListOld = persistentCoProveedores.getCoCotizacionesPorPorveedorList();
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorListNew = coProveedores.getCoCotizacionesPorPorveedorList();
            if (tipoPersonaNew != null) {
                tipoPersonaNew = em.getReference(tipoPersonaNew.getClass(), tipoPersonaNew.getIdTipoPersona());
                coProveedores.setTipoPersona(tipoPersonaNew);
            }
            List<CoCotizacionesPorPorveedor> attachedCoCotizacionesPorPorveedorListNew = new ArrayList<CoCotizacionesPorPorveedor>();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach : coCotizacionesPorPorveedorListNew) {
                coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach = em.getReference(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach.getClass(), coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach.getCoCotizacionesPorPorveedorPK());
                attachedCoCotizacionesPorPorveedorListNew.add(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach);
            }
            coCotizacionesPorPorveedorListNew = attachedCoCotizacionesPorPorveedorListNew;
            coProveedores.setCoCotizacionesPorPorveedorList(coCotizacionesPorPorveedorListNew);
            coProveedores = em.merge(coProveedores);
            if (tipoPersonaOld != null && !tipoPersonaOld.equals(tipoPersonaNew)) {
                tipoPersonaOld.getCoProveedoresList().remove(coProveedores);
                tipoPersonaOld = em.merge(tipoPersonaOld);
            }
            if (tipoPersonaNew != null && !tipoPersonaNew.equals(tipoPersonaOld)) {
                tipoPersonaNew.getCoProveedoresList().add(coProveedores);
                tipoPersonaNew = em.merge(tipoPersonaNew);
            }
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorListOld) {
                if (!coCotizacionesPorPorveedorListNew.contains(coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor)) {
                    coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor.setIdProveedor(null);
                    coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor);
                }
            }
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorListNew) {
                if (!coCotizacionesPorPorveedorListOld.contains(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor)) {
                    CoProveedores oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.getIdProveedor();
                    coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.setIdProveedor(coProveedores);
                    coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
                    if (oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor != null && !oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.equals(coProveedores)) {
                        oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
                        oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = em.merge(oldIdProveedorOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
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
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorList = coProveedores.getCoCotizacionesPorPorveedorList();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorList) {
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.setIdProveedor(null);
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
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
