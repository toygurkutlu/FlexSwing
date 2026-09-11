package io.github.toygurkutlu.flexswing.components.color_picker.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class HueSliderUI extends BasicSliderUI {

    public HueSliderUI(JSlider slider) {
        super(slider);
        slider.setOpaque(false);
        slider.setFocusable(false);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int x = trackRect.x;
        int y = trackRect.y + trackRect.height / 4;
        int w = trackRect.width;
        int h = trackRect.height / 2;

        for (int i = 0; i < w; i++) {
            float hue = (float) i / (float) w;
            g2.setColor(Color.getHSBColor(hue, 1f, 1f));
            g2.drawLine(x + i, y, x + i, y + h);
        }

        g2.dispose();
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int cx = thumbRect.x + thumbRect.width / 2;
        int y = trackRect.y;
        int h = trackRect.height;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(cx, y, cx, y + h);

        g2.dispose();
    }
}