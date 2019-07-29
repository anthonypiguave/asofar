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
import javax.persistence.CascadeType;
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
 * @author admin
 */
@Entity
@Table(name = "co_orden_compras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoOrdenCompras.findAll", query = "SELECT c FROM CoOrdenCompras c")
    , @NamedQuery(name = "CoOrdenCompras.findByIdOrdenCompra", query = "SELECT c FROM CoOrdenCompras c WHERE c.coOrdenComprasPK.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "CoOrdenCompras.findByIdEmpresa", query = "SELECT c FROM CoOrdenCompras c WHERE c.coOrdenComprasPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoOrdenCompras.findByIdSucursal", query = "SELECT c FROM CoOrdenCompras c WHERE c.coOrdenComprasPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoOrdenCompras.findByIdProveedor", query = "SELECT c FROM CoOrdenCompras c WHERE c.idProveedor = :idProveedor")
    , @NamedQuery(name = "CoOrdenCompras.findByIdTipoDocumento", query = "SELECT c FROM CoOrdenCompras c WHERE c.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "CoOrdenCompras.findByObservacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.observacion = :observacion")
    , @NamedQuery(name = "CoOrdenCompras.findByFechaEntrega", query = "SELECT c FROM CoOrdenCompras c WHERE c.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "CoOrdenCompras.findBySubtotal", query = "SELECT c FROM CoOrdenCompras c WHERE c.subtotal = :subtotal")
    , @NamedQuery(name = "CoOrdenCompras.findByTotalDescuento", query = "SELECT c FROM CoOrdenCompras c WHERE c.totalDescuento = :totalDescuento")
    , @NamedQuery(name = "CoOrdenCompras.findByTotalIce", query = "SELECT c FROM CoOrdenCompras c WHERE c.totalIce = :totalIce")
    , @NamedQuery(name = "CoOrdenCompras.findByTotalIva", query = "SELECT c FROM CoOrdenCompras c WHERE c.totalIva = :totalIva")
    , @NamedQuery(name = "CoOrdenCompras.findByTotalCompra", query = "SELECT c FROM CoOrdenCompras c WHERE c.totalCompra = :totalCompra")
    , @NamedQuery(name = "CoOrdenCompras.findByFechaAprobacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.fechaAprobacion = :fechaAprobacion")
    , @NamedQuery(name = "CoOrdenCompras.findByUsuarioCreacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoOrdenCompras.findByFechaCreacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoOrdenCompras.findByUsuarioActualizacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoOrdenCompras.findByFechaActualizacion", query = "SELECT c FROM CoOrdenCompras c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "CoOrdenCompras.findByEstado", query = "SELECT c FROM CoOrdenCompras c WHERE c.estado = :estado")})
public class CoOrdenCompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoOrdenComprasPK coOrdenComprasPK;
    @Column(name = "id_proveedor")
    private BigInteger idProveedor;
    @Column(name = "id_tipo_documento")
    private BigInteger idTipoDocumento;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Column(name = "total_descuento")
    private BigDecimal totalDescuento;
    @Column(name = "total_ice")
    private BigDecimal totalIce;
    @Column(name = "total_iva")
    private BigDecimal totalIva;
    @Column(name = "total_compra")
    private BigDecimal totalCompra;
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
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
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coOrdenCompras")
    private List<CoDetalleOrdenCompra> coDetalleOrdenCompraList;

    public CoOrdenCompras() {
    }

    public CoOrdenCompras(CoOrdenComprasPK coOrdenComprasPK) {
        this.coOrdenComprasPK = coOrdenComprasPK;
    }

    public CoOrdenCompras(long idOrdenCompra, long idEmpresa, long idSucursal) {
        this.coOrdenComprasPK = new CoOrdenComprasPK(idOrdenCompra, idEmpresa, idSucursal);
    }

    public CoOrdenComprasPK getCoOrdenComprasPK() {
        return coOrdenComprasPK;
    }

    public void setCoOrdenComprasPK(CoOrdenComprasPK coOrdenComprasPK) {
        this.coOrdenComprasPK = coOrdenComprasPK;
    }

    public BigInteger getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(BigInteger idProveedor) {
        this.idProveedor = idProveedor;
    }

    public BigInteger getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigInteger idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    @XmlTransient
    public List<CoDetalleOrdenCompra> getCoDetalleOrdenCompraList() {
        return coDetalleOrdenCompraList;
    }

    public void setCoDetalleOrdenCompraList(List<CoDetalleOrdenCompra> coDetalleOrdenCompraList) {
        this.coDetalleOrdenCompraList = coDetalleOrdenCompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coOrdenComprasPK != null ? coOrdenComprasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoOrdenCompras)) {
            return false;
        }
        CoOrdenCompras other = (CoOrdenCompras) object;
        if ((this.coOrdenComprasPK == null && other.coOrdenComprasPK != null) || (this.coOrdenComprasPK != null && !this.coOrdenComprasPK.equals(other.coOrdenComprasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoOrdenCompras[ coOrdenComprasPK=" + coOrdenComprasPK + " ]";
    }
    
}
