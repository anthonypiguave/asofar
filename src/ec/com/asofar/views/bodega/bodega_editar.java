/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.bodega;

import ec.com.asofar.dao.InBodegaJpaController;
import ec.com.asofar.dao.InTipoBodegaJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.ValidarDTO;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InBodegaPK;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class bodega_editar extends javax.swing.JDialog {

    int x, y;
    ObtenerDTO od = new ObtenerDTO();
    List<InTipoBodega> TiBo;
    InTipoBodega TB;
    List<InBodega> Bo;
    InBodega Bodega;
    InTipoBodega ps = new InTipoBodega();
    InBodega bodeg = new InBodega();

    InTipoBodegaJpaController tbc = new InTipoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
    InBodegaJpaController bc = new InBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
    InBodega obj1 = null;
    SeEmpresa emp;
    SeSucursal suc;
    SeUsuarios usu;

    public bodega_editar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public bodega_editar(java.awt.Frame parent, boolean modal, InBodega bod, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        Bodega = bod;
        TiBo = tbc.findInTipoBodegaEntities();
        Bo = bc.findInBodegaEntities();
        txtNombre.setText(Bodega.getNombreBodega());
//        txtBodega.setText(String.valueOf(ObtenerDTO.ObtenerInTipoBodega(Bodega.getInBodegaPK().getIdTipoBodega())));
        txttipoBodega.setText(ObtenerDTO.ObtenerInTipoBodega(bod.getInBodegaPK().getIdTipoBodega()).getNombre());
        cbx_estado.addItem("A");
        cbx_estado.addItem("I");
        emp = em;
        suc = su;
        usu = us;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btncancelar1 = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbx_estado = new javax.swing.JComboBox<>();
        txttipoBodega = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDITAR BODEGA");
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

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("TIPO DE BODEGA:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("NOMBRE:");

        btncancelar1.setBackground(new java.awt.Color(254, 254, 254));
        btncancelar1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btncancelar1.setForeground(new java.awt.Color(1, 1, 1));
        btncancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        btncancelar1.setText("CANCELAR");
        btncancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar1ActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(1, 1, 1));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("ESTADO :");

        cbx_estado.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        txttipoBodega.setEditable(false);
        txttipoBodega.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txttipoBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipoBodegaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btncancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttipoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_estado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttipoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btncancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btncancelar1ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        java.util.Date fechaActual = new java.util.Date();
//        try {
//            boolean valor1 = ValidarDTO.ValidarInBodega(txtNombre.getText());
//            if (valor1 == true) {
//                JOptionPane.showMessageDialog(this, "El tipo de Bodega ya existente");
//            } else {
        Bodega.setNombreBodega(txtNombre.getText());
        Bodega.setEstado(cbx_estado.getSelectedItem().toString());
        Bodega.setUsuarioActualizacion(usu.getUsuario());
        Bodega.setFechaActualizacion(fechaActual);
        try {
            bc.edit(Bodega);
            setVisible(false);
            JOptionPane.showMessageDialog(this, "GUARDADO EXITOSAMENTE");
        } catch (Exception ex) {
            Logger.getLogger(bodega_editar.class.getName()).log(Level.SEVERE, null, ex);
        }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }

    }//GEN-LAST:event_btnguardarActionPerformed

    private void txttipoBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoBodegaActionPerformed

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        txtNombre.setText(txtNombre.getText().toUpperCase());
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int caracteres = 70;
        if(txtNombre.getText().length()>=70){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

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
            java.util.logging.Logger.getLogger(bodega_editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bodega_editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bodega_editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bodega_editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                bodega_editar dialog = new bodega_editar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btncancelar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JComboBox<String> cbx_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txttipoBodega;
    // End of variables declaration//GEN-END:variables
}
