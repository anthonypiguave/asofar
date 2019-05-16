/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.usuarios;

import ec.com.asofar.dao.SePersonasJpaController;
import ec.com.asofar.dao.SeRolesJpaController;
import ec.com.asofar.dao.SeUsuariosJpaController;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.AES;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.views.persona.ActualizarDatosPersonas;
import java.awt.Dimension;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class ActualizarDatosUsuarios extends javax.swing.JDialog {

    SeRolesJpaController mn
            = new SeRolesJpaController(EntityManagerUtil.ObtenerEntityManager());
    SePersonasJpaController mp
            = new SePersonasJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeUsuariosJpaController tpc
            = new SeUsuariosJpaController(EntityManagerUtil.ObtenerEntityManager());
    private Date fecha1 = null;
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;
    SePersonas objPersona;
    SeUsuarios usuario = new SeUsuarios();
    java.util.Date fechaActual = new java.util.Date();
    AES aes = new AES();

    public ActualizarDatosUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();

        this.setLocationRelativeTo(null);

        this.setSize(new Dimension(jPanel2.getWidth() + 4, jPanel2.getHeight() - 1));

    }

    public ActualizarDatosUsuarios(java.awt.Frame parent, boolean modal, SeUsuarios us_crear, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        usuario = us_crear;
        us1 = us;
        em1 = em;
        su1 = su;
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(jPanel2.getWidth() + 4, jPanel2.getHeight()));
        cargarDatos(usuario);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPersona = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtCell = new javax.swing.JTextField();
        BotonUsuario = new javax.swing.JButton();
        txtClaveConfirm = new javax.swing.JPasswordField();
        txtClave = new javax.swing.JPasswordField();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS PERSONALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("PERSONA:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("CLAVE:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("USUARIO :");

        txtIdUsuario.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtIdUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdUsuarioKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdUsuarioKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("CONFIRMAR CLAVE :");

        txtPersona.setEditable(false);
        txtPersona.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtPersona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPersonaFocusLost(evt);
            }
        });
        txtPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPersonaKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("E-MAIL :");

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("TELEFONO :");

        txtCorreo.setEditable(false);
        txtCorreo.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        txtCell.setEditable(false);
        txtCell.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCellKeyTyped(evt);
            }
        });

        BotonUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonUsuario.setText("...");
        BotonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPersona, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClaveConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(txtClaveConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnSalir.setText("CANCELAR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDITAR USUARIO");
        jLabel1.setOpaque(true);

        btnEliminar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        ListarUsuarios mp = new ListarUsuarios(new javax.swing.JFrame(), true, us1, em1, su1);
        setVisible(false);
        mp.setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtPersonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPersonaFocusLost
        txtPersona.setText(txtPersona.getText().toUpperCase());
    }//GEN-LAST:event_txtPersonaFocusLost

    private void txtIdUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdUsuarioKeyTyped

    }//GEN-LAST:event_txtIdUsuarioKeyTyped

    private void txtPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonaKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtPersonaKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtCellKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCellKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCellKeyTyped


    private void txtIdUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdUsuarioKeyReleased


    }//GEN-LAST:event_txtIdUsuarioKeyReleased

    private void BotonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonUsuarioActionPerformed
        Mostrar_persona mp = new Mostrar_persona(new javax.swing.JFrame(), true, us1, em1, su1);
        mp.setVisible(true);
        objPersona = mp.getObj();
        txtPersona.setText(objPersona.getNombres());
        txtCell.setText(objPersona.getTelefono());
        txtCorreo.setText(objPersona.getCorreo());

    }//GEN-LAST:event_BotonUsuarioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        bloqueo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    public void Guardar() {
        if (txtClave.getText().equals(txtClaveConfirm.getText())
                || txtIdUsuario.getText().length() >= 2) {
//            usuario.setIdUsuario(txtIdUsuario.getText());
            usuario.setNombreUsuario(txtPersona.getText());
            usuario.setEstado('A');
            usuario.setFechaActualizacion(fechaActual);
            usuario.setFechaCreacion(fechaActual);
            usuario.setIdPersona(objPersona);
            usuario.setUsuarioCreacion(us1.getNombreUsuario());
            usuario.setUsuarioActualizacion(us1.getNombreUsuario());
            usuario.setPassword(txtClave.getText());
            try {
                tpc.edit(usuario);
                JOptionPane.showMessageDialog(this, "USUARIO ACTUALIZADO CORRECTAMENTE");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            ListarUsuarios mp = new ListarUsuarios(new javax.swing.JFrame(), true, us1, em1, su1);
            setVisible(false);
            mp.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "LAS CONTRASEÑAS NO COINCIDEN");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizarDatosUsuarios dialog = new ActualizarDatosUsuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BotonUsuario;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCell;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JPasswordField txtClaveConfirm;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtPersona;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos(SeUsuarios usuario) {
        txtIdUsuario.setText(usuario.getIdUsuario());
        txtPersona.setText(usuario.getNombreUsuario());
        txtClaveConfirm.setText(aes.decrypt(usuario.getPassword()));
        txtClave.setText(aes.decrypt(usuario.getPassword()));
        txtCorreo.setText(usuario.getIdPersona().getCorreo());
        txtCell.setText(usuario.getIdPersona().getTelefono());

    }

    private void bloqueo() {
        int r = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR EL USUARIO?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            usuario.setEstado('I');
            try {
                tpc.edit(usuario);
                JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO");
                ListarUsuarios mp = new ListarUsuarios(new javax.swing.JFrame(), true, us1, em1, su1);
                setVisible(false);
                mp.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ActualizarDatosPersonas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
