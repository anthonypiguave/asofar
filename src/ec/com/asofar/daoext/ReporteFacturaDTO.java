/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.util.Date;

public class ReporteFacturaDTO {

    public Long id_factura;
    public Long id_empresa;
    public Long id_sucursal;
    public Long id_caja;
    public String id_usuario;
    public Long id_cliente;
    public Date fecha_facturacion;
    public String numero_establecimiento_sri;
    public String punto_emision_sri;
    public String forma_pago;
    public Long secuencia_sri;
    public Double subtotal;
    public Double total_ice;
    public Double total_descuento;
    public Double total_base_iva;
    public Double total_base_no_iva;
    public Double total_iva;
    public Double total_facturado;
    public String estado;
    public String despachado;
    public String usuario_creacion;
    public Date fecha_creacion;
    public String usuario_actualizacion;
    public Date fecha_actualizacion;
    public String nombre_caja;
    public String nombre_comercial_emp;
    public String nombre_comercial_suc;

    public ReporteFacturaDTO() {
    }

    public ReporteFacturaDTO(Long id_factura, Long id_empresa, Long id_sucursal, Long id_caja, String id_usuario, Long id_cliente, Date fecha_facturacion, String numero_establecimiento_sri, String punto_emision_sri, String forma_pago, Long secuencia_sri, Double subtotal, Double total_ice, Double total_descuento, Double total_base_iva, Double total_base_no_iva, Double total_iva, Double total_facturado, String estado, String despachado, String usuario_creacion, Date fecha_creacion, String usuario_actualizacion, Date fecha_actualizacion, String nombre_caja, String nombre_comercial_emp, String nombre_comercial_suc) {
        this.id_factura = id_factura;
        this.id_empresa = id_empresa;
        this.id_sucursal = id_sucursal;
        this.id_caja = id_caja;
        this.id_usuario = id_usuario;
        this.id_cliente = id_cliente;
        this.fecha_facturacion = fecha_facturacion;
        this.numero_establecimiento_sri = numero_establecimiento_sri;
        this.punto_emision_sri = punto_emision_sri;
        this.forma_pago = forma_pago;
        this.secuencia_sri = secuencia_sri;
        this.subtotal = subtotal;
        this.total_ice = total_ice;
        this.total_descuento = total_descuento;
        this.total_base_iva = total_base_iva;
        this.total_base_no_iva = total_base_no_iva;
        this.total_iva = total_iva;
        this.total_facturado = total_facturado;
        this.estado = estado;
        this.despachado = despachado;
        this.usuario_creacion = usuario_creacion;
        this.fecha_creacion = fecha_creacion;
        this.usuario_actualizacion = usuario_actualizacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.nombre_caja = nombre_caja;
        this.nombre_comercial_emp = nombre_comercial_emp;
        this.nombre_comercial_suc = nombre_comercial_suc;
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

    public Long getId_caja() {
        return id_caja;
    }

    public void setId_caja(Long id_caja) {
        this.id_caja = id_caja;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getFecha_facturacion() {
        return fecha_facturacion;
    }

    public void setFecha_facturacion(Date fecha_facturacion) {
        this.fecha_facturacion = fecha_facturacion;
    }

    public String getNumero_establecimiento_sri() {
        return numero_establecimiento_sri;
    }

    public void setNumero_establecimiento_sri(String numero_establecimiento_sri) {
        this.numero_establecimiento_sri = numero_establecimiento_sri;
    }

    public String getPunto_emision_sri() {
        return punto_emision_sri;
    }

    public void setPunto_emision_sri(String punto_emision_sri) {
        this.punto_emision_sri = punto_emision_sri;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public Long getSecuencia_sri() {
        return secuencia_sri;
    }

    public void setSecuencia_sri(Long secuencia_sri) {
        this.secuencia_sri = secuencia_sri;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal_ice() {
        return total_ice;
    }

    public void setTotal_ice(Double total_ice) {
        this.total_ice = total_ice;
    }

    public Double getTotal_descuento() {
        return total_descuento;
    }

    public void setTotal_descuento(Double total_descuento) {
        this.total_descuento = total_descuento;
    }

    public Double getTotal_base_iva() {
        return total_base_iva;
    }

    public void setTotal_base_iva(Double total_base_iva) {
        this.total_base_iva = total_base_iva;
    }

    public Double getTotal_base_no_iva() {
        return total_base_no_iva;
    }

    public void setTotal_base_no_iva(Double total_base_no_iva) {
        this.total_base_no_iva = total_base_no_iva;
    }

    public Double getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(Double total_iva) {
        this.total_iva = total_iva;
    }

    public Double getTotal_facturado() {
        return total_facturado;
    }

    public void setTotal_facturado(Double total_facturado) {
        this.total_facturado = total_facturado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDespachado() {
        return despachado;
    }

    public void setDespachado(String despachado) {
        this.despachado = despachado;
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

    public String getNombre_caja() {
        return nombre_caja;
    }

    public void setNombre_caja(String nombre_caja) {
        this.nombre_caja = nombre_caja;
    }

    public String getNombre_comercial_emp() {
        return nombre_comercial_emp;
    }

    public void setNombre_comercial_emp(String nombre_comercial_emp) {
        this.nombre_comercial_emp = nombre_comercial_emp;
    }

    public String getNombre_comercial_suc() {
        return nombre_comercial_suc;
    }

    public void setNombre_comercial_suc(String nombre_comercial_suc) {
        this.nombre_comercial_suc = nombre_comercial_suc;
    }

}
