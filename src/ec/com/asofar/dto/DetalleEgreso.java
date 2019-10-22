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
@Table(name = "detalle_egreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEgreso.findAll", query = "SELECT d FROM DetalleEgreso d")
    , @NamedQuery(name = "DetalleEgreso.findByIddetalleEgreso", query = "SELECT d FROM DetalleEgreso d WHERE d.iddetalleEgreso = :iddetalleEgreso")
    , @NamedQuery(name = "DetalleEgreso.findByCantida", query = "SELECT d FROM DetalleEgreso d WHERE d.cantida = :cantida")
    , @NamedQuery(name = "DetalleEgreso.findByPvp", query = "SELECT d FROM DetalleEgreso d WHERE d.pvp = :pvp")
    , @NamedQuery(name = "DetalleEgreso.findByFecha", query = "SELECT d FROM DetalleEgreso d WHERE d.fecha = :fecha")})
public class DetalleEgreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_egreso")
    private Integer iddetalleEgreso;
    @Basic(optional = false)
    @Column(name = "cantida")
    private int cantida;
    @Basic(optional = false)
    @Column(name = "pvp")
    private double pvp;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddetalleEgreso")
    private List<Egreso> egresoList;
    @JoinColumn(name = "idproducto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;

    public DetalleEgreso() {
    }

    public DetalleEgreso(Integer iddetalleEgreso) {
        this.iddetalleEgreso = iddetalleEgreso;
    }

    public DetalleEgreso(Integer iddetalleEgreso, int cantida, double pvp, Date fecha) {
        this.iddetalleEgreso = iddetalleEgreso;
        this.cantida = cantida;
        this.pvp = pvp;
        this.fecha = fecha;
    }

    public Integer getIddetalleEgreso() {
        return iddetalleEgreso;
    }

    public void setIddetalleEgreso(Integer iddetalleEgreso) {
        this.iddetalleEgreso = iddetalleEgreso;
    }

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Egreso> getEgresoList() {
        return egresoList;
    }

    public void setEgresoList(List<Egreso> egresoList) {
        this.egresoList = egresoList;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleEgreso != null ? iddetalleEgreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEgreso)) {
            return false;
        }
        DetalleEgreso other = (DetalleEgreso) object;
        if ((this.iddetalleEgreso == null && other.iddetalleEgreso != null) || (this.iddetalleEgreso != null && !this.iddetalleEgreso.equals(other.iddetalleEgreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.DetalleEgreso[ iddetalleEgreso=" + iddetalleEgreso + " ]";
    }
    
}
