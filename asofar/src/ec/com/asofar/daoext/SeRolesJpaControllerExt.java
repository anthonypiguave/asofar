/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class SeRolesJpaControllerExt extends ec.com.asofar.dao.SeRolesJpaController{
    
    public SeRolesJpaControllerExt(EntityManagerFactory emf) {
        super(emf);
    }
    
   
    
}
