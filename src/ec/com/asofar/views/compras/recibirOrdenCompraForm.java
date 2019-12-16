/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.compras;

import ec.com.asofar.dao.CoDetalleOrdenCompraJpaController;
import ec.com.asofar.dao.CoOrdenComprasJpaController;
import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.InDetalleMovimientoJpaController;
import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.InMotivosJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoDetalleOrdenCompra;
import ec.com.asofar.dto.CoOrdenCompras;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.Calendario;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin1
 */
public class recibirOrdenCompraForm extends javax.swing.JDialog {
    
    int x, y;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    
    Boolean selector;
    Boolean[] check;
    
    String[] fechaCaducidad;
    String[] nLote;
    
    Date fecha = null;
    
    Date d = new Date();
    
    CoProveedoresJpaController proveedorController = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoDocumentoJpaController documentoController = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoMovimientoJpaController movimientoController = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InMotivosJpaController motivoController = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
    InKardexExt kardexExt = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());
    CoOrdenComprasJpaController cabCompraController = new CoOrdenComprasJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoDetalleOrdenCompraJpaController detCompraController = new CoDetalleOrdenCompraJpaController(EntityManagerUtil.ObtenerEntityManager());
    
    InMovimientosJpaController cabMovController = new InMovimientosJpaController(EntityManagerUtil.ObtenerEntityManager());
    InDetalleMovimientoJpaController detMovController = new InDetalleMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    
    CoOrdenCompras cabCompra;
    CoDetalleOrdenCompra detCompra;
    InMovimientos cabMovimiento;
    
    List<InDetalleMovimiento> listadet = new ArrayList<InDetalleMovimiento>();
    List<CoDetalleOrdenCompra> listadetCompra = new ArrayList<CoDetalleOrdenCompra>();
    
    public recibirOrdenCompraForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtFecha.setText(FechaActual());
        
        CargarFormulario();
        cargarDetalleCompra();
        
        Timer tiempo = new Timer(100, new recibirOrdenCompraForm.horas());
        tiempo.start();
        
    }
    
    public recibirOrdenCompraForm(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su, CoOrdenCompras objeto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Timer tiempo = new Timer(100, new recibirOrdenCompraForm.horas());
        tiempo.start();
        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;
        cabCompra = objeto;
        txtFecha.setText(FechaActual());
        
        CargarFormulario();
        cargarDetalleCompra();
        
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
    
    public void CargarProveedor(InMovimientos obj) {
        List<CoProveedores> listcaja = proveedorController.findCoProveedoresEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            
            if (listcaja.get(i).getIdProveedor() != null) {
                if (listcaja.get(i).getIdProveedor() == obj.getIdProveedor().getIdProveedor()) {
                    TxtProveedor.setText(listcaja.get(i).getNombre());
                    
                }
            }
            
        }
    }
    
    public void CargarDocumento(InMovimientos obj) {
        List<InTipoDocumento> listcaja = documentoController.findInTipoDocumentoEntities();
        for (int i = 0; i < listcaja.size(); i++) {
            
            if (listcaja.get(i).getIdTipoDocumento() != null) {
                if (listcaja.get(i).getIdTipoDocumento() == obj.getInTipoDocumento().getIdTipoDocumento()) {
                    TxtDocumento.setText(listcaja.get(i).getNombreDocumento());
                    
                }
            }
            
        }
    }
    
    public void CargarMovimiento(InMovimientos obj) {
//        List<InTipoMovimiento> listcaja = movimientoController.findInTipoMovimientoEntities();
//        for (int i = 0; i < listcaja.size(); i++) {
//            
//            if (listcaja.get(i).getIdTipoMovimiento() != null) {
//                if (listcaja.get(i).getIdTipoMovimiento() == obj.getInTipoMovimiento().getIdTipoMovimiento()) {
//                    TxtMovimiento.setText(listcaja.get(i).getNombreMovimiento());
//                    
//                }
//            }
//            
//        }
    }
    
    public void CargarMotivos(InMovimientos obj) {
//        List<InMotivos> listcaja = motivoController.findInMotivosEntities();
//        for (int i = 0; i < listcaja.size(); i++) {
//            
//            if (listcaja.get(i).getIdMotivo() != null) {
//                if (listcaja.get(i).getIdMotivo() == obj.getInMotivos().getIdMotivo()) {
//                    TxtMotivo.setText(listcaja.get(i).getNombre());
//                    
//                }
//            }
//            
//        }
    }
    
    public void CargarFormulario() {
        List<InMovimientos> listCab = new ArrayList<InMovimientos>();
        
        listCab = cabMovController.findInMovimientosEntities();
        
        for (int i = 0; i < listCab.size(); i++) {
            
            BigInteger value = listCab.get(i).getIdOrdenCompra();
            
            if (value != null) {
                
                if ((listCab.get(i).getIdOrdenCompra()).intValue() == cabCompra.getCoOrdenComprasPK().getIdOrdenCompra()) {
                    cabMovimiento = listCab.get(i);
                }
            }
        }
        List<InDetalleMovimiento> listDet = new ArrayList<InDetalleMovimiento>();
        listDet = detMovController.findInDetalleMovimientoEntities();
        
        check = new Boolean[listDet.size()];  // inicializar el Boolean segun la lista
        fechaCaducidad = new String[listDet.size()];
        nLote = new String[listDet.size()];
        
        for (int i = 0; i < listDet.size(); i++) {
            
            check[i] = false; // setear valor falso

            fechaCaducidad[i] = "--SELECCIONE--";
            nLote[i] = "";
            
            if (listDet.get(i).getInDetalleMovimientoPK().getIdMovimientos() == (cabMovimiento.getInMovimientosPK().getIdMovimientos()) && listDet.get(i).getEstado().equals("A")) {
                listadet.add(listDet.get(i));
                
            }
        }
        
        CargarProveedor(cabMovimiento);
        CargarDocumento(cabMovimiento);
        CargarMovimiento(cabMovimiento);
        CargarMotivos(cabMovimiento);
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        TxtFechaRecibo.setText(String.format(formatoFecha.format(cabCompra.getFechaEntrega())));
        
        txtCod.setText("" + cabCompra.getCoOrdenComprasPK().getIdOrdenCompra());
        
        Tablas.listarDetalleRecepcion(listadet, jTable1, nLote, fechaCaducidad);
        
    }
    
    public void cargarDetalleCompra() {
        List<CoDetalleOrdenCompra> lista = new ArrayList<CoDetalleOrdenCompra>();
        lista = detCompraController.findCoDetalleOrdenCompraEntities();
        for (int i = 0; i < lista.size(); i++) {
            
            if (lista.get(i).getCoOrdenCompras().getCoOrdenComprasPK().getIdOrdenCompra() == cabCompra.getCoOrdenComprasPK().getIdOrdenCompra()) {
                
                listadetCompra.add(lista.get(i));
                
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TxtFechaRecibo = new javax.swing.JTextField();
        TxtDocumento = new javax.swing.JTextField();
        TxtProveedor = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtnAprovar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(new java.awt.Color(6, 162, 213));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ORDEN DE COMPRA RECIBIDA");
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

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("FECHA EMISION:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("DOCUMENTO:");

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

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("HORA EMISION:");

        txtHora.setEditable(false);
        txtHora.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("FECHA DE RECIBO:");

        TxtFechaRecibo.setEditable(false);
        TxtFechaRecibo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        TxtFechaRecibo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        TxtDocumento.setEditable(false);
        TxtDocumento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        TxtDocumento.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        TxtProveedor.setEditable(false);
        TxtProveedor.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        TxtProveedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);

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
                            .addGap(54, 54, 54))
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtFechaRecibo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TxtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtFechaRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BtnCancelar.setBackground(new java.awt.Color(254, 254, 254));
        BtnCancelar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(1, 1, 1));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BtnAprovar.setBackground(new java.awt.Color(254, 254, 254));
        BtnAprovar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        BtnAprovar.setForeground(new java.awt.Color(1, 1, 1));
        BtnAprovar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/aceptar_Mesa de trabajo 1.jpg"))); // NOI18N
        BtnAprovar.setText("APROBAR");
        BtnAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAprovarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAprovar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    

    private void BtnAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAprovarActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de guardar los datos?", "", JOptionPane.YES_NO_OPTION);
        int cont1 = 1;
        int cont2 = 1;
        InKardexJpaController kardexController = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
        
        if (r == JOptionPane.YES_OPTION) {
            
            if (txtObservacion.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "LLENE OBSERVACIÓN!");
            } else {
                
                for (int i = 0; i < listadet.size(); i++) {
                    
                    JCheckBox chbox = (JCheckBox) jTable1.getValueAt(i, 8);
                    boolean selected = chbox.isSelected();
                    
                    if (selected) {
                        cont1 = cont1 + 1;
                        
                    } else {
                        cont1 = cont1 - 1;
                    }
                    
                }
                
                if (cont1 < listadet.size()) {
                    JOptionPane.showMessageDialog(null, "NO HAY PRODUCTO RECIBIDOS!");
                } else {
                    
                    for (int i = 0; i < listadet.size(); i++) {
                        
                        if (jTable1.getModel().getValueAt(i, 7).getClass().equals(JComboBox.class)) {
                            cont2 = cont2 - 1;
                            
                        } else {
                            cont2 = cont2 + 1;
                        }
                        
                    }
                    
                    if (cont2 < listadet.size()) {
                        JOptionPane.showMessageDialog(null, "SELECCIONE BODEGA!");
                    } else {
                        
                        cargarBodega();
                        
                        try {
                            
                            for (int i = 0; i < listadet.size(); i++) {
                                
                                PrProductos prodObj = new PrProductos();
                                
                                prodObj= ObtenerDTO.ObtenerPrProductos(listadet.get(i).getInDetalleMovimientoPK().getIdProducto());
                                
                                InKardex objeto = kardexExt.obtenerUltimoProductoKardex(listadet.get(i).getInDetalleMovimientoPK().getIdProducto());
                                
                                InKardex kardex = new InKardex();
                                kardex.setInKardexPK(new InKardexPK());
                                kardex.getInKardexPK().setIdBodega(listadet.get(i).getIdBodegaDestino().intValue());
                                kardex.getInKardexPK().setIdProducto(listadet.get(i).getInDetalleMovimientoPK().getIdProducto());
                                
                                kardex.setInTipoDocumento(cabMovimiento.getInTipoDocumento());
                                kardex.setSeSucursal(seSucursal);
                                
                                Double empaqueXcantidadXunidad = (prodObj.getUnidadEmpaqueCompra() * listadet.get(i).getCantidad().doubleValue() )* prodObj.getCantidadPorEmpaqueCompra();
                                
                                
                                kardex.setCantidad(BigInteger.valueOf(empaqueXcantidadXunidad.longValue()));/// revisar si stock hay que convertilo en double
                                
                                
                                
                                kardex.setAnioDocumento("" + listadet.get(i).getAnioDocumento());
                                kardex.setNumeroDocumento(BigInteger.valueOf(cabCompra.getCoOrdenComprasPK().getIdOrdenCompra()));
                                kardex.setFechaSistema(d);
                                SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY");
                                kardex.setAnioDocumento(formatoFecha.format(d));
                                kardex.setUsuarioCreacion(seUsuario.getUsuario());
                                kardex.setFechaCreacion(d);
                                
                                if (objeto != null) {
                                    
                                    kardex.setSaldoAnterior(objeto.getSaldoActual());
                                    
                                    kardex.setSaldoActual(objeto.getSaldoActual().add(kardex.getCantidad()));
                                    
                                    kardex.setCostoAnterior(objeto.getCostoActual());
                                     System.out.println(" costo"+kardex.getCostoAnterior());
                                     System.out.println("ccc " +(listadet.get(i).getPrecioUnitario().multiply(BigDecimal.valueOf(listadet.get(i).getCantidad().intValue()))));
                                    kardex.setCostoActual(kardex.getCostoAnterior().add(
                                            ((listadet.get(i).getPrecioUnitario().multiply(BigDecimal.valueOf(listadet.get(i).getCantidad().intValue()))))));
                                   
                                    kardex.setCostoPromedio(kardex.getCostoActual().divide(BigDecimal.valueOf(kardex.getSaldoActual().intValue()), 5, RoundingMode.HALF_EVEN));
                                    
                                } else {
                                    
                                    kardex.setSaldoAnterior(BigInteger.valueOf(0));
                                    kardex.setSaldoActual(kardex.getCantidad());
                                    
                                    kardex.setCostoAnterior(BigDecimal.valueOf(0));
                                    kardex.setCostoActual(listadet.get(i).getPrecioUnitario().multiply(BigDecimal.valueOf(listadet.get(i).getCantidad().intValue())));
                                    kardex.setCostoPromedio(kardex.getCostoActual().divide(BigDecimal.valueOf(kardex.getSaldoActual().intValue()), 5, RoundingMode.HALF_EVEN));
                                    
                                }
                                
                                kardexController.create(kardex);
                                
                                cabCompra.setEstado("C");
                                cabCompra.setFechaAprobacion(d);
                                cabCompra.setUsuarioActualizacion(seUsuario.getUsuario());
                                cabCompra.setFechaActualizacion(d);
                                cabCompraController.edit(cabCompra);
                                
                                
                                detCompra = listadetCompra.get(i);
                                
                                detCompra.setLoteFabricacion(nLote[i]);
                                detCompra.setFechaCaducidad(new SimpleDateFormat("YYYY-MM-dd").parse(fechaCaducidad[i]));
                                detCompraController.edit(detCompra);
                                
                                cabMovimiento.setEstado("C");
                                cabMovimiento.setObservacion(txtObservacion.getText());
                                cabMovimiento.setUsuarioActualizacion(seUsuario.getUsuario());
                                cabMovimiento.setFechaActualizacion(d);
                                cabMovimiento.setFechaRecepcion(d);
                                cabMovController.edit(cabMovimiento);
                                
                            }
                            
                        } catch (Exception e) {
                            
                            e.printStackTrace();
                            
                        }
                        
                        JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                        setVisible(false);
                    }
                    
                }
            }
        }
    }//GEN-LAST:event_BtnAprovarActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());
        
        if (evt.getClickCount() == 1) {
            if (col == 8) {
                JCheckBox chbox = (JCheckBox) jTable1.getValueAt(row, col);
                
                boolean selected = chbox.isSelected();
                
                if (selected) {
                    
                    chbox.setSelected(false);
                    
                    selector = false;
                    check[row] = selector;
                    
                } else {
                    
                    chbox.setSelected(true);
                    
                    selector = true;
                    check[row] = selector;
                    
                }
                
            }
            
        }
        if (evt.getClickCount() == 1) {
            if (col == 6) {
                Calendario fechaEntrega = new Calendario(new javax.swing.JFrame(), true);
                fechaEntrega.setVisible(true);
                
                fecha = fechaEntrega.getFecha();
                
                SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                
                fechaCaducidad[row] = String.format(formatoFecha.format(fecha));
                
                Tablas.listarDetalleRecepcion(listadet, jTable1, nLote, fechaCaducidad);
                
            }
        }

    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        try {
            
            int row = jTable1.getSelectedRow();
            
            int col = jTable1.getSelectedColumn();
            
            if (col == 5) {
                
                String valor = (String) jTable1.getValueAt(row, col);
                nLote[row] = valor;
                
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1KeyReleased
    
    public void cargarBodega() {
        try {
            
            for (int i = 0; i < listadet.size(); i++) {
                String valor = (String) jTable1.getModel().getValueAt(i, 7);
                
                InBodega bodega = ObtenerDTO.ObtenerInBodega(valor);
                
                listadet.get(i).setIdBodegaDestino(BigInteger.valueOf(bodega.getInBodegaPK().getIdBodega()));
                
            }
            
        } catch (Exception e) {
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
            java.util.logging.Logger.getLogger(recibirOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(recibirOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(recibirOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(recibirOrdenCompraForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                recibirOrdenCompraForm dialog = new recibirOrdenCompraForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BtnAprovar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JTextField TxtDocumento;
    private javax.swing.JTextField TxtFechaRecibo;
    private javax.swing.JTextField TxtProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
