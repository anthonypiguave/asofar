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
 * @author ADMIN
 */
@Entity
@Table(name = "in_tipo_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InTipoMovimiento.findAll", query = "SELECT i FROM InTipoMovimiento i"),
    @NamedQuery(name = "InTipoMovimiento.findByIdTipoMovimiento", query = "SELECT i FROM InTipoMovimiento i WHERE i.idTipoMovimiento = :idTipoMovimiento"),
    @NamedQuery(name = "InTipoMovimiento.findByNombreMovimiento", query = "SELECT i FROM InTipoMovimiento i WHERE i.nombreMovimiento = :nombreMovimiento"),
    @NamedQuery(name = "InTipoMovimiento.findByEstado", query = "SELECT i FROM InTipoMovimiento i WHERE i.estado = :estado"),
    @NamedQuery(name = "InTipoMovimiento.findByUsuarioCreacion", query = "SELECT i FROM InTipoMovimiento i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "InTipoMovimiento.findByFechaCreacion", query = "SELECT i FROM InTipoMovimiento i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "InTipoMovimiento.findByUsuarioActuliazacion", query = "SELECT i FROM InTipoMovimiento i WHERE i.usuarioActuliazacion = :usuarioActuliazacion"),
    @NamedQuery(name = "InTipoMovimiento.findByFechaActualizacion", query = "SELECT i FROM InTipoMovimiento i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InTipoMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_movimiento")
    private Long idTipoMovimiento;
    @Column(name = "nombre_movimiento")
    private String nombreMovimiento;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actuliazacion")
    private String usuarioActuliazacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(mappedBy = "idTipoMovimiento")
    private List<InMovimientos> inMovimientosList;

    public InTipoMovimiento() {
    }

    public InTipoMovimiento(Long idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Long getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Long idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
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

    public String getUsuarioActuliazacion() {
        return usuarioActuliazacion;
    }

    public void setUsuarioActuliazacion(String usuarioActuliazacion) {
        this.usuarioActuliazacion = usuarioActuliazacion;
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
        hash += (idTipoMovimiento != null ? idTipoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InTipoMovimiento)) {
            return false;
        }
        InTipoMovimiento other = (InTipoMovimiento) object;
        if ((this.idTipoMovimiento == null && other.idTipoMovimiento != null) || (this.idTipoMovimiento != null && !this.idTipoMovimiento.equals(other.idTipoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InTipoMovimiento[ idTipoMovimiento=" + idTipoMovimiento + " ]";
    }
    
}
