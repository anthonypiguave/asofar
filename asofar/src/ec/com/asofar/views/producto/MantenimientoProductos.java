/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.producto;

import ec.com.asofar.dao.PrArticuloJpaController;
import ec.com.asofar.dao.PrGruposJpaController;
import ec.com.asofar.dao.PrMedidasJpaController;
import ec.com.asofar.dao.PrProductosJpaController;
import ec.com.asofar.dao.PrSubgruposJpaController;
import ec.com.asofar.daoext.ObtenerDTO;
import ec.com.asofar.dto.PrArticulo;
import ec.com.asofar.dto.PrGrupos;
import ec.com.asofar.dto.PrMedidas;
import ec.com.asofar.dto.PrMedidasPK;
import ec.com.asofar.dto.PrProductos;
import ec.com.asofar.dto.PrSubgrupos;
import ec.com.asofar.dto.PrTipoMedidas;
import ec.com.asofar.dto.PrTipoPresentacion;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Tablas;
import ec.com.asofar.views.inicio.PantallaPrincipal;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author alumno
 */
public class MantenimientoProductos extends javax.swing.JDialog {

    int x, y;
    PrGruposJpaController cgrupo = new PrGruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrSubgruposJpaController csub = new PrSubgruposJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrArticuloJpaController carti = new PrArticuloJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrMedidasJpaController cmed = new PrMedidasJpaController(EntityManagerUtil.ObtenerEntityManager());
    PrProductosJpaController cprod = new PrProductosJpaController(EntityManagerUtil.ObtenerEntityManager());

    List<PrGrupos> listgrupo = cgrupo.findPrGruposEntities();
    List<PrSubgrupos> listsub = csub.findPrSubgruposEntities();
    List<PrArticulo> listart = carti.findPrArticuloEntities();
    List<PrMedidas> listmed = cmed.findPrMedidasEntities();
    List<PrProductos> listprod = cprod.findPrProductosEntities();
    PrMedidas prmed;

    Tablas tt = new Tablas();
    String valor = "";
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;

