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
 * @author admin1
 */
@Entity
@Table(name = "se_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeRoles.findAll", query = "SELECT s FROM SeRoles s")
    , @NamedQuery(name = "SeRoles.findByIdRoles", query = "SELECT s FROM SeRoles s WHERE s.idRoles = :idRoles")
    , @NamedQuery(name = "SeRoles.findByNombre", query = "SELECT s FROM SeRoles s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeRoles.findByEstado", query = "SELECT s FROM SeRoles s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeRoles.findByUsuarioCreacion", query = "SELECT s FROM SeRoles s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeRoles.findByFechaCreacion", query = "SELECT s FROM SeRoles s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeRoles.findByUsuarioActualizacion", query = "SELECT s FROM SeRoles s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeRoles.findByFechaActualizacion", query = "SELECT s FROM SeRoles s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_roles")
    private Long idRoles;
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seRoles")
    private List<SeOpcionesRoles> seOpcionesRolesList;
    @OneToMany(mappedBy = "idRoles")
    private List<SeUsuarioSucurRol> seUsuarioSucurRolList;

    public SeRoles() {
    }

    public SeRoles(Long idRoles) {
        this.idRoles = idRoles;
    }

    public Long getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Long idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<SeOpcionesRoles> getSeOpcionesRolesList() {
        return seOpcionesRolesList;
    }

    public void setSeOpcionesRolesList(List<SeOpcionesRoles> seOpcionesRolesList) {
        this.seOpcionesRolesList = seOpcionesRolesList;
    }

    @XmlTransient
    public List<SeUsuarioSucurRol> getSeUsuarioSucurRolList() {
        return seUsuarioSucurRolList;
    }

    public void setSeUsuarioSucurRolList(List<SeUsuarioSucurRol> seUsuarioSucurRolList) {
        this.seUsuarioSucurRolList = seUsuarioSucurRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoles != null ? idRoles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeRoles)) {
            return false;
        }
        SeRoles other = (SeRoles) object;
        if ((this.idRoles == null && other.idRoles != null) || (this.idRoles != null && !this.idRoles.equals(other.idRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeRoles[ idRoles=" + idRoles + " ]";
    }
    
}
