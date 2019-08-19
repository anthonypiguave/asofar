/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.DetallesTarifa;

import ec.com.asofar.views.prestacionesporservicios.*;
import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.InPrestacionesPorServiciosPK;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Humbertoezequiel
 */
public class ConsultaPrestacionesporServicio extends javax.swing.JDialog {

    int x, y;
    InPrestacionesPorServicios objpres = new InPrestacionesPorServicios();
    String valor;
    /**
     * Creates new form ConsultaPrestacionesporServicio
     */
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    List<InPrestacionesPorServicios> listapresporserv;
    InPrestacionesPorServiciosJpaController preposer = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
    Long id_Pres, id_Uni;
    String prestacion, unidad;

    PrPrestaciones pr = new PrPrestaciones();
    VeUnidadServicio ve = new VeUnidadServicio();

    public ConsultaPrestacionesporServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listapresporserv = preposer.findInPrestacionesPorServiciosEntities();
        Tablas.TablaPrestacionesPorServicios(listapresporserv, tba_prestacionesporservicios);
    }

    public ConsultaPrestacionesporServicio(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        listapresporserv = preposer.findInPrestacionesPorServiciosEntities();
        Tablas.TablaPrestacionesPorServicios(listapresporserv, tba_prestacionesporservicios);
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
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_prestacionesporservicios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel4.setBackground(new java.awt.Color(255, 102, 0));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRESTACIONES POR UNIDADES DE SERVICIO");
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

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tba_prestacionesporservicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_prestacionesporservicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_prestacionesporserviciosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tba_prestacionesporservicios);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR:");

        txtfiltro.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(173, 42, 48));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(218, 218, 218))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void tba_prestacionesporserviciosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_prestacionesporserviciosMousePressed
        int i = 0;
        if (evt.getClickCount() == 2) {

            i = tba_prestacionesporservicios.getSelectedRow();

            id_Pres = Long.valueOf(tba_prestacionesporservicios.getValueAt(i, 0).toString());
            id_Uni = Long.valueOf(tba_prestacionesporservicios.getValueAt(i, 2).toString());
         
            prestacion = tba_prestacionesporservicios.getValueAt(i, 1).toString();
            unidad = tba_prestacionesporservicios.getValueAt(i, 3).toString();
            if (objpres != null) {
                obtener89();
//                System.out.println("hhh" +  obtener());
//                System.out.println("p"+obtenerP());
//                obtenerP();
//                System.out.println("v"+obtenerV());
//                obtenerV();
                this.setVisible(false);

            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tba_prestacionesporserviciosMousePressed

    private void txtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyReleased
        valor = txtfiltro.getText();
        Tablas.filtro(valor, tba_prestacionesporservicios);
    }//GEN-LAST:event_txtfiltroKeyReleased

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public InPrestacionesPorServicios obtener89() {
//        InPrestacionesPorServicios obj = new InPrestacionesPorServicios();
//        obj.setInPrestacionesPorServiciosPK(new InPrestacionesPorServiciosPK());
//        obj.getInPrestacionesPorServiciosPK().setIdPrestacion(id_Pres);
//        System.out.println("obj "+obj.getInPrestacionesPorServiciosPK().getIdUnidadServicio());
//        obj.getInPrestacionesPorServiciosPK().setIdUnidadServicio(id_Uni);
        InPrestacionesPorServicios obj = new InPrestacionesPorServicios();
        try {

            obj.setInPrestacionesPorServiciosPK(new InPrestacionesPorServiciosPK());
            obj.getInPrestacionesPorServiciosPK().setIdPrestacion(id_Pres);
            obj.getInPrestacionesPorServiciosPK().setIdUnidadServicio(id_Uni);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return obj;
    }

    public PrPrestaciones obtenerP() {
        PrPrestaciones objp = new PrPrestaciones();
        objp.setNombrePrestacion(prestacion);
        return objp;
    }

    public VeUnidadServicio obtenerV() {
        VeUnidadServicio vep = new VeUnidadServicio();
        vep.setNombreUnidadServicio(unidad);

        return vep;
    }
//    public InPrestacionesPorServicios obteneridP() {
//        InPrestacionesPorServicios objp = new InPrestacionesPorServicios();
//        objp.getInPrestacionesPorServiciosPK().setIdPrestacion(id_Pres);
//        return objp;
//    }

//     public InPrestacionesPorServicios obteneridU() {
//        InPrestacionesPorServicios objp = new InPrestacionesPorServicios();
//        objp.getInPrestacionesPorServiciosPK().setIdPrestacion(id_Uni);
//        return objp;
//    }
//    public InPrestacionesPorServicios devuelveObjeto(String datos, List<InPrestacionesPorServicios> listaobjeto) {
//
//        InPrestacionesPorServicios objeto1 = null;
//
//        for (int i = 0; i < listaobjeto.size(); i++) {
//
//            if (datos.equals("" + listaobjeto.get(i).getPrTarifario().getPrTarifarioPK().getIdTarifario())) {
//                objeto1 = listaobjeto.get(i);
//
//                break;
//
//            }
//
//        }
//        return objeto1;
//
//    }
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
            java.util.logging.Logger.getLogger(ConsultaPrestacionesporServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaPrestacionesporServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaPrestacionesporServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaPrestacionesporServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConsultaPrestacionesporServicio dialog = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tba_prestacionesporservicios;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
