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
import ec.com.asofar.dto.CoCotizacionesPorPorveedor;
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
        if (coItemsCotizacion.getCoCotizacionesPorPorveedorList() == null) {
            coItemsCotizacion.setCoCotizacionesPorPorveedorList(new ArrayList<CoCotizacionesPorPorveedor>());
        }
        if (coItemsCotizacion.getCoDetItemsCotizacionList() == null) {
            coItemsCotizacion.setCoDetItemsCotizacionList(new ArrayList<CoDetItemsCotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CoCotizacionesPorPorveedor> attachedCoCotizacionesPorPorveedorList = new ArrayList<CoCotizacionesPorPorveedor>();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach : coItemsCotizacion.getCoCotizacionesPorPorveedorList()) {
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach = em.getReference(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach.getClass(), coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach.getCoCotizacionesPorPorveedorPK());
                attachedCoCotizacionesPorPorveedorList.add(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedorToAttach);
            }
            coItemsCotizacion.setCoCotizacionesPorPorveedorList(attachedCoCotizacionesPorPorveedorList);
            List<CoDetItemsCotizacion> attachedCoDetItemsCotizacionList = new ArrayList<CoDetItemsCotizacion>();
            for (CoDetItemsCotizacion coDetItemsCotizacionListCoDetItemsCotizacionToAttach : coItemsCotizacion.getCoDetItemsCotizacionList()) {
                coDetItemsCotizacionListCoDetItemsCotizacionToAttach = em.getReference(coDetItemsCotizacionListCoDetItemsCotizacionToAttach.getClass(), coDetItemsCotizacionListCoDetItemsCotizacionToAttach.getCoDetItemsCotizacionPK());
                attachedCoDetItemsCotizacionList.add(coDetItemsCotizacionListCoDetItemsCotizacionToAttach);
            }
            coItemsCotizacion.setCoDetItemsCotizacionList(attachedCoDetItemsCotizacionList);
            em.persist(coItemsCotizacion);
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor : coItemsCotizacion.getCoCotizacionesPorPorveedorList()) {
                CoItemsCotizacion oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.getCoItemsCotizacion();
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.setCoItemsCotizacion(coItemsCotizacion);
                coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
                if (oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor != null) {
                    oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
                    oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor = em.merge(oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListCoCotizacionesPorPorveedor);
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
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorListOld = persistentCoItemsCotizacion.getCoCotizacionesPorPorveedorList();
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorListNew = coItemsCotizacion.getCoCotizacionesPorPorveedorList();
            List<CoDetItemsCotizacion> coDetItemsCotizacionListOld = persistentCoItemsCotizacion.getCoDetItemsCotizacionList();
            List<CoDetItemsCotizacion> coDetItemsCotizacionListNew = coItemsCotizacion.getCoDetItemsCotizacionList();
            List<String> illegalOrphanMessages = null;
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorListOld) {
                if (!coCotizacionesPorPorveedorListNew.contains(coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoCotizacionesPorPorveedor " + coCotizacionesPorPorveedorListOldCoCotizacionesPorPorveedor + " since its coItemsCotizacion field is not nullable.");
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
            List<CoCotizacionesPorPorveedor> attachedCoCotizacionesPorPorveedorListNew = new ArrayList<CoCotizacionesPorPorveedor>();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach : coCotizacionesPorPorveedorListNew) {
                coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach = em.getReference(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach.getClass(), coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach.getCoCotizacionesPorPorveedorPK());
                attachedCoCotizacionesPorPorveedorListNew.add(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedorToAttach);
            }
            coCotizacionesPorPorveedorListNew = attachedCoCotizacionesPorPorveedorListNew;
            coItemsCotizacion.setCoCotizacionesPorPorveedorList(coCotizacionesPorPorveedorListNew);
            List<CoDetItemsCotizacion> attachedCoDetItemsCotizacionListNew = new ArrayList<CoDetItemsCotizacion>();
            for (CoDetItemsCotizacion coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach : coDetItemsCotizacionListNew) {
                coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach = em.getReference(coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach.getClass(), coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach.getCoDetItemsCotizacionPK());
                attachedCoDetItemsCotizacionListNew.add(coDetItemsCotizacionListNewCoDetItemsCotizacionToAttach);
            }
            coDetItemsCotizacionListNew = attachedCoDetItemsCotizacionListNew;
            coItemsCotizacion.setCoDetItemsCotizacionList(coDetItemsCotizacionListNew);
            coItemsCotizacion = em.merge(coItemsCotizacion);
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorListNew) {
                if (!coCotizacionesPorPorveedorListOld.contains(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor)) {
                    CoItemsCotizacion oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.getCoItemsCotizacion();
                    coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.setCoItemsCotizacion(coItemsCotizacion);
                    coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = em.merge(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
                    if (oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor != null && !oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.equals(coItemsCotizacion)) {
                        oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor.getCoCotizacionesPorPorveedorList().remove(coCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
                        oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor = em.merge(oldCoItemsCotizacionOfCoCotizacionesPorPorveedorListNewCoCotizacionesPorPorveedor);
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
            List<CoCotizacionesPorPorveedor> coCotizacionesPorPorveedorListOrphanCheck = coItemsCotizacion.getCoCotizacionesPorPorveedorList();
            for (CoCotizacionesPorPorveedor coCotizacionesPorPorveedorListOrphanCheckCoCotizacionesPorPorveedor : coCotizacionesPorPorveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CoItemsCotizacion (" + coItemsCotizacion + ") cannot be destroyed since the CoCotizacionesPorPorveedor " + coCotizacionesPorPorveedorListOrphanCheckCoCotizacionesPorPorveedor + " in its coCotizacionesPorPorveedorList field has a non-nullable coItemsCotizacion field.");
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
