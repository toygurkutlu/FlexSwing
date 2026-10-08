package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.records.ComboAttributes;
import io.github.toygurkutlu.flexswing.components.combo_box.records.PopupAttributes;
import io.github.toygurkutlu.flexswing.objects.Padding;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FlexComboBoxCellRenderer<T> extends JPanel implements ListCellRenderer<T> {

    private final JLabel label = new JLabel();

    private final PopupAttributes attr;

    public FlexComboBoxCellRenderer(ComboAttributes comboAttr) {
        this.attr = comboAttr.popupAttributes();
        Padding padding = attr.padding();

        label.setOpaque(false);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(attr.font());
        label.setBorder(new EmptyBorder(3, 10, 3, 10));

        setLayout(new BorderLayout());
        setOpaque(true);
        setBorder(new EmptyBorder(padding.top(), padding.left(), padding.bottom(), padding.right()));
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            String text = value.toString();
            label.setText(text);

            if (isSelected) {
                label.setForeground(attr.selectedForeground());
                this.setBackground(attr.selectedBackground());
            } else {
                label.setForeground(attr.itemForeground());
                this.setBackground(attr.itemBackground());
            }
        }
        return this;
    }
}