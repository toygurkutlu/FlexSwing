package io.github.toygurkutlu.flexswing.components.navigation_view.records;

import io.github.toygurkutlu.flexswing.components.navigation_view.enums.TextPosition;
import io.github.toygurkutlu.flexswing.objects.Padding;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes of the {@code NavigationView} items.
 * <ul>
 *     <li>User can create custom style or can use default attributes.</li>
 *     <li>If decided to use default attributes, user can change any attributes using {@code with...()} methods.</li>
 * </ul>
 *
 * @param textPosition       the position of the text according to the icon
 * @param iconTextGap        the gap between the text and the icon
 * @param background         the background color of the item
 * @param hoverBackground    the background color of the item when hovered
 * @param selectedBackground the background color of the item when selected
 * @param foreground         the foreground color of the item
 * @param hoverForeground    the foreground color of the item when hovered
 * @param selectedForeground the foreground color of the item when selected
 * @param font               the font of the item
 * @param padding            internal gaps between the item and its border
 * @see #defaultTitleAttributes()
 * @see #defaultSubtitleAttributes()
 * @see #withTextPosition(TextPosition)
 * @see #withIconTextGap(int)
 * @see #withBackground(Color)
 * @see #withHoverBackground(Color)
 * @see #withSelectedBackground(Color)
 * @see #withForeground(Color)
 * @see #withHoverForeground(Color)
 * @see #withSelectedForeground(Color)
 * @see #withFont(Font)
 * @see #withPadding(Padding)
 * @see TextPosition
 * @see Padding
 */
public record NavItemAttributes(TextPosition textPosition, int iconTextGap, Color background, Color hoverBackground,
                                Color selectedBackground, Color foreground, Color hoverForeground,
                                Color selectedForeground, Font font, Padding padding) {

    /**
     * Creates the default style attributes for the {@code NavigationView} title items.
     *
     * @return the {@code NavItemAttributes} of the {@code NavigationView} title items
     */
    public static NavItemAttributes defaultTitleAttributes() {
        return new NavItemAttributes(TextPosition.RIGHT,
                                     5,
                                     new Color(40, 40, 40),
                                     new Color(80, 80, 80),
                                     new Color(100, 200, 100),
                                     new Color(175, 175, 195),
                                     new Color(215, 215, 235),
                                     new Color(10, 10, 10),
                                     new Font("Arial", Font.BOLD, 16),
                                     new Padding(5, 10, 5, 10));
    }

    /**
     * Creates the default style attributes for the {@code NavigationView} subtitle items.
     *
     * @return the {@code NavItemAttributes} of the {@code NavigationView} subtitle items
     */
    public static NavItemAttributes defaultSubtitleAttributes() {
        return new NavItemAttributes(TextPosition.RIGHT,
                                     5,
                                     new Color(40, 40, 40),
                                     new Color(80, 80, 80),
                                     new Color(100, 200, 100),
                                     new Color(175, 175, 195),
                                     new Color(215, 215, 235),
                                     new Color(10, 10, 10),
                                     new Font("Arial", Font.BOLD, 14),
                                     new Padding(5, 10, 5, 15));
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified text position according to the icon.
     *
     * @param textPosition the new text position for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated text position
     * @throws NullPointerException if the provided {@code textPosition} is {@code null}
     */
    public NavItemAttributes withTextPosition(TextPosition textPosition) {
        return new NavItemAttributes(Objects.requireNonNull(textPosition, "TextPosition cannot be null."),
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified gap between the text and the icon.
     *
     * @param gap the new gap between the text and the icon
     * @return a new {@code NavItemAttributes} instance with the updated icon text gap
     * @throws IllegalArgumentException if the provided {@code gap} is negative
     */
    public NavItemAttributes withIconTextGap(int gap) {
        if (gap < 0) throw new IllegalArgumentException("Gap cannot be negative.");
        return new NavItemAttributes(this.textPosition,
                                     gap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified background color.
     *
     * @param color the new background color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withBackground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified background color when hovered.
     *
     * @param color the new hover background color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withHoverBackground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified background color when selected.
     *
     * @param color the new selected background color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withSelectedBackground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified foreground color.
     *
     * @param color the new foreground color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated foreground color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withForeground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified foreground color when hovered.
     *
     * @param color the new hover foreground color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated foreground color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withHoverForeground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.selectedForeground,
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified foreground color when selected.
     *
     * @param color the new selected foreground color for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated foreground color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavItemAttributes withSelectedForeground(Color color) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     Objects.requireNonNull(color, "Color cannot be null."),
                                     this.font,
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified font.
     *
     * @param font the new font for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated font
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public NavItemAttributes withFont(Font font) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     Objects.requireNonNull(font, "Font cannot be null."),
                                     this.padding);
    }

    /**
     * Creates a copy of this {@code NavItemAttributes} with the specified internal padding.
     *
     * @param padding the new padding for the item, cannot be {@code null}
     * @return a new {@code NavItemAttributes} instance with the updated padding
     * @throws NullPointerException if the provided {@code padding} is {@code null}
     */
    public NavItemAttributes withPadding(Padding padding) {
        return new NavItemAttributes(this.textPosition,
                                     this.iconTextGap,
                                     this.background,
                                     this.hoverBackground,
                                     this.selectedBackground,
                                     this.foreground,
                                     this.hoverForeground,
                                     this.selectedForeground,
                                     this.font,
                                     Objects.requireNonNull(padding, "Padding cannot be null."));
    }
}