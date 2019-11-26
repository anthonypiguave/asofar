/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author ineval
 */
public class Formato_Numeros {

    public static String formatoNumero(String valor) {

        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(Double.parseDouble(valor));
//        System.out.println(" val" + valor);
        if (valorFormateado.charAt(0) == ',') {
            String h = "0" + valorFormateado;
            valorFormateado = h;
        }

        return valorFormateado;
    }
    public static String formatoNumero2(String valor) {

        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(Double.parseDouble(valor));
////        System.out.println(" val" + valor);
//        if (valorFormateado.charAt(0) == ',') {
//            String h = "0" + valorFormateado;
//            valorFormateado = h;
//        }

        return valorFormateado;
    }    
    public static String removeScientificNotation(String value) {
        return new BigDecimal(value).toPlainString();
    }
        public static String formatoNumero3(String valor) {

        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(Double.parseDouble(valor));
//        System.out.println(" val" + valor);
        if (valorFormateado.charAt(0) == '.') {
            String h = "0" + valorFormateado;
            valorFormateado = h;
        }

        return valorFormateado;
    }
}
