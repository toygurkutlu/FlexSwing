package io.github.toygurkutlu.flexswing.components.combo_box.records;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the {@code FlexComboBox} arrow button.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param background   the background color of the arrow button
 * @param pressedColor the background color of the arrow button when pressed
 * @param hoverColor   the background color of the arrow button when hovered
 * @param borderColor  the border color of the arrow button
 * @param radii        the corner radii of the arrow button
 * @param arrowColor   the color of the arrow icon
 * @see #defaultArrowButtonAttributes()
 * @see #withBackground(Color)
 * @see #withPressedColor(Color)
 * @see #withHoverColor(Color)
 * @see #withBorderColor(Color)
 * @see #withRadii(Radii)
 * @see #withArrowColor(Color)
 * @see FlexComboBox
 * @see Radii
 */
public record ArrowButtonAttributes(Color background,
                                    Color pressedColor,
                                    Color hoverColor,
                                    Color borderColor,
                                    Radii radii,
                                    Color arrowColor) {

    /**
     * Creates the default style attributes for the arrow button.
     *
     * @return the {@code ArrowButtonAttributes} of the {@code FlexComboBox}
     */
    public static ArrowButtonAttributes defaultArrowButtonAttributes() {
        return new ArrowButtonAttributes(new Color(80, 80, 80),
                                         new Color(100, 100, 100),
                                         new Color(120, 120, 120),
                                         new Color(50, 50, 50),
                                         new Radii(0, 10, 0, 10),
                                         new Color(255, 255, 255));
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color.
     *
     * @param color the new background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonAttributes withBackground(Color color) {
        return new ArrowButtonAttributes(Objects.requireNonNull(color, "Color cannot be null."),
                                         this.pressedColor,
                                         this.hoverColor,
                                         this.borderColor,
                                         this.radii,
                                         this.arrowColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color when the button
     * is pressed.
     *
     * @param color the new pressed background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonAttributes withPressedColor(Color color) {
        return new ArrowButtonAttributes(this.background,
                                         Objects.requireNonNull(color, "Color cannot be null."),
                                         this.hoverColor,
                                         this.borderColor,
                                         this.radii,
                                         this.arrowColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified button background color hovered.
     *
     * @param color the new hover background color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonAttributes withHoverColor(Color color) {
        return new ArrowButtonAttributes(this.background,
                                         this.pressedColor,
                                         Objects.requireNonNull(color, "Color cannot be null."),
                                         this.borderColor,
                                         this.radii,
                                         this.arrowColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified border color of the arrow button.
     *
     * @param color the new border color for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated border color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonAttributes withBorderColor(Color color) {
        return new ArrowButtonAttributes(this.background,
                                         this.pressedColor,
                                         this.hoverColor,
                                         Objects.requireNonNull(color, "Color cannot be null."),
                                         this.radii,
                                         this.arrowColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified border corner radii of the arrow button.
     *
     * @param radii the new corner radii for the arrow button, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     */
    public ArrowButtonAttributes withRadii(Radii radii) {
        return new ArrowButtonAttributes(this.background,
                                         this.pressedColor,
                                         this.hoverColor,
                                         this.borderColor,
                                         Objects.requireNonNull(radii, "Radii cannot be null."),
                                         this.arrowColor);
    }

    /**
     * Creates a copy of this {@code ArrowButtonAttributes} with the specified arrow color of the arrow button.
     *
     * @param color the new color for the arrow icon, cannot be {@code null}
     * @return a new {@code ArrowButtonAttributes} instance with the updated arrow icon color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ArrowButtonAttributes withArrowColor(Color color) {
        return new ArrowButtonAttributes(this.background,
                                         this.pressedColor,
                                         this.hoverColor,
                                         this.borderColor,
                                         this.radii,
                                         Objects.requireNonNull(color, "Color cannot be null."));
    }
}
