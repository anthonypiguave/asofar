/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeOpcionesRoles;
import ec.com.asofar.dto.SeRoles;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin
 */
public class SeRolesJpaController implements Serializable {

    public SeRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeRoles seRoles) {
        if (seRoles.getSeOpcionesRolesList() == null) {
            seRoles.setSeOpcionesRolesList(new ArrayList<SeOpcionesRoles>());
        }
        if (seRoles.getSeUsuarioSucurRolList() == null) {
            seRoles.setSeUsuarioSucurRolList(new ArrayList<SeUsuarioSucurRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SeOpcionesRoles> attachedSeOpcionesRolesList = new ArrayList<SeOpcionesRoles>();
            for (SeOpcionesRoles seOpcionesRolesListSeOpcionesRolesToAttach : seRoles.getSeOpcionesRolesList()) {
                seOpcionesRolesListSeOpcionesRolesToAttach = em.getReference(seOpcionesRolesListSeOpcionesRolesToAttach.getClass(), seOpcionesRolesListSeOpcionesRolesToAttach.getSeOpcionesRolesPK());
                attachedSeOpcionesRolesList.add(seOpcionesRolesListSeOpcionesRolesToAttach);
            }
            seRoles.setSeOpcionesRolesList(attachedSeOpcionesRolesList);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolList = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRolToAttach : seRoles.getSeUsuarioSucurRolList()) {
                seUsuarioSucurRolListSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolList.add(seUsuarioSucurRolListSeUsuarioSucurRolToAttach);
            }
            seRoles.setSeUsuarioSucurRolList(attachedSeUsuarioSucurRolList);
            em.persist(seRoles);
            for (SeOpcionesRoles seOpcionesRolesListSeOpcionesRoles : seRoles.getSeOpcionesRolesList()) {
                SeRoles oldSeRolesOfSeOpcionesRolesListSeOpcionesRoles = seOpcionesRolesListSeOpcionesRoles.getSeRoles();
                seOpcionesRolesListSeOpcionesRoles.setSeRoles(seRoles);
                seOpcionesRolesListSeOpcionesRoles = em.merge(seOpcionesRolesListSeOpcionesRoles);
                if (oldSeRolesOfSeOpcionesRolesListSeOpcionesRoles != null) {
                    oldSeRolesOfSeOpcionesRolesListSeOpcionesRoles.getSeOpcionesRolesList().remove(seOpcionesRolesListSeOpcionesRoles);
                    oldSeRolesOfSeOpcionesRolesListSeOpcionesRoles = em.merge(oldSeRolesOfSeOpcionesRolesListSeOpcionesRoles);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seRoles.getSeUsuarioSucurRolList()) {
                SeRoles oldIdRolesOfSeUsuarioSucurRolListSeUsuarioSucurRol = seUsuarioSucurRolListSeUsuarioSucurRol.getIdRoles();
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdRoles(seRoles);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
                if (oldIdRolesOfSeUsuarioSucurRolListSeUsuarioSucurRol != null) {
                    oldIdRolesOfSeUsuarioSucurRolListSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListSeUsuarioSucurRol);
                    oldIdRolesOfSeUsuarioSucurRolListSeUsuarioSucurRol = em.merge(oldIdRolesOfSeUsuarioSucurRolListSeUsuarioSucurRol);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeRoles seRoles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeRoles persistentSeRoles = em.find(SeRoles.class, seRoles.getIdRoles());
            List<SeOpcionesRoles> seOpcionesRolesListOld = persistentSeRoles.getSeOpcionesRolesList();
            List<SeOpcionesRoles> seOpcionesRolesListNew = seRoles.getSeOpcionesRolesList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOld = persistentSeRoles.getSeUsuarioSucurRolList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListNew = seRoles.getSeUsuarioSucurRolList();
            List<String> illegalOrphanMessages = null;
            for (SeOpcionesRoles seOpcionesRolesListOldSeOpcionesRoles : seOpcionesRolesListOld) {
                if (!seOpcionesRolesListNew.contains(seOpcionesRolesListOldSeOpcionesRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeOpcionesRoles " + seOpcionesRolesListOldSeOpcionesRoles + " since its seRoles field is not nullable.");
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
            seRoles.setSeOpcionesRolesList(seOpcionesRolesListNew);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolListNew = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach : seUsuarioSucurRolListNew) {
                seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolListNew.add(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach);
            }
            seUsuarioSucurRolListNew = attachedSeUsuarioSucurRolListNew;
            seRoles.setSeUsuarioSucurRolList(seUsuarioSucurRolListNew);
            seRoles = em.merge(seRoles);
            for (SeOpcionesRoles seOpcionesRolesListNewSeOpcionesRoles : seOpcionesRolesListNew) {
                if (!seOpcionesRolesListOld.contains(seOpcionesRolesListNewSeOpcionesRoles)) {
                    SeRoles oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles = seOpcionesRolesListNewSeOpcionesRoles.getSeRoles();
                    seOpcionesRolesListNewSeOpcionesRoles.setSeRoles(seRoles);
                    seOpcionesRolesListNewSeOpcionesRoles = em.merge(seOpcionesRolesListNewSeOpcionesRoles);
                    if (oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles != null && !oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles.equals(seRoles)) {
                        oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles.getSeOpcionesRolesList().remove(seOpcionesRolesListNewSeOpcionesRoles);
                        oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles = em.merge(oldSeRolesOfSeOpcionesRolesListNewSeOpcionesRoles);
                    }
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListOldSeUsuarioSucurRol : seUsuarioSucurRolListOld) {
                if (!seUsuarioSucurRolListNew.contains(seUsuarioSucurRolListOldSeUsuarioSucurRol)) {
                    seUsuarioSucurRolListOldSeUsuarioSucurRol.setIdRoles(null);
                    seUsuarioSucurRolListOldSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListOldSeUsuarioSucurRol);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRol : seUsuarioSucurRolListNew) {
                if (!seUsuarioSucurRolListOld.contains(seUsuarioSucurRolListNewSeUsuarioSucurRol)) {
                    SeRoles oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = seUsuarioSucurRolListNewSeUsuarioSucurRol.getIdRoles();
                    seUsuarioSucurRolListNewSeUsuarioSucurRol.setIdRoles(seRoles);
                    seUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                    if (oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol != null && !oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.equals(seRoles)) {
                        oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                        oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(oldIdRolesOfSeUsuarioSucurRolListNewSeUsuarioSucurRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seRoles.getIdRoles();
                if (findSeRoles(id) == null) {
                    throw new NonexistentEntityException("The seRoles with id " + id + " no longer exists.");
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
            SeRoles seRoles;
            try {
                seRoles = em.getReference(SeRoles.class, id);
                seRoles.getIdRoles();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seRoles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SeOpcionesRoles> seOpcionesRolesListOrphanCheck = seRoles.getSeOpcionesRolesList();
            for (SeOpcionesRoles seOpcionesRolesListOrphanCheckSeOpcionesRoles : seOpcionesRolesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeRoles (" + seRoles + ") cannot be destroyed since the SeOpcionesRoles " + seOpcionesRolesListOrphanCheckSeOpcionesRoles + " in its seOpcionesRolesList field has a non-nullable seRoles field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SeUsuarioSucurRol> seUsuarioSucurRolList = seRoles.getSeUsuarioSucurRolList();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seUsuarioSucurRolList) {
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdRoles(null);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
            }
            em.remove(seRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeRoles> findSeRolesEntities() {
        return findSeRolesEntities(true, -1, -1);
    }

    public List<SeRoles> findSeRolesEntities(int maxResults, int firstResult) {
        return findSeRolesEntities(false, maxResults, firstResult);
    }

    private List<SeRoles> findSeRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeRoles.class));
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

    public SeRoles findSeRoles(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeRoles> rt = cq.from(SeRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
