package io.github.toygurkutlu.flexswing.objects;


import java.awt.*;
import java.util.Objects;
import io.github.toygurkutlu.flexswing.components.CardView;
import io.github.toygurkutlu.flexswing.components.FlexLabel;
import io.github.toygurkutlu.flexswing.components.FlexPanel;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.components.card_view.enums.MechanismType;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;

/**
 * Represents the colors for each {@code StateType} of the {@code FlexSwing} components such as {@code CardView},
 * {@code FLabel} and {@code FPanel}.
 * <p>
 * Developers can either create custom {@code StateColors} instances or use the default values
 * provided for each state, allowing a properly configured {@code CardView} to handle state transitions automatically.
 * </p>
 *
 * @see #StateColors(Color, Color, Color, Color)
 * @see #defaultHeaderBackgrounds()
 * @see #defaultHeaderForegrounds()
 * @see #defaultCardBorderColors()
 * @see #defaultContentBackgrounds()
 * @see #defaultContentForegrounds()
 * @see StateType
 * @see CardView
 * @see CardMechanism
 * @see MechanismType
 * @see FlexLabel
 * @see FlexPanel
 */
public class StateColors {

    private Color main;
    private Color mainHover;
    private Color selected;
    private Color selectedHover;

    /**
     * Constructs a new configuration for the states.
     *
     * @param main          the color of the {@link StateType#MAIN}, cannot be {@code null}
     * @param mainHover     the color of the {@link StateType#MAIN_HOVER}, cannot be {@code null}
     * @param selected      the color of the {@link StateType#SELECTED}, cannot be {@code null}
     * @param selectedHover the color of the {@link StateType#SELECTED_HOVER}, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>main</code> is <code>null</code></li>
     *                                <li>When the provided <code>mainHover</code> object is <code>null</code></li>
     *                                <li>When the provided <code>selected</code> is <code>null</code></li>
     *                                <li>When the provided <code>selectedHover</code> object is <code>null</code></li>
     *                              </ul>
     */
    public StateColors(Color main, Color mainHover, Color selected, Color selectedHover) {
        this.main = Objects.requireNonNull(main, "Main color cannot be null.");
        this.mainHover = Objects.requireNonNull(mainHover, "MainHover color cannot be null.");
        this.selected = Objects.requireNonNull(selected, "Selected color cannot be null.");
        this.selectedHover = Objects.requireNonNull(selectedHover, "SelectedHover color cannot be null.");
    }

    /**
     * Sets the same color for the all states.
     *
     * @param color the new color for the all states, cannot be {@code null}.
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * */
    public void setStateColors(Color color){
        this.main = Objects.requireNonNull(color,"Color cannot be null.");
        this.mainHover = color;
        this.selected = color;
        this.selectedHover = color;
    }

    /**
     * Sets the colors according to the provided {@code stateColors} object.
     *
     * @param stateColors the new {@code stateColors} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code stateColors} is {@code null}
     */
    public void setStateColors(StateColors stateColors) {
        Objects.requireNonNull(stateColors, "StateColors cannot be null.");
        this.main = stateColors.getMain();
        this.mainHover = stateColors.getMainHover();
        this.selected = stateColors.getSelected();
        this.selectedHover = stateColors.getSelectedHover();
    }

    /**
     * Sets the state colors according to the provided colors.
     *
     * @param main          the new color of the {@link StateType#MAIN}, cannot be {@code null}
     * @param mainHover     the new color of the {@link StateType#MAIN_HOVER}, cannot be {@code null}
     * @param selected      the new color of the {@link StateType#SELECTED}, cannot be {@code null}
     * @param selectedHover the new color of the {@link StateType#SELECTED_HOVER}, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>main</code> is <code>null</code></li>
     *                                <li>When the provided <code>mainHover</code> object is <code>null</code></li>
     *                                <li>When the provided <code>selected</code> is <code>null</code></li>
     *                                <li>When the provided <code>selectedHover</code> object is <code>null</code></li>
     *                              </ul>
     */
    public void setStateColors(Color main, Color mainHover, Color selected, Color selectedHover) {
        this.main = Objects.requireNonNull(main, "Main color cannot be null.");
        this.mainHover = Objects.requireNonNull(mainHover, "MainHover color cannot be null.");
        this.selected = Objects.requireNonNull(selected, "Selected color cannot be null.");
        this.selectedHover = Objects.requireNonNull(selectedHover, "SelectedHover color cannot be null.");
    }

