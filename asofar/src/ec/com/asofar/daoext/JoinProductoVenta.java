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
    public BigInteger idKardex ;
    public BigInteger idPrestacion ;
    public BigInteger idBodega ;
    public BigInteger idProducto ;
    public BigInteger idUnidadServicio ;
    public String codigoBarra;
    public String descripcion;
    public Integer stock;
    public Double valorventa;
    public Double descuento;
    public String iva;

    public BigInteger getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(BigInteger idKardex) {
        this.idKardex = idKardex;
    }

    public BigInteger getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(BigInteger idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public BigInteger getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(BigInteger idBodega) {
        this.idBodega = idBodega;
    }

    public BigInteger getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigInteger idProducto) {
        this.idProducto = idProducto;
    }

    public BigInteger getIdUnidadServicio() {
        return idUnidadServicio;
    }

    public void setIdUnidadServicio(BigInteger idUnidadServicio) {
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
