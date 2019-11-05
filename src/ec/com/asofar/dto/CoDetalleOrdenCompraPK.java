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
public class CoDetalleOrdenCompraPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_orden_compra")
    private long idDetalleOrdenCompra;
    @Basic(optional = false)
    @Column(name = "id_orden_compra")
    private long idOrdenCompra;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_surcusal")
    private long idSurcusal;
    @Basic(optional = false)
    @Column(name = "linea_detalle")
    private long lineaDetalle;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;

    public CoDetalleOrdenCompraPK() {
    }

    public CoDetalleOrdenCompraPK(long idDetalleOrdenCompra, long idOrdenCompra, long idEmpresa, long idSurcusal, long lineaDetalle, long idProducto) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
        this.idOrdenCompra = idOrdenCompra;
        this.idEmpresa = idEmpresa;
        this.idSurcusal = idSurcusal;
        this.lineaDetalle = lineaDetalle;
        this.idProducto = idProducto;
    }

    public long getIdDetalleOrdenCompra() {
        return idDetalleOrdenCompra;
    }

    public void setIdDetalleOrdenCompra(long idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
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

    public long getIdSurcusal() {
        return idSurcusal;
    }

    public void setIdSurcusal(long idSurcusal) {
        this.idSurcusal = idSurcusal;
    }

    public long getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(long lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
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
        hash += (int) idDetalleOrdenCompra;
        hash += (int) idOrdenCompra;
        hash += (int) idEmpresa;
        hash += (int) idSurcusal;
        hash += (int) lineaDetalle;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenCompraPK)) {
            return false;
        }
        CoDetalleOrdenCompraPK other = (CoDetalleOrdenCompraPK) object;
        if (this.idDetalleOrdenCompra != other.idDetalleOrdenCompra) {
            return false;
        }
        if (this.idOrdenCompra != other.idOrdenCompra) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSurcusal != other.idSurcusal) {
            return false;
        }
        if (this.lineaDetalle != other.lineaDetalle) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenCompraPK[ idDetalleOrdenCompra=" + idDetalleOrdenCompra + ", idOrdenCompra=" + idOrdenCompra + ", idEmpresa=" + idEmpresa + ", idSurcusal=" + idSurcusal + ", lineaDetalle=" + lineaDetalle + ", idProducto=" + idProducto + " ]";
    }
    
}
