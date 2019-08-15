/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.util.Calendario;
import java.sql.Date;
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
        try{
        List<ReporteComprasDTO> itemList = null;
        itemList = reporteCompras();
//        reporteCompras2(em);
//         itemList = obtenerEjemplo();
//        itemList= ed.listarCategorias();
//System.out.println("j "+itemList.add("id_orden_compra"));
//            System.out.println("lalala");
            
        

            for (int i = 0; i < itemList.size(); i++) {
                  System.out.println(itemList.get(i).getId_orden_compra().toString()+" "+
                          itemList.get(i).getFecha_aprobacion().toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<ReporteComprasDTO> reporteCompras() {
        
        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
       
        List<ReporteComprasDTO> listaCompra = null;
        Query q = em.createNativeQuery("SELECT  distinct\n" +
                "		c.id_orden_compra,\n" +
                "		c.id_tipo_documento,\n" +
                "        c.fecha_entrega,\n" +
                "        c.id_proveedor,\n" +
                "        ifnull(c.total_subtotal,0) as total_subtotal,\n" +
                "        ifnull(c.total_ice,0) as total_ice,\n" +
                "        ifnull(c.total_iva,0) as total_iva,\n" +
                "        c.total_compra\n" +
                "FROM co_orden_compras as c\n" +
                "inner join co_detalle_orden_compra as d  \n" +
                "	  on   c.id_orden_compra = d.id_orden_compra\n" +
                "      and  c.estado = 'P';");
        List<Object[]> listobj = q.getResultList();
        listaCompra = new ArrayList<ReporteComprasDTO>();   
         try{
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
            listaCompra.add(ob);
        }
//        
//        for (int i = 0; i <  q.getResultList().size(); i++) {
//             ReporteComprasDTO1 obj = new ReporteComprasDTO1();
//             obj.setId_orden_compra(Long.valueOf(q.getResultList().get(i).toString()));
//             obj.setId_tipo_documento(Long.valueOf(q.getResultList().get(i).toString()));
//              // obj4 = q.getResultList();
//             listaCompra.add(obj);
//              
//        }
//         
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCompra;
    }
    
     private long showId(EntityManager em) {
        String nativeQuery = "SELECT max(id_orden_compra) FROM co_orden_compras;";
        Query query = em.createNativeQuery(nativeQuery);
        long id = ((Number)query.getSingleResult()).longValue();
        System.out.println("prueba numero de orden "+ id);
        return id;
        
    }
     
    public static List<ReporteComprasDTO1> obtenerEjemplo() {
       // EntityManager em = getEntityManager();
        List<ReporteComprasDTO1> QKardex = null;
        String nativeQuery = "SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c";
        Query query = em.createNativeQuery(nativeQuery);
        
        List<Object[]> listobj = query.getResultList();
        QKardex = new ArrayList<ReporteComprasDTO1>();
        
//        for (int i = 0; i < listobj.size(); i++) {
//            ReporteComprasDTO1 obj = new ReporteComprasDTO1();
//            obj.setId_orden_compra(Long.valueOf(Arrays.toString(listobj.get(i))));
//            obj.setId_tipo_documento(Long.valueOf(Arrays.toString(listobj.get(i))));
//            QKardex.add(obj);
//        }
        int aux=0;
        for (Object[] ob : listobj) {
            ReporteComprasDTO1 obj = new ReporteComprasDTO1();
            obj.setId_orden_compra(Long.valueOf(ob[0].toString()));
            obj.setId_tipo_documento(Long.valueOf(ob[1].toString()));
            QKardex.add(obj);
            aux++;
        }
        return QKardex;
    }
    
    public  List<ReporteComprasDTO1> listarCategorias() {
       // EntityManager em = getEntityManager();
        List<ReporteComprasDTO1> lista = null;
        String nativeQuery = "SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c";
        Query query = em.createNativeQuery(nativeQuery);
        //query.setParameter(1, Integer.parseInt(id));
        try {
            
            List<Object[]> lsObj=query.getResultList();
            lista= new ArrayList<ReporteComprasDTO1>();
            
            for (Object[] ooo:lsObj)
            {
                ReporteComprasDTO1 oo= new ReporteComprasDTO1();
                oo.setId_orden_compra(Long.parseLong(ooo[0].toString()));
                oo.setId_tipo_documento(Long.parseLong(ooo[1].toString()));
                lista.add(oo);
            }
            
        } catch (Exception ex) {
               ex.printStackTrace();
        }
        return lista;
    }
}
