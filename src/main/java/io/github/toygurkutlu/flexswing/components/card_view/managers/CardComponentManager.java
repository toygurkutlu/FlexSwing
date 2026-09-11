package io.github.toygurkutlu.flexswing.components.card_view.managers;


import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import io.github.toygurkutlu.flexswing.components.card_view.records.ComponentBinder;
import io.github.toygurkutlu.flexswing.components.CardView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Manager that links between the components and their containers.
 *
 * @see #addComponent(JComponent, TextType)
 * @see #updateComponent(int, JComponent)
 * @see #updateComponent(int, JComponent, TextType)
 * @see #updateTextType(int, TextType)
 * @see #removeComponent(JComponent)
 * @see #removeComponent(int)
 * @see #clearComponents()
 * @see #getComponent(int)
 * @see #getCardComponents()
 * @see #getComponentPosition(JComponent)
 * @see #getTextType(JComponent)
 * @see #getTextType(int)
 * @see JComponent
 * @see ComponentBinder
 * @see CardView
 */
public class CardComponentManager {

    private final List<ComponentBinder> components;

    public CardComponentManager() {
        components = new ArrayList<>();
    }

    /**
     * Adds the new component and its {@code TextType} to the {@code ContentPanel} components collection.
     *
     * @param component the new component to be added, cannot be {@code null}
     * @param textType  the text type of the {@code component} {@code null}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>component</code> is <code>null</code></li>
     *                                    <li>When the provided <code>textType</code> object is <code>null</code></li>
     *                                  </ul>
     * @throws IllegalArgumentException if the {@code ContentPanel} contains the provided {@code component}
     */
    public void addComponent(JComponent component, TextType textType) {
        Objects.requireNonNull(component, "Component cannot be null.");
        Objects.requireNonNull(textType, "TextType cannot be null.");
        boolean alreadyExists = components.stream()
                .anyMatch(cc -> cc.component() == component);
        if (alreadyExists) throw new IllegalArgumentException("This component is already added to ContentPanel!");
        components.add(new ComponentBinder(component, textType));
    }

    /**
     * Updates the existing {@code component} with the new one according to the provided {@code position}.
     *
     * @param position  the {@code position} of the {@code component} that will be updated
     * @param component the new {@code component}, cannot be {@code null}
     * @throws NullPointerException      if the provided {@code component} is {@code null}
     * @throws IndexOutOfBoundsException the provided {@code position} is not valid
     * @throws IllegalArgumentException  if the {@code ContentPanel} contains the provided {@code component}
     */
    public void updateComponent(int position, JComponent component) {
        Objects.requireNonNull(component, "Component cannot be null.");
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        boolean alreadyExists = components.stream()
                .anyMatch(cc -> cc.component() == component);
        if (alreadyExists) throw new IllegalArgumentException("This component is already added to ContentPanel!");
        TextType textType = components.get(position).textType();
        components.set(position, new ComponentBinder(component, textType));
    }

    /**
     * Updates the existing {@code component} and its {@code TextType} with the new one according to the
     * provided {@code position}.
     *
     * @param position  the {@code position} of the {@code component} that will be updated
     * @param component the new {@code component}, cannot be {@code null}
     * @param textType  the new {@code TextType} of the provided {@code component}, cannot be {@code null}
     * @throws NullPointerException      if any of the following parameters are {@code null}:
     *                                   <ul>
     *                                     <li>When the provided <code>component</code> is <code>null</code></li>
     *                                     <li>When the provided <code>textType</code> object is <code>null</code></li>
     *                                   </ul>
     * @throws IndexOutOfBoundsException the provided {@code position} is not valid
     * @throws IllegalArgumentException  if the {@code ContentPanel} contains the provided {@code component}
     */
    public void updateComponent(int position, JComponent component, TextType textType) {
        Objects.requireNonNull(component, "Component cannot be null.");
        Objects.requireNonNull(textType, "TextType cannot be null.");
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        boolean alreadyExists = components.stream()
                .anyMatch(cc -> cc.component() == component);
        if (alreadyExists) throw new IllegalArgumentException("This component is already added to ContentPanel!");
        components.set(position, new ComponentBinder(component, textType));
    }

