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
public class InPrestacionesPorServiciosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_prestacion")
    private long idPrestacion;
    @Basic(optional = false)
    @Column(name = "id_unidad_servicio")
    private long idUnidadServicio;

    public InPrestacionesPorServiciosPK() {
    }

    public InPrestacionesPorServiciosPK(long idPrestacion, long idUnidadServicio) {
        this.idPrestacion = idPrestacion;
        this.idUnidadServicio = idUnidadServicio;
    }

    public long getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(long idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public long getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(long idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrestacion;
        hash += (int) idUnidadServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InPrestacionesPorServiciosPK)) {
            return false;
        }
        InPrestacionesPorServiciosPK other = (InPrestacionesPorServiciosPK) object;
        if (this.idPrestacion != other.idPrestacion) {
            return false;
        }
        if (this.idUnidadServicio != other.idUnidadServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InPrestacionesPorServiciosPK[ idPrestacion=" + idPrestacion + ", idUnidadServicio=" + idUnidadServicio + " ]";
    }
    
}
