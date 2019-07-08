/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dao.VeFacturaJpaController;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.util.EntityManagerUtil;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ValidacionCaja {

    static VeDetalleCajaJpaController cajaController = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    static VeFacturaJpaController facturaController = new VeFacturaJpaController(EntityManagerUtil.ObtenerEntityManager());

    public static boolean ValidacionApertura(VeCaja caja, SeUsuarios su) {
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

    public static boolean ValidacionCierre(VeDetalleCaja detallecaja, SeUsuarios su) {
        boolean valor = false;

        java.util.Date fecha1 = detallecaja.getFechaInicio();
        java.util.Date fecha2 = detallecaja.getHoraInicio();
        String resultado1 = fecha1 + " " + fecha2;
        java.util.Date f1 = Date.valueOf(resultado1);

        java.util.Date fecha3 = detallecaja.getFechaCierre();
        java.util.Date fecha4 = detallecaja.getHoraCierre();
        String resultado2 = fecha3 + " " + fecha4;
        java.util.Date f2 = Date.valueOf(resultado2);
 
        System.out.println("resultado"+f2);
        
        double valor1 = detallecaja.getDineroInicio();
        double valor2 = detallecaja.getDineroCierre();
        double resultado3 = (valor1 + valor2);

        List<VeFactura> lista = facturaController.findVeFacturaEntities();
        for (int i = 0; i < lista.size(); i++) {
            double v = 0;
            v += lista.get(i).getTotalFacturado();

            if (lista.get(i).getIdUsuario().equals(su.getIdUsuario())
                    && lista.get(i).getIdCaja().equals(detallecaja.getVeDetalleCajaPK().getIdCaja())
                    && f1.after(lista.get(i).getFechaFacturacion()) == true
                    && f2.before(lista.get(i).getFechaFacturacion()) == true
                    && resultado3 == v) {
                valor = true;
                System.out.println("hola viejo que hay");
            }
        }
        return valor;
    }
}
