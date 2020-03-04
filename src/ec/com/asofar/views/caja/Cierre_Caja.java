/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.views.caja;

import ec.com.asofar.PruebaImpresora.PruebaImpresion;
import ec.com.asofar.dao.VeCajaJpaController;
import ec.com.asofar.dao.VeDetalleCajaJpaController;
import ec.com.asofar.daoext.ValidacionCaja;
import ec.com.asofar.dto.SeEmpresa;
import ec.com.asofar.dto.SeSucursal;
import ec.com.asofar.dto.SeUsuarios;
import ec.com.asofar.dto.VeDetalleCaja;
import ec.com.asofar.util.ClaseReporte;
import ec.com.asofar.util.EntityManagerUtil;
import ec.com.asofar.util.Fecha;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ms24m
 */
public class Cierre_Caja extends javax.swing.JDialog {

    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int x, y;
    Date d_hora = new Date();
    Date d_fecha = new Date();
    SeUsuarios seUsuario;
    SeEmpresa seEmpresa;
    SeSucursal seSucursal;
    VeDetalleCaja vdc;
    VeCajaJpaController cajacon = new VeCajaJpaController(EntityManagerUtil.ObtenerEntityManager());
    VeDetalleCajaJpaController cajadet = new VeDetalleCajaJpaController(EntityManagerUtil.ObtenerEntityManager());

