package io.github.toygurkutlu.flexswing.components.button;

import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;
import io.github.toygurkutlu.flexswing.components.FlexButton;

import java.awt.*;
/**
 * Represents a type-safe attribute key for customizing the appearance and behavior of a {@link FlexButton}.
 * <p>
 * These keys are passed to {@link FlexButton#setAttribute(ButtonAttribute, Object)} to modify specific
 * properties after the button has been instantiated:
 * <pre>{@code
 * button.setAttribute(ButtonAttribute.BACKGROUNDS, ButtonColors.defaultBackgrounds());
 * button.setAttribute(ButtonAttribute.HAS_BORDER, true);
 * }</pre>
 *
 * @param <T> the value type of the attribute
 * */
public class ButtonAttribute<T> {

    /**
     * Represents the background colors for all three states (main, hover, and pressed).
     */
    public static ButtonAttribute<ButtonColors> BACKGROUNDS = new ButtonAttribute<>("BACKGROUNDS");
    /**
     * Represents the main background color of the button.
     */
    public static ButtonAttribute<Color> BACKGROUND_MAIN = new ButtonAttribute<>("BACKGROUND_MAIN");
    /**
     * Represents the hover background color of the button.
     */
    public static ButtonAttribute<Color> BACKGROUND_HOVER = new ButtonAttribute<>("BACKGROUND_HOVER");
    /**
     * Represents the pressed background color of the button.
     */
    public static ButtonAttribute<Color> BACKGROUND_PRESSED = new ButtonAttribute<>("BACKGROUND_PRESSED");
    /**
     * Represents whether the border is painted or not.
     */
    public static ButtonAttribute<Boolean> HAS_BORDER = new ButtonAttribute<>("HAS_BORDER");
    /**
     * Represents the border colors for all three states (main, hover, and pressed).
     */
    public static ButtonAttribute<ButtonColors> BORDER_COLORS = new ButtonAttribute<>("BORDER_COLORS");
    /**
     * Represents the main border color of the button.
     */
    public static ButtonAttribute<Color> BORDER_COLOR_MAIN = new ButtonAttribute<>("BORDER_COLOR_MAIN");
    /**
     * Represents the hover border color of the button.
     */
    public static ButtonAttribute<Color> BORDER_COLOR_HOVER = new ButtonAttribute<>("BORDER_COLOR_HOVER");
    /**
     * Represents the pressed border color of the button.
     */
    public static ButtonAttribute<Color> BORDER_COLOR_PRESSED = new ButtonAttribute<>("BORDER_COLOR_PRESSED");
    /**
     * Represents the foreground colors for all three states (main, hover, and pressed).
     */
    public static ButtonAttribute<ButtonColors> FOREGROUNDS = new ButtonAttribute<>("FOREGROUNDS");
    /**
     * Represents the main foreground color of the button text.
     */
    public static ButtonAttribute<Color> FOREGROUND_MAIN = new ButtonAttribute<>("FOREGROUND_MAIN");
    /**
     * Represents the hover foreground color of the button text.
     */
    public static ButtonAttribute<Color> FOREGROUND_HOVER = new ButtonAttribute<>("FOREGROUND_HOVER");
    /**
     * Represents the pressed foreground color of the button text.
     */
    public static ButtonAttribute<Color> FOREGROUND_PRESSED = new ButtonAttribute<>("FOREGROUND_PRESSED");
    /**
     * Represents the font of the text.
     */
    public static ButtonAttribute<Font> FONT = new ButtonAttribute<>("FONT");
    /**
     * Represents the horizontal position of the text according to the icon.
     */
    public static ButtonAttribute<Integer> HORIZONTAL_TEXT_POSITION = new ButtonAttribute<>("HORIZONTAL_TEXT_POSITION");
    /**
     * Represents the vertical position of the text according to the icon.
     */
    public static ButtonAttribute<Integer> VERTICAL_TEXT_POSITION = new ButtonAttribute<>("VERTICAL_TEXT_POSITION");
    /**
     * Represents the gap between the icon and the text.
     */
    public static ButtonAttribute<Integer> ICON_TEXT_GAP = new ButtonAttribute<>("ICON_TEXT_GAP");
    /**
     * Represents the internal gaps between the input area and the button borders.
     */
    public static ButtonAttribute<Padding> PADDING = new ButtonAttribute<>("PADDING");
    /**
     * Represents the same internal gaps for all directions between the input area and the button borders.
     * <p>Use this key for setting same internal gaps for all directions with only one method.</p>
     */
    public static ButtonAttribute<Integer> PADDING_ALL = new ButtonAttribute<>("PADDING_ALL");
    /**
     * Represents the internal gap between the input area and the button border's top side.
     */
    public static ButtonAttribute<Integer> PADDING_TOP = new ButtonAttribute<>("PADDING_TOP");
    /**
     * Represents the internal gap between the input area and the button border's left side.
     */
    public static ButtonAttribute<Integer> PADDING_LEFT = new ButtonAttribute<>("PADDING_LEFT");
    /**
     * Represents the internal gap between the input area and the button border's bottom side.
     */
    public static ButtonAttribute<Integer> PADDING_BOTTOM = new ButtonAttribute<>("PADDING_BOTTOM");
    /**
     * Represents the internal gap between the input area and the button border's right side.
     */
    public static ButtonAttribute<Integer> PADDING_RIGHT = new ButtonAttribute<>("PADDING_RIGHT");
    /**
     * Represents the radii of the button corner.
     */
    public static ButtonAttribute<Radii> RADII = new ButtonAttribute<>("RADII");
    /**
     * Represents the same radius for all corners of the button border.
     * <p>Use this key if for setting same radius for all 4 corners with only one method.</p>
     */
    public static ButtonAttribute<Integer> RADIUS = new ButtonAttribute<>("RADIUS");
    /**
     * Represents the top left corner radius of the button border.
     */
    public static ButtonAttribute<Integer> TOP_LEFT_RADIUS = new ButtonAttribute<>("TOP_LEFT_RADIUS");
    /**
     * Represents the top right corner radius of the button border.
     */
    public static ButtonAttribute<Integer> TOP_RIGHT_RADIUS = new ButtonAttribute<>("TOP_RIGHT_RADIUS");
    /**
     * Represents the bottom left corner radius of the button border.
     */
    public static ButtonAttribute<Integer> BOTTOM_LEFT_RADIUS = new ButtonAttribute<>("BOTTOM_LEFT_RADIUS");
    /**
     * Represents the bottom right corner radius of the button border.
     */
    public static ButtonAttribute<Integer> BOTTOM_RIGHT_RADIUS = new ButtonAttribute<>("BOTTOM_RIGHT_RADIUS");

    private final String name;

    public ButtonAttribute(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
