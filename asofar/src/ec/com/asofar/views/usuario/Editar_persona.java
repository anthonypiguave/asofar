/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.usuario;

import ec.com.asofar.dao.SePersonasJpaController;
import ec.com.asofar.dao.SeTipoPersonaJpaController;
import ec.com.asofar.dao.exceptions.NonexistentEntityException;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.daoext.SePersonasJpaControllerExt;
import ec.com.asofar.dto.SePersonas;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.Calendario;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Fecha;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author alumno
 */
public class Editar_persona extends javax.swing.JDialog {
    SePersonas obj;
    SePersonasJpaControllerExt persona_controller
            = new SePersonasJpaControllerExt(EntityManagerUtil.ObtenerEntityManager());
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private Date fecha1 = null;
    String ro = null;
    String est = null;
    String gen = null;
//    private String rutaimagen = "";
    public String fil;
     List<SePersonas> lista_persona;
    SePersonas persona1;
    SeTipoPersonaJpaController tpc
            = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
    java.util.Date fechaActual = new java.util.Date();
SeUsuarios us1;
SePersonasJpaController mp =
            new SePersonasJpaController(EntityManagerUtil.ObtenerEntityManager());
String rutaimagen="";
 String Destino;
    /**
     * Creates new form Registrar
     */
    public Editar_persona(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public Editar_persona(java.awt.Frame parent, boolean modal, SePersonas persona,SeUsuarios us) {
        super(parent, modal);
        initComponents();
        persona1 = persona;
        us1=us;
        cargarValores(persona);
        CargarRol();
        lista_persona
                = persona_controller.findSePersonasEntities();
        Destino = "/home/admin1/NetBeansProjects/asof/";

    }

//    public Editar_persona(java.awt.Frame parent, boolean modal,Listar_usuario obj2) {
//        super(parent, modal);
//        setUndecorated(true);
//        initComponents();
//        this.setLocationRelativeTo(null);
//        obj1 = obj2;
//        llenarForm(obj1);
//        combos(obj1);
////        cbGenero.setModel(Formulario.listarComboGenero(lista1));
////        cbRol.setModel(Formulario.listarComboRol(lista2));
////        cbEstado.setModel(Formulario.listarComboEstado(lista3));
//        txtId.setEnabled(false);
//        bloqueo(false);
//    }
//    
//    public void combos(Listar_usuario obj){
//        lista1 = crud.listarGenero();
//        lista2 = crud.listarRol();
//        lista3 = crud.listarEstado();
//        
//        cbGenero.setModel(Formulario.listarComboGenero(lista1));
//        cbRol.setModel(Formulario.listarComboRol(lista2));
//        cbEstado.setModel(Formulario.listarComboEstado(lista3));
//        
//        ro = crud.getCombosAcUsuarios(Long.valueOf("1"), obj.getId_rol());
//        cbRol.setSelectedItem(ro);
//        System.out.println("pejjd"+obj.getId_rol());
//        est = crud.getCombosAcUsuarios(Long.valueOf("2"), obj.getId_estado());
//        cbEstado.setSelectedItem(est);
//        gen = crud.getCombosAcUsuarios(Long.valueOf("3"), obj.getId_genero());
//        cbGenero.setSelectedItem(gen);
//    }
//    
//    public void llenarForm(Listar_usuario obj1){
//        txtId.setText(obj1.getId_sesion().toString());
//        txtCedula.setText(obj1.getCedula());
//        txtApellido.setText(obj1.getApellidos());
//        txtNombre.setText(obj1.getNombres());
//        txtCell.setText(obj1.getTelefono());
//        txtCorreo.setText(obj1.getCorreo());
//        txtDireccion.setText(obj1.getDireccion());
//        txtObservacion.setText(obj1.getObservacion());
//        txtConven.setText(obj1.getConvencional());
//        getPicture2(obj1.getRuta_imagen());
//    }
    public void bloqueo() {
               
        obj = null;
        
            for (int i = 0; i < lista_persona.size(); i++) {
                if (persona1.getIdPersona() == lista_persona.get(i).getIdPersona() ) {
                    obj = lista_persona.get(i);
                    try {
                        mp.destroy(lista_persona.get(i).getIdPersona());
//                    if (obj != null) {
//                        setVisible(false);
//                        Editar_persona es = new Editar_persona(new javax.swing.JFrame(), true, obj,us1);
//                        es.setVisible(true);
//                    }
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(Editar_persona.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
//            lista = crud.listarProveedores(Long.valueOf("1"));
//            
//            (tabla.getValueAt(id, 0).toString(), lista);
//            System.out.println("si pasa algo"+proveedor.getCedula_ruc());
//            if (proveedor != null) {
//                Editar_Proveedor ep = new Editar_Proveedor(new javax.swing.JFrame(), true, proveedor);
//                setVisible(false);
//                ep.setVisible(true);

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

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbImagen = new javax.swing.JLabel();
        btnLimpiarImg = new javax.swing.JButton();
        btnImagen = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnHabilitar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtCell = new javax.swing.JTextField();
        txtConven = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        BotonFecha1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cbTipoPersona1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "FOTO DE USUARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lbImagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLimpiarImg.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnLimpiarImg.setText("BORRAR FOTO");
        btnLimpiarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarImgActionPerformed(evt);
            }
        });

        btnImagen.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnImagen.setText("AGREGAR FOTO");
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });
        btnImagen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnImagenKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnGuardar.setText("ACTUALIZAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnHabilitar.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        btnHabilitar.setText("ELIMINAR");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 153, 153));
        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ACTUALIZAR PERSONA");
        jLabel17.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS PERSONALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRES:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("APELLIDOS:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("CEDULA:");

        txtCedula.setEditable(false);
        txtCedula.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("DIRECCION:");

        txtNombre.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoFocusLost(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("CELULAR:");

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("CONVENCIONAL:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("CORREO:");

        txtDireccion.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtCell.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCellKeyTyped(evt);
            }
        });

        txtConven.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtConven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConvenKeyTyped(evt);
            }
        });

        txtCorreo.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 1, 1));
        jLabel7.setText("F. NACIMIENTO :");

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        BotonFecha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonFecha1.setText("...");
        BotonFecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonFecha1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel15.setText("TIPO DE PERSONA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(34, 34, 34)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion)
                            .addComponent(cbTipoPersona1, 0, 160, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConven, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConven, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoPersona1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarImgActionPerformed
//        VaciarImagen();
    }//GEN-LAST:event_btnLimpiarImgActionPerformed

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        String pass = "";
        getPicture(pass);
    }//GEN-LAST:event_btnImagenActionPerformed
