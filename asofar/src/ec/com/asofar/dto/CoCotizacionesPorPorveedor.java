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
 * @author admin1
 */
@Entity
@Table(name = "co_cotizaciones_por_porveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoCotizacionesPorPorveedor.findAll", query = "SELECT c FROM CoCotizacionesPorPorveedor c")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByIdCotizacionesPorPorveedor", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.coCotizacionesPorPorveedorPK.idCotizacionesPorPorveedor = :idCotizacionesPorPorveedor")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByIdCotizacion", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.coCotizacionesPorPorveedorPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByIdEmpresa", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.coCotizacionesPorPorveedorPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByIdSucursal", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.coCotizacionesPorPorveedorPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByIdTipoCompra", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.idTipoCompra = :idTipoCompra")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByFechaEnvioCotizacion", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.fechaEnvioCotizacion = :fechaEnvioCotizacion")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByMailContacto", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.mailContacto = :mailContacto")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByTelefonoContacto", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.telefonoContacto = :telefonoContacto")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByEstado", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByFechaIngreso", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByUsuarioIngreso", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.usuarioIngreso = :usuarioIngreso")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByFechaActualizacion", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "CoCotizacionesPorPorveedor.findByUsuarioActualizacion", query = "SELECT c FROM CoCotizacionesPorPorveedor c WHERE c.usuarioActualizacion = :usuarioActualizacion")})
public class CoCotizacionesPorPorveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoCotizacionesPorPorveedorPK coCotizacionesPorPorveedorPK;
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
    private BigInteger usuarioIngreso;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "usuario_actualizacion")
    private BigInteger usuarioActualizacion;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private CoProveedores idProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coCotizacionesPorPorveedor")
    private List<CoDetCotizacionPorProveedor> coDetCotizacionPorProveedorList;

    public CoCotizacionesPorPorveedor() {
    }

    public CoCotizacionesPorPorveedor(CoCotizacionesPorPorveedorPK coCotizacionesPorPorveedorPK) {
        this.coCotizacionesPorPorveedorPK = coCotizacionesPorPorveedorPK;
    }

    public CoCotizacionesPorPorveedor(long idCotizacionesPorPorveedor, long idCotizacion, long idEmpresa, long idSucursal) {
        this.coCotizacionesPorPorveedorPK = new CoCotizacionesPorPorveedorPK(idCotizacionesPorPorveedor, idCotizacion, idEmpresa, idSucursal);
    }

    public CoCotizacionesPorPorveedorPK getCoCotizacionesPorPorveedorPK() {
        return coCotizacionesPorPorveedorPK;
    }

    public void setCoCotizacionesPorPorveedorPK(CoCotizacionesPorPorveedorPK coCotizacionesPorPorveedorPK) {
        this.coCotizacionesPorPorveedorPK = coCotizacionesPorPorveedorPK;
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

    public BigInteger getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(BigInteger usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public BigInteger getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(BigInteger usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public CoProveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(CoProveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    @XmlTransient
    public List<CoDetCotizacionPorProveedor> getCoDetCotizacionPorProveedorList() {
        return coDetCotizacionPorProveedorList;
    }

    public void setCoDetCotizacionPorProveedorList(List<CoDetCotizacionPorProveedor> coDetCotizacionPorProveedorList) {
        this.coDetCotizacionPorProveedorList = coDetCotizacionPorProveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coCotizacionesPorPorveedorPK != null ? coCotizacionesPorPorveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoCotizacionesPorPorveedor)) {
            return false;
        }
        CoCotizacionesPorPorveedor other = (CoCotizacionesPorPorveedor) object;
        if ((this.coCotizacionesPorPorveedorPK == null && other.coCotizacionesPorPorveedorPK != null) || (this.coCotizacionesPorPorveedorPK != null && !this.coCotizacionesPorPorveedorPK.equals(other.coCotizacionesPorPorveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoCotizacionesPorPorveedor[ coCotizacionesPorPorveedorPK=" + coCotizacionesPorPorveedorPK + " ]";
    }
    
}
