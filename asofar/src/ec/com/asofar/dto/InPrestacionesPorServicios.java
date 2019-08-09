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
 * @author admin
 */
@Entity
@Table(name = "in_prestaciones_por_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InPrestacionesPorServicios.findAll", query = "SELECT i FROM InPrestacionesPorServicios i")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByIdPrestacion", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.inPrestacionesPorServiciosPK.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByIdUnidadServicio", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.inPrestacionesPorServiciosPK.idUnidadServicio = :idUnidadServicio")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByIdEmpresa", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByEstado", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.estado = :estado")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByEsFacturable", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.esFacturable = :esFacturable")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByAplicaDescuento", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.aplicaDescuento = :aplicaDescuento")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByAplicaTarifario", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.aplicaTarifario = :aplicaTarifario")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByFechaCreacion", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByUsuarioCreacion", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByFechaActualizacion", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "InPrestacionesPorServicios.findByUsuarioActualizacion", query = "SELECT i FROM InPrestacionesPorServicios i WHERE i.usuarioActualizacion = :usuarioActualizacion")})
public class InPrestacionesPorServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InPrestacionesPorServiciosPK inPrestacionesPorServiciosPK;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "estado")
    private String estado;
    @Column(name = "es_facturable")
    private String esFacturable;
    @Column(name = "aplica_descuento")
    private String aplicaDescuento;
    @Column(name = "aplica_tarifario")
    private String aplicaTarifario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inPrestacionesPorServicios")
    private List<CoDetallesTarifa> coDetallesTarifaList;
    @JoinColumn(name = "id_unidad_servicio", referencedColumnName = "id_unidad_servicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VeUnidadServicio veUnidadServicio;
    @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrPrestaciones prPrestaciones;

    public InPrestacionesPorServicios() {
    }

    public InPrestacionesPorServicios(InPrestacionesPorServiciosPK inPrestacionesPorServiciosPK) {
        this.inPrestacionesPorServiciosPK = inPrestacionesPorServiciosPK;
    }

    public InPrestacionesPorServicios(long idPrestacion, long idUnidadServicio) {
        this.inPrestacionesPorServiciosPK = new InPrestacionesPorServiciosPK(idPrestacion, idUnidadServicio);
    }

    public InPrestacionesPorServiciosPK getInPrestacionesPorServiciosPK() {
        return inPrestacionesPorServiciosPK;
    }

    public void setInPrestacionesPorServiciosPK(InPrestacionesPorServiciosPK inPrestacionesPorServiciosPK) {
        this.inPrestacionesPorServiciosPK = inPrestacionesPorServiciosPK;
    }

    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEsFacturable() {
        return esFacturable;
    }

    public void setEsFacturable(String esFacturable) {
        this.esFacturable = esFacturable;
    }

    public String getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(String aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public String getAplicaTarifario() {
        return aplicaTarifario;
    }

    public void setAplicaTarifario(String aplicaTarifario) {
        this.aplicaTarifario = aplicaTarifario;
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
    public List<CoDetallesTarifa> getCoDetallesTarifaList() {
        return coDetallesTarifaList;
    }

    public void setCoDetallesTarifaList(List<CoDetallesTarifa> coDetallesTarifaList) {
        this.coDetallesTarifaList = coDetallesTarifaList;
    }

    public VeUnidadServicio getVeUnidadServicio() {
        return veUnidadServicio;
    }

    public void setVeUnidadServicio(VeUnidadServicio veUnidadServicio) {
        this.veUnidadServicio = veUnidadServicio;
    }

    public PrPrestaciones getPrPrestaciones() {
        return prPrestaciones;
    }

    public void setPrPrestaciones(PrPrestaciones prPrestaciones) {
        this.prPrestaciones = prPrestaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inPrestacionesPorServiciosPK != null ? inPrestacionesPorServiciosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InPrestacionesPorServicios)) {
            return false;
        }
        InPrestacionesPorServicios other = (InPrestacionesPorServicios) object;
        if ((this.inPrestacionesPorServiciosPK == null && other.inPrestacionesPorServiciosPK != null) || (this.inPrestacionesPorServiciosPK != null && !this.inPrestacionesPorServiciosPK.equals(other.inPrestacionesPorServiciosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InPrestacionesPorServicios[ inPrestacionesPorServiciosPK=" + inPrestacionesPorServiciosPK + " ]";
    }
    
}
