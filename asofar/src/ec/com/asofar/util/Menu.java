/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeOpcionesMenu;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import java.util.List;
import javax.swing.JMenu;

/**
 *
 * @author ADMIN
 */
public class Menu {
    
    public static void HacerMenu(SeOpcionesMenu us,List<SeOpcionesMenu> lista,JMenu menu,SeUsuarios usu,
    SeEmpresa emp,SeSucursal suc){
    
        for (int i = 0; i < us.getSeOpcionesMenuList().size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (true) {
                    
                } else {
                }
            }
            
        }
    }
    
}
