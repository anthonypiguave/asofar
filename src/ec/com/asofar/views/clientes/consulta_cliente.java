package ec.com.asofar.views.clientes;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeContactosClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.ClaseReporte;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class consulta_cliente extends javax.swing.JDialog {
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int x, y;
    String valor = "";
    SeUsuarios usu;
    SeEmpresa emp;
    SeSucursal suc;
    SeClientes cli;
    SeTipoIdentificacion tident;
    List<SeClientes> Cliente;
    SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeLocalidadCliente> LocalidadCliente;
    SeLocalidadClienteJpaController Lc = new SeLocalidadClienteJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeClientes> lista = Cc.findSeClientesEntities();
    SeClientes Client = new SeClientes();
    List<SeContactosClientes> ContactoCliente;
    SeContactosClientes ContactoClientes = new SeContactosClientes();
    SeContactosClientesJpaController Ccl = new SeContactosClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeContactosClientes> lista3 = Ccl.findSeContactosClientesEntities();
    List<SeLocalidadCliente> lista1 = Lc.findSeLocalidadClienteEntities();
    SeLocalidadCliente LocaliClient = new SeLocalidadCliente();

    public consulta_cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal=false);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarClientes();
    }

    /*AGREGAR EN OPCIONES MENU Y OPCIONES ROLES*/
    ///
    public consulta_cliente(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal=false);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        MostrarClientes();
        usu = us;
        emp = em;
        suc = su;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba_clientes = new javax.swing.JTable();
        btn_ingresar_cliente = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_eliminar_cliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tba_localidad = new javax.swing.JTable();
        btn_ingresar_localidad = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_eliminar_localidad = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tba_contacto = new javax.swing.JTable();
        btn_ingresar_contacto = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtbusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_clientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tba_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_clientesMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tba_clientes);

        btn_ingresar_cliente.setBackground(new java.awt.Color(254, 254, 254));
        btn_ingresar_cliente.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_ingresar_cliente.setForeground(new java.awt.Color(1, 1, 1));
        btn_ingresar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/nuevo_Mesa de trabajo 1.png"))); // NOI18N
        btn_ingresar_cliente.setText("INGRESAR");
        btn_ingresar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_clienteActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setText("INACTIVOS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(254, 254, 254));
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(1, 1, 1));
        jButton3.setText("EDITAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_eliminar_cliente.setBackground(new java.awt.Color(254, 254, 254));
        btn_eliminar_cliente.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_eliminar_cliente.setForeground(new java.awt.Color(1, 1, 1));
        btn_eliminar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Eliminar_Mesa de trabajo 1.png"))); // NOI18N
        btn_eliminar_cliente.setText("ELIMINAR");
        btn_eliminar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(btn_eliminar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ingresar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ingresar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "LOCALIDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_localidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_localidad.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tba_localidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_localidadMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_localidadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tba_localidadMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tba_localidad);

        btn_ingresar_localidad.setBackground(new java.awt.Color(254, 254, 254));
        btn_ingresar_localidad.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_ingresar_localidad.setForeground(new java.awt.Color(1, 1, 1));
        btn_ingresar_localidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/nuevo_Mesa de trabajo 1.png"))); // NOI18N
        btn_ingresar_localidad.setText("INGRESAR");
        btn_ingresar_localidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_localidadActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(254, 254, 254));
        btn_editar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_editar.setForeground(new java.awt.Color(1, 1, 1));
        btn_editar.setText("EDITAR");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_eliminar_localidad.setBackground(new java.awt.Color(254, 254, 254));
        btn_eliminar_localidad.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_eliminar_localidad.setForeground(new java.awt.Color(1, 1, 1));
        btn_eliminar_localidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Eliminar_Mesa de trabajo 1.png"))); // NOI18N
        btn_eliminar_localidad.setText("ELIMINAR");
        btn_eliminar_localidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_localidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_eliminar_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ingresar_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ingresar_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar_localidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CONTACTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 14))); // NOI18N

        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tba_contacto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tba_contacto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tba_contacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tba_contactoMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tba_contactoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tba_contacto);

        btn_ingresar_contacto.setBackground(new java.awt.Color(254, 254, 254));
        btn_ingresar_contacto.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btn_ingresar_contacto.setForeground(new java.awt.Color(1, 1, 1));
        btn_ingresar_contacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/nuevo_Mesa de trabajo 1.png"))); // NOI18N
        btn_ingresar_contacto.setText("INGRESAR");
        btn_ingresar_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_contactoActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(254, 254, 254));
        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton5.setForeground(new java.awt.Color(1, 1, 1));
        jButton5.setText("EDITAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(254, 254, 254));
        jButton7.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton7.setForeground(new java.awt.Color(1, 1, 1));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Eliminar_Mesa de trabajo 1.png"))); // NOI18N
        jButton7.setText("ELIMINAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ingresar_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ingresar_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setBackground(java.awt.Color.red);
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTES");
        jLabel3.setOpaque(true);
        jLabel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel3MouseDragged(evt);
            }
        });
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("BUSCAR:");

        txtbusqueda.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusquedaActionPerformed(evt);
            }
        });
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(254, 254, 254));
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/salir_Mesa de trabajo 10.jpg"))); // NOI18N
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnimprimir.setBackground(new java.awt.Color(254, 254, 254));
        btnimprimir.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnimprimir.setForeground(new java.awt.Color(1, 1, 1));
        btnimprimir.setText("IMPRIMIR");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    public void MostrarClientes() {
        try {
            Cliente = Cc.findSeClientesEntities();
            Tablas.TablaClientesActivo(Cliente, tba_clientes);

        } catch (Exception e) {
        }
    }

    public void MostrarLocalidad() {
        try {
            LocalidadCliente = Lc.findSeLocalidadClienteEntities();
            Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad, Client);
        } catch (Exception e) {
        }
    }

    public void MostrarContacto() {
        try {
            ContactoCliente = Ccl.findSeContactosClientesEntities();
            Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
        } catch (Exception e) {
        }
    }

    public void MostrarContactoVacia() {
        try {
            ContactoCliente = Ccl.findSeContactosClientesEntities();
            Tablas.TablaContactoClienteVacia(ContactoCliente, tba_contacto, LocaliClient);
        } catch (Exception e) {
        }
    }
    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel3MouseDragged

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void txtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusquedaActionPerformed

    }//GEN-LAST:event_txtbusquedaActionPerformed

    private void txtbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String text = ("" + c).toUpperCase();
            c = text.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtbusquedaKeyTyped

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        valor = txtbusqueda.getText();
        Tablas.filtro(valor, tba_clientes);
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void tba_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                LocalidadCliente = Lc.findSeLocalidadClienteEntities();
                Tablas.TablaLocalidadCliente(LocalidadCliente, tba_localidad, Client);
                Tablas.VaciarTabla(tba_contacto);
