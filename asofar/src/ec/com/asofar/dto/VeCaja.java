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
 * @author admin
 */
@Entity
@Table(name = "ve_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeCaja.findAll", query = "SELECT v FROM VeCaja v")
    , @NamedQuery(name = "VeCaja.findByIdCaja", query = "SELECT v FROM VeCaja v WHERE v.idCaja = :idCaja")
    , @NamedQuery(name = "VeCaja.findByNombre", query = "SELECT v FROM VeCaja v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "VeCaja.findByEstado", query = "SELECT v FROM VeCaja v WHERE v.estado = :estado")
    , @NamedQuery(name = "VeCaja.findByFechaCreacion", query = "SELECT v FROM VeCaja v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "VeCaja.findByUsuarioCreacion", query = "SELECT v FROM VeCaja v WHERE v.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "VeCaja.findByFechaActualizacion", query = "SELECT v FROM VeCaja v WHERE v.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "VeCaja.findByUsuarioActualizacion", query = "SELECT v FROM VeCaja v WHERE v.usuarioActualizacion = :usuarioActualizacion")})
public class VeCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caja")
    private Long idCaja;
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veCaja")
    private List<VeDetalleCaja> veDetalleCajaList;
    @OneToMany(mappedBy = "idCaja")
    private List<VeFactura> veFacturaList;

    public VeCaja() {
    }

    public VeCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public Long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<VeDetalleCaja> getVeDetalleCajaList() {
        return veDetalleCajaList;
    }

    public void setVeDetalleCajaList(List<VeDetalleCaja> veDetalleCajaList) {
        this.veDetalleCajaList = veDetalleCajaList;
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
        hash += (idCaja != null ? idCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeCaja)) {
            return false;
        }
        VeCaja other = (VeCaja) object;
        if ((this.idCaja == null && other.idCaja != null) || (this.idCaja != null && !this.idCaja.equals(other.idCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeCaja[ idCaja=" + idCaja + " ]";
    }
    
}
