/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.itemscotizacion;

import ec.com.asofar.dao.CoDetItemsCotizacionJpaController;
import ec.com.asofar.dao.InEstadosMovimientoJpaController;
import ec.com.asofar.dao.InTipoCompraJpaController;
import ec.com.asofar.dao.InTipoDepartamentoJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.daoext.modificarDatosDocumentoExt;
import ec.com.asofar.dto.CoDetItemsCotizacion;
//import static ec.com.asofar.dto.CoDetItemsCotizacion_.coDetItemsCotizacionPK;
import ec.com.asofar.dto.InEstadosMovimiento;
import ec.com.asofar.dto.InTipoBodega;
import ec.com.asofar.dto.InTipoCompra;
import ec.com.asofar.dto.InTipoDepartamento;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Formulario;
import ec.com.asofar.util.Tablas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin1
 */
public class itemCotizacionForm extends javax.swing.JDialog {

    EntityManagerFactory conn = EntityManagerUtil.ObtenerEntityManager();
    InTipoDepartamento departamento = new InTipoDepartamento();
    InTipoDepartamentoJpaController jdepartamento = new InTipoDepartamentoJpaController(conn);
    InTipoCompra compra = new InTipoCompra();
    InTipoCompraJpaController jcompra = new InTipoCompraJpaController(conn);
    InEstadosMovimiento estados = new InEstadosMovimiento();
    InEstadosMovimientoJpaController jestados = new InEstadosMovimientoJpaController(conn);
    InTipoDocumento documento = new InTipoDocumento();
    InTipoDocumentoJpaController jdocumento = new InTipoDocumentoJpaController(conn);
    List<InTipoCompra> lcompra;
    List<InTipoDepartamento> ldeparta;
    List<InEstadosMovimiento> lestad;
    List<InTipoDocumento> ldocument;
    PrProductos objeto = null;
    List<PrProductos> lproductos = new ArrayList<>();
    java.util.Date fechaGuardar = new java.util.Date();
    String fecha, hora;
    CoDetItemsCotizacion detCotiz = new CoDetItemsCotizacion();
    CoDetItemsCotizacionJpaController jdetCotiz = new CoDetItemsCotizacionJpaController(conn);
    List<CoDetItemsCotizacion> ldetCotiz;
    modificarDatosDocumentoExt guardar = new modificarDatosDocumentoExt();
    

    public itemCotizacionForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarCab();
        java.util.Date sistFecha = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        fecha = (formato.format(sistFecha));

        //HORA DEL SISTEMA
        Timer tiempo = new Timer(100, new itemCotizacionForm.horas());
        tiempo.start();

    }

    public itemCotizacionForm(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarCab();
        java.util.Date sistFecha = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        fecha = (formato.format(sistFecha));

        //HORA DEL SISTEMA
        Timer tiempo = new Timer(100, new itemCotizacionForm.horas());
        tiempo.start();

    }

    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            java.util.Date sistHora = new java.util.Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy = Calendar.getInstance();
            hora = (String.format(format.format(sistHora), hoy));
            txthora.setText(fecha + " " + hora);
        }
    }

    private void cargarCab() {
        lcompra = jcompra.findInTipoCompraEntities();
        ldeparta = jdepartamento.findInTipoDepartamentoEntities();
        lestad = jestados.findInEstadosMovimientoEntities();
        ldocument = jdocumento.findInTipoDocumentoEntities();

        cb_tcompra.setModel(Formulario.comboTcompra(lcompra));
        cb_tdepartament.setModel(Formulario.comboTdepart(ldeparta));
        cb_estado.setModel(Formulario.comboTestad(lestad));
        cb_tdocument.setModel(Formulario.comboTdoc(ldocument));

        //        for (int i = 0; i < lcompra.size(); i++) {
//            if ("A".equals(lcompra.get(i).getEstado())) {
//                cb_tcompra.addItem(lcompra.get(i).getNombre());
//            }
//        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cb_tcompra = new javax.swing.JComboBox<>();
        txthora = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_tdepartament = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cb_tdocument = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_prod = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/agregar_Mesa de trabajo 1.png"))); // NOI18N
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Eliminar_Mesa de trabajo 1.png"))); // NOI18N
        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(254, 254, 254));
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(1, 1, 1));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/GUARDAR_Mesa de trabajo 1.png"))); // NOI18N
        jButton3.setText("GUARDAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DOCUMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("TIPO DE COMPRA:");

        cb_tcompra.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cb_tcompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        txthora.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("FECHA EMISION:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("DEPARTAMENTO:");

        cb_tdepartament.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cb_tdepartament.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tdepartament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tdepartamentActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("ESTADO:");

        cb_estado.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("DOCUMENTO:");

        cb_tdocument.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cb_tdocument.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_tcompra, 0, 150, Short.MAX_VALUE)
                            .addComponent(cb_tdepartament, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_tdocument, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_tcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_tdepartament, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_tdocument, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DETALLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 1, 14))); // NOI18N

        tb_prod.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        tb_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_prod.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tb_prod);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ITEMS COTIZACION");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        // Point point = MouseInfo.getPointerInfo().getLocation();
        //  setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        //   x = evt.getX();
        //   y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void cb_tdepartamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tdepartamentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tdepartamentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        int i = tb_prod.getSelectedRow();
