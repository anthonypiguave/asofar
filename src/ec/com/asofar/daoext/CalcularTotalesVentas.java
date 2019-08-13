/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.math.BigDecimal;

/**
 *
 * @author admin1
 */
public class CalcularTotalesVentas {

    private BigDecimal SubtotalConIva;
    private BigDecimal SubtotalSinIva;
    private BigDecimal Subtotal;
    private BigDecimal Iva;
    private BigDecimal Descuento;
    private BigDecimal Total;
    private BigDecimal Utilidad;

    public CalcularTotalesVentas() {
    }
    
    
    

    public CalcularTotalesVentas(BigDecimal SubtotalConIva, BigDecimal SubtotalSinIva, BigDecimal Subtotal, BigDecimal Iva, BigDecimal Descuento, BigDecimal Total, BigDecimal Utilidad) {
        this.SubtotalConIva = SubtotalConIva;
        this.SubtotalSinIva = SubtotalSinIva;
        this.Subtotal = Subtotal;
        this.Iva = Iva;
        this.Descuento = Descuento;
        this.Total = Total;
        this.Utilidad = Utilidad;
    }

    public BigDecimal getSubtotalConIva() {
        return SubtotalConIva;
    }

    public void setSubtotalConIva(BigDecimal SubtotalConIva) {
        this.SubtotalConIva = SubtotalConIva;
    }

    public BigDecimal getSubtotalSinIva() {
        return SubtotalSinIva;
    }

    public void setSubtotalSinIva(BigDecimal SubtotalSinIva) {
        this.SubtotalSinIva = SubtotalSinIva;
    }

    public BigDecimal getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(BigDecimal Subtotal) {
        this.Subtotal = Subtotal;
    }

    public BigDecimal getIva() {
        return Iva;
    }

    public void setIva(BigDecimal Iva) {
        this.Iva = Iva;
    }

    public BigDecimal getDescuento() {
        return Descuento;
    }

    public void setDescuento(BigDecimal Descuento) {
        this.Descuento = Descuento;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public BigDecimal getUtilidad() {
        return Utilidad;
    }

    public void setUtilidad(BigDecimal Utilidad) {
        this.Utilidad = Utilidad;
    }
    
    
    

}
