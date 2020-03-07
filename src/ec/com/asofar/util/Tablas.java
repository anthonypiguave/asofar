/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.dao.InBodegaJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.JoinProductoVenta;
import ec.com.asofar.daoext.ObtenerDTO;
import static ec.com.asofar.daoext.ObtenerDTO.ObtenerPrProductos;
import ec.com.asofar.daoext.Pr_ProductoExt;
import ec.com.asofar.daoext.ProductoCadena;
import ec.com.asofar.daoext.ReporteComprasDTO;
import ec.com.asofar.daoext.ReporteDetalleComprasDTO;
import ec.com.asofar.daoext.ReporteDetalleFacturaDTO;
import ec.com.asofar.daoext.ReporteFacturaDTO;
import ec.com.asofar.daoext.ValidacionCaja;
import ec.com.asofar.dto.CoCotizacionesPorProveedor;
import ec.com.asofar.dto.CoDetItemsCotizacion;
import ec.com.asofar.dto.CoDetalleCotizacionPorProveedor;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoItemsCotizacion;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductoBodega;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.SeCiudad;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeUnidadServicio;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
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
    private static boolean[] tbVenta = {false, false, false, true, false, false, false, false, false, true};
    private static boolean[] tbordenpedido2 = {false, false, false, false, true};
    private static boolean[] tbordencompra = {false, false, false, false, true, false, false, true, false};
    private static boolean[] tbordenrecibido = {false, false, false, false, false, true, true, true, false};
    private static boolean[] tbdetallecompra = {false, false, false, false, false, false, false, false};

    public static void filtro(String valor, JTable Tabla) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        Tabla.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + valor));
    }

//    public static void filtro2(String valor, JTable tba_prestacionesporservicios) {
//        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
//        tba_prestacionesporservicios.setRowSorter(tr);
//        tr.setRowFilter(RowFilter.regexFilter("(?i)" + valor));
//    }
    public static DefaultTableModel VaciarTabla(JTable tabla) {
        DefaultTableModel tab
                = (DefaultTableModel) tabla.getModel();
        while (tab.getRowCount() > 0) {
            tab.removeRow(0);
        }
        return tab;
    }

    public static void ListarProductosConsulta(List<PrProductos> listaproducto, JTable Tabla) {
        int[] a = {60, 100, 130, 150, 150, 200, 130, 550};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "COD. BARRA", "CATEGORIA", "SUBCATEGORIA", "ARTICULO",
            "PRESENTACION", "MEDIDAS", "PRODUCTO"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < listaproducto.size(); i++) {

            filas[0] = "" + listaproducto.get(i).getPrProductosPK().getIdProducto();
            filas[1] = "" + listaproducto.get(i).getCodigoBarra();
            filas[2] = listaproducto.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
            filas[3] = listaproducto.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getNombre();
            filas[4] = listaproducto.get(i).getPrMedidas().getPrArticulo().getNombreArticulo();
            filas[5] = listaproducto.get(i).getPrMedidas().getPrTipoPresentacion().getNombre();
            filas[6] = listaproducto.get(i).getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
            filas[7] = listaproducto.get(i).getNombreProducto();

            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
            Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
            Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
            Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
            Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr2);
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

        tabla.setDefaultRenderer(Object.class, new Render1());
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

                if (jButText.getText().equals("ACTIVAR")) {

                    return tbordenpedido2[column];
                } else {

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
        int[] a = {100, 100, 500, 100, 200};

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"N°", "COD. PROD", "PRODUCTO",
            "CANTIDAD", "",}, 0) {

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

                Object filas[] = new Object[5];

                filas[0] = lista.get(i).getCoDetalleOrdenPedidoPK().getLineaDetalle();
                filas[1] = lista.get(i).getCoDetalleOrdenPedidoPK().getIdProducto();

//                JTextArea textArea = new JTextArea();
//                textArea.append(lista.get(i).getDescripcion());
//                filas[2] = textArea;
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidadSolicitada();

//                String ac = (String) vo.getEstado();
                JButton button = new JButton("ELIMINAR");
                filas[4] = button;

                dt.addRow(filas);

            }

        }

        tabla.setModel(dt);
        tabla.setRowHeight(30);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
        tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
