/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.main;

import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.views.inicio.Frm_cargarSistema;
import java.util.Date;

/**
 *
 * @author admin1
 */
public class Asofar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchMethodException {
        Frm_cargarSistema fr= new Frm_cargarSistema();
        fr.setVisible(true);
       

    }
}
