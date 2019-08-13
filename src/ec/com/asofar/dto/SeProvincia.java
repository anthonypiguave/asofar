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
@Table(name = "se_Provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeProvincia.findAll", query = "SELECT s FROM SeProvincia s")
    , @NamedQuery(name = "SeProvincia.findByIdProvincia", query = "SELECT s FROM SeProvincia s WHERE s.idProvincia = :idProvincia")
    , @NamedQuery(name = "SeProvincia.findByNombre", query = "SELECT s FROM SeProvincia s WHERE s.nombre = :nombre")})
public class SeProvincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Provincia")
    private Long idProvincia;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "idProvincia")
    private List<SeLocalidadCliente> seLocalidadClienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<SeCiudad> seCiudadList;
    @JoinColumn(name = "id_Pais", referencedColumnName = "id_Pais")
    @ManyToOne(optional = false)
    private SePais idPais;

    public SeProvincia() {
    }

    public SeProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public SeProvincia(Long idProvincia, String nombre) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<SeLocalidadCliente> getSeLocalidadClienteList() {
        return seLocalidadClienteList;
    }

    public void setSeLocalidadClienteList(List<SeLocalidadCliente> seLocalidadClienteList) {
        this.seLocalidadClienteList = seLocalidadClienteList;
    }

    @XmlTransient
    public List<SeCiudad> getSeCiudadList() {
        return seCiudadList;
    }

    public void setSeCiudadList(List<SeCiudad> seCiudadList) {
        this.seCiudadList = seCiudadList;
    }

    public SePais getIdPais() {
        return idPais;
    }

    public void setIdPais(SePais idPais) {
        this.idPais = idPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeProvincia)) {
            return false;
        }
        SeProvincia other = (SeProvincia) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeProvincia[ idProvincia=" + idProvincia + " ]";
    }
    
}
