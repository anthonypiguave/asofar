/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dto.CoOrdenCompras;
import java.util.ArrayList;
import java.util.Iterator;
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
    // public String Query = null;
////     public ReporteriaExt(EntityManagerFactory emf) {
////        super(emf);
////    }
//private static EntityManagerFactory emf = null;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("asofarPU");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        try{
        List<String> itemList = new ArrayList<String>();
        itemList=reporteCompras(em);
        
            System.out.println("j "+itemList.add("id_orden_compra"));
            System.out.println("lalala");
            
        String[] itemsArray = new String[itemList.size()];
        itemsArray = itemList.toArray(itemsArray);
        
        for(String s : itemsArray)
            System.out.println(s);
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

    public static List<String> reporteCompras(EntityManager em) {
        List<String> listaCompra = new ArrayList<String>();
        String nativeQuery = "SELECT * FROM `co_orden_compras` a\n"
                + "INNER JOIN `co_detalle_orden_compra` b \n"
                + "ON a.`id_orden_compra` = b.`id_orden_compra`\n"
                + "AND a.`estado`='P'\n"
                + "ORDER BY a.`fecha_creacion`";
        Query query = em.createNativeQuery(nativeQuery);
        listaCompra = query.getResultList();
        // System.out.println("prueba numero de orden "+ id);
//
//        for (int i = 0; i < listaCompra.size(); i++) {
//            System.out.println("fffff" + listaCompra.get(i).getUsuarioCreacion());
//
//        }
        return listaCompra;
    }
}
