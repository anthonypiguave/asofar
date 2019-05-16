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
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrProductosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
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
        if (prProductos.getCoDetalleCotizacionPorProveedorList() == null) {
            prProductos.setCoDetalleCotizacionPorProveedorList(new ArrayList<CoDetalleCotizacionPorProveedor>());
        }
        prProductos.getPrProductosPK().setIdTipoMedidas(prProductos.getPrMedidas().getPrMedidasPK().getIdTipoMedidas());
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdTipoPresentacion(prProductos.getPrMedidas().getPrMedidasPK().getIdTipoPresentacion());
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
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
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorList = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach : prProductos.getCoDetalleCotizacionPorProveedorList()) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorList.add(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach);
            }
            prProductos.setCoDetalleCotizacionPorProveedorList(attachedCoDetalleCotizacionPorProveedorList);
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
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : prProductos.getCoDetalleCotizacionPorProveedorList()) {
                PrProductos oldIdProductoOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getIdProducto();
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setIdProducto(prProductos);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                if (oldIdProductoOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor != null) {
                    oldIdProductoOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                    oldIdProductoOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(oldIdProductoOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                }
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
        prProductos.getPrProductosPK().setIdTipoMedidas(prProductos.getPrMedidas().getPrMedidasPK().getIdTipoMedidas());
        prProductos.getPrProductosPK().setIdEmpresa(prProductos.getSeEmpresa().getIdEmpresa());
        prProductos.getPrProductosPK().setIdTipoPresentacion(prProductos.getPrMedidas().getPrMedidasPK().getIdTipoPresentacion());
        prProductos.getPrProductosPK().setIdSubgrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdSubgrupo());
        prProductos.getPrProductosPK().setIdGrupo(prProductos.getPrMedidas().getPrMedidasPK().getIdGrupo());
        prProductos.getPrProductosPK().setIdArticulo(prProductos.getPrMedidas().getPrMedidasPK().getIdArticulo());
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
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOld = persistentPrProductos.getCoDetalleCotizacionPorProveedorList();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListNew = prProductos.getCoDetalleCotizacionPorProveedorList();
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
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorListNew = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach : coDetalleCotizacionPorProveedorListNew) {
                coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorListNew.add(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach);
            }
            coDetalleCotizacionPorProveedorListNew = attachedCoDetalleCotizacionPorProveedorListNew;
            prProductos.setCoDetalleCotizacionPorProveedorList(coDetalleCotizacionPorProveedorListNew);
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
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOld) {
                if (!coDetalleCotizacionPorProveedorListNew.contains(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor)) {
                    coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor.setIdProducto(null);
                    coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor);
                }
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListNew) {
                if (!coDetalleCotizacionPorProveedorListOld.contains(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor)) {
                    PrProductos oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getIdProducto();
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.setIdProducto(prProductos);
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    if (oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor != null && !oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.equals(prProductos)) {
                        oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                        oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(oldIdProductoOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    }
                }
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
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorList = prProductos.getCoDetalleCotizacionPorProveedorList();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorList) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setIdProducto(null);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
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
