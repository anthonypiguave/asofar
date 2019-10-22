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
@Table(name = "ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
    , @NamedQuery(name = "Ingreso.findByIdingreso", query = "SELECT i FROM Ingreso i WHERE i.idingreso = :idingreso")
    , @NamedQuery(name = "Ingreso.findByTotal", query = "SELECT i FROM Ingreso i WHERE i.total = :total")})
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idingreso")
    private Integer idingreso;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @JoinColumn(name = "iddetalle_ingreso", referencedColumnName = "iddetalle_ingreso")
    @ManyToOne(optional = false)
    private DetalleIngreso iddetalleIngreso;

    public Ingreso() {
    }

    public Ingreso(Integer idingreso) {
        this.idingreso = idingreso;
    }

    public Ingreso(Integer idingreso, double total) {
        this.idingreso = idingreso;
        this.total = total;
    }

    public Integer getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(Integer idingreso) {
        this.idingreso = idingreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public DetalleIngreso getIddetalleIngreso() {
        return iddetalleIngreso;
    }

    public void setIddetalleIngreso(DetalleIngreso iddetalleIngreso) {
        this.iddetalleIngreso = iddetalleIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idingreso != null ? idingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.idingreso == null && other.idingreso != null) || (this.idingreso != null && !this.idingreso.equals(other.idingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.Ingreso[ idingreso=" + idingreso + " ]";
    }
    
}
