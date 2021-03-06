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
 * @author usuario
 */
@Entity
@Table(name = "co_items_cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoItemsCotizacion.findAll", query = "SELECT c FROM CoItemsCotizacion c")
    , @NamedQuery(name = "CoItemsCotizacion.findByIdCotizacion", query = "SELECT c FROM CoItemsCotizacion c WHERE c.coItemsCotizacionPK.idCotizacion = :idCotizacion")
    , @NamedQuery(name = "CoItemsCotizacion.findByIdEmpresa", query = "SELECT c FROM CoItemsCotizacion c WHERE c.coItemsCotizacionPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoItemsCotizacion.findByIdSucursal", query = "SELECT c FROM CoItemsCotizacion c WHERE c.coItemsCotizacionPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoItemsCotizacion.findByFechaEmision", query = "SELECT c FROM CoItemsCotizacion c WHERE c.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "CoItemsCotizacion.findByIdEstado", query = "SELECT c FROM CoItemsCotizacion c WHERE c.idEstado = :idEstado")
    , @NamedQuery(name = "CoItemsCotizacion.findByEstado", query = "SELECT c FROM CoItemsCotizacion c WHERE c.estado = :estado")
    , @NamedQuery(name = "CoItemsCotizacion.findByUsuarioCreacion", query = "SELECT c FROM CoItemsCotizacion c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CoItemsCotizacion.findByFechaCreacion", query = "SELECT c FROM CoItemsCotizacion c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CoItemsCotizacion.findByUsuarioActualizacion", query = "SELECT c FROM CoItemsCotizacion c WHERE c.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "CoItemsCotizacion.findByFechaActualizacion", query = "SELECT c FROM CoItemsCotizacion c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "CoItemsCotizacion.findByProcesado", query = "SELECT c FROM CoItemsCotizacion c WHERE c.procesado = :procesado")})
public class CoItemsCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoItemsCotizacionPK coItemsCotizacionPK;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Column(name = "id_estado")
    private BigInteger idEstado;
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
    @Column(name = "procesado")
    private String procesado;
    @JoinColumn(name = "id_tipo_compra", referencedColumnName = "id_in_tipo_compra")
    @ManyToOne
    private InTipoCompra idTipoCompra;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_tipo_departamento")
    @ManyToOne
    private InTipoDepartamento idDepartamento;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne
    private InTipoDocumento idTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coItemsCotizacion")
    private List<CoDetItemsCotizacion> coDetItemsCotizacionList;

    public CoItemsCotizacion() {
    }

    public CoItemsCotizacion(CoItemsCotizacionPK coItemsCotizacionPK) {
        this.coItemsCotizacionPK = coItemsCotizacionPK;
    }

    public CoItemsCotizacion(long idCotizacion, long idEmpresa, long idSucursal) {
        this.coItemsCotizacionPK = new CoItemsCotizacionPK(idCotizacion, idEmpresa, idSucursal);
    }

    public CoItemsCotizacionPK getCoItemsCotizacionPK() {
        return coItemsCotizacionPK;
    }

    public void setCoItemsCotizacionPK(CoItemsCotizacionPK coItemsCotizacionPK) {
        this.coItemsCotizacionPK = coItemsCotizacionPK;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigInteger getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(BigInteger idEstado) {
        this.idEstado = idEstado;
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

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    public InTipoCompra getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(InTipoCompra idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public InTipoDepartamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(InTipoDepartamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public InTipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(InTipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @XmlTransient
    public List<CoDetItemsCotizacion> getCoDetItemsCotizacionList() {
        return coDetItemsCotizacionList;
    }

    public void setCoDetItemsCotizacionList(List<CoDetItemsCotizacion> coDetItemsCotizacionList) {
        this.coDetItemsCotizacionList = coDetItemsCotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coItemsCotizacionPK != null ? coItemsCotizacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoItemsCotizacion)) {
            return false;
        }
        CoItemsCotizacion other = (CoItemsCotizacion) object;
        if ((this.coItemsCotizacionPK == null && other.coItemsCotizacionPK != null) || (this.coItemsCotizacionPK != null && !this.coItemsCotizacionPK.equals(other.coItemsCotizacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoItemsCotizacion[ coItemsCotizacionPK=" + coItemsCotizacionPK + " ]";
    }
    
}
