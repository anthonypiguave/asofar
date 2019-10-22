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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author nuevouser
 */
@Entity
@Table(name = "pr_tipo_prestacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrTipoPrestacion.findAll", query = "SELECT p FROM PrTipoPrestacion p")
    , @NamedQuery(name = "PrTipoPrestacion.findByIdTipoPrestacion", query = "SELECT p FROM PrTipoPrestacion p WHERE p.idTipoPrestacion = :idTipoPrestacion")
    , @NamedQuery(name = "PrTipoPrestacion.findByNombre", query = "SELECT p FROM PrTipoPrestacion p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PrTipoPrestacion.findByUsuarioCreacion", query = "SELECT p FROM PrTipoPrestacion p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrTipoPrestacion.findByEstado", query = "SELECT p FROM PrTipoPrestacion p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrTipoPrestacion.findByFechaCreacion", query = "SELECT p FROM PrTipoPrestacion p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrTipoPrestacion.findByUsuarioActualizacion", query = "SELECT p FROM PrTipoPrestacion p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrTipoPrestacion.findByFechaActualizacion", query = "SELECT p FROM PrTipoPrestacion p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrTipoPrestacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_prestacion")
    private Long idTipoPrestacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(mappedBy = "idTipoPrestacion")
    private List<PrPrestaciones> prPrestacionesList;

    public PrTipoPrestacion() {
    }

    public PrTipoPrestacion(Long idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    public Long getIdTipoPrestacion() {
        return idTipoPrestacion;
    }

    public void setIdTipoPrestacion(Long idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
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
    public List<PrPrestaciones> getPrPrestacionesList() {
        return prPrestacionesList;
    }

    public void setPrPrestacionesList(List<PrPrestaciones> prPrestacionesList) {
        this.prPrestacionesList = prPrestacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrestacion != null ? idTipoPrestacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrTipoPrestacion)) {
            return false;
        }
        PrTipoPrestacion other = (PrTipoPrestacion) object;
        if ((this.idTipoPrestacion == null && other.idTipoPrestacion != null) || (this.idTipoPrestacion != null && !this.idTipoPrestacion.equals(other.idTipoPrestacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrTipoPrestacion[ idTipoPrestacion=" + idTipoPrestacion + " ]";
    }
    
}
