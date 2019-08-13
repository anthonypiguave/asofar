/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

/**
 *
 * @author ADMIN
 */
public class Estado {
    
    public static String ObtenerEstado(String estado){
        String valor=null;
        
        if(estado.equals("A")){
           
            valor="Activo";
        
        } else if(estado.equals("I")){
            
            valor="Inactivo";
        
        } else if(estado.equals("Activo")){
        
            valor="A";
        }else {
        
            valor="I";
        }
        
        
        
        return valor;
    
    
    
    }
    
}
