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
 * @author usuario
 */
@Entity
@Table(name = "pr_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrGrupos.findAll", query = "SELECT p FROM PrGrupos p")
    , @NamedQuery(name = "PrGrupos.findByIdGrupo", query = "SELECT p FROM PrGrupos p WHERE p.idGrupo = :idGrupo")
    , @NamedQuery(name = "PrGrupos.findByNombre", query = "SELECT p FROM PrGrupos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PrGrupos.findByEstado", query = "SELECT p FROM PrGrupos p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrGrupos.findByUsuarioCreacion", query = "SELECT p FROM PrGrupos p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrGrupos.findByFechaCreacion", query = "SELECT p FROM PrGrupos p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrGrupos.findByUsuarioActualizacion", query = "SELECT p FROM PrGrupos p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrGrupos.findByFechaActualizacion", query = "SELECT p FROM PrGrupos p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Long idGrupo;
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prGrupos")
    private List<PrSubgrupos> prSubgruposList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private SeEmpresa idEmpresa;

    public PrGrupos() {
    }

    public PrGrupos(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<PrSubgrupos> getPrSubgruposList() {
        return prSubgruposList;
    }

    public void setPrSubgruposList(List<PrSubgrupos> prSubgruposList) {
        this.prSubgruposList = prSubgruposList;
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
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrGrupos)) {
            return false;
        }
        PrGrupos other = (PrGrupos) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrGrupos[ idGrupo=" + idGrupo + " ]";
    }
    
}
