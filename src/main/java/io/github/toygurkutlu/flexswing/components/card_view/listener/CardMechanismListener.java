package io.github.toygurkutlu.flexswing.components.card_view.listener;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;


/**
 * Listener interface for receiving runtime status updates from {@link CardView} mechanisms.
 * <p>
 * Implement this interface to listen for collapse, select, and favorite state transitions
 * and perform custom operations (such as saving state to Java Preferences or a database).
 * </p>
 *
 * @see CardMechanism
 * @see CardView
 */
public interface CardMechanismListener {

    /**
     * Invoked when the collapse status of the CardView changes.
     *
     * @param isCollapsed {@code true} if the CardView is currently collapsed;
     *                    {@code false} if it is expanded
     */
    void onCollapseStateChanged(boolean isCollapsed);

    /**
     * Invoked when the selection status of the CardView changes.
     *
     * @param isSelected {@code true} if the CardView is currently selected;
     *                   {@code false} if the selection is removed
     */
    void onSelectStateChanged(boolean isSelected);

    /**
     * Invoked when the favorite status of the CardView changes.
     *
     * @param isFavorited {@code true} if the CardView is added to favorites;
     *                    {@code false} if it is removed from favorites
     */
    void onFavoriteStateChanged(boolean isFavorited);
}