/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.Administrador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.DetalleIngreso;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.Producto;
import ec.com.asofar.dto.Historial;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) {
        if (administrador.getDetalleIngresoList() == null) {
            administrador.setDetalleIngresoList(new ArrayList<DetalleIngreso>());
        }
        if (administrador.getProductoList() == null) {
            administrador.setProductoList(new ArrayList<Producto>());
        }
        if (administrador.getHistorialList() == null) {
            administrador.setHistorialList(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleIngreso> attachedDetalleIngresoList = new ArrayList<DetalleIngreso>();
            for (DetalleIngreso detalleIngresoListDetalleIngresoToAttach : administrador.getDetalleIngresoList()) {
                detalleIngresoListDetalleIngresoToAttach = em.getReference(detalleIngresoListDetalleIngresoToAttach.getClass(), detalleIngresoListDetalleIngresoToAttach.getIddetalleIngreso());
                attachedDetalleIngresoList.add(detalleIngresoListDetalleIngresoToAttach);
            }
            administrador.setDetalleIngresoList(attachedDetalleIngresoList);
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : administrador.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            administrador.setProductoList(attachedProductoList);
            List<Historial> attachedHistorialList = new ArrayList<Historial>();
            for (Historial historialListHistorialToAttach : administrador.getHistorialList()) {
                historialListHistorialToAttach = em.getReference(historialListHistorialToAttach.getClass(), historialListHistorialToAttach.getIdhistorial());
                attachedHistorialList.add(historialListHistorialToAttach);
            }
            administrador.setHistorialList(attachedHistorialList);
            em.persist(administrador);
            for (DetalleIngreso detalleIngresoListDetalleIngreso : administrador.getDetalleIngresoList()) {
                Administrador oldIdadministradorOfDetalleIngresoListDetalleIngreso = detalleIngresoListDetalleIngreso.getIdadministrador();
                detalleIngresoListDetalleIngreso.setIdadministrador(administrador);
                detalleIngresoListDetalleIngreso = em.merge(detalleIngresoListDetalleIngreso);
                if (oldIdadministradorOfDetalleIngresoListDetalleIngreso != null) {
                    oldIdadministradorOfDetalleIngresoListDetalleIngreso.getDetalleIngresoList().remove(detalleIngresoListDetalleIngreso);
                    oldIdadministradorOfDetalleIngresoListDetalleIngreso = em.merge(oldIdadministradorOfDetalleIngresoListDetalleIngreso);
                }
            }
            for (Producto productoListProducto : administrador.getProductoList()) {
                Administrador oldIdadministradorOfProductoListProducto = productoListProducto.getIdadministrador();
                productoListProducto.setIdadministrador(administrador);
                productoListProducto = em.merge(productoListProducto);
                if (oldIdadministradorOfProductoListProducto != null) {
                    oldIdadministradorOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldIdadministradorOfProductoListProducto = em.merge(oldIdadministradorOfProductoListProducto);
                }
            }
            for (Historial historialListHistorial : administrador.getHistorialList()) {
                Administrador oldIdadministradorOfHistorialListHistorial = historialListHistorial.getIdadministrador();
                historialListHistorial.setIdadministrador(administrador);
                historialListHistorial = em.merge(historialListHistorial);
                if (oldIdadministradorOfHistorialListHistorial != null) {
                    oldIdadministradorOfHistorialListHistorial.getHistorialList().remove(historialListHistorial);
                    oldIdadministradorOfHistorialListHistorial = em.merge(oldIdadministradorOfHistorialListHistorial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getIdadministrador());
            List<DetalleIngreso> detalleIngresoListOld = persistentAdministrador.getDetalleIngresoList();
            List<DetalleIngreso> detalleIngresoListNew = administrador.getDetalleIngresoList();
            List<Producto> productoListOld = persistentAdministrador.getProductoList();
            List<Producto> productoListNew = administrador.getProductoList();
            List<Historial> historialListOld = persistentAdministrador.getHistorialList();
            List<Historial> historialListNew = administrador.getHistorialList();
            List<String> illegalOrphanMessages = null;
            for (DetalleIngreso detalleIngresoListOldDetalleIngreso : detalleIngresoListOld) {
                if (!detalleIngresoListNew.contains(detalleIngresoListOldDetalleIngreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleIngreso " + detalleIngresoListOldDetalleIngreso + " since its idadministrador field is not nullable.");
                }
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its idadministrador field is not nullable.");
                }
            }
            for (Historial historialListOldHistorial : historialListOld) {
                if (!historialListNew.contains(historialListOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialListOldHistorial + " since its idadministrador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetalleIngreso> attachedDetalleIngresoListNew = new ArrayList<DetalleIngreso>();
            for (DetalleIngreso detalleIngresoListNewDetalleIngresoToAttach : detalleIngresoListNew) {
                detalleIngresoListNewDetalleIngresoToAttach = em.getReference(detalleIngresoListNewDetalleIngresoToAttach.getClass(), detalleIngresoListNewDetalleIngresoToAttach.getIddetalleIngreso());
                attachedDetalleIngresoListNew.add(detalleIngresoListNewDetalleIngresoToAttach);
            }
            detalleIngresoListNew = attachedDetalleIngresoListNew;
            administrador.setDetalleIngresoList(detalleIngresoListNew);
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            administrador.setProductoList(productoListNew);
            List<Historial> attachedHistorialListNew = new ArrayList<Historial>();
            for (Historial historialListNewHistorialToAttach : historialListNew) {
                historialListNewHistorialToAttach = em.getReference(historialListNewHistorialToAttach.getClass(), historialListNewHistorialToAttach.getIdhistorial());
                attachedHistorialListNew.add(historialListNewHistorialToAttach);
            }
            historialListNew = attachedHistorialListNew;
            administrador.setHistorialList(historialListNew);
            administrador = em.merge(administrador);
            for (DetalleIngreso detalleIngresoListNewDetalleIngreso : detalleIngresoListNew) {
                if (!detalleIngresoListOld.contains(detalleIngresoListNewDetalleIngreso)) {
                    Administrador oldIdadministradorOfDetalleIngresoListNewDetalleIngreso = detalleIngresoListNewDetalleIngreso.getIdadministrador();
                    detalleIngresoListNewDetalleIngreso.setIdadministrador(administrador);
                    detalleIngresoListNewDetalleIngreso = em.merge(detalleIngresoListNewDetalleIngreso);
                    if (oldIdadministradorOfDetalleIngresoListNewDetalleIngreso != null && !oldIdadministradorOfDetalleIngresoListNewDetalleIngreso.equals(administrador)) {
                        oldIdadministradorOfDetalleIngresoListNewDetalleIngreso.getDetalleIngresoList().remove(detalleIngresoListNewDetalleIngreso);
                        oldIdadministradorOfDetalleIngresoListNewDetalleIngreso = em.merge(oldIdadministradorOfDetalleIngresoListNewDetalleIngreso);
                    }
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Administrador oldIdadministradorOfProductoListNewProducto = productoListNewProducto.getIdadministrador();
                    productoListNewProducto.setIdadministrador(administrador);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldIdadministradorOfProductoListNewProducto != null && !oldIdadministradorOfProductoListNewProducto.equals(administrador)) {
                        oldIdadministradorOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldIdadministradorOfProductoListNewProducto = em.merge(oldIdadministradorOfProductoListNewProducto);
                    }
                }
            }
            for (Historial historialListNewHistorial : historialListNew) {
                if (!historialListOld.contains(historialListNewHistorial)) {
                    Administrador oldIdadministradorOfHistorialListNewHistorial = historialListNewHistorial.getIdadministrador();
                    historialListNewHistorial.setIdadministrador(administrador);
                    historialListNewHistorial = em.merge(historialListNewHistorial);
                    if (oldIdadministradorOfHistorialListNewHistorial != null && !oldIdadministradorOfHistorialListNewHistorial.equals(administrador)) {
                        oldIdadministradorOfHistorialListNewHistorial.getHistorialList().remove(historialListNewHistorial);
                        oldIdadministradorOfHistorialListNewHistorial = em.merge(oldIdadministradorOfHistorialListNewHistorial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrador.getIdadministrador();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
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
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getIdadministrador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleIngreso> detalleIngresoListOrphanCheck = administrador.getDetalleIngresoList();
            for (DetalleIngreso detalleIngresoListOrphanCheckDetalleIngreso : detalleIngresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrador (" + administrador + ") cannot be destroyed since the DetalleIngreso " + detalleIngresoListOrphanCheckDetalleIngreso + " in its detalleIngresoList field has a non-nullable idadministrador field.");
            }
            List<Producto> productoListOrphanCheck = administrador.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrador (" + administrador + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable idadministrador field.");
            }
            List<Historial> historialListOrphanCheck = administrador.getHistorialList();
            for (Historial historialListOrphanCheckHistorial : historialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrador (" + administrador + ") cannot be destroyed since the Historial " + historialListOrphanCheckHistorial + " in its historialList field has a non-nullable idadministrador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
