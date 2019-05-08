
package ec.com.asofar.views.usuario;

//import com.farmacia.conponentes.Filtros_modulo_seguridad;
//import com.farmacia.conponentes.Tablas;
//import com.farmacia.dao.CRUD;
//import com.farmacia.entities1.ClaseReporte;
//import com.farmacia.entities1.Listar_usuario;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JRViewer;

public class Mostrar_usuario extends javax.swing.JDialog {
    int x,y;
//    CRUD crud = new CRUD();
//    ArrayList<Listar_usuario> listar = null;
//    ArrayList<Listar_usuario> listar2 = null;
//    Listar_usuario objeto = null;
//    Filtros_modulo_seguridad fil = new Filtros_modulo_seguridad();
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    public Mostrar_usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//        listar = crud.get_listar_usuario();
//        Tablas.cargarJoinUsuario(jtUsuario, listar);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnReporte = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPersonas = new javax.swing.JTable();
        txtFiltro = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnReporte.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnReporte.setText("IMPRIMIR");
        btnReporte.setToolTipText("");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPersonasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtPersonasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtPersonas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        txtFiltro.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFiltroFocusLost(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        jButton2.setText("NUEVO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PERSONAS");
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
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(387, 387, 387)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(303, 303, 303))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public Listar_usuario devuelveObjeto(String datos, ArrayList<Listar_usuario> listarobj) {
//        Listar_usuario objeto1 = null;
//        for (int i = 0; i < listarobj.size(); i++) {
//            if (datos.equals(listarobj.get(i).getId_sesion().toString())) {
//                objeto1 = listarobj.get(i);
//                break;
//            }
//        }
//        return objeto1;
//    }
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //System.exit(0);
        int r = JOptionPane.showConfirmDialog(null, "¿Desea salir del módulo usuario?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
//            System.exit(0);
            setVisible(false);
        } else {
    }
        
        
    }//GEN-LAST:event_btnSalirActionPerformed

//    public void filtroUsuario(){
//        String f = txtFiltro.getText().toUpperCase();
//        int pos = cbFiltro.getSelectedIndex();
//        Listar_usuario lu = new Listar_usuario();
//        
//        
//            if (f=="" && pos == 0) {
//            listar = crud.get_listar_usuario();
//            Tablas.cargarJoinUsuario(jtUsuario, listar);
//            //JOptionPane.showMessageDialog(this, "por favor seleccione un filtro");
//        }
//        if (pos == 1) {
//            lu.setId_sesion(Long.valueOf(f));
//            listar2 = crud.filtroCodigoUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "el usuario no existe");
//            }
//        }
//        if (pos == 2) {
//            lu.setCedula(f);
//            listar2 = crud.filtroCedulaUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "el usuario no existe");
//            }
//        }
//        if (pos == 3) {
//            lu.setApellidos(f);
//            listar2 = crud.filtroApellidoUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "el usuario no existe");
//            }
//        }
//        if (pos == 4) {
//            lu.setFecha_registro(f);
//            listar2 = crud.filtroFechaUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "Asegurese de escribir correctamente "
//                        + "el formato de fecha: AAAA-MM-DD o el usuario no existe");
//            }
//        }
//        if (pos == 5) {
//            lu.setId_estado(Long.valueOf("1"));
//            listar2 = crud.filtroEstadoUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "No hay usuarios activos");
//                txtFiltro.setText("");
//            }
//        }
//        if (pos == 6) {
//            lu.setId_estado(Long.valueOf("2"));
//            listar2 = crud.filtroEstadoUs(lu);
//            Tablas.cargarJoinUsuario(jtUsuario, listar2);
//            if (jtUsuario.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "No hay usuarios inactivos");
//                txtFiltro.setText("");
//            }
//        }
//    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
//    filtroUsuario();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
//        String query = "";
//        query = fil.comboTodoUsuario();
//        listar = crud.filtroBusquedaUsuario(query);
//        Tablas.cargarJoinUsuario(jtUsuario, listar);
//        query = "";
//        txtFiltro.setText("");
    }//GEN-LAST:event_btnListarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        Registrar_usuario ru = new Registrar_usuario(new javax.swing.JFrame(), true);
//        ru.setVisible(true);
////        ipv.clear();
//        listar = crud.get_listar_usuario();
//        Tablas.cargarJoinUsuario(jtUsuario, listar);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
//        ArrayList tablac = new ArrayList();
//        for(int i=0;i<jtUsuario.getRowCount();i++){
//            ClaseReporte tabla1 = new ClaseReporte(jtUsuario.getValueAt(i,0).toString(),
//                    jtUsuario.getValueAt(i,1).toString(),
//                    jtUsuario.getValueAt(i,2).toString(),
//                    jtUsuario.getValueAt(i,3).toString(),
//                    jtUsuario.getValueAt(i,4).toString(),
//                    String.valueOf(jtUsuario.getValueAt(i,5)),
//                    jtUsuario.getValueAt(i,6).toString(),
//                    String.valueOf(jtUsuario.getValueAt(i,7)),
//                    jtUsuario.getValueAt(i,8).toString());                   
//            tablac.add(tabla1);}
//        try{
//            String dir = System.getProperty("user.dir")+"/Reportes/"+"Mostrar_usuario.jasper";
//            JasperReport reporte = (JasperReport) JRLoader.loadObject(dir);
//            JasperPrint jprint = JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(tablac));
//            JDialog frame = new JDialog (this);
//            JRViewer viewer = new JRViewer(jprint);
//            frame.add(viewer);
//            frame.setSize(new Dimension(ancho/2,alto/2));
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//            viewer.setFitWidthZoomRatio();
//        } catch (JRException ex) {
//            Logger.getLogger(Mostrar_usuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void jtPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPersonasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtPersonasMouseClicked

    private void jtUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtUsuarioMousePressed
//        int i = 0;
//        try {
//            if (evt.getClickCount() == 2) {
//                i = jtUsuario.getSelectedRow();
//                objeto = devuelveObjeto(jtUsuario.getValueAt(i, 0).toString(), listar);
//                if (objeto != null) {
//                    System.out.println("holaaaaa");
//                    actualizar_usuario acc = new actualizar_usuario(new javax.swing.JFrame(), true, objeto);
//                    acc.setVisible(true);
//                    listar.clear();
//                    listar = crud.get_listar_usuario();
//                    Tablas.cargarJoinUsuario(jtUsuario, listar);
//                }
//
//            }
//        } catch (Exception e) {
//            Logger.getLogger(Mostrar_usuario.class.getName()).log(Level.SEVERE, null, e);
//        }
    }//GEN-LAST:event_jtUsuarioMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void txtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFiltroFocusLost
        txtFiltro.setText(txtFiltro.getText().toUpperCase());
    }//GEN-LAST:event_txtFiltroFocusLost

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
            java.util.logging.Logger.getLogger(Mostrar_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrar_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrar_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrar_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mostrar_usuario dialog = new Mostrar_usuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtPersonas;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
