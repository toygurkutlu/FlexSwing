package io.github.toygurkutlu.flexswing.components.card_view.records;

import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import javax.swing.*;
import java.util.Objects;

/**
 * An object that represents a component added to the content panel.
 *
 * @param component the component to be added to the content panel, cannot be {@code null}
 * @param textType  the typography role of the component, cannot be {@code null}
 * @see TextType
 */
public record ComponentBinder(JComponent component, TextType textType) {

    /**
     * Constructs a new {@code CardComponent} with strict {@code null} safety validation.
     *
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>component</code> is <code>null</code></li>
     *                                <li>When the provided <code>textType</code> is <code>null</code></li>
     *                              </ul>
     */
    public ComponentBinder {
        Objects.requireNonNull(component, "Component cannot be null.");
        Objects.requireNonNull(textType, "TextType cannot be null.");
    }
}