    /**
     * Updates {@code TextType} of the existing {@code component} with the new one according to the provided {@code position}.
     *
     * @param position the {@code position} of the {@code component} that will be updated
     * @param textType the new {@code TextType} of the {@code component} that matches with the provided
     *                 {@code position}, cannot be {@code null}
     * @throws NullPointerException      if the provided {@code textType} is {@code null}
     * @throws IndexOutOfBoundsException the provided {@code position} is not valid
     */
    public void updateTextType(int position, TextType textType) {
        Objects.requireNonNull(textType, "TextType cannot be null.");
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        JComponent currentComp = components.get(position).component();
        components.set(position, new ComponentBinder(currentComp, textType));
    }

    /**
     * Removes the component from the {@code ContentPanel} components collection.
     *
     * @param component the {@code component} to be removed, cannot be {@code null}
     * @throws NullPointerException     if the provided {@code component} is {@code null}
     * @throws IllegalArgumentException if the {@code ContentPanel} does not contain the provided {@code component}
     */
    public void removeComponent(JComponent component) {
        Objects.requireNonNull(component, "Component cannot be null.");
        int position = getComponentPosition(component);
        if (position == -1) throw new IllegalArgumentException("ContentPanel does not contain this component!");
        components.remove(position);
    }

    /**
     * Removes the component from the {@code ContentPanel} components collection according to the provided component position.
     *
     * @param position the position of the component to be removed
     * @throws IndexOutOfBoundsException if the {@code position} is not valid
     */
    public void removeComponent(int position) {
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        components.remove(position);
    }

    /**
     * Clears the {@code ContentPanel} components collection.
     */
    public void clearComponents() {
        components.clear();
    }

    /**
     * Gets the {@code component} that matches with the provided {@code position}.
     *
     * @return the {@code component} that matches with the provided {@code position}
     * @throws IndexOutOfBoundsException if the provided {@code position} is not valid
     */
    public JComponent getComponent(int position) {
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        return components.get(position).component();
    }

    /**
     * Gets the all available components from the {@code ContentPanel} components collection.
     *
     * @return the collection of the {@code CardComponent}
     */
    public List<ComponentBinder> getCardComponents() {
        return Collections.unmodifiableList(components);
    }

    /**
     * Gets the position of the provided {@code component}.
     *
     * @return the position of the provided {@code component}; {@code -1} if the {@code ContentPanel}
     * does not contain the provided {@code component}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code component} is {@code null}
     */
    public int getComponentPosition(JComponent component) {
        Objects.requireNonNull(component, "Component cannot be null.");
        return IntStream.range(0, components.size())
                .filter(i -> components.get(i).component() == component)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Gets the type of the text for the provided {@code component}.
     *
     * @param component the owner component of the text type, cannot be {@code null}
     * @return the {@code TextType} of the component
     * @throws NullPointerException     if the provided {@code component} is {@code null}
     * @throws IllegalArgumentException if the {@code ContentPanel} does not contain the provided {@code component}
     */
    public TextType getTextType(JComponent component) {
        Objects.requireNonNull(component, "Component cannot be null.");
        return components.stream()
                .filter(cc -> cc.component() == component)
                .map(ComponentBinder::textType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ContentPanel does not contain this component!"));

    }

    /**
     * Gets the type of the text for the component that matches with the provided {@code position}.
     *
     * @return the {@code TextType} of the component
     * @throws IndexOutOfBoundsException if the {@code position} is not valid
     */
    public TextType getTextType(int position) {
        if (isPositionNotValid(position)) throw new IndexOutOfBoundsException();
        return components.get(position).textType();
    }

    private boolean isPositionNotValid(int position) {
        return position < 0 || position >= components.size();
    }
}