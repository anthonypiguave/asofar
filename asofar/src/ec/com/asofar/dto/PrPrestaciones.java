/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin1
 */
@Entity
@Table(name = "pr_prestaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrPrestaciones.findAll", query = "SELECT p FROM PrPrestaciones p")
    , @NamedQuery(name = "PrPrestaciones.findByIdPrestacion", query = "SELECT p FROM PrPrestaciones p WHERE p.prPrestacionesPK.idPrestacion = :idPrestacion")
    , @NamedQuery(name = "PrPrestaciones.findByIdEmpresa", query = "SELECT p FROM PrPrestaciones p WHERE p.prPrestacionesPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrPrestaciones.findByIdPoducto", query = "SELECT p FROM PrPrestaciones p WHERE p.prPrestacionesPK.idPoducto = :idPoducto")
    , @NamedQuery(name = "PrPrestaciones.findByNombrePrestacion", query = "SELECT p FROM PrPrestaciones p WHERE p.nombrePrestacion = :nombrePrestacion")
    , @NamedQuery(name = "PrPrestaciones.findByEstado", query = "SELECT p FROM PrPrestaciones p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrPrestaciones.findByAplicaIva", query = "SELECT p FROM PrPrestaciones p WHERE p.aplicaIva = :aplicaIva")})
public class PrPrestaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrPrestacionesPK prPrestacionesPK;
    @Column(name = "nombre_prestacion")
    private String nombrePrestacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "aplica_iva")
    private String aplicaIva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prPrestaciones")
    private List<PrDetalleTarifario> prDetalleTarifarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prPrestaciones")
    private List<InKardex> inKardexList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeEmpresa seEmpresa;
    @JoinColumn(name = "id_poducto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrProductos prProductos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prPrestaciones")
    private List<VeFacturaDetalle> veFacturaDetalleList;

    public PrPrestaciones() {
    }

    public PrPrestaciones(PrPrestacionesPK prPrestacionesPK) {
        this.prPrestacionesPK = prPrestacionesPK;
    }

    public PrPrestaciones(long idPrestacion, long idEmpresa, long idPoducto) {
        this.prPrestacionesPK = new PrPrestacionesPK(idPrestacion, idEmpresa, idPoducto);
    }

    public PrPrestacionesPK getPrPrestacionesPK() {
        return prPrestacionesPK;
    }

    public void setPrPrestacionesPK(PrPrestacionesPK prPrestacionesPK) {
        this.prPrestacionesPK = prPrestacionesPK;
    }

    public String getNombrePrestacion() {
        return nombrePrestacion;
    }

    public void setNombrePrestacion(String nombrePrestacion) {
        this.nombrePrestacion = nombrePrestacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAplicaIva() {
        return aplicaIva;
    }

    public void setAplicaIva(String aplicaIva) {
        this.aplicaIva = aplicaIva;
    }

    @XmlTransient
    public List<PrDetalleTarifario> getPrDetalleTarifarioList() {
        return prDetalleTarifarioList;
    }

    public void setPrDetalleTarifarioList(List<PrDetalleTarifario> prDetalleTarifarioList) {
        this.prDetalleTarifarioList = prDetalleTarifarioList;
    }

    @XmlTransient
    public List<InKardex> getInKardexList() {
        return inKardexList;
    }

    public void setInKardexList(List<InKardex> inKardexList) {
        this.inKardexList = inKardexList;
    }

    public SeEmpresa getSeEmpresa() {
        return seEmpresa;
    }

    public void setSeEmpresa(SeEmpresa seEmpresa) {
        this.seEmpresa = seEmpresa;
    }

    public PrProductos getPrProductos() {
        return prProductos;
    }

    public void setPrProductos(PrProductos prProductos) {
        this.prProductos = prProductos;
    }

    @XmlTransient
    public List<VeFacturaDetalle> getVeFacturaDetalleList() {
        return veFacturaDetalleList;
    }

    public void setVeFacturaDetalleList(List<VeFacturaDetalle> veFacturaDetalleList) {
        this.veFacturaDetalleList = veFacturaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prPrestacionesPK != null ? prPrestacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrPrestaciones)) {
            return false;
        }
        PrPrestaciones other = (PrPrestaciones) object;
        if ((this.prPrestacionesPK == null && other.prPrestacionesPK != null) || (this.prPrestacionesPK != null && !this.prPrestacionesPK.equals(other.prPrestacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrPrestaciones[ prPrestacionesPK=" + prPrestacionesPK + " ]";
    }
    
}
