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
public class ReporteDetalleComprasDTO {

    public Long id_detalle_orden_compra;
    public Long id_orden_compra;
    public Long id_empresa;
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
    public String estado;
    public String usuario_creacion;
    public Date fecha_creacion;
    public String usuario_actualizacion;
    public Date fecha_actualizacion;
    public String lote_fabricacion;
    public Date fecha_caducidad;
    // INNER JOIN
    public String codigo_barra;
    public String id_tipo_presentacion;
    public String nombre_producto;
    public String receta;
    public String nombrePresentacion;

    public ReporteDetalleComprasDTO() {

    }

    public ReporteDetalleComprasDTO(Long id_detalle_orden_compra, Long id_orden_compra, Long id_empresa, Long id_surcusal, Long linea_detalle, Long id_producto, String descripcion, Double precio_unitario, Long cantidad_recibida, Double subtotal, Double iva, Double ice, Double descuento, Double total, String estado, String usuario_creacion, Date fecha_creacion, String usuario_actualizacion, Date fecha_actualizacion, String lote_fabricacion, Date fecha_caducidad, String codigo_barra, String id_tipo_presentacion, String nombre_producto, String receta, String nombrePresentacion) {
        this.id_detalle_orden_compra = id_detalle_orden_compra;
        this.id_orden_compra = id_orden_compra;
        this.id_empresa = id_empresa;
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
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
        this.usuario_actualizacion = usuario_actualizacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.lote_fabricacion = lote_fabricacion;
        this.fecha_caducidad = fecha_caducidad;
        this.codigo_barra = codigo_barra;
        this.id_tipo_presentacion = id_tipo_presentacion;
        this.nombre_producto = nombre_producto;
        this.receta = receta;
        this.nombrePresentacion = nombrePresentacion;
    }

    public Long getId_detalle_orden_compra() {
        return id_detalle_orden_compra;
    }

    public void setId_detalle_orden_compra(Long id_detalle_orden_compra) {
        this.id_detalle_orden_compra = id_detalle_orden_compra;
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

    public String getLote_fabricacion() {
        return lote_fabricacion;
    }

    public void setLote_fabricacion(String lote_fabricacion) {
        this.lote_fabricacion = lote_fabricacion;
    }

    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getId_tipo_presentacion() {
        return id_tipo_presentacion;
    }

    public void setId_tipo_presentacion(String id_tipo_presentacion) {
        this.id_tipo_presentacion = id_tipo_presentacion;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getNombrePresentacion() {
        return nombrePresentacion;
    }

    public void setNombrePresentacion(String nombrePresentacion) {
        this.nombrePresentacion = nombrePresentacion;
    }

}
