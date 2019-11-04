/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.prestacionesporservicios;

import ec.com.asofar.dao.InPrestacionesPorServiciosJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.VeUnidadServicioJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.ValidarDTO;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.views.prestaciones.Consulta_prestaciones;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;

public class AgregarPrestaciones extends javax.swing.JDialog {

    int x, y;
    InPrestacionesPorServicios pol1 = new InPrestacionesPorServicios();
    PrPrestaciones pol2 = new PrPrestaciones();
    PrPrestacionesJpaController pr = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<PrPrestaciones> pr2;
    InPrestacionesPorServiciosJpaController pxs = new InPrestacionesPorServiciosJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<InPrestacionesPorServicios> lppus;
    VeUnidadServicioJpaController us1 = new VeUnidadServicioJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<VeUnidadServicio> us2;
    PrPrestaciones proc;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    Long id;

    public AgregarPrestaciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal = false);
        initComponents();
        setLocationRelativeTo(null);
        pr2 = pr.findPrPrestacionesEntities();
        us2 = us1.findVeUnidadServicioEntities();

    }

    public AgregarPrestaciones(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal = false);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);

        pr2 = pr.findPrPrestacionesEntities();
        us2 = us1.findVeUnidadServicioEntities();
        usu = us;
        emp = em;
        suc = su;
        cbxUnidadServicio();
    }

    public void cbxUnidadServicio() {
        for (int i = 0; i < us2.size(); i++) {
            cbxunidadservicio.addItem(us2.get(i).getNombreUnidadServicio());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxunidadservicio = new javax.swing.JComboBox<>();
        jguardarpreser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxfacturable = new javax.swing.JComboBox<>();
        cbxaplicadescuento1 = new javax.swing.JComboBox<>();
        jsalir = new javax.swing.JButton();
        jbotonbuscar = new javax.swing.JToggleButton();
        txtpresta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("NOMBRE:");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("UNIDAD DE SERVICIO:");

        cbxunidadservicio.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jguardarpreser.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jguardarpreser.setForeground(new java.awt.Color(74, 126, 72));
        jguardarpreser.setText("GUARDAR");
        jguardarpreser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jguardarpreserActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("FACTURABLE:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("APLICA DESCUENTO:");

        cbxfacturable.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxfacturable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        cbxaplicadescuento1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxaplicadescuento1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        jsalir.setBackground(new java.awt.Color(160, 52, 58));
        jsalir.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jsalir.setForeground(new java.awt.Color(254, 254, 254));
        jsalir.setText("SALIR");
        jsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsalirActionPerformed(evt);
            }
        });

        jbotonbuscar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jbotonbuscar.setText("BUSCAR");
        jbotonbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbotonbuscarActionPerformed(evt);
            }
        });

        txtpresta.setEditable(false);
        txtpresta.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 102, 0));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRESTACIONES - SERVICIO");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxfacturable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxunidadservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxaplicadescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtpresta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbotonbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(401, Short.MAX_VALUE)
                .addComponent(jguardarpreser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtpresta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbotonbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxunidadservicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxfacturable, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxaplicadescuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsalir)
                    .addComponent(jguardarpreser))
                .addContainerGap(41, Short.MAX_VALUE))
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


    private void jguardarpreserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jguardarpreserActionPerformed
        InPrestacionesPorServicios objPresxServ = new InPrestacionesPorServicios();
        InPrestacionesPorServicios objPresxServ2 = new InPrestacionesPorServicios();
        VeUnidadServicio vus = new VeUnidadServicio();
        java.util.Date fechaActual = new java.util.Date();

        vus = ObtenerDTO.ObtenerVeUnidadServicio(cbxunidadservicio.getSelectedItem().toString());
        objPresxServ2 = ObtenerDTO.ObtenerInPrestacionesPorServicios("A");

        objPresxServ.setPrPrestaciones(proc);
        objPresxServ.setVeUnidadServicio(vus);
        objPresxServ.setEstado("A");
        objPresxServ.setEsFacturable(cbxfacturable.getSelectedItem().toString());
        objPresxServ.setAplicaDescuento(cbxaplicadescuento1.getSelectedItem().toString());
        objPresxServ.setUsuarioCreacion(usu.getIdUsuario());
        objPresxServ.setFechaCreacion(fechaActual);
        try {
            pxs.create(objPresxServ);
            JOptionPane.showMessageDialog(null, " GUARDADO CON EXITO");
            setVisible(false);
            ConsultaPrestacionesporServicio cps = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true, usu, emp, suc);
            cps.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AgregarPrestaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jguardarpreserActionPerformed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void jsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsalirActionPerformed

        setVisible(false);
        ConsultaPrestacionesporServicio cps = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true, usu, emp, suc);
        cps.setVisible(true);

    }//GEN-LAST:event_jsalirActionPerformed

    private void jbotonbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbotonbuscarActionPerformed
        setVisible(true);
        ConsultaPrestacion cp = new ConsultaPrestacion(new javax.swing.JFrame(), true);
        cp.setVisible(true);

        proc = cp.getPrestacion();
        txtpresta.setText(proc.getNombrePrestacion());
        id = proc.getIdPrestacion();
//        cbxunidadservicio.removeAllItems();
        cargarCbxUnidad(id);
    }//GEN-LAST:event_jbotonbuscarActionPerformed
    public void cargarCbxUnidad(Long idPrestacion) {

        lppus = pxs.findInPrestacionesPorServiciosEntities();
        String valor = null;

        for (int i = 0; i < lppus.size(); i++) {
            if (idPrestacion == lppus.get(i).getPrPrestaciones().getIdPrestacion()) {
                Long idG = lppus.get(i).getVeUnidadServicio().getIdUnidadServicio();
                System.out.println("guardado solo 1 ");
                cbxunidadservicio.removeAllItems();
                nombreUnidad(idG);
            }
//            else //                if (idPrestacion != lppus.get(i).getPrPrestaciones().getIdPrestacion())
//            {
//                valor = "no";
//
//                nombreUnidad(valor);
//            }
        }
    }

    public void nombreUnidad(Long idGuardado) {
        for (int i = 0; i < us2.size(); i++) {

            if (idGuardado != us2.get(i).getIdUnidadServicio()) {
                cbxunidadservicio.addItem(us2.get(i).getNombreUnidadServicio());
            }

        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarPrestaciones dialog = new AgregarPrestaciones(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbxaplicadescuento1;
    private javax.swing.JComboBox<String> cbxfacturable;
    private javax.swing.JComboBox<String> cbxunidadservicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jbotonbuscar;
    private javax.swing.JButton jguardarpreser;
    private javax.swing.JButton jsalir;
    public javax.swing.JTextField txtpresta;
    // End of variables declaration//GEN-END:variables
}
