/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dto.CoOrdenCompras;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author usuario
 */
public class ReporteriaExt {
    // public String Query = null;
////     public ReporteriaExt(EntityManagerFactory emf) {
////        super(emf);
////    }
//private static EntityManagerFactory emf = null;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("asofarPU");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        try{
        List<ReporteComprasDTO1> itemList = new ArrayList<ReporteComprasDTO1>();
        itemList = reporteCompras(em);
      //  reporteCompras2(em);
            //System.out.println("j "+itemList.add("id_orden_compra"));
            System.out.println("lalala");
            
        
            for (ReporteComprasDTO1 str : itemList) {
                System.out.println(str.getId_orden_compra());
            }
//            for (int i = 0; i < itemList.size(); i++) {
//                  System.out.println(itemList.get(i).getFecha_entrega().toString());
//            }
//        
//        Iterator<CoOrdenCompras> iter = lista.iterator();
//        
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("fffff" + lista.get(i).getEstado());
//
//        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<ReporteComprasDTO1> reporteCompras(EntityManager em) {
        List<ReporteComprasDTO1> listaCompra = new ArrayList<>();
        List  obj4 =null;
//        ArrayList<ReporteComprasDTO1> lista = new ArrayList<>();
//        ReporteComprasDTO1 obj=null;
//        System.out.println("jjjj");
//       
//          String nativeQuery = "SELECT id_orden_compra FROM co_orden_compras c";
//          Query  query = em.createNativeQuery(nativeQuery);
//          listaCompra = (ArrayList)query.getResultList();
//          for (int i = 0; i < listaCompra.size(); i++) {
//            //listaCompra = ((List)query.getResultList());
//            obj.setId_orden_compra(listaCompra.get(i).getId_orden_compra());
//            lista.add(obj);
//        }
//        
//        for (ReporteComprasDTO1 f : lista) {
//            System.out.println(f.getId_orden_compra());
//        }
                 
          Query q = em.createNativeQuery("SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c");
          for (int i = 0; i <  q.getResultList().size(); i++) {
             ReporteComprasDTO1 obj = new ReporteComprasDTO1();
//             obj.setId_orden_compra(Long.valueOf(q.getResultList().get(i).toString()));
//             obj.setId_tipo_documento(Long.valueOf(q.getResultList().get(i).toString()));
               obj4 = q.getResultList();
           //   listaCompra.add(obj);
              
        }
         
         for (int j = 0; j < obj4.size(); j++) {
                 ReporteComprasDTO1 obj = new ReporteComprasDTO1();
                 obj.setId_orden_compra(Long.valueOf(obj4.get(j).toString()));
                 listaCompra.add(obj);
              }

            for (ReporteComprasDTO1 a : listaCompra) {
                System.out.println("Author "
                        + a.getId_orden_compra().toString()
                        + " "
                        +a.getId_tipo_documento().toString());
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
     
    public static void reporteCompras2(EntityManager em) {
        List<ReporteComprasDTO1> listaCompra = new ArrayList<>();
      Query query = em.createNativeQuery(
                "SELECT c.id_orden_compra,c.id_tipo_documento FROM co_orden_compras c", 
                ReporteComprasDTO1.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            listaCompra = (List)query.getResultList().get(i);
        }
        for (ReporteComprasDTO1 r : listaCompra) {
            System.out.println(r.getId_orden_compra().toString()+"  "+r.getId_tipo_documento().toString());
        }
    }
}
