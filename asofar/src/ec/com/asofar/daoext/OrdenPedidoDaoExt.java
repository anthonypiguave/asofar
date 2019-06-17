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

        objOrdenPedido.setCoOrdenPedidoPK(new CoOrdenPedidoPK());
        objOrdenPedido.getCoOrdenPedidoPK().setIdEmpresa(objOrdenPedido.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        objOrdenPedido.getCoOrdenPedidoPK().setIdSucursal(objOrdenPedido.getSeSucursal().getSeSucursalPK().getIdSucursal());

        
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(objOrdenPedido);
            em.getTransaction().commit();

//            em.flush();
//            em.refresh(objOrdenPedido);
            
//            CoOrdenPedido id = findCoOrdenPedido(objOrdenPedido.getCoOrdenPedidoPK());
            
//            System.out.println(" preuba 2 " + id.getCoOrdenPedidoPK().getIdOrdenPedido());
        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return objOrdenPedido;
    }

}
