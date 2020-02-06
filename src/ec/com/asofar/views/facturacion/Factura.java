/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.facturacion;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author admin1
 */
@XmlType( propOrder= {"idFactura", "empresa", "sucursal", "fecha", "formaPago", "numeroEstablecimientoSri", "puntoEmisionSri", "despachado", "caja", "usuario", "clienteID","clienteNombreApellido", "clienteTelefono", "clienteCorreo", "clienteDireccion", "totalSubtotal", "totalDescuento", "totalBaseIva", "totalBaseNoIva", "totalIva", "totalFacturado", "idFacturaDetalle", "lineaDetalle", "idProducto", "descripcion", "cantidad", "precioUnitario", "subtotal", "valorIva", "valorDescuento", "valorTotal", "usuarioCreacion", "fechaCreacion", "usuarioActualizacion", "fechaActualizacion"})

public class Factura {

    private Long idFactura;
    private String empresa;
    private String sucursal;
    private Date fecha;

    private String formaPago;
    private String numeroEstablecimientoSri;
    private String puntoEmisionSri;
    private String despachado;

    private String caja;
    private String usuario;

    private String clienteID;
    private String clienteNombreApellido;
    private String clienteTelefono;
    private String clienteCorreo;
    private String clienteDireccion;

    private Double totalSubtotal;
    private Double totalDescuento;
    private Double totalBaseIva;
    private Double totalBaseNoIva;
    private Double totalIva;
    private Double totalFacturado;

    private Long idFacturaDetalle;
    private Long lineaDetalle;
    private Long idProducto;
    private String descripcion;
    private Long cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double valorIva;
    private Double valorDescuento;
    private Double valorTotal;

    private String usuarioCreacion;
    private Date fechaCreacion;
    private String usuarioActualizacion;
    private Date fechaActualizacion;

    public Factura() {

    }
     

    public Long getIdFactura() {
        return idFactura;
    }
    @XmlAttribute(name = "IdFactura")
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getNumeroEstablecimientoSri() {
        return numeroEstablecimientoSri;
    }

    public void setNumeroEstablecimientoSri(String numeroEstablecimientoSri) {
        this.numeroEstablecimientoSri = numeroEstablecimientoSri;
    }

    public String getPuntoEmisionSri() {
        return puntoEmisionSri;
    }

    public void setPuntoEmisionSri(String puntoEmisionSri) {
        this.puntoEmisionSri = puntoEmisionSri;
    }

    public String getDespachado() {
        return despachado;
    }

    public void setDespachado(String despachado) {
        this.despachado = despachado;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getClienteNombreApellido() {
        return clienteNombreApellido;
    }

    public void setClienteNombreApellido(String clienteNombreApellido) {
        this.clienteNombreApellido = clienteNombreApellido;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public String getClienteCorreo() {
        return clienteCorreo;
    }

    public void setClienteCorreo(String clienteCorreo) {
        this.clienteCorreo = clienteCorreo;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public Double getTotalSubtotal() {
        return totalSubtotal;
    }

    public void setTotalSubtotal(Double totalSubtotal) {
        this.totalSubtotal = totalSubtotal;
    }

    public Double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(Double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public Double getTotalBaseIva() {
        return totalBaseIva;
    }

    public void setTotalBaseIva(Double totalBaseIva) {
        this.totalBaseIva = totalBaseIva;
    }

    public Double getTotalBaseNoIva() {
        return totalBaseNoIva;
    }

    public void setTotalBaseNoIva(Double totalBaseNoIva) {
        this.totalBaseNoIva = totalBaseNoIva;
    }

    public Double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public Double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(Double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public Long getIdFacturaDetalle() {
        return idFacturaDetalle;
    }
    @XmlAttribute(name = "IdFacturaDetalle")
    public void setIdFacturaDetalle(Long idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public Long getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(Long lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getValorIva() {
        return valorIva;
    }

    public void setValorIva(Double valorIva) {
        this.valorIva = valorIva;
    }

    public Double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    
    
    

}
