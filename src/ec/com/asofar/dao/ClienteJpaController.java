/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.Factura;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.DetalleEgreso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getFacturaList() == null) {
            cliente.setFacturaList(new ArrayList<Factura>());
        }
        if (cliente.getDetalleEgresoList() == null) {
            cliente.setDetalleEgresoList(new ArrayList<DetalleEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : cliente.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            cliente.setFacturaList(attachedFacturaList);
            List<DetalleEgreso> attachedDetalleEgresoList = new ArrayList<DetalleEgreso>();
            for (DetalleEgreso detalleEgresoListDetalleEgresoToAttach : cliente.getDetalleEgresoList()) {
                detalleEgresoListDetalleEgresoToAttach = em.getReference(detalleEgresoListDetalleEgresoToAttach.getClass(), detalleEgresoListDetalleEgresoToAttach.getIddetalleEgreso());
                attachedDetalleEgresoList.add(detalleEgresoListDetalleEgresoToAttach);
            }
            cliente.setDetalleEgresoList(attachedDetalleEgresoList);
            em.persist(cliente);
            for (Factura facturaListFactura : cliente.getFacturaList()) {
                Cliente oldIdclienteOfFacturaListFactura = facturaListFactura.getIdcliente();
                facturaListFactura.setIdcliente(cliente);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdclienteOfFacturaListFactura != null) {
                    oldIdclienteOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdclienteOfFacturaListFactura = em.merge(oldIdclienteOfFacturaListFactura);
                }
            }
            for (DetalleEgreso detalleEgresoListDetalleEgreso : cliente.getDetalleEgresoList()) {
                Cliente oldIdclienteOfDetalleEgresoListDetalleEgreso = detalleEgresoListDetalleEgreso.getIdcliente();
                detalleEgresoListDetalleEgreso.setIdcliente(cliente);
                detalleEgresoListDetalleEgreso = em.merge(detalleEgresoListDetalleEgreso);
                if (oldIdclienteOfDetalleEgresoListDetalleEgreso != null) {
                    oldIdclienteOfDetalleEgresoListDetalleEgreso.getDetalleEgresoList().remove(detalleEgresoListDetalleEgreso);
                    oldIdclienteOfDetalleEgresoListDetalleEgreso = em.merge(oldIdclienteOfDetalleEgresoListDetalleEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            List<Factura> facturaListOld = persistentCliente.getFacturaList();
            List<Factura> facturaListNew = cliente.getFacturaList();
            List<DetalleEgreso> detalleEgresoListOld = persistentCliente.getDetalleEgresoList();
            List<DetalleEgreso> detalleEgresoListNew = cliente.getDetalleEgresoList();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idcliente field is not nullable.");
                }
            }
            for (DetalleEgreso detalleEgresoListOldDetalleEgreso : detalleEgresoListOld) {
                if (!detalleEgresoListNew.contains(detalleEgresoListOldDetalleEgreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleEgreso " + detalleEgresoListOldDetalleEgreso + " since its idcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            cliente.setFacturaList(facturaListNew);
            List<DetalleEgreso> attachedDetalleEgresoListNew = new ArrayList<DetalleEgreso>();
            for (DetalleEgreso detalleEgresoListNewDetalleEgresoToAttach : detalleEgresoListNew) {
                detalleEgresoListNewDetalleEgresoToAttach = em.getReference(detalleEgresoListNewDetalleEgresoToAttach.getClass(), detalleEgresoListNewDetalleEgresoToAttach.getIddetalleEgreso());
                attachedDetalleEgresoListNew.add(detalleEgresoListNewDetalleEgresoToAttach);
            }
            detalleEgresoListNew = attachedDetalleEgresoListNew;
            cliente.setDetalleEgresoList(detalleEgresoListNew);
            cliente = em.merge(cliente);
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Cliente oldIdclienteOfFacturaListNewFactura = facturaListNewFactura.getIdcliente();
                    facturaListNewFactura.setIdcliente(cliente);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdclienteOfFacturaListNewFactura != null && !oldIdclienteOfFacturaListNewFactura.equals(cliente)) {
                        oldIdclienteOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdclienteOfFacturaListNewFactura = em.merge(oldIdclienteOfFacturaListNewFactura);
                    }
                }
            }
            for (DetalleEgreso detalleEgresoListNewDetalleEgreso : detalleEgresoListNew) {
                if (!detalleEgresoListOld.contains(detalleEgresoListNewDetalleEgreso)) {
                    Cliente oldIdclienteOfDetalleEgresoListNewDetalleEgreso = detalleEgresoListNewDetalleEgreso.getIdcliente();
                    detalleEgresoListNewDetalleEgreso.setIdcliente(cliente);
                    detalleEgresoListNewDetalleEgreso = em.merge(detalleEgresoListNewDetalleEgreso);
                    if (oldIdclienteOfDetalleEgresoListNewDetalleEgreso != null && !oldIdclienteOfDetalleEgresoListNewDetalleEgreso.equals(cliente)) {
                        oldIdclienteOfDetalleEgresoListNewDetalleEgreso.getDetalleEgresoList().remove(detalleEgresoListNewDetalleEgreso);
                        oldIdclienteOfDetalleEgresoListNewDetalleEgreso = em.merge(oldIdclienteOfDetalleEgresoListNewDetalleEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Factura> facturaListOrphanCheck = cliente.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idcliente field.");
            }
            List<DetalleEgreso> detalleEgresoListOrphanCheck = cliente.getDetalleEgresoList();
            for (DetalleEgreso detalleEgresoListOrphanCheckDetalleEgreso : detalleEgresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the DetalleEgreso " + detalleEgresoListOrphanCheckDetalleEgreso + " in its detalleEgresoList field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
