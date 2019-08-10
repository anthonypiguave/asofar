/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.compras;

import ec.com.asofar.dao.CoDetalleOrdenCompraJpaController;
import ec.com.asofar.dao.CoDetalleOrdenPedidoJpaController;
import ec.com.asofar.dao.CoOrdenComprasJpaController;
import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.InDetalleMovimientoJpaController;
import ec.com.asofar.dao.InMotivosJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.daoext.MovimientosDaoExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.OrdenCompraDaoExt;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoDetalleOrdenCompraPK;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InDetalleMovimientoPK;
import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InMovimientosPK;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.Calendario;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin1
 */
public class crearOrdenCompraForm extends javax.swing.JDialog {

    int x, y;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;

    Boolean ivaBoolean;
    Boolean[] check;

    BigDecimal TotalSubTotal = new BigDecimal("0.00");
    BigDecimal TotalIva = new BigDecimal("0.00");
    BigDecimal TotalDescuento = new BigDecimal("0.00");
    BigDecimal TotalCompra = new BigDecimal("0.00");

    Date fecha = null;

    Date d = new Date();

    CoProveedoresJpaController proveedorController = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoDocumentoJpaController documentoController = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoMovimientoJpaController movimientoController = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InMotivosJpaController motivoController = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoDetalleOrdenPedidoJpaController detOrdenPedidocontroller = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());
    OrdenCompraDaoExt obtenerIdCompra = new OrdenCompraDaoExt(EntityManagerUtil.ObtenerEntityManager());
    MovimientosDaoExt obtenerIdMovimiento = new MovimientosDaoExt(EntityManagerUtil.ObtenerEntityManager());

    CoOrdenPedido cOrden;

    List<CoDetalleOrdenPedido> listadet = new ArrayList<CoDetalleOrdenPedido>();
    List<CoDetalleOrdenCompra> listadetCompra = new ArrayList<CoDetalleOrdenCompra>();

    PrProductos objetopro = new PrProductos();

    public crearOrdenCompraForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtFecha.setText(FechaActual());
        CargarProveedor();
        CargarDocumento();
        CargarFormulario();
        CargarMovimiento();
        CargarMotivos();

        Timer tiempo = new Timer(100, new crearOrdenCompraForm.horas());
        tiempo.start();

    }

    public crearOrdenCompraForm(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, CoOrdenPedido objeto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Timer tiempo = new Timer(100, new crearOrdenCompraForm.horas());
        tiempo.start();
        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;
        cOrden = objeto;
        txtFecha.setText(FechaActual());
        CargarProveedor();
        CargarDocumento();
        CargarFormulario();
        CargarMovimiento();
        CargarMotivos();
        this.cbxProveedor.setEnabled(false);

        System.out.println(" " + seUsuario);

    }

    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            java.util.Date sistHora = new java.util.Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy = Calendar.getInstance();
            txtHora.setText(String.format(format.format(sistHora), hoy));

        }
    }

    public static String FechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatoFecha.format(fecha);
    }

    public void CargarProveedor() {
        List<CoProveedores> listcaja = proveedorController.findCoProveedoresEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbxProveedor.addItem(listcaja.get(i).getNombre());
        }
    }

    public void CargarDocumento() {
        List<InTipoDocumento> listcaja = documentoController.findInTipoDocumentoEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbx_documento.addItem(listcaja.get(i).getNombreDocumento());
        }
    }

    public void CargarMovimiento() {
        List<InTipoMovimiento> listcaja = movimientoController.findInTipoMovimientoEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbx_movimiento.addItem(listcaja.get(i).getNombreMovimiento());
        }
    }

    public void CargarMotivos() {
        List<InMotivos> listcaja = motivoController.findInMotivosEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbx_motivo.addItem(listcaja.get(i).getNombre());
        }
    }

    public void CargarFormulario() {

        cbxProveedor.setSelectedIndex(cOrden.getIdProveedor().intValue());
        cbx_documento.setSelectedIndex(cOrden.getIdDocumento().intValue());

        List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();

        list = detOrdenPedidocontroller.findCoDetalleOrdenPedidoEntities();

        check = new Boolean[list.size()];  // inicializar el Boolean segun la lista

        for (int i = 0; i < list.size(); i++) {

            check[i] = false; // setear valor falso
            System.out.println(" prueba  " + list.get(i).getDescripcion());
            if (list.get(i).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido()) && list.get(i).getEstado().equals("A")) {
                listadet.add(list.get(i));

            }

        }
        detalleCompra();

    }

    public void detalleCompra() {

        for (int i = 0; i < listadet.size(); i++) {

            CoDetalleOrdenCompra detalle = new CoDetalleOrdenCompra();

            detalle.setCoDetalleOrdenCompraPK(new CoDetalleOrdenCompraPK());

            detalle.getCoDetalleOrdenCompraPK().setLineaDetalle(i + 1);
            detalle.getCoDetalleOrdenCompraPK().setIdProducto(listadet.get(i).getCoDetalleOrdenPedidoPK().getIdProducto());
            detalle.setDescripcion(listadet.get(i).getDescripcion());
            detalle.setPrecioUnitario(BigDecimal.valueOf(0.00));
            detalle.setCantidadRecibida(listadet.get(i).getCantidadSolicitada());
            detalle.setSubtotal(BigDecimal.valueOf(0.00));
            detalle.setIva(BigDecimal.valueOf(0.00));
            detalle.setIce(BigDecimal.valueOf(0.00));
            detalle.setDescuento(BigDecimal.valueOf(0.00));
            detalle.setTotal(BigDecimal.valueOf(0.00));

            listadetCompra.add(detalle);
        }
        Tablas.listarDetalleCompra(listadetCompra, jTable1);

    }

    public void calcularTotales() {

        BigDecimal iva = new BigDecimal("0.12");////iva quemado

        BigDecimal TotalSubConIva = new BigDecimal("0.00");
        BigDecimal TotalSubSinIva = new BigDecimal("0.00");
        BigDecimal Total = new BigDecimal("0.00");

        TotalSubTotal = new BigDecimal("0.00");
        TotalIva = new BigDecimal("0.00");
        TotalDescuento = new BigDecimal("0.00");
        TotalCompra = new BigDecimal("0.00");

        for (int i = 0; i < listadetCompra.size(); i++) {

            BigInteger Cant = listadetCompra.get(i).getCantidadRecibida();
            BigDecimal Cantidad = new BigDecimal(Cant);
            System.out.println("cantidad " + Cantidad);
            BigDecimal Precio = listadetCompra.get(i).getPrecioUnitario();
            System.out.println("Precio " + Precio);

            BigDecimal Subtotal = Cantidad.multiply(Precio);
            System.out.println("Subtotal " + Subtotal);
            listadetCompra.get(i).setSubtotal(Subtotal);

            BigDecimal Descuento = listadetCompra.get(i).getDescuento();
            System.out.println("descuento  " + Descuento);
            TotalDescuento = TotalDescuento.add(Descuento);
            listadetCompra.get(i).setDescuento(Descuento);

            if (check[i]) {

                TotalIva = TotalIva.add(Subtotal.multiply(iva));
                System.out.println("total iva " + TotalIva);

                listadetCompra.get(i).setIva(Subtotal.multiply(iva));

                TotalSubConIva = TotalSubConIva.add(Subtotal);
                System.out.println("subtotal con iva " + TotalSubConIva);

                Total = Subtotal.add(Subtotal.multiply(iva)).subtract(Descuento);

                listadetCompra.get(i).setTotal(Total);

            } else {
                TotalSubSinIva = TotalSubSinIva.add(Subtotal);
                System.out.println("TotalSub SinIva " + TotalSubSinIva);
                listadetCompra.get(i).setIva(BigDecimal.valueOf(0.00));

                Total = Subtotal.subtract(Descuento);

                listadetCompra.get(i).setTotal(Total);

            }

            TotalCompra = TotalCompra.add(Total);

        }

        TotalSubTotal = TotalSubConIva.add(TotalSubSinIva);
        System.out.println("total totalsubtotal " + TotalSubTotal);
        System.out.println("total iva " + TotalIva);
        System.out.println("total descuento " + TotalDescuento);
        System.out.println("total compra " + TotalCompra);

        txtSubtotal.setText(TotalSubTotal.toEngineeringString());
        txtDescuento.setText(TotalDescuento.toEngineeringString());
        txtIva.setText(TotalIva.toEngineeringString());
        txtTotal.setText(TotalCompra.toEngineeringString());

        Tablas.listarDetalleCompra(listadetCompra, jTable1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbxProveedor = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_documento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFechaEntrega = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbx_motivo = new javax.swing.JComboBox<>();
        cbx_movimiento = new javax.swing.JComboBox<>();
        BtnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtnAprovar = new javax.swing.JButton();
        BtnAnular = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtIva = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ORDEN DE COMPRA");
        jLabel1.setOpaque(true);
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("PROVEEDOR:");

        cbxProveedor.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("FECHA EMISION:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("DOCUMENTO:");

        cbx_documento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbx_documento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("CODIGO DE ORDEN:");

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("OBSERVACION:");

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("HORA EMISION:");

        txtHora.setEditable(false);
        txtHora.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("FECHA DE ENTEGA:");

        txtFechaEntrega.setEditable(false);
        txtFechaEntrega.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtFechaEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrega.setText("--SELECCIONE--");
        txtFechaEntrega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFechaEntregaMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel18.setText("MOTIVO:");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel19.setText("MOVIMIENTO:");

        cbx_motivo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbx_motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        cbx_movimiento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbx_movimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(46, 46, 46))
                                    .addComponent(jLabel12))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(46, 46, 46)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_movimiento, 0, 169, Short.MAX_VALUE)
                            .addComponent(cbxProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_documento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_motivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(25, 25, 25)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnCancelar.setBackground(new java.awt.Color(153, 0, 0));
        BtnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        BtnAprovar.setBackground(new java.awt.Color(13, 153, 0));
        BtnAprovar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnAprovar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAprovar.setText("APROVAR");
        BtnAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAprovarActionPerformed(evt);
            }
        });

        BtnAnular.setBackground(new java.awt.Color(255, 145, 0));
        BtnAnular.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnAnular.setForeground(new java.awt.Color(255, 255, 255));
        BtnAnular.setText("ANULAR");
        BtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnularActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DETALLE DE COMPRA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("SUBTOTAL: ");

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel15.setText("IVA  : ");

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel16.setText("TOTAL: ");

        txtSubtotal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtSubtotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTotal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtIva.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtIva.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel17.setText("DESCUENTO:");

        txtDescuento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtDescuento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Desea Regresar?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            setVisible(false);

        } else {

        }
    }//GEN-LAST:event_BtnCancelarActionPerformed


    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        char car = evt.getKeyChar();
        if (car < '0' || car > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        try {

            int i = jTable1.getSelectedRow();

            int col = jTable1.getSelectedColumn();

            if (col == 4) {
                String valor = (String) jTable1.getValueAt(i, 4);

                BigDecimal valor1 = new BigDecimal(valor);

                for (int j = 0; j < listadetCompra.size(); j++) {

                    if (j == i) {

                        listadetCompra.get(j).setPrecioUnitario(valor1);
                    }

                }
                calcularTotales();

            }

            if (col == 7) {
                String valor = (String) jTable1.getValueAt(i, 7);

                BigDecimal valor1 = new BigDecimal(valor);

                for (int j = 0; j < listadetCompra.size(); j++) {

                    if (j == i) {

                        listadetCompra.get(j).setDescuento(valor1);
                    }

                }
                calcularTotales();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable1KeyReleased

    private void BtnAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAprovarActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de guardar los datos?", "", JOptionPane.YES_NO_OPTION);

        CoProveedores proveedor = ObtenerDTO.ObtenerProveedorPedido(cbxProveedor.getSelectedItem().toString());
        InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido(cbx_documento.getSelectedItem().toString());
        InTipoMovimiento tipoMovimiento = ObtenerDTO.ObtenerInTipoMovimiento(cbx_movimiento.getSelectedItem().toString());
        InMotivos tipoMotivos = ObtenerDTO.ObtenerInMotivos(cbx_motivo.getSelectedItem().toString());

        CoOrdenCompras pkCompra = null;
        InMovimientos pkMovimiento = null;

        if (r == JOptionPane.YES_OPTION) {
            if ("".equals(cbxProveedor.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS!");
            } else {

                /////////// AGREGAR AL ORDEN DE COMPRA
                CoOrdenCompras cabOrden = new CoOrdenCompras();
                CoDetalleOrdenCompra detOrden = new CoDetalleOrdenCompra();
                CoOrdenComprasJpaController cabOrdenController = new CoOrdenComprasJpaController(EntityManagerUtil.ObtenerEntityManager());
                CoDetalleOrdenCompraJpaController detOrdenController = new CoDetalleOrdenCompraJpaController(EntityManagerUtil.ObtenerEntityManager());

                ///////setear la pk cab orden compra
                cabOrden.setSeSucursal(seSucursal);
                ///////setear la  cab orden compra
                cabOrden.setIdProveedor(BigInteger.valueOf(proveedor.getIdProveedor()));
                cabOrden.setObservacion(txtObservacion.getText());
                cabOrden.setIdTipoDocumento(BigInteger.valueOf(tipoDocumento.getIdTipoDocumento()));
                cabOrden.setEstado("A");
                cabOrden.setFechaEntrega(fecha);

                cabOrden.setUsuarioCreacion(seUsuario.getIdUsuario());
                cabOrden.setFechaCreacion(d);

                cabOrden.setTotalSubtotal(TotalSubTotal);
                cabOrden.setTotalIva(TotalIva);
                cabOrden.setTotalIce(BigDecimal.valueOf(0));
                cabOrden.setTotalDescuento(TotalDescuento);
                cabOrden.setTotalCompra(TotalCompra);

                try {

                    pkCompra = obtenerIdCompra.guardarPedido(cabOrden);
                    System.out.println(" IDcabedcera compra " + pkCompra);

                    for (int i = 0; i < listadetCompra.size(); i++) {
                        ////setear el pk detalle con compras
                        detOrden.setCoOrdenCompras(pkCompra);
                        /////// setear el detalle 
                        detOrden.setCoDetalleOrdenCompraPK(new CoDetalleOrdenCompraPK());
                        detOrden.getCoDetalleOrdenCompraPK().setIdProducto(listadetCompra.get(i).getCoDetalleOrdenCompraPK().getIdProducto());
                        detOrden.getCoDetalleOrdenCompraPK().setLineaDetalle(listadetCompra.get(i).getCoDetalleOrdenCompraPK().getLineaDetalle());
                        detOrden.setDescripcion(listadetCompra.get(i).getDescripcion());

                        detOrden.setCantidadRecibida(listadetCompra.get(i).getCantidadRecibida());
                        detOrden.setEstado("A");

                        detOrden.setPrecioUnitario(listadetCompra.get(i).getPrecioUnitario());
                        detOrden.setIce(BigDecimal.valueOf(0));
                        detOrden.setIva(listadetCompra.get(i).getIva());
                        detOrden.setDescuento(listadetCompra.get(i).getDescuento());
                        detOrden.setSubtotal(listadetCompra.get(i).getSubtotal());
                        detOrden.setTotal(listadetCompra.get(i).getTotal());

                        detOrden.setUsuarioCreacion(seUsuario.getIdUsuario());
                        detOrden.setFechaCreacion(d);

                        detOrdenController.create(detOrden);
                    }
                    /////////// actualizar la cab de la orden de pedido
                    CoOrdenPedidoJpaController cab = new CoOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

                    cOrden.setEstado("I");
                    cOrden.setUsuarioActualizacion(seUsuario.getIdUsuario());
                    cOrden.setFechaActualizacion(d);

                    cab.edit(cOrden);

                } catch (Exception e) {

                    e.printStackTrace();

                }

                /////////// AGREGAR AL MOVIMIENTO
                InMovimientosJpaController cabMovController = new InMovimientosJpaController(EntityManagerUtil.ObtenerEntityManager());
                InDetalleMovimientoJpaController detMovController = new InDetalleMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
                InMovimientos cabMovimiento = new InMovimientos();
                InDetalleMovimiento detMovimiento = new InDetalleMovimiento();

                try {

                    ////////////setear para la pk cab movimientos
                    cabMovimiento.setSeSucursal(seSucursal);
                    cabMovimiento.setInTipoDocumento(tipoDocumento);
                    cabMovimiento.setInTipoMovimiento(tipoMovimiento);
                    cabMovimiento.setInMotivos(tipoMotivos);

                    ///////////setear cab movimientos
                    cabMovimiento.setFechaSistema(d);
                    cabMovimiento.setAnioDocumento(fecha);
                    cabMovimiento.setIdProveedor(proveedor);
                    cabMovimiento.setIdOrdenCompra(BigInteger.valueOf(pkCompra.getCoOrdenComprasPK().getIdOrdenCompra()));
                    cabMovimiento.setEstado("P");

                    cabMovimiento.setUsuarioCreacion(seUsuario.getIdUsuario());
                    cabMovimiento.setFechaCreacion(d);

                    pkMovimiento = obtenerIdMovimiento.guardarPedido(cabMovimiento);
                    System.out.println(" IDcabedcera movimiento" + pkMovimiento);

                    for (int i = 0; i < listadetCompra.size(); i++) {
                        ////////////setear para la pk detalle con cab movimiento
                        detMovimiento.setInMovimientos(pkMovimiento);
                        ///////////setear detalle movimientos
                        detMovimiento.setInDetalleMovimientoPK(new InDetalleMovimientoPK()); // inicializar pk
                        detMovimiento.getInDetalleMovimientoPK().setLineaDetalle(listadetCompra.get(i).getCoDetalleOrdenCompraPK().getLineaDetalle());
                        detMovimiento.getInDetalleMovimientoPK().setIdProducto(listadetCompra.get(i).getCoDetalleOrdenCompraPK().getIdProducto());

                        detMovimiento.setDescripcion(listadetCompra.get(i).getDescripcion());
                        detMovimiento.setCantidad(listadetCompra.get(i).getCantidadRecibida());
                        detMovimiento.setPrecioUnitario(listadetCompra.get(i).getPrecioUnitario());
                        detMovimiento.setEstado("A");

                        detMovimiento.setUsuarioCreacion(seUsuario.getIdUsuario());
                        detMovimiento.setFechaCreacion(d);

                        detMovController.create(detMovimiento);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                setVisible(false);
            }
        }
    }//GEN-LAST:event_BtnAprovarActionPerformed

    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed


    }//GEN-LAST:event_BtnAnularActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());

        if (evt.getClickCount() == 1) {
            if (col == 6) {
                JCheckBox chbox = (JCheckBox) jTable1.getValueAt(row, col);

                boolean selected = chbox.isSelected();

                if (selected) {

                    chbox.setSelected(false);

                    ivaBoolean = false;
                    check[row] = ivaBoolean;
                    calcularTotales();

                } else {

                    chbox.setSelected(true);

                    ivaBoolean = true;
                    check[row] = ivaBoolean;
                    calcularTotales();
                }

            }
        }


    }//GEN-LAST:event_jTable1MousePressed

    private void txtFechaEntregaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaEntregaMousePressed

        Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
        fechaEntrega.setVisible(true);

        fecha = fechaEntrega.getFecha();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        txtFechaEntrega.setText(String.format(formatoFecha.format(fecha)));


    }//GEN-LAST:event_txtFechaEntregaMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(crearOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crearOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crearOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crearOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                crearOrdenCompraForm dialog = new crearOrdenCompraForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnular;
    private javax.swing.JButton BtnAprovar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JComboBox<String> cbx_documento;
    private javax.swing.JComboBox<String> cbx_motivo;
    private javax.swing.JComboBox<String> cbx_movimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCod;
    private javax.swing.JLabel txtDescuento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtHora;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JLabel txtSubtotal;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
