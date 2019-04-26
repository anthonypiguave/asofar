/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import ec.com.asofar.views.inicio.Login;
import javax.swing.JProgressBar;

/**
 *
 * @author ms24m
 */
public class Cargar1 extends Thread {

    public Cargar1() {
        super();

    }

    @Override
    public void run() {
        hiloT();
    
    }
    
    public int hiloT(){
        int i;
        Login fc = new Login(new javax.swing.JFrame(), true); 
        for (i = 0; i < 20; i++) {
            if(i==19){
//                System.exit(0);
                
                
             
                fc.setVisible(true);
                
                
            }
            System.out.println(i);
            pausa();
            
        } 
          fc.dispose();
        return i;
    }

    public void pausa() {
           try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
