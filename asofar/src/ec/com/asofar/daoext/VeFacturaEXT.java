/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeFacturaJpaController;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

}