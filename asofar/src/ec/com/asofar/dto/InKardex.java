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
 * @author ADMIN
 */
@Entity
@Table(name = "in_kardex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InKardex.findAll", query = "SELECT i FROM InKardex i"),
    @NamedQuery(name = "InKardex.findByIdKardex", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idKardex = :idKardex"),
    @NamedQuery(name = "InKardex.findByIdEmpresa", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "InKardex.findByIdSucursal", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "InKardex.findByIdBodega", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idBodega = :idBodega"),
    @NamedQuery(name = "InKardex.findByIdProducto", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idProducto = :idProducto"),
    @NamedQuery(name = "InKardex.findByFechaMovimiento", query = "SELECT i FROM InKardex i WHERE i.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "InKardex.findByIdTipoDocumento", query = "SELECT i FROM InKardex i WHERE i.inKardexPK.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "InKardex.findByAnioDocumento", query = "SELECT i FROM InKardex i WHERE i.anioDocumento = :anioDocumento"),
    @NamedQuery(name = "InKardex.findByNumeroDocumento", query = "SELECT i FROM InKardex i WHERE i.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "InKardex.findByFechaSistema", query = "SELECT i FROM InKardex i WHERE i.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "InKardex.findByCantidad", query = "SELECT i FROM InKardex i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "InKardex.findBySaldoActual", query = "SELECT i FROM InKardex i WHERE i.saldoActual = :saldoActual"),
    @NamedQuery(name = "InKardex.findBySaldoAnterior", query = "SELECT i FROM InKardex i WHERE i.saldoAnterior = :saldoAnterior"),
    @NamedQuery(name = "InKardex.findBySaldoActualBodega", query = "SELECT i FROM InKardex i WHERE i.saldoActualBodega = :saldoActualBodega"),
    @NamedQuery(name = "InKardex.findBySaldoAnteriorBodega", query = "SELECT i FROM InKardex i WHERE i.saldoAnteriorBodega = :saldoAnteriorBodega"),
    @NamedQuery(name = "InKardex.findBySaldoActualEmpresa", query = "SELECT i FROM InKardex i WHERE i.saldoActualEmpresa = :saldoActualEmpresa"),
    @NamedQuery(name = "InKardex.findBySaldoAnteriorEmpresa", query = "SELECT i FROM InKardex i WHERE i.saldoAnteriorEmpresa = :saldoAnteriorEmpresa"),
    @NamedQuery(name = "InKardex.findByCostoActual", query = "SELECT i FROM InKardex i WHERE i.costoActual = :costoActual"),
    @NamedQuery(name = "InKardex.findByCostoAnterior", query = "SELECT i FROM InKardex i WHERE i.costoAnterior = :costoAnterior"),
    @NamedQuery(name = "InKardex.findByCostoPromedio", query = "SELECT i FROM InKardex i WHERE i.costoPromedio = :costoPromedio"),
    @NamedQuery(name = "InKardex.findByUsuarioCreacion", query = "SELECT i FROM InKardex i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "InKardex.findByFechaCreacion", query = "SELECT i FROM InKardex i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "InKardex.findByUsuarioActualizacion", query = "SELECT i FROM InKardex i WHERE i.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "InKardex.findByFechaActualizacion", query = "SELECT i FROM InKardex i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InKardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InKardexPK inKardexPK;
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @Column(name = "anio_documento")
    private String anioDocumento;
    @Column(name = "numero_documento")
    private BigInteger numeroDocumento;
    @Column(name = "fecha_sistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Column(name = "cantidad")
    private BigInteger cantidad;
    @Column(name = "saldo_actual")
    private BigInteger saldoActual;
    @Column(name = "saldo_anterior")
    private BigInteger saldoAnterior;
    @Column(name = "saldo_actual_bodega")
    private BigInteger saldoActualBodega;
    @Column(name = "saldo_anterior_bodega")
    private BigInteger saldoAnteriorBodega;
    @Column(name = "saldo_actual_empresa")
    private BigInteger saldoActualEmpresa;
    @Column(name = "saldo_anterior_empresa")
    private BigInteger saldoAnteriorEmpresa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_actual")
    private BigDecimal costoActual;
    @Column(name = "costo_anterior")
    private BigDecimal costoAnterior;
    @Column(name = "costo_promedio")
    private BigDecimal costoPromedio;
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
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoDocumento inTipoDocumento;

    public InKardex() {
    }

    public InKardex(InKardexPK inKardexPK) {
        this.inKardexPK = inKardexPK;
    }

    public InKardex(long idKardex, long idEmpresa, long idSucursal, long idBodega, long idProducto, long idTipoDocumento) {
        this.inKardexPK = new InKardexPK(idKardex, idEmpresa, idSucursal, idBodega, idProducto, idTipoDocumento);
    }

    public InKardexPK getInKardexPK() {
        return inKardexPK;
    }

    public void setInKardexPK(InKardexPK inKardexPK) {
        this.inKardexPK = inKardexPK;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(String anioDocumento) {
        this.anioDocumento = anioDocumento;
    }

    public BigInteger getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(BigInteger numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public BigInteger getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigInteger saldoActual) {
        this.saldoActual = saldoActual;
    }

    public BigInteger getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigInteger saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public BigInteger getSaldoActualBodega() {
        return saldoActualBodega;
    }

    public void setSaldoActualBodega(BigInteger saldoActualBodega) {
        this.saldoActualBodega = saldoActualBodega;
    }

    public BigInteger getSaldoAnteriorBodega() {
        return saldoAnteriorBodega;
    }

    public void setSaldoAnteriorBodega(BigInteger saldoAnteriorBodega) {
        this.saldoAnteriorBodega = saldoAnteriorBodega;
    }

    public BigInteger getSaldoActualEmpresa() {
        return saldoActualEmpresa;
    }

    public void setSaldoActualEmpresa(BigInteger saldoActualEmpresa) {
        this.saldoActualEmpresa = saldoActualEmpresa;
    }

    public BigInteger getSaldoAnteriorEmpresa() {
        return saldoAnteriorEmpresa;
    }

    public void setSaldoAnteriorEmpresa(BigInteger saldoAnteriorEmpresa) {
        this.saldoAnteriorEmpresa = saldoAnteriorEmpresa;
    }

    public BigDecimal getCostoActual() {
        return costoActual;
    }

    public void setCostoActual(BigDecimal costoActual) {
        this.costoActual = costoActual;
    }

    public BigDecimal getCostoAnterior() {
        return costoAnterior;
    }

    public void setCostoAnterior(BigDecimal costoAnterior) {
        this.costoAnterior = costoAnterior;
    }

    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
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

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    public InTipoDocumento getInTipoDocumento() {
        return inTipoDocumento;
    }

    public void setInTipoDocumento(InTipoDocumento inTipoDocumento) {
        this.inTipoDocumento = inTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inKardexPK != null ? inKardexPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InKardex)) {
            return false;
        }
        InKardex other = (InKardex) object;
        if ((this.inKardexPK == null && other.inKardexPK != null) || (this.inKardexPK != null && !this.inKardexPK.equals(other.inKardexPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InKardex[ inKardexPK=" + inKardexPK + " ]";
    }
    
}
