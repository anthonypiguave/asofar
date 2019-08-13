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
public class CoDetalleOrdenPedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_orden_pedido")
    private long idDetalleOrdenPedido;
    @Basic(optional = false)
    @Column(name = "id_orden_pedido")
    private long idOrdenPedido;
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

    public CoDetalleOrdenPedidoPK() {
    }

    public CoDetalleOrdenPedidoPK(long idDetalleOrdenPedido, long idOrdenPedido, long idEmpresa, long idSurcusal, long lineaDetalle, long idProducto) {
        this.idDetalleOrdenPedido = idDetalleOrdenPedido;
        this.idOrdenPedido = idOrdenPedido;
        this.idEmpresa = idEmpresa;
        this.idSurcusal = idSurcusal;
        this.lineaDetalle = lineaDetalle;
        this.idProducto = idProducto;
    }

    public long getIdDetalleOrdenPedido() {
        return idDetalleOrdenPedido;
    }

    public void setIdDetalleOrdenPedido(long idDetalleOrdenPedido) {
        this.idDetalleOrdenPedido = idDetalleOrdenPedido;
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
        hash += (int) idDetalleOrdenPedido;
        hash += (int) idOrdenPedido;
        hash += (int) idEmpresa;
        hash += (int) idSurcusal;
        hash += (int) lineaDetalle;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenPedidoPK)) {
            return false;
        }
        CoDetalleOrdenPedidoPK other = (CoDetalleOrdenPedidoPK) object;
        if (this.idDetalleOrdenPedido != other.idDetalleOrdenPedido) {
            return false;
        }
        if (this.idOrdenPedido != other.idOrdenPedido) {
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
        return "ec.com.asofar.dto.CoDetalleOrdenPedidoPK[ idDetalleOrdenPedido=" + idDetalleOrdenPedido + ", idOrdenPedido=" + idOrdenPedido + ", idEmpresa=" + idEmpresa + ", idSurcusal=" + idSurcusal + ", lineaDetalle=" + lineaDetalle + ", idProducto=" + idProducto + " ]";
    }
    
}
