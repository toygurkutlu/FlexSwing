package io.github.toygurkutlu.flexswing.components.increaser;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.TextAlignment;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;

public class IncreaserAttribute<T> {
    /**
     * Represents the background color of the combo box.
     *
     * @see #ARROW_BUTTON_BACKGROUND
     */
    public static IncreaserAttribute<Color> DISPLAY_PANEL_BACKGROUND = new IncreaserAttribute<>("DISPLAY_PANEL_BACKGROUND");
    /**
     * Represents the foreground color of the combo box.
     *
     * @see #ARROW_BUTTON_ICON_COLOR
     */
    public static IncreaserAttribute<Color> DISPLAY_PANEL_FOREGROUND = new IncreaserAttribute<>("DISPLAY_PANEL_FOREGROUND");
    /**
     * Represents the font of the combo box.
     */
    public static IncreaserAttribute<Font> DISPLAY_PANEL_FONT = new IncreaserAttribute<>("DISPLAY_PANEL_FONT");
    /**
     * Represents the text alignment of the combo box.
     */
    public static IncreaserAttribute<TextAlignment> DISPLAY_PANEL_TEXT_ALIGNMENT = new IncreaserAttribute<>("DISPLAY_PANEL_TEXT_ALIGNMENT");
    /**
     * Represents the border color of the combo box.
     *
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static IncreaserAttribute<Color> DISPLAY_PANEL_BORDER_COLOR = new IncreaserAttribute<>("DISPLAY_PANEL_BORDER_COLOR");
    /**
     * Represents the corner radii of the outer border of the combo box.
     */
    public static IncreaserAttribute<Radii> DISPLAY_PANEL_RADII = new IncreaserAttribute<>("DISPLAY_PANEL_RADII");
    /**
     * Represents the icon color of the arrow button.
     */
    public static IncreaserAttribute<Color> ARROW_BUTTON_ICON_COLOR = new IncreaserAttribute<>("ARROW_BUTTON_ICON_COLOR");
    /**
     * Represents the background color of the arrow button.
     */
    public static IncreaserAttribute<Color> ARROW_BUTTON_BACKGROUND = new IncreaserAttribute<>("ARROW_BUTTON_BACKGROUND");
    /**
     * Represents the background color of the arrow button on hover.
     */
    public static IncreaserAttribute<Color> ARROW_BUTTON_BACKGROUND_HOVER = new IncreaserAttribute<>("ARROW_BUTTON_BACKGROUND_HOVER");
    /**
     * Represents the background color of the arrow button when pressed.
     */
    public static IncreaserAttribute<Color> ARROW_BUTTON_BACKGROUND_PRESSED = new IncreaserAttribute<>("ARROW_BUTTON_BACKGROUND_PRESSED");
    /**
     * Represents the border color of the arrow button.
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #POPUP_BORDER_COLOR
     */
    public static IncreaserAttribute<Color> ARROW_BUTTON_BORDER_COLOR = new IncreaserAttribute<>("ARROW_BUTTON_BORDER_COLOR");
    /**
     * Represents the background color of the popup (drop-down list).
     */
    public static IncreaserAttribute<Color> POPUP_BACKGROUND = new IncreaserAttribute<>("POPUP_BACKGROUND");
    /**
     * Represents the border color of the popup (drop-down list).
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static IncreaserAttribute<Color> POPUP_BORDER_COLOR = new IncreaserAttribute<>("POPUP_BORDER_COLOR");
    /**
     * Represents the background color of each item in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_BACKGROUND
     */
    public static IncreaserAttribute<Color> POPUP_ITEM_BACKGROUND = new IncreaserAttribute<>("POPUP_ITEM_BACKGROUND");
    /**
     * Represents the foreground color of each item in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_FOREGROUND
     */
    public static IncreaserAttribute<Color> POPUP_ITEM_FOREGROUND = new IncreaserAttribute<>("POPUP_ITEM_FOREGROUND");
    /**
     * Represents the background and hover background colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_BACKGROUND
     */
    public static IncreaserAttribute<Color> POPUP_SELECTED_ITEM_BACKGROUND = new IncreaserAttribute<>("POPUP_SELECTED_ITEM_BACKGROUND");
    /**
     * Represents the foreground and hover foreground colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_FOREGROUND
     */
    public static IncreaserAttribute<Color> POPUP_SELECTED_ITEM_FOREGROUND = new IncreaserAttribute<>("POPUP_SELECTED_ITEM_FOREGROUND");
    /**
     * Represents the font of the items in the popup (drop-down list).
     */
    public static IncreaserAttribute<Font> POPUP_FONT = new IncreaserAttribute<>("POPUP_FONT");
    /**
     * Represents whether popup contains tooltip.
     */
    public static IncreaserAttribute<Boolean> POPUP_HAS_TOOLTIP = new IncreaserAttribute<>("POPUP_HAS_TOOLTIP");
    /**
     * Represents the track color of the vertical scroll bar in the popup (drop-down list).
     */
    public static IncreaserAttribute<Color> POPUP_SCROLL_TRACK_COLOR = new IncreaserAttribute<>("POPUP_SCROLL_TRACK_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list).
     */
    public static IncreaserAttribute<Color> POPUP_SCROLL_THUMB_COLOR = new IncreaserAttribute<>("POPUP_SCROLL_THUMB_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list) while dragging.
     */
    public static IncreaserAttribute<Color> POPUP_SCROLL_THUMB_DRAG_COLOR = new IncreaserAttribute<>("POPUP_SCROLL_THUMB_DRAG_COLOR");
    /**
     * Represents the radius of thumb for the vertical scroll bar in the popup (drop-down list).
     */
    public static IncreaserAttribute<Integer> POPUP_SCROLL_THUMB_RADIUS = new IncreaserAttribute<>("POPUP_SCROLL_THUMB_RADIUS");
    /**
     * Represents the background color of the {@code FlexIncreaser}.
     */
    public static IncreaserAttribute<Color> BACKGROUND = new IncreaserAttribute<>("BACKGROUND");
    /**
     * Represents the border color of the {@code FlexIncreaser}.
     */
    public static IncreaserAttribute<Color> BORDER_COLOR = new IncreaserAttribute<>("BORDER_COLOR");
    /**
     * Represents the corner radii of the {@code FlexIncreaser}.
     */
    public static IncreaserAttribute<Radii> RADII = new IncreaserAttribute<>("RADII");
    /**
     * Represents the inner padding of the {@code FlexIncreaser}.
     */
    public static IncreaserAttribute<Padding> PADDING = new IncreaserAttribute<>("PADDING");
    /**
     * Represents the gap between items (decrease button, combo box and increaser button).
     */
    public static IncreaserAttribute<Integer> GAP_BETWEEN_ITEMS = new IncreaserAttribute<>("GAP_BETWEEN_ITEMS");
    /**
     * Represents the background color of the decrease and increase buttons.
     */
    public static IncreaserAttribute<Color> BUTTONS_BACKGROUND = new IncreaserAttribute<>("BUTTONS_BACKGROUND");
    /**
     * Represents the background color of the decrease and increase buttons on hover.
     */
    public static IncreaserAttribute<Color> BUTTONS_BACKGROUND_HOVER = new IncreaserAttribute<>("BUTTONS_BACKGROUND_HOVER");
    /**
     * Represents the background color of the decrease and increase buttons when pressed.
     */
    public static IncreaserAttribute<Color> BUTTONS_BACKGROUND_PRESSED = new IncreaserAttribute<>("BUTTONS_BACKGROUND_PRESSED");
    /**
     * Represents the foreground color of the decrease and increase buttons.
     */
    public static IncreaserAttribute<Color> BUTTONS_FOREGROUND = new IncreaserAttribute<>("BUTTONS_FOREGROUND");
    /**
     * Represents the foreground color of the decrease and increase buttons on hover.
     */
    public static IncreaserAttribute<Color> BUTTONS_FOREGROUND_HOVER = new IncreaserAttribute<>("BUTTONS_FOREGROUND_HOVER");
    /**
     * Represents the foreground color of the decrease and increase buttons when pressed.
     */
    public static IncreaserAttribute<Color> BUTTONS_FOREGROUND_PRESSED = new IncreaserAttribute<>("BUTTONS_FOREGROUND_PRESSED");
    /**
     * Represents the font of the decrease and increase buttons.
     */
    public static IncreaserAttribute<Font> BUTTONS_FONT = new IncreaserAttribute<>("BUTTONS_FONT");

    private final String name;

    public IncreaserAttribute(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}