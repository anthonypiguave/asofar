/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.inicio;

import ec.com.asofar.daoext.SubGruposExt;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeOpcionesMenu;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.util.ActionItem;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Fondo;
import ec.com.asofar.util.Reflection;
import ec.com.asofar.views.supgrupos.ConsultaSubgrupos;
import ec.com.asofar.views.venta.Venta;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author admin1
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    List<SeOpcionesMenu> lista = null;
    SeUsuarios us1;
    SeEmpresa em1;
    SeSucursal su1;
    SubGruposExt cSubgrupos = new SubGruposExt(EntityManagerUtil.ObtenerEntityManager());
    static JMenuItem salida = new JMenuItem("SALIR");

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
        salida.addActionListener((e) -> {
            System.exit(0);
        });
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
        salida.addActionListener((e) -> {
            System.exit(0);
        });
        
        jdpescritorio.add(new Fondo(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-75), BorderLayout.CENTER);
    }

    public void cargarMenu(List<SeOpcionesMenu> lis) {
        for (int i = 0; i < lis.size(); i++) {
            if (lis.get(i).getIdPadre() == null) {
                if (lis.get(i).getRuta() == null) {
                    JMenu menu = new JMenu(lis.get(i).getNombre());
                    for (int j = 0; j < lis.get(i).getSeOpcionesMenuList().size(); j++) {
                        for (int k = 0; k < lis.size(); k++) {
                            if (lis.get(i).getSeOpcionesMenuList().get(j) == lis.get(k)) {
                                if (lis.get(i).getSeOpcionesMenuList().get(j).getRuta() == null) {
                                    JMenu menu2 = new JMenu(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
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
                                    String nombre = "GENERAR VENTA ";
                                        if(lis.get(i).getSeOpcionesMenuList().get(j).getNombre().equals(nombre)){
                                        JMenuItem item = new JMenuItem(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                        item.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                Venta fac = new Venta(new javax.swing.JFrame(), true,us1, em1, su1);
                                                jdpescritorio.add(fac);
                                                fac.show();
                                            }
                                        });
                                        menu.add(item);
                                    } 
                                    else {

//                                    JMenuItem item = new JMenuItem(lis.get(i).getNombre());
                                        JMenuItem item = new JMenuItem(lis.get(i).getSeOpcionesMenuList().get(j).getNombre());
                                        item.addActionListener(ActionItem.Obtener(lis.get(i).getSeOpcionesMenuList().get(j).getRuta(), us1, em1, su1));
                                        menu.add(item);
                                    }
                                }
                            }
                        }
                    }
                    meMenuBase.add(menu);
                } else {
                    JMenuItem item = new JMenuItem(lis.get(i).getNombre());
                    item.addActionListener(ActionItem.Obtener(lis.get(i).getRuta(), us1, em1, su1));
                    meMenuBase.add(item);
                }

            }
        }
        meMenuBase.add(salida);
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
        meMenuBase = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jScrollPane1.setViewportView(jTextPane1);

        jdpescritorio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jdpescritorioLayout = new javax.swing.GroupLayout(jdpescritorio);
        jdpescritorio.setLayout(jdpescritorioLayout);
        jdpescritorioLayout.setHorizontalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );
        jdpescritorioLayout.setVerticalGroup(
            jdpescritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
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
    // End of variables declaration//GEN-END:variables
}
