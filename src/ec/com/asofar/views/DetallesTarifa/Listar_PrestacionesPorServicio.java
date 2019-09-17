/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.DetallesTarifa;

import ec.com.asofar.dao.CoDetallesTarifaJpaController;
import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dao.PrDetalleTarifarioJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dto.CoDetallesTarifa;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTarifarioPK;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;

/**
 *
 * @author Luis Alberto Mero
 */
public class Listar_PrestacionesPorServicio extends javax.swing.JDialog {

    PrDetalleTarifarioJpaController PPS = new PrDetalleTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<PrDetalleTarifario> lista = PPS.findPrDetalleTarifarioEntities();
    String valor = "";
    int x, y;
    AgregarNuevoDetalle pre = new AgregarNuevoDetalle(new javax.swing.JFrame(), true);
    PrDetalleTarifario prestacionesPPS = new PrDetalleTarifario();
    PrDetalleTarifario obj;
    List<PrDetalleTarifario> prestacionPPS;
    PrDetalleTarifario prcPPS;
    PrDetalleTarifario prpkPPS;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    PrTarifario tp = new PrTarifario();
    PrPrestacionesJpaController Pc = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<PrPrestaciones> listaP;

    /**
     * Creates new form Listar_PrestacionesPorServicio
     */
    public Listar_PrestacionesPorServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarTabla2();
    }

    public Listar_PrestacionesPorServicio(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, PrTarifario tr) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);

        usu = us;
        emp = em;
        suc = su;

        tp = tr;
        MostrarTabla2();
//       listarPrestacionesDetalleTarifario
    }

    public void MostrarTabla2() {
        try {
            listaP = Pc.findPrPrestacionesEntities();
            lista = PPS.findPrDetalleTarifarioEntities();
            Tablas.listarPrestacionesDetalleTarifario(lista, listaP, tblPrestacionesPorServicios);
        } catch (Exception e) {
        }
    }

    public void MostrarTabla() {
        try {
            listaP = Pc.findPrPrestacionesEntities();
            lista = PPS.findPrDetalleTarifarioEntities();
            Tablas.listarDetalleTarifarios(lista, tblPrestacionesPorServicios, tp, listaP);
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtfiltro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrestacionesPorServicios = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txtfiltro.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR:");

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 0));
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LISTA DE PRESTACIONES POR SERVICIOS");
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

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tblPrestacionesPorServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPrestacionesPorServicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPrestacionesPorServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPrestacionesPorServiciosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPrestacionesPorServicios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton2.setBackground(new java.awt.Color(173, 42, 48));
        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(254, 254, 254));
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void txtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyReleased
        valor = txtfiltro.getText();
        Tablas.filtro(valor, tblPrestacionesPorServicios);        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroKeyReleased

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();        // TODO add your handling code here:
        }
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void tblPrestacionesPorServiciosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestacionesPorServiciosMousePressed

//        int i = 0;
//        if (evt.getClickCount() == 2) {
//
//            i = tblPrestacionesPorServicios.getSelectedRow();
//            prestacionesPPS = devuelveObjeto(tblPrestacionesPorServicios.getValueAt(i, 0).toString(), lista);
//            System.out.println("este ide es :" + prestacionesPPS.getPrTarifario().getPrTarifarioPK().getIdTarifario());
//            pre.txtidtarifario.setText(String.valueOf(prestacionesPPS.getPrTarifario().getPrTarifarioPK().getIdTarifario()));
//            if (prestacionesPPS != null) {
//                this.setVisible(false);
//                AgregarNuevoDetalle cp = new AgregarNuevoDetalle(new javax.swing.JFrame(), true);
//                cp.setVisible(true);
//            }
//
//        }

    }//GEN-LAST:event_tblPrestacionesPorServiciosMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AgregarNuevoDetalle cp = new AgregarNuevoDetalle(new javax.swing.JFrame(), true, tp, usu, emp, suc);
        cp.setVisible(true);
        MostrarTabla2();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    public PrDetalleTarifario getObji() {
        return obj;
    }

    public void setObji(PrDetalleTarifario obj) {
        this.obj = obj;
    }

    public PrDetalleTarifario getPrDetalleTarifario() {
        return prestacionesPPS;
    }

    public PrDetalleTarifario devuelveObjeto(String datos, List<PrDetalleTarifario> listaobjeto) {

        PrDetalleTarifario objeto1 = null;

        for (int i = 0; i < listaobjeto.size(); i++) {

            if (datos.equals("" + listaobjeto.get(i).getPrTarifario().getPrTarifarioPK().getIdTarifario())) {
                objeto1 = listaobjeto.get(i);

                break;

            }

        }

        return objeto1;

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
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Listar_PrestacionesPorServicio dialog = new Listar_PrestacionesPorServicio(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPrestacionesPorServicios;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
