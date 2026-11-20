package io.github.toygurkutlu.flexswing.components.combo_box.domain;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the {@code FlexComboBox} arrow button.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @see #defaultArrowButtonAttributes()
 * @see #withBackground(Color)
 * @see #withPressedColor(Color)
 * @see #withHoverColor(Color)
 * @see #withBorderColor(Color)
 * @see #withArrowColor(Color)
 * @see FlexComboBox
 */
public class ArrowButtonConfig {

    private Color background;
    private Color pressedBackground;
    private Color hoverBackground;
    private Color borderColor;
    private Color arrowIconColor;

    public ArrowButtonConfig(Color background, Color pressedBackground, Color hoverBackground, Color borderColor,
                             Color arrowIconColor) {
        this.background = background;
        this.pressedBackground = pressedBackground;
        this.hoverBackground = hoverBackground;
        this.borderColor = borderColor;
        this.arrowIconColor = arrowIconColor;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getPressedBackground() {
        return pressedBackground;
    }

    public void setPressedBackground(Color pressedBackground) {
        this.pressedBackground = pressedBackground;
    }

    public Color getHoverBackground() {
        return hoverBackground;
    }

    public void setHoverBackground(Color hoverBackground) {
        this.hoverBackground = hoverBackground;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getArrowIconColor() {
        return arrowIconColor;
    }

    public void setArrowIconColor(Color arrowIconColor) {
        this.arrowIconColor = arrowIconColor;
    }

    /**
     * Creates the default style attributes for the arrow button.
     *
     * @return the {@code ArrowButtonAttributes} of the {@code FlexComboBox}
     */
    public static ArrowButtonConfig defaultArrowButtonAttributes() {
        return new ArrowButtonConfig(new Color(80, 80, 80),
                                     new Color(100, 100, 100),
                                     new Color(120, 120, 120),
                                     new Color(50, 50, 50),
                                     new Color(255, 255, 255));
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color.
     *
     * @param color the new background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonConfig withBackground(Color color) {
        return new ArrowButtonConfig(Objects.requireNonNull(color, "Color cannot be null."),
                                     this.pressedBackground,
                                     this.hoverBackground,
                                     this.borderColor,
                                     this.arrowIconColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color when the button
     * is pressed.
     *
     * @param color the new pressed background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonConfig withPressedColor(Color color) {
        return new ArrowButtonConfig(this.background,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.hoverBackground,
                                     this.borderColor,
                                     this.arrowIconColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color hovered.
     *
     * @param color the new hover background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonConfig withHoverColor(Color color) {
        return new ArrowButtonConfig(this.background,
                                     this.pressedBackground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.borderColor,
                                     this.arrowIconColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified border color of the arrow button.
     *
     * @param color the new border color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated border color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonConfig withBorderColor(Color color) {
        return new ArrowButtonConfig(this.background,
                                     this.pressedBackground,
                                     this.hoverBackground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.arrowIconColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified arrow color of the arrow button.
     *
     * @param color the new color for the arrow icon, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated arrow icon color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonConfig withArrowColor(Color color) {
        return new ArrowButtonConfig(this.background,
                                     this.pressedBackground,
                                     this.hoverBackground,
                                     this.borderColor,
                                     Objects.requireNonNull(color, "Color cannot be null."));
    }
}