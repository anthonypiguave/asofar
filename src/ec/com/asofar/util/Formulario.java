/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InTipoCompra;
import ec.com.asofar.dto.InTipoDepartamento;
import ec.com.asofar.dto.InTipoDocumento;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author usuario
 */
public class Formulario {

    public static DefaultComboBoxModel comboTcompra(List<InTipoCompra> lcompra) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("--SELECCIONE--");
        for (int i = 0; i < lcompra.size(); i++) {
            if ("A".equals(lcompra.get(i).getEstado())) {
                model.addElement(lcompra.get(i).getNombre());
            }
        }
        return model;
    }

    public static DefaultComboBoxModel comboTdepart(List<InTipoDepartamento> ldepa) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("--SELECCIONE--");
        for (int i = 0; i < ldepa.size(); i++) {
            if ("A".equals(ldepa.get(i).getEstado())) {
                model.addElement(ldepa.get(i).getNombre());
            }
        }
        return model;
    }

    public static DefaultComboBoxModel comboTestad(List<InEstadosMovimiento> lesta) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("--SELECCIONE--");
        for (int i = 0; i < lesta.size(); i++) {
            if ("A".equals(lesta.get(i).getEstado())) {
                model.addElement(lesta.get(i).getNombre());
            }
        }
        return model;
    }

    public static DefaultComboBoxModel comboTdoc(List<InTipoDocumento> ldoc) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("--SELECCIONE--");
        for (int i = 0; i < ldoc.size(); i++) {
            if ("A".equals(ldoc.get(i).getEstado())) {
                model.addElement(ldoc.get(i).getNombreDocumento());
            }
        }
        return model;
    }
}
