/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "in_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InMovimientos.findAll", query = "SELECT i FROM InMovimientos i")
    , @NamedQuery(name = "InMovimientos.findByIdMovimientos", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InMovimientos.findByIdTipoDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InMovimientos.findByAnioDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.anioDocumento = :anioDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdNumeroDocumento", query = "SELECT i FROM InMovimientos i WHERE i.idNumeroDocumento = :idNumeroDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdEmpresa", query = "SELECT i FROM InMovimientos i WHERE i.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InMovimientos.findByIdSucursal", query = "SELECT i FROM InMovimientos i WHERE i.idSucursal = :idSucursal")
    , @NamedQuery(name = "InMovimientos.findByIdBodega", query = "SELECT i FROM InMovimientos i WHERE i.idBodega = :idBodega")
    , @NamedQuery(name = "InMovimientos.findByIdUsuario", query = "SELECT i FROM InMovimientos i WHERE i.idUsuario = :idUsuario")
    , @NamedQuery(name = "InMovimientos.findByIdTipoBodega", query = "SELECT i FROM InMovimientos i WHERE i.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "InMovimientos.findByBodegaDestino", query = "SELECT i FROM InMovimientos i WHERE i.bodegaDestino = :bodegaDestino")
    , @NamedQuery(name = "InMovimientos.findBySucursalDestino", query = "SELECT i FROM InMovimientos i WHERE i.sucursalDestino = :sucursalDestino")
    , @NamedQuery(name = "InMovimientos.findByObservacion", query = "SELECT i FROM InMovimientos i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "InMovimientos.findByIdProveedor", query = "SELECT i FROM InMovimientos i WHERE i.idProveedor = :idProveedor")
    , @NamedQuery(name = "InMovimientos.findByIdOrdenCompra", query = "SELECT i FROM InMovimientos i WHERE i.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioCreacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "InMovimientos.findByFechaCreacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")
    , @NamedQuery(name = "InMovimientos.findByFechaActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InMovimientosPK inMovimientosPK;
    @Column(name = "id_numero_documento")
    private BigInteger idNumeroDocumento;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "id_sucursal")
    private BigInteger idSucursal;
    @Column(name = "id_bodega")
    private BigInteger idBodega;
    @Column(name = "id_usuario")
    private BigInteger idUsuario;
    @Column(name = "id_tipo_bodega")
    private BigInteger idTipoBodega;
    @Column(name = "bodega_destino")
    private String bodegaDestino;
    @Column(name = "sucursal_destino")
    private String sucursalDestino;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "id_proveedor")
    private BigInteger idProveedor;
    @Column(name = "id_orden_compra")
    private BigInteger idOrdenCompra;
    @Column(name = "id_usuario_creacion")
    private BigInteger idUsuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "id_usuario_actualizacion")
    private BigInteger idUsuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado_movimiento")
    @ManyToOne
    private InEstadosMovimiento idEstado;
    @JoinColumn(name = "id_motivo", referencedColumnName = "id_motivo")
    @ManyToOne
    private InMotivos idMotivo;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoDocumento inTipoDocumento;
    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento")
    @ManyToOne
    private InTipoMovimiento idTipoMovimiento;

    public InMovimientos() {
    }

    public InMovimientos(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public InMovimientos(long idMovimientos, long idTipoDocumento, String anioDocumento) {
        this.inMovimientosPK = new InMovimientosPK(idMovimientos, idTipoDocumento, anioDocumento);
    }

    public InMovimientosPK getInMovimientosPK() {
        return inMovimientosPK;
    }

    public void setInMovimientosPK(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public BigInteger getIdNumeroDocumento() {
        return idNumeroDocumento;
    }

    public void setIdNumeroDocumento(BigInteger idNumeroDocumento) {
        this.idNumeroDocumento = idNumeroDocumento;
    }

    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BigInteger getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(BigInteger idSucursal) {
        this.idSucursal = idSucursal;
    }

    public BigInteger getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(BigInteger idBodega) {
        this.idBodega = idBodega;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigInteger getIdTipoBodega() {
        return idTipoBodega;
    }

    public void setIdTipoBodega(BigInteger idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
    }

    public String getBodegaDestino() {
        return bodegaDestino;
    }

    public void setBodegaDestino(String bodegaDestino) {
        this.bodegaDestino = bodegaDestino;
    }

    public String getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(String sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigInteger getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(BigInteger idProveedor) {
        this.idProveedor = idProveedor;
    }

    public BigInteger getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(BigInteger idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public BigInteger getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(BigInteger idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(BigInteger idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public InEstadosMovimiento getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(InEstadosMovimiento idEstado) {
        this.idEstado = idEstado;
    }

    public InMotivos getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(InMotivos idMotivo) {
        this.idMotivo = idMotivo;
    }

    public InTipoDocumento getInTipoDocumento() {
        return inTipoDocumento;
    }

    public void setInTipoDocumento(InTipoDocumento inTipoDocumento) {
        this.inTipoDocumento = inTipoDocumento;
    }

    public InTipoMovimiento getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(InTipoMovimiento idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inMovimientosPK != null ? inMovimientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InMovimientos)) {
            return false;
        }
        InMovimientos other = (InMovimientos) object;
        if ((this.inMovimientosPK == null && other.inMovimientosPK != null) || (this.inMovimientosPK != null && !this.inMovimientosPK.equals(other.inMovimientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InMovimientos[ inMovimientosPK=" + inMovimientosPK + " ]";
    }
    
}
