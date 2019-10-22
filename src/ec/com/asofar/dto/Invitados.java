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
@Table(name = "invitados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitados.findAll", query = "SELECT i FROM Invitados i")
    , @NamedQuery(name = "Invitados.findByIdinvitado", query = "SELECT i FROM Invitados i WHERE i.idinvitado = :idinvitado")
    , @NamedQuery(name = "Invitados.findByNombre", query = "SELECT i FROM Invitados i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Invitados.findByFecha", query = "SELECT i FROM Invitados i WHERE i.fecha = :fecha")})
public class Invitados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinvitado")
    private Integer idinvitado;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinvitado")
    private List<Historial> historialList;

    public Invitados() {
    }

    public Invitados(Integer idinvitado) {
        this.idinvitado = idinvitado;
    }

    public Invitados(Integer idinvitado, String nombre, Date fecha) {
        this.idinvitado = idinvitado;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Integer getIdinvitado() {
        return idinvitado;
    }

    public void setIdinvitado(Integer idinvitado) {
        this.idinvitado = idinvitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinvitado != null ? idinvitado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitados)) {
            return false;
        }
        Invitados other = (Invitados) object;
        if ((this.idinvitado == null && other.idinvitado != null) || (this.idinvitado != null && !this.idinvitado.equals(other.idinvitado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.Invitados[ idinvitado=" + idinvitado + " ]";
    }
    
}
