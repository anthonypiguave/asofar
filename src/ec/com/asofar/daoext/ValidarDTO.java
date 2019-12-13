/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.InBodegaJpaController;
import ec.com.asofar.dao.InTipoBodegaJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.dao.PrArticuloJpaController;
import ec.com.asofar.dao.PrFabricanteJpaController;
import ec.com.asofar.dao.PrGruposJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.dao.PrTipoMedidasJpaController;
import ec.com.asofar.dao.PrTipoPresentacionJpaController;
import ec.com.asofar.dao.SeRolesJpaController;
import ec.com.asofar.dao.SeTipoPersonaJpaController;
import ec.com.asofar.dao.VeUnidadServicioJpaController;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.SeRoles;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dao.*;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ValidarDTO {

    public static boolean ValidarInBodega(String nombre) {
        InBodegaJpaController control = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InBodega> lista = control.findInBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreBodega().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }
//    public static boolean ValidarSeCliente(String nombre){
//        SeClientesJpaController control=new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
//        boolean valor=false;
//        List<SeClientes> lista=control.findSeClientesEntities();
//        
//        for (int i = 0; i <lista.size(); i++) {
//            if(lista.get(i).getNombreCompleto().equals(nombre)){
//            valor=true;
//            }
//        }
//        
//        return valor;
//    }    

    public static boolean ValidarSeClienteCedula(String cedula) {
        SeClientesJpaController control = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<SeClientes> lista = control.findSeClientesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumeroIdentificacion().equals(cedula)) {
                valor = true;
            }
        }

        return valor;
    }

    public static boolean ValidarPreporservi(String nombre) {

        InPrestacionesPorServiciosJpaController control1 = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InPrestacionesPorServicios> lista = control1.findInPrestacionesPorServiciosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrPrestaciones().getNombrePrestacion().equals(nombre)) {
                valor = true;
            }
        }

        return valor;
    }

    public static boolean ValidarInTipoBodega(String nombre) {
        InTipoBodegaJpaController control = new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InTipoBodega> lista = control.findInTipoBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarInTipoDocumento(String nombre) {
        InTipoDocumentoJpaController control = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InTipoDocumento> lista = control.findInTipoDocumentoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreDocumento().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarInTipoMovimiento(String nombre) {
        InTipoMovimientoJpaController control = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InTipoMovimiento> lista = control.findInTipoMovimientoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreMovimiento().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrArticulo(String nombre) {
        PrArticuloJpaController control = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrArticulo> lista = control.findPrArticuloEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreArticulo().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrGrupos(String nombre) {
        PrGruposJpaController control = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrGrupos> lista = control.findPrGruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrPrestaciones(String nombre) {
        PrPrestacionesJpaController control = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrPrestaciones> lista = control.findPrPrestacionesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombrePrestacion().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrSubGrupos(String nombre) {
        PrSubgruposJpaController control = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrSubgrupos> lista = control.findPrSubgruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrTipoMedidas(String nombre) {
        PrTipoMedidasJpaController control = new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrTipoMedidas> lista = control.findPrTipoMedidasEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreTipoMedida().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPrTipoPresentacion(String nombre) {
        PrTipoPresentacionJpaController control = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrTipoPresentacion> lista = control.findPrTipoPresentacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarSeRoles(String nombre) {
        SeRolesJpaController control = new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<SeRoles> lista = control.findSeRolesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarSeTipoPersona(String nombre) {
        SeTipoPersonaJpaController control = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<SeTipoPersona> lista = control.findSeTipoPersonaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarVeUnidadServicio(String nombre) {
        VeUnidadServicioJpaController control = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<VeUnidadServicio> lista = control.findVeUnidadServicioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreUnidadServicio().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarFab(String nombre) {
        PrFabricanteJpaController control = new PrFabricanteJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrFabricante> lista = control.findPrFabricanteEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                valor = true;
            }
        }

        return valor;

    }

    public static boolean ValidarPresta(String nomPres) {

        InPrestacionesPorServiciosJpaController control1 = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InPrestacionesPorServicios> lista = control1.findInPrestacionesPorServiciosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrPrestaciones().getNombrePrestacion().equals(nomPres)) {
                valor = true;
            }
        }
        return valor;
    }

    public static boolean ValidarUnidadServicio(String nomUnidad) {

        InPrestacionesPorServiciosJpaController control1 = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<InPrestacionesPorServicios> lista = control1.findInPrestacionesPorServiciosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrPrestaciones().getNombrePrestacion().equals(nomUnidad)) {
                valor = true;
            }
        }
        return valor;
    }

    public static boolean ValidarEmpaques(String nombre) {
        PrEmpaqueJpaController control = new PrEmpaqueJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrEmpaque> lista = control.findPrEmpaqueEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreEmpaque().equals(nombre)) {
                valor = true;
            }
        }

        return valor;
    }

    public static boolean ValidarSeUsuario(String nombre) {
        SeUsuariosJpaController control = new SeUsuariosJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<SeUsuarios> lista = control.findSeUsuariosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUsuario().equals(nombre)) {
                valor = true;
            }
        }
        return valor;
        }
    }
