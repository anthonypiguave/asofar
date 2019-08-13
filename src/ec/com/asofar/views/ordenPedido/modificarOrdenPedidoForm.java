/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.ordenPedido;

import ec.com.asofar.dao.CoDetalleOrdenPedidoJpaController;
import ec.com.asofar.dao.CoOrdenPedidoJpaController;
import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dto.CoDetalleOrdenPedido;
import ec.com.asofar.dto.CoDetalleOrdenPedidoPK;
import ec.com.asofar.dto.CoOrdenPedido;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Render1;
import ec.com.asofar.util.Tablas;
import ec.com.asofar.views.producto.ConsultaProducto;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin1
 */
public class modificarOrdenPedidoForm extends javax.swing.JDialog {

    int x, y;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;

    Date d = new Date();
    SeEmpresa se = new SeEmpresa();
    CoProveedoresJpaController proveedorcontroller = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoDocumentoJpaController movcontroller = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoDetalleOrdenPedidoJpaController detordencontroller = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

    CoOrdenPedido cOrden;

    List<CoDetalleOrdenPedido> listadet = new ArrayList<CoDetalleOrdenPedido>();

    List<CoOrdenPedido> listcab = new ArrayList<CoOrdenPedido>();

    PrProductos objetopro = new PrProductos();

    int contFilas = 1;

    public modificarOrdenPedidoForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtFecha.setText(FechaActual());
        CargarProveedor();
        CargarDocumento();
        CargarFormulario();

