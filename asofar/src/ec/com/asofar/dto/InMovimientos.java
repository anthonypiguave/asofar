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
 * @author admin1
 */
@Entity
@Table(name = "in_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InMovimientos.findAll", query = "SELECT i FROM InMovimientos i")
    , @NamedQuery(name = "InMovimientos.findByIdMovimientos", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InMovimientos.findByIdTipoDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InMovimientos.findByA\u00f1oDocumento", query = "SELECT i FROM InMovimientos i WHERE i.a\u00f1oDocumento = :a\u00f1oDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdEmpresa", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InMovimientos.findByIdSucursal", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "InMovimientos.findByIdBodega", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idBodega = :idBodega")
    , @NamedQuery(name = "InMovimientos.findByIdUsuario", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "InMovimientos.findByEstado", query = "SELECT i FROM InMovimientos i WHERE i.estado = :estado")
    , @NamedQuery(name = "InMovimientos.findByBodegaDestino", query = "SELECT i FROM InMovimientos i WHERE i.bodegaDestino = :bodegaDestino")
    , @NamedQuery(name = "InMovimientos.findBySucursalDestino", query = "SELECT i FROM InMovimientos i WHERE i.sucursalDestino = :sucursalDestino")
    , @NamedQuery(name = "InMovimientos.findByObservacion", query = "SELECT i FROM InMovimientos i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "InMovimientos.findByIdProveedor", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idProveedor = :idProveedor")
    , @NamedQuery(name = "InMovimientos.findByIdOrdenCompra", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioCreacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "InMovimientos.findByFechaCreacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InMovimientos.findByIdUsuarioActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")
    , @NamedQuery(name = "InMovimientos.findByFechaActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InMovimientosPK inMovimientosPK;
    @Column(name = "a\u00f1o_documento")
    private String añoDocumento;
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
    @OneToMany(mappedBy = "idMovimientos")
    private List<InDetalleMovimiento> inDetalleMovimientoList;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)
        , @JoinColumn(name = "id_bodega", referencedColumnName = "id_bodega", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private InBodega inBodega;
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CoOrdenCompras coOrdenCompras;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SePersonas sePersonas;
    @JoinColumn(name = "id_numero_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne
    private InTipoDocumento idNumeroDocumento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeUsuarios seUsuarios;

    public InMovimientos() {
    }

    public InMovimientos(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public InMovimientos(long idMovimientos, long idTipoDocumento, long idEmpresa, long idSucursal, long idBodega, long idUsuario, long idProveedor, long idOrdenCompra) {
        this.inMovimientosPK = new InMovimientosPK(idMovimientos, idTipoDocumento, idEmpresa, idSucursal, idBodega, idUsuario, idProveedor, idOrdenCompra);
    }

    public InMovimientosPK getInMovimientosPK() {
        return inMovimientosPK;
    }

    public void setInMovimientosPK(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public String getAñoDocumento() {
        return añoDocumento;
    }

    public void setAñoDocumento(String añoDocumento) {
        this.añoDocumento = añoDocumento;
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

    public InBodega getInBodega() {
        return inBodega;
    }

    public void setInBodega(InBodega inBodega) {
        this.inBodega = inBodega;
    }

    public CoOrdenCompras getCoOrdenCompras() {
        return coOrdenCompras;
    }

    public void setCoOrdenCompras(CoOrdenCompras coOrdenCompras) {
        this.coOrdenCompras = coOrdenCompras;
    }

    public SePersonas getSePersonas() {
        return sePersonas;
    }

    public void setSePersonas(SePersonas sePersonas) {
        this.sePersonas = sePersonas;
    }

    public InTipoDocumento getIdNumeroDocumento() {
        return idNumeroDocumento;
    }

    public void setIdNumeroDocumento(InTipoDocumento idNumeroDocumento) {
        this.idNumeroDocumento = idNumeroDocumento;
    }

    public SeUsuarios getSeUsuarios() {
        return seUsuarios;
    }

    public void setSeUsuarios(SeUsuarios seUsuarios) {
        this.seUsuarios = seUsuarios;
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