    public Cierre_Caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        nombreCaja.setEditable(false);
    }

    
    public Cierre_Caja(java.awt.Frame parent, boolean modal, SeUsuarios se, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        seUsuario = se;
        seEmpresa = em;
        seSucursal = su;
        nombreCaja.setEditable(false);
    }

    public Cierre_Caja(java.awt.Frame parent, boolean modal, VeDetalleCaja veCaja, SeUsuarios se, SeEmpresa em, SeSucursal su) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        seUsuario = se;
        seEmpresa = em;
        seSucursal = su;
        vdc = veCaja;
        llenarCampos(veCaja);
        nombreCaja.setEditable(false);
    }

    public class PrintEpson implements Printable {

        public List<String> getPrinters() {
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService printServices[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            List<String> printerList = new ArrayList<String>();
            for (PrintService printerService : printServices) {
                printerList.add(printerService.getName());
            }
            return printerList;
        }

        @Override
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException {
            if (page > 0) {
                /* We have only one page, and 'page' is zero-based */
                return NO_SUCH_PAGE;
            }
            /*
             * User (0,0) is typically outside the imageable area, so we must
             * translate by the X and Y values in the PageFormat to avoid clipping
             */
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            /* Now we perform our rendering */
            g.setFont(new Font("Roman", 0, 8));
            g.drawString("Hello world !", 0, 10);
            return PAGE_EXISTS;
        }

        public void printString(String printerName, String text) {
            // find the printService of name printerName
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService service = findPrintService("JAPOS1", printService);
            DocPrintJob job = service.createPrintJob();
            try {
                byte[] bytes;
                // important for umlaut chars
                bytes = text.getBytes("CP437");
                Doc doc = new SimpleDoc(bytes, flavor, null);
                job.print(doc, null);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void printBytes(String printerName, byte[] bytes) {
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService service = findPrintService(printerName, printService);
            DocPrintJob job = service.createPrintJob();
            try {
                Doc doc = new SimpleDoc(bytes, flavor, null);
                job.print(doc, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private PrintService findPrintService(String printerName,
                PrintService[] services) {
            for (PrintService service : services) {
                if (service.getName().equalsIgnoreCase(printerName)) {
                    return service;
                }
            }
            return null;
        }
    }

    private void llenarCampos(VeDetalleCaja veCaja) {
        SimpleDateFormat nombre = new SimpleDateFormat("HH: mm: ss");
        nombreCaja.setText(veCaja.getVeCaja().getNombre());
        montoInicial.setText(String.valueOf(veCaja.getDineroInicio()));
        horaInicio.setText(nombre.format(veCaja.getHoraInicio()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreCaja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        montoInicial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        montocierre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        horaInicio = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setBackground(java.awt.Color.red);
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CIERRE DE CAJA");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("CAJA:");

        nombreCaja.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("MONTO INICIAL:");

        montoInicial.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        montoInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("MONTO DE CIERRE:");

        montocierre.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        montocierre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        montocierre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                montocierreKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel5.setText("HORA DE APERTURA:");

        horaInicio.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        horaInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnGrabar.setBackground(new java.awt.Color(254, 254, 254));
        btnGrabar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnGrabar.setForeground(new java.awt.Color(1, 1, 1));
        btnGrabar.setText("CERRAR");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(254, 254, 254));
        btnCancelar.setFont(new java.awt.Font("Ubuntu", 1, 10)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(1, 1, 1));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/asofar/icon/Cancelar_Mesa de trabajo 1.jpg"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel7.setText("$$");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel8.setText("$$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(montoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(horaInicio)
                            .addComponent(montocierre, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(montoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(horaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(montocierre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        String Cadena = "";
        if (" ".equals(montocierre.getText())) {
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO VALIDO", "ACCION NO PERMITIDA!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                vdc.setDineroCierre(Double.parseDouble(montocierre.getText()));
                vdc.setFechaCierre(d_fecha);
                vdc.setHoraCierre(d_hora);
                vdc.setEstado("I");
                if (ValidacionCaja.ValidacionCierre(vdc, seUsuario) == true) {
                    Double factura = ValidacionCaja.facturadoRetorno(vdc);
                    Double cierre = Double.parseDouble(montocierre.getText());
                    Double resta = 0.0;
                    Double resta1 = 0.0;
                    Double total = factura + Double.parseDouble(montoInicial.getText());
                    System.out.println("total " + total);
                    System.out.println("ini  " + Double.parseDouble(montoInicial.getText()));
                    System.out.println("factura " + factura);
                    System.out.println("cierre " + cierre);
                    Double anuladas = ValidacionCaja.facturasAnuladas(vdc);
                    if (cierre < total) {
                        resta = cierre - total;
                        Cadena = "PERDIDA : " + resta;
                        System.out.println(" " + Cadena);
                    }
                    if (cierre > total) {
                        resta = cierre - total;
                        Cadena = "SOBRANTE : " + resta;
                        System.out.println(" " + Cadena + " cierre " + cierre + "total " + total + "resta " + resta);
                    }
                    if (cierre.equals(total)) {
                        Cadena = "Cierre Correcto!";
                        System.out.println(" " + Cadena + " cierre " + cierre + "total " + total);
                    }if(anuladas.equals(null)){
                        anuladas =0.0;
                    }
                    /*   */
                    cajadet.edit(vdc);
                    setVisible(false);
                    Cierre_Caja.PrintEpson printerService = new Cierre_Caja.PrintEpson();
                    System.out.println(printerService.getPrinters());
                    //print some stuff. Change the printer name to your thermal printer name.
                    printerService.printString("EPSON-TM-T20II", "--------------------------------------\n");
                    printerService.printString("EPSON-TM-T20II", "  *       CIERRE DE CAJA          *  \n");
                    printerService.printString("EPSON-TM-T20II", "--------------------------------------\n");
                    printerService.printString("EPSON-TM-T20II", "\n    NOMBRE DE CAJA: " + nombreCaja.getText() + "\n");
                    printerService.printString("EPSON-TM-T20II", "      MONTO INICIAL: " + montoInicial.getText() + "\n");
                    printerService.printString("EPSON-TM-T20II", "      HORA DE INICIO: " + horaInicio.getText() + "\n");
                    printerService.printString("EPSON-TM-T20II", "      MONTO DE CIERRE: " + montocierre.getText() + "\n");
                    printerService.printString("EPSON-TM-T20II", "      ESTADO: " + Cadena + "\n");
                    printerService.printString("EPSON-TM-T20II", "      ANULACION: " + anuladas + "\n");
                    printerService.printString("EPSON-TM-T20II", "--------------------------------------\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");
                    printerService.printString("EPSON-TM-T20II", "\n");

//                    byte[] cutP = new byte[]{0x1d, 'V', 1};
//                    printerService.printBytes("EPSON-TM-T20II", cutP);
                }
            } catch (Exception ex) {
                Logger.getLogger(Cierre_Caja.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void montocierreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montocierreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c == ',') || Character.isAlphabetic(c) || Character.isSpaceChar(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_montocierreKeyTyped

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
            java.util.logging.Logger.getLogger(Cierre_Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cierre_Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cierre_Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cierre_Caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cierre_Caja dialog = new Cierre_Caja(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JTextField horaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField montoInicial;
    private javax.swing.JTextField montocierre;
    private javax.swing.JTextField nombreCaja;
    // End of variables declaration//GEN-END:variables
}
