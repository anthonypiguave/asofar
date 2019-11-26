/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author admin1
 */
public class Fondo extends javax.swing.JLabel {

    public Fondo(int w,int h) {
        this.setSize(w,h);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Dimension dim = getSize();
        ImageIcon imagenfondo = new ImageIcon(getClass().getResource("/ec/com/asofar/imagenes/8.png"));
        g.drawImage(imagenfondo.getImage(),0,0,dim.width,dim.height,null);
        setOpaque(false);
        super.paintComponent(g);
    }
    
}
