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
 * @author nuevouser
 */
@Embeddable
public class PrProductosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_articulo")
    private long idArticulo;
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private long idGrupo;
    @Basic(optional = false)
    @Column(name = "id_subgrupo")
    private long idSubgrupo;
    @Basic(optional = false)
    @Column(name = "id_tipo_presentacion")
    private long idTipoPresentacion;
    @Basic(optional = false)
    @Column(name = "id_tipo_medidas")
    private long idTipoMedidas;

    public PrProductosPK() {
    }

    public PrProductosPK(long idProducto, long idEmpresa, long idArticulo, long idGrupo, long idSubgrupo, long idTipoPresentacion, long idTipoMedidas) {
        this.idProducto = idProducto;
        this.idEmpresa = idEmpresa;
        this.idArticulo = idArticulo;
        this.idGrupo = idGrupo;
        this.idSubgrupo = idSubgrupo;
        this.idTipoPresentacion = idTipoPresentacion;
        this.idTipoMedidas = idTipoMedidas;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
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

    public long getIdTipoPresentacion() {
        return idTipoPresentacion;
    }

    public void setIdTipoPresentacion(long idTipoPresentacion) {
        this.idTipoPresentacion = idTipoPresentacion;
    }

    public long getIdTipoMedidas() {
        return idTipoMedidas;
    }

    public void setIdTipoMedidas(long idTipoMedidas) {
        this.idTipoMedidas = idTipoMedidas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idEmpresa;
        hash += (int) idArticulo;
        hash += (int) idGrupo;
        hash += (int) idSubgrupo;
        hash += (int) idTipoPresentacion;
        hash += (int) idTipoMedidas;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrProductosPK)) {
            return false;
        }
        PrProductosPK other = (PrProductosPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        if (this.idSubgrupo != other.idSubgrupo) {
            return false;
        }
        if (this.idTipoPresentacion != other.idTipoPresentacion) {
            return false;
        }
        if (this.idTipoMedidas != other.idTipoMedidas) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrProductosPK[ idProducto=" + idProducto + ", idEmpresa=" + idEmpresa + ", idArticulo=" + idArticulo + ", idGrupo=" + idGrupo + ", idSubgrupo=" + idSubgrupo + ", idTipoPresentacion=" + idTipoPresentacion + ", idTipoMedidas=" + idTipoMedidas + " ]";
    }
    
}
