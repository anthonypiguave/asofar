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
 * @author admini
 */
@Entity
@Table(name = "pr_empaque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrEmpaque.findAll", query = "SELECT p FROM PrEmpaque p")
    , @NamedQuery(name = "PrEmpaque.findById", query = "SELECT p FROM PrEmpaque p WHERE p.id = :id")
    , @NamedQuery(name = "PrEmpaque.findByNombreEmpaque", query = "SELECT p FROM PrEmpaque p WHERE p.nombreEmpaque = :nombreEmpaque")
    , @NamedQuery(name = "PrEmpaque.findByEstado", query = "SELECT p FROM PrEmpaque p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrEmpaque.findByUsuarioCreacion", query = "SELECT p FROM PrEmpaque p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrEmpaque.findByFechaCreacion", query = "SELECT p FROM PrEmpaque p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrEmpaque.findByUsuarioActualizacion", query = "SELECT p FROM PrEmpaque p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrEmpaque.findByFechaActualizacion", query = "SELECT p FROM PrEmpaque p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrEmpaque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre_empaque")
    private String nombreEmpaque;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(mappedBy = "medidaEmpaqueCompra")
    private List<PrProductos> prProductosList;
    @OneToMany(mappedBy = "medidaPorEmpaqueCompra")
    private List<PrProductos> prProductosList1;
    @OneToMany(mappedBy = "medidaEmpaqueVenta")
    private List<PrProductos> prProductosList2;
    @OneToMany(mappedBy = "medidaPorEmpaqueVenta")
    private List<PrProductos> prProductosList3;

    public PrEmpaque() {
    }

    public PrEmpaque(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpaque() {
        return nombreEmpaque;
    }

    public void setNombreEmpaque(String nombreEmpaque) {
        this.nombreEmpaque = nombreEmpaque;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @XmlTransient
    public List<PrProductos> getPrProductosList() {
        return prProductosList;
    }

    public void setPrProductosList(List<PrProductos> prProductosList) {
        this.prProductosList = prProductosList;
    }

    @XmlTransient
    public List<PrProductos> getPrProductosList1() {
        return prProductosList1;
    }

    public void setPrProductosList1(List<PrProductos> prProductosList1) {
        this.prProductosList1 = prProductosList1;
    }

    @XmlTransient
    public List<PrProductos> getPrProductosList2() {
        return prProductosList2;
    }

    public void setPrProductosList2(List<PrProductos> prProductosList2) {
        this.prProductosList2 = prProductosList2;
    }

    @XmlTransient
    public List<PrProductos> getPrProductosList3() {
        return prProductosList3;
    }

    public void setPrProductosList3(List<PrProductos> prProductosList3) {
        this.prProductosList3 = prProductosList3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrEmpaque)) {
            return false;
        }
        PrEmpaque other = (PrEmpaque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrEmpaque[ id=" + id + " ]";
    }
    
}
