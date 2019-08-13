/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;


import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InMovimientosPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class MovimientosDaoExt extends InMovimientosJpaController {

    public MovimientosDaoExt(EntityManagerFactory emf) {
        super(emf);
    }

    public InMovimientos guardarPedido(InMovimientos obj) throws Exception {
        EntityManager em = getEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        obj.setInMovimientosPK(new InMovimientosPK());
        obj.getInMovimientosPK().setIdTipoDocumento(obj.getInTipoDocumento().getIdTipoDocumento());
        obj.getInMovimientosPK().setIdEmpresa(obj.getSeSucursal().getSeSucursalPK().getIdEmpresa());
        obj.getInMovimientosPK().setIdMotivo(obj.getInMotivos().getIdMotivo());
        obj.getInMovimientosPK().setIdTipoMovimiento(obj.getInTipoMovimiento().getIdTipoMovimiento());
        obj.getInMovimientosPK().setIdSucursal(obj.getSeSucursal().getSeSucursalPK().getIdSucursal());

      long id = 0 ;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obj);
            em.flush();
            id = showId(em);
            em.getTransaction().commit();
            
            obj.getInMovimientosPK().setIdMovimientos(id);
            

        } catch (Exception e) {
            System.out.println("creates: " + e.getMessage());
        } finally {
             
            if (em != null) {
                em.close();
            }
        }
        return obj;
    }
    
    
     private long showId(EntityManager em) {
        String nativeQuery = "SELECT MAX(id_movimientos) FROM in_movimientos;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number)query.getSingleResult()).longValue();
        System.out.println("prueba numero de orden "+ id);
        return id;
        
    }

}