// private void getPicture2(String path) {
//        //JFileChooser dig = new JFileChooser(path);
//        //dig.setFileFilter(new FileNameExtensionFilter("Archivos de imagen",
//              //  "tif", "jpg", "jpeg", "png", "gif"));
//        //int opcion = dig.showOpenDialog(this);
//        //if (opcion == JFileChooser.APPROVE_OPTION) {
//            //fot = dig.getSelectedFile().getPath();
//            //rutaimagen = dig.getSelectedFile().getPath();
//            rutaimagen = path;
//            lbImagen.setIcon(new ImageIcon(path));
//            ImageIcon icon = new ImageIcon(path);
//            Image img = icon.getImage();
//            Image newimg = img.getScaledInstance(110,106, java.awt.Image.SCALE_SMOOTH);
//            ImageIcon newIcono = new ImageIcon(newimg);
//            lbImagen.setIcon(newIcono);            
////            System.out.println(fot + " Foto " + imagen.getWidth() + " " + imagen.getHeight());
////            System.out.println("ruta= "+rutaimagen +"\n"+
////                                "ruta2 "+fot);
//        }
    private void getPicture(String path) {
        JFileChooser dig = new JFileChooser(path);
        dig.setFileFilter(new FileNameExtensionFilter("Archivos de imagen",
                "tif", "jpg", "jpeg", "png", "gif"));
        int opcion = dig.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String fil = dig.getSelectedFile().getPath();
            lbImagen.setIcon(new ImageIcon(fil));
            ImageIcon icon = new ImageIcon(fil);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(lbImagen.getWidth(), lbImagen.getHeight(), Image.SCALE_DEFAULT/*259, 221, java.awt.Image.SCALE_SMOOTH*/);
            ImageIcon newIcono = new ImageIcon(newimg);
            lbImagen.setIcon(newIcono);

             rutaimagen = fil;
            System.out.println(fil + " Foto " + lbImagen.getWidth() + " " + lbImagen.getHeight());
        }
    }


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed
    public void Guardar() {
//        persona1.setCedula(txtCedula.getText());
        persona1.setNombres(txtNombre.getText());
        persona1.setApellidos(txtApellido.getText());
        persona1.setTelefono(txtCell.getText());
        persona1.setTelefono2(txtConven.getText());
        persona1.setCorreo(txtCorreo.getText());
        persona1.setFechaNacimiento(fecha1);
        persona1.setDireccion(txtDireccion.getText());
        persona1.setFechaActualizacion(fechaActual);
        persona1.setUsuarioActualizacion(BigInteger.valueOf(us1.getIdUsuario()));
        SeTipoPersona tp = ObtenerDTO.ObtenerSeTipoPersona(cbTipoPersona1.getSelectedItem().toString());
        persona1.setIdTipoPersona(tp);
        persona1.setRutaImagen(rutaimagen);

        try {
            mp.edit(persona1);
        } catch (Exception ex) {
            Logger.getLogger(Editar_persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImagenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImagenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            Guardar();
        }
    }//GEN-LAST:event_btnImagenKeyPressed

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
        bloqueo();
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        //        char c = evt.getKeyChar();
        //        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
        //            getToolkit().beep();
        //            evt.consume();
        //        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased

    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        txtNombre.setText(txtNombre.getText().toUpperCase());
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoFocusLost
        txtApellido.setText(txtApellido.getText().toUpperCase());
    }//GEN-LAST:event_txtApellidoFocusLost

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        txtDireccion.setText(txtDireccion.getText().toUpperCase());
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtCellKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCellKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCellKeyTyped

    private void txtConvenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConvenKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtConvenKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        char c = evt.getKeyChar();
        char mas = '+', por = '*', div = '/', dp = ':', pc = ';', c2 = ',', p1 = '{', p2 = '}';
        char lla1 = '[', el = '^', lla2 = ']', el2 = '¿', co = '?', co2 = '¡', c3 = '!', d = '"', e = '#';
        char col = '$', a = '!', b = '=', e2 = '%', f = '&', g = '=', h = 'º', i = 'ª', j = '(', k = ')', l = '<', m = '>';
        char n = 'ç', o = '´', p = '`', q = '¨', r = 'Ñ', s = '·', t = 'ñ';
        if (Character.isWhitespace(c) || c == mas || c == por || c == div || c == dp
                || c == pc || c == c2 || c == p1 || c == p2 || c == lla1 || c == lla2
                || c == el || c == el2 || c == co || c == co2 || c == c3 || c == d || c == e
                || c == col || c == a || c == b || c == e2 || c == f || c == g || c == h
                || c == i || c == j || c == k || c == l || c == m || c == n || c == o || c == p
                || c == q || c == r || c == s || c == t) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void BotonFecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonFecha1ActionPerformed
        //////////        // TODO add your handling code here:
        Calendario calen = new Calendario(new javax.swing.JFrame(), true);
        calen.setVisible(true);
        fecha1 = calen.getFecha();
        int com = Fecha.compararFecha(fecha1, (Date) Fecha.FechaSql());

        if (com == 1 || com == 0) {
            JOptionPane.showMessageDialog(null, "Fecha invalida");
            fecha = null;
            fecha.setText(" ");
        } else {
            fecha.setText(Fecha.getStringFecha(fecha1));

        }
    }//GEN-LAST:event_BotonFecha1ActionPerformed

