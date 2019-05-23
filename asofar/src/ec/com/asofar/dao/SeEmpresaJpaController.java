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
import ec.com.asofar.dto.PrProductos;
import java.util.ArrayList;
import java.util.List;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.SeEmpresa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class SeEmpresaJpaController implements Serializable {

    public SeEmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeEmpresa seEmpresa) {
        if (seEmpresa.getPrProductosList() == null) {
            seEmpresa.setPrProductosList(new ArrayList<PrProductos>());
        }
        if (seEmpresa.getPrSubgruposList() == null) {
            seEmpresa.setPrSubgruposList(new ArrayList<PrSubgrupos>());
        }
        if (seEmpresa.getSeSucursalList() == null) {
            seEmpresa.setSeSucursalList(new ArrayList<SeSucursal>());
        }
        if (seEmpresa.getPrGruposList() == null) {
            seEmpresa.setPrGruposList(new ArrayList<PrGrupos>());
        }
        if (seEmpresa.getPrPrestacionesList() == null) {
            seEmpresa.setPrPrestacionesList(new ArrayList<PrPrestaciones>());
        }
        if (seEmpresa.getPrTipoMedidasList() == null) {
            seEmpresa.setPrTipoMedidasList(new ArrayList<PrTipoMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PrProductos> attachedPrProductosList = new ArrayList<PrProductos>();
            for (PrProductos prProductosListPrProductosToAttach : seEmpresa.getPrProductosList()) {
                prProductosListPrProductosToAttach = em.getReference(prProductosListPrProductosToAttach.getClass(), prProductosListPrProductosToAttach.getPrProductosPK());
                attachedPrProductosList.add(prProductosListPrProductosToAttach);
            }
            seEmpresa.setPrProductosList(attachedPrProductosList);
            List<PrSubgrupos> attachedPrSubgruposList = new ArrayList<PrSubgrupos>();
            for (PrSubgrupos prSubgruposListPrSubgruposToAttach : seEmpresa.getPrSubgruposList()) {
                prSubgruposListPrSubgruposToAttach = em.getReference(prSubgruposListPrSubgruposToAttach.getClass(), prSubgruposListPrSubgruposToAttach.getPrSubgruposPK());
                attachedPrSubgruposList.add(prSubgruposListPrSubgruposToAttach);
            }
            seEmpresa.setPrSubgruposList(attachedPrSubgruposList);
            List<SeSucursal> attachedSeSucursalList = new ArrayList<SeSucursal>();
            for (SeSucursal seSucursalListSeSucursalToAttach : seEmpresa.getSeSucursalList()) {
                seSucursalListSeSucursalToAttach = em.getReference(seSucursalListSeSucursalToAttach.getClass(), seSucursalListSeSucursalToAttach.getSeSucursalPK());
                attachedSeSucursalList.add(seSucursalListSeSucursalToAttach);
            }
            seEmpresa.setSeSucursalList(attachedSeSucursalList);
            List<PrGrupos> attachedPrGruposList = new ArrayList<PrGrupos>();
            for (PrGrupos prGruposListPrGruposToAttach : seEmpresa.getPrGruposList()) {
                prGruposListPrGruposToAttach = em.getReference(prGruposListPrGruposToAttach.getClass(), prGruposListPrGruposToAttach.getIdGrupo());
                attachedPrGruposList.add(prGruposListPrGruposToAttach);
            }
            seEmpresa.setPrGruposList(attachedPrGruposList);
            List<PrPrestaciones> attachedPrPrestacionesList = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListPrPrestacionesToAttach : seEmpresa.getPrPrestacionesList()) {
                prPrestacionesListPrPrestacionesToAttach = em.getReference(prPrestacionesListPrPrestacionesToAttach.getClass(), prPrestacionesListPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesList.add(prPrestacionesListPrPrestacionesToAttach);
            }
            seEmpresa.setPrPrestacionesList(attachedPrPrestacionesList);
            List<PrTipoMedidas> attachedPrTipoMedidasList = new ArrayList<PrTipoMedidas>();
            for (PrTipoMedidas prTipoMedidasListPrTipoMedidasToAttach : seEmpresa.getPrTipoMedidasList()) {
                prTipoMedidasListPrTipoMedidasToAttach = em.getReference(prTipoMedidasListPrTipoMedidasToAttach.getClass(), prTipoMedidasListPrTipoMedidasToAttach.getIdTipoMedidas());
                attachedPrTipoMedidasList.add(prTipoMedidasListPrTipoMedidasToAttach);
            }
            seEmpresa.setPrTipoMedidasList(attachedPrTipoMedidasList);
            em.persist(seEmpresa);
            for (PrProductos prProductosListPrProductos : seEmpresa.getPrProductosList()) {
                SeEmpresa oldSeEmpresaOfPrProductosListPrProductos = prProductosListPrProductos.getSeEmpresa();
                prProductosListPrProductos.setSeEmpresa(seEmpresa);
                prProductosListPrProductos = em.merge(prProductosListPrProductos);
                if (oldSeEmpresaOfPrProductosListPrProductos != null) {
                    oldSeEmpresaOfPrProductosListPrProductos.getPrProductosList().remove(prProductosListPrProductos);
                    oldSeEmpresaOfPrProductosListPrProductos = em.merge(oldSeEmpresaOfPrProductosListPrProductos);
                }
            }
            for (PrSubgrupos prSubgruposListPrSubgrupos : seEmpresa.getPrSubgruposList()) {
                SeEmpresa oldIdEmpresaOfPrSubgruposListPrSubgrupos = prSubgruposListPrSubgrupos.getIdEmpresa();
                prSubgruposListPrSubgrupos.setIdEmpresa(seEmpresa);
                prSubgruposListPrSubgrupos = em.merge(prSubgruposListPrSubgrupos);
                if (oldIdEmpresaOfPrSubgruposListPrSubgrupos != null) {
                    oldIdEmpresaOfPrSubgruposListPrSubgrupos.getPrSubgruposList().remove(prSubgruposListPrSubgrupos);
                    oldIdEmpresaOfPrSubgruposListPrSubgrupos = em.merge(oldIdEmpresaOfPrSubgruposListPrSubgrupos);
                }
            }
            for (SeSucursal seSucursalListSeSucursal : seEmpresa.getSeSucursalList()) {
                SeEmpresa oldSeEmpresaOfSeSucursalListSeSucursal = seSucursalListSeSucursal.getSeEmpresa();
                seSucursalListSeSucursal.setSeEmpresa(seEmpresa);
                seSucursalListSeSucursal = em.merge(seSucursalListSeSucursal);
                if (oldSeEmpresaOfSeSucursalListSeSucursal != null) {
                    oldSeEmpresaOfSeSucursalListSeSucursal.getSeSucursalList().remove(seSucursalListSeSucursal);
                    oldSeEmpresaOfSeSucursalListSeSucursal = em.merge(oldSeEmpresaOfSeSucursalListSeSucursal);
                }
            }
            for (PrGrupos prGruposListPrGrupos : seEmpresa.getPrGruposList()) {
                SeEmpresa oldIdEmpresaOfPrGruposListPrGrupos = prGruposListPrGrupos.getIdEmpresa();
                prGruposListPrGrupos.setIdEmpresa(seEmpresa);
                prGruposListPrGrupos = em.merge(prGruposListPrGrupos);
                if (oldIdEmpresaOfPrGruposListPrGrupos != null) {
                    oldIdEmpresaOfPrGruposListPrGrupos.getPrGruposList().remove(prGruposListPrGrupos);
                    oldIdEmpresaOfPrGruposListPrGrupos = em.merge(oldIdEmpresaOfPrGruposListPrGrupos);
                }
            }
            for (PrPrestaciones prPrestacionesListPrPrestaciones : seEmpresa.getPrPrestacionesList()) {
                SeEmpresa oldSeEmpresaOfPrPrestacionesListPrPrestaciones = prPrestacionesListPrPrestaciones.getSeEmpresa();
                prPrestacionesListPrPrestaciones.setSeEmpresa(seEmpresa);
                prPrestacionesListPrPrestaciones = em.merge(prPrestacionesListPrPrestaciones);
                if (oldSeEmpresaOfPrPrestacionesListPrPrestaciones != null) {
                    oldSeEmpresaOfPrPrestacionesListPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListPrPrestaciones);
                    oldSeEmpresaOfPrPrestacionesListPrPrestaciones = em.merge(oldSeEmpresaOfPrPrestacionesListPrPrestaciones);
                }
            }
            for (PrTipoMedidas prTipoMedidasListPrTipoMedidas : seEmpresa.getPrTipoMedidasList()) {
                SeEmpresa oldIdEmpresaOfPrTipoMedidasListPrTipoMedidas = prTipoMedidasListPrTipoMedidas.getIdEmpresa();
                prTipoMedidasListPrTipoMedidas.setIdEmpresa(seEmpresa);
                prTipoMedidasListPrTipoMedidas = em.merge(prTipoMedidasListPrTipoMedidas);
                if (oldIdEmpresaOfPrTipoMedidasListPrTipoMedidas != null) {
                    oldIdEmpresaOfPrTipoMedidasListPrTipoMedidas.getPrTipoMedidasList().remove(prTipoMedidasListPrTipoMedidas);
                    oldIdEmpresaOfPrTipoMedidasListPrTipoMedidas = em.merge(oldIdEmpresaOfPrTipoMedidasListPrTipoMedidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeEmpresa seEmpresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeEmpresa persistentSeEmpresa = em.find(SeEmpresa.class, seEmpresa.getIdEmpresa());
            List<PrProductos> prProductosListOld = persistentSeEmpresa.getPrProductosList();
            List<PrProductos> prProductosListNew = seEmpresa.getPrProductosList();
            List<PrSubgrupos> prSubgruposListOld = persistentSeEmpresa.getPrSubgruposList();
            List<PrSubgrupos> prSubgruposListNew = seEmpresa.getPrSubgruposList();
            List<SeSucursal> seSucursalListOld = persistentSeEmpresa.getSeSucursalList();
            List<SeSucursal> seSucursalListNew = seEmpresa.getSeSucursalList();
            List<PrGrupos> prGruposListOld = persistentSeEmpresa.getPrGruposList();
            List<PrGrupos> prGruposListNew = seEmpresa.getPrGruposList();
            List<PrPrestaciones> prPrestacionesListOld = persistentSeEmpresa.getPrPrestacionesList();
            List<PrPrestaciones> prPrestacionesListNew = seEmpresa.getPrPrestacionesList();
            List<PrTipoMedidas> prTipoMedidasListOld = persistentSeEmpresa.getPrTipoMedidasList();
            List<PrTipoMedidas> prTipoMedidasListNew = seEmpresa.getPrTipoMedidasList();
            List<String> illegalOrphanMessages = null;
            for (PrProductos prProductosListOldPrProductos : prProductosListOld) {
                if (!prProductosListNew.contains(prProductosListOldPrProductos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrProductos " + prProductosListOldPrProductos + " since its seEmpresa field is not nullable.");
                }
            }
            for (SeSucursal seSucursalListOldSeSucursal : seSucursalListOld) {
                if (!seSucursalListNew.contains(seSucursalListOldSeSucursal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SeSucursal " + seSucursalListOldSeSucursal + " since its seEmpresa field is not nullable.");
                }
            }
            for (PrPrestaciones prPrestacionesListOldPrPrestaciones : prPrestacionesListOld) {
                if (!prPrestacionesListNew.contains(prPrestacionesListOldPrPrestaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrPrestaciones " + prPrestacionesListOldPrPrestaciones + " since its seEmpresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PrProductos> attachedPrProductosListNew = new ArrayList<PrProductos>();
            for (PrProductos prProductosListNewPrProductosToAttach : prProductosListNew) {
                prProductosListNewPrProductosToAttach = em.getReference(prProductosListNewPrProductosToAttach.getClass(), prProductosListNewPrProductosToAttach.getPrProductosPK());
                attachedPrProductosListNew.add(prProductosListNewPrProductosToAttach);
            }
            prProductosListNew = attachedPrProductosListNew;
            seEmpresa.setPrProductosList(prProductosListNew);
            List<PrSubgrupos> attachedPrSubgruposListNew = new ArrayList<PrSubgrupos>();
            for (PrSubgrupos prSubgruposListNewPrSubgruposToAttach : prSubgruposListNew) {
                prSubgruposListNewPrSubgruposToAttach = em.getReference(prSubgruposListNewPrSubgruposToAttach.getClass(), prSubgruposListNewPrSubgruposToAttach.getPrSubgruposPK());
                attachedPrSubgruposListNew.add(prSubgruposListNewPrSubgruposToAttach);
            }
            prSubgruposListNew = attachedPrSubgruposListNew;
            seEmpresa.setPrSubgruposList(prSubgruposListNew);
            List<SeSucursal> attachedSeSucursalListNew = new ArrayList<SeSucursal>();
            for (SeSucursal seSucursalListNewSeSucursalToAttach : seSucursalListNew) {
                seSucursalListNewSeSucursalToAttach = em.getReference(seSucursalListNewSeSucursalToAttach.getClass(), seSucursalListNewSeSucursalToAttach.getSeSucursalPK());
                attachedSeSucursalListNew.add(seSucursalListNewSeSucursalToAttach);
            }
            seSucursalListNew = attachedSeSucursalListNew;
            seEmpresa.setSeSucursalList(seSucursalListNew);
            List<PrGrupos> attachedPrGruposListNew = new ArrayList<PrGrupos>();
            for (PrGrupos prGruposListNewPrGruposToAttach : prGruposListNew) {
                prGruposListNewPrGruposToAttach = em.getReference(prGruposListNewPrGruposToAttach.getClass(), prGruposListNewPrGruposToAttach.getIdGrupo());
                attachedPrGruposListNew.add(prGruposListNewPrGruposToAttach);
            }
            prGruposListNew = attachedPrGruposListNew;
            seEmpresa.setPrGruposList(prGruposListNew);
            List<PrPrestaciones> attachedPrPrestacionesListNew = new ArrayList<PrPrestaciones>();
            for (PrPrestaciones prPrestacionesListNewPrPrestacionesToAttach : prPrestacionesListNew) {
                prPrestacionesListNewPrPrestacionesToAttach = em.getReference(prPrestacionesListNewPrPrestacionesToAttach.getClass(), prPrestacionesListNewPrPrestacionesToAttach.getPrPrestacionesPK());
                attachedPrPrestacionesListNew.add(prPrestacionesListNewPrPrestacionesToAttach);
            }
            prPrestacionesListNew = attachedPrPrestacionesListNew;
            seEmpresa.setPrPrestacionesList(prPrestacionesListNew);
            List<PrTipoMedidas> attachedPrTipoMedidasListNew = new ArrayList<PrTipoMedidas>();
            for (PrTipoMedidas prTipoMedidasListNewPrTipoMedidasToAttach : prTipoMedidasListNew) {
                prTipoMedidasListNewPrTipoMedidasToAttach = em.getReference(prTipoMedidasListNewPrTipoMedidasToAttach.getClass(), prTipoMedidasListNewPrTipoMedidasToAttach.getIdTipoMedidas());
                attachedPrTipoMedidasListNew.add(prTipoMedidasListNewPrTipoMedidasToAttach);
            }
            prTipoMedidasListNew = attachedPrTipoMedidasListNew;
            seEmpresa.setPrTipoMedidasList(prTipoMedidasListNew);
            seEmpresa = em.merge(seEmpresa);
            for (PrProductos prProductosListNewPrProductos : prProductosListNew) {
                if (!prProductosListOld.contains(prProductosListNewPrProductos)) {
                    SeEmpresa oldSeEmpresaOfPrProductosListNewPrProductos = prProductosListNewPrProductos.getSeEmpresa();
                    prProductosListNewPrProductos.setSeEmpresa(seEmpresa);
                    prProductosListNewPrProductos = em.merge(prProductosListNewPrProductos);
                    if (oldSeEmpresaOfPrProductosListNewPrProductos != null && !oldSeEmpresaOfPrProductosListNewPrProductos.equals(seEmpresa)) {
                        oldSeEmpresaOfPrProductosListNewPrProductos.getPrProductosList().remove(prProductosListNewPrProductos);
                        oldSeEmpresaOfPrProductosListNewPrProductos = em.merge(oldSeEmpresaOfPrProductosListNewPrProductos);
                    }
                }
            }
            for (PrSubgrupos prSubgruposListOldPrSubgrupos : prSubgruposListOld) {
                if (!prSubgruposListNew.contains(prSubgruposListOldPrSubgrupos)) {
                    prSubgruposListOldPrSubgrupos.setIdEmpresa(null);
                    prSubgruposListOldPrSubgrupos = em.merge(prSubgruposListOldPrSubgrupos);
                }
            }
            for (PrSubgrupos prSubgruposListNewPrSubgrupos : prSubgruposListNew) {
                if (!prSubgruposListOld.contains(prSubgruposListNewPrSubgrupos)) {
                    SeEmpresa oldIdEmpresaOfPrSubgruposListNewPrSubgrupos = prSubgruposListNewPrSubgrupos.getIdEmpresa();
                    prSubgruposListNewPrSubgrupos.setIdEmpresa(seEmpresa);
                    prSubgruposListNewPrSubgrupos = em.merge(prSubgruposListNewPrSubgrupos);
                    if (oldIdEmpresaOfPrSubgruposListNewPrSubgrupos != null && !oldIdEmpresaOfPrSubgruposListNewPrSubgrupos.equals(seEmpresa)) {
                        oldIdEmpresaOfPrSubgruposListNewPrSubgrupos.getPrSubgruposList().remove(prSubgruposListNewPrSubgrupos);
                        oldIdEmpresaOfPrSubgruposListNewPrSubgrupos = em.merge(oldIdEmpresaOfPrSubgruposListNewPrSubgrupos);
                    }
                }
            }
            for (SeSucursal seSucursalListNewSeSucursal : seSucursalListNew) {
                if (!seSucursalListOld.contains(seSucursalListNewSeSucursal)) {
                    SeEmpresa oldSeEmpresaOfSeSucursalListNewSeSucursal = seSucursalListNewSeSucursal.getSeEmpresa();
                    seSucursalListNewSeSucursal.setSeEmpresa(seEmpresa);
                    seSucursalListNewSeSucursal = em.merge(seSucursalListNewSeSucursal);
                    if (oldSeEmpresaOfSeSucursalListNewSeSucursal != null && !oldSeEmpresaOfSeSucursalListNewSeSucursal.equals(seEmpresa)) {
                        oldSeEmpresaOfSeSucursalListNewSeSucursal.getSeSucursalList().remove(seSucursalListNewSeSucursal);
                        oldSeEmpresaOfSeSucursalListNewSeSucursal = em.merge(oldSeEmpresaOfSeSucursalListNewSeSucursal);
                    }
                }
            }
            for (PrGrupos prGruposListOldPrGrupos : prGruposListOld) {
                if (!prGruposListNew.contains(prGruposListOldPrGrupos)) {
                    prGruposListOldPrGrupos.setIdEmpresa(null);
                    prGruposListOldPrGrupos = em.merge(prGruposListOldPrGrupos);
                }
            }
            for (PrGrupos prGruposListNewPrGrupos : prGruposListNew) {
                if (!prGruposListOld.contains(prGruposListNewPrGrupos)) {
                    SeEmpresa oldIdEmpresaOfPrGruposListNewPrGrupos = prGruposListNewPrGrupos.getIdEmpresa();
                    prGruposListNewPrGrupos.setIdEmpresa(seEmpresa);
                    prGruposListNewPrGrupos = em.merge(prGruposListNewPrGrupos);
                    if (oldIdEmpresaOfPrGruposListNewPrGrupos != null && !oldIdEmpresaOfPrGruposListNewPrGrupos.equals(seEmpresa)) {
                        oldIdEmpresaOfPrGruposListNewPrGrupos.getPrGruposList().remove(prGruposListNewPrGrupos);
                        oldIdEmpresaOfPrGruposListNewPrGrupos = em.merge(oldIdEmpresaOfPrGruposListNewPrGrupos);
                    }
                }
            }
            for (PrPrestaciones prPrestacionesListNewPrPrestaciones : prPrestacionesListNew) {
                if (!prPrestacionesListOld.contains(prPrestacionesListNewPrPrestaciones)) {
                    SeEmpresa oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones = prPrestacionesListNewPrPrestaciones.getSeEmpresa();
                    prPrestacionesListNewPrPrestaciones.setSeEmpresa(seEmpresa);
                    prPrestacionesListNewPrPrestaciones = em.merge(prPrestacionesListNewPrPrestaciones);
                    if (oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones != null && !oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones.equals(seEmpresa)) {
                        oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones.getPrPrestacionesList().remove(prPrestacionesListNewPrPrestaciones);
                        oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones = em.merge(oldSeEmpresaOfPrPrestacionesListNewPrPrestaciones);
                    }
                }
            }
            for (PrTipoMedidas prTipoMedidasListOldPrTipoMedidas : prTipoMedidasListOld) {
                if (!prTipoMedidasListNew.contains(prTipoMedidasListOldPrTipoMedidas)) {
                    prTipoMedidasListOldPrTipoMedidas.setIdEmpresa(null);
                    prTipoMedidasListOldPrTipoMedidas = em.merge(prTipoMedidasListOldPrTipoMedidas);
                }
            }
            for (PrTipoMedidas prTipoMedidasListNewPrTipoMedidas : prTipoMedidasListNew) {
                if (!prTipoMedidasListOld.contains(prTipoMedidasListNewPrTipoMedidas)) {
                    SeEmpresa oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas = prTipoMedidasListNewPrTipoMedidas.getIdEmpresa();
                    prTipoMedidasListNewPrTipoMedidas.setIdEmpresa(seEmpresa);
                    prTipoMedidasListNewPrTipoMedidas = em.merge(prTipoMedidasListNewPrTipoMedidas);
                    if (oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas != null && !oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas.equals(seEmpresa)) {
                        oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas.getPrTipoMedidasList().remove(prTipoMedidasListNewPrTipoMedidas);
                        oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas = em.merge(oldIdEmpresaOfPrTipoMedidasListNewPrTipoMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seEmpresa.getIdEmpresa();
                if (findSeEmpresa(id) == null) {
                    throw new NonexistentEntityException("The seEmpresa with id " + id + " no longer exists.");
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
            SeEmpresa seEmpresa;
            try {
                seEmpresa = em.getReference(SeEmpresa.class, id);
                seEmpresa.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seEmpresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PrProductos> prProductosListOrphanCheck = seEmpresa.getPrProductosList();
            for (PrProductos prProductosListOrphanCheckPrProductos : prProductosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeEmpresa (" + seEmpresa + ") cannot be destroyed since the PrProductos " + prProductosListOrphanCheckPrProductos + " in its prProductosList field has a non-nullable seEmpresa field.");
            }
            List<SeSucursal> seSucursalListOrphanCheck = seEmpresa.getSeSucursalList();
            for (SeSucursal seSucursalListOrphanCheckSeSucursal : seSucursalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeEmpresa (" + seEmpresa + ") cannot be destroyed since the SeSucursal " + seSucursalListOrphanCheckSeSucursal + " in its seSucursalList field has a non-nullable seEmpresa field.");
            }
            List<PrPrestaciones> prPrestacionesListOrphanCheck = seEmpresa.getPrPrestacionesList();
            for (PrPrestaciones prPrestacionesListOrphanCheckPrPrestaciones : prPrestacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SeEmpresa (" + seEmpresa + ") cannot be destroyed since the PrPrestaciones " + prPrestacionesListOrphanCheckPrPrestaciones + " in its prPrestacionesList field has a non-nullable seEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PrSubgrupos> prSubgruposList = seEmpresa.getPrSubgruposList();
            for (PrSubgrupos prSubgruposListPrSubgrupos : prSubgruposList) {
                prSubgruposListPrSubgrupos.setIdEmpresa(null);
                prSubgruposListPrSubgrupos = em.merge(prSubgruposListPrSubgrupos);
            }
            List<PrGrupos> prGruposList = seEmpresa.getPrGruposList();
            for (PrGrupos prGruposListPrGrupos : prGruposList) {
                prGruposListPrGrupos.setIdEmpresa(null);
                prGruposListPrGrupos = em.merge(prGruposListPrGrupos);
            }
            List<PrTipoMedidas> prTipoMedidasList = seEmpresa.getPrTipoMedidasList();
            for (PrTipoMedidas prTipoMedidasListPrTipoMedidas : prTipoMedidasList) {
                prTipoMedidasListPrTipoMedidas.setIdEmpresa(null);
                prTipoMedidasListPrTipoMedidas = em.merge(prTipoMedidasListPrTipoMedidas);
            }
            em.remove(seEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeEmpresa> findSeEmpresaEntities() {
        return findSeEmpresaEntities(true, -1, -1);
    }

    public List<SeEmpresa> findSeEmpresaEntities(int maxResults, int firstResult) {
        return findSeEmpresaEntities(false, maxResults, firstResult);
    }

    private List<SeEmpresa> findSeEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeEmpresa.class));
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

    public SeEmpresa findSeEmpresa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeEmpresa> rt = cq.from(SeEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