    public MantenimientoProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        CargarArbol();
    }

    public MantenimientoProductos(java.awt.Frame parent, boolean modal, SeUsuarios us, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        CargarArbol();
        us1 = us;
        em1 = em;
        su1 = su;

    }

    public void CargarArbol() {
        try {
            DefaultMutableTreeNode grupo = new DefaultMutableTreeNode();

            for (PrGrupos g : listgrupo) {
                DefaultMutableTreeNode hgru = new DefaultMutableTreeNode();

                //  hgru.setUserObject(g.getNombre());
                if (g.getEstado().equals("A")) {
                    hgru.setUserObject(g.getNombre());
                    grupo.add(hgru);

                    for (PrSubgrupos sg : listsub) {
                        DefaultMutableTreeNode hsub = new DefaultMutableTreeNode();
                        if (sg.getPrSubgruposPK().getIdGrupo() == g.getIdGrupo()) {
                            if (sg.getEstado().equals("A")) {
                                hsub.setUserObject(sg.getNombre());
                                hgru.add(hsub);
                            }
                            for (PrArticulo a : listart) {
                                DefaultMutableTreeNode hart = new DefaultMutableTreeNode();
                                if (a.getPrArticuloPK().getIdSubgrupo() == sg.getPrSubgruposPK().getIdSubgrupo()) {
                                    if (a.getEstado().equals("A")) {
                                        hart.setUserObject(a.getNombreArticulo());
                                        hsub.add(hart);

                                    }
                                }
                            }
                        }

                    }
                }
            }

            DefaultTreeModel model = new DefaultTreeModel(grupo);
            this.arbol.setModel(model);
        } catch (Exception e) {
            System.out.println("Error al cargar arbol " + e.getMessage());
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_med = new javax.swing.JTable();
        BotonNuevaMedida = new javax.swing.JButton();
        txtfiltro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_prod = new javax.swing.JTable();
        BotonNuevoProducto = new javax.swing.JButton();
        txtfiltro1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        arbol.setRootVisible(false);
        arbol.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arbolValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(arbol);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MEDIDAS");

        tabla_med.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccione un articulo para cargar los datos..."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_med.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla_med.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_medMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_med);

        BotonNuevaMedida.setText("NUEVA MEDIDA");
        BotonNuevaMedida.setEnabled(false);
        BotonNuevaMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevaMedidaActionPerformed(evt);
            }
        });

        txtfiltro.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel5.setText("BUSCAR:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(BotonNuevaMedida)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonNuevaMedida)
                .addGap(5, 5, 5))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRODUCTOS");

        tabla_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccione una medida para cargar los productos..."
            }
        ));
        tabla_prod.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_prodMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_prod);

        BotonNuevoProducto.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        BotonNuevoProducto.setForeground(new java.awt.Color(101, 171, 99));
        BotonNuevoProducto.setText("NUEVO PRODUCTO");
        BotonNuevoProducto.setEnabled(false);
        BotonNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevoProductoActionPerformed(evt);
            }
        });

        txtfiltro1.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        txtfiltro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltro1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltro1KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel4.setText("BUSCAR:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setBackground(new java.awt.Color(0, 153, 153));
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MANTENIMIENTO DE PRODUCTOS");
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

        BotonSalir.setBackground(new java.awt.Color(208, 57, 52));
        BotonSalir.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        BotonSalir.setForeground(new java.awt.Color(254, 254, 254));
        BotonSalir.setText("SALIR");
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevoProductoActionPerformed
        // TODO add your handling code here:
        
        NuevoProducto np = new NuevoProducto(new javax.swing.JFrame(), true, prmed, us1, em1);
        setVisible(false);
        np.setVisible(true);
    }//GEN-LAST:event_BotonNuevoProductoActionPerformed

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_BotonSalirActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_jLabel3MouseDragged

    private void arbolValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arbolValueChanged
        // TODO add your handling code here:
        try {
            TreePath objeto = arbol.getSelectionPath();
            if (evt.getPath().getPathCount() == 4) {
                System.out.println(objeto.getPathComponent(3));
                PrArticulo arti = ObtenerDTO.ObtenerPrArticulo(objeto.getPathComponent(3).toString());
                BotonNuevaMedida.setEnabled(true);
                tt.TablaMedida2(arti.getPrMedidasList(), tabla_med);
                //   Tablas.TablaMedida2(arti.getPrMedidasList(), tabla_med);
                System.out.println("eeeee");
            } else {
                System.out.println("nooooo");
                BotonNuevaMedida.setEnabled(false);
            }
        } catch (Exception e) {
            System.out.println("Error.." + e.getMessage() + e);
        }
    }//GEN-LAST:event_arbolValueChanged

    private void BotonNuevaMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevaMedidaActionPerformed
        // TODO add your handling code here:
        TreePath objeto = arbol.getSelectionPath();
        String valor = objeto.getPathComponent(3).toString();
        PrArticulo arti = ObtenerDTO.ObtenerPrArticulo(valor);
        NuevaMedida nv = new NuevaMedida(new javax.swing.JFrame(), true, arti, us1, em1);
        setVisible(false);
        nv.setVisible(true);
    }//GEN-LAST:event_BotonNuevaMedidaActionPerformed

    private void tabla_medMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_medMousePressed
       
        if (evt.getClickCount() == 1) {

            PrArticulo arti = new PrArticulo();
            PrTipoMedidas tipo = new PrTipoMedidas();
            PrTipoPresentacion pre = new PrTipoPresentacion();
            TreePath objeto = arbol.getSelectionPath();
            String valor = objeto.getPathComponent(3).toString();
            arti = ObtenerDTO.ObtenerPrArticulo(valor);
            tipo = ObtenerDTO.ObtenerPrTipoMedidas(tabla_med.getValueAt(tabla_med.getSelectedRow(), 0).toString());
            pre = ObtenerDTO.ObtenerPrTipoPresentacion(tabla_med.getValueAt(tabla_med.getSelectedRow(), 1).toString());
            System.out.println(pre + "" + arti + tipo);
            PrMedidasPK pk = new PrMedidasPK(arti.getPrArticuloPK().getIdArticulo(), arti.getPrArticuloPK().getIdGrupo(), arti.getPrArticuloPK().getIdSubgrupo(), pre.getIdTipoPresentacion(), tipo.getIdTipoMedidas());
            PrMedidas obj = ObtenerDTO.ObtenerPrMedidas(pk);
            System.out.println(obj + " Hola Mundoo " + pk);
            prmed=obj;
            Tablas.TablaProducto(obj.getPrProductosList(), tabla_prod);
            BotonNuevoProducto.setEnabled(true);
            System.out.println(tabla_med.getValueAt(tabla_med.getSelectedRow(), 2));
 if (tabla_med.getSelectedColumn()==2) {
     if ((boolean)tabla_med.getValueAt(tabla_med.getSelectedRow(), 2)) {
         
         try {
             obj.setEstado("I");
         
            cmed.edit(obj);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
     } else {
         try {
              obj.setEstado("A");
            cmed.edit(obj);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
     
            
        }
        }
    }//GEN-LAST:event_tabla_medMousePressed

    private void txtfiltro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltro1KeyReleased
        // TODO add your handling code here:
        valor = txtfiltro1.getText();
        Tablas.filtro(valor, tabla_prod);
    }//GEN-LAST:event_txtfiltro1KeyReleased

    private void txtfiltro1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltro1KeyTyped
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltro1KeyTyped

    private void txtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyReleased
        // TODO add your handling code here:
        valor = txtfiltro.getText();
        Tablas.filtro(valor, tabla_med);
    }//GEN-LAST:event_txtfiltroKeyReleased

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtfiltroKeyTyped

    private void tabla_prodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_prodMousePressed
        
        if(evt.getClickCount()==2){
            
            String valor=tabla_prod.getValueAt(tabla_prod.getSelectedRow(), 0).toString();
            PrProductos pro = ObtenerDTO.ObtenerProducto(valor);
            EditarProducto edi = new EditarProducto(new javax.swing.JFrame(), true, pro, us1, em1);
            setVisible(false);
            edi.setVisible(true);
        }

    }//GEN-LAST:event_tabla_prodMousePressed

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
            java.util.logging.Logger.getLogger(MantenimientoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantenimientoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantenimientoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantenimientoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                MantenimientoProductos dialog = new MantenimientoProductos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BotonNuevaMedida;
    private javax.swing.JButton BotonNuevoProducto;
    private javax.swing.JButton BotonSalir;
    private javax.swing.JTree arbol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_med;
    private javax.swing.JTable tabla_prod;
    private javax.swing.JTextField txtfiltro;
    private javax.swing.JTextField txtfiltro1;
    // End of variables declaration//GEN-END:variables
}