//    public void VaciarImagen() {
//        // String fil = "\\G:\\sin-imagen.png";
//        String fil = "//home//ineval//Escritorio//P-FARMACIA UBUNTU//sin-imagen.png";
//        lbImagen.setIcon(new ImageIcon(fil));
//        ImageIcon icon = new ImageIcon(fil);
//        Image img = icon.getImage();
//        System.out.println(fil + " Foto " + lbImagen.getWidth() + " " + lbImagen.getHeight());
//        Image newimg = img.getScaledInstance(259, 221, java.awt.Image.SCALE_SMOOTH);
//        ImageIcon newIcono = new ImageIcon(newimg);
//        lbImagen.setIcon(newIcono);
//        imagen = fil;
//    }
//    private void getPicture(String path) {
//        JFileChooser dig = new JFileChooser(path);
//        dig.setFileFilter(new FileNameExtensionFilter("Archivos de imagen",
//                "tif", "jpg", "jpeg", "png", "gif"));
//        int opcion = dig.showOpenDialog(this);
//        if (opcion == JFileChooser.APPROVE_OPTION) {
//            String fil = dig.getSelectedFile().getPath();
//            lbImagen.setIcon(new ImageIcon(fil));
//            ImageIcon icon = new ImageIcon(fil);
//            Image img = icon.getImage();
//            Image newimg = img.getScaledInstance(lbImagen.getWidth(),lbImagen.getHeight(),Image.SCALE_DEFAULT/*259, 221, java.awt.Image.SCALE_SMOOTH*/);
//            ImageIcon newIcono = new ImageIcon(newimg);
//            lbImagen.setIcon(newIcono);
//            imagen = dig.getSelectedFile().getPath();
//            // rutaimagen = Cadenas.getPathMysql(dig.getSelectedFile().getPath());
//            System.out.println(fil + " Foto " + lbImagen.getWidth() + " " + lbImagen.getHeight());
//        }
//    }
    public void CargarRol() {
        List<SeTipoPersona> listar = tpc.findSeTipoPersonaEntities();
        for (int i = 0; i < listar.size(); i++) {

            cbTipoPersona1.addItem(listar.get(i).getNombre());
        }
        cbTipoPersona1.setSelectedItem(persona1.getIdTipoPersona().getNombre());

    }
        public void copiarImagen(String origen) {
        Path origenPath = FileSystems.getDefault().getPath(origen);
        Path destinoPath = FileSystems.getDefault().getPath(Destino);

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    private void getPicture2(String path) {
        lbImagen.setIcon(new ImageIcon(path));
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(lbImagen.getWidth(), lbImagen.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newIcono = new ImageIcon(newimg);
        lbImagen.setIcon(newIcono);
//        System.out.println(fil + " Foto " + lbImagen.getWidth() + " " + lbImagen.getHeight());
//        System.out.println("ruta= " + rutaimagen + "\n"
//                + "ruta2 " + fil);
    }

//    public void Guardar() {
//        if (txtCedula.getText() == null ) {
//            JOptionPane.showMessageDialog(null, "Revise el campo cédula");
//        }else if (txtNombre.getText().length() < 3 || txtApellido.getText().length() < 3 ) {
//            JOptionPane.showMessageDialog(null, "Ingrese un nombre o paellido válido ");
//        }else if (txtCell.getText().length() < 9) {
//            JOptionPane.showMessageDialog(null, "Ingrese un número de contacto válido ");
//        }else if (txtCell.getText().length() > 12) {
//            JOptionPane.showMessageDialog(null, "Exede la cantidad de números para un celular");
//        }else if (txtCorreo.getText().length() < 5) {
//            JOptionPane.showMessageDialog(null, "Ingrese un correo válido ");
//        }else if (txtDireccion.getText().length() < 4) {
//            JOptionPane.showMessageDialog(null, "Ingrese una dirección válida ");
//        }else if (txtObservacion.getText().length() < 4) {
//            JOptionPane.showMessageDialog(null, "Ingrese una observación válida ");
//        }else if (txtPass.getText().length() < 6) {
//            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres");
//        }else if (!txtPass.getText().equals(txtConPass.getText())) {
//            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
//        }else if (cbEstado.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione un estado válido");
//        }else if (cbGenero.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione un género válido");
//        }else if (cbRol.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione un rol válido");
//        }else {
//            int r = JOptionPane.showConfirmDialog(null, "¿Está seguro de actualizar", "", JOptionPane.YES_NO_OPTION);
//
//        if (r == JOptionPane.YES_OPTION) {
//            
//            Usuario_S obj = new Usuario_S();
//            
//            obj.setApellido(obj1.getApellidos());
//            obj.setCargo(obj1.getCargo());
//            obj.setCedula(obj1.getCedula());
//            obj.setConvencional(obj1.getConvencional());
//            obj.setCorreo(obj1.getCorreo());
//            obj.setDireccion(obj1.getDireccion());
//            obj.setEstado(obj1.getEstado());
//            obj.setFecha_registro(Date.valueOf(obj1.getFecha_registro()));
//            obj.setGenero(obj1.getGenero());
//            obj.setId_sesion(obj1.getId_sesion());
//            obj.setNombre(obj1.getNombres());
//            obj.setTelefono(obj1.getTelefono());
//            obj.setIp_equipo(Operaciones.getIpDispositivo());
////            obj.setIp_publico(Operaciones.getIpPublica().getIp_publica_full());
//            obj.setDir_ip_completa(Operaciones.getIpLocalCompleta());
//            obj.setUsuario_equipo(Operaciones.getNombreDispositivo());
//            obj.setObservacion(obj.getObservacion());
//            
//            try {
//                crud.Respaldo_usuario(obj);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e);
//            }
//            
////            System.out.println("apellido prueba:"+obj1.getApellidos());
//            
//            
//            obj.setCedula(txtCedula.getText());
//            obj.setNombre(txtNombre.getText());
//            obj.setApellido(txtApellido.getText());
//            obj.setTelefono(txtCell.getText());
//            obj.setRuta_imagen(imagen);
//            obj.setGenero(cbGenero.getSelectedItem().toString());
//            obj.setCargo(cbRol.getSelectedItem().toString());
//            obj.setConvencional(txtConven.getText());
//            obj.setCorreo(txtCorreo.getText());
//            obj.setDireccion(txtDireccion.getText());
//            obj.setObservacion(txtObservacion.getText());
//            obj.setIp_equipo(Operaciones.getIpDispositivo());
////            obj.setIp_publico(Operaciones.getIpPublica().getIp_publica_full());
//            obj.setDir_ip_completa(Operaciones.getIpLocalCompleta());
//            obj.setUsuario_equipo(Operaciones.getNombreDispositivo());
//            obj.setPassword(txtPass.getText());
//            obj.setEstado(cbEstado.getSelectedItem().toString());
//            obj.setId_sesion(Long.valueOf(txtId.getText()));
//            try {
//            String a =  crud.Actualizar_usuario(obj);
//            JOptionPane.showMessageDialog(this, a);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e);
//            }
//            setVisible(false);
//        }
//            
//        }
//    }
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
            java.util.logging.Logger.getLogger(Editar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Editar_persona dialog = new Editar_persona(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BotonFecha1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnLimpiarImg;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbTipoPersona1;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbImagen;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCell;
    private javax.swing.JTextField txtConven;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void cargarValores(SePersonas persona) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        txtCedula.setText(persona.getCedula());
        txtNombre.setText(persona.getNombres());
        txtApellido.setText(persona.getApellidos());
        txtDireccion.setText(persona.getDireccion());
        txtCorreo.setText(persona.getCorreo());
        txtCell.setText(persona.getTelefono());
        txtConven.setText(persona.getTelefono2());
        txtCorreo.setText(persona.getCorreo());
        fecha.setText(Fecha.getStringFecha(new java.sql.Date(persona.getFechaNacimiento().getTime())));
        getPicture2(persona.getRutaImagen());
        copiarImagen(rutaimagen);
    }
}
