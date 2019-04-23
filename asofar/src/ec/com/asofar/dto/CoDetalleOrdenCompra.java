/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin1
 */
@Entity
@Table(name = "co_detalle_orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleOrdenCompra.findAll", query = "SELECT c FROM CoDetalleOrdenCompra c")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdDetalleOrdenCompra", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idDetalleOrdenCompra = :idDetalleOrdenCompra")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdEmpresa", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByLineaDetalle", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByDescripcion", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdProducto", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdGrupo", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idGrupo = :idGrupo")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdSubgrupo", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idSubgrupo = :idSubgrupo")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdArticulo", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idArticulo = :idArticulo")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdTipoMarca", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idTipoMarca = :idTipoMarca")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByMarca", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.marca = :marca")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIdTipoModelado", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.idTipoModelado = :idTipoModelado")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByModelado", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.modelado = :modelado")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByCantidadTotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.cantidadTotal = :cantidadTotal")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByPrecioUnitario", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByPrecioTotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.precioTotal = :precioTotal")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByEstado", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByIva", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.iva = :iva")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findBySubtotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.subtotal = :subtotal")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByDescuento", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.descuento = :descuento")
    , @NamedQuery(name = "CoDetalleOrdenCompra.findByTotal", query = "SELECT c FROM CoDetalleOrdenCompra c WHERE c.total = :total")})
public class CoDetalleOrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_orden_compra")
    private Long idDetalleOrdenCompra;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "linea_detalle")
    private BigInteger lineaDetalle;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    @Column(name = "id_grupo")
    private BigInteger idGrupo;
    @Column(name = "id_subgrupo")
    private BigInteger idSubgrupo;
    @Column(name = "id_articulo")
    private BigInteger idArticulo;
    @Column(name = "id_tipo_marca")
    private BigInteger idTipoMarca;
    @Column(name = "marca")
    private String marca;
    @Column(name = "id_tipo_modelado")
    private BigInteger idTipoModelado;
    @Column(name = "modelado")
    private String modelado;
    @Column(name = "cantidad_total")
    private BigInteger cantidadTotal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "precio_total")
    private BigDecimal precioTotal;
    @Column(name = "estado")
    private String estado;
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra")
    @ManyToOne
    private CoOrdenCompras idOrdenCompra;

    public CoDetalleOrdenCompra() {
    }

    public CoDetalleOrdenCompra(Long idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
    }

    public Long getIdDetalleOrdenCompra() {
        return idDetalleOrdenCompra;
    }

    public void setIdDetalleOrdenCompra(Long idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
    }

    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public BigInteger getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigInteger idProducto) {
        this.idProducto = idProducto;
    }

    public BigInteger getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigInteger idGrupo) {
        this.idGrupo = idGrupo;
    }

    public BigInteger getIdSubgrupo() {
        return idSubgrupo;
    }

    public void setIdSubgrupo(BigInteger idSubgrupo) {
        this.idSubgrupo = idSubgrupo;
    }

    public BigInteger getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(BigInteger idArticulo) {
        this.idArticulo = idArticulo;
    }

    public BigInteger getIdTipoMarca() {
        return idTipoMarca;
    }

    public void setIdTipoMarca(BigInteger idTipoMarca) {
        this.idTipoMarca = idTipoMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigInteger getIdTipoModelado() {
        return idTipoModelado;
    }

    public void setIdTipoModelado(BigInteger idTipoModelado) {
        this.idTipoModelado = idTipoModelado;
    }

    public String getModelado() {
        return modelado;
    }

    public void setModelado(String modelado) {
        this.modelado = modelado;
    }

    public BigInteger getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(BigInteger cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public CoOrdenCompras getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(CoOrdenCompras idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleOrdenCompra != null ? idDetalleOrdenCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenCompra)) {
            return false;
        }
        CoDetalleOrdenCompra other = (CoDetalleOrdenCompra) object;
        if ((this.idDetalleOrdenCompra == null && other.idDetalleOrdenCompra != null) || (this.idDetalleOrdenCompra != null && !this.idDetalleOrdenCompra.equals(other.idDetalleOrdenCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenCompra[ idDetalleOrdenCompra=" + idDetalleOrdenCompra + " ]";
    }
    
}