//        if (i == -1) {
//            JOptionPane.showMessageDialog(this, "SELECCIONE UN DOCUMENTO");
//        } else {

        if (documento != null) {
            try {
                //setVisible(false);
                itemProductosForm tme = new itemProductosForm(new javax.swing.JFrame(), true);
                tme.setVisible(true);
                if (tme.getProducto() != null) {
                    objeto = tme.getProducto();
                    anadirPrLista(objeto);
                } else {

                }
            } catch (Exception e) {
                Logger.getLogger(itemCotizacionForm.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int i = tb_prod.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "SELECCIONE UN ITEM");
        } else {
            lproductos.remove(i);
            Tablas.listarProductoItemsCotizacion(lproductos, tb_prod);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ldetCotiz = jdetCotiz.findCoDetItemsCotizacionEntities();
        
        List<CoDetItemsCotizacion> lista = new ArrayList<>();
        
        for (int i = 0; i < tb_prod.getRowCount(); i++) {
            CoDetItemsCotizacion tdet = new CoDetItemsCotizacion();
            tdet=ldetCotiz.get(i);
            tdet.getCoDetItemsCotizacionPK().setIdCotizacion(1);
            tdet.getCoDetItemsCotizacionPK().setIdEmpresa(2);
            tdet.getCoDetItemsCotizacionPK().setIdSucursal(3);
            tdet.getCoDetItemsCotizacionPK().setLineaDetalle(i);
            tdet.setDescripcion(tb_prod.getValueAt(i, 3).toString());
            tdet.setCantidadPedida(BigInteger.valueOf(Long.valueOf(tb_prod.getValueAt(i, 2).toString())));
            tdet.setIdProducto(new BigInteger(tb_prod.getValueAt(i, 0).toString()));
            lista.add(tdet);
        }
        
        for(CoDetItemsCotizacion k:lista){
            System.out.println("dff " + k.getIdProducto());
        }
        try {
            // guardar.guardarDetItemsCotizacion(ldetCotiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void anadirPrLista(PrProductos obj) {
        System.out.println("tam " + lproductos.size());
        if (lproductos.size() == 0) {
            System.out.println("jaaaaaaaaaa");
            lproductos.add(obj);
            Tablas.listarProductoItemsCotizacion(lproductos, tb_prod);
        } else {
            boolean ban = false;
            for (int i = 0; i < lproductos.size(); i++) {
                if (lproductos.get(i).getPrProductosPK().getIdProducto() == obj.getPrProductosPK().getIdProducto()) {
                    ban = true;
                }
            }
            if (ban == false) {
                System.out.println("hooaoaasks");
                lproductos.add(obj);
                Tablas.listarProductoItemsCotizacion(lproductos, tb_prod);
            } else {
                JOptionPane.showMessageDialog(rootPane, "GUARDADO EXITOSAMENTE");
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
            java.util.logging.Logger.getLogger(itemCotizacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itemCotizacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itemCotizacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itemCotizacionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                itemCotizacionForm dialog = new itemCotizacionForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cb_estado;
    private javax.swing.JComboBox<String> cb_tcompra;
    private javax.swing.JComboBox<String> cb_tdepartament;
    private javax.swing.JComboBox<String> cb_tdocument;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tb_prod;
    private javax.swing.JTextField txthora;
    // End of variables declaration//GEN-END:variables
}
