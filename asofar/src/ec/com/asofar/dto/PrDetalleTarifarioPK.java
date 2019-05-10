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
public class PrDetalleTarifarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_tarifario")
    private long idDetalleTarifario;
    @Basic(optional = false)
    @Column(name = "id_tarifario")
    private long idTarifario;
    @Basic(optional = false)
    @Column(name = "id_prestacion")
    private long idPrestacion;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_surcusal")
    private long idSurcusal;
    @Basic(optional = false)
    @Column(name = "id_unidad_servicio")
    private long idUnidadServicio;

    public PrDetalleTarifarioPK() {
    }

    public PrDetalleTarifarioPK(long idDetalleTarifario, long idTarifario, long idPrestacion, long idEmpresa, long idSurcusal, long idUnidadServicio) {
        this.idDetalleTarifario = idDetalleTarifario;
        this.idTarifario = idTarifario;
        this.idPrestacion = idPrestacion;
        this.idEmpresa = idEmpresa;
        this.idSurcusal = idSurcusal;
        this.idUnidadServicio = idUnidadServicio;
    }

    public long getIdDetalleTarifario() {
        return idDetalleTarifario;
    }

    public void setIdDetalleTarifario(long idDetalleTarifario) {
        this.idDetalleTarifario = idDetalleTarifario;
    }

    public long getIdTarifario() {
        return idTarifario;
    }

    public void setIdTarifario(long idTarifario) {
        this.idTarifario = idTarifario;
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

    public long getIdSurcusal() {
        return idSurcusal;
    }

    public void setIdSurcusal(long idSurcusal) {
        this.idSurcusal = idSurcusal;
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
        hash += (int) idDetalleTarifario;
        hash += (int) idTarifario;
        hash += (int) idPrestacion;
        hash += (int) idEmpresa;
        hash += (int) idSurcusal;
        hash += (int) idUnidadServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrDetalleTarifarioPK)) {
            return false;
        }
        PrDetalleTarifarioPK other = (PrDetalleTarifarioPK) object;
        if (this.idDetalleTarifario != other.idDetalleTarifario) {
            return false;
        }
        if (this.idTarifario != other.idTarifario) {
            return false;
        }
        if (this.idPrestacion != other.idPrestacion) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSurcusal != other.idSurcusal) {
            return false;
        }
        if (this.idUnidadServicio != other.idUnidadServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dao.PrDetalleTarifarioPK[ idDetalleTarifario=" + idDetalleTarifario + ", idTarifario=" + idTarifario + ", idPrestacion=" + idPrestacion + ", idEmpresa=" + idEmpresa + ", idSurcusal=" + idSurcusal + ", idUnidadServicio=" + idUnidadServicio + " ]";
    }
    
}
