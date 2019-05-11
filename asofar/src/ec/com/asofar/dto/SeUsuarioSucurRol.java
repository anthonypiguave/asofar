/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin1
 */
@Entity
@Table(name = "se_usuario_sucur_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeUsuarioSucurRol.findAll", query = "SELECT s FROM SeUsuarioSucurRol s")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByIdUsuarioSucurRol", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.seUsuarioSucurRolPK.idUsuarioSucurRol = :idUsuarioSucurRol")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByIdSucursal", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.seUsuarioSucurRolPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByIdEmpresa", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.seUsuarioSucurRolPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByEstado", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByUsuarioCreacion", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByFechaCreacion", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByUsuarioActualizacion", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeUsuarioSucurRol.findByFechaActualizacion", query = "SELECT s FROM SeUsuarioSucurRol s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeUsuarioSucurRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeUsuarioSucurRolPK seUsuarioSucurRolPK;
    @Column(name = "estado")
    private Character estado;
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
    @JoinColumn(name = "id_roles", referencedColumnName = "id_roles")
    @ManyToOne
    private SeRoles idRoles;
    @JoinColumns({
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private SeUsuarios idUsuario;

    public SeUsuarioSucurRol() {
    }

    public SeUsuarioSucurRol(SeUsuarioSucurRolPK seUsuarioSucurRolPK) {
        this.seUsuarioSucurRolPK = seUsuarioSucurRolPK;
    }

    public SeUsuarioSucurRol(long idUsuarioSucurRol, long idSucursal, long idEmpresa) {
        this.seUsuarioSucurRolPK = new SeUsuarioSucurRolPK(idUsuarioSucurRol, idSucursal, idEmpresa);
    }

    public SeUsuarioSucurRolPK getSeUsuarioSucurRolPK() {
        return seUsuarioSucurRolPK;
    }

    public void setSeUsuarioSucurRolPK(SeUsuarioSucurRolPK seUsuarioSucurRolPK) {
        this.seUsuarioSucurRolPK = seUsuarioSucurRolPK;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
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

    public SeRoles getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(SeRoles idRoles) {
        this.idRoles = idRoles;
    }

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    public SeUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SeUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seUsuarioSucurRolPK != null ? seUsuarioSucurRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeUsuarioSucurRol)) {
            return false;
        }
        SeUsuarioSucurRol other = (SeUsuarioSucurRol) object;
        if ((this.seUsuarioSucurRolPK == null && other.seUsuarioSucurRolPK != null) || (this.seUsuarioSucurRolPK != null && !this.seUsuarioSucurRolPK.equals(other.seUsuarioSucurRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeUsuarioSucurRol[ seUsuarioSucurRolPK=" + seUsuarioSucurRolPK + " ]";
    }
    
}
