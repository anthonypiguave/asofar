/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.facturacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author jorge
 */
public class GenerarXml2 {

//    public static void main(String[] args) throws JAXBException, IOException {
//
//        List<Factura> listaCab = new ArrayList<Factura>();
//        List<Factura> listaDet = new ArrayList<Factura>();
//        Factura objCab = new Factura();
//        Factura objDet = new Factura();
//        Date d = new Date();
//
//        objCab.setIdFactura(Long.valueOf("1"));
//        objCab.setEmpresa("asofar");
//        objCab.setSucursal("sucursal1");
//        objCab.setFecha(d);
//
//        objCab.setFormaPago("contado");
//        objCab.setNumeroEstablecimientoSri("11454121444");
//        objCab.setPuntoEmisionSri("0001");
//        objCab.setDespachado("si");
//
//        objCab.setCaja("caja1");
//        objCab.setUsuario("admin");
//
//        objCab.setClienteID("099999999");
//        objCab.setClienteNombreApellido("cliente final");
//        objCab.setClienteTelefono("222222");
//        objCab.setClienteCorreo("s@sss.com");
//        objCab.setClienteDireccion("xxxx");
//
//        objCab.setTotalSubtotal(10.5);
//        objCab.setTotalDescuento(10.0);
//        objCab.setTotalBaseIva(12.0);
//        objCab.setTotalBaseNoIva(0.0);
//        objCab.setTotalIva(12.0);
//        objCab.setTotalFacturado(45.0);
//
//        objDet.setIdFacturaDetalle(Long.valueOf("1"));
//        objDet.setLineaDetalle(Long.valueOf("1"));
//        objDet.setIdProducto(Long.valueOf("1555"));
//        objDet.setDescripcion("producto a ");
//        objDet.setCantidad(Long.valueOf("5"));
//        objDet.setPrecioUnitario(12.5);
//        objDet.setSubtotal(20.0);
//        objDet.setValorIva(1.2);
//        objDet.setValorDescuento(0.0);
//        objDet.setValorTotal(88.0);
//        objDet.setUsuarioCreacion("admin");
//        objDet.setFechaCreacion(d);
//        objDet.setUsuarioActualizacion("admin");
//        objDet.setFechaActualizacion(d);
//
//        listaCab.add(objCab);
//        listaDet.add(objDet);
//
//        objDet = new Factura();
//        objDet.setIdFacturaDetalle(Long.valueOf("2"));
//        objDet.setLineaDetalle(Long.valueOf("1"));
//        objDet.setIdProducto(Long.valueOf("1555"));
//        objDet.setDescripcion("producto b ");
//        objDet.setCantidad(Long.valueOf("5"));
//        objDet.setPrecioUnitario(12.5);
//        objDet.setSubtotal(20.0);
//        objDet.setValorIva(1.2);
//        objDet.setValorDescuento(0.0);
//        objDet.setValorTotal(88.0);
//        objDet.setUsuarioCreacion("admin");
//        objDet.setFechaCreacion(d);
//        objDet.setUsuarioActualizacion("admin");
//        objDet.setFechaActualizacion(d);
//            
//        generarXml(listaCab, listaDet);
//
//
//    }

    public static void generarXml(List<Factura> listaCab, List<Factura> listaDet) throws IOException {
        
//        System.out.println("ffffffffff " + listaCab.get(0).getIdFactura());
        
        
        
        
        try {

            FacturaOrden factOrden = new FacturaOrden();

            factOrden.setFacturaCabecera(listaCab);
            factOrden.setFacturaDetalle(listaDet);

            //contexto      
            JAXBContext ctx = JAXBContext.newInstance(FacturaOrden.class);

            //convercion obj a xml
            Marshaller ms = ctx.createMarshaller();

            // formato delarchivo xml
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // realiza conversion a xml
//            ms.marshal(factOrden, new File("/home/admin1/prueba.xml"));
            ms.marshal(factOrden, System.out);
            ms.marshal(factOrden, new FileWriter("prueba.xml"));

        } catch (JAXBException e) {
            Logger.getLogger(GenerarXml2.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
