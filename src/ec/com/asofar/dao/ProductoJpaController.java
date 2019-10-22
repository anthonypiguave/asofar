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
import ec.com.asofar.dto.Administrador;
import ec.com.asofar.dto.DetalleIngreso;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.Factura;
import ec.com.asofar.dto.DetalleEgreso;
import ec.com.asofar.dto.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nuevouser
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetalleIngresoList() == null) {
            producto.setDetalleIngresoList(new ArrayList<DetalleIngreso>());
        }
        if (producto.getFacturaList() == null) {
            producto.setFacturaList(new ArrayList<Factura>());
        }
        if (producto.getDetalleEgresoList() == null) {
            producto.setDetalleEgresoList(new ArrayList<DetalleEgreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador idadministrador = producto.getIdadministrador();
            if (idadministrador != null) {
                idadministrador = em.getReference(idadministrador.getClass(), idadministrador.getIdadministrador());
                producto.setIdadministrador(idadministrador);
            }
            List<DetalleIngreso> attachedDetalleIngresoList = new ArrayList<DetalleIngreso>();
            for (DetalleIngreso detalleIngresoListDetalleIngresoToAttach : producto.getDetalleIngresoList()) {
                detalleIngresoListDetalleIngresoToAttach = em.getReference(detalleIngresoListDetalleIngresoToAttach.getClass(), detalleIngresoListDetalleIngresoToAttach.getIddetalleIngreso());
                attachedDetalleIngresoList.add(detalleIngresoListDetalleIngresoToAttach);
            }
            producto.setDetalleIngresoList(attachedDetalleIngresoList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : producto.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            producto.setFacturaList(attachedFacturaList);
            List<DetalleEgreso> attachedDetalleEgresoList = new ArrayList<DetalleEgreso>();
            for (DetalleEgreso detalleEgresoListDetalleEgresoToAttach : producto.getDetalleEgresoList()) {
                detalleEgresoListDetalleEgresoToAttach = em.getReference(detalleEgresoListDetalleEgresoToAttach.getClass(), detalleEgresoListDetalleEgresoToAttach.getIddetalleEgreso());
                attachedDetalleEgresoList.add(detalleEgresoListDetalleEgresoToAttach);
            }
            producto.setDetalleEgresoList(attachedDetalleEgresoList);
            em.persist(producto);
            if (idadministrador != null) {
                idadministrador.getProductoList().add(producto);
                idadministrador = em.merge(idadministrador);
            }
            for (DetalleIngreso detalleIngresoListDetalleIngreso : producto.getDetalleIngresoList()) {
                Producto oldIdProductoOfDetalleIngresoListDetalleIngreso = detalleIngresoListDetalleIngreso.getIdProducto();
                detalleIngresoListDetalleIngreso.setIdProducto(producto);
                detalleIngresoListDetalleIngreso = em.merge(detalleIngresoListDetalleIngreso);
                if (oldIdProductoOfDetalleIngresoListDetalleIngreso != null) {
                    oldIdProductoOfDetalleIngresoListDetalleIngreso.getDetalleIngresoList().remove(detalleIngresoListDetalleIngreso);
                    oldIdProductoOfDetalleIngresoListDetalleIngreso = em.merge(oldIdProductoOfDetalleIngresoListDetalleIngreso);
                }
            }
            for (Factura facturaListFactura : producto.getFacturaList()) {
                Producto oldIdProductoOfFacturaListFactura = facturaListFactura.getIdProducto();
                facturaListFactura.setIdProducto(producto);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdProductoOfFacturaListFactura != null) {
                    oldIdProductoOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdProductoOfFacturaListFactura = em.merge(oldIdProductoOfFacturaListFactura);
                }
            }
            for (DetalleEgreso detalleEgresoListDetalleEgreso : producto.getDetalleEgresoList()) {
                Producto oldIdproductoOfDetalleEgresoListDetalleEgreso = detalleEgresoListDetalleEgreso.getIdproducto();
                detalleEgresoListDetalleEgreso.setIdproducto(producto);
                detalleEgresoListDetalleEgreso = em.merge(detalleEgresoListDetalleEgreso);
                if (oldIdproductoOfDetalleEgresoListDetalleEgreso != null) {
                    oldIdproductoOfDetalleEgresoListDetalleEgreso.getDetalleEgresoList().remove(detalleEgresoListDetalleEgreso);
                    oldIdproductoOfDetalleEgresoListDetalleEgreso = em.merge(oldIdproductoOfDetalleEgresoListDetalleEgreso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Administrador idadministradorOld = persistentProducto.getIdadministrador();
            Administrador idadministradorNew = producto.getIdadministrador();
            List<DetalleIngreso> detalleIngresoListOld = persistentProducto.getDetalleIngresoList();
            List<DetalleIngreso> detalleIngresoListNew = producto.getDetalleIngresoList();
            List<Factura> facturaListOld = persistentProducto.getFacturaList();
            List<Factura> facturaListNew = producto.getFacturaList();
            List<DetalleEgreso> detalleEgresoListOld = persistentProducto.getDetalleEgresoList();
            List<DetalleEgreso> detalleEgresoListNew = producto.getDetalleEgresoList();
            List<String> illegalOrphanMessages = null;
            for (DetalleIngreso detalleIngresoListOldDetalleIngreso : detalleIngresoListOld) {
                if (!detalleIngresoListNew.contains(detalleIngresoListOldDetalleIngreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleIngreso " + detalleIngresoListOldDetalleIngreso + " since its idProducto field is not nullable.");
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idProducto field is not nullable.");
                }
            }
            for (DetalleEgreso detalleEgresoListOldDetalleEgreso : detalleEgresoListOld) {
                if (!detalleEgresoListNew.contains(detalleEgresoListOldDetalleEgreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleEgreso " + detalleEgresoListOldDetalleEgreso + " since its idproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idadministradorNew != null) {
                idadministradorNew = em.getReference(idadministradorNew.getClass(), idadministradorNew.getIdadministrador());
                producto.setIdadministrador(idadministradorNew);
            }
            List<DetalleIngreso> attachedDetalleIngresoListNew = new ArrayList<DetalleIngreso>();
            for (DetalleIngreso detalleIngresoListNewDetalleIngresoToAttach : detalleIngresoListNew) {
                detalleIngresoListNewDetalleIngresoToAttach = em.getReference(detalleIngresoListNewDetalleIngresoToAttach.getClass(), detalleIngresoListNewDetalleIngresoToAttach.getIddetalleIngreso());
                attachedDetalleIngresoListNew.add(detalleIngresoListNewDetalleIngresoToAttach);
            }
            detalleIngresoListNew = attachedDetalleIngresoListNew;
            producto.setDetalleIngresoList(detalleIngresoListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            producto.setFacturaList(facturaListNew);
            List<DetalleEgreso> attachedDetalleEgresoListNew = new ArrayList<DetalleEgreso>();
            for (DetalleEgreso detalleEgresoListNewDetalleEgresoToAttach : detalleEgresoListNew) {
                detalleEgresoListNewDetalleEgresoToAttach = em.getReference(detalleEgresoListNewDetalleEgresoToAttach.getClass(), detalleEgresoListNewDetalleEgresoToAttach.getIddetalleEgreso());
                attachedDetalleEgresoListNew.add(detalleEgresoListNewDetalleEgresoToAttach);
            }
            detalleEgresoListNew = attachedDetalleEgresoListNew;
            producto.setDetalleEgresoList(detalleEgresoListNew);
            producto = em.merge(producto);
            if (idadministradorOld != null && !idadministradorOld.equals(idadministradorNew)) {
                idadministradorOld.getProductoList().remove(producto);
                idadministradorOld = em.merge(idadministradorOld);
            }
            if (idadministradorNew != null && !idadministradorNew.equals(idadministradorOld)) {
                idadministradorNew.getProductoList().add(producto);
                idadministradorNew = em.merge(idadministradorNew);
            }
            for (DetalleIngreso detalleIngresoListNewDetalleIngreso : detalleIngresoListNew) {
                if (!detalleIngresoListOld.contains(detalleIngresoListNewDetalleIngreso)) {
                    Producto oldIdProductoOfDetalleIngresoListNewDetalleIngreso = detalleIngresoListNewDetalleIngreso.getIdProducto();
                    detalleIngresoListNewDetalleIngreso.setIdProducto(producto);
                    detalleIngresoListNewDetalleIngreso = em.merge(detalleIngresoListNewDetalleIngreso);
                    if (oldIdProductoOfDetalleIngresoListNewDetalleIngreso != null && !oldIdProductoOfDetalleIngresoListNewDetalleIngreso.equals(producto)) {
                        oldIdProductoOfDetalleIngresoListNewDetalleIngreso.getDetalleIngresoList().remove(detalleIngresoListNewDetalleIngreso);
                        oldIdProductoOfDetalleIngresoListNewDetalleIngreso = em.merge(oldIdProductoOfDetalleIngresoListNewDetalleIngreso);
                    }
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Producto oldIdProductoOfFacturaListNewFactura = facturaListNewFactura.getIdProducto();
                    facturaListNewFactura.setIdProducto(producto);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdProductoOfFacturaListNewFactura != null && !oldIdProductoOfFacturaListNewFactura.equals(producto)) {
                        oldIdProductoOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdProductoOfFacturaListNewFactura = em.merge(oldIdProductoOfFacturaListNewFactura);
                    }
                }
            }
            for (DetalleEgreso detalleEgresoListNewDetalleEgreso : detalleEgresoListNew) {
                if (!detalleEgresoListOld.contains(detalleEgresoListNewDetalleEgreso)) {
                    Producto oldIdproductoOfDetalleEgresoListNewDetalleEgreso = detalleEgresoListNewDetalleEgreso.getIdproducto();
                    detalleEgresoListNewDetalleEgreso.setIdproducto(producto);
                    detalleEgresoListNewDetalleEgreso = em.merge(detalleEgresoListNewDetalleEgreso);
                    if (oldIdproductoOfDetalleEgresoListNewDetalleEgreso != null && !oldIdproductoOfDetalleEgresoListNewDetalleEgreso.equals(producto)) {
                        oldIdproductoOfDetalleEgresoListNewDetalleEgreso.getDetalleEgresoList().remove(detalleEgresoListNewDetalleEgreso);
                        oldIdproductoOfDetalleEgresoListNewDetalleEgreso = em.merge(oldIdproductoOfDetalleEgresoListNewDetalleEgreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleIngreso> detalleIngresoListOrphanCheck = producto.getDetalleIngresoList();
            for (DetalleIngreso detalleIngresoListOrphanCheckDetalleIngreso : detalleIngresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleIngreso " + detalleIngresoListOrphanCheckDetalleIngreso + " in its detalleIngresoList field has a non-nullable idProducto field.");
            }
            List<Factura> facturaListOrphanCheck = producto.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idProducto field.");
            }
            List<DetalleEgreso> detalleEgresoListOrphanCheck = producto.getDetalleEgresoList();
            for (DetalleEgreso detalleEgresoListOrphanCheckDetalleEgreso : detalleEgresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleEgreso " + detalleEgresoListOrphanCheckDetalleEgreso + " in its detalleEgresoList field has a non-nullable idproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Administrador idadministrador = producto.getIdadministrador();
            if (idadministrador != null) {
                idadministrador.getProductoList().remove(producto);
                idadministrador = em.merge(idadministrador);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
