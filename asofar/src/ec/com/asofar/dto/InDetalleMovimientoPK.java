/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author admin
 */
@Embeddable
public class InDetalleMovimientoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_movimiento")
    private long idDetalleMovimiento;
    @Basic(optional = false)
    @Column(name = "id_movimientos")
    private long idMovimientos;
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private long idTipoDocumento;
    @Basic(optional = false)
    @Column(name = "id_tipo_movimiento")
    private long idTipoMovimiento;
    @Basic(optional = false)
    @Column(name = "id_motivo")
    private long idMotivo;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private long idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @Column(name = "linea_detalle")
    private long lineaDetalle;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;

    public InDetalleMovimientoPK() {
    }

    public InDetalleMovimientoPK(long idDetalleMovimiento, long idMovimientos, long idTipoDocumento, long idTipoMovimiento, long idMotivo, long idEmpresa, long idSucursal, long lineaDetalle, long idProducto) {
        this.idDetalleMovimiento = idDetalleMovimiento;
        this.idMovimientos = idMovimientos;
        this.idTipoDocumento = idTipoDocumento;
        this.idTipoMovimiento = idTipoMovimiento;
        this.idMotivo = idMotivo;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
        this.lineaDetalle = lineaDetalle;
        this.idProducto = idProducto;
    }

    public long getIdDetalleMovimiento() {
        return idDetalleMovimiento;
    }

    public void setIdDetalleMovimiento(long idDetalleMovimiento) {
        this.idDetalleMovimiento = idDetalleMovimiento;
    }

    public long getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(long idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public long getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(long idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(long idMotivo) {
        this.idMotivo = idMotivo;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getLineaDetalle() {
        return lineaDetalle;
    }

    public void setLineaDetalle(long lineaDetalle) {
        this.lineaDetalle = lineaDetalle;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleMovimiento;
        hash += (int) idMovimientos;
        hash += (int) idTipoDocumento;
        hash += (int) idTipoMovimiento;
        hash += (int) idMotivo;
        hash += (int) idEmpresa;
        hash += (int) idSucursal;
        hash += (int) lineaDetalle;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InDetalleMovimientoPK)) {
            return false;
        }
        InDetalleMovimientoPK other = (InDetalleMovimientoPK) object;
        if (this.idDetalleMovimiento != other.idDetalleMovimiento) {
            return false;
        }
        if (this.idMovimientos != other.idMovimientos) {
            return false;
        }
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        if (this.idTipoMovimiento != other.idTipoMovimiento) {
            return false;
        }
        if (this.idMotivo != other.idMotivo) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if (this.lineaDetalle != other.lineaDetalle) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.asofar.dto.InDetalleMovimientoPK[ idDetalleMovimiento=" + idDetalleMovimiento + ", idMovimientos=" + idMovimientos + ", idTipoDocumento=" + idTipoDocumento + ", idTipoMovimiento=" + idTipoMovimiento + ", idMotivo=" + idMotivo + ", idEmpresa=" + idEmpresa + ", idSucursal=" + idSucursal + ", lineaDetalle=" + lineaDetalle + ", idProducto=" + idProducto + " ]";
    }
    
}
