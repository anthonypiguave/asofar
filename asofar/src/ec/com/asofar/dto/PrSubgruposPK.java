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
public class PrSubgruposPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_subgrupo")
    private long idSubgrupo;
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private long idGrupo;

    public PrSubgruposPK() {
    }

    public PrSubgruposPK(long idSubgrupo, long idGrupo) {
        this.idSubgrupo = idSubgrupo;
        this.idGrupo = idGrupo;
    }

    public long getIdSubgrupo() {
        return idSubgrupo;
    }

    public void setIdSubgrupo(long idSubgrupo) {
        this.idSubgrupo = idSubgrupo;
    }

    public long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(long idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSubgrupo;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrSubgruposPK)) {
            return false;
        }
        PrSubgruposPK other = (PrSubgruposPK) object;
        if (this.idSubgrupo != other.idSubgrupo) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dao.PrSubgruposPK[ idSubgrupo=" + idSubgrupo + ", idGrupo=" + idGrupo + " ]";
    }
    
}
