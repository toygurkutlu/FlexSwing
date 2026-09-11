package io.github.toygurkutlu.flexswing.objects;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Describing an object capable of rendering a border around the edges of a swing component with roundable corners
 *
 * @see Border
 */
public class RoundedBorder implements Border {
    private final int radius;

    /**
     * Creates an instance of the {@code RoundedBorder} with the specified radius.
     *
     * @param radius the corner radius of the border
     * @throws IllegalArgumentException if the provided radius is negative
     */
    public RoundedBorder(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}