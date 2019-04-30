/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin1
 */
public class Asofar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchMethodException {
        try {
//            Class<?> cl = Class.forName("ec.com.asofar.views.Grupos.ConsultaGruposForm");
//            Constructor<?> cr = cl.getConstructor(java.awt.Frame.class,boolean.class);
//            cr.newInstance(new javax.swing.JFrame(),true);
               String dogClassName ="ec.com.asofar.views.Grupos.ConsultaGruposForm";
               Class<?> dogClass = Class.forName(dogClassName);
               
               java.lang.reflect.Constructor<?> dogConstructor = dogClass.getConstructor(java.awt.Frame.class,boolean.class);
               Object dog = dogConstructor.newInstance(new javax.swing.JFrame(), true);
               String mth = "setVisible";
               Method m= dog.getClass().getMethod(mth,boolean.class);
               m.invoke(dog, true);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
