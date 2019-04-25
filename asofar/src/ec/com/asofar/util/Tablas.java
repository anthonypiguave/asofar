/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoMedidas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin1
 */
public class Tablas {

    static DefaultTableModel model;

    public static void listarGrupos(List<PrGrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "NOMBRE", "ESTADO", "FECHA CREACION", "FECHA ACTUALIZACION"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            Filas[0] = lista.get(i).getIdGrupo().toString();
            Filas[1] = lista.get(i).getNombre();
            Filas[2] = lista.get(i).getEstado();
            Filas[3] = lista.get(i).getFechaCreacion().toString();
            Filas[4] = lista.get(i).getFechaActualizacion().toString();

            model.addRow(Filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
            Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        }

    }
        public static void listarSubgrupos(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N.GRUPO","N.SUBGRUPO", "FECHA CREACION", "FECHA ACTUALIZACION"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            
            if(lista.get(i).getEstado().equals("A")){
            Filas[0] = lista.get(i).getPrGrupos().getNombre();
            Filas[1] = lista.get(i).getNombre();
            Filas[2] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
            Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));

            model.addRow(Filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            }
        }

    }

    public static DefaultTableModel VaciarTabla(JTable tabla) {
        DefaultTableModel lab
                = (DefaultTableModel) tabla.getModel();
        while (lab.getRowCount() > 0) {
            lab.removeRow(0);
        }
        return lab;

    }
    public static void TablaTipoMedida(List<PrTipoMedidas> listamedida, JTable tabla) {
        int[] a = {5, 300, 20};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);
        String[] b = {"ID", "MEDIDA", "ESTADO"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);
        for (int i = 0; i < listamedida.size(); i++) {
            filas[0] = String.valueOf(listamedida.get(i).getIdTipoMedidas());
            filas[1] = listamedida.get(i).getNombreTipoMedida();
            filas[2] = listamedida.get(i).getEstado();
            model.addRow(filas);
            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            tabla.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            tabla.getColumnModel().getColumn(1).setCellRenderer(dtcr2);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            tabla.getColumnModel().getColumn(2).setCellRenderer(dtcr1);
        }
    }
    public static void listaArticulos(List<PrArticulo> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "GRUPO", "SUBGRUPO", "ARTICULO", "ESTADO"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            Filas[0] = String.valueOf(lista.get(i).getPrArticuloPK().getIdArticulo());
            Filas[1] = lista.get(i).getPrSubgrupos().getPrGrupos().getNombre();
            Filas[2] = lista.get(i).getPrSubgrupos().getNombre();
            Filas[3] = lista.get(i).getNombreArticulo();
            Filas[4] = lista.get(i).getEstado();

            model.addRow(Filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
            Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        }

    }
}
