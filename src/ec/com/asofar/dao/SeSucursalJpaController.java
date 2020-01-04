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
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.InBodega;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.SeUsuarioSucurRol;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeSucursalPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class SeSucursalJpaController implements Serializable {

    public SeSucursalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeSucursal seSucursal) throws PreexistingEntityException, Exception {
        if (seSucursal.getSeSucursalPK() == null) {
            seSucursal.setSeSucursalPK(new SeSucursalPK());
        }
        if (seSucursal.getInBodegaList() == null) {
            seSucursal.setInBodegaList(new ArrayList<InBodega>());
        }
        if (seSucursal.getCoOrdenPedidoList() == null) {
            seSucursal.setCoOrdenPedidoList(new ArrayList<CoOrdenPedido>());
        }
        if (seSucursal.getCoOrdenComprasList() == null) {
            seSucursal.setCoOrdenComprasList(new ArrayList<CoOrdenCompras>());
        }
        if (seSucursal.getInMovimientosList() == null) {
            seSucursal.setInMovimientosList(new ArrayList<InMovimientos>());
        }
        if (seSucursal.getVeFacturaList() == null) {
            seSucursal.setVeFacturaList(new ArrayList<VeFactura>());
        }
        if (seSucursal.getPrTarifarioList() == null) {
            seSucursal.setPrTarifarioList(new ArrayList<PrTarifario>());
        }
        if (seSucursal.getSeUsuarioSucurRolList() == null) {
            seSucursal.setSeUsuarioSucurRolList(new ArrayList<SeUsuarioSucurRol>());
        }
        if (seSucursal.getInKardexList() == null) {
            seSucursal.setInKardexList(new ArrayList<InKardex>());
        }
        seSucursal.getSeSucursalPK().setIdEmpresa(seSucursal.getSeEmpresa().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa seEmpresa = seSucursal.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa = em.getReference(seEmpresa.getClass(), seEmpresa.getIdEmpresa());
                seSucursal.setSeEmpresa(seEmpresa);
            }
            List<InBodega> attachedInBodegaList = new ArrayList<InBodega>();
            for (InBodega inBodegaListInBodegaToAttach : seSucursal.getInBodegaList()) {
                inBodegaListInBodegaToAttach = em.getReference(inBodegaListInBodegaToAttach.getClass(), inBodegaListInBodegaToAttach.getInBodegaPK());
                attachedInBodegaList.add(inBodegaListInBodegaToAttach);
            }
            seSucursal.setInBodegaList(attachedInBodegaList);
            List<CoOrdenPedido> attachedCoOrdenPedidoList = new ArrayList<CoOrdenPedido>();
            for (CoOrdenPedido coOrdenPedidoListCoOrdenPedidoToAttach : seSucursal.getCoOrdenPedidoList()) {
                coOrdenPedidoListCoOrdenPedidoToAttach = em.getReference(coOrdenPedidoListCoOrdenPedidoToAttach.getClass(), coOrdenPedidoListCoOrdenPedidoToAttach.getCoOrdenPedidoPK());
                attachedCoOrdenPedidoList.add(coOrdenPedidoListCoOrdenPedidoToAttach);
            }
            seSucursal.setCoOrdenPedidoList(attachedCoOrdenPedidoList);
            List<CoOrdenCompras> attachedCoOrdenComprasList = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListCoOrdenComprasToAttach : seSucursal.getCoOrdenComprasList()) {
                coOrdenComprasListCoOrdenComprasToAttach = em.getReference(coOrdenComprasListCoOrdenComprasToAttach.getClass(), coOrdenComprasListCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasList.add(coOrdenComprasListCoOrdenComprasToAttach);
            }
            seSucursal.setCoOrdenComprasList(attachedCoOrdenComprasList);
            List<InMovimientos> attachedInMovimientosList = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListInMovimientosToAttach : seSucursal.getInMovimientosList()) {
                inMovimientosListInMovimientosToAttach = em.getReference(inMovimientosListInMovimientosToAttach.getClass(), inMovimientosListInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosList.add(inMovimientosListInMovimientosToAttach);
            }
            seSucursal.setInMovimientosList(attachedInMovimientosList);
            List<VeFactura> attachedVeFacturaList = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListVeFacturaToAttach : seSucursal.getVeFacturaList()) {
                veFacturaListVeFacturaToAttach = em.getReference(veFacturaListVeFacturaToAttach.getClass(), veFacturaListVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaList.add(veFacturaListVeFacturaToAttach);
            }
            seSucursal.setVeFacturaList(attachedVeFacturaList);
            List<PrTarifario> attachedPrTarifarioList = new ArrayList<PrTarifario>();
            for (PrTarifario prTarifarioListPrTarifarioToAttach : seSucursal.getPrTarifarioList()) {
                prTarifarioListPrTarifarioToAttach = em.getReference(prTarifarioListPrTarifarioToAttach.getClass(), prTarifarioListPrTarifarioToAttach.getPrTarifarioPK());
                attachedPrTarifarioList.add(prTarifarioListPrTarifarioToAttach);
            }
            seSucursal.setPrTarifarioList(attachedPrTarifarioList);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolList = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRolToAttach : seSucursal.getSeUsuarioSucurRolList()) {
                seUsuarioSucurRolListSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolList.add(seUsuarioSucurRolListSeUsuarioSucurRolToAttach);
            }
            seSucursal.setSeUsuarioSucurRolList(attachedSeUsuarioSucurRolList);
            List<InKardex> attachedInKardexList = new ArrayList<InKardex>();
            for (InKardex inKardexListInKardexToAttach : seSucursal.getInKardexList()) {
                inKardexListInKardexToAttach = em.getReference(inKardexListInKardexToAttach.getClass(), inKardexListInKardexToAttach.getInKardexPK());
                attachedInKardexList.add(inKardexListInKardexToAttach);
            }
            seSucursal.setInKardexList(attachedInKardexList);
            em.persist(seSucursal);
            if (seEmpresa != null) {
                seEmpresa.getSeSucursalList().add(seSucursal);
                seEmpresa = em.merge(seEmpresa);
            }
            for (InBodega inBodegaListInBodega : seSucursal.getInBodegaList()) {
                SeSucursal oldSeSucursalOfInBodegaListInBodega = inBodegaListInBodega.getSeSucursal();
                inBodegaListInBodega.setSeSucursal(seSucursal);
                inBodegaListInBodega = em.merge(inBodegaListInBodega);
                if (oldSeSucursalOfInBodegaListInBodega != null) {
                    oldSeSucursalOfInBodegaListInBodega.getInBodegaList().remove(inBodegaListInBodega);
                    oldSeSucursalOfInBodegaListInBodega = em.merge(oldSeSucursalOfInBodegaListInBodega);
                }
            }
            for (CoOrdenPedido coOrdenPedidoListCoOrdenPedido : seSucursal.getCoOrdenPedidoList()) {
                SeSucursal oldSeSucursalOfCoOrdenPedidoListCoOrdenPedido = coOrdenPedidoListCoOrdenPedido.getSeSucursal();
                coOrdenPedidoListCoOrdenPedido.setSeSucursal(seSucursal);
                coOrdenPedidoListCoOrdenPedido = em.merge(coOrdenPedidoListCoOrdenPedido);
                if (oldSeSucursalOfCoOrdenPedidoListCoOrdenPedido != null) {
                    oldSeSucursalOfCoOrdenPedidoListCoOrdenPedido.getCoOrdenPedidoList().remove(coOrdenPedidoListCoOrdenPedido);
                    oldSeSucursalOfCoOrdenPedidoListCoOrdenPedido = em.merge(oldSeSucursalOfCoOrdenPedidoListCoOrdenPedido);
                }
            }
            for (CoOrdenCompras coOrdenComprasListCoOrdenCompras : seSucursal.getCoOrdenComprasList()) {
                SeSucursal oldSeSucursalOfCoOrdenComprasListCoOrdenCompras = coOrdenComprasListCoOrdenCompras.getSeSucursal();
                coOrdenComprasListCoOrdenCompras.setSeSucursal(seSucursal);
                coOrdenComprasListCoOrdenCompras = em.merge(coOrdenComprasListCoOrdenCompras);
                if (oldSeSucursalOfCoOrdenComprasListCoOrdenCompras != null) {
                    oldSeSucursalOfCoOrdenComprasListCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListCoOrdenCompras);
                    oldSeSucursalOfCoOrdenComprasListCoOrdenCompras = em.merge(oldSeSucursalOfCoOrdenComprasListCoOrdenCompras);
                }
            }
            for (InMovimientos inMovimientosListInMovimientos : seSucursal.getInMovimientosList()) {
                SeSucursal oldSeSucursalOfInMovimientosListInMovimientos = inMovimientosListInMovimientos.getSeSucursal();
                inMovimientosListInMovimientos.setSeSucursal(seSucursal);
                inMovimientosListInMovimientos = em.merge(inMovimientosListInMovimientos);
                if (oldSeSucursalOfInMovimientosListInMovimientos != null) {
                    oldSeSucursalOfInMovimientosListInMovimientos.getInMovimientosList().remove(inMovimientosListInMovimientos);
                    oldSeSucursalOfInMovimientosListInMovimientos = em.merge(oldSeSucursalOfInMovimientosListInMovimientos);
                }
            }
            for (VeFactura veFacturaListVeFactura : seSucursal.getVeFacturaList()) {
                SeSucursal oldSeSucursalOfVeFacturaListVeFactura = veFacturaListVeFactura.getSeSucursal();
                veFacturaListVeFactura.setSeSucursal(seSucursal);
                veFacturaListVeFactura = em.merge(veFacturaListVeFactura);
                if (oldSeSucursalOfVeFacturaListVeFactura != null) {
                    oldSeSucursalOfVeFacturaListVeFactura.getVeFacturaList().remove(veFacturaListVeFactura);
                    oldSeSucursalOfVeFacturaListVeFactura = em.merge(oldSeSucursalOfVeFacturaListVeFactura);
                }
            }
            for (PrTarifario prTarifarioListPrTarifario : seSucursal.getPrTarifarioList()) {
                SeSucursal oldSeSucursalOfPrTarifarioListPrTarifario = prTarifarioListPrTarifario.getSeSucursal();
                prTarifarioListPrTarifario.setSeSucursal(seSucursal);
                prTarifarioListPrTarifario = em.merge(prTarifarioListPrTarifario);
                if (oldSeSucursalOfPrTarifarioListPrTarifario != null) {
                    oldSeSucursalOfPrTarifarioListPrTarifario.getPrTarifarioList().remove(prTarifarioListPrTarifario);
                    oldSeSucursalOfPrTarifarioListPrTarifario = em.merge(oldSeSucursalOfPrTarifarioListPrTarifario);
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListSeUsuarioSucurRol : seSucursal.getSeUsuarioSucurRolList()) {
                SeSucursal oldSeSucursalOfSeUsuarioSucurRolListSeUsuarioSucurRol = seUsuarioSucurRolListSeUsuarioSucurRol.getSeSucursal();
                seUsuarioSucurRolListSeUsuarioSucurRol.setSeSucursal(seSucursal);
                seUsuarioSucurRolListSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListSeUsuarioSucurRol);
                if (oldSeSucursalOfSeUsuarioSucurRolListSeUsuarioSucurRol != null) {
                    oldSeSucursalOfSeUsuarioSucurRolListSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListSeUsuarioSucurRol);
                    oldSeSucursalOfSeUsuarioSucurRolListSeUsuarioSucurRol = em.merge(oldSeSucursalOfSeUsuarioSucurRolListSeUsuarioSucurRol);
                }
            }
            for (InKardex inKardexListInKardex : seSucursal.getInKardexList()) {
                SeSucursal oldSeSucursalOfInKardexListInKardex = inKardexListInKardex.getSeSucursal();
                inKardexListInKardex.setSeSucursal(seSucursal);
                inKardexListInKardex = em.merge(inKardexListInKardex);
                if (oldSeSucursalOfInKardexListInKardex != null) {
                    oldSeSucursalOfInKardexListInKardex.getInKardexList().remove(inKardexListInKardex);
                    oldSeSucursalOfInKardexListInKardex = em.merge(oldSeSucursalOfInKardexListInKardex);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeSucursal(seSucursal.getSeSucursalPK()) != null) {
                throw new PreexistingEntityException("SeSucursal " + seSucursal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeSucursal seSucursal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        seSucursal.getSeSucursalPK().setIdEmpresa(seSucursal.getSeEmpresa().getIdEmpresa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal persistentSeSucursal = em.find(SeSucursal.class, seSucursal.getSeSucursalPK());
            SeEmpresa seEmpresaOld = persistentSeSucursal.getSeEmpresa();
            SeEmpresa seEmpresaNew = seSucursal.getSeEmpresa();
            List<InBodega> inBodegaListOld = persistentSeSucursal.getInBodegaList();
            List<InBodega> inBodegaListNew = seSucursal.getInBodegaList();
            List<CoOrdenPedido> coOrdenPedidoListOld = persistentSeSucursal.getCoOrdenPedidoList();
            List<CoOrdenPedido> coOrdenPedidoListNew = seSucursal.getCoOrdenPedidoList();
            List<CoOrdenCompras> coOrdenComprasListOld = persistentSeSucursal.getCoOrdenComprasList();
            List<CoOrdenCompras> coOrdenComprasListNew = seSucursal.getCoOrdenComprasList();
            List<InMovimientos> inMovimientosListOld = persistentSeSucursal.getInMovimientosList();
            List<InMovimientos> inMovimientosListNew = seSucursal.getInMovimientosList();
            List<VeFactura> veFacturaListOld = persistentSeSucursal.getVeFacturaList();
            List<VeFactura> veFacturaListNew = seSucursal.getVeFacturaList();
            List<PrTarifario> prTarifarioListOld = persistentSeSucursal.getPrTarifarioList();
            List<PrTarifario> prTarifarioListNew = seSucursal.getPrTarifarioList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOld = persistentSeSucursal.getSeUsuarioSucurRolList();
            List<SeUsuarioSucurRol> seUsuarioSucurRolListNew = seSucursal.getSeUsuarioSucurRolList();
            List<InKardex> inKardexListOld = persistentSeSucursal.getInKardexList();
            List<InKardex> inKardexListNew = seSucursal.getInKardexList();
            List<String> illegalOrphanMessages = null;
            for (InBodega inBodegaListOldInBodega : inBodegaListOld) {
                if (!inBodegaListNew.contains(inBodegaListOldInBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InBodega " + inBodegaListOldInBodega + " since its seSucursal field is not nullable.");
                }
            }
            for (CoOrdenPedido coOrdenPedidoListOldCoOrdenPedido : coOrdenPedidoListOld) {
                if (!coOrdenPedidoListNew.contains(coOrdenPedidoListOldCoOrdenPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoOrdenPedido " + coOrdenPedidoListOldCoOrdenPedido + " since its seSucursal field is not nullable.");
                }
            }
            for (CoOrdenCompras coOrdenComprasListOldCoOrdenCompras : coOrdenComprasListOld) {
                if (!coOrdenComprasListNew.contains(coOrdenComprasListOldCoOrdenCompras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CoOrdenCompras " + coOrdenComprasListOldCoOrdenCompras + " since its seSucursal field is not nullable.");
                }
            }
            for (InMovimientos inMovimientosListOldInMovimientos : inMovimientosListOld) {
                if (!inMovimientosListNew.contains(inMovimientosListOldInMovimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InMovimientos " + inMovimientosListOldInMovimientos + " since its seSucursal field is not nullable.");
                }
            }
            for (VeFactura veFacturaListOldVeFactura : veFacturaListOld) {
                if (!veFacturaListNew.contains(veFacturaListOldVeFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VeFactura " + veFacturaListOldVeFactura + " since its seSucursal field is not nullable.");
                }
            }
            for (PrTarifario prTarifarioListOldPrTarifario : prTarifarioListOld) {
                if (!prTarifarioListNew.contains(prTarifarioListOldPrTarifario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrTarifario " + prTarifarioListOldPrTarifario + " since its seSucursal field is not nullable.");
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListOldSeUsuarioSucurRol : seUsuarioSucurRolListOld) {
                if (!seUsuarioSucurRolListNew.contains(seUsuarioSucurRolListOldSeUsuarioSucurRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeUsuarioSucurRol " + seUsuarioSucurRolListOldSeUsuarioSucurRol + " since its seSucursal field is not nullable.");
                }
            }
            for (InKardex inKardexListOldInKardex : inKardexListOld) {
                if (!inKardexListNew.contains(inKardexListOldInKardex)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InKardex " + inKardexListOldInKardex + " since its seSucursal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (seEmpresaNew != null) {
                seEmpresaNew = em.getReference(seEmpresaNew.getClass(), seEmpresaNew.getIdEmpresa());
                seSucursal.setSeEmpresa(seEmpresaNew);
            }
            List<InBodega> attachedInBodegaListNew = new ArrayList<InBodega>();
            for (InBodega inBodegaListNewInBodegaToAttach : inBodegaListNew) {
                inBodegaListNewInBodegaToAttach = em.getReference(inBodegaListNewInBodegaToAttach.getClass(), inBodegaListNewInBodegaToAttach.getInBodegaPK());
                attachedInBodegaListNew.add(inBodegaListNewInBodegaToAttach);
            }
            inBodegaListNew = attachedInBodegaListNew;
            seSucursal.setInBodegaList(inBodegaListNew);
            List<CoOrdenPedido> attachedCoOrdenPedidoListNew = new ArrayList<CoOrdenPedido>();
            for (CoOrdenPedido coOrdenPedidoListNewCoOrdenPedidoToAttach : coOrdenPedidoListNew) {
                coOrdenPedidoListNewCoOrdenPedidoToAttach = em.getReference(coOrdenPedidoListNewCoOrdenPedidoToAttach.getClass(), coOrdenPedidoListNewCoOrdenPedidoToAttach.getCoOrdenPedidoPK());
                attachedCoOrdenPedidoListNew.add(coOrdenPedidoListNewCoOrdenPedidoToAttach);
            }
            coOrdenPedidoListNew = attachedCoOrdenPedidoListNew;
            seSucursal.setCoOrdenPedidoList(coOrdenPedidoListNew);
            List<CoOrdenCompras> attachedCoOrdenComprasListNew = new ArrayList<CoOrdenCompras>();
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenComprasToAttach : coOrdenComprasListNew) {
                coOrdenComprasListNewCoOrdenComprasToAttach = em.getReference(coOrdenComprasListNewCoOrdenComprasToAttach.getClass(), coOrdenComprasListNewCoOrdenComprasToAttach.getCoOrdenComprasPK());
                attachedCoOrdenComprasListNew.add(coOrdenComprasListNewCoOrdenComprasToAttach);
            }
            coOrdenComprasListNew = attachedCoOrdenComprasListNew;
            seSucursal.setCoOrdenComprasList(coOrdenComprasListNew);
            List<InMovimientos> attachedInMovimientosListNew = new ArrayList<InMovimientos>();
            for (InMovimientos inMovimientosListNewInMovimientosToAttach : inMovimientosListNew) {
                inMovimientosListNewInMovimientosToAttach = em.getReference(inMovimientosListNewInMovimientosToAttach.getClass(), inMovimientosListNewInMovimientosToAttach.getInMovimientosPK());
                attachedInMovimientosListNew.add(inMovimientosListNewInMovimientosToAttach);
            }
            inMovimientosListNew = attachedInMovimientosListNew;
            seSucursal.setInMovimientosList(inMovimientosListNew);
            List<VeFactura> attachedVeFacturaListNew = new ArrayList<VeFactura>();
            for (VeFactura veFacturaListNewVeFacturaToAttach : veFacturaListNew) {
                veFacturaListNewVeFacturaToAttach = em.getReference(veFacturaListNewVeFacturaToAttach.getClass(), veFacturaListNewVeFacturaToAttach.getVeFacturaPK());
                attachedVeFacturaListNew.add(veFacturaListNewVeFacturaToAttach);
            }
            veFacturaListNew = attachedVeFacturaListNew;
            seSucursal.setVeFacturaList(veFacturaListNew);
            List<PrTarifario> attachedPrTarifarioListNew = new ArrayList<PrTarifario>();
            for (PrTarifario prTarifarioListNewPrTarifarioToAttach : prTarifarioListNew) {
                prTarifarioListNewPrTarifarioToAttach = em.getReference(prTarifarioListNewPrTarifarioToAttach.getClass(), prTarifarioListNewPrTarifarioToAttach.getPrTarifarioPK());
                attachedPrTarifarioListNew.add(prTarifarioListNewPrTarifarioToAttach);
            }
            prTarifarioListNew = attachedPrTarifarioListNew;
            seSucursal.setPrTarifarioList(prTarifarioListNew);
            List<SeUsuarioSucurRol> attachedSeUsuarioSucurRolListNew = new ArrayList<SeUsuarioSucurRol>();
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach : seUsuarioSucurRolListNew) {
                seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach = em.getReference(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getClass(), seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach.getSeUsuarioSucurRolPK());
                attachedSeUsuarioSucurRolListNew.add(seUsuarioSucurRolListNewSeUsuarioSucurRolToAttach);
            }
            seUsuarioSucurRolListNew = attachedSeUsuarioSucurRolListNew;
            seSucursal.setSeUsuarioSucurRolList(seUsuarioSucurRolListNew);
            List<InKardex> attachedInKardexListNew = new ArrayList<InKardex>();
            for (InKardex inKardexListNewInKardexToAttach : inKardexListNew) {
                inKardexListNewInKardexToAttach = em.getReference(inKardexListNewInKardexToAttach.getClass(), inKardexListNewInKardexToAttach.getInKardexPK());
                attachedInKardexListNew.add(inKardexListNewInKardexToAttach);
            }
            inKardexListNew = attachedInKardexListNew;
            seSucursal.setInKardexList(inKardexListNew);
            seSucursal = em.merge(seSucursal);
            if (seEmpresaOld != null && !seEmpresaOld.equals(seEmpresaNew)) {
                seEmpresaOld.getSeSucursalList().remove(seSucursal);
                seEmpresaOld = em.merge(seEmpresaOld);
            }
            if (seEmpresaNew != null && !seEmpresaNew.equals(seEmpresaOld)) {
                seEmpresaNew.getSeSucursalList().add(seSucursal);
                seEmpresaNew = em.merge(seEmpresaNew);
            }
            for (InBodega inBodegaListNewInBodega : inBodegaListNew) {
                if (!inBodegaListOld.contains(inBodegaListNewInBodega)) {
                    SeSucursal oldSeSucursalOfInBodegaListNewInBodega = inBodegaListNewInBodega.getSeSucursal();
                    inBodegaListNewInBodega.setSeSucursal(seSucursal);
                    inBodegaListNewInBodega = em.merge(inBodegaListNewInBodega);
                    if (oldSeSucursalOfInBodegaListNewInBodega != null && !oldSeSucursalOfInBodegaListNewInBodega.equals(seSucursal)) {
                        oldSeSucursalOfInBodegaListNewInBodega.getInBodegaList().remove(inBodegaListNewInBodega);
                        oldSeSucursalOfInBodegaListNewInBodega = em.merge(oldSeSucursalOfInBodegaListNewInBodega);
                    }
                }
            }
            for (CoOrdenPedido coOrdenPedidoListNewCoOrdenPedido : coOrdenPedidoListNew) {
                if (!coOrdenPedidoListOld.contains(coOrdenPedidoListNewCoOrdenPedido)) {
                    SeSucursal oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido = coOrdenPedidoListNewCoOrdenPedido.getSeSucursal();
                    coOrdenPedidoListNewCoOrdenPedido.setSeSucursal(seSucursal);
                    coOrdenPedidoListNewCoOrdenPedido = em.merge(coOrdenPedidoListNewCoOrdenPedido);
                    if (oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido != null && !oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido.equals(seSucursal)) {
                        oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido.getCoOrdenPedidoList().remove(coOrdenPedidoListNewCoOrdenPedido);
                        oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido = em.merge(oldSeSucursalOfCoOrdenPedidoListNewCoOrdenPedido);
                    }
                }
            }
            for (CoOrdenCompras coOrdenComprasListNewCoOrdenCompras : coOrdenComprasListNew) {
                if (!coOrdenComprasListOld.contains(coOrdenComprasListNewCoOrdenCompras)) {
                    SeSucursal oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras = coOrdenComprasListNewCoOrdenCompras.getSeSucursal();
                    coOrdenComprasListNewCoOrdenCompras.setSeSucursal(seSucursal);
                    coOrdenComprasListNewCoOrdenCompras = em.merge(coOrdenComprasListNewCoOrdenCompras);
                    if (oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras != null && !oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras.equals(seSucursal)) {
                        oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras.getCoOrdenComprasList().remove(coOrdenComprasListNewCoOrdenCompras);
                        oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras = em.merge(oldSeSucursalOfCoOrdenComprasListNewCoOrdenCompras);
                    }
                }
            }
            for (InMovimientos inMovimientosListNewInMovimientos : inMovimientosListNew) {
                if (!inMovimientosListOld.contains(inMovimientosListNewInMovimientos)) {
                    SeSucursal oldSeSucursalOfInMovimientosListNewInMovimientos = inMovimientosListNewInMovimientos.getSeSucursal();
                    inMovimientosListNewInMovimientos.setSeSucursal(seSucursal);
                    inMovimientosListNewInMovimientos = em.merge(inMovimientosListNewInMovimientos);
                    if (oldSeSucursalOfInMovimientosListNewInMovimientos != null && !oldSeSucursalOfInMovimientosListNewInMovimientos.equals(seSucursal)) {
                        oldSeSucursalOfInMovimientosListNewInMovimientos.getInMovimientosList().remove(inMovimientosListNewInMovimientos);
                        oldSeSucursalOfInMovimientosListNewInMovimientos = em.merge(oldSeSucursalOfInMovimientosListNewInMovimientos);
                    }
                }
            }
            for (VeFactura veFacturaListNewVeFactura : veFacturaListNew) {
                if (!veFacturaListOld.contains(veFacturaListNewVeFactura)) {
                    SeSucursal oldSeSucursalOfVeFacturaListNewVeFactura = veFacturaListNewVeFactura.getSeSucursal();
                    veFacturaListNewVeFactura.setSeSucursal(seSucursal);
                    veFacturaListNewVeFactura = em.merge(veFacturaListNewVeFactura);
                    if (oldSeSucursalOfVeFacturaListNewVeFactura != null && !oldSeSucursalOfVeFacturaListNewVeFactura.equals(seSucursal)) {
                        oldSeSucursalOfVeFacturaListNewVeFactura.getVeFacturaList().remove(veFacturaListNewVeFactura);
                        oldSeSucursalOfVeFacturaListNewVeFactura = em.merge(oldSeSucursalOfVeFacturaListNewVeFactura);
                    }
                }
            }
            for (PrTarifario prTarifarioListNewPrTarifario : prTarifarioListNew) {
                if (!prTarifarioListOld.contains(prTarifarioListNewPrTarifario)) {
                    SeSucursal oldSeSucursalOfPrTarifarioListNewPrTarifario = prTarifarioListNewPrTarifario.getSeSucursal();
                    prTarifarioListNewPrTarifario.setSeSucursal(seSucursal);
                    prTarifarioListNewPrTarifario = em.merge(prTarifarioListNewPrTarifario);
                    if (oldSeSucursalOfPrTarifarioListNewPrTarifario != null && !oldSeSucursalOfPrTarifarioListNewPrTarifario.equals(seSucursal)) {
                        oldSeSucursalOfPrTarifarioListNewPrTarifario.getPrTarifarioList().remove(prTarifarioListNewPrTarifario);
                        oldSeSucursalOfPrTarifarioListNewPrTarifario = em.merge(oldSeSucursalOfPrTarifarioListNewPrTarifario);
                    }
                }
            }
            for (SeUsuarioSucurRol seUsuarioSucurRolListNewSeUsuarioSucurRol : seUsuarioSucurRolListNew) {
                if (!seUsuarioSucurRolListOld.contains(seUsuarioSucurRolListNewSeUsuarioSucurRol)) {
                    SeSucursal oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = seUsuarioSucurRolListNewSeUsuarioSucurRol.getSeSucursal();
                    seUsuarioSucurRolListNewSeUsuarioSucurRol.setSeSucursal(seSucursal);
                    seUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                    if (oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol != null && !oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.equals(seSucursal)) {
                        oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol.getSeUsuarioSucurRolList().remove(seUsuarioSucurRolListNewSeUsuarioSucurRol);
                        oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol = em.merge(oldSeSucursalOfSeUsuarioSucurRolListNewSeUsuarioSucurRol);
                    }
                }
            }
            for (InKardex inKardexListNewInKardex : inKardexListNew) {
                if (!inKardexListOld.contains(inKardexListNewInKardex)) {
                    SeSucursal oldSeSucursalOfInKardexListNewInKardex = inKardexListNewInKardex.getSeSucursal();
                    inKardexListNewInKardex.setSeSucursal(seSucursal);
                    inKardexListNewInKardex = em.merge(inKardexListNewInKardex);
                    if (oldSeSucursalOfInKardexListNewInKardex != null && !oldSeSucursalOfInKardexListNewInKardex.equals(seSucursal)) {
                        oldSeSucursalOfInKardexListNewInKardex.getInKardexList().remove(inKardexListNewInKardex);
                        oldSeSucursalOfInKardexListNewInKardex = em.merge(oldSeSucursalOfInKardexListNewInKardex);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SeSucursalPK id = seSucursal.getSeSucursalPK();
                if (findSeSucursal(id) == null) {
                    throw new NonexistentEntityException("The seSucursal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SeSucursalPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeSucursal seSucursal;
            try {
                seSucursal = em.getReference(SeSucursal.class, id);
                seSucursal.getSeSucursalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seSucursal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InBodega> inBodegaListOrphanCheck = seSucursal.getInBodegaList();
            for (InBodega inBodegaListOrphanCheckInBodega : inBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the InBodega " + inBodegaListOrphanCheckInBodega + " in its inBodegaList field has a non-nullable seSucursal field.");
            }
            List<CoOrdenPedido> coOrdenPedidoListOrphanCheck = seSucursal.getCoOrdenPedidoList();
            for (CoOrdenPedido coOrdenPedidoListOrphanCheckCoOrdenPedido : coOrdenPedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the CoOrdenPedido " + coOrdenPedidoListOrphanCheckCoOrdenPedido + " in its coOrdenPedidoList field has a non-nullable seSucursal field.");
            }
            List<CoOrdenCompras> coOrdenComprasListOrphanCheck = seSucursal.getCoOrdenComprasList();
            for (CoOrdenCompras coOrdenComprasListOrphanCheckCoOrdenCompras : coOrdenComprasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the CoOrdenCompras " + coOrdenComprasListOrphanCheckCoOrdenCompras + " in its coOrdenComprasList field has a non-nullable seSucursal field.");
            }
            List<InMovimientos> inMovimientosListOrphanCheck = seSucursal.getInMovimientosList();
            for (InMovimientos inMovimientosListOrphanCheckInMovimientos : inMovimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the InMovimientos " + inMovimientosListOrphanCheckInMovimientos + " in its inMovimientosList field has a non-nullable seSucursal field.");
            }
            List<VeFactura> veFacturaListOrphanCheck = seSucursal.getVeFacturaList();
            for (VeFactura veFacturaListOrphanCheckVeFactura : veFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the VeFactura " + veFacturaListOrphanCheckVeFactura + " in its veFacturaList field has a non-nullable seSucursal field.");
            }
            List<PrTarifario> prTarifarioListOrphanCheck = seSucursal.getPrTarifarioList();
            for (PrTarifario prTarifarioListOrphanCheckPrTarifario : prTarifarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the PrTarifario " + prTarifarioListOrphanCheckPrTarifario + " in its prTarifarioList field has a non-nullable seSucursal field.");
            }
            List<SeUsuarioSucurRol> seUsuarioSucurRolListOrphanCheck = seSucursal.getSeUsuarioSucurRolList();
            for (SeUsuarioSucurRol seUsuarioSucurRolListOrphanCheckSeUsuarioSucurRol : seUsuarioSucurRolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the SeUsuarioSucurRol " + seUsuarioSucurRolListOrphanCheckSeUsuarioSucurRol + " in its seUsuarioSucurRolList field has a non-nullable seSucursal field.");
            }
            List<InKardex> inKardexListOrphanCheck = seSucursal.getInKardexList();
            for (InKardex inKardexListOrphanCheckInKardex : inKardexListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeSucursal (" + seSucursal + ") cannot be destroyed since the InKardex " + inKardexListOrphanCheckInKardex + " in its inKardexList field has a non-nullable seSucursal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SeEmpresa seEmpresa = seSucursal.getSeEmpresa();
            if (seEmpresa != null) {
                seEmpresa.getSeSucursalList().remove(seSucursal);
                seEmpresa = em.merge(seEmpresa);
            }
            em.remove(seSucursal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeSucursal> findSeSucursalEntities() {
        return findSeSucursalEntities(true, -1, -1);
    }

    public List<SeSucursal> findSeSucursalEntities(int maxResults, int firstResult) {
        return findSeSucursalEntities(false, maxResults, firstResult);
    }

    private List<SeSucursal> findSeSucursalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeSucursal.class));
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

    public SeSucursal findSeSucursal(SeSucursalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeSucursal.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeSucursalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeSucursal> rt = cq.from(SeSucursal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
