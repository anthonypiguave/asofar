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
 * @author ms24m
 */
@Embeddable
public class VeFacturaDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_factura_detalle")
    private long idFacturaDetalle;
    @Basic(optional = false)
    @Column(name = "id_factura")
    private long idFactura;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_prestaciones")
    private long idPrestaciones;
    @Basic(optional = false)
    @Column(name = "id_unidad_servicio")
    private long idUnidadServicio;

    public VeFacturaDetallePK() {
    }

    public VeFacturaDetallePK(long idFacturaDetalle, long idFactura, long idEmpresa, long idSucursal, long idPrestaciones, long idUnidadServicio) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.idFactura = idFactura;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.idPrestaciones = idPrestaciones;
        this.idUnidadServicio = idUnidadServicio;
    }

    public long getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(long idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
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

    public long getIdPrestaciones() {
        return idPrestaciones;
    }

    public void setIdPrestaciones(long idPrestaciones) {
        this.idPrestaciones = idPrestaciones;
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
        hash += (int) idFacturaDetalle;
        hash += (int) idFactura;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) idPrestaciones;
        hash += (int) idUnidadServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeFacturaDetallePK)) {
            return false;
        }
        VeFacturaDetallePK other = (VeFacturaDetallePK) object;
        if (this.idFacturaDetalle != other.idFacturaDetalle) {
            return false;
        }
        if (this.idFactura != other.idFactura) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.idPrestaciones != other.idPrestaciones) {
            return false;
        }
        if (this.idUnidadServicio != other.idUnidadServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeFacturaDetallePK[ idFacturaDetalle=" + idFacturaDetalle + ", idFactura=" + idFactura + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", idPrestaciones=" + idPrestaciones + ", idUnidadServicio=" + idUnidadServicio + " ]";
    }
    
}
