/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * @author usuario
 */
@Entity
@Table(name = "se_ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeCiudad.findAll", query = "SELECT s FROM SeCiudad s")
    , @NamedQuery(name = "SeCiudad.findByIdCiudad", query = "SELECT s FROM SeCiudad s WHERE s.idCiudad = :idCiudad")
    , @NamedQuery(name = "SeCiudad.findByNombre", query = "SELECT s FROM SeCiudad s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeCiudad.findByEstado", query = "SELECT s FROM SeCiudad s WHERE s.estado = :estado")})
public class SeCiudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Ciudad")
    private Long idCiudad;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_Provincia", referencedColumnName = "id_Provincia")
    @ManyToOne(optional = false)
    private SeProvincia idProvincia;
    @OneToMany(mappedBy = "idCiudad")
    private List<SeLocalidadCliente> seLocalidadClienteList;

    public SeCiudad() {
    }

    public SeCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public SeCiudad(Long idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
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

    public SeProvincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(SeProvincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    @XmlTransient
    public List<SeLocalidadCliente> getSeLocalidadClienteList() {
        return seLocalidadClienteList;
    }

    public void setSeLocalidadClienteList(List<SeLocalidadCliente> seLocalidadClienteList) {
        this.seLocalidadClienteList = seLocalidadClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeCiudad)) {
            return false;
        }
        SeCiudad other = (SeCiudad) object;
        if ((this.idCiudad == null && other.idCiudad != null) || (this.idCiudad != null && !this.idCiudad.equals(other.idCiudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.SeCiudad[ idCiudad=" + idCiudad + " ]";
    }
    
}
