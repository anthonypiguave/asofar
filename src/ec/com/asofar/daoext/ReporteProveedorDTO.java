/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class ReporteProveedorDTO {

    public Long id_orden_compra;
    public Long id_proveedor;
    public String nombre;
    public String direccion;
    public String telefono1;
    public String telefono2;
    public String pagina_web;
    public String numero_identificacion;
    public String email;
    public String tipo_persona;
    public String id_pais;
    public String contribuyente_especial;
    public String codigo_contribuyente;
    public String observaciones;
    public String nombre_comercial;

    public ReporteProveedorDTO() {
    }

    public ReporteProveedorDTO(Long id_orden_compra, Long id_proveedor, String nombre, String direccion, String telefono1, String telefono2, String pagina_web, String numero_identificacion, String email, String tipo_persona, String id_pais, String contribuyente_especial, String codigo_contribuyente, String observaciones, String nombre_comercial) {
        this.id_orden_compra = id_orden_compra;
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.pagina_web = pagina_web;
        this.numero_identificacion = numero_identificacion;
        this.email = email;
        this.tipo_persona = tipo_persona;
        this.id_pais = id_pais;
        this.contribuyente_especial = contribuyente_especial;
        this.codigo_contribuyente = codigo_contribuyente;
        this.observaciones = observaciones;
        this.nombre_comercial = nombre_comercial;
    }

    public Long getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Long id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public Long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getId_pais() {
        return id_pais;
    }

    public void setId_pais(String id_pais) {
        this.id_pais = id_pais;
    }

    public String getContribuyente_especial() {
        return contribuyente_especial;
    }

    public void setContribuyente_especial(String contribuyente_especial) {
        this.contribuyente_especial = contribuyente_especial;
    }

    public String getCodigo_contribuyente() {
        return codigo_contribuyente;
    }

    public void setCodigo_contribuyente(String codigo_contribuyente) {
        this.codigo_contribuyente = codigo_contribuyente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }
    
    
}
