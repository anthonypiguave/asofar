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
 * @author Usuario
 */
@Embeddable
public class PrArticuloPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_articulo")
    private long idArticulo;
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private long idGrupo;
    @Basic(optional = false)
    @Column(name = "id_subgrupo")
    private long idSubgrupo;

    public PrArticuloPK() {
    }

    public PrArticuloPK(long idArticulo, long idGrupo, long idSubgrupo) {
        this.idArticulo = idArticulo;
        this.idGrupo = idGrupo;
        this.idSubgrupo = idSubgrupo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArticulo;
        hash += (int) idGrupo;
        hash += (int) idSubgrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrArticuloPK)) {
            return false;
        }
        PrArticuloPK other = (PrArticuloPK) object;
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        if (this.idSubgrupo != other.idSubgrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrArticuloPK[ idArticulo=" + idArticulo + ", idGrupo=" + idGrupo + ", idSubgrupo=" + idSubgrupo + " ]";
    }
    
}
