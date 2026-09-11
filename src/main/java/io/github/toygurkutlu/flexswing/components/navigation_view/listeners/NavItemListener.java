package io.github.toygurkutlu.flexswing.components.navigation_view.listeners;

import io.github.toygurkutlu.flexswing.components.NavigationView;

/**
 * Listener interface for receiving runtime selection from {@code NavigationView}.
 *
 * @see NavigationView
 */
public interface NavItemListener {

    /**
     * Invoked when the title is selected.
     *
     * @param titleIndex the index of the selected title
     */
    void onTitleSelected(int titleIndex);

    /**
     * Invoked when the subtitle is selected.
     *
     * @param titleIndex the index of the selected subtitle's parent title
     * @param subtitleIndex the index of the selected subtitle
     */
    void onSubtitleSelected(int titleIndex, int subtitleIndex);
}
