package io.github.toygurkutlu.flexswing.components.navigation_view.objects;


import javax.swing.*;
import java.util.Objects;

/**
 * Represents an item within the {@code NavigationView}.
 * <p>
 * • Can be used with or without subtitles.<br>
 * • If used with subtitles, subtitles will be grouped and will feature an expand/collapse mechanism.<br>
 * • If used without subtitles,  a simple {@code ListView} appearance (with or without icons) can be achieved.
 * </p>
 */
public class NavItem {

    private String title;
    private Icon titleIcon;
    private String[] subtitles;
    private Icon[] subtitleIcons;

    /**
     * Creates a {@code NavItem} instance for the {@code NavigationView}.
     *
     * @param title         The title text, cannot be null
     * @param titleIcon     The icon for the title
     * @param subtitles     An array of {@code String} objects representing the subtitles
     * @param subtitleIcons An array of {@code Icon} objects representing the subtitle icons
     * @throws NullPointerException     if the provided {@code title} is {@code null}
     * @throws IllegalArgumentException if one of the following conditions is met:
     *                                  <ul>
     *                                    <li>The provided <code>subtitles</code> is <code>null</code> but the provided
     *                                    <code>subtitleIcons</code> is not <code>null</code>.</li>
     *                                    <li>The provided <code>subtitles</code> and <code>subtitleIcons</code> lengths
     *                                    are different.</li>
     *                                  </ul>
     * @apiNote • Omit subtitles if you want to achieve a simple {@code ListView} appearance (with or without icons).<br>
     * • Provide subtitles if you want to group them and achieve a tree-like appearance.<br>
     */
    public NavItem(String title, Icon titleIcon, String[] subtitles,
                   Icon[] subtitleIcons) {
        this.title = Objects.requireNonNull(title);
        this.titleIcon = titleIcon;
        if (subtitles == null && subtitleIcons != null)
            throw new IllegalArgumentException("Subtitles cannot contains only icons.");
        if (subtitles != null && subtitleIcons != null && subtitles.length != subtitleIcons.length)
            throw new IllegalArgumentException("subtitles.length() and subtitleIcons.length() cannot be different.");
        this.subtitles = subtitles;
        this.subtitleIcons = subtitleIcons;
    }

    /**
     * Gets the title of {@code NavItem}.
     *
     * @return The title text of the {@code NavItem}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of {@code NavItem}.
     *
     * @param title The title text for the {@code NavItem}.
     * @apiNote Set the title if you want to group your subtitles.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the title icon of {@code NavItem}.
     *
     * @return The icon of the title, or {@code null} if the title does not have an icon.
     */
    public Icon getTitleIcon() {
        return titleIcon;
    }

    /**
     * Sets the title icon for {@code NavItem}.
     *
     * @param titleIcon The icon of the title.
     */
    public void setTitleIcon(Icon titleIcon) {
        this.titleIcon = titleIcon;
    }

    /**
     * Gets the subtitles of {@code NavItem}.
     *
     * @return An array of {@code String} objects representing the subtitles or {@code null} if not provided.
     */
    public String[] getSubtitles() {
        return subtitles;
    }

    /**
     * Sets the subtitles for {@code NavItem}.
     *
     * @param subtitles An array of {@code String} objects representing the subtitles.
     */
    public void setSubtitles(String[] subtitles) {
        this.subtitles = subtitles;
    }

    /**
     * Gets the icons of Subtitles
     *
     * @return An array of {@code Icon} objects representing the subtitle icons or {@code null} if not provided.
     */
    public Icon[] getSubtitleIcons() {
        return subtitleIcons;
    }

    /**
     * Sets the icons for the Subtitles.
     *
     * @param subtitleIcons An array of {@code Icon} objects representing the subtitle icons.
     */
    public void setSubtitleIcons(Icon[] subtitleIcons) {
        this.subtitleIcons = subtitleIcons;
    }
}