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
import ec.com.asofar.dto.Administrador;
import ec.com.asofar.dto.DetalleIngreso;
import ec.com.asofar.dto.Ingreso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class DetalleIngresoJpaController implements Serializable {

    public DetalleIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleIngreso detalleIngreso) {
        if (detalleIngreso.getIngresoList() == null) {
            detalleIngreso.setIngresoList(new ArrayList<Ingreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = detalleIngreso.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                detalleIngreso.setIdProducto(idProducto);
            }
            Administrador idadministrador = detalleIngreso.getIdadministrador();
            if (idadministrador != null) {
                idadministrador = em.getReference(idadministrador.getClass(), idadministrador.getIdadministrador());
                detalleIngreso.setIdadministrador(idadministrador);
            }
            List<Ingreso> attachedIngresoList = new ArrayList<Ingreso>();
            for (Ingreso ingresoListIngresoToAttach : detalleIngreso.getIngresoList()) {
                ingresoListIngresoToAttach = em.getReference(ingresoListIngresoToAttach.getClass(), ingresoListIngresoToAttach.getIdingreso());
                attachedIngresoList.add(ingresoListIngresoToAttach);
            }
            detalleIngreso.setIngresoList(attachedIngresoList);
            em.persist(detalleIngreso);
            if (idProducto != null) {
                idProducto.getDetalleIngresoList().add(detalleIngreso);
                idProducto = em.merge(idProducto);
            }
            if (idadministrador != null) {
                idadministrador.getDetalleIngresoList().add(detalleIngreso);
                idadministrador = em.merge(idadministrador);
            }
            for (Ingreso ingresoListIngreso : detalleIngreso.getIngresoList()) {
                DetalleIngreso oldIddetalleIngresoOfIngresoListIngreso = ingresoListIngreso.getIddetalleIngreso();
                ingresoListIngreso.setIddetalleIngreso(detalleIngreso);
                ingresoListIngreso = em.merge(ingresoListIngreso);
                if (oldIddetalleIngresoOfIngresoListIngreso != null) {
                    oldIddetalleIngresoOfIngresoListIngreso.getIngresoList().remove(ingresoListIngreso);
                    oldIddetalleIngresoOfIngresoListIngreso = em.merge(oldIddetalleIngresoOfIngresoListIngreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleIngreso detalleIngreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleIngreso persistentDetalleIngreso = em.find(DetalleIngreso.class, detalleIngreso.getIddetalleIngreso());
            Producto idProductoOld = persistentDetalleIngreso.getIdProducto();
            Producto idProductoNew = detalleIngreso.getIdProducto();
            Administrador idadministradorOld = persistentDetalleIngreso.getIdadministrador();
            Administrador idadministradorNew = detalleIngreso.getIdadministrador();
            List<Ingreso> ingresoListOld = persistentDetalleIngreso.getIngresoList();
            List<Ingreso> ingresoListNew = detalleIngreso.getIngresoList();
            List<String> illegalOrphanMessages = null;
            for (Ingreso ingresoListOldIngreso : ingresoListOld) {
                if (!ingresoListNew.contains(ingresoListOldIngreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ingreso " + ingresoListOldIngreso + " since its iddetalleIngreso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                detalleIngreso.setIdProducto(idProductoNew);
            }
            if (idadministradorNew != null) {
                idadministradorNew = em.getReference(idadministradorNew.getClass(), idadministradorNew.getIdadministrador());
                detalleIngreso.setIdadministrador(idadministradorNew);
            }
            List<Ingreso> attachedIngresoListNew = new ArrayList<Ingreso>();
            for (Ingreso ingresoListNewIngresoToAttach : ingresoListNew) {
                ingresoListNewIngresoToAttach = em.getReference(ingresoListNewIngresoToAttach.getClass(), ingresoListNewIngresoToAttach.getIdingreso());
                attachedIngresoListNew.add(ingresoListNewIngresoToAttach);
            }
            ingresoListNew = attachedIngresoListNew;
            detalleIngreso.setIngresoList(ingresoListNew);
            detalleIngreso = em.merge(detalleIngreso);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDetalleIngresoList().remove(detalleIngreso);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDetalleIngresoList().add(detalleIngreso);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idadministradorOld != null && !idadministradorOld.equals(idadministradorNew)) {
                idadministradorOld.getDetalleIngresoList().remove(detalleIngreso);
                idadministradorOld = em.merge(idadministradorOld);
            }
            if (idadministradorNew != null && !idadministradorNew.equals(idadministradorOld)) {
                idadministradorNew.getDetalleIngresoList().add(detalleIngreso);
                idadministradorNew = em.merge(idadministradorNew);
            }
            for (Ingreso ingresoListNewIngreso : ingresoListNew) {
                if (!ingresoListOld.contains(ingresoListNewIngreso)) {
                    DetalleIngreso oldIddetalleIngresoOfIngresoListNewIngreso = ingresoListNewIngreso.getIddetalleIngreso();
                    ingresoListNewIngreso.setIddetalleIngreso(detalleIngreso);
                    ingresoListNewIngreso = em.merge(ingresoListNewIngreso);
                    if (oldIddetalleIngresoOfIngresoListNewIngreso != null && !oldIddetalleIngresoOfIngresoListNewIngreso.equals(detalleIngreso)) {
                        oldIddetalleIngresoOfIngresoListNewIngreso.getIngresoList().remove(ingresoListNewIngreso);
                        oldIddetalleIngresoOfIngresoListNewIngreso = em.merge(oldIddetalleIngresoOfIngresoListNewIngreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleIngreso.getIddetalleIngreso();
                if (findDetalleIngreso(id) == null) {
                    throw new NonexistentEntityException("The detalleIngreso with id " + id + " no longer exists.");
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
            DetalleIngreso detalleIngreso;
            try {
                detalleIngreso = em.getReference(DetalleIngreso.class, id);
                detalleIngreso.getIddetalleIngreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleIngreso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ingreso> ingresoListOrphanCheck = detalleIngreso.getIngresoList();
            for (Ingreso ingresoListOrphanCheckIngreso : ingresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DetalleIngreso (" + detalleIngreso + ") cannot be destroyed since the Ingreso " + ingresoListOrphanCheckIngreso + " in its ingresoList field has a non-nullable iddetalleIngreso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producto idProducto = detalleIngreso.getIdProducto();
            if (idProducto != null) {
                idProducto.getDetalleIngresoList().remove(detalleIngreso);
                idProducto = em.merge(idProducto);
            }
            Administrador idadministrador = detalleIngreso.getIdadministrador();
            if (idadministrador != null) {
                idadministrador.getDetalleIngresoList().remove(detalleIngreso);
                idadministrador = em.merge(idadministrador);
            }
            em.remove(detalleIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleIngreso> findDetalleIngresoEntities() {
        return findDetalleIngresoEntities(true, -1, -1);
    }

    public List<DetalleIngreso> findDetalleIngresoEntities(int maxResults, int firstResult) {
        return findDetalleIngresoEntities(false, maxResults, firstResult);
    }

    private List<DetalleIngreso> findDetalleIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleIngreso.class));
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

    public DetalleIngreso findDetalleIngreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleIngreso> rt = cq.from(DetalleIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
