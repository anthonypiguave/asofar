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
public class VeFacturaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_factura")
    private long idFactura;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;

    public VeFacturaPK() {
    }

    public VeFacturaPK(long idFactura, long idEmpresa) {
        this.idFactura = idFactura;
        this.idEmpresa = idEmpresa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFactura;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeFacturaPK)) {
            return false;
        }
        VeFacturaPK other = (VeFacturaPK) object;
        if (this.idFactura != other.idFactura) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeFacturaPK[ idFactura=" + idFactura + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
