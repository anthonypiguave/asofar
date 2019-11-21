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
 * @author jorge
 */
@Embeddable
public class PrProductoBodegaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_producto_bodega")
    private long idProductoBodega;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;
    @Basic(optional = false)
    @Column(name = "id_bodega")
    private long idBodega;
    @Basic(optional = false)
    @Column(name = "id_tipo_bodega")
    private long idTipoBodega;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;

    public PrProductoBodegaPK() {
    }

    public PrProductoBodegaPK(long idProductoBodega, long idProducto, long idBodega, long idTipoBodega, long idEmpresa, long idSucursal) {
        this.idProductoBodega = idProductoBodega;
        this.idProducto = idProducto;
        this.idBodega = idBodega;
        this.idTipoBodega = idTipoBodega;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public long getIdProductoBodega() {
        return idProductoBodega;
    }

    public void setIdProductoBodega(long idProductoBodega) {
        this.idProductoBodega = idProductoBodega;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public long getIdTipoBodega() {
        return idTipoBodega;
    }

    public void setIdTipoBodega(long idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
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
        hash += (int) idProductoBodega;
        hash += (int) idProducto;
        hash += (int) idBodega;
        hash += (int) idTipoBodega;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrProductoBodegaPK)) {
            return false;
        }
        PrProductoBodegaPK other = (PrProductoBodegaPK) object;
        if (this.idProductoBodega != other.idProductoBodega) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idBodega != other.idBodega) {
            return false;
        }
        if (this.idTipoBodega != other.idTipoBodega) {
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
        return "ec.com.asofar.dto.PrProductoBodegaPK[ idProductoBodega=" + idProductoBodega + ", idProducto=" + idProducto + ", idBodega=" + idBodega + ", idTipoBodega=" + idTipoBodega + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
