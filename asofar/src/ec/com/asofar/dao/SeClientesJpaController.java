/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.SeClientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeLocalidadCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class SeClientesJpaController implements Serializable {

    public SeClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeClientes seClientes) throws PreexistingEntityException, Exception {
        if (seClientes.getSeLocalidadClienteList() == null) {
            seClientes.setSeLocalidadClienteList(new ArrayList<SeLocalidadCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SeLocalidadCliente> attachedSeLocalidadClienteList = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadClienteToAttach : seClientes.getSeLocalidadClienteList()) {
                seLocalidadClienteListSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteList.add(seLocalidadClienteListSeLocalidadClienteToAttach);
            }
            seClientes.setSeLocalidadClienteList(attachedSeLocalidadClienteList);
            em.persist(seClientes);
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seClientes.getSeLocalidadClienteList()) {
                SeClientes oldIdClienteOfSeLocalidadClienteListSeLocalidadCliente = seLocalidadClienteListSeLocalidadCliente.getIdCliente();
                seLocalidadClienteListSeLocalidadCliente.setIdCliente(seClientes);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
                if (oldIdClienteOfSeLocalidadClienteListSeLocalidadCliente != null) {
                    oldIdClienteOfSeLocalidadClienteListSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListSeLocalidadCliente);
                    oldIdClienteOfSeLocalidadClienteListSeLocalidadCliente = em.merge(oldIdClienteOfSeLocalidadClienteListSeLocalidadCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeClientes(seClientes.getIdClientes()) != null) {
                throw new PreexistingEntityException("SeClientes " + seClientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeClientes seClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeClientes persistentSeClientes = em.find(SeClientes.class, seClientes.getIdClientes());
            List<SeLocalidadCliente> seLocalidadClienteListOld = persistentSeClientes.getSeLocalidadClienteList();
            List<SeLocalidadCliente> seLocalidadClienteListNew = seClientes.getSeLocalidadClienteList();
            List<SeLocalidadCliente> attachedSeLocalidadClienteListNew = new ArrayList<SeLocalidadCliente>();
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadClienteToAttach : seLocalidadClienteListNew) {
                seLocalidadClienteListNewSeLocalidadClienteToAttach = em.getReference(seLocalidadClienteListNewSeLocalidadClienteToAttach.getClass(), seLocalidadClienteListNewSeLocalidadClienteToAttach.getIdLocalidadCliente());
                attachedSeLocalidadClienteListNew.add(seLocalidadClienteListNewSeLocalidadClienteToAttach);
            }
            seLocalidadClienteListNew = attachedSeLocalidadClienteListNew;
            seClientes.setSeLocalidadClienteList(seLocalidadClienteListNew);
            seClientes = em.merge(seClientes);
            for (SeLocalidadCliente seLocalidadClienteListOldSeLocalidadCliente : seLocalidadClienteListOld) {
                if (!seLocalidadClienteListNew.contains(seLocalidadClienteListOldSeLocalidadCliente)) {
                    seLocalidadClienteListOldSeLocalidadCliente.setIdCliente(null);
                    seLocalidadClienteListOldSeLocalidadCliente = em.merge(seLocalidadClienteListOldSeLocalidadCliente);
                }
            }
            for (SeLocalidadCliente seLocalidadClienteListNewSeLocalidadCliente : seLocalidadClienteListNew) {
                if (!seLocalidadClienteListOld.contains(seLocalidadClienteListNewSeLocalidadCliente)) {
                    SeClientes oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente = seLocalidadClienteListNewSeLocalidadCliente.getIdCliente();
                    seLocalidadClienteListNewSeLocalidadCliente.setIdCliente(seClientes);
                    seLocalidadClienteListNewSeLocalidadCliente = em.merge(seLocalidadClienteListNewSeLocalidadCliente);
                    if (oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente != null && !oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente.equals(seClientes)) {
                        oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente.getSeLocalidadClienteList().remove(seLocalidadClienteListNewSeLocalidadCliente);
                        oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente = em.merge(oldIdClienteOfSeLocalidadClienteListNewSeLocalidadCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seClientes.getIdClientes();
                if (findSeClientes(id) == null) {
                    throw new NonexistentEntityException("The seClientes with id " + id + " no longer exists.");
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
            SeClientes seClientes;
            try {
                seClientes = em.getReference(SeClientes.class, id);
                seClientes.getIdClientes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seClientes with id " + id + " no longer exists.", enfe);
            }
            List<SeLocalidadCliente> seLocalidadClienteList = seClientes.getSeLocalidadClienteList();
            for (SeLocalidadCliente seLocalidadClienteListSeLocalidadCliente : seLocalidadClienteList) {
                seLocalidadClienteListSeLocalidadCliente.setIdCliente(null);
                seLocalidadClienteListSeLocalidadCliente = em.merge(seLocalidadClienteListSeLocalidadCliente);
            }
            em.remove(seClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeClientes> findSeClientesEntities() {
        return findSeClientesEntities(true, -1, -1);
    }

    public List<SeClientes> findSeClientesEntities(int maxResults, int firstResult) {
        return findSeClientesEntities(false, maxResults, firstResult);
    }

    private List<SeClientes> findSeClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeClientes.class));
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

    public SeClientes findSeClientes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeClientes> rt = cq.from(SeClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
