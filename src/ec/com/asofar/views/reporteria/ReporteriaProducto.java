package ec.com.asofar.views.reporteria;

import ec.com.asofar.dao.InBodegaJpaController;
import ec.com.asofar.views.venta.*;
import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.PrDetalleTarifarioJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.JoinProductoVenta;
import ec.com.asofar.daoext.JoinProductoVentaExt;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.util.ClaseReporte;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Formato_Numeros;
import ec.com.asofar.util.Tablas;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Usuario
 */
public class ReporteriaProducto extends javax.swing.JDialog {

    /**
     * Creates new form ConsultaProductoVenta
     */
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int x, y;
    String valor;
    List<InKardex> listaKardex;
    List<PrProductos> listaProd;
    PrProductos objProd = new PrProductos();
    List<PrDetalleTarifario> listaDetaTari;
    List<PrPrestaciones> listaPresta;
    InKardexJpaController Kc = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductosJpaController Pc = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrDetalleTarifarioJpaController Dtc = new PrDetalleTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrPrestacionesJpaController Prestc = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductos objetoProducto = new PrProductos();
    PrPrestaciones objPrest = new PrPrestaciones();
    PrDetalleTarifario objDetalTa = new PrDetalleTarifario();
    VeFacturaDetalle objFacDet = new VeFacturaDetalle();
    Long id_pre, id_bodega;
    String nombre;
    BigInteger id_prod, id_Unidad;
    Double descuento;
    String iva;
    Double precio;
    List<JoinProductoVenta> ListProdVent = null;
    JoinProductoVentaExt selectProdVent = new JoinProductoVentaExt();
    JoinProductoVenta objJoinProVen = new JoinProductoVenta();
    List<JoinProductoVenta> listaProVent;
    Double VGTtotal;
    List<InBodega> LisBod;
    String Buscar = "";
    InBodegaJpaController BC = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());


    /**/
    public ReporteriaProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal = false);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        cargarTbaProductoInventario();
        sumarCantidad();
        sumarTotalPvp();
//        sumarTotalCosto();
//        Totalizar();
//        TotalizarCantidad();
    }

    public ReporteriaProducto(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal = false);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        cargarTbaProductoInventario();
        sumarCantidad();
//        sumarTotalCosto();
        sumarTotalPvp();
