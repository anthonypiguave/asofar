/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.daoext;

import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author admin1
 */
public class ProductoCadena {

    public static String obtenerCadena(Long id) {

        PrProductosJpaController productoController = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
        PrProductos dto = new PrProductos();

        List<PrProductos> lista = productoController.findPrProductosEntities();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPrProductosPK().getIdProducto() == id) {
                dto = lista.get(i);
                break;
            }

        }
        
        String articulo = dto.getPrMedidas().getPrArticulo().getNombreArticulo();
        String grupo = dto.getPrMedidas().getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
        String subGrupo = dto.getPrMedidas().getPrArticulo().getPrSubgrupos().getNombre();
        String tipoPresentacion = dto.getPrMedidas().getPrTipoPresentacion().getNombre();
        String tipoMedidas = dto.getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
        
        
        String cadena = grupo +" \n"+ subGrupo +" \n"+ articulo +" \n"+ tipoPresentacion +" \n"+ tipoMedidas ;

        return cadena.toUpperCase();
    }

}
