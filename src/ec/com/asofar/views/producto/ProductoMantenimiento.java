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
import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.dao.PrTipoMedidasJpaController;
import ec.com.asofar.dao.PrTipoPresentacionJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrEmpaque;
import ec.com.asofar.dto.PrFabricante;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductoMantenimiento extends javax.swing.JDialog {

    int x, y;

    PrGruposJpaController GrupoController = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrSubgruposJpaController SubgrupoController = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrArticuloJpaController ArticuloContoller = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTipoMedidasJpaController MedidaController = new PrTipoMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrTipoPresentacionJpaController PresentacionController = new PrTipoPresentacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    CoProveedoresJpaController ProveedorController = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrEmpaqueJpaController EmpaqueController = new PrEmpaqueJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrFabricanteJpaController FabricanteController = new PrFabricanteJpaController(EntityManagerUtil.ObtenerEntityManager());

    List<PrGrupos> listGrupo = GrupoController.findPrGruposEntities();
    List<PrSubgrupos> listSubgrupo = SubgrupoController.findPrSubgruposEntities();
    List<PrArticulo> listArticulo = ArticuloContoller.findPrArticuloEntities();
    List<PrTipoMedidas> listMedida = MedidaController.findPrTipoMedidasEntities();
    List<CoProveedores> listProveedor = ProveedorController.findCoProveedoresEntities();
    List<PrEmpaque> listEmpaque = EmpaqueController.findPrEmpaqueEntities();
    List<PrTipoPresentacion> listPresentacion = PresentacionController.findPrTipoPresentacionEntities();
    List<PrFabricante> listFabricante = FabricanteController.findPrFabricanteEntities();

    public ProductoMantenimiento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        CargarGrupo();
//        CargarSubgrupo();
//        CargarArticulos();
        CargarMedidas();
        CargarPresentacion();
        CargarProveedor();
        CargarEmpaque();
        CargarFabricante();

    }

    public void CargarGrupo() {

        for (int i = 0; i < listGrupo.size(); i++) {
            cbxGrupo.addItem(listGrupo.get(i).getNombre());
        }
    }

