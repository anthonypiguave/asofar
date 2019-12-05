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
import ec.com.asofar.util.Formato_Numeros;
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
        txt_valor_min.setEditable(false);
        txtDescuent_valor.setEditable(false);
        txtidtarifario.setEditable(false);
        txtPrestacion.setEditable(false);
        txtUnidadServicio.setEditable(false);
        
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
        btnAgrgarPrestacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtidtarifario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_valor_min = new javax.swing.JTextField();
        txt_porcentaje_descuento = new javax.swing.JTextField();
        txtDescuent_valor = new javax.swing.JTextField();
        txt_valor_venta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_valor_costo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_valor_min_porcentaje = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPrestacion = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txtUnidadServicio.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        btnAgrgarPrestacion.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        btnAgrgarPrestacion.setText("+");
        btnAgrgarPrestacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrgarPrestacionActionPerformed(evt);
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

        txt_porcentaje_descuento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_porcentaje_descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_porcentaje_descuentoKeyReleased(evt);
            }
        });

        txtDescuent_valor.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtDescuent_valor.setMinimumSize(new java.awt.Dimension(6, 30));

        txt_valor_venta.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_valor_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_valor_ventaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valor_ventaKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("VALOR MINIMO DE VENTA:");

        txt_valor_costo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("VALOR DE COSTO :");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("VALOR DE VENTA:");

        txt_valor_min_porcentaje.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txt_valor_min_porcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valor_min_porcentajeKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("%");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("%");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(18, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_porcentaje_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescuent_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_valor_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(txt_valor_min_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_valor_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_valor_min, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_valor_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valor_min, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valor_min_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_valor_venta, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_porcentaje_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuent_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(txtUnidadServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtidtarifario))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgrgarPrestacion))
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtPrestacion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtidtarifario, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnAgrgarPrestacion))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtPrestacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUnidadServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
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

    public void CalcularValorMinimo() {
        double porcentajeMinVenta = Double.parseDouble(txt_valor_min_porcentaje.getText());
        
        double valorCosto = Double.parseDouble(txt_valor_costo.getText());
        
        double valor = (((valorCosto * porcentajeMinVenta) / 100)) + valorCosto;
        
        txt_valor_min.setText(String.format("%.2f", valor).replace(",", "."));
        
    }
    
    public void CalcularDescuento() {
        double porcentajeDescuento = Double.parseDouble(txt_porcentaje_descuento.getText());
        
        double valorVenta = Double.parseDouble(txt_valor_venta.getText());
        
        double valor = (((valorVenta * porcentajeDescuento) / 100));
        System.out.println(" valor" + valor);
        txtDescuent_valor.setText(String.format("%.2f", valor).replace(",", "."));
        
    }

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
        
        PrTarifarioPK prTarifarioPK = new PrTarifarioPK();
        prTarifarioPK.setIdEmpresa(emp.getIdEmpresa());
        prTarifarioPK.setIdSurcusal(suc.getSeSucursalPK().getIdSucursal());
        prTarifarioPK.setIdTarifario(tp.getPrTarifarioPK().getIdTarifario());
        
        PrTarifario prTarifario = new PrTarifario();
        prTarifario.setPrTarifarioPK(prTarifarioPK);
        pre.setPrTarifario(prTarifario);
        
        pre.setValorCosto(Double.parseDouble(txt_valor_costo.getText().toString()));
        pre.setValorDescuento(Double.parseDouble(txtDescuent_valor.getText()));
        pre.setValorVenta(Double.parseDouble(txt_valor_venta.getText()));
        pre.setValorMinVenta(Double.parseDouble(txt_valor_min.getText()));
        pre.setEstado("A");
        pre.setUsuarioCreacion(usu.getUsuario());
        pre.setIdPrestacion(BigInteger.valueOf(objpres.getPrPrestaciones().getIdPrestacion()));
        pre.setIdUnidadServicio(BigInteger.valueOf(objpres.getVeUnidadServicio().getIdUnidadServicio()));
        
        try {
            
            prp.create(pre);
            
            JOptionPane.showMessageDialog(null, " GUARDADO EXITOSAMENTE");
            setVisible(false);
        } catch (Exception e) {
            Logger.getLogger(Listar_PrestacionesPorServicio.class.getName()).log(Level.SEVERE, null, e);
        }
        

    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnAgrgarPrestacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrgarPrestacionActionPerformed
        ConsultaPrestacionesporServicio cp = new ConsultaPrestacionesporServicio(new javax.swing.JFrame(), true);
        cp.setVisible(true);
        objpres = cp.getObjeto();
        
        txtPrestacion.setText(objpres.getPrPrestaciones().getNombrePrestacion());
        txtUnidadServicio.setText(objpres.getVeUnidadServicio().getNombreUnidadServicio());
        
        InKardex objetoKardex = kardexExt.obtenerUltimoProductoKardex(objpres.getPrPrestaciones().getIdPoducto().longValue());
        txt_valor_costo.setText(String.format("%.2f", objetoKardex.getCostoPromedio()).replace(",", "."));

    }//GEN-LAST:event_btnAgrgarPrestacionActionPerformed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel2MouseDragged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
        setVisible(false);
        

    }//GEN-LAST:event_btnSalirActionPerformed

    private void txt_valor_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_ventaKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_valor_ventaKeyTyped

    private void txt_valor_minKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_minKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_valor_minKeyTyped

    private void txt_valor_min_porcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_min_porcentajeKeyReleased
        try {
            CalcularValorMinimo();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txt_valor_min_porcentajeKeyReleased

    private void txt_porcentaje_descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porcentaje_descuentoKeyReleased
        
        try {
            CalcularDescuento();
        } catch (Exception e) {
        }
        

    }//GEN-LAST:event_txt_porcentaje_descuentoKeyReleased

    private void txt_valor_ventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_ventaKeyReleased
        txt_porcentaje_descuento.setText("");
    }//GEN-LAST:event_txt_valor_ventaKeyReleased
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
    private javax.swing.JButton btnAgrgarPrestacion;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField txt_porcentaje_descuento;
    private javax.swing.JTextField txt_valor_costo;
    private javax.swing.JTextField txt_valor_min;
    private javax.swing.JTextField txt_valor_min_porcentaje;
    private javax.swing.JTextField txt_valor_venta;
    public javax.swing.JTextField txtidtarifario;
    // End of variables declaration//GEN-END:variables
}
