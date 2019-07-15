/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoDetallesTarifa;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeUnidadServicio;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author admin1
 */
public class Tablas {

    Font fuente = new Font("Bold", Font.BOLD, 14);
    static DefaultTableModel model;

//    public static void TablaCotizacionPorProveedorDos(JTable jtCabecera, List<CoCotizacionesPorProveedor> lista) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private boolean[] editable = {false, false, true};
    private static boolean[] editable1 = {false, false, true};
    private static boolean[] editable2 = {false, false, false, false, false, true};
    private static boolean[] editable3 = {false, false, false, false, false, false, false, true};
    private static boolean[] editable4 = {false, false, false, true};
    private static boolean[] editable5 = {false, false, false, false, false, true, true, true, true};
    private static boolean[] tbordenpedido = {false, false, false, true, true};
    private static boolean[] tbordenpedido2 = {false, false, false, false, true};

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

    public static void ListarProductosConsulta(List<PrProductos> listaproducto, JTable Tabla) {
        int[] a = {50, 50, 50, 50, 50, 50, 50, 50};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "CATEGORIA", "SUBCATEGORIA", "ARTICULO", "PRODUCTO", "EMPRESA",
            "PRESENTACION", "MEDIDAS"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaproducto.size(); i++) {
            if (listaproducto.get(i).getEstado().equals("A")) {
                filas[0] = "" + listaproducto.get(i).getPrProductosPK().getIdProducto();
                filas[1] = listaproducto.get(i).getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
                filas[2] = listaproducto.get(i).getPrArticulo().getPrSubgrupos().getNombre();
                filas[3] = listaproducto.get(i).getPrArticulo().getNombreArticulo();
                filas[4] = listaproducto.get(i).getNombreProducto();
                filas[5] = listaproducto.get(i).getSeEmpresa().getNombreComercial();
                filas[6] = listaproducto.get(i).getPrMedidas().getPrTipoPresentacion().getNombre();
                filas[7] = listaproducto.get(i).getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
//                filas[2] = listaproducto.get(i).getModelado();
                model.addRow(filas);
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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
                Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
                Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
            }
        }
    }

    public static void ListarDetalleOrdenCompra(List<CoDetalleOrdenCompra> listadeorco, JTable Tabla, CoOrdenCompras orco) {
        int[] a = {50, 50, 50, 50, 50, 50, 50, 50};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"PRODUCTO", "PRESENTACION", "CANTIDAD",
            "PRECIO UNITARIO", "IVA", "SUBTOTAL", "DESCUENTO", "TOTAL"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listadeorco.size(); i++) {
            if (listadeorco.get(i).getEstado().equals("A")
                    && listadeorco.get(i).getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra() == orco.getCoOrdenComprasPK().getIdOrdenCompra()) {
                filas[0] = listadeorco.get(i).getDescripcion();
//                filas[1] = listadeorco.get(i).getMarca();
//                filas[2] = listadeorco.get(i).getModelado();
                model.addRow(filas);
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

    public static void llenarDetalledeOrdenAprovado(JTable tabla, List<CoDetalleOrdenPedido> lista) {
        CoDetalleOrdenPedido vo = new CoDetalleOrdenPedido();

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"N°", "COD. PROD", "NOMBRE PRODUCTO",
            "CANTIDAD SOLICITADA", "",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, JButton.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {

                JButton jButText = (JButton) tabla.getValueAt(row, 4);

                System.out.println("wwwww :" + jButText.getText());

                if (jButText.getText().equals("ACTIVAR")) {
                    System.out.println("w1 :" + jButText.getText());
                    return tbordenpedido2[column];
                } else {
                    System.out.println("w2 :" + jButText.getText());
                    return tbordenpedido[column];
                }
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {

                Object filas[] = new Object[5];
                vo = lista.get(i);

                filas[0] = lista.get(i).getCoDetalleOrdenPedidoPK().getLineaDetalle();
                filas[1] = lista.get(i).getCoDetalleOrdenPedidoPK().getIdProducto();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidadSolicitada();

                if (lista.get(i).getEstado().equals("A")) {
                    filas[4] = new JButton("DESACTIVAR");


                } else {
                    filas[4] = new JButton("ACTIVAR");

                }

                dt.addRow(filas);

            }

        }

        tabla.setModel(dt);
    }

    public static void llenarDetalledeOrden(JTable tabla, List<CoDetalleOrdenPedido> lista) {
        CoDetalleOrdenPedido vo = new CoDetalleOrdenPedido();

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"N°", "COD. PROD", "NOMBRE PRODUCTO",
            "CANTIDAD SOLICITADA", "",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, JButton.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbordenpedido[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
//                if (lista.get(i).getEstado().equals("A") ){
//                 model.addRow(new Object[]{});
                Object filas[] = new Object[5];
                vo = lista.get(i);
//                fila[0] = "" + vo.getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor();

                filas[0] = lista.get(i).getCoDetalleOrdenPedidoPK().getLineaDetalle();
                filas[1] = lista.get(i).getCoDetalleOrdenPedidoPK().getIdProducto();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidadSolicitada();

//                String ac = (String) vo.getEstado();
                JButton button = new JButton("ELIMINAR");
                filas[4] = button;

                dt.addRow(filas);

            }

        }

        tabla.setModel(dt);
    }

    public static void listarOrdenesdeCompra(List<CoOrdenCompras> lista, JTable Tabla) {
        int[] a = {30, 30, 40, 50, 30, 35};
        Font fuente = new Font("Bold", Font.BOLD, 12);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"No. ORDEN", "EMPRESA", "PROVEEDOR", "FECHA ENTREGA", "TOTAL", "TOTAL CON IVA"};
        String[] Filas = new String[6];
        model = new DefaultTableModel(null, Co);
        JTableHeader jt;
        jt = Tabla.getTableHeader();
        jt.setFont(fuente);
        jt.setDefaultRenderer(tcr);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = String.valueOf(lista.get(i).getCoOrdenComprasPK().getIdOrdenCompra());
                Filas[1] = String.valueOf(lista.get(i).getSeSucursal().getSeEmpresa().getNombreComercial());
                Filas[2] = String.valueOf(lista.get(i).getIdProveedor().getNombre());
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaEntrega().getTime()));
                Filas[4] = String.valueOf(Double.valueOf(String.valueOf(lista.get(i).getTotalCompra())));
                Filas[5] = String.valueOf(Double.valueOf(String.valueOf(lista.get(i).getTotalIva())));
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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
            }
        }
    }

    public static void listarEmpresa(List<SeEmpresa> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"RUC", "NOMBRE", "DIRECCION", "TELEFONO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals('A')) {
                Filas[0] = lista.get(i).getRuc();
                Filas[1] = lista.get(i).getNombreComercial();
                Filas[2] = lista.get(i).getDireccion();
                Filas[3] = lista.get(i).getTelefono();

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

    public static void listarSucursal(List<SeSucursal> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"NOMBRE", "DIRECCION", "TELEFONO"};
        String[] Filas = new String[3];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals('A')) {

                Filas[0] = lista.get(i).getNombreComercial();
                Filas[1] = lista.get(i).getDireccion();
                Filas[2] = lista.get(i).getTelefono();

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

    public static void listarProveedor(List<CoProveedores> lista, JTable Tabla) {
        int[] a = {30, 30, 40, 50};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"NOMBRE", "DIRECCION", "No. IDENTIFICACION", "NOMBRE COMERCIAL"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals('A')) {
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
        int[] a = {30, 30, 40, 50};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"NOMBRE", "DIRECCION", "No. IDENTIFICACION", "NOMBRE COMERCIAL"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals('I')) {
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
        int[] a = {5, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"Id", "NOMBRE"};
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
        String[] Co = {"Id", "NOMBRE"};
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

            if (lista.get(i).getEstado().equals('A')) {
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
            }
        }

    }

    public static void listarUsuarios(List<SeUsuarios> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"USUARIO", "NOMBRES", "CORREO", "FECHA CREACION", "FECHA ACTUALIZACION"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals('A')) {
                Filas[0] = lista.get(i).getIdUsuario();
                Filas[1] = lista.get(i).getNombreUsuario();
                Filas[2] = lista.get(i).getIdPersona().getCorreo();
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

    public void TablaMedida2(List<PrMedidas> listamedida, JTable tabla) {

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
        int[] a = {10, 50, 125, 100, 50, 50};
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
        model = VaciarTabla(tabla);
        InTipoDocumento vo = new InTipoDocumento();
        tabla.setDefaultRenderer(Object.class, new Render());
        //DefaultTableModel dt = new DefaultTableModel(new String[]{"COD.DOCUMENTO", "DOCUMENTO", "ESTADO",}, 0) {
        model = new DefaultTableModel(new String[]{"COD.DOCUMENTO", "DOCUMENTO", "ESTADO",}, 0) {

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

                model.addRow(fila);

            }

        }

        tabla.setModel(model);
    }

    public static void listarBodega(List<InBodega> lista, JTable Tabla) {
        int[] a = {5, 30, 80, 20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODIGO", "BODEGA", "TIPO DE BODEGA", "ESTADO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = "" + lista.get(i).getInBodegaPK().getIdBodega();
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

    public static void listarBodegaInactivos(List<InBodega> lista, JTable Tabla) {
        int[] a = {5, 30, 80, 20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODIGO", "BODEGA", "TIPO DE BODEGA", "ESTADO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = "" + lista.get(i).getInBodegaPK().getIdBodega();
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

    public static void TablaTipoBodegaInactivo(List<InTipoBodega> lista, JTable tabla) {
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
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("I")) {
                filas[0] = String.valueOf(lista.get(i).getIdTipoBodega());
                filas[1] = lista.get(i).getNombre();
                filas[2] = lista.get(i).getEstado();
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

    public static void TablaCajaActiva(List<VeCaja> lista, JTable Tabla) {
        int[] a = {5, 100, 90, 120, 20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "NOMBRE CAJA", "FECHA CREACION", "FECHA ACTUALIZACION", "ESTADO"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getIdCaja().toString();
                Filas[1] = lista.get(i).getNombre();
                Filas[2] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));
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

    public static void TablaCajaInactiva(List<VeCaja> lista, JTable Tabla) {
        int[] a = {5, 100, 90, 120, 20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "NOMBRE CAJA", "FECHA CREACION", "FECHA ACTUALIZACION", "ESTADO"};
        String[] Filas = new String[5];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = lista.get(i).getIdCaja().toString();
                Filas[1] = lista.get(i).getNombre();
                Filas[2] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));
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

    public static void tablaCotizacionPorProveedor(JTable Tabla, List<CoCotizacionesPorProveedor> lista) {
        int[] a = {5, 100, 100, 50, 80, 90, 90};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"    CODIGO", "EMPRESA", "PROVEEDOR", "TELEFONO", "CORREO", "FECHA DE ENVIO", "FECHA DE INGRESO"};
        String[] Filas = new String[7];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
//            Filas[0] = "" + lista.get(i).getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor();
//            Filas[4] = "" + lista.get(i).getIdProveedor().getTelefono1();
//            Filas[3] = lista.get(i).getIdProveedor().getEmail();
//            Filas[1] = lista.get(i).getIdProveedor().getNombreComercial();
//            Filas[5] = "" + lista.get(i).getFechaEnvioCotizacion();
//            Filas[6] = "" + lista.get(i).getFechaIngreso();
//
//            model.addRilas[2] = "" + lista.get(i).getIdProveedor().getNombre();
//            Filas[3] = lista.get(i).getIdProveedor().getEmail();
//            Filas[1] = lista.get(i).getIdProveedor().getNombreComercial();
//            Filas[5] = "" + lista.get(i).getFechaEnvioCotizacion();
//            Filas[6] = "" + lista.get(i).getFechaIngreso();
//
//            model.addRow(Filas);
//            Tabla.setModel(model);
//            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
//            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
//            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
//            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr1);
//            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
//            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr1);
//            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
//            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr1);
//            Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
//            Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr1);
//            Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
//            Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr1);
//            Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
//            Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr1);
        }
    }

    public static void TablaCotizacionPorProveedorDos(JTable tabla, List<CoCotizacionesPorProveedor> lista) {
        CoCotizacionesPorProveedor vo = new CoCotizacionesPorProveedor();
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"CODIGO", "EMPRESA", "PROVEEDOR", "TELEFONO", "CORREO", "FECHA DE ENVIO", "FECHA DE INGRESO", "ESTADO",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable3[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
//                 model.addRow(new Object[]{});
                Object fila[] = new Object[8];
                vo = lista.get(i);
//                fila[0] = "" + vo.getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor();
                fila[1] = ObtenerDTO.ObtenerCoProveedores(vo.getIdProveedor().longValue()).getNombreComercial();
                fila[2] = ObtenerDTO.ObtenerCoProveedores(vo.getIdProveedor().longValue()).getNombre();
                fila[3] = ObtenerDTO.ObtenerCoProveedores(vo.getIdProveedor().longValue()).getEmail();
                fila[4] = ObtenerDTO.ObtenerCoProveedores(vo.getIdProveedor().longValue()).getTelefono1();
                fila[5] = vo.getFechaEnvioCotizacion();
                fila[6] = vo.getFechaIngreso();
                String ac = (String) vo.getEstado();
                if ("A".equals(ac)) {
                    fila[7] = true;
                } else {
                    fila[7] = false;
                }

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
    }

    public static void TablaLecturaCotizacion(JTable tabla, List<CoItemsCotizacion> lista) {
        CoItemsCotizacion vo = new CoItemsCotizacion();
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"CODIGO", "FECHA DE EMISION", "PROSCESADO", "ESTADO",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable4[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
//                 model.addRow(new Object[]{});
                Object fila[] = new Object[4];
                vo = lista.get(i);
                fila[0] = "" + vo.getCoItemsCotizacionPK().getIdCotizacion();
                fila[1] = vo.getFechaEmision();
                fila[2] = vo.getProcesado();
                String ac = (String) vo.getEstado();
                if ("A".equals(ac)) {
                    fila[3] = true;
                } else {
                    fila[3] = false;
                }

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
    }

    public static void TablaDetallePorProveerdo(List<CoDetalleCotizacionPorProveedor> lista, JTable tabla) {
        CoDetalleCotizacionPorProveedor vo = new CoDetalleCotizacionPorProveedor();
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"LINEA DE DETALLE", "PRODUCTO", "DESCRIPCION", "CANTIDAD", "CANTIDAD COTIZADO", "ESTADO",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable2[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                // model.addRow(new Object[]{});
                Object fila[] = new Object[6];
                vo = lista.get(i);
//                fila[0] = vo.getCoDetalleCotizacionPorProveedorPK().getLineaDetalle();
                fila[1] = ObtenerDTO.ObtenerPrProductos(vo.getIdProducto().longValue()).getNombreProducto();
                fila[2] = vo.getDescripcion();
                fila[3] = vo.getCantidadPedido();
                fila[4] = vo.getCantidadCotizado();
                String ac = (String) vo.getEstado();
                if ("A".equals(ac)) {
                    fila[5] = true;
                } else {
                    fila[5] = false;
                }

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
    }

    public static void TablaDetallePorItemCotizacion(List<CoDetItemsCotizacion> lista, JTable tabla) {
        CoDetItemsCotizacion vo = new CoDetItemsCotizacion();
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"LINEA DE DETALLE", "PRODUCTO", "DESCRIPCION", "FALTANTE", "CANTIDAD PEDIDO", "CANTIDAD COTIZADO", "VALOR MINIMO", "VALOR MAXIMO", "ESTADO",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable5[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                // model.addRow(new Object[]{});
                Object fila[] = new Object[9];
                vo = lista.get(i);
                fila[0] = vo.getCoDetItemsCotizacionPK().getLineaDetalle();
                fila[1] = ObtenerDTO.ObtenerPrProductos(vo.getIdProducto().longValue()).getNombreProducto();
                fila[2] = vo.getDescripcion();
                fila[3] = vo.getCantidadFaltante();
                fila[4] = vo.getCantidadPedida();
                fila[5] = vo.getCantidadCotizada();
                fila[6] = vo.getValorMinRef();
                fila[7] = vo.getValorMaxRef();
                String ac = (String) vo.getEstado();
                if ("A".equals(ac)) {
                    fila[8] = true;
                } else {
                    fila[8] = false;
                }

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
    }

    public static void listarProductoItems(List<PrProductos> lista, JTable Tabla) {
        int[] a = {75, 190, 180, 180, 190, 190, 190, 90, 90};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD_PRO", "COD_BARRA", "PRODUCTO", "GRUPO", "SUBGRUPO", "PRESENTACION", "MEDIDA", "DESCONTINUADO", "RECETA"};
        String[] Filas = new String[9];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = String.valueOf(lista.get(i).getPrProductosPK().getIdProducto());
                Filas[1] = lista.get(i).getCodigoBarra();
                Filas[2] = lista.get(i).getNombreProducto();
                Filas[3] = lista.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
                Filas[4] = lista.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getNombre();
                Filas[5] = lista.get(i).getPrMedidas().getPrTipoPresentacion().getNombre();
                //Filas[3] = ObtenerDTO.ObtenerPrTipoPresentacion(i).getNombre();
                Filas[6] = lista.get(i).getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
                Filas[7] = lista.get(i).getDescontinuado();
                Filas[8] = lista.get(i).getReceta();

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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
                Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
                Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(8).setPreferredWidth(a[8]);
                Tabla.getColumnModel().getColumn(8).setCellRenderer(tcr);
            }
        }

    }

    public static void listarProductoItemsCotizacion(List<PrProductos> lista, JTable Tabla) {
        int[] a = {75, 190, 80, 180, 190, 190, 190, 90};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD_PRO", "PRODUCTO", "CANTIDAD", "GRUPO", "SUBGRUPO", "PRESENTACION", "MEDIDA", "RECETA"};
        String[] Filas = new String[9];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = String.valueOf(lista.get(i).getPrProductosPK().getIdProducto());
                Filas[1] = lista.get(i).getNombreProducto();
                Filas[2] = "";
                Filas[3] = lista.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
                Filas[4] = lista.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getNombre();
                Filas[5] = lista.get(i).getPrMedidas().getPrTipoPresentacion().getNombre();
                Filas[6] = lista.get(i).getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
                Filas[7] = lista.get(i).getReceta();

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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
                Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
                Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);

            }
        }

    }

    public static void TablaClientesActivo(List<SeClientes> listacliente, JTable Tabla) {
        int[] a = {50, 120, 200, 300};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "IDENTIFICACION", "NUMERO DE IDENTIFICACION", "NOMBRE COMPLETO"};
        String[] filas = new String[4];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listacliente.size(); i++) {
            if (listacliente.get(i).getEstado().equals("A")) {
                filas[0] = String.valueOf(listacliente.get(i).getIdClientes());
                filas[1] = listacliente.get(i).getIdTipoIndentificacion().getNombreIdentificacion();
                filas[2] = listacliente.get(i).getNumeroIdentificacion();
                filas[3] = listacliente.get(i).getNombreCompleto();
                model.addRow(filas);
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

    public static void TablaLocalidadCliente(List<SeLocalidadCliente> listalocalidadcliente, JTable Tabla, SeClientes lista) {
        int[] a = {50, 250, 250, 100, 100, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "DIRECCION CLIENTE", "DIRECCION ENTREGA", "PAIS", "PROVINCIA", "CIUDAD"};
        String[] filas = new String[6];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listalocalidadcliente.size(); i++) {
            if (listalocalidadcliente.get(i).getEstado().equals("A")
                    && listalocalidadcliente.get(i).getIdCliente().getIdClientes() == lista.getIdClientes()) {
                filas[0] = String.valueOf(listalocalidadcliente.get(i).getIdLocalidadCliente());
                filas[1] = listalocalidadcliente.get(i).getDirreccionCliente();
                filas[2] = listalocalidadcliente.get(i).getDirreccionEntrega();
                filas[3] = listalocalidadcliente.get(i).getIdPais().getNombre();
                filas[4] = listalocalidadcliente.get(i).getIdProvincia().getNombre();
                filas[5] = listalocalidadcliente.get(i).getIdCiudad().getNombre();
                model.addRow(filas);
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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
            }
        }
    }

    public static void TablaContactoCliente(List<SeContactosClientes> listacontactoscliente, JTable Tabla, SeLocalidadCliente lista) {
        int[] a = {50, 200, 150, 150, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "CONTACTO", "TELEFONO", "CELULAR", "CORREO"};
        String[] filas = new String[5];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listacontactoscliente.size(); i++) {
            if (listacontactoscliente.get(i).getEstado().equals("A")
                    && listacontactoscliente.get(i).getIdLocalidad().getIdLocalidadCliente() == lista.getIdLocalidadCliente()) {
                filas[0] = String.valueOf(listacontactoscliente.get(i).getIdContactosClientes());
                filas[1] = listacontactoscliente.get(i).getNombre();
                filas[2] = listacontactoscliente.get(i).getTelefono();
                filas[3] = listacontactoscliente.get(i).getCelular();
                filas[4] = listacontactoscliente.get(i).getEmail();
                model.addRow(filas);
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

    public static void TablaClientesInactivos(List<SeClientes> listacliente, JTable Tabla) {
        int[] a = {5, 50, 120, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "IDENTIFICACION", "NUMERO DE IDENTIFICACION", "NOMBRE COMPLETO"};
        String[] filas = new String[4];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listacliente.size(); i++) {
            if (listacliente.get(i).getEstado().equals("I")) {
                filas[0] = String.valueOf(listacliente.get(i).getIdClientes());
                filas[1] = listacliente.get(i).getIdTipoIndentificacion().getNombreIdentificacion();
                filas[2] = listacliente.get(i).getNumeroIdentificacion();
                filas[3] = listacliente.get(i).getNombreCompleto();
                model.addRow(filas);
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

    public static void TablaUnidadServicio(List<VeUnidadServicio> listaunidad, JTable Tabla) {
        int[] a = {5, 50, 166};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "NOMBRE", "ESTADO"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaunidad.size(); i++) {
            filas[0] = String.valueOf(listaunidad.get(i).getIdUnidadServicio());
            filas[1] = listaunidad.get(i).getNombreUnidadServicio();
            filas[2] = listaunidad.get(i).getEstado();
            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        }

    }

    public static void TablaPrestaciones(List<PrPrestaciones> listaprestaciones, JTable Tabla) {
        int[] a = {40, 40, 15, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"Codigo Prestacion", "Nombre prestacion", "Estado", "Aplica Iva"};
        String[] filas = new String[4];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaprestaciones.size(); i++) {
//            if (listaprestaciones.get(i).getEstado().equals('A')) {
            filas[0] = String.valueOf(listaprestaciones.get(i).getIdPrestacion());
            filas[1] = listaprestaciones.get(i).getNombrePrestacion();
            filas[2] = listaprestaciones.get(i).getEstado();
            filas[3] = listaprestaciones.get(i).getAplicaIva();
            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
//            }
        }
    }

    public static void TablaContactoClienteVacia(List<SeContactosClientes> listacontactoscliente, JTable Tabla, SeLocalidadCliente lista) {
        int[] a = {5, 50, 50, 80, 80};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID", "NOMBRE", "TELEFONO", "CELULAR", "CORREO"};
        String[] filas = new String[5];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listacontactoscliente.size(); i++) {
            filas[0] = "";
            filas[1] = "";
            filas[2] = "";
            filas[3] = "";
            filas[4] = "";
            model.addRow(filas);
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

    public static void TablaPrestacionesPorServicios(List<InPrestacionesPorServicios> listapresporserv, JTable Tabla) {
        int[] a = {150, 150, 150, 80, 80, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID PRESTACIONES", "NOMBRE PRESTACIONES", "UNIDAD DE SERVICIO", "ESTADO", "Facturable", "Aplica Descuento"};
        String[] filas = new String[6];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listapresporserv.size(); i++) {
            filas[0] = String.valueOf(listapresporserv.get(i).getPrPrestaciones().getIdPrestacion());
            filas[1] = listapresporserv.get(i).getPrPrestaciones().getNombrePrestacion();
            filas[2] = listapresporserv.get(i).getVeUnidadServicio().getNombreUnidadServicio();
            filas[3] = listapresporserv.get(i).getEstado();
            filas[4] = listapresporserv.get(i).getEsFacturable();
            filas[5] = listapresporserv.get(i).getAplicaDescuento();
            model.addRow(filas);
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
            Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
            Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);

        }

    }

    public static void listarCabOrdendePedido(List<CoOrdenPedido> lista, JTable Tabla) {
        int[] a = {5, 5, 5, 20, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "FECHA DE EMISION", "USUARIO", "ESTADO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("P")) {
                Filas[0] = "" + lista.get(i).getCoOrdenPedidoPK().getIdOrdenPedido();
                Filas[3] = lista.get(i).getEstado();
                Filas[1] = "" + Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaEmision().getTime()));
                Filas[2] = lista.get(i).getUsuarioCreacion();

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

    public static void listarDetalleTarifario(List<CoDetallesTarifa> listaprestacionesPSO, JTable Tabla) {
        int[] a = {150, 200, 100, 100, 100, 200, 100, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"Nombre prestacion", "Nombre Unidad por servicio", "Empresa", "Estado", "valor costo", "valor minimo venta", "valor venta", "valor venta mayorista"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaprestacionesPSO.size(); i++) {
            if (listaprestacionesPSO.get(i).getInPrestacionesPorServicios().getEstado().equals("A")) {
                filas[0] = String.valueOf(listaprestacionesPSO.get(i).getInPrestacionesPorServicios().getPrPrestaciones().getNombrePrestacion());
                filas[1] = String.valueOf(listaprestacionesPSO.get(i).getInPrestacionesPorServicios().getVeUnidadServicio().getNombreUnidadServicio());
                filas[2] = String.valueOf(listaprestacionesPSO.get(i).getInPrestacionesPorServicios().getIdEmpresa());
                filas[3] = listaprestacionesPSO.get(i).getInPrestacionesPorServicios().getEstado();
                filas[4] = String.valueOf(listaprestacionesPSO.get(i).getValorCosto());
                filas[5] = String.valueOf(listaprestacionesPSO.get(i).getValorMinimoVenta());
                filas[6] = String.valueOf(listaprestacionesPSO.get(i).getValorVenta());
                filas[7] = String.valueOf(listaprestacionesPSO.get(i).getValorVentaMayorista());

                model.addRow(filas);
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
                Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
                Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
                Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
                Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);

            }
        }

    }

    public static void listarTarifario(List<PrTarifario> listaT, JTable Tabla) {
        int[] a = {200, 150, 200, 350, 400, 400, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID TARIFARIO", "EMPRESA", "SUCURSAL", "DESCRIPCION", "FECHA INICIO VIGENTE", "FECHA FIN VIGENTE", "ESTADO"};
        String[] filas = new String[7];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaT.size(); i++) {

            filas[0] = String.valueOf(listaT.get(i).getPrTarifarioPK().getIdTarifario());
            filas[1] = String.valueOf(listaT.get(i).getPrTarifarioPK().getIdEmpresa());
            filas[2] = String.valueOf(listaT.get(i).getSeSucursal());
            filas[3] = String.valueOf(listaT.get(i).getDescripcion());
            filas[4] = String.valueOf(listaT.get(i).getFechaInicioVigente());
            filas[5] = String.valueOf(listaT.get(i).getFechaFinVigente());
            filas[6] = String.valueOf(listaT.get(i).getEstado());

            model.addRow(filas);
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
            Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
            Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
            Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);

        }

    }

    public static void ListarProductosVenta(List<PrPrestaciones> lisPrest, List<PrDetalleTarifario> listDetaTari, List<InKardex> listaKardex, List<PrProductos> listProd, JTable Tabla) {
        int[] a = {50, 300, 100, 100,100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "DESCRIPCION", "CANTIDAD", "PRECIO","DESCUENTO"};
        String[] filas = new String[5];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < listProd.size(); i++) {
            for (int j = 0; j < listaKardex.size(); j++) {
                if (listaKardex.get(j).getInKardexPK().getIdProducto()
                        == listProd.get(i).getPrProductosPK().getIdProducto()) {
                    for (int k = 0; k < listDetaTari.size(); k++) {
                        for (int l = 0; l < lisPrest.size(); l++) {
                            if (listDetaTari.get(k).getIdPrestacion().toString().equals(
                                    lisPrest.get(l).getIdPrestacion().toString())) {
                                if (listaKardex.get(j).getInKardexPK().getIdProducto()
                                        == Long.valueOf(lisPrest.get(l).getIdPoducto().toString())) {
                                    
                                    filas[0] = "" + lisPrest.get(l).getIdPrestacion();
//                                    filas[0] = "" + listaKardex.get(j).getInKardexPK().getIdProducto();
                                    filas[1] = listProd.get(i).getNombreProducto();
                                    filas[2] = listaKardex.get(j).getCantidad().toString();
                                    filas[3] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorVenta());
                                    filas[4] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorDescuento());
                                    
                                    model.addRow(filas);
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

                    }
                }
            }
        }
    }

    public static void llenarDetalleVenta(JTable tabla, List<VeFacturaDetalle> lista) {
        VeFacturaDetalle vo = new VeFacturaDetalle();

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"N°", "COD. PROD", "NOMBRE PRODUCTO",
            "CANTIDAD SOLICITADA", "",}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, JButton.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbordenpedido[column];
            }
        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
//                 model.addRow(new Object[]{});
                Object filas[] = new Object[5];
                vo = lista.get(i);
//                fila[0] = "" + vo.getCoCotizacionesPorProveedorPK().getIdCotizacionesPorPorveedor();

                filas[0] = lista.get(i).getVeFacturaDetallePK().getLineaDetalle();
                filas[1] = lista.get(i).getVeFacturaDetallePK().getIdPrestaciones();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidad();

//                String ac = (String) vo.getEstado();
                filas[4] = new JButton("ELIMINAR");

                dt.addRow(filas);

            }

        }

        tabla.setModel(dt);
    }
}
