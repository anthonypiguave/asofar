/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.SePersonasJpaController;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ADMIN
 */
public class SePersonasJpaControllerExt extends SePersonasJpaController {
    
    public SePersonasJpaControllerExt(EntityManagerFactory emf) {
        super(emf);
    }
    
}
