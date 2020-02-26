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
 * @author usuario
 */
@Entity
@Table(name = "co_detalle_orden_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetalleOrdenPedido.findAll", query = "SELECT c FROM CoDetalleOrdenPedido c")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdDetalleOrdenPedido", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.idDetalleOrdenPedido = :idDetalleOrdenPedido")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdOrdenPedido", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.idOrdenPedido = :idOrdenPedido")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdEmpresa", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdSurcusal", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.idSurcusal = :idSurcusal")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByLineaDetalle", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.lineaDetalle = :lineaDetalle")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByIdProducto", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.coDetalleOrdenPedidoPK.idProducto = :idProducto")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByFormaPago", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.formaPago = :formaPago")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByDescripcion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByCantidadSolicitada", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.cantidadSolicitada = :cantidadSolicitada")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByEstado", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByUsuarioCreacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByFechaCreacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByUsuarioActualizacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoDetalleOrdenPedido.findByFechaActualizacion", query = "SELECT c FROM CoDetalleOrdenPedido c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CoDetalleOrdenPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetalleOrdenPedidoPK coDetalleOrdenPedidoPK;
    @Column(name = "forma_pago")
    private String formaPago;
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
    @JoinColumns({
        @JoinColumn(name = "id_orden_pedido", referencedColumnName = "id_orden_pedido", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_surcusal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CoOrdenPedido coOrdenPedido;

    public CoDetalleOrdenPedido() {
    }

    public CoDetalleOrdenPedido(CoDetalleOrdenPedidoPK coDetalleOrdenPedidoPK) {
        this.coDetalleOrdenPedidoPK = coDetalleOrdenPedidoPK;
    }

    public CoDetalleOrdenPedido(long idDetalleOrdenPedido, long idOrdenPedido, long idEmpresa, long idSurcusal, long lineaDetalle, long idProducto) {
        this.coDetalleOrdenPedidoPK = new CoDetalleOrdenPedidoPK(idDetalleOrdenPedido, idOrdenPedido, idEmpresa, idSurcusal, lineaDetalle, idProducto);
    }

    public CoDetalleOrdenPedidoPK getCoDetalleOrdenPedidoPK() {
        return coDetalleOrdenPedidoPK;
    }

    public void setCoDetalleOrdenPedidoPK(CoDetalleOrdenPedidoPK coDetalleOrdenPedidoPK) {
        this.coDetalleOrdenPedidoPK = coDetalleOrdenPedidoPK;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
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

    public CoOrdenPedido getCoOrdenPedido() {
        return coOrdenPedido;
    }

    public void setCoOrdenPedido(CoOrdenPedido coOrdenPedido) {
        this.coOrdenPedido = coOrdenPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coDetalleOrdenPedidoPK != null ? coDetalleOrdenPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetalleOrdenPedido)) {
            return false;
        }
        CoDetalleOrdenPedido other = (CoDetalleOrdenPedido) object;
        if ((this.coDetalleOrdenPedidoPK == null && other.coDetalleOrdenPedidoPK != null) || (this.coDetalleOrdenPedidoPK != null && !this.coDetalleOrdenPedidoPK.equals(other.coDetalleOrdenPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetalleOrdenPedido[ coDetalleOrdenPedidoPK=" + coDetalleOrdenPedidoPK + " ]";
    }
    
}
