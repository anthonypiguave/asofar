/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dto.InKardex;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class InKardexExt extends InKardexJpaController {

    public InKardexExt(EntityManagerFactory emf) {
        super(emf);
    }

    public List<InKardex> obtenerProductoKardex(Long id) {
        EntityManager em = getEntityManager();
        List<InKardex> QKardex = null;
//        String nativeQuery = "SELECT P.*"
//                + " FROM pr_productos P\n"
//                + "WHERE P.estado='A';";
        String nativeQuery = "SELECT *\n"
                + "FROM `in_kardex` \n"
                + "WHERE id_producto = " + id +" "
                + " AND fecha_movimiento=(SELECT MAX(fecha_movimiento)FROM `in_kardex`)";
        Query query = em.createNativeQuery(nativeQuery, InKardex.class);
        QKardex = query.getResultList();
        return QKardex;
    }
}
