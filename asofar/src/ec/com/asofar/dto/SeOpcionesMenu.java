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
@Table(name = "se_opciones_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeOpcionesMenu.findAll", query = "SELECT s FROM SeOpcionesMenu s")
    , @NamedQuery(name = "SeOpcionesMenu.findByIdOpcionesMenu", query = "SELECT s FROM SeOpcionesMenu s WHERE s.idOpcionesMenu = :idOpcionesMenu")
    , @NamedQuery(name = "SeOpcionesMenu.findByNombre", query = "SELECT s FROM SeOpcionesMenu s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeOpcionesMenu.findByDescripcion", query = "SELECT s FROM SeOpcionesMenu s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "SeOpcionesMenu.findByEstado", query = "SELECT s FROM SeOpcionesMenu s WHERE s.estado = :estado")
    , @NamedQuery(name = "SeOpcionesMenu.findByUsuarioCreacion", query = "SELECT s FROM SeOpcionesMenu s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeOpcionesMenu.findByFechaCreacion", query = "SELECT s FROM SeOpcionesMenu s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeOpcionesMenu.findByUsuarioActualizacion", query = "SELECT s FROM SeOpcionesMenu s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeOpcionesMenu.findByFechaActualizacion", query = "SELECT s FROM SeOpcionesMenu s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeOpcionesMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opciones_menu")
    private Long idOpcionesMenu;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seOpcionesMenu")
    private List<SeOpcionesRoles> seOpcionesRolesList;

    public SeOpcionesMenu() {
    }

    public SeOpcionesMenu(Long idOpcionesMenu) {
        this.idOpcionesMenu = idOpcionesMenu;
    }

    public Long getIdOpcionesMenu() {
        return idOpcionesMenu;
    }

    public void setIdOpcionesMenu(Long idOpcionesMenu) {
        this.idOpcionesMenu = idOpcionesMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    public List<SeOpcionesRoles> getSeOpcionesRolesList() {
        return seOpcionesRolesList;
    }

    public void setSeOpcionesRolesList(List<SeOpcionesRoles> seOpcionesRolesList) {
        this.seOpcionesRolesList = seOpcionesRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionesMenu != null ? idOpcionesMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeOpcionesMenu)) {
            return false;
        }
        SeOpcionesMenu other = (SeOpcionesMenu) object;
        if ((this.idOpcionesMenu == null && other.idOpcionesMenu != null) || (this.idOpcionesMenu != null && !this.idOpcionesMenu.equals(other.idOpcionesMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeOpcionesMenu[ idOpcionesMenu=" + idOpcionesMenu + " ]";
    }
    
}
