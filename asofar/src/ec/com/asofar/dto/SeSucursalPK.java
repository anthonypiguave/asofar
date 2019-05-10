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
public class SeSucursalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;

    public SeSucursalPK() {
    }

    public SeSucursalPK(long idSucursal, long idEmpresa) {
        this.idSucursal = idSucursal;
        this.idEmpresa = idEmpresa;
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
        hash += (int) idSucursal;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeSucursalPK)) {
            return false;
        }
        SeSucursalPK other = (SeSucursalPK) object;
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
        return "ec.com.asofar.dto.SeSucursalPK[ idSucursal=" + idSucursal + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
