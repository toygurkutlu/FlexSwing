package io.github.toygurkutlu.flexswing.components.card_view.records;


import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.StateColors;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes of the {@code CardView} texts.
 * <ul>
 *     <li>User can create custom style or can use default attributes.</li>
 *     <li>If decided to use default attributes, user can change any attributes using {@code with...()} methods.</li>
 * </ul>
 *
 * @param textType    type of the text
 * @param foregrounds the foreground colors of the text. Each color represents one of the {@link CardView}'s
 *                    {@code StateType}
 * @param font        the font of the text
 * @param padding     internal gaps between texts
 * @see #defaultHeaderTextAttributes()
 * @see #defaultContentTitleTextAttributes()
 * @see #defaultContentSubtitleTextAttributes()
 * @see #defaultContentPlainTextAttributes()
 * @see #defaultContentUnitTextAttributes()
 * @see #withForegrounds(StateColors)
 * @see #withFont(Font)
 * @see #withPadding(Padding)
 * @see #withPadding(int)
 * @see TextType
 * @see StateColors
 * @see StateType
 * @see Padding
 */
public record TextAttributes(TextType textType,
                             StateColors foregrounds,
                             Font font,
                             Padding padding) {

    /**
     * Creates the default style attributes for the header text.
     *
     * @return the {@code TextAttributes} of the header
     */
    public static TextAttributes defaultHeaderTextAttributes() {
        return new TextAttributes(TextType.TITLE,
                                  StateColors.defaultHeaderForegrounds(),
                                  new Font("Segoe UI", Font.BOLD, 16),
                                  new Padding(5, 10, 5, 10));
    }

    /**
     * Creates the default style attributes for the {@link TextType#TITLE} components in the content panel.
     *
     * @return the default {@code TextAttributes} configuration for title text
     */
    public static TextAttributes defaultContentTitleTextAttributes() {
        return new TextAttributes(TextType.TITLE,
                                  StateColors.defaultContentForegrounds(),
                                  new Font("Segoe UI", Font.BOLD, 15),
                                  new Padding(5, 10, 5, 10));
    }

    /**
     * Creates the default style attributes for the {@link TextType#SUBTITLE} components in the content panel.
     *
     * @return the default {@code TextAttributes} configuration for subtitle text
     */
    public static TextAttributes defaultContentSubtitleTextAttributes() {
        return new TextAttributes(TextType.SUBTITLE,
                                  StateColors.defaultContentForegrounds(),
                                  new Font("Segoe UI", Font.BOLD, 14),
                                  new Padding(5, 10, 5, 10));
    }

    /**
     * Creates the default style attributes for the {@link TextType#PLAIN} components in the content panel.
     *
     * @return the default {@code TextAttributes} configuration for plain text
     */
    public static TextAttributes defaultContentPlainTextAttributes() {
        return new TextAttributes(TextType.PLAIN,
                                  StateColors.defaultContentForegrounds(),
                                  new Font("Dialog", Font.PLAIN, 13),
                                  new Padding(5, 10, 5, 10));
    }

    /**
     * Creates the default style attributes for the {@link TextType#UNIT} components in the content panel.
     *
     * @return the default {@code TextAttributes} configuration for unit text
     */
    public static TextAttributes defaultContentUnitTextAttributes() {
        return new TextAttributes(TextType.UNIT,
                                  StateColors.defaultContentForegrounds(),
                                  new Font("Dialog", Font.ITALIC, 13),
                                  new Padding(5, 10, 5, 10));
    }

    /**
     * Creates a copy of this {@code TextAttributes} with the specified foreground colors.
     *
     * @param foregrounds the new foreground colors for the text, cannot be {@code null}
     * @return a new {@code TextAttributes} instance with the updated foreground colors
     * @throws NullPointerException if the provided {@code foregrounds} is {@code null}
     * @see StateColors#StateColors(Color, Color, Color, Color)
     */
    public TextAttributes withForegrounds(StateColors foregrounds) {
        return new TextAttributes(this.textType,
                                  Objects.requireNonNull(foregrounds, "Foregrounds cannot be null."),
                                  this.font,
                                  new Padding(5, 5, 5, 5));
    }

    /**
     * Creates a copy of this {@code TextAttributes} with the specified font.
     *
     * @param font the new font for the text, cannot be {@code null}
     * @return a new {@code TextAttributes} instance with the updated font
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public TextAttributes withFont(Font font) {
        return new TextAttributes(this.textType,
                                  this.foregrounds,
                                  Objects.requireNonNull(font, "Font cannot be null."),
                                  new Padding(5, 5, 5, 5));
    }

    /**
     * Creates a copy of this {@code TextAttributes} with the internal gaps.
     *
     * @param padding the new internal gaps between texts, cannot be {@code null}
     * @return a new {@code TextAttributes} instance with the updated padding
     * @throws NullPointerException if the provided {@code padding} is {@code null}
     * @see #withPadding(int)
     */
    public TextAttributes withPadding(Padding padding) {
        return new TextAttributes(this.textType,
                                  this.foregrounds,
                                  this.font,
                                  Objects.requireNonNull(padding, "Padding cannot be null."));
    }

    /**
     * Creates a copy of this {@code TextAttributes} with the internal gaps.
     *
     * @param padding the new internal gaps between texts, cannot be {@code null}
     * @return a new {@code TextAttributes} instance with the updated padding
     * @throws NullPointerException if the provided {@code padding} is {@code null}
     * @see #withPadding(Padding)
     */
    public TextAttributes withPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        return new TextAttributes(this.textType,
                                  this.foregrounds,
                                  this.font,
                                  new Padding(padding));
    }

    /**
     * Gets the foreground color associated with the specified {@code StateType}.
     *
     * @param stateType the type of the card state, cannot be {@code null}
     * @return the foreground color that matches the specified {@code StateType}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public Color foregroundByState(StateType stateType) {
        return this.foregrounds.getColorByState(Objects.requireNonNull(stateType, "StateType cannot be null."));
    }
}