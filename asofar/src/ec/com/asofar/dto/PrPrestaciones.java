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
@Table(name = "pr_prestaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrPrestaciones.findAll", query = "SELECT p FROM PrPrestaciones p")
    , @NamedQuery(name = "PrPrestaciones.findByIdPrestacion", query = "SELECT p FROM PrPrestaciones p WHERE p.prPrestacionesPK.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "PrPrestaciones.findByIdEmpresa", query = "SELECT p FROM PrPrestaciones p WHERE p.prPrestacionesPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrPrestaciones.findByIdPoducto", query = "SELECT p FROM PrPrestaciones p WHERE p.idPoducto = :idPoducto")
    , @NamedQuery(name = "PrPrestaciones.findByNombrePrestacion", query = "SELECT p FROM PrPrestaciones p WHERE p.nombrePrestacion = :nombrePrestacion")
    , @NamedQuery(name = "PrPrestaciones.findByEstado", query = "SELECT p FROM PrPrestaciones p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrPrestaciones.findByAplicaIva", query = "SELECT p FROM PrPrestaciones p WHERE p.aplicaIva = :aplicaIva")
    , @NamedQuery(name = "PrPrestaciones.findByUsuarioCreacion", query = "SELECT p FROM PrPrestaciones p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrPrestaciones.findByFechaCreacion", query = "SELECT p FROM PrPrestaciones p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrPrestaciones.findByUsuarioActualizacion", query = "SELECT p FROM PrPrestaciones p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrPrestaciones.findByFechaActualizacion", query = "SELECT p FROM PrPrestaciones p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrPrestaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrPrestacionesPK prPrestacionesPK;
    @Column(name = "id_poducto")
    private BigInteger idPoducto;
    @Column(name = "nombre_prestacion")
    private String nombrePrestacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "aplica_iva")
    private String aplicaIva;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prPrestaciones")
    private List<VeFacturaDetalle> veFacturaDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prPrestaciones")
    private List<PrDetalleTarifario> prDetalleTarifarioList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeEmpresa seEmpresa;
    @JoinColumn(name = "id_tipo_prestacion", referencedColumnName = "id_tipo_prestacion")
    @ManyToOne
    private PrTipoPrestacion idTipoPrestacion;

    public PrPrestaciones() {
    }

    public PrPrestaciones(PrPrestacionesPK prPrestacionesPK) {
        this.prPrestacionesPK = prPrestacionesPK;
    }

    public PrPrestaciones(long idPrestacion, long idEmpresa) {
        this.prPrestacionesPK = new PrPrestacionesPK(idPrestacion, idEmpresa);
    }

    public PrPrestacionesPK getPrPrestacionesPK() {
        return prPrestacionesPK;
    }

    public void setPrPrestacionesPK(PrPrestacionesPK prPrestacionesPK) {
        this.prPrestacionesPK = prPrestacionesPK;
    }

    public BigInteger getIdPoducto() {
        return idPoducto;
    }

    public void setIdPoducto(BigInteger idPoducto) {
        this.idPoducto = idPoducto;
    }

    public String getNombrePrestacion() {
        return nombrePrestacion;
    }

    public void setNombrePrestacion(String nombrePrestacion) {
        this.nombrePrestacion = nombrePrestacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAplicaIva() {
        return aplicaIva;
    }

    public void setAplicaIva(String aplicaIva) {
        this.aplicaIva = aplicaIva;
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

    @XmlTransient
    public List<PrDetalleTarifario> getPrDetalleTarifarioList() {
        return prDetalleTarifarioList;
    }

    public void setPrDetalleTarifarioList(List<PrDetalleTarifario> prDetalleTarifarioList) {
        this.prDetalleTarifarioList = prDetalleTarifarioList;
    }

    public SeEmpresa getSeEmpresa() {
        return seEmpresa;
    }

    public void setSeEmpresa(SeEmpresa seEmpresa) {
        this.seEmpresa = seEmpresa;
    }

    public PrTipoPrestacion getIdTipoPrestacion() {
        return idTipoPrestacion;
    }

    public void setIdTipoPrestacion(PrTipoPrestacion idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prPrestacionesPK != null ? prPrestacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrPrestaciones)) {
            return false;
        }
        PrPrestaciones other = (PrPrestaciones) object;
        if ((this.prPrestacionesPK == null && other.prPrestacionesPK != null) || (this.prPrestacionesPK != null && !this.prPrestacionesPK.equals(other.prPrestacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrPrestaciones[ prPrestacionesPK=" + prPrestacionesPK + " ]";
    }
    
}
