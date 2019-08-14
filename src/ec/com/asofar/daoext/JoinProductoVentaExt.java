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
                = "SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c";
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
                oo.setValor_descuento(Double.parseDouble(ooo[8].toString()));
                oo.setAplica_iva(ooo[9].toString());
                
                lista.add(oo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
