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
@Table(name = "in_tipo_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoDepartamento.findAll", query = "SELECT i FROM InTipoDepartamento i")
    , @NamedQuery(name = "InTipoDepartamento.findByIdTipoDepartamento", query = "SELECT i FROM InTipoDepartamento i WHERE i.idTipoDepartamento = :idTipoDepartamento")
    , @NamedQuery(name = "InTipoDepartamento.findByNombre", query = "SELECT i FROM InTipoDepartamento i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InTipoDepartamento.findByEstado", query = "SELECT i FROM InTipoDepartamento i WHERE i.estado = :estado")
    , @NamedQuery(name = "InTipoDepartamento.findByUsuarioCreacion", query = "SELECT i FROM InTipoDepartamento i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InTipoDepartamento.findByFechaCreacion", query = "SELECT i FROM InTipoDepartamento i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InTipoDepartamento.findByUsuarioActualizacion", query = "SELECT i FROM InTipoDepartamento i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InTipoDepartamento.findByFechaActualizacion", query = "SELECT i FROM InTipoDepartamento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_departamento")
    private Long idTipoDepartamento;
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
    @OneToMany(mappedBy = "idDepartamento")
    private List<CoItemsCotizacion> coItemsCotizacionList;

    public InTipoDepartamento() {
    }

    public InTipoDepartamento(Long idTipoDepartamento) {
        this.idTipoDepartamento = idTipoDepartamento;
    }

    public Long getIdTipoDepartamento() {
        return idTipoDepartamento;
    }

    public void setIdTipoDepartamento(Long idTipoDepartamento) {
        this.idTipoDepartamento = idTipoDepartamento;
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
        hash += (idTipoDepartamento != null ? idTipoDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InTipoDepartamento)) {
            return false;
        }
        InTipoDepartamento other = (InTipoDepartamento) object;
        if ((this.idTipoDepartamento == null && other.idTipoDepartamento != null) || (this.idTipoDepartamento != null && !this.idTipoDepartamento.equals(other.idTipoDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InTipoDepartamento[ idTipoDepartamento=" + idTipoDepartamento + " ]";
    }
    
}
