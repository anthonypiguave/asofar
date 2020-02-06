/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.facturacion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin1
 */
@XmlRootElement
public class FacturaOrden {

    private List<Factura> facturaCabecera;
    private List<Factura> facturaDetalle;


    public FacturaOrden() {
    }

    public List<Factura> getFacturaCabecera() {
        return facturaCabecera;
    }
    
    @XmlElement(name = "facturaCabecera")
    public void setFacturaCabecera(List<Factura> facturaCabecera) {
        this.facturaCabecera = facturaCabecera;
    }

    public List<Factura> getFacturaDetalle() {
        return facturaDetalle;
    }
    @XmlElementWrapper(name = "facturaDetalle")
    @XmlElement(name = "detalle")
    public void setFacturaDetalle(List<Factura> facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
    }

   
}
