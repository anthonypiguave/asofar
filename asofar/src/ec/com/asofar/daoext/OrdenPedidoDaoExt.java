/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoOrdenPedidoPK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class OrdenPedidoDaoExt extends CoOrdenPedidoJpaController {

    public OrdenPedidoDaoExt(EntityManagerFactory emf) {
        super(emf);
    }

    public CoOrdenPedido guardarPedido(CoOrdenPedido objOrdenPedido) throws Exception {
        EntityManager em = getEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        objOrdenPedido.setCoOrdenPedidoPK(new CoOrdenPedidoPK());
        objOrdenPedido.getCoOrdenPedidoPK().setIdEmpresa(objOrdenPedido.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        objOrdenPedido.getCoOrdenPedidoPK().setIdSucursal(objOrdenPedido.getSeSucursal().getSeSucursalPK().getIdSucursal());

      long id = 0 ;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objOrdenPedido);
            em.flush();
            id = showId(em);
            em.getTransaction().commit();
            
            objOrdenPedido.getCoOrdenPedidoPK().setIdOrdenPedido(id);
            

        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {
             
            if (em != null) {
                em.close();
            }
        }
        return objOrdenPedido;
    }
    
    
     private long showId(EntityManager em) {
        String nativeQuery = "SELECT max(id_orden_pedido) FROM co_orden_pedido;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number)query.getSingleResult()).longValue();
        System.out.println("prueba numero de orden "+ id);
        return id;
        
    }

}
