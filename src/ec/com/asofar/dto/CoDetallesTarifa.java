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
 * @author admin
 */
@Entity
@Table(name = "co_detalles_tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoDetallesTarifa.findAll", query = "SELECT c FROM CoDetallesTarifa c")
    , @NamedQuery(name = "CoDetallesTarifa.findByIdTarifa", query = "SELECT c FROM CoDetallesTarifa c WHERE c.coDetallesTarifaPK.idTarifa = :idTarifa")
    , @NamedQuery(name = "CoDetallesTarifa.findByIdEmpresa", query = "SELECT c FROM CoDetallesTarifa c WHERE c.coDetallesTarifaPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "CoDetallesTarifa.findByIdSucursal", query = "SELECT c FROM CoDetallesTarifa c WHERE c.coDetallesTarifaPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "CoDetallesTarifa.findByIdPrestacion", query = "SELECT c FROM CoDetallesTarifa c WHERE c.coDetallesTarifaPK.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "CoDetallesTarifa.findByIdUnidadServicio", query = "SELECT c FROM CoDetallesTarifa c WHERE c.coDetallesTarifaPK.idUnidadServicio = :idUnidadServicio")
    , @NamedQuery(name = "CoDetallesTarifa.findByValorCosto", query = "SELECT c FROM CoDetallesTarifa c WHERE c.valorCosto = :valorCosto")
    , @NamedQuery(name = "CoDetallesTarifa.findByValorMinimoVenta", query = "SELECT c FROM CoDetallesTarifa c WHERE c.valorMinimoVenta = :valorMinimoVenta")
    , @NamedQuery(name = "CoDetallesTarifa.findByValorVenta", query = "SELECT c FROM CoDetallesTarifa c WHERE c.valorVenta = :valorVenta")
    , @NamedQuery(name = "CoDetallesTarifa.findByValorVentaMayorista", query = "SELECT c FROM CoDetallesTarifa c WHERE c.valorVentaMayorista = :valorVentaMayorista")
    , @NamedQuery(name = "CoDetallesTarifa.findByEsActivo", query = "SELECT c FROM CoDetallesTarifa c WHERE c.esActivo = :esActivo")
    , @NamedQuery(name = "CoDetallesTarifa.findByFechaIngreso", query = "SELECT c FROM CoDetallesTarifa c WHERE c.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "CoDetallesTarifa.findByUsuarioIngreso", query = "SELECT c FROM CoDetallesTarifa c WHERE c.usuarioIngreso = :usuarioIngreso")
    , @NamedQuery(name = "CoDetallesTarifa.findByFechaModificacion", query = "SELECT c FROM CoDetallesTarifa c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "CoDetallesTarifa.findByUsuarioModificacion", query = "SELECT c FROM CoDetallesTarifa c WHERE c.usuarioModificacion = :usuarioModificacion")})
public class CoDetallesTarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoDetallesTarifaPK coDetallesTarifaPK;
    @Column(name = "valor_costo")
    private BigInteger valorCosto;
    @Column(name = "valor_minimo_venta")
    private BigInteger valorMinimoVenta;
    @Column(name = "valor_venta")
    private BigInteger valorVenta;
    @Column(name = "valor_venta_mayorista")
    private BigInteger valorVentaMayorista;
    @Column(name = "es_activo")
    private String esActivo;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "usuario_ingreso")
    private String usuarioIngreso;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @JoinColumns({
        @JoinColumn(name = "id_tarifa", referencedColumnName = "id_tarifario", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_surcusal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PrTarifario prTarifario;
    @JoinColumns({
        @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion", insertable = false, updatable = false)
        , @JoinColumn(name = "id_unidad_servicio", referencedColumnName = "id_unidad_servicio", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private InPrestacionesPorServicios inPrestacionesPorServicios;

    public CoDetallesTarifa() {
    }

    public CoDetallesTarifa(CoDetallesTarifaPK coDetallesTarifaPK) {
        this.coDetallesTarifaPK = coDetallesTarifaPK;
    }

    public CoDetallesTarifa(long idTarifa, long idEmpresa, long idSucursal, long idPrestacion, long idUnidadServicio) {
        this.coDetallesTarifaPK = new CoDetallesTarifaPK(idTarifa, idEmpresa, idSucursal, idPrestacion, idUnidadServicio);
    }

    public CoDetallesTarifaPK getCoDetallesTarifaPK() {
        return coDetallesTarifaPK;
    }

    public void setCoDetallesTarifaPK(CoDetallesTarifaPK coDetallesTarifaPK) {
        this.coDetallesTarifaPK = coDetallesTarifaPK;
    }

    public BigInteger getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(BigInteger valorCosto) {
        this.valorCosto = valorCosto;
    }

    public BigInteger getValorMinimoVenta() {
        return valorMinimoVenta;
    }

    public void setValorMinimoVenta(BigInteger valorMinimoVenta) {
        this.valorMinimoVenta = valorMinimoVenta;
    }

    public BigInteger getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(BigInteger valorVenta) {
        this.valorVenta = valorVenta;
    }

    public BigInteger getValorVentaMayorista() {
        return valorVentaMayorista;
    }

    public void setValorVentaMayorista(BigInteger valorVentaMayorista) {
        this.valorVentaMayorista = valorVentaMayorista;
    }

    public String getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(String esActivo) {
        this.esActivo = esActivo;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public PrTarifario getPrTarifario() {
        return prTarifario;
    }

    public void setPrTarifario(PrTarifario prTarifario) {
        this.prTarifario = prTarifario;
    }

    public InPrestacionesPorServicios getInPrestacionesPorServicios() {
        return inPrestacionesPorServicios;
    }

    public void setInPrestacionesPorServicios(InPrestacionesPorServicios inPrestacionesPorServicios) {
        this.inPrestacionesPorServicios = inPrestacionesPorServicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coDetallesTarifaPK != null ? coDetallesTarifaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoDetallesTarifa)) {
            return false;
        }
        CoDetallesTarifa other = (CoDetallesTarifa) object;
        if ((this.coDetallesTarifaPK == null && other.coDetallesTarifaPK != null) || (this.coDetallesTarifaPK != null && !this.coDetallesTarifaPK.equals(other.coDetallesTarifaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.CoDetallesTarifa[ coDetallesTarifaPK=" + coDetallesTarifaPK + " ]";
    }
    
}
