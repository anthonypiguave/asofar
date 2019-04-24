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
 * @author ADMIN
 */
@Embeddable
public class InMovimientosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_movimientos")
    private long idMovimientos;
    @Basic(optional = false)
    @Column(name = "id_numero_documento")
    private long idNumeroDocumento;
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private long idTipoDocumento;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;

    public InMovimientosPK() {
    }

    public InMovimientosPK(long idMovimientos, long idNumeroDocumento, long idTipoDocumento, long idEmpresa) {
        this.idMovimientos = idMovimientos;
        this.idNumeroDocumento = idNumeroDocumento;
        this.idTipoDocumento = idTipoDocumento;
        this.idEmpresa = idEmpresa;
    }

    public long getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(long idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public long getIdNumeroDocumento() {
        return idNumeroDocumento;
    }

    public void setIdNumeroDocumento(long idNumeroDocumento) {
        this.idNumeroDocumento = idNumeroDocumento;
    }

    public long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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
        hash += (int) idMovimientos;
        hash += (int) idNumeroDocumento;
        hash += (int) idTipoDocumento;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InMovimientosPK)) {
            return false;
        }
        InMovimientosPK other = (InMovimientosPK) object;
        if (this.idMovimientos != other.idMovimientos) {
            return false;
        }
        if (this.idNumeroDocumento != other.idNumeroDocumento) {
            return false;
        }
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InMovimientosPK[ idMovimientos=" + idMovimientos + ", idNumeroDocumento=" + idNumeroDocumento + ", idTipoDocumento=" + idTipoDocumento + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
