package io.github.toygurkutlu.flexswing.components.card_view.records;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import io.github.toygurkutlu.flexswing.objects.StateColors;
import io.github.toygurkutlu.flexswing.objects.Padding;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes of the {@code CardView ContentPanel}.
 * <ul>
 *     <li>User can create custom style or can use default attributes.</li>
 *     <li>If decided to use default attributes, user can change any attributes using {@code with...()} methods.</li>
 * </ul>
 *
 * @param backgrounds        the background colors of the {@code ContentPanel}. Each color represents one of the
 *                           {@link CardView}'s {@code StateType}
 * @param titleAttributes    the style attributes of the {@link TextType#TITLE} components in the content panel
 * @param subtitleAttributes the style attributes of the {@link TextType#SUBTITLE} components in the content panel
 * @param plainAttributes    the style attributes of the {@link TextType#PLAIN} components in the content panel
 * @param unitAttributes     the style attributes of the {@link TextType#UNIT} components in the content panel
 * @see #defaultContentAttributes()
 * @see #withBackgrounds(StateColors)
 * @see #withTitleTextAttributes(TextAttributes)
 * @see #withSubtitleTextAttributes(TextAttributes)
 * @see #withPlainTextAttributes(TextAttributes)
 * @see #withUnitTextAttributes(TextAttributes)
 * @see #backgroundByState(StateType)
 * @see #foregroundByState(TextType, StateType)
 * @see #fontByTextType(TextType)
 * @see #paddingByTextType(TextType)
 * @see StateColors
 * @see StateType
 * @see TextAttributes
 * @see TextType
 */
public record ContentAttributes(StateColors backgrounds,
                                TextAttributes titleAttributes,
                                TextAttributes subtitleAttributes,
                                TextAttributes plainAttributes,
                                TextAttributes unitAttributes) {

    /**
     * Creates the default style attributes for the {@code ContentPanel}.
     *
     * @return the {@code ContentAttributes} of the {@code ContentPanel}
     */
    public static ContentAttributes defaultContentAttributes() {
        return new ContentAttributes(StateColors.defaultContentBackgrounds(),
                                     TextAttributes.defaultContentTitleTextAttributes(),
                                     TextAttributes.defaultContentSubtitleTextAttributes(),
                                     TextAttributes.defaultContentPlainTextAttributes(),
                                     TextAttributes.defaultContentUnitTextAttributes());
    }

    /**
     * Creates a copy of this {@code ContentAttributes} with the specified background colors.
     *
     * @param backgrounds the new background colors for the {@code ContentPanel}, cannot be {@code null}
     * @return a new {@code ContentAttributes} instance with the updated background colors
     * @throws NullPointerException if the provided {@code background} object is {@code null}
     * @see StateColors#StateColors(Color, Color, Color, Color)
     */
    public ContentAttributes withBackgrounds(StateColors backgrounds) {
        return new ContentAttributes(Objects.requireNonNull(backgrounds, "Backgrounds cannot be null."),
                                     this.titleAttributes,
                                     this.subtitleAttributes,
                                     this.plainAttributes,
                                     this.unitAttributes);
    }

    /**
     * Creates a copy of this {@code ContentAttributes} with the specified header text style.
     *
     * @param titleTextAttributes the new style for the {@link TextType#TITLE} components in the content panel,
     *                            cannot be {@code null}
     * @return a new {@code ContentAttributes} instance with the updated title text style
     * @throws NullPointerException if the provided {@code titleTextAttributes} object is {@code null}
     * @see TextAttributes#TextAttributes(TextType, StateColors, Font, Padding)
     */
    public ContentAttributes withTitleTextAttributes(TextAttributes titleTextAttributes) {
        return new ContentAttributes(this.backgrounds,
                                     Objects.requireNonNull(titleTextAttributes, "TitleTextAttributes cannot be null."),
                                     this.subtitleAttributes,
                                     this.plainAttributes,
                                     this.unitAttributes);
    }

    /**
     * Creates a copy of this {@code ContentAttributes} with the specified header text style.
     *
     * @param subtitleTextAttributes the new style for the {@link TextType#SUBTITLE} components in the content panel,
     *                               cannot be {@code null}
     * @return a new {@code ContentAttributes} instance with the updated subtitle text style
     * @throws NullPointerException if the provided {@code subtitleTextAttributes} object is {@code null}
     * @see TextAttributes#TextAttributes(TextType, StateColors, Font, Padding)
     */
    public ContentAttributes withSubtitleTextAttributes(TextAttributes subtitleTextAttributes) {
        return new ContentAttributes(this.backgrounds,
                                     this.titleAttributes,
                                     Objects.requireNonNull(subtitleTextAttributes, "SubtitleTextAttributes cannot be null."),
                                     this.plainAttributes,
                                     this.unitAttributes);
    }

    /**
     * Creates a copy of this {@code ContentAttributes} with the specified header text style.
     *
     * @param plainTextAttribute the new style for the {@link TextType#PLAIN} components in the content panel,
     *                           cannot be {@code null}
     * @return a new {@code ContentAttributes} instance with the updated plain text style
     * @throws NullPointerException if the provided {@code plainTextAttribute} object is {@code null}
     * @see TextAttributes#TextAttributes(TextType, StateColors, Font, Padding)
     */
    public ContentAttributes withPlainTextAttributes(TextAttributes plainTextAttribute) {
        return new ContentAttributes(this.backgrounds,
                                     this.titleAttributes,
                                     this.subtitleAttributes,
                                     Objects.requireNonNull(plainTextAttribute, "PlainTextAttribute cannot be null."),
                                     this.unitAttributes);
    }

    /**
     * Creates a copy of this {@code ContentAttributes} with the specified header text style.
     *
     * @param unitTextAttributes the new style for the {@link TextType#UNIT} components in the content panel,
     *                           cannot be {@code null}
     * @return a new {@code ContentAttributes} instance with the updated unit text style
     * @throws NullPointerException if the provided {@code unitTextAttributes} object is {@code null}
     * @see TextAttributes#TextAttributes(TextType, StateColors, Font, Padding)
     */
    public ContentAttributes withUnitTextAttributes(TextAttributes unitTextAttributes) {
        return new ContentAttributes(this.backgrounds,
                                     this.titleAttributes,
                                     this.subtitleAttributes,
                                     this.plainAttributes,
                                     Objects.requireNonNull(unitTextAttributes, "UnitTextAttributes cannot be null."));
    }

    /**
     * Gets the background color associated with the specified {@code StateType}.
     *
     * @param stateType the type of the card state, cannot be {@code null}
     * @return the background color that matches the specified {@code StateType}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public Color backgroundByState(StateType stateType) {
        return backgrounds.getColorByState(Objects.requireNonNull(stateType, "StateType cannot be null."));
    }

    /**
     * Gets the foreground color associated with the specified {@code TextType} and {@code StateType}.
     *
     * @param textType  the type of the text, cannot be {@code null}
     * @param stateType the type of the card state, cannot be {@code null}
     * @return the foreground color that matches the specified {@code TextType} and {@code StateType}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>textType</code> is <code>null</code></li>
     *                                    <li>When the provided <code>stateType</code> object is <code>null</code></li>
     *                                  </ul>
     */
    public Color foregroundByState(TextType textType, StateType stateType) {
        Objects.requireNonNull(textType, "TextType cannot be null.");
        Objects.requireNonNull(stateType, "StateType cannot be null.");
        return switch (textType) {
            case TITLE -> this.titleAttributes.foregroundByState(stateType);
            case SUBTITLE -> this.subtitleAttributes.foregroundByState(stateType);
            case PLAIN -> this.plainAttributes.foregroundByState(stateType);
            case UNIT -> this.unitAttributes.foregroundByState(stateType);
        };
    }

    /**
     * Gets the text font associated with the specified {@code TextType}.
     *
     * @param textType the type of the text, cannot be {@code null}
     * @return the text font that matches the specified {@code TextType}
     * @throws NullPointerException if the provided {@code textType} is {@code null}
     */
    public Font fontByTextType(TextType textType) {
        Objects.requireNonNull(textType, "TextType cannot be null.");
        return switch (textType) {
            case TITLE -> this.titleAttributes.font();
            case SUBTITLE -> this.subtitleAttributes.font();
            case PLAIN -> this.plainAttributes.font();
            case UNIT -> this.unitAttributes.font();
        };
    }

    /**
     * Gets the internal gaps between texts associated with the specified {@code TextType}.
     *
     * @param textType the type of the text, cannot be {@code null}
     * @return the internal that matches the specified {@code TextType}
     * @throws NullPointerException if the provided {@code textType} is {@code null}
     */
    public Padding paddingByTextType(TextType textType) {
        Objects.requireNonNull(textType, "TextType cannot be null.");
        return switch (textType) {
            case TITLE -> this.titleAttributes.padding();
            case SUBTITLE -> this.subtitleAttributes.padding();
            case PLAIN -> this.plainAttributes.padding();
            case UNIT -> this.unitAttributes.padding();
        };
    }
}