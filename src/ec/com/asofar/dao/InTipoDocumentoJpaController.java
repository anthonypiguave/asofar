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
import ec.com.asofar.dto.InMovimientos;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.InTipoDocumento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admini
 */
public class InTipoDocumentoJpaController implements Serializable {

    public InTipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InTipoDocumento inTipoDocumento) {
        if (inTipoDocumento.getInMovimientosList() == null) {
            inTipoDocumento.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (inTipoDocumento.getInKardexList() == null) {
            inTipoDocumento.setInKardexList(new ArrayList<InKardex>());
        }
        if (inTipoDocumento.getCoItemsCotizacionList() == null) {
            inTipoDocumento.setCoItemsCotizacionList(new ArrayList<CoItemsCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : inTipoDocumento.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            inTipoDocumento.setInMovimientosList(attachedInMovimientosList);
            List<InKardex> attachedInKardexList = new ArrayList<InKardex>();
            for (InKardex inKardexListInKardexToAttach : inTipoDocumento.getInKardexList()) {
                inKardexListInKardexToAttach = em.getReference(inKardexListInKardexToAttach.getClass(), inKardexListInKardexToAttach.getInKardexPK());
                attachedInKardexList.add(inKardexListInKardexToAttach);
            }
            inTipoDocumento.setInKardexList(attachedInKardexList);
            List<CoItemsCotizacion> attachedCoItemsCotizacionList = new ArrayList<CoItemsCotizacion>();
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacionToAttach : inTipoDocumento.getCoItemsCotizacionList()) {
                coItemsCotizacionListCoItemsCotizacionToAttach = em.getReference(coItemsCotizacionListCoItemsCotizacionToAttach.getClass(), coItemsCotizacionListCoItemsCotizacionToAttach.getCoItemsCotizacionPK());
                attachedCoItemsCotizacionList.add(coItemsCotizacionListCoItemsCotizacionToAttach);
            }
            inTipoDocumento.setCoItemsCotizacionList(attachedCoItemsCotizacionList);
            em.persist(inTipoDocumento);
            for (InMovimientos inMovimientosListInMovimientos : inTipoDocumento.getInMovimientosList()) {
                InTipoDocumento oldInTipoDocumentoOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getInTipoDocumento();
                inMovimientosListInMovimientos.setInTipoDocumento(inTipoDocumento);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldInTipoDocumentoOfInMovimientosListInMovimientos != null) {
                    oldInTipoDocumentoOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldInTipoDocumentoOfInMovimientosListInMovimientos = em.merge(oldInTipoDocumentoOfInMovimientosListInMovimientos);
                }
            }
            for (InKardex inKardexListInKardex : inTipoDocumento.getInKardexList()) {
                InTipoDocumento oldInTipoDocumentoOfInKardexListInKardex = inKardexListInKardex.getInTipoDocumento();
                inKardexListInKardex.setInTipoDocumento(inTipoDocumento);
                inKardexListInKardex = em.merge(inKardexListInKardex);
                if (oldInTipoDocumentoOfInKardexListInKardex != null) {
                    oldInTipoDocumentoOfInKardexListInKardex.getInKardexList().remove(inKardexListInKardex);
                    oldInTipoDocumentoOfInKardexListInKardex = em.merge(oldInTipoDocumentoOfInKardexListInKardex);
                }
            }
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacion : inTipoDocumento.getCoItemsCotizacionList()) {
                InTipoDocumento oldIdTipoDocumentoOfCoItemsCotizacionListCoItemsCotizacion = coItemsCotizacionListCoItemsCotizacion.getIdTipoDocumento();
                coItemsCotizacionListCoItemsCotizacion.setIdTipoDocumento(inTipoDocumento);
                coItemsCotizacionListCoItemsCotizacion = em.merge(coItemsCotizacionListCoItemsCotizacion);
                if (oldIdTipoDocumentoOfCoItemsCotizacionListCoItemsCotizacion != null) {
                    oldIdTipoDocumentoOfCoItemsCotizacionListCoItemsCotizacion.getCoItemsCotizacionList().remove(coItemsCotizacionListCoItemsCotizacion);
                    oldIdTipoDocumentoOfCoItemsCotizacionListCoItemsCotizacion = em.merge(oldIdTipoDocumentoOfCoItemsCotizacionListCoItemsCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InTipoDocumento inTipoDocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoDocumento persistentInTipoDocumento = em.find(InTipoDocumento.class, inTipoDocumento.getIdTipoDocumento());
            List<InMovimientos> inMovimientosListOld = persistentInTipoDocumento.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = inTipoDocumento.getInMovimientosList();
            List<InKardex> inKardexListOld = persistentInTipoDocumento.getInKardexList();
            List<InKardex> inKardexListNew = inTipoDocumento.getInKardexList();
            List<CoItemsCotizacion> coItemsCotizacionListOld = persistentInTipoDocumento.getCoItemsCotizacionList();
            List<CoItemsCotizacion> coItemsCotizacionListNew = inTipoDocumento.getCoItemsCotizacionList();
            List<String> illegalOrphanMessages = null;
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its inTipoDocumento field is not nullable.");
                }
            }
            for (InKardex inKardexListOldInKardex : inKardexListOld) {
                if (!inKardexListNew.contains(inKardexListOldInKardex)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InKardex " + inKardexListOldInKardex + " since its inTipoDocumento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            inTipoDocumento.setInMovimientosList(inMovimientosListNew);
            List<InKardex> attachedInKardexListNew = new ArrayList<InKardex>();
            for (InKardex inKardexListNewInKardexToAttach : inKardexListNew) {
                inKardexListNewInKardexToAttach = em.getReference(inKardexListNewInKardexToAttach.getClass(), inKardexListNewInKardexToAttach.getInKardexPK());
                attachedInKardexListNew.add(inKardexListNewInKardexToAttach);
            }
            inKardexListNew = attachedInKardexListNew;
            inTipoDocumento.setInKardexList(inKardexListNew);
            List<CoItemsCotizacion> attachedCoItemsCotizacionListNew = new ArrayList<CoItemsCotizacion>();
            for (CoItemsCotizacion coItemsCotizacionListNewCoItemsCotizacionToAttach : coItemsCotizacionListNew) {
                coItemsCotizacionListNewCoItemsCotizacionToAttach = em.getReference(coItemsCotizacionListNewCoItemsCotizacionToAttach.getClass(), coItemsCotizacionListNewCoItemsCotizacionToAttach.getCoItemsCotizacionPK());
                attachedCoItemsCotizacionListNew.add(coItemsCotizacionListNewCoItemsCotizacionToAttach);
            }
            coItemsCotizacionListNew = attachedCoItemsCotizacionListNew;
            inTipoDocumento.setCoItemsCotizacionList(coItemsCotizacionListNew);
            inTipoDocumento = em.merge(inTipoDocumento);
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    InTipoDocumento oldInTipoDocumentoOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getInTipoDocumento();
                    inMovimientosListNewInMovimientos.setInTipoDocumento(inTipoDocumento);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldInTipoDocumentoOfInMovimientosListNewInMovimientos != null && !oldInTipoDocumentoOfInMovimientosListNewInMovimientos.equals(inTipoDocumento)) {
                        oldInTipoDocumentoOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldInTipoDocumentoOfInMovimientosListNewInMovimientos = em.merge(oldInTipoDocumentoOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            for (InKardex inKardexListNewInKardex : inKardexListNew) {
                if (!inKardexListOld.contains(inKardexListNewInKardex)) {
                    InTipoDocumento oldInTipoDocumentoOfInKardexListNewInKardex = inKardexListNewInKardex.getInTipoDocumento();
                    inKardexListNewInKardex.setInTipoDocumento(inTipoDocumento);
                    inKardexListNewInKardex = em.merge(inKardexListNewInKardex);
                    if (oldInTipoDocumentoOfInKardexListNewInKardex != null && !oldInTipoDocumentoOfInKardexListNewInKardex.equals(inTipoDocumento)) {
                        oldInTipoDocumentoOfInKardexListNewInKardex.getInKardexList().remove(inKardexListNewInKardex);
                        oldInTipoDocumentoOfInKardexListNewInKardex = em.merge(oldInTipoDocumentoOfInKardexListNewInKardex);
                    }
                }
            }
            for (CoItemsCotizacion coItemsCotizacionListOldCoItemsCotizacion : coItemsCotizacionListOld) {
                if (!coItemsCotizacionListNew.contains(coItemsCotizacionListOldCoItemsCotizacion)) {
                    coItemsCotizacionListOldCoItemsCotizacion.setIdTipoDocumento(null);
                    coItemsCotizacionListOldCoItemsCotizacion = em.merge(coItemsCotizacionListOldCoItemsCotizacion);
                }
            }
            for (CoItemsCotizacion coItemsCotizacionListNewCoItemsCotizacion : coItemsCotizacionListNew) {
                if (!coItemsCotizacionListOld.contains(coItemsCotizacionListNewCoItemsCotizacion)) {
                    InTipoDocumento oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion = coItemsCotizacionListNewCoItemsCotizacion.getIdTipoDocumento();
                    coItemsCotizacionListNewCoItemsCotizacion.setIdTipoDocumento(inTipoDocumento);
                    coItemsCotizacionListNewCoItemsCotizacion = em.merge(coItemsCotizacionListNewCoItemsCotizacion);
                    if (oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion != null && !oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion.equals(inTipoDocumento)) {
                        oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion.getCoItemsCotizacionList().remove(coItemsCotizacionListNewCoItemsCotizacion);
                        oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion = em.merge(oldIdTipoDocumentoOfCoItemsCotizacionListNewCoItemsCotizacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inTipoDocumento.getIdTipoDocumento();
                if (findInTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The inTipoDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InTipoDocumento inTipoDocumento;
            try {
                inTipoDocumento = em.getReference(InTipoDocumento.class, id);
                inTipoDocumento.getIdTipoDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inTipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InMovimientos> inMovimientosListOrphanCheck = inTipoDocumento.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InTipoDocumento (" + inTipoDocumento + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable inTipoDocumento field.");
            }
            List<InKardex> inKardexListOrphanCheck = inTipoDocumento.getInKardexList();
            for (InKardex inKardexListOrphanCheckInKardex : inKardexListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InTipoDocumento (" + inTipoDocumento + ") cannot be destroyed since the InKardex " + inKardexListOrphanCheckInKardex + " in its inKardexList field has a non-nullable inTipoDocumento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CoItemsCotizacion> coItemsCotizacionList = inTipoDocumento.getCoItemsCotizacionList();
            for (CoItemsCotizacion coItemsCotizacionListCoItemsCotizacion : coItemsCotizacionList) {
                coItemsCotizacionListCoItemsCotizacion.setIdTipoDocumento(null);
                coItemsCotizacionListCoItemsCotizacion = em.merge(coItemsCotizacionListCoItemsCotizacion);
            }
            em.remove(inTipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InTipoDocumento> findInTipoDocumentoEntities() {
        return findInTipoDocumentoEntities(true, -1, -1);
    }

    public List<InTipoDocumento> findInTipoDocumentoEntities(int maxResults, int firstResult) {
        return findInTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<InTipoDocumento> findInTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InTipoDocumento.class));
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

    public InTipoDocumento findInTipoDocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InTipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InTipoDocumento> rt = cq.from(InTipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
