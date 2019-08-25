/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author admin
 */
@Embeddable
public class SeOpcionesRolesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_opciones_roles")
    private long idOpcionesRoles;
    @Basic(optional = false)
    @Column(name = "id_opciones_menu")
    private long idOpcionesMenu;
    @Basic(optional = false)
    @Column(name = "id_rol")
    private long idRol;

    public SeOpcionesRolesPK() {
    }

    public SeOpcionesRolesPK(long idOpcionesRoles, long idOpcionesMenu, long idRol) {
        this.idOpcionesRoles = idOpcionesRoles;
        this.idOpcionesMenu = idOpcionesMenu;
        this.idRol = idRol;
    }

    public long getIdOpcionesRoles() {
        return idOpcionesRoles;
    }

    public void setIdOpcionesRoles(long idOpcionesRoles) {
        this.idOpcionesRoles = idOpcionesRoles;
    }

    public long getIdOpcionesMenu() {
        return idOpcionesMenu;
    }

    public void setIdOpcionesMenu(long idOpcionesMenu) {
        this.idOpcionesMenu = idOpcionesMenu;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOpcionesRoles;
        hash += (int) idOpcionesMenu;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeOpcionesRolesPK)) {
            return false;
        }
        SeOpcionesRolesPK other = (SeOpcionesRolesPK) object;
        if (this.idOpcionesRoles != other.idOpcionesRoles) {
            return false;
        }
        if (this.idOpcionesMenu != other.idOpcionesMenu) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeOpcionesRolesPK[ idOpcionesRoles=" + idOpcionesRoles + ", idOpcionesMenu=" + idOpcionesMenu + ", idRol=" + idRol + " ]";
    }
    
}
