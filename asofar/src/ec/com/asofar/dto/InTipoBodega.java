/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "in_tipo_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoBodega.findAll", query = "SELECT i FROM InTipoBodega i")
    , @NamedQuery(name = "InTipoBodega.findByIdTipoBodega", query = "SELECT i FROM InTipoBodega i WHERE i.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "InTipoBodega.findByNombre", query = "SELECT i FROM InTipoBodega i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InTipoBodega.findByEstado", query = "SELECT i FROM InTipoBodega i WHERE i.estado = :estado")
    , @NamedQuery(name = "InTipoBodega.findByIdUsuarioCreacion", query = "SELECT i FROM InTipoBodega i WHERE i.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "InTipoBodega.findByFechaCreacion", query = "SELECT i FROM InTipoBodega i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InTipoBodega.findByIdUsuarioActualizacion", query = "SELECT i FROM InTipoBodega i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")
    , @NamedQuery(name = "InTipoBodega.findByFechaActualizacion", query = "SELECT i FROM InTipoBodega i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_bodega")
    private Long idTipoBodega;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_usuario_creacion")
    private BigInteger idUsuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "id_usuario_actualizacion")
    private BigInteger idUsuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

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

    public BigInteger getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(BigInteger idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(BigInteger idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
