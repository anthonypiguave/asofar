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
public class InMovimientosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_movimientos")
    private long idMovimientos;
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private long idTipoDocumento;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "id_bodega")
    private long idBodega;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private long idProveedor;
    @Basic(optional = false)
    @Column(name = "id_orden_compra")
    private long idOrdenCompra;

    public InMovimientosPK() {
    }

    public InMovimientosPK(long idMovimientos, long idTipoDocumento, long idEmpresa, long idSucursal, long idBodega, long idUsuario, long idProveedor, long idOrdenCompra) {
        this.idMovimientos = idMovimientos;
        this.idTipoDocumento = idTipoDocumento;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.idBodega = idBodega;
        this.idUsuario = idUsuario;
        this.idProveedor = idProveedor;
        this.idOrdenCompra = idOrdenCompra;
    }

    public long getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(long idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public long getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(long idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimientos;
        hash += (int) idTipoDocumento;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) idBodega;
        hash += (int) idUsuario;
        hash += (int) idProveedor;
        hash += (int) idOrdenCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InMovimientosPK)) {
            return false;
        }
        InMovimientosPK other = (InMovimientosPK) object;
        if (this.idMovimientos != other.idMovimientos) {
            return false;
        }
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.idBodega != other.idBodega) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idProveedor != other.idProveedor) {
            return false;
        }
        if (this.idOrdenCompra != other.idOrdenCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InMovimientosPK[ idMovimientos=" + idMovimientos + ", idTipoDocumento=" + idTipoDocumento + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", idBodega=" + idBodega + ", idUsuario=" + idUsuario + ", idProveedor=" + idProveedor + ", idOrdenCompra=" + idOrdenCompra + " ]";
    }
    
}
