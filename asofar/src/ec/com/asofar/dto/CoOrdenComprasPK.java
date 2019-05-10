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
public class CoOrdenComprasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_orden_compra")
    private long idOrdenCompra;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;

    public CoOrdenComprasPK() {
    }

    public CoOrdenComprasPK(long idOrdenCompra, long idEmpresa, long idSucursal) {
        this.idOrdenCompra = idOrdenCompra;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public long getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(long idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
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
        hash += (int) idOrdenCompra;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoOrdenComprasPK)) {
            return false;
        }
        CoOrdenComprasPK other = (CoOrdenComprasPK) object;
        if (this.idOrdenCompra != other.idOrdenCompra) {
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
        return "ec.com.asofar.dao.CoOrdenComprasPK[ idOrdenCompra=" + idOrdenCompra + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
