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
                + "        g.nombre_comercial\n"
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
                + "        g.nombre_comercial\n"
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
                + "pr_productos.codigo_barra,\n"
                + "pr_productos.id_tipo_presentacion,\n"
                + "pr_productos.nombre_producto,\n"
                + "pr_productos.receta,\n"
                + "pr_tipo_presentacion.nombre\n"
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
        ReporteProveedorDTO obj =null;
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
}
