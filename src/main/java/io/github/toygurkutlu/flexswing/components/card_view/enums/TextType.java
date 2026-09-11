package io.github.toygurkutlu.flexswing.components.card_view.enums;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.card_view.records.CardAttributes;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;
import io.github.toygurkutlu.flexswing.enums.StateType;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the typography roles or text types available within a {@code CardView}.
 * <p>
 * {@code CardView} automatically handles foreground color changes when the developer utilizes one of the
 * recommended {@code addContent(...)} methods, provided that the required configurations
 * ({@link MechanismType#HOVERABLE} and {@link MechanismType#SELECTABLE}) are enabled within the
 * {@link CardAttributes}.
 * </p>
 *
 * @see CardView
 * @see CardView#addContent(JComponent, TextType)
 * @see CardView#addContent(JComponent, TextType, GridBagConstraints, boolean)
 * @see CardAttributes
 * @see StateType
 * @see CardMechanism
 * @see MechanismType
 */
public enum TextType {

    /**
     * Represents title text.
     */
    TITLE,

    /**
     * Represents subtitle text.
     */
    SUBTITLE,

    /**
     * Represents plain text.
     */
    PLAIN,

    /**
     * Represents unit text.
     */
    UNIT
}