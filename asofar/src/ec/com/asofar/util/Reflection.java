/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import java.lang.reflect.Method;

/**
 *
 * @author ADMIN
 */
public class Reflection {

    public static void Llamar(String direccion, SeUsuarios us, SeEmpresa em, SeSucursal su) {

        try {
            Class<?> dogClass = Class.forName(direccion);
            java.lang.reflect.Constructor<?> dogConstructor
                    = dogClass.getConstructor(java.awt.Frame.class, boolean.class, SeUsuarios.class, SeEmpresa.class, SeSucursal.class);
            Object dog = dogConstructor.newInstance(new javax.swing.JFrame(), true, us, em, su);
            String mth = "setVisible";
            Method m = dog.getClass().getMethod(mth, boolean.class);
            m.invoke(dog, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
