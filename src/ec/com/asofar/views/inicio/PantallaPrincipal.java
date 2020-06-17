/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.inicio;

import ec.com.asofar.dao.VeCajaJpaController;
import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.daoext.SubGruposExt;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeOpcionesMenu;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeCaja;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.util.ActionItem;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Fondo;
import ec.com.asofar.util.Reflection;
import ec.com.asofar.views.compras.crearOrdenCompraForm;
import ec.com.asofar.views.supgrupos.ConsultaSubgrupos;
import ec.com.asofar.views.venta.Venta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 *
 * @author admin1
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    VeDetalleCajaJpaController cajaDetC = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    VeCajaJpaController cajaC = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    List<SeOpcionesMenu> lista = null;
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;
    SubGruposExt cSubgrupos = new SubGruposExt(EntityManagerUtil.ObtenerEntityManager());
    //static JMenuItem salida = new JMenuItem("SALIR");
    java.util.Date fechaActual = new java.util.Date();
    
    public PantallaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.add(new Fondo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), BorderLayout.CENTER);
        JMenu meOpciones = new JMenu();
        meOpciones.setLabel("INVENTARIO");
        JMenu mei = new JMenu();
        mei.setLabel("MANTENIMIENTO");
        JMenuItem mei2 = new JMenuItem();
        mei2.setLabel("MANTENIMIENTO DE GRUPOS");
        mei.add(mei2);
        meOpciones.add(mei);
        meMenuBase.add(meOpciones);
        
    }
    
    public PantallaPrincipal(SeUsuarios us, SeEmpresa em, SeSucursal su) {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
//        this.add(new Fondo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), BorderLayout.CENTER);
        us1 = us;
        em1 = em;
        su1 = su;
        lista = cSubgrupos.ObtenerMenu(us);
        cargarMenu(lista);
//        salida.addActionListener((e) -> {
//            System.exit(0);
//        });
        txtUsuario.setText(us1.getUsuario());
        Timer tiempo = new Timer(100, new PantallaPrincipal.horas());
        tiempo.start();
        jdpescritorio.add(new Fondo(Toolkit.getDefaultToolkit().getScreenSize().width - 0, Toolkit.getDefaultToolkit().getScreenSize().height - 30), BorderLayout.CENTER);
    }
    
    class horas implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            java.util.Date sistHora = new java.util.Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy = Calendar.getInstance();
            txtFechaHora.setText(FechaActual() + " " + String.format(format.format(sistHora), hoy));
            
        }
    }
    
    public static String FechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatoFecha.format(fecha);
    }
    
    public void cargarMenu(List<SeOpcionesMenu> lis) {
//        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
//        txtFechaHora.setText(FechaActual() + "  " + df1.format(fechaActual));
        for (int i = 0; i < lis.size(); i++) {
            if (lis.get(i).getIdPadre() == null) {
                if (lis.get(i).getRuta() == null) {
                    if (lis.get(i).getNombre().equals("SALIR")) {
                        JMenuItem item = new JMenuItem(lis.get(i).getNombre());
                        item.setIcon(new javax.swing.ImageIcon(getClass().getResource(lis.get(i).getRutaIcono())));
                        item.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        item.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        item.setPreferredSize(new java.awt.Dimension(90, 65));
                        item.setMinimumSize(new Dimension(20, 20));
                        item.setMaximumSize(new Dimension(90, 70));
                        item.setPreferredSize(new Dimension(120, 30));
                        /*  item.setOpaque(true);
                        item.setBackground(Color.white);*/
                        item.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                        item.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                
                                System.exit(0);
                                //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        meMenuBase.add(item);
                        
                    } else {
                        
                        JMenu menu = new JMenu(lis.get(i).getNombre());

                        //Ruta
//                        System.out.println("Ruta.." + lis.get(i).getRutaIcono());
                        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource(lis.get(i).getRutaIcono())));
                        menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        menu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        /*menu.setOpaque(true);
                        menu.setBackground(Color.white);*/
                        menu.setMinimumSize(new Dimension(20, 20));
                        menu.setMaximumSize(new Dimension(90, 70));
                        //menu.setPreferredSize(new Dimension(120,30));
                        menu.setPreferredSize(new java.awt.Dimension(90, 65));
                        menu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                        
                        for (int j = 0; j < lis.get(i).getSeOpcionesMenuList().size(); j++) {
                            for (int k = 0; k < lis.size(); k++) {
                                if (lis.get(i).getSeOpcionesMenuList().get(j) == lis.get(k)) {
                                    if (lis.get(i).getSeOpcionesMenuList().get(j).getRuta() == null) {
                                        JMenu menu2 = new JMenu(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
//                                    System.out.println(" meg "+lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                        for (int l = 0; l < lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().size(); l++) {
                                            for (int m = 0; m < lis.size(); m++) {
                                                if (lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().get(l) == lis.get(m)) {
                                                    if (lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().get(l).getRuta() == null) {
                                                        JMenu menu3 = new JMenu(lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().get(l).getNombre());
                                                        menu2.add(menu3);
                                                    } else {
                                                        JMenuItem item = new JMenuItem(lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().get(l).getNombre());
                                                        item.addActionListener(ActionItem.Obtener(lis.get(i).getSeOpcionesMenuList().get(j).getSeOpcionesMenuList().get(l).getRuta(), us1, em1, su1));
                                                        menu2.add(item);
                                                    }
                                                }
                                            }
                                        }
                                        menu.add(menu2);
                                    } else {
//                                    String permiso = pVender();
//                                    if(permiso.equals("no")){
//                                        JOptionPane.showMessageDialog(null, "Debe abrir Caja para Vender");
//                                    }else{/*puede vender*/
//                                        
//                                        }
                                        String nombre = "GENERAR VENTA ";
                                        if (lis.get(i).getSeOpcionesMenuList().get(j).getNombre().equals(nombre)) {
                                            JMenuItem item = new JMenuItem(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                            item.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
//                                                String permiso = "si";
                                                    String permiso = pVender();
                                                    if (permiso.equals("si")) {
                                                         Venta fac = new Venta(new javax.swing.JFrame(), true, us1, em1, su1);
                                                        jdpescritorio.add(fac);
                                                        fac.show();
                                                    } else {
                                                         JOptionPane.showMessageDialog(null, "Debe abrir Caja para Vender");
                                                    }
                                                }
                                            });
                                            menu.add(item);
                                        } else {
//                                    JMenuItem item = new JMenuItem(lis.get(i).getNombre());
//                                    System.out.println("menu ffg "+lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                            JMenuItem item = new JMenuItem(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                            item.addActionListener(ActionItem.Obtener(lis.get(i).getSeOpcionesMenuList().get(j).getRuta(), us1, em1, su1));
                                            menu.add(item);
                                        }
                                        
                                    }
                                }
                            }
                        }
                        meMenuBase.add(menu);
                    }
                } else {
                    JMenuItem item = new JMenuItem(lis.get(i).getNombre());
                    item.addActionListener(ActionItem.Obtener(lis.get(i).getRuta(), us1, em1, su1));
                    meMenuBase.add(item);
                }
                
            }
            
        }