//        tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);

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
                Filas[2] = String.valueOf(lista.get(i).getIdProveedor());
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

            if (lista.get(i).getEstado().equals("A")) {
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

            if (lista.get(i).getEstado().equals("A")) {

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
        int[] a = {70, 200, 70};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODIGO", "MOVIMIENTO", "ESTADO"};
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
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "NOMBRE"};
        String[] Filas = new String[2];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getIdGrupo().toString();
                Filas[1] = lista.get(i).getNombre();

                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr1);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr1);

            }
        }

    }

    public static void listarGruposInactivos(List<PrGrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.", "NOMBRE"};
        String[] Filas = new String[2];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = lista.get(i).getIdGrupo().toString();
                Filas[1] = lista.get(i).getNombre();

                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr1);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr1);

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

            if (lista.get(i).getEstado().equals("A")) {
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

    public static void listarUsuarios(List<SeUsuarios> lista, JTable Tabla, SeSucursal suc) {
        int[] a = {5, 5, 30, 5};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"ID", "USUARIO", "CORREO", "ROL"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("sucursal: "+lista.get(i).getSeUsuarioSucurRolList().get(0));
//            System.out.println("suc:"+suc);
            if ((lista.get(i).getEstado().equals("A") && lista.get(i).getSeUsuarioSucurRolList().get(0).getSeSucursal().equals(suc))) {

                Filas[0] = "" + lista.get(i).getIdUsuario();
                Filas[1] = lista.get(i).getUsuario();
                Filas[2] = lista.get(i).getIdPersona().getCorreo();
                Filas[3] = lista.get(i).getSeUsuarioSucurRolList().get(0).getIdRoles().getNombre();

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

    public static void listarSubgrupos(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {5, 30};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"GRUPO", "SUBGRUPO"};
        String[] Filas = new String[2];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getPrGrupos().getNombre();
                Filas[1] = lista.get(i).getNombre();

                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr1);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr1);

            }
        }

    }

    public static void listarSubgruposIn(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {5, 30, 30, 10, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"GRUPO", "SUBGRUPO"};
        String[] Filas = new String[2];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("I")) {
                Filas[0] = lista.get(i).getPrGrupos().getNombre();
                Filas[1] = lista.get(i).getNombre();

                model.addRow(Filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr1);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr1);

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
            if (listaprod.get(i).getCodigoBarra() == null) {
                filas[2] = "--";
            } else {
                filas[2] = listaprod.get(i).getCodigoBarra();
            }
            if (listaprod.get(i).getCodFabricante() == null) {
                filas[3] = "--";
            } else {
                filas[3] = listaprod.get(i).getCodFabricante().getNombre();
            }
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
        int[] a = {10, 100, 100, 10};
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
                Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
                Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            }
        }
    }

    public static void listarBodegaInactivos(List<InBodega> lista, JTable Tabla) {
        int[] a = {10, 100, 100, 10};
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
                Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
                Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
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
        int[] a = {5, 100, 100, 100, 120, 20};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CODIGO", "NOMBRE CAJA", "USUARIO", "FECHA CREACION", "FECHA ACTUALIZACION", "ESTADO"};
        String[] Filas = new String[6];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
                Filas[0] = lista.get(i).getIdCaja().toString();
                Filas[1] = lista.get(i).getNombre();
                SeUsuarios o
                        = ObtenerDTO.ObtenerUsuarios(lista.get(i).getIdUsuario().longValue());
                Filas[2] = "" + o.getUsuario();
                Filas[3] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaCreacion().getTime()));
                Filas[4] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaActualizacion().getTime()));
                Filas[5] = lista.get(i).getEstado();
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

    public static void TablaCajaInactiva(List<VeCaja> lista, JTable Tabla) {
        int[] a = {5, 100, 100, 120, 20};
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
//                filas[2] = listacontactoscliente.get(i).getTelefono();
//                filas[3] = listacontactoscliente.get(i).getCelular();
//                filas[4] = listacontactoscliente.get(i).getEmail();
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
        int[] a = {5, 50, 40};
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
        int[] a = {120, 500, 60, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"Codigo Prestacion", "Nombre Prestacion", "Estado", "Aplica Iva"};
        String[] filas = new String[4];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaprestaciones.size(); i++) {

            filas[0] = String.valueOf(listaprestaciones.get(i).getIdPrestacion());
            filas[1] = listaprestaciones.get(i).getNombrePrestacion();
            filas[2] = listaprestaciones.get(i).getEstado();
            filas[3] = listaprestaciones.get(i).getAplicaIva();
            model.addRow(filas);
            Tabla.setModel(model);

            Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
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
        int[] a = {130, 475, 140, 65, 95, 130};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.CENTER);
        model = VaciarTabla(Tabla);
        String[] b = {"ID PRESTACION", "NOMBRE DE PRESTACIONES", "UNIDAD DE SERVICIO", "ESTADO", "FACTURABLE", "APLICA DESCUENTO"};
        String[] filas = new String[6];
        model = new DefaultTableModel(null, b);

        Tabla.setShowGrid(true);
        for (int i = 0; i < listapresporserv.size(); i++) {
            filas[0] = String.valueOf(listapresporserv.get(i).getPrPrestaciones().getIdPrestacion());
            filas[1] = listapresporserv.get(i).getPrPrestaciones().getNombrePrestacion();
//            filas[2] = "" + listapresporserv.get(i).getVeUnidadServicio().getIdUnidadServicio();
            filas[2] = listapresporserv.get(i).getVeUnidadServicio().getNombreUnidadServicio();
            filas[3] = listapresporserv.get(i).getEstado();
            filas[4] = listapresporserv.get(i).getEsFacturable();
            filas[5] = listapresporserv.get(i).getAplicaDescuento();
            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
            Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
            Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
            Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
            Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
            Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
//            Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
//            Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);

        }
    }

    public static void TablaBuscarPrestaciones(List<PrPrestaciones> listabuscarprestaciones,
            JTable Tabla, List<VeUnidadServicio> listaUni, List<InPrestacionesPorServicios> listaPresxUni) {
        int count = 0;
        int[] a = {120, 500, 120};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD. PRESTACIONES", "NOMBRE PRESTACIONES", "ESTADO"};
        String[] filas = new String[4];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listabuscarprestaciones.size(); i++) {
            if (listabuscarprestaciones.get(i).getInPrestacionesPorServiciosList().size() < 2) {
                filas[0] = String.valueOf(listabuscarprestaciones.get(i).getIdPrestacion());
                filas[1] = listabuscarprestaciones.get(i).getNombrePrestacion();
                filas[2] = listabuscarprestaciones.get(i).getEstado();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
                Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            }
        }
