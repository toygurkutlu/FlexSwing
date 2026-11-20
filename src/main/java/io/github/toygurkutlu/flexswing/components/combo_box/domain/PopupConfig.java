package io.github.toygurkutlu.flexswing.components.combo_box.domain;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.ListTooltip;
import io.github.toygurkutlu.flexswing.objects.Radii;
import io.github.toygurkutlu.flexswing.objects.ScrollAttributes;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the popup (drop down panel) of the {@code FlexComboBox}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @see #defaultPopupAttributes()
 * @see #withBackground(Color)
 * @see #withBorderColor(Color)
 * @see #withItemForeground(Color)
 * @see #withItemBackground(Color)
 * @see #withSelectedForeground(Color)
 * @see #withSelectedBackground(Color)
 * @see #withFont(Font)
 * @see #withHasTooltip(boolean)
 * @see #withScrollAttributes(ScrollAttributes)
 * @see FlexComboBox
 * @see Radii
 * @see ScrollAttributes
 */
public class PopupConfig {
    private Color background;
    private Color borderColor;
    private Color itemForeground;
    private Color itemBackground;
    private Color selectedForeground;
    private Color selectedBackground;
    private Font font;
    private boolean hasTooltip;
    private ScrollAttributes scrollAttributes;

    public PopupConfig(Color background, Color borderColor, Color itemForeground, Color itemBackground,
                       Color selectedForeground, Color selectedBackground, Font font, boolean hasTooltip,
                       ScrollAttributes scrollAttributes) {
        this.background = background;
        this.borderColor = borderColor;
        this.itemForeground = itemForeground;
        this.itemBackground = itemBackground;
        this.selectedForeground = selectedForeground;
        this.selectedBackground = selectedBackground;
        this.font = font;
        this.hasTooltip = hasTooltip;
        this.scrollAttributes = scrollAttributes;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getItemForeground() {
        return itemForeground;
    }

    public void setItemForeground(Color itemForeground) {
        this.itemForeground = itemForeground;
    }

    public Color getItemBackground() {
        return itemBackground;
    }

    public void setItemBackground(Color itemBackground) {
        this.itemBackground = itemBackground;
    }

    public Color getSelectedForeground() {
        return selectedForeground;
    }

    public void setSelectedForeground(Color selectedForeground) {
        this.selectedForeground = selectedForeground;
    }

    public Color getSelectedBackground() {
        return selectedBackground;
    }

    public void setSelectedBackground(Color selectedBackground) {
        this.selectedBackground = selectedBackground;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean isHasTooltip() {
        return hasTooltip;
    }

    public void setHasTooltip(boolean hasTooltip) {
        this.hasTooltip = hasTooltip;
    }

    public ScrollAttributes getScrollAttributes() {
        return scrollAttributes;
    }

    public void setScrollAttributes(ScrollAttributes scrollAttributes) {
        this.scrollAttributes = scrollAttributes;
    }

    /**
     * Creates the default popup style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code PopupAttributes} of the {@code FlexComboBox}
     */
    public static PopupConfig defaultPopupAttributes() {
        return new PopupConfig(new Color(80, 80, 80),
                               new Color(80, 80, 80),
                               new Color(255, 255, 255),
                               new Color(80, 80, 80),
                               new Color(50, 50, 50),
                               new Color(255, 255, 255),
                               new Font("Arial", Font.PLAIN, 14),
                               true,
                               ScrollAttributes.defaultScrollAttributes());
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified background color of the popup.
     *
     * @param color the new background color of the popup, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withBackground(Color color) {
        return new PopupConfig(Objects.requireNonNull(color, "Color cannot be null."),
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified border color of the popup.
     *
     * @param color the new border color of the popup, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated border color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withBorderColor(Color color) {
        return new PopupConfig(this.background,
                               Objects.requireNonNull(color, "Color cannot be null."),
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified foreground color of the items in the list.
     *
     * @param color the new foreground color of the items, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated foreground color of the items
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withItemForeground(Color color) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               Objects.requireNonNull(color, "Color cannot be null."),
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified background color of the items in the list.
     *
     * @param color the new background color of the items, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated background color of the items
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withItemBackground(Color color) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               Objects.requireNonNull(color, "Color cannot be null."),
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified foreground color of the selected item
     * in the list.
     *
     * @param color the new foreground color of the selected item, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated foreground color of the selected item
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withSelectedForeground(Color color) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               Objects.requireNonNull(color, "Color cannot be null."),
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified background color of the selected item
     * in the list.
     *
     * @param color the new background color of the selected item, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated background color of the selected item
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public PopupConfig withSelectedBackground(Color color) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               Objects.requireNonNull(color, "Color cannot be null."),
                               this.font,
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified font for the popup.
     *
     * @param font the new font for the popup, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated font
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public PopupConfig withFont(Font font) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               Objects.requireNonNull(font, "Font cannot be null."),
                               this.hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified tooltip config for the {@code Popup}
     *
     * @param hasTooltip {@code true} for enabling tooltip; {@code false} otherwise
     * @return a new {@code PopupAttributes} instance with the updated tooltip config
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * @apiNote If enabled, displays item text in the tooltip when hovered. Useful for long texts.
     * @see FlexComboBox#updateTooltip(ListTooltip.ListTooltipAttribute, Object)
     */
    public PopupConfig withHasTooltip(boolean hasTooltip) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               hasTooltip,
                               this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified style attributes for the vertical scroll bar
     * of the {@code Popup}
     *
     * @param attr the new style attributes for the vertical scroll bar, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated style attributes of the vertical scroll bar
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public PopupConfig withScrollAttributes(ScrollAttributes attr) {
        return new PopupConfig(this.background,
                               this.borderColor,
                               this.itemForeground,
                               this.itemBackground,
                               this.selectedForeground,
                               this.selectedBackground,
                               this.font,
                               this.hasTooltip,
                               Objects.requireNonNull(attr, "ScrollAttributes cannot be null."));
    }
}