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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "se_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeUsuarios.findAll", query = "SELECT s FROM SeUsuarios s")
    , @NamedQuery(name = "SeUsuarios.findByIdUsuario", query = "SELECT s FROM SeUsuarios s WHERE s.idUsuario = :idUsuario")
    , @NamedQuery(name = "SeUsuarios.findByIdTipoPersona", query = "SELECT s FROM SeUsuarios s WHERE s.idTipoPersona = :idTipoPersona")
    , @NamedQuery(name = "SeUsuarios.findByEstado", query = "SELECT s FROM SeUsuarios s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeUsuarios.findByUsuarioCreacion", query = "SELECT s FROM SeUsuarios s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeUsuarios.findByFechaCreacion", query = "SELECT s FROM SeUsuarios s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeUsuarios.findByUsuarioActualizacion", query = "SELECT s FROM SeUsuarios s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeUsuarios.findByFechaActualizacion", query = "SELECT s FROM SeUsuarios s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SeUsuarios.findByCorreo", query = "SELECT s FROM SeUsuarios s WHERE s.correo = :correo")
    , @NamedQuery(name = "SeUsuarios.findByTelefono", query = "SELECT s FROM SeUsuarios s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SeUsuarios.findByTelefono2", query = "SELECT s FROM SeUsuarios s WHERE s.telefono2 = :telefono2")
    , @NamedQuery(name = "SeUsuarios.findByDepartamento", query = "SELECT s FROM SeUsuarios s WHERE s.departamento = :departamento")
    , @NamedQuery(name = "SeUsuarios.findByNombreUsuario", query = "SELECT s FROM SeUsuarios s WHERE s.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "SeUsuarios.findBySueldo", query = "SELECT s FROM SeUsuarios s WHERE s.sueldo = :sueldo")})
public class SeUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_tipo_persona")
    private BigInteger idTipoPersona;
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
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono2")
    private String telefono2;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Lob
    @Column(name = "password")
    private String password;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo")
    private Double sueldo;
    @OneToMany(mappedBy = "idUsuario")
    private List<SeUsuarioSucurRol> seUsuarioSucurRolList;
    @OneToMany(mappedBy = "idUsuario")
    private List<InMovimientos> inMovimientosList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private SePersonas idPersona;
    @OneToMany(mappedBy = "idUsuario")
    private List<VeFactura> veFacturaList;

    public SeUsuarios() {
    }

    public SeUsuarios(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigInteger getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(BigInteger idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    @XmlTransient
    public List<SeUsuarioSucurRol> getSeUsuarioSucurRolList() {
        return seUsuarioSucurRolList;
    }

    public void setSeUsuarioSucurRolList(List<SeUsuarioSucurRol> seUsuarioSucurRolList) {
        this.seUsuarioSucurRolList = seUsuarioSucurRolList;
    }

    @XmlTransient
    public List<InMovimientos> getInMovimientosList() {
        return inMovimientosList;
    }

    public void setInMovimientosList(List<InMovimientos> inMovimientosList) {
        this.inMovimientosList = inMovimientosList;
    }

    public SePersonas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(SePersonas idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<VeFactura> getVeFacturaList() {
        return veFacturaList;
    }

    public void setVeFacturaList(List<VeFactura> veFacturaList) {
        this.veFacturaList = veFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeUsuarios)) {
            return false;
        }
        SeUsuarios other = (SeUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