//        for (int i = 0; i < listabuscarprestaciones.size(); i++) {
//            for (int j = 0; j < listaPresxUni.size(); j++) {
//                if (listabuscarprestaciones.get(i).getIdPrestacion().equals(listaPresxUni.get(j).getPrPrestaciones().getIdPrestacion())) {
//                    for (int k = 0; k < listaUni.size(); k++) {
//                        if(listaPresxUni.get(j).getVeUnidadServicio().getIdUnidadServicio()== listaUni.get(k).getIdUnidadServicio()){
//                            count++;
//                        }
//                    }
//                }
//
//                if (count != listaUni.size()) {
//                    System.out.println("jjjjjjjjjjjjjj");
//                    if (listabuscarprestaciones.get(i).getInPrestacionesPorServiciosList().size() <2){
//                      filas[0] = String.valueOf(listabuscarprestaciones.get(i).getIdPrestacion());
//                    filas[1] = listabuscarprestaciones.get(i).getNombrePrestacion();
//                    filas[2] = listabuscarprestaciones.get(i).getEstado();
//
//                    model.addRow(filas);
//                    Tabla.setModel(model);
//                    Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
//                    Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
//                    Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
//                    Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
//                    Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
//                    Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);  
//                    }
//                    
//                }
//            }
//
//        }

    }

    public static void listarCabOrdendePedido(List<CoOrdenPedido> lista, JTable Tabla) {
        int[] a = {5, 25, 35, 45, 50, 5};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N. ORDEN", "FECHA DE EMISION", "PROVEEDOR", "DOCUMENTO", "USUARIO DE CREACIÓN", "ESTADO"};
        String[] Filas = new String[6];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("P")) {
                Filas[0] = "" + lista.get(i).getCoOrdenPedidoPK().getIdOrdenPedido();
                Filas[1] = "" + Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaEmision().getTime()));
                CoProveedores proveedor = ObtenerDTO.ObtenerProveedorPedido(lista.get(i).getIdProveedor());
                Filas[2] = proveedor.getNombre();
                InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido(lista.get(i).getIdDocumento());
                Filas[3] = tipoDocumento.getNombreDocumento();
                Filas[4] = lista.get(i).getUsuarioCreacion();
                Filas[5] = lista.get(i).getEstado();

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

    public static void listarDetalleTarifarios(List<PrDetalleTarifario> listaprestacionesPSO, JTable Tabla, PrTarifario tp1, List<PrPrestaciones> listaPrestacion) {
        int[] a = {150, 200, 100, 100, 100, 200, 200, 100, 200, 200, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"detalle tarifario", "tarifario", "unidad servicio", "Empresa", "Sucursal", "nombre prestacion", "valor costo", "valor minimo venta", "valor venta", "valor descueto", "Estado",};
        String[] filas = new String[11];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < listaprestacionesPSO.size(); i++) {
//            for (int j = 0; j < listaPrestacion.size(); j++) {

            if (listaprestacionesPSO.get(i).getEstado().equals("A")) {
                if (tp1.getPrTarifarioPK().getIdTarifario() == listaprestacionesPSO.get(i).getPrTarifario().getPrTarifarioPK().getIdTarifario()) {
//                    if (listaprestacionesPSO.get(i).getIdPrestacion().equals(listaPrestacion.get(j).getIdPrestacion())) {

                    System.out.println("entro");
                    filas[0] = String.valueOf(listaprestacionesPSO.get(i).getIdDetalleTarifario());
                    PrTarifario pt = ObtenerDTO.ObtenerPrTarifario(BigInteger.valueOf(listaprestacionesPSO.get(i).getPrTarifario().getPrTarifarioPK().getIdTarifario()));
                    filas[1] = pt.getDescripcion();
                    VeUnidadServicio veuniser = ObtenerDTO.ObtenerVeUnidadServiciON(listaprestacionesPSO.get(i).getIdUnidadServicio());
                    filas[2] = veuniser.getNombreUnidadServicio();
                    SeEmpresa semp = ObtenerDTO.ObtenerSeEmpresaL(listaprestacionesPSO.get(i).getPrTarifario().getPrTarifarioPK().getIdEmpresa());
                    filas[3] = semp.getNombreComercial();
                    SeSucursal sucl = ObtenerDTO.ObtenerSeSucursalL(listaprestacionesPSO.get(i).getPrTarifario().getSeSucursal().getSeSucursalPK().getIdSucursal());
                    filas[4] = sucl.getNombreComercial();
                    PrPrestaciones presser = ObtenerDTO.ObtenerPrPrestacionesOn(listaprestacionesPSO.get(i).getIdPrestacion());
                    System.out.println("hhhhhhhhhh" + presser.getNombrePrestacion());
                    //listaprestacionesPSO.get(i).getIdPrestacion
                    filas[5] = presser.getNombrePrestacion();
                    filas[6] = String.valueOf(listaprestacionesPSO.get(i).getValorCosto());
                    filas[7] = String.valueOf(listaprestacionesPSO.get(i).getValorMinVenta());
                    filas[8] = String.valueOf(listaprestacionesPSO.get(i).getValorVenta());
                }
                if (listaprestacionesPSO.get(i).getValorDescuento() == null) {
                    filas[9] = "0.00";

                } else {
                    filas[9] = String.valueOf(listaprestacionesPSO.get(i).getValorDescuento());

                }

                filas[10] = String.valueOf(listaprestacionesPSO.get(i).getEstado());

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
                Tabla.getColumnModel().getColumn(8).setPreferredWidth(a[8]);
                Tabla.getColumnModel().getColumn(8).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(9).setPreferredWidth(a[9]);
                Tabla.getColumnModel().getColumn(9).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(10).setPreferredWidth(a[10]);
                Tabla.getColumnModel().getColumn(10).setCellRenderer(tcr);
//                    }
//                }

            }
        }
    }

    public static String convertirFecha(Date valor) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = "";
        date1 = format1.format(valor);
        return date1;
    }

    public static void listarTarifario(List<PrTarifario> listaT, JTable Tabla) {
        int[] a = {300, 600, 450, 400, 600, 600, 200, 400};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID TARIFARIO", "EMPRESA", "SUCURSAL", "DESCRIPCION", "FECHA INICIO VIGENTE", "FECHA FIN VIGENTE", "ESTADO", "USUARIO"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaT.size(); i++) {

            filas[0] = String.valueOf(listaT.get(i).getPrTarifarioPK().getIdTarifario());
            SeEmpresa em = ObtenerDTO.ObtenerSeEmpresaL(listaT.get(i).getPrTarifarioPK().getIdEmpresa());
            filas[1] = em.getNombreComercial();
//            filas[1] = String.valueOf(listaT.get(i).getPrTarifarioPK().getIdEmpresa());
            SeSucursal suc = ObtenerDTO.ObtenerSeSucursalL(listaT.get(i).getSeSucursal().getSeSucursalPK().getIdSucursal());
            filas[2] = suc.getNombreComercial();
//            filas[2] = String.valueOf(listaT.get(i).getSeSucursal().getSeSucursalPK().getIdSucursal());
            filas[3] = String.valueOf(listaT.get(i).getDescripcion());
            filas[4] = convertirFecha(listaT.get(i).getFechaInicioVigente());
            filas[5] = convertirFecha(listaT.get(i).getFechaFinVigente());
            filas[6] = String.valueOf(listaT.get(i).getEstado());
            filas[7] = String.valueOf(listaT.get(i).getUsuarioCreacion());

            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.setRowHeight(20);
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

    public static void ListarProductosVenta(List<PrPrestaciones> lisPrest, List<PrDetalleTarifario> listDetaTari, List<InKardex> listaKardex, List<PrProductos> listProd, JTable Tabla) {
        int[] a = {40, 300, 300, 100, 100, 100, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "COD. BARRA", "DESCRIPCION", "STOCK", "PRECIO", "DESCUENTO", "IVA"};
        String[] filas = new String[7];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listProd.size(); i++) {
//            System.out.println("list pro " + listProd.get(i).getPrProductosPK().getIdProducto());
            for (int j = 0; j < listaKardex.size(); j++) {
//                System.out.println("list Kardex "+listaKardex.get(j).getInKardexPK().getIdProducto());
                if (listaKardex.get(j).getInKardexPK().getIdProducto()
                        == listProd.get(i).getPrProductosPK().getIdProducto()) {
                    for (int k = 0; k < listDetaTari.size(); k++) {
                        for (int l = 0; l < lisPrest.size(); l++) {
                            if (listDetaTari.get(k).getIdPrestacion().toString().equals(
                                    lisPrest.get(l).getIdPrestacion().toString())) {
                                if (lisPrest.get(l).getIdPoducto() == null) {
                                    filas[0] = "-";
                                    if (listProd.get(i).getCodigoBarra() == null) {
                                        filas[1] = "-";
                                    } else {
                                        filas[1] = "" + listProd.get(i).getCodigoBarra();
                                    }
//                                    filas[0] = "" + listaKardex.get(j).getInKardexPK().getIdProducto();
                                    filas[2] = listProd.get(i).getNombreProducto();
                                    filas[3] = listaKardex.get(j).getCantidad().toString();
//                                    filas[3] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorVenta());
                                    filas[4] = "" + listDetaTari.get(k).getValorVenta();
                                    if (listDetaTari.get(k).getValorDescuento() == null) {
                                        filas[5] = "-";
                                        filas[6] = lisPrest.get(l).getAplicaIva();

                                    } else {
//                                        System.out.println("tablaclass" + listDetaTari.get(k).getValorDescuento());
//                                        filas[4] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorDescuento());
                                        filas[5] = "" + listDetaTari.get(k).getValorDescuento();
                                        filas[6] = lisPrest.get(l).getAplicaIva();
                                    }
                                } else {
                                    if (listaKardex.get(j).getInKardexPK().getIdProducto()
                                            == Long.valueOf(lisPrest.get(l).getIdPoducto().toString())) {
                                        filas[0] = "" + lisPrest.get(l).getIdPrestacion();
                                        if (listProd.get(i).getCodigoBarra() == null) {
                                            filas[1] = "-";
                                        } else {
                                            filas[1] = "" + listProd.get(i).getCodigoBarra();
                                        }
//                                    filas[0] = "" + listaKardex.get(j).getInKardexPK().getIdProducto();
                                        filas[2] = listProd.get(i).getNombreProducto();
                                        filas[3] = listaKardex.get(j).getCantidad().toString();
//                                    filas[3] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorVenta());
                                        filas[4] = "" + listDetaTari.get(k).getValorVenta();
                                        if (listDetaTari.get(k).getValorDescuento() == null) {
                                            filas[5] = "-";
                                            filas[6] = lisPrest.get(l).getAplicaIva();

                                        } else {
//                                        System.out.println("tablaclass" + listDetaTari.get(k).getValorDescuento());
//                                        filas[4] = Formato_Numeros.formatoNumero("" + listDetaTari.get(k).getValorDescuento());
                                            filas[5] = "" + listDetaTari.get(k).getValorDescuento();
                                            filas[6] = lisPrest.get(l).getAplicaIva();
                                        }
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
                            }

                        }

                    }
                }
            }
        }
    }

    public static void llenarDetalleVenta(JTable tabla, List<VeFacturaDetalle> lista) {
        int[] a = {10, 30, 200, 30, 60};
        VeFacturaDetalle vo = new VeFacturaDetalle();

        tabla
                .setDefaultRenderer(Object.class,
                        new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"N°", "COD. PROD",
            "DESCRIPCION", "CANTIDAD", "PRECIO", "DESCUENTO", "IVA", "SUBTOTAL", "TOTAL", "",}, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                JButton.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbVenta[column];
            }
        };
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                Object filas[] = new Object[10];
                vo = lista.get(i);
                filas[0] = lista.get(i).getVeFacturaDetallePK().getLineaDetalle();
                filas[1] = lista.get(i).getVeFacturaDetallePK().getIdPrestaciones();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidad();
                filas[4] = lista.get(i).getPrecioUnitarioVenta();
                filas[5] = lista.get(i).getValorDescuento();
                filas[6] = Formato_Numeros.formatoNumero("" + lista.get(i).getValorIva());
                filas[7] = Formato_Numeros.formatoNumero("" + lista.get(i).getSubtotal());
                filas[8] = Formato_Numeros.formatoNumero("" + lista.get(i).getValorTotal());
                JButton button = new JButton("ELIMINAR");
                filas[9] = button;
                dt.addRow(filas);

            }

        }
        tabla.setModel(dt);
        tabla.setRowHeight(30);

        tabla.setModel(dt);
    }

    public static void listarCabOrdendePedidoCompra(List<CoOrdenPedido> lista, JTable Tabla) {
        int[] a = {5, 5, 5, 20, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N. ORDEN", "FECHA DE EMISION", "USUARIO", "ESTADO"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("A")) {
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

    public static void listarDetalleCompra(List<CoDetalleOrdenCompra> lista, JTable tabla) {
        tabla.setDefaultRenderer(Object.class,
                new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"No.", "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "SUBTOTAL", "IVA", "DESCUENTO", "TOTAL"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                JCheckBox.class,
                java.lang.Object.class,
                java.lang.Object.class,};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbordencompra[column];
            }

        };

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                Object filas[] = new Object[9];

                filas[0] = "" + lista.get(i).getCoDetalleOrdenCompraPK().getLineaDetalle();
                filas[1] = "" + lista.get(i).getCoDetalleOrdenCompraPK().getIdProducto();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidadRecibida().toString();
                filas[4] = lista.get(i).getPrecioUnitario().toString();
                filas[5] = lista.get(i).getSubtotal().toEngineeringString();
                JCheckBox ch = new JCheckBox();
                System.out.println(" iva valor " + lista.get(i).getIva());

                BigDecimal bg1 = new BigDecimal("0.00");
                int res;

                res = bg1.compareTo(lista.get(i).getIva());

                if (res == 0) {
                    System.out.println(" entra 1");
                    ch.setSelected(false);
                } else {
                    System.out.println(" entra 2");
                    ch.setSelected(true);
                }
                filas[6] = ch;
                filas[7] = lista.get(i).getDescuento().toEngineeringString();
                filas[8] = lista.get(i).getTotal().toEngineeringString();

                dt.addRow(filas);
            }

        }

        tabla.setModel(dt);
    }

    public static void listarCabOrdendeCompra(List<CoOrdenCompras> lista, JTable Tabla) {
        int[] a = {5, 20, 40, 40, 20, 20, 20};
//        Font fuente = new Font("Bold", Font.BOLD, 10);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"N. ORDEN COMPRA", "FECHA RECEPCION", " PROVEEDOR", "TIPO DOCUMENTO", "TOTAL COMPRA", "USUARIO", "ESTADO"};
        String[] Filas = new String[7];
        model = new DefaultTableModel(null, Co);
//        JTableHeader jt;
//        jt = Tabla.getTableHeader();
//        jt.setFont(fuente);
//        jt.setDefaultRenderer(tcr);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado().equals("P")) {
                Filas[0] = "" + lista.get(i).getCoOrdenComprasPK().getIdOrdenCompra();
                Filas[1] = Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFechaEntrega().getTime()));
                CoProveedores proveedor = ObtenerDTO.ObtenerProveedorPedido(lista.get(i).getIdProveedor());
                Filas[2] = proveedor.getNombre();
                InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido(lista.get(i).getIdTipoDocumento());
                Filas[3] = tipoDocumento.getNombreDocumento();
                Filas[4] = lista.get(i).getTotalCompra().setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();
                Filas[5] = lista.get(i).getUsuarioCreacion();
                Filas[6] = lista.get(i).getEstado();

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

            }
        }

    }

    public static void listarDetalleRecepcion(List<InDetalleMovimiento> lista, JTable tabla, String[] nLote, String[] fecha) {
        int[] a = {60, 150, 600, 150, 150, 150, 150, 200, 100};

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        tabla.setDefaultRenderer(Object.class,
                new Render());

        DefaultTableModel dt = new DefaultTableModel(new String[]{"No.", "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "N. LOTE", "F. CADUCIDAD", "BODEGA", "RECIBIDO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
                JComboBox.class,
                JCheckBox.class,};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbordenrecibido[column];
            }

        };

        JComboBox cb = null;
        String[] values = null;

        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {
                Object filas[] = new Object[9];

                filas[0] = "" + lista.get(i).getInDetalleMovimientoPK().getLineaDetalle();
                filas[1] = "" + lista.get(i).getInDetalleMovimientoPK().getIdProducto();
                filas[2] = lista.get(i).getDescripcion();
                filas[3] = lista.get(i).getCantidad();
                filas[4] = lista.get(i).getPrecioUnitario().setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();

                JCheckBox ch = new JCheckBox();
                ch.setSelected(false);

                filas[8] = ch;

                cb = new JComboBox();
                cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--SELECCIONE--"}));

                InBodegaJpaController InBodegaController = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
                List<InBodega> listcaja = InBodegaController.findInBodegaEntities();

                values = new String[listcaja.size()];

                for (int j = 0; j < listcaja.size(); j++) {
                    cb.addItem(listcaja.get(j).getNombreBodega());
                    values[j] = listcaja.get(j).getNombreBodega();

                }

                filas[7] = cb;

                filas[5] = nLote[i];

                filas[6] = fecha[i];

                dt.addRow(filas);

            }

        }

        tabla.setModel(dt);
        tabla.setRowHeight(24);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
        tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
