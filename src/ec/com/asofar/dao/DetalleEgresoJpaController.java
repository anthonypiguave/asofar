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
import ec.com.asofar.dto.Producto;
import ec.com.asofar.dto.Cliente;
import ec.com.asofar.dto.DetalleEgreso;
import ec.com.asofar.dto.Egreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class DetalleEgresoJpaController implements Serializable {

    public DetalleEgresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleEgreso detalleEgreso) {
        if (detalleEgreso.getEgresoList() == null) {
            detalleEgreso.setEgresoList(new ArrayList<Egreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idproducto = detalleEgreso.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdProducto());
                detalleEgreso.setIdproducto(idproducto);
            }
            Cliente idcliente = detalleEgreso.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                detalleEgreso.setIdcliente(idcliente);
            }
            List<Egreso> attachedEgresoList = new ArrayList<Egreso>();
            for (Egreso egresoListEgresoToAttach : detalleEgreso.getEgresoList()) {
                egresoListEgresoToAttach = em.getReference(egresoListEgresoToAttach.getClass(), egresoListEgresoToAttach.getIdegreso());
                attachedEgresoList.add(egresoListEgresoToAttach);
            }
            detalleEgreso.setEgresoList(attachedEgresoList);
            em.persist(detalleEgreso);
            if (idproducto != null) {
                idproducto.getDetalleEgresoList().add(detalleEgreso);
                idproducto = em.merge(idproducto);
            }
            if (idcliente != null) {
                idcliente.getDetalleEgresoList().add(detalleEgreso);
                idcliente = em.merge(idcliente);
            }
            for (Egreso egresoListEgreso : detalleEgreso.getEgresoList()) {
                DetalleEgreso oldIddetalleEgresoOfEgresoListEgreso = egresoListEgreso.getIddetalleEgreso();
                egresoListEgreso.setIddetalleEgreso(detalleEgreso);
                egresoListEgreso = em.merge(egresoListEgreso);
                if (oldIddetalleEgresoOfEgresoListEgreso != null) {
                    oldIddetalleEgresoOfEgresoListEgreso.getEgresoList().remove(egresoListEgreso);
                    oldIddetalleEgresoOfEgresoListEgreso = em.merge(oldIddetalleEgresoOfEgresoListEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleEgreso detalleEgreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleEgreso persistentDetalleEgreso = em.find(DetalleEgreso.class, detalleEgreso.getIddetalleEgreso());
            Producto idproductoOld = persistentDetalleEgreso.getIdproducto();
            Producto idproductoNew = detalleEgreso.getIdproducto();
            Cliente idclienteOld = persistentDetalleEgreso.getIdcliente();
            Cliente idclienteNew = detalleEgreso.getIdcliente();
            List<Egreso> egresoListOld = persistentDetalleEgreso.getEgresoList();
            List<Egreso> egresoListNew = detalleEgreso.getEgresoList();
            List<String> illegalOrphanMessages = null;
            for (Egreso egresoListOldEgreso : egresoListOld) {
                if (!egresoListNew.contains(egresoListOldEgreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Egreso " + egresoListOldEgreso + " since its iddetalleEgreso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdProducto());
                detalleEgreso.setIdproducto(idproductoNew);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                detalleEgreso.setIdcliente(idclienteNew);
            }
            List<Egreso> attachedEgresoListNew = new ArrayList<Egreso>();
            for (Egreso egresoListNewEgresoToAttach : egresoListNew) {
                egresoListNewEgresoToAttach = em.getReference(egresoListNewEgresoToAttach.getClass(), egresoListNewEgresoToAttach.getIdegreso());
                attachedEgresoListNew.add(egresoListNewEgresoToAttach);
            }
            egresoListNew = attachedEgresoListNew;
            detalleEgreso.setEgresoList(egresoListNew);
            detalleEgreso = em.merge(detalleEgreso);
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetalleEgresoList().remove(detalleEgreso);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetalleEgresoList().add(detalleEgreso);
                idproductoNew = em.merge(idproductoNew);
            }
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getDetalleEgresoList().remove(detalleEgreso);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getDetalleEgresoList().add(detalleEgreso);
                idclienteNew = em.merge(idclienteNew);
            }
            for (Egreso egresoListNewEgreso : egresoListNew) {
                if (!egresoListOld.contains(egresoListNewEgreso)) {
                    DetalleEgreso oldIddetalleEgresoOfEgresoListNewEgreso = egresoListNewEgreso.getIddetalleEgreso();
                    egresoListNewEgreso.setIddetalleEgreso(detalleEgreso);
                    egresoListNewEgreso = em.merge(egresoListNewEgreso);
                    if (oldIddetalleEgresoOfEgresoListNewEgreso != null && !oldIddetalleEgresoOfEgresoListNewEgreso.equals(detalleEgreso)) {
                        oldIddetalleEgresoOfEgresoListNewEgreso.getEgresoList().remove(egresoListNewEgreso);
                        oldIddetalleEgresoOfEgresoListNewEgreso = em.merge(oldIddetalleEgresoOfEgresoListNewEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleEgreso.getIddetalleEgreso();
                if (findDetalleEgreso(id) == null) {
                    throw new NonexistentEntityException("The detalleEgreso with id " + id + " no longer exists.");
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
            DetalleEgreso detalleEgreso;
            try {
                detalleEgreso = em.getReference(DetalleEgreso.class, id);
                detalleEgreso.getIddetalleEgreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleEgreso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Egreso> egresoListOrphanCheck = detalleEgreso.getEgresoList();
            for (Egreso egresoListOrphanCheckEgreso : egresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DetalleEgreso (" + detalleEgreso + ") cannot be destroyed since the Egreso " + egresoListOrphanCheckEgreso + " in its egresoList field has a non-nullable iddetalleEgreso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producto idproducto = detalleEgreso.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetalleEgresoList().remove(detalleEgreso);
                idproducto = em.merge(idproducto);
            }
            Cliente idcliente = detalleEgreso.getIdcliente();
            if (idcliente != null) {
                idcliente.getDetalleEgresoList().remove(detalleEgreso);
                idcliente = em.merge(idcliente);
            }
            em.remove(detalleEgreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleEgreso> findDetalleEgresoEntities() {
        return findDetalleEgresoEntities(true, -1, -1);
    }

    public List<DetalleEgreso> findDetalleEgresoEntities(int maxResults, int firstResult) {
        return findDetalleEgresoEntities(false, maxResults, firstResult);
    }

    private List<DetalleEgreso> findDetalleEgresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleEgreso.class));
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

    public DetalleEgreso findDetalleEgreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleEgreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleEgresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleEgreso> rt = cq.from(DetalleEgreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
