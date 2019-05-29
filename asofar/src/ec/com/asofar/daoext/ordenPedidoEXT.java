/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dto.CoOrdenPedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class ordenPedidoEXT extends CoOrdenPedidoJpaController {

    public ordenPedidoEXT(EntityManagerFactory emf) {
        super(emf);
    }

    public int obtenerNumeroOrden() {
        EntityManager em = getEntityManager();
//        List<CoOrdenPedido> lprod = null;

        String nativeQuery = "SELECT COUNT(*)+1 FROM co_orden_pedido;";
        Query query = em.createNativeQuery(nativeQuery);
        int lprod2 = ((Number)query.getSingleResult()).intValue();
//        Object lprod = query.getSingleResult();
        System.out.println("prueba numero de orden "+lprod2);
        return lprod2;

    }

}
