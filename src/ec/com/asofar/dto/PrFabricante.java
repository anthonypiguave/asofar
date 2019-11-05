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
 * @author jorge
 */
@Entity
@Table(name = "pr_fabricante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrFabricante.findAll", query = "SELECT p FROM PrFabricante p")
    , @NamedQuery(name = "PrFabricante.findByIdFabricante", query = "SELECT p FROM PrFabricante p WHERE p.idFabricante = :idFabricante")
    , @NamedQuery(name = "PrFabricante.findByNombre", query = "SELECT p FROM PrFabricante p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PrFabricante.findByEstado", query = "SELECT p FROM PrFabricante p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrFabricante.findByUsuarioCreacion", query = "SELECT p FROM PrFabricante p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrFabricante.findByFechaCreacion", query = "SELECT p FROM PrFabricante p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrFabricante.findByUsuarioActualizacion", query = "SELECT p FROM PrFabricante p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrFabricante.findByFechaActualizacion", query = "SELECT p FROM PrFabricante p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrFabricante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fabricante")
    private Long idFabricante;
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(mappedBy = "codFabricante")
    private List<PrProductos> prProductosList;

    public PrFabricante() {
    }

    public PrFabricante(Long idFabricante) {
        this.idFabricante = idFabricante;
    }

    public Long getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Long idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabricante != null ? idFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrFabricante)) {
            return false;
        }
        PrFabricante other = (PrFabricante) object;
        if ((this.idFabricante == null && other.idFabricante != null) || (this.idFabricante != null && !this.idFabricante.equals(other.idFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrFabricante[ idFabricante=" + idFabricante + " ]";
    }
    
}
