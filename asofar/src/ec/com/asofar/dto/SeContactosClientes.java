/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin1
 */
@Entity
@Table(name = "se_contactos_clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeContactosClientes.findAll", query = "SELECT s FROM SeContactosClientes s")
    , @NamedQuery(name = "SeContactosClientes.findByIdContactosClientes", query = "SELECT s FROM SeContactosClientes s WHERE s.idContactosClientes = :idContactosClientes")
    , @NamedQuery(name = "SeContactosClientes.findByNombre", query = "SELECT s FROM SeContactosClientes s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeContactosClientes.findByTelefono", query = "SELECT s FROM SeContactosClientes s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SeContactosClientes.findByCelular", query = "SELECT s FROM SeContactosClientes s WHERE s.celular = :celular")
    , @NamedQuery(name = "SeContactosClientes.findByEmail", query = "SELECT s FROM SeContactosClientes s WHERE s.email = :email")
    , @NamedQuery(name = "SeContactosClientes.findByEstado", query = "SELECT s FROM SeContactosClientes s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeContactosClientes.findByUsuarioCreacion", query = "SELECT s FROM SeContactosClientes s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeContactosClientes.findByFechaCreacion", query = "SELECT s FROM SeContactosClientes s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeContactosClientes.findByUsuarioActualizacion", query = "SELECT s FROM SeContactosClientes s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeContactosClientes.findByFechaActualizacion", query = "SELECT s FROM SeContactosClientes s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeContactosClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contactos_clientes")
    private Long idContactosClientes;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
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
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad_cliente")
    @ManyToOne
    private SeLocalidadCliente idLocalidad;

    public SeContactosClientes() {
    }

    public SeContactosClientes(Long idContactosClientes) {
        this.idContactosClientes = idContactosClientes;
    }

    public Long getIdContactosClientes() {
        return idContactosClientes;
    }

    public void setIdContactosClientes(Long idContactosClientes) {
        this.idContactosClientes = idContactosClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public SeLocalidadCliente getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(SeLocalidadCliente idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactosClientes != null ? idContactosClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeContactosClientes)) {
            return false;
        }
        SeContactosClientes other = (SeContactosClientes) object;
        if ((this.idContactosClientes == null && other.idContactosClientes != null) || (this.idContactosClientes != null && !this.idContactosClientes.equals(other.idContactosClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeContactosClientes[ idContactosClientes=" + idContactosClientes + " ]";
    }
    
}
