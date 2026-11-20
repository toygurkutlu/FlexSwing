package io.github.toygurkutlu.flexswing.components.button;

import io.github.toygurkutlu.flexswing.components.FlexButton;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

/**
 * Represents a set of state-dependent colors for a button, including main, hover, and pressed states.
 * <p>
 * This class can be used to configure background, foreground, or border colors.
 * </p>
 */
public class ButtonColors {

    /**
     * Represents the base (main) color.
     */
    private Color main;
    /**
     * Represents the color used on hover if hover is enabled.
     *
     * @see FlexButton#isHoverEnabled()
     * @see FlexButton#setHoverIsEnabled(boolean)
     */
    private Color hover;
    /**
     * Represents the color used when pressed if pressed effect is enabled.
     *
     * @see FlexButton#isPressedEffectEnabled()
     * @see FlexButton#setPressedEffectIsEnabled(boolean)
     */
    private Color pressed;

    /**
     * Constructs a new configuration for the button colors.
     *
     * @param main    the main color, cannot be {@code null}
     * @param hover   the hover color, cannot be {@code null}
     * @param pressed the pressed color, cannot be null {@code null}
     * @throws NullPointerException if any of the provided colors are {@code null}
     */
    public ButtonColors(@NotNull Color main, @NotNull Color hover, @NotNull Color pressed) {
        this.main = Objects.requireNonNull(main, "Main color cannot be null.");
        this.hover = Objects.requireNonNull(hover, "Hover color cannot be null.");
        this.pressed = Objects.requireNonNull(pressed, "Pressed color cannot be null.");
    }

    /**
     * Gets the main color of the configuration.
     *
     * @return the main color
     */
    public Color getMain() {
        return main;
    }

    /**
     * Sets the main color of the configuration.
     *
     * @param main the new main color, cannot be {@code null}
     */
    public void setMain(Color main) {
        this.main = Objects.requireNonNull(main, "Main color cannot be null.");
    }

    /**
     * Gets the hover color of the configuration.
     *
     * @return the hover color
     */
    public Color getHover() {
        return hover;
    }

    /**
     * Sets the hover color of the configuration.
     *
     * @param hover the new hover color, cannot be {@code null}
     * @apiNote The hover color is ignored if {@code FlexButton} is not configured as hoverable
     * @see FlexButton#isHoverEnabled()
     * @see FlexButton#setHoverIsEnabled(boolean)
     */
    public void setHover(Color hover) {
        this.hover = Objects.requireNonNull(hover,
                                            "Hover color cannot be null.");
    }

    /**
     * Gets the pressed color of the configuration.
     *
     * @return the pressed color
     */
    public Color getPressed() {
        return pressed;
    }

    /**
     * Sets the pressed color of the configuration.
     *
     * @param pressed the new pressed color, cannot be {@code null}
     * @apiNote The pressed color is ignored if the {@code FlexButton} is not configured as pressed effect is enabled
     * @see FlexButton#isPressedEffectEnabled()
     * @see FlexButton#setPressedEffectIsEnabled(boolean)
     */
    public void setPressed(Color pressed) {
        ButtonColors backgrounds = ButtonColors.defaultBackgrounds()
                .withMain(Color.ORANGE)
                .withHover(Color.PINK);
        this.pressed = Objects.requireNonNull(pressed, "Pressed color cannot be null.");
    }

    /**
     * Creates a new {@code ButtonColors} instance initialized with the default background colors
     * for all three states (main, hover, and pressed).
     *
     * @return a new {@code ButtonColors} instance with default background colors.
     * @apiNote The user can change any state color during creation by chaining {@code with...()} methods.
     * For example:<pre>{@code
     *         ButtonColors backgrounds = ButtonColors.defaultBackgrounds()
     *                 .withMain(Color.ORANGE)
     *                 .withHover(Color.PINK);}</pre>
     */
    public static ButtonColors defaultBackgrounds() {
        return new ButtonColors(new Color(25, 25, 25),
                                new Color(75, 75, 75),
                                new Color(155, 155, 155));
    }

    /**
     * Creates a new {@code ButtonColors} instance initialized with the default border colors
     * for all three states (main, hover, and pressed).
     *
     * @return a new {@code ButtonColors} instance with default border colors.
     * @apiNote The user can change any state color during creation by chaining {@code with...()} methods.
     * For example:<pre>{@code
     *         ButtonColors borderColors = ButtonColors.defaultBorderColors()
     *                 .withHover(Color.Color.DARK_GRAY);}</pre>
     */
    public static ButtonColors defaultBorderColors() {
        return new ButtonColors(new Color(25, 25, 25),
                                new Color(75, 75, 75),
                                new Color(155, 155, 155));
    }

    /**
     * Creates a new {@code ButtonColors} instance initialized with the default foreground colors
     * for all three states (main, hover, and pressed).
     *
     * @return a new {@code ButtonColors} instance with default foreground colors.
     * @apiNote The user can change any state color during creation by chaining {@code with...()} methods.
     * For example:<pre>{@code
     *         ButtonColors foregrounds = ButtonColors.defaultForegrounds()
     *                 .withMain(Color.BLUE)
     *                 .withPressed(new Color(0, 175, 175));}</pre>
     */
    public static ButtonColors defaultForegrounds() {
        return new ButtonColors(new Color(255, 255, 255),
                                new Color(25, 25, 25),
                                new Color(0, 0, 0));
    }

    /**
     * Creates a copy of this {@code ButtonColors} with the provided main color.
     *
     * @param main the new main color, cannot be {@code null}
     * @return a new {@code ButtonColors} instance with the updated main color
     * @throws NullPointerException if the provided main color is {@code null}
     */
    public ButtonColors withMain(Color main) {
        return new ButtonColors(Objects.requireNonNull(main, "Main color cannot be null."),
                                this.hover, this.pressed);
    }

    /**
     * Creates a copy of this {@code ButtonColors} with the provided hover color.
     *
     * @param hover the new hover color, cannot be {@code null}
     * @return a new {@code ButtonColors} instance with the updated hover color
     * @throws NullPointerException if the provided hover color is {@code null}
     * @apiNote The hover color is ignored if the {@code FlexButton} is not configured as hoverable
     * @see FlexButton#isHoverEnabled()
     * @see FlexButton#setHoverIsEnabled(boolean)
     */
    public ButtonColors withHover(Color hover) {
        return new ButtonColors(this.main, Objects.requireNonNull(hover, "Hover color cannot be null."),
                                this.pressed);
    }

    /**
     * Creates a copy of this {@code ButtonColors} with the provided pressed color.
     *
     * @param pressed the new pressed color, cannot be {@code null}
     * @return a new {@code ButtonColors} instance with the updated pressed color
     * @throws NullPointerException if the provided pressed color is {@code null}
     * @apiNote The pressed color is ignored if the {@code FlexButton} is not configured as pressed effect is enabled
     * @see FlexButton#isPressedEffectEnabled()
     * @see FlexButton#setPressedEffectIsEnabled(boolean)
     */
    public ButtonColors withPressed(Color pressed) {
        return new ButtonColors(this.main, this.hover,
                                Objects.requireNonNull(pressed, "Pressed color cannot be null."));
    }
}