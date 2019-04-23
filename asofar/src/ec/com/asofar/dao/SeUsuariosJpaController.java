/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.SeUsuariosPK;
import ec.com.asofar.dto.VeFactura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
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
        if (seUsuarios.getSeUsuariosPK() == null) {
            seUsuarios.setSeUsuariosPK(new SeUsuariosPK());
        }
        if (seUsuarios.getSeUsuarioSucurRolList() == null) {
            seUsuarios.setSeUsuarioSucurRolList(new ArrayList<SeUsuarioSucurRol>());
        }
        if (seUsuarios.getInMovimientosList() == null) {
            seUsuarios.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (seUsuarios.getVeFacturaList() == null) {
            seUsuarios.setVeFacturaList(new ArrayList<VeFactura>());
        }
        seUsuarios.getSeUsuariosPK().setIdPersona(seUsuarios.getSePersonas().getSePersonasPK().getIdPersona());
        seUsuarios.getSeUsuariosPK().setIdTipoPersona(seUsuarios.getSePersonas().getSePersonasPK().getIdTipoPersona());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas sePersonas = seUsuarios.getSePersonas();
            if (sePersonas != null) {
                sePersonas = em.getReference(sePersonas.getClass(), sePersonas.getSePersonasPK());
                seUsuarios.setSePersonas(sePersonas);
            }
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolList = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRolToAttach : seUsuarios.getSeUsuarioSucurRolList()) {
                seUsuarioSucurRolListSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolList.add(seUsuarioSucurRolListSeUsuarioSucurRolToAttach);
            }
            seUsuarios.setSeUsuarioSucurRolList(attachedSeUsuarioSucurRolList);
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : seUsuarios.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            seUsuarios.setInMovimientosList(attachedInMovimientosList);
            List<VeFactura> attachedVeFacturaList = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListVeFacturaToAttach : seUsuarios.getVeFacturaList()) {
                veFacturaListVeFacturaToAttach = em.getReference(veFacturaListVeFacturaToAttach.getClass(), veFacturaListVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaList.add(veFacturaListVeFacturaToAttach);
            }
            seUsuarios.setVeFacturaList(attachedVeFacturaList);
            em.persist(seUsuarios);
            if (sePersonas != null) {
                sePersonas.getSeUsuariosList().add(seUsuarios);
                sePersonas = em.merge(sePersonas);
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
            for (InMovimientos inMovimientosListInMovimientos : seUsuarios.getInMovimientosList()) {
                SeUsuarios oldSeUsuariosOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getSeUsuarios();
                inMovimientosListInMovimientos.setSeUsuarios(seUsuarios);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldSeUsuariosOfInMovimientosListInMovimientos != null) {
                    oldSeUsuariosOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldSeUsuariosOfInMovimientosListInMovimientos = em.merge(oldSeUsuariosOfInMovimientosListInMovimientos);
                }
            }
            for (VeFactura veFacturaListVeFactura : seUsuarios.getVeFacturaList()) {
                SeUsuarios oldSeUsuariosOfVeFacturaListVeFactura = veFacturaListVeFactura.getSeUsuarios();
                veFacturaListVeFactura.setSeUsuarios(seUsuarios);
                veFacturaListVeFactura = em.merge(veFacturaListVeFactura);
                if (oldSeUsuariosOfVeFacturaListVeFactura != null) {
                    oldSeUsuariosOfVeFacturaListVeFactura.getVeFacturaList().remove(veFacturaListVeFactura);
                    oldSeUsuariosOfVeFacturaListVeFactura = em.merge(oldSeUsuariosOfVeFacturaListVeFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeUsuarios(seUsuarios.getSeUsuariosPK()) != null) {
                throw new PreexistingEntityException("SeUsuarios " + seUsuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeUsuarios seUsuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        seUsuarios.getSeUsuariosPK().setIdPersona(seUsuarios.getSePersonas().getSePersonasPK().getIdPersona());
        seUsuarios.getSeUsuariosPK().setIdTipoPersona(seUsuarios.getSePersonas().getSePersonasPK().getIdTipoPersona());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios persistentSeUsuarios = em.find(SeUsuarios.class, seUsuarios.getSeUsuariosPK());
            SePersonas sePersonasOld = persistentSeUsuarios.getSePersonas();
            SePersonas sePersonasNew = seUsuarios.getSePersonas();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOld = persistentSeUsuarios.getSeUsuarioSucurRolList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListNew = seUsuarios.getSeUsuarioSucurRolList();
            List<InMovimientos> inMovimientosListOld = persistentSeUsuarios.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = seUsuarios.getInMovimientosList();
            List<VeFactura> veFacturaListOld = persistentSeUsuarios.getVeFacturaList();
            List<VeFactura> veFacturaListNew = seUsuarios.getVeFacturaList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its seUsuarios field is not nullable.");
                }
            }
            for (VeFactura veFacturaListOldVeFactura : veFacturaListOld) {
                if (!veFacturaListNew.contains(veFacturaListOldVeFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFactura " + veFacturaListOldVeFactura + " since its seUsuarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sePersonasNew != null) {
                sePersonasNew = em.getReference(sePersonasNew.getClass(), sePersonasNew.getSePersonasPK());
                seUsuarios.setSePersonas(sePersonasNew);
            }
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolListNew = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach : seUsuarioSucurRolListNew) {
                seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolListNew.add(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach);
            }
            seUsuarioSucurRolListNew = attachedSeUsuarioSucurRolListNew;
            seUsuarios.setSeUsuarioSucurRolList(seUsuarioSucurRolListNew);
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            seUsuarios.setInMovimientosList(inMovimientosListNew);
            List<VeFactura> attachedVeFacturaListNew = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListNewVeFacturaToAttach : veFacturaListNew) {
                veFacturaListNewVeFacturaToAttach = em.getReference(veFacturaListNewVeFacturaToAttach.getClass(), veFacturaListNewVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaListNew.add(veFacturaListNewVeFacturaToAttach);
            }
            veFacturaListNew = attachedVeFacturaListNew;
            seUsuarios.setVeFacturaList(veFacturaListNew);
            seUsuarios = em.merge(seUsuarios);
            if (sePersonasOld != null && !sePersonasOld.equals(sePersonasNew)) {
                sePersonasOld.getSeUsuariosList().remove(seUsuarios);
                sePersonasOld = em.merge(sePersonasOld);
            }
            if (sePersonasNew != null && !sePersonasNew.equals(sePersonasOld)) {
                sePersonasNew.getSeUsuariosList().add(seUsuarios);
                sePersonasNew = em.merge(sePersonasNew);
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
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    SeUsuarios oldSeUsuariosOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getSeUsuarios();
                    inMovimientosListNewInMovimientos.setSeUsuarios(seUsuarios);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldSeUsuariosOfInMovimientosListNewInMovimientos != null && !oldSeUsuariosOfInMovimientosListNewInMovimientos.equals(seUsuarios)) {
                        oldSeUsuariosOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldSeUsuariosOfInMovimientosListNewInMovimientos = em.merge(oldSeUsuariosOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            for (VeFactura veFacturaListNewVeFactura : veFacturaListNew) {
                if (!veFacturaListOld.contains(veFacturaListNewVeFactura)) {
                    SeUsuarios oldSeUsuariosOfVeFacturaListNewVeFactura = veFacturaListNewVeFactura.getSeUsuarios();
                    veFacturaListNewVeFactura.setSeUsuarios(seUsuarios);
                    veFacturaListNewVeFactura = em.merge(veFacturaListNewVeFactura);
                    if (oldSeUsuariosOfVeFacturaListNewVeFactura != null && !oldSeUsuariosOfVeFacturaListNewVeFactura.equals(seUsuarios)) {
                        oldSeUsuariosOfVeFacturaListNewVeFactura.getVeFacturaList().remove(veFacturaListNewVeFactura);
                        oldSeUsuariosOfVeFacturaListNewVeFactura = em.merge(oldSeUsuariosOfVeFacturaListNewVeFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SeUsuariosPK id = seUsuarios.getSeUsuariosPK();
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

    public void destroy(SeUsuariosPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeUsuarios seUsuarios;
            try {
                seUsuarios = em.getReference(SeUsuarios.class, id);
                seUsuarios.getSeUsuariosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seUsuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = seUsuarios.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeUsuarios (" + seUsuarios + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable seUsuarios field.");
            }
            List<VeFactura> veFacturaListOrphanCheck = seUsuarios.getVeFacturaList();
            for (VeFactura veFacturaListOrphanCheckVeFactura : veFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeUsuarios (" + seUsuarios + ") cannot be destroyed since the VeFactura " + veFacturaListOrphanCheckVeFactura + " in its veFacturaList field has a non-nullable seUsuarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SePersonas sePersonas = seUsuarios.getSePersonas();
            if (sePersonas != null) {
                sePersonas.getSeUsuariosList().remove(seUsuarios);
                sePersonas = em.merge(sePersonas);
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

    public SeUsuarios findSeUsuarios(SeUsuariosPK id) {
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