//        tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
        tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
        tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
//        tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
//        tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
//        tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(a[8]);
//        tabla.getColumnModel().getColumn(8).setCellRenderer(tcr);

        TableColumn col = tabla.getColumnModel().getColumn(7);
        col.setCellEditor(new MyComboBoxEditor(values));
//    col.setCellRenderer(new MyComboBoxRenderer(values));

    }

    public static void ListarProductosVenta2(List<JoinProductoVenta> lisProdVen, JTable Tabla) {
        int[] a = {40, 100, 300, 100, 80, 90, 80};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "COD. BARRA", "DESCRIPCION", "STOCK", "PRECIO", "DESCUENTO", "IVA"};
        String[] filas = new String[7];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
//        ProductoCadena k = new ProductoCadena();
        for (int i = 0; i < lisProdVen.size(); i++) {

//            System.out.println("error " + lisProdVen.get(i).getId_prestacion());
            filas[0] = "" + lisProdVen.get(i).getId_prestacion();
            if (lisProdVen.get(i).getCodigoBarra() == "") {
                filas[1] = "-";
                filas[3] = "-";
            } else {
                filas[1] = lisProdVen.get(i).getCodigoBarra();
                filas[3] = "" + lisProdVen.get(i).getSaldo_actual();
            }
//            filas[2] = lisProdVen.get(i).getNombre_producto()
            filas[2] = lisProdVen.get(i).getNombre_producto();
            filas[4] = "" + lisProdVen.get(i).getValor_venta();
            filas[5] = "" + lisProdVen.get(i).getValor_descuento();

            filas[6] = "" + lisProdVen.get(i).getAplica_iva();
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

    public static void listarReporteCompras(List<ReporteComprasDTO> lista, JTable Tabla) {

        int[] a = {5, 5, 20, 15, 15, 15, 15, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"COD.COMPRA", "TIPO DOC.", "FECHA ENTREGA", "PROVEEDOR", "SUBTOTAL", "T.IVA", "T.DESCUENTO", "T.COMPRA"};
        String[] Filas = new String[8];
        model = new DefaultTableModel(null, Co);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            // if (lista.get(i).getEstado().equals("P")) {
            Filas[0] = "" + lista.get(i).getId_orden_compra().toString();
            Filas[1] = lista.get(i).getNombre_documento().toString();
            //Filas[2] = "" + Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFecha_aprobacion().getTime()));
            Filas[2] = "" + lista.get(i).getFecha_aprobacion().toString();
            Filas[3] = lista.get(i).getNombre_proveedor().toString();
            Filas[4] = lista.get(i).getSubtotal().toString();
            Filas[5] = lista.get(i).getIva().toString();
            Filas[6] = lista.get(i).getDescuento().toString();
            Filas[7] = lista.get(i).getTotal_compra().toString();

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

    public static void listarReporteDetalleCompra(List<ReporteDetalleComprasDTO> lista, JTable Tabla) {
        int[] a = {5, 200, 20, 15, 15, 15, 15, 15};

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        Tabla.setShowGrid(true);
        Tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"COD.DET", "DESCRIPCION", "N° LOTE", "FECHA_CADUCIDAD", "SUBTOTAL",
            "T.IVA", "DESCUENTO", "T.COMPRA"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, JTextArea.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbdetallecompra[column];
            }
        };

        for (int i = 0; i < lista.size(); i++) {

            Object Filas[] = new Object[8];
            Filas[0] = "" + lista.get(i).getId_detalle_orden_compra().toString();
//            JTextArea textArea = new JTextArea();
//            textArea.append(""+lista.get(i).getDescripcion());
//            Filas[1] = textArea;
//            Filas[1] = "" + Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFecha_aprobacion().getTime()));
            Filas[1] = lista.get(i).getDescripcion();
            Filas[2] = "" + lista.get(i).getLote_fabricacion().toString();
            Filas[3] = lista.get(i).getFecha_caducidad().toString();
            Filas[4] = lista.get(i).getSubtotal().toString();
            Filas[5] = lista.get(i).getIva().toString();
            Filas[6] = lista.get(i).getDescuento().toString();
            Filas[7] = lista.get(i).getTotal().toString();

            dt.addRow(Filas);

            Tabla.setModel(dt);
            Tabla.setRowHeight(50);

            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
//               Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
//             Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
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

    public static void listarPrestacionesDetalleTarifario(List<PrDetalleTarifario> listDetalleTari, List<PrPrestaciones> listaPrestacion, JTable Tabla) {
        int[] a = {400, 500, 500, 500, 800, 300, 300, 250, 300, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"De tarifario", "Tarifario", "U. servicio", "Sucursal", "Nom. prestacion", "V.Costo", "V.Min. Venta", "V. venta", "V.Descuento", "Estado",};
        String[] Filas = new String[10];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listDetalleTari.size(); i++) {
            Filas[0] = listDetalleTari.get(i).getIdDetalleTarifario().toString();
            Filas[1] = "" + listDetalleTari.get(i).getPrTarifario().getDescripcion();
            VeUnidadServicio ve = ObtenerDTO.ObtenerVeUnidadServiciON(listDetalleTari.get(i).getIdUnidadServicio());
            Filas[2] = ve.getNombreUnidadServicio();
//            Filas[3] = "" + listDetalleTari.get(i).getPrTarifario().getSeSucursal().getSeEmpresa().getNombreComercial();
            Filas[3] = "" + listDetalleTari.get(i).getPrTarifario().getSeSucursal().getNombreComercial();
            PrPrestaciones pr = ObtenerDTO.ObtenerPrPrestacionesOn(listDetalleTari.get(i).getIdPrestacion());
            Filas[4] = pr.getNombrePrestacion();
            Filas[5] = "" + listDetalleTari.get(i).getValorCosto();
            Filas[6] = "" + listDetalleTari.get(i).getValorMinVenta();
            Filas[7] = "" + listDetalleTari.get(i).getValorVenta();
            Filas[8] = "" + listDetalleTari.get(i).getValorDescuento();
            Filas[9] = "" + listDetalleTari.get(i).getEstado();
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
            Tabla.getColumnModel().getColumn(9).setPreferredWidth(a[9]);
            Tabla.getColumnModel().getColumn(9).setCellRenderer(tcr);
//            Tabla.getColumnModel().getColumn(10).setPreferredWidth(a[10]);
//            Tabla.getColumnModel().getColumn(10).setCellRenderer(tcr);
        }
    }

    public static void listarReporteFactura(List<ReporteFacturaDTO> lista, JTable Tabla) {

        int[] a = {5, 5, 20, 15, 15, 15, 15, 15, 15};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] b = {"COD.FACT", "CAJA", "FECHA FACTURACION", "COMERCIAL", "SUBTOTAL", "T.DESCUENTO", "T.IVA", "T.FACTURA", "ESTADO"};
        String[] Filas = new String[9];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {
           

            Filas[0] = lista.get(i).getId_factura().toString();
            Filas[1] = lista.get(0).getNombre_caja();
            Filas[2] = lista.get(0).getFecha_facturacion().toString();
            Filas[3] = lista.get(0).getNombre_comercial_suc();
            Filas[4] = lista.get(0).getSubtotal().toString();
            Filas[5] = lista.get(0).getTotal_descuento().toString();
            Filas[6] = lista.get(0).getTotal_iva().toString();
            Filas[7] = lista.get(0).getTotal_facturado().toString();
            Filas[8] = lista.get(i).getEstado();

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

    public static void listarReporteDetalleFactura(List<ReporteDetalleFacturaDTO> lista, JTable Tabla) {
        int[] a = {5, 200, 20, 15, 15, 15, 15, 15};
        String Detalle = "";

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        Tabla.setShowGrid(true);
        Tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"COD.DET", "DESCRIPCION", "CANTIDAD", "PREC.UNI", "SUBTOTAL",
            "T.IVA", "DESCUENTO", "T.COMPRA"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, JTextArea.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return tbdetallecompra[column];
            }
        };

        for (int i = 0; i < lista.size(); i++) {
//            Detalle = ProductoCadena.obtenerCadena(lista.get(i).getId_producto());
            Object Filas[] = new Object[8];
            Filas[0] = "" + lista.get(i).getId_factura_detalle().toString();
            JTextArea textArea = new JTextArea();

            textArea.append(lista.get(i).getDescripcion());
            Filas[1] = textArea;

            //Filas[2] = "" + Fecha.getStringFecha(new java.sql.Date(lista.get(i).getFecha_aprobacion().getTime()));
            Filas[2] = "" + lista.get(i).getCantidad().toString();
            Filas[3] = lista.get(i).getPrecio_unitario_venta().toString();
            Filas[4] = lista.get(i).getSubtotal().toString();
            Filas[5] = lista.get(i).getValor_iva().toString();
            Filas[6] = lista.get(i).getValor_descuento().toString();
            Filas[7] = lista.get(i).getValor_total().toString();

            dt.addRow(Filas);

            Tabla.setModel(dt);
            Tabla.setRowHeight(50);

            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            //   Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            // Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
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
            Detalle = "";
        }

    }

    public static void ListarProductosConsulta2(List<PrProductos> listaproducto, JTable Tabla) {
        int[] a = {60, 150, 150, 150, 150, 150, 150, 800};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "COD. BARRA", "CATEGORIA", "SUBCATEGORIA", "ARTICULO",
            "PRESENTACION", "MEDIDAS", "PRODUCTO"};
        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
        for (int i = 0; i < listaproducto.size(); i++) {

            if (listaproducto.get(i) != null) {

                filas[0] = "" + listaproducto.get(i).getPrProductosPK().getIdProducto();
                filas[1] = listaproducto.get(i).getCodigoBarra();
                filas[2] = listaproducto.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getPrGrupos().getNombre();
                filas[3] = listaproducto.get(i).getPrMedidas().getPrArticulo().getPrSubgrupos().getNombre();
                filas[4] = listaproducto.get(i).getPrMedidas().getPrArticulo().getNombreArticulo();

                filas[5] = listaproducto.get(i).getPrMedidas().getPrTipoPresentacion().getNombre();
                filas[6] = listaproducto.get(i).getPrMedidas().getPrTipoMedidas().getNombreTipoMedida();
                filas[7] = listaproducto.get(i).getNombreProducto();

                model.addRow(filas);

            }

        }

        Tabla.setModel(model);
        Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
        Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
        Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
        Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
        Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(3).setPreferredWidth(a[3]);
        Tabla.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(4).setPreferredWidth(a[4]);
        Tabla.getColumnModel().getColumn(4).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(5).setPreferredWidth(a[5]);
        Tabla.getColumnModel().getColumn(5).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
        Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr2);
        Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
        Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr2);

    }

    public static void listarEmpaque(List<PrEmpaque> lista, JTable Tabla) {
        int[] a = {30, 30, 40};
        Font fuente = new Font("Bold", Font.BOLD, 12);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"No. ", "NOMBRE", "ESTADO"};
        String[] Filas = new String[3];
        model = new DefaultTableModel(null, Co);
        JTableHeader jt;
        jt = Tabla.getTableHeader();
        jt.setFont(fuente);
        jt.setDefaultRenderer(tcr);
        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            Filas[0] = String.valueOf(lista.get(i).getId());
            Filas[1] = String.valueOf(lista.get(i).getNombreEmpaque());
            Filas[2] = String.valueOf(lista.get(i).getEstado());
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

    public static void ListarProductosInventario(List<JoinProductoVenta> lisProdVen, JTable Tabla, List<InBodega> lisBode) {
        int[] a = {40, 150, 400, 100, /*100,*/ 100/*, 100*/, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "COD. BARRA", "DESCRIPCION", "STOCK",/* "COSTO",*/ "P.VENTA"/*, "DESCUENTO"*/, "BODEGA"};
        String[] filas = new String[6];
//        String[] filas = new String[8];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);
//        ProductoCadena k = new ProductoCadena();
        for (int i = 0; i < lisProdVen.size(); i++) {
            filas[0] = "" + lisProdVen.get(i).getId_prestacion();
            filas[1] = lisProdVen.get(i).getCodigoBarra();
            filas[2] = lisProdVen.get(i).getNombre_producto();
//            filas[2] = ProductoCadena.obtenerCadena(lisProdVen.get(i).getId_producto());
            filas[3] = "" + lisProdVen.get(i).getSaldo_actual();
//            filas[4] = "" + lisProdVen.get(i).getCosto();
            filas[4] = "" + lisProdVen.get(i).getValor_venta();
//            filas[6] = "" + lisProdVen.get(i).getValor_descuento();
            Long id_bodega = lisProdVen.get(i).getId_bodega();

            for (int j = 0; j < lisBode.size(); j++) {
                if (lisBode.get(j).getInBodegaPK().getIdBodega() == id_bodega) {
                    filas[5] = "" + lisBode.get(j).getNombreBodega();
//                    filas[7] = "" + lisBode.get(j).getNombreBodega();
                }
            }
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
//            Tabla.getColumnModel().getColumn(6).setPreferredWidth(a[6]);
//            Tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
//            Tabla.getColumnModel().getColumn(7).setPreferredWidth(a[7]);
//            Tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);

        }

    }

    public static void ListarGrupoConsulta(List<PrGrupos> lista, JTable Tabla) {
        int[] a = {60, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"CODIGO", "CATEGORIA"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdGrupo();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarSubGrupoConsulta(List<PrSubgrupos> lista, JTable Tabla) {
        int[] a = {60, 500};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "SUBCATEGORIA"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getPrSubgruposPK().getIdSubgrupo();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarArticuloConsulta(List<PrArticulo> lista, JTable Tabla) {
        int[] a = {60, 500};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "ARTICULO"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getPrArticuloPK().getIdArticulo();
                filas[1] = lista.get(i).getNombreArticulo();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarTipoMedidaConsulta(List<PrTipoMedidas> lista, JTable Tabla) {
        int[] a = {60, 500};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "TIPO MEDIDAS"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdTipoMedidas();
                filas[1] = lista.get(i).getNombreTipoMedida();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarTipoPresentacionMedidaConsulta(List<PrMedidas> lista, JTable Tabla) {
        int[] a = {400, 300};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"TIPO PRESENTACION", "TIPO MEDIDA"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = lista.get(i).getPrTipoPresentacion().getNombre();
                filas[1] = lista.get(i).getPrTipoMedidas().getNombreTipoMedida();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarTipoPresentacionConsulta(List<PrTipoPresentacion> lista, JTable Tabla) {
        int[] a = {60, 300};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"CODIGO", "TIPO PRESENTACION"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdTipoPresentacion();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarTipoMedidasConsulta(List<PrTipoMedidas> lista, JTable Tabla) {
        int[] a = {60, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "TIPO PRESENTACION"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdTipoMedidas();
                filas[1] = lista.get(i).getNombreTipoMedida();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarProveedorConsulta(List<CoProveedores> lista, JTable Tabla) {
        int[] a = {60, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "PROVEEDORES"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals('A')) {
                filas[0] = "" + lista.get(i).getIdProveedor();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarEmpaqueConsulta(List<PrEmpaque> lista, JTable Tabla) {
        int[] a = {60, 250};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "EMPAQUE"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getId();
                filas[1] = lista.get(i).getNombreEmpaque();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarFabricanteConsulta(List<PrFabricante> lista, JTable Tabla) {
        int[] a = {60, 500};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "FABRICANTE"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdFabricante();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarBodegaConsulta(List<InBodega> lista, JTable Tabla) {
        int[] a = {60, 500};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "BODEGA"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getInBodegaPK().getIdBodega();
                filas[1] = lista.get(i).getNombreBodega();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarTipoIdentificacionConsulta(List<SeTipoIdentificacion> lista, JTable Tabla) {
        int[] a = {60, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"CODIGO", "IDENTIFICACIÓN"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdTipoIdentificacion();
                filas[1] = lista.get(i).getNombreIdentificacion();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarPaisConsulta(List<SePais> lista, JTable Tabla) {
        int[] a = {60, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "PAIS"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdPais();
                filas[1] = lista.get(i).getNombre();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarProvinciaConsulta(List<SeProvincia> lista, JTable Tabla) {
        int[] a = {60, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "Provincia"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).getEstado().equals("A")) {
            filas[0] = "" + lista.get(i).getIdProvincia();
            filas[1] = lista.get(i).getNombre();

            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

//            }
        }

    }

    public static void ListarCiudadConsulta(List<SeCiudad> lista, JTable Tabla) {
        int[] a = {60, 200};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "CIUDAD"};
        String[] filas = new String[2];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

//            if (lista.get(i).getEstado().equals("A")) {
            filas[0] = "" + lista.get(i).getIdCiudad();
            filas[1] = lista.get(i).getNombre();

            model.addRow(filas);
            Tabla.setModel(model);
            Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
            Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
            Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);

//            }
        }

    }

    public static void ListarClientesConsulta(List<SeClientes> lista, JTable Tabla) {
        int[] a = {60, 200, 400,};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"COD.", "N. IDENTIFICACION", "CLIENTE"};
        String[] filas = new String[3];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getEstado().equals("A")) {
                filas[0] = "" + lista.get(i).getIdClientes();
                filas[1] = lista.get(i).getNumeroIdentificacion();
                filas[2] = lista.get(i).getNombreCompleto();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
                Tabla.getColumnModel().getColumn(2).setPreferredWidth(a[2]);
                Tabla.getColumnModel().getColumn(2).setCellRenderer(tcr2);

            }

        }

    }

    public static void ListarProductoBodegaConsulta(List<PrProductoBodega> lista, JTable Tabla) {
        int[] a = {60, 500, 150, 150, 150, 100, 100};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        model = VaciarTabla(Tabla);
        String[] b = {"ID.PROD", "PRODUCTO", "BODEGA ", "STOCK x UNIDAD", "EMPAQUE X UNIDAD", "MIN", "MAX"};
        String[] filas = new String[7];
        model = new DefaultTableModel(null, b);
        Tabla.setShowGrid(true);

        InKardexExt kardexExt = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());

        Pr_ProductoExt productoExt = new Pr_ProductoExt(EntityManagerUtil.ObtenerEntityManager());

        for (int i = 0; i < lista.size(); i++) {

            System.out.println(" entro 1");

            if (lista.get(i).getEstado().equals("A")) {

                System.out.println(" entro 2");

                PrProductos prod = productoExt.obtenerProductoObj(lista.get(i).getPrProductoBodegaPK().getIdProducto());
                System.out.println(" entro 3    " + prod.getPrProductosPK());
//                PrProductos prod = ObtenerPrProductos(lista.get(i).getPrProductoBodegaPK().getIdProducto());

                InKardex kard = kardexExt.obtenerUltimoProductoKardex(lista.get(i).getPrProductoBodegaPK().getIdProducto());

                if (prod != null) {

                    filas[0] = "" + prod.getPrProductosPK().getIdProducto();
                    filas[1] = prod.getNombreProducto();

                } else {
                    filas[0] = "null";
                    filas[1] = "null";
                }

                filas[2] = lista.get(i).getInBodega().getNombreBodega();

                if (kard != null) {
                    System.out.println(" entro 4    " + kard.getInKardexPK());
                    filas[3] = kard.getSaldoActual().toString();
                } else {
                    filas[3] = "0";
                }

                filas[4] = String.format("%.0f", prod.getUnidadEmpaqueCompra()) + " " + prod.getMedidaEmpaqueCompra().getNombreEmpaque() + " / " + String.format("%.0f", prod.getCantidadPorEmpaqueCompra()) + " " + prod.getMedidaPorEmpaqueCompra().getNombreEmpaque();

                filas[5] = lista.get(i).getStockMinimo().toString();
                filas[6] = lista.get(i).getStockMaximo().toString();

                model.addRow(filas);
                Tabla.setModel(model);
                Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                Tabla.getColumnModel().getColumn(0).setPreferredWidth(a[0]);
                Tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
                Tabla.getColumnModel().getColumn(1).setPreferredWidth(a[1]);
                Tabla.getColumnModel().getColumn(1).setCellRenderer(tcr2);
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

    }

    public static void listarVeDetalleCaja(List<VeDetalleCaja> lista, JTable Tabla) {
        int[] a = {100, 100, 50, 50, 10, 50};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.RIGHT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"CAJA", "USUARIO", "APERTURA", "CIERRE", "TOTAL FACTURADO", "ESTADO"};
        String[] Filas = new String[6];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            VeCaja caja = ObtenerDTO.ObtenerVeCaja(lista.get(i).getVeDetalleCajaPK().getIdCaja());
            SeUsuarios usu = ObtenerDTO.ObtenerUsuarios(lista.get(i).getIdUsuario().longValue());
            Filas[0] = caja.getNombre();
            Filas[1] = usu.getUsuario();
            Filas[2] = lista.get(i).getDineroInicio().toString();
            if (lista.get(i).getDineroCierre() == null) {
                Filas[3] = "NO CERRADO";
                Filas[4] = "";
                Filas[5] = "";
            } else {
                Filas[3] = lista.get(i).getDineroCierre().toString();
                Double facturado = ValidacionCaja.facturadoRetorno(lista.get(i));
                Double total = facturado + lista.get(i).getDineroInicio();

                Filas[4] = facturado.toString();

                if (lista.get(i).getDineroCierre() < total) {
                    Filas[5] = "PERDIDA";
                }
                if (lista.get(i).getDineroCierre() > total) {
                    Filas[5] = "SOBRANTE";
                }
                if (lista.get(i).getDineroCierre().equals(total)) {
                    Filas[5] = "CORRECTO";
                }
            }

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

    public static void listarUsuariosEscoger(List<SeUsuarios> lista, JTable Tabla, SeSucursal suc, List<VeCaja> listaCaja) {
        int[] a = {5, 5, 30, 5};
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr1.setHorizontalAlignment(SwingConstants.LEFT);
        model = Tablas.VaciarTabla(Tabla);
        String[] Co = {"ID", "USUARIO", "CORREO", "ROL"};
        String[] Filas = new String[4];
        model = new DefaultTableModel(null, Co);

        Tabla.setShowGrid(true);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("id-U " + lista.get(i).getIdUsuario());
            for (int j = 0; j < listaCaja.size(); j++) {
//                if (listaCaja.get(j).getIdUsuario().longValue() > 1) {
//                    if(lista.get(i).getIdUsuario()!=listaCaja.get(j).getIdUsuario().longValue()){
                System.out.println("iddesdecaja " + listaCaja.get(j).getIdUsuario());
//                }
            }
            if ((lista.get(i).getEstado().equals("A")
                    && lista.get(i).getSeUsuarioSucurRolList().get(0).getSeSucursal().equals(suc))) {

                Filas[0] = "" + lista.get(i).getIdUsuario();
                Filas[1] = lista.get(i).getUsuario();
                Filas[2] = lista.get(i).getIdPersona().getCorreo();
                Filas[3] = lista.get(i).getSeUsuarioSucurRolList().get(0).getIdRoles().getNombre();

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

}

///// ---------es otra clases //la clase tabla esta arriba  -------------------
class MyComboBoxEditor extends DefaultCellEditor {

    public MyComboBoxEditor(String[] items) {
        super(new JComboBox(items));
    }
}