    /**
     * Gets the color matches with the provided {@code StateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @return the color of the state
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public Color getColorByState(StateType stateType) {
        Objects.requireNonNull(stateType, "StateType cannot be null.");
        return switch (stateType) {
            case MAIN -> main;
            case MAIN_HOVER -> mainHover;
            case SELECTED -> selected;
            case SELECTED_HOVER -> selectedHover;
        };
    }

    /**
     * Sets the color matches with the provided {@code StateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @param color     the color of the state, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>stateType</code> is <code>null</code></li>
     *                                <li>When the provided <code>color</code> object is <code>null</code></li>
     *                              </ul>
     */
    public void setColorByState(StateType stateType, Color color) {
        switch (stateType) {
            case MAIN -> this.main = color;
            case MAIN_HOVER -> this.mainHover = color;
            case SELECTED -> this.selected = color;
            case SELECTED_HOVER -> this.selectedHover = color;
        }
    }

    /**
     * Gets the color of the {@link StateType#MAIN} state.
     *
     * @return the color of the  {@link StateType#MAIN} state
     */
    public Color getMain() {
        return main;
    }

    /**
     * Sets the color of the {@link StateType#MAIN}.
     *
     * @param color the new color of the  {@link StateType#MAIN}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setMain(Color color) {
        this.main = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the color of the {@link StateType#MAIN_HOVER} state.
     *
     * @return the color of the  {@link StateType#MAIN_HOVER} state
     */
    public Color getMainHover() {
        return mainHover;
    }

    /**
     * Sets the color of the {@link StateType#MAIN_HOVER}.
     *
     * @param color the new color of the  {@link StateType#MAIN_HOVER}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setMainHover(Color color) {
        this.mainHover =  Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the color of the {@link StateType#SELECTED} state.
     *
     * @return the color of the  {@link StateType#SELECTED} state
     */
    public Color getSelected() {
        return selected;
    }

    /**
     * Sets the color of the {@link StateType#SELECTED}.
     *
     * @param color the new color of the  {@link StateType#SELECTED}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setSelected(Color color) {
        this.selected = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the color of the {@link StateType#SELECTED_HOVER} state.
     *
     * @return the color of the  {@link StateType#SELECTED_HOVER} state
     */
    public Color getSelectedHover() {
        return selectedHover;
    }

    /**
     * Sets the color of the {@link StateType#SELECTED_HOVER}.
     *
     * @param color the new color of the  {@link StateType#SELECTED_HOVER}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setSelectedHover(Color color) {
        this.selectedHover = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the default colors for the {@code CardView}'s border.
     *
     * @return the default {@code StateColors} object for {@code CardView}'s border.
     */
    public static StateColors defaultCardBorderColors() {
        return new StateColors(new Color(40, 40, 40),
                               new Color(80, 80, 80),
                               new Color(0, 60, 0),
                               new Color(0, 100, 0));
    }

    /**
     * Gets the default colors for the {@code CardView}'s header panel background.
     *
     * @return the default {@code StateColors} object for the header panel background
     */
    public static StateColors defaultHeaderBackgrounds() {
        return new StateColors(new Color(40, 40, 40),
                               new Color(80, 80, 80),
                               new Color(0, 60, 0),
                               new Color(0, 80, 0));
    }

    /**
     * Gets the default colors for the {@code CardView}'s header text foreground.
     *
     * @return the default {@code StateColors} object for the header text foreground
     */
    public static StateColors defaultHeaderForegrounds() {
        return new StateColors(new Color(255, 255, 255),
                               new Color(200, 200, 200),
                               new Color(255, 255, 255),
                               new Color(200, 200, 200));
    }

    /**
     * Gets the default colors for the {@code CardView}'s content panel background.
     *
     * @return the default {@code StateColors} object for the content panel background
     */
    public static StateColors defaultContentBackgrounds() {
        return new StateColors(new Color(80, 80, 80),
                               new Color(120, 120, 120),
                               new Color(0, 100, 0),
                               new Color(0, 140, 0));
    }

    /**
     * Gets the default colors for the {@code CardView}'s content panel foreground.
     *
     * @return the default {@code StateColors} object for the content panel foreground
     */
    public static StateColors defaultContentForegrounds() {
        return new StateColors(new Color(255, 255, 255),
                               new Color(200, 200, 200),
                               new Color(255, 255, 255),
                               new Color(200, 200, 200));
    }
}