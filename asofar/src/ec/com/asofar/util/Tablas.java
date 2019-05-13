/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author admin1
 */
public class Tablas {

    static DefaultTableModel model;
    private boolean[] editable = {false, false, true};
    private static boolean[] editable1 = {false, false, true};

    public static void filtro(String valor, JTable Tabla) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        Tabla.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + valor));
    }

    public static DefaultTableModel VaciarTabla(JTable tabla) {
        DefaultTableModel tab
                = (DefaultTableModel) tabla.getModel();
        while (tab.getRowCount() > 0) {
            tab.removeRow(0);
        }
        return tab;
    }
    
    public static void listarProveedor(List<CoProveedores> lista, JTable Tabla) {
        int[] a = {30, 30, 30, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"NOMBRE","DIRECCION","NUMERO IDENTIFICACION","NOMBRE COMERCIAL"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getNombre();
                Filas[1] = lista.get(i).getDireccion();
                Filas[2] = lista.get(i).getNumeroIdentificacion();
                Filas[3] = lista.get(i).getNombreComercial();                
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
    
    public static void listarProveedorin(List<CoProveedores> lista, JTable Tabla) {
        int[] a = {30, 30, 30, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"NOMBRE","DIRECCION","NUMERO IDENTIFICACION","NOMBRE COMERCIAL"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = lista.get(i).getNombre();
                Filas[1] = lista.get(i).getDireccion();
                Filas[2] = lista.get(i).getNumeroIdentificacion();
                Filas[3] = lista.get(i).getNombreComercial();                
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
    
    public static void listarmotivo(List<InMotivos> lista, JTable Tabla) {
        int[] a = {5, 30, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"Id","NOMBRE"};
        String[] Filas = new String[3];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = String.valueOf(lista.get(i).getIdMotivo());
                Filas[1] = lista.get(i).getNombre();                
                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);                
            }
        }

    }
    
    public static void listarmotivoin(List<InMotivos> lista, JTable Tabla) {
        int[] a = {5, 30, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"Id","NOMBRE"};
        String[] Filas = new String[3];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = String.valueOf(lista.get(i).getIdMotivo());
                Filas[1] = lista.get(i).getNombre();                

                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);                
            }
        }

    }
    
    public static void listaTipoMovimiento(List<InTipoMovimiento> lista, JTable Tabla) {
        int[] a = {5, 30, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODDIGO", "MOVIMINETO", "ESTADO"};
        String[] Filas = new String[3];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            Filas[0] = String.valueOf(lista.get(i).getIdTipoMovimiento());
            Filas[1] = lista.get(i).getNombreMovimiento();
            Filas[2] = lista.get(i).getEstado();

            model.addRow(Filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        }

    }

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
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getIdGrupo().toString();
                Filas[1] = lista.get(i).getNombre();
                Filas[2] = lista.get(i).getEstado();
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
                Filas[4] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));
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
    
        public static void listarGruposInactivos(List<PrGrupos> lista, JTable Tabla) {
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

            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = lista.get(i).getIdGrupo().toString();
                Filas[1] = lista.get(i).getNombre();
                Filas[2] = lista.get(i).getEstado();
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
                Filas[4] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));

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

    public static void listarPersonas(List<SePersonas> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CEDULA.", "NOMBRES", "APELLIDOS", "DIRECCION", "CORREO"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getCedula();
                Filas[1] = lista.get(i).getNombres();
                Filas[2] = lista.get(i).getApellidos();
                Filas[3] = lista.get(i).getDireccion();
                Filas[4] = lista.get(i).getCorreo();

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
//            }
        }

    }
