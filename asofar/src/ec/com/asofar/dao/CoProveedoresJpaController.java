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
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoProveedores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
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
        if (coProveedores.getCoCotizacionesPorProveedorList() == null) {
            coProveedores.setCoCotizacionesPorProveedorList(new ArrayList<CoCotizacionesPorProveedor>());
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
            List<CoCotizacionesPorProveedor> attachedCoCotizacionesPorProveedorList = new ArrayList<CoCotizacionesPorProveedor>();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach : coProveedores.getCoCotizacionesPorProveedorList()) {
                coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach = em.getReference(coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach.getClass(), coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach.getCoCotizacionesPorProveedorPK());
                attachedCoCotizacionesPorProveedorList.add(coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach);
            }
            coProveedores.setCoCotizacionesPorProveedorList(attachedCoCotizacionesPorProveedorList);
            em.persist(coProveedores);
            if (tipoPersona != null) {
                tipoPersona.getCoProveedoresList().add(coProveedores);
                tipoPersona = em.merge(tipoPersona);
            }
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListCoCotizacionesPorProveedor : coProveedores.getCoCotizacionesPorProveedorList()) {
                CoProveedores oldIdProveedorOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor = coCotizacionesPorProveedorListCoCotizacionesPorProveedor.getIdProveedor();
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor.setIdProveedor(coProveedores);
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListCoCotizacionesPorProveedor);
                if (oldIdProveedorOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor != null) {
                    oldIdProveedorOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedorListCoCotizacionesPorProveedor);
                    oldIdProveedorOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor = em.merge(oldIdProveedorOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor);
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
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorListOld = persistentCoProveedores.getCoCotizacionesPorProveedorList();
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorListNew = coProveedores.getCoCotizacionesPorProveedorList();
            if (tipoPersonaNew != null) {
                tipoPersonaNew = em.getReference(tipoPersonaNew.getClass(), tipoPersonaNew.getIdTipoPersona());
                coProveedores.setTipoPersona(tipoPersonaNew);
            }
            List<CoCotizacionesPorProveedor> attachedCoCotizacionesPorProveedorListNew = new ArrayList<CoCotizacionesPorProveedor>();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach : coCotizacionesPorProveedorListNew) {
                coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach = em.getReference(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach.getClass(), coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach.getCoCotizacionesPorProveedorPK());
                attachedCoCotizacionesPorProveedorListNew.add(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach);
            }
            coCotizacionesPorProveedorListNew = attachedCoCotizacionesPorProveedorListNew;
            coProveedores.setCoCotizacionesPorProveedorList(coCotizacionesPorProveedorListNew);
            coProveedores = em.merge(coProveedores);
            if (tipoPersonaOld != null && !tipoPersonaOld.equals(tipoPersonaNew)) {
                tipoPersonaOld.getCoProveedoresList().remove(coProveedores);
                tipoPersonaOld = em.merge(tipoPersonaOld);
            }
            if (tipoPersonaNew != null && !tipoPersonaNew.equals(tipoPersonaOld)) {
                tipoPersonaNew.getCoProveedoresList().add(coProveedores);
                tipoPersonaNew = em.merge(tipoPersonaNew);
            }
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor : coCotizacionesPorProveedorListOld) {
                if (!coCotizacionesPorProveedorListNew.contains(coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor)) {
                    coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor.setIdProveedor(null);
                    coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor);
                }
            }
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor : coCotizacionesPorProveedorListNew) {
                if (!coCotizacionesPorProveedorListOld.contains(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor)) {
                    CoProveedores oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.getIdProveedor();
                    coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.setIdProveedor(coProveedores);
                    coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
                    if (oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor != null && !oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.equals(coProveedores)) {
                        oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
                        oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = em.merge(oldIdProveedorOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
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
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorList = coProveedores.getCoCotizacionesPorProveedorList();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListCoCotizacionesPorProveedor : coCotizacionesPorProveedorList) {
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor.setIdProveedor(null);
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListCoCotizacionesPorProveedor);
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
