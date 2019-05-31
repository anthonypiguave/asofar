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
}
