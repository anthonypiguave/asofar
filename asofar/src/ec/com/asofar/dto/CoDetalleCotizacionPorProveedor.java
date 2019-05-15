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
@Table(name = "co_detalle_cotizacion_por_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findAll", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdCotizacionesPorPorveedor", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.coDetalleCotizacionPorProveedorPK.idCotizacionesPorPorveedor = :idCotizacionesPorPorveedor")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdCotizacion", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.coDetalleCotizacionPorProveedorPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdEmpresa", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.coDetalleCotizacionPorProveedorPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdSucursal", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.coDetalleCotizacionPorProveedorPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByLineaDetalle", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.coDetalleCotizacionPorProveedorPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByDescripcion", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdProducto", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByValorMinimoReferencial", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.valorMinimoReferencial = :valorMinimoReferencial")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByValorMaximoReferencial", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.valorMaximoReferencial = :valorMaximoReferencial")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByCantidadPedido", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.cantidadPedido = :cantidadPedido")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByCantidadCotizado", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.cantidadCotizado = :cantidadCotizado")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByPrecioUnitarioNeto", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.precioUnitarioNeto = :precioUnitarioNeto")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByEstado", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByFecha", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.fecha = :fecha")})
public class CoDetalleCotizacionPorProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetalleCotizacionPorProveedorPK coDetalleCotizacionPorProveedorPK;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_minimo_referencial")
    private Double valorMinimoReferencial;
    @Column(name = "valor_maximo_referencial")
    private Double valorMaximoReferencial;
    @Column(name = "cantidad_pedido")
    private Integer cantidadPedido;
    @Column(name = "cantidad_cotizado")
    private Double cantidadCotizado;
    @Column(name = "precio_unitario_neto")
    private Double precioUnitarioNeto;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumns({
        @JoinColumn(name = "id_cotizaciones_por_porveedor", referencedColumnName = "id_cotizaciones_por_porveedor", insertable = false, updatable = false)
        , @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CoCotizacionesPorPorveedor coCotizacionesPorPorveedor;

    public CoDetalleCotizacionPorProveedor() {
    }

    public CoDetalleCotizacionPorProveedor(CoDetalleCotizacionPorProveedorPK coDetalleCotizacionPorProveedorPK) {
        this.coDetalleCotizacionPorProveedorPK = coDetalleCotizacionPorProveedorPK;
    }

    public CoDetalleCotizacionPorProveedor(long idCotizacionesPorPorveedor, long idCotizacion, long idEmpresa, long idSucursal, long lineaDetalle) {
        this.coDetalleCotizacionPorProveedorPK = new CoDetalleCotizacionPorProveedorPK(idCotizacionesPorPorveedor, idCotizacion, idEmpresa, idSucursal, lineaDetalle);
    }

    public CoDetalleCotizacionPorProveedorPK getCoDetalleCotizacionPorProveedorPK() {
        return coDetalleCotizacionPorProveedorPK;
    }

    public void setCoDetalleCotizacionPorProveedorPK(CoDetalleCotizacionPorProveedorPK coDetalleCotizacionPorProveedorPK) {
        this.coDetalleCotizacionPorProveedorPK = coDetalleCotizacionPorProveedorPK;
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

    public Double getValorMinimoReferencial() {
        return valorMinimoReferencial;
    }

    public void setValorMinimoReferencial(Double valorMinimoReferencial) {
        this.valorMinimoReferencial = valorMinimoReferencial;
    }

    public Double getValorMaximoReferencial() {
        return valorMaximoReferencial;
    }

    public void setValorMaximoReferencial(Double valorMaximoReferencial) {
        this.valorMaximoReferencial = valorMaximoReferencial;
    }

    public Integer getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Integer cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public Double getCantidadCotizado() {
        return cantidadCotizado;
    }

    public void setCantidadCotizado(Double cantidadCotizado) {
        this.cantidadCotizado = cantidadCotizado;
    }

    public Double getPrecioUnitarioNeto() {
        return precioUnitarioNeto;
    }

    public void setPrecioUnitarioNeto(Double precioUnitarioNeto) {
        this.precioUnitarioNeto = precioUnitarioNeto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CoCotizacionesPorPorveedor getCoCotizacionesPorPorveedor() {
        return coCotizacionesPorPorveedor;
    }

    public void setCoCotizacionesPorPorveedor(CoCotizacionesPorPorveedor coCotizacionesPorPorveedor) {
        this.coCotizacionesPorPorveedor = coCotizacionesPorPorveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coDetalleCotizacionPorProveedorPK != null ? coDetalleCotizacionPorProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleCotizacionPorProveedor)) {
            return false;
        }
        CoDetalleCotizacionPorProveedor other = (CoDetalleCotizacionPorProveedor) object;
        if ((this.coDetalleCotizacionPorProveedorPK == null && other.coDetalleCotizacionPorProveedorPK != null) || (this.coDetalleCotizacionPorProveedorPK != null && !this.coDetalleCotizacionPorProveedorPK.equals(other.coDetalleCotizacionPorProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleCotizacionPorProveedor[ coDetalleCotizacionPorProveedorPK=" + coDetalleCotizacionPorProveedorPK + " ]";
    }
    
}
