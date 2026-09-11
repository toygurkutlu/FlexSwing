package io.github.toygurkutlu.flexswing.components.color_picker.components;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {

    private int diameter;
    private Color fillColor = Color.RED;
    private Color borderColor = Color.BLACK;
    private float borderWidth = 2f;

    public CirclePanel(int diameter) {
        this.diameter = diameter;
        int dim = (int) (borderWidth + diameter+1);
        setPreferredSize(new Dimension(dim, dim));
        setOpaque(false);
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        repaint();
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        repaint();
    }

    public Color getFillColor(){
        return fillColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2.setColor(fillColor);
        g2.fillOval(x, y, diameter, diameter);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));
        g2.drawOval(x, y, diameter, diameter);

        g2.dispose();
    }
}