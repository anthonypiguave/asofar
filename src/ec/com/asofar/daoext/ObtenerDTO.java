/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.InBodegaJpaController;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.SeRoles;
import ec.com.asofar.dto.PrMedidasPK;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dao.*;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTipoPrestacion;
import ec.com.asofar.dto.SeCiudad;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.util.EntityManagerUtil;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ObtenerDTO {

    public static InBodega ObtenerInBodega(String nombre) {

        InBodegaJpaController control = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InBodega dto = new InBodega();
        List<InBodega> lista = control.findInBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreBodega().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SePais ObtenerSePais(String nombre) {
        SePaisJpaController control = new SePaisJpaController(EntityManagerUtil.ObtenerEntityManager());
        SePais dto = new SePais();
        List<SePais> lista = control.findSePaisEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SePais ObtenerSePais(int id) {
        SePaisJpaController control = new SePaisJpaController(EntityManagerUtil.ObtenerEntityManager());
        SePais dto = new SePais();
        List<SePais> lista = control.findSePaisEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPais().equals(id)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeProvincia ObtenerSeProvincia(int id) {
        SeProvinciaJpaController control = new SeProvinciaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeProvincia dto = new SeProvincia();
        List<SeProvincia> lista = control.findSeProvinciaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProvincia().equals(id)) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static SeProvincia ObtenerSeProvincia(String nombre) {
        SeProvinciaJpaController control = new SeProvinciaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeProvincia dto = new SeProvincia();
        List<SeProvincia> lista = control.findSeProvinciaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static SeCiudad ObtenerSeCiudad(int id) {
        SeCiudadJpaController control = new SeCiudadJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeCiudad dto = new SeCiudad();
        List<SeCiudad> lista = control.findSeCiudadEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdCiudad().equals(id)) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static SeCiudad ObtenerSeCiudad(String nombre) {
        SeCiudadJpaController control = new SeCiudadJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeCiudad dto = new SeCiudad();
        List<SeCiudad> lista = control.findSeCiudadEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static VeCaja ObtenerVeCaja(String nombre) {
        VeCajaJpaController control = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeCaja dto = new VeCaja();
        List<VeCaja> lista = control.findVeCajaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static CoProveedores ObtenerProveedorPedido(String nombre) {
        CoProveedoresJpaController control = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoProveedores dto = new CoProveedores();
        List<CoProveedores> lista = control.findCoProveedoresEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static CoProveedores ObtenerProveedorPedido(BigInteger id) {
        CoProveedoresJpaController control = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoProveedores dto = new CoProveedores();
        List<CoProveedores> lista = control.findCoProveedoresEntities();

        BigInteger big = id;
        int valor = big.intValue();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProveedor() == valor) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoDocumento ObtenerDocumentoPedido(String nombre) {
        InTipoDocumentoJpaController control = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto = new InTipoDocumento();
        List<InTipoDocumento> lista = control.findInTipoDocumentoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreDocumento().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoDocumento ObtenerDocumentoPedido(BigInteger id) {
        InTipoDocumentoJpaController control = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto = new InTipoDocumento();
        List<InTipoDocumento> lista = control.findInTipoDocumentoEntities();

        BigInteger big = id;
        int valor = big.intValue();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoDocumento() == valor) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeUsuarios ObtenerUsuarios(long id) {
        SeUsuariosJpaController control = new SeUsuariosJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeUsuarios dto = new SeUsuarios();
        List<SeUsuarios> lista = control.findSeUsuariosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdUsuario().equals(id)) {
                dto = lista.get(i);
                System.out.println("** "+dto);
                break;
            }
        }

        return dto;

    }

    public static PrProductos ObtenerProducto(String id) {
        PrProductosJpaController control = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrProductos dto = new PrProductos();
        List<PrProductos> lista = control.findPrProductosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getPrProductosPK().getIdProducto()).equals(id)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InBodega ObtenerInBodega(int id) {
        InBodegaJpaController control = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InBodega dto = new InBodega();
        List<InBodega> lista = control.findInBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getInBodegaPK().getIdBodega() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static InTipoBodega ObtenerInTipoBodega(String nombre) {
        InTipoBodegaJpaController control = new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoBodega dto = new InTipoBodega();
        List<InTipoBodega> lista = control.findInTipoBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoBodega ObtenerInTipoBodega(Long id) {
        InTipoBodegaJpaController control = new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoBodega dto = new InTipoBodega();
        List<InTipoBodega> lista = control.findInTipoBodegaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoBodega() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoDocumento ObtenerInTipoDocumento(String nombre) {
        InTipoDocumentoJpaController control = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto = new InTipoDocumento();
        List<InTipoDocumento> lista = control.findInTipoDocumentoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreDocumento().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoDocumento ObtenerInTipoDocumento(int id) {
        InTipoDocumentoJpaController control = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto = new InTipoDocumento();
        List<InTipoDocumento> lista = control.findInTipoDocumentoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoDocumento() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoMovimiento ObtenerInTipoMovimiento(String nombre) {
        InTipoMovimientoJpaController control = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoMovimiento dto = new InTipoMovimiento();
        List<InTipoMovimiento> lista = control.findInTipoMovimientoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreMovimiento().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InTipoMovimiento ObtenerInTipoMovimiento(int id) {
        InTipoMovimientoJpaController control = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoMovimiento dto = new InTipoMovimiento();
        List<InTipoMovimiento> lista = control.findInTipoMovimientoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoMovimiento() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrArticulo ObtenerPrArticulo(String nombre) {
        PrArticuloJpaController control = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto = new PrArticulo();
        List<PrArticulo> lista = control.findPrArticuloEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreArticulo().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrArticulo ObtenerPrArticulo(int id) {
        PrArticuloJpaController control = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto = new PrArticulo();
        List<PrArticulo> lista = control.findPrArticuloEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrArticuloPK().getIdArticulo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static String ObtenerPrArticulo(Long id) {
        PrArticuloJpaController control = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto = new PrArticulo();
        List<PrArticulo> lista = control.findPrArticuloEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrArticuloPK().getIdArticulo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto.getNombreArticulo();

    }

    public static PrGrupos ObtenerPrGrupos(String nombre) {
        PrGruposJpaController control = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto = new PrGrupos();
        List<PrGrupos> lista = control.findPrGruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrGrupos ObtenerPrGrupos(int id) {
        PrGruposJpaController control = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto = new PrGrupos();
        List<PrGrupos> lista = control.findPrGruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdGrupo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static String ObtenerPrGrupos(Long id) {
        PrGruposJpaController control = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto = new PrGrupos();
        List<PrGrupos> lista = control.findPrGruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdGrupo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto.getNombre();

    }

    public static PrPrestaciones ObtenerPrPrestaciones(String nombre) {
        PrPrestacionesJpaController control = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto = new PrPrestaciones();
        List<PrPrestaciones> lista = control.findPrPrestacionesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombrePrestacion().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrPrestaciones ObtenerPrPrestaciones(int id) {
        PrPrestacionesJpaController control = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto = new PrPrestaciones();
        List<PrPrestaciones> lista = control.findPrPrestacionesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPrestacion() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrPrestaciones ObtenerPrPrestacionesOn(BigInteger id) {
        PrPrestacionesJpaController control = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto = new PrPrestaciones();
        List<PrPrestaciones> lista = control.findPrPrestacionesEntities();

        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("iiiiii"+lista.get(i).getNombrePrestacion());
//            System.out.println("id ajjajaja"+id);
//            System.out.println("listA "+lista.get(i).getIdPrestacion());
            if (lista.get(i).getIdPrestacion() == Long.valueOf(id.toString())) {

                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

////    public static PrTarifario ObtenerPrTarifarioON(Long id) {
////        PrTarifarioJpaController control = new PrTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
////        PrTarifario dto = new PrTarifario();
////        List<PrTarifario> lista = control.findPrTarifarioEntities();
////
////        for (int i = 0; i < lista.size(); i++) {
////            if (Long.valueOf(lista.get(i).getPrTarifarioPK().getIdTarifario()) == id) {
////                dto = lista.get(i);
////                break;
////            }
////        }
////
////        return dto;
////
////    }
////    
    public static PrTarifario ObtenerPrTarifario(BigInteger id) {
        PrTarifarioJpaController control = new PrTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTarifario dto = new PrTarifario();
        List<PrTarifario> lista = control.findPrTarifarioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (BigInteger.valueOf(lista.get(i).getPrTarifarioPK().getIdTarifario()) == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrSubgrupos ObtenerPrSubGrupos(String nombre) {
        PrSubgruposJpaController control = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto = new PrSubgrupos();
        List<PrSubgrupos> lista = control.findPrSubgruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrSubgrupos ObtenerPrSubGrupos(int id) {
        PrSubgruposJpaController control = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto = new PrSubgrupos();
        List<PrSubgrupos> lista = control.findPrSubgruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrSubgruposPK().getIdSubgrupo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static String ObtenerPrSubGrupos(long id) {
        PrSubgruposJpaController control = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto = new PrSubgrupos();
        List<PrSubgrupos> lista = control.findPrSubgruposEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrSubgruposPK().getIdSubgrupo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto.getNombre();

    }

    public static PrTipoMedidas ObtenerPrTipoMedidas(String nombre) {
        PrTipoMedidasJpaController control = new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoMedidas dto = new PrTipoMedidas();
        List<PrTipoMedidas> lista = control.findPrTipoMedidasEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreTipoMedida().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static String ObtenerPrTipoMedidas(Long id) {
        PrTipoMedidasJpaController control = new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoMedidas dto = new PrTipoMedidas();
        List<PrTipoMedidas> lista = control.findPrTipoMedidasEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoMedidas() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto.getNombreTipoMedida();

    }

    public static PrTipoPresentacion ObtenerPrTipoPresentacion(String nombre) {
        PrTipoPresentacionJpaController control = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto = new PrTipoPresentacion();
        List<PrTipoPresentacion> lista = control.findPrTipoPresentacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static PrTipoPresentacion ObtenerPrTipoPresentacion(int id) {
        PrTipoPresentacionJpaController control = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto = new PrTipoPresentacion();
        List<PrTipoPresentacion> lista = control.findPrTipoPresentacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoPresentacion() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static String ObtenerPrTipoPresentacion(Long id) {
        PrTipoPresentacionJpaController control = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto = new PrTipoPresentacion();
        List<PrTipoPresentacion> lista = control.findPrTipoPresentacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoPresentacion() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto.getNombre();

    }

    public static SeRoles ObtenerSeRoles(String nombre) {
        SeRolesJpaController control = new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeRoles dto = new SeRoles();
        List<SeRoles> lista = control.findSeRolesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeRoles ObtenerSeRoles(int id) {
        SeRolesJpaController control = new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeRoles dto = new SeRoles();
        List<SeRoles> lista = control.findSeRolesEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdRoles() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeTipoPersona ObtenerSeTipoPersona(String nombre) {
        SeTipoPersonaJpaController control = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoPersona dto = new SeTipoPersona();
        List<SeTipoPersona> lista = control.findSeTipoPersonaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeTipoPersona ObtenerSeTipoPersona(int id) {
        SeTipoPersonaJpaController control = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoPersona dto = new SeTipoPersona();
        List<SeTipoPersona> lista = control.findSeTipoPersonaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoPersona() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static VeUnidadServicio ObtenerVeUnidadServicio(String nombre) {
        VeUnidadServicioJpaController control = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto = new VeUnidadServicio();
        List<VeUnidadServicio> lista = control.findVeUnidadServicioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreUnidadServicio().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InPrestacionesPorServicios ObtenerInPrestacionesPorServicios(String nombre) {
        InPrestacionesPorServiciosJpaController control = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
        InPrestacionesPorServicios dto = new InPrestacionesPorServicios();
        List<InPrestacionesPorServicios> lista = control.findInPrestacionesPorServiciosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static VeUnidadServicio ObtenerVeUnidadServiciON(BigInteger id) {
        VeUnidadServicioJpaController control = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto = new VeUnidadServicio();
        List<VeUnidadServicio> lista = control.findVeUnidadServicioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (BigInteger.valueOf(lista.get(i).getIdUnidadServicio()) == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static VeUnidadServicio ObtenerVeUnidadServicio(int id) {
        VeUnidadServicioJpaController control = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto = new VeUnidadServicio();
        List<VeUnidadServicio> lista = control.findVeUnidadServicioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdUnidadServicio() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static SeSucursal ObtenerSeSucursal(String objeto) {
        SeSucursalJpaController control = new SeSucursalJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeSucursal dto = new SeSucursal();
        List<SeSucursal> lista = control.findSeSucursalEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreComercial().equals(objeto)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static SeSucursal ObtenerSeSucursalL(Long id) {
        SeSucursalJpaController control = new SeSucursalJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeSucursal dto = new SeSucursal();
        List<SeSucursal> lista = control.findSeSucursalEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getSeSucursalPK().getIdSucursal() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static SeEmpresa ObtenerSeEmpresa(String objeto) {
        SeEmpresaJpaController control = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeEmpresa dto = new SeEmpresa();
        List<SeEmpresa> lista = control.findSeEmpresaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreComercial().equals(objeto)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static SeEmpresa ObtenerSeEmpresai(int id) {
        SeEmpresaJpaController control = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeEmpresa dto = new SeEmpresa();
        List<SeEmpresa> lista = control.findSeEmpresaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdEmpresa() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static SeEmpresa ObtenerSeEmpresaL(Long id) {
        SeEmpresaJpaController control = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeEmpresa dto = new SeEmpresa();
        List<SeEmpresa> lista = control.findSeEmpresaEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdEmpresa() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static PrMedidas ObtenerPrMedidas(PrMedidasPK objeto) {
        PrMedidasJpaController control = new PrMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrMedidas dto = new PrMedidas();
        List<PrMedidas> lista = control.findPrMedidasEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrMedidasPK().getIdArticulo() == objeto.getIdArticulo()) {
                if (lista.get(i).getPrMedidasPK().getIdGrupo() == objeto.getIdGrupo()) {
                    if (lista.get(i).getPrMedidasPK().getIdSubgrupo() == objeto.getIdSubgrupo()) {
                        if (lista.get(i).getPrMedidasPK().getIdTipoMedidas() == objeto.getIdTipoMedidas()) {
                            if (lista.get(i).getPrMedidasPK().getIdTipoPresentacion() == objeto.getIdTipoPresentacion()) {
                                dto = lista.get(i);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dto;
    }

    public static PrFabricante ObtenerPrFabricante(String nombre) {
        PrFabricanteJpaController control = new PrFabricanteJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrFabricante dto = new PrFabricante();
        List<PrFabricante> lista = control.findPrFabricanteEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

//    public static CoCotizacionesPorProveedor ObtenerCoCotizacionesPorProveedor(String id) {
//        CoCotizacionesPorProveedorJpaController control = new CoCotizacionesPorProveedorJpaController(EntityManagerUtil.ObtenerEntityManager());
//        CoCotizacionesPorProveedor dto = new CoCotizacionesPorProveedor();
//        List<CoCotizacionesPorProveedor> lista = control.findCoCotizacionesPorProveedorEntities();
//
//        for (int i = 0; i < lista.size(); i++) {
////            if (String.valueOf(lista.get(i).getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor()).equals(id)) {
//                dto = lista.get(i);
//                break;
//            }
//        }
//
//        return dto;
//
//    }
    public static PrProductos ObtenerPrProductos(Long id) {
        PrProductosJpaController control = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrProductos dto = new PrProductos();
        List<PrProductos> lista = control.findPrProductosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrProductosPK().getIdProducto() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static CoProveedores ObtenerCoProveedores(Long id) {
        CoProveedoresJpaController control = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoProveedores dto = new CoProveedores();
        List<CoProveedores> lista = control.findCoProveedoresEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProveedor() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static CoProveedores ObtenerCoProveedores(int id) {
        CoProveedoresJpaController control = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoProveedores dto = new CoProveedores();
        List<CoProveedores> lista = control.findCoProveedoresEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProveedor() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static CoItemsCotizacion ObtenerCoItemsCotizacion(String id) {
        CoItemsCotizacionJpaController control = new CoItemsCotizacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoItemsCotizacion dto = new CoItemsCotizacion();
        List<CoItemsCotizacion> lista = control.findCoItemsCotizacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getCoItemsCotizacionPK().getIdCotizacion()).equals(id)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

//    public static CoItemsCotizacion ObtenerCoItemsCotizacion(Long id) {
//        CoItemsCotizacionJpaController control = new CoItemsCotizacionJpaController(EntityManagerUtil.ObtenerEntityManager());
//        CoItemsCotizacion dto = new CoItemsCotizacion();
//        List<CoItemsCotizacion> lista = control.findCoItemsCotizacionEntities();
//
//        for (int i = 0; i < lista.size(); i++) {
//            if (lista.get(i).get == id) {
//                dto = lista.get(i);
//                break;
//            }
//        }
//        return dto;
//    }
    ////////////////
    public static SeTipoIdentificacion ObtenerSeTipoIdentificacion(Long id) {
        SeTipoIdentificacionJpaController control = new SeTipoIdentificacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoIdentificacion dto = new SeTipoIdentificacion();
        List<SeTipoIdentificacion> lista = control.findSeTipoIdentificacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoIdentificacion() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static SeTipoIdentificacion ObtenerSeTipoIdentificacion(String nombre) {
        SeTipoIdentificacionJpaController control = new SeTipoIdentificacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoIdentificacion dto = new SeTipoIdentificacion();
        List<SeTipoIdentificacion> lista = control.findSeTipoIdentificacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreIdentificacion().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;
    }

    public static CoOrdenPedido ObtenerCoOrdenPedido(Long id) {
        CoOrdenPedidoJpaController control = new CoOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoOrdenPedido dto = new CoOrdenPedido();
        List<CoOrdenPedido> lista = control.findCoOrdenPedidoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCoOrdenPedidoPK().getIdOrdenPedido() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;
    }

    public static PrTipoPrestacion ObtenerPrTipoPrestacion(String nombre) {

        PrTipoPrestacionJpaController control = new PrTipoPrestacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPrestacion dto = new PrTipoPrestacion();
        List<PrTipoPrestacion> lista = control.findPrTipoPrestacionEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InMotivos ObtenerInMotivos(String nombre) {
        InMotivosJpaController control = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
        InMotivos dto = new InMotivos();
        List<InMotivos> lista = control.findInMotivosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InMotivos ObtenerInMotivos(int id) {
        InMotivosJpaController control = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
        InMotivos dto = new InMotivos();
        List<InMotivos> lista = control.findInMotivosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdMotivo() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InEstadosMovimiento ObtenerInEstadosMovimiento(String nombre) {
        InEstadosMovimientoJpaController control = new InEstadosMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InEstadosMovimiento dto = new InEstadosMovimiento();
        List<InEstadosMovimiento> lista = control.findInEstadosMovimientoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static VeUnidadServicio ObtenerVeUniddadservicio(String nombre) {
        VeUnidadServicioJpaController control = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto = new VeUnidadServicio();
        List<VeUnidadServicio> lista = control.findVeUnidadServicioEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreUnidadServicio().equals(nombre)) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static InEstadosMovimiento ObtenerInEstadosMovimiento(int id) {
        InEstadosMovimientoJpaController control = new InEstadosMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InEstadosMovimiento dto = new InEstadosMovimiento();
        List<InEstadosMovimiento> lista = control.findInEstadosMovimientoEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdEstadoMovimiento() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }

    public static VeCaja ObtenerVeCaja(Long id) {
        VeCajaJpaController control = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeCaja dto = new VeCaja();
        List<VeCaja> lista = control.findVeCajaEntities();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdCaja() == id) {
                dto = lista.get(i);
                break;
            }
        }
        return dto;

    }

    public static SeUsuarios ObtenerSeUsuarios(int id) {
        SeUsuariosJpaController control = new SeUsuariosJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeUsuarios dto = new SeUsuarios();
        List<SeUsuarios> lista = control.findSeUsuariosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdUsuario() == id) {
                dto = lista.get(i);
                break;
            }
        }

        return dto;

    }
}
