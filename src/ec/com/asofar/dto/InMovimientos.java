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
@Table(name = "in_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InMovimientos.findAll", query = "SELECT i FROM InMovimientos i")
    , @NamedQuery(name = "InMovimientos.findByIdMovimientos", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InMovimientos.findByIdTipoDocumento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InMovimientos.findByAnioDocumento", query = "SELECT i FROM InMovimientos i WHERE i.anioDocumento = :anioDocumento")
    , @NamedQuery(name = "InMovimientos.findByIdTipoMovimiento", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idTipoMovimiento = :idTipoMovimiento")
    , @NamedQuery(name = "InMovimientos.findByIdMotivo", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idMotivo = :idMotivo")
    , @NamedQuery(name = "InMovimientos.findByIdEmpresa", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InMovimientos.findByIdSucursal", query = "SELECT i FROM InMovimientos i WHERE i.inMovimientosPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "InMovimientos.findByIdBodegaOrigen", query = "SELECT i FROM InMovimientos i WHERE i.idBodegaOrigen = :idBodegaOrigen")
    , @NamedQuery(name = "InMovimientos.findByIdBodegaDestino", query = "SELECT i FROM InMovimientos i WHERE i.idBodegaDestino = :idBodegaDestino")
    , @NamedQuery(name = "InMovimientos.findByIdSucursalDestino", query = "SELECT i FROM InMovimientos i WHERE i.idSucursalDestino = :idSucursalDestino")
    , @NamedQuery(name = "InMovimientos.findByFechaSistema", query = "SELECT i FROM InMovimientos i WHERE i.fechaSistema = :fechaSistema")
    , @NamedQuery(name = "InMovimientos.findByFechaTransferencia", query = "SELECT i FROM InMovimientos i WHERE i.fechaTransferencia = :fechaTransferencia")
    , @NamedQuery(name = "InMovimientos.findByFechaRecepcion", query = "SELECT i FROM InMovimientos i WHERE i.fechaRecepcion = :fechaRecepcion")
    , @NamedQuery(name = "InMovimientos.findByObservacion", query = "SELECT i FROM InMovimientos i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "InMovimientos.findByIdOrdenCompra", query = "SELECT i FROM InMovimientos i WHERE i.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "InMovimientos.findByFechaOrden", query = "SELECT i FROM InMovimientos i WHERE i.fechaOrden = :fechaOrden")
    , @NamedQuery(name = "InMovimientos.findByIdFactura", query = "SELECT i FROM InMovimientos i WHERE i.idFactura = :idFactura")
    , @NamedQuery(name = "InMovimientos.findByFechaFactura", query = "SELECT i FROM InMovimientos i WHERE i.fechaFactura = :fechaFactura")
    , @NamedQuery(name = "InMovimientos.findByEstado", query = "SELECT i FROM InMovimientos i WHERE i.estado = :estado")
    , @NamedQuery(name = "InMovimientos.findByUsuarioCreacion", query = "SELECT i FROM InMovimientos i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InMovimientos.findByFechaCreacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InMovimientos.findByUsuarioActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InMovimientos.findByFechaActualizacion", query = "SELECT i FROM InMovimientos i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InMovimientosPK inMovimientosPK;
    @Column(name = "anio_documento")
    @Temporal(TemporalType.DATE)
    private Date anioDocumento;
    @Column(name = "id_bodega_origen")
    private BigInteger idBodegaOrigen;
    @Column(name = "id_bodega_destino")
    private BigInteger idBodegaDestino;
    @Column(name = "id_sucursal_destino")
    private BigInteger idSucursalDestino;
    @Column(name = "fecha_sistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Column(name = "fecha_transferencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransferencia;
    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "id_orden_compra")
    private BigInteger idOrdenCompra;
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrden;
    @Column(name = "id_factura")
    private BigInteger idFactura;
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inMovimientos")
    private List<InDetalleMovimiento> inDetalleMovimientoList;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoDocumento inTipoDocumento;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private CoProveedores idProveedor;
    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoMovimiento inTipoMovimiento;
    @JoinColumn(name = "id_motivo", referencedColumnName = "id_motivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InMotivos inMotivos;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;

    public InMovimientos() {
    }

    public InMovimientos(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public InMovimientos(long idMovimientos, long idTipoDocumento, long idTipoMovimiento, long idMotivo, long idEmpresa, long idSucursal) {
        this.inMovimientosPK = new InMovimientosPK(idMovimientos, idTipoDocumento, idTipoMovimiento, idMotivo, idEmpresa, idSucursal);
    }

    public InMovimientosPK getInMovimientosPK() {
        return inMovimientosPK;
    }

    public void setInMovimientosPK(InMovimientosPK inMovimientosPK) {
        this.inMovimientosPK = inMovimientosPK;
    }

    public Date getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(Date anioDocumento) {
        this.anioDocumento = anioDocumento;
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

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigInteger getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(BigInteger idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public BigInteger getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(BigInteger idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
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

    @XmlTransient
    public List<InDetalleMovimiento> getInDetalleMovimientoList() {
        return inDetalleMovimientoList;
    }

    public void setInDetalleMovimientoList(List<InDetalleMovimiento> inDetalleMovimientoList) {
        this.inDetalleMovimientoList = inDetalleMovimientoList;
    }

    public InTipoDocumento getInTipoDocumento() {
        return inTipoDocumento;
    }

    public void setInTipoDocumento(InTipoDocumento inTipoDocumento) {
        this.inTipoDocumento = inTipoDocumento;
    }

    public CoProveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(CoProveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public InTipoMovimiento getInTipoMovimiento() {
        return inTipoMovimiento;
    }

    public void setInTipoMovimiento(InTipoMovimiento inTipoMovimiento) {
        this.inTipoMovimiento = inTipoMovimiento;
    }

    public InMotivos getInMotivos() {
        return inMotivos;
    }

    public void setInMotivos(InMotivos inMotivos) {
        this.inMotivos = inMotivos;
    }

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
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
