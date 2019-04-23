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
 * @author admin1
 */
@Entity
@Table(name = "pr_detalle_tarifario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrDetalleTarifario.findAll", query = "SELECT p FROM PrDetalleTarifario p")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdDetalleTarifario", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idDetalleTarifario = :idDetalleTarifario")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdTarifario", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idTarifario = :idTarifario")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdPrestacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdEmpresa", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdSurcusal", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idSurcusal = :idSurcusal")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdUnidadServicio", query = "SELECT p FROM PrDetalleTarifario p WHERE p.prDetalleTarifarioPK.idUnidadServicio = :idUnidadServicio")
    , @NamedQuery(name = "PrDetalleTarifario.findByValorCosto", query = "SELECT p FROM PrDetalleTarifario p WHERE p.valorCosto = :valorCosto")
    , @NamedQuery(name = "PrDetalleTarifario.findByValorMinVenta", query = "SELECT p FROM PrDetalleTarifario p WHERE p.valorMinVenta = :valorMinVenta")
    , @NamedQuery(name = "PrDetalleTarifario.findByValorVenta", query = "SELECT p FROM PrDetalleTarifario p WHERE p.valorVenta = :valorVenta")
    , @NamedQuery(name = "PrDetalleTarifario.findByValorDescuento", query = "SELECT p FROM PrDetalleTarifario p WHERE p.valorDescuento = :valorDescuento")
    , @NamedQuery(name = "PrDetalleTarifario.findByEstado", query = "SELECT p FROM PrDetalleTarifario p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrDetalleTarifario.findByUsuarioCreacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrDetalleTarifario.findByFechaCreacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrDetalleTarifario.findByUsuarioActualizacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrDetalleTarifario.findByFechaActualizacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrDetalleTarifario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrDetalleTarifarioPK prDetalleTarifarioPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_costo")
    private Double valorCosto;
    @Column(name = "valor_min_venta")
    private Double valorMinVenta;
    @Column(name = "valor_venta")
    private Double valorVenta;
    @Column(name = "valor_descuento")
    private Double valorDescuento;
    @Column(name = "estado")
    private String estado;
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
    @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrPrestaciones prPrestaciones;
    @JoinColumns({
        @JoinColumn(name = "id_tarifario", referencedColumnName = "id_tarifario", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_surcusal", referencedColumnName = "id_surcusal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PrTarifario prTarifario;
    @JoinColumn(name = "id_unidad_servicio", referencedColumnName = "id_unidad_servicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VeUnidadServicio veUnidadServicio;

    public PrDetalleTarifario() {
    }

    public PrDetalleTarifario(PrDetalleTarifarioPK prDetalleTarifarioPK) {
        this.prDetalleTarifarioPK = prDetalleTarifarioPK;
    }

    public PrDetalleTarifario(long idDetalleTarifario, long idTarifario, long idPrestacion, long idEmpresa, long idSurcusal, long idUnidadServicio) {
        this.prDetalleTarifarioPK = new PrDetalleTarifarioPK(idDetalleTarifario, idTarifario, idPrestacion, idEmpresa, idSurcusal, idUnidadServicio);
    }

    public PrDetalleTarifarioPK getPrDetalleTarifarioPK() {
        return prDetalleTarifarioPK;
    }

    public void setPrDetalleTarifarioPK(PrDetalleTarifarioPK prDetalleTarifarioPK) {
        this.prDetalleTarifarioPK = prDetalleTarifarioPK;
    }

    public Double getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(Double valorCosto) {
        this.valorCosto = valorCosto;
    }

    public Double getValorMinVenta() {
        return valorMinVenta;
    }

    public void setValorMinVenta(Double valorMinVenta) {
        this.valorMinVenta = valorMinVenta;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(Double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public PrPrestaciones getPrPrestaciones() {
        return prPrestaciones;
    }

    public void setPrPrestaciones(PrPrestaciones prPrestaciones) {
        this.prPrestaciones = prPrestaciones;
    }

    public PrTarifario getPrTarifario() {
        return prTarifario;
    }

    public void setPrTarifario(PrTarifario prTarifario) {
        this.prTarifario = prTarifario;
    }

    public VeUnidadServicio getVeUnidadServicio() {
        return veUnidadServicio;
    }

    public void setVeUnidadServicio(VeUnidadServicio veUnidadServicio) {
        this.veUnidadServicio = veUnidadServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prDetalleTarifarioPK != null ? prDetalleTarifarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrDetalleTarifario)) {
            return false;
        }
        PrDetalleTarifario other = (PrDetalleTarifario) object;
        if ((this.prDetalleTarifarioPK == null && other.prDetalleTarifarioPK != null) || (this.prDetalleTarifarioPK != null && !this.prDetalleTarifarioPK.equals(other.prDetalleTarifarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrDetalleTarifario[ prDetalleTarifarioPK=" + prDetalleTarifarioPK + " ]";
    }
    
}
