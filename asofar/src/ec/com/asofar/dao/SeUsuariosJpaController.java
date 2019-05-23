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
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.VeFactura;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import ec.com.asofar.dto.SeUsuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class SeUsuariosJpaController implements Serializable {

    public SeUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeUsuarios seUsuarios) throws PreexistingEntityException, Exception {
        if (seUsuarios.getVeFacturaList() == null) {
            seUsuarios.setVeFacturaList(new ArrayList<VeFactura>());
        }
        if (seUsuarios.getSeUsuarioSucurRolList() == null) {
            seUsuarios.setSeUsuarioSucurRolList(new ArrayList<SeUsuarioSucurRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas idPersona = seUsuarios.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                seUsuarios.setIdPersona(idPersona);
            }
            List<VeFactura> attachedVeFacturaList = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListVeFacturaToAttach : seUsuarios.getVeFacturaList()) {
                veFacturaListVeFacturaToAttach = em.getReference(veFacturaListVeFacturaToAttach.getClass(), veFacturaListVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaList.add(veFacturaListVeFacturaToAttach);
            }
            seUsuarios.setVeFacturaList(attachedVeFacturaList);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolList = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRolToAttach : seUsuarios.getSeUsuarioSucurRolList()) {
                seUsuarioSucurRolListSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolList.add(seUsuarioSucurRolListSeUsuarioSucurRolToAttach);
            }
            seUsuarios.setSeUsuarioSucurRolList(attachedSeUsuarioSucurRolList);
            em.persist(seUsuarios);
            if (idPersona != null) {
                idPersona.getSeUsuariosList().add(seUsuarios);
                idPersona = em.merge(idPersona);
            }
            for (VeFactura veFacturaListVeFactura : seUsuarios.getVeFacturaList()) {
                SeUsuarios oldIdUsuarioOfVeFacturaListVeFactura = veFacturaListVeFactura.getIdUsuario();
                veFacturaListVeFactura.setIdUsuario(seUsuarios);
                veFacturaListVeFactura = em.merge(veFacturaListVeFactura);
                if (oldIdUsuarioOfVeFacturaListVeFactura != null) {
                    oldIdUsuarioOfVeFacturaListVeFactura.getVeFacturaList().remove(veFacturaListVeFactura);
                    oldIdUsuarioOfVeFacturaListVeFactura = em.merge(oldIdUsuarioOfVeFacturaListVeFactura);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seUsuarios.getSeUsuarioSucurRolList()) {
                SeUsuarios oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol = seUsuarioSucurRolListSeUsuarioSucurRol.getIdUsuario();
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdUsuario(seUsuarios);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
                if (oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol != null) {
                    oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListSeUsuarioSucurRol);
                    oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol = em.merge(oldIdUsuarioOfSeUsuarioSucurRolListSeUsuarioSucurRol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeUsuarios(seUsuarios.getIdUsuario()) != null) {
                throw new PreexistingEntityException("SeUsuarios " + seUsuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeUsuarios seUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios persistentSeUsuarios = em.find(SeUsuarios.class, seUsuarios.getIdUsuario());
            SePersonas idPersonaOld = persistentSeUsuarios.getIdPersona();
            SePersonas idPersonaNew = seUsuarios.getIdPersona();
            List<VeFactura> veFacturaListOld = persistentSeUsuarios.getVeFacturaList();
            List<VeFactura> veFacturaListNew = seUsuarios.getVeFacturaList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOld = persistentSeUsuarios.getSeUsuarioSucurRolList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListNew = seUsuarios.getSeUsuarioSucurRolList();
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                seUsuarios.setIdPersona(idPersonaNew);
            }
            List<VeFactura> attachedVeFacturaListNew = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListNewVeFacturaToAttach : veFacturaListNew) {
                veFacturaListNewVeFacturaToAttach = em.getReference(veFacturaListNewVeFacturaToAttach.getClass(), veFacturaListNewVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaListNew.add(veFacturaListNewVeFacturaToAttach);
            }
            veFacturaListNew = attachedVeFacturaListNew;
            seUsuarios.setVeFacturaList(veFacturaListNew);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolListNew = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach : seUsuarioSucurRolListNew) {
                seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolListNew.add(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach);
            }
            seUsuarioSucurRolListNew = attachedSeUsuarioSucurRolListNew;
            seUsuarios.setSeUsuarioSucurRolList(seUsuarioSucurRolListNew);
            seUsuarios = em.merge(seUsuarios);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getSeUsuariosList().remove(seUsuarios);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getSeUsuariosList().add(seUsuarios);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (VeFactura veFacturaListOldVeFactura : veFacturaListOld) {
                if (!veFacturaListNew.contains(veFacturaListOldVeFactura)) {
                    veFacturaListOldVeFactura.setIdUsuario(null);
                    veFacturaListOldVeFactura = em.merge(veFacturaListOldVeFactura);
                }
            }
            for (VeFactura veFacturaListNewVeFactura : veFacturaListNew) {
                if (!veFacturaListOld.contains(veFacturaListNewVeFactura)) {
                    SeUsuarios oldIdUsuarioOfVeFacturaListNewVeFactura = veFacturaListNewVeFactura.getIdUsuario();
                    veFacturaListNewVeFactura.setIdUsuario(seUsuarios);
                    veFacturaListNewVeFactura = em.merge(veFacturaListNewVeFactura);
                    if (oldIdUsuarioOfVeFacturaListNewVeFactura != null && !oldIdUsuarioOfVeFacturaListNewVeFactura.equals(seUsuarios)) {
                        oldIdUsuarioOfVeFacturaListNewVeFactura.getVeFacturaList().remove(veFacturaListNewVeFactura);
                        oldIdUsuarioOfVeFacturaListNewVeFactura = em.merge(oldIdUsuarioOfVeFacturaListNewVeFactura);
                    }
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListOldSeUsuarioSucurRol : seUsuarioSucurRolListOld) {
                if (!seUsuarioSucurRolListNew.contains(seUsuarioSucurRolListOldSeUsuarioSucurRol)) {
                    seUsuarioSucurRolListOldSeUsuarioSucurRol.setIdUsuario(null);
                    seUsuarioSucurRolListOldSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListOldSeUsuarioSucurRol);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRol : seUsuarioSucurRolListNew) {
                if (!seUsuarioSucurRolListOld.contains(seUsuarioSucurRolListNewSeUsuarioSucurRol)) {
                    SeUsuarios oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = seUsuarioSucurRolListNewSeUsuarioSucurRol.getIdUsuario();
                    seUsuarioSucurRolListNewSeUsuarioSucurRol.setIdUsuario(seUsuarios);
                    seUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                    if (oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol != null && !oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.equals(seUsuarios)) {
                        oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                        oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(oldIdUsuarioOfSeUsuarioSucurRolListNewSeUsuarioSucurRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = seUsuarios.getIdUsuario();
                if (findSeUsuarios(id) == null) {
                    throw new NonexistentEntityException("The seUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios seUsuarios;
            try {
                seUsuarios = em.getReference(SeUsuarios.class, id);
                seUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seUsuarios with id " + id + " no longer exists.", enfe);
            }
            SePersonas idPersona = seUsuarios.getIdPersona();
            if (idPersona != null) {
                idPersona.getSeUsuariosList().remove(seUsuarios);
                idPersona = em.merge(idPersona);
            }
            List<VeFactura> veFacturaList = seUsuarios.getVeFacturaList();
            for (VeFactura veFacturaListVeFactura : veFacturaList) {
                veFacturaListVeFactura.setIdUsuario(null);
                veFacturaListVeFactura = em.merge(veFacturaListVeFactura);
            }
            List<SeUsuarioSucurRol> seUsuarioSucurRolList = seUsuarios.getSeUsuarioSucurRolList();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seUsuarioSucurRolList) {
                seUsuarioSucurRolListSeUsuarioSucurRol.setIdUsuario(null);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
            }
            em.remove(seUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeUsuarios> findSeUsuariosEntities() {
        return findSeUsuariosEntities(true, -1, -1);
    }

    public List<SeUsuarios> findSeUsuariosEntities(int maxResults, int firstResult) {
        return findSeUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SeUsuarios> findSeUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeUsuarios.class));
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

    public SeUsuarios findSeUsuarios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeUsuarios> rt = cq.from(SeUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
