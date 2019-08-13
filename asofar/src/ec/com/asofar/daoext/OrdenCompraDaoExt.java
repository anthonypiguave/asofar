/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenComprasPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class OrdenCompraDaoExt extends CoOrdenPedidoJpaController {

    public OrdenCompraDaoExt(EntityManagerFactory emf) {
        super(emf);
    }

    public CoOrdenCompras guardarPedido(CoOrdenCompras objOrdenCompra) throws Exception {
        EntityManager em = getEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        objOrdenCompra.setCoOrdenComprasPK(new CoOrdenComprasPK());
        objOrdenCompra.getCoOrdenComprasPK().setIdEmpresa(objOrdenCompra.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        objOrdenCompra.getCoOrdenComprasPK().setIdSucursal(objOrdenCompra.getSeSucursal().getSeSucursalPK().getIdSucursal());

      long id = 0 ;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objOrdenCompra);
            em.flush();
            id = showId(em);
            em.getTransaction().commit();
            
            objOrdenCompra.getCoOrdenComprasPK().setIdOrdenCompra(id);
            

        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {
             
            if (em != null) {
                em.close();
            }
        }
        return objOrdenCompra;
    }
    
    
     private long showId(EntityManager em) {
        String nativeQuery = "SELECT max(id_orden_compra) FROM co_orden_compras;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number)query.getSingleResult()).longValue();
        System.out.println("prueba numero de orden "+ id);
        return id;
        
    }

}
