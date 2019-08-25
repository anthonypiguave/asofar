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
 * @author admin
 */
@Entity
@Table(name = "in_detalle_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InDetalleMovimiento.findAll", query = "SELECT i FROM InDetalleMovimiento i")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdDetalleMovimiento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idDetalleMovimiento = :idDetalleMovimiento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdMovimientos", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdTipoDocumento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdTipoMovimiento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idTipoMovimiento = :idTipoMovimiento")
    , @NamedQuery(name = "InDetalleMovimiento.findByAnioDocumento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.anioDocumento = :anioDocumento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdMotivo", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idMotivo = :idMotivo")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdEmpresa", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdSucursal", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "InDetalleMovimiento.findByLineaDetalle", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdProducto", query = "SELECT i FROM InDetalleMovimiento i WHERE i.inDetalleMovimientoPK.idProducto = :idProducto")
    , @NamedQuery(name = "InDetalleMovimiento.findByDescripcion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "InDetalleMovimiento.findByCantidad", query = "SELECT i FROM InDetalleMovimiento i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "InDetalleMovimiento.findByPrecioUnitario", query = "SELECT i FROM InDetalleMovimiento i WHERE i.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "InDetalleMovimiento.findByEstado", query = "SELECT i FROM InDetalleMovimiento i WHERE i.estado = :estado")
    , @NamedQuery(name = "InDetalleMovimiento.findByDespachado", query = "SELECT i FROM InDetalleMovimiento i WHERE i.despachado = :despachado")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdBodegaOrigen", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idBodegaOrigen = :idBodegaOrigen")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdBodegaDestino", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idBodegaDestino = :idBodegaDestino")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdSucursalDestino", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idSucursalDestino = :idSucursalDestino")
    , @NamedQuery(name = "InDetalleMovimiento.findByUsuarioCreacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByFechaCreacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByUsuarioActualizacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByFechaActualizacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InDetalleMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InDetalleMovimientoPK inDetalleMovimientoPK;
    @Column(name = "anio_documento")
    @Temporal(TemporalType.DATE)
    private Date anioDocumento;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private BigInteger cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "estado")
    private String estado;
    @Column(name = "despachado")
    private String despachado;
    @Column(name = "id_bodega_origen")
    private BigInteger idBodegaOrigen;
    @Column(name = "id_bodega_destino")
    private BigInteger idBodegaDestino;
    @Column(name = "id_sucursal_destino")
    private BigInteger idSucursalDestino;
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
        @JoinColumn(name = "id_movimientos", referencedColumnName = "id_movimientos", insertable = false, updatable = false)
        , @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
        , @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento", insertable = false, updatable = false)
        , @JoinColumn(name = "id_motivo", referencedColumnName = "id_motivo", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private InMovimientos inMovimientos;

    public InDetalleMovimiento() {
    }

    public InDetalleMovimiento(InDetalleMovimientoPK inDetalleMovimientoPK) {
        this.inDetalleMovimientoPK = inDetalleMovimientoPK;
    }

    public InDetalleMovimiento(long idDetalleMovimiento, long idMovimientos, long idTipoDocumento, long idTipoMovimiento, long idMotivo, long idEmpresa, long idSucursal, long lineaDetalle, long idProducto) {
        this.inDetalleMovimientoPK = new InDetalleMovimientoPK(idDetalleMovimiento, idMovimientos, idTipoDocumento, idTipoMovimiento, idMotivo, idEmpresa, idSucursal, lineaDetalle, idProducto);
    }

    public InDetalleMovimientoPK getInDetalleMovimientoPK() {
        return inDetalleMovimientoPK;
    }

    public void setInDetalleMovimientoPK(InDetalleMovimientoPK inDetalleMovimientoPK) {
        this.inDetalleMovimientoPK = inDetalleMovimientoPK;
    }

    public Date getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(Date anioDocumento) {
        this.anioDocumento = anioDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public BigInteger getIdBodegaOrigen() {
        return idBodegaOrigen;
    }

    public void setIdBodegaOrigen(BigInteger idBodegaOrigen) {
        this.idBodegaOrigen = idBodegaOrigen;
    }

    public BigInteger getIdBodegaDestino() {
        return idBodegaDestino;
    }

    public void setIdBodegaDestino(BigInteger idBodegaDestino) {
        this.idBodegaDestino = idBodegaDestino;
    }

    public BigInteger getIdSucursalDestino() {
        return idSucursalDestino;
    }

    public void setIdSucursalDestino(BigInteger idSucursalDestino) {
        this.idSucursalDestino = idSucursalDestino;
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

    public InMovimientos getInMovimientos() {
        return inMovimientos;
    }

    public void setInMovimientos(InMovimientos inMovimientos) {
        this.inMovimientos = inMovimientos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inDetalleMovimientoPK != null ? inDetalleMovimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InDetalleMovimiento)) {
            return false;
        }
        InDetalleMovimiento other = (InDetalleMovimiento) object;
        if ((this.inDetalleMovimientoPK == null && other.inDetalleMovimientoPK != null) || (this.inDetalleMovimientoPK != null && !this.inDetalleMovimientoPK.equals(other.inDetalleMovimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InDetalleMovimiento[ inDetalleMovimientoPK=" + inDetalleMovimientoPK + " ]";
    }
    
}
