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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class ValidacionCaja {

    static VeDetalleCajaJpaController cajaController = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    static VeFacturaJpaController facturaController = new VeFacturaJpaController(EntityManagerUtil.ObtenerEntityManager());
    static VeFacturaEXT vf = new VeFacturaEXT(EntityManagerUtil.ObtenerEntityManager());

    public static boolean ValidacionApertura(VeCaja caja, SeUsuarios su) {
        boolean valor = true;
        List<VeDetalleCaja> lista = cajaController.findVeDetalleCajaEntities();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getFechaCierre() == null
                    && Character.valueOf('A').equals(lista.get(i).getEstado())
                    && lista.get(i).getIdUsuario().longValue() == su.getIdUsuario()
                    && lista.get(i).getVeCaja().getIdCaja() == caja.getIdCaja()) {
                valor = false;
            }
        }
        return valor;
    }
    public static boolean ValidacionApertura1(VeCaja caja, SeUsuarios su) {
        boolean valor = true;
        List<VeDetalleCaja> lista = cajaController.findVeDetalleCajaEntities();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(" == null "+lista.get(i).getFechaCierre());
            //lista.get(i).getFechaCierre() == null
            System.out.println("='A' "+lista.get(i).getEstado());
              //  && Character.valueOf('A').equals(lista.get(i).getEstado())
            System.out.println("id udua_Login + id usu guar "+su.getIdUsuario()+" "+lista.get(i).getIdUsuario().longValue());
                   // && lista.get(i).getIdUsuario().longValue() == su.getIdUsuario()
            System.out.println("id _caja "+lista.get(i).getVeCaja().getIdCaja()+" "+caja.getIdCaja());
//                    && lista.get(i).getVeCaja().getIdCaja() == caja.getIdCaja()
            if (lista.get(i).getFechaCierre() == null
                    && Character.valueOf('A').equals(lista.get(i).getEstado())
                    && lista.get(i).getIdUsuario().longValue() == su.getIdUsuario()
                    && lista.get(i).getVeCaja().getIdCaja() == caja.getIdCaja()) {
                valor = false;
            }
        }
        System.out.println("caloer"+valor);
        return valor;
    }

    public static boolean ValidacionCierre(VeDetalleCaja detallecaja, SeUsuarios su) {
        boolean valor = false;
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");

        java.sql.Date fecha1 = new java.sql.Date(detallecaja.getFechaInicio().getTime());
        String fecha2 = df1.format(detallecaja.getHoraInicio().getTime());
        String resultado1 = fecha1 + " " + fecha2;
        java.sql.Date fecha3 = new java.sql.Date(detallecaja.getFechaCierre().getTime());
//        String fecha4 = detallecaja.getHoraCierre().toString();
        String fecha4 = df1.format(detallecaja.getHoraCierre().getTime());
        String resultado2 = fecha3 + " " + fecha4;
        Double factura = 0.0;
        Double total = 0.0;

        List<VeFactura> lista = facturaController.findVeFacturaEntities();
        List<VeFactura> listafactura = vf.RecorrerFecha(resultado1, resultado2, detallecaja);
//        for (int i = 0; i < lista.size(); i++) {
//            if (Objects.equals(lista.get(i).getIdCaja(), detallecaja.getVeCaja().getIdCaja())) {
//            String idCaja = lista.get(i).getIdCaja().toString();
//            String detIdCaja = detallecaja.getVeCaja().getIdCaja().toString();
//            if (idCaja.equals(detIdCaja)) {
                for (int j = 0; j < listafactura.size(); j++) {
                    if (listafactura.get(j).getIdUsuario()==detallecaja.getIdUsuario()) {
                        factura += listafactura.get(j).getTotalFacturado();
                        total = factura + detallecaja.getDineroInicio();
//                        break;
                    }
                }
//            }
//        }
        if (Objects.equals(total, detallecaja.getDineroCierre())) {
            valor = true;
            JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO EXITOSAMENTE!", "CAJA CERRADA",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {/*inicio*/
            
//            int confirmar = JOptionPane.showConfirmDialog(null,"¿DESEA CERRAR CAJA ?"
//                    + "CON LOS MOVIMIENTOS..  CIERRE REQUERIDO: $ "
//                    + total + "\n CIERRE ACTUAL: $ " + detallecaja.getDineroCierre(),
//                    "¿DESEA CERRAR CAJA IGUALMENTE?",JOptionPane.YES_NO_OPTION);
            int confirmar = JOptionPane.showConfirmDialog(null,"¿DESEA CERRAR CAJA ?","",JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                valor = true;
            }
        }/*final*/
        return valor;
    }

    public static Double facturadoRetorno(VeDetalleCaja detallecaja) {
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");

        java.sql.Date fecha1 = new java.sql.Date(detallecaja.getFechaInicio().getTime());
        String fecha2 = df1.format(detallecaja.getHoraInicio().getTime());
        String resultado1 = fecha1 + " " + fecha2;
        java.sql.Date fecha3 = new java.sql.Date(detallecaja.getFechaCierre().getTime());
//        String fecha4 = detallecaja.getHoraCierre().toString();
        String fecha4 = df1.format(detallecaja.getHoraCierre().getTime());
        String resultado2 = fecha3 + " " + fecha4;
        Double factura = 0.0;
        Double total = 0.0;

        List<VeFactura> lista = facturaController.findVeFacturaEntities();
        List<VeFactura> listafactura = vf.RecorrerFecha(resultado1, resultado2, detallecaja);
//        for (int i = 0; i < lista.size(); i++) {
//            if (Objects.equals(lista.get(i).getIdCaja(), detallecaja.getVeCaja().getIdCaja())) {
//            String idCaja = lista.get(i).getIdCaja().toString();
//            String detIdCaja = detallecaja.getVeCaja().getIdCaja().toString();
//            if (idCaja.equals(detIdCaja)) {
                for (int j = 0; j < listafactura.size(); j++) {
                    if (listafactura.get(j).getIdUsuario()==detallecaja.getIdUsuario()&&
                        listafactura.get(j).getEstado().equals("A")) {
                        System.out.println("Lista "+listafactura.get(j).getTotalFacturado());
                        factura = factura +listafactura.get(j).getTotalFacturado();
                        total = factura + detallecaja.getDineroInicio();
//                        break;
//                        System.out.println("VALOR "+total);
//                        break;
                    }
                }
//            }
//        }
        return factura;
    }

    public static Double valoresCaja(VeDetalleCaja detallecaja) {
//        boolean valor = false;
        DateFormat df1 = new SimpleDateFormat("hh:mm:ss");

        java.sql.Date fecha1 = new java.sql.Date(detallecaja.getFechaInicio().getTime());
        String fecha2 = df1.format(detallecaja.getHoraInicio());
        String resultado1 = fecha1 + " " + fecha2;

        java.sql.Date fecha3 = new java.sql.Date(detallecaja.getFechaCierre().getTime());
        String fecha4 = df1.format(detallecaja.getHoraCierre());
        String resultado2 = fecha3 + " " + fecha4;

        Double factura = 0.0;
        Double total = 0.0;
        List<VeFactura> lista = facturaController.findVeFacturaEntities();
        List<VeFactura> listafactura = vf.RecorrerFecha(resultado1, resultado2, detallecaja);
        for (int i = 0; i < lista.size(); i++) {
            if (Objects.equals(lista.get(i).getIdCaja(), detallecaja.getVeCaja().getIdCaja())) {
                for (int j = 0; j < listafactura.size(); j++) {
                    if (listafactura.get(j).getIdUsuario().equals(detallecaja.getIdUsuario())) {
                        factura += listafactura.get(j).getTotalFacturado();
                        total = factura + detallecaja.getDineroInicio();
                    }
                }
            }
        }
        Double valorApertura = detallecaja.getDineroInicio();
        return valorApertura;
    }
       public static Double facturasAnuladas(VeDetalleCaja detallecaja) {
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");

        java.sql.Date fecha1 = new java.sql.Date(detallecaja.getFechaInicio().getTime());
        String fecha2 = df1.format(detallecaja.getHoraInicio().getTime());
        String resultado1 = fecha1 + " " + fecha2;
        java.sql.Date fecha3 = new java.sql.Date(detallecaja.getFechaCierre().getTime());
//        String fecha4 = detallecaja.getHoraCierre().toString();
        String fecha4 = df1.format(detallecaja.getHoraCierre().getTime());
        String resultado2 = fecha3 + " " + fecha4;
        Double factura = 0.0;
        Double total = 0.0;

        List<VeFactura> lista = facturaController.findVeFacturaEntities();
        List<VeFactura> listafactura = vf.RecorrerFecha(resultado1, resultado2, detallecaja);
//        for (int i = 0; i < lista.size(); i++) {
//            if (Objects.equals(lista.get(i).getIdCaja(), detallecaja.getVeCaja().getIdCaja())) {
//            String idCaja = lista.get(i).getIdCaja().toString();
//            String detIdCaja = detallecaja.getVeCaja().getIdCaja().toString();
//            if (idCaja.equals(detIdCaja)) {
                for (int j = 0; j < listafactura.size(); j++) {
                    if (listafactura.get(j).getIdUsuario()==detallecaja.getIdUsuario()&&
                        listafactura.get(j).getEstado().equals("D")) {
                        System.out.println("Lista "+listafactura.get(j).getTotalFacturado());
                        factura = factura +listafactura.get(j).getTotalFacturado();
                        total = factura + detallecaja.getDineroInicio();
//                        break;
//                        System.out.println("VALOR "+total);
//                        break;
                    }
                }
//            }
//        }
        return factura;
    }
}
