/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeRoles;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import ec.com.asofar.dto.SeUsuarioSucurRolPK;
import ec.com.asofar.dto.SeUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
 */
public class SeUsuarioSucurRolJpaController implements Serializable {

    public SeUsuarioSucurRolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeUsuarioSucurRol seUsuarioSucurRol) throws PreexistingEntityException, Exception {
        if (seUsuarioSucurRol.getSeUsuarioSucurRolPK() == null) {
            seUsuarioSucurRol.setSeUsuarioSucurRolPK(new SeUsuarioSucurRolPK());
        }
        seUsuarioSucurRol.getSeUsuarioSucurRolPK().setIdEmpresa(seUsuarioSucurRol.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        seUsuarioSucurRol.getSeUsuarioSucurRolPK().setIdSucursal(seUsuarioSucurRol.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeRoles idRoles = seUsuarioSucurRol.getIdRoles();
            if (idRoles != null) {
                idRoles = em.getReference(idRoles.getClass(), idRoles.getIdRoles());
                seUsuarioSucurRol.setIdRoles(idRoles);
            }
            SeSucursal seSucursal = seUsuarioSucurRol.getSeSucursal();
            if (seSucursal != null) {
                seSucursal = em.getReference(seSucursal.getClass(), seSucursal.getSeSucursalPK());
                seUsuarioSucurRol.setSeSucursal(seSucursal);
            }
            SeUsuarios idUsuario = seUsuarioSucurRol.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                seUsuarioSucurRol.setIdUsuario(idUsuario);
            }
            em.persist(seUsuarioSucurRol);
            if (idRoles != null) {
                idRoles.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                idRoles = em.merge(idRoles);
            }
            if (seSucursal != null) {
                seSucursal.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                seSucursal = em.merge(seSucursal);
            }
            if (idUsuario != null) {
                idUsuario.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeUsuarioSucurRol(seUsuarioSucurRol.getSeUsuarioSucurRolPK()) != null) {
                throw new PreexistingEntityException("SeUsuarioSucurRol " + seUsuarioSucurRol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeUsuarioSucurRol seUsuarioSucurRol) throws NonexistentEntityException, Exception {
        seUsuarioSucurRol.getSeUsuarioSucurRolPK().setIdEmpresa(seUsuarioSucurRol.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        seUsuarioSucurRol.getSeUsuarioSucurRolPK().setIdSucursal(seUsuarioSucurRol.getSeSucursal().getSeSucursalPK().getIdSucursal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarioSucurRol persistentSeUsuarioSucurRol = em.find(SeUsuarioSucurRol.class, seUsuarioSucurRol.getSeUsuarioSucurRolPK());
            SeRoles idRolesOld = persistentSeUsuarioSucurRol.getIdRoles();
            SeRoles idRolesNew = seUsuarioSucurRol.getIdRoles();
            SeSucursal seSucursalOld = persistentSeUsuarioSucurRol.getSeSucursal();
            SeSucursal seSucursalNew = seUsuarioSucurRol.getSeSucursal();
            SeUsuarios idUsuarioOld = persistentSeUsuarioSucurRol.getIdUsuario();
            SeUsuarios idUsuarioNew = seUsuarioSucurRol.getIdUsuario();
            if (idRolesNew != null) {
                idRolesNew = em.getReference(idRolesNew.getClass(), idRolesNew.getIdRoles());
                seUsuarioSucurRol.setIdRoles(idRolesNew);
            }
            if (seSucursalNew != null) {
                seSucursalNew = em.getReference(seSucursalNew.getClass(), seSucursalNew.getSeSucursalPK());
                seUsuarioSucurRol.setSeSucursal(seSucursalNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                seUsuarioSucurRol.setIdUsuario(idUsuarioNew);
            }
            seUsuarioSucurRol = em.merge(seUsuarioSucurRol);
            if (idRolesOld != null && !idRolesOld.equals(idRolesNew)) {
                idRolesOld.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                idRolesOld = em.merge(idRolesOld);
            }
            if (idRolesNew != null && !idRolesNew.equals(idRolesOld)) {
                idRolesNew.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                idRolesNew = em.merge(idRolesNew);
            }
            if (seSucursalOld != null && !seSucursalOld.equals(seSucursalNew)) {
                seSucursalOld.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                seSucursalOld = em.merge(seSucursalOld);
            }
            if (seSucursalNew != null && !seSucursalNew.equals(seSucursalOld)) {
                seSucursalNew.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                seSucursalNew = em.merge(seSucursalNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSeUsuarioSucurRolList().add(seUsuarioSucurRol);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SeUsuarioSucurRolPK id = seUsuarioSucurRol.getSeUsuarioSucurRolPK();
                if (findSeUsuarioSucurRol(id) == null) {
                    throw new NonexistentEntityException("The seUsuarioSucurRol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SeUsuarioSucurRolPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarioSucurRol seUsuarioSucurRol;
            try {
                seUsuarioSucurRol = em.getReference(SeUsuarioSucurRol.class, id);
                seUsuarioSucurRol.getSeUsuarioSucurRolPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seUsuarioSucurRol with id " + id + " no longer exists.", enfe);
            }
            SeRoles idRoles = seUsuarioSucurRol.getIdRoles();
            if (idRoles != null) {
                idRoles.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                idRoles = em.merge(idRoles);
            }
            SeSucursal seSucursal = seUsuarioSucurRol.getSeSucursal();
            if (seSucursal != null) {
                seSucursal.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                seSucursal = em.merge(seSucursal);
            }
            SeUsuarios idUsuario = seUsuarioSucurRol.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSeUsuarioSucurRolList().remove(seUsuarioSucurRol);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(seUsuarioSucurRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeUsuarioSucurRol> findSeUsuarioSucurRolEntities() {
        return findSeUsuarioSucurRolEntities(true, -1, -1);
    }

    public List<SeUsuarioSucurRol> findSeUsuarioSucurRolEntities(int maxResults, int firstResult) {
        return findSeUsuarioSucurRolEntities(false, maxResults, firstResult);
    }

    private List<SeUsuarioSucurRol> findSeUsuarioSucurRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeUsuarioSucurRol.class));
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

    public SeUsuarioSucurRol findSeUsuarioSucurRol(SeUsuarioSucurRolPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeUsuarioSucurRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeUsuarioSucurRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeUsuarioSucurRol> rt = cq.from(SeUsuarioSucurRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
