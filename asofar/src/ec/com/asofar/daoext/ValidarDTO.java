/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.*;
import ec.com.asofar.dto.*;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ValidarDTO {

    public boolean ValidarInBodega(String nombre){
        InBodegaJpaController control=new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<InBodega> lista=control.findInBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreBodega().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarInTipoBodega(String nombre){
        InTipoBodegaJpaController control=new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<InTipoBodega> lista=control.findInTipoBodegaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarInTipoDocumento(String nombre){
        InTipoDocumentoJpaController control=new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<InTipoDocumento> lista=control.findInTipoDocumentoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreDocumento().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarInTipoMovimiento(String nombre){
        InTipoMovimientoJpaController control=new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<InTipoMovimiento> lista=control.findInTipoMovimientoEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreMovimiento().equals(nombre)){
            valor= true;
            }
        }
        
        return valor;
    
    
    }
    
    public boolean ValidarPrArticulo(String nombre){
        PrArticuloJpaController control=new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<PrArticulo> lista=control.findPrArticuloEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreArticulo().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarPrGrupos(String nombre){
        PrGruposJpaController control=new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<PrGrupos> lista=control.findPrGruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarPrPrestaciones(String nombre){
        PrPrestacionesJpaController control=new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<PrPrestaciones> lista=control.findPrPrestacionesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombrePrestacion().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarPrSubGrupos(String nombre){
        PrSubgruposJpaController control=new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrSubgrupos> lista=control.findPrSubgruposEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor= true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarPrTipoMedidas(String nombre){
        PrTipoMedidasJpaController control=new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor = false;
        List<PrTipoMedidas> lista=control.findPrTipoMedidasEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreTipoMedida().equals(nombre)){
            valor =true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarPrTipoPresentacion(String nombre){
        PrTipoPresentacionJpaController control=new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<PrTipoPresentacion> lista=control.findPrTipoPresentacionEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarSeRoles(String nombre){
        SeRolesJpaController control=new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor= false;
        List<SeRoles> lista=control.findSeRolesEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
public boolean ValidarSeTipoPersona(String nombre){
        SeTipoPersonaJpaController control=new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor= false;
        List<SeTipoPersona> lista=control.findSeTipoPersonaEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombre().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    public boolean ValidarVeUnidadServicio(String nombre){
        VeUnidadServicioJpaController control=new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
        boolean valor=false;
        List<VeUnidadServicio> lista=control.findVeUnidadServicioEntities();
        
        for (int i = 0; i <lista.size(); i++) {
            if(lista.get(i).getNombreUnidadServicio().equals(nombre)){
            valor=true;
            }
        }
        
        return valor;
    
    
    }
    
}
