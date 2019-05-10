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
import ec.com.asofar.dto.*;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ObtenerDTO {
    
    public static InBodega ObtenerInBodega(String nombre){
        InBodegaJpaController control=new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InBodega dto=new InBodega();
        List<InBodega> lista=control.findInBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreBodega().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
          public static SeUsuarios ObtenerUsuarios(String id){
        SeUsuariosJpaController control=new SeUsuariosJpaController(EntityManagerUtil.ObtenerEntityManager());
       SeUsuarios dto=new SeUsuarios();
        List<SeUsuarios> lista=control.findSeUsuariosEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdUsuario()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    
       public static InBodega ObtenerInBodega(int id){
        InBodegaJpaController control=new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InBodega dto=new InBodega();
        List<InBodega> lista=control.findInBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getInBodegaPK().getIdBodega()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    
    public static InTipoBodega ObtenerInTipoBodega(String nombre){
        InTipoBodegaJpaController control=new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoBodega dto=new InTipoBodega();
        List<InTipoBodega> lista=control.findInTipoBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static InTipoBodega ObtenerInTipoBodega(Long id){
        InTipoBodegaJpaController control=new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoBodega dto=new InTipoBodega();
        List<InTipoBodega> lista=control.findInTipoBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdTipoBodega()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static InTipoDocumento ObtenerInTipoDocumento(String nombre){
        InTipoDocumentoJpaController control=new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto=new InTipoDocumento();
        List<InTipoDocumento> lista=control.findInTipoDocumentoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreDocumento().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static InTipoDocumento ObtenerInTipoDocumento(int id){
        InTipoDocumentoJpaController control=new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto=new InTipoDocumento();
        List<InTipoDocumento> lista=control.findInTipoDocumentoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdTipoDocumento()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static InTipoMovimiento ObtenerInTipoMovimiento(String nombre){
        InTipoMovimientoJpaController control=new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoMovimiento dto=new InTipoMovimiento();
        List<InTipoMovimiento> lista=control.findInTipoMovimientoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreMovimiento().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static InTipoMovimiento ObtenerInTipoMovimiento(int id){
        InTipoMovimientoJpaController control=new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoMovimiento dto=new InTipoMovimiento();
        List<InTipoMovimiento> lista=control.findInTipoMovimientoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdTipoMovimiento()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrArticulo ObtenerPrArticulo(String nombre){
        PrArticuloJpaController control=new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto=new PrArticulo();
        List<PrArticulo> lista=control.findPrArticuloEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreArticulo().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrArticulo ObtenerPrArticulo(int id){
        PrArticuloJpaController control=new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto=new PrArticulo();
        List<PrArticulo> lista=control.findPrArticuloEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getPrArticuloPK().getIdArticulo()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrGrupos ObtenerPrGrupos(String nombre){
        PrGruposJpaController control=new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto=new PrGrupos();
        List<PrGrupos> lista=control.findPrGruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrGrupos ObtenerPrGrupos(int id){
        PrGruposJpaController control=new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto=new PrGrupos();
        List<PrGrupos> lista=control.findPrGruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdGrupo()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrPrestaciones ObtenerPrPrestaciones(String nombre){
        PrPrestacionesJpaController control=new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto=new PrPrestaciones();
        List<PrPrestaciones> lista=control.findPrPrestacionesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombrePrestacion().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrPrestaciones ObtenerPrPrestaciones(int id){
        PrPrestacionesJpaController control=new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto=new PrPrestaciones();
        List<PrPrestaciones> lista=control.findPrPrestacionesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getPrPrestacionesPK().getIdPrestacion()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
     public static PrSubgrupos ObtenerPrSubGrupos(String nombre){
        PrSubgruposJpaController control=new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto=new PrSubgrupos();
        List<PrSubgrupos> lista=control.findPrSubgruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrSubgrupos ObtenerPrSubGrupos(int id){
        PrSubgruposJpaController control=new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto=new PrSubgrupos();
        List<PrSubgrupos> lista=control.findPrSubgruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getPrSubgruposPK().getIdSubgrupo()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrTipoMedidas ObtenerPrTipoMedidas(String nombre){
        PrTipoMedidasJpaController control=new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoMedidas dto=new PrTipoMedidas();
        List<PrTipoMedidas> lista=control.findPrTipoMedidasEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreTipoMedida().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrTipoPresentacion ObtenerPrTipoPresentacion(String nombre){
        PrTipoPresentacionJpaController control=new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto=new PrTipoPresentacion();
        List<PrTipoPresentacion> lista=control.findPrTipoPresentacionEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static PrTipoPresentacion ObtenerPrTipoPresentacion(int id){
        PrTipoPresentacionJpaController control=new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto=new PrTipoPresentacion();
        List<PrTipoPresentacion> lista=control.findPrTipoPresentacionEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdTipoPresentacion()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static SeRoles ObtenerSeRoles(String nombre){
        SeRolesJpaController control=new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeRoles dto=new SeRoles();
        List<SeRoles> lista=control.findSeRolesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static SeRoles ObtenerSeRoles(int id){
        SeRolesJpaController control=new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeRoles dto=new SeRoles();
        List<SeRoles> lista=control.findSeRolesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdRoles()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
public static SeTipoPersona ObtenerSeTipoPersona(String nombre){
        SeTipoPersonaJpaController control=new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoPersona dto=new SeTipoPersona();
        List<SeTipoPersona> lista=control.findSeTipoPersonaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
public static SeTipoPersona ObtenerSeTipoPersona(int id){
        SeTipoPersonaJpaController control=new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoPersona dto=new SeTipoPersona();
        List<SeTipoPersona> lista=control.findSeTipoPersonaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdTipoPersona()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static VeUnidadServicio ObtenerVeUnidadServicio(String nombre){
        VeUnidadServicioJpaController control=new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto=new VeUnidadServicio();
        List<VeUnidadServicio> lista=control.findVeUnidadServicioEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreUnidadServicio().equals(nombre)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
    public static VeUnidadServicio ObtenerVeUnidadServicio(int id){
        VeUnidadServicioJpaController control=new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto=new VeUnidadServicio();
        List<VeUnidadServicio> lista=control.findVeUnidadServicioEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdUnidadServicio()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    
    
    }
       public static SeSucursal ObtenerSeSucursal(String objeto) {
           SeSucursalJpaController control=new SeSucursalJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeSucursal dto=new SeSucursal();
        List<SeSucursal> lista=control.findSeSucursalEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreComercial().equals(objeto)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    }

    public static SeEmpresa ObtenerSeEmpresa(String objeto) {
           SeEmpresaJpaController control=new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeEmpresa dto=new SeEmpresa();
        List<SeEmpresa> lista=control.findSeEmpresaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreComercial().equals(objeto)){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    }
     public static SeEmpresa ObtenerSeEmpresa(int id) {
           SeEmpresaJpaController control=new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeEmpresa dto=new SeEmpresa();
        List<SeEmpresa> lista=control.findSeEmpresaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getIdEmpresa()==id){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    }
     public static PrMedidas ObtenerPrMedidas(PrMedidasPK objeto) {
           PrMedidasJpaController control=new PrMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrMedidas dto=new PrMedidas();
        List<PrMedidas> lista=control.findPrMedidasEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getPrMedidasPK()==objeto){
            dto=lista.get(i);
            break;
            }
        }
        
        return dto;
    }
}
