/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class ReporteComprasDTO1 {

    public Long id_orden_compra;
    public Long id_tipo_documento; 
    public ReporteComprasDTO1() {
    }

    public ReporteComprasDTO1(Long id_orden_compra, Long id_documen) {
        this.id_orden_compra = id_orden_compra;
        this.id_tipo_documento = id_tipo_documento;
    }

    public Long getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Long id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public Long getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(Long id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

   
}
