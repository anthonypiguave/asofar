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
import javax.persistence.JoinColumn;
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
 * @author admini
 */
@Entity
@Table(name = "se_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SePersonas.findAll", query = "SELECT s FROM SePersonas s")
    , @NamedQuery(name = "SePersonas.findByIdPersona", query = "SELECT s FROM SePersonas s WHERE s.idPersona = :idPersona")
    , @NamedQuery(name = "SePersonas.findByCedula", query = "SELECT s FROM SePersonas s WHERE s.cedula = :cedula")
    , @NamedQuery(name = "SePersonas.findByNombres", query = "SELECT s FROM SePersonas s WHERE s.nombres = :nombres")
    , @NamedQuery(name = "SePersonas.findByApellidos", query = "SELECT s FROM SePersonas s WHERE s.apellidos = :apellidos")
    , @NamedQuery(name = "SePersonas.findByFechaNacimiento", query = "SELECT s FROM SePersonas s WHERE s.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "SePersonas.findByDireccion", query = "SELECT s FROM SePersonas s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "SePersonas.findByCorreo", query = "SELECT s FROM SePersonas s WHERE s.correo = :correo")
    , @NamedQuery(name = "SePersonas.findByTelefono", query = "SELECT s FROM SePersonas s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SePersonas.findByTelefono2", query = "SELECT s FROM SePersonas s WHERE s.telefono2 = :telefono2")
    , @NamedQuery(name = "SePersonas.findByUsuarioCreacion", query = "SELECT s FROM SePersonas s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SePersonas.findByFechaCreacion", query = "SELECT s FROM SePersonas s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SePersonas.findByUsuarioActualizacion", query = "SELECT s FROM SePersonas s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SePersonas.findByFechaActualizacion", query = "SELECT s FROM SePersonas s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SePersonas.findByEstado", query = "SELECT s FROM SePersonas s WHERE s.estado = :estado")})
public class SePersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Long idPersona;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono2")
    private String telefono2;
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
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_tipo_persona")
    @ManyToOne
    private SeTipoPersona idTipoPersona;
    @OneToMany(mappedBy = "idPersona")
    private List<SeUsuarios> seUsuariosList;

    public SePersonas() {
    }

    public SePersonas(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SeTipoPersona getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(SeTipoPersona idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    @XmlTransient
    public List<SeUsuarios> getSeUsuariosList() {
        return seUsuariosList;
    }

    public void setSeUsuariosList(List<SeUsuarios> seUsuariosList) {
        this.seUsuariosList = seUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SePersonas)) {
            return false;
        }
        SePersonas other = (SePersonas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SePersonas[ idPersona=" + idPersona + " ]";
    }
    
}
