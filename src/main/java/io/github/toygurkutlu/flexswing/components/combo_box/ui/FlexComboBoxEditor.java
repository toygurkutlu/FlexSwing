package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.DisplayPanelConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.TextAlignment;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.awt.geom.Area;

public class FlexComboBoxEditor extends BasicComboBoxEditor {

    private String selectedValue;
    private final JPanel panel;
    private final JLabel label = new JLabel();

    private final DisplayPanelConfig displayAttr;
    private int bottomLeft;
    private int topLeft;
    private Color background;
    private Color borderColor;

    public FlexComboBoxEditor(ComboBoxConfig attr) {
        displayAttr = attr.getDisplayPanelConfig();

        topLeft = displayAttr.getRadii().topLeft();
        bottomLeft = displayAttr.getRadii().bottomLeft();
        TextAlignment textAlignment = attr.getDisplayPanelConfig().getTextAlignment();

        background = displayAttr.getBackground();
        borderColor = displayAttr.getBorderColor();

        int borderThickness = 1;

        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Area area = new Area(FlexUtil.createRoundTopLeft(panel, topLeft, borderThickness));

                if (bottomLeft > 0) {
                    area.intersect(new Area(FlexUtil.createRoundBottomLeft(panel, bottomLeft, borderThickness)));
                }

                g2.setColor(background);
                g2.fill(area);

                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(borderThickness));
                g2.draw(area);
                g2.dispose();
            }
        };

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        label.setOpaque(false);
        label.setHorizontalAlignment(textAlignment.getAlignment());
        label.setForeground(displayAttr.getForeground());
        label.setFont(displayAttr.getFont());

        panel.add(label, BorderLayout.CENTER);
    }

    public void updateBorder(boolean isPopupVisible) {
        bottomLeft = isPopupVisible ? 0 : displayAttr.getRadii().bottomLeft();
        panel.repaint();
    }

    public Component getEditorComponent() {
        return this.panel;
    }

    public Object getItem() {
        return this.selectedValue;
    }

    public void setItem(Object item) {
        if (item == null) {
            return;
        }
        selectedValue = item.toString();
        label.setText(selectedValue);
    }

    public void setTextAlignment(TextAlignment alignment) {
        label.setHorizontalAlignment(alignment.getAlignment());
    }

    public void setTextColor(Color color) {
        label.setForeground(color);
    }

    public void setTextFont(Font font) {
        label.setFont(font);
    }

    public void setDisplayPanelRadii(int topLeft, int bottomLeft) {
        this.topLeft = topLeft;
        this.bottomLeft = bottomLeft;
        panel.revalidate();
        panel.repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        panel.revalidate();
        panel.repaint();
    }

    public void setBackgroundColor(Color color) {
        this.background = color;
        panel.revalidate();
        panel.repaint();
    }
}