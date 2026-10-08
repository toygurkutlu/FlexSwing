package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.records.ComboAttributes;
import io.github.toygurkutlu.flexswing.components.combo_box.records.DisplayPanelAttributes;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.awt.geom.Area;

public class FlexComboBoxEditor extends BasicComboBoxEditor {

    private String selectedValue;
    private final JPanel panel;
    private final JLabel label = new JLabel();

    private final int borderThickness;
    private final DisplayPanelAttributes displayAttr;
    private final int topLeft;
    private final int topRight;
    private int bottomLeft;
    private final int bottomRight;

    public FlexComboBoxEditor(ComboAttributes attr) {
        displayAttr = attr.displayPanelAttributes();
        topLeft = displayAttr.radii().topLeft();
        topRight = displayAttr.radii().topRight();
        bottomLeft = displayAttr.radii().bottomLeft();
        bottomRight = displayAttr.radii().bottomRight();

        Padding gaps = displayAttr.padding();

        borderThickness = displayAttr.borderThickness();

        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                setOpaque(false);
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Area area = new Area(FlexUtil.createRoundTopLeft(panel, topLeft, borderThickness));

                if (topRight > 0) {
                    area.intersect(new Area(FlexUtil.createRoundTopRight(panel, topRight, borderThickness)));
                }
                if (bottomLeft > 0) {
                    area.intersect(new Area(FlexUtil.createRoundBottomLeft(panel, bottomLeft, borderThickness)));
                }
                if (bottomRight > 0) {
                    area.intersect(new Area(FlexUtil.createRoundBottomRight(panel, bottomRight, borderThickness)));
                }

                g2.setColor(displayAttr.background());
                g2.fill(area);

                if (borderThickness > 0) {
                    g2.setColor(displayAttr.borderColor());
                    g2.setStroke(new BasicStroke(borderThickness));
                    g2.draw(area);
                }
                g2.dispose();
            }
        };

        panel.setBorder(BorderFactory.createEmptyBorder(gaps.top(), gaps.left(), gaps.bottom(), gaps.right()));

        label.setOpaque(false);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setForeground(displayAttr.foreground());
        label.setFont(displayAttr.font());

        panel.add(label, BorderLayout.CENTER);
    }

    public void updateBorder(boolean isPopupVisible) {
        bottomLeft = isPopupVisible ? 0 : displayAttr.radii().bottomLeft();
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
}