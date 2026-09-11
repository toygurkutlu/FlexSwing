package io.github.toygurkutlu.flexswing.components.color_picker.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class AlphaSliderUI extends BasicSliderUI {
    private Color baseColor = Color.RED;

    public AlphaSliderUI(JSlider slider) {
        super(slider);
        slider.setOpaque(false);
        slider.setFocusable(false);
    }

    public void setBaseColor(Color c) {
        this.baseColor = c;
        slider.repaint();
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int x = trackRect.x;
        int w = trackRect.width;

        int h = 10;
        int y = trackRect.y + (trackRect.height - h) / 2;

        paintCheckerboard(g2, x, y, w, h, 4);

        for (int i = 0; i < w; i++) {
            float t = (float) i / (float) (w - 1);
            int alpha = (int) (255 * t);

            Color c = new Color(
                    baseColor.getRed(),
                    baseColor.getGreen(),
                    baseColor.getBlue(),
                    alpha
            );


            g2.setColor(c);
            g2.fillRect(x + i, y, 1, h + 2);
        }

        g2.dispose();
    }

    private void paintCheckerboard(Graphics2D g2, int x, int y, int w, int h, int size) {
        Color light = new Color(220, 220, 220);
        Color dark = new Color(180, 180, 180);

        for (int iy = 0; iy < h; iy += size) {
            for (int ix = 0; ix < w; ix += size) {
                boolean even = ((ix / size) + (iy / size)) % 2 == 0;
                g2.setColor(even ? light : dark);
                g2.fillRect(x + ix, y + iy, size, size);
            }
        }
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