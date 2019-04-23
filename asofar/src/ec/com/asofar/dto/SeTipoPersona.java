/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author admin1
 */
@Entity
@Table(name = "se_tipo_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeTipoPersona.findAll", query = "SELECT s FROM SeTipoPersona s")
    , @NamedQuery(name = "SeTipoPersona.findByIdTipoPersona", query = "SELECT s FROM SeTipoPersona s WHERE s.idTipoPersona = :idTipoPersona")
    , @NamedQuery(name = "SeTipoPersona.findByNombre", query = "SELECT s FROM SeTipoPersona s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeTipoPersona.findByEstado", query = "SELECT s FROM SeTipoPersona s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeTipoPersona.findByUsuarioCreacion", query = "SELECT s FROM SeTipoPersona s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeTipoPersona.findByFechaCreacion", query = "SELECT s FROM SeTipoPersona s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeTipoPersona.findByUsuarioActualizacion", query = "SELECT s FROM SeTipoPersona s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeTipoPersona.findByFechaActualizacion", query = "SELECT s FROM SeTipoPersona s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeTipoPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_persona")
    private Long idTipoPersona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private BigInteger usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seTipoPersona")
    private List<SePersonas> sePersonasList;

    public SeTipoPersona() {
    }

    public SeTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public Long getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(BigInteger usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @XmlTransient
    public List<SePersonas> getSePersonasList() {
        return sePersonasList;
    }

    public void setSePersonasList(List<SePersonas> sePersonasList) {
        this.sePersonasList = sePersonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPersona != null ? idTipoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeTipoPersona)) {
            return false;
        }
        SeTipoPersona other = (SeTipoPersona) object;
        if ((this.idTipoPersona == null && other.idTipoPersona != null) || (this.idTipoPersona != null && !this.idTipoPersona.equals(other.idTipoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeTipoPersona[ idTipoPersona=" + idTipoPersona + " ]";
    }
    
}
