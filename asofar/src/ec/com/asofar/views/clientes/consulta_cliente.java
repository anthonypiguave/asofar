/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.clientes;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeContactosClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class consulta_cliente extends javax.swing.JDialog {

    int x, y;
    String valor = "";
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    SeClientes cli;
    SeTipoIdentificacion tident;
    List<SeClientes> Cliente;
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeLocalidadCliente> LocalidadCliente;
    SeLocalidadClienteJpaController Lc = new SeLocalidadClienteJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeClientes> lista = Cc.findSeClientesEntities();
    SeClientes Client = new SeClientes();
    List<SeContactosClientes> ContactoCliente;
    SeContactosClientesJpaController Ccl = new SeContactosClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeLocalidadCliente> lista1 = Lc.findSeLocalidadClienteEntities();
    SeLocalidadCliente LocaliClient = new SeLocalidadCliente();

    public consulta_cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarClientes();
    }

    /*AGREGAR EN OPCIONES MENU Y OPCIONES ROLES*/
    ///
    public consulta_cliente(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarClientes();
        usu = us;
        emp = em;
        suc = su;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_clientes = new javax.swing.JTable();
        btn_ingresar_cliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tba_localidad = new javax.swing.JTable();
        btn_ingresar_localidad = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tba_contacto = new javax.swing.JTable();
        btn_ingresar_contacto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        busqueda_tf = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        tba_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_clientesMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tba_clientes);

        btn_ingresar_cliente.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btn_ingresar_cliente.setForeground(new java.awt.Color(41, 139, 40));
        btn_ingresar_cliente.setText("INGRESAR");
        btn_ingresar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_ingresar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ingresar_cliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Localidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        tba_localidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_localidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_localidadMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_localidadMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tba_localidad);

        btn_ingresar_localidad.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btn_ingresar_localidad.setForeground(new java.awt.Color(41, 139, 40));
        btn_ingresar_localidad.setText("INGRESAR");
        btn_ingresar_localidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_localidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_ingresar_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ingresar_localidad))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        tba_contacto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_contacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_contactoMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_contactoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tba_contacto);

        btn_ingresar_contacto.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btn_ingresar_contacto.setForeground(new java.awt.Color(41, 139, 40));
        btn_ingresar_contacto.setText("INGRESAR");
        btn_ingresar_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_contactoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_ingresar_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ingresar_contacto))
        );

        jLabel3.setBackground(new java.awt.Color(255, 102, 0));
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTES");
        jLabel3.setOpaque(true);
        jLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel3MouseDragged(evt);
            }
        });
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("Buscar:");

        busqueda_tf.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        busqueda_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busqueda_tfActionPerformed(evt);
            }
        });
        busqueda_tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busqueda_tfKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busqueda_tfKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(173, 42, 48));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(254, 254, 254));
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(busqueda_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(busqueda_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void MostrarClientes() {
        try {

            Cliente = Cc.findSeClientesEntities();
            Tablas.TablaClientesActivo(Cliente, tba_clientes);
        } catch (Exception e) {
        }
    }

//    public void MostrarLocalidad() {
//        try {
//            LocalidadCliente = Lc.findSeLocalidadClienteEntities();
//            Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad);
//        } catch (Exception e) {
//        }
//    }
    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel3MouseDragged

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void busqueda_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busqueda_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busqueda_tfActionPerformed

    private void busqueda_tfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busqueda_tfKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String text = ("" + c).toUpperCase();
            c = text.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_busqueda_tfKeyTyped

    private void busqueda_tfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busqueda_tfKeyReleased
        valor = busqueda_tf.getText();
        Tablas.filtro(valor, tba_clientes);
    }//GEN-LAST:event_busqueda_tfKeyReleased

    private void tba_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                LocalidadCliente = Lc.findSeLocalidadClienteEntities();
                Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad, Client);
                
//                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
//                tba_contacto.clearSelection();
            }
        }
    }//GEN-LAST:event_tba_clientesMouseClicked

    private void tba_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMousePressed

    }//GEN-LAST:event_tba_clientesMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_ingresar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_clienteActionPerformed

        cliente_agregar cagg = new cliente_agregar(new javax.swing.JFrame(), true, usu, emp, suc, tident);
        cagg.setVisible(true);

    }//GEN-LAST:event_btn_ingresar_clienteActionPerformed

    private void tba_localidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_localidadMousePressed
    public SeLocalidadCliente devuelveObjeto2(Long id, List<SeLocalidadCliente> listabod) {
        SeLocalidadCliente doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdLocalidadCliente(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void tba_localidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                System.out.println("id locali " + LocaliClient.getIdLocalidadCliente());
                ContactoCliente = Ccl.findSeContactosClientesEntities();
//                System.out.println("contaco id local"+ContactoCliente.get(id));
                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);

            }
        }
    }//GEN-LAST:event_tba_localidadMouseClicked

    private void btn_ingresar_localidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_localidadActionPerformed
//        int id = 0;
//        if (tba_localidad.getSelectedRow() >= 0) {
//            id = tba_localidad.getSelectedRow();
//            Client = devuelveObjeto(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista);
//
//            if (Client != null) {
        Localidad_agregar Lagg = new Localidad_agregar(new javax.swing.JFrame(), true, usu, emp, suc, Client);
        Lagg.setVisible(true);

        LocalidadCliente = Lc.findSeLocalidadClienteEntities();
        Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad, Client);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "SELECCIONE UN REGISTO DE LA TABLA CLIENTE");
//        }
    }//GEN-LAST:event_btn_ingresar_localidadActionPerformed
//    private SeClientes devuelveObjeto3(Long id, List<SeClientes> listacliente) {
//        listacliente = Cc.findSeClientesEntities();
//        for (int i = 0; i < listacliente.size(); i++) {
//            if (Objects.equals(listacliente.get(i).getIdClientes(), id)) {
//                Client = listacliente.get(i);
//            }
//        }
//        return Client;
//    }
    private void tba_contactoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMousePressed

    private void tba_contactoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMouseClicked

    private void btn_ingresar_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_contactoActionPerformed

        int id = 0;
        if (tba_localidad.getSelectedRow() >= 0) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                contacto_agregar Cagg = new contacto_agregar(new javax.swing.JFrame(), true, usu, emp, suc, LocaliClient);
                Cagg.setVisible(true);
            }
            ////
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN REGISTO DE LA TABLA LOCALIDAD");
        }

    }//GEN-LAST:event_btn_ingresar_contactoActionPerformed
    public SeClientes devuelveObjeto(Long id, List<SeClientes> listabod) {
        SeClientes doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdClientes(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
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
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                consulta_cliente dialog = new consulta_cliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_ingresar_cliente;
    private javax.swing.JButton btn_ingresar_contacto;
    private javax.swing.JButton btn_ingresar_localidad;
    private javax.swing.JTextField busqueda_tf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tba_clientes;
    private javax.swing.JTable tba_contacto;
    private javax.swing.JTable tba_localidad;
    // End of variables declaration//GEN-END:variables
}
