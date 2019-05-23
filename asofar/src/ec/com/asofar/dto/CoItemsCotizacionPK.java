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
 * @author ADMIN
 */
@Embeddable
public class CoItemsCotizacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_cotizacion")
    private long idCotizacion;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;

    public CoItemsCotizacionPK() {
    }

    public CoItemsCotizacionPK(long idCotizacion, long idEmpresa, long idSucursal) {
        this.idCotizacion = idCotizacion;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCotizacion;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoItemsCotizacionPK)) {
            return false;
        }
        CoItemsCotizacionPK other = (CoItemsCotizacionPK) object;
        if (this.idCotizacion != other.idCotizacion) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoItemsCotizacionPK[ idCotizacion=" + idCotizacion + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
