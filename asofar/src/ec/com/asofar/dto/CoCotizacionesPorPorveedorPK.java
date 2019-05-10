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
public class CoCotizacionesPorPorveedorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_cotizaciones_por_porveedor")
    private long idCotizacionesPorPorveedor;
    @Basic(optional = false)
    @Column(name = "id_cotizacion")
    private long idCotizacion;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;

    public CoCotizacionesPorPorveedorPK() {
    }

    public CoCotizacionesPorPorveedorPK(long idCotizacionesPorPorveedor, long idCotizacion, long idEmpresa, long idSucursal) {
        this.idCotizacionesPorPorveedor = idCotizacionesPorPorveedor;
        this.idCotizacion = idCotizacion;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public long getIdCotizacionesPorPorveedor() {
        return idCotizacionesPorPorveedor;
    }

    public void setIdCotizacionesPorPorveedor(long idCotizacionesPorPorveedor) {
        this.idCotizacionesPorPorveedor = idCotizacionesPorPorveedor;
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
        hash += (int) idCotizacionesPorPorveedor;
        hash += (int) idCotizacion;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoCotizacionesPorPorveedorPK)) {
            return false;
        }
        CoCotizacionesPorPorveedorPK other = (CoCotizacionesPorPorveedorPK) object;
        if (this.idCotizacionesPorPorveedor != other.idCotizacionesPorPorveedor) {
            return false;
        }
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
        return "ec.com.asofar.dto.CoCotizacionesPorPorveedorPK[ idCotizacionesPorPorveedor=" + idCotizacionesPorPorveedor + ", idCotizacion=" + idCotizacion + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
