/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoCotizacionesPorPorveedor;
import ec.com.asofar.dto.CoCotizacionesPorPorveedorPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoCotizacionesPorPorveedorJpaController implements Serializable {

    public CoCotizacionesPorPorveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoCotizacionesPorPorveedor coCotizacionesPorPorveedor) throws PreexistingEntityException, Exception {
        if (coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK() == null) {
            coCotizacionesPorPorveedor.setCoCotizacionesPorPorveedorPK(new CoCotizacionesPorPorveedorPK());
        }
        if (coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList() == null) {
            coCotizacionesPorPorveedor.setCoDetalleCotizacionPorProveedorList(new ArrayList<CoDetalleCotizacionPorProveedor>());
        }
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdSucursal(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdEmpresa(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdCotizacion(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorPorveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion = em.getReference(coItemsCotizacion.getClass(), coItemsCotizacion.getCoItemsCotizacionPK());
                coCotizacionesPorPorveedor.setCoItemsCotizacion(coItemsCotizacion);
            }
            CoProveedores idProveedor = coCotizacionesPorPorveedor.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                coCotizacionesPorPorveedor.setIdProveedor(idProveedor);
            }
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorList = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach : coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList()) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorList.add(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach);
            }
            coCotizacionesPorPorveedor.setCoDetalleCotizacionPorProveedorList(attachedCoDetalleCotizacionPorProveedorList);
            em.persist(coCotizacionesPorPorveedor);
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorPorveedorList().add(coCotizacionesPorPorveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            if (idProveedor != null) {
                idProveedor.getCoCotizacionesPorPorveedorList().add(coCotizacionesPorPorveedor);
                idProveedor = em.merge(idProveedor);
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList()) {
                CoCotizacionesPorPorveedor oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                if (oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor != null) {
                    oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                    oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK()) != null) {
                throw new PreexistingEntityException("CoCotizacionesPorPorveedor " + coCotizacionesPorPorveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoCotizacionesPorPorveedor coCotizacionesPorPorveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdSucursal(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdEmpresa(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK().setIdCotizacion(coCotizacionesPorPorveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorPorveedor persistentCoCotizacionesPorPorveedor = em.find(CoCotizacionesPorPorveedor.class, coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK());
            CoItemsCotizacion coItemsCotizacionOld = persistentCoCotizacionesPorPorveedor.getCoItemsCotizacion();
            CoItemsCotizacion coItemsCotizacionNew = coCotizacionesPorPorveedor.getCoItemsCotizacion();
            CoProveedores idProveedorOld = persistentCoCotizacionesPorPorveedor.getIdProveedor();
            CoProveedores idProveedorNew = coCotizacionesPorPorveedor.getIdProveedor();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOld = persistentCoCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListNew = coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList();
            List<String> illegalOrphanMessages = null;
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOld) {
                if (!coDetalleCotizacionPorProveedorListNew.contains(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetalleCotizacionPorProveedor " + coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor + " since its coCotizacionesPorPorveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (coItemsCotizacionNew != null) {
                coItemsCotizacionNew = em.getReference(coItemsCotizacionNew.getClass(), coItemsCotizacionNew.getCoItemsCotizacionPK());
                coCotizacionesPorPorveedor.setCoItemsCotizacion(coItemsCotizacionNew);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                coCotizacionesPorPorveedor.setIdProveedor(idProveedorNew);
            }
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorListNew = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach : coDetalleCotizacionPorProveedorListNew) {
                coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorListNew.add(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach);
            }
            coDetalleCotizacionPorProveedorListNew = attachedCoDetalleCotizacionPorProveedorListNew;
            coCotizacionesPorPorveedor.setCoDetalleCotizacionPorProveedorList(coDetalleCotizacionPorProveedorListNew);
            coCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedor);
            if (coItemsCotizacionOld != null && !coItemsCotizacionOld.equals(coItemsCotizacionNew)) {
                coItemsCotizacionOld.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedor);
                coItemsCotizacionOld = em.merge(coItemsCotizacionOld);
            }
            if (coItemsCotizacionNew != null && !coItemsCotizacionNew.equals(coItemsCotizacionOld)) {
                coItemsCotizacionNew.getCoCotizacionesPorPorveedorList().add(coCotizacionesPorPorveedor);
                coItemsCotizacionNew = em.merge(coItemsCotizacionNew);
            }
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedor);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getCoCotizacionesPorPorveedorList().add(coCotizacionesPorPorveedor);
                idProveedorNew = em.merge(idProveedorNew);
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListNew) {
                if (!coDetalleCotizacionPorProveedorListOld.contains(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor)) {
                    CoCotizacionesPorPorveedor oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoCotizacionesPorPorveedor();
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.setCoCotizacionesPorPorveedor(coCotizacionesPorPorveedor);
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    if (oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor != null && !oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.equals(coCotizacionesPorPorveedor)) {
                        oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                        oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(oldCoCotizacionesPorPorveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoCotizacionesPorPorveedorPK id = coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK();
                if (findCoCotizacionesPorPorveedor(id) == null) {
                    throw new NonexistentEntityException("The coCotizacionesPorPorveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoCotizacionesPorPorveedorPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorPorveedor coCotizacionesPorPorveedor;
            try {
                coCotizacionesPorPorveedor = em.getReference(CoCotizacionesPorPorveedor.class, id);
                coCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coCotizacionesPorPorveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOrphanCheck = coCotizacionesPorPorveedor.getCoDetalleCotizacionPorProveedorList();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOrphanCheckCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoCotizacionesPorPorveedor (" + coCotizacionesPorPorveedor + ") cannot be destroyed since the CoDetalleCotizacionPorProveedor " + coDetalleCotizacionPorProveedorListOrphanCheckCoDetalleCotizacionPorProveedor + " in its coDetalleCotizacionPorProveedorList field has a non-nullable coCotizacionesPorPorveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorPorveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            CoProveedores idProveedor = coCotizacionesPorPorveedor.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedor);
                idProveedor = em.merge(idProveedor);
            }
            em.remove(coCotizacionesPorPorveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities() {
        return findCoCotizacionesPorPorveedorEntities(true, -1, -1);
    }

    public List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities(int maxResults, int firstResult) {
        return findCoCotizacionesPorPorveedorEntities(false, maxResults, firstResult);
    }

    private List<CoCotizacionesPorPorveedor> findCoCotizacionesPorPorveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoCotizacionesPorPorveedor.class));
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

    public CoCotizacionesPorPorveedor findCoCotizacionesPorPorveedor(CoCotizacionesPorPorveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoCotizacionesPorPorveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoCotizacionesPorPorveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoCotizacionesPorPorveedor> rt = cq.from(CoCotizacionesPorPorveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
