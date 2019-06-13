/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dto.CoOrdenPedido;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class OrdenPedidoDaoExt extends CoOrdenPedidoJpaController {

    public OrdenPedidoDaoExt(EntityManagerFactory emf) {
        super(emf);
    }

    public CoOrdenPedido guardarPedido(CoOrdenPedido objOrdenPedido) throws Exception {
        EntityManager em = null;
        CoOrdenPedido co = new CoOrdenPedido();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objOrdenPedido);
            co = em.merge(objOrdenPedido);
//        objOrdenPedido.getCoOrdenPedidoPK().getIdOrdenPedido();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return co;
    }

}
