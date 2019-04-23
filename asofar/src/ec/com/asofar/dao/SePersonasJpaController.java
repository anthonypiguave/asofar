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
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.CoOrdenCompras;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SePersonasPK;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeFactura;
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

    public void create(SePersonas sePersonas) throws PreexistingEntityException, Exception {
        if (sePersonas.getSePersonasPK() == null) {
            sePersonas.setSePersonasPK(new SePersonasPK());
        }
        if (sePersonas.getCoOrdenComprasList() == null) {
            sePersonas.setCoOrdenComprasList(new ArrayList<CoOrdenCompras>());
        }
        if (sePersonas.getInMovimientosList() == null) {
            sePersonas.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (sePersonas.getSeUsuariosList() == null) {
            sePersonas.setSeUsuariosList(new ArrayList<SeUsuarios>());
        }
        if (sePersonas.getVeFacturaList() == null) {
            sePersonas.setVeFacturaList(new ArrayList<VeFactura>());
        }
        sePersonas.getSePersonasPK().setIdTipoPersona(sePersonas.getSeTipoPersona().getIdTipoPersona());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeTipoPersona seTipoPersona = sePersonas.getSeTipoPersona();
            if (seTipoPersona != null) {
                seTipoPersona = em.getReference(seTipoPersona.getClass(), seTipoPersona.getIdTipoPersona());
                sePersonas.setSeTipoPersona(seTipoPersona);
            }
            List<CoOrdenCompras> attachedCoOrdenComprasList = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListCoOrdenComprasToAttach : sePersonas.getCoOrdenComprasList()) {
                coOrdenComprasListCoOrdenComprasToAttach = em.getReference(coOrdenComprasListCoOrdenComprasToAttach.getClass(), coOrdenComprasListCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasList.add(coOrdenComprasListCoOrdenComprasToAttach);
            }
            sePersonas.setCoOrdenComprasList(attachedCoOrdenComprasList);
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : sePersonas.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            sePersonas.setInMovimientosList(attachedInMovimientosList);
            List<SeUsuarios> attachedSeUsuariosList = new ArrayList<SeUsuarios>();
            for (SeUsuarios seUsuariosListSeUsuariosToAttach : sePersonas.getSeUsuariosList()) {
                seUsuariosListSeUsuariosToAttach = em.getReference(seUsuariosListSeUsuariosToAttach.getClass(), seUsuariosListSeUsuariosToAttach.getSeUsuariosPK());
                attachedSeUsuariosList.add(seUsuariosListSeUsuariosToAttach);
            }
            sePersonas.setSeUsuariosList(attachedSeUsuariosList);
            List<VeFactura> attachedVeFacturaList = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListVeFacturaToAttach : sePersonas.getVeFacturaList()) {
                veFacturaListVeFacturaToAttach = em.getReference(veFacturaListVeFacturaToAttach.getClass(), veFacturaListVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaList.add(veFacturaListVeFacturaToAttach);
            }
            sePersonas.setVeFacturaList(attachedVeFacturaList);
            em.persist(sePersonas);
            if (seTipoPersona != null) {
                seTipoPersona.getSePersonasList().add(sePersonas);
                seTipoPersona = em.merge(seTipoPersona);
            }
            for (CoOrdenCompras coOrdenComprasListCoOrdenCompras : sePersonas.getCoOrdenComprasList()) {
                SePersonas oldIdProveedorOfCoOrdenComprasListCoOrdenCompras = coOrdenComprasListCoOrdenCompras.getIdProveedor();
                coOrdenComprasListCoOrdenCompras.setIdProveedor(sePersonas);
                coOrdenComprasListCoOrdenCompras = em.merge(coOrdenComprasListCoOrdenCompras);
                if (oldIdProveedorOfCoOrdenComprasListCoOrdenCompras != null) {
                    oldIdProveedorOfCoOrdenComprasListCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListCoOrdenCompras);
                    oldIdProveedorOfCoOrdenComprasListCoOrdenCompras = em.merge(oldIdProveedorOfCoOrdenComprasListCoOrdenCompras);
                }
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
                SePersonas oldSePersonasOfSeUsuariosListSeUsuarios = seUsuariosListSeUsuarios.getSePersonas();
                seUsuariosListSeUsuarios.setSePersonas(sePersonas);
                seUsuariosListSeUsuarios = em.merge(seUsuariosListSeUsuarios);
                if (oldSePersonasOfSeUsuariosListSeUsuarios != null) {
                    oldSePersonasOfSeUsuariosListSeUsuarios.getSeUsuariosList().remove(seUsuariosListSeUsuarios);
                    oldSePersonasOfSeUsuariosListSeUsuarios = em.merge(oldSePersonasOfSeUsuariosListSeUsuarios);
                }
            }
            for (VeFactura veFacturaListVeFactura : sePersonas.getVeFacturaList()) {
                SePersonas oldSePersonasOfVeFacturaListVeFactura = veFacturaListVeFactura.getSePersonas();
                veFacturaListVeFactura.setSePersonas(sePersonas);
                veFacturaListVeFactura = em.merge(veFacturaListVeFactura);
                if (oldSePersonasOfVeFacturaListVeFactura != null) {
                    oldSePersonasOfVeFacturaListVeFactura.getVeFacturaList().remove(veFacturaListVeFactura);
                    oldSePersonasOfVeFacturaListVeFactura = em.merge(oldSePersonasOfVeFacturaListVeFactura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSePersonas(sePersonas.getSePersonasPK()) != null) {
                throw new PreexistingEntityException("SePersonas " + sePersonas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SePersonas sePersonas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        sePersonas.getSePersonasPK().setIdTipoPersona(sePersonas.getSeTipoPersona().getIdTipoPersona());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas persistentSePersonas = em.find(SePersonas.class, sePersonas.getSePersonasPK());
            SeTipoPersona seTipoPersonaOld = persistentSePersonas.getSeTipoPersona();
            SeTipoPersona seTipoPersonaNew = sePersonas.getSeTipoPersona();
            List<CoOrdenCompras> coOrdenComprasListOld = persistentSePersonas.getCoOrdenComprasList();
            List<CoOrdenCompras> coOrdenComprasListNew = sePersonas.getCoOrdenComprasList();
            List<InMovimientos> inMovimientosListOld = persistentSePersonas.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = sePersonas.getInMovimientosList();
            List<SeUsuarios> seUsuariosListOld = persistentSePersonas.getSeUsuariosList();
            List<SeUsuarios> seUsuariosListNew = sePersonas.getSeUsuariosList();
            List<VeFactura> veFacturaListOld = persistentSePersonas.getVeFacturaList();
            List<VeFactura> veFacturaListNew = sePersonas.getVeFacturaList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its sePersonas field is not nullable.");
                }
            }
            for (SeUsuarios seUsuariosListOldSeUsuarios : seUsuariosListOld) {
                if (!seUsuariosListNew.contains(seUsuariosListOldSeUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeUsuarios " + seUsuariosListOldSeUsuarios + " since its sePersonas field is not nullable.");
                }
            }
            for (VeFactura veFacturaListOldVeFactura : veFacturaListOld) {
                if (!veFacturaListNew.contains(veFacturaListOldVeFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFactura " + veFacturaListOldVeFactura + " since its sePersonas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seTipoPersonaNew != null) {
                seTipoPersonaNew = em.getReference(seTipoPersonaNew.getClass(), seTipoPersonaNew.getIdTipoPersona());
                sePersonas.setSeTipoPersona(seTipoPersonaNew);
            }
            List<CoOrdenCompras> attachedCoOrdenComprasListNew = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenComprasToAttach : coOrdenComprasListNew) {
                coOrdenComprasListNewCoOrdenComprasToAttach = em.getReference(coOrdenComprasListNewCoOrdenComprasToAttach.getClass(), coOrdenComprasListNewCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasListNew.add(coOrdenComprasListNewCoOrdenComprasToAttach);
            }
            coOrdenComprasListNew = attachedCoOrdenComprasListNew;
            sePersonas.setCoOrdenComprasList(coOrdenComprasListNew);
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            sePersonas.setInMovimientosList(inMovimientosListNew);
            List<SeUsuarios> attachedSeUsuariosListNew = new ArrayList<SeUsuarios>();
            for (SeUsuarios seUsuariosListNewSeUsuariosToAttach : seUsuariosListNew) {
                seUsuariosListNewSeUsuariosToAttach = em.getReference(seUsuariosListNewSeUsuariosToAttach.getClass(), seUsuariosListNewSeUsuariosToAttach.getSeUsuariosPK());
                attachedSeUsuariosListNew.add(seUsuariosListNewSeUsuariosToAttach);
            }
            seUsuariosListNew = attachedSeUsuariosListNew;
            sePersonas.setSeUsuariosList(seUsuariosListNew);
            List<VeFactura> attachedVeFacturaListNew = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListNewVeFacturaToAttach : veFacturaListNew) {
                veFacturaListNewVeFacturaToAttach = em.getReference(veFacturaListNewVeFacturaToAttach.getClass(), veFacturaListNewVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaListNew.add(veFacturaListNewVeFacturaToAttach);
            }
            veFacturaListNew = attachedVeFacturaListNew;
            sePersonas.setVeFacturaList(veFacturaListNew);
            sePersonas = em.merge(sePersonas);
            if (seTipoPersonaOld != null && !seTipoPersonaOld.equals(seTipoPersonaNew)) {
                seTipoPersonaOld.getSePersonasList().remove(sePersonas);
                seTipoPersonaOld = em.merge(seTipoPersonaOld);
            }
            if (seTipoPersonaNew != null && !seTipoPersonaNew.equals(seTipoPersonaOld)) {
                seTipoPersonaNew.getSePersonasList().add(sePersonas);
                seTipoPersonaNew = em.merge(seTipoPersonaNew);
            }
            for (CoOrdenCompras coOrdenComprasListOldCoOrdenCompras : coOrdenComprasListOld) {
                if (!coOrdenComprasListNew.contains(coOrdenComprasListOldCoOrdenCompras)) {
                    coOrdenComprasListOldCoOrdenCompras.setIdProveedor(null);
                    coOrdenComprasListOldCoOrdenCompras = em.merge(coOrdenComprasListOldCoOrdenCompras);
                }
            }
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenCompras : coOrdenComprasListNew) {
                if (!coOrdenComprasListOld.contains(coOrdenComprasListNewCoOrdenCompras)) {
                    SePersonas oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras = coOrdenComprasListNewCoOrdenCompras.getIdProveedor();
                    coOrdenComprasListNewCoOrdenCompras.setIdProveedor(sePersonas);
                    coOrdenComprasListNewCoOrdenCompras = em.merge(coOrdenComprasListNewCoOrdenCompras);
                    if (oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras != null && !oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras.equals(sePersonas)) {
                        oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListNewCoOrdenCompras);
                        oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras = em.merge(oldIdProveedorOfCoOrdenComprasListNewCoOrdenCompras);
                    }
                }
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
            for (SeUsuarios seUsuariosListNewSeUsuarios : seUsuariosListNew) {
                if (!seUsuariosListOld.contains(seUsuariosListNewSeUsuarios)) {
                    SePersonas oldSePersonasOfSeUsuariosListNewSeUsuarios = seUsuariosListNewSeUsuarios.getSePersonas();
                    seUsuariosListNewSeUsuarios.setSePersonas(sePersonas);
                    seUsuariosListNewSeUsuarios = em.merge(seUsuariosListNewSeUsuarios);
                    if (oldSePersonasOfSeUsuariosListNewSeUsuarios != null && !oldSePersonasOfSeUsuariosListNewSeUsuarios.equals(sePersonas)) {
                        oldSePersonasOfSeUsuariosListNewSeUsuarios.getSeUsuariosList().remove(seUsuariosListNewSeUsuarios);
                        oldSePersonasOfSeUsuariosListNewSeUsuarios = em.merge(oldSePersonasOfSeUsuariosListNewSeUsuarios);
                    }
                }
            }
            for (VeFactura veFacturaListNewVeFactura : veFacturaListNew) {
                if (!veFacturaListOld.contains(veFacturaListNewVeFactura)) {
                    SePersonas oldSePersonasOfVeFacturaListNewVeFactura = veFacturaListNewVeFactura.getSePersonas();
                    veFacturaListNewVeFactura.setSePersonas(sePersonas);
                    veFacturaListNewVeFactura = em.merge(veFacturaListNewVeFactura);
                    if (oldSePersonasOfVeFacturaListNewVeFactura != null && !oldSePersonasOfVeFacturaListNewVeFactura.equals(sePersonas)) {
                        oldSePersonasOfVeFacturaListNewVeFactura.getVeFacturaList().remove(veFacturaListNewVeFactura);
                        oldSePersonasOfVeFacturaListNewVeFactura = em.merge(oldSePersonasOfVeFacturaListNewVeFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SePersonasPK id = sePersonas.getSePersonasPK();
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

    public void destroy(SePersonasPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePersonas sePersonas;
            try {
                sePersonas = em.getReference(SePersonas.class, id);
                sePersonas.getSePersonasPK();
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
            List<SeUsuarios> seUsuariosListOrphanCheck = sePersonas.getSeUsuariosList();
            for (SeUsuarios seUsuariosListOrphanCheckSeUsuarios : seUsuariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SePersonas (" + sePersonas + ") cannot be destroyed since the SeUsuarios " + seUsuariosListOrphanCheckSeUsuarios + " in its seUsuariosList field has a non-nullable sePersonas field.");
            }
            List<VeFactura> veFacturaListOrphanCheck = sePersonas.getVeFacturaList();
            for (VeFactura veFacturaListOrphanCheckVeFactura : veFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SePersonas (" + sePersonas + ") cannot be destroyed since the VeFactura " + veFacturaListOrphanCheckVeFactura + " in its veFacturaList field has a non-nullable sePersonas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeTipoPersona seTipoPersona = sePersonas.getSeTipoPersona();
            if (seTipoPersona != null) {
                seTipoPersona.getSePersonasList().remove(sePersonas);
                seTipoPersona = em.merge(seTipoPersona);
            }
            List<CoOrdenCompras> coOrdenComprasList = sePersonas.getCoOrdenComprasList();
            for (CoOrdenCompras coOrdenComprasListCoOrdenCompras : coOrdenComprasList) {
                coOrdenComprasListCoOrdenCompras.setIdProveedor(null);
                coOrdenComprasListCoOrdenCompras = em.merge(coOrdenComprasListCoOrdenCompras);
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

    public SePersonas findSePersonas(SePersonasPK id) {
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
