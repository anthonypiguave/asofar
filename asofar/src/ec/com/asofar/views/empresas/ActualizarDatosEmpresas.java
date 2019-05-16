/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.empresas;

import ec.com.asofar.dao.SeEmpresaJpaController;
import ec.com.asofar.dao.SeTipoPersonaJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Fecha;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class ActualizarDatosEmpresas extends javax.swing.JDialog {

    SePersonas obj;
    SeEmpresaJpaController persona_controller
            = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private Date fecha1 = null;
    String ro = null;
    String est = null;
    String gen = null;
//    private String rutaimagen = "";
    public String fil;
    List<SeEmpresa> listaEmpresa;
    SeEmpresa empresa;
    SeTipoPersonaJpaController tpc
            = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
    java.util.Date fechaActual = new java.util.Date();
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;
    SeEmpresaJpaController empresaController
            = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());

    public ActualizarDatosEmpresas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public ActualizarDatosEmpresas(java.awt.Frame parent, boolean modal, SeEmpresa empresa, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        empresa = empresa;
        us1 = us;
        em1 = em;
        su1 = su;
        cargarValores(empresa);
        listaEmpresa
                = persona_controller.findSeEmpresaEntities();

    }

    public void bloqueo() {

        int r = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR EL USUARIO?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            empresa.setEstado('I');
            try {
                empresaController.edit(empresa);
                JOptionPane.showMessageDialog(null, "PERSONA ELIMINADA");
                ListaEmpresas mp = new ListaEmpresas(new javax.swing.JFrame(), true);
                setVisible(false);
                mp.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ActualizarDatosEmpresas.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnHabilitar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnGuardar.setText("ACTUALIZAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnHabilitar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnHabilitar.setText("ELIMINAR");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 153, 153));
        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ACTUALIZAR DATOS EMPRESARIALES");
        jLabel17.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS PERSONALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRE :");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("RUC :");

        txtRuc.setEditable(false);
        txtRuc.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("DIRECCION:");

        txtNombre.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
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
        jLabel5.setText("TELEFONO");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("CORREO:");

        txtDireccion.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtCorreo.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(65, 65, 65)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed
    public void Guardar() {
//        empresa.setCedula(txtCedula.getText());
        int r = JOptionPane.showConfirmDialog(null, "¿DESEA ACTUALIZAR LOS DATOS DE EMPRESA?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            empresa.setNombreComercial(txtNombre.getText());
            empresa.setTelefono(txtTelefono.getText());
            empresa.setCorreo(txtCorreo.getText());
            empresa.setDireccion(txtDireccion.getText());
            empresa.setFechaActualizacion(fechaActual);
            empresa.setUsuarioActualizacion(us1.getIdUsuario());           
            try {
                empresaController.edit(empresa);
                JOptionPane.showMessageDialog(null, "EMPRESA ACTUALIZADA");
                ListaEmpresas mp = new ListaEmpresas(new javax.swing.JFrame(), true, us1, em1, su1);
                setVisible(false);
                mp.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ActualizarDatosEmpresas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        ListaEmpresas mp = new ListaEmpresas(new javax.swing.JFrame(), true, us1, em1, su1);
        setVisible(false);
        mp.setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
        bloqueo();
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped

    }//GEN-LAST:event_txtRucKeyTyped

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased

    }//GEN-LAST:event_txtRucKeyReleased

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        txtNombre.setText(txtNombre.getText().toUpperCase());
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        txtDireccion.setText(txtDireccion.getText().toUpperCase());
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        char c = evt.getKeyChar();
        char mas = '+', por = '*', div = '/', dp = ':', pc = ';', c2 = ',', p1 = '{', p2 = '}';
        char lla1 = '[', el = '^', lla2 = ']', el2 = '¿', co = '?', co2 = '¡', c3 = '!', d = '"', e = '#';
        char col = '$', a = '!', b = '=', e2 = '%', f = '&', g = '=', h = 'º', i = 'ª', j = '(', k = ')', l = '<', m = '>';
        char n = 'ç', o = '´', p = '`', q = '¨', r = 'Ñ', s = '·', t = 'ñ';
        if (Character.isWhitespace(c) || c == mas || c == por || c == div || c == dp
                || c == pc || c == c2 || c == p1 || c == p2 || c == lla1 || c == lla2
                || c == el || c == el2 || c == co || c == co2 || c == c3 || c == d || c == e
                || c == col || c == a || c == b || c == e2 || c == f || c == g || c == h
                || c == i || c == j || c == k || c == l || c == m || c == n || c == o || c == p
                || c == q || c == r || c == s || c == t) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

//    public void CargarRol() {
//        List<SeTipoPersona> listar = tpc.findSeTipoPersonaEntities();
//        for (int i = 0; i < listar.size(); i++) {
//
//            cbTipoPersona1.addItem(listar.get(i).getNombre());
//        }
//        cbTipoPersona1.setSelectedItem(empresa.getIdTipoPersona().getNombre());
//
//    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizarDatosEmpresas dialog = new ActualizarDatosEmpresas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void cargarValores(SeEmpresa persona) {
        txtRuc.setText(persona.getRuc());
        txtNombre.setText(persona.getNombreComercial());
        txtDireccion.setText(persona.getDireccion());
        txtCorreo.setText(persona.getCorreo());
        txtTelefono.setText(persona.getTelefono());
        txtCorreo.setText(persona.getCorreo());       
    }
}
