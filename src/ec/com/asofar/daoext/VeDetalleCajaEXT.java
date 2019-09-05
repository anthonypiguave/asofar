/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author ubuntu
 */
public class VeDetalleCajaEXT extends VeDetalleCajaJpaController {

    public VeDetalleCajaEXT(EntityManagerFactory emf) {
        super(emf);
    }

    public List<VeDetalleCaja> ObtenerResultados(SeUsuarios seUsuario) {

        EntityManager em = getEntityManager();

        List<VeDetalleCaja> lista = null;

        String nativeQuery = "select * from ve_detalle_caja \n"
                + "where ve_detalle_caja.id_usuario = '"+seUsuario.getIdUsuario()+"'\n"
                + "and ve_detalle_caja.estado = 'I'\n"
                + "order by id_detalle_caja desc\n"
                + "limit 1;";
        Query query = em.createNativeQuery(nativeQuery, VeDetalleCaja.class);

        lista = query.getResultList();

        return lista;
    }

}
