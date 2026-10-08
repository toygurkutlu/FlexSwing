package io.github.toygurkutlu.flexswing.components.combo_box.records;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.ListTooltip;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;
import io.github.toygurkutlu.flexswing.records.ScrollAttributes;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the popup (drop down panel) of the {@code FlexComboBox}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param background         the background color of the popup
 * @param borderColor        the border color of the popup
 * @param borderThickness    the border thickness of the popup
 * @param radii              the border corner radii of the popup
 * @param itemForeground     the text color of the items in the list
 * @param itemBackground     the background color of items in the list
 * @param selectedForeground the text color of the selected item
 * @param selectedBackground the background color of the selected item
 * @param font               the font of the popup
 * @param padding            the internal gaps between the displayed text and the border of the popup
 * @param hasTooltip         the status of the tooltip mechanism when hovered
 * @param scrollAttributes   the style of the vertical scroll bar
 * @see #defaultPopupAttributes()
 * @see #withBackground(Color)
 * @see #withBorderColor(Color)
 * @see #withBorderThickness(int)
 * @see #withRadii(Radii)
 * @see #withItemForeground(Color)
 * @see #withItemBackground(Color)
 * @see #withSelectedForeground(Color)
 * @see #withSelectedBackground(Color)
 * @see #withFont(Font)
 * @see #withHasTooltip(boolean)
 * @see #withScrollAttributes(ScrollAttributes)
 * @see FlexComboBox
 * @see Radii
 * @see Padding
 * @see ScrollAttributes
 */
public record PopupAttributes(Color background,
                              Color borderColor,
                              int borderThickness,
                              Radii radii,
                              Color itemForeground,
                              Color itemBackground,
                              Color selectedForeground,
                              Color selectedBackground,
                              Font font,
                              Padding padding,
                              boolean hasTooltip,
                              ScrollAttributes scrollAttributes) {

    /**
     * Creates the default popup style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code PopupAttributes} of the {@code FlexComboBox}
     */
    public static PopupAttributes defaultPopupAttributes() {
        return new PopupAttributes(new Color(80, 80, 80),
                                   new Color(80, 80, 80),
                                   1,
                                   new Radii(0, 0, 10, 10),
                                   new Color(255, 255, 255),
                                   new Color(80, 80, 80),
                                   new Color(50, 50, 50),
                                   new Color(255, 255, 255),
                                   new Font("Arial", Font.PLAIN, 14),
                                   new Padding(3, 3, 3, 10),
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
    public PopupAttributes withBackground(Color color) {
        return new PopupAttributes(Objects.requireNonNull(color, "Color cannot be null."),
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withBorderColor(Color color) {
        return new PopupAttributes(this.background,
                                   Objects.requireNonNull(color, "Color cannot be null."),
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
                                   this.hasTooltip,
                                   this.scrollAttributes);
    }

    public PopupAttributes withBorderThickness(int thickness) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   thickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
                                   this.hasTooltip,
                                   this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified border corner radii of the popup.
     *
     * @param radii the new border corner radii of the popup, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated border corner radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     */
    public PopupAttributes withRadii(Radii radii) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   Objects.requireNonNull(radii, "Radii cannot be null."),
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withItemForeground(Color color) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   Objects.requireNonNull(color, "Color cannot be null."),
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withItemBackground(Color color) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   Objects.requireNonNull(color, "Color cannot be null."),
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withSelectedForeground(Color color) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   Objects.requireNonNull(color, "Color cannot be null."),
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withSelectedBackground(Color color) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   Objects.requireNonNull(color, "Color cannot be null."),
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withFont(Font font) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   Objects.requireNonNull(font, "Font cannot be null."),
                                   this.padding,
                                   this.hasTooltip,
                                   this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code PopupAttributes} with the specified internal gaps between items.
     *
     * @param padding the internal gaps between items, cannot be {@code null}
     * @return a new {@code PopupAttributes} instance with the updated padding
     * @throws NullPointerException if the provided {@code padding} is {@code null}
     */
    public PopupAttributes withPadding(Padding padding) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   Objects.requireNonNull(padding, "Padding cannot be null."),
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
    public PopupAttributes withHasTooltip(boolean hasTooltip) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
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
    public PopupAttributes withScrollAttributes(ScrollAttributes attr) {
        return new PopupAttributes(this.background,
                                   this.borderColor,
                                   this.borderThickness,
                                   this.radii,
                                   this.itemForeground,
                                   this.itemBackground,
                                   this.selectedForeground,
                                   this.selectedBackground,
                                   this.font,
                                   this.padding,
                                   this.hasTooltip,
                                   Objects.requireNonNull(attr, "ScrollAttributes cannot be null."));
    }
}