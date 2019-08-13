/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

/**
 *
 * @author ms24m
 */
public class Display {

    public void printTime(int hour, int minute, int second) {
        String fullHour = "";
        fullHour += (hour > 9) ? ":" + hour : "0" + hour;
        fullHour += (minute > 9) ? ":" + minute : ":0" + minute;
        fullHour += (second > 9) ? ":" + second : ":0" + second;
        System.out.println(fullHour);
    }
}
