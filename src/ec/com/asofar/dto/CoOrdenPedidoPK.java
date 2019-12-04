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
 * @author admini
 */
@Embeddable
public class CoOrdenPedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_orden_pedido")
    private long idOrdenPedido;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;

    public CoOrdenPedidoPK() {
    }

    public CoOrdenPedidoPK(long idOrdenPedido, long idEmpresa, long idSucursal) {
        this.idOrdenPedido = idOrdenPedido;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public long getIdOrdenPedido() {
        return idOrdenPedido;
    }

    public void setIdOrdenPedido(long idOrdenPedido) {
        this.idOrdenPedido = idOrdenPedido;
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
        hash += (int) idOrdenPedido;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoOrdenPedidoPK)) {
            return false;
        }
        CoOrdenPedidoPK other = (CoOrdenPedidoPK) object;
        if (this.idOrdenPedido != other.idOrdenPedido) {
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
        return "ec.com.asofar.dto.CoOrdenPedidoPK[ idOrdenPedido=" + idOrdenPedido + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
