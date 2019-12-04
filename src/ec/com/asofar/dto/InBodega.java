/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "in_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InBodega.findAll", query = "SELECT i FROM InBodega i")
    , @NamedQuery(name = "InBodega.findByIdBodega", query = "SELECT i FROM InBodega i WHERE i.inBodegaPK.idBodega = :idBodega")
    , @NamedQuery(name = "InBodega.findByIdTipoBodega", query = "SELECT i FROM InBodega i WHERE i.inBodegaPK.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "InBodega.findByIdEmpresa", query = "SELECT i FROM InBodega i WHERE i.inBodegaPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "InBodega.findByIdSucursal", query = "SELECT i FROM InBodega i WHERE i.inBodegaPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "InBodega.findByNombreBodega", query = "SELECT i FROM InBodega i WHERE i.nombreBodega = :nombreBodega")
    , @NamedQuery(name = "InBodega.findByEstado", query = "SELECT i FROM InBodega i WHERE i.estado = :estado")
    , @NamedQuery(name = "InBodega.findByUsuarioCreacion", query = "SELECT i FROM InBodega i WHERE i.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "InBodega.findByFechaCreacion", query = "SELECT i FROM InBodega i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InBodega.findByUsuarioActualizacion", query = "SELECT i FROM InBodega i WHERE i.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "InBodega.findByFechaActualizacion", query = "SELECT i FROM InBodega i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InBodegaPK inBodegaPK;
    @Column(name = "nombre_bodega")
    private String nombreBodega;
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
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;
    @JoinColumn(name = "id_tipo_bodega", referencedColumnName = "id_tipo_bodega", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InTipoBodega inTipoBodega;
    @OneToMany(mappedBy = "inBodega")
    private List<PrProductoBodega> prProductoBodegaList;

    public InBodega() {
    }

    public InBodega(InBodegaPK inBodegaPK) {
        this.inBodegaPK = inBodegaPK;
    }

    public InBodega(long idBodega, long idTipoBodega, long idEmpresa, long idSucursal) {
        this.inBodegaPK = new InBodegaPK(idBodega, idTipoBodega, idEmpresa, idSucursal);
    }

    public InBodegaPK getInBodegaPK() {
        return inBodegaPK;
    }

    public void setInBodegaPK(InBodegaPK inBodegaPK) {
        this.inBodegaPK = inBodegaPK;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
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

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    public InTipoBodega getInTipoBodega() {
        return inTipoBodega;
    }

    public void setInTipoBodega(InTipoBodega inTipoBodega) {
        this.inTipoBodega = inTipoBodega;
    }

    @XmlTransient
    public List<PrProductoBodega> getPrProductoBodegaList() {
        return prProductoBodegaList;
    }

    public void setPrProductoBodegaList(List<PrProductoBodega> prProductoBodegaList) {
        this.prProductoBodegaList = prProductoBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inBodegaPK != null ? inBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InBodega)) {
            return false;
        }
        InBodega other = (InBodega) object;
        if ((this.inBodegaPK == null && other.inBodegaPK != null) || (this.inBodegaPK != null && !this.inBodegaPK.equals(other.inBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InBodega[ inBodegaPK=" + inBodegaPK + " ]";
    }
    
}
