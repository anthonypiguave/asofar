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
 * @author Usuario
 */
@Entity
@Table(name = "ve_factura_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeFacturaDetalle.findAll", query = "SELECT v FROM VeFacturaDetalle v")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdFacturaDetalle", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idFacturaDetalle = :idFacturaDetalle")
    , @NamedQuery(name = "VeFacturaDetalle.findByLineaDetalle", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdFactura", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idFactura = :idFactura")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdEmpresa", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdSucursal", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idSucursal = :idSucursal")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdPrestaciones", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idPrestaciones = :idPrestaciones")
    , @NamedQuery(name = "VeFacturaDetalle.findByDescripcion", query = "SELECT v FROM VeFacturaDetalle v WHERE v.descripcion = :descripcion")
    , @NamedQuery(name = "VeFacturaDetalle.findByIdUnidadServicio", query = "SELECT v FROM VeFacturaDetalle v WHERE v.veFacturaDetallePK.idUnidadServicio = :idUnidadServicio")
    , @NamedQuery(name = "VeFacturaDetalle.findByCantidad", query = "SELECT v FROM VeFacturaDetalle v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "VeFacturaDetalle.findByPrecioUnitarioVenta", query = "SELECT v FROM VeFacturaDetalle v WHERE v.precioUnitarioVenta = :precioUnitarioVenta")
    , @NamedQuery(name = "VeFacturaDetalle.findByValorIce", query = "SELECT v FROM VeFacturaDetalle v WHERE v.valorIce = :valorIce")
    , @NamedQuery(name = "VeFacturaDetalle.findByValorIva", query = "SELECT v FROM VeFacturaDetalle v WHERE v.valorIva = :valorIva")
    , @NamedQuery(name = "VeFacturaDetalle.findByValorDescuento", query = "SELECT v FROM VeFacturaDetalle v WHERE v.valorDescuento = :valorDescuento")
    , @NamedQuery(name = "VeFacturaDetalle.findByValorTotal", query = "SELECT v FROM VeFacturaDetalle v WHERE v.valorTotal = :valorTotal")
    , @NamedQuery(name = "VeFacturaDetalle.findByEstado", query = "SELECT v FROM VeFacturaDetalle v WHERE v.estado = :estado")
    , @NamedQuery(name = "VeFacturaDetalle.findByUsuarioCreacion", query = "SELECT v FROM VeFacturaDetalle v WHERE v.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "VeFacturaDetalle.findByFechaCreacion", query = "SELECT v FROM VeFacturaDetalle v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VeFacturaDetalle.findByUsuarioActualizacion", query = "SELECT v FROM VeFacturaDetalle v WHERE v.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "VeFacturaDetalle.findByFechaActualizacion", query = "SELECT v FROM VeFacturaDetalle v WHERE v.fechaActualizacion = :fechaActualizacion")})
public class VeFacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeFacturaDetallePK veFacturaDetallePK;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private BigInteger cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario_venta")
    private Double precioUnitarioVenta;
    @Column(name = "valor_ice")
    private Double valorIce;
    @Column(name = "valor_iva")
    private Double valorIva;
    @Column(name = "valor_descuento")
    private Double valorDescuento;
    @Column(name = "valor_total")
    private Double valorTotal;
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
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @JoinColumns({
        @JoinColumn(name = "id_factura", referencedColumnName = "id_factura", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private VeFactura veFactura;

    public VeFacturaDetalle() {
    }

    public VeFacturaDetalle(VeFacturaDetallePK veFacturaDetallePK) {
        this.veFacturaDetallePK = veFacturaDetallePK;
    }

    public VeFacturaDetalle(long idFacturaDetalle, long lineaDetalle, long idFactura, long idEmpresa, long idSucursal, long idPrestaciones, long idUnidadServicio) {
        this.veFacturaDetallePK = new VeFacturaDetallePK(idFacturaDetalle, lineaDetalle, idFactura, idEmpresa, idSucursal, idPrestaciones, idUnidadServicio);
    }

    public VeFacturaDetallePK getVeFacturaDetallePK() {
        return veFacturaDetallePK;
    }

    public void setVeFacturaDetallePK(VeFacturaDetallePK veFacturaDetallePK) {
        this.veFacturaDetallePK = veFacturaDetallePK;
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

    public Double getPrecioUnitarioVenta() {
        return precioUnitarioVenta;
    }

    public void setPrecioUnitarioVenta(Double precioUnitarioVenta) {
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public Double getValorIce() {
        return valorIce;
    }

    public void setValorIce(Double valorIce) {
        this.valorIce = valorIce;
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

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    public VeFactura getVeFactura() {
        return veFactura;
    }

    public void setVeFactura(VeFactura veFactura) {
        this.veFactura = veFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veFacturaDetallePK != null ? veFacturaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeFacturaDetalle)) {
            return false;
        }
        VeFacturaDetalle other = (VeFacturaDetalle) object;
        if ((this.veFacturaDetallePK == null && other.veFacturaDetallePK != null) || (this.veFacturaDetallePK != null && !this.veFacturaDetallePK.equals(other.veFacturaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeFacturaDetalle[ veFacturaDetallePK=" + veFacturaDetallePK + " ]";
    }
    
}
