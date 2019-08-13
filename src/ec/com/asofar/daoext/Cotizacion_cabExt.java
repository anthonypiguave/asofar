/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoCotizacionesPorProveedorJpaController;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author admin1
 */
public class Cotizacion_cabExt extends CoCotizacionesPorProveedorJpaController{
    public Cotizacion_cabExt () {
        super(EntityManagerUtil.ObtenerEntityManager());
    }
    
     public void guardarEstadoCotizacion (List<CoCotizacionesPorProveedor> lista) throws Exception{
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        for (CoCotizacionesPorProveedor trx:lista)
        {
             em.merge(trx);
        }
       
        em.getTransaction().commit();
        System.out.println("hjjsjhs");
    }
}
