/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.facturacion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin1
 */
@XmlRootElement
public class FacturaOrden {

    private List<Factura> facturaOrdenCabecera;
    private List<Factura> facturaOrdenDetalle;
    private List<Factura> detalleProducto;

    public FacturaOrden() {
    }

    public List<Factura> getFacturaOrdenCabecera() {
        return facturaOrdenCabecera;
    }

    @XmlElement(name = "facturaOrdenCabecera")
    public void setFacturaOrdenCabecera(List<Factura> facturaOrdenCabecera) {
        this.facturaOrdenCabecera = facturaOrdenCabecera;
    }

    public List<Factura> getFacturaOrdenDetalle() {
        return facturaOrdenDetalle;
    }

    @XmlElement(name = "facturaOrdenDetalle")
    public void setFacturaOrdenDetalle(List<Factura> facturaOrdenDetalle) {
        this.facturaOrdenDetalle = facturaOrdenDetalle;
    }

    public List<Factura> getDetalleProducto() {
        return detalleProducto;
    }

    @XmlElement(name = "detalleProducto")
    public void setDetalleProducto(List<Factura> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

}
