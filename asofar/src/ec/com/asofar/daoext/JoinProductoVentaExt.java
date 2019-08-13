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

/*--id_kardex,
            id_producto,
            id_prestacion,
            id_bodega,
            codigo_barra
            ,nombre_producto,
            cantidad,
            id_unidad_servicio
valor_venta,valor_descuento,
pro.codigo_barra,aplica_i*/
            
            for (Object[] ooo : lsObj) {
                JoinProductoVenta oo = new JoinProductoVenta();
                oo.setIdKardex(Long.parseLong(ooo[0].toString()));
                oo.setIdProducto(Long.parseLong(ooo[1].toString()));
                oo.setIdPrestacion(Long.parseLong(ooo[2].toString()));
                oo.setIdBodega(Long.parseLong(ooo[3].toString()));
                if (ooo[4] == null) {
                    oo.setCodigoBarra("-");
//                    System.out.println("4 "+ooo[4].toString());
                } else {
                    oo.setCodigoBarra(ooo[4].toString());
                }
                oo.setDescripcion(ooo[5].toString());
                oo.setStock(Integer.parseInt(ooo[6].toString()));
                oo.setIdUnidadServicio(Long.parseLong(ooo[7].toString()));
                oo.setValorventa(Double.parseDouble(ooo[8].toString()));
                oo.setDescuento(Double.parseDouble(ooo[9].toString()));
                if (ooo[9] == null) {
                    oo.setIva("erros");
//                    System.out.println("9 "+ooo[9].toString());
                } else {
                    oo.setIva(ooo[9].toString());
                }
                lista.add(oo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
