/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.proveedor;

import ec.com.asofar.dao.CoProveedoresJpaController;
import ec.com.asofar.dao.SePaisJpaController;
import ec.com.asofar.dao.SeTipoPersonaJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.CoProveedores;
import ec.com.asofar.dto.SePais;
import ec.com.asofar.dto.SeTipoPersona;
import ec.com.asofar.util.EntityManagerUtil;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin1
 */
public class EditarProveedor extends javax.swing.JDialog {
    int x,y;
    List<CoProveedores>lista;
    CoProveedores cprov = new CoProveedores();
    CoProveedoresJpaController cpcont = new CoProveedoresJpaController(EntityManagerUtil.ObtenerEntityManager());
    Date d = new Date();
    SeTipoPersona stp = new SeTipoPersona();
    List<SeTipoPersona> listatipop;
    SeTipoPersonaJpaController stpcont = new SeTipoPersonaJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SePais>listarpais;
    SePaisJpaController paiscont = new SePaisJpaController(EntityManagerUtil.ObtenerEntityManager());
    SePais pais = new SePais();
    /**
     * Creates new form EditarProveedor
     */
    public EditarProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public EditarProveedor(java.awt.Frame parent, boolean modal,CoProveedores cpro) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cprov = cpro;
        listarpais = paiscont.findSePaisEntities();
        cargarcombotipo();
        LlenarDatos(cpro);
        jPanel1.setSize(624,402);
        cargarcombopais();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txttelfseg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpaginaweb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtidentificacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbtipopersona = new javax.swing.JComboBox<>();
        cbcontribuyente = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtcodigocont = new javax.swing.JTextField();
        txtnombrecomercial = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtobservacion = new javax.swing.JTextField();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        cbpais = new javax.swing.JComboBox<>();
        btninactivar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACTUALIZAR PROVEEDOR");
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

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("DIRECCION:");

        txtdireccion.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("TELEFONO:");

