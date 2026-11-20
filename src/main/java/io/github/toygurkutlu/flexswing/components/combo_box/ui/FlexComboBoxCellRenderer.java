package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.PopupConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FlexComboBoxCellRenderer<T> extends JPanel implements ListCellRenderer<T> {

    private final JLabel label = new JLabel();
    private Color itemForeground;
    private Color itemBackground;
    private Color selectedForeground;
    private Color selectedBackground;
    private Font font;

    private final PopupConfig attr;

    public FlexComboBoxCellRenderer(ComboBoxConfig comboAttr) {
        this.attr = comboAttr.getPopupConfig();

        itemBackground = attr.getItemBackground();
        itemForeground = attr.getItemForeground();
        selectedBackground = attr.getSelectedBackground();
        selectedForeground = attr.getSelectedForeground();
        font = attr.getFont();

        label.setOpaque(false);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(font);
        label.setBorder(new EmptyBorder(3, 10, 3, 10));

        setOpaque(true);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(3, 3, 3, 3));
        add(label, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            String text = value.toString();
            label.setText(text);

            if (isSelected) {
                label.setForeground(selectedForeground);
                this.setBackground(selectedBackground);
            } else {
                label.setForeground(itemForeground);
                this.setBackground(itemBackground);
            }
        }
        return this;
    }

    public void setItemForeground(Color fg) {
        this.itemForeground = fg;
        revalidate();
        repaint();
    }

    public void setItemBackground(Color bg) {
        this.itemBackground = bg;
        revalidate();
        repaint();
    }

    public void setSelectedForeground(Color fg) {
        this.selectedForeground = fg;
        revalidate();
        repaint();
    }

    public void setSelectedBackground(Color bg) {
        this.selectedBackground = bg;
        revalidate();
        repaint();
    }

    public void setItemFont(Font font) {
        this.font = font;
        label.setFont(font);
        revalidate();
        repaint();
    }
}