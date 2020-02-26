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
 * @author usuario
 */
@Entity
@Table(name = "se_clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeClientes.findAll", query = "SELECT s FROM SeClientes s")
    , @NamedQuery(name = "SeClientes.findByIdClientes", query = "SELECT s FROM SeClientes s WHERE s.idClientes = :idClientes")
    , @NamedQuery(name = "SeClientes.findByNumeroIdentificacion", query = "SELECT s FROM SeClientes s WHERE s.numeroIdentificacion = :numeroIdentificacion")
    , @NamedQuery(name = "SeClientes.findByPrimerNombre", query = "SELECT s FROM SeClientes s WHERE s.primerNombre = :primerNombre")
    , @NamedQuery(name = "SeClientes.findBySegundoNombre", query = "SELECT s FROM SeClientes s WHERE s.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "SeClientes.findByPrimerApellido", query = "SELECT s FROM SeClientes s WHERE s.primerApellido = :primerApellido")
    , @NamedQuery(name = "SeClientes.findBySegundoApellido", query = "SELECT s FROM SeClientes s WHERE s.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "SeClientes.findByRazonSocial", query = "SELECT s FROM SeClientes s WHERE s.razonSocial = :razonSocial")
    , @NamedQuery(name = "SeClientes.findByNombreCompleto", query = "SELECT s FROM SeClientes s WHERE s.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "SeClientes.findByEstado", query = "SELECT s FROM SeClientes s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeClientes.findByUsuarioCreacion", query = "SELECT s FROM SeClientes s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeClientes.findByFechaCreacion", query = "SELECT s FROM SeClientes s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeClientes.findByUsuarioActualizacion", query = "SELECT s FROM SeClientes s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeClientes.findByFechaActualizacion", query = "SELECT s FROM SeClientes s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clientes")
    private Long idClientes;
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
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
    @JoinColumn(name = "id_tipo_indentificacion", referencedColumnName = "id_tipo_identificacion")
    @ManyToOne
    private SeTipoIdentificacion idTipoIndentificacion;
    @OneToMany(mappedBy = "idCliente")
    private List<SeLocalidadCliente> seLocalidadClienteList;

    public SeClientes() {
    }

    public SeClientes(Long idClientes) {
        this.idClientes = idClientes;
    }

    public Long getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Long idClientes) {
        this.idClientes = idClientes;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public SeTipoIdentificacion getIdTipoIndentificacion() {
        return idTipoIndentificacion;
    }

    public void setIdTipoIndentificacion(SeTipoIdentificacion idTipoIndentificacion) {
        this.idTipoIndentificacion = idTipoIndentificacion;
    }

    @XmlTransient
    public List<SeLocalidadCliente> getSeLocalidadClienteList() {
        return seLocalidadClienteList;
    }

    public void setSeLocalidadClienteList(List<SeLocalidadCliente> seLocalidadClienteList) {
        this.seLocalidadClienteList = seLocalidadClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClientes != null ? idClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeClientes)) {
            return false;
        }
        SeClientes other = (SeClientes) object;
        if ((this.idClientes == null && other.idClientes != null) || (this.idClientes != null && !this.idClientes.equals(other.idClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeClientes[ idClientes=" + idClientes + " ]";
    }
    
}
