/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nuevouser
 */
@Entity
@Table(name = "detalle_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleIngreso.findAll", query = "SELECT d FROM DetalleIngreso d")
    , @NamedQuery(name = "DetalleIngreso.findByIddetalleIngreso", query = "SELECT d FROM DetalleIngreso d WHERE d.iddetalleIngreso = :iddetalleIngreso")
    , @NamedQuery(name = "DetalleIngreso.findByCantidad", query = "SELECT d FROM DetalleIngreso d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleIngreso.findByCosto", query = "SELECT d FROM DetalleIngreso d WHERE d.costo = :costo")
    , @NamedQuery(name = "DetalleIngreso.findByFecha", query = "SELECT d FROM DetalleIngreso d WHERE d.fecha = :fecha")})
public class DetalleIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_ingreso")
    private Integer iddetalleIngreso;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumn(name = "idadministrador", referencedColumnName = "idadministrador")
    @ManyToOne(optional = false)
    private Administrador idadministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetalleIngreso")
    private List<Ingreso> ingresoList;

    public DetalleIngreso() {
    }

    public DetalleIngreso(Integer iddetalleIngreso) {
        this.iddetalleIngreso = iddetalleIngreso;
    }

    public DetalleIngreso(Integer iddetalleIngreso, int cantidad, double costo, Date fecha) {
        this.iddetalleIngreso = iddetalleIngreso;
        this.cantidad = cantidad;
        this.costo = costo;
        this.fecha = fecha;
    }

    public Integer getIddetalleIngreso() {
        return iddetalleIngreso;
    }

    public void setIddetalleIngreso(Integer iddetalleIngreso) {
        this.iddetalleIngreso = iddetalleIngreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Administrador getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Administrador idadministrador) {
        this.idadministrador = idadministrador;
    }

    @XmlTransient
    public List<Ingreso> getIngresoList() {
        return ingresoList;
    }

    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleIngreso != null ? iddetalleIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleIngreso)) {
            return false;
        }
        DetalleIngreso other = (DetalleIngreso) object;
        if ((this.iddetalleIngreso == null && other.iddetalleIngreso != null) || (this.iddetalleIngreso != null && !this.iddetalleIngreso.equals(other.iddetalleIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.DetalleIngreso[ iddetalleIngreso=" + iddetalleIngreso + " ]";
    }
    
}
