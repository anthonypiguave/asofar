/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeCiudad;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeLocalidadCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class SeLocalidadClienteJpaController implements Serializable {

    public SeLocalidadClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeLocalidadCliente seLocalidadCliente) {
        if (seLocalidadCliente.getSeContactosClientesList() == null) {
            seLocalidadCliente.setSeContactosClientesList(new ArrayList<SeContactosClientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeClientes idCliente = seLocalidadCliente.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdClientes());
                seLocalidadCliente.setIdCliente(idCliente);
            }
            SeCiudad idCiudad = seLocalidadCliente.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                seLocalidadCliente.setIdCiudad(idCiudad);
            }
            SeProvincia idProvincia = seLocalidadCliente.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                seLocalidadCliente.setIdProvincia(idProvincia);
            }
            SePais idPais = seLocalidadCliente.getIdPais();
            if (idPais != null) {
                idPais = em.getReference(idPais.getClass(), idPais.getIdPais());
                seLocalidadCliente.setIdPais(idPais);
            }
            List<SeContactosClientes> attachedSeContactosClientesList = new ArrayList<SeContactosClientes>();
            for (SeContactosClientes seContactosClientesListSeContactosClientesToAttach : seLocalidadCliente.getSeContactosClientesList()) {
                seContactosClientesListSeContactosClientesToAttach = em.getReference(seContactosClientesListSeContactosClientesToAttach.getClass(), seContactosClientesListSeContactosClientesToAttach.getIdContactosClientes());
                attachedSeContactosClientesList.add(seContactosClientesListSeContactosClientesToAttach);
            }
            seLocalidadCliente.setSeContactosClientesList(attachedSeContactosClientesList);
            em.persist(seLocalidadCliente);
            if (idCliente != null) {
                idCliente.getSeLocalidadClienteList().add(seLocalidadCliente);
                idCliente = em.merge(idCliente);
            }
            if (idCiudad != null) {
                idCiudad.getSeLocalidadClienteList().add(seLocalidadCliente);
                idCiudad = em.merge(idCiudad);
            }
            if (idProvincia != null) {
                idProvincia.getSeLocalidadClienteList().add(seLocalidadCliente);
                idProvincia = em.merge(idProvincia);
            }
            if (idPais != null) {
                idPais.getSeLocalidadClienteList().add(seLocalidadCliente);
                idPais = em.merge(idPais);
            }
            for (SeContactosClientes seContactosClientesListSeContactosClientes : seLocalidadCliente.getSeContactosClientesList()) {
                SeLocalidadCliente oldIdLocalidadOfSeContactosClientesListSeContactosClientes = seContactosClientesListSeContactosClientes.getIdLocalidad();
                seContactosClientesListSeContactosClientes.setIdLocalidad(seLocalidadCliente);
                seContactosClientesListSeContactosClientes = em.merge(seContactosClientesListSeContactosClientes);
                if (oldIdLocalidadOfSeContactosClientesListSeContactosClientes != null) {
                    oldIdLocalidadOfSeContactosClientesListSeContactosClientes.getSeContactosClientesList().remove(seContactosClientesListSeContactosClientes);
                    oldIdLocalidadOfSeContactosClientesListSeContactosClientes = em.merge(oldIdLocalidadOfSeContactosClientesListSeContactosClientes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeLocalidadCliente seLocalidadCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeLocalidadCliente persistentSeLocalidadCliente = em.find(SeLocalidadCliente.class, seLocalidadCliente.getIdLocalidadCliente());
            SeClientes idClienteOld = persistentSeLocalidadCliente.getIdCliente();
            SeClientes idClienteNew = seLocalidadCliente.getIdCliente();
            SeCiudad idCiudadOld = persistentSeLocalidadCliente.getIdCiudad();
            SeCiudad idCiudadNew = seLocalidadCliente.getIdCiudad();
            SeProvincia idProvinciaOld = persistentSeLocalidadCliente.getIdProvincia();
            SeProvincia idProvinciaNew = seLocalidadCliente.getIdProvincia();
            SePais idPaisOld = persistentSeLocalidadCliente.getIdPais();
            SePais idPaisNew = seLocalidadCliente.getIdPais();
            List<SeContactosClientes> seContactosClientesListOld = persistentSeLocalidadCliente.getSeContactosClientesList();
            List<SeContactosClientes> seContactosClientesListNew = seLocalidadCliente.getSeContactosClientesList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdClientes());
                seLocalidadCliente.setIdCliente(idClienteNew);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                seLocalidadCliente.setIdCiudad(idCiudadNew);
            }
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                seLocalidadCliente.setIdProvincia(idProvinciaNew);
            }
            if (idPaisNew != null) {
                idPaisNew = em.getReference(idPaisNew.getClass(), idPaisNew.getIdPais());
                seLocalidadCliente.setIdPais(idPaisNew);
            }
            List<SeContactosClientes> attachedSeContactosClientesListNew = new ArrayList<SeContactosClientes>();
            for (SeContactosClientes seContactosClientesListNewSeContactosClientesToAttach : seContactosClientesListNew) {
                seContactosClientesListNewSeContactosClientesToAttach = em.getReference(seContactosClientesListNewSeContactosClientesToAttach.getClass(), seContactosClientesListNewSeContactosClientesToAttach.getIdContactosClientes());
                attachedSeContactosClientesListNew.add(seContactosClientesListNewSeContactosClientesToAttach);
            }
            seContactosClientesListNew = attachedSeContactosClientesListNew;
            seLocalidadCliente.setSeContactosClientesList(seContactosClientesListNew);
            seLocalidadCliente = em.merge(seLocalidadCliente);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getSeLocalidadClienteList().add(seLocalidadCliente);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getSeLocalidadClienteList().add(seLocalidadCliente);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getSeLocalidadClienteList().add(seLocalidadCliente);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            if (idPaisOld != null && !idPaisOld.equals(idPaisNew)) {
                idPaisOld.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idPaisOld = em.merge(idPaisOld);
            }
            if (idPaisNew != null && !idPaisNew.equals(idPaisOld)) {
                idPaisNew.getSeLocalidadClienteList().add(seLocalidadCliente);
                idPaisNew = em.merge(idPaisNew);
            }
            for (SeContactosClientes seContactosClientesListOldSeContactosClientes : seContactosClientesListOld) {
                if (!seContactosClientesListNew.contains(seContactosClientesListOldSeContactosClientes)) {
                    seContactosClientesListOldSeContactosClientes.setIdLocalidad(null);
                    seContactosClientesListOldSeContactosClientes = em.merge(seContactosClientesListOldSeContactosClientes);
                }
            }
            for (SeContactosClientes seContactosClientesListNewSeContactosClientes : seContactosClientesListNew) {
                if (!seContactosClientesListOld.contains(seContactosClientesListNewSeContactosClientes)) {
                    SeLocalidadCliente oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes = seContactosClientesListNewSeContactosClientes.getIdLocalidad();
                    seContactosClientesListNewSeContactosClientes.setIdLocalidad(seLocalidadCliente);
                    seContactosClientesListNewSeContactosClientes = em.merge(seContactosClientesListNewSeContactosClientes);
                    if (oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes != null && !oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes.equals(seLocalidadCliente)) {
                        oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes.getSeContactosClientesList().remove(seContactosClientesListNewSeContactosClientes);
                        oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes = em.merge(oldIdLocalidadOfSeContactosClientesListNewSeContactosClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seLocalidadCliente.getIdLocalidadCliente();
                if (findSeLocalidadCliente(id) == null) {
                    throw new NonexistentEntityException("The seLocalidadCliente with id " + id + " no longer exists.");
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
            SeLocalidadCliente seLocalidadCliente;
            try {
                seLocalidadCliente = em.getReference(SeLocalidadCliente.class, id);
                seLocalidadCliente.getIdLocalidadCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seLocalidadCliente with id " + id + " no longer exists.", enfe);
            }
            SeClientes idCliente = seLocalidadCliente.getIdCliente();
            if (idCliente != null) {
                idCliente.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idCliente = em.merge(idCliente);
            }
            SeCiudad idCiudad = seLocalidadCliente.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idCiudad = em.merge(idCiudad);
            }
            SeProvincia idProvincia = seLocalidadCliente.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idProvincia = em.merge(idProvincia);
            }
            SePais idPais = seLocalidadCliente.getIdPais();
            if (idPais != null) {
                idPais.getSeLocalidadClienteList().remove(seLocalidadCliente);
                idPais = em.merge(idPais);
            }
            List<SeContactosClientes> seContactosClientesList = seLocalidadCliente.getSeContactosClientesList();
            for (SeContactosClientes seContactosClientesListSeContactosClientes : seContactosClientesList) {
                seContactosClientesListSeContactosClientes.setIdLocalidad(null);
                seContactosClientesListSeContactosClientes = em.merge(seContactosClientesListSeContactosClientes);
            }
            em.remove(seLocalidadCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeLocalidadCliente> findSeLocalidadClienteEntities() {
        return findSeLocalidadClienteEntities(true, -1, -1);
    }

    public List<SeLocalidadCliente> findSeLocalidadClienteEntities(int maxResults, int firstResult) {
        return findSeLocalidadClienteEntities(false, maxResults, firstResult);
    }

    private List<SeLocalidadCliente> findSeLocalidadClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeLocalidadCliente.class));
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

    public SeLocalidadCliente findSeLocalidadCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeLocalidadCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeLocalidadClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeLocalidadCliente> rt = cq.from(SeLocalidadCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
