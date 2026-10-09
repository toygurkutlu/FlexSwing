package io.github.toygurkutlu.flexswing.components.navigation_view.records;

import io.github.toygurkutlu.flexswing.records.ScrollAttributes;

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
 * @param background         the background color of the {@code NavigationView}
 * @param titleAttributes    the style attributes for the title items
 * @param subtitleAttributes the style attributes for the subtitle items
 * @param scrollAttributes   the style attributes for the vertical scroll bar
 * @see NavItemAttributes
 * @see ScrollAttributes
 */
public record NavAttributes(Color background, NavItemAttributes titleAttributes, NavItemAttributes subtitleAttributes,
                            ScrollAttributes scrollAttributes) {

    /**
     * Creates the default style attributes for the {@code NavigationView}.
     *
     * @return the {@code NavAttributes} of the {@code NavigationView}
     */
    public static NavAttributes defaultNavAttributes() {
        return new NavAttributes(new Color(40, 40, 40),
                                 NavItemAttributes.defaultTitleAttributes(),
                                 NavItemAttributes.defaultSubtitleAttributes(),
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
    public NavAttributes withBackground(Color color) {
        return new NavAttributes(Objects.requireNonNull(color, "Color cannot be null."),
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
    public NavAttributes withTitleAttributes(NavItemAttributes attr) {
        return new NavAttributes(this.background,
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
    public NavAttributes withSubtitleAttributes(NavItemAttributes attr) {
        return new NavAttributes(this.background,
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
    public NavAttributes withScrollAttributes(ScrollAttributes attr) {
        return new NavAttributes(this.background,
                                 this.titleAttributes,
                                 this.subtitleAttributes,
                                 Objects.requireNonNull(attr, "ScrollAttributes cannot be null."));
    }
}