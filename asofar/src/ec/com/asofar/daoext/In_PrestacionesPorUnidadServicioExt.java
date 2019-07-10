/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class In_PrestacionesPorUnidadServicioExt extends InPrestacionesPorServiciosJpaController {

    public In_PrestacionesPorUnidadServicioExt(EntityManagerFactory emf) {
        super(emf);
    }

    public List<InPrestacionesPorServicios> obtenerPrestNoRegis() {
        EntityManager em = getEntityManager();
        List<InPrestacionesPorServicios> lprod = null;
/*
select * from pr_prestaciones
where id_prestacion NOT IN (select id_prestacion from in_prestaciones_por_servicios)
*/
        String nativeQuery = "SELECT P.*"
                + " FROM pr_productos P\n"
                + "WHERE P.estado='A';";
        Query query = em.createNativeQuery(nativeQuery, InPrestacionesPorServicios.class);
        lprod = query.getResultList();
        return lprod;
    }
}
