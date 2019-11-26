/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. ec.com.asofar.dao ec.com.asofar.dto
 */
package ec.com.asofar.views.DetallesTarifa;

import ec.com.asofar.dao.PrDetalleTarifarioJpaController;
import ec.com.asofar.dao.PrTarifarioJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InPrestacionesPorServicios;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrTarifario;
import ec.com.asofar.dto.PrTarifarioPK;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeUnidadServicio;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Alberto Mero
 */
public class AgregarNuevoDetalle extends javax.swing.JDialog {

    int x, y;
    PrPrestaciones pr = new PrPrestaciones();
    VeUnidadServicio ve = new VeUnidadServicio();
    PrDetalleTarifarioJpaController prp = new PrDetalleTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTarifarioJpaController op = new PrTarifarioJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTarifario tp = new PrTarifario();
    InKardexExt kardexExt = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());

    List< PrDetalleTarifario> listaTarifario;
    List<PrDetalleTarifario> lista = prp.findPrDetalleTarifarioEntities();

    InPrestacionesPorServicios objpres = new InPrestacionesPorServicios();
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    String CADENA = "";

    public AgregarNuevoDetalle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public AgregarNuevoDetalle(java.awt.Frame parent, boolean modal, PrTarifario tr, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        tp = tr;
        usu = us;
        emp = em;
        suc = su;

        txt_valor_costo.setEditable(false);
        
        cargar();

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
        txtUnidadServicio = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtidtarifario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_valor_min = new javax.swing.JTextField();
        txt_porcentaje = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDescuent_valor = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        txt_valor_venta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Btncalcular = new javax.swing.JButton();
        txt_valor_costo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPrestacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txtUnidadServicio.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel1.setText("PRESTACIONES POR UNIDAD DE SERVICIO:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("TARIFARIO:");

        txtidtarifario.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE DE PRESTACION:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE DE UNIDAD DE SERVICIO:");

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("VALOR DESCUENTO:");

        txt_valor_min.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_valor_min.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_valor_minKeyTyped(evt);
            }
        });

        txt_porcentaje.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_porcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_porcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_porcentajeKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("PORCENTAJE DESCUENTO:");

        txtDescuent_valor.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtDescuent_valor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuent_valorKeyTyped(evt);
            }
        });

        btnGrabar.setBackground(new java.awt.Color(254, 254, 254));
        btnGrabar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnGrabar.setForeground(new java.awt.Color(1, 1, 1));
        btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        btnGrabar.setText("GUARDAR");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        txt_valor_venta.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_valor_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_valor_ventaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("VALOR DE VENTA:");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("VALOR MINIMO DE VENTA:");

        jButton3.setBackground(new java.awt.Color(254, 254, 254));
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(1, 1, 1));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        jButton3.setText("SALIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Btncalcular.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        Btncalcular.setText("CALCULAR");
        Btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtncalcularActionPerformed(evt);
            }
        });

        txt_valor_costo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("VALOR DE COSTO :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_porcentaje, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(txtDescuent_valor)
                            .addComponent(txt_valor_venta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btncalcular))
                    .addComponent(txt_valor_min, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valor_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_valor_costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_valor_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescuent_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(Btncalcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_valor_min, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jLabel2.setBackground(new java.awt.Color(6, 162, 213));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AGREGAR NUEVO PRECIO");
        jLabel2.setOpaque(true);
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidadServicio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidtarifario))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(txtPrestacion, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtidtarifario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrestacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnidadServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed

        List<PrDetalleTarifario> list = prp.findPrDetalleTarifarioEntities();
        for (int i = 0; i < list.size(); i++) {
            PrDetalleTarifario deta = new PrDetalleTarifario();
            deta = list.get(i);

            if (list.get(i).getIdPrestacion() != null) {

                if (list.get(i).getIdPrestacion().intValue() == objpres.getInPrestacionesPorServiciosPK().getIdPrestacion()) {
                    deta.setEstado("I");
                    try {
                        prp.edit(deta);
                    } catch (Exception ex) {
                        Logger.getLogger(AgregarNuevoDetalle.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        PrDetalleTarifario pre = new PrDetalleTarifario();

//        ConsultaPrestacionesporServicio cp = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true);
//        objpres = cp.obtener89();
/////enviar el detalle tarifario a I donde la pretacion sea igua a la prestacion
        //que envias y el estado sea A
        PrTarifarioPK prTarifarioPK = new PrTarifarioPK();
        prTarifarioPK.setIdEmpresa(emp.getIdEmpresa());
        prTarifarioPK.setIdSurcusal(suc.getSeSucursalPK().getIdSucursal());
        prTarifarioPK.setIdTarifario(tp.getPrTarifarioPK().getIdTarifario());
//        prTarifarioPK.setIdTarifario(Long.valueOf(txtidtarifario.getText()));
        //setear compuestas
        PrTarifario prTarifario = new PrTarifario();
        prTarifario.setPrTarifarioPK(prTarifarioPK);
        pre.setPrTarifario(prTarifario);
        /*
        valor_costo
        valor_min_venta
        valor_ventavalor_descuento
         */
//        pre.setValorCosto(Double.parseDouble(txt_valor_costo.getText()));
//        pre.setValorMinVenta(Double.parseDouble(txt_valor_min.getText()));
        pre.setValorDescuento(Double.parseDouble(txtDescuent_valor.getText()));
        pre.setValorVenta(Double.parseDouble(txt_valor_venta.getText()));
        pre.setEstado("A");
        pre.setUsuarioCreacion(usu.getNombreUsuario());
        pre.setIdPrestacion(BigInteger.valueOf(objpres.getPrPrestaciones().getIdPrestacion()));
        pre.setIdUnidadServicio(BigInteger.valueOf(objpres.getVeUnidadServicio().getIdUnidadServicio()));

        try {

            prp.create(pre);
//            op.create(pr);

//            prp.create(pre);
            JOptionPane.showMessageDialog(null, " GUARDADO CON EXITO");
            setVisible(false);
        } catch (Exception e) {
            Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(Level.SEVERE, null, e);
        }


    }//GEN-LAST:event_btnGrabarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ConsultaPrestacionesporServicio cp = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true);
        cp.setVisible(true);
        objpres = cp.getObjeto();

        txtPrestacion.setText(objpres.getPrPrestaciones().getNombrePrestacion());
        txtUnidadServicio.setText(objpres.getVeUnidadServicio().getNombreUnidadServicio());

        InKardex objetoKardex = kardexExt.obtenerUltimoProductoKardex(objpres.getPrPrestaciones().getIdPoducto().longValue());
        txt_valor_costo.setText(String.format("%.2f", objetoKardex.getCostoPromedio()));

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        setVisible(false);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_valor_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_ventaKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_valor_ventaKeyTyped

    private void txt_porcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porcentajeKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_porcentajeKeyTyped

    private void txtDescuent_valorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuent_valorKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuent_valorKeyTyped

    private void txt_valor_minKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_minKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_valor_minKeyTyped

    private void BtncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtncalcularActionPerformed

        int entero1, entero2;

        Double cadena1 = Double.parseDouble(txt_valor_venta.getText());
        Integer cadena2 = Integer.valueOf(txt_porcentaje.getText());

        Double res = (cadena1 * cadena2) / 100;

        txtDescuent_valor.setText(res.toString());

    }//GEN-LAST:event_BtncalcularActionPerformed

    private void txt_porcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porcentajeKeyReleased
        int largor = txt_porcentaje.getText().length();
        if (largor > 2) {
            if (Integer.parseInt(txt_porcentaje.getText()) == 100) {
                CADENA = txt_porcentaje.getText();
                txt_porcentaje.setText(CADENA);
            } else {
                CADENA = txt_porcentaje.getText().substring(0, 2);
                txt_porcentaje.setText(CADENA);
                JOptionPane.showMessageDialog(null, "INGRESE VALOR CORRECTO");
            }
        }

    }//GEN-LAST:event_txt_porcentajeKeyReleased
    public void cargar() {
        txtidtarifario.setText(String.valueOf(tp.getDescripcion()));
//        emp1.setText(String.valueOf(tp.getPrTarifarioPK().getIdEmpresa()));
//        sucur.setText(String.valueOf(tp.getPrTarifarioPK().getIdSurcusal()));
//        USER.setText(String.valueOf(tp.getUsuarioCreacion()));
    }

    public void ValiEstado() {
        PrDetalleTarifario pre = new PrDetalleTarifario();

        for (int i = 0; i < listaTarifario.size(); i++) {
            if (listaTarifario.get(i).getEstado().equals("A")) {
                if (listaTarifario.get(i).getIdPrestacion().equals(listaTarifario.get(i).getIdPrestacion())) {

                    pre.setEstado("I");

                } else {

                    System.out.println("no se encontro otra prestacion activa");

                }

            }

        }

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
            java.util.logging.Logger.getLogger(AgregarNuevoDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarNuevoDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarNuevoDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarNuevoDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarNuevoDetalle dialog = new AgregarNuevoDetalle(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Btncalcular;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtDescuent_valor;
    private javax.swing.JTextField txtPrestacion;
    private javax.swing.JTextField txtUnidadServicio;
    private javax.swing.JTextField txt_porcentaje;
    private javax.swing.JTextField txt_valor_costo;
    private javax.swing.JTextField txt_valor_min;
    private javax.swing.JTextField txt_valor_venta;
    public javax.swing.JTextField txtidtarifario;
    // End of variables declaration//GEN-END:variables
}
