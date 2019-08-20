package ec.com.asofar.views.venta;

import ec.com.asofar.dao.InDetalleMovimientoJpaController;
import ec.com.asofar.dao.InKardexJpaController;
import ec.com.asofar.dao.InMotivosJpaController;
import ec.com.asofar.dao.InMovimientosJpaController;
import ec.com.asofar.dao.InTipoDocumentoJpaController;
import ec.com.asofar.dao.InTipoMovimientoJpaController;
import ec.com.asofar.dao.PrPrestacionesJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeTipoIdentificacionJpaController;
import ec.com.asofar.dao.VeCajaJpaController;
import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.dao.VeFacturaDetalleJpaController;
import ec.com.asofar.dao.VeFacturaJpaController;
import ec.com.asofar.daoext.InKardexExt;
import ec.com.asofar.daoext.JoinProductoVenta;
import ec.com.asofar.daoext.MovimientosDaoExt;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.OrdenPedidoDaoExt;
import ec.com.asofar.daoext.VeFacturaEXT;
import ec.com.asofar.dto.InDetalleMovimiento;
import ec.com.asofar.dto.InDetalleMovimientoPK;
import ec.com.asofar.dto.InKardex;
import ec.com.asofar.dto.InKardexPK;
import ec.com.asofar.dto.InMotivos;
import ec.com.asofar.dto.InMovimientos;
import ec.com.asofar.dto.InTipoDocumento;
import ec.com.asofar.dto.InTipoMovimiento;
import ec.com.asofar.dto.PrDetalleTarifario;
import ec.com.asofar.dto.PrPrestaciones;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.dto.VeFactura;
import ec.com.asofar.dto.VeFacturaDetalle;
import ec.com.asofar.dto.VeFacturaDetallePK;
import ec.com.asofar.dto.VeFacturaPK;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Formato_Numeros;
import ec.com.asofar.util.Tablas;
import ec.com.asofar.views.clientes.cliente_agregar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Venta extends javax.swing.JInternalFrame {

    /**
     * Creates new form Factura
     */
    Date d = new Date();
    List<InKardex> listaKardex;
    InKardexJpaController Kc = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<PrPrestaciones> listaPresta;
    PrPrestacionesJpaController Prestc = new PrPrestacionesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeClientes> Cliente;
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeTipoIdentificacion> TiIden;
    SeTipoIdentificacionJpaController tic = new SeTipoIdentificacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductos objetoProducto = new PrProductos();
    List<PrProductos> ListProduct;
    PrProductosJpaController ProducC = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
    
    PrPrestaciones objetoPrestacion = new PrPrestaciones();
    VeFacturaDetalle objetoFactDeta = new VeFacturaDetalle();
    PrDetalleTarifario objetoDetTarif = new PrDetalleTarifario();
//    InKardex objetoKardex = new InKardex();
    SeClientes objetoCli = new SeClientes();
    List<PrPrestaciones> listaPrest;
    List<VeFactura> Factura;
    VeFacturaJpaController FactC = new VeFacturaJpaController(EntityManagerUtil.ObtenerEntityManager());
    Long id_prest, idCaja;
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    VeDetalleCaja vdc;
    List<VeFacturaDetalle> listaDetFactura = new ArrayList<VeFacturaDetalle>();
    BigInteger cantidad, cantidadModi, idCliente, id_uni;
    Double precio, precioIva, total, descuento, subtotal;
    Double VGTsubtotal, VGTtotal, VGTiva, VGTdescuento;
    int Cont = 1;
    VeFacturaEXT obtenerId_Factura = new VeFacturaEXT(EntityManagerUtil.ObtenerEntityManager());
    VeDetalleCajaJpaController cajaDetC = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoDocumentoJpaController docCon = new InTipoDocumentoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InTipoMovimientoJpaController tipoMovCon = new InTipoMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
    InMotivosJpaController motivoCon = new InMotivosJpaController(EntityManagerUtil.ObtenerEntityManager());
    Date fecha = null;
    VeCajaJpaController cajaC = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    MovimientosDaoExt obtenerIdMovimiento = new MovimientosDaoExt(EntityManagerUtil.ObtenerEntityManager());
////

    List<InKardex> lisKar;
    InKardex objetoKardex = new InKardex();
    List<InKardex> ListKardex = null;
    InKardexExt selectKardex = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());
    InKardexExt guardarKard = new InKardexExt(EntityManagerUtil.ObtenerEntityManager());
