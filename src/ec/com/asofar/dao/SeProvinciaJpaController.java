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
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeCiudad;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SeProvincia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class SeProvinciaJpaController implements Serializable {

    public SeProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeProvincia seProvincia) {
        if (seProvincia.getSeCiudadList() == null) {
            seProvincia.setSeCiudadList(new ArrayList<SeCiudad>());
        }
        if (seProvincia.getSeLocalidadClienteList() == null) {
            seProvincia.setSeLocalidadClienteList(new ArrayList<SeLocalidadCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SePais idPais = seProvincia.getIdPais();
            if (idPais != null) {
                idPais = em.getReference(idPais.getClass(), idPais.getIdPais());
                seProvincia.setIdPais(idPais);
            }
            List<SeCiudad> attachedSeCiudadList = new ArrayList<SeCiudad>();
            for (SeCiudad seCiudadListSeCiudadToAttach : seProvincia.getSeCiudadList()) {
                seCiudadListSeCiudadToAttach = em.getReference(seCiudadListSeCiudadToAttach.getClass(), seCiudadListSeCiudadToAttach.getIdCiudad());
                attachedSeCiudadList.add(seCiudadListSeCiudadToAttach);
            }
            seProvincia.setSeCiudadList(attachedSeCiudadList);
            List<SeLocalidadCliente> attachedSeLocalidadClienteList = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadClienteToAttach : seProvincia.getSeLocalidadClienteList()) {
                seLocalidadClienteListSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteList.add(seLocalidadClienteListSeLocalidadClienteToAttach);
            }
            seProvincia.setSeLocalidadClienteList(attachedSeLocalidadClienteList);
            em.persist(seProvincia);
            if (idPais != null) {
                idPais.getSeProvinciaList().add(seProvincia);
                idPais = em.merge(idPais);
            }
            for (SeCiudad seCiudadListSeCiudad : seProvincia.getSeCiudadList()) {
                SeProvincia oldIdProvinciaOfSeCiudadListSeCiudad = seCiudadListSeCiudad.getIdProvincia();
                seCiudadListSeCiudad.setIdProvincia(seProvincia);
                seCiudadListSeCiudad = em.merge(seCiudadListSeCiudad);
                if (oldIdProvinciaOfSeCiudadListSeCiudad != null) {
                    oldIdProvinciaOfSeCiudadListSeCiudad.getSeCiudadList().remove(seCiudadListSeCiudad);
                    oldIdProvinciaOfSeCiudadListSeCiudad = em.merge(oldIdProvinciaOfSeCiudadListSeCiudad);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seProvincia.getSeLocalidadClienteList()) {
                SeProvincia oldIdProvinciaOfSeLocalidadClienteListSeLocalidadCliente = seLocalidadClienteListSeLocalidadCliente.getIdProvincia();
                seLocalidadClienteListSeLocalidadCliente.setIdProvincia(seProvincia);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
                if (oldIdProvinciaOfSeLocalidadClienteListSeLocalidadCliente != null) {
                    oldIdProvinciaOfSeLocalidadClienteListSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListSeLocalidadCliente);
                    oldIdProvinciaOfSeLocalidadClienteListSeLocalidadCliente = em.merge(oldIdProvinciaOfSeLocalidadClienteListSeLocalidadCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeProvincia seProvincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeProvincia persistentSeProvincia = em.find(SeProvincia.class, seProvincia.getIdProvincia());
            SePais idPaisOld = persistentSeProvincia.getIdPais();
            SePais idPaisNew = seProvincia.getIdPais();
            List<SeCiudad> seCiudadListOld = persistentSeProvincia.getSeCiudadList();
            List<SeCiudad> seCiudadListNew = seProvincia.getSeCiudadList();
            List<SeLocalidadCliente> seLocalidadClienteListOld = persistentSeProvincia.getSeLocalidadClienteList();
            List<SeLocalidadCliente> seLocalidadClienteListNew = seProvincia.getSeLocalidadClienteList();
            List<String> illegalOrphanMessages = null;
            for (SeCiudad seCiudadListOldSeCiudad : seCiudadListOld) {
                if (!seCiudadListNew.contains(seCiudadListOldSeCiudad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeCiudad " + seCiudadListOldSeCiudad + " since its idProvincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPaisNew != null) {
                idPaisNew = em.getReference(idPaisNew.getClass(), idPaisNew.getIdPais());
                seProvincia.setIdPais(idPaisNew);
            }
            List<SeCiudad> attachedSeCiudadListNew = new ArrayList<SeCiudad>();
            for (SeCiudad seCiudadListNewSeCiudadToAttach : seCiudadListNew) {
                seCiudadListNewSeCiudadToAttach = em.getReference(seCiudadListNewSeCiudadToAttach.getClass(), seCiudadListNewSeCiudadToAttach.getIdCiudad());
                attachedSeCiudadListNew.add(seCiudadListNewSeCiudadToAttach);
            }
            seCiudadListNew = attachedSeCiudadListNew;
            seProvincia.setSeCiudadList(seCiudadListNew);
            List<SeLocalidadCliente> attachedSeLocalidadClienteListNew = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadClienteToAttach : seLocalidadClienteListNew) {
                seLocalidadClienteListNewSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListNewSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListNewSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteListNew.add(seLocalidadClienteListNewSeLocalidadClienteToAttach);
            }
            seLocalidadClienteListNew = attachedSeLocalidadClienteListNew;
            seProvincia.setSeLocalidadClienteList(seLocalidadClienteListNew);
            seProvincia = em.merge(seProvincia);
            if (idPaisOld != null && !idPaisOld.equals(idPaisNew)) {
                idPaisOld.getSeProvinciaList().remove(seProvincia);
                idPaisOld = em.merge(idPaisOld);
            }
            if (idPaisNew != null && !idPaisNew.equals(idPaisOld)) {
                idPaisNew.getSeProvinciaList().add(seProvincia);
                idPaisNew = em.merge(idPaisNew);
            }
            for (SeCiudad seCiudadListNewSeCiudad : seCiudadListNew) {
                if (!seCiudadListOld.contains(seCiudadListNewSeCiudad)) {
                    SeProvincia oldIdProvinciaOfSeCiudadListNewSeCiudad = seCiudadListNewSeCiudad.getIdProvincia();
                    seCiudadListNewSeCiudad.setIdProvincia(seProvincia);
                    seCiudadListNewSeCiudad = em.merge(seCiudadListNewSeCiudad);
                    if (oldIdProvinciaOfSeCiudadListNewSeCiudad != null && !oldIdProvinciaOfSeCiudadListNewSeCiudad.equals(seProvincia)) {
                        oldIdProvinciaOfSeCiudadListNewSeCiudad.getSeCiudadList().remove(seCiudadListNewSeCiudad);
                        oldIdProvinciaOfSeCiudadListNewSeCiudad = em.merge(oldIdProvinciaOfSeCiudadListNewSeCiudad);
                    }
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListOldSeLocalidadCliente : seLocalidadClienteListOld) {
                if (!seLocalidadClienteListNew.contains(seLocalidadClienteListOldSeLocalidadCliente)) {
                    seLocalidadClienteListOldSeLocalidadCliente.setIdProvincia(null);
                    seLocalidadClienteListOldSeLocalidadCliente = em.merge(seLocalidadClienteListOldSeLocalidadCliente);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadCliente : seLocalidadClienteListNew) {
                if (!seLocalidadClienteListOld.contains(seLocalidadClienteListNewSeLocalidadCliente)) {
                    SeProvincia oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente = seLocalidadClienteListNewSeLocalidadCliente.getIdProvincia();
                    seLocalidadClienteListNewSeLocalidadCliente.setIdProvincia(seProvincia);
                    seLocalidadClienteListNewSeLocalidadCliente = em.merge(seLocalidadClienteListNewSeLocalidadCliente);
                    if (oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente != null && !oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente.equals(seProvincia)) {
                        oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListNewSeLocalidadCliente);
                        oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente = em.merge(oldIdProvinciaOfSeLocalidadClienteListNewSeLocalidadCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seProvincia.getIdProvincia();
                if (findSeProvincia(id) == null) {
                    throw new NonexistentEntityException("The seProvincia with id " + id + " no longer exists.");
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
            SeProvincia seProvincia;
            try {
                seProvincia = em.getReference(SeProvincia.class, id);
                seProvincia.getIdProvincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seProvincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SeCiudad> seCiudadListOrphanCheck = seProvincia.getSeCiudadList();
            for (SeCiudad seCiudadListOrphanCheckSeCiudad : seCiudadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeProvincia (" + seProvincia + ") cannot be destroyed since the SeCiudad " + seCiudadListOrphanCheckSeCiudad + " in its seCiudadList field has a non-nullable idProvincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SePais idPais = seProvincia.getIdPais();
            if (idPais != null) {
                idPais.getSeProvinciaList().remove(seProvincia);
                idPais = em.merge(idPais);
            }
            List<SeLocalidadCliente> seLocalidadClienteList = seProvincia.getSeLocalidadClienteList();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seLocalidadClienteList) {
                seLocalidadClienteListSeLocalidadCliente.setIdProvincia(null);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
            }
            em.remove(seProvincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeProvincia> findSeProvinciaEntities() {
        return findSeProvinciaEntities(true, -1, -1);
    }

    public List<SeProvincia> findSeProvinciaEntities(int maxResults, int firstResult) {
        return findSeProvinciaEntities(false, maxResults, firstResult);
    }

    private List<SeProvincia> findSeProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeProvincia.class));
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

    public SeProvincia findSeProvincia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeProvincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeProvincia> rt = cq.from(SeProvincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
