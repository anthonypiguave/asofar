/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoDetalleOrdenCompraPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.CoOrdenCompras;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoDetalleOrdenCompraJpaController implements Serializable {

    public CoDetalleOrdenCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoDetalleOrdenCompra coDetalleOrdenCompra) throws PreexistingEntityException, Exception {
        if (coDetalleOrdenCompra.getCoDetalleOrdenCompraPK() == null) {
            coDetalleOrdenCompra.setCoDetalleOrdenCompraPK(new CoDetalleOrdenCompraPK());
        }
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdOrdenCompra(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdProducto(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdProducto());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdGrupo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdEmpresa());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdTipoPresentacion(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdTipoPresentacion());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdEmpresa(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdEmpresa());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdSurcusal(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdSucursal());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdSubgrupo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdArticulo());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdTipoMedidas(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdSubgrupo());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdArticulo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrProductos prProductos = coDetalleOrdenCompra.getPrProductos();
            if (prProductos != null) {
                prProductos = em.getReference(prProductos.getClass(), prProductos.getPrProductosPK());
                coDetalleOrdenCompra.setPrProductos(prProductos);
            }
            CoOrdenCompras coOrdenCompras = coDetalleOrdenCompra.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras = em.getReference(coOrdenCompras.getClass(), coOrdenCompras.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
            }
            em.persist(coDetalleOrdenCompra);
            if (prProductos != null) {
                prProductos.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                prProductos = em.merge(prProductos);
            }
            if (coOrdenCompras != null) {
                coOrdenCompras.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoDetalleOrdenCompra(coDetalleOrdenCompra.getCoDetalleOrdenCompraPK()) != null) {
                throw new PreexistingEntityException("CoDetalleOrdenCompra " + coDetalleOrdenCompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetalleOrdenCompra coDetalleOrdenCompra) throws NonexistentEntityException, Exception {
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdOrdenCompra(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdProducto(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdProducto());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdGrupo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdEmpresa());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdTipoPresentacion(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdTipoPresentacion());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdEmpresa(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdEmpresa());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdSurcusal(coDetalleOrdenCompra.getCoOrdenCompras().getCoOrdenComprasPK().getIdSucursal());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdSubgrupo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdArticulo());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdTipoMedidas(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdSubgrupo());
        coDetalleOrdenCompra.getCoDetalleOrdenCompraPK().setIdArticulo(coDetalleOrdenCompra.getPrProductos().getPrProductosPK().getIdGrupo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenCompra persistentCoDetalleOrdenCompra = em.find(CoDetalleOrdenCompra.class, coDetalleOrdenCompra.getCoDetalleOrdenCompraPK());
            PrProductos prProductosOld = persistentCoDetalleOrdenCompra.getPrProductos();
            PrProductos prProductosNew = coDetalleOrdenCompra.getPrProductos();
            CoOrdenCompras coOrdenComprasOld = persistentCoDetalleOrdenCompra.getCoOrdenCompras();
            CoOrdenCompras coOrdenComprasNew = coDetalleOrdenCompra.getCoOrdenCompras();
            if (prProductosNew != null) {
                prProductosNew = em.getReference(prProductosNew.getClass(), prProductosNew.getPrProductosPK());
                coDetalleOrdenCompra.setPrProductos(prProductosNew);
            }
            if (coOrdenComprasNew != null) {
                coOrdenComprasNew = em.getReference(coOrdenComprasNew.getClass(), coOrdenComprasNew.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setCoOrdenCompras(coOrdenComprasNew);
            }
            coDetalleOrdenCompra = em.merge(coDetalleOrdenCompra);
            if (prProductosOld != null && !prProductosOld.equals(prProductosNew)) {
                prProductosOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                prProductosOld = em.merge(prProductosOld);
            }
            if (prProductosNew != null && !prProductosNew.equals(prProductosOld)) {
                prProductosNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                prProductosNew = em.merge(prProductosNew);
            }
            if (coOrdenComprasOld != null && !coOrdenComprasOld.equals(coOrdenComprasNew)) {
                coOrdenComprasOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                coOrdenComprasOld = em.merge(coOrdenComprasOld);
            }
            if (coOrdenComprasNew != null && !coOrdenComprasNew.equals(coOrdenComprasOld)) {
                coOrdenComprasNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                coOrdenComprasNew = em.merge(coOrdenComprasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoDetalleOrdenCompraPK id = coDetalleOrdenCompra.getCoDetalleOrdenCompraPK();
                if (findCoDetalleOrdenCompra(id) == null) {
                    throw new NonexistentEntityException("The coDetalleOrdenCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoDetalleOrdenCompraPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenCompra coDetalleOrdenCompra;
            try {
                coDetalleOrdenCompra = em.getReference(CoDetalleOrdenCompra.class, id);
                coDetalleOrdenCompra.getCoDetalleOrdenCompraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetalleOrdenCompra with id " + id + " no longer exists.", enfe);
            }
            PrProductos prProductos = coDetalleOrdenCompra.getPrProductos();
            if (prProductos != null) {
                prProductos.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                prProductos = em.merge(prProductos);
            }
            CoOrdenCompras coOrdenCompras = coDetalleOrdenCompra.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            em.remove(coDetalleOrdenCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities() {
        return findCoDetalleOrdenCompraEntities(true, -1, -1);
    }

    public List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities(int maxResults, int firstResult) {
        return findCoDetalleOrdenCompraEntities(false, maxResults, firstResult);
    }

    private List<CoDetalleOrdenCompra> findCoDetalleOrdenCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoDetalleOrdenCompra.class));
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

    public CoDetalleOrdenCompra findCoDetalleOrdenCompra(CoDetalleOrdenCompraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoDetalleOrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoDetalleOrdenCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoDetalleOrdenCompra> rt = cq.from(CoDetalleOrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
