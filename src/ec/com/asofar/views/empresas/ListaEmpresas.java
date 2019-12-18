package ec.com.asofar.views.empresas;

import ec.com.asofar.dao.SeEmpresaJpaController;
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

public class ListaEmpresas extends javax.swing.JDialog {

    int x, y;
    SeEmpresa obj;
    List<SeEmpresa> listaEmpresa;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    SeEmpresaJpaController empresaController = new SeEmpresaJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;

    public ListaEmpresas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarTabla();

    }

    public ListaEmpresas(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        us1 = us;
        em1 = em;
        su1 = su;
        cargarTabla();

    }

    public void cargarTabla() {

        listaEmpresa = new ArrayList<SeEmpresa>();

        listaEmpresa = empresaController.findSeEmpresaEntities();
        Tablas.listarEmpresa(listaEmpresa, jtPersonas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPersonas = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        jtPersonas.setModel(new javax.swing.table.DefaultTableModel(
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
        jtPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtPersonasMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPersonasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtPersonas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPRESAS");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        IngresarEmpresas ru = new IngresarEmpresas(new javax.swing.JFrame(), true, us1, em1, su1);
        ru.setVisible(true);
        cargarTabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPersonasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPersonasMouseClicked

    private void jtPersonasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPersonasMousePressed
        int id = 0;
        obj = null;
        if (evt.getClickCount() == 2) {
            id = jtPersonas.getSelectedRow();
            for (int i = 0; i < listaEmpresa.size(); i++) {
                if ((jtPersonas.getValueAt(id, 0).toString().equals(listaEmpresa.get(i).getRuc()))) {
                    obj = listaEmpresa.get(i);
                    if (obj != null) {

                        ActualizarDatosEmpresas es = new ActualizarDatosEmpresas(new javax.swing.JFrame(), true, obj, us1, em1, su1);
                        setVisible(false);
                        es.setVisible(true);
                    }
                }
            }

        }
    }//GEN-LAST:event_jtPersonasMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListaEmpresas dialog = new ListaEmpresas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtPersonas;
    // End of variables declaration//GEN-END:variables
}
