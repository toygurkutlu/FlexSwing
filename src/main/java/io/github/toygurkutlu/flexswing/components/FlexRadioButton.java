package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.components.radio_button.RadioButtonIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Customizable an item that can be selected or deselected, and which displays its state to the user.
 * Used with a ButtonGroup object to create a group of buttons in which only one button at a time can be
 * selected. (Create a ButtonGroup object and use its add method to include the JRadioButton objects in the group.)
 *
 <p>Use the following methods to customize attributes:</p>
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
 *     <li><strong>Dot (inner circle) color:</strong>
 *         <ul>
 *             <li>{@link #setDotColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Outer circle:</strong>
 *         <ul>
 *             <li>{@link #setCircleBackgroundColor(Color)}</li>
 *             <li>{@link #setCircleBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Hover effect:</strong>
 *         <ul>
 *             <li>{@link #setHoverCircleBackgroundColor(Color)}</li>
 *             <li>{@link #setHoverCircleBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Pressed effect:</strong>
 *         <ul>
 *             <li>{@link #setPressedCircleBackgroundColor(Color)}</li>
 *             <li>{@link #setPressedCircleBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JCheckBox
 */
public class FlexRadioButton extends JRadioButton {

    private Color circleBackground = new Color(40, 40, 40);
    private Color hoverCircleBackground = new Color(80, 80, 80);
    private Color pressedCircleBackground = new Color(120, 120, 120);

    private Color circleBorderColor = new Color(40, 40, 40);
    private Color hoverCircleBorderColor = new Color(80, 80, 80);
    private Color pressedCircleBorderColor = new Color(120, 120, 120);
    private Color dotColor = new Color(0, 200, 0);

    public FlexRadioButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        setIcon(new RadioButtonIcon());
    }

    /**
     * Gets the background color of the outer circle.
     *
     * @return the background color of the outer circle
     */
    public Color getCircleBackgroundColor() {
        return circleBackground;
    }

    /**
     * Sets the background color of the outer circle.
     *
     * @param color the new background color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setCircleBackgroundColor(Color color) {
        this.circleBackground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the border color of the outer circle.
     *
     * @return the border color of the outer circle
     */
    public Color getCircleBorderColor() {
        return circleBorderColor;
    }

    /**
     * Sets the border color of the outer circle.
     *
     * @param color  the new border color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setCircleBorderColor(Color color) {
        this.circleBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the background color of the outer circle when the component is hovered.
     *
     * @return the hover background color of the outer circle
     */
    public Color getHoverCircleBackgroundColor() {
        return hoverCircleBackground;
    }

    /**
     * Sets the background color of the outer circle when the component is hovered.
     *
     * @param color the new hover background color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setHoverCircleBackgroundColor(Color color) {
        this.hoverCircleBackground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the border color of the outer circle when the component is hovered.
     *
     * @return the hover border color of the outer circle
     */
    public Color getHoverCircleBorderColor() {
        return hoverCircleBorderColor;
    }

    /**
     * Sets the border color of the outer circle when the component is hovered.
     *
     * @param color the new hover border color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setHoverCircleBorderColor(Color color) {
        this.hoverCircleBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the background color of the outer circle when the component is pressed.
     *
     * @return the pressed background color of the outer circle
     */
    public Color getPressedCircleBackgroundColor() {
        return pressedCircleBackground;
    }

    /**
     * Sets the background color of the outer circle when the component is pressed.
     *
     * @param color the new pressed background color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setPressedCircleBackgroundColor(Color color) {
        this.pressedCircleBackground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the border color of the outer circle when the component is pressed.
     *
     * @return the pressed border color of the outer circle
     */
    public Color getPressedCircleBorderColor() {
        return pressedCircleBorderColor;
    }

    /**
     * Sets the border color of the outer circle when the component is pressed.
     *
     * @param color the new pressed border color of the outer circle, cannot be {@code null}
     * @throws NullPointerException if the provided color is {@code null}
     */
    public void setPressedCircleBorderColor(Color color) {
        this.pressedCircleBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the color of the inner selected dot.
     *
     * @return the inner dot color
     */
    public Color getDotColor() {
        return dotColor;
    }

    /**
     * Sets the color of the inner selected dot.
     *
     * @param color color to be used for the inner dot; cannot be {@code null}
     * @throws NullPointerException if the color is {@code null}
     */
    public void setDotColor(Color color) {
        this.dotColor = Objects.requireNonNull(color, "Color cannot be null.");
    }
}