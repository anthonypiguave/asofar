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
 * @author nuevouser
 */
@Entity
@Table(name = "co_detalle_cotizacion_por_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findAll", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdCotizacionesPorPorveedor", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.idCotizacionesPorPorveedor = :idCotizacionesPorPorveedor")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdEmpresa", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdSucursal", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByIdProducto", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByLineaDetalle", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByDescripcion", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByValorMinimoReferencial", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.valorMinimoReferencial = :valorMinimoReferencial")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByValorMaximoReferencial", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.valorMaximoReferencial = :valorMaximoReferencial")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByCantidadPedido", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.cantidadPedido = :cantidadPedido")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByCantidadCotizado", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.cantidadCotizado = :cantidadCotizado")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByPrecioUnitarioNeto", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.precioUnitarioNeto = :precioUnitarioNeto")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByEstado", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleCotizacionPorProveedor.findByFecha", query = "SELECT c FROM CoDetalleCotizacionPorProveedor c WHERE c.fecha = :fecha")})
public class CoDetalleCotizacionPorProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cotizaciones_por_porveedor")
    private Long idCotizacionesPorPorveedor;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "id_sucursal")
    private BigInteger idSucursal;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    @Column(name = "linea_detalle")
    private BigInteger lineaDetalle;
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizaciones_por_porveedor")
    @ManyToOne
    private CoCotizacionesPorProveedor idCotizacion;

    public CoDetalleCotizacionPorProveedor() {
    }

    public CoDetalleCotizacionPorProveedor(Long idCotizacionesPorPorveedor) {
        this.idCotizacionesPorPorveedor = idCotizacionesPorPorveedor;
    }

    public Long getIdCotizacionesPorPorveedor() {
        return idCotizacionesPorPorveedor;
    }

    public void setIdCotizacionesPorPorveedor(Long idCotizacionesPorPorveedor) {
        this.idCotizacionesPorPorveedor = idCotizacionesPorPorveedor;
    }

    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BigInteger getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(BigInteger idSucursal) {
        this.idSucursal = idSucursal;
    }

    public BigInteger getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigInteger idProducto) {
        this.idProducto = idProducto;
    }

    public BigInteger getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(BigInteger lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public CoCotizacionesPorProveedor getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(CoCotizacionesPorProveedor idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCotizacionesPorPorveedor != null ? idCotizacionesPorPorveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleCotizacionPorProveedor)) {
            return false;
        }
        CoDetalleCotizacionPorProveedor other = (CoDetalleCotizacionPorProveedor) object;
        if ((this.idCotizacionesPorPorveedor == null && other.idCotizacionesPorPorveedor != null) || (this.idCotizacionesPorPorveedor != null && !this.idCotizacionesPorPorveedor.equals(other.idCotizacionesPorPorveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleCotizacionPorProveedor[ idCotizacionesPorPorveedor=" + idCotizacionesPorPorveedor + " ]";
    }
    
}
