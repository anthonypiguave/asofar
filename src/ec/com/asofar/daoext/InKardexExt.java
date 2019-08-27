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
        String nativeQuery = "SELECT *\n"
                + "FROM in_kardex a\n"
                + "WHERE a.id_producto = " + id + "\n"
                + "AND a.fecha_movimiento=\n"
                + "(SELECT MAX(b.fecha_movimiento) FROM in_kardex b where b.id_producto = a.id_producto)";
        Query query = em.createNativeQuery(nativeQuery, InKardex.class);
        QKardex = query.getResultList();
        return QKardex;
    }

    public InKardex obtenerUltimoProductoKardex(Long id) {
        InKardex kardex = null;
        try {

            EntityManager em = getEntityManager();
            String nativeQuery = "SELECT * FROM in_kardex \n"
                    + "WHERE in_kardex.id_producto = " + id + "\n"
                    + "ORDER BY in_kardex.id_kardex DESC LIMIT 1;";
            Query query = em.createNativeQuery(nativeQuery, InKardex.class);
            kardex = (InKardex) query.getSingleResult();
        } catch (Exception e) {
        }

        return kardex;

    }

}
