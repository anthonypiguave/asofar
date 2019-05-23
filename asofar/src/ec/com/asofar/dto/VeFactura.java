/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
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
 * @author ADMIN
 */
@Entity
@Table(name = "ve_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeFactura.findAll", query = "SELECT v FROM VeFactura v"),
    @NamedQuery(name = "VeFactura.findByIdFactura", query = "SELECT v FROM VeFactura v WHERE v.veFacturaPK.idFactura = :idFactura"),
    @NamedQuery(name = "VeFactura.findByIdEmpresa", query = "SELECT v FROM VeFactura v WHERE v.veFacturaPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "VeFactura.findByFechaFacturacion", query = "SELECT v FROM VeFactura v WHERE v.fechaFacturacion = :fechaFacturacion"),
    @NamedQuery(name = "VeFactura.findByNumeroEstablecimientoSri", query = "SELECT v FROM VeFactura v WHERE v.numeroEstablecimientoSri = :numeroEstablecimientoSri"),
    @NamedQuery(name = "VeFactura.findByPuntoEmisionSri", query = "SELECT v FROM VeFactura v WHERE v.puntoEmisionSri = :puntoEmisionSri"),
    @NamedQuery(name = "VeFactura.findBySecuenciaSri", query = "SELECT v FROM VeFactura v WHERE v.secuenciaSri = :secuenciaSri"),
    @NamedQuery(name = "VeFactura.findBySubtotal", query = "SELECT v FROM VeFactura v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VeFactura.findByTotalIce", query = "SELECT v FROM VeFactura v WHERE v.totalIce = :totalIce"),
    @NamedQuery(name = "VeFactura.findByTotalDescuento", query = "SELECT v FROM VeFactura v WHERE v.totalDescuento = :totalDescuento"),
    @NamedQuery(name = "VeFactura.findByTotalBaseIva", query = "SELECT v FROM VeFactura v WHERE v.totalBaseIva = :totalBaseIva"),
    @NamedQuery(name = "VeFactura.findByTotalBaseNoIva", query = "SELECT v FROM VeFactura v WHERE v.totalBaseNoIva = :totalBaseNoIva"),
    @NamedQuery(name = "VeFactura.findByTotalIva", query = "SELECT v FROM VeFactura v WHERE v.totalIva = :totalIva"),
    @NamedQuery(name = "VeFactura.findByTotalFacturado", query = "SELECT v FROM VeFactura v WHERE v.totalFacturado = :totalFacturado"),
    @NamedQuery(name = "VeFactura.findByEstado", query = "SELECT v FROM VeFactura v WHERE v.estado = :estado"),
    @NamedQuery(name = "VeFactura.findByDespachado", query = "SELECT v FROM VeFactura v WHERE v.despachado = :despachado"),
    @NamedQuery(name = "VeFactura.findByUsuarioCreacion", query = "SELECT v FROM VeFactura v WHERE v.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "VeFactura.findByFechaCreacion", query = "SELECT v FROM VeFactura v WHERE v.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "VeFactura.findByUsuarioActualizacion", query = "SELECT v FROM VeFactura v WHERE v.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "VeFactura.findByFechaActualizacion", query = "SELECT v FROM VeFactura v WHERE v.fechaActualizacion = :fechaActualizacion")})
public class VeFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeFacturaPK veFacturaPK;
    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;
    @Column(name = "numero_establecimiento_sri")
    private String numeroEstablecimientoSri;
    @Column(name = "punto_emision_sri")
    private String puntoEmisionSri;
    @Column(name = "secuencia_sri")
    private String secuenciaSri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "total_ice")
    private Double totalIce;
    @Column(name = "total_descuento")
    private Double totalDescuento;
    @Column(name = "total_base_iva")
    private Double totalBaseIva;
    @Column(name = "total_base_no_iva")
    private Double totalBaseNoIva;
    @Column(name = "total_iva")
    private Double totalIva;
    @Column(name = "total_facturado")
    private Double totalFacturado;
    @Column(name = "estado")
    private String estado;
    @Column(name = "despachado")
    private String despachado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veFactura")
    private List<VeFacturaDetalle> veFacturaDetalleList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_persona")
    @ManyToOne
    private SePersonas idCliente;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
    @ManyToOne
    private VeCaja idCaja;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private SeUsuarios idUsuario;

    public VeFactura() {
    }

    public VeFactura(VeFacturaPK veFacturaPK) {
        this.veFacturaPK = veFacturaPK;
    }

    public VeFactura(long idFactura, long idEmpresa) {
        this.veFacturaPK = new VeFacturaPK(idFactura, idEmpresa);
    }

    public VeFacturaPK getVeFacturaPK() {
        return veFacturaPK;
    }

    public void setVeFacturaPK(VeFacturaPK veFacturaPK) {
        this.veFacturaPK = veFacturaPK;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
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

    public String getSecuenciaSri() {
        return secuenciaSri;
    }

    public void setSecuenciaSri(String secuenciaSri) {
        this.secuenciaSri = secuenciaSri;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalIce() {
        return totalIce;
    }

    public void setTotalIce(Double totalIce) {
        this.totalIce = totalIce;
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
    public List<VeFacturaDetalle> getVeFacturaDetalleList() {
        return veFacturaDetalleList;
    }

    public void setVeFacturaDetalleList(List<VeFacturaDetalle> veFacturaDetalleList) {
        this.veFacturaDetalleList = veFacturaDetalleList;
    }

    public SePersonas getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(SePersonas idCliente) {
        this.idCliente = idCliente;
    }

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    public VeCaja getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(VeCaja idCaja) {
        this.idCaja = idCaja;
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
        hash += (veFacturaPK != null ? veFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeFactura)) {
            return false;
        }
        VeFactura other = (VeFactura) object;
        if ((this.veFacturaPK == null && other.veFacturaPK != null) || (this.veFacturaPK != null && !this.veFacturaPK.equals(other.veFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeFactura[ veFacturaPK=" + veFacturaPK + " ]";
    }
    
}
