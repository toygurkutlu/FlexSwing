package io.github.toygurkutlu.flexswing.enums;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.FlexLabel;
import io.github.toygurkutlu.flexswing.components.FlexPanel;
import io.github.toygurkutlu.flexswing.components.card_view.enums.MechanismType;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;
import io.github.toygurkutlu.flexswing.components.card_view.records.CardAttributes;
import io.github.toygurkutlu.flexswing.objects.StateColors;

/**
 * Represents the available runtime visual states of a {@code FlexSwing} components such as {@code CardView},
 * {@code FlexPanel} and {@code FlexLabel}.
 *
 * <p>These states ensure that the component UI is painted accordingly when the component configured properly.
 *
 * @see StateColors
 * @see CardView
 * @see FlexPanel
 * @see FlexLabel
 * @see CardAttributes
 * @see CardMechanism
 * @see MechanismType
 */
public enum StateType {
    /**
     * Represents standard state of the component.
     */
    MAIN,

    /**
     * Represents standard state with hover effect of the component.
     */
    MAIN_HOVER,

    /**
     * Represents selected state of the component.
     */
    SELECTED,

    /**
     * Represents selected state of the component.
     */
    SELECTED_HOVER
}