//    public void CargarSubgrupo() {
//
//        for (int i = 0; i < listSubgrupo.size(); i++) {
//            cbxSubgrupo.addItem(listSubgrupo.get(i).getNombre());
//        }
//    }
//
//    public void CargarArticulos() {
//
//        for (int i = 0; i < listArticulo.size(); i++) {
//            cbxArticulo.addItem(listArticulo.get(i).getNombreArticulo());
//        }
//    }
    public void CargarMedidas() {

        for (int i = 0; i < listMedida.size(); i++) {
            cbxMedida.addItem(listMedida.get(i).getNombreTipoMedida());
        }
    }

    public void CargarPresentacion() {

        for (int i = 0; i < listPresentacion.size(); i++) {
            cbxPresentacion.addItem(listPresentacion.get(i).getNombre());
        }
    }

    public void CargarProveedor() {

        for (int i = 0; i < listProveedor.size(); i++) {
            cbxProveedor.addItem(listProveedor.get(i).getNombre());
        }

    }

    public void CargarEmpaque() {

        for (int i = 0; i < listEmpaque.size(); i++) {
            cbxEmpaqueUnidadCompra.addItem(listEmpaque.get(i).getNombreEmpaque());
            cbxEmpaqueCantidadCompra.addItem(listEmpaque.get(i).getNombreEmpaque());
            cbxUnidadEmpaqueVenta.addItem(listEmpaque.get(i).getNombreEmpaque());
            cbxCantidadEmpaqueVenta.addItem(listEmpaque.get(i).getNombreEmpaque());
        }

    }

    public void CargarFabricante() {

        for (int i = 0; i < listFabricante.size(); i++) {
            cbxFabricante.addItem(listFabricante.get(i).getNombre());

        }

    }

    public String GenerarProductoNombre() {

        String articulo = cbxArticulo.getSelectedItem().toString();
        String grupo = cbxGrupo.getSelectedItem().toString();
        String subGrupo = cbxSubgrupo.getSelectedItem().toString();
        String tipoPresentacion = cbxPresentacion.getSelectedItem().toString();
        String tipoMedidas = cbxMedida.getSelectedItem().toString();

        String cadena = grupo + "  " + subGrupo + "  " + articulo + "  " + tipoPresentacion + "  " + tipoMedidas;

        return cadena.toUpperCase();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        cbxEmpaqueUnidadCompra = new javax.swing.JComboBox<>();
        cbxEmpaqueCantidadCompra = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxProveedor = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        cbxUnidadEmpaqueVenta = new javax.swing.JComboBox<>();
        cbxCantidadEmpaqueVenta = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbxGrupo = new javax.swing.JComboBox<>();
        cbxSubgrupo = new javax.swing.JComboBox<>();
        cbxPresentacion = new javax.swing.JComboBox<>();
        cbxArticulo = new javax.swing.JComboBox<>();
        txtProducto = new javax.swing.JTextField();
        cbxMedida = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxFabricante = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Subcategoría:");

        jLabel3.setText("Categoría:");

        jLabel4.setText("Articulo:");

        jLabel5.setText("Producto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("General", jPanel1);

        jLabel6.setText("unidad de medida de empaque:");

        jLabel7.setText("cantidad por unidad de empaque:");

        cbxEmpaqueUnidadCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        cbxEmpaqueCantidadCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        jLabel8.setText("proveedor por defecto:");

        cbxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxEmpaqueUnidadCompra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEmpaqueCantidadCompra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEmpaqueUnidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEmpaqueCantidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos de compras", jPanel2);

        jLabel15.setText("unidad de medida de empaque:");

        jLabel16.setText("cantidad por unidad de empaque:");

        cbxUnidadEmpaqueVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        cbxCantidadEmpaqueVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(37, 37, 37)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxUnidadEmpaqueVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxCantidadEmpaqueVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUnidadEmpaqueVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCantidadEmpaqueVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos de ventas", jPanel3);

        jLabel12.setText("Nivel de Stock");

        jLabel13.setText("Mínimo:");

        jLabel14.setText("Máximo:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(583, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos de inventario", jPanel4);

        jLabel9.setText("Medidas:");

        jLabel10.setText("Presentación:");

        jLabel11.setText("Código de barra:");

        cbxGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));
        cbxGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxGrupoItemStateChanged(evt);
            }
        });

        cbxSubgrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubgrupoItemStateChanged(evt);
            }
        });

        cbxPresentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        cbxMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        jLabel17.setBackground(new java.awt.Color(255, 102, 0));
        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("MANTENIMIENTO DE PRODUCTOS");
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

        jButton1.setText("+");

        jButton2.setText("+");

        jButton3.setText("+");

        jButton4.setText("+");

        jButton5.setText("+");

        jButton6.setText("Crear");

        jButton7.setText("Cancelar");

        jLabel1.setText("Fabricante:");

        cbxFabricante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--" }));

        jButton8.setText("+");

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(43, 43, 43)
                                .addComponent(jButton7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxGrupo, 0, 350, Short.MAX_VALUE)
                                            .addComponent(cbxSubgrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxMedida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxArticulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxPresentacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtProducto)
                                            .addComponent(cbxFabricante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(jButton2)
                                            .addComponent(jButton3)
                                            .addComponent(jButton4)
                                            .addComponent(jButton5)
                                            .addComponent(jButton8)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jButton9)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxSubgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(19, 19, 19))
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String cadena = GenerarProductoNombre();
        txtProducto.setText(cadena);
        System.out.println("kkkkkkkkkkkk " + cadena);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void cbxGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxGrupoItemStateChanged
        cbxSubgrupo.setEnabled(true);

        String nombre = cbxGrupo.getSelectedItem().toString();

        PrGrupos grupos = ObtenerDTO.ObtenerPrGrupos(nombre);

        cbxSubgrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione una Opcion.."}));

        for (int i = 0; i < grupos.getPrSubgruposList().size(); i++) {

            cbxSubgrupo.addItem(grupos.getPrSubgruposList().get(i).getNombre());

        }
    }//GEN-LAST:event_cbxGrupoItemStateChanged

    private void cbxSubgrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSubgrupoItemStateChanged
        cbxArticulo.setEnabled(true);

        String nombre = cbxSubgrupo.getSelectedItem().toString();

        PrSubgrupos subgrupos = ObtenerDTO.ObtenerPrSubGrupos(nombre);

        cbxArticulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Seleccione una Opcion.."}));

        for (int i = 0; i < subgrupos.getPrArticuloList().size(); i++) {

            cbxArticulo.addItem(subgrupos.getPrArticuloList().get(i).getNombreArticulo());

        }
    }//GEN-LAST:event_cbxSubgrupoItemStateChanged

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
            java.util.logging.Logger.getLogger(ProductoMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoMantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductoMantenimiento dialog = new ProductoMantenimiento(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbxArticulo;
    private javax.swing.JComboBox<String> cbxCantidadEmpaqueVenta;
    private javax.swing.JComboBox<String> cbxEmpaqueCantidadCompra;
    private javax.swing.JComboBox<String> cbxEmpaqueUnidadCompra;
    private javax.swing.JComboBox<String> cbxFabricante;
    private javax.swing.JComboBox<String> cbxGrupo;
    private javax.swing.JComboBox<String> cbxMedida;
    private javax.swing.JComboBox<String> cbxPresentacion;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JComboBox<String> cbxSubgrupo;
    private javax.swing.JComboBox<String> cbxUnidadEmpaqueVenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
