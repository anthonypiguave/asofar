/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dto.*;
import ec.com.asofar.dao.*;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ObtenerDTO {
    
    public InBodega ObtenerInBodega(String nombre){
        InBodegaJpaController control=new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InBodega dto=new InBodega();
        List<InBodega> lista=control.findInBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreBodega().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public InTipoBodega ObtenerInTipoBodega(String nombre){
        InTipoBodegaJpaController control=new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoBodega dto=new InTipoBodega();
        List<InTipoBodega> lista=control.findInTipoBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public InTipoDocumento ObtenerInTipoDocumento(String nombre){
        InTipoDocumentoJpaController control=new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoDocumento dto=new InTipoDocumento();
        List<InTipoDocumento> lista=control.findInTipoDocumentoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreDocumento().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public InTipoMovimiento ObtenerInTipoMovimiento(String nombre){
        InTipoMovimientoJpaController control=new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        InTipoMovimiento dto=new InTipoMovimiento();
        List<InTipoMovimiento> lista=control.findInTipoMovimientoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreMovimiento().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    
    public PrArticulo ObtenerPrArticulo(String nombre){
        PrArticuloJpaController control=new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrArticulo dto=new PrArticulo();
        List<PrArticulo> lista=control.findPrArticuloEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreArticulo().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public PrGrupos ObtenerPrGrupos(String nombre){
        PrGruposJpaController control=new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrGrupos dto=new PrGrupos();
        List<PrGrupos> lista=control.findPrGruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public PrPrestaciones ObtenerPrPrestaciones(String nombre){
        PrPrestacionesJpaController control=new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrPrestaciones dto=new PrPrestaciones();
        List<PrPrestaciones> lista=control.findPrPrestacionesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombrePrestacion().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public PrSubgrupos ObtenerPrSubGrupos(String nombre){
        PrSubgruposJpaController control=new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrSubgrupos dto=new PrSubgrupos();
        List<PrSubgrupos> lista=control.findPrSubgruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public PrTipoMedidas ObtenerPrTipoMedidas(String nombre){
        PrTipoMedidasJpaController control=new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoMedidas dto=new PrTipoMedidas();
        List<PrTipoMedidas> lista=control.findPrTipoMedidasEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreTipoMedida().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public PrTipoPresentacion ObtenerPrTipoPresentacion(String nombre){
        PrTipoPresentacionJpaController control=new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrTipoPresentacion dto=new PrTipoPresentacion();
        List<PrTipoPresentacion> lista=control.findPrTipoPresentacionEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public SeRoles ObtenerSeRoles(String nombre){
        SeRolesJpaController control=new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeRoles dto=new SeRoles();
        List<SeRoles> lista=control.findSeRolesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
public SeTipoPersona ObtenerSeTipoPersona(String nombre){
        SeTipoPersonaJpaController control=new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        SeTipoPersona dto=new SeTipoPersona();
        List<SeTipoPersona> lista=control.findSeTipoPersonaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
    public VeUnidadServicio ObtenerVeUnidadServicio(String nombre){
        VeUnidadServicioJpaController control=new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        VeUnidadServicio dto=new VeUnidadServicio();
        List<VeUnidadServicio> lista=control.findVeUnidadServicioEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreUnidadServicio().equals(nombre)){
            dto=lista.get(i);
            }
        }
        
        return dto;
    
    
    }
}
