/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.clientes;

import ec.com.asofar.dao.SeClientesJpaController;
import ec.com.asofar.dao.SeContactosClientesJpaController;
import ec.com.asofar.dao.SeLocalidadClienteJpaController;
import ec.com.asofar.dao.SeTipoIdentificacionJpaController;
import ec.com.asofar.dto.SeCiudad;
import ec.com.asofar.dto.SeClientes;
import ec.com.asofar.dto.SeContactosClientes;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeLocalidadCliente;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeProvincia;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeTipoIdentificacion;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Validacion;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class EditarCliente extends javax.swing.JDialog {

    int x, y;
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    Date d = new Date();

    boolean correo = false;

    String[] cadenaArray1 = {"Seleccione una Opcion..", ""};

    SePais pais = new SePais();
    SeProvincia provincia = new SeProvincia();
    SeCiudad ciudad = new SeCiudad();

    SeClientes cliente = new SeClientes();
    List<SeClientes> lista = new ArrayList<SeClientes>();

    SeTipoIdentificacion tipoIndentificacion = new SeTipoIdentificacion();

    SeLocalidadCliente localidadCliente = new SeLocalidadCliente();
    List<SeLocalidadCliente> lista1 = new ArrayList<SeLocalidadCliente>();

    SeContactosClientes contactosClientes = new SeContactosClientes();

    SeLocalidadClienteJpaController localidadClienteController = new SeLocalidadClienteJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeClientesJpaController clienteController = new SeClientesJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeTipoIdentificacionJpaController tipoIdentificacionController = new SeTipoIdentificacionJpaController(EntityManagerUtil.ObtenerEntityManager());
    SeContactosClientesJpaController contactosClientesController = new SeContactosClientesJpaController(EntityManagerUtil.ObtenerEntityManager());

    public EditarCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

    }

    public EditarCliente(java.awt.Frame parent, boolean modal, SeClientes objeto, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);

        seUsuario = us;
        seEmpresa = em;
        seSucursal = su;
        cliente = objeto;
        CargarFormulario();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTipoIdentificacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtNCedula = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtMovil = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtProvincia = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtDireccionEntrega = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTabbedPane8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Apellido:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de identificación:");

        txtTipoIdentificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTipoIdentificacionMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Razon social:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("N. identificación:");

        txtNCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNCedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(77, 77, 77)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtNCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Datos Personales", jPanel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Telefono fijo :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Telefono movil:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Ciudad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Provincia:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Pais:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Direccion de entrega:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Direccion:");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        txtMovil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMovilKeyTyped(evt);
            }
        });

        txtPais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPaisMousePressed(evt);
            }
        });

        txtProvincia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtProvinciaMousePressed(evt);
            }
        });

        txtCiudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCiudadMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addComponent(txtDireccionEntrega, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel10)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txtProvincia)
                                            .addGap(1, 1, 1))))))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Direcciones", jPanel3);

        jLabel17.setBackground(java.awt.Color.red);
        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("EDITAR CLIENTE");
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
        btnCrear.setText("ACTUALIZAR");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane8)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void CargarFormulario() {
        CargarTipoIndentificacionLocalidadContactoPaisProvinciaCiudad();

        if (cliente.getSegundoApellido() != null) {
            txtApellido.setText(cliente.getPrimerApellido() + " " + cliente.getSegundoApellido());
        } else {
            txtApellido.setText(cliente.getPrimerApellido());
        }

        if (cliente.getSegundoNombre() != null) {
            txtNombre.setText(cliente.getPrimerNombre() + " " + cliente.getSegundoNombre());
        } else {
            txtNombre.setText(cliente.getPrimerNombre());
        }

        txtNCedula.setText(cliente.getNumeroIdentificacion());
        txtRazonSocial.setText(cliente.getRazonSocial());
        txtTipoIdentificacion.setText(cliente.getIdTipoIndentificacion().getNombreIdentificacion());

        txtTelefono.setText(localidadCliente.getTelefono());
        txtMovil.setText(localidadCliente.getCelular());
        txtEmail.setText(localidadCliente.getEmail());

        if (pais != null && provincia != null && ciudad != null) {
            txtPais.setText(pais.getNombre());
            txtProvincia.setText(provincia.getNombre());
            txtCiudad.setText(ciudad.getNombre());
        } else {
            txtPais.setText("Seleccione una Opcion..");

        }

        txtDireccion.setText(localidadCliente.getDirreccionCliente());
        txtDireccionEntrega.setText(localidadCliente.getDirreccionEntrega());
    }

    private void CargarTipoIndentificacionLocalidadContactoPaisProvinciaCiudad() {
        localidadCliente = cliente.getSeLocalidadClienteList().get(0);
        contactosClientes = localidadCliente.getSeContactosClientesList().get(0);
        pais = localidadCliente.getIdPais();
        provincia = localidadCliente.getIdProvincia();
        ciudad = localidadCliente.getIdCiudad();
        tipoIndentificacion = cliente.getIdTipoIndentificacion();

    }

    private void ArmarCadenaUsuario() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();

        String separador = Pattern.quote(" ");
        String[] parts1 = nombre.split(separador);
        String[] parts2 = apellido.split(separador);

        for (int i = 0; i < parts1.length; i++) {

            if (i == 0) {
                cliente.setPrimerNombre(parts1[i].toUpperCase());
            } else {
                cliente.setSegundoNombre(parts1[i].toUpperCase());
            }

        }

        for (int i = 0; i < parts2.length; i++) {

            if (i == 0) {
                cliente.setPrimerApellido(parts2[i].toUpperCase());
            } else {
                cliente.setSegundoApellido(parts2[i].toUpperCase());
            }

        }
        String nombreCompleto = (txtNombre.getText() + " " + txtApellido.getText()).toUpperCase();

        cliente.setNombreCompleto(nombreCompleto);

    }


    private void jLabel17MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel17MouseDragged

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel17MousePressed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de guardar los datos?", "", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {

            if (Arrays.asList(cadenaArray1).contains(txtNombre.getText())) {
                JOptionPane.showMessageDialog(null, "falta Nombres!");
            } else {
                if (Arrays.asList(cadenaArray1).contains(txtApellido.getText())) {
                    JOptionPane.showMessageDialog(null, "falta Apellidos!");
                } else {
                    if (Arrays.asList(cadenaArray1).contains(txtTipoIdentificacion.getText())) {
                        JOptionPane.showMessageDialog(null, "elija Tipo Identificacion!");
                    } else {

                        if (Arrays.asList(cadenaArray1).contains(txtNCedula.getText())) {
                            JOptionPane.showMessageDialog(null, "falta N. Indentificacion!");
                        } else {

//                            if (Arrays.asList(cadenaArray1).contains(txtTelefono.getText())) {
//                                JOptionPane.showMessageDialog(null, "falta telefono!");
//                            } else {
//
//                                if (Arrays.asList(cadenaArray1).contains(txtMovil.getText())) {
//                                    JOptionPane.showMessageDialog(null, "falta Movil!");
//                                } else {
//                                    if (Arrays.asList(cadenaArray1).contains(txtEmail.getText())) {
//                                        JOptionPane.showMessageDialog(null, "falta Email!");
//                                    } else {
//                                        if (Arrays.asList(cadenaArray1).contains(txtPais.getText())) {
//                                            JOptionPane.showMessageDialog(null, "elija la Pais!");
//                                        } else {
//                                            if (Arrays.asList(cadenaArray1).contains(txtProvincia.getText())) {
//                                                JOptionPane.showMessageDialog(null, "elija la Provincia!");
//                                            } else {
//
//                                                if (Arrays.asList(cadenaArray1).contains(txtCiudad.getText())) {
//                                                    JOptionPane.showMessageDialog(null, "elija la Ciuada!");
//                                                } else {
//                                                    if (Arrays.asList(cadenaArray1).contains(txtDireccion.getText())) {
//                                                        JOptionPane.showMessageDialog(null, "falta Direccion");
//                                                    } else {
//
//                                                        if (Arrays.asList(cadenaArray1).contains(txtDireccionEntrega.getText())) {
//                                                            JOptionPane.showMessageDialog(null, "falta Direccion Entrega!");
//                                                        } else {
                            try {
                                ArmarCadenaUsuario();

                                cliente.setIdTipoIndentificacion(tipoIndentificacion);
                                cliente.setNumeroIdentificacion(txtNCedula.getText());

                                cliente.setRazonSocial(txtRazonSocial.getText());
                                cliente.setEstado("A");
                                cliente.setUsuarioActualizacion(seUsuario.getUsuario());
                                cliente.setFechaActualizacion(d);

                                clienteController.edit(cliente);

                                ////////////////////////////////////////////////
                                localidadCliente.setCelular(txtMovil.getText());
                                localidadCliente.setTelefono(txtTelefono.getText());
                                localidadCliente.setDirreccionCliente(txtDireccion.getText());
                                localidadCliente.setDirreccionEntrega(txtDireccionEntrega.getText());
                                localidadCliente.setEmail(txtEmail.getText());

                                if (pais.getNombre() != null && provincia.getNombre() != null && ciudad.getNombre() != null) {
                                    localidadCliente.setIdPais(pais);
                                    localidadCliente.setIdProvincia(provincia);
                                    localidadCliente.setIdCiudad(ciudad);
                                }

                                localidadCliente.setEstado("A");
                                localidadCliente.setUsuarioActualizacion(seUsuario.getUsuario());
                                localidadCliente.setFechaActualizacion(d);

                                localidadClienteController.edit(localidadCliente);

                                //////////////////////////////////////
                                contactosClientes.setNombre("PROPIO");

                                contactosClientes.setEstado("A");
                                contactosClientes.setUsuarioActualizacion(seUsuario.getUsuario());
                                contactosClientes.setFechaActualizacion(d);

                                contactosClientesController.edit(contactosClientes);

                                JOptionPane.showMessageDialog(null, "Datos guardados correctamente!");
                                setVisible(false);

                            } catch (Exception e) {

                                Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, e);

                            }

//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }

                        }
                    }

                }
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtTipoIdentificacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTipoIdentificacionMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 2) {
            try {

                ConsultarTipoIdentificacion dialog = new ConsultarTipoIdentificacion(new javax.swing.JFrame(), true);
                dialog.setVisible(true);

                tipoIndentificacion = dialog.getObjeto();

                if (tipoIndentificacion.getNombreIdentificacion() != null) {

                    txtTipoIdentificacion.setText(tipoIndentificacion.getNombreIdentificacion());
                }
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtTipoIdentificacionMousePressed

    private void txtPaisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPaisMousePressed

        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 2) {
            try {

                ConsultarPais dialog = new ConsultarPais(new javax.swing.JFrame(), true, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                pais = dialog.getObjeto();
                if (pais.getNombre() != null) {
                    txtPais.setText(pais.getNombre());

                    txtProvincia.setText("Seleccione una Opcion..");
                    txtCiudad.setText("");

                }
            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_txtPaisMousePressed

    private void txtProvinciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProvinciaMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 2) {

            try {
                ConsultarProvincia dialog = new ConsultarProvincia(new javax.swing.JFrame(), true, pais, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                provincia = dialog.getObjeto();

                if (provincia.getNombre() != null) {

                    txtProvincia.setText(provincia.getNombre());

                    txtCiudad.setText("Seleccione una Opcion..");

                }

            } catch (Exception e) {

            }

        }
    }//GEN-LAST:event_txtProvinciaMousePressed

    private void txtCiudadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCiudadMousePressed
        int i = 0;
        String msg = null;
        if (evt.getClickCount() == 2) {
            System.out.println("************cuiusdiucs");
            try {
                ConsultarCiudad dialog = new ConsultarCiudad(new javax.swing.JFrame(), true, provincia, seUsuario, seEmpresa, seSucursal);
                dialog.setVisible(true);

                ciudad = dialog.getObjeto();

                if (ciudad.getNombre() != null) {

                    txtCiudad.setText(ciudad.getNombre());

                }
            } catch (Exception e) {

            }

        }
    }//GEN-LAST:event_txtCiudadMousePressed

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        correo = Validacion.Email(txtEmail.getText());
        if (correo != true) {
            //msge = msge + "\n Correo invalido";
            JOptionPane.showMessageDialog(null, "Correo invalido");
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtMovilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMovilKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMovilKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCedulaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtNCedulaKeyTyped

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
            java.util.logging.Logger.getLogger(EditarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarCliente dialog = new EditarCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEntrega;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMovil;
    private javax.swing.JTextField txtNCedula;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtProvincia;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipoIdentificacion;
    // End of variables declaration//GEN-END:variables
}
