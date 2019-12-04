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
@Table(name = "se_localidad_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeLocalidadCliente.findAll", query = "SELECT s FROM SeLocalidadCliente s")
    , @NamedQuery(name = "SeLocalidadCliente.findByIdLocalidadCliente", query = "SELECT s FROM SeLocalidadCliente s WHERE s.idLocalidadCliente = :idLocalidadCliente")
    , @NamedQuery(name = "SeLocalidadCliente.findByTelefono", query = "SELECT s FROM SeLocalidadCliente s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SeLocalidadCliente.findByCelular", query = "SELECT s FROM SeLocalidadCliente s WHERE s.celular = :celular")
    , @NamedQuery(name = "SeLocalidadCliente.findByEmail", query = "SELECT s FROM SeLocalidadCliente s WHERE s.email = :email")
    , @NamedQuery(name = "SeLocalidadCliente.findByDirreccionCliente", query = "SELECT s FROM SeLocalidadCliente s WHERE s.dirreccionCliente = :dirreccionCliente")
    , @NamedQuery(name = "SeLocalidadCliente.findByDirreccionEntrega", query = "SELECT s FROM SeLocalidadCliente s WHERE s.dirreccionEntrega = :dirreccionEntrega")
    , @NamedQuery(name = "SeLocalidadCliente.findByEstado", query = "SELECT s FROM SeLocalidadCliente s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeLocalidadCliente.findByFechaCreacion", query = "SELECT s FROM SeLocalidadCliente s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeLocalidadCliente.findByUsuarioCreacion", query = "SELECT s FROM SeLocalidadCliente s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeLocalidadCliente.findByFechaActualizacion", query = "SELECT s FROM SeLocalidadCliente s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SeLocalidadCliente.findByUsuarioActualizacion", query = "SELECT s FROM SeLocalidadCliente s WHERE s.usuarioActualizacion = :usuarioActualizacion")})
public class SeLocalidadCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_localidad_cliente")
    private Long idLocalidadCliente;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
    @Column(name = "dirreccion_cliente")
    private String dirreccionCliente;
    @Column(name = "dirreccion_entrega")
    private String dirreccionEntrega;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_clientes")
    @ManyToOne
    private SeClientes idCliente;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_Ciudad")
    @ManyToOne
    private SeCiudad idCiudad;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_Provincia")
    @ManyToOne
    private SeProvincia idProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_Pais")
    @ManyToOne
    private SePais idPais;
    @OneToMany(mappedBy = "idLocalidad")
    private List<SeContactosClientes> seContactosClientesList;

    public SeLocalidadCliente() {
    }

    public SeLocalidadCliente(Long idLocalidadCliente) {
        this.idLocalidadCliente = idLocalidadCliente;
    }

    public Long getIdLocalidadCliente() {
        return idLocalidadCliente;
    }

    public void setIdLocalidadCliente(Long idLocalidadCliente) {
        this.idLocalidadCliente = idLocalidadCliente;
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

    public String getDirreccionCliente() {
        return dirreccionCliente;
    }

    public void setDirreccionCliente(String dirreccionCliente) {
        this.dirreccionCliente = dirreccionCliente;
    }

    public String getDirreccionEntrega() {
        return dirreccionEntrega;
    }

    public void setDirreccionEntrega(String dirreccionEntrega) {
        this.dirreccionEntrega = dirreccionEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public SeClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(SeClientes idCliente) {
        this.idCliente = idCliente;
    }

    public SeCiudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(SeCiudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public SeProvincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(SeProvincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    public SePais getIdPais() {
        return idPais;
    }

    public void setIdPais(SePais idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public List<SeContactosClientes> getSeContactosClientesList() {
        return seContactosClientesList;
    }

    public void setSeContactosClientesList(List<SeContactosClientes> seContactosClientesList) {
        this.seContactosClientesList = seContactosClientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalidadCliente != null ? idLocalidadCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeLocalidadCliente)) {
            return false;
        }
        SeLocalidadCliente other = (SeLocalidadCliente) object;
        if ((this.idLocalidadCliente == null && other.idLocalidadCliente != null) || (this.idLocalidadCliente != null && !this.idLocalidadCliente.equals(other.idLocalidadCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeLocalidadCliente[ idLocalidadCliente=" + idLocalidadCliente + " ]";
    }
    
}
