/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.PrTipoMedidas;
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

    public void create(CoDetalleOrdenCompra coDetalleOrdenCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoOrdenCompras coOrdenCompras = coDetalleOrdenCompra.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras = em.getReference(coOrdenCompras.getClass(), coOrdenCompras.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setCoOrdenCompras(coOrdenCompras);
            }
            PrProductos idProducto = coDetalleOrdenCompra.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getPrProductosPK());
                coDetalleOrdenCompra.setIdProducto(idProducto);
            }
            PrArticulo idArticulo = coDetalleOrdenCompra.getIdArticulo();
            if (idArticulo != null) {
                idArticulo = em.getReference(idArticulo.getClass(), idArticulo.getPrArticuloPK());
                coDetalleOrdenCompra.setIdArticulo(idArticulo);
            }
            PrSubgrupos prSubgrupos = coDetalleOrdenCompra.getPrSubgrupos();
            if (prSubgrupos != null) {
                prSubgrupos = em.getReference(prSubgrupos.getClass(), prSubgrupos.getPrSubgruposPK());
                coDetalleOrdenCompra.setPrSubgrupos(prSubgrupos);
            }
            PrTipoPresentacion idTipoPresentacion = coDetalleOrdenCompra.getIdTipoPresentacion();
            if (idTipoPresentacion != null) {
                idTipoPresentacion = em.getReference(idTipoPresentacion.getClass(), idTipoPresentacion.getIdTipoPresentacion());
                coDetalleOrdenCompra.setIdTipoPresentacion(idTipoPresentacion);
            }
            PrTipoMedidas idTipoMedidas = coDetalleOrdenCompra.getIdTipoMedidas();
            if (idTipoMedidas != null) {
                idTipoMedidas = em.getReference(idTipoMedidas.getClass(), idTipoMedidas.getIdTipoMedidas());
                coDetalleOrdenCompra.setIdTipoMedidas(idTipoMedidas);
            }
            em.persist(coDetalleOrdenCompra);
            if (coOrdenCompras != null) {
                coOrdenCompras.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            if (idProducto != null) {
                idProducto.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idProducto = em.merge(idProducto);
            }
            if (idArticulo != null) {
                idArticulo.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idArticulo = em.merge(idArticulo);
            }
            if (prSubgrupos != null) {
                prSubgrupos.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                prSubgrupos = em.merge(prSubgrupos);
            }
            if (idTipoPresentacion != null) {
                idTipoPresentacion.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idTipoPresentacion = em.merge(idTipoPresentacion);
            }
            if (idTipoMedidas != null) {
                idTipoMedidas.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idTipoMedidas = em.merge(idTipoMedidas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoDetalleOrdenCompra coDetalleOrdenCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenCompra persistentCoDetalleOrdenCompra = em.find(CoDetalleOrdenCompra.class, coDetalleOrdenCompra.getIdDetalleOrdenCompra());
            CoOrdenCompras coOrdenComprasOld = persistentCoDetalleOrdenCompra.getCoOrdenCompras();
            CoOrdenCompras coOrdenComprasNew = coDetalleOrdenCompra.getCoOrdenCompras();
            PrProductos idProductoOld = persistentCoDetalleOrdenCompra.getIdProducto();
            PrProductos idProductoNew = coDetalleOrdenCompra.getIdProducto();
            PrArticulo idArticuloOld = persistentCoDetalleOrdenCompra.getIdArticulo();
            PrArticulo idArticuloNew = coDetalleOrdenCompra.getIdArticulo();
            PrSubgrupos prSubgruposOld = persistentCoDetalleOrdenCompra.getPrSubgrupos();
            PrSubgrupos prSubgruposNew = coDetalleOrdenCompra.getPrSubgrupos();
            PrTipoPresentacion idTipoPresentacionOld = persistentCoDetalleOrdenCompra.getIdTipoPresentacion();
            PrTipoPresentacion idTipoPresentacionNew = coDetalleOrdenCompra.getIdTipoPresentacion();
            PrTipoMedidas idTipoMedidasOld = persistentCoDetalleOrdenCompra.getIdTipoMedidas();
            PrTipoMedidas idTipoMedidasNew = coDetalleOrdenCompra.getIdTipoMedidas();
            if (coOrdenComprasNew != null) {
                coOrdenComprasNew = em.getReference(coOrdenComprasNew.getClass(), coOrdenComprasNew.getCoOrdenComprasPK());
                coDetalleOrdenCompra.setCoOrdenCompras(coOrdenComprasNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getPrProductosPK());
                coDetalleOrdenCompra.setIdProducto(idProductoNew);
            }
            if (idArticuloNew != null) {
                idArticuloNew = em.getReference(idArticuloNew.getClass(), idArticuloNew.getPrArticuloPK());
                coDetalleOrdenCompra.setIdArticulo(idArticuloNew);
            }
            if (prSubgruposNew != null) {
                prSubgruposNew = em.getReference(prSubgruposNew.getClass(), prSubgruposNew.getPrSubgruposPK());
                coDetalleOrdenCompra.setPrSubgrupos(prSubgruposNew);
            }
            if (idTipoPresentacionNew != null) {
                idTipoPresentacionNew = em.getReference(idTipoPresentacionNew.getClass(), idTipoPresentacionNew.getIdTipoPresentacion());
                coDetalleOrdenCompra.setIdTipoPresentacion(idTipoPresentacionNew);
            }
            if (idTipoMedidasNew != null) {
                idTipoMedidasNew = em.getReference(idTipoMedidasNew.getClass(), idTipoMedidasNew.getIdTipoMedidas());
                coDetalleOrdenCompra.setIdTipoMedidas(idTipoMedidasNew);
            }
            coDetalleOrdenCompra = em.merge(coDetalleOrdenCompra);
            if (coOrdenComprasOld != null && !coOrdenComprasOld.equals(coOrdenComprasNew)) {
                coOrdenComprasOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                coOrdenComprasOld = em.merge(coOrdenComprasOld);
            }
            if (coOrdenComprasNew != null && !coOrdenComprasNew.equals(coOrdenComprasOld)) {
                coOrdenComprasNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                coOrdenComprasNew = em.merge(coOrdenComprasNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
                idArticuloOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idArticuloOld = em.merge(idArticuloOld);
            }
            if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
                idArticuloNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idArticuloNew = em.merge(idArticuloNew);
            }
            if (prSubgruposOld != null && !prSubgruposOld.equals(prSubgruposNew)) {
                prSubgruposOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                prSubgruposOld = em.merge(prSubgruposOld);
            }
            if (prSubgruposNew != null && !prSubgruposNew.equals(prSubgruposOld)) {
                prSubgruposNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                prSubgruposNew = em.merge(prSubgruposNew);
            }
            if (idTipoPresentacionOld != null && !idTipoPresentacionOld.equals(idTipoPresentacionNew)) {
                idTipoPresentacionOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idTipoPresentacionOld = em.merge(idTipoPresentacionOld);
            }
            if (idTipoPresentacionNew != null && !idTipoPresentacionNew.equals(idTipoPresentacionOld)) {
                idTipoPresentacionNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idTipoPresentacionNew = em.merge(idTipoPresentacionNew);
            }
            if (idTipoMedidasOld != null && !idTipoMedidasOld.equals(idTipoMedidasNew)) {
                idTipoMedidasOld.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idTipoMedidasOld = em.merge(idTipoMedidasOld);
            }
            if (idTipoMedidasNew != null && !idTipoMedidasNew.equals(idTipoMedidasOld)) {
                idTipoMedidasNew.getCoDetalleOrdenCompraList().add(coDetalleOrdenCompra);
                idTipoMedidasNew = em.merge(idTipoMedidasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = coDetalleOrdenCompra.getIdDetalleOrdenCompra();
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

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoDetalleOrdenCompra coDetalleOrdenCompra;
            try {
                coDetalleOrdenCompra = em.getReference(CoDetalleOrdenCompra.class, id);
                coDetalleOrdenCompra.getIdDetalleOrdenCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coDetalleOrdenCompra with id " + id + " no longer exists.", enfe);
            }
            CoOrdenCompras coOrdenCompras = coDetalleOrdenCompra.getCoOrdenCompras();
            if (coOrdenCompras != null) {
                coOrdenCompras.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                coOrdenCompras = em.merge(coOrdenCompras);
            }
            PrProductos idProducto = coDetalleOrdenCompra.getIdProducto();
            if (idProducto != null) {
                idProducto.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idProducto = em.merge(idProducto);
            }
            PrArticulo idArticulo = coDetalleOrdenCompra.getIdArticulo();
            if (idArticulo != null) {
                idArticulo.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idArticulo = em.merge(idArticulo);
            }
            PrSubgrupos prSubgrupos = coDetalleOrdenCompra.getPrSubgrupos();
            if (prSubgrupos != null) {
                prSubgrupos.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                prSubgrupos = em.merge(prSubgrupos);
            }
            PrTipoPresentacion idTipoPresentacion = coDetalleOrdenCompra.getIdTipoPresentacion();
            if (idTipoPresentacion != null) {
                idTipoPresentacion.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idTipoPresentacion = em.merge(idTipoPresentacion);
            }
            PrTipoMedidas idTipoMedidas = coDetalleOrdenCompra.getIdTipoMedidas();
            if (idTipoMedidas != null) {
                idTipoMedidas.getCoDetalleOrdenCompraList().remove(coDetalleOrdenCompra);
                idTipoMedidas = em.merge(idTipoMedidas);
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

    public CoDetalleOrdenCompra findCoDetalleOrdenCompra(Long id) {
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
