/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

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
                //                = "SELECT k.`id_kardex`,k.`id_producto`,k.id_bodega,dt.id_unidad_servicio,\n"
                //                + "pres.`id_prestacion`,pro.`nombre_producto`,k.saldo_actual\n"
                //                + ",dt.`valor_venta`,dt.valor_descuento,pres.aplica_iva,pro.codigo_barra\n"
                //                + "FROM `in_kardex` k\n"
                //                + "JOIN `pr_productos` pro ON  pro.`id_producto`= k.`id_producto`\n"
                //                + "JOIN `pr_prestaciones` pres ON pres.`id_poducto`= pro.`id_producto`\n"
                //                + "JOIN `pr_detalle_tarifario` dt ON dt.`id_prestacion` = pres.`id_prestacion`\n"
                //                + "WHERE k.`id_producto`IN(SELECT p.`id_producto`FROM `pr_productos` p)\n"
                //                + "AND pres.`id_prestacion` IN(SELECT ta.`id_prestacion`FROM`pr_detalle_tarifario`ta)\n"
                //                + "AND dt.`estado`='A'\n"
                //                + "ORDER BY pres.`id_prestacion`";
                = "SELECT in_kardex.`id_kardex`,in_kardex.`id_producto`,in_kardex.id_bodega,pr_detalle_tarifario.id_unidad_servicio,\n"
                + "pr_prestaciones.`id_prestacion`,pr_productos.`nombre_producto`,in_kardex.saldo_actual\n"
                + ",pr_detalle_tarifario.`valor_venta`,pr_detalle_tarifario.valor_descuento,pr_prestaciones.aplica_iva,pr_productos.codigo_barra\n"
                + "/* * */ \n"
                + "FROM in_kardex in_kardex\n"
                + "INNER JOIN pr_productos\n"
                + "ON pr_productos.id_producto = in_kardex.id_producto\n"
                + "INNER JOIN pr_prestaciones\n"
                + "ON pr_prestaciones.id_poducto = pr_productos.id_producto\n"
                + "AND pr_prestaciones.`id_prestacion` IN(SELECT ta.`id_prestacion`FROM`pr_detalle_tarifario`ta WHERE ta.`id_prestacion`= pr_prestaciones.`id_prestacion`)\n"
                + "AND pr_prestaciones.`estado`='A'\n"
                + "INNER JOIN pr_detalle_tarifario\n"
                + "ON pr_detalle_tarifario.id_prestacion = pr_prestaciones.id_prestacion\n"
                + "AND pr_detalle_tarifario.`estado`='A' \n"
                + "WHERE in_kardex.id_producto IN (SELECT p.`id_producto`FROM pr_productos p WHERE p.`id_producto` = in_kardex.`id_producto`)\n"
                + "AND (in_kardex.id_producto,in_kardex.fecha_movimiento) IN (SELECT h.id_producto,MAX(h.fecha_movimiento)\n"
                + "FROM in_kardex h GROUP BY h.id_producto)";
        Query query = em.createNativeQuery(nativeQuery);
        //query.setParameter(1, Integer.parseInt(id));
        try {

            List<Object[]> lsObj = query.getResultList();
            lista = new ArrayList<JoinProductoVenta>();

            for (Object[] ooo : lsObj) {
                JoinProductoVenta oo = new JoinProductoVenta();
                oo.setId_kardex(Long.parseLong(ooo[0].toString()));
                oo.setId_producto(Long.parseLong(ooo[1].toString()));
                oo.setId_bodega(Long.parseLong(ooo[2].toString()));
                oo.setId_unidad_servicio(Long.parseLong(ooo[3].toString()));
                oo.setId_prestacion(Long.parseLong(ooo[4].toString()));
                oo.setNombre_producto(ooo[5].toString());
                oo.setSaldo_actual(Integer.parseInt(ooo[6].toString()));
                oo.setValor_venta(Double.parseDouble(ooo[7].toString()));

                if (ooo[8] == null) {
                    oo.setValor_descuento(0.0);
                } else {
                    oo.setValor_descuento(Double.parseDouble(ooo[8].toString()));
                }
                oo.setAplica_iva(ooo[9].toString());
                if (ooo[10] == null) {
                    oo.setCodigoBarra("-");
                } else {
                    oo.setCodigoBarra(ooo[10].toString());
                }
                lista.add(oo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
