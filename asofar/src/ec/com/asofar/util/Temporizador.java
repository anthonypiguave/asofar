/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.awt.DisplayMode;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ms24m
 */
public class Temporizador {

    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private Timer timer;
    private boolean isTimerRunning;
    private Display display;

    public Temporizador() {
        timer = new Timer();
        display = new Display();
    }
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isTimerRunning = true;
            if (second >= 0) {
                second++;
            } else {
                second = 59;
                if (minute > 0) {
                    minute++;
                } else {
                    minute = 59;
                    if (hour > 0) {
                        hour++;
                    } else {
                        isTimerRunning = false;
                        timer.cancel();
                        timer.purge();
                    }
                }
            }
            if (isTimerRunning) {
                display.printTime(hour, minute, second);
            }
        }
    };

    public void start(int timeout, int interval) {
        timer.schedule(task, timeout, interval);
    }
}
