package io.github.toygurkutlu.flexswing.components.card_view.enums;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.objects.StateColors;
import io.github.toygurkutlu.flexswing.components.card_view.records.CardAttributes;
import io.github.toygurkutlu.flexswing.components.card_view.listener.CardMechanismListener;
import io.github.toygurkutlu.flexswing.components.card_view.adapter.CardMechanismAdapter;

/**
 * Represents the available mechanisms of the {@code CardView}.
 *
 * @see CardView
 * @see CardMechanism
 * @see StateType
 * @see StateColors
 */
public enum MechanismType {

    /**
     * Represents the hover mechanism of the card.
     *
     * @apiNote When enabled, the hover mechanism automatically highlights the card based on
     * the configured {@code CardAttributes} when the mouse cursor enters the card boundaries.
     * @see CardAttributes
     */
    HOVERABLE,

    /**
     * Represents the select mechanism of the card.
     *
     * @apiNote When enabled, the select mechanism paints the card based on the configured
     * {@code CardAttributes} and allows the user to manage runtime status changes
     * by registering a {@link CardMechanismListener}.
     * @see CardAttributes
     * @see CardMechanismListener
     * @see CardMechanismAdapter
     * @see CardView#setCardMechanismListener(CardMechanismListener)
     */
    SELECTABLE,

    /**
     * Represents the collapse mechanism of the card.
     *
     * @apiNote When enabled, the collapse mechanism provides functionality to collapse (hide the content panel)
     * or expand (show the content panel) the card, and allows the user to manage runtime status
     * changes by registering a {@link CardMechanismListener}.
     * @see CardMechanismListener
     * @see CardMechanismAdapter
     * @see CardView#setCardMechanismListener(CardMechanismListener)
     */
    COLLAPSIBLE,

    /**
     * Represents the favorite mechanism of the card.
     *
     * @apiNote When enabled, the favorite mechanism adds a interactive favorite icon to the configured
     * {@link CardSection}, allows the user to toggle the favorite state, and enables runtime
     * status change management by registering a {@link CardMechanismListener}.
     * @see CardAttributes#favIconLocation()
     * @see CardMechanismListener
     * @see CardMechanismAdapter
     * @see CardView#setCardMechanismListener(CardMechanismListener)
     */
    FAVORITABLE
}