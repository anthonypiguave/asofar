/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.math.BigInteger;

/**
 *
 * @author admin1
 */
public class JoinProductoVenta {
    public Long idKardex ;
    public Long idPrestacion ;
    public Long idBodega ;
    public Long idProducto ;
    public Long idUnidadServicio ;
    public String codigoBarra;
    public String descripcion;
    public Integer stock;
    public Double valorventa;
    public Double descuento;
    public String iva;

    public Long getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Long idKardex) {
        this.idKardex = idKardex;
    }

    public Long getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(Long idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(Long idUnidadServicio) {
        this.idUnidadServicio = idUnidadServicio;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getValorventa() {
        return valorventa;
    }

    public void setValorventa(Double valorventa) {
        this.valorventa = valorventa;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

}

  