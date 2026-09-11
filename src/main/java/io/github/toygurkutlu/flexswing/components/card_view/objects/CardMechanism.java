package io.github.toygurkutlu.flexswing.components.card_view.objects;


import io.github.toygurkutlu.flexswing.components.card_view.enums.MechanismType;
import io.github.toygurkutlu.flexswing.components.card_view.records.CardAttributes;

import java.util.Objects;

/**
 * Configuration class representing and managing the state mechanics of a {@code CardView}.
 * <p>
 * Each instance defines whether a specific feature (such as hover, selection, collapse,
 * or favorite) is enabled and holds its initial or current runtime status.
 * </p>
 *
 * @see MechanismType
 * @see CardAttributes
 */
public class CardMechanism {

    private final MechanismType mechanismType;
    private final boolean isEnabled;
    private boolean status;

    /**
     * Constructs a new configuration for the specified {@link MechanismType}.
     *
     * @param mechanismType the type of the card mechanism, cannot be {@code null}
     * @param isEnabled     {@code true} for enabling the card mechanism; {@code false} otherwise
     * @param initialStatus {@code true} for applying the card mechanism and updating the card UI; {@code false} otherwise
     *                      Affects only if card mechanism is enabled
     * @throws NullPointerException     if the provided {@code mechanismType} is {@code null}
     * @throws IllegalArgumentException if the {@code mechanismType} is {@link MechanismType#HOVERABLE} and
     *                                  {@code initialStatus} is {@code true}
     * @apiNote The configuration for the {@link MechanismType#HOVERABLE} mechanism cannot be initialized with
     * a {@code true} status. Therefore, for the {@link MechanismType#HOVERABLE} mechanism,
     * {@code initialStatus} must be {@code false}.
     */
    public CardMechanism(MechanismType mechanismType, boolean isEnabled, boolean initialStatus) {
        this.mechanismType = Objects.requireNonNull(mechanismType, "MechanismType cannot be null");
        this.isEnabled = isEnabled;
        if (mechanismType == MechanismType.HOVERABLE && initialStatus) {
            throw new IllegalArgumentException("Hoverable mechanism cannot have a true initial status.");
        }
        this.status = initialStatus;
    }

    /**
     * Gets the type of the mechanism.
     *
     * @return the {@code MechanismType} of this configuration
     */
    public MechanismType getMechanismType() {
        return mechanismType;
    }

    /**
     * Gets the enable status of the card mechanism.
     *
     * @return {@code true} if the card mechanism is enabled; {@code false} otherwise
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Gets the current status of the card mechanism.
     *
     * @return the current status of the card mechanism
     * @throws IllegalArgumentException if the card mechanism is not enabled
     */
    public boolean isStatus() {
        if (!isEnabled)
            throw new IllegalArgumentException("Cannot change status because the " + mechanismType + " mechanism is not enabled.");
        return status;
    }

    /**
     * Sets the current status of the card mechanism.
     *
     * @param status the new status of the card mechanism
     * @throws IllegalArgumentException if the card mechanism is not enabled
     */
    public void setStatus(boolean status) {
        if (!isEnabled)
            throw new IllegalArgumentException("Cannot change status because the " + mechanismType + " mechanism is not enabled.");
        this.status = status;
    }
}