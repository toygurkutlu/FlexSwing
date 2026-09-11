package io.github.toygurkutlu.flexswing.components.radio_button;

import io.github.toygurkutlu.flexswing.components.FlexRadioButton;

import javax.swing.*;
import java.awt.*;

public class RadioButtonIcon implements Icon {

    private static final int CIRCLE_SIZE = 12;
    private static final int DOT_SIZE = 7;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        FlexRadioButton radioButton = (FlexRadioButton) c;
        ButtonModel model = radioButton.getModel();
        Graphics2D g2 = (Graphics2D) g.create();


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        Color backgroundColor;
        Color borderColor;
        if (model.isPressed()) {
            backgroundColor = radioButton.getPressedCircleBackgroundColor();
            borderColor = radioButton.getPressedCircleBorderColor();
        } else if (model.isRollover()) {
            backgroundColor = radioButton.getHoverCircleBackgroundColor();
            borderColor = radioButton.getHoverCircleBorderColor();
        } else {
            borderColor = radioButton.getCircleBorderColor();
            backgroundColor = radioButton.getCircleBackgroundColor();
        }

        int correctedY = ((c.getHeight() - CIRCLE_SIZE) / 2) + 1;

        g2.setColor(borderColor);
        g2.fillOval(x, correctedY, CIRCLE_SIZE, CIRCLE_SIZE);

        g2.setColor(backgroundColor);
        g2.fillOval(x + 1, correctedY + 1, CIRCLE_SIZE - 2, CIRCLE_SIZE - 2);

        if (model.isSelected()) {
            g2.setColor(radioButton.getDotColor());

            double centerX = x + (CIRCLE_SIZE / 2.0);
            double centerY = correctedY + (CIRCLE_SIZE / 2.0);
            double dotSize = 6.5;
            double dotX = centerX - (dotSize / 2.0);
            double dotY = centerY - (dotSize / 2.0);

            java.awt.geom.Ellipse2D.Double innerDot = new java.awt.geom.Ellipse2D.Double(dotX, dotY, dotSize, dotSize);
            g2.fill(innerDot);
        }

        g2.dispose();
    }

    @Override
    public int getIconWidth() { return CIRCLE_SIZE; }

    @Override
    public int getIconHeight() { return CIRCLE_SIZE; }
}