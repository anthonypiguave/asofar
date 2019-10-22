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
import ec.com.asofar.dto.SeOpcionesMenu;
import ec.com.asofar.dto.SeOpcionesRoles;
import ec.com.asofar.dto.SeOpcionesRolesPK;
import ec.com.asofar.dto.SeRoles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class SeOpcionesRolesJpaController implements Serializable {

    public SeOpcionesRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeOpcionesRoles seOpcionesRoles) throws PreexistingEntityException, Exception {
        if (seOpcionesRoles.getSeOpcionesRolesPK() == null) {
            seOpcionesRoles.setSeOpcionesRolesPK(new SeOpcionesRolesPK());
        }
        seOpcionesRoles.getSeOpcionesRolesPK().setIdOpcionesMenu(seOpcionesRoles.getSeOpcionesMenu().getIdOpcionesMenu());
        seOpcionesRoles.getSeOpcionesRolesPK().setIdRol(seOpcionesRoles.getSeRoles().getIdRoles());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeOpcionesMenu seOpcionesMenu = seOpcionesRoles.getSeOpcionesMenu();
            if (seOpcionesMenu != null) {
                seOpcionesMenu = em.getReference(seOpcionesMenu.getClass(), seOpcionesMenu.getIdOpcionesMenu());
                seOpcionesRoles.setSeOpcionesMenu(seOpcionesMenu);
            }
            SeRoles seRoles = seOpcionesRoles.getSeRoles();
            if (seRoles != null) {
                seRoles = em.getReference(seRoles.getClass(), seRoles.getIdRoles());
                seOpcionesRoles.setSeRoles(seRoles);
            }
            em.persist(seOpcionesRoles);
            if (seOpcionesMenu != null) {
                seOpcionesMenu.getSeOpcionesRolesList().add(seOpcionesRoles);
                seOpcionesMenu = em.merge(seOpcionesMenu);
            }
            if (seRoles != null) {
                seRoles.getSeOpcionesRolesList().add(seOpcionesRoles);
                seRoles = em.merge(seRoles);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeOpcionesRoles(seOpcionesRoles.getSeOpcionesRolesPK()) != null) {
                throw new PreexistingEntityException("SeOpcionesRoles " + seOpcionesRoles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeOpcionesRoles seOpcionesRoles) throws NonexistentEntityException, Exception {
        seOpcionesRoles.getSeOpcionesRolesPK().setIdOpcionesMenu(seOpcionesRoles.getSeOpcionesMenu().getIdOpcionesMenu());
        seOpcionesRoles.getSeOpcionesRolesPK().setIdRol(seOpcionesRoles.getSeRoles().getIdRoles());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeOpcionesRoles persistentSeOpcionesRoles = em.find(SeOpcionesRoles.class, seOpcionesRoles.getSeOpcionesRolesPK());
            SeOpcionesMenu seOpcionesMenuOld = persistentSeOpcionesRoles.getSeOpcionesMenu();
            SeOpcionesMenu seOpcionesMenuNew = seOpcionesRoles.getSeOpcionesMenu();
            SeRoles seRolesOld = persistentSeOpcionesRoles.getSeRoles();
            SeRoles seRolesNew = seOpcionesRoles.getSeRoles();
            if (seOpcionesMenuNew != null) {
                seOpcionesMenuNew = em.getReference(seOpcionesMenuNew.getClass(), seOpcionesMenuNew.getIdOpcionesMenu());
                seOpcionesRoles.setSeOpcionesMenu(seOpcionesMenuNew);
            }
            if (seRolesNew != null) {
                seRolesNew = em.getReference(seRolesNew.getClass(), seRolesNew.getIdRoles());
                seOpcionesRoles.setSeRoles(seRolesNew);
            }
            seOpcionesRoles = em.merge(seOpcionesRoles);
            if (seOpcionesMenuOld != null && !seOpcionesMenuOld.equals(seOpcionesMenuNew)) {
                seOpcionesMenuOld.getSeOpcionesRolesList().remove(seOpcionesRoles);
                seOpcionesMenuOld = em.merge(seOpcionesMenuOld);
            }
            if (seOpcionesMenuNew != null && !seOpcionesMenuNew.equals(seOpcionesMenuOld)) {
                seOpcionesMenuNew.getSeOpcionesRolesList().add(seOpcionesRoles);
                seOpcionesMenuNew = em.merge(seOpcionesMenuNew);
            }
            if (seRolesOld != null && !seRolesOld.equals(seRolesNew)) {
                seRolesOld.getSeOpcionesRolesList().remove(seOpcionesRoles);
                seRolesOld = em.merge(seRolesOld);
            }
            if (seRolesNew != null && !seRolesNew.equals(seRolesOld)) {
                seRolesNew.getSeOpcionesRolesList().add(seOpcionesRoles);
                seRolesNew = em.merge(seRolesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SeOpcionesRolesPK id = seOpcionesRoles.getSeOpcionesRolesPK();
                if (findSeOpcionesRoles(id) == null) {
                    throw new NonexistentEntityException("The seOpcionesRoles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SeOpcionesRolesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeOpcionesRoles seOpcionesRoles;
            try {
                seOpcionesRoles = em.getReference(SeOpcionesRoles.class, id);
                seOpcionesRoles.getSeOpcionesRolesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seOpcionesRoles with id " + id + " no longer exists.", enfe);
            }
            SeOpcionesMenu seOpcionesMenu = seOpcionesRoles.getSeOpcionesMenu();
            if (seOpcionesMenu != null) {
                seOpcionesMenu.getSeOpcionesRolesList().remove(seOpcionesRoles);
                seOpcionesMenu = em.merge(seOpcionesMenu);
            }
            SeRoles seRoles = seOpcionesRoles.getSeRoles();
            if (seRoles != null) {
                seRoles.getSeOpcionesRolesList().remove(seOpcionesRoles);
                seRoles = em.merge(seRoles);
            }
            em.remove(seOpcionesRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeOpcionesRoles> findSeOpcionesRolesEntities() {
        return findSeOpcionesRolesEntities(true, -1, -1);
    }

    public List<SeOpcionesRoles> findSeOpcionesRolesEntities(int maxResults, int firstResult) {
        return findSeOpcionesRolesEntities(false, maxResults, firstResult);
    }

    private List<SeOpcionesRoles> findSeOpcionesRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeOpcionesRoles.class));
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

    public SeOpcionesRoles findSeOpcionesRoles(SeOpcionesRolesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeOpcionesRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeOpcionesRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeOpcionesRoles> rt = cq.from(SeOpcionesRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
