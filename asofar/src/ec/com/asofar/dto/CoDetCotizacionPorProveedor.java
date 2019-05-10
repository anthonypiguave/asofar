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
 * @author admin1
 */
@Entity
@Table(name = "co_det_cotizacion_por_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetCotizacionPorProveedor.findAll", query = "SELECT c FROM CoDetCotizacionPorProveedor c")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByIdCotizacionesPorPorveedor", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.coDetCotizacionPorProveedorPK.idCotizacionesPorPorveedor = :idCotizacionesPorPorveedor")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByIdCotizacion", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.coDetCotizacionPorProveedorPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByIdEmpresa", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.coDetCotizacionPorProveedorPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByIdSucursal", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.coDetCotizacionPorProveedorPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByLineaDetalle", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.coDetCotizacionPorProveedorPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByDescripcion", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByIdProducto", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByValorMinimoReferencial", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.valorMinimoReferencial = :valorMinimoReferencial")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByValorMaximoReferencial", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.valorMaximoReferencial = :valorMaximoReferencial")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByCantidadPedido", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.cantidadPedido = :cantidadPedido")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByCantidadCotizado", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.cantidadCotizado = :cantidadCotizado")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByPrecioUnitarioNeto", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.precioUnitarioNeto = :precioUnitarioNeto")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByEstado", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetCotizacionPorProveedor.findByFecha", query = "SELECT c FROM CoDetCotizacionPorProveedor c WHERE c.fecha = :fecha")})
public class CoDetCotizacionPorProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetCotizacionPorProveedorPK coDetCotizacionPorProveedorPK;
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

    public CoDetCotizacionPorProveedor() {
    }

    public CoDetCotizacionPorProveedor(CoDetCotizacionPorProveedorPK coDetCotizacionPorProveedorPK) {
        this.coDetCotizacionPorProveedorPK = coDetCotizacionPorProveedorPK;
    }

    public CoDetCotizacionPorProveedor(long idCotizacionesPorPorveedor, long idCotizacion, long idEmpresa, long idSucursal, long lineaDetalle) {
        this.coDetCotizacionPorProveedorPK = new CoDetCotizacionPorProveedorPK(idCotizacionesPorPorveedor, idCotizacion, idEmpresa, idSucursal, lineaDetalle);
    }

    public CoDetCotizacionPorProveedorPK getCoDetCotizacionPorProveedorPK() {
        return coDetCotizacionPorProveedorPK;
    }

    public void setCoDetCotizacionPorProveedorPK(CoDetCotizacionPorProveedorPK coDetCotizacionPorProveedorPK) {
        this.coDetCotizacionPorProveedorPK = coDetCotizacionPorProveedorPK;
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
        hash += (coDetCotizacionPorProveedorPK != null ? coDetCotizacionPorProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetCotizacionPorProveedor)) {
            return false;
        }
        CoDetCotizacionPorProveedor other = (CoDetCotizacionPorProveedor) object;
        if ((this.coDetCotizacionPorProveedorPK == null && other.coDetCotizacionPorProveedorPK != null) || (this.coDetCotizacionPorProveedorPK != null && !this.coDetCotizacionPorProveedorPK.equals(other.coDetCotizacionPorProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetCotizacionPorProveedor[ coDetCotizacionPorProveedorPK=" + coDetCotizacionPorProveedorPK + " ]";
    }
    
}
