/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeFacturaJpaController;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author michaels
 */
public class VeFacturaEXT extends VeFacturaJpaController {

    public VeFacturaEXT(EntityManagerFactory emf) {
        super(emf);
    }

    public List<VeFactura> RecorrerFecha(String resultado1, String resultado2, VeDetalleCaja vc) {

        EntityManager em = getEntityManager();

        List<VeFactura> lista = null;

        String nativeQuery = "SELECT DISTINCT ve_factura.* FROM bd_farmacia_desa.ve_factura "
                + "inner join ve_detalle_caja on \n"
                + "ve_detalle_caja.id_caja = ve_factura.id_caja\n"
                + "where ve_detalle_caja.id_caja = '" + vc.getVeCaja().getIdCaja() + "' \n"
                + "and ve_factura.fecha_facturacion \n"
                + "between '" + resultado1 + "' and '" + resultado2 + "';";
        Query query = em.createNativeQuery(nativeQuery, VeFactura.class);
        lista = query.getResultList();

        return lista;
    }

    public VeFactura guardarVenta(VeFactura objVenta) throws Exception {
        EntityManager em = getEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        objVenta.setVeFacturaPK(new VeFacturaPK());
        objVenta.getVeFacturaPK().setIdEmpresa(objVenta.getSeSucursal().getSeSucursalPK().getIdEmpresa());
/*objOrdenCompra.getCoOrdenComprasPK().setIdEmpresa(objOrdenCompra.getSeSucursal().getSeSucursalPK().getIdEmpresa());*/
        objVenta.getVeFacturaPK().setIdSucursal(objVenta.getSeSucursal().getSeSucursalPK().getIdSucursal());
        long id = 0;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objVenta);
            em.flush();
            id = showId(em);
            em.getTransaction().commit();
            
            objVenta.getVeFacturaPK().setIdFactura(id);

        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return objVenta;
    }

    private long showId(EntityManager em) {
        String nativeQuery = "SELECT max(id_factura) FROM ve_factura;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number) query.getSingleResult()).longValue();
        System.out.println("** prueba numero de factura  ** " + id);
        return id;

    }
}
