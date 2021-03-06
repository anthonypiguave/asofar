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
 * @author usuario
 */
@Embeddable
public class CoDetItemsCotizacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_cotizacion")
    private long idCotizacion;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "linea_detalle")
    private long lineaDetalle;

    public CoDetItemsCotizacionPK() {
    }

    public CoDetItemsCotizacionPK(long idCotizacion, long idEmpresa, long idSucursal, long lineaDetalle) {
        this.idCotizacion = idCotizacion;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.lineaDetalle = lineaDetalle;
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

    public long getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(long lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCotizacion;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) lineaDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetItemsCotizacionPK)) {
            return false;
        }
        CoDetItemsCotizacionPK other = (CoDetItemsCotizacionPK) object;
        if (this.idCotizacion != other.idCotizacion) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.lineaDetalle != other.lineaDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetItemsCotizacionPK[ idCotizacion=" + idCotizacion + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", lineaDetalle=" + lineaDetalle + " ]";
    }
    
}
