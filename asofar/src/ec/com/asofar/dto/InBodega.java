/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
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
    , @NamedQuery(name = "InBodega.findByIdUsuarioCreacion", query = "SELECT i FROM InBodega i WHERE i.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "InBodega.findByFechaCreacion", query = "SELECT i FROM InBodega i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "InBodega.findByIdUsuarioActualizacion", query = "SELECT i FROM InBodega i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")
    , @NamedQuery(name = "InBodega.findByFechaActualizacion", query = "SELECT i FROM InBodega i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class InBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InBodegaPK inBodegaPK;
    @Column(name = "nombre_bodega")
    private String nombreBodega;
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_usuario_creacion")
    private BigInteger idUsuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "id_usuario_actualizacion")
    private BigInteger idUsuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

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

    public BigInteger getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(BigInteger idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(BigInteger idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
