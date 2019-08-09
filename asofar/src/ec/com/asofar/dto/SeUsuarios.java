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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "se_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeUsuarios.findAll", query = "SELECT s FROM SeUsuarios s")
    , @NamedQuery(name = "SeUsuarios.findByIdUsuario", query = "SELECT s FROM SeUsuarios s WHERE s.idUsuario = :idUsuario")
    , @NamedQuery(name = "SeUsuarios.findByEstado", query = "SELECT s FROM SeUsuarios s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeUsuarios.findByUsuarioCreacion", query = "SELECT s FROM SeUsuarios s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeUsuarios.findByFechaCreacion", query = "SELECT s FROM SeUsuarios s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeUsuarios.findByUsuarioActualizacion", query = "SELECT s FROM SeUsuarios s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeUsuarios.findByFechaActualizacion", query = "SELECT s FROM SeUsuarios s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SeUsuarios.findByNombreUsuario", query = "SELECT s FROM SeUsuarios s WHERE s.nombreUsuario = :nombreUsuario")})
public class SeUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private String idUsuario;
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
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Lob
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "idUsuario")
    private List<SeUsuarioSucurRol> seUsuarioSucurRolList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private SePersonas idPersona;

    public SeUsuarios() {
    }

    public SeUsuarios(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<SeUsuarioSucurRol> getSeUsuarioSucurRolList() {
        return seUsuarioSucurRolList;
    }

    public void setSeUsuarioSucurRolList(List<SeUsuarioSucurRol> seUsuarioSucurRolList) {
        this.seUsuarioSucurRolList = seUsuarioSucurRolList;
    }

    public SePersonas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(SePersonas idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeUsuarios)) {
            return false;
        }
        SeUsuarios other = (SeUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
