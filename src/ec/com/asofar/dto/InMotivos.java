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
 * @author admini
 */
@Entity
@Table(name = "in_motivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InMotivos.findAll", query = "SELECT i FROM InMotivos i")
    , @NamedQuery(name = "InMotivos.findByIdMotivo", query = "SELECT i FROM InMotivos i WHERE i.idMotivo = :idMotivo")
    , @NamedQuery(name = "InMotivos.findByNombre", query = "SELECT i FROM InMotivos i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InMotivos.findByEstado", query = "SELECT i FROM InMotivos i WHERE i.estado = :estado")
    , @NamedQuery(name = "InMotivos.findByUsuarioCreacion", query = "SELECT i FROM InMotivos i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InMotivos.findByFechaCreacion", query = "SELECT i FROM InMotivos i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InMotivos.findByUsuarioActualizacion", query = "SELECT i FROM InMotivos i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InMotivos.findByFechaActualizacion", query = "SELECT i FROM InMotivos i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InMotivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_motivo")
    private Long idMotivo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inMotivos")
    private List<InMovimientos> inMovimientosList;

    public InMotivos() {
    }

    public InMotivos(Long idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
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
    public List<InMovimientos> getInMovimientosList() {
        return inMovimientosList;
    }

    public void setInMovimientosList(List<InMovimientos> inMovimientosList) {
        this.inMovimientosList = inMovimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivo != null ? idMotivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InMotivos)) {
            return false;
        }
        InMotivos other = (InMotivos) object;
        if ((this.idMotivo == null && other.idMotivo != null) || (this.idMotivo != null && !this.idMotivo.equals(other.idMotivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InMotivos[ idMotivo=" + idMotivo + " ]";
    }
    
}
