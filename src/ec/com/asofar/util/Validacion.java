/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin1
 */
public class Validacion {

    public static boolean Email(String Mail) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(Mail);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean FiltroLetraNumeroSinEspacio(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || Character.isSpaceChar(c)) {

            System.out.println("true1");
            return true;
        } else {

            System.out.println("false");
            return false;
        }
    }

    public static boolean FiltroLetraNumeroSinEspacio2(java.awt.event.KeyEvent evt) {

        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')
                && Character.isSpaceChar(car)&& !Character.isLetterOrDigit(car)
                ){
            evt.consume();
        }
        return true;

    }

    public static boolean FiltroLetraSinEspacio(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if (!Character.isAlphabetic(c) || Character.isSpaceChar(c)) {

            return true;
        } else {
            return false;
        }

    }

    public static boolean FiltroNumeroSinEspacio(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isSpaceChar(c)) {

            return true;
        } else {
            return false;
        }

    }
}
