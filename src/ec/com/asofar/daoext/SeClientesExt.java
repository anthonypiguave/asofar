/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dto.SeClientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class SeClientesExt extends SeClientesJpaController {

    public SeClientesExt(EntityManagerFactory emf) {
        super(emf);
    }

    public List<SeClientes> obtenerClienteVenta(String cedula) {
        EntityManager em = getEntityManager();
        List<SeClientes> QCliente = null;
        String nativeQuery = "SELECT  scc.* ,slc.*,sc.*,sti.nombre_identificacion\n"
                + "FROM se_contactos_clientes scc\n"
                + "JOIN se_localidad_cliente slc ON slc.id_localidad_cliente = scc.id_contactos_clientes\n"
                + "JOIN se_clientes sc ON sc.id_clientes = slc.id_cliente\n"
                + "JOIN se_tipo_identificacion sti ON sti.id_tipo_identificacion = sc.id_tipo_indentificacion\n"
                + "WHERE sc.numero_identificacion='"+cedula+"';";
        Query query = em.createNativeQuery(nativeQuery, SeClientes.class);
        QCliente = query.getResultList();

        return QCliente;
    }
}
