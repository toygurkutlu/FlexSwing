package io.github.toygurkutlu.flexswing.components.combo_box.domain;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
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
 * @see #defaultDisplayPanelAttributes()
 * @see #withBackground(Color)
 * @see #withBorderColor(Color)
 * @see #withRadii(Radii)
 * @see #withRadii(int)
 * @see #withForeground(Color)
 * @see #withFont(Font)
 * @see #withTextAlignment(TextAlignment)
 * @see FlexComboBox
 * @see Radii
 * @see TextAlignment
 */
public class DisplayPanelConfig {

    private Color background;
    private Color foreground;
    private Font font;
    private TextAlignment textAlignment;
    private Color borderColor;
    private Radii radii;

    public DisplayPanelConfig(Color background,
                              Color foreground,
                              Font font,
                              TextAlignment textAlignment,
                              Color borderColor,
                              Radii radii) {
        this.background = background;
        this.foreground = foreground;
        this.font = font;
        this.textAlignment = textAlignment;
        this.borderColor = borderColor;
        this.radii = radii;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Radii getRadii() {
        return radii;
    }

    public void setRadii(Radii radii) {
        this.radii = radii;
    }

    /**
     * Creates the default display panel style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code DisplayPanelAttributes} of the {@code FlexComboBox}
     */
    public static DisplayPanelConfig defaultDisplayPanelAttributes() {
        return new DisplayPanelConfig(new Color(50, 50, 50),
                                      new Color(255, 255, 255),
                                      new Font("Arial", Font.PLAIN, 14),
                                      TextAlignment.CENTER,
                                      new Color(50, 50, 50),
                                      new Radii(10, 10, 10, 10));
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified background color of the display panel.
     *
     * @param color the new background color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelConfig withBackground(Color color) {
        return new DisplayPanelConfig(Objects.requireNonNull(color, "Color cannot be null."),
                                      this.foreground,
                                      this.font,
                                      this.textAlignment,
                                      this.borderColor,
                                      this.radii);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified foreground color of the display panel.
     *
     * @param color the new foreground color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated foreground color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelConfig withForeground(Color color) {
        return new DisplayPanelConfig(this.background,
                                      Objects.requireNonNull(color, "Color cannot be null."),
                                      this.font,
                                      this.textAlignment,
                                      this.borderColor,
                                      this.radii);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified font of the display panel.
     *
     * @param font the new font of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated font
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public DisplayPanelConfig withFont(Font font) {
        return new DisplayPanelConfig(this.background,
                                      this.foreground,
                                      Objects.requireNonNull(font, "Font cannot be null."),
                                      this.textAlignment,
                                      this.borderColor,
                                      this.radii);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified text alignment of the display panel.
     *
     * @param alignment the new text alignment of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated text alignment
     * @throws NullPointerException if the provided {@code alignment} is {@code null}
     */
    public DisplayPanelConfig withTextAlignment(TextAlignment alignment) {
        return new DisplayPanelConfig(this.background,
                                      this.foreground,
                                      this.font,
                                      Objects.requireNonNull(textAlignment, "TextAlignment cannot be null."),
                                      this.borderColor,
                                      this.radii);
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified border color of the display panel.
     *
     * @param color the new border color of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated border color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public DisplayPanelConfig withBorderColor(Color color) {
        return new DisplayPanelConfig(this.background,
                                      this.foreground,
                                      this.font,
                                      this.textAlignment,
                                      Objects.requireNonNull(color, "Color cannot be null."),
                                      this.radii);
    }


    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the specified corner radii of the display panel border.
     *
     * @param radii the new corner radii of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated corner radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     * @see #withRadii(int)
     */
    public DisplayPanelConfig withRadii(Radii radii) {
        return new DisplayPanelConfig(this.background,
                                      this.foreground,
                                      this.font,
                                      this.textAlignment,
                                      this.borderColor,
                                      Objects.requireNonNull(radii, "Radii cannot be null."));
    }

    /**
     * Creates a copy of this {@code DisplayPanelAttributes} with the same corner radius of the display panel border.
     *
     * @param radius the new corner radius of the display panel, cannot be {@code null}
     * @return a new {@code DisplayPanelAttributes} instance with the updated corner radius
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     * @see #withRadii(Radii)
     */
    public DisplayPanelConfig withRadii(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        return new DisplayPanelConfig(this.background,
                                      this.foreground,
                                      this.font,
                                      this.textAlignment,
                                      this.borderColor,
                                      new Radii(radius));
    }
}