/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import javax.swing.JProgressBar;

/**
 *
 * @author ms24m
 */
public class Cargar extends Thread {

    JProgressBar progreso;

    public Cargar(JProgressBar progreso) {
        super();
        this.progreso = progreso;
    }

    @Override
    public void run() {
        for (int a = 1; a <= 100; a++) {
            progreso.setValue(a);
            
            pausa(20);
        }
    }

    public void pausa(int mlseg) {
        try {
            Thread.sleep(mlseg);
//            System.out.println("enfermo");
        } catch (InterruptedException e) {
        }
    }
}
