package io.github.toygurkutlu.flexswing.components.card_view.records;

import io.github.toygurkutlu.flexswing.objects.StateColors;
import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.enums.StateType;
import java.awt.*;
import java.util.Objects;

/**
 * Style attributes of the {@code CardView HeaderPanel}.
 * <ul>
 *     <li>User can create custom style or can use default attributes.</li>
 *     <li>If decided to use default attributes, user can change any attributes using {@code with...()} methods.</li>
 * </ul>
 *
 * @param backgrounds    the background colors of the header. Each color represents one of the
 *                       {@link CardView}'s {@code StateType}
 * @param textAttributes the style attributes of the header text
 * @see #defaultHeaderAttributes()
 * @see #withBackgrounds(StateColors)
 * @see #withTextAttribute(TextAttributes)
 * @see #backgroundByState(StateType)
 * @see StateColors
 * @see StateType
 * @see TextAttributes
 */
public record HeaderAttributes(StateColors backgrounds,
                               TextAttributes textAttributes) {

    /**
     * Creates the default style attributes for the header.
     *
     * @return the {@code HeaderAttributes} of the header
     */
    public static HeaderAttributes defaultHeaderAttributes() {
        return new HeaderAttributes(StateColors.defaultHeaderBackgrounds(),
                                    TextAttributes.defaultHeaderTextAttributes());
    }

    /**
     * Creates a copy of this {@code HeaderAttributes} with the specified background colors.
     *
     * @param backgrounds the new background colors for the header, cannot be {@code null}
     * @return a new {@code HeaderAttributes} instance with the updated background colors
     * @throws NullPointerException if the provided {@code background} object is {@code null}
     * @see StateColors#StateColors(Color, Color, Color, Color)
     */
    public HeaderAttributes withBackgrounds(StateColors backgrounds) {
        return new HeaderAttributes(Objects.requireNonNull(backgrounds, "Backgrounds cannot be null."),
                                    this.textAttributes);
    }

    /**
     * Creates a copy of this {@code HeaderAttributes} with the specified header text style.
     *
     * @param textAttributes the new style for the header text, cannot be {@code null}
     * @return a new {@code HeaderAttributes} instance with the updated header text style
     * @throws NullPointerException if the provided {@code textAttributes} object is {@code null}
     */
    public HeaderAttributes withTextAttribute(TextAttributes textAttributes) {
        return new HeaderAttributes(this.backgrounds,
                                    Objects.requireNonNull(textAttributes, "TextAttributes cannot be null."));
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
}