//        Totalizar();
//        TotalizarCantidad();

    }

    public void cargarTbaProductoInventario() {
        ListProdVent = selectProdVent.listarProductoInventario();
        LisBod = BC.findInBodegaEntities();
        Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        Chooser2 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_productos = new javax.swing.JTable();
        btnsalir = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Txt_Cantidad = new javax.swing.JTextField();
        PanelSec1 = new javax.swing.JPanel();
        cbxFiltro = new javax.swing.JComboBox<>();
        btnBuscar1 = new javax.swing.JButton();
        Txt_buscar = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("ENTRE");

        Chooser2.setDateFormatString("yyyy/MM/dd");
        Chooser2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        Chooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Chooser2KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STOCK");
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

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tba_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tba_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_productosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tba_productos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        );

        btnsalir.setBackground(new java.awt.Color(254, 254, 254));
        btnsalir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(1, 1, 1));
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        txt_total.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("PVP : ");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("CANTIDAD : ");

        Txt_Cantidad.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        PanelSec1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        cbxFiltro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODO", "CODIGO BARRA", "NOMBRE", "BODEGA" }));

        btnBuscar1.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btnBuscar1.setText("BUSCAR");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        Txt_buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_buscarActionPerformed(evt);
            }
        });
        Txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Txt_buscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Txt_buscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PanelSec1Layout = new javax.swing.GroupLayout(PanelSec1);
        PanelSec1.setLayout(PanelSec1Layout);
        PanelSec1Layout.setHorizontalGroup(
            PanelSec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSec1Layout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        PanelSec1Layout.setVerticalGroup(
            PanelSec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSec1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSec1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnimprimir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnimprimir.setForeground(new java.awt.Color(1, 1, 1));
        btnimprimir.setText("IMPRIMIR");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSec1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelSec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(Txt_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    public PrDetalleTarifario devuelveObjeto3(Double valor, List<PrDetalleTarifario> listabod) {
        PrDetalleTarifario doc = null;
        for (int i = 0; i < listabod.size(); i++) {

            if (Objects.equals(listabod.get(i).getValorDescuento(), valor)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void tba_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_productosMousePressed
        int id = 0;
        if (evt.getClickCount() == 2) {
            id = tba_productos.getSelectedRow();
            listaProVent = selectProdVent.listarProductoVenta();
            objJoinProVen = devuelveObjeto5(Long.valueOf(tba_productos.getValueAt(id, 0).toString()), listaProVent);
            if (objJoinProVen != null) {
                objJoinProVen.setNombre_producto(tba_productos.getValueAt(id, 2).toString());
                setVisible(false);
            }
        }
    }//GEN-LAST:event_tba_productosMousePressed
    public JoinProductoVenta devuelveObjeto5(Long id, List<JoinProductoVenta> listaPrVen) {
        JoinProductoVenta doc = null;
        for (int i = 0; i < listaPrVen.size(); i++) {
            if (Objects.equals(listaPrVen.get(i).getId_prestacion(), id)) {
                doc = listaPrVen.get(i);
                break;
            }
        }
        return doc;
    }

    public JoinProductoVenta obtObjProdVent() {
//JoinProductoVenta p = new JoinProductoVenta();
//p.setNombre_producto(nombre);
        return objJoinProVen;
    }

    public VeFacturaDetalle getFac() {
        VeFacturaDetalle objFac = new VeFacturaDetalle();
        objFac.setValorDescuento(descuento);
        objFac.setPrecioUnitarioVenta(precio);
        return objFac;
    }

    public PrDetalleTarifario getUnid() {
        PrDetalleTarifario objDetTar = new PrDetalleTarifario();
        objDetTar.setIdUnidadServicio(id_Unidad);
        return objDetTar;
    }

    public PrPrestaciones getPresta() {
        PrPrestaciones objPre = new PrPrestaciones();
        objPre.setIdPrestacion(id_pre);
        objPre.setNombrePrestacion(nombre);
        objPre.setIdPoducto(id_prod);
        objPre.setAplicaIva(iva);
        return objPre;
    }

    public Long getPre(Long id_pres, String nombreT, BigInteger id_prodc) {
        id_pre = id_pres;
        nombre = nombreT;
        id_prod = id_prodc;
        return id_pre;

    }

    public BigInteger getUni(BigInteger id_uni) {
        id_Unidad = id_uni;
        return id_Unidad;
    }
    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed
    public PrPrestaciones devuelveObjeto(Long id, List<PrPrestaciones> listabod) {
        PrPrestaciones doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdPrestacion(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }

    public PrProductos devuelveObjeto2(Long id, List<PrProductos> listabod) {
        PrProductos doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getPrProductosPK().getIdProducto(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void Chooser2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Chooser2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("sss");
        }
    }//GEN-LAST:event_Chooser2KeyPressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscar1ActionPerformed
    public void Buscar() {
        String query = "";
        String txt = Txt_buscar.getText();
        String pos = cbxFiltro.getSelectedItem().toString();
        int index = cbxFiltro.getSelectedIndex();

        if (pos == "TODO") {
            ListProdVent = selectProdVent.listarProductoInventario();
            LisBod = BC.findInBodegaEntities();
            Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
            sumarCantidad();
//            sumarTotalCosto();
            sumarTotalPvp();
        }
        if (pos == "NOMBRE") {
            ListProdVent = selectProdVent.listarProductoInventarioFiltroNombre(txt);
            LisBod = BC.findInBodegaEntities();
            Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
            sumarCantidad();
//            sumarTotalCosto();
            sumarTotalPvp();
        }
        if (pos == "CODIGO BARRA") {
            ListProdVent = selectProdVent.listarProductoInventarioFiltroCodigoBarra(txt);
            LisBod = BC.findInBodegaEntities();
            Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
            sumarCantidad();
//            sumarTotalCosto();
            sumarTotalPvp();
        }
        if (index == 3) {
            ListProdVent = selectProdVent.listarProductoInventarioFiltroBodega(txt);
            LisBod = BC.findInBodegaEntities();
            Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
            sumarCantidad();
//            sumarTotalCosto();
            sumarTotalPvp();
        }
    }

    public void sumarCantidad() {
        Integer t = 0;
        Integer p = 0;
        if (tba_productos.getRowCount() > 0) {
            for (int i = 0; i < tba_productos.getRowCount(); i++) {
                p = Integer.parseInt(tba_productos.getValueAt(i, 3).toString());
                t += p;
//                System.out.println("sum " + t);
            }
        }
        Txt_Cantidad.setText(t.toString());
    }

//    public void sumarTotalCosto() {
//        Double t = 0.0;
//        Double p = 0.0;
//        if (tba_productos.getRowCount() > 0) {
//            for (int i = 0; i < tba_productos.getRowCount(); i++) {
//                p = Double.parseDouble(tba_productos.getValueAt(i, 4).toString());
//                t += p;
////                System.out.println("sum " + t);
//            }
//        }
//        txt_Costo.setText("$ " + t.toString());
//    }

    public void sumarTotalPvp() {
        Double t = 0.0;
        Double p = 0.0;
        if (tba_productos.getRowCount() > 0) {
            for (int i = 0; i < tba_productos.getRowCount(); i++) {
                p = Double.parseDouble(tba_productos.getValueAt(i, 4).toString());
                t += p;
//                System.out.println("sum " + t);
            }
        }
        txt_total.setText("$ " + t.toString());
    }
    private void Txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_buscarActionPerformed

    }//GEN-LAST:event_Txt_buscarActionPerformed


    private void Txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Buscar();
        }
    }//GEN-LAST:event_Txt_buscarKeyPressed

    private void Txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_buscarKeyReleased
        //        String buscar = filtro.getText();
        //        Tablas.filtro(buscar, t_Nota_faltantes);
    }//GEN-LAST:event_Txt_buscarKeyReleased

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        ArrayList lista = new ArrayList();
        for (int i = 0; i < tba_productos.getRowCount(); i++) {
            ClaseReporte clase = new ClaseReporte(tba_productos.getValueAt(i, 0).toString(),
                                                  tba_productos.getValueAt(i, 1).toString(),
                                                  tba_productos.getValueAt(i, 2).toString(), 
                                                  tba_productos.getValueAt(i, 3).toString(), 
                                                  tba_productos.getValueAt(i, 4).toString(), 
                                                  tba_productos.getValueAt(i, 5).toString(),                                                   
                                                  Txt_Cantidad.getText(), 
                                                  txt_total.getText());
            lista.add(clase);
        }
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(System.getProperty("user.dir") + "/Reportes/ReporteriaProducto.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JRViewer viewer = new JRViewer(jprint);
            JDialog ventana = new JDialog();
            ventana.add(viewer);
            ventana.setSize(new Dimension(ancho / 2, alto / 2));
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            viewer.setFitWidthZoomRatio();
        } catch (JRException ex) {
            Logger.getLogger(ReporteriaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteriaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteriaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteriaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteriaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteriaProducto dialog = new ReporteriaProducto(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser Chooser2;
    private javax.swing.JPanel PanelSec1;
    private javax.swing.JTextField Txt_Cantidad;
    private javax.swing.JTextField Txt_buscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tba_productos;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
