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
public class CoDetallesTarifaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_tarifa")
    private long idTarifa;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_prestacion")
    private long idPrestacion;
    @Basic(optional = false)
    @Column(name = "id_unidad_servicio")
    private long idUnidadServicio;

    public CoDetallesTarifaPK() {
    }

    public CoDetallesTarifaPK(long idTarifa, long idEmpresa, long idSucursal, long idPrestacion, long idUnidadServicio) {
        this.idTarifa = idTarifa;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.idPrestacion = idPrestacion;
        this.idUnidadServicio = idUnidadServicio;
    }

    public long getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(long idTarifa) {
        this.idTarifa = idTarifa;
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

    public long getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(long idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public long getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(long idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTarifa;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) idPrestacion;
        hash += (int) idUnidadServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetallesTarifaPK)) {
            return false;
        }
        CoDetallesTarifaPK other = (CoDetallesTarifaPK) object;
        if (this.idTarifa != other.idTarifa) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.idPrestacion != other.idPrestacion) {
            return false;
        }
        if (this.idUnidadServicio != other.idUnidadServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetallesTarifaPK[ idTarifa=" + idTarifa + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", idPrestacion=" + idPrestacion + ", idUnidadServicio=" + idUnidadServicio + " ]";
    }
    
}
