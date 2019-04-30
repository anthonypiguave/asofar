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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "in_detalle_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InDetalleMovimiento.findAll", query = "SELECT i FROM InDetalleMovimiento i")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdDetalleMovimiento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idDetalleMovimiento = :idDetalleMovimiento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdMovimientos", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idMovimientos = :idMovimientos")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdTipoDocumento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdNumeroDocumento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idNumeroDocumento = :idNumeroDocumento")
    , @NamedQuery(name = "InDetalleMovimiento.findByIdEmpresa", query = "SELECT i FROM InDetalleMovimiento i WHERE i.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InDetalleMovimiento.findByAnioDocumento", query = "SELECT i FROM InDetalleMovimiento i WHERE i.anioDocumento = :anioDocumento")
    , @NamedQuery(name = "InDetalleMovimiento.findByLineaDetalle", query = "SELECT i FROM InDetalleMovimiento i WHERE i.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "InDetalleMovimiento.findByDescripcion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "InDetalleMovimiento.findByCantidad", query = "SELECT i FROM InDetalleMovimiento i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "InDetalleMovimiento.findByPrecioUnitario", query = "SELECT i FROM InDetalleMovimiento i WHERE i.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "InDetalleMovimiento.findByEstado", query = "SELECT i FROM InDetalleMovimiento i WHERE i.estado = :estado")
    , @NamedQuery(name = "InDetalleMovimiento.findByDespachado", query = "SELECT i FROM InDetalleMovimiento i WHERE i.despachado = :despachado")
    , @NamedQuery(name = "InDetalleMovimiento.findByUsuarioCreacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByFechaCreacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByUsuarioActualizacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InDetalleMovimiento.findByFechaActualizacion", query = "SELECT i FROM InDetalleMovimiento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InDetalleMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_movimiento")
    private Long idDetalleMovimiento;
    @Column(name = "id_movimientos")
    private BigInteger idMovimientos;
    @Column(name = "id_tipo_documento")
    private BigInteger idTipoDocumento;
    @Column(name = "id_numero_documento")
    private BigInteger idNumeroDocumento;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "anio_documento")
    private String anioDocumento;
    @Column(name = "linea_detalle")
    private BigInteger lineaDetalle;
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
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private BigInteger usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento")
    @ManyToOne
    private InTipoMovimiento idTipoMovimiento;

    public InDetalleMovimiento() {
    }

    public InDetalleMovimiento(Long idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public Long getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(Long idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public BigInteger getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(BigInteger idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public BigInteger getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigInteger idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public String getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(String anioDocumento) {
        this.anioDocumento = anioDocumento;
    }

    public BigInteger getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(BigInteger lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
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

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(BigInteger usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
        hash += (idDetalleMovimiento != null ? idDetalleMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InDetalleMovimiento)) {
            return false;
        }
        InDetalleMovimiento other = (InDetalleMovimiento) object;
        if ((this.idDetalleMovimiento == null && other.idDetalleMovimiento != null) || (this.idDetalleMovimiento != null && !this.idDetalleMovimiento.equals(other.idDetalleMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InDetalleMovimiento[ idDetalleMovimiento=" + idDetalleMovimiento + " ]";
    }
    
}
