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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "co_det_items_cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetItemsCotizacion.findAll", query = "SELECT c FROM CoDetItemsCotizacion c")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByIdCotizacion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.coDetItemsCotizacionPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByIdEmpresa", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.coDetItemsCotizacionPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByIdSucursal", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.coDetItemsCotizacionPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByLineaDetalle", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.coDetItemsCotizacionPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByDescripcion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByIdProducto", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByCantidadPedida", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.cantidadPedida = :cantidadPedida")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByCantidadCotizada", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.cantidadCotizada = :cantidadCotizada")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByCantidadFaltante", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.cantidadFaltante = :cantidadFaltante")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByValorMinRef", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.valorMinRef = :valorMinRef")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByValorMaxRef", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.valorMaxRef = :valorMaxRef")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByEstado", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByUsuarioCreacion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByFechaCreacion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByUsuarioActualizacion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoDetItemsCotizacion.findByFechaActualizacion", query = "SELECT c FROM CoDetItemsCotizacion c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CoDetItemsCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetItemsCotizacionPK coDetItemsCotizacionPK;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    @Column(name = "cantidad_pedida")
    private BigInteger cantidadPedida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_cotizada")
    private Double cantidadCotizada;
    @Column(name = "cantidad_faltante")
    private Double cantidadFaltante;
    @Column(name = "valor_min_ref")
    private Double valorMinRef;
    @Column(name = "valor_max_ref")
    private Double valorMaxRef;
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
    private String fechaActualizacion;
    @JoinColumns({
        @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CoItemsCotizacion coItemsCotizacion;

    public CoDetItemsCotizacion() {
    }

    public CoDetItemsCotizacion(CoDetItemsCotizacionPK coDetItemsCotizacionPK) {
        this.coDetItemsCotizacionPK = coDetItemsCotizacionPK;
    }

    public CoDetItemsCotizacion(long idCotizacion, long idEmpresa, long idSucursal, long lineaDetalle) {
        this.coDetItemsCotizacionPK = new CoDetItemsCotizacionPK(idCotizacion, idEmpresa, idSucursal, lineaDetalle);
    }

    public CoDetItemsCotizacionPK getCoDetItemsCotizacionPK() {
        return coDetItemsCotizacionPK;
    }

    public void setCoDetItemsCotizacionPK(CoDetItemsCotizacionPK coDetItemsCotizacionPK) {
        this.coDetItemsCotizacionPK = coDetItemsCotizacionPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigInteger idProducto) {
        this.idProducto = idProducto;
    }

    public BigInteger getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(BigInteger cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public Double getCantidadCotizada() {
        return cantidadCotizada;
    }

    public void setCantidadCotizada(Double cantidadCotizada) {
        this.cantidadCotizada = cantidadCotizada;
    }

    public Double getCantidadFaltante() {
        return cantidadFaltante;
    }

    public void setCantidadFaltante(Double cantidadFaltante) {
        this.cantidadFaltante = cantidadFaltante;
    }

    public Double getValorMinRef() {
        return valorMinRef;
    }

    public void setValorMinRef(Double valorMinRef) {
        this.valorMinRef = valorMinRef;
    }

    public Double getValorMaxRef() {
        return valorMaxRef;
    }

    public void setValorMaxRef(Double valorMaxRef) {
        this.valorMaxRef = valorMaxRef;
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public CoItemsCotizacion getCoItemsCotizacion() {
        return coItemsCotizacion;
    }

    public void setCoItemsCotizacion(CoItemsCotizacion coItemsCotizacion) {
        this.coItemsCotizacion = coItemsCotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coDetItemsCotizacionPK != null ? coDetItemsCotizacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetItemsCotizacion)) {
            return false;
        }
        CoDetItemsCotizacion other = (CoDetItemsCotizacion) object;
        if ((this.coDetItemsCotizacionPK == null && other.coDetItemsCotizacionPK != null) || (this.coDetItemsCotizacionPK != null && !this.coDetItemsCotizacionPK.equals(other.coDetItemsCotizacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetItemsCotizacion[ coDetItemsCotizacionPK=" + coDetItemsCotizacionPK + " ]";
    }
    
}
