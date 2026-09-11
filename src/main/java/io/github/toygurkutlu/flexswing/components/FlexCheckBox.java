package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.components.check_box.CheckBoxIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Customizable an item that can be selected or deselected, and which displays its state to the user.
 *
 * <p>Use the following methods to customize attributes:</p>
 * <ol>
 *     <li><strong>Text color:</strong>
 *         <ul>
 *             <li>{@link #setForeground(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text font:</strong>
 *         <ul>
 *             <li>{@link #setFont(Font)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Gap between the text and the box:</strong>
 *         <ul>
 *             <li>{@link #setIconTextGap(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Background color:</strong>
 *         <ul>
 *              <li>{@link #setOpaque(boolean)}</li>
 *             <li>{@link #setBackground(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Check mark color:</strong>
 *         <ul>
 *             <li>{@link #setCheckMarkColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Box corner radius:</strong>
 *         <ul>
 *             <li>{@link #setBoxCornerRadius(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Box color:</strong>
 *
 *         <ul>
 *             <li>{@link #setBoxBackgroundColor(Color)}</li>
 *             <li>{@link #setBoxBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Hover effect:</strong>
 *         <ul>
 *             <li>{@link #setHoverBoxBackground(Color)}</li>
 *             <li>{@link #setHoverBoxBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Pressed effect:</strong>
 *         <ul>
 *             <li>{@link #setPressedBoxBackground(Color)}</li>
 *             <li>{@link #setPressedBoxBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JCheckBox
 */
public class FlexCheckBox extends JCheckBox {

    private int radius = 6;
    private Color boxBackground = new Color(40, 40, 40);
    private Color hoverBoxBackground = new Color(80, 80, 80);
    private Color pressedBoxBackground = new Color(120, 120, 120);

    private Color boxBorderColor = new Color(40, 40, 40);
    private Color hoverBoxBorderColor = new Color(80, 80, 80);
    private Color pressedBoxBorderColor = new Color(120, 120, 120);
    private Color checkColor = new Color(0, 200, 0);

    public FlexCheckBox(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setIcon(new CheckBoxIcon());
        setIconTextGap(5);
    }

    /**
     * Returns the corner radius of the checkbox selection box.
     *
     * @return the corner radius in pixels
     */
    public int getBoxCornerRadius() {
        return radius;
    }

    /**
     * Sets the corner radius of the checkbox selection box.
     *
     * @param radius the radius in pixels used to round the checkbox corners
     * @throws IllegalArgumentException if {@code cornerRadius} is negative
     */
    public void setBoxCornerRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");

        this.radius = radius;
        repaint();
    }

    /**
     * Gets the background color of the box.
     *
     * @return the background color of the box
     */
    public Color getBoxBackgroundColor() {
        return boxBackground;
    }

    /**
     * Sets the background color of the box.
     *
     * @param color the new box background color, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setBoxBackgroundColor(Color color) {
        this.boxBackground = Objects.requireNonNull(color, "Color cannot be null.");
        repaint();
    }

    /**
     * Gets the box border color.
     *
     * @return the box border color
     */
    public Color getBoxBorderColor() {
        return boxBorderColor;
    }

    /**
     * Sets the border color of the box.
     *
     * @param color the new box border color, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setBoxBorderColor(Color color) {
        this.boxBorderColor = Objects.requireNonNull(color, "Border color cannot be null.");
        repaint();
    }

    /**
     * Gets the background color of the box when the component is hovered.
     *
     * @return the hover background color of the box
     */
    public Color getHoverBoxBackground() {
        return hoverBoxBackground;
    }

    /**
     * Sets the background color of the box when the component is hovered.
     *
     * @param color the new hover background color of the box, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setHoverBoxBackground(Color color) {
        this.hoverBoxBackground = Objects.requireNonNull(color, "Hover background cannot be null.");
    }

    /**
     * Gets the border color of the box when the component is hovered.
     *
     * @return the hover border color of the box
     */
    public Color getHoverBoxBorderColor() {
        return hoverBoxBorderColor;
    }

    /**
     * Sets the border color of the box when the component is hovered.
     *
     * @param color the new hover border color of the box, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setHoverBoxBorderColor(Color color) {
        this.hoverBoxBorderColor = Objects.requireNonNull(color, "Hover border color cannot be null.");
    }

    /**
     * Gets the background color of the box when the component is pressed.
     *
     * @return the pressed background color of the box
     */
    public Color getPressedBoxBackground() {
        return pressedBoxBackground;
    }

    /**
     * Sets the background color of the box when the component is pressed.
     *
     * @param color the new pressed background color of the box, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setPressedBoxBackground(Color color) {
        this.pressedBoxBackground = Objects.requireNonNull(color, "Pressed box background color cannot be null.");
    }

    /**
     * Gets the border color of the box when the component is pressed.
     *
     * @return the pressed border color of the box
     */
    public Color getPressedBoxBorderColor() {
        return pressedBoxBorderColor;
    }

    /**
     * Sets the border color of the box when the component is pressed.
     *
     * @param color the new pressed border color of the box, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setPressedBoxBorderColor(Color color) {
        this.pressedBoxBorderColor = Objects.requireNonNull(color, "Pressed border color cannot be null.");
    }

    /**
     * Gets the color of the check mark.
     *
     * @return the check mark color
     */
    public Color getCheckMarkColor() {
        return checkColor;
    }

    /**
     * Sets the color of the check mark inside the checkbox.
     *
     * @param checkColor the {@code Color} to be used for the check mark; must not be {@code null}
     * @throws NullPointerException if {@code checkColor} is {@code null}
     */
    public void setCheckMarkColor(Color checkColor) {
        this.checkColor = Objects.requireNonNull(checkColor, "Check color cannot be null.");
        repaint();
    }
}