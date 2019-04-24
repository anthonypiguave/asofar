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
 * @author ADMIN
 */
@Entity
@Table(name = "pr_tarifario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrTarifario.findAll", query = "SELECT p FROM PrTarifario p")
    , @NamedQuery(name = "PrTarifario.findByIdTarifario", query = "SELECT p FROM PrTarifario p WHERE p.prTarifarioPK.idTarifario = :idTarifario")
    , @NamedQuery(name = "PrTarifario.findByIdEmpresa", query = "SELECT p FROM PrTarifario p WHERE p.prTarifarioPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrTarifario.findByIdSurcusal", query = "SELECT p FROM PrTarifario p WHERE p.prTarifarioPK.idSurcusal = :idSurcusal")
    , @NamedQuery(name = "PrTarifario.findByDescripcion", query = "SELECT p FROM PrTarifario p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "PrTarifario.findByFechaInicioVigente", query = "SELECT p FROM PrTarifario p WHERE p.fechaInicioVigente = :fechaInicioVigente")
    , @NamedQuery(name = "PrTarifario.findByFechaFinVigente", query = "SELECT p FROM PrTarifario p WHERE p.fechaFinVigente = :fechaFinVigente")
    , @NamedQuery(name = "PrTarifario.findByEstado", query = "SELECT p FROM PrTarifario p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrTarifario.findByUsuarioCreacion", query = "SELECT p FROM PrTarifario p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrTarifario.findByFechaCreacion", query = "SELECT p FROM PrTarifario p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrTarifario.findByUsuarioActualizacion", query = "SELECT p FROM PrTarifario p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrTarifario.findByFechaActualizacion", query = "SELECT p FROM PrTarifario p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrTarifario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrTarifarioPK prTarifarioPK;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_inicio_vigente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigente;
    @Column(name = "fecha_fin_vigente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigente;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prTarifario")
    private List<PrDetalleTarifario> prDetalleTarifarioList;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_surcusal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;

    public PrTarifario() {
    }

    public PrTarifario(PrTarifarioPK prTarifarioPK) {
        this.prTarifarioPK = prTarifarioPK;
    }

    public PrTarifario(long idTarifario, long idEmpresa, long idSurcusal) {
        this.prTarifarioPK = new PrTarifarioPK(idTarifario, idEmpresa, idSurcusal);
    }

    public PrTarifarioPK getPrTarifarioPK() {
        return prTarifarioPK;
    }

    public void setPrTarifarioPK(PrTarifarioPK prTarifarioPK) {
        this.prTarifarioPK = prTarifarioPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicioVigente() {
        return fechaInicioVigente;
    }

    public void setFechaInicioVigente(Date fechaInicioVigente) {
        this.fechaInicioVigente = fechaInicioVigente;
    }

    public Date getFechaFinVigente() {
        return fechaFinVigente;
    }

    public void setFechaFinVigente(Date fechaFinVigente) {
        this.fechaFinVigente = fechaFinVigente;
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

    @XmlTransient
    public List<PrDetalleTarifario> getPrDetalleTarifarioList() {
        return prDetalleTarifarioList;
    }

    public void setPrDetalleTarifarioList(List<PrDetalleTarifario> prDetalleTarifarioList) {
        this.prDetalleTarifarioList = prDetalleTarifarioList;
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
        hash += (prTarifarioPK != null ? prTarifarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrTarifario)) {
            return false;
        }
        PrTarifario other = (PrTarifario) object;
        if ((this.prTarifarioPK == null && other.prTarifarioPK != null) || (this.prTarifarioPK != null && !this.prTarifarioPK.equals(other.prTarifarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrTarifario[ prTarifarioPK=" + prTarifarioPK + " ]";
    }
    
}
