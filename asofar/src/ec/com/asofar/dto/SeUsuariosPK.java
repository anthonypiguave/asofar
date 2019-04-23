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
 * @author admin1
 */
@Embeddable
public class SeUsuariosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_usuario")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "id_persona")
    private long idPersona;
    @Basic(optional = false)
    @Column(name = "id_tipo_persona")
    private long idTipoPersona;

    public SeUsuariosPK() {
    }

    public SeUsuariosPK(long idUsuario, long idPersona, long idTipoPersona) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
        this.idTipoPersona = idTipoPersona;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idPersona;
        hash += (int) idTipoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeUsuariosPK)) {
            return false;
        }
        SeUsuariosPK other = (SeUsuariosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idTipoPersona != other.idTipoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeUsuariosPK[ idUsuario=" + idUsuario + ", idPersona=" + idPersona + ", idTipoPersona=" + idTipoPersona + " ]";
    }
    
}
