/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "co_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoProveedores.findAll", query = "SELECT c FROM CoProveedores c")
    , @NamedQuery(name = "CoProveedores.findByIdProveedor", query = "SELECT c FROM CoProveedores c WHERE c.idProveedor = :idProveedor")
    , @NamedQuery(name = "CoProveedores.findByNombre", query = "SELECT c FROM CoProveedores c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CoProveedores.findByDireccion", query = "SELECT c FROM CoProveedores c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CoProveedores.findByTelefono1", query = "SELECT c FROM CoProveedores c WHERE c.telefono1 = :telefono1")
    , @NamedQuery(name = "CoProveedores.findByTelefono2", query = "SELECT c FROM CoProveedores c WHERE c.telefono2 = :telefono2")
    , @NamedQuery(name = "CoProveedores.findByPaginaWeb", query = "SELECT c FROM CoProveedores c WHERE c.paginaWeb = :paginaWeb")
    , @NamedQuery(name = "CoProveedores.findByNumeroIdentificacion", query = "SELECT c FROM CoProveedores c WHERE c.numeroIdentificacion = :numeroIdentificacion")
    , @NamedQuery(name = "CoProveedores.findByEmail", query = "SELECT c FROM CoProveedores c WHERE c.email = :email")
    , @NamedQuery(name = "CoProveedores.findByIdPais", query = "SELECT c FROM CoProveedores c WHERE c.idPais = :idPais")
    , @NamedQuery(name = "CoProveedores.findByContribuyenteEspecial", query = "SELECT c FROM CoProveedores c WHERE c.contribuyenteEspecial = :contribuyenteEspecial")
    , @NamedQuery(name = "CoProveedores.findByCodigoContribuyente", query = "SELECT c FROM CoProveedores c WHERE c.codigoContribuyente = :codigoContribuyente")
    , @NamedQuery(name = "CoProveedores.findByObservaciones", query = "SELECT c FROM CoProveedores c WHERE c.observaciones = :observaciones")
    , @NamedQuery(name = "CoProveedores.findByNombreComercial", query = "SELECT c FROM CoProveedores c WHERE c.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "CoProveedores.findByUsuarioCreacion", query = "SELECT c FROM CoProveedores c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoProveedores.findByFechaCreacion", query = "SELECT c FROM CoProveedores c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoProveedores.findByUsuarioActualizacion", query = "SELECT c FROM CoProveedores c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoProveedores.findByFechaActualizacion", query = "SELECT c FROM CoProveedores c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "CoProveedores.findByEstado", query = "SELECT c FROM CoProveedores c WHERE c.estado = :estado")})
public class CoProveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Long idProveedor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono1")
    private String telefono1;
    @Column(name = "telefono2")
    private String telefono2;
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;
    @Column(name = "email")
    private String email;
    @Column(name = "id_pais")
    private BigInteger idPais;
    @Column(name = "contribuyente_especial")
    private String contribuyenteEspecial;
    @Column(name = "codigo_contribuyente")
    private String codigoContribuyente;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "nombre_comercial")
    private String nombreComercial;
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
    private Character estado;
    @JoinColumn(name = "tipo_persona", referencedColumnName = "id_tipo_persona")
    @ManyToOne
    private SeTipoPersona tipoPersona;

    public CoProveedores() {
    }

    public CoProveedores(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getIdPais() {
        return idPais;
    }

    public void setIdPais(BigInteger idPais) {
        this.idPais = idPais;
    }

    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    public String getCodigoContribuyente() {
        return codigoContribuyente;
    }

    public void setCodigoContribuyente(String codigoContribuyente) {
        this.codigoContribuyente = codigoContribuyente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public SeTipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(SeTipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoProveedores)) {
            return false;
        }
        CoProveedores other = (CoProveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoProveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