//        salida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/B_SALIR.jpeg")));
//        salida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        salida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//       // salida.setPreferredSize(new java.awt.Dimension(5, 55));
//        salida.setPreferredSize(new java.awt.Dimension(40, 65));
//        salida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
//        meMenuBase.add(salida);
    }
    
    public String pVender() {
        String v = null;
        List<VeDetalleCaja> listadetallecaja = cajaDetC.findVeDetalleCajaEntities();
//        List<VeCaja> listaCaja = cajaC.findVeCajaEntities();
        for (int i = 0; i < listadetallecaja.size(); i++) {
            if (listadetallecaja.get(i).getEstado().equals("A")
                    && listadetallecaja.get(i).getIdUsuario().longValue() == us1.getIdUsuario()
                    && listadetallecaja.get(i).getFechaCierre() == null
                    && listadetallecaja.get(i).getHoraCierre() == null) {
                v = "si";
            } else {
                v = "no";
            }
        }
        return v;
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
        jTextPane1 = new javax.swing.JTextPane();
        jdpescritorio = new javax.swing.JDesktopPane();
        txtFechaHora = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        meMenuBase = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jScrollPane1.setViewportView(jTextPane1);

        jdpescritorio.setBackground(new java.awt.Color(255, 255, 255));
        jdpescritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtFechaHora.setEditable(false);
        txtFechaHora.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        txtUsuario.setEditable(false);

        jdpescritorio.setLayer(txtFechaHora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jdpescritorio.setLayer(txtUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jdpescritorioLayout = new javax.swing.GroupLayout(jdpescritorio);
        jdpescritorio.setLayout(jdpescritorioLayout);
        jdpescritorioLayout.setHorizontalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jdpescritorioLayout.setVerticalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdpescritorioLayout.createSequentialGroup()
                .addGap(0, 415, Short.MAX_VALUE)
                .addGroup(jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setJMenuBar(meMenuBase);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }
    
    public static SeUsuarios obtenerUsuario() {
        return null;
        
    }

//        JMenu menu=null;
//        for(int i = 0 ; i < lis.size();i++){
//            if(lis.get(i).getIdPadre() == null){
//                 menu = new JMenu(lis.get(i).getNombre());
//              
//                 meMenuBase.add(menu);
//            }
//        }
//        for(int i = 0 ; i < lis.size();i++){
//            if(lis.get(i).getSeOpcionesMenuList() != null){
//                
//                
//                if(lis.get(i).getIdPadre()!=null){
//                String valor2=lis.get(i).getIdPadre().getNombre();
//                for (int j = 0; j < meMenuBase.getMenuCount(); j++) {
//                    String valor1= meMenuBase.getMenu(j).getText();
//                    if(valor1.equals(valor2)){
//                    JMenu menu2 = new JMenu(lis.get(i).getNombre());
//                        List<SeOpcionesMenu> lista2 = lis.get(i).getSeOpcionesMenuList();
//                        for (int k = 0; k < lista2.size(); k++) {
//                            for (int l = 0; l < lis.size(); l++) {
//                                if(lis.get(l)==lista2.get(k)){
//                                JMenuItem item = new JMenuItem(lista2.get(k).getNombre());
//                            menu2.add(item);
//                            String ruta=lista2.get(k).getRuta();
//                           item.addActionListener(new ActionListener() {
//                                    @Override
//                                    public void actionPerformed(ActionEvent e) {
//                                        Reflection re= new Reflection();
//                                        
//                                        re.Llamar(ruta,us1,em1,su1);
//                                        
//                                         //To change body of generated methods, choose Tools | Templates.
//                                    }
//                                });
//                           
//                           
//                                }
//                                
//                            }
//                            
//                        }
//                        System.out.println(valor1+" "+valor2);
//                        meMenuBase.getMenu(j).add(menu2); 
//                    } 
//                }
//                
//                }
//                
//                
//               
//                
//            }
//        }
//        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JDesktopPane jdpescritorio;
    private javax.swing.JMenuBar meMenuBase;
    private javax.swing.JTextField txtFechaHora;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
