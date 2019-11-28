/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.dto.SeOpcionesMenu;
import ec.com.asofar.dto.SeUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author admin1
 */
public class SubGruposExt extends PrSubgruposJpaController {

    public SubGruposExt(EntityManagerFactory emf) {
        super(emf);
    }
    public  List<SeOpcionesMenu> ObtenerMenu(SeUsuarios user){
        
        System.out.println(" usuario id : " + user.getIdUsuario());
        
        EntityManager em = getEntityManager();
        List<SeOpcionesMenu> lista = null;
        String nativeQuery ="SELECT DISTINCT om.* "
                + "FROM se_usuarios us , se_usuario_sucur_rol usr , se_opciones_roles orl, se_opciones_menu om "
                + "WHERE usr.id_usuario = "+ user.getIdUsuario()+" and "
                + "usr.id_roles= orl.id_rol and "
                + "orl.id_opciones_menu = om.id_opciones_menu order by om.id_padre , om.orden";
        Query query = em.createNativeQuery(nativeQuery, SeOpcionesMenu.class);
//        query.setParameter("iduser", user.getIdUsuario());
        
        lista = query.getResultList();
//        "SELECT OM \n" +
//                  "FROM SeUsuarios US , SeUsuarioSucurRol USR, SeOpcionesRoles ORl, SeOpcionesMenu OM "
//                + "where USR.IdUsuario=?iduser and "
//                + "USR.IdRoles=ORl.IdRol and "
//                + "ORL.IdOpcionesMenu=OM.IdOpcionesMenu"
        

        return lista;
    }
  
}
