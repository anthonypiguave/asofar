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
@Table(name = "ve_detalle_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeDetalleCaja.findAll", query = "SELECT v FROM VeDetalleCaja v")
    , @NamedQuery(name = "VeDetalleCaja.findByIdDetalleCaja", query = "SELECT v FROM VeDetalleCaja v WHERE v.veDetalleCajaPK.idDetalleCaja = :idDetalleCaja")
    , @NamedQuery(name = "VeDetalleCaja.findByIdCaja", query = "SELECT v FROM VeDetalleCaja v WHERE v.veDetalleCajaPK.idCaja = :idCaja")
    , @NamedQuery(name = "VeDetalleCaja.findByIdUsuario", query = "SELECT v FROM VeDetalleCaja v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "VeDetalleCaja.findByFechaInicio", query = "SELECT v FROM VeDetalleCaja v WHERE v.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "VeDetalleCaja.findByHoraInicio", query = "SELECT v FROM VeDetalleCaja v WHERE v.horaInicio = :horaInicio")
    , @NamedQuery(name = "VeDetalleCaja.findByFechaCierre", query = "SELECT v FROM VeDetalleCaja v WHERE v.fechaCierre = :fechaCierre")
    , @NamedQuery(name = "VeDetalleCaja.findByHoraCierre", query = "SELECT v FROM VeDetalleCaja v WHERE v.horaCierre = :horaCierre")
    , @NamedQuery(name = "VeDetalleCaja.findByDineroInicio", query = "SELECT v FROM VeDetalleCaja v WHERE v.dineroInicio = :dineroInicio")
    , @NamedQuery(name = "VeDetalleCaja.findByDineroCierre", query = "SELECT v FROM VeDetalleCaja v WHERE v.dineroCierre = :dineroCierre")
    , @NamedQuery(name = "VeDetalleCaja.findByEstado", query = "SELECT v FROM VeDetalleCaja v WHERE v.estado = :estado")
    , @NamedQuery(name = "VeDetalleCaja.findByIdVoucher", query = "SELECT v FROM VeDetalleCaja v WHERE v.idVoucher = :idVoucher")})
public class VeDetalleCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeDetalleCajaPK veDetalleCajaPK;
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Column(name = "hora_cierre")
    @Temporal(TemporalType.TIME)
    private Date horaCierre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dinero_inicio")
    private Double dineroInicio;
    @Column(name = "dinero_cierre")
    private Double dineroCierre;
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_voucher")
    private BigInteger idVoucher;
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VeCaja veCaja;

    public VeDetalleCaja() {
    }

    public VeDetalleCaja(VeDetalleCajaPK veDetalleCajaPK) {
        this.veDetalleCajaPK = veDetalleCajaPK;
    }

    public VeDetalleCaja(long idDetalleCaja, long idCaja) {
        this.veDetalleCajaPK = new VeDetalleCajaPK(idDetalleCaja, idCaja);
    }

    public VeDetalleCajaPK getVeDetalleCajaPK() {
        return veDetalleCajaPK;
    }

    public void setVeDetalleCajaPK(VeDetalleCajaPK veDetalleCajaPK) {
        this.veDetalleCajaPK = veDetalleCajaPK;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Double getDineroInicio() {
        return dineroInicio;
    }

    public void setDineroInicio(Double dineroInicio) {
        this.dineroInicio = dineroInicio;
    }

    public Double getDineroCierre() {
        return dineroCierre;
    }

    public void setDineroCierre(Double dineroCierre) {
        this.dineroCierre = dineroCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(BigInteger idVoucher) {
        this.idVoucher = idVoucher;
    }

    public VeCaja getVeCaja() {
        return veCaja;
    }

    public void setVeCaja(VeCaja veCaja) {
        this.veCaja = veCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veDetalleCajaPK != null ? veDetalleCajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeDetalleCaja)) {
            return false;
        }
        VeDetalleCaja other = (VeDetalleCaja) object;
        if ((this.veDetalleCajaPK == null && other.veDetalleCajaPK != null) || (this.veDetalleCajaPK != null && !this.veDetalleCajaPK.equals(other.veDetalleCajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.VeDetalleCaja[ veDetalleCajaPK=" + veDetalleCajaPK + " ]";
    }
    
}
