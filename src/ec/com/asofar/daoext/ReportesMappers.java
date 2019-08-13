/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ReportesMappers {
     public static ReporteComprasDTO getProductosFromResultSet(ResultSet rs) {
        ReporteComprasDTO obj = new ReporteComprasDTO();
        try {
            obj.setId_orden_compra(rs.getLong("id_orden_compra"));
            obj.setFecha_entrega(rs.getDate("fecha_entrega"));

        } catch (SQLException ex) {
            Logger.getLogger(ReportesMappers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}
