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
public class VeDetalleCajaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_caja")
    private long idDetalleCaja;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private long idCaja;

    public VeDetalleCajaPK() {
    }

    public VeDetalleCajaPK(long idDetalleCaja, long idCaja) {
        this.idDetalleCaja = idDetalleCaja;
        this.idCaja = idCaja;
    }

    public long getIdDetalleCaja() {
        return idDetalleCaja;
    }

    public void setIdDetalleCaja(long idDetalleCaja) {
        this.idDetalleCaja = idDetalleCaja;
    }

    public long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(long idCaja) {
        this.idCaja = idCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleCaja;
        hash += (int) idCaja;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeDetalleCajaPK)) {
            return false;
        }
        VeDetalleCajaPK other = (VeDetalleCajaPK) object;
        if (this.idDetalleCaja != other.idDetalleCaja) {
            return false;
        }
        if (this.idCaja != other.idCaja) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeDetalleCajaPK[ idDetalleCaja=" + idDetalleCaja + ", idCaja=" + idCaja + " ]";
    }
    
}
