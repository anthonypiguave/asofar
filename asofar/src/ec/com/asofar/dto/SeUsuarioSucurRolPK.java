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
public class SeUsuarioSucurRolPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_usuario_sucur_rol")
    private long idUsuarioSucurRol;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;

    public SeUsuarioSucurRolPK() {
    }

    public SeUsuarioSucurRolPK(long idUsuarioSucurRol, long idSucursal, long idEmpresa) {
        this.idUsuarioSucurRol = idUsuarioSucurRol;
        this.idSucursal = idSucursal;
        this.idEmpresa = idEmpresa;
    }

    public long getIdUsuarioSucurRol() {
        return idUsuarioSucurRol;
    }

    public void setIdUsuarioSucurRol(long idUsuarioSucurRol) {
        this.idUsuarioSucurRol = idUsuarioSucurRol;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuarioSucurRol;
        hash += (int) idSucursal;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeUsuarioSucurRolPK)) {
            return false;
        }
        SeUsuarioSucurRolPK other = (SeUsuarioSucurRolPK) object;
        if (this.idUsuarioSucurRol != other.idUsuarioSucurRol) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeUsuarioSucurRolPK[ idUsuarioSucurRol=" + idUsuarioSucurRol + ", idSucursal=" + idSucursal + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
