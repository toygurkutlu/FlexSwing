package io.github.toygurkutlu.flexswing.components.navigation_view.records;

import io.github.toygurkutlu.flexswing.objects.ScrollAttributes;

import java.awt.*;
import java.util.Objects;

/**
 * Style attributes for the {@code NavigationView}.
 *
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param background                 the background color of the {@code NavigationView}
 * @param isCollapseMechanismEnabled the config of the collapse mechanism
 * @param titleAttributes            the style attributes for the title items
 * @param subtitleAttributes         the style attributes for the subtitle items
 * @param scrollAttributes           the style attributes for the vertical scroll bar
 * @see NavViewItemConfig
 * @see ScrollAttributes
 */
public record NavigationViewConfig(Color background,
                                   boolean isCollapseMechanismEnabled,
                                   NavViewItemConfig titleAttributes,
                                   NavViewItemConfig subtitleAttributes,
                                   ScrollAttributes scrollAttributes) {

    /**
     * Creates the default style attributes for the {@code NavigationView}.
     *
     * @return the {@code NavAttributes} of the {@code NavigationView}
     */
    public static NavigationViewConfig defaultNavAttributes() {
        return new NavigationViewConfig(new Color(40, 40, 40),
                                        true,
                                        NavViewItemConfig.defaultTitleAttributes(),
                                        NavViewItemConfig.defaultSubtitleAttributes(),
                                        new ScrollAttributes(new Color(40, 40, 40),
                                                             new Color(55, 55, 55),
                                                             new Color(215, 215, 215),
                                                             6));
    }

    /**
     * Creates a copy of this {@code NavAttributes} with the specified background color.
     *
     * @param color the new background color for the {@code NavigationView}, cannot be {@code null}
     * @return a new {@code NavAttributes} instance with the updated background color
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public NavigationViewConfig withBackground(Color color) {
        return new NavigationViewConfig(Objects.requireNonNull(color, "Color cannot be null."),
                                        true,
                                        this.titleAttributes,
                                        this.subtitleAttributes,
                                        this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code NavAttributes} with the specified collapse mechanism.
     *
     * @param isEnabled {@code true} for enabling collapse mechanism; {@code false} otherwise
     * @return a new {@code NavAttributes} instance with the updated collapse mechanism
     */
    public NavigationViewConfig withCollapseMechanism(boolean isEnabled) {
        return new NavigationViewConfig(this.background,
                                        isEnabled,
                                        this.titleAttributes,
                                        this.subtitleAttributes,
                                        this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code NavAttributes} with the specified title style attributes.
     *
     * @param attr the new title attributes for the {@code NavigationView}, cannot be {@code null}
     * @return a new {@code NavAttributes} instance with the updated title style
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public NavigationViewConfig withTitleAttributes(NavViewItemConfig attr) {
        return new NavigationViewConfig(this.background,
                                        this.isCollapseMechanismEnabled,
                                        Objects.requireNonNull(attr, "NavItemAttributes cannot be null."),
                                        this.subtitleAttributes,
                                        this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code NavAttributes} with the specified subtitle style attributes.
     *
     * @param attr the new subtitle attributes for the {@code NavigationView}, cannot be {@code null}
     * @return a new {@code NavAttributes} instance with the updated subtitle style
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public NavigationViewConfig withSubtitleAttributes(NavViewItemConfig attr) {
        return new NavigationViewConfig(this.background,
                                        this.isCollapseMechanismEnabled,
                                        this.titleAttributes,
                                        Objects.requireNonNull(attr, "NavItemAttributes cannot be null."),
                                        this.scrollAttributes);
    }

    /**
     * Creates a copy of this {@code NavAttributes} with the specified scroll style attributes.
     *
     * @param attr the new scroll attributes for the {@code NavigationView}, cannot be {@code null}
     * @return a new {@code NavAttributes} instance with the updated scroll style
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public NavigationViewConfig withScrollAttributes(ScrollAttributes attr) {
        return new NavigationViewConfig(this.background,
                                        this.isCollapseMechanismEnabled,
                                        this.titleAttributes,
                                        this.subtitleAttributes,
                                        Objects.requireNonNull(attr, "ScrollAttributes cannot be null."));
    }
}