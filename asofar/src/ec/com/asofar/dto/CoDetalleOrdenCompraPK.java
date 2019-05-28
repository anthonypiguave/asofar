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
public class CoDetalleOrdenCompraPK implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private long idGrupo;
    @Basic(optional = false)
    @Column(name = "id_subgrupo")
    private long idSubgrupo;
    @Basic(optional = false)
    @Column(name = "id_articulo")
    private long idArticulo;
    @Basic(optional = false)
    @Column(name = "id_tipo_medidas")
    private long idTipoMedidas;
    @Basic(optional = false)
    @Column(name = "id_tipo_presentacion")
    private long idTipoPresentacion;

    public CoDetalleOrdenCompraPK() {
    }

    public CoDetalleOrdenCompraPK(long idOrdenCompra, long idEmpresa, long idSurcusal, long lineaDetalle, long idProducto, long idGrupo, long idSubgrupo, long idArticulo, long idTipoMedidas, long idTipoPresentacion) {
        this.idOrdenCompra = idOrdenCompra;
        this.idEmpresa = idEmpresa;
        this.idSurcusal = idSurcusal;
        this.lineaDetalle = lineaDetalle;
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
        this.idSubgrupo = idSubgrupo;
        this.idArticulo = idArticulo;
        this.idTipoMedidas = idTipoMedidas;
        this.idTipoPresentacion = idTipoPresentacion;
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

    public long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public long getIdSubgrupo() {
        return idSubgrupo;
    }

    public void setIdSubgrupo(long idSubgrupo) {
        this.idSubgrupo = idSubgrupo;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public long getIdTipoMedidas() {
        return idTipoMedidas;
    }

    public void setIdTipoMedidas(long idTipoMedidas) {
        this.idTipoMedidas = idTipoMedidas;
    }

    public long getIdTipoPresentacion() {
        return idTipoPresentacion;
    }

    public void setIdTipoPresentacion(long idTipoPresentacion) {
        this.idTipoPresentacion = idTipoPresentacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOrdenCompra;
        hash += (int) idEmpresa;
        hash += (int) idSurcusal;
        hash += (int) lineaDetalle;
        hash += (int) idProducto;
        hash += (int) idGrupo;
        hash += (int) idSubgrupo;
        hash += (int) idArticulo;
        hash += (int) idTipoMedidas;
        hash += (int) idTipoPresentacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenCompraPK)) {
            return false;
        }
        CoDetalleOrdenCompraPK other = (CoDetalleOrdenCompraPK) object;
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
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        if (this.idSubgrupo != other.idSubgrupo) {
            return false;
        }
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if (this.idTipoMedidas != other.idTipoMedidas) {
            return false;
        }
        if (this.idTipoPresentacion != other.idTipoPresentacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenCompraPK[ idOrdenCompra=" + idOrdenCompra + ", idEmpresa=" + idEmpresa + ", idSurcusal=" + idSurcusal + ", lineaDetalle=" + lineaDetalle + ", idProducto=" + idProducto + ", idGrupo=" + idGrupo + ", idSubgrupo=" + idSubgrupo + ", idArticulo=" + idArticulo + ", idTipoMedidas=" + idTipoMedidas + ", idTipoPresentacion=" + idTipoPresentacion + " ]";
    }
    
}
