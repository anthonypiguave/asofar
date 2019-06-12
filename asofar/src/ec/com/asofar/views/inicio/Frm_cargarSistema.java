/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.inicio;

import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.util.Cargar;
import ec.com.asofar.util.Cargar1;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author admin1
 */
public class Frm_cargarSistema extends javax.swing.JFrame {
    Cargar hilo;
    Cargar1 hilo1;
    PrSubgruposJpaController ed = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrSubgrupos pr = new PrSubgrupos();
    public Frm_cargarSistema() {
        initComponents();
                Image logof = new ImageIcon(System.getProperty("user.dir") + "/src/ec/com/asofar/util/Asofar.jpeg").getImage();

        this.setIconImage(logof);
        setLocationRelativeTo(null);
        carga_pb.setStringPainted(true);
        UIManager.put("nimbusOrange", new Color(38, 139, 210));
        hilo = new Cargar(carga_pb);
        hilo.start();
        hilo = null;
        imagenes();
    }
        public void imagenes() {
        Image logof = new ImageIcon(System.getProperty("user.dir") + "/src/ec/com/asofar/util/Asofar.jpeg").getImage();
        Icon fondoLogo = new ImageIcon(logof.getScaledInstance(lbImagen.getWidth(), lbImagen.getHeight(), Image.SCALE_DEFAULT));
        lbImagen.setIcon(fondoLogo);
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbImagen = new javax.swing.JLabel();
        carga_pb = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        carga_pb.setForeground(new java.awt.Color(255, 102, 0));
        carga_pb.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                carga_pbStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(carga_pb, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carga_pb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carga_pbStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_carga_pbStateChanged
        // TODO add your handling code here:
            if (carga_pb.getValue() == 70) {
            if (!EntityManagerUtil.ObtenerEntityManagerConnection()) {
                JOptionPane.showMessageDialog(this, "LA BASE DE DATOS NO ESTÁ ACTIVA");
            } else {
                dispose();
                    Login fl = new Login(new javax.swing.JFrame(),true);
                    fl.setVisible(true);
            }
        }
    }//GEN-LAST:event_carga_pbStateChanged

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
            java.util.logging.Logger.getLogger(Frm_cargarSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_cargarSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_cargarSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_cargarSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_cargarSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar carga_pb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbImagen;
    // End of variables declaration//GEN-END:variables
}
