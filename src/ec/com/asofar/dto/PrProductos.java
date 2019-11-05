/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "pr_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrProductos.findAll", query = "SELECT p FROM PrProductos p")
    , @NamedQuery(name = "PrProductos.findByIdProducto", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idProducto = :idProducto")
    , @NamedQuery(name = "PrProductos.findByIdEmpresa", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "PrProductos.findByIdArticulo", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idArticulo = :idArticulo")
    , @NamedQuery(name = "PrProductos.findByIdGrupo", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idGrupo = :idGrupo")
    , @NamedQuery(name = "PrProductos.findByIdSubgrupo", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idSubgrupo = :idSubgrupo")
    , @NamedQuery(name = "PrProductos.findByIdTipoPresentacion", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idTipoPresentacion = :idTipoPresentacion")
    , @NamedQuery(name = "PrProductos.findByIdTipoMedidas", query = "SELECT p FROM PrProductos p WHERE p.prProductosPK.idTipoMedidas = :idTipoMedidas")
    , @NamedQuery(name = "PrProductos.findByDescontinuado", query = "SELECT p FROM PrProductos p WHERE p.descontinuado = :descontinuado")
    , @NamedQuery(name = "PrProductos.findByNombreProducto", query = "SELECT p FROM PrProductos p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "PrProductos.findByReceta", query = "SELECT p FROM PrProductos p WHERE p.receta = :receta")
    , @NamedQuery(name = "PrProductos.findByEstado", query = "SELECT p FROM PrProductos p WHERE p.estado = :estado")
    , @NamedQuery(name = "PrProductos.findByUsuarioCreacion", query = "SELECT p FROM PrProductos p WHERE p.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "PrProductos.findByFechaCreacion", query = "SELECT p FROM PrProductos p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PrProductos.findByUsuarioActualizacion", query = "SELECT p FROM PrProductos p WHERE p.usuarioActualizacion = :usuarioActualizacion")
    , @NamedQuery(name = "PrProductos.findByFechaActualizacion", query = "SELECT p FROM PrProductos p WHERE p.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "PrProductos.findByUnidadEmpaqueCompra", query = "SELECT p FROM PrProductos p WHERE p.unidadEmpaqueCompra = :unidadEmpaqueCompra")
    , @NamedQuery(name = "PrProductos.findByMedidaEmpaqueCompra", query = "SELECT p FROM PrProductos p WHERE p.medidaEmpaqueCompra = :medidaEmpaqueCompra")
    , @NamedQuery(name = "PrProductos.findByCantidadPorEmpaqueCompra", query = "SELECT p FROM PrProductos p WHERE p.cantidadPorEmpaqueCompra = :cantidadPorEmpaqueCompra")
    , @NamedQuery(name = "PrProductos.findByMedidaPorEmpaqueCompra", query = "SELECT p FROM PrProductos p WHERE p.medidaPorEmpaqueCompra = :medidaPorEmpaqueCompra")
    , @NamedQuery(name = "PrProductos.findByUnidadEmpaqueVenta", query = "SELECT p FROM PrProductos p WHERE p.unidadEmpaqueVenta = :unidadEmpaqueVenta")
    , @NamedQuery(name = "PrProductos.findByMedidaEmpaqueVenta", query = "SELECT p FROM PrProductos p WHERE p.medidaEmpaqueVenta = :medidaEmpaqueVenta")
    , @NamedQuery(name = "PrProductos.findByCantidadPorEmpaqueVenta", query = "SELECT p FROM PrProductos p WHERE p.cantidadPorEmpaqueVenta = :cantidadPorEmpaqueVenta")
    , @NamedQuery(name = "PrProductos.findByMedidaPorEmpaqueVenta", query = "SELECT p FROM PrProductos p WHERE p.medidaPorEmpaqueVenta = :medidaPorEmpaqueVenta")})
public class PrProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrProductosPK prProductosPK;
    @Lob
    @Column(name = "codigo_barra")
    private String codigoBarra;
    @Column(name = "descontinuado")
    private String descontinuado;
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Lob
    @Column(name = "registro_sanitario_local")
    private String registroSanitarioLocal;
    @Lob
    @Column(name = "registro_sanitario_extranjero")
    private String registroSanitarioExtranjero;
    @Column(name = "receta")
    private String receta;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unidad_empaque_compra")
    private Double unidadEmpaqueCompra;
    @Column(name = "medida_empaque_compra")
    private String medidaEmpaqueCompra;
    @Column(name = "cantidad_por_empaque_compra")
    private Double cantidadPorEmpaqueCompra;
    @Column(name = "medida_por_empaque_compra")
    private String medidaPorEmpaqueCompra;
    @Column(name = "unidad_empaque_venta")
    private Double unidadEmpaqueVenta;
    @Column(name = "medida_empaque_venta")
    private String medidaEmpaqueVenta;
    @Column(name = "cantidad_por_empaque_venta")
    private Double cantidadPorEmpaqueVenta;
    @Column(name = "medida_por_empaque_venta")
    private String medidaPorEmpaqueVenta;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeEmpresa seEmpresa;
    @JoinColumn(name = "cod_fabricante", referencedColumnName = "id_fabricante")
    @ManyToOne
    private PrFabricante codFabricante;
    @JoinColumns({
        @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo", insertable = false, updatable = false)
        , @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo", insertable = false, updatable = false)
        , @JoinColumn(name = "id_subgrupo", referencedColumnName = "id_subgrupo", insertable = false, updatable = false)
        , @JoinColumn(name = "id_tipo_presentacion", referencedColumnName = "id_tipo_presentacion", insertable = false, updatable = false)
        , @JoinColumn(name = "id_tipo_medidas", referencedColumnName = "id_tipo_medidas", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PrMedidas prMedidas;

    public PrProductos() {
    }

    public PrProductos(PrProductosPK prProductosPK) {
        this.prProductosPK = prProductosPK;
    }

    public PrProductos(long idProducto, long idEmpresa, long idArticulo, long idGrupo, long idSubgrupo, long idTipoPresentacion, long idTipoMedidas) {
        this.prProductosPK = new PrProductosPK(idProducto, idEmpresa, idArticulo, idGrupo, idSubgrupo, idTipoPresentacion, idTipoMedidas);
    }

    public PrProductosPK getPrProductosPK() {
        return prProductosPK;
    }

    public void setPrProductosPK(PrProductosPK prProductosPK) {
        this.prProductosPK = prProductosPK;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getDescontinuado() {
        return descontinuado;
    }

    public void setDescontinuado(String descontinuado) {
        this.descontinuado = descontinuado;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getRegistroSanitarioLocal() {
        return registroSanitarioLocal;
    }

    public void setRegistroSanitarioLocal(String registroSanitarioLocal) {
        this.registroSanitarioLocal = registroSanitarioLocal;
    }

    public String getRegistroSanitarioExtranjero() {
        return registroSanitarioExtranjero;
    }

    public void setRegistroSanitarioExtranjero(String registroSanitarioExtranjero) {
        this.registroSanitarioExtranjero = registroSanitarioExtranjero;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
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

    public Double getUnidadEmpaqueCompra() {
        return unidadEmpaqueCompra;
    }

    public void setUnidadEmpaqueCompra(Double unidadEmpaqueCompra) {
        this.unidadEmpaqueCompra = unidadEmpaqueCompra;
    }

    public String getMedidaEmpaqueCompra() {
        return medidaEmpaqueCompra;
    }

    public void setMedidaEmpaqueCompra(String medidaEmpaqueCompra) {
        this.medidaEmpaqueCompra = medidaEmpaqueCompra;
    }

    public Double getCantidadPorEmpaqueCompra() {
        return cantidadPorEmpaqueCompra;
    }

    public void setCantidadPorEmpaqueCompra(Double cantidadPorEmpaqueCompra) {
        this.cantidadPorEmpaqueCompra = cantidadPorEmpaqueCompra;
    }

    public String getMedidaPorEmpaqueCompra() {
        return medidaPorEmpaqueCompra;
    }

    public void setMedidaPorEmpaqueCompra(String medidaPorEmpaqueCompra) {
        this.medidaPorEmpaqueCompra = medidaPorEmpaqueCompra;
    }

    public Double getUnidadEmpaqueVenta() {
        return unidadEmpaqueVenta;
    }

    public void setUnidadEmpaqueVenta(Double unidadEmpaqueVenta) {
        this.unidadEmpaqueVenta = unidadEmpaqueVenta;
    }

    public String getMedidaEmpaqueVenta() {
        return medidaEmpaqueVenta;
    }

    public void setMedidaEmpaqueVenta(String medidaEmpaqueVenta) {
        this.medidaEmpaqueVenta = medidaEmpaqueVenta;
    }

    public Double getCantidadPorEmpaqueVenta() {
        return cantidadPorEmpaqueVenta;
    }

    public void setCantidadPorEmpaqueVenta(Double cantidadPorEmpaqueVenta) {
        this.cantidadPorEmpaqueVenta = cantidadPorEmpaqueVenta;
    }

    public String getMedidaPorEmpaqueVenta() {
        return medidaPorEmpaqueVenta;
    }

    public void setMedidaPorEmpaqueVenta(String medidaPorEmpaqueVenta) {
        this.medidaPorEmpaqueVenta = medidaPorEmpaqueVenta;
    }

    public SeEmpresa getSeEmpresa() {
        return seEmpresa;
    }

    public void setSeEmpresa(SeEmpresa seEmpresa) {
        this.seEmpresa = seEmpresa;
    }

    public PrFabricante getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(PrFabricante codFabricante) {
        this.codFabricante = codFabricante;
    }

    public PrMedidas getPrMedidas() {
        return prMedidas;
    }

    public void setPrMedidas(PrMedidas prMedidas) {
        this.prMedidas = prMedidas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prProductosPK != null ? prProductosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrProductos)) {
            return false;
        }
        PrProductos other = (PrProductos) object;
        if ((this.prProductosPK == null && other.prProductosPK != null) || (this.prProductosPK != null && !this.prProductosPK.equals(other.prProductosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.PrProductos[ prProductosPK=" + prProductosPK + " ]";
    }
    
}
