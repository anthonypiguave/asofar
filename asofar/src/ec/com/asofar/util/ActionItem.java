/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ADMIN
 */
public class ActionItem {
    
    public static ActionListener Obtener(String ruta,SeUsuarios us1,SeEmpresa em1,SeSucursal su1){
        
        ActionListener ac= new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Reflection re = new Reflection();

                                    re.Llamar(ruta, us1, em1, su1);

                                    //To change body of generated methods, choose Tools | Templates.
                                }
                            };
        
        
        return ac;
    
    
    }
    
}
