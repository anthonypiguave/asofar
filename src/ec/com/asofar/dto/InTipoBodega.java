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
 * @author Usuario
 */
@Entity
@Table(name = "in_tipo_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoBodega.findAll", query = "SELECT i FROM InTipoBodega i")
    , @NamedQuery(name = "InTipoBodega.findByIdTipoBodega", query = "SELECT i FROM InTipoBodega i WHERE i.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "InTipoBodega.findByNombre", query = "SELECT i FROM InTipoBodega i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InTipoBodega.findByEstado", query = "SELECT i FROM InTipoBodega i WHERE i.estado = :estado")
    , @NamedQuery(name = "InTipoBodega.findByUsuarioCreacion", query = "SELECT i FROM InTipoBodega i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InTipoBodega.findByFechaCreacion", query = "SELECT i FROM InTipoBodega i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InTipoBodega.findByUsuarioActualizacion", query = "SELECT i FROM InTipoBodega i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InTipoBodega.findByFechaActualizacion", query = "SELECT i FROM InTipoBodega i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_bodega")
    private Long idTipoBodega;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inTipoBodega")
    private List<InBodega> inBodegaList;

    public InTipoBodega() {
    }

    public InTipoBodega(Long idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
    }

    public Long getIdTipoBodega() {
        return idTipoBodega;
    }

    public void setIdTipoBodega(Long idTipoBodega) {
        this.idTipoBodega = idTipoBodega;
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
    public List<InBodega> getInBodegaList() {
        return inBodegaList;
    }

    public void setInBodegaList(List<InBodega> inBodegaList) {
        this.inBodegaList = inBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBodega != null ? idTipoBodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InTipoBodega)) {
            return false;
        }
        InTipoBodega other = (InTipoBodega) object;
        if ((this.idTipoBodega == null && other.idTipoBodega != null) || (this.idTipoBodega != null && !this.idTipoBodega.equals(other.idTipoBodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InTipoBodega[ idTipoBodega=" + idTipoBodega + " ]";
    }
    
}
