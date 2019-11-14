/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.articulo;

import ec.com.asofar.dao.PrArticuloJpaController;
import ec.com.asofar.dao.PrGruposJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.PrArticuloJpaControllerExt;
import ec.com.asofar.daoext.ValidarDTO;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class IngresarArticulo extends javax.swing.JDialog {

    PrGruposJpaController control = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrArticuloJpaControllerExt control2 = new PrArticuloJpaControllerExt(EntityManagerUtil.ObtenerEntityManager());
    List<PrGrupos> lista = control.findPrGruposEntities();
    int x, y, numerocaracter;

    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;

    /**
     * Creates new form IngresarArticulo
     */
    public IngresarArticulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        ObtenerGrupo();
    }

    public IngresarArticulo(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        ObtenerGrupo();
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
        estado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        subgrupo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        articulo = new javax.swing.JTextField();
        grupo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        estado.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("SUBGRUPO:");

        jLabel1.setBackground(new java.awt.Color(6, 162, 213));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA ARTICULO");
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

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("ARTICULO:");

        subgrupo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        subgrupo.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("ESTADO:");

        guardar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        guardar.setForeground(new java.awt.Color(1, 1, 1));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        guardar.setText("GUARDAR");
        guardar.setOpaque(true);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        articulo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        articulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                articuloFocusLost(evt);
            }
        });
        articulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                articuloKeyTyped(evt);
            }
        });

        grupo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        grupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        grupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                grupoItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("GRUPO:");

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setOpaque(true);
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
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
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

    private void grupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_grupoItemStateChanged
        
        
    
        subgrupo.setEnabled(true);

        String nombre = grupo.getSelectedItem().toString();

        PrGrupos gru = ObtenerDTO.ObtenerPrGrupos(nombre);

        subgrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione una Opcion.."}));

        for (int i = 0; i < gru.getPrSubgruposList().size(); i++) {

            subgrupo.addItem(gru.getPrSubgruposList().get(i).getNombre());

        }


    }//GEN-LAST:event_grupoItemStateChanged

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            boolean valor1 = ValidarDTO.ValidarPrArticulo(articulo.getText());
            if (valor1 == true) {
                JOptionPane.showMessageDialog(this, "Articulo ya existente");
            } else {
                String valor = "I";
                PrArticulo arti = new PrArticulo();
                PrSubgrupos sub = new PrSubgrupos();
                if (estado.getSelectedIndex() == 0) {
                    valor = "A";
                }
                sub = ObtenerDTO.ObtenerPrSubGrupos(subgrupo.getSelectedItem().toString());

                arti.setNombreArticulo(articulo.getText());
                arti.setPrSubgrupos(sub);
                arti.setEstado(valor);
                control2.create(arti);
                JOptionPane.showMessageDialog(this, "Articulo Guardados");
                Actualizar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//       
        Actualizar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void articuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyTyped
        numerocaracter = 70;
        if (articulo.getText().length() >= numerocaracter) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo puede ingresar maximo 70 caracteres");
        }
    }//GEN-LAST:event_articuloKeyTyped

    private void articuloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_articuloFocusLost
        articulo.setText(articulo.getText().toUpperCase());
    }//GEN-LAST:event_articuloFocusLost
    public void Actualizar() {
        setVisible(false);
        ConsultaArticulo cs = new ConsultaArticulo(new javax.swing.JFrame(), true);
        cs.setVisible(true);
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
            java.util.logging.Logger.getLogger(IngresarArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IngresarArticulo dialog = new IngresarArticulo(new javax.swing.JFrame(), true);
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

    public void ObtenerGrupo() {

        for (int i = 0; i < lista.size(); i++) {
            grupo.addItem(lista.get(i).getNombre());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField articulo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JComboBox<String> grupo;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> subgrupo;
    // End of variables declaration//GEN-END:variables
}
