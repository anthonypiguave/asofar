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
public class PrPrestacionesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_prestacion")
    private long idPrestacion;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_poducto")
    private long idPoducto;

    public PrPrestacionesPK() {
    }

    public PrPrestacionesPK(long idPrestacion, long idEmpresa, long idPoducto) {
        this.idPrestacion = idPrestacion;
        this.idEmpresa = idEmpresa;
        this.idPoducto = idPoducto;
    }

    public long getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(long idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public long getIdPoducto() {
        return idPoducto;
    }

    public void setIdPoducto(long idPoducto) {
        this.idPoducto = idPoducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrestacion;
        hash += (int) idEmpresa;
        hash += (int) idPoducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrPrestacionesPK)) {
            return false;
        }
        PrPrestacionesPK other = (PrPrestacionesPK) object;
        if (this.idPrestacion != other.idPrestacion) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idPoducto != other.idPoducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrPrestacionesPK[ idPrestacion=" + idPrestacion + ", idEmpresa=" + idEmpresa + ", idPoducto=" + idPoducto + " ]";
    }
    
}
