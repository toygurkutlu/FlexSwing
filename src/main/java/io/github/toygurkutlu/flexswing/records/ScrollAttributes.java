package io.github.toygurkutlu.flexswing.records;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import io.github.toygurkutlu.flexswing.components.NavigationView;
import io.github.toygurkutlu.flexswing.components.navigation_view.records.NavItemAttributes;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the vertical scroll bar.
 *
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param trackColor     the track color
 * @param thumbColor     the thumb color
 * @param thumbDragColor the thumb color while dragging
 * @param thumbRadius    the radius of the thumb corners
 * @see NavigationView
 * @see NavItemAttributes
 * @see FlexComboBox
 * @see #defaultScrollAttributes()
 * @see #withTrackColor(Color)
 * @see #withThumbColor(Color)
 * @see #withThumbDragColor(Color)
 * @see #withThumbRadius(int)
 */
public record ScrollAttributes(Color trackColor,
                               Color thumbColor,
                               Color thumbDragColor,
                               int thumbRadius) {

    /**
     * Creates the default style attributes for the vertical scroll bar.
     *
     * @return the pre-defined {@code ScrollAttributes}
     */
    public static ScrollAttributes defaultScrollAttributes() {
        return new ScrollAttributes(new Color(80, 80, 80),
                                    new Color(120, 120, 120),
                                    new Color(160, 160, 160),
                                    6);
    }

    /**
     * Creates a copy of this {@code ScrollAttributes} with the specified track color.
     *
     * @param color the new track color for the vertical scroll bar, cannot be {@code null}
     * @return a new {@code ScrollAttributes} instance with the updated track color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ScrollAttributes withTrackColor(Color color) {
        return new ScrollAttributes(Objects.requireNonNull(color, "Color cannot be null."),
                                    this.thumbColor,
                                    this.thumbDragColor,
                                    this.thumbRadius);
    }

    /**
     * Creates a copy of this {@code ScrollAttributes} with the specified thumb color.
     *
     * @param color the new thumb color for the vertical scroll bar, cannot be {@code null}
     * @return a new {@code ScrollAttributes} instance with the updated thumb color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ScrollAttributes withThumbColor(Color color) {
        return new ScrollAttributes(this.trackColor,
                                    Objects.requireNonNull(color, "Color cannot be null."),
                                    this.thumbDragColor,
                                    this.thumbRadius);
    }

    /**
     * Creates a copy of this {@code ScrollAttributes} with the specified thumb color while dragging.
     *
     * @param color the new dragging thumb color for the vertical scroll bar, cannot be {@code null}
     * @return a new {@code ScrollAttributes} instance with the updated dragging thumb color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public ScrollAttributes withThumbDragColor(Color color) {
        return new ScrollAttributes(this.trackColor,
                                    this.thumbColor,
                                    Objects.requireNonNull(color, "Color cannot be null."),
                                    this.thumbRadius);
    }

    /**
     * Creates a copy of this {@code ScrollAttributes} with the specified thumb corner radius.
     *
     * @param radius the new dragging thumb color for the vertical scroll bar, cannot be negative
     * @return a new {@code ScrollAttributes} instance with the updated thumb corner radius
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public ScrollAttributes withThumbRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot  be negative.");
        return new ScrollAttributes(this.trackColor,
                                    this.thumbColor,
                                    this.thumbDragColor,
                                    radius);
    }
}