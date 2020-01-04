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
 * @author Usuario
 */
@Embeddable
public class PrProductoBodegaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_producto_bodega")
    private long idProductoBodega;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;

    public PrProductoBodegaPK() {
    }

    public PrProductoBodegaPK(long idProductoBodega, long idProducto) {
        this.idProductoBodega = idProductoBodega;
        this.idProducto = idProducto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProductoBodega;
        hash += (int) idProducto;
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
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrProductoBodegaPK[ idProductoBodega=" + idProductoBodega + ", idProducto=" + idProducto + " ]";
    }
    
}
