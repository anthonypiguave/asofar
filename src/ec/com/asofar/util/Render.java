/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.asofar.util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

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

        if (value instanceof JComboBox) {
            JComboBox cb = (JComboBox) value;

            if (isSelected) {
                cb.setForeground(table.getSelectionForeground());
                cb.setBackground(table.getSelectionBackground());
            } else {
                cb.setForeground(table.getForeground());
                cb.setBackground(table.getBackground());
            }

            return cb;
        }
                if (value instanceof JTextField) {
            JTextField tx = (JTextField) value;

            if (isSelected) {
                tx.setForeground(table.getSelectionForeground());
                tx.setBackground(table.getSelectionBackground());
            } else {
                tx.setForeground(table.getForeground());
                tx.setBackground(table.getBackground());
            }

            return tx;
        }

        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
