/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.font.TextAttribute;
import java.awt.Font;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;

public class Render1 extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

//        Component comp = super.getTableCellRendererComponent(table, value, isSelected,
//                hasFocus, row, column);

        JButton button = (JButton) table.getValueAt(row, 4);
//        Map attributes = (new Font("Serif", Font.BOLD, 12)).getAttributes();
//        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);

        if (button.getText().equals("ACTIVAR")) {

            setForeground(Color.RED);
//            comp.setFont(new Font(attributes));
            
//            return comp;

        } else {
            setForeground(Color.BLACK);
        }

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            if (isSelected) {
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());

            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }

        if (value instanceof JCheckBox) {
            JCheckBox ch = (JCheckBox) value;

            return ch;
        }

//
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.

//return comp;
    }

}
