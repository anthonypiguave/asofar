/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrProductosPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jorge
 */
public class PrProductosJpaController implements Serializable {

    public PrProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrProductos prProductos) throws PreexistingEntityException, Exception {
        if (prProductos.getPrProductosPK() == null) {
            prProductos.setPrProductosPK(new PrProductosPK());
        }
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa seEmpresa = prProductos.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa = em.getReference(seEmpresa.getClass(), seEmpresa.getIdEmpresa());
                prProductos.setSeEmpresa(seEmpresa);
            }
            PrFabricante codFabricante = prProductos.getCodFabricante();
            if (codFabricante != null) {
                codFabricante = em.getReference(codFabricante.getClass(), codFabricante.getIdFabricante());
                prProductos.setCodFabricante(codFabricante);
            }
            PrMedidas prMedidas = prProductos.getPrMedidas();
            if (prMedidas != null) {
                prMedidas = em.getReference(prMedidas.getClass(), prMedidas.getPrMedidasPK());
                prProductos.setPrMedidas(prMedidas);
            }
            PrEmpaque medidaEmpaqueCompra = prProductos.getMedidaEmpaqueCompra();
            if (medidaEmpaqueCompra != null) {
                medidaEmpaqueCompra = em.getReference(medidaEmpaqueCompra.getClass(), medidaEmpaqueCompra.getId());
                prProductos.setMedidaEmpaqueCompra(medidaEmpaqueCompra);
            }
            PrEmpaque medidaPorEmpaqueCompra = prProductos.getMedidaPorEmpaqueCompra();
            if (medidaPorEmpaqueCompra != null) {
                medidaPorEmpaqueCompra = em.getReference(medidaPorEmpaqueCompra.getClass(), medidaPorEmpaqueCompra.getId());
                prProductos.setMedidaPorEmpaqueCompra(medidaPorEmpaqueCompra);
            }
            PrEmpaque medidaEmpaqueVenta = prProductos.getMedidaEmpaqueVenta();
            if (medidaEmpaqueVenta != null) {
                medidaEmpaqueVenta = em.getReference(medidaEmpaqueVenta.getClass(), medidaEmpaqueVenta.getId());
                prProductos.setMedidaEmpaqueVenta(medidaEmpaqueVenta);
            }
            PrEmpaque medidaPorEmpaqueVenta = prProductos.getMedidaPorEmpaqueVenta();
            if (medidaPorEmpaqueVenta != null) {
                medidaPorEmpaqueVenta = em.getReference(medidaPorEmpaqueVenta.getClass(), medidaPorEmpaqueVenta.getId());
                prProductos.setMedidaPorEmpaqueVenta(medidaPorEmpaqueVenta);
            }
            CoProveedores idProveedor = prProductos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                prProductos.setIdProveedor(idProveedor);
            }
            em.persist(prProductos);
            if (seEmpresa != null) {
                seEmpresa.getPrProductosList().add(prProductos);
                seEmpresa = em.merge(seEmpresa);
            }
            if (codFabricante != null) {
                codFabricante.getPrProductosList().add(prProductos);
                codFabricante = em.merge(codFabricante);
            }
            if (prMedidas != null) {
                prMedidas.getPrProductosList().add(prProductos);
                prMedidas = em.merge(prMedidas);
            }
            if (medidaEmpaqueCompra != null) {
                medidaEmpaqueCompra.getPrProductosList().add(prProductos);
                medidaEmpaqueCompra = em.merge(medidaEmpaqueCompra);
            }
            if (medidaPorEmpaqueCompra != null) {
                medidaPorEmpaqueCompra.getPrProductosList().add(prProductos);
                medidaPorEmpaqueCompra = em.merge(medidaPorEmpaqueCompra);
            }
            if (medidaEmpaqueVenta != null) {
                medidaEmpaqueVenta.getPrProductosList().add(prProductos);
                medidaEmpaqueVenta = em.merge(medidaEmpaqueVenta);
            }
            if (medidaPorEmpaqueVenta != null) {
                medidaPorEmpaqueVenta.getPrProductosList().add(prProductos);
                medidaPorEmpaqueVenta = em.merge(medidaPorEmpaqueVenta);
            }
            if (idProveedor != null) {
                idProveedor.getPrProductosList().add(prProductos);
                idProveedor = em.merge(idProveedor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrProductos(prProductos.getPrProductosPK()) != null) {
                throw new PreexistingEntityException("PrProductos " + prProductos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrProductos prProductos) throws NonexistentEntityException, Exception {
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductos persistentPrProductos = em.find(PrProductos.class, prProductos.getPrProductosPK());
            SeEmpresa seEmpresaOld = persistentPrProductos.getSeEmpresa();
            SeEmpresa seEmpresaNew = prProductos.getSeEmpresa();
            PrFabricante codFabricanteOld = persistentPrProductos.getCodFabricante();
            PrFabricante codFabricanteNew = prProductos.getCodFabricante();
            PrMedidas prMedidasOld = persistentPrProductos.getPrMedidas();
            PrMedidas prMedidasNew = prProductos.getPrMedidas();
            PrEmpaque medidaEmpaqueCompraOld = persistentPrProductos.getMedidaEmpaqueCompra();
            PrEmpaque medidaEmpaqueCompraNew = prProductos.getMedidaEmpaqueCompra();
            PrEmpaque medidaPorEmpaqueCompraOld = persistentPrProductos.getMedidaPorEmpaqueCompra();
            PrEmpaque medidaPorEmpaqueCompraNew = prProductos.getMedidaPorEmpaqueCompra();
            PrEmpaque medidaEmpaqueVentaOld = persistentPrProductos.getMedidaEmpaqueVenta();
            PrEmpaque medidaEmpaqueVentaNew = prProductos.getMedidaEmpaqueVenta();
            PrEmpaque medidaPorEmpaqueVentaOld = persistentPrProductos.getMedidaPorEmpaqueVenta();
            PrEmpaque medidaPorEmpaqueVentaNew = prProductos.getMedidaPorEmpaqueVenta();
            CoProveedores idProveedorOld = persistentPrProductos.getIdProveedor();
            CoProveedores idProveedorNew = prProductos.getIdProveedor();
            if (seEmpresaNew != null) {
                seEmpresaNew = em.getReference(seEmpresaNew.getClass(), seEmpresaNew.getIdEmpresa());
                prProductos.setSeEmpresa(seEmpresaNew);
            }
            if (codFabricanteNew != null) {
                codFabricanteNew = em.getReference(codFabricanteNew.getClass(), codFabricanteNew.getIdFabricante());
                prProductos.setCodFabricante(codFabricanteNew);
            }
            if (prMedidasNew != null) {
                prMedidasNew = em.getReference(prMedidasNew.getClass(), prMedidasNew.getPrMedidasPK());
                prProductos.setPrMedidas(prMedidasNew);
            }
            if (medidaEmpaqueCompraNew != null) {
                medidaEmpaqueCompraNew = em.getReference(medidaEmpaqueCompraNew.getClass(), medidaEmpaqueCompraNew.getId());
                prProductos.setMedidaEmpaqueCompra(medidaEmpaqueCompraNew);
            }
            if (medidaPorEmpaqueCompraNew != null) {
                medidaPorEmpaqueCompraNew = em.getReference(medidaPorEmpaqueCompraNew.getClass(), medidaPorEmpaqueCompraNew.getId());
                prProductos.setMedidaPorEmpaqueCompra(medidaPorEmpaqueCompraNew);
            }
            if (medidaEmpaqueVentaNew != null) {
                medidaEmpaqueVentaNew = em.getReference(medidaEmpaqueVentaNew.getClass(), medidaEmpaqueVentaNew.getId());
                prProductos.setMedidaEmpaqueVenta(medidaEmpaqueVentaNew);
            }
            if (medidaPorEmpaqueVentaNew != null) {
                medidaPorEmpaqueVentaNew = em.getReference(medidaPorEmpaqueVentaNew.getClass(), medidaPorEmpaqueVentaNew.getId());
                prProductos.setMedidaPorEmpaqueVenta(medidaPorEmpaqueVentaNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                prProductos.setIdProveedor(idProveedorNew);
            }
            prProductos = em.merge(prProductos);
            if (seEmpresaOld != null && !seEmpresaOld.equals(seEmpresaNew)) {
                seEmpresaOld.getPrProductosList().remove(prProductos);
                seEmpresaOld = em.merge(seEmpresaOld);
            }
            if (seEmpresaNew != null && !seEmpresaNew.equals(seEmpresaOld)) {
                seEmpresaNew.getPrProductosList().add(prProductos);
                seEmpresaNew = em.merge(seEmpresaNew);
            }
            if (codFabricanteOld != null && !codFabricanteOld.equals(codFabricanteNew)) {
                codFabricanteOld.getPrProductosList().remove(prProductos);
                codFabricanteOld = em.merge(codFabricanteOld);
            }
            if (codFabricanteNew != null && !codFabricanteNew.equals(codFabricanteOld)) {
                codFabricanteNew.getPrProductosList().add(prProductos);
                codFabricanteNew = em.merge(codFabricanteNew);
            }
            if (prMedidasOld != null && !prMedidasOld.equals(prMedidasNew)) {
                prMedidasOld.getPrProductosList().remove(prProductos);
                prMedidasOld = em.merge(prMedidasOld);
            }
            if (prMedidasNew != null && !prMedidasNew.equals(prMedidasOld)) {
                prMedidasNew.getPrProductosList().add(prProductos);
                prMedidasNew = em.merge(prMedidasNew);
            }
            if (medidaEmpaqueCompraOld != null && !medidaEmpaqueCompraOld.equals(medidaEmpaqueCompraNew)) {
                medidaEmpaqueCompraOld.getPrProductosList().remove(prProductos);
                medidaEmpaqueCompraOld = em.merge(medidaEmpaqueCompraOld);
            }
            if (medidaEmpaqueCompraNew != null && !medidaEmpaqueCompraNew.equals(medidaEmpaqueCompraOld)) {
                medidaEmpaqueCompraNew.getPrProductosList().add(prProductos);
                medidaEmpaqueCompraNew = em.merge(medidaEmpaqueCompraNew);
            }
            if (medidaPorEmpaqueCompraOld != null && !medidaPorEmpaqueCompraOld.equals(medidaPorEmpaqueCompraNew)) {
                medidaPorEmpaqueCompraOld.getPrProductosList().remove(prProductos);
                medidaPorEmpaqueCompraOld = em.merge(medidaPorEmpaqueCompraOld);
            }
            if (medidaPorEmpaqueCompraNew != null && !medidaPorEmpaqueCompraNew.equals(medidaPorEmpaqueCompraOld)) {
                medidaPorEmpaqueCompraNew.getPrProductosList().add(prProductos);
                medidaPorEmpaqueCompraNew = em.merge(medidaPorEmpaqueCompraNew);
            }
            if (medidaEmpaqueVentaOld != null && !medidaEmpaqueVentaOld.equals(medidaEmpaqueVentaNew)) {
                medidaEmpaqueVentaOld.getPrProductosList().remove(prProductos);
                medidaEmpaqueVentaOld = em.merge(medidaEmpaqueVentaOld);
            }
            if (medidaEmpaqueVentaNew != null && !medidaEmpaqueVentaNew.equals(medidaEmpaqueVentaOld)) {
                medidaEmpaqueVentaNew.getPrProductosList().add(prProductos);
                medidaEmpaqueVentaNew = em.merge(medidaEmpaqueVentaNew);
            }
            if (medidaPorEmpaqueVentaOld != null && !medidaPorEmpaqueVentaOld.equals(medidaPorEmpaqueVentaNew)) {
                medidaPorEmpaqueVentaOld.getPrProductosList().remove(prProductos);
                medidaPorEmpaqueVentaOld = em.merge(medidaPorEmpaqueVentaOld);
            }
            if (medidaPorEmpaqueVentaNew != null && !medidaPorEmpaqueVentaNew.equals(medidaPorEmpaqueVentaOld)) {
                medidaPorEmpaqueVentaNew.getPrProductosList().add(prProductos);
                medidaPorEmpaqueVentaNew = em.merge(medidaPorEmpaqueVentaNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getPrProductosList().remove(prProductos);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getPrProductosList().add(prProductos);
                idProveedorNew = em.merge(idProveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PrProductosPK id = prProductos.getPrProductosPK();
                if (findPrProductos(id) == null) {
                    throw new NonexistentEntityException("The prProductos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PrProductosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductos prProductos;
            try {
                prProductos = em.getReference(PrProductos.class, id);
                prProductos.getPrProductosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prProductos with id " + id + " no longer exists.", enfe);
            }
            SeEmpresa seEmpresa = prProductos.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa.getPrProductosList().remove(prProductos);
                seEmpresa = em.merge(seEmpresa);
            }
            PrFabricante codFabricante = prProductos.getCodFabricante();
            if (codFabricante != null) {
                codFabricante.getPrProductosList().remove(prProductos);
                codFabricante = em.merge(codFabricante);
            }
            PrMedidas prMedidas = prProductos.getPrMedidas();
            if (prMedidas != null) {
                prMedidas.getPrProductosList().remove(prProductos);
                prMedidas = em.merge(prMedidas);
            }
            PrEmpaque medidaEmpaqueCompra = prProductos.getMedidaEmpaqueCompra();
            if (medidaEmpaqueCompra != null) {
                medidaEmpaqueCompra.getPrProductosList().remove(prProductos);
                medidaEmpaqueCompra = em.merge(medidaEmpaqueCompra);
            }
            PrEmpaque medidaPorEmpaqueCompra = prProductos.getMedidaPorEmpaqueCompra();
            if (medidaPorEmpaqueCompra != null) {
                medidaPorEmpaqueCompra.getPrProductosList().remove(prProductos);
                medidaPorEmpaqueCompra = em.merge(medidaPorEmpaqueCompra);
            }
            PrEmpaque medidaEmpaqueVenta = prProductos.getMedidaEmpaqueVenta();
            if (medidaEmpaqueVenta != null) {
                medidaEmpaqueVenta.getPrProductosList().remove(prProductos);
                medidaEmpaqueVenta = em.merge(medidaEmpaqueVenta);
            }
            PrEmpaque medidaPorEmpaqueVenta = prProductos.getMedidaPorEmpaqueVenta();
            if (medidaPorEmpaqueVenta != null) {
                medidaPorEmpaqueVenta.getPrProductosList().remove(prProductos);
                medidaPorEmpaqueVenta = em.merge(medidaPorEmpaqueVenta);
            }
            CoProveedores idProveedor = prProductos.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getPrProductosList().remove(prProductos);
                idProveedor = em.merge(idProveedor);
            }
            em.remove(prProductos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrProductos> findPrProductosEntities() {
        return findPrProductosEntities(true, -1, -1);
    }

    public List<PrProductos> findPrProductosEntities(int maxResults, int firstResult) {
        return findPrProductosEntities(false, maxResults, firstResult);
    }

    private List<PrProductos> findPrProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrProductos.class));
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

    public PrProductos findPrProductos(PrProductosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrProductos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrProductos> rt = cq.from(PrProductos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
