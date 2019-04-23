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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author admin1
 */
@Entity
@Table(name = "se_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SePersonas.findAll", query = "SELECT s FROM SePersonas s")
    , @NamedQuery(name = "SePersonas.findByIdPersona", query = "SELECT s FROM SePersonas s WHERE s.sePersonasPK.idPersona = :idPersona")
    , @NamedQuery(name = "SePersonas.findByIdTipoPersona", query = "SELECT s FROM SePersonas s WHERE s.sePersonasPK.idTipoPersona = :idTipoPersona")
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
    @EmbeddedId
    protected SePersonasPK sePersonasPK;
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
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private BigInteger usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_tipo_persona", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeTipoPersona seTipoPersona;
    @OneToMany(mappedBy = "idProveedor")
    private List<CoOrdenCompras> coOrdenComprasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sePersonas")
    private List<InMovimientos> inMovimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sePersonas")
    private List<SeUsuarios> seUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sePersonas")
    private List<VeFactura> veFacturaList;

    public SePersonas() {
    }

    public SePersonas(SePersonasPK sePersonasPK) {
        this.sePersonasPK = sePersonasPK;
    }

    public SePersonas(long idPersona, long idTipoPersona) {
        this.sePersonasPK = new SePersonasPK(idPersona, idTipoPersona);
    }

    public SePersonasPK getSePersonasPK() {
        return sePersonasPK;
    }

    public void setSePersonasPK(SePersonasPK sePersonasPK) {
        this.sePersonasPK = sePersonasPK;
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public SeTipoPersona getSeTipoPersona() {
        return seTipoPersona;
    }

    public void setSeTipoPersona(SeTipoPersona seTipoPersona) {
        this.seTipoPersona = seTipoPersona;
    }

    @XmlTransient
    public List<CoOrdenCompras> getCoOrdenComprasList() {
        return coOrdenComprasList;
    }

    public void setCoOrdenComprasList(List<CoOrdenCompras> coOrdenComprasList) {
        this.coOrdenComprasList = coOrdenComprasList;
    }

    @XmlTransient
    public List<InMovimientos> getInMovimientosList() {
        return inMovimientosList;
    }

    public void setInMovimientosList(List<InMovimientos> inMovimientosList) {
        this.inMovimientosList = inMovimientosList;
    }

    @XmlTransient
    public List<SeUsuarios> getSeUsuariosList() {
        return seUsuariosList;
    }

    public void setSeUsuariosList(List<SeUsuarios> seUsuariosList) {
        this.seUsuariosList = seUsuariosList;
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
        hash += (sePersonasPK != null ? sePersonasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SePersonas)) {
            return false;
        }
        SePersonas other = (SePersonas) object;
        if ((this.sePersonasPK == null && other.sePersonasPK != null) || (this.sePersonasPK != null && !this.sePersonasPK.equals(other.sePersonasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SePersonas[ sePersonasPK=" + sePersonasPK + " ]";
    }
    
}