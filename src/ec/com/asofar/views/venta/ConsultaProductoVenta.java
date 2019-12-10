package ec.com.asofar.views.venta;

import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.PrDetalleTarifarioJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.JoinProductoVenta;
import ec.com.asofar.daoext.JoinProductoVentaExt;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ConsultaProductoVenta extends javax.swing.JDialog {

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

    /**/
    public ConsultaProductoVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
//        cargarTbaProduc();
//        Prueba();
        cargarTbaProductoVenta();

    }

    public void Prueba() {
        ListProdVent = selectProdVent.listarProductoVenta();
        for (int i = 0; i < ListProdVent.size(); i++) {
//            System.out.println("prueba " + ListProdVent.get(i).getId_kardex());
//            System.out.println(" " + ListProdVent.get(i).getSaldo_actual());
//            System.out.println(" " + ListProdVent.get(i).getAplica_iva());
        }
    }

    public void cargarTbaProductoVenta() {
        ListProdVent = selectProdVent.listarProductoVenta();
        Tablas.ListarProductosVenta2(ListProdVent, tba_productos);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_productos = new javax.swing.JTable();
        btnsalir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(new java.awt.Color(6, 162, 213));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCTOS");
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

        txtfiltro.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfiltroActionPerformed(evt);
            }
        });
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfiltroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnsalir.setBackground(new java.awt.Color(254, 254, 254));
        btnsalir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(1, 1, 1));
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.setOpaque(true);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setText("ESCOGER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 178, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtfiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyPressed
        valor = txtfiltro.getText();
        Tablas.filtro(valor, tba_productos);
    }//GEN-LAST:event_txtfiltroKeyPressed

    private void txtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyReleased
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltroKeyReleased
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
    private void txtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            int id = 0;
        if (tba_productos.getSelectedRow() >= 0) {
            /*hgfrt*/
            id = tba_productos.getSelectedRow();
            listaProVent = selectProdVent.listarProductoVenta();
            objJoinProVen = devuelveObjeto5(Long.valueOf(tba_productos.getValueAt(id, 0).toString()), listaProVent);

            if (objJoinProVen != null) {
//                cliente_editar Ce = new cliente_editar(new javax.swing.JFrame(), true, usu, emp, suc, Client);
                setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaProductoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConsultaProductoVenta dialog = new ConsultaProductoVenta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tba_productos;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
