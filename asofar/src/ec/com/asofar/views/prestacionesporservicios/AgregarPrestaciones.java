/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.prestacionesporservicios;

import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.VeUnidadServicioJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.views.prestaciones.Listar_Prestaciones;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AgregarPrestaciones extends javax.swing.JDialog {

    InPrestacionesPorServicios pol1 = new InPrestacionesPorServicios();
    PrPrestaciones pol2 = new PrPrestaciones();
    VeUnidadServicio pol3 = new VeUnidadServicio();
    
    PrPrestacionesJpaController pr = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<PrPrestaciones> pr2;

    InPrestacionesPorServiciosJpaController pxs = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());

    VeUnidadServicioJpaController us1 = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<VeUnidadServicio> us2;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    /**
     * Creates new form AgregarPrestaciones
     * @param parent
     */
    // 26-06-19
//    public int bloqueo() {
//       
//        int bloqueo;
//        String selecciona = (String) cbxpxs.getSelectedItem();
//        System.out.println(selecciona);
//        
//        return 1;
//
//    }
    //26-06
    public AgregarPrestaciones(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        pr2 = pr.findPrPrestacionesEntities();
        nombrePrestacion(pr2);
        us2 = us1.findVeUnidadServicioEntities();
        nombreUnidad(us2);
        
//        usu = us;
//        emp = em;
//        suc = su;

    }

    public AgregarPrestaciones(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        pr2 = pr.findPrPrestacionesEntities();
        nombrePrestacion(pr2);

        us2 = us1.findVeUnidadServicioEntities();
        nombreUnidad(us2);
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

        jPanel1 = new javax.swing.JPanel();
        cbxprestacion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxunidadservicio = new javax.swing.JComboBox<>();
        jguardarpreser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxfacturable = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxaplicadescuento1 = new javax.swing.JComboBox<>();
        jcancelar = new javax.swing.JButton();
        cbxestado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        cbxprestacion.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxprestacion.setForeground(new java.awt.Color(1, 1, 1));
        cbxprestacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxprestacionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("PRESTACION:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("UNIDAD DE SERVICIO:");

        cbxunidadservicio.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxunidadservicio.setForeground(new java.awt.Color(1, 1, 1));
        cbxunidadservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxunidadservicioActionPerformed(evt);
            }
        });

        jguardarpreser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jguardarpreser.setForeground(new java.awt.Color(46, 127, 45));
        jguardarpreser.setText("GUARDAR");
        jguardarpreser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jguardarpreserActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("FACTURABLE:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setText("APLICA DESCUENTO:");

        cbxfacturable.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxfacturable.setForeground(new java.awt.Color(1, 1, 1));
        cbxfacturable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));
        cbxfacturable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxfacturableActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("ESTADO:");

        cbxaplicadescuento1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxaplicadescuento1.setForeground(new java.awt.Color(1, 1, 1));
        cbxaplicadescuento1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        jcancelar.setBackground(new java.awt.Color(165, 47, 59));
        jcancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcancelar.setForeground(new java.awt.Color(254, 254, 254));
        jcancelar.setText("CANCELAR");
        jcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcancelarActionPerformed(evt);
            }
        });

        cbxestado.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxestado.setForeground(new java.awt.Color(1, 1, 1));
        cbxestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));

        jLabel4.setBackground(new java.awt.Color(255, 102, 0));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRESTACIONES - SERVICIO");
        jLabel4.setAutoscrolls(true);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jguardarpreser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxfacturable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxprestacion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxaplicadescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxunidadservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxestado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxprestacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxunidadservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxfacturable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxaplicadescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxestado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jguardarpreser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jguardarpreserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jguardarpreserActionPerformed

        InPrestacionesPorServicios preServ = new InPrestacionesPorServicios();
        java.util.Date fechaActual = new java.util.Date();
        pol2 = ObtenerDTO.ObtenerPrPrestaciones(cbxprestacion.getSelectedItem().toString());
        System.out.println(" " + ObtenerDTO.ObtenerPrPrestaciones(cbxprestacion.getSelectedItem().toString()));
        pol3 = ObtenerDTO.ObtenerVeUnidadServicio(cbxunidadservicio.getSelectedItem().toString());
        System.out.println("unidad " + ObtenerDTO.ObtenerVeUnidadServicio(cbxunidadservicio.getSelectedItem().toString()));
//        pol1= ObtenerDTO.ObtenerInPrestacionesPorServicios(cbxestado.getSelectedItem().toString());
        
        System.out.println(""+pol2);
        System.out.println(""+pol3);
//        System.out.println(""+pol1);
        
        preServ.setPrPrestaciones(pol2);
        preServ.setVeUnidadServicio(pol3);
        preServ.setEsFacturable(cbxfacturable.getSelectedItem().toString());
        preServ.setAplicaDescuento(cbxestado.getSelectedItem().toString());
        preServ.setEstado(cbxestado.getSelectedItem().toString());
        preServ.setUsuarioCreacion(usu.getNombreUsuario());
        preServ.setFechaCreacion(fechaActual);
       
        
        try {
            pxs.create(preServ);
            JOptionPane.showMessageDialog(null, " GUARDADO CON EXITO");
            setVisible(false);
        } catch (Exception e) {
            Logger.getLogger(AgregarPrestaciones.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_jguardarpreserActionPerformed

    public void nombrePrestacion(List<PrPrestaciones> pr2) {

        for (int i = 0; i < pr2.size(); i++) {

            cbxprestacion.addItem(pr2.get(i).getNombrePrestacion());

        }
    }

    public void nombreUnidad(List<VeUnidadServicio> us2) {
        for (int i = 0; i < us2.size(); i++) {

            cbxunidadservicio.addItem(us2.get(i).getNombreUnidadServicio());
        }
    }
    private void cbxprestacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxprestacionActionPerformed

    }//GEN-LAST:event_cbxprestacionActionPerformed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged

    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

    }//GEN-LAST:event_jLabel4MousePressed

    private void cbxunidadservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxunidadservicioActionPerformed


    }//GEN-LAST:event_cbxunidadservicioActionPerformed

    private void cbxfacturableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxfacturableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxfacturableActionPerformed

    private void jcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcancelarActionPerformed

        setVisible(false);
 // TODO add your handling code here:
    }//GEN-LAST:event_jcancelarActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarPrestaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarPrestaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarPrestaciones dialog = new AgregarPrestaciones(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbxaplicadescuento1;
    private javax.swing.JComboBox<String> cbxestado;
    private javax.swing.JComboBox<String> cbxfacturable;
    private javax.swing.JComboBox<String> cbxprestacion;
    private javax.swing.JComboBox<String> cbxunidadservicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jcancelar;
    private javax.swing.JButton jguardarpreser;
    // End of variables declaration//GEN-END:variables
}