        Timer tiempo = new Timer(100, new modificarOrdenPedidoForm.horas());
        tiempo.start();

    }

    public modificarOrdenPedidoForm(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, CoOrdenPedido objeto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Timer tiempo = new Timer(100, new modificarOrdenPedidoForm.horas());
        tiempo.start();
        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;
        cOrden = objeto;
        listcab.add(objeto);
        txtFecha.setText(FechaActual());
        CargarProveedor();
        CargarDocumento();
        CargarFormulario();
        this.txtCod.setEditable(false);
        this.txtObservacion.setEnabled(false);
        this.cbxProveedor.setEnabled(false);
        this.cbx_documento.setEnabled(false);

        System.out.println(" " + seUsuario);

    }

    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            java.util.Date sistHora = new java.util.Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy = Calendar.getInstance();
            txtHora.setText(String.format(format.format(sistHora), hoy));

        }
    }

    public static String FechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatoFecha.format(fecha);
    }

    public void CargarProveedor() {
        List<CoProveedores> listcaja = proveedorcontroller.findCoProveedoresEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbxProveedor.addItem(listcaja.get(i).getNombre());
        }
    }

    public void CargarDocumento() {
        List<InTipoDocumento> listcaja = movcontroller.findInTipoDocumentoEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            cbx_documento.addItem(listcaja.get(i).getNombreDocumento());
        }
    }

    public void CargarFormulario() {

        txtCod.setText("" + cOrden.getCoOrdenPedidoPK().getIdOrdenPedido());
        txtObservacion.setText(cOrden.getObservacion());

        cbxProveedor.setSelectedIndex(cOrden.getIdProveedor().intValue());
        cbx_documento.setSelectedIndex(cOrden.getIdDocumento().intValue());

        List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();

        list = detordencontroller.findCoDetalleOrdenPedidoEntities();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(" prueba  " + list.get(i).getDescripcion());
            if (list.get(i).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {
                listadet.add(list.get(i));

            }

        }
        Tablas.llenarDetalledeOrdenAprovado(jTable1, listadet);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbxProveedor = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_documento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtnAprovar = new javax.swing.JButton();
        BtnAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ORDEN DE PEDIDO");
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

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("PROVEEDOR:");

        cbxProveedor.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("FECHA EMISION:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("DOCUMENTO:");

        cbx_documento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbx_documento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("CODIGO DE ORDEN:");

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("OBSERVACION:");

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("AGREGAR PRODUCTOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("HORA EMISION:");

        txtHora.setEditable(false);
        txtHora.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(46, 46, 46))
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(36, 36, 36)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxProveedor, 0, 169, Short.MAX_VALUE)
                            .addComponent(cbx_documento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnCancelar.setBackground(new java.awt.Color(153, 0, 0));
        BtnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        BtnAprovar.setBackground(new java.awt.Color(13, 153, 0));
        BtnAprovar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnAprovar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAprovar.setText("APROVAR");
        BtnAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAprovarActionPerformed(evt);
            }
        });

        BtnAnular.setBackground(new java.awt.Color(255, 145, 0));
        BtnAnular.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnAnular.setForeground(new java.awt.Color(255, 255, 255));
        BtnAnular.setText("ANULAR");
        BtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Desea Regresar?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            setVisible(false);

        } else {

        }
    }//GEN-LAST:event_BtnCancelarActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ConsultaProducto cproducto = new ConsultaProducto(new javax.swing.JFrame(), true);
        cproducto.setVisible(true);

        objetopro = cproducto.getProducto();

        CoDetalleOrdenPedidoJpaController detOrdencontroller = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

        try {
            if (validarProductos("" + (objetopro.getPrProductosPK().getIdProducto())).equals("si")) {
                JOptionPane.showMessageDialog(rootPane, "El producto ya se fue seleccionado!");
            } else {

                CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();

                detalle.setCoDetalleOrdenPedidoPK(new CoDetalleOrdenPedidoPK());
                detalle.getCoDetalleOrdenPedidoPK().setIdProducto(objetopro.getPrProductosPK().getIdProducto());
                detalle.setDescripcion(objetopro.getNombreProducto());
                detalle.setCantidadSolicitada(BigInteger.valueOf(0));
                detalle.setEstado("A");
                detalle.setFechaCreacion(d);
                detalle.setUsuarioCreacion(seUsuario.getIdUsuario());
                detalle.setCoOrdenPedido(cOrden);

                listadet.add(detalle);

                for (int i = 0; i < listadet.size(); i++) {

                    contFilas = i + 1;

                    detalle.getCoDetalleOrdenPedidoPK().setLineaDetalle(contFilas);

                }

                Tablas.llenarDetalledeOrdenAprovado(jTable1, listadet);
                detOrdencontroller.create(detalle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed


    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

        CoDetalleOrdenPedidoJpaController detOrdencontroller = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1) {
            if (jTable1.getModel().getColumnClass(col).equals(JButton.class)) {

                JButton jButText = (JButton) jTable1.getValueAt(row, col);

                if (jButText.getText().equals("DESACTIVAR")) {

                    try {

                        int r = JOptionPane.showConfirmDialog(null, "Desea desactivar este producto?", "", JOptionPane.YES_OPTION);
                        if (r == JOptionPane.YES_OPTION) {
                            int i = jTable1.getSelectedRow();

                            CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();
                            List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();
                            List<CoDetalleOrdenPedido> list2 = new ArrayList<CoDetalleOrdenPedido>();

                            list = detOrdencontroller.findCoDetalleOrdenPedidoEntities();

                            for (int j = 0; j < list.size(); j++) {

                                if (list.get(j).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {
                                    detalle = list.get(j);
                                    list2.add(detalle);

                                }
                            }

                            detalle = list2.get(i);
                            detalle.setEstado("I");
                            System.out.println("prueba 2:" + detalle.getEstado());
                            detOrdencontroller.edit(detalle);

                            listadet.get(i).setEstado("I");

                            Tablas.llenarDetalledeOrdenAprovado(jTable1, listadet);

                        }

                    } catch (Exception ex) {
                        Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (jButText.getText().equals("ACTIVAR")) {

                    try {

                        int r = JOptionPane.showConfirmDialog(null, "Desea activar este producto?", "", JOptionPane.YES_OPTION);
                        if (r == JOptionPane.YES_OPTION) {
                            int i = jTable1.getSelectedRow();

                            CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();
                            List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();
                            List<CoDetalleOrdenPedido> list2 = new ArrayList<CoDetalleOrdenPedido>();

                            list = detOrdencontroller.findCoDetalleOrdenPedidoEntities();

                            for (int j = 0; j < list.size(); j++) {

                                if (list.get(j).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {
                                    detalle = list.get(j);
                                    list2.add(detalle);

                                }
                            }

                            detalle = list2.get(i);
                            detalle.setEstado("A");
                            System.out.println("prueba 2:" + detalle.getEstado());
                            detOrdencontroller.edit(detalle);

                            listadet.get(i).setEstado("A");

                            Tablas.llenarDetalledeOrdenAprovado(jTable1, listadet);

                        }

                    } catch (Exception ex) {
                        Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Render1 render = new Render1();
                jTable1.setDefaultRenderer(Object.class, render);
            }

        }

    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        char car = evt.getKeyChar();
        if (car < '0' || car > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        CoDetalleOrdenPedidoJpaController detOrdencontroller = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());
        try {

            int i = jTable1.getSelectedRow();

            String valor = (String) jTable1.getValueAt(i, 3);

            BigInteger cantidad = new BigInteger(valor);

            CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();

            List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();
            List<CoDetalleOrdenPedido> list2 = new ArrayList<CoDetalleOrdenPedido>();

            list = detOrdencontroller.findCoDetalleOrdenPedidoEntities();

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {

                    detalle = list.get(j);
                    list2.add(detalle);
                }

            }

            for (int k = 0; k < list2.size(); k++) {
                if (k == i) {
                    detalle = list2.get(k);
                    listadet.get(k).setCantidadSolicitada(cantidad);
                    detalle.setCantidadSolicitada(cantidad);

                    break;
                }

            }

            detOrdencontroller.edit(detalle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void BtnAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAprovarActionPerformed

        CoDetalleOrdenPedidoJpaController detOrdenController = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoOrdenPedidoJpaController cabOrdenController = new CoOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

        CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();
        detalle.setCoDetalleOrdenPedidoPK(new CoDetalleOrdenPedidoPK());

        List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();
        list = detOrdenController.findCoDetalleOrdenPedidoEntities();
        int r = JOptionPane.showConfirmDialog(null, "Desea aprovar?", "", JOptionPane.YES_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            try {
                for (int j = 0; j < list.size(); j++) {

                    if (list.get(j).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {

                        detalle = list.get(j);

                        detalle.setFechaActualizacion(d);
                        detalle.setUsuarioActualizacion(seUsuario.getIdUsuario());

                        detOrdenController.edit(detalle);
                    }
                }

                CoOrdenPedido cab = cabOrdenController.findCoOrdenPedido(cOrden.getCoOrdenPedidoPK());

                cab.setFechaActualizacion(d);
                cab.setUsuarioActualizacion(seUsuario.getNombreUsuario());
                cab.setEstado("A");

                cabOrdenController.edit(cab);

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                setVisible(false);

            } catch (Exception ex) {
                Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnAprovarActionPerformed

    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
        CoDetalleOrdenPedidoJpaController detOrdenController = new CoDetalleOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());
        CoOrdenPedidoJpaController cabOrdenController = new CoOrdenPedidoJpaController(EntityManagerUtil.ObtenerEntityManager());

        CoDetalleOrdenPedido detalle = new CoDetalleOrdenPedido();
        detalle.setCoDetalleOrdenPedidoPK(new CoDetalleOrdenPedidoPK());

        List<CoDetalleOrdenPedido> list = new ArrayList<CoDetalleOrdenPedido>();
        list = detOrdenController.findCoDetalleOrdenPedidoEntities();
        int r = JOptionPane.showConfirmDialog(null, "Desea aprovar?", "", JOptionPane.YES_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            try {
                for (int j = 0; j < list.size(); j++) {

                    if (list.get(j).getCoDetalleOrdenPedidoPK().getIdOrdenPedido() == (cOrden.getCoOrdenPedidoPK().getIdOrdenPedido())) {

                        detalle = list.get(j);
                        detalle.setEstado("I");
                        detalle.setFechaActualizacion(d);
                        detalle.setUsuarioActualizacion(seUsuario.getIdUsuario());

                        detOrdenController.edit(detalle);
                    }
                }

                CoOrdenPedido cab = cabOrdenController.findCoOrdenPedido(cOrden.getCoOrdenPedidoPK());

                cab.setFechaActualizacion(d);
                cab.setUsuarioActualizacion(seUsuario.getNombreUsuario());
                cab.setEstado("I");

                cabOrdenController.edit(cab);

                JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                setVisible(false);

            } catch (Exception ex) {
                Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnAnularActionPerformed

    public String validarProductos(String datos) {
        String obj1 = "no";

        for (int i = 0; i < listadet.size(); i++) {

            if (datos.equals("" + (listadet.get(i).getCoDetalleOrdenPedidoPK().getIdProducto()))) {
                System.out.println("lista si " + listadet.get(i).getCoDetalleOrdenPedidoPK().getIdProducto());
                obj1 = "si";

                break;
            }

        }

        return obj1;

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
            java.util.logging.Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarOrdenPedidoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modificarOrdenPedidoForm dialog = new modificarOrdenPedidoForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtnAnular;
    private javax.swing.JButton BtnAprovar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JComboBox<String> cbx_documento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}