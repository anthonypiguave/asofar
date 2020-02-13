/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.dto.PrProductos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class Pr_ProductoExt extends PrProductosJpaController {

    public Pr_ProductoExt(EntityManagerFactory emf) {
        super(emf);
    }

    public  List<PrProductos> obtenerProducto() {
        EntityManager em = getEntityManager();
        List<PrProductos> lprod = null;
        
        String nativeQuery = "SELECT P.*"
                + " FROM pr_productos P\n"
                + "WHERE P.estado='A';";
        Query query = em.createNativeQuery(nativeQuery, PrProductos.class);
        lprod = query.getResultList();
        return lprod;
    }
    
        public  PrProductos obtenerProductoObj( Long id) {
        EntityManager em = getEntityManager();
        PrProductos lprod = null;
        String nativeQuery = "SELECT P.*"
                + " FROM pr_productos P\n"
                + " WHERE P.id_producto = " + id + ";";
        Query query = em.createNativeQuery(nativeQuery, PrProductos.class);
        lprod = ( PrProductos) query.getSingleResult();
        return lprod;
    }
    
    
    
}
