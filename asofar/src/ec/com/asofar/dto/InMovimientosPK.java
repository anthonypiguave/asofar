/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario
 */
@Embeddable
public class InMovimientosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_movimientos")
    private long idMovimientos;
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private long idTipoDocumento;
    @Basic(optional = false)
    @Column(name = "anio_documento")
    @Temporal(TemporalType.DATE)
    private Date anioDocumento;

    public InMovimientosPK() {
    }

    public InMovimientosPK(long idMovimientos, long idTipoDocumento, Date anioDocumento) {
        this.idMovimientos = idMovimientos;
        this.idTipoDocumento = idTipoDocumento;
        this.anioDocumento = anioDocumento;
    }

    public long getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(long idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Date getAnioDocumento() {
        return anioDocumento;
    }

    public void setAnioDocumento(Date anioDocumento) {
        this.anioDocumento = anioDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimientos;
        hash += (int) idTipoDocumento;
        hash += (anioDocumento != null ? anioDocumento.hashCode() : 0);
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
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        if ((this.anioDocumento == null && other.anioDocumento != null) || (this.anioDocumento != null && !this.anioDocumento.equals(other.anioDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InMovimientosPK[ idMovimientos=" + idMovimientos + ", idTipoDocumento=" + idTipoDocumento + ", anioDocumento=" + anioDocumento + " ]";
    }
    
}
