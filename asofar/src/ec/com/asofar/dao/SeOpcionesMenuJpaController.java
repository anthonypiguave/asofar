/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.SeOpcionesMenu;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeOpcionesRoles;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class SeOpcionesMenuJpaController implements Serializable {

    public SeOpcionesMenuJpaController() {
        this.emf = Persistence.createEntityManagerFactory("asofarPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeOpcionesMenu seOpcionesMenu) {
        if (seOpcionesMenu.getSeOpcionesRolesList() == null) {
            seOpcionesMenu.setSeOpcionesRolesList(new ArrayList<SeOpcionesRoles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SeOpcionesRoles> attachedSeOpcionesRolesList = new ArrayList<SeOpcionesRoles>();
            for (SeOpcionesRoles seOpcionesRolesListSeOpcionesRolesToAttach : seOpcionesMenu.getSeOpcionesRolesList()) {
                seOpcionesRolesListSeOpcionesRolesToAttach = em.getReference(seOpcionesRolesListSeOpcionesRolesToAttach.getClass(), seOpcionesRolesListSeOpcionesRolesToAttach.getSeOpcionesRolesPK());
                attachedSeOpcionesRolesList.add(seOpcionesRolesListSeOpcionesRolesToAttach);
            }
            seOpcionesMenu.setSeOpcionesRolesList(attachedSeOpcionesRolesList);
            em.persist(seOpcionesMenu);
            for (SeOpcionesRoles seOpcionesRolesListSeOpcionesRoles : seOpcionesMenu.getSeOpcionesRolesList()) {
                SeOpcionesMenu oldSeOpcionesMenuOfSeOpcionesRolesListSeOpcionesRoles = seOpcionesRolesListSeOpcionesRoles.getSeOpcionesMenu();
                seOpcionesRolesListSeOpcionesRoles.setSeOpcionesMenu(seOpcionesMenu);
                seOpcionesRolesListSeOpcionesRoles = em.merge(seOpcionesRolesListSeOpcionesRoles);
                if (oldSeOpcionesMenuOfSeOpcionesRolesListSeOpcionesRoles != null) {
                    oldSeOpcionesMenuOfSeOpcionesRolesListSeOpcionesRoles.getSeOpcionesRolesList().remove(seOpcionesRolesListSeOpcionesRoles);
                    oldSeOpcionesMenuOfSeOpcionesRolesListSeOpcionesRoles = em.merge(oldSeOpcionesMenuOfSeOpcionesRolesListSeOpcionesRoles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeOpcionesMenu seOpcionesMenu) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeOpcionesMenu persistentSeOpcionesMenu = em.find(SeOpcionesMenu.class, seOpcionesMenu.getIdOpcionesMenu());
            List<SeOpcionesRoles> seOpcionesRolesListOld = persistentSeOpcionesMenu.getSeOpcionesRolesList();
            List<SeOpcionesRoles> seOpcionesRolesListNew = seOpcionesMenu.getSeOpcionesRolesList();
            List<String> illegalOrphanMessages = null;
            for (SeOpcionesRoles seOpcionesRolesListOldSeOpcionesRoles : seOpcionesRolesListOld) {
                if (!seOpcionesRolesListNew.contains(seOpcionesRolesListOldSeOpcionesRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeOpcionesRoles " + seOpcionesRolesListOldSeOpcionesRoles + " since its seOpcionesMenu field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SeOpcionesRoles> attachedSeOpcionesRolesListNew = new ArrayList<SeOpcionesRoles>();
            for (SeOpcionesRoles seOpcionesRolesListNewSeOpcionesRolesToAttach : seOpcionesRolesListNew) {
                seOpcionesRolesListNewSeOpcionesRolesToAttach = em.getReference(seOpcionesRolesListNewSeOpcionesRolesToAttach.getClass(), seOpcionesRolesListNewSeOpcionesRolesToAttach.getSeOpcionesRolesPK());
                attachedSeOpcionesRolesListNew.add(seOpcionesRolesListNewSeOpcionesRolesToAttach);
            }
            seOpcionesRolesListNew = attachedSeOpcionesRolesListNew;
            seOpcionesMenu.setSeOpcionesRolesList(seOpcionesRolesListNew);
            seOpcionesMenu = em.merge(seOpcionesMenu);
            for (SeOpcionesRoles seOpcionesRolesListNewSeOpcionesRoles : seOpcionesRolesListNew) {
                if (!seOpcionesRolesListOld.contains(seOpcionesRolesListNewSeOpcionesRoles)) {
                    SeOpcionesMenu oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles = seOpcionesRolesListNewSeOpcionesRoles.getSeOpcionesMenu();
                    seOpcionesRolesListNewSeOpcionesRoles.setSeOpcionesMenu(seOpcionesMenu);
                    seOpcionesRolesListNewSeOpcionesRoles = em.merge(seOpcionesRolesListNewSeOpcionesRoles);
                    if (oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles != null && !oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles.equals(seOpcionesMenu)) {
                        oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles.getSeOpcionesRolesList().remove(seOpcionesRolesListNewSeOpcionesRoles);
                        oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles = em.merge(oldSeOpcionesMenuOfSeOpcionesRolesListNewSeOpcionesRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seOpcionesMenu.getIdOpcionesMenu();
                if (findSeOpcionesMenu(id) == null) {
                    throw new NonexistentEntityException("The seOpcionesMenu with id " + id + " no longer exists.");
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
            SeOpcionesMenu seOpcionesMenu;
            try {
                seOpcionesMenu = em.getReference(SeOpcionesMenu.class, id);
                seOpcionesMenu.getIdOpcionesMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seOpcionesMenu with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SeOpcionesRoles> seOpcionesRolesListOrphanCheck = seOpcionesMenu.getSeOpcionesRolesList();
            for (SeOpcionesRoles seOpcionesRolesListOrphanCheckSeOpcionesRoles : seOpcionesRolesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeOpcionesMenu (" + seOpcionesMenu + ") cannot be destroyed since the SeOpcionesRoles " + seOpcionesRolesListOrphanCheckSeOpcionesRoles + " in its seOpcionesRolesList field has a non-nullable seOpcionesMenu field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(seOpcionesMenu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeOpcionesMenu> findSeOpcionesMenuEntities() {
        return findSeOpcionesMenuEntities(true, -1, -1);
    }

    public List<SeOpcionesMenu> findSeOpcionesMenuEntities(int maxResults, int firstResult) {
        return findSeOpcionesMenuEntities(false, maxResults, firstResult);
    }

    private List<SeOpcionesMenu> findSeOpcionesMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeOpcionesMenu.class));
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

    public SeOpcionesMenu findSeOpcionesMenu(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeOpcionesMenu.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeOpcionesMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeOpcionesMenu> rt = cq.from(SeOpcionesMenu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