//    InKardex objKardex = new InKardex();
    InKardexJpaController KC = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
//    InKardexExtJpaController KcExt = new InKardexExtJpaController(EntityManagerUtil.ObtenerEntityManager());

    JoinProductoVenta objJoinProVen = new JoinProductoVenta();
    
    public Venta() {
        initComponents();
        this.setLocation(350, 15);
        this.toFront();
        btn_agregar_prod.setMnemonic(KeyEvent.VK_ENTER);
        cargarLisCliente();
        TiIden = tic.findSeTipoIdentificacionEntities();
    }
    
    public Venta(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        initComponents();
        this.setLocation(250, 15);
        cargarLisCliente();
        btn_agregar_prod.setMnemonic(KeyEvent.VK_ENTER);
        TiIden = tic.findSeTipoIdentificacionEntities();
        usu = us;
        emp = em;
        suc = su;
        cargartxt();
        pVender();
        consFinal();
    }
    
    public void consFinal() {
        txtNombre.setText("CONSUMIDOR");
        txtApellido.setText("FINAL");
        txtIdentificacion.setText("9999999999999");
        txtTipoIdent.setText("**********************************");
        txtEmail.setText("**********************************");
        txtDireccion.setText("**********************************");
        txtTelefono.setText("**********************************");
    }
    
    public void Prueba() {
        
        ListKardex = selectKardex.obtenerProductoKardex(Long.valueOf(18));
        for (int i = 0; i < ListKardex.size(); i++) {
            System.out.println("prueba " + ListKardex.get(i).getCantidad());
            System.out.println(" " + ListKardex.get(i).getSaldoActual());
            System.out.println(" " + ListKardex.get(i).getSaldoAnterior());
            
        }
    }
    
    public void pVender() {
        String v = null;
        List<VeDetalleCaja> listadetallecaja = cajaDetC.findVeDetalleCajaEntities();
        List<VeCaja> listaCaja = cajaC.findVeCajaEntities();
        for (int i = 0; i < listadetallecaja.size(); i++) {
            if ("A".equals(listadetallecaja.get(i).getEstado())
                    && listadetallecaja.get(i).getIdUsuario().equals(usu.getIdUsuario())
                    && listadetallecaja.get(i).getFechaCierre() == null
                    && listadetallecaja.get(i).getHoraCierre() == null) {
                v = "si";
                for (int j = 0; j < listaCaja.size(); j++) {
                    VeCaja get = listaCaja.get(j);
                    if (listadetallecaja.get(i).getVeDetalleCajaPK().getIdCaja()
                            == listaCaja.get(j).getIdCaja()) {
                        txt_NumeroCaja.setText("" + listadetallecaja.get(i).getVeDetalleCajaPK().getIdCaja());
                        txt_NombreCaja.setText(listaCaja.get(j).getNombre());
                        idCaja = listadetallecaja.get(i).getVeDetalleCajaPK().getIdCaja();
                    }
                }
            } else {
                v = "no";
            }
        }
        if (v.equals("no")) {
            JOptionPane.showMessageDialog(null, "Debe abrir Caja para Vender");
            
        }
    }
    
    public void cargartxt() {
        txtSubtotal.setText("0.0");
        txtDescuento.setText("0.0");
        txtIva.setText("0.0");
        txtTotal.setText("0.0");
    }
    
    public void cargarLisCliente() {
        Cliente = Cc.findSeClientesEntities();
        for (int i = 0; i < Cliente.size(); i++) {
//            System.out.println("sdfbsdhjfbvjsdf");
//            System.out.println("prin "+Cliente.get(i).getPrimerNombre()+" "+Cliente.get(i).getNumeroIdentificacion());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        btnguardar1 = new javax.swing.JButton();
        txt_idCliente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTipoIdent = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtIva = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tba_detalle = new javax.swing.JTable();
        btn_agregar_prod = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_NumeroCaja = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_NombreCaja = new javax.swing.JTextField();

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

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("FACTURA");
        setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS DEL CLIENTE:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        txtApellido.setEditable(false);
        txtApellido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("IDENTIFICACION:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("NOMBRE:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("APELLIDO: ");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("CORREO: ");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("TELEFONO: ");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Codigo:");

        txtIdentificacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyPressed(evt);
            }
        });

        btnguardar1.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btnguardar1.setForeground(new java.awt.Color(0, 102, 0));
        btnguardar1.setText("BUSCAR");
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });

        txt_idCliente.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("DIRECCION:");

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Nª: ");

        txtTipoIdent.setEditable(false);
        txtTipoIdent.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTipoIdent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoIdentActionPerformed(evt);
            }
        });
        txtTipoIdent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoIdentKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoIdent, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnguardar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_idCliente)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoIdent, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENTA ");
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DETALLE DE FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("SUBTOTAL: ");

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("IVA  : ");

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel15.setText("TOTAL: ");

        txtSubtotal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtSubtotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTotal.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtIva.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtIva.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel16.setText("DESCUENTO:");

        txtDescuento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtDescuento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DETALLE DE FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tba_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_detalleMousePressed(evt);
            }
        });
        tba_detalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tba_detalleKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tba_detalleKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tba_detalle);

        btn_agregar_prod.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btn_agregar_prod.setForeground(new java.awt.Color(0, 102, 0));
        btn_agregar_prod.setMnemonic('e');
        btn_agregar_prod.setText("AGREGAR");
        btn_agregar_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_prodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(btn_agregar_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btn_agregar_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("GENERAR VENTA ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("SALIR ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("N° :");

        jLabel4.setText("Caja : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jButton1)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NombreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(301, 301, 301)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_NumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_NombreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );

        setBounds(0, 0, 794, 685);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char validar = evt.getKeyChar();
        if ((validar < 'a' || validar > 'z') && (validar < 'A' || validar > 'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped

    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        char validar = evt.getKeyChar();
        if ((validar < 'a' || validar > 'z') && (validar < 'A' || validar > 'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtIdentificacionKeyTyped
    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
        CargarCliente();
    }//GEN-LAST:event_btnguardar1ActionPerformed
    public void CargarCliente() {
        /**/
//        String Ident = cbxtipo_identificacion.getSelectedItem().toString();
        String ObjIden = null;
        for (int i = 0; i < Cliente.size(); i++) {
            ObjIden = Cliente.get(i).getIdTipoIndentificacion().getNombreIdentificacion().toString();
            if (txtIdentificacion.getText().equals(Cliente.get(i).getNumeroIdentificacion())
                    //                    && Ident.equals(ObjIden)
                    && Cliente.get(i).getSeLocalidadClienteList().get(i).getSeContactosClientesList().get(i).getNombre().equals("PROPIO")) {
                txtDireccion.setText(Cliente.get(i).getSeLocalidadClienteList().get(i).getDirreccionCliente());
                txtNombre.setText(Cliente.get(i).getPrimerNombre());
                txtApellido.setText(Cliente.get(i).getPrimerApellido());
                txtTipoIdent.setText(Cliente.get(i).getIdTipoIndentificacion().getNombreIdentificacion());
                txtTelefono.setText(Cliente.get(i).getSeLocalidadClienteList().get(i).getSeContactosClientesList().get(i).getCelular());
                txtEmail.setText(Cliente.get(i).getSeLocalidadClienteList().get(i).getSeContactosClientesList().get(i).getEmail());
                txt_idCliente.setText(Cliente.get(i).getIdClientes().toString());
                idCliente = BigInteger.valueOf(Cliente.get(i).getIdClientes());
            }
        }
    }
    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged

    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

    }//GEN-LAST:event_jLabel1MousePressed
    public void limpiarTxt() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
    private void btn_agregar_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_prodActionPerformed
        ConsultaProductoVenta ingre = new ConsultaProductoVenta(new javax.swing.JFrame(), true);
        ingre.setVisible(true);
        objJoinProVen = ingre.obtObjProdVent();
        if (objJoinProVen == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        } else {
            VeFacturaDetalle FactDeta = new VeFacturaDetalle();
            FactDeta.setVeFacturaDetallePK(new VeFacturaDetallePK());
            FactDeta.getVeFacturaDetallePK().setIdPrestaciones(objJoinProVen.getId_prestacion());
            FactDeta.setDescripcion(objJoinProVen.getNombre_producto());
            cantidad = BigInteger.ONE;
            precio = objJoinProVen.getValor_venta();
            precioIva = calcularIvaItem();
            descuento = objJoinProVen.getValor_descuento();
            FactDeta.setCantidad(BigInteger.ONE);
            FactDeta.setValorDescuento(descuento);
            FactDeta.setValorIva(precioIva);
            FactDeta.setPrecioUnitarioVenta(objJoinProVen.getValor_venta());
            subtotal = calcularSubtotal();
            total = calcularTotalItem();
            FactDeta.setValorTotal(total);
            FactDeta.setSubtotal(subtotal);
            
            listaDetFactura.add(FactDeta);
            
            for (int i = 0; i < listaDetFactura.size(); i++) {
                Cont = i + 1;
                FactDeta.getVeFacturaDetallePK().setLineaDetalle(Cont);
            }
            
            Tablas.llenarDetalleVenta(tba_detalle, listaDetFactura);
            Totalizar();
            TotalizarIva();
            TotalizarDescuento();
            TotalizarSubtotal();
        }
    }//GEN-LAST:event_btn_agregar_prodActionPerformed
    private void Totalizar() {
        Double t = 0.0;
        Double p = 0.0;
        if (tba_detalle.getRowCount() > 0) {
            for (int i = 0; i < tba_detalle.getRowCount(); i++) {
                p = listaDetFactura.get(i).getValorTotal();
                t += p;
//                Formato_Numeros.formatoNumero(t.toString());
                VGTtotal = t;
                txtTotal.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtTotal.setText(t.toString());
            }
        }
    }
    
    private void TotalizarIva() {
        Double t = 0.0;
        Double p = 0.0;
        if (tba_detalle.getRowCount() > 0) {
            for (int i = 0; i < tba_detalle.getRowCount(); i++) {
                p = listaDetFactura.get(i).getValorIva();
                t += p;
//                t=Double.valueOf(Formato_Numeros.formatoNumero(t.toString()));
                VGTiva = t;
                txtIva.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtIva.setText(t.toString());
            }
        }
    }
    
    private void TotalizarDescuento() {
        Double t = 0.0;
        Double p = 0.0;
        if (tba_detalle.getRowCount() > 0) {
            for (int i = 0; i < tba_detalle.getRowCount(); i++) {
                p = listaDetFactura.get(i).getValorDescuento();
                t += p;
//                Formato_Numeros.formatoNumero(t.toString());
                VGTdescuento = t;
                txtDescuento.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtDescuento.setText(t.toString());
            }
        }
    }
    
    private void TotalizarSubtotal() {
        Double t = 0.0;
        Double p = 0.0;
        Double pre = 0.0;
        BigInteger cant = null;
        Double subtotal = 0.0;
        if (tba_detalle.getRowCount() > 0) {
            for (int i = 0; i < tba_detalle.getRowCount(); i++) {
                pre = listaDetFactura.get(i).getPrecioUnitarioVenta();
                cant = listaDetFactura.get(i).getCantidad();
                
                p = listaDetFactura.get(i).getSubtotal();
                t += p;
                VGTsubtotal = t;
                txtSubtotal.setText(Formato_Numeros.formatoNumero(t.toString()));
//                txtSubtotal.setText(t.toString());
            }
        }
    }
    
    public Double calcularSubtotal() {
        BigInteger cant;
        Double pre;
        Double subt;
        cant = cantidad;
        pre = precio;
        subt = cant.doubleValue() * pre;
        
        return subt;
    }
    
    public Double calcularTotalItem() {
        BigInteger cant;
        Double pre;
        Double preIva;
        Double total2;
        Double desc;
        /**/
        preIva = calcularIvaItem();
        cant = cantidad;
        pre = precio;
        desc = descuento;
        total2 = (cant.doubleValue() * pre) + preIva - desc;
        
        return total2;
    }
    
    public Double calcularIvaItem() {
        BigInteger cant;
        Double pre;
        Double precioIva = null;
        String aplica = objJoinProVen.getAplica_iva();
        if (aplica.equals("SI")) {
            cant = cantidad;
            pre = precio;
            precioIva = (cant.doubleValue() * pre) * 12 / 100;
        } else {
            precioIva = 0.0;
        }
        return precioIva;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtIdentificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CargarCliente();
        }
    }//GEN-LAST:event_txtIdentificacionKeyPressed

    private void tba_detalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_detalleMousePressed
        int row = tba_detalle.rowAtPoint(evt.getPoint());
        int col = tba_detalle.columnAtPoint(evt.getPoint());
        if (evt.getClickCount() == 1) {
            if (tba_detalle.getModel().getColumnClass(col).equals(JButton.class)) {
                try {
                    
                    int r = JOptionPane.showConfirmDialog(null, "Desea eliminar este producto?", "", JOptionPane.YES_OPTION);
                    if (r == JOptionPane.YES_OPTION) {
                        int i = tba_detalle.getSelectedRow();
                        listaDetFactura.remove(i);
                        Cont = Cont - 1;
                        for (int j = 0; j < listaDetFactura.size(); j++) {
                            Cont = j + 1;
                            listaDetFactura.get(j).getVeFacturaDetallePK().setLineaDetalle(Cont);
                        }
                        /**/
                        Totalizar();
                        TotalizarIva();
                        TotalizarDescuento();
                        TotalizarSubtotal();
                        Tablas.llenarDetalleVenta(tba_detalle, listaDetFactura);
                    }
                } catch (Exception e) {
                }
            }
            
        }
    }//GEN-LAST:event_tba_detalleMousePressed

    private void tba_detalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tba_detalleKeyTyped
        char car = evt.getKeyChar();
        if (car < '0' || car > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_tba_detalleKeyTyped

    private void tba_detalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tba_detalleKeyReleased
        try {
            int i = tba_detalle.getSelectedRow();
            
            String valor = (String) tba_detalle.getValueAt(i, 3);
            
            BigInteger cantidadMod = new BigInteger(valor);
            cantidadModi = cantidadMod;
            String ivaS = tba_detalle.getValueAt(i, 6).toString();
            Double ivaT = Double.valueOf(ivaS.replace(",", "."));
            String precioS = tba_detalle.getValueAt(i, 4).toString();
            Double precioT = Double.valueOf(precioS.replace(",", "."));
            String descuentoS = tba_detalle.getValueAt(i, 5).toString();
            Double descuentoT = Double.valueOf(descuentoS.replace(",", "."));
            
            Double IvaMod = calcularIvaItemCantMod(cantidadModi, ivaT, precioT);
//            Double IvaMod = calcularIvaItemCantMod(cantidadModi);
            Double Desc = calcularDescuentoItemCantMod(cantidadModi, descuentoT);
            Double subt = calcularSubtotalItemCantMod(cantidadModi, precioT);
            Double total = calcularTotalItemCantMod(cantidadModi, IvaMod, Desc, precioT);
            
            listaDetFactura.get(i).setCantidad(cantidadMod);
            listaDetFactura.get(i).setValorIva(IvaMod);
            listaDetFactura.get(i).setSubtotal(subt);
            listaDetFactura.get(i).setValorTotal(total);
            listaDetFactura.get(i).setValorDescuento(Desc);
            Tablas.llenarDetalleVenta(tba_detalle, listaDetFactura);
            
            Totalizar();
            TotalizarIva();
            TotalizarDescuento();
            TotalizarSubtotal();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tba_detalleKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("".equals(txtIdentificacion.getText().toString())) {
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS!");
        } else {

//            /**/guardarKardex();
            VeFactura cabFact = new VeFactura();
            InMovimientos pkMovimiento = null;
            VeFacturaDetalle detFact = new VeFacturaDetalle();
            VeFacturaDetalleJpaController detFactController = new VeFacturaDetalleJpaController(EntityManagerUtil.ObtenerEntityManager());
            
            cabFact.setIdCaja(BigInteger.valueOf(idCaja));
            cabFact.setSeSucursal(suc);
            cabFact.setIdCliente(idCliente);
            cabFact.setSubtotal(VGTsubtotal);
            cabFact.setTotalIva(VGTiva);
            cabFact.setTotalDescuento(VGTdescuento);
            cabFact.setTotalFacturado(VGTtotal);
            cabFact.setDespachado("SI");
            try {
                VeFactura pkFactura = obtenerId_Factura.guardarVenta(cabFact);
                for (int i = 0; i < listaDetFactura.size(); i++) {
                    
                    detFact.setVeFactura(pkFactura);
                    detFact.setVeFacturaDetallePK(new VeFacturaDetallePK());
                    detFact.getVeFacturaDetallePK().setLineaDetalle(listaDetFactura.get(i).getVeFacturaDetallePK().getLineaDetalle());
                    
                    detFact.getVeFacturaDetallePK().setIdPrestaciones(listaDetFactura.get(i).getVeFacturaDetallePK().getIdPrestaciones());
                    detFact.getVeFacturaDetallePK().setIdUnidadServicio(objJoinProVen.getId_unidad_servicio().longValue());
                    detFact.setDescripcion(listaDetFactura.get(i).getDescripcion());
                    detFact.setCantidad(listaDetFactura.get(i).getCantidad());
                    detFact.setPrecioUnitarioVenta(listaDetFactura.get(i).getPrecioUnitarioVenta());
                    detFact.setSubtotal(listaDetFactura.get(i).getSubtotal());
                    detFact.setValorIva(listaDetFactura.get(i).getValorIva());
                    detFact.setValorDescuento(listaDetFactura.get(i).getValorDescuento());
                    detFact.setValorTotal(listaDetFactura.get(i).getValorTotal());
                    detFact.setEstado("A");
                    detFact.setUsuarioCreacion(usu.getIdUsuario());
                    detFact.setFechaCreacion(d);
                    
                    detFactController.create(detFact);
                }
/////////// AGREGANDO A MOVIMIENTO
                InMovimientosJpaController cabMovController = new InMovimientosJpaController(EntityManagerUtil.ObtenerEntityManager());
                InDetalleMovimientoJpaController detMovController = new InDetalleMovimientoJpaController(EntityManagerUtil.ObtenerEntityManager());
                InMovimientos cabMovimiento = new InMovimientos();
                InTipoMovimiento tipoMovimiento = ObtenerDTO.ObtenerInTipoMovimiento("Venta");
                InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido("FACTURA");
                InMotivos tipoMotivos = ObtenerDTO.ObtenerInMotivos("Venta Cliente Final");
                
                InDetalleMovimiento detMovimiento = new InDetalleMovimiento();
                try {
                    cabMovimiento.setSeSucursal(suc);
                    cabMovimiento.setInTipoDocumento(tipoDocumento);
                    cabMovimiento.setInTipoMovimiento(tipoMovimiento);
                    cabMovimiento.setInMotivos(tipoMotivos);
                    cabMovimiento.setFechaSistema(d);
                    cabMovimiento.setAnioDocumento(fecha);
                    cabMovimiento.setIdFactura(BigInteger.valueOf(pkFactura.getVeFacturaPK().getIdFactura()));
                    cabMovimiento.setEstado("F");
                    cabMovimiento.setFechaFactura(d);
                    cabMovimiento.setUsuarioCreacion(usu.getIdUsuario());
                    cabMovimiento.setFechaCreacion(d);
                    
                    pkMovimiento = obtenerIdMovimiento.guardarPedido(cabMovimiento);
                    
                    for (int i = 0; i < listaDetFactura.size(); i++) {
                        detMovimiento.setInMovimientos(pkMovimiento);
                        detMovimiento.setInDetalleMovimientoPK(new InDetalleMovimientoPK()); // inicializar pk
                        detMovimiento.getInDetalleMovimientoPK().setLineaDetalle(listaDetFactura.get(i).getVeFacturaDetallePK().getLineaDetalle());
                        Long id_pro = IdProductoDsdObPres(listaDetFactura);
                        Long id_Bod = IdBodegD(id_pro);
                        
                        detMovimiento.setIdBodegaOrigen(BigInteger.valueOf(id_Bod));
                        detMovimiento.getInDetalleMovimientoPK().setIdProducto(id_pro);
                        detMovimiento.setDescripcion(listaDetFactura.get(i).getDescripcion());
                        detMovimiento.setCantidad(listaDetFactura.get(i).getCantidad());
                        detMovimiento.setPrecioUnitario(BigDecimal.valueOf(listaDetFactura.get(i).getPrecioUnitarioVenta()));
                        detMovimiento.setEstado("A");
                        
                        detMovimiento.setUsuarioCreacion(usu.getNombreUsuario());
                        detMovimiento.setFechaCreacion(d);
                        detMovController.create(detMovimiento);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                guardarKardex2(listaDetFactura);
                
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed
//    public void guardarKardex() {
//        InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido("FACTURA");
//        for (int i = 0; i < listaDetFactura.size(); i++) {
//            
//        Long id_pro = IdProductoDsdObPres(listaDetFactura);
//        Long id_Bod = IdBodegD(id_pro);
//
//        InKardex objKardex = new InKardex();
//        InKardexJpaController KC = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
//        objKardex.setInKardexPK(new InKardexPK());
//
//        objKardex.getInKardexPK().setIdBodega(id_Bod);
//        objKardex.getInKardexPK().setIdProducto(id_pro);
////        objKardex.getInKardexPK().setIdTipoDocumento(7);
//        
//        objKardex.setInTipoDocumento(tipoDocumento);
//        objKardex.setSeSucursal(suc);
//
//        objKardex.setFechaMovimiento(d);
////                        objKardex.getInKardexPK().setIdTipoDocumento(7);
////                        objKar.setAnioDocumento(fecha.toString());
////                        objKardex.setFechaSistema(fecha);
////        objKardex.setCantidad(cantVenta);
////        objKardex.setSaldoActual(resta);
////        objKardex.setSaldoAnterior(cantActual);
//
//        try {
//            KC.create(objKardex);
//        } catch (Exception ex) {
//            Logger.getLogger(InKardex.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }
//    }

    public void guardarKardex2(List<VeFacturaDetalle> listaDetFactura) {
        listaPresta = Prestc.findPrPrestacionesEntities();
        Long id_Prod = null;
        InKardexJpaController KCon = new InKardexJpaController(EntityManagerUtil.ObtenerEntityManager());
        
        InTipoMovimiento tipoMovimiento = ObtenerDTO.ObtenerInTipoMovimiento("Venta");
        InTipoDocumento tipoDocumento = ObtenerDTO.ObtenerDocumentoPedido("FACTURA");
        InMotivos tipoMotivos = ObtenerDTO.ObtenerInMotivos("Venta Cliente Final");
        
        for (int i = 0; i < listaPresta.size(); i++) {
            for (int j = 0; j < listaDetFactura.size(); j++) {
                if (listaPresta.get(i).getIdPrestacion().equals(listaDetFactura.get(j).getVeFacturaDetallePK().getIdPrestaciones())) {
                    
                    id_Prod = Long.parseLong(listaPresta.get(i).getIdPoducto().toString());
////
                    ListKardex = selectKardex.obtenerProductoKardex(id_Prod);
                    for (int k = 0; k < ListKardex.size(); k++) {
                        
                        BigInteger cantVenta = listaDetFactura.get(j).getCantidad();
                        BigInteger cantActual = ListKardex.get(k).getSaldoActual();
                        System.out.println("select ultimo id  ");
                        System.out.println("cant venta " + listaDetFactura.get(j).getCantidad());
                        System.out.println("saldo actual " + ListKardex.get(k).getSaldoActual());
                        System.out.println(" --");
                        BigInteger resta = cantActual.subtract(cantVenta);
                        System.out.println("/*/*/*/");
                        Long id_Bod = IdBodegD(id_Prod);
                        
                        InKardex objKardex = new InKardex();
                        objKardex.setInKardexPK(new InKardexPK());
                        
                        objKardex.getInKardexPK().setIdBodega(id_Bod);
                        objKardex.getInKardexPK().setIdProducto(id_Prod);
//                        objKardex.getInKardexPK().setIdTipoDocumento(7);

                        objKardex.setInTipoDocumento(tipoDocumento);
                        objKardex.setSeSucursal(suc);
                        objKardex.setFechaMovimiento(d);
//                        objKardex.getInKardexPK().setIdTipoDocumento(7);
//                        objKar.setAnioDocumento(fecha.toString());
//                        objKardex.setFechaSistema(fecha);
                        objKardex.setCantidad(cantVenta);
                        objKardex.setSaldoActual(resta);
                        objKardex.setSaldoAnterior(cantActual);
//                        objKardex.setUsuarioCreacion(usu.getIdUsuario());
                        objKardex.setFechaCreacion(fecha);
                        try {
                            KCon.create(objKardex);
                        } catch (Exception ex) {
                            Logger.getLogger(InKardex.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        
    }
    
    public void selectKardex(Long id) {
        ListKardex = selectKardex.obtenerProductoKardex(id);
        for (int i = 0; i < ListKardex.size(); i++) {
            System.out.println("select ultimo id  " + ListKardex.get(i).getCantidad());
            System.out.println(" " + ListKardex.get(i).getSaldoActual());
            System.out.println(" " + ListKardex.get(i).getSaldoAnterior());
            System.out.println(" --");
            
        }
    }
    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTipoIdentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoIdentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoIdentActionPerformed

    private void txtTipoIdentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoIdentKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoIdentKeyTyped
    
    public Long IdProductoDsdObPres(List<VeFacturaDetalle> listaDetFactura) {
        Long id_producto = null;
        listaPresta = Prestc.findPrPrestacionesEntities();
        
        for (int i = 0; i < listaPresta.size(); i++) {
            for (int j = 0; j < listaDetFactura.size(); j++) {
                if (listaPresta.get(i).getIdPrestacion().equals(listaDetFactura.get(j).getVeFacturaDetallePK().getIdPrestaciones())) {
                    id_producto = Long.parseLong(listaPresta.get(i).getIdPoducto().toString());
                    
                }
            }
        }
        return id_producto;
        
    }
    
    public Long IdBodegD(Long id_Pro) {
        Long id_bog = null;
        lisKar = KC.findInKardexEntities();
        
        for (int k = 0; k < lisKar.size(); k++) {
            if (id_Pro.equals(lisKar.get(k).getInKardexPK().getIdProducto())) {
                id_bog = lisKar.get(k).getInKardexPK().getIdBodega();
            }
        }
        return id_bog;
    }
    
    public Double calcularSubtotalItemCantMod(BigInteger cantMod, Double PrecioIva) {
        Double subto = null;
        subto = cantMod.doubleValue() * PrecioIva;
        return subto;
    }
    
    public Double calcularTotalItemCantMod(BigInteger cantMod, Double PrecioIva, Double desc) {
        
        Double pre;
        Double total2;
        /**/
        pre = precio;
        total2 = (cantMod.doubleValue() * pre) + PrecioIva - desc;
        
        return total2;
    }
    
    public Double calcularTotalItemCantMod(BigInteger cantMod, Double PrecioIva, Double desc, Double precio) {
        
        Double pre;
        Double total2;
        /**/
        pre = precio;
        total2 = (cantMod.doubleValue() * pre) + PrecioIva - desc;
        
        return total2;
    }
    
    public Double calcularDescuentoItemCantMod(BigInteger cantMod, Double descuento) {
        Double descuent = null;
        descuent = cantMod.doubleValue() * descuento;
        return descuent;
    }
    
    public Double calcularDescuentoItemCantMod(BigInteger cantMod, Double descuento, Double posicionDescuento) {
        Double descuent = null;
        if (posicionDescuento != 0.0) {
            descuent = cantMod.doubleValue() * descuento;
        } else {
            descuent = 0.0;
        }
        return descuent;
    }
    
    public Double calcularIvaItemCantMod(BigInteger cantMod, Double posicionIva, Double posicionPrecio) {

//        BigInteger cant;
        Double pre;
        Double precioIva2 = null;
        String aplica = objetoPrestacion.getAplicaIva();
        
        if (posicionIva != 0.0) {
//            cant = cantidad;
            pre = posicionPrecio;
            precioIva2 = (cantMod.doubleValue() * pre) * 12 / 100;
        } else {
            precioIva2 = 0.0;
        }
        return precioIva2;
    }
    
    public Double calcularIvaItemCantMod(BigInteger cantMod) {

//        BigInteger cant;
        Double pre;
        Double precioIva2 = null;
        String aplica = objetoPrestacion.getAplicaIva();
        
        if (aplica.equals("SI")) {
//            cant = cantidad;
            pre = precio;
            precioIva2 = (cantMod.doubleValue() * pre) * 12 / 100;
        } else {
            precioIva2 = 0.0;
        }
        return precioIva2;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar_prod;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTable tba_detalle;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JLabel txtDescuento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtSubtotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipoIdent;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txt_NombreCaja;
    private javax.swing.JTextField txt_NumeroCaja;
    private javax.swing.JTextField txt_idCliente;
    // End of variables declaration//GEN-END:variables

}
