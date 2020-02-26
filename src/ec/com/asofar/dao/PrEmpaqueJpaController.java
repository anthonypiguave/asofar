/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dao;

import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.dto.PrEmpaque;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class PrEmpaqueJpaController implements Serializable {

    public PrEmpaqueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrEmpaque prEmpaque) {
        if (prEmpaque.getPrProductosList() == null) {
            prEmpaque.setPrProductosList(new ArrayList<PrProductos>());
        }
        if (prEmpaque.getPrProductosList1() == null) {
            prEmpaque.setPrProductosList1(new ArrayList<PrProductos>());
        }
        if (prEmpaque.getPrProductosList2() == null) {
            prEmpaque.setPrProductosList2(new ArrayList<PrProductos>());
        }
        if (prEmpaque.getPrProductosList3() == null) {
            prEmpaque.setPrProductosList3(new ArrayList<PrProductos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : prEmpaque.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            prEmpaque.setPrProductosList(attachedPrProductosList);
            List<PrProductos> attachedPrProductosList1 = new ArrayList<PrProductos>();
            for (PrProductos prProductosList1PrProductosToAttach : prEmpaque.getPrProductosList1()) {
                prProductosList1PrProductosToAttach = em.getReference(prProductosList1PrProductosToAttach.getClass(), prProductosList1PrProductosToAttach.getPrProductosPK());
                attachedPrProductosList1.add(prProductosList1PrProductosToAttach);
            }
            prEmpaque.setPrProductosList1(attachedPrProductosList1);
            List<PrProductos> attachedPrProductosList2 = new ArrayList<PrProductos>();
            for (PrProductos prProductosList2PrProductosToAttach : prEmpaque.getPrProductosList2()) {
                prProductosList2PrProductosToAttach = em.getReference(prProductosList2PrProductosToAttach.getClass(), prProductosList2PrProductosToAttach.getPrProductosPK());
                attachedPrProductosList2.add(prProductosList2PrProductosToAttach);
            }
            prEmpaque.setPrProductosList2(attachedPrProductosList2);
            List<PrProductos> attachedPrProductosList3 = new ArrayList<PrProductos>();
            for (PrProductos prProductosList3PrProductosToAttach : prEmpaque.getPrProductosList3()) {
                prProductosList3PrProductosToAttach = em.getReference(prProductosList3PrProductosToAttach.getClass(), prProductosList3PrProductosToAttach.getPrProductosPK());
                attachedPrProductosList3.add(prProductosList3PrProductosToAttach);
            }
            prEmpaque.setPrProductosList3(attachedPrProductosList3);
            em.persist(prEmpaque);
            for (PrProductos prProductosListPrProductos : prEmpaque.getPrProductosList()) {
                PrEmpaque oldMedidaEmpaqueCompraOfPrProductosListPrProductos = prProductosListPrProductos.getMedidaEmpaqueCompra();
                prProductosListPrProductos.setMedidaEmpaqueCompra(prEmpaque);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldMedidaEmpaqueCompraOfPrProductosListPrProductos != null) {
                    oldMedidaEmpaqueCompraOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldMedidaEmpaqueCompraOfPrProductosListPrProductos = em.merge(oldMedidaEmpaqueCompraOfPrProductosListPrProductos);
                }
            }
            for (PrProductos prProductosList1PrProductos : prEmpaque.getPrProductosList1()) {
                PrEmpaque oldMedidaPorEmpaqueCompraOfPrProductosList1PrProductos = prProductosList1PrProductos.getMedidaPorEmpaqueCompra();
                prProductosList1PrProductos.setMedidaPorEmpaqueCompra(prEmpaque);
                prProductosList1PrProductos = em.merge(prProductosList1PrProductos);
                if (oldMedidaPorEmpaqueCompraOfPrProductosList1PrProductos != null) {
                    oldMedidaPorEmpaqueCompraOfPrProductosList1PrProductos.getPrProductosList1().remove(prProductosList1PrProductos);
                    oldMedidaPorEmpaqueCompraOfPrProductosList1PrProductos = em.merge(oldMedidaPorEmpaqueCompraOfPrProductosList1PrProductos);
                }
            }
            for (PrProductos prProductosList2PrProductos : prEmpaque.getPrProductosList2()) {
                PrEmpaque oldMedidaEmpaqueVentaOfPrProductosList2PrProductos = prProductosList2PrProductos.getMedidaEmpaqueVenta();
                prProductosList2PrProductos.setMedidaEmpaqueVenta(prEmpaque);
                prProductosList2PrProductos = em.merge(prProductosList2PrProductos);
                if (oldMedidaEmpaqueVentaOfPrProductosList2PrProductos != null) {
                    oldMedidaEmpaqueVentaOfPrProductosList2PrProductos.getPrProductosList2().remove(prProductosList2PrProductos);
                    oldMedidaEmpaqueVentaOfPrProductosList2PrProductos = em.merge(oldMedidaEmpaqueVentaOfPrProductosList2PrProductos);
                }
            }
            for (PrProductos prProductosList3PrProductos : prEmpaque.getPrProductosList3()) {
                PrEmpaque oldMedidaPorEmpaqueVentaOfPrProductosList3PrProductos = prProductosList3PrProductos.getMedidaPorEmpaqueVenta();
                prProductosList3PrProductos.setMedidaPorEmpaqueVenta(prEmpaque);
                prProductosList3PrProductos = em.merge(prProductosList3PrProductos);
                if (oldMedidaPorEmpaqueVentaOfPrProductosList3PrProductos != null) {
                    oldMedidaPorEmpaqueVentaOfPrProductosList3PrProductos.getPrProductosList3().remove(prProductosList3PrProductos);
                    oldMedidaPorEmpaqueVentaOfPrProductosList3PrProductos = em.merge(oldMedidaPorEmpaqueVentaOfPrProductosList3PrProductos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrEmpaque prEmpaque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrEmpaque persistentPrEmpaque = em.find(PrEmpaque.class, prEmpaque.getId());
            List<PrProductos> prProductosListOld = persistentPrEmpaque.getPrProductosList();
            List<PrProductos> prProductosListNew = prEmpaque.getPrProductosList();
            List<PrProductos> prProductosList1Old = persistentPrEmpaque.getPrProductosList1();
            List<PrProductos> prProductosList1New = prEmpaque.getPrProductosList1();
            List<PrProductos> prProductosList2Old = persistentPrEmpaque.getPrProductosList2();
            List<PrProductos> prProductosList2New = prEmpaque.getPrProductosList2();
            List<PrProductos> prProductosList3Old = persistentPrEmpaque.getPrProductosList3();
            List<PrProductos> prProductosList3New = prEmpaque.getPrProductosList3();
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            prEmpaque.setPrProductosList(prProductosListNew);
            List<PrProductos> attachedPrProductosList1New = new ArrayList<PrProductos>();
            for (PrProductos prProductosList1NewPrProductosToAttach : prProductosList1New) {
                prProductosList1NewPrProductosToAttach = em.getReference(prProductosList1NewPrProductosToAttach.getClass(), prProductosList1NewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList1New.add(prProductosList1NewPrProductosToAttach);
            }
            prProductosList1New = attachedPrProductosList1New;
            prEmpaque.setPrProductosList1(prProductosList1New);
            List<PrProductos> attachedPrProductosList2New = new ArrayList<PrProductos>();
            for (PrProductos prProductosList2NewPrProductosToAttach : prProductosList2New) {
                prProductosList2NewPrProductosToAttach = em.getReference(prProductosList2NewPrProductosToAttach.getClass(), prProductosList2NewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList2New.add(prProductosList2NewPrProductosToAttach);
            }
            prProductosList2New = attachedPrProductosList2New;
            prEmpaque.setPrProductosList2(prProductosList2New);
            List<PrProductos> attachedPrProductosList3New = new ArrayList<PrProductos>();
            for (PrProductos prProductosList3NewPrProductosToAttach : prProductosList3New) {
                prProductosList3NewPrProductosToAttach = em.getReference(prProductosList3NewPrProductosToAttach.getClass(), prProductosList3NewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList3New.add(prProductosList3NewPrProductosToAttach);
            }
            prProductosList3New = attachedPrProductosList3New;
            prEmpaque.setPrProductosList3(prProductosList3New);
            prEmpaque = em.merge(prEmpaque);
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    prProductosListOldPrProductos.setMedidaEmpaqueCompra(null);
                    prProductosListOldPrProductos = em.merge(prProductosListOldPrProductos);
                }
            }
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    PrEmpaque oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getMedidaEmpaqueCompra();
                    prProductosListNewPrProductos.setMedidaEmpaqueCompra(prEmpaque);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos != null && !oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos.equals(prEmpaque)) {
                        oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos = em.merge(oldMedidaEmpaqueCompraOfPrProductosListNewPrProductos);
                    }
                }
            }
            for (PrProductos prProductosList1OldPrProductos : prProductosList1Old) {
                if (!prProductosList1New.contains(prProductosList1OldPrProductos)) {
                    prProductosList1OldPrProductos.setMedidaPorEmpaqueCompra(null);
                    prProductosList1OldPrProductos = em.merge(prProductosList1OldPrProductos);
                }
            }
            for (PrProductos prProductosList1NewPrProductos : prProductosList1New) {
                if (!prProductosList1Old.contains(prProductosList1NewPrProductos)) {
                    PrEmpaque oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos = prProductosList1NewPrProductos.getMedidaPorEmpaqueCompra();
                    prProductosList1NewPrProductos.setMedidaPorEmpaqueCompra(prEmpaque);
                    prProductosList1NewPrProductos = em.merge(prProductosList1NewPrProductos);
                    if (oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos != null && !oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos.equals(prEmpaque)) {
                        oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos.getPrProductosList1().remove(prProductosList1NewPrProductos);
                        oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos = em.merge(oldMedidaPorEmpaqueCompraOfPrProductosList1NewPrProductos);
                    }
                }
            }
            for (PrProductos prProductosList2OldPrProductos : prProductosList2Old) {
                if (!prProductosList2New.contains(prProductosList2OldPrProductos)) {
                    prProductosList2OldPrProductos.setMedidaEmpaqueVenta(null);
                    prProductosList2OldPrProductos = em.merge(prProductosList2OldPrProductos);
                }
            }
            for (PrProductos prProductosList2NewPrProductos : prProductosList2New) {
                if (!prProductosList2Old.contains(prProductosList2NewPrProductos)) {
                    PrEmpaque oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos = prProductosList2NewPrProductos.getMedidaEmpaqueVenta();
                    prProductosList2NewPrProductos.setMedidaEmpaqueVenta(prEmpaque);
                    prProductosList2NewPrProductos = em.merge(prProductosList2NewPrProductos);
                    if (oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos != null && !oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos.equals(prEmpaque)) {
                        oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos.getPrProductosList2().remove(prProductosList2NewPrProductos);
                        oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos = em.merge(oldMedidaEmpaqueVentaOfPrProductosList2NewPrProductos);
                    }
                }
            }
            for (PrProductos prProductosList3OldPrProductos : prProductosList3Old) {
                if (!prProductosList3New.contains(prProductosList3OldPrProductos)) {
                    prProductosList3OldPrProductos.setMedidaPorEmpaqueVenta(null);
                    prProductosList3OldPrProductos = em.merge(prProductosList3OldPrProductos);
                }
            }
            for (PrProductos prProductosList3NewPrProductos : prProductosList3New) {
                if (!prProductosList3Old.contains(prProductosList3NewPrProductos)) {
                    PrEmpaque oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos = prProductosList3NewPrProductos.getMedidaPorEmpaqueVenta();
                    prProductosList3NewPrProductos.setMedidaPorEmpaqueVenta(prEmpaque);
                    prProductosList3NewPrProductos = em.merge(prProductosList3NewPrProductos);
                    if (oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos != null && !oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos.equals(prEmpaque)) {
                        oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos.getPrProductosList3().remove(prProductosList3NewPrProductos);
                        oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos = em.merge(oldMedidaPorEmpaqueVentaOfPrProductosList3NewPrProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prEmpaque.getId();
                if (findPrEmpaque(id) == null) {
                    throw new NonexistentEntityException("The prEmpaque with id " + id + " no longer exists.");
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
            PrEmpaque prEmpaque;
            try {
                prEmpaque = em.getReference(PrEmpaque.class, id);
                prEmpaque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prEmpaque with id " + id + " no longer exists.", enfe);
            }
            List<PrProductos> prProductosList = prEmpaque.getPrProductosList();
            for (PrProductos prProductosListPrProductos : prProductosList) {
                prProductosListPrProductos.setMedidaEmpaqueCompra(null);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
            }
            List<PrProductos> prProductosList1 = prEmpaque.getPrProductosList1();
            for (PrProductos prProductosList1PrProductos : prProductosList1) {
                prProductosList1PrProductos.setMedidaPorEmpaqueCompra(null);
                prProductosList1PrProductos = em.merge(prProductosList1PrProductos);
            }
            List<PrProductos> prProductosList2 = prEmpaque.getPrProductosList2();
            for (PrProductos prProductosList2PrProductos : prProductosList2) {
                prProductosList2PrProductos.setMedidaEmpaqueVenta(null);
                prProductosList2PrProductos = em.merge(prProductosList2PrProductos);
            }
            List<PrProductos> prProductosList3 = prEmpaque.getPrProductosList3();
            for (PrProductos prProductosList3PrProductos : prProductosList3) {
                prProductosList3PrProductos.setMedidaPorEmpaqueVenta(null);
                prProductosList3PrProductos = em.merge(prProductosList3PrProductos);
            }
            em.remove(prEmpaque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrEmpaque> findPrEmpaqueEntities() {
        return findPrEmpaqueEntities(true, -1, -1);
    }

    public List<PrEmpaque> findPrEmpaqueEntities(int maxResults, int firstResult) {
        return findPrEmpaqueEntities(false, maxResults, firstResult);
    }

    private List<PrEmpaque> findPrEmpaqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrEmpaque.class));
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

    public PrEmpaque findPrEmpaque(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrEmpaque.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrEmpaqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrEmpaque> rt = cq.from(PrEmpaque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
