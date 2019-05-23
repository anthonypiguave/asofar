/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "co_orden_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoOrdenPedido.findAll", query = "SELECT c FROM CoOrdenPedido c"),
    @NamedQuery(name = "CoOrdenPedido.findByIdOrdenPedido", query = "SELECT c FROM CoOrdenPedido c WHERE c.coOrdenPedidoPK.idOrdenPedido = :idOrdenPedido"),
    @NamedQuery(name = "CoOrdenPedido.findByIdEmpresa", query = "SELECT c FROM CoOrdenPedido c WHERE c.coOrdenPedidoPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "CoOrdenPedido.findByIdSucursal", query = "SELECT c FROM CoOrdenPedido c WHERE c.coOrdenPedidoPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "CoOrdenPedido.findByCodigoCotizacionProveedores", query = "SELECT c FROM CoOrdenPedido c WHERE c.codigoCotizacionProveedores = :codigoCotizacionProveedores"),
    @NamedQuery(name = "CoOrdenPedido.findByCodigoCotizacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.codigoCotizacion = :codigoCotizacion"),
    @NamedQuery(name = "CoOrdenPedido.findByIdProveedor", query = "SELECT c FROM CoOrdenPedido c WHERE c.idProveedor = :idProveedor"),
    @NamedQuery(name = "CoOrdenPedido.findByEstado", query = "SELECT c FROM CoOrdenPedido c WHERE c.estado = :estado"),
    @NamedQuery(name = "CoOrdenPedido.findByIdDocumento", query = "SELECT c FROM CoOrdenPedido c WHERE c.idDocumento = :idDocumento"),
    @NamedQuery(name = "CoOrdenPedido.findByObservacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CoOrdenPedido.findByFechaEntrega", query = "SELECT c FROM CoOrdenPedido c WHERE c.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "CoOrdenPedido.findByTotalCompra", query = "SELECT c FROM CoOrdenPedido c WHERE c.totalCompra = :totalCompra"),
    @NamedQuery(name = "CoOrdenPedido.findBySubtotal", query = "SELECT c FROM CoOrdenPedido c WHERE c.subtotal = :subtotal"),
    @NamedQuery(name = "CoOrdenPedido.findByTotalDescuento", query = "SELECT c FROM CoOrdenPedido c WHERE c.totalDescuento = :totalDescuento"),
    @NamedQuery(name = "CoOrdenPedido.findByTotalIce", query = "SELECT c FROM CoOrdenPedido c WHERE c.totalIce = :totalIce"),
    @NamedQuery(name = "CoOrdenPedido.findByTotalIva", query = "SELECT c FROM CoOrdenPedido c WHERE c.totalIva = :totalIva"),
    @NamedQuery(name = "CoOrdenPedido.findByMailNotificador", query = "SELECT c FROM CoOrdenPedido c WHERE c.mailNotificador = :mailNotificador"),
    @NamedQuery(name = "CoOrdenPedido.findByUsuarioCreacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "CoOrdenPedido.findByFechaCreacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CoOrdenPedido.findByUsuarioActualizacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "CoOrdenPedido.findByFechaActualizacion", query = "SELECT c FROM CoOrdenPedido c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CoOrdenPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoOrdenPedidoPK coOrdenPedidoPK;
    @Column(name = "codigo_cotizacion_proveedores")
    private BigInteger codigoCotizacionProveedores;
    @Column(name = "codigo_cotizacion")
    private BigInteger codigoCotizacion;
    @Column(name = "id_proveedor")
    private BigInteger idProveedor;
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_documento")
    private BigInteger idDocumento;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_compra")
    private BigDecimal totalCompra;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Column(name = "total_descuento")
    private BigDecimal totalDescuento;
    @Column(name = "total_ice")
    private BigDecimal totalIce;
    @Column(name = "total_iva")
    private BigDecimal totalIva;
    @Column(name = "mail_notificador")
    private String mailNotificador;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @OneToMany(mappedBy = "coOrdenPedido")
    private List<CoDetalleOrdenPedido> coDetalleOrdenPedidoList;

    public CoOrdenPedido() {
    }

    public CoOrdenPedido(CoOrdenPedidoPK coOrdenPedidoPK) {
        this.coOrdenPedidoPK = coOrdenPedidoPK;
    }

    public CoOrdenPedido(long idOrdenPedido, long idEmpresa, long idSucursal) {
        this.coOrdenPedidoPK = new CoOrdenPedidoPK(idOrdenPedido, idEmpresa, idSucursal);
    }

    public CoOrdenPedidoPK getCoOrdenPedidoPK() {
        return coOrdenPedidoPK;
    }

    public void setCoOrdenPedidoPK(CoOrdenPedidoPK coOrdenPedidoPK) {
        this.coOrdenPedidoPK = coOrdenPedidoPK;
    }

    public BigInteger getCodigoCotizacionProveedores() {
        return codigoCotizacionProveedores;
    }

    public void setCodigoCotizacionProveedores(BigInteger codigoCotizacionProveedores) {
        this.codigoCotizacionProveedores = codigoCotizacionProveedores;
    }

    public BigInteger getCodigoCotizacion() {
        return codigoCotizacion;
    }

    public void setCodigoCotizacion(BigInteger codigoCotizacion) {
        this.codigoCotizacion = codigoCotizacion;
    }

    public BigInteger getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(BigInteger idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(BigInteger idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getTotalIce() {
        return totalIce;
    }

    public void setTotalIce(BigDecimal totalIce) {
        this.totalIce = totalIce;
    }

    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public String getMailNotificador() {
        return mailNotificador;
    }

    public void setMailNotificador(String mailNotificador) {
        this.mailNotificador = mailNotificador;
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

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    @XmlTransient
    public List<CoDetalleOrdenPedido> getCoDetalleOrdenPedidoList() {
        return coDetalleOrdenPedidoList;
    }

    public void setCoDetalleOrdenPedidoList(List<CoDetalleOrdenPedido> coDetalleOrdenPedidoList) {
        this.coDetalleOrdenPedidoList = coDetalleOrdenPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coOrdenPedidoPK != null ? coOrdenPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoOrdenPedido)) {
            return false;
        }
        CoOrdenPedido other = (CoOrdenPedido) object;
        if ((this.coOrdenPedidoPK == null && other.coOrdenPedidoPK != null) || (this.coOrdenPedidoPK != null && !this.coOrdenPedidoPK.equals(other.coOrdenPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoOrdenPedido[ coOrdenPedidoPK=" + coOrdenPedidoPK + " ]";
    }
    
}