        txttelefono.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });

        txttelfseg.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txttelfseg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelfsegActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("2do. TELEFONO:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel6.setText("PAGINA WEB:");

        txtpaginaweb.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtpaginaweb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaginawebActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("No. IDENTIFICACION:");

        txtidentificacion.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtidentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidentificacionActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("EMAIL:");

        txtemail.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel9.setText("TIPO DE PERSONA:");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel10.setText("PAIS:");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel11.setText("CONTRIBUYENTE ESPECIAL:");

        cbtipopersona.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbtipopersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));

        cbcontribuyente.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cbcontribuyente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel12.setText("CODIGO CONTRIBUYENTE:");

        txtcodigocont.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        txtnombrecomercial.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        txtnombrecomercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombrecomercialActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel14.setText("NOMBRE COMERCIAL:");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel13.setText("OBSERVACIONES:");

        txtobservacion.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        btnactualizar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btnactualizar.setForeground(new java.awt.Color(64, 145, 64));
        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setBackground(new java.awt.Color(153, 28, 35));
        btncancelar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btncancelar.setForeground(new java.awt.Color(254, 254, 254));
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        cbpais.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        btninactivar.setBackground(new java.awt.Color(1, 1, 1));
        btninactivar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        btninactivar.setForeground(new java.awt.Color(254, 254, 254));
        btninactivar.setText("DESACTIVAR");
        btninactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninactivarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(209, 30, 30));
        jLabel15.setText("*");

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(209, 30, 30));
        jLabel16.setText("*");

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(209, 30, 30));
        jLabel17.setText("*");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(209, 30, 30));
        jLabel19.setText("*");

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(209, 30, 30));
        jLabel20.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(51, 51, 51)
                .addComponent(jLabel17)
                .addGap(0, 0, 0)
                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(71, 71, 71)
                .addComponent(jLabel20)
                .addGap(2, 2, 2)
                .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addGap(50, 50, 50)
                .addComponent(txttelfseg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addGap(35, 35, 35)
                .addComponent(txtpaginaweb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addComponent(jLabel19)
                .addGap(2, 2, 2)
                .addComponent(txtidentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addGap(69, 69, 69)
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(cbtipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addGap(89, 89, 89)
                .addComponent(cbpais, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addComponent(jLabel15)
                .addGap(2, 2, 2)
                .addComponent(txtnombrecomercial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(cbcontribuyente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addGap(12, 12, 12)
                .addComponent(txtcodigocont, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btninactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel17))
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel20))
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelfseg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6))
                    .addComponent(txtpaginaweb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel19))
                    .addComponent(txtidentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16))
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9))
                    .addComponent(cbtipopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbpais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrecomercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbcontribuyente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcodigocont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))))
                .addGap(12, 12, 12)
                .addComponent(jLabel13)
                .addGap(6, 6, 6)
                .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnactualizar)
                    .addComponent(btninactivar)
                    .addComponent(btncancelar))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void cargarcombotipo(){
        listatipop = stpcont.findSeTipoPersonaEntities();
        for (int i = 0; i <listatipop.size(); i++) {
            cbtipopersona.addItem(listatipop.get(i).getNombre());
        }
        for (int i = 0; i <listatipop.size(); i++) {
            if(cprov.getTipoPersona().getIdTipoPersona() == listatipop.get(i).getIdTipoPersona()){
                cbtipopersona.setSelectedItem(listatipop.get(i).getNombre());
            }
                }        
    }
    public void cargarcombopais(){
        listarpais = paiscont.findSePaisEntities();
        for (int i = 0; i <listarpais.size(); i++) {
            cbpais.addItem(listarpais.get(i).getNombre());
        }
        for (int i = 0; i <listarpais.size(); i++) {
            if(pais.getIdPais() == listarpais.get(i).getIdPais()){
                cbpais.setSelectedItem(listarpais.get(i).getNombre());
            }
                }        
    }
    public void LlenarDatos(CoProveedores cpro){
        txtnombre.setText(cpro.getNombre());
        txtdireccion.setText(cpro.getDireccion());
        txttelefono.setText(cpro.getTelefono1());
        txttelfseg.setText(cpro.getTelefono2());
        txtpaginaweb.setText(cpro.getPaginaWeb());
        txtidentificacion.setText(cpro.getNumeroIdentificacion());
        txtemail.setText(cpro.getEmail());               
        txtnombrecomercial.setText(cpro.getNombreComercial());
        cbcontribuyente.setSelectedItem(cprov.getContribuyenteEspecial());
        txtcodigocont.setText(cpro.getCodigoContribuyente());
        txtobservacion.setText(cpro.getObservaciones());
    }
    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x-x,point.y-y);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txttelfsegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelfsegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelfsegActionPerformed

    private void txtpaginawebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaginawebActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaginawebActionPerformed

    private void txtidentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidentificacionActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtnombrecomercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombrecomercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecomercialActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        int responsive = JOptionPane.showConfirmDialog(null,"SEGURO DESEA GUARDAR LOS CAMBIOS","",JOptionPane.YES_NO_OPTION);
        if(responsive == JOptionPane.YES_OPTION){                                
                if ("".equals(txtnombre.getText())
                        || "".equals(txtnombrecomercial.getText())
                        || "".equals(txtidentificacion.getText())
                        || "".equals(txtemail.getText())
                        || "".equals(txtdireccion.getText())) {
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS OBLIGATORIOS");
                } else {
            cprov.setNombre(txtnombre.getText());
            cprov.setDireccion(txtdireccion.getText());
            cprov.setTelefono1(txttelefono.getText());
            cprov.setTelefono2(txttelfseg.getText());
            cprov.setPaginaWeb(txtpaginaweb.getText());
            cprov.setNumeroIdentificacion(txtidentificacion.getText());
            cprov.setEmail(txtemail.getText());
            cprov.setTipoPersona(ObtenerDTO.ObtenerSeTipoPersona(cbtipopersona.getSelectedItem().toString()));
            cprov.setIdPais(ObtenerDTO.ObtenerSePais(cbpais.getSelectedItem().toString()));
            cprov.setNombreComercial(txtnombrecomercial.getText());
            cprov.setContribuyenteEspecial(cbcontribuyente.getSelectedItem().toString());
            cprov.setCodigoContribuyente(txtcodigocont.getText());
            cprov.setObservaciones(txtobservacion.getText());
            cprov.setFechaActualizacion(d);
            cprov.setEstado('A');
            try {
                cpcont.edit(cprov);
                JOptionPane.showMessageDialog(this, "PROVEEDOR ACTUALIZADO");
                setVisible(false);
                ConsultaProveedor cp = new ConsultaProveedor(new javax.swing.JFrame(),true);
                cp.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(EditarProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "SEGURO QUE DESEA SALIR", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
        setVisible(false);
        ConsultaProveedor cp = new ConsultaProveedor(new javax.swing.JFrame(),true);
        cp.setVisible(true);}
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btninactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninactivarActionPerformed
        int responsive = JOptionPane.showConfirmDialog(null,"SEGURO DESEA DESACTIVAR EL PROVEEDOR","",JOptionPane.YES_NO_OPTION);
        if(responsive == JOptionPane.YES_OPTION){
        cprov.setEstado('I');
        cprov.setFechaActualizacion(d);
        try {
            cpcont.edit(cprov);
            JOptionPane.showMessageDialog(null,"PROVEEDOR DESACTIVADO");
            setVisible(false);
            ConsultaProveedor cp = new ConsultaProveedor(new javax.swing.JFrame(),true);
            cp.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(EditarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btninactivarActionPerformed

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
            java.util.logging.Logger.getLogger(EditarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarProveedor dialog = new EditarProveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btninactivar;
    private javax.swing.JComboBox<String> cbcontribuyente;
    private javax.swing.JComboBox<String> cbpais;
    private javax.swing.JComboBox<String> cbtipopersona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtcodigocont;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtidentificacion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombrecomercial;
    private javax.swing.JTextField txtobservacion;
    private javax.swing.JTextField txtpaginaweb;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelfseg;
    // End of variables declaration//GEN-END:variables
}
