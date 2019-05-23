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
 * @author ADMIN
 */
@Entity
@Table(name = "pr_medidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrMedidas.findAll", query = "SELECT p FROM PrMedidas p"),
    @NamedQuery(name = "PrMedidas.findByIdArticulo", query = "SELECT p FROM PrMedidas p WHERE p.prMedidasPK.idArticulo = :idArticulo"),
    @NamedQuery(name = "PrMedidas.findByIdGrupo", query = "SELECT p FROM PrMedidas p WHERE p.prMedidasPK.idGrupo = :idGrupo"),
    @NamedQuery(name = "PrMedidas.findByIdSubgrupo", query = "SELECT p FROM PrMedidas p WHERE p.prMedidasPK.idSubgrupo = :idSubgrupo"),
    @NamedQuery(name = "PrMedidas.findByIdTipoPresentacion", query = "SELECT p FROM PrMedidas p WHERE p.prMedidasPK.idTipoPresentacion = :idTipoPresentacion"),
    @NamedQuery(name = "PrMedidas.findByIdTipoMedidas", query = "SELECT p FROM PrMedidas p WHERE p.prMedidasPK.idTipoMedidas = :idTipoMedidas"),
    @NamedQuery(name = "PrMedidas.findByEstado", query = "SELECT p FROM PrMedidas p WHERE p.estado = :estado"),
    @NamedQuery(name = "PrMedidas.findByUsuarioCreacion", query = "SELECT p FROM PrMedidas p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PrMedidas.findByFechaCreacion", query = "SELECT p FROM PrMedidas p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PrMedidas.findByUsuarioActualizacion", query = "SELECT p FROM PrMedidas p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PrMedidas.findByFechaActualizacion", query = "SELECT p FROM PrMedidas p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrMedidas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrMedidasPK prMedidasPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prMedidas")
    private List<PrProductos> prProductosList;
    @JoinColumns({
        @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", insertable = false, updatable = false),
        @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo", insertable = false, updatable = false),
        @JoinColumn(name = "id_subgrupo", referencedColumnName = "id_subgrupo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PrArticulo prArticulo;
    @JoinColumn(name = "id_tipo_medidas", referencedColumnName = "id_tipo_medidas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrTipoMedidas prTipoMedidas;
    @JoinColumn(name = "id_tipo_presentacion", referencedColumnName = "id_tipo_presentacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrTipoPresentacion prTipoPresentacion;

    public PrMedidas() {
    }

    public PrMedidas(PrMedidasPK prMedidasPK) {
        this.prMedidasPK = prMedidasPK;
    }

    public PrMedidas(long idArticulo, long idGrupo, long idSubgrupo, long idTipoPresentacion, long idTipoMedidas) {
        this.prMedidasPK = new PrMedidasPK(idArticulo, idGrupo, idSubgrupo, idTipoPresentacion, idTipoMedidas);
    }

    public PrMedidasPK getPrMedidasPK() {
        return prMedidasPK;
    }

    public void setPrMedidasPK(PrMedidasPK prMedidasPK) {
        this.prMedidasPK = prMedidasPK;
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

    @XmlTransient
    public List<PrProductos> getPrProductosList() {
        return prProductosList;
    }

    public void setPrProductosList(List<PrProductos> prProductosList) {
        this.prProductosList = prProductosList;
    }

    public PrArticulo getPrArticulo() {
        return prArticulo;
    }

    public void setPrArticulo(PrArticulo prArticulo) {
        this.prArticulo = prArticulo;
    }

    public PrTipoMedidas getPrTipoMedidas() {
        return prTipoMedidas;
    }

    public void setPrTipoMedidas(PrTipoMedidas prTipoMedidas) {
        this.prTipoMedidas = prTipoMedidas;
    }

    public PrTipoPresentacion getPrTipoPresentacion() {
        return prTipoPresentacion;
    }

    public void setPrTipoPresentacion(PrTipoPresentacion prTipoPresentacion) {
        this.prTipoPresentacion = prTipoPresentacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prMedidasPK != null ? prMedidasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrMedidas)) {
            return false;
        }
        PrMedidas other = (PrMedidas) object;
        if ((this.prMedidasPK == null && other.prMedidasPK != null) || (this.prMedidasPK != null && !this.prMedidasPK.equals(other.prMedidasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrMedidas[ prMedidasPK=" + prMedidasPK + " ]";
    }
    
}
