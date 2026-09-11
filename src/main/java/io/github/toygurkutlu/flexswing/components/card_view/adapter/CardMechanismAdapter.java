package io.github.toygurkutlu.flexswing.components.card_view.adapter;

import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.card_view.listener.CardMechanismListener;

/**
 * An abstract adapter class for receiving card mechanism events.
 * <p>
 * The methods in this class are empty. This class exists as a convenience
 * for creating listener objects. Extend this class to override only the specific
 * methods for the events you are interested in, rather than implementing all
 * methods of the {@link CardMechanismListener} interface.
 * </p>
 *
 * @see CardMechanismListener
 * @see CardView
 */
public abstract class CardMechanismAdapter implements CardMechanismListener {

    /**
     * {@inheritDoc}
     * <p>
     * Override this method to perform custom actions when the collapse state changes.
     * </p>
     */
    @Override
    public void onCollapseStateChanged(boolean isCollapsed) {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Override this method to perform custom actions when the selection state changes.
     * </p>
     */
    @Override
    public void onSelectStateChanged(boolean isSelected) {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Override this method to perform custom actions when the favorite state changes.
     * </p>
     */
    @Override
    public void onFavoriteStateChanged(boolean isFavorited) {
    }
}