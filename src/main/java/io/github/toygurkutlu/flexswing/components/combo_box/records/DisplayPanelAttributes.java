package io.github.toygurkutlu.flexswing.components.combo_box.records;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the display panel of the {@code FlexComboBox}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param background      the background color of the display panel
 * @param borderColor     the border color of the display panel
 * @param borderThickness the border thickness of the display panel
 * @param radii           the border corner radii of the display panel
 * @param foreground      the text color of the displayed text
 * @param font            the text font of the displayed text
 * @param padding         the internal gaps between the displayed text and the border of the display panel
 * @see #defaultDisplayPanelAttributes()
 * @see #withBackground(Color)
 * @see #withBorderColor(Color)
 * @see #withBorderThickness(int)
 * @see #withRadii(Radii)
 * @see #withForeground(Color)
 * @see #withFont(Font)
 * @see #withPadding(Padding)
 * @see FlexComboBox
 * @see Radii
 * @see Padding
 */
public record DisplayPanelAttributes(Color background,
                                     Color borderColor,
                                     int borderThickness,
                                     Radii radii,
                                     Color foreground,
                                     Font font,
                                     Padding padding) {

    /**
     * Creates the default display panel style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code DisplayPanelAttributes} of the {@code FlexComboBox}
     */
    public static DisplayPanelAttributes defaultDisplayPanelAttributes() {
        return new DisplayPanelAttributes(new Color(50, 50, 50),
                                          new Color(50, 50, 50),
                                          1,
                                          new Radii(10, 0, 10, 0),
                                          new Color(255, 255, 255),
                                          new Font("Arial", Font.PLAIN, 14),
                                          new Padding(5, 10, 5, 5));
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified background color of the display panel.
     *
     * @param color the new background color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelAttributes withBackground(Color color) {
        return new DisplayPanelAttributes(Objects.requireNonNull(color, "Color cannot be null."),
                                          this.borderColor,
                                          this.borderThickness,
                                          this.radii,
                                          this.foreground,
                                          this.font,
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified border color of the display panel.
     *
     * @param color the new border color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated border color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelAttributes withBorderColor(Color color) {
        return new DisplayPanelAttributes(this.background,
                                          Objects.requireNonNull(color, "Color cannot be null."),
                                          this.borderThickness,
                                          this.radii,
                                          this.foreground,
                                          this.font,
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified border thickness of the display panel.
     *
     * @param thickness the new border thickness of the display panel
     * @return a new {@code DisplayPanelAttributes} instance with the updated border thickness
     * @throws IllegalArgumentException if the provided {@code thickness} is negative
     */
    public DisplayPanelAttributes withBorderThickness(int thickness) {
        if (thickness < 0) throw new IllegalArgumentException("Thickness cannot be negative.");
        return new DisplayPanelAttributes(this.background,
                                          this.borderColor,
                                          thickness,
                                          this.radii,
                                          this.foreground,
                                          this.font,
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified corner radii of the display panel border.
     *
     * @param radii the new corner radii of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated corner radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     */
    public DisplayPanelAttributes withRadii(Radii radii) {
        return new DisplayPanelAttributes(this.background,
                                          this.borderColor,
                                          this.borderThickness,
                                          Objects.requireNonNull(radii, "Radii cannot be null."),
                                          this.foreground,
                                          this.font,
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified foreground color of the display panel.
     *
     * @param color the new foreground color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated foreground color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelAttributes withForeground(Color color) {
        return new DisplayPanelAttributes(this.background,
                                          this.borderColor,
                                          this.borderThickness,
                                          this.radii,
                                          Objects.requireNonNull(color, "Color cannot be null."),
                                          this.font,
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified font of the display panel.
     *
     * @param font the new font of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated font
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public DisplayPanelAttributes withFont(Font font) {
        return new DisplayPanelAttributes(this.background,
                                          this.borderColor,
                                          this.borderThickness,
                                          this.radii,
                                          this.foreground,
                                          Objects.requireNonNull(font, "Font cannot be null."),
                                          this.padding);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified internal gaps between the displayed
     * text and the border of the display panel.
     *
     * @param padding the new internal gaps of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated internal gaps
     * @throws NullPointerException if the provided {@code padding} is {@code null}
     */
    public DisplayPanelAttributes withPadding(Padding padding) {
        return new DisplayPanelAttributes(this.background,
                                          this.borderColor,
                                          this.borderThickness,
                                          this.radii,
                                          this.foreground,
                                          this.font,
                                          Objects.requireNonNull(padding, "Padding cannot be null."));
    }
}