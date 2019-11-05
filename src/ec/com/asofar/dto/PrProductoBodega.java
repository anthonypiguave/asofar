/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "pr_producto_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrProductoBodega.findAll", query = "SELECT p FROM PrProductoBodega p")
    , @NamedQuery(name = "PrProductoBodega.findByIdProductoBodega", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idProductoBodega = :idProductoBodega")
    , @NamedQuery(name = "PrProductoBodega.findByIdBodega", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idBodega = :idBodega")
    , @NamedQuery(name = "PrProductoBodega.findByIdProducto", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idProducto = :idProducto")
    , @NamedQuery(name = "PrProductoBodega.findByIdEmpresa", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrProductoBodega.findByIdSucursal", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idSucursal = :idSucursal")
    , @NamedQuery(name = "PrProductoBodega.findByIdTipoBodega", query = "SELECT p FROM PrProductoBodega p WHERE p.prProductoBodegaPK.idTipoBodega = :idTipoBodega")
    , @NamedQuery(name = "PrProductoBodega.findByEstado", query = "SELECT p FROM PrProductoBodega p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrProductoBodega.findByStockMinimo", query = "SELECT p FROM PrProductoBodega p WHERE p.stockMinimo = :stockMinimo")
    , @NamedQuery(name = "PrProductoBodega.findByStockMaximo", query = "SELECT p FROM PrProductoBodega p WHERE p.stockMaximo = :stockMaximo")})
public class PrProductoBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrProductoBodegaPK prProductoBodegaPK;
    @Column(name = "estado")
    private String estado;
    @Column(name = "stock_minimo")
    private BigInteger stockMinimo;
    @Column(name = "stock_maximo")
    private BigInteger stockMaximo;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
        , @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SeSucursal seSucursal;

    public PrProductoBodega() {
    }

    public PrProductoBodega(PrProductoBodegaPK prProductoBodegaPK) {
        this.prProductoBodegaPK = prProductoBodegaPK;
    }

    public PrProductoBodega(long idProductoBodega, long idBodega, long idProducto, long idEmpresa, long idSucursal, long idTipoBodega) {
        this.prProductoBodegaPK = new PrProductoBodegaPK(idProductoBodega, idBodega, idProducto, idEmpresa, idSucursal, idTipoBodega);
    }

    public PrProductoBodegaPK getPrProductoBodegaPK() {
        return prProductoBodegaPK;
    }

    public void setPrProductoBodegaPK(PrProductoBodegaPK prProductoBodegaPK) {
        this.prProductoBodegaPK = prProductoBodegaPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigInteger stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public BigInteger getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(BigInteger stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public SeSucursal getSeSucursal() {
        return seSucursal;
    }

    public void setSeSucursal(SeSucursal seSucursal) {
        this.seSucursal = seSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prProductoBodegaPK != null ? prProductoBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrProductoBodega)) {
            return false;
        }
        PrProductoBodega other = (PrProductoBodega) object;
        if ((this.prProductoBodegaPK == null && other.prProductoBodegaPK != null) || (this.prProductoBodegaPK != null && !this.prProductoBodegaPK.equals(other.prProductoBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrProductoBodega[ prProductoBodegaPK=" + prProductoBodegaPK + " ]";
    }
    
}
