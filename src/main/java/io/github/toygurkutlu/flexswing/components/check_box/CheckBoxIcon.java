package io.github.toygurkutlu.flexswing.components.check_box;

import io.github.toygurkutlu.flexswing.components.FlexCheckBox;

import javax.swing.*;
import java.awt.*;

public class CheckBoxIcon implements Icon {

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (!(c instanceof FlexCheckBox checkBox)) {
            return;
        }

        ButtonModel model = checkBox.getModel();
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = 14;

        int correctedY = ((c.getHeight() - size) / 2) + 1;

        Color backgroundColor;
        Color borderColor;
        if (model.isPressed()) {
            backgroundColor = checkBox.getPressedBoxBackground();
            borderColor = checkBox.getPressedBoxBorderColor();
        } else if (model.isRollover()) {
            backgroundColor = checkBox.getHoverBoxBackground();
            borderColor = checkBox.getHoverBoxBorderColor();
        } else {
            backgroundColor = checkBox.getBoxBackgroundColor();
            borderColor = checkBox.getBoxBorderColor();
        }

        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, correctedY, size, size, checkBox.getBoxCornerRadius(), checkBox.getBoxCornerRadius());

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawRoundRect(x, correctedY, size - 1, size - 1, checkBox.getBoxCornerRadius(), checkBox.getBoxCornerRadius());

        if (model.isSelected()) {
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF
            );

            final int checkSize = 13;

            g2.setColor(checkBox.getCheckMarkColor());

            g2.fillRect(
                    x + 3,
                    correctedY + 5,
                    2,
                    checkSize - 8
            );

            g2.drawLine(
                    x + 9,
                    correctedY + 3,
                    x + 5,
                    correctedY + 7
            );

            g2.drawLine(
                    x + 9,
                    correctedY + 4,
                    x + 5,
                    correctedY + 8
            );
            g2.dispose();
        }
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 13;
    }

    @Override
    public int getIconHeight() {
        return 13;
    }
}