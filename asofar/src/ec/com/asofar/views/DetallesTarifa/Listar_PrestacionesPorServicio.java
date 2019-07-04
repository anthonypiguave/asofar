/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.DetallesTarifa;


import ec.com.asofar.dao.CoDetallesTarifaJpaController;
import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dto.CoDetallesTarifa;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Alberto Mero
 */
public class Listar_PrestacionesPorServicio extends javax.swing.JDialog {

    
    CoDetallesTarifaJpaController PPS = new CoDetallesTarifaJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<CoDetallesTarifa> lista = PPS.findCoDetallesTarifaEntities();
    String valor = "";
    int x, y;
    CoDetallesTarifa prestacionesPPS = new CoDetallesTarifa();
    CoDetallesTarifa objetoPPS;
    List<CoDetallesTarifa> prestacionPP;
    CoDetallesTarifa prcPPS;
    CoDetallesTarifa prpkPPS;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
 
    /**
     * Creates new form Listar_PrestacionesPorServicio
     */
    
    
    public Listar_PrestacionesPorServicio(java.awt.Frame parent, boolean modal) {
       super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);     
        System.out.println("************");
        Tablas.listarDetalleTarifario(lista, tblPrestacionesPorServicios);
    }

   public Listar_PrestacionesPorServicio(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarTabla();
        usu = us;
        emp = em;
        suc = su;
       MostrarTabla();
    }
   public void MostrarTabla() {
        try {

            lista = PPS.findCoDetallesTarifaEntities();
            Tablas.listarDetalleTarifario(lista, tblPrestacionesPorServicios);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrestacionesPorServicios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE PRESTACIONES POR SERVICIOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR:");

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 0));
        jButton1.setText("AGREGAR");

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
        tblPrestacionesPorServicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPrestacionesPorServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPrestacionesPorServiciosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPrestacionesPorServicios);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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
        if(Character.isSpaceChar(c)){
            getToolkit().beep();
            evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroKeyTyped
    }
    private void tblPrestacionesPorServiciosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestacionesPorServiciosMousePressed
   int id = 0;
        if (evt.getClickCount() == 2) {
            System.out.println("holamundo");
            id = tblPrestacionesPorServicios.getSelectedRow();
            prestacionesPPS = devuelveObjeto(Long.valueOf(tblPrestacionesPorServicios.getValueAt(id, 0).toString()), lista);
            if (prestacionesPPS != null) {
                System.out.println("holamundo2w");
                Nuevotarifario ep = new Nuevotarifario(new javax.swing.JFrame(), true, prestacionesPPS,usu,emp,suc);
                ep.setVisible(true);
                
                prestacionPP = PPS.findCoDetallesTarifaEntities();
                Tablas.listarDetalleTarifario(prestacionPP, tblPrestacionesPorServicios);
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPrestacionesPorServiciosMousePressed
    
    public CoDetallesTarifa devuelveObjeto(Long id, List<CoDetallesTarifa> listabod) {
        CoDetallesTarifa doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getInPrestacionesPorServicios().getPrPrestaciones().getIdPrestacion(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    public InPrestacionesPorServicios devuelvePrestacionPorServicio(Long id, List<InPrestacionesPorServicios> listapre) {
        InPrestacionesPorServicios doc = null;
        for (int i = 0; i < listapre.size(); i++) {
            if (Objects.equals(listapre.get(i).getInPrestacionesPorServiciosPK(), id)) {
                doc = listapre.get(i);
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
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Listar_PrestacionesPorServicio dialog = new Listar_PrestacionesPorServicio(new javax.swing.JFrame(),true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPrestacionesPorServicios;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables
}
