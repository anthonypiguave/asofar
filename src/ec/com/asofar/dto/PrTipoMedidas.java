/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author admin
 */
@Entity
@Table(name = "pr_tipo_medidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrTipoMedidas.findAll", query = "SELECT p FROM PrTipoMedidas p")
    , @NamedQuery(name = "PrTipoMedidas.findByIdTipoMedidas", query = "SELECT p FROM PrTipoMedidas p WHERE p.idTipoMedidas = :idTipoMedidas")
    , @NamedQuery(name = "PrTipoMedidas.findByNombreTipoMedida", query = "SELECT p FROM PrTipoMedidas p WHERE p.nombreTipoMedida = :nombreTipoMedida")
    , @NamedQuery(name = "PrTipoMedidas.findByEstado", query = "SELECT p FROM PrTipoMedidas p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrTipoMedidas.findByUsuarioCreacion", query = "SELECT p FROM PrTipoMedidas p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrTipoMedidas.findByFechaCreacion", query = "SELECT p FROM PrTipoMedidas p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrTipoMedidas.findByUsuarioActualizacion", query = "SELECT p FROM PrTipoMedidas p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrTipoMedidas.findByFechaActualizacion", query = "SELECT p FROM PrTipoMedidas p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrTipoMedidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_medidas")
    private Long idTipoMedidas;
    @Column(name = "nombre_tipo_medida")
    private String nombreTipoMedida;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prTipoMedidas")
    private List<PrMedidas> prMedidasList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private SeEmpresa idEmpresa;

    public PrTipoMedidas() {
    }

    public PrTipoMedidas(Long idTipoMedidas) {
        this.idTipoMedidas = idTipoMedidas;
    }

    public Long getIdTipoMedidas() {
        return idTipoMedidas;
    }

    public void setIdTipoMedidas(Long idTipoMedidas) {
        this.idTipoMedidas = idTipoMedidas;
    }

    public String getNombreTipoMedida() {
        return nombreTipoMedida;
    }

    public void setNombreTipoMedida(String nombreTipoMedida) {
        this.nombreTipoMedida = nombreTipoMedida;
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
    public List<PrMedidas> getPrMedidasList() {
        return prMedidasList;
    }

    public void setPrMedidasList(List<PrMedidas> prMedidasList) {
        this.prMedidasList = prMedidasList;
    }

    public SeEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SeEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMedidas != null ? idTipoMedidas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrTipoMedidas)) {
            return false;
        }
        PrTipoMedidas other = (PrTipoMedidas) object;
        if ((this.idTipoMedidas == null && other.idTipoMedidas != null) || (this.idTipoMedidas != null && !this.idTipoMedidas.equals(other.idTipoMedidas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrTipoMedidas[ idTipoMedidas=" + idTipoMedidas + " ]";
    }
    
}
