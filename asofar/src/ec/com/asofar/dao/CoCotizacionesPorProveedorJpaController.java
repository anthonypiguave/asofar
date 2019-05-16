/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoCotizacionesPorProveedorPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class CoCotizacionesPorProveedorJpaController implements Serializable {

    public CoCotizacionesPorProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoCotizacionesPorProveedor coCotizacionesPorProveedor) throws PreexistingEntityException, Exception {
        if (coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK() == null) {
            coCotizacionesPorProveedor.setCoCotizacionesPorProveedorPK(new CoCotizacionesPorProveedorPK());
        }
        if (coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList() == null) {
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(new ArrayList<CoDetalleCotizacionPorProveedor>());
        }
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdEmpresa(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdSucursal(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdCotizacion(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoProveedores idProveedor = coCotizacionesPorProveedor.getIdProveedor();
            if (idProveedor != null) {
                idProveedor = em.getReference(idProveedor.getClass(), idProveedor.getIdProveedor());
                coCotizacionesPorProveedor.setIdProveedor(idProveedor);
            }
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorProveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion = em.getReference(coItemsCotizacion.getClass(), coItemsCotizacion.getCoItemsCotizacionPK());
                coCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacion);
            }
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorList = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach : coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList()) {
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorList.add(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedorToAttach);
            }
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(attachedCoDetalleCotizacionPorProveedorList);
            em.persist(coCotizacionesPorProveedor);
            if (idProveedor != null) {
                idProveedor.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                idProveedor = em.merge(idProveedor);
            }
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor : coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList()) {
                CoCotizacionesPorProveedor oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.setCoCotizacionesPorProveedor(coCotizacionesPorProveedor);
                coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                if (oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor != null) {
                    oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                    oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor = em.merge(oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListCoDetalleCotizacionPorProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoCotizacionesPorProveedor(coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK()) != null) {
                throw new PreexistingEntityException("CoCotizacionesPorProveedor " + coCotizacionesPorProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoCotizacionesPorProveedor coCotizacionesPorProveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdEmpresa(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdEmpresa());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdSucursal(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdSucursal());
        coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK().setIdCotizacion(coCotizacionesPorProveedor.getCoItemsCotizacion().getCoItemsCotizacionPK().getIdCotizacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor persistentCoCotizacionesPorProveedor = em.find(CoCotizacionesPorProveedor.class, coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK());
            CoProveedores idProveedorOld = persistentCoCotizacionesPorProveedor.getIdProveedor();
            CoProveedores idProveedorNew = coCotizacionesPorProveedor.getIdProveedor();
            CoItemsCotizacion coItemsCotizacionOld = persistentCoCotizacionesPorProveedor.getCoItemsCotizacion();
            CoItemsCotizacion coItemsCotizacionNew = coCotizacionesPorProveedor.getCoItemsCotizacion();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOld = persistentCoCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListNew = coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            List<String> illegalOrphanMessages = null;
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOld) {
                if (!coDetalleCotizacionPorProveedorListNew.contains(coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetalleCotizacionPorProveedor " + coDetalleCotizacionPorProveedorListOldCoDetalleCotizacionPorProveedor + " since its coCotizacionesPorProveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProveedorNew != null) {
                idProveedorNew = em.getReference(idProveedorNew.getClass(), idProveedorNew.getIdProveedor());
                coCotizacionesPorProveedor.setIdProveedor(idProveedorNew);
            }
            if (coItemsCotizacionNew != null) {
                coItemsCotizacionNew = em.getReference(coItemsCotizacionNew.getClass(), coItemsCotizacionNew.getCoItemsCotizacionPK());
                coCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacionNew);
            }
            List<CoDetalleCotizacionPorProveedor> attachedCoDetalleCotizacionPorProveedorListNew = new ArrayList<CoDetalleCotizacionPorProveedor>();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach : coDetalleCotizacionPorProveedorListNew) {
                coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach = em.getReference(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getClass(), coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach.getCoDetalleCotizacionPorProveedorPK());
                attachedCoDetalleCotizacionPorProveedorListNew.add(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedorToAttach);
            }
            coDetalleCotizacionPorProveedorListNew = attachedCoDetalleCotizacionPorProveedorListNew;
            coCotizacionesPorProveedor.setCoDetalleCotizacionPorProveedorList(coDetalleCotizacionPorProveedorListNew);
            coCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedor);
            if (idProveedorOld != null && !idProveedorOld.equals(idProveedorNew)) {
                idProveedorOld.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                idProveedorOld = em.merge(idProveedorOld);
            }
            if (idProveedorNew != null && !idProveedorNew.equals(idProveedorOld)) {
                idProveedorNew.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                idProveedorNew = em.merge(idProveedorNew);
            }
            if (coItemsCotizacionOld != null && !coItemsCotizacionOld.equals(coItemsCotizacionNew)) {
                coItemsCotizacionOld.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                coItemsCotizacionOld = em.merge(coItemsCotizacionOld);
            }
            if (coItemsCotizacionNew != null && !coItemsCotizacionNew.equals(coItemsCotizacionOld)) {
                coItemsCotizacionNew.getCoCotizacionesPorProveedorList().add(coCotizacionesPorProveedor);
                coItemsCotizacionNew = em.merge(coItemsCotizacionNew);
            }
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListNew) {
                if (!coDetalleCotizacionPorProveedorListOld.contains(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor)) {
                    CoCotizacionesPorProveedor oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoCotizacionesPorProveedor();
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.setCoCotizacionesPorProveedor(coCotizacionesPorProveedor);
                    coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    if (oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor != null && !oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.equals(coCotizacionesPorProveedor)) {
                        oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor.getCoDetalleCotizacionPorProveedorList().remove(coDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                        oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor = em.merge(oldCoCotizacionesPorProveedorOfCoDetalleCotizacionPorProveedorListNewCoDetalleCotizacionPorProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoCotizacionesPorProveedorPK id = coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK();
                if (findCoCotizacionesPorProveedor(id) == null) {
                    throw new NonexistentEntityException("The coCotizacionesPorProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoCotizacionesPorProveedorPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoCotizacionesPorProveedor coCotizacionesPorProveedor;
            try {
                coCotizacionesPorProveedor = em.getReference(CoCotizacionesPorProveedor.class, id);
                coCotizacionesPorProveedor.getCoCotizacionesPorProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coCotizacionesPorProveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorListOrphanCheck = coCotizacionesPorProveedor.getCoDetalleCotizacionPorProveedorList();
            for (CoDetalleCotizacionPorProveedor coDetalleCotizacionPorProveedorListOrphanCheckCoDetalleCotizacionPorProveedor : coDetalleCotizacionPorProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoCotizacionesPorProveedor (" + coCotizacionesPorProveedor + ") cannot be destroyed since the CoDetalleCotizacionPorProveedor " + coDetalleCotizacionPorProveedorListOrphanCheckCoDetalleCotizacionPorProveedor + " in its coDetalleCotizacionPorProveedorList field has a non-nullable coCotizacionesPorProveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CoProveedores idProveedor = coCotizacionesPorProveedor.getIdProveedor();
            if (idProveedor != null) {
                idProveedor.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                idProveedor = em.merge(idProveedor);
            }
            CoItemsCotizacion coItemsCotizacion = coCotizacionesPorProveedor.getCoItemsCotizacion();
            if (coItemsCotizacion != null) {
                coItemsCotizacion.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedor);
                coItemsCotizacion = em.merge(coItemsCotizacion);
            }
            em.remove(coCotizacionesPorProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities() {
        return findCoCotizacionesPorProveedorEntities(true, -1, -1);
    }

    public List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities(int maxResults, int firstResult) {
        return findCoCotizacionesPorProveedorEntities(false, maxResults, firstResult);
    }

    private List<CoCotizacionesPorProveedor> findCoCotizacionesPorProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoCotizacionesPorProveedor.class));
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

    public CoCotizacionesPorProveedor findCoCotizacionesPorProveedor(CoCotizacionesPorProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoCotizacionesPorProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoCotizacionesPorProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoCotizacionesPorProveedor> rt = cq.from(CoCotizacionesPorProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
