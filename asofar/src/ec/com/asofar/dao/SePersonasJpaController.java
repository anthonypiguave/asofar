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
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.SePersonas;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.SeUsuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class SePersonasJpaController implements Serializable {

    public SePersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SePersonas sePersonas) {
        if (sePersonas.getInMovimientosList() == null) {
            sePersonas.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (sePersonas.getSeUsuariosList() == null) {
            sePersonas.setSeUsuariosList(new ArrayList<SeUsuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeTipoPersona idTipoPersona = sePersonas.getIdTipoPersona();
            if (idTipoPersona != null) {
                idTipoPersona = em.getReference(idTipoPersona.getClass(), idTipoPersona.getIdTipoPersona());
                sePersonas.setIdTipoPersona(idTipoPersona);
            }
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : sePersonas.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            sePersonas.setInMovimientosList(attachedInMovimientosList);
            List<SeUsuarios> attachedSeUsuariosList = new ArrayList<SeUsuarios>();
            for (SeUsuarios seUsuariosListSeUsuariosToAttach : sePersonas.getSeUsuariosList()) {
                seUsuariosListSeUsuariosToAttach = em.getReference(seUsuariosListSeUsuariosToAttach.getClass(), seUsuariosListSeUsuariosToAttach.getIdUsuario());
                attachedSeUsuariosList.add(seUsuariosListSeUsuariosToAttach);
            }
            sePersonas.setSeUsuariosList(attachedSeUsuariosList);
            em.persist(sePersonas);
            if (idTipoPersona != null) {
                idTipoPersona.getSePersonasList().add(sePersonas);
                idTipoPersona = em.merge(idTipoPersona);
            }
            for (InMovimientos inMovimientosListInMovimientos : sePersonas.getInMovimientosList()) {
                SePersonas oldSePersonasOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getSePersonas();
                inMovimientosListInMovimientos.setSePersonas(sePersonas);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldSePersonasOfInMovimientosListInMovimientos != null) {
                    oldSePersonasOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldSePersonasOfInMovimientosListInMovimientos = em.merge(oldSePersonasOfInMovimientosListInMovimientos);
                }
            }
            for (SeUsuarios seUsuariosListSeUsuarios : sePersonas.getSeUsuariosList()) {
                SePersonas oldIdPersonaOfSeUsuariosListSeUsuarios = seUsuariosListSeUsuarios.getIdPersona();
                seUsuariosListSeUsuarios.setIdPersona(sePersonas);
                seUsuariosListSeUsuarios = em.merge(seUsuariosListSeUsuarios);
                if (oldIdPersonaOfSeUsuariosListSeUsuarios != null) {
                    oldIdPersonaOfSeUsuariosListSeUsuarios.getSeUsuariosList().remove(seUsuariosListSeUsuarios);
                    oldIdPersonaOfSeUsuariosListSeUsuarios = em.merge(oldIdPersonaOfSeUsuariosListSeUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SePersonas sePersonas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas persistentSePersonas = em.find(SePersonas.class, sePersonas.getIdPersona());
            SeTipoPersona idTipoPersonaOld = persistentSePersonas.getIdTipoPersona();
            SeTipoPersona idTipoPersonaNew = sePersonas.getIdTipoPersona();
            List<InMovimientos> inMovimientosListOld = persistentSePersonas.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = sePersonas.getInMovimientosList();
            List<SeUsuarios> seUsuariosListOld = persistentSePersonas.getSeUsuariosList();
            List<SeUsuarios> seUsuariosListNew = sePersonas.getSeUsuariosList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its sePersonas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoPersonaNew != null) {
                idTipoPersonaNew = em.getReference(idTipoPersonaNew.getClass(), idTipoPersonaNew.getIdTipoPersona());
                sePersonas.setIdTipoPersona(idTipoPersonaNew);
            }
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            sePersonas.setInMovimientosList(inMovimientosListNew);
            List<SeUsuarios> attachedSeUsuariosListNew = new ArrayList<SeUsuarios>();
            for (SeUsuarios seUsuariosListNewSeUsuariosToAttach : seUsuariosListNew) {
                seUsuariosListNewSeUsuariosToAttach = em.getReference(seUsuariosListNewSeUsuariosToAttach.getClass(), seUsuariosListNewSeUsuariosToAttach.getIdUsuario());
                attachedSeUsuariosListNew.add(seUsuariosListNewSeUsuariosToAttach);
            }
            seUsuariosListNew = attachedSeUsuariosListNew;
            sePersonas.setSeUsuariosList(seUsuariosListNew);
            sePersonas = em.merge(sePersonas);
            if (idTipoPersonaOld != null && !idTipoPersonaOld.equals(idTipoPersonaNew)) {
                idTipoPersonaOld.getSePersonasList().remove(sePersonas);
                idTipoPersonaOld = em.merge(idTipoPersonaOld);
            }
            if (idTipoPersonaNew != null && !idTipoPersonaNew.equals(idTipoPersonaOld)) {
                idTipoPersonaNew.getSePersonasList().add(sePersonas);
                idTipoPersonaNew = em.merge(idTipoPersonaNew);
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    SePersonas oldSePersonasOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getSePersonas();
                    inMovimientosListNewInMovimientos.setSePersonas(sePersonas);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldSePersonasOfInMovimientosListNewInMovimientos != null && !oldSePersonasOfInMovimientosListNewInMovimientos.equals(sePersonas)) {
                        oldSePersonasOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldSePersonasOfInMovimientosListNewInMovimientos = em.merge(oldSePersonasOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            for (SeUsuarios seUsuariosListOldSeUsuarios : seUsuariosListOld) {
                if (!seUsuariosListNew.contains(seUsuariosListOldSeUsuarios)) {
                    seUsuariosListOldSeUsuarios.setIdPersona(null);
                    seUsuariosListOldSeUsuarios = em.merge(seUsuariosListOldSeUsuarios);
                }
            }
            for (SeUsuarios seUsuariosListNewSeUsuarios : seUsuariosListNew) {
                if (!seUsuariosListOld.contains(seUsuariosListNewSeUsuarios)) {
                    SePersonas oldIdPersonaOfSeUsuariosListNewSeUsuarios = seUsuariosListNewSeUsuarios.getIdPersona();
                    seUsuariosListNewSeUsuarios.setIdPersona(sePersonas);
                    seUsuariosListNewSeUsuarios = em.merge(seUsuariosListNewSeUsuarios);
                    if (oldIdPersonaOfSeUsuariosListNewSeUsuarios != null && !oldIdPersonaOfSeUsuariosListNewSeUsuarios.equals(sePersonas)) {
                        oldIdPersonaOfSeUsuariosListNewSeUsuarios.getSeUsuariosList().remove(seUsuariosListNewSeUsuarios);
                        oldIdPersonaOfSeUsuariosListNewSeUsuarios = em.merge(oldIdPersonaOfSeUsuariosListNewSeUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sePersonas.getIdPersona();
                if (findSePersonas(id) == null) {
                    throw new NonexistentEntityException("The sePersonas with id " + id + " no longer exists.");
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
            SePersonas sePersonas;
            try {
                sePersonas = em.getReference(SePersonas.class, id);
                sePersonas.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sePersonas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = sePersonas.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SePersonas (" + sePersonas + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable sePersonas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeTipoPersona idTipoPersona = sePersonas.getIdTipoPersona();
            if (idTipoPersona != null) {
                idTipoPersona.getSePersonasList().remove(sePersonas);
                idTipoPersona = em.merge(idTipoPersona);
            }
            List<SeUsuarios> seUsuariosList = sePersonas.getSeUsuariosList();
            for (SeUsuarios seUsuariosListSeUsuarios : seUsuariosList) {
                seUsuariosListSeUsuarios.setIdPersona(null);
                seUsuariosListSeUsuarios = em.merge(seUsuariosListSeUsuarios);
            }
            em.remove(sePersonas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SePersonas> findSePersonasEntities() {
        return findSePersonasEntities(true, -1, -1);
    }

    public List<SePersonas> findSePersonasEntities(int maxResults, int firstResult) {
        return findSePersonasEntities(false, maxResults, firstResult);
    }

    private List<SePersonas> findSePersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SePersonas.class));
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

    public SePersonas findSePersonas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SePersonas.class, id);
        } finally {
            em.close();
        }
    }

    public int getSePersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SePersonas> rt = cq.from(SePersonas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
