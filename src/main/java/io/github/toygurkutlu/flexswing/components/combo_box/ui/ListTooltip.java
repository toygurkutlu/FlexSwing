package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.objects.Padding;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ListTooltip extends JWindow {

    public enum ListTooltipAttribute {
        BACKGROUND,
        BORDER_COLOR,
        BORDER_RADIUS,
        FOREGROUND,
        FONT,
        PADDING
    }

    private JLabel label;
    private JPanel panel;
    private Color background;
    private Color foreground;
    private Color borderColor;
    private Font font;
    private int borderRadius = 0;

    public ListTooltip() {
        background = new Color(45, 45, 45, 230);
        foreground = Color.WHITE;
        borderColor = new Color(100, 100, 100);
        font = new Font("Arial", Font.PLAIN, 14);

        init();
    }

    private void init() {
        createViews();

        panel.add(label, BorderLayout.CENTER);

        setBackground(new Color(0, 0, 0, 0));

        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        add(panel);
        pack();
    }

    public void showTooltip(String text, Point point) {
        String htmlText = "<html>" + text + "</html>";
        label.setText(htmlText);
        label.setSize(new Dimension(300, Integer.MAX_VALUE));

        pack();

        setLocation(point.x + 15, point.y + 15);

        if (!isVisible()) {
            setVisible(true);
        }
    }

    public void hideTooltip() {
        if (isVisible()) {
            setVisible(false);
        }
    }

    private void createViews() {
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(background);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

                g2d.setColor(borderColor);
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

                g2d.dispose();
            }
        };
        panel.setOpaque(false);

        label = new JLabel();
        label.setForeground(foreground);
        label.setFont(font);
        label.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }

    public void setBorderRadius(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        this.borderRadius = radius;
        if (panel != null) {
            panel.repaint();
        }
    }

    public void setTooltipFont(Font font) {
        this.font = Objects.requireNonNull(font,"Font cannot be null.");
        if (label != null) {
            label.setFont(font);
            pack();
        }
    }

    public void setForegroundColor(Color color) {
        this.foreground = Objects.requireNonNull(color,"Foreground color cannot be null.");
        if (label != null) {
            label.setForeground(color);
        }
    }

    public void setBackgroundColor(Color color) {
        this.background = Objects.requireNonNull(color, "Background color cannot be null.");
        if (panel != null) {
            panel.repaint();
        }
    }

    public void setBorderColor(Color color) {
        this.borderColor = Objects.requireNonNull(color, "Border color cannot be null");
        if (panel != null) {
            panel.repaint();
        }
    }

    public void setPadding(Padding padding) {
        Objects.requireNonNull(padding, "Padding cannot be null");
        label.setBorder(BorderFactory.createEmptyBorder(padding.top(),
                                                        padding.left(),
                                                        padding.bottom(),
                                                        padding.right()));
    }

    public void updateListTooltip(ListTooltipAttribute attr, Object value) {
        Objects.requireNonNull(attr, "Attribute cannot be null.");
        Objects.requireNonNull(value, "Value cannot be null.");
        switch (attr) {
            case BACKGROUND -> setBackgroundColor((Color) value);
            case BORDER_COLOR -> setBorderColor((Color) value);
            case BORDER_RADIUS -> setBorderRadius((int) value);
            case FOREGROUND -> setForegroundColor((Color) value);
            case FONT -> setTooltipFont((Font) value);
            case PADDING -> setPadding((Padding) value);
        }

    }
}