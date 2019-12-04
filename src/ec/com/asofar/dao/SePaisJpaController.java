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
import ec.com.asofar.dto.CoProveedores;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SePais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class SePaisJpaController implements Serializable {

    public SePaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SePais sePais) {
        if (sePais.getCoProveedoresList() == null) {
            sePais.setCoProveedoresList(new ArrayList<CoProveedores>());
        }
        if (sePais.getSeProvinciaList() == null) {
            sePais.setSeProvinciaList(new ArrayList<SeProvincia>());
        }
        if (sePais.getSeLocalidadClienteList() == null) {
            sePais.setSeLocalidadClienteList(new ArrayList<SeLocalidadCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CoProveedores> attachedCoProveedoresList = new ArrayList<CoProveedores>();
            for (CoProveedores coProveedoresListCoProveedoresToAttach : sePais.getCoProveedoresList()) {
                coProveedoresListCoProveedoresToAttach = em.getReference(coProveedoresListCoProveedoresToAttach.getClass(), coProveedoresListCoProveedoresToAttach.getIdProveedor());
                attachedCoProveedoresList.add(coProveedoresListCoProveedoresToAttach);
            }
            sePais.setCoProveedoresList(attachedCoProveedoresList);
            List<SeProvincia> attachedSeProvinciaList = new ArrayList<SeProvincia>();
            for (SeProvincia seProvinciaListSeProvinciaToAttach : sePais.getSeProvinciaList()) {
                seProvinciaListSeProvinciaToAttach = em.getReference(seProvinciaListSeProvinciaToAttach.getClass(), seProvinciaListSeProvinciaToAttach.getIdProvincia());
                attachedSeProvinciaList.add(seProvinciaListSeProvinciaToAttach);
            }
            sePais.setSeProvinciaList(attachedSeProvinciaList);
            List<SeLocalidadCliente> attachedSeLocalidadClienteList = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadClienteToAttach : sePais.getSeLocalidadClienteList()) {
                seLocalidadClienteListSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteList.add(seLocalidadClienteListSeLocalidadClienteToAttach);
            }
            sePais.setSeLocalidadClienteList(attachedSeLocalidadClienteList);
            em.persist(sePais);
            for (CoProveedores coProveedoresListCoProveedores : sePais.getCoProveedoresList()) {
                SePais oldIdPaisOfCoProveedoresListCoProveedores = coProveedoresListCoProveedores.getIdPais();
                coProveedoresListCoProveedores.setIdPais(sePais);
                coProveedoresListCoProveedores = em.merge(coProveedoresListCoProveedores);
                if (oldIdPaisOfCoProveedoresListCoProveedores != null) {
                    oldIdPaisOfCoProveedoresListCoProveedores.getCoProveedoresList().remove(coProveedoresListCoProveedores);
                    oldIdPaisOfCoProveedoresListCoProveedores = em.merge(oldIdPaisOfCoProveedoresListCoProveedores);
                }
            }
            for (SeProvincia seProvinciaListSeProvincia : sePais.getSeProvinciaList()) {
                SePais oldIdPaisOfSeProvinciaListSeProvincia = seProvinciaListSeProvincia.getIdPais();
                seProvinciaListSeProvincia.setIdPais(sePais);
                seProvinciaListSeProvincia = em.merge(seProvinciaListSeProvincia);
                if (oldIdPaisOfSeProvinciaListSeProvincia != null) {
                    oldIdPaisOfSeProvinciaListSeProvincia.getSeProvinciaList().remove(seProvinciaListSeProvincia);
                    oldIdPaisOfSeProvinciaListSeProvincia = em.merge(oldIdPaisOfSeProvinciaListSeProvincia);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : sePais.getSeLocalidadClienteList()) {
                SePais oldIdPaisOfSeLocalidadClienteListSeLocalidadCliente = seLocalidadClienteListSeLocalidadCliente.getIdPais();
                seLocalidadClienteListSeLocalidadCliente.setIdPais(sePais);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
                if (oldIdPaisOfSeLocalidadClienteListSeLocalidadCliente != null) {
                    oldIdPaisOfSeLocalidadClienteListSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListSeLocalidadCliente);
                    oldIdPaisOfSeLocalidadClienteListSeLocalidadCliente = em.merge(oldIdPaisOfSeLocalidadClienteListSeLocalidadCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SePais sePais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePais persistentSePais = em.find(SePais.class, sePais.getIdPais());
            List<CoProveedores> coProveedoresListOld = persistentSePais.getCoProveedoresList();
            List<CoProveedores> coProveedoresListNew = sePais.getCoProveedoresList();
            List<SeProvincia> seProvinciaListOld = persistentSePais.getSeProvinciaList();
            List<SeProvincia> seProvinciaListNew = sePais.getSeProvinciaList();
            List<SeLocalidadCliente> seLocalidadClienteListOld = persistentSePais.getSeLocalidadClienteList();
            List<SeLocalidadCliente> seLocalidadClienteListNew = sePais.getSeLocalidadClienteList();
            List<String> illegalOrphanMessages = null;
            for (SeProvincia seProvinciaListOldSeProvincia : seProvinciaListOld) {
                if (!seProvinciaListNew.contains(seProvinciaListOldSeProvincia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeProvincia " + seProvinciaListOldSeProvincia + " since its idPais field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CoProveedores> attachedCoProveedoresListNew = new ArrayList<CoProveedores>();
            for (CoProveedores coProveedoresListNewCoProveedoresToAttach : coProveedoresListNew) {
                coProveedoresListNewCoProveedoresToAttach = em.getReference(coProveedoresListNewCoProveedoresToAttach.getClass(), coProveedoresListNewCoProveedoresToAttach.getIdProveedor());
                attachedCoProveedoresListNew.add(coProveedoresListNewCoProveedoresToAttach);
            }
            coProveedoresListNew = attachedCoProveedoresListNew;
            sePais.setCoProveedoresList(coProveedoresListNew);
            List<SeProvincia> attachedSeProvinciaListNew = new ArrayList<SeProvincia>();
            for (SeProvincia seProvinciaListNewSeProvinciaToAttach : seProvinciaListNew) {
                seProvinciaListNewSeProvinciaToAttach = em.getReference(seProvinciaListNewSeProvinciaToAttach.getClass(), seProvinciaListNewSeProvinciaToAttach.getIdProvincia());
                attachedSeProvinciaListNew.add(seProvinciaListNewSeProvinciaToAttach);
            }
            seProvinciaListNew = attachedSeProvinciaListNew;
            sePais.setSeProvinciaList(seProvinciaListNew);
            List<SeLocalidadCliente> attachedSeLocalidadClienteListNew = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadClienteToAttach : seLocalidadClienteListNew) {
                seLocalidadClienteListNewSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListNewSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListNewSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteListNew.add(seLocalidadClienteListNewSeLocalidadClienteToAttach);
            }
            seLocalidadClienteListNew = attachedSeLocalidadClienteListNew;
            sePais.setSeLocalidadClienteList(seLocalidadClienteListNew);
            sePais = em.merge(sePais);
            for (CoProveedores coProveedoresListOldCoProveedores : coProveedoresListOld) {
                if (!coProveedoresListNew.contains(coProveedoresListOldCoProveedores)) {
                    coProveedoresListOldCoProveedores.setIdPais(null);
                    coProveedoresListOldCoProveedores = em.merge(coProveedoresListOldCoProveedores);
                }
            }
            for (CoProveedores coProveedoresListNewCoProveedores : coProveedoresListNew) {
                if (!coProveedoresListOld.contains(coProveedoresListNewCoProveedores)) {
                    SePais oldIdPaisOfCoProveedoresListNewCoProveedores = coProveedoresListNewCoProveedores.getIdPais();
                    coProveedoresListNewCoProveedores.setIdPais(sePais);
                    coProveedoresListNewCoProveedores = em.merge(coProveedoresListNewCoProveedores);
                    if (oldIdPaisOfCoProveedoresListNewCoProveedores != null && !oldIdPaisOfCoProveedoresListNewCoProveedores.equals(sePais)) {
                        oldIdPaisOfCoProveedoresListNewCoProveedores.getCoProveedoresList().remove(coProveedoresListNewCoProveedores);
                        oldIdPaisOfCoProveedoresListNewCoProveedores = em.merge(oldIdPaisOfCoProveedoresListNewCoProveedores);
                    }
                }
            }
            for (SeProvincia seProvinciaListNewSeProvincia : seProvinciaListNew) {
                if (!seProvinciaListOld.contains(seProvinciaListNewSeProvincia)) {
                    SePais oldIdPaisOfSeProvinciaListNewSeProvincia = seProvinciaListNewSeProvincia.getIdPais();
                    seProvinciaListNewSeProvincia.setIdPais(sePais);
                    seProvinciaListNewSeProvincia = em.merge(seProvinciaListNewSeProvincia);
                    if (oldIdPaisOfSeProvinciaListNewSeProvincia != null && !oldIdPaisOfSeProvinciaListNewSeProvincia.equals(sePais)) {
                        oldIdPaisOfSeProvinciaListNewSeProvincia.getSeProvinciaList().remove(seProvinciaListNewSeProvincia);
                        oldIdPaisOfSeProvinciaListNewSeProvincia = em.merge(oldIdPaisOfSeProvinciaListNewSeProvincia);
                    }
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListOldSeLocalidadCliente : seLocalidadClienteListOld) {
                if (!seLocalidadClienteListNew.contains(seLocalidadClienteListOldSeLocalidadCliente)) {
                    seLocalidadClienteListOldSeLocalidadCliente.setIdPais(null);
                    seLocalidadClienteListOldSeLocalidadCliente = em.merge(seLocalidadClienteListOldSeLocalidadCliente);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadCliente : seLocalidadClienteListNew) {
                if (!seLocalidadClienteListOld.contains(seLocalidadClienteListNewSeLocalidadCliente)) {
                    SePais oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente = seLocalidadClienteListNewSeLocalidadCliente.getIdPais();
                    seLocalidadClienteListNewSeLocalidadCliente.setIdPais(sePais);
                    seLocalidadClienteListNewSeLocalidadCliente = em.merge(seLocalidadClienteListNewSeLocalidadCliente);
                    if (oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente != null && !oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente.equals(sePais)) {
                        oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListNewSeLocalidadCliente);
                        oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente = em.merge(oldIdPaisOfSeLocalidadClienteListNewSeLocalidadCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sePais.getIdPais();
                if (findSePais(id) == null) {
                    throw new NonexistentEntityException("The sePais with id " + id + " no longer exists.");
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
            SePais sePais;
            try {
                sePais = em.getReference(SePais.class, id);
                sePais.getIdPais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sePais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SeProvincia> seProvinciaListOrphanCheck = sePais.getSeProvinciaList();
            for (SeProvincia seProvinciaListOrphanCheckSeProvincia : seProvinciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SePais (" + sePais + ") cannot be destroyed since the SeProvincia " + seProvinciaListOrphanCheckSeProvincia + " in its seProvinciaList field has a non-nullable idPais field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CoProveedores> coProveedoresList = sePais.getCoProveedoresList();
            for (CoProveedores coProveedoresListCoProveedores : coProveedoresList) {
                coProveedoresListCoProveedores.setIdPais(null);
                coProveedoresListCoProveedores = em.merge(coProveedoresListCoProveedores);
            }
            List<SeLocalidadCliente> seLocalidadClienteList = sePais.getSeLocalidadClienteList();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seLocalidadClienteList) {
                seLocalidadClienteListSeLocalidadCliente.setIdPais(null);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
            }
            em.remove(sePais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SePais> findSePaisEntities() {
        return findSePaisEntities(true, -1, -1);
    }

    public List<SePais> findSePaisEntities(int maxResults, int firstResult) {
        return findSePaisEntities(false, maxResults, firstResult);
    }

    private List<SePais> findSePaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SePais.class));
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

    public SePais findSePais(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SePais.class, id);
        } finally {
            em.close();
        }
    }

    public int getSePaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SePais> rt = cq.from(SePais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
