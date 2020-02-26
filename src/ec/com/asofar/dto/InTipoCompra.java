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
 * @author usuario
 */
@Entity
@Table(name = "in_tipo_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoCompra.findAll", query = "SELECT i FROM InTipoCompra i")
    , @NamedQuery(name = "InTipoCompra.findByIdInTipoCompra", query = "SELECT i FROM InTipoCompra i WHERE i.idInTipoCompra = :idInTipoCompra")
    , @NamedQuery(name = "InTipoCompra.findByNombre", query = "SELECT i FROM InTipoCompra i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InTipoCompra.findByEstado", query = "SELECT i FROM InTipoCompra i WHERE i.estado = :estado")
    , @NamedQuery(name = "InTipoCompra.findByUsuarioCreacion", query = "SELECT i FROM InTipoCompra i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InTipoCompra.findByFechaCreacion", query = "SELECT i FROM InTipoCompra i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InTipoCompra.findByUsuarioActualizacion", query = "SELECT i FROM InTipoCompra i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InTipoCompra.findByFechaActualizacion", query = "SELECT i FROM InTipoCompra i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_in_tipo_compra")
    private Long idInTipoCompra;
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
    private String fechaActualizacion;
    @OneToMany(mappedBy = "idTipoCompra")
    private List<CoItemsCotizacion> coItemsCotizacionList;

    public InTipoCompra() {
    }

    public InTipoCompra(Long idInTipoCompra) {
        this.idInTipoCompra = idInTipoCompra;
    }

    public Long getIdInTipoCompra() {
        return idInTipoCompra;
    }

    public void setIdInTipoCompra(Long idInTipoCompra) {
        this.idInTipoCompra = idInTipoCompra;
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @XmlTransient
    public List<CoItemsCotizacion> getCoItemsCotizacionList() {
        return coItemsCotizacionList;
    }

    public void setCoItemsCotizacionList(List<CoItemsCotizacion> coItemsCotizacionList) {
        this.coItemsCotizacionList = coItemsCotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInTipoCompra != null ? idInTipoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InTipoCompra)) {
            return false;
        }
        InTipoCompra other = (InTipoCompra) object;
        if ((this.idInTipoCompra == null && other.idInTipoCompra != null) || (this.idInTipoCompra != null && !this.idInTipoCompra.equals(other.idInTipoCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InTipoCompra[ idInTipoCompra=" + idInTipoCompra + " ]";
    }
    
}