public static void listarUsuarios(List<SeUsuarios> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"USUARIO", "NOMBRES", "DEPARTAMENTO", "FECHA CREACION", "CORREO"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getIdUsuario();
                Filas[1] = lista.get(i).getNombreUsuario();
                Filas[2] = lista.get(i).getDepartamento();
                Filas[3] = String.valueOf(lista.get(i).getFechaActualizacion());
                Filas[4] = lista.get(i).getCorreo();

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
//            }
        }

    }

    public static void listarSubgrupos(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N.GRUPO", "N.SUBGRUPO", "FECHA CREACION", "FECHA ACTUALIZACION"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
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

    public static void listarSubgruposIn(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N.GRUPO", "N.SUBGRUPO", "FECHA CREACION", "FECHA ACTUALIZACION"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("I")) {
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

    public static void TablaTipoMedidaActivo(List<PrTipoMedidas> listamedida, JTable tabla) {
        int[] a = {5, 100, 20};
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
            if (listamedida.get(i).getEstado().equals("A")) {
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
    }

    public static void TablaTipoMedidaInactivo(List<PrTipoMedidas> listamedida, JTable tabla) {
        int[] a = {5, 100, 20};
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
            if (listamedida.get(i).getEstado().equals("I")) {
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

    public static void TablaMedida(List<PrMedidas> listamedida, JTable tabla) {
        int[] a = {40, 50, 20};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);

        String[] b = {"TIPO MEDIDA", "TIPO PRESENTACION", "ESTADO"};

        String[] filas = new String[2];
        Boolean[] fila3 = new Boolean[1];

        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);
        for (int i = 0; i < listamedida.size(); i++) {
            filas[0] = listamedida.get(i).getPrTipoMedidas().getNombreTipoMedida();
            filas[1] = listamedida.get(i).getPrTipoPresentacion().getNombre();
            if (listamedida.get(i).getEstado().equals("A")) {
                fila3[0] = true;
            } else {
                fila3[0] = false;
            }

            model.addRow(filas);
            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            tabla.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            tabla.getColumnModel().getColumn(1).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            tabla.getColumnModel().getColumn(2).setCellRenderer(dtcr1);
        }
    }

    public  void TablaMedida2(List<PrMedidas> listamedida, JTable tabla) {

        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new String[]{"TIPO MEDIDA", "TIPO PRESENTACION", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };
        if (listamedida.size() > 0) {

            for (int i = 0; i < listamedida.size(); i++) {
                Object filas[] = new Object[3];
                filas[0] = listamedida.get(i).getPrTipoMedidas().getNombreTipoMedida();
                filas[1] = listamedida.get(i).getPrTipoPresentacion().getNombre();
                if (listamedida.get(i).getEstado().equals("A")) {
                    filas[2] = true;
                } else {
                    filas[2] = false;
                }
                model.addRow(filas);
            }
        }
        tabla.setModel(model);

    }

    public static void TablaProducto(List<PrProductos> listaprod, JTable tabla) {
        int[] a = {10, 50, 125,100, 50, 50};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);
        String[] b = {"ID", "NOMBRE", "COD BARRA", "FABRICANTE", "RECETA", "ESTADO"};
        String[] filas = new String[6];
        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);
        for (int i = 0; i < listaprod.size(); i++) {
            filas[0] = String.valueOf(listaprod.get(i).getPrProductosPK().getIdProducto());
            filas[1] = listaprod.get(i).getNombreProducto();
            filas[2] = listaprod.get(i).getCodigoBarra();
            filas[3] = listaprod.get(i).getCodFabricante().getNombre();
            filas[4] = listaprod.get(i).getReceta();
            filas[5] = listaprod.get(i).getEstado();
            model.addRow(filas);
            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            tabla.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            tabla.getColumnModel().getColumn(1).setCellRenderer(dtcr2);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            tabla.getColumnModel().getColumn(2).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            tabla.getColumnModel().getColumn(3).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
            tabla.getColumnModel().getColumn(4).setCellRenderer(dtcr1);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
            tabla.getColumnModel().getColumn(5).setCellRenderer(dtcr1);
        }
    }

    public static void TablaFabricante(List<PrFabricante> listafabri, JTable tabla) {
        int[] a = {5, 30, 20};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);
        String[] b = {"ID", "NOMBRE"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);

        for (int i = 0; i < listafabri.size(); i++) {
            if (listafabri.get(i).getEstado().equals("A")) {
                filas[0] = String.valueOf(listafabri.get(i).getIdFabricante());
                filas[1] = listafabri.get(i).getNombre();

                model.addRow(filas);
                tabla.setModel(model);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                tabla.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                tabla.getColumnModel().getColumn(1).setCellRenderer(dtcr1);

            }
        }
    }

    public static void TablaFabricanteInac(List<PrFabricante> listafabri, JTable tabla) {
        int[] a = {5, 30, 20};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);
        String[] b = {"ID", "NOMBRE"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);

        for (int i = 0; i < listafabri.size(); i++) {
            if (listafabri.get(i).getEstado().equals("I")) {
                filas[0] = String.valueOf(listafabri.get(i).getIdFabricante());
                filas[1] = listafabri.get(i).getNombre();

                model.addRow(filas);
                tabla.setModel(model);
                tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                tabla.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
                tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                tabla.getColumnModel().getColumn(1).setCellRenderer(dtcr1);

            }
        }
    }
public static void TablaTipobodegaActivo(List<InTipoBodega> listabodega, JTable tabla) {
        int[] a = {5, 100, 20};
        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
        dtcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(tabla);
        String[] b = {"ID", "MEDIDA", "ESTADO"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        tabla.setShowGrid(true);
        for (int i = 0; i < listabodega.size(); i++) {
            if (listabodega.get(i).getEstado().equals("A")) {
                filas[0] = String.valueOf(listabodega.get(i).getIdTipoBodega());
                filas[1] = listabodega.get(i).getNombre();
                filas[2] = listabodega.get(i).getEstado();
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
    }
public static void tabla_documento(JTable tabla, List<InTipoDocumento> lista) {
        
        InTipoDocumento vo=new InTipoDocumento();
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"COD.DOCUMENTO", "DOCUMENTO","ESTADO",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable1[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                // model.addRow(new Object[]{});
                Object fila[] = new Object[3];
                vo = lista.get(i);
                fila[0] = vo.getIdTipoDocumento();
                fila[1] = vo.getNombreDocumento();
                String ac = (String) vo.getEstado();
                if ("A".equals(ac)) {
                    fila[2] = true;
                } else {
                    fila[2] = false;
                }
                
                

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
    }
 public static void listarBodega(List<InBodega> lista, JTable Tabla) {
        int[] a = {5, 30, 80,20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODIGO", "BODEGA","TIPO DE BODEGA", "ESTADO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            Filas[0] = ""+lista.get(i).getInBodegaPK().getIdBodega();
            Filas[1] = lista.get(i).getNombreBodega();
            Filas[2] = ObtenerDTO.ObtenerInTipoBodega(lista.get(i).getInBodegaPK().getIdTipoBodega()).getNombre();
            Filas[3] = lista.get(i).getEstado();            
            model.addRow(Filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        }

    }
}
