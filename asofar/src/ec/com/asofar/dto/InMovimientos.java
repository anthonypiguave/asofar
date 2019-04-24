/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
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
@Table(name = "in_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InMovimientos.findAll", query = "SELECT i FROM InMovimientos i")
    , @NamedQuery(name = "InMovimientos.findByIdMovimientos", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InMovimientos.findByIdNumeroDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idNumeroDocumento = :idNumeroDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdTipoDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InMovimientos.findByAnioDocumento", query = "SELECT i FROM InMovimientos i WHERE i.anioDocumento = :anioDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdEmpresa", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InMovimientos.findByIdBodega", query = "SELECT i FROM InMovimientos i WHERE i.idBodega = :idBodega")
    , @NamedQuery(name = "InMovimientos.findByIdTipoBodega", query = "SELECT i FROM InMovimientos i WHERE i.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "InMovimientos.findByEstado", query = "SELECT i FROM InMovimientos i WHERE i.estado = :estado")
    , @NamedQuery(name = "InMovimientos.findByBodegaDestino", query = "SELECT i FROM InMovimientos i WHERE i.bodegaDestino = :bodegaDestino")
    , @NamedQuery(name = "InMovimientos.findBySucursalDestino", query = "SELECT i FROM InMovimientos i WHERE i.sucursalDestino = :sucursalDestino")
    , @NamedQuery(name = "InMovimientos.findByObservacion", query = "SELECT i FROM InMovimientos i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioCreacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "InMovimientos.findByFechaCreacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")
    , @NamedQuery(name = "InMovimientos.findByFechaActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InMovimientosPK inMovimientosPK;
    @Column(name = "anio_documento")
    private String anioDocumento;
    @Column(name = "id_bodega")
    private BigInteger idBodega;
    @Column(name = "id_tipo_bodega")
    private BigInteger idTipoBodega;
    @Column(name = "estado")
    private String estado;
    @Column(name = "bodega_destino")
    private String bodegaDestino;
    @Column(name = "sucursal_destino")
    private String sucursalDestino;
    @Column(name = "observacion")
    private String observacion;
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
    @OneToMany(mappedBy = "inMovimientos")
    private List<InDetalleMovimiento> inDetalleMovimientoList;
    @JoinColumns({
        @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra")
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")})
    @ManyToOne(optional = false)
    private CoOrdenCompras coOrdenCompras;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona")
    @ManyToOne
    private SePersonas idProveedor;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoDocumento inTipoDocumento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private SeUsuarios idUsuario;

    public InMovimientos() {
    }

    public InMovimientos(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public InMovimientos(long idMovimientos, long idNumeroDocumento, long idTipoDocumento, long idEmpresa) {
        this.inMovimientosPK = new InMovimientosPK(idMovimientos, idNumeroDocumento, idTipoDocumento, idEmpresa);
    }

    public InMovimientosPK getInMovimientosPK() {
        return inMovimientosPK;
    }

    public void setInMovimientosPK(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public String getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(String anioDocumento) {
        this.anioDocumento = anioDocumento;
    }

    public BigInteger getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(BigInteger idBodega) {
        this.idBodega = idBodega;
    }

    public BigInteger getIdTipoBodega() {
        return idTipoBodega;
    }

    public void setIdTipoBodega(BigInteger idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<InDetalleMovimiento> getInDetalleMovimientoList() {
        return inDetalleMovimientoList;
    }

    public void setInDetalleMovimientoList(List<InDetalleMovimiento> inDetalleMovimientoList) {
        this.inDetalleMovimientoList = inDetalleMovimientoList;
    }

    public CoOrdenCompras getCoOrdenCompras() {
        return coOrdenCompras;
    }

    public void setCoOrdenCompras(CoOrdenCompras coOrdenCompras) {
        this.coOrdenCompras = coOrdenCompras;
    }

    public SePersonas getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(SePersonas idProveedor) {
        this.idProveedor = idProveedor;
    }

    public InTipoDocumento getInTipoDocumento() {
        return inTipoDocumento;
    }

    public void setInTipoDocumento(InTipoDocumento inTipoDocumento) {
        this.inTipoDocumento = inTipoDocumento;
    }

    public SeUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SeUsuarios idUsuario) {
        this.idUsuario = idUsuario;
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
