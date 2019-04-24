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
 * @author ADMIN
 */
@Entity
@Table(name = "se_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeSucursal.findAll", query = "SELECT s FROM SeSucursal s")
    , @NamedQuery(name = "SeSucursal.findByIdSucursal", query = "SELECT s FROM SeSucursal s WHERE s.seSucursalPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "SeSucursal.findByIdEmpresa", query = "SELECT s FROM SeSucursal s WHERE s.seSucursalPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "SeSucursal.findByNombreComercial", query = "SELECT s FROM SeSucursal s WHERE s.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "SeSucursal.findByTelefono", query = "SELECT s FROM SeSucursal s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SeSucursal.findByDireccion", query = "SELECT s FROM SeSucursal s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "SeSucursal.findByCorreo", query = "SELECT s FROM SeSucursal s WHERE s.correo = :correo")
    , @NamedQuery(name = "SeSucursal.findByUsuarioCreacion", query = "SELECT s FROM SeSucursal s WHERE s.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "SeSucursal.findByFechaCreacion", query = "SELECT s FROM SeSucursal s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeSucursal.findByUsuarioActualizacion", query = "SELECT s FROM SeSucursal s WHERE s.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "SeSucursal.findByFechaActualizacion", query = "SELECT s FROM SeSucursal s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SeSucursal.findByEstado", query = "SELECT s FROM SeSucursal s WHERE s.estado = :estado")})
public class SeSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeSucursalPK seSucursalPK;
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correo")
    private String correo;
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_actualizacion")
    private BigInteger usuarioActualizacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<SeUsuarioSucurRol> seUsuarioSucurRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<CoOrdenPedido> coOrdenPedidoList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeEmpresa seEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<CoOrdenCompras> coOrdenComprasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<InKardex> inKardexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<VeFacturaDetalle> veFacturaDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<VeFactura> veFacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<PrProductoBodega> prProductoBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seSucursal")
    private List<PrTarifario> prTarifarioList;

    public SeSucursal() {
    }

    public SeSucursal(SeSucursalPK seSucursalPK) {
        this.seSucursalPK = seSucursalPK;
    }

    public SeSucursal(long idSucursal, long idEmpresa) {
        this.seSucursalPK = new SeSucursalPK(idSucursal, idEmpresa);
    }

    public SeSucursalPK getSeSucursalPK() {
        return seSucursalPK;
    }

    public void setSeSucursalPK(SeSucursalPK seSucursalPK) {
        this.seSucursalPK = seSucursalPK;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(BigInteger usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<SeUsuarioSucurRol> getSeUsuarioSucurRolList() {
        return seUsuarioSucurRolList;
    }

    public void setSeUsuarioSucurRolList(List<SeUsuarioSucurRol> seUsuarioSucurRolList) {
        this.seUsuarioSucurRolList = seUsuarioSucurRolList;
    }

    @XmlTransient
    public List<CoOrdenPedido> getCoOrdenPedidoList() {
        return coOrdenPedidoList;
    }

    public void setCoOrdenPedidoList(List<CoOrdenPedido> coOrdenPedidoList) {
        this.coOrdenPedidoList = coOrdenPedidoList;
    }

    public SeEmpresa getSeEmpresa() {
        return seEmpresa;
    }

    public void setSeEmpresa(SeEmpresa seEmpresa) {
        this.seEmpresa = seEmpresa;
    }

    @XmlTransient
    public List<CoOrdenCompras> getCoOrdenComprasList() {
        return coOrdenComprasList;
    }

    public void setCoOrdenComprasList(List<CoOrdenCompras> coOrdenComprasList) {
        this.coOrdenComprasList = coOrdenComprasList;
    }

    @XmlTransient
    public List<InKardex> getInKardexList() {
        return inKardexList;
    }

    public void setInKardexList(List<InKardex> inKardexList) {
        this.inKardexList = inKardexList;
    }

    @XmlTransient
    public List<VeFacturaDetalle> getVeFacturaDetalleList() {
        return veFacturaDetalleList;
    }

    public void setVeFacturaDetalleList(List<VeFacturaDetalle> veFacturaDetalleList) {
        this.veFacturaDetalleList = veFacturaDetalleList;
    }

    @XmlTransient
    public List<VeFactura> getVeFacturaList() {
        return veFacturaList;
    }

    public void setVeFacturaList(List<VeFactura> veFacturaList) {
        this.veFacturaList = veFacturaList;
    }

    @XmlTransient
    public List<PrProductoBodega> getPrProductoBodegaList() {
        return prProductoBodegaList;
    }

    public void setPrProductoBodegaList(List<PrProductoBodega> prProductoBodegaList) {
        this.prProductoBodegaList = prProductoBodegaList;
    }

    @XmlTransient
    public List<PrTarifario> getPrTarifarioList() {
        return prTarifarioList;
    }

    public void setPrTarifarioList(List<PrTarifario> prTarifarioList) {
        this.prTarifarioList = prTarifarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seSucursalPK != null ? seSucursalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeSucursal)) {
            return false;
        }
        SeSucursal other = (SeSucursal) object;
        if ((this.seSucursalPK == null && other.seSucursalPK != null) || (this.seSucursalPK != null && !this.seSucursalPK.equals(other.seSucursalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeSucursal[ seSucursalPK=" + seSucursalPK + " ]";
    }
    
}
