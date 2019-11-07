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
                = "SELECT \"\" AS  id_kardex,\n"
                + "pr_prestaciones.id_poducto,\n"
                + "\"\" AS id_bodega,\n"
                + "pr_detalle_tarifario.id_unidad_servicio,\n"
                + "pr_prestaciones.id_prestacion,\n"
                + "pr_prestaciones.nombre_prestacion AS 'prestacion',\n"
                + "\"\"AS saldo_actual,\n"
                + "pr_detalle_tarifario.valor_venta, \n"
                + "pr_detalle_tarifario.valor_descuento,\n"
                + "pr_prestaciones.aplica_iva , \n"
                + "\"\" AS codigo_barra\n"
                + "FROM pr_detalle_tarifario\n"
                + "INNER JOIN pr_prestaciones\n"
                + "ON pr_prestaciones.id_prestacion = pr_detalle_tarifario.id_prestacion\n"
                + "WHERE pr_detalle_tarifario.estado = 'A' AND  pr_prestaciones.id_poducto IS NULL\n"
                + "                \n"
                + "UNION ALL\n"
                + "                \n"
                + "SELECT in_kardex.id_kardex,pr_prestaciones.id_poducto, in_kardex.id_bodega,\n"
                + "pr_detalle_tarifario.id_unidad_servicio,pr_prestaciones.id_prestacion,\n"
                + "pr_prestaciones.nombre_prestacion AS 'prestacion',in_kardex.saldo_actual,\n"
                + "pr_detalle_tarifario.valor_venta, pr_detalle_tarifario.valor_descuento,\n"
                + "pr_prestaciones.aplica_iva , pr_productos.codigo_barra\n"
                + "FROM pr_detalle_tarifario\n"
                + "INNER JOIN pr_prestaciones\n"
                + "ON pr_prestaciones.id_prestacion = pr_detalle_tarifario.id_prestacion\n"
                + "INNER JOIN in_kardex\n"
                + "ON in_kardex.id_producto = pr_prestaciones.id_poducto\n"
                + "INNER JOIN \n"
                + "pr_productos ON pr_productos.id_producto = in_kardex.id_producto\n"
                + "WHERE pr_detalle_tarifario.estado = 'A'               \n"
                + "AND in_kardex.id_kardex = ANY(            \n"
                + "SELECT p.id_kardex FROM in_kardex  p WHERE \n"
                + "p.id_kardex = ALL (\n"
                + "SELECT MAX(k.id_kardex)  \n"
                + "FROM in_kardex k\n"
                + "WHERE k.id_producto = p.id_producto\n"
                + "ORDER BY k.id_kardex DESC )            \n"
                + ")             \n"
                + "ORDER BY prestacion;";
        Query query = em.createNativeQuery(nativeQuery);
        //query.setParameter(1, Integer.parseInt(id));
        try {

            List<Object[]> lsObj = query.getResultList();
            lista = new ArrayList<JoinProductoVenta>();

            for (Object[] ooo : lsObj) {
                JoinProductoVenta oo = new JoinProductoVenta();
//                if (oo.getId_kardex() == null && oo.getId_producto() == null
//                        && oo.getId_bodega() == null && oo.getCodigoBarra() == null) {
//                    oo.setId_kardex(Long.parseLong("0"));
//                    oo.setId_producto(Long.parseLong("0"));
//                    oo.setId_bodega(Long.parseLong("0"));
//                    oo.setId_unidad_servicio(Long.parseLong(ooo[3].toString()));
//                    oo.setNombre_producto(ooo[5].toString());
//                    oo.setSaldo_actual(Integer.parseInt("0"));
//                    oo.setValor_venta(Double.parseDouble(ooo[7].toString()));
//                    oo.setCodigoBarra("-");
//                } else {
                if (ooo[0] == "") {
                    oo.setId_kardex(Long.parseLong("0"));
                } else {
                    oo.setId_kardex(Long.parseLong(ooo[0].toString()));
                }
                if (ooo[1] == null) {
                    oo.setId_producto(Long.parseLong("0"));
                } else {
                    oo.setId_producto(Long.parseLong(ooo[1].toString()));
                }
                if (ooo[2] == "") {
                    oo.setId_bodega(Long.parseLong("0"));
                } else {
                    oo.setId_bodega(Long.parseLong(ooo[2].toString()));
                }

                oo.setId_unidad_servicio(Long.parseLong(ooo[3].toString()));
                oo.setId_prestacion(Long.parseLong(ooo[4].toString()));
                oo.setNombre_producto(ooo[5].toString());
                if (ooo[6] == "") {
                    oo.setSaldo_actual(Integer.parseInt("0"));
                } else {
                    oo.setSaldo_actual(Integer.parseInt(ooo[6].toString()));
                }
                oo.setValor_venta(Double.parseDouble(ooo[7].toString()));
                //  }
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

    public List<JoinProductoVenta> listarProductoInventario() {
        // EntityManager em = getEntityManager();
        List<JoinProductoVenta> lista = null;
        String nativeQuery
                = "SELECT \"\" as id_kardex,pr_prestaciones.id_poducto,\"\" as id_bodega, \n"
                + "pr_detalle_tarifario.id_unidad_servicio, pr_prestaciones.id_prestacion,\n"
                + "pr_prestaciones.nombre_prestacion as 'prestacion',\"\" as saldo_actual, \n"
                + "pr_detalle_tarifario.valor_venta, pr_detalle_tarifario.valor_descuento,\n"
                + " \"\" as codigo_barra\n"
                + "\n"
                + "FROM pr_detalle_tarifario\n"
                + "INNER JOIN pr_prestaciones \n"
                + "ON pr_prestaciones.id_prestacion = pr_detalle_tarifario.id_prestacion\n"
                + "where pr_detalle_tarifario.estado = 'A' and  pr_prestaciones.id_poducto is null\n"
                + "union all \n"
                + "SELECT in_kardex.id_kardex,pr_prestaciones.id_poducto, in_kardex.id_bodega,\n"
                + "pr_detalle_tarifario.id_unidad_servicio,pr_prestaciones.id_prestacion,\n"
                + "pr_prestaciones.nombre_prestacion as 'prestacion',in_kardex.saldo_actual,\n"
                + " pr_detalle_tarifario.valor_venta, pr_detalle_tarifario.valor_descuento,\n"
                + "  pr_productos.codigo_barra\n"
                + "\n"
                + "FROM pr_detalle_tarifario\n"
                + "INNER JOIN pr_prestaciones \n"
                + "ON pr_prestaciones.id_prestacion = pr_detalle_tarifario.id_prestacion\n"
                + "INNER JOIN in_kardex \n"
                + "ON in_kardex.id_producto = pr_prestaciones.id_poducto\n"
                + "INNER JOIN \n"
                + "pr_productos on pr_productos.id_producto = in_kardex.id_producto\n"
                + "where pr_detalle_tarifario.estado = 'A'\n"
                + "AND in_kardex.id_kardex = any(\n"
                + "SELECT p.id_kardex from in_kardex  p where \n"
                + "p.id_kardex = all (\n"
                + "SELECT max(k.id_kardex)  FROM in_kardex k\n"
                + "WHERE k.id_producto = p.id_producto\n"
                + "ORDER BY k.id_kardex DESC ))\n"
                + "order by prestacion;";
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
//                oo.setAplica_iva(ooo[9].toString());
                if (ooo[9] == null) {
                    oo.setCodigoBarra("-");
                } else {
                    oo.setCodigoBarra(ooo[9].toString());
                }
                lista.add(oo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
