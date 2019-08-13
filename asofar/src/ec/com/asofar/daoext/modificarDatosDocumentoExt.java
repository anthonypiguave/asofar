/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import static org.eclipse.persistence.sessions.SessionProfiler.Transaction;

 

public class modificarDatosDocumentoExt extends InTipoDocumentoJpaController{
    
    
    public modificarDatosDocumentoExt () {
        super(EntityManagerUtil.ObtenerEntityManager());
    }
    
    
    public void guardarEstadoDocumento (List<InTipoDocumento> lista) throws Exception{
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        for (InTipoDocumento trx:lista)
        {
             em.merge(trx);
        }
       
        em.getTransaction().commit();
        System.out.println("hjjsjhs");
    }
    
    public  void guardarItemsProducto(List<String> lista) {
        EntityManager em = getEntityManager();
        //List<String> litems = null;

        for (int i = 0; i < lista.size(); i++) {
            String nativeQuery = lista.get(i);
            Query query = em.createNativeQuery(nativeQuery, InTipoDocumento.class);
            query.executeUpdate();
            nativeQuery="";
        }
        
    }
    public void guardarDetItemsCotizacion (List<CoDetItemsCotizacion> lista) throws Exception{
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        for (CoDetItemsCotizacion trx:lista)
        {
             em.merge(trx);
        }
       
        em.getTransaction().commit();
        System.out.println("hjjsjhs2");
    }
}
