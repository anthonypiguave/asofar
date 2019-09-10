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

        String tipoPresentacion = ObtenerDTO.ObtenerPrTipoPresentacion(dto.getPrProductosPK().getIdTipoPresentacion());
        String tipoMedidas = ObtenerDTO.ObtenerPrTipoMedidas(dto.getPrProductosPK().getIdTipoMedidas());
        String subGrupo = ObtenerDTO.ObtenerPrSubGrupos(dto.getPrProductosPK().getIdSubgrupo());
        String grupo = ObtenerDTO.ObtenerPrGrupos(dto.getPrProductosPK().getIdGrupo());
        String articulo = ObtenerDTO.ObtenerPrArticulo(dto.getPrProductosPK().getIdArticulo());
        
        
        String cadena = grupo +"/"+ subGrupo +"/"+ articulo +"\n"+ tipoMedidas +"/"+ tipoPresentacion ;

        return cadena.toUpperCase();
    }

}