//                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
//                tba_contacto.clearSelection();
//                MostrarContactoVacia();
            }
        }
    }//GEN-LAST:event_tba_clientesMouseClicked

    private void tba_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_clientesMousePressed

    }//GEN-LAST:event_tba_clientesMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_ingresar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_clienteActionPerformed
        setVisible(false);
        cliente_agregar cagg = new cliente_agregar(new javax.swing.JFrame(), true, usu, emp, suc, tident);
        cagg.setVisible(true);
        MostrarClientes();
    }//GEN-LAST:event_btn_ingresar_clienteActionPerformed

    private void tba_localidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_localidadMousePressed
    public SeLocalidadCliente devuelveObjeto2(Long id, List<SeLocalidadCliente> listabod) {
        SeLocalidadCliente doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdLocalidadCliente(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }
    private void tba_localidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMouseClicked
        int id = 0;
        if (evt.getClickCount() == 1) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                ContactoCliente = Ccl.findSeContactosClientesEntities();
                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
            }
        }
    }//GEN-LAST:event_tba_localidadMouseClicked

    private void btn_ingresar_localidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_localidadActionPerformed

        int id = 0;
        if (tba_clientes.getSelectedRow() >= 0) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                setVisible(false);
                Localidad_agregar Lagg = new Localidad_agregar(new javax.swing.JFrame(), true, usu, emp, suc, Client, LocaliClient);
                Lagg.setVisible(true);
                MostrarLocalidad();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN REGISTO DE LA TABLA CLIENTE");
        }
    }//GEN-LAST:event_btn_ingresar_localidadActionPerformed

    private void tba_contactoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMousePressed

    private void tba_contactoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_contactoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_contactoMouseClicked

    private void btn_ingresar_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_contactoActionPerformed

        int id = 0;
        if (tba_localidad.getSelectedRow() >= 0) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                setVisible(false);
                contacto_agregar Cagg = new contacto_agregar(new javax.swing.JFrame(), true, usu, emp, suc, LocaliClient);
                Cagg.setVisible(true);
                MostrarContacto();
