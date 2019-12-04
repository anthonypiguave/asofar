/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.SeContactosClientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeLocalidadCliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class SeContactosClientesJpaController implements Serializable {

    public SeContactosClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeContactosClientes seContactosClientes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeLocalidadCliente idLocalidad = seContactosClientes.getIdLocalidad();
            if (idLocalidad != null) {
                idLocalidad = em.getReference(idLocalidad.getClass(), idLocalidad.getIdLocalidadCliente());
                seContactosClientes.setIdLocalidad(idLocalidad);
            }
            em.persist(seContactosClientes);
            if (idLocalidad != null) {
                idLocalidad.getSeContactosClientesList().add(seContactosClientes);
                idLocalidad = em.merge(idLocalidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeContactosClientes seContactosClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeContactosClientes persistentSeContactosClientes = em.find(SeContactosClientes.class, seContactosClientes.getIdContactosClientes());
            SeLocalidadCliente idLocalidadOld = persistentSeContactosClientes.getIdLocalidad();
            SeLocalidadCliente idLocalidadNew = seContactosClientes.getIdLocalidad();
            if (idLocalidadNew != null) {
                idLocalidadNew = em.getReference(idLocalidadNew.getClass(), idLocalidadNew.getIdLocalidadCliente());
                seContactosClientes.setIdLocalidad(idLocalidadNew);
            }
            seContactosClientes = em.merge(seContactosClientes);
            if (idLocalidadOld != null && !idLocalidadOld.equals(idLocalidadNew)) {
                idLocalidadOld.getSeContactosClientesList().remove(seContactosClientes);
                idLocalidadOld = em.merge(idLocalidadOld);
            }
            if (idLocalidadNew != null && !idLocalidadNew.equals(idLocalidadOld)) {
                idLocalidadNew.getSeContactosClientesList().add(seContactosClientes);
                idLocalidadNew = em.merge(idLocalidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seContactosClientes.getIdContactosClientes();
                if (findSeContactosClientes(id) == null) {
                    throw new NonexistentEntityException("The seContactosClientes with id " + id + " no longer exists.");
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
            SeContactosClientes seContactosClientes;
            try {
                seContactosClientes = em.getReference(SeContactosClientes.class, id);
                seContactosClientes.getIdContactosClientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seContactosClientes with id " + id + " no longer exists.", enfe);
            }
            SeLocalidadCliente idLocalidad = seContactosClientes.getIdLocalidad();
            if (idLocalidad != null) {
                idLocalidad.getSeContactosClientesList().remove(seContactosClientes);
                idLocalidad = em.merge(idLocalidad);
            }
            em.remove(seContactosClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeContactosClientes> findSeContactosClientesEntities() {
        return findSeContactosClientesEntities(true, -1, -1);
    }

    public List<SeContactosClientes> findSeContactosClientesEntities(int maxResults, int firstResult) {
        return findSeContactosClientesEntities(false, maxResults, firstResult);
    }

    private List<SeContactosClientes> findSeContactosClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeContactosClientes.class));
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

    public SeContactosClientes findSeContactosClientes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeContactosClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeContactosClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeContactosClientes> rt = cq.from(SeContactosClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
