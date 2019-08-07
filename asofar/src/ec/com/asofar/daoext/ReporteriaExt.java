/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */


public class ReporteriaExt {
   // public String Query = null;
////     public ReporteriaExt(EntityManagerFactory emf) {
////        super(emf);
////    }
    
    public List<String> reporteCompras(EntityManager em){
    List<String> listaCompra = new ArrayList<String>();
   String nativeQuery = "SELECT * FROM `co_orden_compras` a\n" +
                        "INNER JOIN `co_detalle_orden_compra` b \n" +
                        "ON a.`id_orden_compra` = b.`id_orden_compra`\n" +
                        "AND a.`estado`='P'\n" +
                        "ORDER BY a.`fecha_creacion`";
        Query query = em.createNativeQuery(nativeQuery);
         listaCompra = query.getResultList();
       // System.out.println("prueba numero de orden "+ id);
        return listaCompra;
    }
}
