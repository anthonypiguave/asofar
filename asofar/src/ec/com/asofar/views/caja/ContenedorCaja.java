/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.caja;

import ec.com.asofar.dao.VeCajaJpaController;
import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.util.EntityManagerUtil;
import java.util.List;

/**
 *
 * @author ms24m
 */
public class ContenedorCaja extends javax.swing.JDialog {

    boolean verificar = true;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    VeDetalleCaja vdc;
    VeCajaJpaController cajacon = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    VeDetalleCajaJpaController cajadet = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());

    /**
     * Creates new form ContenedorCaja
     */
    public ContenedorCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public ContenedorCaja(java.awt.Frame parent, boolean modal, VeDetalleCaja veCaja, SeUsuarios se, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        btnAperturaCaja.setEnabled(false);
        setLocationRelativeTo(null);
        vdc = veCaja;

    }

    public ContenedorCaja(java.awt.Frame parent, boolean modal, SeUsuarios se, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        seUsuario = se;
        seEmpresa = em;
        seSucursal = su;
        List<VeDetalleCaja> listadetallecaja = cajadet.findVeDetalleCajaEntities();
        for (int i = 0; i < listadetallecaja.size(); i++) {
            if ("A".equals(listadetallecaja.get(i).getEstado())
                    && listadetallecaja.get(i).getIdUsuario().equals(seUsuario.getIdUsuario())
                    && listadetallecaja.get(i).getFechaCierre() == null
                    && listadetallecaja.get(i).getHoraCierre() == null) {
                btnAperturaCaja.setEnabled(false);
                btnCierre.setEnabled(true);
                vdc = listadetallecaja.get(i);

            } else {
                btnCierre.setEnabled(false);
            }

        }
        if (listadetallecaja.isEmpty()) {
            btnCierre.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAperturaCaja = new javax.swing.JButton();
        btnCierre = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnAperturaCaja.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btnAperturaCaja.setForeground(new java.awt.Color(0, 102, 0));
        btnAperturaCaja.setText("APERTURA");
        btnAperturaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAperturaCajaActionPerformed(evt);
            }
        });

        btnCierre.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btnCierre.setForeground(new java.awt.Color(153, 0, 0));
        btnCierre.setText("CIERRE ");
        btnCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCierreActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(1, 1, 1));
        jButton5.setText("VOLVER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPCIONES DE CAJA");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAperturaCaja, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCierre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAperturaCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void btnAperturaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAperturaCajaActionPerformed
        Apertura_Caja acaja = new Apertura_Caja(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
        this.setVisible(false);
        acaja.setVisible(true);
    }//GEN-LAST:event_btnAperturaCajaActionPerformed

    private void btnCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCierreActionPerformed
        Cierre_Caja cierre = new Cierre_Caja(new javax.swing.JFrame(), true, vdc, seUsuario, seEmpresa, seSucursal);
        this.setVisible(false);
        cierre.setVisible(true);
    }//GEN-LAST:event_btnCierreActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(ContenedorCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContenedorCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContenedorCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContenedorCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ContenedorCaja dialog = new ContenedorCaja(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAperturaCaja;
    private javax.swing.JButton btnCierre;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
