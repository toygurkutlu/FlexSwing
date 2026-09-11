package io.github.toygurkutlu.flexswing.components.color_picker.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorArea extends JPanel {

    private float hue = 0f;
    private float saturation = 1f;
    private float brightness = 1f;

    private int selectedX = 0;
    private int selectedY = 0;
    private int alpha = 255;

    public ColorArea(Color color) {
        setColor(color);
        setPreferredSize(new Dimension(200, 150));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateFromMouse(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateFromMouse(e.getX(), e.getY());
            }
        });
    }

    private void updateFromMouse(int x, int y) {
        float w = getWidth();
        float h = getHeight();

        saturation = clamp(x / w);
        brightness = clamp(1f - (y / h));

        selectedX = x;
        selectedY = y;

        Color c = Color.getHSBColor(hue, saturation, brightness);
        firePropertyChange("color", null, c);

        repaint();
    }

    private float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }

    public void setHue(float hue) {
        this.hue = hue;
        repaint();
    }

    public void setColor(Color c) {
        alpha = c.getAlpha();
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

        this.hue = hsb[0];
        this.saturation = hsb[1];
        this.brightness = hsb[2];

        if (getWidth() == 0 || getHeight() == 0) {
            SwingUtilities.invokeLater(() -> {
                selectedX = (int) (saturation * getWidth());
                selectedY = (int) ((1f - brightness) * getHeight());
                repaint();
            });
        } else {
            selectedX = (int) (saturation * getWidth());
            selectedY = (int) ((1f - brightness) * getHeight());
            repaint();
        }
    }

    public float getHue(){
        return hue;
    }

    public float getBrightness() {
        return brightness;
    }

    public float getSaturation() {
        return saturation;
    }

    public Color getColor() {
        Color color = Color.getHSBColor(hue, getSaturation(), getBrightness());
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                float s = (float) x / w;
                float b = 1f - ((float) y / h);
                g2.setColor(Color.getHSBColor(hue, s, b));
                g2.drawLine(x, y, x, y);
            }
        }

        Color selectedColor = Color.getHSBColor(hue, saturation, brightness);

        float luminance = (selectedColor.getRed() * 0.299f +
                selectedColor.getGreen() * 0.587f +
                selectedColor.getBlue() * 0.114f);

        boolean dark = luminance < 128;

        int r = 4;

        g2.setColor(dark ? Color.WHITE : Color.BLACK);
        g2.setStroke(new BasicStroke(1f));
        g2.drawOval(selectedX - r, selectedY - r, r * 2, r * 2);

        g2.dispose();
    }
}