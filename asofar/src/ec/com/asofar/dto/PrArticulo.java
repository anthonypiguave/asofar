/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
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
 * @author admin1
 */
@Entity
@Table(name = "pr_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrArticulo.findAll", query = "SELECT p FROM PrArticulo p")
    , @NamedQuery(name = "PrArticulo.findByIdArticulo", query = "SELECT p FROM PrArticulo p WHERE p.prArticuloPK.idArticulo = :idArticulo")
    , @NamedQuery(name = "PrArticulo.findByIdGrupo", query = "SELECT p FROM PrArticulo p WHERE p.prArticuloPK.idGrupo = :idGrupo")
    , @NamedQuery(name = "PrArticulo.findByIdSubgrupo", query = "SELECT p FROM PrArticulo p WHERE p.prArticuloPK.idSubgrupo = :idSubgrupo")
    , @NamedQuery(name = "PrArticulo.findByNombreArticulo", query = "SELECT p FROM PrArticulo p WHERE p.nombreArticulo = :nombreArticulo")
    , @NamedQuery(name = "PrArticulo.findByEstado", query = "SELECT p FROM PrArticulo p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrArticulo.findByUsuarioCreacion", query = "SELECT p FROM PrArticulo p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrArticulo.findByFechaCreacion", query = "SELECT p FROM PrArticulo p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrArticulo.findByUsuarioActualizacion", query = "SELECT p FROM PrArticulo p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrArticulo.findByFechaActualizacion", query = "SELECT p FROM PrArticulo p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrArticuloPK prArticuloPK;
    @Column(name = "nombre_articulo")
    private String nombreArticulo;
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
        @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo", insertable = false, updatable = false)
        , @JoinColumn(name = "id_subgrupo", referencedColumnName = "id_subgrupo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PrSubgrupos prSubgrupos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prArticulo")
    private List<PrMedidas> prMedidasList;
    @OneToMany(mappedBy = "idArticulo")
    private List<CoDetalleOrdenCompra> coDetalleOrdenCompraList;

    public PrArticulo() {
    }

    public PrArticulo(PrArticuloPK prArticuloPK) {
        this.prArticuloPK = prArticuloPK;
    }

    public PrArticulo(long idArticulo, long idGrupo, long idSubgrupo) {
        this.prArticuloPK = new PrArticuloPK(idArticulo, idGrupo, idSubgrupo);
    }

    public PrArticuloPK getPrArticuloPK() {
        return prArticuloPK;
    }

    public void setPrArticuloPK(PrArticuloPK prArticuloPK) {
        this.prArticuloPK = prArticuloPK;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
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

    public PrSubgrupos getPrSubgrupos() {
        return prSubgrupos;
    }

    public void setPrSubgrupos(PrSubgrupos prSubgrupos) {
        this.prSubgrupos = prSubgrupos;
    }

    @XmlTransient
    public List<PrMedidas> getPrMedidasList() {
        return prMedidasList;
    }

    public void setPrMedidasList(List<PrMedidas> prMedidasList) {
        this.prMedidasList = prMedidasList;
    }

    @XmlTransient
    public List<CoDetalleOrdenCompra> getCoDetalleOrdenCompraList() {
        return coDetalleOrdenCompraList;
    }

    public void setCoDetalleOrdenCompraList(List<CoDetalleOrdenCompra> coDetalleOrdenCompraList) {
        this.coDetalleOrdenCompraList = coDetalleOrdenCompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prArticuloPK != null ? prArticuloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrArticulo)) {
            return false;
        }
        PrArticulo other = (PrArticulo) object;
        if ((this.prArticuloPK == null && other.prArticuloPK != null) || (this.prArticuloPK != null && !this.prArticuloPK.equals(other.prArticuloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrArticulo[ prArticuloPK=" + prArticuloPK + " ]";
    }
    
}
