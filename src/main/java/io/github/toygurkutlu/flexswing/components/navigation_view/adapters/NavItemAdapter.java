package io.github.toygurkutlu.flexswing.components.navigation_view.adapters;

import io.github.toygurkutlu.flexswing.components.NavigationView;
import io.github.toygurkutlu.flexswing.components.navigation_view.listeners.NavItemListener;

/**
 * An abstract adapter class for tracking selected item.
 * <p>
 * The methods in this class are empty. This class exists as a convenience
 * for creating listener objects. Extend this class to override only the specific
 * methods for the events you are interested in, rather than implementing all
 * methods of the {@link NavItemListener} interface.
 * </p>
 *
 * @see NavItemListener
 * @see NavigationView
 */
public abstract class NavItemAdapter implements NavItemListener {

    /**
     * {@inheritDoc}
     * <p>
     * Override this method to perform custom actions when the title is selected.
     * </p>
     */
    @Override
    public void onTitleSelected(int titleIndex){

    }

    /**
     * {@inheritDoc}
     * <p>
     * Override this method to perform custom actions when the subtitle is selected.
     * </p>
     */
    @Override
    public void onSubtitleSelected(int titleIndex, int subtitleIndex){

    }
}