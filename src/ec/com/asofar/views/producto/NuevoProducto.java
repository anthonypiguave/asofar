/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.producto;

import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.PrArticuloJpaController;
import ec.com.asofar.dao.PrEmpaqueJpaController;
import ec.com.asofar.dao.PrFabricanteJpaController;
import ec.com.asofar.dao.PrGruposJpaController;
import ec.com.asofar.dao.PrMedidasJpaController;
import ec.com.asofar.dao.PrProductoBodegaJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.dao.PrTipoMedidasJpaController;
import ec.com.asofar.dao.PrTipoPresentacionJpaController;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.InBodega;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrProductoBodega;
import ec.com.asofar.dto.PrProductoBodegaPK;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Validacion;
import java.awt.MouseInfo;
import java.awt.Point;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class NuevoProducto extends javax.swing.JDialog {

    int x, y, con = 0;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    Date d = new Date();

    PrGruposJpaController GrupoController = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrSubgruposJpaController SubgrupoController = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrArticuloJpaController ArticuloContoller = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTipoMedidasJpaController MedidaController = new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTipoPresentacionJpaController PresentacionController = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoProveedoresJpaController ProveedorController = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrEmpaqueJpaController EmpaqueController = new PrEmpaqueJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrFabricanteJpaController FabricanteController = new PrFabricanteJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductosJpaController productController = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductoBodegaJpaController bodegaStockController = new PrProductoBodegaJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrMedidasJpaController presentacionMedidaController = new PrMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());

    List<PrGrupos> listGrupo = GrupoController.findPrGruposEntities();
    List<PrSubgrupos> listSubgrupo = SubgrupoController.findPrSubgruposEntities();
    List<PrArticulo> listArticulo = ArticuloContoller.findPrArticuloEntities();
    List<PrTipoMedidas> listMedida = MedidaController.findPrTipoMedidasEntities();
    List<CoProveedores> listProveedor = ProveedorController.findCoProveedoresEntities();
    List<PrEmpaque> listEmpaque = EmpaqueController.findPrEmpaqueEntities();
    List<PrTipoPresentacion> listPresentacion = PresentacionController.findPrTipoPresentacionEntities();
    List<PrFabricante> listFabricante = FabricanteController.findPrFabricanteEntities();

    PrGrupos grupo;
    PrSubgrupos subgrupo;
    PrArticulo articulo;
    PrMedidas presentacionMedida;
    CoProveedores proveedor;
    PrEmpaque empaqueCompra1, empaqueCompra2, empaqueVenta1, empaqueVenta2;
    PrFabricante fabricante;
    InBodega bodega;
    PrProductoBodega bodegaStock;
    PrProductos producto;

    String[] cadenaArray1 = {"Seleccione una Opcion..", ""};

    String[] cadenaArray2 = {"0", "","0.0"};

    public NuevoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

    }

    public NuevoProducto(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        txtGrupo.setText("Seleccione una Opcion..");
        txtFabricante.setText("Seleccione una Opcion..");
        txtProveedor.setText("Seleccione una Opcion..");
        txtEmpaqueVenta1.setText("Seleccione una Opcion..");
        txtEmpaqueVenta2.setText("Seleccione una Opcion..");
        txtEmpaqueCompra1.setText("Seleccione una Opcion..");
        txtEmpaqueCompra2.setText("Seleccione una Opcion..");
        txtBodega.setText("Seleccione una Opcion..");
        txtUCompra.setText("0");
        txtUVenta.setText("0");
        txtCCompra.setText("0");
        txtCVenta.setText("0");
        txtStockMin.setText("0");
        txtStockMax.setText("0");
        txtGrupo.setEditable(false);
        txtSubGrupo.setEditable(false);
        txtArticulo.setEditable(false);
        txtPresentacionMedida.setEditable(false);
        txtFabricante.setEditable(false);
        txtProveedor.setEditable(false);
        txtEmpaqueVenta1.setEditable(false);
        txtEmpaqueVenta2.setEditable(false);
        txtEmpaqueCompra1.setEditable(false);
        txtEmpaqueCompra2.setEditable(false);
        txtBodega.setEditable(false);

        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;

        chReceta.setSelected(false);
        chDescontinuado.setSelected(false);

    }

    public String GenerarProductoNombre() {

        String subGru = subgrupo.getNombre();
        String art = articulo.getNombreArticulo();
        String tipoPre = presentacionMedida.getPrTipoPresentacion().getNombre();
        String tipoMe = presentacionMedida.getPrTipoMedidas().getNombreTipoMedida();

        String cadena = subGru + "  " + art + " en " + tipoPre + " de " + tipoMe;

        return cadena.toUpperCase();

    }

    public boolean comprobar() {
        int max = Integer.parseInt(txtStockMax.getText());
        int min = Integer.parseInt(txtStockMin.getText());

        if (min > max) {
            return true;

        } else {
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        chDescontinuado = new javax.swing.JCheckBox();
        txtFabricante = new javax.swing.JTextField();
        txtRegistroSanitarioExtranjero = new javax.swing.JTextField();
        txtRegistroSanitarioLocal = new javax.swing.JTextField();
        chReceta = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUCompra = new javax.swing.JTextField();
        txtCCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtEmpaqueCompra1 = new javax.swing.JTextField();
        txtEmpaqueCompra2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtEmpaqueVenta1 = new javax.swing.JTextField();
        txtEmpaqueVenta2 = new javax.swing.JTextField();
        txtUVenta = new javax.swing.JTextField();
        txtCVenta = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtStockMin = new javax.swing.JTextField();
        txtStockMax = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        txtCodigoBarra = new javax.swing.JTextField();
        btnGenerarCadena = new javax.swing.JButton();
        txtSubGrupo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        txtPresentacionMedida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("Fabricante:");

        jLabel18.setText("Registro sanitario local:");

        jLabel19.setText("Receta Medica:");

        jLabel20.setText("Registro sanitario extranjero:");

        jLabel21.setText("Descontinuado:");

        txtFabricante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFabricanteMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel1)
                    .addComponent(jLabel20))
                .addGap(7, 7, 7)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRegistroSanitarioLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFabricante)
                        .addComponent(txtRegistroSanitarioExtranjero, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chDescontinuado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel1))
                    .addComponent(chReceta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRegistroSanitarioLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel21))
                    .addComponent(chDescontinuado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegistroSanitarioExtranjero, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(32, 32, 32))
        );

        jTabbedPane8.addTab("General", jPanel11);

        jLabel6.setText("Unidad de medida de empaque:");

        jLabel7.setText("Cantidad por unidad de empaque:");

        txtUCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUCompraKeyTyped(evt);
            }
        });

        txtCCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCCompraKeyTyped(evt);
            }
        });

        jLabel8.setText("Proveedor por defecto:");

        txtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtProveedorMousePressed(evt);
            }
        });

        txtEmpaqueCompra1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmpaqueCompra1MousePressed(evt);
            }
        });

        txtEmpaqueCompra2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmpaqueCompra2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCCompra)
                            .addComponent(txtUCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpaqueCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpaqueCompra2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpaqueCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpaqueCompra2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jTabbedPane8.addTab("Datos de compras", jPanel2);

        jLabel15.setText("Unidad de medida de empaque:");

        jLabel16.setText("Cantidad por unidad de empaque:");

        txtEmpaqueVenta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmpaqueVenta1MousePressed(evt);
            }
        });

        txtEmpaqueVenta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmpaqueVenta2MousePressed(evt);
            }
        });

        txtUVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUVentaKeyTyped(evt);
            }
        });

        txtCVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCVentaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmpaqueVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpaqueVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(320, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtUVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpaqueVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpaqueVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Datos de ventas", jPanel3);

        jLabel12.setText("Nivel de Stock");

        jLabel13.setText("Mínimo:");

        jLabel14.setText("Máximo:");

        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMinKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockMinKeyReleased(evt);
            }
        });

        txtStockMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStockMaxFocusLost(evt);
            }
        });
        txtStockMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMaxKeyTyped(evt);
            }
        });

        jLabel22.setText("Bodega");

        txtBodega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtBodegaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(359, 359, 359))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Datos de inventario", jPanel4);

        jLabel17.setBackground(java.awt.Color.red);
        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("NUEVO PRODUCTO");
        jLabel17.setOpaque(true);
        jLabel17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel17MouseDragged(evt);
            }
        });
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });

        btnCrear.setBackground(new java.awt.Color(254, 254, 254));
        btnCrear.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(1, 1, 1));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/agregar_Mesa de trabajo 1.png"))); // NOI18N
        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(254, 254, 254));
        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(1, 1, 1));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        btnSalir.setText("CANCELAR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnGenerarCadena.setText("GENERAR NOMBRE");
        btnGenerarCadena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCadenaActionPerformed(evt);
            }
        });

        txtSubGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSubGrupoMousePressed(evt);
            }
        });

        jLabel10.setText("Presentación y Medidas:");

        txtGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtGrupoMousePressed(evt);
            }
        });
        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });

        jLabel11.setText("Código de barra:");

        txtArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtArticuloMousePressed(evt);
            }
        });

        txtPresentacionMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPresentacionMedidaMousePressed(evt);
            }
        });
        txtPresentacionMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPresentacionMedidaActionPerformed(evt);
            }
        });

        jLabel2.setText("Subcategoría:");

        jLabel3.setText("Categoría:");

        jLabel4.setText("Articulo:");

        txtProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductoFocusLost(evt);
            }
        });

        jLabel5.setText("Producto:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSubGrupo)
                        .addComponent(txtGrupo)
                        .addComponent(txtArticulo)
                        .addComponent(txtPresentacionMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerarCadena))
                    .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPresentacionMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jTabbedPane8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249)))
                .addContainerGap())
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void jLabel17MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel17MouseDragged

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel17MousePressed

    private void btnGenerarCadenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCadenaActionPerformed

        if (Arrays.asList(cadenaArray1).contains(txtPresentacionMedida.getText())) {
            JOptionPane.showMessageDialog(null, "llene los campos desde Categoria hasta la Presentacion y Medida");

        } else {
            String cadena = GenerarProductoNombre();
            txtProducto.setText(cadena);
        }

    }//GEN-LAST:event_btnGenerarCadenaActionPerformed

    private void txtGrupoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGrupoMousePressed

        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {
            try {

                ConsultarGrupo dialog = new ConsultarGrupo(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrGrupos grupoObj = dialog.getObjeto();

                if (grupoObj.getNombre() != null) {

                    grupo = grupoObj;

                    txtGrupo.setText(grupo.getNombre());

                    txtSubGrupo.setText("Seleccione una Opcion..");
                    txtArticulo.setText("");
                    txtPresentacionMedida.setText("");
                    txtProducto.setText("");
                }
            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_txtGrupoMousePressed

    private void txtSubGrupoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSubGrupoMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarSubGrupo dialog = new ConsultarSubGrupo(new javax.swing.JFrame(), true, grupo, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrSubgrupos subgrupoObj = dialog.getObjeto();

                if (subgrupoObj.getNombre() != null) {

                    subgrupo = subgrupoObj;

                    txtSubGrupo.setText(subgrupo.getNombre());
                    txtArticulo.setText("Seleccione una Opcion..");
                    txtPresentacionMedida.setText("");
                    txtProducto.setText("");
                }

            } catch (Exception e) {

            }

        }
    }//GEN-LAST:event_txtSubGrupoMousePressed

    private void txtArticuloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtArticuloMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarArticulo dialog = new ConsultarArticulo(new javax.swing.JFrame(), true, subgrupo, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrArticulo articuloObj = dialog.getObjeto();

                if (articuloObj.getNombreArticulo() != null) {
                    articulo = articuloObj;
                    txtArticulo.setText(articulo.getNombreArticulo());
                    txtPresentacionMedida.setText("Seleccione una Opcion..");
                    txtProducto.setText("");
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtArticuloMousePressed

    private void txtPresentacionMedidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPresentacionMedidaMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarPresentacionMedida dialog = new ConsultarPresentacionMedida(new javax.swing.JFrame(), true, articulo, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrMedidas presentacionMedidaObj = dialog.getObjeto();

                String cadena = "";

                if (presentacionMedidaObj.getPrTipoPresentacion().getNombre() != null
                        && presentacionMedidaObj.getPrTipoMedidas().getNombreTipoMedida() != null) {

                    presentacionMedida = presentacionMedidaObj;

                    cadena = presentacionMedida.getPrTipoPresentacion().getNombre() + " De " + presentacionMedida.getPrTipoMedidas().getNombreTipoMedida();
                    txtPresentacionMedida.setText(cadena);
                    txtProducto.setText("");
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtPresentacionMedidaMousePressed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de guardar los datos?", "", JOptionPane.YES_NO_OPTION);
        PrProductos obj = new PrProductos();
        bodegaStock = new PrProductoBodega();

        List<PrProductos> list = new ArrayList<PrProductos>();

        if (r == JOptionPane.YES_OPTION) {

            if (Arrays.asList(cadenaArray1).contains(txtGrupo.getText())) {
                JOptionPane.showMessageDialog(null, "elija la Categoria!");
            } else {
                if (Arrays.asList(cadenaArray1).contains(txtSubGrupo.getText())) {
                    JOptionPane.showMessageDialog(null, "elija la SubCategoria!");
                } else {
                    if (Arrays.asList(cadenaArray1).contains(txtArticulo.getText())) {
                        JOptionPane.showMessageDialog(null, "elija el Articulo!");
                    } else {
                        if (Arrays.asList(cadenaArray1).contains(txtPresentacionMedida.getText())) {
                            JOptionPane.showMessageDialog(null, "elija la Presentacion y Medida");
                        } else {
                            if (Arrays.asList(cadenaArray1).contains(txtProducto.getText())) {
                                JOptionPane.showMessageDialog(null, "genere o escriba el Producto!");
                            } else {

                                list = presentacionMedida.getPrProductosList();
                                if (list.size() >= 1) {
                                    JOptionPane.showMessageDialog(null, "Producto ya existe!");
                                } else {
                                    if (Arrays.asList(cadenaArray1).contains(txtBodega.getText())) {
                                        JOptionPane.showMessageDialog(null, "elija una bodega en Datos de inventario!");

                                    } else {
                                        if (Arrays.asList(cadenaArray2).contains(txtStockMin.getText())) {
                                            JOptionPane.showMessageDialog(null, "escriba el Stock Minimo!");

                                        } else {

                                            if (Arrays.asList(cadenaArray2).contains(txtStockMax.getText())) {
                                                JOptionPane.showMessageDialog(null, "escriba el Stock Maximo!");

                                            } else {
                                                if (Arrays.asList(cadenaArray1).contains(txtEmpaqueCompra1.getText())) {
                                                    JOptionPane.showMessageDialog(null, "elija medida de empaque compra!");

                                                } else {
                                                    if (Arrays.asList(cadenaArray1).contains(txtEmpaqueCompra2.getText())) {
                                                        JOptionPane.showMessageDialog(null, "elija medida de cantidad por empaque compra!");

                                                    } else {

                                                        if (Arrays.asList(cadenaArray2).contains(txtUCompra.getText())) {
                                                            JOptionPane.showMessageDialog(null, "elija cantidad de unidad por empaque compra!");

                                                        } else {

                                                            if (Arrays.asList(cadenaArray2).contains(txtCCompra.getText())) {
                                                                JOptionPane.showMessageDialog(null, "elija cantidad de unidad por cantidad empaque compra!");

                                                            } else {

                                                                try {

                                                                    obj.setSeEmpresa(seEmpresa);
                                                                    obj.setPrMedidas(presentacionMedida);
                                                                    obj.setNombreProducto(txtProducto.getText());
                                                                    obj.setEstado("A");
                                                                    obj.setUsuarioCreacion(seUsuario.getUsuario());
                                                                    obj.setFechaCreacion(d);

                                                                    if (txtCodigoBarra.getText().equals("")) {

                                                                    } else {
                                                                        obj.setCodigoBarra(txtCodigoBarra.getText());
                                                                    }
                                                                    if (txtRegistroSanitarioExtranjero.getText().equals("")) {

                                                                    } else {
                                                                        obj.setRegistroSanitarioExtranjero(txtRegistroSanitarioExtranjero.getText());
                                                                    }

                                                                    if (txtRegistroSanitarioLocal.getText().equals("")) {

                                                                    } else {
                                                                        obj.setRegistroSanitarioLocal(txtRegistroSanitarioLocal.getText());
                                                                    }

                                                                    if (chReceta.isSelected()) {
                                                                        obj.setReceta("SI");
                                                                    } else {
                                                                        obj.setReceta("NO");
                                                                    }

                                                                    if (chDescontinuado.isSelected()) {
                                                                        obj.setDescontinuado("SI");
                                                                    } else {
                                                                        obj.setDescontinuado("NO");
                                                                    }

                                                                    if (Arrays.asList(cadenaArray1).contains(txtFabricante.getText())) {

                                                                    } else {
                                                                        obj.setCodFabricante(fabricante);
                                                                    }
                                                                    if (Arrays.asList(cadenaArray1).contains(txtProveedor.getText())) {

                                                                    } else {
                                                                        obj.setIdProveedor(proveedor);
                                                                    }
                                                                    if (Arrays.asList(cadenaArray1).contains(txtEmpaqueCompra1.getText())) {

                                                                    } else {
                                                                        obj.setMedidaEmpaqueCompra(empaqueCompra1);
                                                                    }
                                                                    if (Arrays.asList(cadenaArray1).contains(txtEmpaqueCompra2.getText())) {

                                                                    } else {
                                                                        obj.setMedidaPorEmpaqueCompra(empaqueCompra2);
                                                                    }
                                                                    if (Arrays.asList(cadenaArray2).contains(txtUCompra.getText())) {

                                                                    } else {
                                                                        obj.setUnidadEmpaqueCompra(Double.parseDouble(txtUCompra.getText()));
                                                                    }
                                                                    if (Arrays.asList(cadenaArray2).contains(txtCCompra.getText())) {

                                                                    } else {
                                                                        obj.setCantidadPorEmpaqueCompra(Double.parseDouble(txtCCompra.getText()));
                                                                    }
                                                                    if (Arrays.asList(cadenaArray1).contains(txtEmpaqueVenta1.getText())) {

                                                                    } else {
                                                                        obj.setMedidaEmpaqueVenta(empaqueVenta1);
                                                                    }

                                                                    if (Arrays.asList(cadenaArray1).contains(txtEmpaqueVenta2.getText())) {

                                                                    } else {

                                                                        obj.setMedidaPorEmpaqueVenta(empaqueVenta2);
                                                                    }
                                                                    if (Arrays.asList(cadenaArray2).contains(txtUVenta.getText())) {

                                                                    } else {

                                                                        obj.setUnidadEmpaqueVenta(Double.parseDouble(txtUVenta.getText()));
                                                                    }
                                                                    if (Arrays.asList(cadenaArray2).contains(txtCVenta.getText())) {

                                                                    } else {
                                                                        obj.setCantidadPorEmpaqueVenta(Double.parseDouble(txtCVenta.getText()));
                                                                    }


                                                                    if (Arrays.asList(cadenaArray2).contains(txtStockMin.getText())) {

                                                                    } else {

                                                                        bodegaStock.setStockMinimo(BigInteger.valueOf(Long.parseLong(txtStockMin.getText())));
                                                                    }
                                                                    if (Arrays.asList(cadenaArray2).contains(txtStockMax.getText())) {

                                                                    } else {
                                                                        bodegaStock.setStockMaximo(BigInteger.valueOf(Long.parseLong(txtStockMax.getText())));
                                                                    }

                                                                    productController.create(obj);

                                                                    list = new ArrayList<PrProductos>();

                                                                    producto = new PrProductos();

                                                                    list = productController.findPrProductosEntities();

                                                                    for (int i = 0; i < list.size(); i++) {

                                                                        if (list.get(i).getPrMedidas().equals(presentacionMedida)) {
                                                                            producto = list.get(i);

                                                                        }
                                                                    }

                                                                    bodegaStock.setPrProductoBodegaPK(new PrProductoBodegaPK());
                                                                    bodegaStock.getPrProductoBodegaPK().setIdProducto(producto.getPrProductosPK().getIdProducto());

                                                                    bodegaStock.setInBodega(bodega);
                                                                    bodegaStock.setUsuarioCreacion(seUsuario.getUsuario());
                                                                    bodegaStock.setFechaCreacion(d);
                                                                    bodegaStock.setEstado("A");

                                                                    bodegaStockController.create(bodegaStock);

                                                                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                                                                    setVisible(false);

                                                                } catch (Exception e) {
                                                                    Logger.getLogger(NuevoProducto.class.getName()).log(Level.SEVERE, null, e);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProveedorMousePressed

        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarProveedor dialog = new ConsultarProveedor(new javax.swing.JFrame(), true);
                dialog.setVisible(true);

                CoProveedores proveedorObj = dialog.getObjeto();

                if (proveedorObj.getNombre() != null) {
                    proveedor = proveedorObj;
                    txtProveedor.setText(proveedor.getNombre());
                }

            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_txtProveedorMousePressed

    private void txtEmpaqueCompra1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmpaqueCompra1MousePressed

        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarEmpaque dialog = new ConsultarEmpaque(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrEmpaque empaqueCompra1Obj = dialog.getObjeto();

                if (empaqueCompra1Obj.getNombreEmpaque() != null) {
                    empaqueCompra1 = empaqueCompra1Obj;
                    txtEmpaqueCompra1.setText(empaqueCompra1.getNombreEmpaque());
                }

            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_txtEmpaqueCompra1MousePressed

    private void txtEmpaqueCompra2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmpaqueCompra2MousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarEmpaque dialog = new ConsultarEmpaque(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrEmpaque empaqueCompra2Obj = dialog.getObjeto();

                if (empaqueCompra2Obj.getNombreEmpaque() != null) {
                    empaqueCompra2 = empaqueCompra2Obj;
                    txtEmpaqueCompra2.setText(empaqueCompra2.getNombreEmpaque());
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtEmpaqueCompra2MousePressed

    private void txtEmpaqueVenta1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmpaqueVenta1MousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarEmpaque dialog = new ConsultarEmpaque(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrEmpaque empaqueVenta1Obj = dialog.getObjeto();

                if (empaqueVenta1Obj.getNombreEmpaque() != null) {
                    empaqueVenta1 = empaqueVenta1Obj;
                    txtEmpaqueVenta1.setText(empaqueVenta1.getNombreEmpaque());
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtEmpaqueVenta1MousePressed

    private void txtEmpaqueVenta2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmpaqueVenta2MousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarEmpaque dialog = new ConsultarEmpaque(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrEmpaque empaqueVenta2Obj = dialog.getObjeto();

                if (empaqueVenta2Obj.getNombreEmpaque() != null) {
                    empaqueVenta2 = empaqueVenta2Obj;
                    txtEmpaqueVenta2.setText(empaqueVenta2.getNombreEmpaque());
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtEmpaqueVenta2MousePressed

    private void txtFabricanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFabricanteMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarFabricante dialog = new ConsultarFabricante(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                PrFabricante fabricanteObj = dialog.getObjeto();

                if (fabricanteObj.getNombre() != null) {
                    fabricante = fabricanteObj;
                    txtFabricante.setText(fabricante.getNombre());
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtFabricanteMousePressed

    private void txtBodegaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMousePressed

        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 1) {

            try {
                ConsultarBodega dialog = new ConsultarBodega(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                InBodega bodegaObj = dialog.getObjeto();

                if (bodegaObj.getNombreBodega() != null) {

                    bodega = bodegaObj;

                    txtBodega.setText(bodega.getNombreBodega());
                }

            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_txtBodegaMousePressed

    private void txtProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusLost
        txtProducto.setText(txtProducto.getText().toUpperCase());
    }//GEN-LAST:event_txtProductoFocusLost

    private void txtStockMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtStockMinKeyTyped

    private void txtStockMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMaxKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtStockMaxKeyTyped

    private void txtStockMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyReleased
        txtStockMax.setText("");

    }//GEN-LAST:event_txtStockMinKeyReleased

    private void txtStockMaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockMaxFocusLost

        boolean estado;

        estado = comprobar();

        if (estado) {
            JOptionPane.showMessageDialog(null, "elija Stock Maximo tiene que ser superior al Minimo!");
            txtStockMax.setText("");
        }
    }//GEN-LAST:event_txtStockMaxFocusLost

    private void txtUCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUCompraKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtUCompraKeyTyped

    private void txtCCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCompraKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCCompraKeyTyped

    private void txtUVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUVentaKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtUVentaKeyTyped

    private void txtCVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCVentaKeyTyped
        boolean estado = Validacion.FiltroNumeroSinEspacio(evt);
        if (estado) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCVentaKeyTyped

    private void txtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoActionPerformed

    private void txtPresentacionMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPresentacionMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPresentacionMedidaActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoProducto.class
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoProducto dialog = new NuevoProducto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnGenerarCadena;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chDescontinuado;
    private javax.swing.JCheckBox chReceta;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCCompra;
    private javax.swing.JTextField txtCVenta;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtEmpaqueCompra1;
    private javax.swing.JTextField txtEmpaqueCompra2;
    private javax.swing.JTextField txtEmpaqueVenta1;
    private javax.swing.JTextField txtEmpaqueVenta2;
    private javax.swing.JTextField txtFabricante;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtPresentacionMedida;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRegistroSanitarioExtranjero;
    private javax.swing.JTextField txtRegistroSanitarioLocal;
    private javax.swing.JTextField txtStockMax;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextField txtSubGrupo;
    private javax.swing.JTextField txtUCompra;
    private javax.swing.JTextField txtUVenta;
    // End of variables declaration//GEN-END:variables
}
