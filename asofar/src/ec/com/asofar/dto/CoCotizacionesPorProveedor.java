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
 * @author ADMIN
 */
@Entity
@Table(name = "co_cotizaciones_por_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoCotizacionesPorProveedor.findAll", query = "SELECT c FROM CoCotizacionesPorProveedor c")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByIdCotizacionesPorPorveedor", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.coCotizacionesPorProveedorPK.idCotizacionesPorPorveedor = :idCotizacionesPorPorveedor")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByIdCotizacion", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.coCotizacionesPorProveedorPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByIdEmpresa", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.coCotizacionesPorProveedorPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByIdSucursal", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.coCotizacionesPorProveedorPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByIdTipoCompra", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.idTipoCompra = :idTipoCompra")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByFechaEnvioCotizacion", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.fechaEnvioCotizacion = :fechaEnvioCotizacion")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByMailContacto", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.mailContacto = :mailContacto")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByTelefonoContacto", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.telefonoContacto = :telefonoContacto")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByEstado", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByFechaIngreso", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByUsuarioIngreso", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.usuarioIngreso = :usuarioIngreso")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByFechaActualizacion", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "CoCotizacionesPorProveedor.findByUsuarioActualizacion", query = "SELECT c FROM CoCotizacionesPorProveedor c WHERE c.usuarioActualizacion = :usuarioActualizacion")})
public class CoCotizacionesPorProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoCotizacionesPorProveedorPK coCotizacionesPorProveedorPK;
    @Column(name = "id_tipo_compra")
    private BigInteger idTipoCompra;
    @Column(name = "fecha_envio_cotizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvioCotizacion;
    @Column(name = "mail_contacto")
    private String mailContacto;
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "usuario_ingreso")
    private String usuarioIngreso;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private CoProveedores idProveedor;
    @JoinColumns({
        @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CoItemsCotizacion coItemsCotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coCotizacionesPorProveedor")
    private List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorList;

    public CoCotizacionesPorProveedor() {
    }

    public CoCotizacionesPorProveedor(CoCotizacionesPorProveedorPK coCotizacionesPorProveedorPK) {
        this.coCotizacionesPorProveedorPK = coCotizacionesPorProveedorPK;
    }

    public CoCotizacionesPorProveedor(long idCotizacionesPorPorveedor, long idCotizacion, long idEmpresa, long idSucursal) {
        this.coCotizacionesPorProveedorPK = new CoCotizacionesPorProveedorPK(idCotizacionesPorPorveedor, idCotizacion, idEmpresa, idSucursal);
    }

    public CoCotizacionesPorProveedorPK getCoCotizacionesPorProveedorPK() {
        return coCotizacionesPorProveedorPK;
    }

    public void setCoCotizacionesPorProveedorPK(CoCotizacionesPorProveedorPK coCotizacionesPorProveedorPK) {
        this.coCotizacionesPorProveedorPK = coCotizacionesPorProveedorPK;
    }

    public BigInteger getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(BigInteger idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public Date getFechaEnvioCotizacion() {
        return fechaEnvioCotizacion;
    }

    public void setFechaEnvioCotizacion(Date fechaEnvioCotizacion) {
        this.fechaEnvioCotizacion = fechaEnvioCotizacion;
    }

    public String getMailContacto() {
        return mailContacto;
    }

    public void setMailContacto(String mailContacto) {
        this.mailContacto = mailContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(String usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
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

    public CoProveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(CoProveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public CoItemsCotizacion getCoItemsCotizacion() {
        return coItemsCotizacion;
    }

    public void setCoItemsCotizacion(CoItemsCotizacion coItemsCotizacion) {
        this.coItemsCotizacion = coItemsCotizacion;
    }

    @XmlTransient
    public List<CoDetalleCotizacionPorProveedor> getCoDetalleCotizacionPorProveedorList() {
        return coDetalleCotizacionPorProveedorList;
    }

    public void setCoDetalleCotizacionPorProveedorList(List<CoDetalleCotizacionPorProveedor> coDetalleCotizacionPorProveedorList) {
        this.coDetalleCotizacionPorProveedorList = coDetalleCotizacionPorProveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coCotizacionesPorProveedorPK != null ? coCotizacionesPorProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoCotizacionesPorProveedor)) {
            return false;
        }
        CoCotizacionesPorProveedor other = (CoCotizacionesPorProveedor) object;
        if ((this.coCotizacionesPorProveedorPK == null && other.coCotizacionesPorProveedorPK != null) || (this.coCotizacionesPorProveedorPK != null && !this.coCotizacionesPorProveedorPK.equals(other.coCotizacionesPorProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoCotizacionesPorProveedor[ coCotizacionesPorProveedorPK=" + coCotizacionesPorProveedorPK + " ]";
    }
    
}
