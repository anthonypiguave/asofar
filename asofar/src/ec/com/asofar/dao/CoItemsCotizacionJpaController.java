/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.IllegalOrphanException;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.InTipoCompra;
import ec.com.asofar.dto.InTipoDepartamento;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoItemsCotizacionPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class CoItemsCotizacionJpaController implements Serializable {

    public CoItemsCotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CoItemsCotizacion coItemsCotizacion) throws PreexistingEntityException, Exception {
        if (coItemsCotizacion.getCoItemsCotizacionPK() == null) {
            coItemsCotizacion.setCoItemsCotizacionPK(new CoItemsCotizacionPK());
        }
        if (coItemsCotizacion.getCoCotizacionesPorProveedorList() == null) {
            coItemsCotizacion.setCoCotizacionesPorProveedorList(new ArrayList<CoCotizacionesPorProveedor>());
        }
        if (coItemsCotizacion.getCoDetItemsCotizacionList() == null) {
            coItemsCotizacion.setCoDetItemsCotizacionList(new ArrayList<CoDetItemsCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoCompra idTipoCompra = coItemsCotizacion.getIdTipoCompra();
            if (idTipoCompra != null) {
                idTipoCompra = em.getReference(idTipoCompra.getClass(), idTipoCompra.getIdInTipoCompra());
                coItemsCotizacion.setIdTipoCompra(idTipoCompra);
            }
            InTipoDepartamento idDepartamento = coItemsCotizacion.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento = em.getReference(idDepartamento.getClass(), idDepartamento.getIdTipoDepartamento());
                coItemsCotizacion.setIdDepartamento(idDepartamento);
            }
            InTipoDocumento idTipoDocumento = coItemsCotizacion.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento = em.getReference(idTipoDocumento.getClass(), idTipoDocumento.getIdTipoDocumento());
                coItemsCotizacion.setIdTipoDocumento(idTipoDocumento);
            }
            List<CoCotizacionesPorProveedor> attachedCoCotizacionesPorProveedorList = new ArrayList<CoCotizacionesPorProveedor>();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach : coItemsCotizacion.getCoCotizacionesPorProveedorList()) {
                coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach = em.getReference(coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach.getClass(), coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach.getCoCotizacionesPorProveedorPK());
                attachedCoCotizacionesPorProveedorList.add(coCotizacionesPorProveedorListCoCotizacionesPorProveedorToAttach);
            }
            coItemsCotizacion.setCoCotizacionesPorProveedorList(attachedCoCotizacionesPorProveedorList);
            List<CoDetItemsCotizacion> attachedCoDetItemsCotizacionList = new ArrayList<CoDetItemsCotizacion>();
            for (CoDetItemsCotizacion coDetItemsCotizacionListCoDetItemsCotizacionToAttach : coItemsCotizacion.getCoDetItemsCotizacionList()) {
                coDetItemsCotizacionListCoDetItemsCotizacionToAttach = em.getReference(coDetItemsCotizacionListCoDetItemsCotizacionToAttach.getClass(), coDetItemsCotizacionListCoDetItemsCotizacionToAttach.getCoDetItemsCotizacionPK());
                attachedCoDetItemsCotizacionList.add(coDetItemsCotizacionListCoDetItemsCotizacionToAttach);
            }
            coItemsCotizacion.setCoDetItemsCotizacionList(attachedCoDetItemsCotizacionList);
            em.persist(coItemsCotizacion);
            if (idTipoCompra != null) {
                idTipoCompra.getCoItemsCotizacionList().add(coItemsCotizacion);
                idTipoCompra = em.merge(idTipoCompra);
            }
            if (idDepartamento != null) {
                idDepartamento.getCoItemsCotizacionList().add(coItemsCotizacion);
                idDepartamento = em.merge(idDepartamento);
            }
            if (idTipoDocumento != null) {
                idTipoDocumento.getCoItemsCotizacionList().add(coItemsCotizacion);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListCoCotizacionesPorProveedor : coItemsCotizacion.getCoCotizacionesPorProveedorList()) {
                CoItemsCotizacion oldCoItemsCotizacionOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor = coCotizacionesPorProveedorListCoCotizacionesPorProveedor.getCoItemsCotizacion();
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacion);
                coCotizacionesPorProveedorListCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListCoCotizacionesPorProveedor);
                if (oldCoItemsCotizacionOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor != null) {
                    oldCoItemsCotizacionOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedorListCoCotizacionesPorProveedor);
                    oldCoItemsCotizacionOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor = em.merge(oldCoItemsCotizacionOfCoCotizacionesPorProveedorListCoCotizacionesPorProveedor);
                }
            }
            for (CoDetItemsCotizacion coDetItemsCotizacionListCoDetItemsCotizacion : coItemsCotizacion.getCoDetItemsCotizacionList()) {
                CoItemsCotizacion oldCoItemsCotizacionOfCoDetItemsCotizacionListCoDetItemsCotizacion = coDetItemsCotizacionListCoDetItemsCotizacion.getCoItemsCotizacion();
                coDetItemsCotizacionListCoDetItemsCotizacion.setCoItemsCotizacion(coItemsCotizacion);
                coDetItemsCotizacionListCoDetItemsCotizacion = em.merge(coDetItemsCotizacionListCoDetItemsCotizacion);
                if (oldCoItemsCotizacionOfCoDetItemsCotizacionListCoDetItemsCotizacion != null) {
                    oldCoItemsCotizacionOfCoDetItemsCotizacionListCoDetItemsCotizacion.getCoDetItemsCotizacionList().remove(coDetItemsCotizacionListCoDetItemsCotizacion);
                    oldCoItemsCotizacionOfCoDetItemsCotizacionListCoDetItemsCotizacion = em.merge(oldCoItemsCotizacionOfCoDetItemsCotizacionListCoDetItemsCotizacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCoItemsCotizacion(coItemsCotizacion.getCoItemsCotizacionPK()) != null) {
                throw new PreexistingEntityException("CoItemsCotizacion " + coItemsCotizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoItemsCotizacion coItemsCotizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoItemsCotizacion persistentCoItemsCotizacion = em.find(CoItemsCotizacion.class, coItemsCotizacion.getCoItemsCotizacionPK());
            InTipoCompra idTipoCompraOld = persistentCoItemsCotizacion.getIdTipoCompra();
            InTipoCompra idTipoCompraNew = coItemsCotizacion.getIdTipoCompra();
            InTipoDepartamento idDepartamentoOld = persistentCoItemsCotizacion.getIdDepartamento();
            InTipoDepartamento idDepartamentoNew = coItemsCotizacion.getIdDepartamento();
            InTipoDocumento idTipoDocumentoOld = persistentCoItemsCotizacion.getIdTipoDocumento();
            InTipoDocumento idTipoDocumentoNew = coItemsCotizacion.getIdTipoDocumento();
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorListOld = persistentCoItemsCotizacion.getCoCotizacionesPorProveedorList();
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorListNew = coItemsCotizacion.getCoCotizacionesPorProveedorList();
            List<CoDetItemsCotizacion> coDetItemsCotizacionListOld = persistentCoItemsCotizacion.getCoDetItemsCotizacionList();
            List<CoDetItemsCotizacion> coDetItemsCotizacionListNew = coItemsCotizacion.getCoDetItemsCotizacionList();
            List<String> illegalOrphanMessages = null;
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor : coCotizacionesPorProveedorListOld) {
                if (!coCotizacionesPorProveedorListNew.contains(coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoCotizacionesPorProveedor " + coCotizacionesPorProveedorListOldCoCotizacionesPorProveedor + " since its coItemsCotizacion field is not nullable.");
                }
            }
            for (CoDetItemsCotizacion coDetItemsCotizacionListOldCoDetItemsCotizacion : coDetItemsCotizacionListOld) {
                if (!coDetItemsCotizacionListNew.contains(coDetItemsCotizacionListOldCoDetItemsCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoDetItemsCotizacion " + coDetItemsCotizacionListOldCoDetItemsCotizacion + " since its coItemsCotizacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoCompraNew != null) {
                idTipoCompraNew = em.getReference(idTipoCompraNew.getClass(), idTipoCompraNew.getIdInTipoCompra());
                coItemsCotizacion.setIdTipoCompra(idTipoCompraNew);
            }
            if (idDepartamentoNew != null) {
                idDepartamentoNew = em.getReference(idDepartamentoNew.getClass(), idDepartamentoNew.getIdTipoDepartamento());
                coItemsCotizacion.setIdDepartamento(idDepartamentoNew);
            }
            if (idTipoDocumentoNew != null) {
                idTipoDocumentoNew = em.getReference(idTipoDocumentoNew.getClass(), idTipoDocumentoNew.getIdTipoDocumento());
                coItemsCotizacion.setIdTipoDocumento(idTipoDocumentoNew);
            }
            List<CoCotizacionesPorProveedor> attachedCoCotizacionesPorProveedorListNew = new ArrayList<CoCotizacionesPorProveedor>();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach : coCotizacionesPorProveedorListNew) {
                coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach = em.getReference(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach.getClass(), coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach.getCoCotizacionesPorProveedorPK());
                attachedCoCotizacionesPorProveedorListNew.add(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedorToAttach);
            }
            coCotizacionesPorProveedorListNew = attachedCoCotizacionesPorProveedorListNew;
            coItemsCotizacion.setCoCotizacionesPorProveedorList(coCotizacionesPorProveedorListNew);
            List<CoDetItemsCotizacion> attachedCoDetItemsCotizacionListNew = new ArrayList<CoDetItemsCotizacion>();
            for (CoDetItemsCotizacion coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach : coDetItemsCotizacionListNew) {
                coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach = em.getReference(coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach.getClass(), coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach.getCoDetItemsCotizacionPK());
                attachedCoDetItemsCotizacionListNew.add(coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach);
            }
            coDetItemsCotizacionListNew = attachedCoDetItemsCotizacionListNew;
            coItemsCotizacion.setCoDetItemsCotizacionList(coDetItemsCotizacionListNew);
            coItemsCotizacion = em.merge(coItemsCotizacion);
            if (idTipoCompraOld != null && !idTipoCompraOld.equals(idTipoCompraNew)) {
                idTipoCompraOld.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idTipoCompraOld = em.merge(idTipoCompraOld);
            }
            if (idTipoCompraNew != null && !idTipoCompraNew.equals(idTipoCompraOld)) {
                idTipoCompraNew.getCoItemsCotizacionList().add(coItemsCotizacion);
                idTipoCompraNew = em.merge(idTipoCompraNew);
            }
            if (idDepartamentoOld != null && !idDepartamentoOld.equals(idDepartamentoNew)) {
                idDepartamentoOld.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idDepartamentoOld = em.merge(idDepartamentoOld);
            }
            if (idDepartamentoNew != null && !idDepartamentoNew.equals(idDepartamentoOld)) {
                idDepartamentoNew.getCoItemsCotizacionList().add(coItemsCotizacion);
                idDepartamentoNew = em.merge(idDepartamentoNew);
            }
            if (idTipoDocumentoOld != null && !idTipoDocumentoOld.equals(idTipoDocumentoNew)) {
                idTipoDocumentoOld.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idTipoDocumentoOld = em.merge(idTipoDocumentoOld);
            }
            if (idTipoDocumentoNew != null && !idTipoDocumentoNew.equals(idTipoDocumentoOld)) {
                idTipoDocumentoNew.getCoItemsCotizacionList().add(coItemsCotizacion);
                idTipoDocumentoNew = em.merge(idTipoDocumentoNew);
            }
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor : coCotizacionesPorProveedorListNew) {
                if (!coCotizacionesPorProveedorListOld.contains(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor)) {
                    CoItemsCotizacion oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.getCoItemsCotizacion();
                    coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.setCoItemsCotizacion(coItemsCotizacion);
                    coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = em.merge(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
                    if (oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor != null && !oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.equals(coItemsCotizacion)) {
                        oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor.getCoCotizacionesPorProveedorList().remove(coCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
                        oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor = em.merge(oldCoItemsCotizacionOfCoCotizacionesPorProveedorListNewCoCotizacionesPorProveedor);
                    }
                }
            }
            for (CoDetItemsCotizacion coDetItemsCotizacionListNewCoDetItemsCotizacion : coDetItemsCotizacionListNew) {
                if (!coDetItemsCotizacionListOld.contains(coDetItemsCotizacionListNewCoDetItemsCotizacion)) {
                    CoItemsCotizacion oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion = coDetItemsCotizacionListNewCoDetItemsCotizacion.getCoItemsCotizacion();
                    coDetItemsCotizacionListNewCoDetItemsCotizacion.setCoItemsCotizacion(coItemsCotizacion);
                    coDetItemsCotizacionListNewCoDetItemsCotizacion = em.merge(coDetItemsCotizacionListNewCoDetItemsCotizacion);
                    if (oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion != null && !oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion.equals(coItemsCotizacion)) {
                        oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion.getCoDetItemsCotizacionList().remove(coDetItemsCotizacionListNewCoDetItemsCotizacion);
                        oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion = em.merge(oldCoItemsCotizacionOfCoDetItemsCotizacionListNewCoDetItemsCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CoItemsCotizacionPK id = coItemsCotizacion.getCoItemsCotizacionPK();
                if (findCoItemsCotizacion(id) == null) {
                    throw new NonexistentEntityException("The coItemsCotizacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CoItemsCotizacionPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoItemsCotizacion coItemsCotizacion;
            try {
                coItemsCotizacion = em.getReference(CoItemsCotizacion.class, id);
                coItemsCotizacion.getCoItemsCotizacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coItemsCotizacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CoCotizacionesPorProveedor> coCotizacionesPorProveedorListOrphanCheck = coItemsCotizacion.getCoCotizacionesPorProveedorList();
            for (CoCotizacionesPorProveedor coCotizacionesPorProveedorListOrphanCheckCoCotizacionesPorProveedor : coCotizacionesPorProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoItemsCotizacion (" + coItemsCotizacion + ") cannot be destroyed since the CoCotizacionesPorProveedor " + coCotizacionesPorProveedorListOrphanCheckCoCotizacionesPorProveedor + " in its coCotizacionesPorProveedorList field has a non-nullable coItemsCotizacion field.");
            }
            List<CoDetItemsCotizacion> coDetItemsCotizacionListOrphanCheck = coItemsCotizacion.getCoDetItemsCotizacionList();
            for (CoDetItemsCotizacion coDetItemsCotizacionListOrphanCheckCoDetItemsCotizacion : coDetItemsCotizacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoItemsCotizacion (" + coItemsCotizacion + ") cannot be destroyed since the CoDetItemsCotizacion " + coDetItemsCotizacionListOrphanCheckCoDetItemsCotizacion + " in its coDetItemsCotizacionList field has a non-nullable coItemsCotizacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InTipoCompra idTipoCompra = coItemsCotizacion.getIdTipoCompra();
            if (idTipoCompra != null) {
                idTipoCompra.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idTipoCompra = em.merge(idTipoCompra);
            }
            InTipoDepartamento idDepartamento = coItemsCotizacion.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idDepartamento = em.merge(idDepartamento);
            }
            InTipoDocumento idTipoDocumento = coItemsCotizacion.getIdTipoDocumento();
            if (idTipoDocumento != null) {
                idTipoDocumento.getCoItemsCotizacionList().remove(coItemsCotizacion);
                idTipoDocumento = em.merge(idTipoDocumento);
            }
            em.remove(coItemsCotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoItemsCotizacion> findCoItemsCotizacionEntities() {
        return findCoItemsCotizacionEntities(true, -1, -1);
    }

    public List<CoItemsCotizacion> findCoItemsCotizacionEntities(int maxResults, int firstResult) {
        return findCoItemsCotizacionEntities(false, maxResults, firstResult);
    }

    private List<CoItemsCotizacion> findCoItemsCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoItemsCotizacion.class));
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

    public CoItemsCotizacion findCoItemsCotizacion(CoItemsCotizacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoItemsCotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoItemsCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoItemsCotizacion> rt = cq.from(CoItemsCotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
