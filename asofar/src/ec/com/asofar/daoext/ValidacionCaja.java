/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ValidacionCaja {
    
    public static boolean Validacion(VeCaja caja){
        boolean valor=true;
        for (int i = 0; i < caja.getVeDetalleCajaList().size(); i++) {
            if(caja.getVeDetalleCajaList().get(i).getFechaCierre()==null){
            valor=false;
            }
        }
        return valor;
    
    }
    
}
