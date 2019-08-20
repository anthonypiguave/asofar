/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dto.SeClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class SeClientesExt extends SeClientesJpaController  {
    
    public SeClientesExt(EntityManagerFactory emf) {
        super(emf);
    }
        public List<SeClientes> obtenerClienteVenta(String cedula) {
        EntityManager em = getEntityManager();
        List<SeClientes> QKardex = null;
        String nativeQuery = " ";
        Query query = em.createNativeQuery(nativeQuery, SeClientes.class);
        QKardex = query.getResultList();
        return QKardex;
    }
}
