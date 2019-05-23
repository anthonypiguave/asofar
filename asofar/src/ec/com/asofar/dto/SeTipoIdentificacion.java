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
 * @author ADMIN
 */
@Entity
@Table(name = "se_tipo_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeTipoIdentificacion.findAll", query = "SELECT s FROM SeTipoIdentificacion s"),
    @NamedQuery(name = "SeTipoIdentificacion.findByIdTipoIdentificacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.idTipoIdentificacion = :idTipoIdentificacion"),
    @NamedQuery(name = "SeTipoIdentificacion.findByNombreIdentificacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.nombreIdentificacion = :nombreIdentificacion"),
    @NamedQuery(name = "SeTipoIdentificacion.findByEstado", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.estado = :estado"),
    @NamedQuery(name = "SeTipoIdentificacion.findByUsuarioCreacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "SeTipoIdentificacion.findByFechaCreacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "SeTipoIdentificacion.findByUsuarioActualizacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "SeTipoIdentificacion.findByFechaActualizacion", query = "SELECT s FROM SeTipoIdentificacion s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeTipoIdentificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_identificacion")
    private Long idTipoIdentificacion;
    @Column(name = "nombre_identificacion")
    private String nombreIdentificacion;
    @Column(name = "estado")
    private Character estado;
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
    @OneToMany(mappedBy = "idTipoIdentificacion")
    private List<SeClientes> seClientesList;

    public SeTipoIdentificacion() {
    }

    public SeTipoIdentificacion(Long idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public Long getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNombreIdentificacion() {
        return nombreIdentificacion;
    }

    public void setNombreIdentificacion(String nombreIdentificacion) {
        this.nombreIdentificacion = nombreIdentificacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
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
    public List<SeClientes> getSeClientesList() {
        return seClientesList;
    }

    public void setSeClientesList(List<SeClientes> seClientesList) {
        this.seClientesList = seClientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoIdentificacion != null ? idTipoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeTipoIdentificacion)) {
            return false;
        }
        SeTipoIdentificacion other = (SeTipoIdentificacion) object;
        if ((this.idTipoIdentificacion == null && other.idTipoIdentificacion != null) || (this.idTipoIdentificacion != null && !this.idTipoIdentificacion.equals(other.idTipoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeTipoIdentificacion[ idTipoIdentificacion=" + idTipoIdentificacion + " ]";
    }
    
}
