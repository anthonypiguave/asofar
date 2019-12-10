/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import com.toedter.calendar.JDateChooser;
import ec.com.asofar.util.Calendario;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
public class ReporteriaExt {
    //putoooo el que lo lea..!!
    // public String Query = null;
////     public ReporteriaExt(EntityManagerFactory emf) {
////        super(emf);
////    }
//private static EntityManagerFactory emf = null;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("asofarPU");
    static EntityManager em = emf.createEntityManager();
    static ReporteriaExt ed = new ReporteriaExt();

    public static void main(String[] args) {
        try {
            List<ReporteComprasDTO> itemList = null;
            itemList = reporteCompras();
//        reporteCompras2(em);
//         itemList = obtenerEjemplo();
//        itemList= ed.listarCategorias();
//System.out.println("j "+itemList.add("id_orden_compra"));
//            System.out.println("lalala");

            for (int i = 0; i < itemList.size(); i++) {
                System.out.println(itemList.get(i).getId_orden_compra().toString() + " "
                        + itemList.get(i).getFecha_aprobacion().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ReporteComprasDTO> reporteCompras() {

        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteComprasDTO> listaCompra = null;
        Query q = em.createNativeQuery("SELECT  distinct\n"
                + "   		c.id_orden_compra,\n"
                + "   		c.id_tipo_documento,\n"
                + "        c.fecha_entrega,\n"
                + "        c.id_proveedor,\n"
                + "        ifnull(c.total_subtotal,0) as total_subtotal,\n"
                + "        ifnull(c.total_ice,0) as total_ice,\n"
                + "        ifnull(c.total_iva,0) as total_iva,\n"
                + "        c.total_compra,\n"
                + "        f.nombre_documento,\n"
                + "        g.nombre_comercial,\n"
                + "        ifnull(c.total_descuento,0) as total_descuento\n"
                + "   FROM co_orden_compras as c\n"
                + "   inner join co_detalle_orden_compra as d\n"
                + "   	  on   c.id_orden_compra = d.id_orden_compra\n"
                + "         and  c.estado = 'C'\n"
                + "         and  c.fecha_aprobacion\n"
                + "         BETWEEN concat(date_format(sysdate(),'%Y-%m-%d'),' 00:00:00')\n"
                + "         and concat(date_format(sysdate(),'%Y-%m-%d'),' 23:59:59')\n"
                + "   inner join in_tipo_documento as f\n"
                + "	  on f.id_tipo_documento = c.id_tipo_documento\n"
                + "	inner join co_proveedores as g\n"
                + "      on g.id_proveedor = c.id_proveedor;");
        List<Object[]> listobj = q.getResultList();
        listaCompra = new ArrayList<ReporteComprasDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteComprasDTO ob = new ReporteComprasDTO();
                ob.setId_orden_compra(Long.parseLong(obj[0].toString()));
                ob.setId_tipo_documento(Long.parseLong(obj[1].toString()));
                ob.setFecha_aprobacion(Date.valueOf(obj[2].toString()));
                ob.setId_proveedor(Long.parseLong(obj[3].toString()));
                ob.setSubtotal(Double.parseDouble(obj[4].toString()));
                ob.setIce(Double.parseDouble(obj[5].toString()));
                ob.setIva(Double.parseDouble(obj[6].toString()));
                ob.setTotal_compra(Double.parseDouble(obj[7].toString()));
                ob.setNombre_documento(String.valueOf(obj[8].toString()));
                ob.setNombre_proveedor(String.valueOf(obj[9].toString()));
                ob.setDescuento(Double.parseDouble(obj[10].toString()));
                listaCompra.add(ob);
            }
//        
//        for (int i = 0; i <  q.getResultList().size(); i++) {
//             ReporteDetalleComprasDTO obj = new ReporteDetalleComprasDTO();
//             obj.setId_orden_compra(Long.valueOf(q.getResultList().get(i).toString()));
//             obj.setId_tipo_documento(Long.valueOf(q.getResultList().get(i).toString()));
//              // obj4 = q.getResultList();
//             listaCompra.add(obj);
//              
//        }
//         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompra;
    }

    public static List<ReporteComprasDTO> reporteComprasFechas(String desde, String hasta) {

        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteComprasDTO> listaCompra = null;
        Query q = em.createNativeQuery("SELECT  distinct\n"
                + "                		c.id_orden_compra,\n"
                + "                		c.id_tipo_documento,\n"
                + "                        c.fecha_entrega,\n"
                + "                        c.id_proveedor,\n"
                + "                        ifnull(c.total_subtotal,0) as total_subtotal,\n"
                + "                        ifnull(c.total_ice,0) as total_ice,\n"
                + "                        ifnull(c.total_iva,0) as total_iva,\n"
                + "                        c.total_compra,\n"
                + "        f.nombre_documento,\n"
                + "        g.nombre_comercial,\n"
                + "        ifnull(c.total_descuento,0) as total_descuento\n"
                + "                FROM co_orden_compras as c\n"
                + "                inner join co_detalle_orden_compra as d \n"
                + "                	  on   c.id_orden_compra = d.id_orden_compra\n"
                + "                      and  c.estado = 'C'\n"
                + "                      and  c.fecha_aprobacion \n"
                + "                      BETWEEN concat('" + desde + "',' 00:00:00')\n"
                + "                      and concat('" + hasta + "',' 23:59:59')\n"
                + "   inner join in_tipo_documento as f\n"
                + "	  on f.id_tipo_documento = c.id_tipo_documento\n"
                + "	inner join co_proveedores as g\n"
                + "      on g.id_proveedor = c.id_proveedor;");
        List<Object[]> listobj = q.getResultList();
        listaCompra = new ArrayList<ReporteComprasDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteComprasDTO ob = new ReporteComprasDTO();
                ob.setId_orden_compra(Long.parseLong(obj[0].toString()));
                ob.setId_tipo_documento(Long.parseLong(obj[1].toString()));
                ob.setFecha_aprobacion(Date.valueOf(obj[2].toString()));
                ob.setId_proveedor(Long.parseLong(obj[3].toString()));
                ob.setSubtotal(Double.parseDouble(obj[4].toString()));
                ob.setIce(Double.parseDouble(obj[5].toString()));
                ob.setIva(Double.parseDouble(obj[6].toString()));
                ob.setTotal_compra(Double.parseDouble(obj[7].toString()));
                ob.setNombre_documento(String.valueOf(obj[8].toString()));
                ob.setNombre_proveedor(String.valueOf(obj[9].toString()));
                ob.setDescuento(Double.parseDouble(obj[10].toString()));
                listaCompra.add(ob);
            }
//        
//        for (int i = 0; i <  q.getResultList().size(); i++) {
//             ReporteDetalleComprasDTO obj = new ReporteDetalleComprasDTO();
//             obj.setId_orden_compra(Long.valueOf(q.getResultList().get(i).toString()));
//             obj.setId_tipo_documento(Long.valueOf(q.getResultList().get(i).toString()));
//              // obj4 = q.getResultList();
//             listaCompra.add(obj);
//              
//        }
//         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompra;
    }

    public static List<ReporteDetalleComprasDTO> listadoDetallesCompras(ReporteComprasDTO objcab) {
        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteDetalleComprasDTO> listaDetalle = null;
        Query q = em.createNativeQuery("select co_detalle_orden_compra.id_detalle_orden_compra,\n"
                + "co_detalle_orden_compra.id_orden_compra,\n"
                + "co_detalle_orden_compra.id_empresa,\n"
                + "co_detalle_orden_compra.id_surcusal,\n"
                + "co_detalle_orden_compra.id_producto,\n"
                + "co_detalle_orden_compra.linea_detalle,\n"
                + "co_detalle_orden_compra.descripcion,\n"
                + "co_detalle_orden_compra.precio_unitario,\n"
                + "co_detalle_orden_compra.cantidad_recibida,\n"
                + "co_detalle_orden_compra.subtotal,\n"
                + "co_detalle_orden_compra.iva,\n"
                + "co_detalle_orden_compra.descuento,\n"
                + "co_detalle_orden_compra.total,\n"
                + "ifnull(pr_productos.codigo_barra,'---'),\n"
                + "pr_productos.id_tipo_presentacion,\n"
                + "pr_productos.nombre_producto,\n"
                + "ifnull(pr_productos.receta,'--'),\n"
                + "pr_tipo_presentacion.nombre,\n"
                + "ifnull(co_detalle_orden_compra.lote_fabricacion,'---'),\n"
                + "co_detalle_orden_compra.fecha_caducidad\n"
                + "from co_orden_compras\n"
                + "inner join co_detalle_orden_compra\n"
                + "on co_orden_compras.id_orden_compra = co_detalle_orden_compra.id_orden_compra\n"
                + "inner join pr_productos\n"
                + "on pr_productos.id_producto = co_detalle_orden_compra.id_producto\n"
                + "and pr_productos.estado = 'A'\n"
                + "inner join pr_tipo_presentacion\n"
                + "on pr_tipo_presentacion.id_tipo_presentacion = pr_productos.id_tipo_presentacion\n"
                + "WHERE co_orden_compras.id_orden_compra = " + objcab.getId_orden_compra().toString() + ";");
        List<Object[]> listobj = q.getResultList();
        listaDetalle = new ArrayList<ReporteDetalleComprasDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteDetalleComprasDTO ob = new ReporteDetalleComprasDTO();
                ob.setId_detalle_orden_compra(Long.parseLong(obj[0].toString()));
                ob.setId_orden_compra(Long.parseLong(obj[1].toString()));
                ob.setId_empresa(Long.parseLong(obj[2].toString()));//
                ob.setId_surcusal(Long.parseLong(obj[3].toString()));
                ob.setId_producto(Long.parseLong(obj[4].toString()));
                ob.setLinea_detalle(Long.parseLong(obj[5].toString()));
                ob.setDescripcion((obj[6].toString()));
                ob.setPrecio_unitario(Double.parseDouble(obj[7].toString()));
                ob.setCantidad_recibida(Long.parseLong(obj[8].toString()));
                ob.setSubtotal(Double.parseDouble(obj[9].toString()));
                ob.setIva(Double.parseDouble(obj[10].toString()));
                ob.setDescuento(Double.parseDouble(obj[11].toString()));
                ob.setTotal(Double.parseDouble(obj[12].toString()));
                ob.setCodigo_barra(String.valueOf(obj[13].toString()));
                ob.setId_tipo_presentacion(String.valueOf(obj[14].toString()));
                ob.setNombre_producto(String.valueOf(obj[15].toString()));
                ob.setReceta(String.valueOf(obj[16].toString()));
                ob.setNombrePresentacion(String.valueOf(obj[17].toString()));
                ob.setLote_fabricacion(String.valueOf(obj[18].toString()));
                ob.setFecha_caducidad(Date.valueOf(obj[19].toString()));
                listaDetalle.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetalle;

    }

    public static List<ReporteFacturaDTO> reporteFactura() {

        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteFacturaDTO> listaCompra = null;
        Query q = em.createNativeQuery("SELECT  DISTINCT\n"
                + "         c.id_factura,\n"
                + "         c.id_empresa,\n"
                + "         c.id_sucursal,\n"
                + "         c.id_caja,\n"
                + "         c.id_usuario,\n"
                + "         c.id_cliente,\n"
                + "         DATE_FORMAT(c.fecha_facturacion,'%Y-%m-%d') AS fecha_facturacion,\n"
                + "         IFNULL(c.subtotal,0) AS total_subtotal,\n"
                + "         IFNULL(c.total_ice,0) AS total_ice,\n"
                + "         IFNULL(c.total_descuento,0) AS total_descuento,\n"
                + "         IFNULL(c.total_base_iva,0) AS base_iva,\n"
                + "         IFNULL(c.total_base_no_iva,0) AS base_no_iva,\n"
                + "         IFNULL(c.total_iva,0) AS base_no_iva,\n"
                + " 		IFNULL(c.total_facturado,0) AS total_fact,\n"
                + "         c.despachado,\n"
                + "         caja.nombre,\n"
                + "         e.nombre_comercial,\n"
                + "         s.nombre_comercial,\n"
                + "         c.estado\n"
//                + "         d.cantidad,\n"
//                + "         pres.id_poducto\n"
                + "    FROM ve_factura AS c\n"
                + "    INNER JOIN ve_factura_detalle AS d\n"
                + "    	  ON  c.id_factura = d.id_factura\n"
                + "      -- and  c.estado = 'C'\n"
                + "	AND  c.fecha_facturacion\n"
                + "	BETWEEN CONCAT(DATE_FORMAT(SYSDATE(),'%Y-%m-%d'),' 00:00:00')\n"
                + "        AND CONCAT(DATE_FORMAT(SYSDATE(),'%Y-%m-%d'),' 23:59:59')\n"
                + "    INNER JOIN ve_caja AS caja\n"
                + "     ON caja.id_caja = c.id_caja\n"
                + "    INNER JOIN se_empresa AS e\n"
                + " 	ON e.id_empresa = c.id_empresa\n"
                + "    INNER JOIN se_sucursal AS s\n"
                + "     ON s.id_sucursal = c.id_sucursal\n"
                + "    INNER JOIN pr_prestaciones AS pres\n"
                + "     ON pres.id_prestacion = d.id_prestaciones;");
        List<Object[]> listobj = q.getResultList();
        listaCompra = new ArrayList<ReporteFacturaDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteFacturaDTO ob = new ReporteFacturaDTO();
                ob.setId_factura(Long.parseLong(obj[0].toString()));
                ob.setId_empresa(Long.parseLong(obj[1].toString()));
                ob.setId_sucursal(Long.parseLong(obj[2].toString()));
                ob.setId_caja(Long.parseLong(obj[3].toString()));
                ob.setId_usuario(obj[4].toString());
//                if(obj[4]!= null){
//                ob.setId_usuario(Long.parseLong(obj[4].toString()));
//                }else{
//                ob.setId_usuario(Long.valueOf("1"));
//                }
                ob.setId_cliente(Long.parseLong(obj[5].toString()));
                ob.setFecha_facturacion(Date.valueOf(obj[6].toString()));
                ob.setSubtotal(Double.parseDouble(obj[7].toString()));
                ob.setTotal_ice(Double.parseDouble(obj[8].toString()));
                ob.setTotal_descuento(Double.parseDouble(obj[9].toString()));
                ob.setTotal_base_iva(Double.parseDouble(obj[10].toString()));
                ob.setTotal_base_no_iva(Double.parseDouble(obj[11].toString()));
                ob.setTotal_iva(Double.parseDouble(obj[12].toString()));
                ob.setTotal_facturado(Double.parseDouble(obj[13].toString()));
                ob.setDespachado(String.valueOf(obj[14].toString()));
                ob.setNombre_caja(String.valueOf(obj[15].toString()));
                ob.setNombre_comercial_emp(String.valueOf(obj[16].toString()));
                ob.setNombre_comercial_suc(String.valueOf(obj[17].toString()));
                ob.setEstado(String.valueOf(obj[18].toString()));

                listaCompra.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompra;
    }

    public static List<ReporteFacturaDTO> reporteFacturaFechas(String desde, String hasta) {

        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteFacturaDTO> listaCompra = null;
        Query q = em.createNativeQuery("SELECT  DISTINCT\n"
                + "         c.id_factura,\n"
                + "         c.id_empresa,\n"
                + "         c.id_sucursal,\n"
                + "         c.id_caja,\n"
                + "         c.id_usuario,\n"
                + "         c.id_cliente,\n"
                + "         DATE_FORMAT(c.fecha_facturacion,'%Y-%m-%d') AS fecha_facturacion,\n"
                + "         IFNULL(c.subtotal,0) AS total_subtotal,\n"
                + "         IFNULL(c.total_ice,0) AS total_ice,\n"
                + "         IFNULL(c.total_descuento,0) AS total_descuento,\n"
                + "         IFNULL(c.total_base_iva,0) AS base_iva,\n"
                + "         IFNULL(c.total_base_no_iva,0) AS base_no_iva,\n"
                + "         IFNULL(c.total_iva,0) AS base_no_iva,\n"
                + " 		IFNULL(c.total_facturado,0) AS total_fact,\n"
                + "         c.despachado,\n"
                + "         caja.nombre,\n"
                + "         e.nombre_comercial,\n"
                + "         s.nombre_comercial,\n"
                + "         d.cantidad,\n"
                + "         pres.id_poducto\n"
                + "    FROM ve_factura AS c\n"
                + "    INNER JOIN ve_factura_detalle AS d\n"
                + "    	  ON  c.id_factura = d.id_factura\n"
                + "      -- and  c.estado = 'C'\n"
                + "	AND  c.fecha_facturacion\n"
                + "         BETWEEN concat('" + desde + "',' 00:00:00')\n"
                + "         and concat('" + hasta + "',' 23:59:59')\n"
                + "    INNER JOIN ve_caja AS caja\n"
                + "     ON caja.id_caja = c.id_caja\n"
                + "    INNER JOIN se_empresa AS e\n"
                + " 	ON e.id_empresa = c.id_empresa\n"
                + "    INNER JOIN se_sucursal AS s\n"
                + "     ON s.id_sucursal = c.id_sucursal\n"
                + "    INNER JOIN pr_prestaciones AS pres\n"
                + "     ON pres.id_prestacion = d.id_prestaciones;");
        List<Object[]> listobj = q.getResultList();
        listaCompra = new ArrayList<ReporteFacturaDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteFacturaDTO ob = new ReporteFacturaDTO();
                ob.setId_factura(Long.parseLong(obj[0].toString()));
                ob.setId_empresa(Long.parseLong(obj[1].toString()));
                ob.setId_sucursal(Long.parseLong(obj[2].toString()));
                ob.setId_caja(Long.parseLong(obj[3].toString()));
                ob.setId_usuario(obj[4].toString());
//                if(obj[4]!=null){
//                    ob.setId_usuario(Long.parseLong(obj[4].toString()));
//                }else{
//                    ob.setId_usuario(Long.valueOf("1"));
//                }
                ob.setId_cliente(Long.parseLong(obj[5].toString()));
                ob.setFecha_facturacion(Date.valueOf(obj[6].toString()));
                ob.setSubtotal(Double.parseDouble(obj[7].toString()));
                ob.setTotal_ice(Double.parseDouble(obj[8].toString()));
                ob.setTotal_descuento(Double.parseDouble(obj[9].toString()));
                ob.setTotal_base_iva(Double.parseDouble(obj[10].toString()));
                ob.setTotal_base_no_iva(Double.parseDouble(obj[11].toString()));
                ob.setTotal_iva(Double.parseDouble(obj[12].toString()));
                ob.setTotal_facturado(Double.parseDouble(obj[13].toString()));
                ob.setDespachado(String.valueOf(obj[14].toString()));
                ob.setNombre_caja(String.valueOf(obj[15].toString()));
                ob.setNombre_comercial_emp(String.valueOf(obj[16].toString()));
                ob.setNombre_comercial_suc(String.valueOf(obj[17].toString()));
                listaCompra.add(ob);
            }
//        
//        for (int i = 0; i <  q.getResultList().size(); i++) {
//             ReporteDetalleComprasDTO obj = new ReporteDetalleComprasDTO();
//             obj.setId_orden_compra(Long.valueOf(q.getResultList().get(i).toString()));
//             obj.setId_tipo_documento(Long.valueOf(q.getResultList().get(i).toString()));
//              // obj4 = q.getResultList();
//             listaCompra.add(obj);
//              
//        }
//         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompra;
    }

    public static List<ReporteDetalleFacturaDTO> listadoDetallesFactura(ReporteFacturaDTO objcab) {
        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        List<ReporteDetalleFacturaDTO> listaDetalle = null;
        Query q = em.createNativeQuery("select \n"
                + "	ve_factura_detalle.id_factura,\n"
                + "    ve_factura_detalle.id_factura_detalle,\n"
                + "    ve_factura_detalle.linea_detalle,\n"
                + "    ve_factura_detalle.descripcion,\n"
                + "    ve_factura_detalle.precio_unitario_venta,\n"
                + "    ve_factura_detalle.subtotal,\n"
                + "    ve_factura_detalle.valor_iva,\n"
                + "    ve_factura_detalle.valor_descuento,\n"
                + "    ve_factura_detalle.valor_total,\n"
                + "    ve_factura_detalle.estado,\n"
                + "    ve_factura_detalle.cantidad,\n"
                + "    pr_prestaciones.id_poducto\n"
                + "from ve_factura \n"
                + "inner join ve_factura_detalle\n"
                + "on ve_factura.id_factura = ve_factura_detalle.id_factura\n"
                + "inner join pr_prestaciones \n"
                + "on pr_prestaciones.id_prestacion = ve_factura_detalle.id_prestaciones\n"
                + "where ve_factura_detalle.id_factura = " + objcab.getId_factura().toString() + ";");
        List<Object[]> listobj = q.getResultList();
        listaDetalle = new ArrayList<ReporteDetalleFacturaDTO>();
        try {
            for (Object[] obj : listobj) {
                ReporteDetalleFacturaDTO ob = new ReporteDetalleFacturaDTO();
                ob.setId_factura(Long.parseLong(obj[0].toString()));
                ob.setId_factura_detalle(Long.parseLong(obj[1].toString()));
                ob.setLinea_detalle(Long.parseLong(obj[2].toString()));//
                ob.setDescripcion((obj[3].toString()));
                ob.setPrecio_unitario_venta(Double.parseDouble(obj[4].toString()));
                ob.setSubtotal(Double.parseDouble(obj[5].toString()));
                ob.setValor_iva(Double.parseDouble(obj[6].toString()));
                ob.setValor_descuento(Double.parseDouble(obj[7].toString()));
                ob.setValor_total(Double.parseDouble(obj[8].toString()));
                ob.setEstado(String.valueOf(obj[9].toString()));
                ob.setCantidad(Long.parseLong(obj[10].toString()));
                ob.setId_producto(Long.parseLong(obj[11].toString()));
                listaDetalle.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetalle;

    }

    private long showId(EntityManager em) {
        String nativeQuery = "SELECT max(id_orden_compra) FROM co_orden_compras;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number) query.getSingleResult()).longValue();
        System.out.println("prueba numero de orden " + id);
        return id;

    }

    public static List<ReporteProveedorDTO> obtenerEjemplo() {
        // EntityManager em = getEntityManager();
        List<ReporteProveedorDTO> QKardex = null;
        String nativeQuery = "SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> listobj = query.getResultList();
        QKardex = new ArrayList<ReporteProveedorDTO>();

//        for (int i = 0; i < listobj.size(); i++) {
//            ReporteDetalleComprasDTO obj = new ReporteDetalleComprasDTO();
//            obj.setId_orden_compra(Long.valueOf(Arrays.toString(listobj.get(i))));
//            obj.setId_tipo_documento(Long.valueOf(Arrays.toString(listobj.get(i))));
//            QKardex.add(obj);
//        }
        int aux = 0;
//        for (Object[] ob : listobj) {
//            ReporteDetalleComprasDTO obj = new ReporteDetalleComprasDTO();
//            obj.setId_orden_compra(Long.valueOf(ob[0].toString()));
//            obj.setId_tipo_documento(Long.valueOf(ob[1].toString()));
//            QKardex.add(obj);
//            aux++;
//        }
        return QKardex;
    }

    public ReporteProveedorDTO obtenerProveedor(Long id) {
        List<ReporteProveedorDTO> lista = null;
        ReporteProveedorDTO obj = null;
        String nativeQuery = "SELECT\n"
                + "  `id_proveedor`,\n"
                + "  `nombre`,\n"
                + "  `direccion`,\n"
                + "  `telefono1`,\n"
                + "  `telefono2`,\n"
                + "  `pagina_web`,\n"
                + "  `numero_identificacion`,\n"
                + "  `email`,\n"
                + "  `tipo_persona`,\n"
                + "  `id_pais`,\n"
                + "  `contribuyente_especial`,\n"
                + "  `codigo_contribuyente`,\n"
                + "  `observaciones`,\n"
                + "  `nombre_comercial`,\n"
                + "  `estado`\n"
                + "FROM `co_proveedores`\n"
                + "WHERE `co_proveedores`.`id_proveedor`= " + id + ";";
        Query query = em.createNativeQuery(nativeQuery);
        try {

            List<Object[]> lsObj = query.getResultList();
            lista = new ArrayList<ReporteProveedorDTO>();

            for (Object[] oo : lsObj) {
                obj = new ReporteProveedorDTO();
                obj.setId_proveedor(Long.parseLong(oo[0].toString()));
                obj.setNombre((String) (oo[1]));
                obj.setDireccion((String) (oo[2]));
                obj.setTelefono1((String) (oo[3]));
                obj.setTelefono2((String) (oo[4]));
                obj.setPagina_web((String) (oo[5]));
                obj.setNumero_identificacion((String) (oo[6]));
                obj.setEmail((String) (oo[7]));
                obj.setTipo_persona((Long) (oo[8]));
                obj.setId_pais((Long) (oo[9]));
                obj.setContribuyente_especial((String) (oo[10]));
                obj.setCodigo_contribuyente((String) (oo[11]));
                obj.setObservaciones((String) (oo[12]));
                obj.setNombre_comercial((String) (oo[13]));
                obj.setEstado((String) (oo[14]));
                //  lista.add(obj);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public static java.util.Date fechaActual() {
        java.util.Date fechaParseada = new java.util.Date();
        return fechaParseada;
    }
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");

    public String getFecha(JDateChooser jd) {
        if (jd.getDate() != null) {
            return Formato.format(jd.getDate());
        } else {
            return null;
        }
    }

    public static String formatoNumero(String valor) {   ////////////////   1

        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(Double.parseDouble(valor));

        if (valorFormateado.charAt(0) == ',') {
            String h = "0" + valorFormateado;
            valorFormateado = h;
        }

        return valorFormateado;
    }

    public static String removeScientificNotation(String value) {
        return new BigDecimal(value).toPlainString();
    }

    public static String buscarLocalidad(Long id) {
        String cadena = "";
        String nativeQuery = "SELECT DISTINCT IFNULL(lc.`dirreccion_cliente`,'---') FROM `se_localidad_cliente` lc \n"
                + "WHERE lc.`id_cliente` = " + id + " limit 0,1;";
        Query query = em.createNativeQuery(nativeQuery);
        if (cadena.equals("")) {
            cadena = "*******";
        } else {
            cadena = query.getSingleResult().toString();
        }
        return cadena;
    }

    public static String buscarCelular8(Long id) {
        String cadena = "";
        String nativeQuery = "SELECT DISTINCT IFNULL(lv.`celular`,'---') FROM `se_localidad_cliente` lc,`se_contactos_clientes` lv \n"
                + "WHERE lv.`id_localidad` = lc.`id_localidad_cliente`\n"
                + "AND lc.`id_cliente` = " + id + "\n"
                + "AND lv.`nombre` = 'PROPIO';";
        Query query = em.createNativeQuery(nativeQuery);
        cadena = query.getSingleResult().toString();
        return cadena;
    }

    public static String buscarCelular(Long id) {
        String cadena = "";
        String nativeQuery = "SELECT se_localidad_cliente.`celular` FROM `se_localidad_cliente`\n"
                + "INNER JOIN `se_contactos_clientes` \n"
                + "ON se_contactos_clientes.`id_localidad` = `se_localidad_cliente`.`id_localidad_cliente`\n"
                + "WHERE se_contactos_clientes.`nombre`='PROPIO' AND se_localidad_cliente.`id_cliente`= " + id + ";";
        Query query = em.createNativeQuery(nativeQuery);
        if (cadena.equals("")) {
            cadena = "*******";
        } else {
            cadena = query.getSingleResult().toString();
            System.out.println("poooooooooooo " + cadena);
        }
        return cadena;

    }

    public static String buscarCorreo1(Long id) {
        String cadena = "";
        String nativeQuery = "SELECT se_localidad_cliente.`email` FROM `se_localidad_cliente`\n"
                + "INNER JOIN `se_contactos_clientes` \n"
                + "ON se_contactos_clientes.`id_localidad` = `se_localidad_cliente`.`id_localidad_cliente`\n"
                + "WHERE se_contactos_clientes.`nombre`='PROPIO' AND se_localidad_cliente.`id_cliente`= " + id + ";";
        Query query = em.createNativeQuery(nativeQuery);
        cadena = query.getSingleResult().toString();
        return cadena;
    }

    public static String buscarCorreo(Long id) {
        String cadena = "";
        String nativeQuery = "SELECT se_localidad_cliente.`email` FROM `se_localidad_cliente`\n"
                + "INNER JOIN `se_contactos_clientes` \n"
                + "ON se_contactos_clientes.`id_localidad` = `se_localidad_cliente`.`id_localidad_cliente`\n"
                + "WHERE se_contactos_clientes.`nombre`='PROPIO' AND se_localidad_cliente.`id_cliente`= " + id + ";";
        Query query = em.createNativeQuery(nativeQuery);
        if (cadena.equals("")) {
            cadena = "********";

        } else {
            cadena = query.getSingleResult().toString();
        }
        return cadena;
    }
}
