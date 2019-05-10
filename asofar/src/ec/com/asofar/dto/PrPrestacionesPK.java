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

    public PrPrestacionesPK() {
    }

    public PrPrestacionesPK(long idPrestacion, long idEmpresa) {
        this.idPrestacion = idPrestacion;
        this.idEmpresa = idEmpresa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrestacion;
        hash += (int) idEmpresa;
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
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dao.PrPrestacionesPK[ idPrestacion=" + idPrestacion + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
