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
@Table(name = "co_detalle_orden_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleOrdenPedido.findAll", query = "SELECT c FROM CoDetalleOrdenPedido c")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdDetalleOrdenPedido", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.idDetalleOrdenPedido = :idDetalleOrdenPedido")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdOrdenPedido", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.idOrdenPedido = :idOrdenPedido")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdEmpresa", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdSurcusal", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.idSurcusal = :idSurcusal")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByLineaDetalle", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdProducto", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByDescripcion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByCantidadSolicitada", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.cantidadSolicitada = :cantidadSolicitada")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByEstado", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByUsuarioCreacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByFechaCreacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByUsuarioActualizacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByFechaActualizacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CoDetalleOrdenPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_orden_pedido")
    private Long idDetalleOrdenPedido;
    @Column(name = "id_orden_pedido")
    private BigInteger idOrdenPedido;
    @Column(name = "id_empresa")
    private BigInteger idEmpresa;
    @Column(name = "id_surcusal")
    private BigInteger idSurcusal;
    @Column(name = "linea_detalle")
    private BigInteger lineaDetalle;
    @Column(name = "id_producto")
    private BigInteger idProducto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad_solicitada")
    private BigInteger cantidadSolicitada;
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

    public CoDetalleOrdenPedido() {
    }

    public CoDetalleOrdenPedido(Long idDetalleOrdenPedido) {
        this.idDetalleOrdenPedido = idDetalleOrdenPedido;
    }

    public Long getIdDetalleOrdenPedido() {
        return idDetalleOrdenPedido;
    }

    public void setIdDetalleOrdenPedido(Long idDetalleOrdenPedido) {
        this.idDetalleOrdenPedido = idDetalleOrdenPedido;
    }

    public BigInteger getIdOrdenPedido() {
        return idOrdenPedido;
    }

    public void setIdOrdenPedido(BigInteger idOrdenPedido) {
        this.idOrdenPedido = idOrdenPedido;
    }

    public BigInteger getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigInteger idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BigInteger getIdSurcusal() {
        return idSurcusal;
    }

    public void setIdSurcusal(BigInteger idSurcusal) {
        this.idSurcusal = idSurcusal;
    }

    public BigInteger getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(BigInteger lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
    }

    public BigInteger getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigInteger idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(BigInteger cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleOrdenPedido != null ? idDetalleOrdenPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenPedido)) {
            return false;
        }
        CoDetalleOrdenPedido other = (CoDetalleOrdenPedido) object;
        if ((this.idDetalleOrdenPedido == null && other.idDetalleOrdenPedido != null) || (this.idDetalleOrdenPedido != null && !this.idDetalleOrdenPedido.equals(other.idDetalleOrdenPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenPedido[ idDetalleOrdenPedido=" + idDetalleOrdenPedido + " ]";
    }
    
}
