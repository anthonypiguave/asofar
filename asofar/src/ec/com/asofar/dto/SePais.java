/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "se_Pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SePais.findAll", query = "SELECT s FROM SePais s")
    , @NamedQuery(name = "SePais.findByIdPais", query = "SELECT s FROM SePais s WHERE s.idPais = :idPais")
    , @NamedQuery(name = "SePais.findByNombre", query = "SELECT s FROM SePais s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SePais.findByEstado", query = "SELECT s FROM SePais s WHERE s.estado = :estado")})
public class SePais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Pais")
    private Long idPais;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Estado")
    private String estado;
    @OneToMany(mappedBy = "idPais")
    private List<CoProveedores> coProveedoresList;
    @OneToMany(mappedBy = "idPais")
    private List<SeLocalidadCliente> seLocalidadClienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<SeProvincia> seProvinciaList;

    public SePais() {
    }

    public SePais(Long idPais) {
        this.idPais = idPais;
    }

    public SePais(Long idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
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

    @XmlTransient
    public List<CoProveedores> getCoProveedoresList() {
        return coProveedoresList;
    }

    public void setCoProveedoresList(List<CoProveedores> coProveedoresList) {
        this.coProveedoresList = coProveedoresList;
    }

    @XmlTransient
    public List<SeLocalidadCliente> getSeLocalidadClienteList() {
        return seLocalidadClienteList;
    }

    public void setSeLocalidadClienteList(List<SeLocalidadCliente> seLocalidadClienteList) {
        this.seLocalidadClienteList = seLocalidadClienteList;
    }

    @XmlTransient
    public List<SeProvincia> getSeProvinciaList() {
        return seProvinciaList;
    }

    public void setSeProvinciaList(List<SeProvincia> seProvinciaList) {
        this.seProvinciaList = seProvinciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SePais)) {
            return false;
        }
        SePais other = (SePais) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SePais[ idPais=" + idPais + " ]";
    }
    
}
