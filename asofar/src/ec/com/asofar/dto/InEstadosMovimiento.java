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
 * @author Usuario
 */
@Entity
@Table(name = "in_estados_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InEstadosMovimiento.findAll", query = "SELECT i FROM InEstadosMovimiento i")
    , @NamedQuery(name = "InEstadosMovimiento.findByIdEstadoMovimiento", query = "SELECT i FROM InEstadosMovimiento i WHERE i.idEstadoMovimiento = :idEstadoMovimiento")
    , @NamedQuery(name = "InEstadosMovimiento.findByNombre", query = "SELECT i FROM InEstadosMovimiento i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InEstadosMovimiento.findByEstado", query = "SELECT i FROM InEstadosMovimiento i WHERE i.estado = :estado")
    , @NamedQuery(name = "InEstadosMovimiento.findByUsuarioCreacion", query = "SELECT i FROM InEstadosMovimiento i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InEstadosMovimiento.findByFechaCreacion", query = "SELECT i FROM InEstadosMovimiento i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InEstadosMovimiento.findByUsuarioActualizacion", query = "SELECT i FROM InEstadosMovimiento i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InEstadosMovimiento.findByFechaActualizacion", query = "SELECT i FROM InEstadosMovimiento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InEstadosMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_movimiento")
    private Long idEstadoMovimiento;
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
    @OneToMany(mappedBy = "idEstado")
    private List<InMovimientos> inMovimientosList;

    public InEstadosMovimiento() {
    }

    public InEstadosMovimiento(Long idEstadoMovimiento) {
        this.idEstadoMovimiento = idEstadoMovimiento;
    }

    public Long getIdEstadoMovimiento() {
        return idEstadoMovimiento;
    }

    public void setIdEstadoMovimiento(Long idEstadoMovimiento) {
        this.idEstadoMovimiento = idEstadoMovimiento;
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
    public List<InMovimientos> getInMovimientosList() {
        return inMovimientosList;
    }

    public void setInMovimientosList(List<InMovimientos> inMovimientosList) {
        this.inMovimientosList = inMovimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoMovimiento != null ? idEstadoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InEstadosMovimiento)) {
            return false;
        }
        InEstadosMovimiento other = (InEstadosMovimiento) object;
        if ((this.idEstadoMovimiento == null && other.idEstadoMovimiento != null) || (this.idEstadoMovimiento != null && !this.idEstadoMovimiento.equals(other.idEstadoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InEstadosMovimiento[ idEstadoMovimiento=" + idEstadoMovimiento + " ]";
    }
    
}
