/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.util.Date;

/**
 *
 * @author admin1
 */
public class ReporteDetalleFacturaDTO {
public Long id_factura_detalle;
public Long linea_detalle;
public Long id_factura;
public Long id_empresa;
public Long id_sucursal;
public Long id_prestaciones;
public String descripcion;
public Long id_unidad_servicio;
public Long cantidad;
public Double precio_unitario_venta;
public Double valor_ice;
public Double subtotal;
public Double valor_iva;
public Double valor_descuento;
public Double valor_total;
public String estado;
public String usuario_creacion;
public Date fecha_creacion;
public String usuario_actualizacion;
public Date fecha_actualizacion;
public Long id_producto;
    public ReporteDetalleFacturaDTO() {
    }

    public ReporteDetalleFacturaDTO(Long id_factura_detalle, Long linea_detalle, Long id_factura, Long id_empresa, Long id_sucursal, Long id_prestaciones, String descripcion, Long id_unidad_servicio, Long cantidad, Double precio_unitario_venta, Double valor_ice, Double subtotal, Double valor_iva, Double valor_descuento, Double valor_total, String estado, String usuario_creacion, Date fecha_creacion, String usuario_actualizacion, Date fecha_actualizacion, Long id_producto) {
        this.id_factura_detalle = id_factura_detalle;
        this.linea_detalle = linea_detalle;
        this.id_factura = id_factura;
        this.id_empresa = id_empresa;
        this.id_sucursal = id_sucursal;
        this.id_prestaciones = id_prestaciones;
        this.descripcion = descripcion;
        this.id_unidad_servicio = id_unidad_servicio;
        this.cantidad = cantidad;
        this.precio_unitario_venta = precio_unitario_venta;
        this.valor_ice = valor_ice;
        this.subtotal = subtotal;
        this.valor_iva = valor_iva;
        this.valor_descuento = valor_descuento;
        this.valor_total = valor_total;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
        this.usuario_actualizacion = usuario_actualizacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_producto = id_producto;
    }

    public Long getId_factura_detalle() {
        return id_factura_detalle;
    }

    public void setId_factura_detalle(Long id_factura_detalle) {
        this.id_factura_detalle = id_factura_detalle;
    }

    public Long getLinea_detalle() {
        return linea_detalle;
    }

    public void setLinea_detalle(Long linea_detalle) {
        this.linea_detalle = linea_detalle;
    }

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Long getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Long getId_prestaciones() {
        return id_prestaciones;
    }

    public void setId_prestaciones(Long id_prestaciones) {
        this.id_prestaciones = id_prestaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId_unidad_servicio() {
        return id_unidad_servicio;
    }

    public void setId_unidad_servicio(Long id_unidad_servicio) {
        this.id_unidad_servicio = id_unidad_servicio;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario_venta() {
        return precio_unitario_venta;
    }

    public void setPrecio_unitario_venta(Double precio_unitario_venta) {
        this.precio_unitario_venta = precio_unitario_venta;
    }

    public Double getValor_ice() {
        return valor_ice;
    }

    public void setValor_ice(Double valor_ice) {
        this.valor_ice = valor_ice;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getValor_iva() {
        return valor_iva;
    }

    public void setValor_iva(Double valor_iva) {
        this.valor_iva = valor_iva;
    }

    public Double getValor_descuento() {
        return valor_descuento;
    }

    public void setValor_descuento(Double valor_descuento) {
        this.valor_descuento = valor_descuento;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getUsuario_actualizacion() {
        return usuario_actualizacion;
    }

    public void setUsuario_actualizacion(String usuario_actualizacion) {
        this.usuario_actualizacion = usuario_actualizacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

}
