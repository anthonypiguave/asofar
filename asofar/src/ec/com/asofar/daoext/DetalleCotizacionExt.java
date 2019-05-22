/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoDetalleCotizacionPorProveedorJpaController;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author admin1
 */
public class DetalleCotizacionExt extends CoDetalleCotizacionPorProveedorJpaController{
    
    public DetalleCotizacionExt() {
        super(EntityManagerUtil.ObtenerEntityManager());
    }
    public void guardarEstadoCotizacionDetalle (List<CoDetalleCotizacionPorProveedor> lista) throws Exception{
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        for (CoDetalleCotizacionPorProveedor trx:lista)
        {
             em.merge(trx);
        }
       
        em.getTransaction().commit();
        System.out.println("hjjsjhs");
    }
}
