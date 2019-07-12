/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.SeCiudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SeLocalidadCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class SeCiudadJpaController implements Serializable {

    public SeCiudadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeCiudad seCiudad) {
        if (seCiudad.getSeLocalidadClienteList() == null) {
            seCiudad.setSeLocalidadClienteList(new ArrayList<SeLocalidadCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeProvincia idProvincia = seCiudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                seCiudad.setIdProvincia(idProvincia);
            }
            List<SeLocalidadCliente> attachedSeLocalidadClienteList = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadClienteToAttach : seCiudad.getSeLocalidadClienteList()) {
                seLocalidadClienteListSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteList.add(seLocalidadClienteListSeLocalidadClienteToAttach);
            }
            seCiudad.setSeLocalidadClienteList(attachedSeLocalidadClienteList);
            em.persist(seCiudad);
            if (idProvincia != null) {
                idProvincia.getSeCiudadList().add(seCiudad);
                idProvincia = em.merge(idProvincia);
            }
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seCiudad.getSeLocalidadClienteList()) {
                SeCiudad oldIdCiudadOfSeLocalidadClienteListSeLocalidadCliente = seLocalidadClienteListSeLocalidadCliente.getIdCiudad();
                seLocalidadClienteListSeLocalidadCliente.setIdCiudad(seCiudad);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
                if (oldIdCiudadOfSeLocalidadClienteListSeLocalidadCliente != null) {
                    oldIdCiudadOfSeLocalidadClienteListSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListSeLocalidadCliente);
                    oldIdCiudadOfSeLocalidadClienteListSeLocalidadCliente = em.merge(oldIdCiudadOfSeLocalidadClienteListSeLocalidadCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeCiudad seCiudad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeCiudad persistentSeCiudad = em.find(SeCiudad.class, seCiudad.getIdCiudad());
            SeProvincia idProvinciaOld = persistentSeCiudad.getIdProvincia();
            SeProvincia idProvinciaNew = seCiudad.getIdProvincia();
            List<SeLocalidadCliente> seLocalidadClienteListOld = persistentSeCiudad.getSeLocalidadClienteList();
            List<SeLocalidadCliente> seLocalidadClienteListNew = seCiudad.getSeLocalidadClienteList();
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                seCiudad.setIdProvincia(idProvinciaNew);
            }
            List<SeLocalidadCliente> attachedSeLocalidadClienteListNew = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadClienteToAttach : seLocalidadClienteListNew) {
                seLocalidadClienteListNewSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListNewSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListNewSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteListNew.add(seLocalidadClienteListNewSeLocalidadClienteToAttach);
            }
            seLocalidadClienteListNew = attachedSeLocalidadClienteListNew;
            seCiudad.setSeLocalidadClienteList(seLocalidadClienteListNew);
            seCiudad = em.merge(seCiudad);
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getSeCiudadList().remove(seCiudad);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getSeCiudadList().add(seCiudad);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            for (SeLocalidadCliente seLocalidadClienteListOldSeLocalidadCliente : seLocalidadClienteListOld) {
                if (!seLocalidadClienteListNew.contains(seLocalidadClienteListOldSeLocalidadCliente)) {
                    seLocalidadClienteListOldSeLocalidadCliente.setIdCiudad(null);
                    seLocalidadClienteListOldSeLocalidadCliente = em.merge(seLocalidadClienteListOldSeLocalidadCliente);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadCliente : seLocalidadClienteListNew) {
                if (!seLocalidadClienteListOld.contains(seLocalidadClienteListNewSeLocalidadCliente)) {
                    SeCiudad oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente = seLocalidadClienteListNewSeLocalidadCliente.getIdCiudad();
                    seLocalidadClienteListNewSeLocalidadCliente.setIdCiudad(seCiudad);
                    seLocalidadClienteListNewSeLocalidadCliente = em.merge(seLocalidadClienteListNewSeLocalidadCliente);
                    if (oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente != null && !oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente.equals(seCiudad)) {
                        oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListNewSeLocalidadCliente);
                        oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente = em.merge(oldIdCiudadOfSeLocalidadClienteListNewSeLocalidadCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seCiudad.getIdCiudad();
                if (findSeCiudad(id) == null) {
                    throw new NonexistentEntityException("The seCiudad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeCiudad seCiudad;
            try {
                seCiudad = em.getReference(SeCiudad.class, id);
                seCiudad.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seCiudad with id " + id + " no longer exists.", enfe);
            }
            SeProvincia idProvincia = seCiudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getSeCiudadList().remove(seCiudad);
                idProvincia = em.merge(idProvincia);
            }
            List<SeLocalidadCliente> seLocalidadClienteList = seCiudad.getSeLocalidadClienteList();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seLocalidadClienteList) {
                seLocalidadClienteListSeLocalidadCliente.setIdCiudad(null);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
            }
            em.remove(seCiudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeCiudad> findSeCiudadEntities() {
        return findSeCiudadEntities(true, -1, -1);
    }

    public List<SeCiudad> findSeCiudadEntities(int maxResults, int firstResult) {
        return findSeCiudadEntities(false, maxResults, firstResult);
    }

    private List<SeCiudad> findSeCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeCiudad.class));
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

    public SeCiudad findSeCiudad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeCiudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeCiudad> rt = cq.from(SeCiudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
