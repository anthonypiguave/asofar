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
@Table(name = "pr_subgrupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrSubgrupos.findAll", query = "SELECT p FROM PrSubgrupos p")
    , @NamedQuery(name = "PrSubgrupos.findByIdSubgrupo", query = "SELECT p FROM PrSubgrupos p WHERE p.prSubgruposPK.idSubgrupo = :idSubgrupo")
    , @NamedQuery(name = "PrSubgrupos.findByIdGrupo", query = "SELECT p FROM PrSubgrupos p WHERE p.prSubgruposPK.idGrupo = :idGrupo")
    , @NamedQuery(name = "PrSubgrupos.findByNombre", query = "SELECT p FROM PrSubgrupos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PrSubgrupos.findByEstado", query = "SELECT p FROM PrSubgrupos p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrSubgrupos.findByUsuarioCreacion", query = "SELECT p FROM PrSubgrupos p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrSubgrupos.findByFechaCreacion", query = "SELECT p FROM PrSubgrupos p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrSubgrupos.findByUsuarioActualizacion", query = "SELECT p FROM PrSubgrupos p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrSubgrupos.findByFechaActualizacion", query = "SELECT p FROM PrSubgrupos p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PrSubgrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrSubgruposPK prSubgruposPK;
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prSubgrupos")
    private List<PrArticulo> prArticuloList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private SeEmpresa idEmpresa;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrGrupos prGrupos;

    public PrSubgrupos() {
    }

    public PrSubgrupos(PrSubgruposPK prSubgruposPK) {
        this.prSubgruposPK = prSubgruposPK;
    }

    public PrSubgrupos(long idSubgrupo, long idGrupo) {
        this.prSubgruposPK = new PrSubgruposPK(idSubgrupo, idGrupo);
    }

    public PrSubgruposPK getPrSubgruposPK() {
        return prSubgruposPK;
    }

    public void setPrSubgruposPK(PrSubgruposPK prSubgruposPK) {
        this.prSubgruposPK = prSubgruposPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<PrArticulo> getPrArticuloList() {
        return prArticuloList;
    }

    public void setPrArticuloList(List<PrArticulo> prArticuloList) {
        this.prArticuloList = prArticuloList;
    }

    public SeEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SeEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public PrGrupos getPrGrupos() {
        return prGrupos;
    }

    public void setPrGrupos(PrGrupos prGrupos) {
        this.prGrupos = prGrupos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prSubgruposPK != null ? prSubgruposPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrSubgrupos)) {
            return false;
        }
        PrSubgrupos other = (PrSubgrupos) object;
        if ((this.prSubgruposPK == null && other.prSubgruposPK != null) || (this.prSubgruposPK != null && !this.prSubgruposPK.equals(other.prSubgruposPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrSubgrupos[ prSubgruposPK=" + prSubgruposPK + " ]";
    }
    
}
