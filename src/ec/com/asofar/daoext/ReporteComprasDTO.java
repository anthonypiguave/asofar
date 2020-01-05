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
public class ReporteComprasDTO {

    public Long id_orden_compra;
    public Long id_empresa; 
    public Long id_sucursal;
    public Long id_proveedor;    
    public Long id_tipo_documento;
    public String observacion;
    public String forma_pago;
    public Date fecha_entrega;
    public Double total_subtotal; 
    public Double total_descuento;
    public Double total_ice;
    public Double total_iva;
    public Double total_compra;
    public Date fecha_aprobacion;
    public Long usuario_creacion;
    public Date fecha_creacion;
    public String usuario_actualizacion;
    public Date fecha_actualizacion;
    public String estado;
    public String nombre_documento;
    public String nombre_proveedor;
        //ReporteDetalleComprasDTO
    public Long id_detalle_orden_compra;
    public Long id_orden_comprad;
    public Long id_empresa2;
    public Long id_surcusal;
    public Long linea_detalle;
    public Long id_producto;
    public String descripcion;
    public Double precio_unitario;
    public Long cantidad_recibida;
    public Double subtotal;
    public Double iva;
    public Double ice;
    public Double descuento;
    public Double total;
    public String estado2;
    public Long usuario_creacion2;
    public Date fecha_creacion2;
    public String usuario_actualizacion2;
    public Date fecha_actualizacion2;

    public ReporteComprasDTO() {
    }

    public ReporteComprasDTO(Long id_orden_compra, Long id_empresa, Long id_sucursal, Long id_proveedor, Long id_tipo_documento, String observacion, String forma_pago, Date fecha_entrega, Double total_subtotal, Double total_descuento, Double total_ice, Double total_iva, Double total_compra, Date fecha_aprobacion, Long usuario_creacion, Date fecha_creacion, String usuario_actualizacion, Date fecha_actualizacion, String estado, String nombre_documento, String nombre_proveedor, Long id_detalle_orden_compra, Long id_orden_comprad, Long id_empresa2, Long id_surcusal, Long linea_detalle, Long id_producto, String descripcion, Double precio_unitario, Long cantidad_recibida, Double subtotal, Double iva, Double ice, Double descuento, Double total, String estado2, Long usuario_creacion2, Date fecha_creacion2, String usuario_actualizacion2, Date fecha_actualizacion2) {
        this.id_orden_compra = id_orden_compra;
        this.id_empresa = id_empresa;
        this.id_sucursal = id_sucursal;
        this.id_proveedor = id_proveedor;
        this.id_tipo_documento = id_tipo_documento;
        this.observacion = observacion;
        this.forma_pago = forma_pago;
        this.fecha_entrega = fecha_entrega;
        this.total_subtotal = total_subtotal;
        this.total_descuento = total_descuento;
        this.total_ice = total_ice;
        this.total_iva = total_iva;
        this.total_compra = total_compra;
        this.fecha_aprobacion = fecha_aprobacion;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
        this.usuario_actualizacion = usuario_actualizacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.estado = estado;
        this.nombre_documento = nombre_documento;
        this.nombre_proveedor = nombre_proveedor;
        this.id_detalle_orden_compra = id_detalle_orden_compra;
        this.id_orden_comprad = id_orden_comprad;
        this.id_empresa2 = id_empresa2;
        this.id_surcusal = id_surcusal;
        this.linea_detalle = linea_detalle;
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.cantidad_recibida = cantidad_recibida;
        this.subtotal = subtotal;
        this.iva = iva;
        this.ice = ice;
        this.descuento = descuento;
        this.total = total;
        this.estado2 = estado2;
        this.usuario_creacion2 = usuario_creacion2;
        this.fecha_creacion2 = fecha_creacion2;
        this.usuario_actualizacion2 = usuario_actualizacion2;
        this.fecha_actualizacion2 = fecha_actualizacion2;
    }

    public Long getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Long id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
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

    public Long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Long getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(Long id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Double getTotal_subtotal() {
        return total_subtotal;
    }

    public void setTotal_subtotal(Double total_subtotal) {
        this.total_subtotal = total_subtotal;
    }

    public Double getTotal_descuento() {
        return total_descuento;
    }

    public void setTotal_descuento(Double total_descuento) {
        this.total_descuento = total_descuento;
    }

    public Double getTotal_ice() {
        return total_ice;
    }

    public void setTotal_ice(Double total_ice) {
        this.total_ice = total_ice;
    }

    public Double getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(Double total_iva) {
        this.total_iva = total_iva;
    }

    public Double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(Double total_compra) {
        this.total_compra = total_compra;
    }

    public Date getFecha_aprobacion() {
        return fecha_aprobacion;
    }

    public void setFecha_aprobacion(Date fecha_aprobacion) {
        this.fecha_aprobacion = fecha_aprobacion;
    }

    public Long getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(Long usuario_creacion) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public Long getId_detalle_orden_compra() {
        return id_detalle_orden_compra;
    }

    public void setId_detalle_orden_compra(Long id_detalle_orden_compra) {
        this.id_detalle_orden_compra = id_detalle_orden_compra;
    }

    public Long getId_orden_comprad() {
        return id_orden_comprad;
    }

    public void setId_orden_comprad(Long id_orden_comprad) {
        this.id_orden_comprad = id_orden_comprad;
    }

    public Long getId_empresa2() {
        return id_empresa2;
    }

    public void setId_empresa2(Long id_empresa2) {
        this.id_empresa2 = id_empresa2;
    }

    public Long getId_surcusal() {
        return id_surcusal;
    }

    public void setId_surcusal(Long id_surcusal) {
        this.id_surcusal = id_surcusal;
    }

    public Long getLinea_detalle() {
        return linea_detalle;
    }

    public void setLinea_detalle(Long linea_detalle) {
        this.linea_detalle = linea_detalle;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Long getCantidad_recibida() {
        return cantidad_recibida;
    }

    public void setCantidad_recibida(Long cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getIce() {
        return ice;
    }

    public void setIce(Double ice) {
        this.ice = ice;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public Long getUsuario_creacion2() {
        return usuario_creacion2;
    }

    public void setUsuario_creacion2(Long usuario_creacion2) {
        this.usuario_creacion2 = usuario_creacion2;
    }

    public Date getFecha_creacion2() {
        return fecha_creacion2;
    }

    public void setFecha_creacion2(Date fecha_creacion2) {
        this.fecha_creacion2 = fecha_creacion2;
    }

    public String getUsuario_actualizacion2() {
        return usuario_actualizacion2;
    }

    public void setUsuario_actualizacion2(String usuario_actualizacion2) {
        this.usuario_actualizacion2 = usuario_actualizacion2;
    }

    public Date getFecha_actualizacion2() {
        return fecha_actualizacion2;
    }

    public void setFecha_actualizacion2(Date fecha_actualizacion2) {
        this.fecha_actualizacion2 = fecha_actualizacion2;
    }
        
}
