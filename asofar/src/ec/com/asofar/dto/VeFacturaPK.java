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
public class VeFacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_factura")
    private long idFactura;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private long idCaja;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private long idCliente;

    public VeFacturaPK() {
    }

    public VeFacturaPK(long idFactura, long idCaja, long idEmpresa, long idSucursal, long idUsuario, long idCliente) {
        this.idFactura = idFactura;
        this.idCaja = idCaja;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(long idCaja) {
        this.idCaja = idCaja;
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

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFactura;
        hash += (int) idCaja;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) idUsuario;
        hash += (int) idCliente;
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
        if (this.idCaja != other.idCaja) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeFacturaPK[ idFactura=" + idFactura + ", idCaja=" + idCaja + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", idUsuario=" + idUsuario + ", idCliente=" + idCliente + " ]";
    }
    
}
