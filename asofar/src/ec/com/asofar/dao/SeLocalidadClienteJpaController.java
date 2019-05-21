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
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeLocalidadCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ms24m
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
            List<SeContactosClientes> seContactosClientesListOld = persistentSeLocalidadCliente.getSeContactosClientesList();
            List<SeContactosClientes> seContactosClientesListNew = seLocalidadCliente.getSeContactosClientesList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdClientes());
                seLocalidadCliente.setIdCliente(idClienteNew);
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
