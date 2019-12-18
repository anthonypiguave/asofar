/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.clientes;

import ec.com.asofar.dao.SeCiudadJpaController;
import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dao.SePaisJpaController;
import ec.com.asofar.dao.SeProvinciaJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.SeCiudad;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeProvincia;
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
public class Localidad_agregar extends javax.swing.JDialog {

    /**
     * Creates new form Localidad_agregar
     */
    int x, y;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    SeClientes cliente;
    List<SePais> Pais;
    SePaisJpaController Pc = new SePaisJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeCiudad> Ciudad;
    SeCiudadJpaController Cciu = new SeCiudadJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeProvincia> Provincia;
    SeProvinciaJpaController Prc = new SeProvinciaJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeLocalidadCliente localidadclientes = new SeLocalidadCliente();
    SeLocalidadClienteJpaController Lc = new SeLocalidadClienteJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeLocalidadCliente LocalidadCliente;

    public Localidad_agregar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Localidad_agregar(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, SeClientes cl, SeLocalidadCliente lc) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        Pais = Pc.findSePaisEntities();
        Ciudad = Cciu.findSeCiudadEntities();
        Provincia = Prc.findSeProvinciaEntities();
        usu = us;
        emp = em;
        suc = su;
        cliente = cl;
        LocalidadCliente = lc;
        llenarComboPais(Pais);
        llenarComboCiudad(Ciudad);
        llenarComboProvincia(Provincia);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txt_direccion_cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_direccion_entrega = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        cbx_pais = new javax.swing.JComboBox<>();
        cbx_provincia = new javax.swing.JComboBox<>();
        cbx_ciudad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        txt_direccion_cliente.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_direccion_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_direccion_clienteFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("DIRECCION DEL CLIENTE:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("DIRECCION DE ENTREGA:");

        txt_direccion_entrega.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_direccion_entrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_direccion_entregaFocusLost(evt);
            }
        });

        jLabel4.setBackground(java.awt.Color.red);
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("AGREGAR LOCALIDAD");
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

        btn_agregar.setBackground(new java.awt.Color(254, 254, 254));
        btn_agregar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_agregar.setForeground(new java.awt.Color(1, 1, 1));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/nuevo_Mesa de trabajo 1.png"))); // NOI18N
        btn_agregar.setText("AGREGAR");
        btn_agregar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btn_agregar.setFocusable(false);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        cbx_pais.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        cbx_provincia.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        cbx_ciudad.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("PAIS:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("PROVINCIA:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("CIUDAD:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1)
                                .addComponent(jLabel6)))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_pais, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_direccion_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_direccion_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_pais, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    public void llenarComboPais(List<SePais> Pais) {
        for (int i = 0; i < Pais.size(); i++) {
            if (!"I".equals(Pais.get(i).getEstado())) {
                cbx_pais.addItem(Pais.get(i).getNombre());
//                if (LocalidadCliente.getIdPais().getNombre() == Pais.get(i).getNombre()) {
//                    cbx_pais.setSelectedItem(Pais.get(i).getNombre());
//                }
            }
        }
    }

    public void llenarComboProvincia(List<SeProvincia> Provincia) {
        for (int i = 0; i < Provincia.size(); i++) {
            if (!"I".equals(Provincia.get(i).getNombre())) {
                cbx_provincia.addItem(Provincia.get(i).getNombre());
                cbx_provincia.setSelectedItem("Guayas");
//                if (LocalidadCliente.getIdProvincia().getNombre() == Provincia.get(i).getNombre()) {
//                    cbx_provincia.setSelectedItem(Provincia.get(i).getNombre());
//                }
            }
        }
    }

    public void llenarComboCiudad(List<SeCiudad> Ciudad) {
        for (int i = 0; i < Ciudad.size(); i++) {
            if (!"I".equals(Ciudad.get(i).getNombre())) {
                cbx_ciudad.addItem(Ciudad.get(i).getNombre());
                cbx_ciudad.setSelectedItem("Guayaquil");
//                if (LocalidadCliente.getIdCiudad().getNombre() == Ciudad.get(i).getNombre()) {
//                    cbx_ciudad.setSelectedItem(Ciudad.get(i).getNombre());
//                }
            }
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        consulta_cliente ccli = new consulta_cliente(new javax.swing.JFrame(), true, usu, emp, suc);
        ccli.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        java.util.Date fechaActual = new java.util.Date();
        SePais pais = new SePais();
        pais = ObtenerDTO.ObtenerSePais(cbx_pais.getSelectedItem().toString());
        System.out.println("pais " + pais);
        SeProvincia provincia = new SeProvincia();
        provincia = ObtenerDTO.ObtenerSeProvincia(cbx_provincia.getSelectedItem().toString());
        System.out.println("provincia " + provincia);
        SeCiudad ciudad = new SeCiudad();
        ciudad = ObtenerDTO.ObtenerSeCiudad(cbx_ciudad.getSelectedItem().toString());
        localidadclientes.setDirreccionCliente(txt_direccion_cliente.getText());
        localidadclientes.setIdCliente(cliente);
        localidadclientes.setDirreccionEntrega(txt_direccion_entrega.getText());
        localidadclientes.setIdCiudad(ciudad);
        localidadclientes.setIdProvincia(provincia);
        localidadclientes.setIdPais(pais);
        localidadclientes.setEstado("A");
        localidadclientes.setUsuarioCreacion(usu.getUsuario());
        localidadclientes.setFechaCreacion(fechaActual);
        try {
            Lc.create(localidadclientes);
            JOptionPane.showMessageDialog(null, " GUARDADO CON EXITO");
            setVisible(false);
            /*sssd*/ consulta_cliente cagg = new consulta_cliente(new javax.swing.JFrame(), true, usu, emp, suc);
            cagg.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(Localidad_agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_direccion_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_direccion_clienteFocusLost
        txt_direccion_cliente.setText(txt_direccion_cliente.getText().toUpperCase());
    }//GEN-LAST:event_txt_direccion_clienteFocusLost

    private void txt_direccion_entregaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_direccion_entregaFocusLost
        txt_direccion_entrega.setText(txt_direccion_entrega.getText().toUpperCase());
    }//GEN-LAST:event_txt_direccion_entregaFocusLost

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
            java.util.logging.Logger.getLogger(Localidad_agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Localidad_agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Localidad_agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Localidad_agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Localidad_agregar dialog = new Localidad_agregar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_agregar;
    private javax.swing.JComboBox<String> cbx_ciudad;
    private javax.swing.JComboBox<String> cbx_pais;
    private javax.swing.JComboBox<String> cbx_provincia;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_direccion_cliente;
    private javax.swing.JTextField txt_direccion_entrega;
    // End of variables declaration//GEN-END:variables
}