//                ContactoCliente = Ccl.findSeContactosClientesEntities();
//                Tablas.TablaContactoCliente(ContactoCliente, tba_contacto, LocaliClient);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN REGISTO DE LA TABLA LOCALIDAD");
        }

    }//GEN-LAST:event_btn_ingresar_contactoActionPerformed

    private void tba_localidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tba_localidadMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tba_localidadMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        consulta_cliente_inactivos Cci = new consulta_cliente_inactivos(new javax.swing.JFrame(), true, usu, emp, suc);
        Cci.setVisible(true);
        MostrarClientes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int id = 0;
        if (tba_clientes.getSelectedRow() >= 0) {
            /*hgfrt*/
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                cliente_editar Ce = new cliente_editar(new javax.swing.JFrame(), true, usu, emp, suc, Client);
                Ce.setVisible(true);
                MostrarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE PARA EDITAR");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int id = 0;
        if (tba_localidad.getSelectedRow() >= 0) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {

                Localidad_editar Lce = new Localidad_editar(new javax.swing.JFrame(), true, usu, emp, suc, Client, LocaliClient);
                Lce.setVisible(true);
                MostrarLocalidad();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA LOCALIDAD PARA EDITAR");
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int id = 0;
        if (tba_contacto.getSelectedRow() >= 0) {
            id = tba_contacto.getSelectedRow();
            ContactoClientes = devuelveObjeto3(Long.valueOf(tba_contacto.getValueAt(id, 0).toString()), lista3);

            if (ContactoClientes != null) {
                contacto_editar Ce = new contacto_editar(new javax.swing.JFrame(), true, usu, emp, suc, LocaliClient, ContactoClientes);
                Ce.setVisible(true);
                MostrarContacto();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CONTACTO PARA EDITAR");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_eliminar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_clienteActionPerformed
        int id = 0;
        if (tba_clientes.getSelectedRow() >= 0) {
            id = tba_clientes.getSelectedRow();
            Client = devuelveObjeto(Long.valueOf(tba_clientes.getValueAt(id, 0).toString()), lista);

            if (Client != null) {
                int OP = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este Cliente?", "", JOptionPane.YES_NO_OPTION);
                if (OP == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < Cliente.size(); i++) {
                        if (Client.getIdClientes().equals(Cliente.get(i).getIdClientes())) {
                            Client.setEstado("I");
                            try {

                                SeClientesJpaController Cc = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
                                Cc.edit(Client);
                                JOptionPane.showMessageDialog(null, " ELIMINADO CON EXITO");
                            } catch (Exception ex) {
                                Logger.getLogger(contacto_agregar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                }
                MostrarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE PARA ELIMINAR");
        }
    }//GEN-LAST:event_btn_eliminar_clienteActionPerformed

    private void btn_eliminar_localidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_localidadActionPerformed
        int id = 0;
        if (tba_localidad.getSelectedRow() >= 0) {
            id = tba_localidad.getSelectedRow();
            LocaliClient = devuelveObjeto2(Long.valueOf(tba_localidad.getValueAt(id, 0).toString()), lista1);

            if (LocaliClient != null) {
                int OP = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este Localidad?", "", JOptionPane.YES_NO_OPTION);
                if (OP == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < LocalidadCliente.size(); i++) {
                        if (LocaliClient.getIdLocalidadCliente().equals(LocalidadCliente.get(i).getIdLocalidadCliente())) {
                            System.out.println("************");
//                            LocaliClient.setEstado("I");
                            try {
                                Lc.destroy(LocaliClient.getIdLocalidadCliente());
                                JOptionPane.showMessageDialog(null, " ELIMINADO CON EXITO");
                            } catch (Exception ex) {
                                Logger.getLogger(contacto_agregar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                }

                MostrarLocalidad();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA LOCALIDAD PARA EDITAR");
        }
    }//GEN-LAST:event_btn_eliminar_localidadActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int id = 0;
        if (tba_contacto.getSelectedRow() >= 0) {
            id = tba_contacto.getSelectedRow();
            ContactoClientes = devuelveObjeto3(Long.valueOf(tba_contacto.getValueAt(id, 0).toString()), lista3);

            if (ContactoClientes != null) {
                int OP = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este Contacto?", "", JOptionPane.YES_NO_OPTION);
                if (OP == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < ContactoCliente.size(); i++) {
                        if (ContactoClientes.getIdContactosClientes().equals(ContactoCliente.get(i).getIdContactosClientes())) {
                            System.out.println("************");
//                            LocaliClient.setEstado("I");
                            try {
                                Ccl.destroy(ContactoClientes.getIdContactosClientes());
                                JOptionPane.showMessageDialog(null, " ELIMINADO CON EXITO");
                            } catch (Exception ex) {
                                Logger.getLogger(contacto_agregar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                }
                MostrarContacto();
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN CONTACTO PARA EDITAR");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        ArrayList lista = new ArrayList();   
        if(tba_clientes.getSelectedRowCount()==1){
            if(tba_localidad.getSelectedRowCount()!=0){
                for(int i=0;i<tba_localidad.getSelectedRowCount();i++){
                   ClaseReporte creporte = new ClaseReporte(
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),0).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),1).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),2).toString(),
                   tba_clientes.getValueAt(tba_clientes.getSelectedRow(),3).toString(),                   
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),0).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),1).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),2).toString(),
                   tba_localidad.getValueAt(tba_localidad.getSelectedRow(),3).toString(),                   
                   tba_contacto.getValueAt(i,0).toString(),
                   tba_contacto.getValueAt(i,1).toString(),
                   String.valueOf(tba_contacto.getValueAt(i,2)),
                   tba_contacto.getValueAt(i,3).toString(),
                   tba_contacto.getValueAt(i,4).toString());
                   lista.add(creporte);
                    }
                   try {
                    JasperReport reporte = (JasperReport)JRLoader.loadObject(System.getProperty("user.dir")+"/Reportes/consulta_clienteCOMPLETO.jasper");
                    JasperPrint jprint = JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(lista));
                    JRViewer jviewer = new JRViewer(jprint);
                    JDialog ventana = new JDialog();
                    ventana.add(jviewer);
                    ventana.setVisible(true);
                    ventana.setSize(new Dimension(ancho/2,alto/2));
                    ventana.setLocationRelativeTo(null);
                    jviewer.setFitWidthZoomRatio();
                    
                } catch (JRException ex) {
                    Logger.getLogger(consulta_cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                JOptionPane.showMessageDialog(null,"Por favor seleccione una Localidad");
            }                                        
        }else{
                for(int i=0;i<tba_clientes.getRowCount();i++){
                    ClaseReporte creporte = new ClaseReporte(
                    tba_clientes.getValueAt(i,0).toString(),
                    tba_clientes.getValueAt(i,1).toString(),
                    tba_clientes.getValueAt(i,2).toString(),
                    (String)tba_clientes.getValueAt(i,3));
                    lista.add(creporte);
                }
                try {
                    JasperReport reporte = (JasperReport)JRLoader.loadObject(System.getProperty("user.dir")+"/Reportes/consulta_clienteLISTA.jasper");
                    JasperPrint jprint = JasperFillManager.fillReport(reporte,null,new JRBeanCollectionDataSource(lista));
                    JRViewer jviewer = new JRViewer(jprint);
                    JDialog ventana = new JDialog();
                    ventana.add(jviewer);
                    ventana.setVisible(true);
                    ventana.setSize(new Dimension(ancho/2,alto/2));
                    ventana.setLocationRelativeTo(null);
                    jviewer.setFitWidthZoomRatio();
                    
                }catch (JRException ex) {
                    Logger.getLogger(consulta_cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
        }                
    }//GEN-LAST:event_btnimprimirActionPerformed
    public SeContactosClientes devuelveObjeto3(Long id, List<SeContactosClientes> listabod) {
        SeContactosClientes doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdContactosClientes(), id)) {
                doc = listabod.get(i);
                break;
            }
        }
        return doc;
    }

    public SeClientes devuelveObjeto(Long id, List<SeClientes> listabod) {
        SeClientes doc = null;
        for (int i = 0; i < listabod.size(); i++) {
            if (Objects.equals(listabod.get(i).getIdClientes(), id)) {
                doc = listabod.get(i);
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
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                consulta_cliente dialog = new consulta_cliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar_cliente;
    private javax.swing.JButton btn_eliminar_localidad;
    private javax.swing.JButton btn_ingresar_cliente;
    private javax.swing.JButton btn_ingresar_contacto;
    private javax.swing.JButton btn_ingresar_localidad;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tba_clientes;
    private javax.swing.JTable tba_contacto;
    private javax.swing.JTable tba_localidad;
    private javax.swing.JTextField txtbusqueda;
    // End of variables declaration//GEN-END:variables
}
