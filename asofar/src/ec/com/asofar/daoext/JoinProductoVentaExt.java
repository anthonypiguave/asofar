/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class JoinProductoVentaExt {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("asofarPU");
    static EntityManager em = emf.createEntityManager();
    static ReporteriaExt ed = new ReporteriaExt();

    public List<JoinProductoVenta> listarProductoVenta() {
        // EntityManager em = getEntityManager();
        List<JoinProductoVenta> lista = null;
        String nativeQuery
                = "SELECT k.`id_kardex`,k.`id_producto` AS CodPro ,pres.`id_prestacion`,k.id_bodega,\n"
                + "pro.codigo_barra,\n"
                + "pro.`nombre_producto`,k.`cantidad`,dt.id_unidad_servicio\n"
                + ",dt.`valor_venta`,dt.valor_descuento,pres.aplica_iva\n"
                + "FROM `in_kardex` k\n"
                + "JOIN `pr_productos` pro ON  pro.`id_producto`= k.`id_producto`\n"
                + "JOIN `pr_prestaciones` pres ON pres.`id_poducto`= pro.`id_producto`\n"
                + "JOIN `pr_detalle_tarifario` dt ON dt.`id_prestacion` = pres.`id_prestacion`\n"
                + "WHERE k.`id_producto`IN(SELECT p.`id_producto`FROM `pr_productos` p)\n"
                + "AND pres.`id_prestacion` IN(SELECT ta.`id_prestacion`FROM`pr_detalle_tarifario` ta)\n"
                + "ORDER BY pres.`id_prestacion`";
        Query query = em.createNativeQuery(nativeQuery);
        //query.setParameter(1, Integer.parseInt(id));
        try {

            List<Object[]> lsObj = query.getResultList();
            lista = new ArrayList<JoinProductoVenta>();

            for (Object[] ooo : lsObj) {
                JoinProductoVenta oo = new JoinProductoVenta();
                oo.setIdKardex((BigInteger)ooo[0]);
                oo.setIdProducto((BigInteger)ooo[1]);
                oo.setIdPrestacion((BigInteger)ooo[2]);
                oo.setIdBodega((BigInteger)ooo[3]);
                oo.setCodigoBarra(ooo[4].toString());
                oo.setDescripcion(ooo[5].toString());
                oo.setStock((Integer) ooo[6]);
                oo.setValorventa((Double) ooo[7]);
                oo.setDescuento((Double) ooo[8]);
                oo.setIva(ooo[9].toString());

                lista.add(oo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
