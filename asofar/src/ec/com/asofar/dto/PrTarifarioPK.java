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
 * @author admin
 */
@Embeddable
public class PrTarifarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_tarifario")
    private long idTarifario;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_surcusal")
    private long idSurcusal;

    public PrTarifarioPK() {
    }

    public PrTarifarioPK(long idTarifario, long idEmpresa, long idSurcusal) {
        this.idTarifario = idTarifario;
        this.idEmpresa = idEmpresa;
        this.idSurcusal = idSurcusal;
    }

    public long getIdTarifario() {
        return idTarifario;
    }

    public void setIdTarifario(long idTarifario) {
        this.idTarifario = idTarifario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTarifario;
        hash += (int) idEmpresa;
        hash += (int) idSurcusal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrTarifarioPK)) {
            return false;
        }
        PrTarifarioPK other = (PrTarifarioPK) object;
        if (this.idTarifario != other.idTarifario) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSurcusal != other.idSurcusal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrTarifarioPK[ idTarifario=" + idTarifario + ", idEmpresa=" + idEmpresa + ", idSurcusal=" + idSurcusal + " ]";
    }
    
}
