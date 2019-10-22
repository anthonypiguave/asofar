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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nuevouser
 */
@Entity
@Table(name = "co_detalle_orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleOrdenCompra.findAll", query = "SELECT c FROM CoDetalleOrdenCompra c")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdDetalleOrdenCompra", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.idDetalleOrdenCompra = :idDetalleOrdenCompra")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdOrdenCompra", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdEmpresa", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdSurcusal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.idSurcusal = :idSurcusal")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByLineaDetalle", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdProducto", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.coDetalleOrdenCompraPK.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByDescripcion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByPrecioUnitario", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByCantidadRecibida", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.cantidadRecibida = :cantidadRecibida")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findBySubtotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.subtotal = :subtotal")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIva", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.iva = :iva")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIce", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.ice = :ice")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByDescuento", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.descuento = :descuento")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByTotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.total = :total")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByLoteFabricacion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.loteFabricacion = :loteFabricacion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByFechaCaducidad", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.fechaCaducidad = :fechaCaducidad")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByEstado", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByUsuarioCreacion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByFechaCreacion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByUsuarioActualizacion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByFechaActualizacion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CoDetalleOrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetalleOrdenCompraPK coDetalleOrdenCompraPK;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "cantidad_recibida")
    private BigInteger cantidadRecibida;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "ice")
    private BigDecimal ice;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "lote_fabricacion")
    private String loteFabricacion;
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Column(name = "estado")
    private String estado;
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
        @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_surcusal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CoOrdenCompras coOrdenCompras;

    public CoDetalleOrdenCompra() {
    }

    public CoDetalleOrdenCompra(CoDetalleOrdenCompraPK coDetalleOrdenCompraPK) {
        this.coDetalleOrdenCompraPK = coDetalleOrdenCompraPK;
    }

    public CoDetalleOrdenCompra(long idDetalleOrdenCompra, long idOrdenCompra, long idEmpresa, long idSurcusal, long lineaDetalle, long idProducto) {
        this.coDetalleOrdenCompraPK = new CoDetalleOrdenCompraPK(idDetalleOrdenCompra, idOrdenCompra, idEmpresa, idSurcusal, lineaDetalle, idProducto);
    }

    public CoDetalleOrdenCompraPK getCoDetalleOrdenCompraPK() {
        return coDetalleOrdenCompraPK;
    }

    public void setCoDetalleOrdenCompraPK(CoDetalleOrdenCompraPK coDetalleOrdenCompraPK) {
        this.coDetalleOrdenCompraPK = coDetalleOrdenCompraPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigInteger getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(BigInteger cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getIce() {
        return ice;
    }

    public void setIce(BigDecimal ice) {
        this.ice = ice;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getLoteFabricacion() {
        return loteFabricacion;
    }

    public void setLoteFabricacion(String loteFabricacion) {
        this.loteFabricacion = loteFabricacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public CoOrdenCompras getCoOrdenCompras() {
        return coOrdenCompras;
    }

    public void setCoOrdenCompras(CoOrdenCompras coOrdenCompras) {
        this.coOrdenCompras = coOrdenCompras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coDetalleOrdenCompraPK != null ? coDetalleOrdenCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenCompra)) {
            return false;
        }
        CoDetalleOrdenCompra other = (CoDetalleOrdenCompra) object;
        if ((this.coDetalleOrdenCompraPK == null && other.coDetalleOrdenCompraPK != null) || (this.coDetalleOrdenCompraPK != null && !this.coDetalleOrdenCompraPK.equals(other.coDetalleOrdenCompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenCompra[ coDetalleOrdenCompraPK=" + coDetalleOrdenCompraPK + " ]";
    }
    
}
