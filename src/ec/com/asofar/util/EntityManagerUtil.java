/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author admin1
 */
public class EntityManagerUtil {

    public static EntityManagerFactory ObtenerEntityManager() {

        EntityManagerFactory emf = null;

        emf = Persistence.createEntityManagerFactory("asofarPU");

        return emf;

    }

    public static boolean ObtenerEntityManagerConnection() {

        EntityManagerFactory emf = ObtenerEntityManager();

//        emf = Persistence.createEntityManagerFactory("asofarPU");
        return emf.isOpen();

    }

}
