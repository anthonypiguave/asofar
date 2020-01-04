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
@Table(name = "in_tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoDocumento.findAll", query = "SELECT i FROM InTipoDocumento i")
    , @NamedQuery(name = "InTipoDocumento.findByIdTipoDocumento", query = "SELECT i FROM InTipoDocumento i WHERE i.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "InTipoDocumento.findByNombreDocumento", query = "SELECT i FROM InTipoDocumento i WHERE i.nombreDocumento = :nombreDocumento")
    , @NamedQuery(name = "InTipoDocumento.findByEstado", query = "SELECT i FROM InTipoDocumento i WHERE i.estado = :estado")
    , @NamedQuery(name = "InTipoDocumento.findByUsuarioCreacion", query = "SELECT i FROM InTipoDocumento i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InTipoDocumento.findByFechaCreacion", query = "SELECT i FROM InTipoDocumento i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InTipoDocumento.findByUsuarioActualizacion", query = "SELECT i FROM InTipoDocumento i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InTipoDocumento.findByFechaActualizacion", query = "SELECT i FROM InTipoDocumento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;
    @Column(name = "nombre_documento")
    private String nombreDocumento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inTipoDocumento")
    private List<InMovimientos> inMovimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inTipoDocumento")
    private List<InKardex> inKardexList;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<CoItemsCotizacion> coItemsCotizacionList;

    public InTipoDocumento() {
    }

    public InTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
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
    public List<InMovimientos> getInMovimientosList() {
        return inMovimientosList;
    }

    public void setInMovimientosList(List<InMovimientos> inMovimientosList) {
        this.inMovimientosList = inMovimientosList;
    }

    @XmlTransient
    public List<InKardex> getInKardexList() {
        return inKardexList;
    }

    public void setInKardexList(List<InKardex> inKardexList) {
        this.inKardexList = inKardexList;
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
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InTipoDocumento)) {
            return false;
        }
        InTipoDocumento other = (InTipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InTipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
