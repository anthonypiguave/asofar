/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.prestaciones;

import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.PrTipoPrestacionJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.ProductoCadena;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrTipoPrestacion;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Alberto Mero
 */
public class AgregarPrestacion extends javax.swing.JDialog {

    PrProductos proc;
    PrPrestaciones presta = new PrPrestaciones();
    List<PrTipoPrestacion> TipoP;
    List<PrProductos> Lpro;
    List<PrPrestaciones> Lpre;
    PrTipoPrestacion pol = new PrTipoPrestacion();
    PrTipoPrestacionJpaController ti = new PrTipoPrestacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrPrestacionesJpaController pr = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    Long id;
    int x, y;
//    String cadena = "";

    /**
     * Creates new form AgregarPrestacion
     */
    public AgregarPrestacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        TipoP = ti.findPrTipoPrestacionEntities();
        llenarTipoPrestacion(TipoP);

    }

    public AgregarPrestacion(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        TipoP = ti.findPrTipoPrestacionEntities();
        llenarTipoPrestacion(TipoP);

        usu = us;
        emp = em;
        suc = su;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbxIG = new javax.swing.JComboBox<>();
        cmbxSN = new javax.swing.JComboBox<>();
        btngrabar = new javax.swing.JButton();
        btnbuscarP = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtCadena = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("TIPO DE PRESTACIONES:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRE DE PRESTACION:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("APLICA IVA:");

        cmbxIG.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cmbxIG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxIGActionPerformed(evt);
            }
        });

        cmbxSN.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cmbxSN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        btngrabar.setBackground(new java.awt.Color(254, 254, 254));
        btngrabar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btngrabar.setForeground(new java.awt.Color(1, 1, 1));
        btngrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        btngrabar.setText("GUARDAR");
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });

        btnbuscarP.setBackground(new java.awt.Color(254, 254, 254));
        btnbuscarP.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnbuscarP.setForeground(new java.awt.Color(1, 1, 1));
        btnbuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/buscar_Mesa de trabajo 1.png"))); // NOI18N
        btnbuscarP.setText("BUSCAR");
        btnbuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarPActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(6, 162, 213));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("AGREGAR PRESTACIONES");
        jLabel4.setOpaque(true);
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel4MouseDragged(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCadena))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbxSN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbxIG, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(221, 221, 221))
                            .addComponent(btnbuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxIG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnbuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxSN, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void btnbuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarPActionPerformed
        ConsultaProducto cp = new ConsultaProducto(new javax.swing.JFrame(), true);
        cp.setVisible(true);
        proc = cp.getProducto();


        id = proc.getPrProductosPK().getIdProducto();
       


     txtCadena.setText(proc.getNombreProducto());
    }//GEN-LAST:event_btnbuscarPActionPerformed

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
        PrPrestaciones PRE = new PrPrestaciones();
        java.util.Date fechaActual = new java.util.Date();

        if (txtCadena.equals("")) {

            JOptionPane.showMessageDialog(rootPane, "Llene los campos!");

        } else {

            pol = ObtenerDTO.ObtenerPrTipoPrestacion(cmbxIG.getSelectedItem().toString());
            PRE.setAplicaIva(cmbxSN.getSelectedItem().toString());
            PRE.setEstado("A");
            PRE.setNombrePrestacion(txtCadena.getText());
            PRE.setIdTipoPrestacion(pol);

            if (cmbxIG.getSelectedItem().toString().equals("INVENTARIO")) {
                PRE.setIdPoducto(BigInteger.valueOf(id));
            } else {
                PRE.setIdPoducto(null);
            }

            PRE.setUsuarioCreacion(usu.getUsuario());
            PRE.setIdEmpresa(emp);
            PRE.setFechaCreacion(fechaActual);
            try {

                pr.create(PRE);
                JOptionPane.showMessageDialog(null, "GUARDADO EXITOSAMENTE");
                setVisible(false);
            } catch (Exception e) {
                Logger.getLogger(AgregarPrestacion.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }//GEN-LAST:event_btngrabarActionPerformed

    private void cmbxIGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxIGActionPerformed
        bloqueo();
        txtCadena.setText("");
        
    }//GEN-LAST:event_cmbxIGActionPerformed
    public int bloqueo() {
        int bloqueo;
        String selecciona = (String) cmbxIG.getSelectedItem();
        System.out.println(selecciona);
        if (selecciona.equals("SERVICIO")) {
            btnbuscarP.setEnabled(false);
        } else {
            btnbuscarP.setEnabled(true);
        }

        return 1;

    }

    public void llenarTipoPrestacion(List<PrTipoPrestacion> TipoP) {
        for (int i = 0; i < TipoP.size(); i++) {

            cmbxIG.addItem(TipoP.get(i).getNombre());

        }
    }

    public void cargarPrest() {
        for (int i = 0; i < Lpre.size(); i++) {
            System.out.println(" iides:" + Lpre.get(i).getIdPoducto());

        }
    }

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
            java.util.logging.Logger.getLogger(AgregarPrestacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarPrestacion dialog = new AgregarPrestacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnbuscarP;
    private javax.swing.JButton btngrabar;
    private javax.swing.JComboBox<String> cmbxIG;
    private javax.swing.JComboBox<String> cmbxSN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCadena;
    // End of variables declaration//GEN-END:variables

}
