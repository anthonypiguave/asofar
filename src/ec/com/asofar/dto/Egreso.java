/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nuevouser
 */
@Entity
@Table(name = "egreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e")
    , @NamedQuery(name = "Egreso.findByIdegreso", query = "SELECT e FROM Egreso e WHERE e.idegreso = :idegreso")
    , @NamedQuery(name = "Egreso.findByTotal", query = "SELECT e FROM Egreso e WHERE e.total = :total")})
public class Egreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idegreso")
    private Integer idegreso;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @JoinColumn(name = "iddetalle_egreso", referencedColumnName = "iddetalle_egreso")
    @ManyToOne(optional = false)
    private DetalleEgreso iddetalleEgreso;

    public Egreso() {
    }

    public Egreso(Integer idegreso) {
        this.idegreso = idegreso;
    }

    public Egreso(Integer idegreso, double total) {
        this.idegreso = idegreso;
        this.total = total;
    }

    public Integer getIdegreso() {
        return idegreso;
    }

    public void setIdegreso(Integer idegreso) {
        this.idegreso = idegreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public DetalleEgreso getIddetalleEgreso() {
        return iddetalleEgreso;
    }

    public void setIddetalleEgreso(DetalleEgreso iddetalleEgreso) {
        this.iddetalleEgreso = iddetalleEgreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idegreso != null ? idegreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egreso)) {
            return false;
        }
        Egreso other = (Egreso) object;
        if ((this.idegreso == null && other.idegreso != null) || (this.idegreso != null && !this.idegreso.equals(other.idegreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.Egreso[ idegreso=" + idegreso + " ]";
    }
    
}
