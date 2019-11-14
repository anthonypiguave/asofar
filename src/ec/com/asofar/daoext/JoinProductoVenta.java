/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

/**
 *
 * @author admin1
 */
public class JoinProductoVenta {

    public Long id_kardex;
    public Long id_producto;
    public Long id_bodega;
    public Long id_unidad_servicio;
    public Long id_prestacion;
    public String nombre_producto;
    public Integer saldo_actual;
    public Double valor_venta;
    public Double valor_descuento;
    public String aplica_iva;
    public String codigoBarra;
    public Double costo;

    public JoinProductoVenta() {
    }

    public Long getId_kardex() {
        return id_kardex;
    }

    public void setId_kardex(Long id_kardex) {
        this.id_kardex = id_kardex;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public Long getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Long id_bodega) {
        this.id_bodega = id_bodega;
    }

    public Long getId_unidad_servicio() {
        return id_unidad_servicio;
    }

    public void setId_unidad_servicio(Long id_unidad_servicio) {
        this.id_unidad_servicio = id_unidad_servicio;
    }

    public Long getId_prestacion() {
        return id_prestacion;
    }

    public void setId_prestacion(Long id_prestacion) {
        this.id_prestacion = id_prestacion;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Integer getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(Integer saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public Double getValor_venta() {
        return valor_venta;
    }

    public void setValor_venta(Double valor_venta) {
        this.valor_venta = valor_venta;
    }

    public Double getValor_descuento() {
        return valor_descuento;
    }

    public void setValor_descuento(Double valor_descuento) {
        this.valor_descuento = valor_descuento;
    }

    public String getAplica_iva() {
        return aplica_iva;
    }

    public void setAplica_iva(String aplica_iva) {
        this.aplica_iva = aplica_iva;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }


}
