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
public class InKardexPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_kardex")
    private long idKardex;
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
    @Column(name = "id_producto")
    private long idProducto;
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private long idTipoDocumento;

    public InKardexPK() {
    }

    public InKardexPK(long idKardex, long idEmpresa, long idSucursal, long idBodega, long idProducto, long idTipoDocumento) {
        this.idKardex = idKardex;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.idBodega = idBodega;
        this.idProducto = idProducto;
        this.idTipoDocumento = idTipoDocumento;
    }

    public long getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(long idKardex) {
        this.idKardex = idKardex;
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

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idKardex;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) idBodega;
        hash += (int) idProducto;
        hash += (int) idTipoDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InKardexPK)) {
            return false;
        }
        InKardexPK other = (InKardexPK) object;
        if (this.idKardex != other.idKardex) {
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
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InKardexPK[ idKardex=" + idKardex + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", idBodega=" + idBodega + ", idProducto=" + idProducto + ", idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
