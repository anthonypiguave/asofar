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
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Formato_Numeros;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ReporteriaProducto extends javax.swing.JDialog {

    /**
     * Creates new form ConsultaProductoVenta
     */
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
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        cargarTbaProductoVenta();
        Totalizar();
        TotalizarCantidad();
    }

    public ReporteriaProducto(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        cargarTbaProductoVenta();
        Totalizar();
        TotalizarCantidad();
    }

    private void Totalizar() {
        Double t = 0.0;
        Double p = 0.0;
        if (tba_productos.getRowCount() > 0) {
            for (int i = 0; i < tba_productos.getRowCount(); i++) {
                p = ListProdVent.get(i).getValor_venta();
                t += p;
//                Formato_Numeros.formatoNumero(t.toString());
                VGTtotal = t;
                txt_total.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtTotal.setText(t.toString()); Txt_Cantidad
            }
        }
    }

    private void TotalizarCantidad() {
        Double t = 0.0;
        Integer p = 0;
        if (tba_productos.getRowCount() > 0) {
            for (int i = 0; i < tba_productos.getRowCount(); i++) {
                p = ListProdVent.get(i).getSaldo_actual();
                t += p;
//                Formato_Numeros.formatoNumero(t.toString());
                VGTtotal = t;
                Txt_Cantidad.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtTotal.setText(t.toString()); Txt_Cantidad
            }
        }
    }

    public void cargarTbaProductoVenta() {
        ListProdVent = selectProdVent.listarProductoInventario();
        LisBod = BC.findInBodegaEntities();
        Tablas.ListarProductosInventario(ListProdVent, tba_productos, LisBod);
    }

//    public void cargarTbaProduc() {
//        listaKardex = Kc.findInKardexEntities();
//        listaProd = Pc.findPrProductosEntities();
//        listaDetaTari = Dtc.findPrDetalleTarifarioEntities();
//        listaPresta = Prestc.findPrPrestacionesEntities();
//        Tablas.ListarProductosVenta(listaPresta, listaDetaTari, listaKardex, listaProd, tba_productos);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        Chooser2 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_productos = new javax.swing.JTable();
        btnsalir = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Txt_Cantidad = new javax.swing.JTextField();
        txtbusqueda = new javax.swing.JTextField();

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

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
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

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR:");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnsalir.setBackground(new java.awt.Color(153, 0, 0));
        btnsalir.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 1.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel3.setText("TOTAL :");

        jLabel4.setText("CANTIDAD : ");

        txtbusqueda.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txt_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(Txt_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        Buscar = txtbusqueda.getText();
        Tablas.filtro(Buscar, tba_productos);
        Totalizar();
        TotalizarCantidad();
    }//GEN-LAST:event_txtbusquedaKeyReleased

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
    private javax.swing.JTextField Txt_Cantidad;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tba_productos;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}
