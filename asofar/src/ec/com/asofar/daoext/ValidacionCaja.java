/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ValidacionCaja {

    static VeDetalleCajaJpaController cajaController = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    public static boolean Validacion(VeCaja caja, SeUsuarios su) {
        boolean valor = true;
        List<VeDetalleCaja> lista = cajaController.findVeDetalleCajaEntities();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getFechaCierre() == null
                    && Character.valueOf('A').equals(lista.get(i).getEstado())
                    && lista.get(i).getIdUsuario() == su.getIdUsuario()
                    && lista.get(i).getVeCaja().getIdCaja() == caja.getIdCaja()) {
                valor = false;
                System.out.println("hola viejo que hay");
            }
        }
        return valor;
    }
}
