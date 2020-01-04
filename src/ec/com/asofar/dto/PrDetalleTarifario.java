/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "pr_detalle_tarifario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrDetalleTarifario.findAll", query = "SELECT p FROM PrDetalleTarifario p")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdDetalleTarifario", query = "SELECT p FROM PrDetalleTarifario p WHERE p.idDetalleTarifario = :idDetalleTarifario")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdPrestacion", query = "SELECT p FROM PrDetalleTarifario p WHERE p.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "PrDetalleTarifario.findByIdUnidadServicio", query = "SELECT p FROM PrDetalleTarifario p WHERE p.idUnidadServicio = :idUnidadServicio")
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_tarifario")
    private Long idDetalleTarifario;
    @Column(name = "id_prestacion")
    private BigInteger idPrestacion;
    @Column(name = "id_unidad_servicio")
    private BigInteger idUnidadServicio;
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
        @JoinColumn(name = "id_tarifario", referencedColumnName = "id_tarifario")
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
        , @JoinColumn(name = "id_surcusal", referencedColumnName = "id_surcusal")})
    @ManyToOne
    private PrTarifario prTarifario;

    public PrDetalleTarifario() {
    }

    public PrDetalleTarifario(Long idDetalleTarifario) {
        this.idDetalleTarifario = idDetalleTarifario;
    }

    public Long getIdDetalleTarifario() {
        return idDetalleTarifario;
    }

    public void setIdDetalleTarifario(Long idDetalleTarifario) {
        this.idDetalleTarifario = idDetalleTarifario;
    }

    public BigInteger getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(BigInteger idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public BigInteger getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(BigInteger idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
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

    public PrTarifario getPrTarifario() {
        return prTarifario;
    }

    public void setPrTarifario(PrTarifario prTarifario) {
        this.prTarifario = prTarifario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleTarifario != null ? idDetalleTarifario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrDetalleTarifario)) {
            return false;
        }
        PrDetalleTarifario other = (PrDetalleTarifario) object;
        if ((this.idDetalleTarifario == null && other.idDetalleTarifario != null) || (this.idDetalleTarifario != null && !this.idDetalleTarifario.equals(other.idDetalleTarifario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrDetalleTarifario[ idDetalleTarifario=" + idDetalleTarifario + " ]";
    }
    
}
