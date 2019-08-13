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
    private int minute = 1;
    private int second = 50;
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
            if (second < 59) {
                second++;
            } else {
                second = 00;
                if (minute < 59) {
                    minute++;
                } else {
                    minute = 00;
                    if (hour < 23) {
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
