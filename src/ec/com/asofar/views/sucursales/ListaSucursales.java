package ec.com.asofar.views.sucursales;

import ec.com.asofar.dao.SeSucursalJpaController;
import ec.com.asofar.dto.SeEmpresa;

import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ListaSucursales extends javax.swing.JDialog {

    String valor;
    int x, y;
    SeSucursal obj;
    List<SeSucursal> listaSucursal;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    SeSucursalJpaController sucursalController
            = new SeSucursalJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;
    SeSucursal objeto = new SeSucursal();

    public ListaSucursales(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarTabla();

    }

    public ListaSucursales(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        us1 = us;
        em1 = em;
        su1 = su;
        cargarTabla();

    }

    public void cargarTabla() {

        listaSucursal = new ArrayList<SeSucursal>();
        listaSucursal = sucursalController.findSeSucursalEntities();
        Tablas.listarSucursal(listaSucursal, tablaSucursal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSucursal = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        SUCURSAL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 326));

        btnSalir.setBackground(new java.awt.Color(254, 254, 254));
        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(1, 1, 1));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 2));

        tablaSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaSucursalMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSucursalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSucursal);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/nuevo_Mesa de trabajo 1.png"))); // NOI18N
        jButton2.setText("NUEVO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        SUCURSAL.setBackground(java.awt.Color.red);
        SUCURSAL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        SUCURSAL.setForeground(new java.awt.Color(254, 254, 254));
        SUCURSAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SUCURSAL.setText("SUCURSALES");
        SUCURSAL.setOpaque(true);
        SUCURSAL.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                SUCURSALMouseDragged(evt);
            }
        });
        SUCURSAL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SUCURSALMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR:");

        txtfiltro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SUCURSAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 86, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(SUCURSAL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        int r = JOptionPane.showConfirmDialog(null, "¿DESEA SALIR?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            setVisible(false);

//            asdfg12345
        } else {

        }

    }//GEN-LAST:event_btnSalirActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IngresarSucursal ru = new IngresarSucursal(new javax.swing.JFrame(), true, us1, em1, su1);
        ru.setVisible(true);
        cargarTabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSucursalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSucursalMouseClicked

//    public SeSucursal getObjeto() {
//        return objeto;
//    }
//
//    public SeSucursal devuelveObjeto(String datos, List<SeSucursal> listaobjeto) {
//
//        SeSucursal objeto1 = null;
//
//        for (int i = 0; i < listaobjeto.size(); i++) {
//
//            if (datos.equals("" + listaobjeto.get(i).getSeSucursalPK().getIdSucursal())) {
//                objeto1 = listaobjeto.get(i);
//
//                break;
//
//            }
//
//        }
//
//        return objeto1;
//
//    }

    private void tablaSucursalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSucursalMousePressed
        int id = 0;
        obj = null;
        if (evt.getClickCount() == 2) {
            id = tablaSucursal.getSelectedRow();
            for (int i = 0; i < listaSucursal.size(); i++) {
                if ((tablaSucursal.getValueAt(id, 0).toString().equals(listaSucursal.get(i).getNombreComercial()))) {
                    obj = listaSucursal.get(i);

                }
            }
            if (obj != null) {

                ActualizarDatosSucursal es = new ActualizarDatosSucursal(new javax.swing.JFrame(), true, obj, us1, em1, su1);
//                        setVisible(false);
                es.setVisible(true);
            }
            listaSucursal
                    = sucursalController.findSeSucursalEntities();
            Tablas.listarSucursal(listaSucursal, tablaSucursal);

        }

//        int i = 0;
//        String msg = null;
//        if (evt.getClickCount() == 2) {
//            i = tablaSucursal.getSelectedRow();
//            obj = devuelveObjeto(tablaSucursal.getValueAt(i, 0).toString(), listaSucursal);
//
//            if (obj != null) {
//                this.setVisible(false);
//            }
//            listaSucursal
//                            = sucursalController.findSeSucursalEntities();
//                    Tablas.listarSucursal(listaSucursal, tablaSucursal);
//        }
    }//GEN-LAST:event_tablaSucursalMousePressed

    private void SUCURSALMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SUCURSALMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_SUCURSALMousePressed

    private void SUCURSALMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SUCURSALMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_SUCURSALMouseDragged

    private void txtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyReleased
        valor = txtfiltro.getText();
        Tablas.filtro(valor, tablaSucursal);
    }//GEN-LAST:event_txtfiltroKeyReleased

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltroKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListaSucursales dialog = new ListaSucursales(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel SUCURSAL;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaSucursal;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
