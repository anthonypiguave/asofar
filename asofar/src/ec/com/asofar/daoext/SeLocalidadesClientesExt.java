/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author admin1
 */
public class SeLocalidadesClientesExt extends SeLocalidadClienteJpaController{
    
    public SeLocalidadesClientesExt(EntityManagerFactory emf) {
        super(emf);
    }
    
    
    
    public void editar (){
    
    }
    
}
