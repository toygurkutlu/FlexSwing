package io.github.toygurkutlu.flexswing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Customizable separator that extends from JSeparator.
 *<p>Use the following methods to customize the {@code separator}:</p>
 * <ol>
 *     <li><strong>Orientation:</strong>
 *         <ul>
 *             <li>{@link #setOrientation(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Thickness:</strong>
 *         <ul>
 *             <li>{@link #setThickness(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Color:</strong>
 *         <ul>
 *             <li>{@link #setColor(Color)}</li>
 *             <li>{@link #setLineColor(Color)}</li>
 *         </ul>
 *     </li>
 * </ol>
 * */
public class FlexSeparator extends JSeparator {

    private int thickness = 1;

    /**
     * Creates an instance with the specified orientation. Default thickness is 1 pixel.
     * @param orientation the orientation of the separator
     * */
    public FlexSeparator(int orientation) {
        super(orientation);
        updateSizes();
    }

    /**
     * Creates an instance with the specified orientation and thickness.
     * @param orientation the orientation of the separator
     * @param thickness the thickness of the separator
     * */
    public FlexSeparator(int orientation, int thickness) {
        super(orientation);
        this.thickness = thickness;
        updateSizes();
    }

    /**
     * Creates an instance with the specified orientation and color.
     * @param orientation the orientation of the separator
     * @param color the color of the separator
     * */
    public FlexSeparator(int orientation, Color color) {
        super(orientation);
        setForeground(color);
        updateSizes();
    }

    /**
     * Creates an instance with the specified orientation, thickness and color.
     * @param orientation the orientation of the separator
     * @param thickness the thickness of the separator
     * @param color the color of the separator
     * */
    public FlexSeparator(int orientation, int thickness, Color color) {
        super(orientation);
        this.thickness = thickness;
        setForeground(color);
        updateSizes();
    }

    private void updateSizes() {
        if (getOrientation() == JSeparator.HORIZONTAL) {
            setPreferredSize(new Dimension(0, thickness));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, thickness));
        } else {
            setPreferredSize(new Dimension(thickness, 0));
            setMaximumSize(new Dimension(thickness, Integer.MAX_VALUE));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getForeground());
        g2d.setStroke(new BasicStroke(thickness));

        if (getOrientation() == JSeparator.HORIZONTAL) {
            int y = getHeight() / 2;
            g2d.drawLine(0, y, getWidth() - 1, y);
        } else {
            int x = getWidth() / 2;
            g2d.drawLine(x, 0, x, getHeight() - 1);
        }
        g2d.dispose();
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        updateSizes();
        revalidate();
        repaint();
    }

    /**
     * Sets the thickness of the separator. The default value of this property is 1 pixel.
     * @param thickness the new thickness of the separator in pixels
     * */
    public void setThickness(int thickness) {
        this.thickness = thickness;
        updateSizes();
        revalidate();
        repaint();
    }

    /**
     * Sets the color of the separator.
     * @param color the new color of the separator
     * */
    public void setColor(Color color) {
        setForeground(color);
    }

    /**
     * Sets the color of the separator.
     * @param color the new color of the separator
     * */
    public void setLineColor(Color color) {
        setColor(color);
    }

    /**
     * Gets the thickness of the separator.
     * @return the thickness of the separator in pixels
     * */
    public int getThickness() {
        return this.thickness;
    }
}