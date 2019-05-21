/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ms24m
 */
@Entity
@Table(name = "se_opciones_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeOpcionesRoles.findAll", query = "SELECT s FROM SeOpcionesRoles s")
    , @NamedQuery(name = "SeOpcionesRoles.findByIdOpcionesRoles", query = "SELECT s FROM SeOpcionesRoles s WHERE s.seOpcionesRolesPK.idOpcionesRoles = :idOpcionesRoles")
    , @NamedQuery(name = "SeOpcionesRoles.findByIdOpcionesMenu", query = "SELECT s FROM SeOpcionesRoles s WHERE s.seOpcionesRolesPK.idOpcionesMenu = :idOpcionesMenu")
    , @NamedQuery(name = "SeOpcionesRoles.findByIdRol", query = "SELECT s FROM SeOpcionesRoles s WHERE s.seOpcionesRolesPK.idRol = :idRol")
    , @NamedQuery(name = "SeOpcionesRoles.findByEstado", query = "SELECT s FROM SeOpcionesRoles s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeOpcionesRoles.findByUsuarioCreacion", query = "SELECT s FROM SeOpcionesRoles s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeOpcionesRoles.findByFechaCreacion", query = "SELECT s FROM SeOpcionesRoles s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeOpcionesRoles.findByUsuarioActualizacion", query = "SELECT s FROM SeOpcionesRoles s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeOpcionesRoles.findByFechaActualizacion", query = "SELECT s FROM SeOpcionesRoles s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeOpcionesRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeOpcionesRolesPK seOpcionesRolesPK;
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
    @JoinColumn(name = "id_opciones_menu", referencedColumnName = "id_opciones_menu", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeOpcionesMenu seOpcionesMenu;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_roles", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeRoles seRoles;

    public SeOpcionesRoles() {
    }

    public SeOpcionesRoles(SeOpcionesRolesPK seOpcionesRolesPK) {
        this.seOpcionesRolesPK = seOpcionesRolesPK;
    }

    public SeOpcionesRoles(long idOpcionesRoles, long idOpcionesMenu, long idRol) {
        this.seOpcionesRolesPK = new SeOpcionesRolesPK(idOpcionesRoles, idOpcionesMenu, idRol);
    }

    public SeOpcionesRolesPK getSeOpcionesRolesPK() {
        return seOpcionesRolesPK;
    }

    public void setSeOpcionesRolesPK(SeOpcionesRolesPK seOpcionesRolesPK) {
        this.seOpcionesRolesPK = seOpcionesRolesPK;
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

    public SeOpcionesMenu getSeOpcionesMenu() {
        return seOpcionesMenu;
    }

    public void setSeOpcionesMenu(SeOpcionesMenu seOpcionesMenu) {
        this.seOpcionesMenu = seOpcionesMenu;
    }

    public SeRoles getSeRoles() {
        return seRoles;
    }

    public void setSeRoles(SeRoles seRoles) {
        this.seRoles = seRoles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seOpcionesRolesPK != null ? seOpcionesRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeOpcionesRoles)) {
            return false;
        }
        SeOpcionesRoles other = (SeOpcionesRoles) object;
        if ((this.seOpcionesRolesPK == null && other.seOpcionesRolesPK != null) || (this.seOpcionesRolesPK != null && !this.seOpcionesRolesPK.equals(other.seOpcionesRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeOpcionesRoles[ seOpcionesRolesPK=" + seOpcionesRolesPK + " ]";
    }
    
}
